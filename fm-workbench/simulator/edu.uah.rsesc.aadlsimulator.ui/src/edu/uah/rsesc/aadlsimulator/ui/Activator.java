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

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.jogamp.common.util.JarUtil;

import edu.uah.rsesc.aadlsimulator.launch.SimulationLaunch;

public class Activator extends Plugin {
	private String originalPerspectiveId;
	private final String simulatorPerspectiveId = "edu.uah.rsesc.aadlsimulator.ui.SimulatorPerspective";
	private static Activator plugin;

	public static Activator getDefault() {
		return plugin;
	}

	public Activator() {
		// Add a launch listener that will switch to the AADL Simulator perspective when a simulation begins and then switch back to the original perspective when it ends
		final ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		launchManager.addLaunchListener(new ILaunchListener() {
			@Override
			public void launchRemoved(final ILaunch launch) {
				if(launch instanceof SimulationLaunch) {
					try {
						if (originalPerspectiveId != null) {
							PlatformUI.getWorkbench().showPerspective(originalPerspectiveId, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
							originalPerspectiveId = null;
						}
					} catch (CoreException e) {
						handleException("Error reverting to original perspective", e);
					}
				}
			}

			@Override
			public void launchChanged(final ILaunch launch) {
			}

			@Override
			public void launchAdded(final ILaunch launch) {
				// Special handling of simulation launches
				if(launch instanceof SimulationLaunch) {
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
							if(window != null) {
								final IWorkbenchPage activePage = window.getActivePage();
								if(activePage != null) {
									// Switch perspective as needed
									if (!activePage.getPerspective().getId().equalsIgnoreCase(simulatorPerspectiveId)) {
										originalPerspectiveId = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId();
										try {
											PlatformUI.getWorkbench().showPerspective(simulatorPerspectiveId, window);
										} catch (final WorkbenchException e) {
											handleException("Error showing simulator perspective", e);
										}
									}

									// Ensure the Status, Variables, and Properties views are visible
									try {
										activePage.showView(UIConstants.STATUS_VIEW_ID);
										activePage.showView(UIConstants.VARIABLES_VIEW_ID);
										activePage.showView(UIConstants.PROPERTIES_VIEW_ID);
									} catch (final PartInitException e) {
										handleException("Error showing simulator views", e);
									}						
								}
							}
						}
					});
				}
			}
		});
	}

	@Override
	public void start(org.osgi.framework.BundleContext context) throws Exception {
		super.start(context);
		JarUtil.setResolver(new JarUtil.Resolver() {

			@Override
			public URL resolve(URL url) {
				try {
					URL after = FileLocator.resolve(url);
					return (after);
				} catch (IOException ioexception) {
					return (url);
				}
			}
		});


	};

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	private void handleException(final String msg, final CoreException e) {
		StatusManager.getManager().handle(new Status(e.getStatus().getSeverity(), FrameworkUtil.getBundle(getClass()).getSymbolicName(), msg, e), StatusManager.LOG | StatusManager.SHOW);
	}

}