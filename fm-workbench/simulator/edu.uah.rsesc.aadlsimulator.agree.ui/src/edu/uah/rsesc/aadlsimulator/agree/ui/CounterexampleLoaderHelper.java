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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.Property;
import jkind.results.UnknownProperty;
import jkind.util.BigFraction;

import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.IStatusAdapterConstants;
import org.eclipse.ui.statushandlers.StatusAdapter;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osate.aadl2.ComponentImplementation;
import org.osgi.framework.FrameworkUtil;
import edu.uah.rsesc.aadlsimulator.NotificationHandler;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineNotification;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProperty;
import edu.uah.rsesc.aadlsimulator.agree.engine.AGREEPropertyStatus;
import edu.uah.rsesc.aadlsimulator.agree.engine.AGREESimulationEngine;
import edu.uah.rsesc.aadlsimulator.agree.engine.AGREESimulationState;
import edu.uah.rsesc.aadlsimulator.launch.SimulationLaunch;
import edu.uah.rsesc.aadlsimulator.services.SimulationService;
import edu.uah.rsesc.aadlsimulator.ui.UIConstants;
import edu.uah.rsesc.aadlsimulator.ui.launch.SimulationLaunchShortcut;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BinaryExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BooleanLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraintFactory;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntegerLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.util.ExpressionFactory;

/**
 * Helper class which contains functionality to support loading test cases and counterexamples
 * The intention is to refactor and mature the interface to this helper class as needed to support implementation of the test case and counterexample loaders 
 */
class CounterexampleLoaderHelper {
	public enum Mode {
		MONOLITHIC(AGREESimulationEngine.ENGINE_TYPE_ID_MONOLITHIC, AGREESimulationEngine.ENGINE_TYPE_ID_MONOLITHIC_INDUCTIVE),
		SINGLE_LAYER(AGREESimulationEngine.ENGINE_TYPE_ID_SINGLE_LAYER, AGREESimulationEngine.ENGINE_TYPE_ID_SINGLE_LAYER_INDUCTIVE);
		
		public final String engineTypeId;
		public final String inductiveEngineTypeId;
		
		Mode(final String engineTypeId, final String inductiveEngineTypeId) {
			this.engineTypeId = engineTypeId;
			this.inductiveEngineTypeId = inductiveEngineTypeId;
		}
	}
	
	public void receiveCex(final ComponentImplementation compImpl, Property property, EObject agreeProperty, final Counterexample cex, final Map<String, EObject> refMap, final Mode mode) {		
		// Launch the simulation
		final SimulationService simulationService = Objects.requireNonNull((SimulationService)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(SimulationService.class), "Unable to retrieve simulation service");
		final SimulationLaunchShortcut launchShortcut = new SimulationLaunchShortcut();
		try {
			final boolean isInductiveCex = property instanceof UnknownProperty;
			final ILaunch launch = launchShortcut.launch(compImpl, isInductiveCex ? mode.inductiveEngineTypeId : mode.engineTypeId, ILaunchManager.RUN_MODE);
			
			// Get the simulation engine
			final SimulationEngine simulationEngine = getSimulationEngine(launch);
			if(simulationEngine instanceof AGREESimulationEngine) {
				final AGREESimulationEngine agreeSimulationEngine = (AGREESimulationEngine)simulationEngine;
				final SimulationUIService simulationUIService = Objects.requireNonNull((SimulationUIService)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(SimulationUIService.class), "Unable to retrieve simulation UI service");
				final Map<String, Object> signalNameToSimStateElementMap = buildAgreeNameToSimulationStateElementMap(agreeSimulationEngine);
				simulateCounterexample(cex, 0, signalNameToSimStateElementMap, agreeSimulationEngine, simulationService, simulationUIService);
			}			
		} catch (final Exception e) {
			simulationService.getExceptionHandler().handleException(e);			
		}		
	}
	
