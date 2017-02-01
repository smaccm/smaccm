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
import java.io.PrintWriter;
import java.util.List;

import jkind.JKindException;
import jkind.api.KindApi;
import jkind.api.results.JKindResult;
import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;

import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferencesUtil;
import com.rockwellcollins.atc.tcg.TcgException;
import com.rockwellcollins.atc.tcg.lustre.visitors.GenerateUfcObligationsVisitor;
import com.rockwellcollins.atc.tcg.obligations.ufc.TcgRenaming;
import com.rockwellcollins.atc.tcg.preferences.TcgPreferenceUtils;
import com.rockwellcollins.atc.tcg.suite.TestSuite;
import com.rockwellcollins.atc.tcg.suite.TestSuiteUtils;
import com.rockwellcollins.atc.tcg.util.TcgUtils;
import com.rockwellcollins.atc.tcg.views.TestSuiteLinker;
import com.rockwellcollins.atc.tcg.views.TestCaseGeneratorResultsView;
import com.rockwellcollins.atc.tcg.views.TestSuiteLinkerFactory;
import com.rockwellcollins.atc.tcg.views.TestSuiteView;
import com.rockwellcollins.atc.tcg.writers.TcgWriterUtils;
import com.rockwellcollins.atc.tcg.writers.TcgXmlWriter;

public class VerifyHandler extends AadlHandler {
    protected TestSuiteLinker linker = new TestSuiteLinker();
	protected IHandlerService handlerService;
	protected boolean Debug = false;
	private IHandlerActivation terminateActivation;
	

	@Override
	protected final IStatus runJob(Element root, IProgressMonitor monitor) {
		handlerService = (IHandlerService) getWindow().getService(IHandlerService.class);

		if (!(root instanceof ComponentImplementation)) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Must select an AADL Component Implementation");
		}

		try {
			ComponentImplementation ci = (ComponentImplementation)root;
			TestSuiteLinkerFactory linkerFactory = new TestSuiteLinkerFactory(ci, true); 
			linker = linkerFactory.constructLinker();
			
			System.out.println("About to perform analysis...");
			return doAnalysis(monitor);
		} catch (Throwable e) {
			String messages = TcgUtils.getNestedMessages(e);
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
		}
	}

	private Program constructUfcProgram(Program program, TcgRenaming renaming) {
		System.out.println("building ufc obligations...");
		GenerateUfcObligationsVisitor ufcGenerator = new GenerateUfcObligationsVisitor(program, renaming);
		ufcGenerator.setGenerateAssumptionObligations(TcgPreferenceUtils.getGenerateTestsForAssumptions());
		ufcGenerator.setGenerateEqObligations(TcgPreferenceUtils.getGenerateTestsForEquations());
		ufcGenerator.setGenerateBoundaryValueTests(TcgPreferenceUtils.getPerformBoundaryValueChecks());
		ufcGenerator.traverse();
		return ufcGenerator.constructNewProgram();
	}

	
	private void writeIntermediateFiles(Program program, Program ufcProgram) {
		if (Debug) {
			try {
				System.out.println("writing ufc obligations...");
				writeLustre("orig.lus", program);
				writeLustre("tcg.lus", ufcProgram);
			} catch (FileNotFoundException ff) {
				throw new TcgException("couldn't write .lus files in VerifyHandler", ff);
			}
		}
	}

	
	private void emitResult(TestSuite testSuite) {
		TcgPreferenceUtils.AutoSaveFileInfo fileInfo = TcgPreferenceUtils.getAutoSaveFileInfo();
		Boolean doAutoSave = fileInfo.autoSaveType != TcgPreferenceUtils.AutoSaveType.DO_NOT_SAVE;
		if (doAutoSave) {
			String fileName = fileInfo.saveDestination;
			if (fileInfo.autoSaveType == TcgPreferenceUtils.AutoSaveType.SAVE_TO_NEW_FILE) {
				fileName = TcgWriterUtils.createUniqueFileName(fileName);
			}
			
			try {
				
				testSuite.setName(TcgWriterUtils.getFileNameNoExtension(fileName));
				TcgXmlWriter writer = new TcgXmlWriter(fileName, null, false);
				writer.writeSuite(testSuite);
			}
			catch (Exception e) {
				throw new TcgException("Error creating XML output file", e);
			}
		}
	}
	
	
	/* 
	 * 
	 * To Dos: 
	 * 
	 * */
	protected IStatus doAnalysis(final IProgressMonitor monitor) {
		Thread analysisThread = new Thread() {
			@Override
			public void run() {
				activateTerminateHandler(monitor);
				KindApi api = PreferencesUtil.getKindApi();

				Program ufcProgram = constructUfcProgram(linker.getProgram(), linker.getRenaming()); 
				JKindResult result = createJKindResult(ufcProgram, linker.getRenaming());
				writeIntermediateFiles(linker.getProgram(), ufcProgram); 

				try {
					System.out.println("Calling jkind...");
					showGeneratorView(result, linker);
					System.out.println("constructed view...");
					api.execute(ufcProgram, result, monitor);
					System.out.println("executed API...");
					TestSuite testSuite = TestSuiteUtils.testSuiteFromJKindResult(result, linker.getComponent().getQualifiedName(), result.getName(), result.getText());
					emitResult(testSuite);
					showSuiteView(testSuite, linker);
					
				} catch (JKindException e) {
					System.out.println(result.getText());
					System.out.println("******** Error Occurred: HERE IS THE LUSTRE ********");
					System.out.println(linker.getProgram());
				} finally {
					deactivateTerminateHandler();
					System.out.println("UFC generation complete");
				}
				
				
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

	private void writeLustre(String filename, Program program) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(filename)) {
			writer.append(program.toString());
		}
	}

	private JKindResult createJKindResult(Program ufcProgram, TcgRenaming renaming) {
		List<String> properties = ufcProgram.getMainNode().properties;
		return new JKindResult("Properties", properties, renaming);
	}

	private void showGeneratorView(final JKindResult result, final TestSuiteLinker linker) {
		syncExec(() -> {
			try {
				TestCaseGeneratorResultsView page = (TestCaseGeneratorResultsView) getWindow().getActivePage()
						.showView(TestCaseGeneratorResultsView.ID);
				page.setInput(result, linker);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		});
	}

	
	private void showSuiteView(final TestSuite result, final TestSuiteLinker linker) {
		syncExec(() -> {
			try {
				TestSuiteView page = (TestSuiteView) getWindow().getActivePage()
						.showView(TestSuiteView.ID, null, org.eclipse.ui.IWorkbenchPage.VIEW_VISIBLE);
				page.setInput(result, linker);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Override
	protected String getJobName() {
		return "TCG - Generate tests";
	}
}