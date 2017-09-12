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

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import edu.uah.rsesc.aadlsimulator.ui.events.BeforeStepForwardEvent;
import edu.uah.rsesc.aadlsimulator.ui.events.SimulatorUIEvents;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorUIExtensionService;

// ViewPart which wraps the E4 Style VariablesView POJO.
public class VariablesViewPart extends ViewPart {
	private VariablesView variablesView;
	private final EventHandler eventHandler = new EventHandler() {
		@Override
		public void handleEvent(final Event event) {
			final Object data = event.getProperty(IEventBroker.DATA);
			if(variablesView != null && SimulatorUIEvents.BEFORE_STEP_FORWARD.equals(event.getTopic()) && data instanceof BeforeStepForwardEvent) {
				variablesView.beforeCreateStepArguments((BeforeStepForwardEvent)data);
			}
		}		
	};	
	
	public VariablesViewPart() {
		final SimulationUIService simulationUiService = Objects.requireNonNull(EclipseContextFactory.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext()).get(SimulationUIService.class), "unable to get simulation UI service");
		final SimulatorUIExtensionService extService = Objects.requireNonNull((SimulatorUIExtensionService)PlatformUI.getWorkbench().getService(SimulatorUIExtensionService.class), "unable to get simulation UI extension service");
		variablesView = new VariablesView(simulationUiService, extService);
	}
	
	@Override
	public void createPartControl(final Composite parent) {
		variablesView.createView(parent);;
		getEventBroker().subscribe(SimulatorUIEvents.BEFORE_STEP_FORWARD, eventHandler);
	}
	
	@Override
	public void dispose() {
		// Unregister the event handler
		getEventBroker().unsubscribe(eventHandler);
		
		// Destroy the variables view
		if(variablesView != null) {
			variablesView.preDestroy();
			variablesView = null;
		}
		
		super.dispose();
	}
	
	
	@Override
	public void setFocus() {
		 if(variablesView != null) {
			 variablesView.setFocus();
		 }
	}
	
	public void scrollToAndSelectStateElement(final Object stateElement) {
		variablesView.scrollToAndSelectStateElement(stateElement);
	}
	
	private IEventBroker getEventBroker() {
		final IEclipseContext ctx = Objects.requireNonNull(EclipseContextFactory.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext()), "unable to retrieve OSGI context");
		final IWorkbench workbench = Objects.requireNonNull(ctx.get(IWorkbench.class), "unable to retrieve E4 workbench");
		final IEventBroker eventBroker = Objects.requireNonNull(workbench.getApplication().getContext().get(IEventBroker.class), "unable to retrieve event broker");
		return eventBroker;
	}
	

}
