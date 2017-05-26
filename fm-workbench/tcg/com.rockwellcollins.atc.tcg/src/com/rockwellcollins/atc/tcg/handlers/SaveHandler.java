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

package com.rockwellcollins.atc.tcg.handlers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jkind.api.results.AnalysisResult;
import jkind.lustre.Type;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;

import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.tcg.suite.TestSuite;
import com.rockwellcollins.atc.tcg.util.TcgUtils;
import com.rockwellcollins.atc.tcg.views.TestSuiteLinker;
import com.rockwellcollins.atc.tcg.views.TestSuiteView;
import com.rockwellcollins.atc.tcg.writers.TcgXmlWriter;

public class SaveHandler extends NoElementHandler {
	private IHandlerActivation terminateActivation;
	private String startingFilePath;
    protected TestSuiteLinker linker;
	protected IHandlerService handlerService;
	protected boolean Debug = false;

	@Override
	protected final IStatus runJob(IProgressMonitor monitor) {
		handlerService = (IHandlerService) getWindow().getService(IHandlerService.class);

		try {
			return doAnalysis(monitor);
		} catch (Throwable e) {
			String messages = TcgUtils.getNestedMessages(e);
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
		}
	}
	
	protected IStatus doAnalysis(final IProgressMonitor monitor) {
		
		Thread analysisThread = new Thread() {

			String filePath;
			
			TestSuiteView view;
			
			@Override
			public void run() {
				activateTerminateHandler(monitor);
				Shell activeShell = getWindow().getShell();

				System.out.println("Saving test suite...");
				syncExec(() -> {
				   view = (TestSuiteView)getWindow().getActivePage().findView(TestSuiteView.ID);
				}); 
				if (view != null) {
					TestSuite suite = view.getInput();
					if (suite != null) {
						System.out.println("Bringing up file dialog...");
						syncExec(() -> {
							FileDialog dialog = new FileDialog(activeShell, SWT.SAVE);
							if (startingFilePath != null) {
								dialog.setFileName(startingFilePath);
							}
							String [] filterNames = new String [] {"XML Files", "All Files (*)"};
							String [] filterExtensions = new String [] {"*.xml", "*"};
							dialog.setFilterNames (filterNames);
							dialog.setFilterExtensions (filterExtensions);
							dialog.setOverwrite(true);

							filePath = dialog.open();
						});
						if (filePath != null) {
							startingFilePath = filePath;
							System.out.println("filePath: " + filePath);
							TestSuiteLinker linker = (TestSuiteLinker)view.getMenuListener().getLinker();
							AnalysisResult result = view.getMenuListener().getAnalysisResult();
							List<Type> types = linker.getAgreeProgram(result).globalTypes;
							try {
								TcgXmlWriter tcgXmlWriter = new TcgXmlWriter(filePath, types, false);
								tcgXmlWriter.writeSuite(suite);
								System.out.println("This would be where test suite written to " + filePath);
							}
							catch (FileNotFoundException fnfe){
								fnfe.printStackTrace();
							}
	//			    		TcgXmlWriter consoleWriter = new TcgXmlWriter(null, null, true);
	//			    		consoleWriter.writeSuite(suite);
						}
					}
					else {
						syncExec(() -> {
							MessageBox mb = new MessageBox(activeShell); 
							mb.setMessage("Error: no test suite loaded.  Please open a test suite\n");
							mb.open();
						});
					}

				}
				else {
					syncExec(() -> {
						MessageBox mb = new MessageBox(activeShell); 
						mb.setMessage("Error: test suite view needs to be active in order to save.\n");
						mb.open();});
				}
				deactivateTerminateHandler();
			}
		};
		analysisThread.start();
		return Status.OK_STATUS;
	}

	private void activateTerminateHandler(final IProgressMonitor monitor) {
		syncExec(() -> {
			terminateActivation = handlerService.activateHandler(TERMINATE_ID, new TerminateHandler(monitor));
		});
	}

	private void deactivateTerminateHandler() {
		syncExec(() -> {
			handlerService.deactivateHandler(terminateActivation);
		});
	}

	private void syncExec(Runnable runnable) {
		getWindow().getShell().getDisplay().syncExec(runnable);
	}

	@Override
	protected String getJobName() {
		return "TCG - Save Test Suite";
	}
}