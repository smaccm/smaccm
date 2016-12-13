/*
Copyright (c) 2016, Rockwell Collins.
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
package edu.uah.rsesc.aadlsimulator.ui.handlers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.FrameworkUtil;

import edu.uah.rsesc.aadlsimulator.NotificationHandler;
import edu.uah.rsesc.aadlsimulator.Rational;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineNotification;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.SelectStepsDialog;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.SimulatePossibilitiesChartDialog;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.ChartElement;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.SimulatePossibilitiesChartDialog.SetValueSelectionAdapter;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;
import edu.uah.rsesc.aadlsimulator.ui.services.StepForwardCanceledException;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BinaryExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BooleanLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraintFactory;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntegerLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.util.ExpressionFactory;

public class SimulatePossibilitiesHandler extends AbstractHandler {
	public Object execute(ExecutionEvent execEvent) throws ExecutionException {
		try {
			final SimulationUIService simulationUIService = (SimulationUIService)Objects.requireNonNull(PlatformUI.getWorkbench().getService(SimulationUIService.class), "unable to get simulation UI service");
			final Shell shell = new Shell(Display.getDefault().getActiveShell(), SWT.SHELL_TRIM & ~SWT.MIN);
			// Notify the UI service to prepare to step forward. This will validate input constraints which are being edited
			simulationUIService.prepareToStepForward();
			
			final List<SimulationEngineState> simulationEngineStates = new ArrayList<>();
			final SelectStepsDialog dlg = new SelectStepsDialog(new Shell(Display.getDefault(), SWT.NONE));
			final int returnCode = dlg.open();
	
			// Object to control when job is done/canceled
			final Object simControlLock = new Object();
			if(returnCode == 0 && dlg.getSteps() != null) {
				simulationUIService.lockUserInterface();
				new Job("Simulate Possibilities") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {					
						final SimulationEngine simulationEngine = simulationUIService.getCurrentState().getSimulationEngine().fork();
						try {
							final SimulationEngineState initialState = simulationEngine.getCurrentState();							
							final SimulatorStateListener canceledListener = new SimulatorStateListener() {
								@Override
								public void onSimulatorStateChanged(SimulatorState simulatorState) {
									// Check if canceled
									if(simulationUIService.getCurrentState().getEngineState() == null) {
										// notify to exit job
										synchronized(simControlLock) {
											simControlLock.notify();
										}
									}
								}
							};						
							simulationUIService.addStateChangeListener(canceledListener);
							
							try {
								monitor.beginTask("Simulate", dlg.getSteps());
								runSimulation(monitor, simControlLock, simulationUIService, simulationEngine, simulationEngineStates, dlg);
								openChart(monitor, shell, simControlLock, simulationUIService, simulationEngine, simulationEngineStates, initialState);
								synchronized(simControlLock) {
									simControlLock.wait();
								}
								
								return monitor.isCanceled() ? Status.CANCEL_STATUS : Status.OK_STATUS;
							} catch(final InterruptedException e) {
								Thread.currentThread().interrupt();
								throw new RuntimeException(e);
							} catch(final Exception e) {
								throw e;
							} finally {
								simulationUIService.unlockUserInterface();
								simulationUIService.removeStateChangeListener(canceledListener);
							}		
						} finally {
							simulationEngine.dispose();
						}
					}
				}.schedule();
			}
		} catch(StepForwardCanceledException ex) {
			final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Error preparing to step forward", ex);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
			return null;
		} catch(final Exception ex) {
			final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Error", ex);
			StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
		}
		
		return null;
	}

	private void runSimulation(final IProgressMonitor monitor, final Object simControlLock,
			final SimulationUIService simulationUIService, final SimulationEngine simulationEngine,
			final List<SimulationEngineState> simulationEngineStates, final SelectStepsDialog dlg) {

		// Step forward
		for(int i = 0; i < dlg.getSteps(); i++) {
			simulationEngine.stepForward();
			
			// Add state to list
			simulationEngine.queueNotification(new NotificationHandler() {
				@Override
				public void handleNotification(final SimulationEngineNotification notification) {
					if(monitor.isCanceled()) {
						synchronized(simControlLock) {
							simControlLock.notify();
						}

						return;
					}

					final SimulationEngineState simulationState = simulationEngine.getCurrentState();
					simulationEngineStates.add(simulationState);

					// Update monitor
					monitor.subTask("Simulating step " + simulationEngineStates.size() + " of " + dlg.getSteps());
					monitor.worked(1);
				}
			});
			
			// Step backward
			simulationEngine.stepBackward();
		}
	}

	private void openChart(final IProgressMonitor monitor, final Shell shell, final Object simControlLock,
			final SimulationUIService simulationUIService, final SimulationEngine simulationEngine,
			final List<SimulationEngineState> simulationEngineStates, final SimulationEngineState initialState) {
		simulationEngine.queueNotification(new NotificationHandler() {
			@Override
			public void handleNotification(final SimulationEngineNotification notification) {
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						try {
							if(!simulationEngineStates.isEmpty() && !monitor.isCanceled()) {
								// Listening for cancellation
								final SimulatorStateListener simListener = new SimulatorStateListener() {
									@Override
									public void onSimulatorStateChanged(SimulatorState simulatorState) {
										if(simulationUIService.getCurrentState().getEngineState() == null) {
											if(shell != null && !shell.isDisposed()) {
												// Close SimulatingPossibilitiesView on cancel
												shell.close();
	
												// Notify on cancel
												synchronized (simControlLock) {
													simControlLock.notify();
												}
											}
										}
									}
								};
	
								simulationUIService.addStateChangeListener(simListener);
								try {
									// Notify to complete progress monitor
									synchronized(simControlLock) {
										simControlLock.notify();
									}
		
									monitor.done();
								
									// Lock simulation while chart is open
									simulationUIService.lockUserInterface();
									final SimulatePossibilitiesChartDialog view = new SimulatePossibilitiesChartDialog(shell, simulationUIService, simulationEngineStates);
									final SetValueSelectionAdapter setValueSelectionAdapter = view.getSetValueSelectionAdapter();
									final SimulationEngineState returnedState = setValueSelectionAdapter.getSelectedState();
	
									if(returnedState != null) {
										setSelectedState(view, returnedState);
									}
								} finally {
									simulationUIService.removeStateChangeListener(simListener);
								}
							} 
						} finally {
							simulationUIService.unlockUserInterface();
						}
					}

					private void setSelectedState(final SimulatePossibilitiesChartDialog view, final SimulationEngineState returnedState) {
						final SimulationEngine originalSimulationEngine = simulationUIService.getCurrentState().getSimulationEngine();
						for(final ChartElement chartElement : view.getChartElements()) {
							final Object simulationStateElement = chartElement.getSimulationStateElement();

							if(simulationStateElement != null) {
								final Object value = returnedState.getElementValue(returnedState.getNumberOfFrames()-1, simulationStateElement);

								if(value != null) {
									if(value instanceof BigInteger) {
										final IntegerLiteral il = InputConstraintFactory.eINSTANCE.createIntegerLiteral();
										il.setValue((BigInteger)value);

										originalSimulationEngine.setInputConstraint(simulationStateElement, il);
									} else if(value instanceof Rational) {
										final Rational rational = (Rational)value;
										final BinaryExpression be = ExpressionFactory.createFraction(rational.numerator, rational.denominator);

										originalSimulationEngine.setInputConstraint(simulationStateElement, be);
									} else if(value instanceof Boolean) {
										final BooleanLiteral bl = InputConstraintFactory.eINSTANCE.createBooleanLiteral();
										bl.setValue((Boolean)value);

										originalSimulationEngine.setInputConstraint(simulationStateElement, bl);
									} else {
										throw new RuntimeException("Unhandled Type: " + returnedState.getElementType(simulationStateElement));
									}
								}
							}
						}

						simulationUIService.stepForward();
						originalSimulationEngine.resetInputConstraints();
						
						for(final ChartElement chartElement : view.getChartElements()) {
							final Object simulationStateElement = chartElement.getSimulationStateElement();
							if(simulationStateElement != null) {
								originalSimulationEngine.setInputConstraint(simulationStateElement, initialState.getElementInputConstraintForNextFrame(simulationStateElement));
							}
						}
					}
				});
			}
		});
	}
}
