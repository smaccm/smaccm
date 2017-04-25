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

import java.util.Objects;

import javax.inject.Named;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.ge.di.Activate;
import org.osate.ge.di.HasDoneChanges;
import org.osate.ge.di.IsAvailable;
import org.osate.ge.di.GetLabel;
import org.osate.ge.di.Names;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.views.VariablesViewPart;

/**
 * OSATE Graphical Editor Command for selecting the row in the Simulation Variables View(tablar display) which corresponds with a graphical element
 */
public class ShowInSimulationVariablesViewCommand {
	@GetLabel
	public String getLabel() {
		return "Show in Simulation Variables View";
	}

	@IsAvailable
	public boolean isAvailable(final SimulationUIService simulationUiService, @Named(Names.BUSINESS_OBJECTS) final Object[] bos) {
		if(simulationUiService.getCurrentState().getEngineState() != null && bos.length == 1) {
			final Object bo = bos[0];
			
			// Make the command available if there is only one business object selected and it is not the root System instance
			if((bo instanceof FeatureInstance || bo instanceof ComponentInstance) && bos[0] != ((InstanceObject)bos[0]).getSystemInstance()) {
				final InstanceObject io = (InstanceObject)bo;
				
				// Compare the references from the diagram and simulation system instances. Only show the command if they match
				final URI diagramSystemInstanceUri = EcoreUtil.getURI(io.getSystemInstance());
				final URI simulationSystemInstanceUri = EcoreUtil.getURI(simulationUiService.getCurrentState().getSimulationEngine().getSystemInstance());
				return diagramSystemInstanceUri != null && diagramSystemInstanceUri.equals(simulationSystemInstanceUri);
			}
		}
		
		return false;
	}
	
	@Activate
	public void activate(final SimulationUIService simulationUiService, @Named(Names.BUSINESS_OBJECTS) final Object[] bos) {
		try {			
			final SimulationEngineState engineState = simulationUiService.getCurrentState().getEngineState();
			Objects.requireNonNull(engineState, "engineState must not be null");
			final Object stateElement = Objects.requireNonNull(engineState.findElement((InstanceObject)bos[0]), "unable to retrieve state element");

			// Ensure variables view is visible
			final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			final IViewPart variablesViewPart = page.showView(UIConstants.VARIABLES_VIEW_ID);
			if(variablesViewPart instanceof VariablesViewPart) {
				// Scroll to and select the state element
				final VariablesViewPart variablesView = (VariablesViewPart)variablesViewPart;
				variablesView.scrollToAndSelectStateElement(stateElement);	
			}
		} catch (final PartInitException e) {
			throw new RuntimeException(e);
		}
	}
	
	@HasDoneChanges
	public boolean hasDoneChanges() {
		// Always return false because this command does not modify the diagram or AADL model.
		return false;
	}
}
