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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.FrameworkUtil;

import edu.uah.rsesc.aadlsimulator.NotificationHandler;
import edu.uah.rsesc.aadlsimulator.SimulationEngineNotification;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.VariablesChartDialog;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;

public class OpenVariablesChartHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			final SimulationUIService simulationUIService = (SimulationUIService)Objects.requireNonNull(PlatformUI.getWorkbench().getService(SimulationUIService.class), "unable to get simulation UI service");
			
			// Open Value Visualization Chart
			simulationUIService.getCurrentState().getSimulationEngine().queueNotification(new NotificationHandler() {
				@Override
				public void handleNotification(final SimulationEngineNotification notification) {
					Display.getDefault().syncExec(new Runnable() {
						@Override
						public void run() {
							if(simulationUIService.getCurrentState().getEngineState().getNumberOfFrames() != 0) {
									simulationUIService.lockUserInterface();
									final Shell shell = new Shell(Display.getDefault().getActiveShell(), SWT.SHELL_TRIM & ~SWT.MIN);
	
									// Listening for cancellation
									final SimulatorStateListener simCanceledListener = new SimulatorStateListener() {
										@Override
										public void onSimulatorStateChanged(SimulatorState simulatorState) {
											if(simulationUIService.getCurrentState().getEngineState() == null) {
												if(shell != null && !shell.isDisposed()) {
													// Close view on cancel
													shell.close();
												}
											}
										}
									};
	
									simulationUIService.addStateChangeListener(simCanceledListener);
								try {
									new VariablesChartDialog(shell, simulationUIService);
								} finally {
									simulationUIService.removeStateChangeListener(simCanceledListener);
									simulationUIService.unlockUserInterface();
								}
							}
						}
					});
				}
			});
		} catch(final Exception ex) {
			final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Error", ex);
			StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
		}
		
		return null;
	}
}
