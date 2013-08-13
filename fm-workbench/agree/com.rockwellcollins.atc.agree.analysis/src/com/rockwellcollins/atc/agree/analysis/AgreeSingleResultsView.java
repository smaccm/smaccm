package com.rockwellcollins.atc.agree.analysis;

import jkind.api.results.JKindResult;
import jkind.api.ui.AnalysisResultTable;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class AgreeSingleResultsView extends ViewPart {
	public static final String ID = "com.rockwellcollins.atc.agree.analysis.agreeSingleResultsView";
	private AnalysisResultTable table;

	@Override
	public void createPartControl(Composite parent) {
		table = new AnalysisResultTable(parent);
	}

	@Override
	public void setFocus() {
		table.getControl().setFocus();
	}

	public void setInput(JKindResult result) {
		table.setInput(result);
	}
}
