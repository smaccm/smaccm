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

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.IEvaluationService;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.osate.aadl2.Aadl2Factory;
import org.osate.aadl2.Aadl2Package;
import org.osgi.framework.FrameworkUtil;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;

public class SimulationPropertyTester extends PropertyTester {
	private static final String propertyNamespacePrefix = "edu.uah.rsesc.aadlsimulator.ui.";
	private static final String canStartProperty = "canStart";
	private static final String canStepForwardProperty = "canStepForward";
	private static final String canStepBackwardProperty = "canStepBackward";
	private static final String isRunning = "isRunning";
	private final SimulationUIService simulationUiService;
	private final GraphicalEditorSelectionHelper graphicalEditorSelectionHelper = new GraphicalEditorSelectionHelper();
	
	public SimulationPropertyTester() {
		simulationUiService = Objects.requireNonNull(EclipseContextFactory.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext()).get(SimulationUIService.class), "unable to get simulation UI service");
		simulationUiService.addStateChangeListener(new SimulatorStateListener() {
			@Override
			public void onSimulatorStateChanged(SimulatorState simulatorState) {
				final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if(window != null) {
					final IEvaluationService evaluationService = (IEvaluationService)window.getService(IEvaluationService.class);
					evaluationService.requestEvaluation(propertyNamespacePrefix + canStepForwardProperty);
					evaluationService.requestEvaluation(propertyNamespacePrefix + canStepBackwardProperty);
					evaluationService.requestEvaluation(propertyNamespacePrefix + isRunning);
				}
			}
		});
	}
	
	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		if(property.equals(canStartProperty)) {
			if(simulationUiService.isUserInterfaceLocked()) {
				return false;
			}
			
			if(receiver instanceof IFile) {
				final IFile file = (IFile)receiver;
				return file.getName().toLowerCase().endsWith(".aaxl2");
			} else if(receiver instanceof EObjectNode) {
				final Aadl2Package p = Aadl2Factory.eINSTANCE.getAadl2Package();
				final EClass eClass = ((EObjectNode) receiver).getEClass();
				return p.getComponentImplementation().isSuperTypeOf(eClass);       
			} else if(graphicalEditorSelectionHelper.isApplicable(receiver)) {
				return graphicalEditorSelectionHelper.getComponentImplementationByApplicableObject(receiver) != null;
			}			
		}  else if(property.equals(canStepForwardProperty)) {
			return simulationUiService.canStepForward();
		} else if(property.equals(canStepBackwardProperty)) {
			return simulationUiService.canStepBackward();
		} else if(property.equals(isRunning)) {
			return simulationUiService.getCurrentState().getSimulationEngine() != null;
		}
		
		return false;
	}
}
