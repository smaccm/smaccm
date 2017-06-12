package com.rockwellcollins.atc.tcg.views;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import jkind.api.ui.results.AnalysisResultColumnViewer;
import jkind.api.ui.results.AnalysisResultLabelProvider;
import jkind.api.ui.results.AnalysisResultColumnViewer.Column;

public class TcgResultTree extends AnalysisResultColumnViewer {
	private TreeViewer treeViewer;
	
	public TcgResultTree(Composite parent) {
		super(parent);
	}

	@Override
	protected ColumnViewer createViewer() {
		treeViewer = new TreeViewer(composite, SWT.FULL_SELECTION);
		treeViewer.getTree().setHeaderVisible(true);
		createColumns();
		return treeViewer;
	}
	
	private void createColumns() {
		TreeViewerColumn propertyColumn = new TreeViewerColumn(treeViewer, SWT.None);
		propertyColumn.getColumn().setText("Test Spec");
		propertyColumn.getColumn().setWidth(400);
//		propertyColumn.setLabelProvider(new AnalysisResultLabelProvider(Column.PROPERTY, treeViewer));
		propertyColumn.setLabelProvider(new TestCaseGeneratorResultLabelProvider(Column.PROPERTY, treeViewer));

		TreeViewerColumn resultColumn = new TreeViewerColumn(treeViewer, SWT.None);
		resultColumn.getColumn().setText("Result");
//		resultColumn.setLabelProvider(new AnalysisResultLabelProvider(Column.RESULT));
		resultColumn.setLabelProvider(new TestCaseGeneratorResultLabelProvider(Column.RESULT));
		
		TreeColumnLayout layout = new TreeColumnLayout();
		composite.setLayout(layout);
		layout.setColumnData(propertyColumn.getColumn(), new ColumnWeightData(2));
		layout.setColumnData(resultColumn.getColumn(), new ColumnWeightData(1));
	}
	
	@Override
	public TreeViewer getViewer() {
		return treeViewer;
	}
}
