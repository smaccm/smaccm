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
package edu.uah.rsesc.aadlsimulator.agree.ui;

import java.util.Objects;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.IEvaluationService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.agree.LustreProgramProvider;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;

public class AGREESimulationPropertyTester extends PropertyTester {
	private static final String propertyNamespacePrefix = "edu.uah.rsesc.aadlsimulator.agree.ui.";
	private static final String frameStateContainsLustre = "frameStateContainsLustre";
	private SimulationUIService simulationUiService;
	
	public AGREESimulationPropertyTester() {
		final BundleContext bundleContext = FrameworkUtil.getBundle(getClass()).getBundleContext();
		simulationUiService = Objects.requireNonNull(EclipseContextFactory.getServiceContext(bundleContext).get(SimulationUIService.class), "unable to get simulation UI service");
		simulationUiService.addStateChangeListener(new SimulatorStateListener() {
			@Override
			public void onSimulatorStateChanged(SimulatorState simulatorState) {
				final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if(window != null) {
					final IEvaluationService evaluationService = (IEvaluationService)window.getService(IEvaluationService.class);
					evaluationService.requestEvaluation(propertyNamespacePrefix + frameStateContainsLustre);
				}
			}
		});
	}
	
	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		if(property.equals(frameStateContainsLustre)) {
			final SimulationEngineState engineState = simulationUiService.getCurrentState().getEngineState();
			return engineState instanceof LustreProgramProvider && ((LustreProgramProvider)engineState).getLustreProgram() != null;
		}
		return false;
	}

}