	private void simulateCounterexample(final Counterexample cex, final int startStepIndex, final Map<String, Object> signalNameToSimStateElementMap, final AGREESimulationEngine simulationEngine, final SimulationService simulationService, final SimulationUIService simulationUIService) {
		// For each frame in the counter example, step through the simulation and constrain the arguments to match those of the counter example
		for(int stepIndex = startStepIndex; stepIndex < cex.getLength(); stepIndex++) {
			simulationEngine.resetInputConstraints();
			
			// Constraint simulation variables based on the counterexample
			for(final Entry<String, Value> sv : cex.getStep(stepIndex).entrySet()) {
				final String signalName = sv.getKey();
				final Object stateElement = signalNameToSimStateElementMap.get(signalName);
				
				// Throw exception if the state element corresponding to the signal in the counterexample was not found unless the signal corresponds to a clock or a specification statement						
				if(stateElement == null) {
					// TODO: Cleanup
					// Ignore variables that do not have state elements. Need a way to verify that they can be safely ignored. Clocks may be ignored if they aren't
					// used. Variables for SpecStatements are okay to ignore but we don't have a way of getting the reference in all cases. 
					// In the case of a monolithic analysis, the references for all layers are not contained in the reference map
					/*
					final String renamedClockIdSuffix = agreeSimulationEngine.getSimulationProgram().getAgreeRenaming().forceRename(AgreeASTBuilder.clockIDSuffix);
					final boolean isClock = signalName.endsWith(renamedClockIdSuffix);
					final EObject ref = refMap.get(signalName);	
					if(!isClock && !(ref instanceof SpecStatement)) {
						//throw new RuntimeException("Unable to find state element for signal '" + signalName + "'");
					}
					*/
				} else {
					// Don't include hidden variables
					if(!simulationEngine.getCurrentState().isElementHidden(stateElement)) {
						// Constrain the next value of the variable to match the value contained in the counter example
						final InputConstraint ic = lustreValueToInputConstraint(sv.getValue());
						simulationEngine.setInputConstraint(stateElement, ic);
					}
				}
			}
			
			simulationEngine.stepForward();
		}
						
		simulationEngine.queueNotification(new NotificationHandler() {
			@Override
			public void handleNotification(final SimulationEngineNotification notification) {
				// Check that the number of simulated frames matches the expected number. It should match the length of the counter example
				if(cex.getLength() == notification.getEngineState().getNumberOfFrames()) {
					simulationEngine.resetInputConstraints();
				} else {
					// Determine whether there are unsatisfied properties that are halting the simulation
					boolean hasUnsatisfiedProperty = false;
					for(final SimulationProperty simProp : simulationEngine.getSimulationProgram().getSimulationProperties()) {
						if(notification.getEngineState().getPropertyStatus(simProp) == AGREEPropertyStatus.UNSATISFIED_ERROR) {
							hasUnsatisfiedProperty= true;
						}						
					}
					
					if(hasUnsatisfiedProperty) {					
						Display.getDefault().syncExec(new Runnable() {							
							@Override
							public void run() {
								final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
								final IWorkbenchPage activePage = window.getActivePage();
								if(activePage != null) {
									try {
										activePage.showView(UIConstants.PROPERTIES_VIEW_ID);
									} catch (final PartInitException e) {
										// Ignore
									}
								}

								final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
								final String errorMsg = "One or more properties could not be satisfied. Disable one or more properties from the Simulation Properties view and select Retry to continue.";
								class RetryDialog extends MessageDialog {
									public RetryDialog() {
										super(shell, "Unable to Simulate Counterexample", null, errorMsg, MessageDialog.ERROR, new String[] {"Retry", "End Simulation"}, 0);
										setShellStyle(SWT.CLOSE | SWT.MODELESS | SWT.BORDER | SWT.TITLE);
									    setBlockOnOpen(false);
									}
									
									@Override
									protected void buttonPressed(final int buttonId) {
										switch(buttonId) {
										case 0: // Retry
											// Try to resume the simulation
											simulationEngine.stepBackward();
											simulateCounterexample(cex, notification.getEngineState().getNumberOfFrames()-1, signalNameToSimStateElementMap, simulationEngine, simulationService, simulationUIService);
											break;
											
										case 1: // End Simulation
											simulationService.dispose(simulationEngine);
											break;
										}
										
										super.buttonPressed(buttonId);
									}
									
								}
								final MessageDialog dlg = new RetryDialog();
								dlg.open();
							}									
						});
					} else {
						final StatusAdapter statusAdapter = new StatusAdapter(new Status(Status.WARNING, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Simulation halted before every step in the counterexample was simulated.", new RuntimeException("The number of simulated frames does not match the length of the counterexample.")));
						statusAdapter.setProperty(IStatusAdapterConstants.TITLE_PROPERTY, "Unable to Simulate Counterexample");
						StatusManager.getManager().handle(statusAdapter, StatusManager.SHOW);
					}
				}
			}					
		});
	}
	
	private InputConstraint lustreValueToInputConstraint(final Value value) {
		if(value instanceof BooleanValue) {
			final BooleanLiteral ic = InputConstraintFactory.eINSTANCE.createBooleanLiteral();
			ic.setValue(((BooleanValue)value).value);
			return ic;
		} else if(value instanceof IntegerValue) {
			final IntegerLiteral ic = InputConstraintFactory.eINSTANCE.createIntegerLiteral();
			ic.setValue(((IntegerValue)value).value);
			return ic;
		} else if(value instanceof RealValue) {
			final BigFraction fraction = ((RealValue)value).value;
			final BinaryExpression ic = ExpressionFactory.createFraction(fraction.getNumerator(), fraction.getDenominator());
			return ic;
		} else {
			throw new RuntimeException("Unsupported value type: " + value.getClass());
		}
	}
	
	private Map<String, Object> buildAgreeNameToSimulationStateElementMap(final AGREESimulationEngine simEngine) {
		final AGREESimulationState simState = simEngine.getCurrentState();
		final Map<String, Object> result = new HashMap<String, Object>();
		
		for(Object element : simState.getRootElements()) {
			populateAgreeNameToSimulationStateElementMap(result, simState, element);
		}
		
		return result;
	}
	
	private void populateAgreeNameToSimulationStateElementMap(Map<String, Object> map, final AGREESimulationState simState, final Object element) {
		final String agreeName = simState.getAgreeName(element);
		if(agreeName != null) {
			map.put(agreeName, element);
		}
		
		for(final Object child : simState.getChildElements(element)) {
			populateAgreeNameToSimulationStateElementMap(map, simState, child);
		}
	}

	private SimulationEngine getSimulationEngine(final ILaunch launch) {
		if(launch instanceof SimulationLaunch) {
			return ((SimulationLaunch) launch).getSimulationEngine();
		}
		
		return null;
	}
}
