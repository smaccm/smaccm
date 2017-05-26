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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osate.aadl2.ComponentImplementation;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.tcg.TcgException;
import com.rockwellcollins.atc.tcg.preferences.TcgPreferenceUtils;
import com.rockwellcollins.atc.tcg.readers.TcgXmlReader;
import com.rockwellcollins.atc.tcg.suite.TestSuite;
import com.rockwellcollins.atc.tcg.util.TcgUtils;
import com.rockwellcollins.atc.tcg.views.TestSuiteLinker;
// import com.rockwellcollins.atc.tcg.views.TestSuiteLinkerFactory;
import com.rockwellcollins.atc.tcg.views.TestSuiteView;

public class OpenHandler extends NoElementHandler {
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
	
	void printClassifiers() {
		EList<IEObjectDescription> classifiers = EMFIndexRetrieval.getAllClassifiersInWorkspace();
		for (IEObjectDescription cl: classifiers) {
			System.out.println("Classifier: " + cl.getName() + "; qualified name: " + cl.getQualifiedName());
		}
	}
	
	protected IStatus doAnalysis(final IProgressMonitor monitor) {
		try {
			printClassifiers();
			System.out.println("Loading test suite...");
			TestSuite testSuite = loadTests();
			if (testSuite != null) {
				ComponentImplementation ci = TcgUtils.getComponentImplFromString(testSuite.getSystemImplUnderTest());
				// TestSuiteLinkerFactory linkerFactory = new TestSuiteLinkerFactory(ci, true); 
				// linker = linkerFactory.constructLinker();
				// showSuiteView(testSuite, linker);
			}
		} catch (Exception e) {
			System.out.println("Error" + e.toString());
			throw e;
		}
		finally {
			System.out.println("Test suite loading complete");
		}
		return Status.OK_STATUS;
	}

	File location;

	private TestSuite loadTests() throws TcgException {
		try {
		    Shell activeShell = getWindow().getShell();
			syncExec(() -> { location = TcgPreferenceUtils.getTestCaseOpenFileDialog(activeShell, null); });
			if (location != null) {
				InputStream targetStream = new FileInputStream(location);
				TestSuite ts = new TestSuite();
				TcgXmlReader reader = new TcgXmlReader(targetStream); 
				reader.readSuite(ts);
				ts.setState(TestSuite.State.LOADED);
				return ts;
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new TcgException("Error loading test file", e);
		}
	}
	
	

	private void syncExec(Runnable runnable) {
		getWindow().getShell().getDisplay().syncExec(runnable);
	}

	private void showSuiteView(final TestSuite result, final TestSuiteLinker linker) {
		syncExec(() -> {
			try {
				TestSuiteView page = (TestSuiteView) getWindow().getActivePage()
						.showView(TestSuiteView.ID);
				page.setInput(result, linker);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Override
	protected String getJobName() {
		return "TCG - Open Test Suite";
	}
}