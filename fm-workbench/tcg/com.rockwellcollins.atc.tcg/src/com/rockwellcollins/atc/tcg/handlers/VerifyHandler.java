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
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import jkind.JKindException;
import jkind.api.KindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;

import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferencesUtil;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.tcg.TcgException;
import com.rockwellcollins.atc.tcg.lustre.visitors.GenerateUfcObligationsVisitor;
import com.rockwellcollins.atc.tcg.obligations.ufc.TcgRenaming;
import com.rockwellcollins.atc.tcg.preferences.TcgPreferenceUtils;
import com.rockwellcollins.atc.tcg.suite.TestSuite;
import com.rockwellcollins.atc.tcg.suite.TestSuiteUtils;
import com.rockwellcollins.atc.tcg.views.TcgLinkerFactory;
import com.rockwellcollins.atc.tcg.views.TestCaseGeneratorResultsView;
import com.rockwellcollins.atc.tcg.writers.TcgWriterUtils;
import com.rockwellcollins.atc.tcg.writers.TcgXmlWriter;

public class VerifyHandler extends AadlHandler {
    protected Queue<JKindResult> queue = new ArrayDeque<>();
    protected AgreeResultsLinker linker = new AgreeResultsLinker();
	protected IHandlerService handlerService;
    protected AtomicReference<IProgressMonitor> monitorRef = new AtomicReference<>();
	protected boolean Debug = false;
	private IHandlerActivation terminateActivation;
	
	
	public boolean isRecursive() {
		return false;
	}

    protected String getNestedMessages(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        while (e != null) {
            if (e.getMessage() != null && !e.getMessage().isEmpty()) {
                pw.println(e.getMessage());
            }
            e = e.getCause();
        }
        pw.close();
        return sw.toString();
    }
	
	@Override
	protected final IStatus runJob(Element root, IProgressMonitor monitor) {
		handlerService = (IHandlerService) getWindow().getService(IHandlerService.class);

		if (!(root instanceof ComponentImplementation)) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Must select an AADL Component Implementation");
		}
        
		if (isRecursive() && AgreeUtils.usingKind2()){
            throw new AgreeException("Kind2 only supports monolithic verification");
        }

		try {
			TcgLinkerFactory factory = 
				new TcgLinkerFactory((ComponentImplementation)root, isMonolithic(), isRecursive()); 
            
            AnalysisResult result = factory.getAnalysisResult();
            linker = factory.getLinker();
            queue = factory.getWorkQueue();
            
            showView(result, linker);
            return doAnalysis(root, monitor);
        } catch (Throwable e) {
            String messages = getNestedMessages(e);
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
        }
	}

    protected void showView(final AnalysisResult result, final AgreeResultsLinker linker) {
		/*
		 * This command is executed while the xtext document is locked. Thus it must be async
		 * otherwise we can get a deadlock condition if the UI tries to lock the document,
		 * e.g., to pull up hover information.
		 */
        getWindow().getShell().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    TestCaseGeneratorResultsView page =
                            (TestCaseGeneratorResultsView) getWindow().getActivePage().showView(TestCaseGeneratorResultsView.ID);
                    page.setInput(result, linker);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void clearView() {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    TestCaseGeneratorResultsView page =
                            (TestCaseGeneratorResultsView) getWindow().getActivePage().showView(TestCaseGeneratorResultsView.ID);
                    page.setInput(new CompositeAnalysisResult("empty"), null);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });
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

    protected boolean isMonolithic() {
    	return TcgPreferenceUtils.getUseMonolithicAnalysis();
    }


	protected IStatus doAnalysis(final Element root, final IProgressMonitor monitor) {
		Thread analysisThread = new Thread() {
			@Override
			public void run() {
				activateTerminateHandler(monitor);
				KindApi api = PreferencesUtil.getKindApi();

				while (!queue.isEmpty() && !monitor.isCanceled()) {
					JKindResult result = queue.peek();
					NullProgressMonitor subMonitor = new NullProgressMonitor();
                    monitorRef.set(subMonitor);

					Program ufcProgram = constructUfcProgram(linker.getProgram(result), (TcgRenaming)linker.getRenaming(result)); 
					ufcProgram.getMainNode().properties.forEach(p -> result.addProperty(p));
					writeIntermediateFiles(linker.getProgram(result), ufcProgram); 

					try {
						System.out.println("Calling jkind...");
						api.execute(ufcProgram, result, monitor);
						System.out.println("executed API...");
						TestSuite testSuite = TestSuiteUtils.
							testSuiteFromJKindResult(result, 
									linker.getComponent(result).getQualifiedName(), result.getName(), result.getText());
						emitResult(testSuite);
						// showSuiteView(testSuite, linker);
						
					} catch (JKindException e) {
						System.out.println(result.getText());
						System.out.println("******** Error Occurred: HERE IS THE LUSTRE ********");
						System.out.println(linker.getProgram(result));
						break;
					} finally {
						deactivateTerminateHandler();
						System.out.println("UFC generation complete");
					}
                    queue.remove();
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
/*
	private JKindResult createJKindResult(Program ufcProgram, TcgRenaming renaming) {
		List<String> properties = ufcProgram.getMainNode().properties;
		return new JKindResult("Properties", properties, renaming);
	}
*/
	
	private void showSuiteView(final TestSuite result, final AgreeResultsLinker linker) {
		syncExec(() -> {
//			try {
//				TestSuiteView page = (TestSuiteView) getWindow().getActivePage()
//					.showView(TestSuiteView.ID, null, org.eclipse.ui.IWorkbenchPage.VIEW_VISIBLE);
//                AgreeResultsView page =
//                        (AgreeResultsView) getWindow().getActivePage().showView(AgreeResultsView.ID);
//				page.setInput(result, linker);
//			} catch (PartInitException e) {
//				e.printStackTrace();
//			}
		});
	}
	
	@Override
	protected String getJobName() {
		return "TCG - Generate tests";
	}
}