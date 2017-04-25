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
package edu.uah.rsesc.aadlsimulator.ui.views;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;

import edu.uah.rsesc.aadlsimulator.PropertyStatus;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.ext.Command;
import edu.uah.rsesc.aadlsimulator.ui.ext.CommandContext;
import edu.uah.rsesc.aadlsimulator.ui.ext.SimpleCommandContext;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorUIExtensionService;

public class PropertiesView {
	private final SimulationUIService simulationUiService;
	private final SimulatorUIExtensionService extService;
	private SimulationEngine engine;
	private SimulationEngineState engineState;
	
	private final SimulatorStateListener stateListener = new SimulatorStateListener() {
		@Override
		public void onSimulatorStateChanged(final SimulatorState simulatorState) {
			final SimulationEngine engine = simulatorState.getSimulationEngine();
			PropertiesView.this.engine = engine;
			if(engine == null) {
				engineState = null;
			} else {
				engineState = simulatorState.getEngineState();
			}
			propertiesStateViewer.setInput(engineState);
		}
	};
	
	private PropertiesStateViewer propertiesStateViewer;

	@Inject
	public PropertiesView(final SimulationUIService simulationUiService, final SimulatorUIExtensionService extService) {
		this.simulationUiService = Objects.requireNonNull(simulationUiService, "simulationUiService must not be null");
		this.extService = Objects.requireNonNull(extService, "extService must not be null");
	}

	@PostConstruct
	void createView(final Composite parent) {
		propertiesStateViewer = new PropertiesStateViewer(parent);
		propertiesStateViewer.addPropertyMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(final IMenuManager manager) {
				// Only populate the menu if a single property is selected
				final IStructuredSelection selection = propertiesStateViewer.getSelection();
				if(selection.size() == 1 && engineState != null && engine != null) {
					final Object selectedProperty = selection.getFirstElement();
					
					// Populate context menu using command extensions
					final CommandContext cmdCtx = new SimpleCommandContext(selectedProperty, engineState, engine);
					
					for(final Command cmd : extService.getCommands()) {
						if(cmd.isAvailable(cmdCtx)) {
							manager.add(new Action(cmd.getLabel(cmdCtx)) {
								@Override
								public boolean isEnabled() {
									return cmd.isActivatable(cmdCtx);
								}
								
								@Override
								public void run() {
									cmd.activate(cmdCtx);
								}
							});
						}
					}
				}
			}
		});
		
		// Add listener and populate UI with current state
		simulationUiService.addStateChangeListener(stateListener);
		stateListener.onSimulatorStateChanged(simulationUiService.getCurrentState());
	}

	@PreDestroy
	public void preDestroy() {
		simulationUiService.removeStateChangeListener(stateListener);
	}
	
	public void setFocus() {
		if(propertiesStateViewer != null) {
			propertiesStateViewer.getControl().setFocus();
		}
	}
	
	public void setShowOnlyErrorsAndWarnings(final boolean value) {
		if(value) {
			propertiesStateViewer.setFilters(new PropertyStatusStyleFilter[] { new PropertyStatusStyleFilter(PropertyStatus.Style.OK, PropertyStatus.Style.INFO) });
		} else {
			propertiesStateViewer.resetFilters();						
		}		
	}
}
