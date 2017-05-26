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

package com.rockwellcollins.atc.tcg.views;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.tcg.suite.TestSuite;

import jkind.api.results.AnalysisResult;
import jkind.api.results.JKindResult;

import org.eclipse.ui.ISaveablePart2;

public class TestSuiteView extends ViewPart implements ISaveablePart2 {
	public static final String ID = "tcg.views.testSuiteView";
	private TestSuiteTable table;
    private TestSuiteMenuListener menuListener;
    private TestSuite suite;

	@Override
	public void createPartControl(Composite parent) {
		table = new TestSuiteTable(parent);

        menuListener = new TestSuiteMenuListener(getViewSite().getWorkbenchWindow(), table);
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(menuListener);
        table.getControl().setMenu(manager.createContextMenu(table.getViewer().getTable()));
	}

	@Override
	public void setFocus() {
		table.getControl().setFocus();
	}

	public void setInput(TestSuite suite, final AgreeResultsLinker linker, final JKindResult result) {
		this.suite = suite;
		table.setInput(suite);
        menuListener.setLinker(linker);
        menuListener.setAnalysisResult(result);
        this.setPartName("Test Suite: " + result.getName() + " [" + suite.getSystemImplUnderTest() + "]");
	}

	public TestSuite getInput() {
		return table.getInput();
	}
	
	public TestSuiteMenuListener getMenuListener() {
		return menuListener;
	}

	// TODO: implement methods to save test suite on close if dirty (if requested).
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		System.out.println("doSave method.");
	}

	@Override
	public void doSaveAs() {
		System.out.println("doSaveAs method.");
	}

	@Override
	public boolean isDirty() {
		System.out.println("isDirty method.");
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		System.out.println("isSaveAllowed method.");
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		System.out.println("isSaveOnClose method.");
		return true;
	}

	@Override
	public int promptToSaveOnClose() {
		System.out.println("Saving on close method.");
		setInput(null, null, null);
		return 0;
	}
}
