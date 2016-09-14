/*
Copyright (c) 2015, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package edu.uah.rsesc.aadlsimulator.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Named;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.widgets.Composite;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.ge.di.Names;
import org.osate.ge.di.Activate;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;

public class SimulatorTooltipContributor {
	private static final int INDENT_SIZE = 15;
	
	@Activate
	public void getTooltip(final SimulationUIService simulationUiService, final Composite parent, final @Named(Names.BUSINESS_OBJECT) Object bo) {
		final SimulatorState simulatorState = simulationUiService.getCurrentState();
		if(simulatorState.getEngineState() != null) {
			final SimulationEngineState engineState = simulatorState.getEngineState();
			if(engineState.getNumberOfFrames() > 0) {
				if(bo instanceof FeatureInstance || bo instanceof ComponentInstance) {
					final InstanceObject io = (InstanceObject)bo;
					
					// Compare the references from the diagram and simulation system instances. Only show tooltip if they match
					// This will ensure that the tooltips only appear for diagrams of instances of the same component implementation
					final URI diagramSystemInstanceUri = getResourceUri(io.getSystemInstance());
					final URI simulationSystemInstanceUri = getResourceUri(simulatorState.getSimulationEngine().getSystemInstance());
					if(diagramSystemInstanceUri != null && diagramSystemInstanceUri.equals(simulationSystemInstanceUri)) {
						final int frameIndex = simulatorState.getSelectedFrameIndex() == SimulatorState.NO_FRAME_INDEX_SELECTED ? engineState.getNumberOfFrames()-1 : simulatorState.getSelectedFrameIndex();
						if(frameIndex < engineState.getNumberOfFrames()) {
							// Handle the root instance object as a special case because there will not be a state element for it.
							if(io == io.getSystemInstance()) {
								addTooltipInfo(new StyledText(parent, SWT.NONE), engineState, frameIndex, io.getName(), null, engineState.getRootElements(), io, INDENT_SIZE);
							} else {						
								final Object stateElement = engineState.findElement(io);
								if(stateElement != null) {
									addStateElementsInfo(new StyledText(parent, SWT.NONE), engineState, frameIndex, Collections.singleton(stateElement), io, INDENT_SIZE);
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	private URI getResourceUri(final SystemInstance si) {
		final Resource res = si == null ? null : si.eResource();
		return res == null ? null : res.getURI();
	}
	/**
	 * Adds an entry containing a name, value, and child state elements to a StyledText object
	 */
	private void addTooltipInfo(final StyledText styledText, final SimulationEngineState engineState, final int frameIndex, final String name, final Object value, final Collection<?> childStateElements, final InstanceObject rootBo, int indentation) {
		assert styledText != null;
		assert engineState != null;
		assert childStateElements != null;

	    // Create Step Label
		if(styledText.getCharCount() == 0) {
			styledText.append("Step: " + (frameIndex + 1));
			styledText.append(System.getProperty("line.separator"));
			styledText.setLineIndent(0, 1, 4);
		}
		
		// Configure bullet
		final StyleRange bulletStyle = new StyleRange();
	    bulletStyle.metrics = new GlyphMetrics(0, 0, indentation);
	    final Bullet bullet = new Bullet(bulletStyle);
		styledText.setLineBullet(styledText.getLineCount()-1, 1, bullet);
		
		// Append name and value
		styledText.append(name);
		if(value != null) {
			styledText.append(": ");
			styledText.append(ValueFormatter.formatValue(value));
		}

		// Add Children
		addStateElementsInfo(styledText, engineState, frameIndex, childStateElements, rootBo, indentation + INDENT_SIZE);
	}
	
	private void addStateElementsInfo(final StyledText styledText, final SimulationEngineState engineState, final int frameIndex, final Collection<?> stateElements, final InstanceObject rootBo, int indentation) {
		// Sort variables by name
		final List<Object> sortedStateElements = new ArrayList<>(stateElements); 
		Collections.sort(sortedStateElements, new Comparator<Object>() {
			@Override
			public int compare(final Object se1, final Object se2) {
				return engineState.getElementName(se1).compareTo(engineState.getElementName(se2));
			}			
		});
		
		// Add Children
		for(final Object stateElement : sortedStateElements) {
			// Don't always display child state elements. 
			// For feature instances: show all children
			// For subcomponents, state elements for inner values should be shown but not feature instances.			
			if(!engineState.isElementHidden(stateElement) && (rootBo instanceof FeatureInstance || engineState.getElementInstanceObject(stateElement) == null || engineState.getElementInstanceObject(stateElement).getFullName().equals(rootBo.getFullName()))) {
				if(styledText.getCharCount() != 0) {
					styledText.append(System.getProperty("line.separator"));
				}
				addStateElementInfo(styledText, engineState, frameIndex, stateElement, rootBo, indentation);
			}
		}
	}
	
	
	/**
	 * Adds info regarding a state element and its children to a StyledText
	*/
	private void addStateElementInfo(final StyledText styledText, final SimulationEngineState engineState, final int frameIndex, final Object stateElement, final InstanceObject rootBo, int indentation) {
		assert styledText != null;
		assert engineState != null;
		assert stateElement != null;
		
		addTooltipInfo(styledText, engineState, frameIndex, engineState.getElementName(stateElement), engineState.getElementValue(frameIndex, stateElement), engineState.getChildElements(stateElement), rootBo, indentation);
	}
}
