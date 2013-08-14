package com.rockwellcollins.atc.agree.analysis;

import jkind.api.results.AnalysisResult;
import jkind.api.ui.AnalysisResultTree;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class AgreeMultipleResultsView extends ViewPart {
	public static final String ID = "com.rockwellcollins.atc.agree.analysis.agreeMultipleResultsView";
	private AnalysisResultTree tree;

	@Override
	public void createPartControl(Composite parent) {
		tree = new AnalysisResultTree(parent);
	}

	@Override
	public void setFocus() {
		tree.getControl().setFocus();
	}

	public void setInput(AnalysisResult result) {
		tree.setInput(result);
	}
}
