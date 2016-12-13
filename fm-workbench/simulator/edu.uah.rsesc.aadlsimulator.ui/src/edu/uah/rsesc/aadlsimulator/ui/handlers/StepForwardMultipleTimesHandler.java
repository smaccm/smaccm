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
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineNotification;
import edu.uah.rsesc.aadlsimulator.services.SimulationService;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.SelectStepsDialog;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;
import edu.uah.rsesc.aadlsimulator.ui.services.StepForwardCanceledException;

public class StepForwardMultipleTimesHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			final SimulationUIService simulationUIService = (SimulationUIService)Objects.requireNonNull(PlatformUI.getWorkbench().getService(SimulationUIService.class), "Unable to get simulation UI service");
			final SimulationService simulationService = Objects.requireNonNull((SimulationService)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(SimulationService.class), "Unable to retrieve simulation service");
			final SimulationEngine simulationEngine = simulationUIService.getCurrentState().getSimulationEngine();
	
			// Notify the UI service to prepare to step forward. This will validate input constraints which are being edited
			try {
				simulationUIService.prepareToStepForward();
			} catch (StepForwardCanceledException ex) {
				final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Error preparing to step forward", ex);
				StatusManager.getManager().handle(status, StatusManager.SHOW);
				return null;
			}
		
			final SelectStepsDialog dlg = new SelectStepsDialog(new Shell(Display.getDefault(), SWT.NONE));
			final int returnCode = dlg.open();
	
			final Object simControlLock = new Object();
			if(returnCode == 0 && dlg.getSteps() != null) {
				simulationUIService.lockUserInterface();
				new Job("Simulating") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						final SimulatorStateListener canceledListener = new SimulatorStateListener() {
							@Override
							public void onSimulatorStateChanged(SimulatorState simulatorState) {
								// Check if canceled
								if(simulatorState.getEngineState() == null) {
									// notify to exit job
									synchronized(simControlLock) {
										simControlLock.notify();
									}
								}
							}
						};
	
						simulationUIService.addStateChangeListener(canceledListener);
	
						try {
							monitor.beginTask("Simulate Steps", dlg.getSteps());
							runSimulation(monitor, simControlLock, simulationUIService, simulationService, simulationEngine, dlg);
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
							simulationEngine.clearPendingCommands();
						}
					}
				}.schedule();
			}
		} catch(final Exception ex) {
			final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Error", ex);
			StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
		}

		return null;
	}

	protected void runSimulation(final IProgressMonitor monitor, final Object simControlLock,
			final SimulationUIService simulationUIService, final SimulationService simulationService, final SimulationEngine simulationEngine, final SelectStepsDialog dlg) {
		for(int i = 1; i <= dlg.getSteps(); i++) {
			// Step forward
			simulationEngine.stepForward();
						
			final String subTaskMsg = "Simulating step " + i + " of " + dlg.getSteps();
			// Add state to list
			simulationEngine.queueNotification(new NotificationHandler() {
				@Override
				public void handleNotification(final SimulationEngineNotification notification) {
					if(monitor.isCanceled() || !notification.getEngineState().canStepForward()) {
						if(!notification.getEngineState().canStepForward()) {
							final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Simulation has halted.");
							StatusManager.getManager().handle(status, StatusManager.SHOW);
						}

						simulationEngine.clearPendingCommands();
						synchronized(simControlLock) {
							simControlLock.notify();
						}

						return;
					}

					// Update monitor
					monitor.subTask(subTaskMsg);
					monitor.worked(1);
				}
			});
		}
		
		simulationEngine.queueNotification(new NotificationHandler() {
			@Override
			public void handleNotification(final SimulationEngineNotification notification) {
				monitor.done();
				synchronized(simControlLock) {
					simControlLock.notify();
				}
			}
		});
	}
}
