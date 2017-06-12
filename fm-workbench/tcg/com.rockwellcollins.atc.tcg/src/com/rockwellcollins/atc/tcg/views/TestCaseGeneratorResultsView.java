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

import jkind.api.results.AnalysisResult;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;

public class TestCaseGeneratorResultsView extends ViewPart {
	public static final String ID = "tcg.views.testCaseGeneratorResultsView";
	private TcgResultTree tree;
    private TestCaseGeneratorMenuListener menuListener;

	@Override
	public void createPartControl(Composite parent) {
		tree = new TcgResultTree(parent);
        tree.getViewer().setAutoExpandLevel(2);

        menuListener = new TestCaseGeneratorMenuListener(getViewSite().getWorkbenchWindow(), tree);
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(menuListener);
        tree.getControl().setMenu(manager.createContextMenu(tree.getViewer().getTree()));
	}

	@Override
	public void setFocus() {
		tree.getControl().setFocus();
	}

	public void setInput(AnalysisResult result, final AgreeResultsLinker linker) {
		tree.setInput(result);
        menuListener.setLinker(linker);
	}
}
