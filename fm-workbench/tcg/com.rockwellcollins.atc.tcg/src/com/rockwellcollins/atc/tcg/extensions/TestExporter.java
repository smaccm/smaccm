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

package com.rockwellcollins.atc.tcg.extensions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osate.aadl2.ComponentImplementation;



import com.rockwellcollins.atc.tcg.suite.TestCase;
import com.rockwellcollins.atc.tcg.suite.TestSuite;
import com.rockwellcollins.atc.tcg.views.TestSuiteLinker;
import com.rockwellcollins.atc.tcg.views.TestSuiteLinkerFactory;
import com.rockwellcollins.atc.tcg.views.TestSuiteView;



import jkind.api.results.Renaming;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.Signal;
import edu.uah.rsesc.aadlsimulator.agree.ext.AGREESimulationExporter;

public class TestExporter implements AGREESimulationExporter {

	private IWorkbenchWindow window;
	private TestSuiteView testSuiteView;
	/*	@Override
	public void export(final ExportArguments args) {
		System.out.println("TEST EXPORTER: " + args.getComponentImplementation());
		for(int step = 0; step < args.getCounterexample().getLength(); step++) {
			System.out.println("\tSTEP: " + step);
			for(final Signal<Value> signal : args.getCounterexample().getSignals()) {
				System.out.println("\t\t" + args.getAgreeRenaming().rename(signal.getName()) + "(" + signal.getName() + ") := " + signal.getValues().get(step));
			}
		}
		
		System.out.println("DONE");
	}
*/
	@Override
	public void export(final ExportArguments args) {
		System.out.println("TEST EXPORTER: " + args.getComponentImplementation());
		storeTest(args.getComponentImplementation(), args.getCounterexample(), args.getAgreeRenaming());
		for(int step = 0; step < args.getCounterexample().getLength(); step++) {
			System.out.println("\tSTEP: " + step);
			for(final Signal<Value> signal : args.getCounterexample().getSignals()) {
				System.out.println("\t\t" + args.getAgreeRenaming().rename(signal.getName()) + "(" + signal.getName() + ") := " + signal.getValues().get(step));
			}
		}
		
		System.out.println("DONE");
	}
	
    public void storeTest(ComponentImplementation compImpl, Counterexample cex, Renaming renaming) {
    	IWorkbench wb = PlatformUI.getWorkbench();
    	window = wb.getActiveWorkbenchWindow();
    	IWorkbenchPage page = window.getActivePage();
    	
    	testSuiteView = (TestSuiteView) page.findView(TestSuiteView.ID);
    	System.out.println("part is...");
    	if (testSuiteView == null) {
    		System.out.println("  -> NULL");
    	} else {
    		System.out.println("  -> Non-null");
    	}

		syncExec(() -> {
			try {
				TestSuite suite = null;
				if (testSuiteView == null) {
					testSuiteView = (TestSuiteView) window.getActivePage()
							.showView(TestSuiteView.ID);
				} else {
					suite = testSuiteView.getInput();
				}
				
				// construct new suite if not defined.
				if (suite == null) {
					suite = new TestSuite();
					suite.setSystemImplUnderTest(compImpl.getQualifiedName());
					TestSuiteLinkerFactory linkerFactory = new TestSuiteLinkerFactory(compImpl, true); 
					TestSuiteLinker linker = linkerFactory.constructLinker();
					testSuiteView.setInput(suite, linker);
				} else {
					String topLevel = suite.getSystemImplUnderTest();
					if (!topLevel.equalsIgnoreCase(compImpl.getQualifiedName())) {
						MessageBox mb = new MessageBox(window.getShell(), SWT.ICON_ERROR);
						mb.setMessage("Error: Test suite operates against different component implementation than simulation.");
						mb.open();
						return; 
					}
				}
				TestCase tc = new TestCase("<Untitled Test Case>", "<Description>", cex);
				suite.addTestCase(tc);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		});
   	return;
    }

	private void syncExec(Runnable runnable) {
		window.getShell().getDisplay().syncExec(runnable);
	}
	
}
