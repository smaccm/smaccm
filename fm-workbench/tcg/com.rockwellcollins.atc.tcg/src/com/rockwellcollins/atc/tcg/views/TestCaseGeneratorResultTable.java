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

import jkind.api.ui.results.AnalysisResultColumnViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TestCaseGeneratorResultTable extends AnalysisResultColumnViewer {
	private TableViewer tableViewer;

	public TestCaseGeneratorResultTable(Composite parent) {
		super(parent);
	}

	@Override
	protected ColumnViewer createViewer() {
		tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		tableViewer.getTable().setHeaderVisible(true);
		createColumns();
		return tableViewer;
	}

	private void createColumns() {
		TableViewerColumn propertyColumn = new TableViewerColumn(tableViewer, SWT.None);
		propertyColumn.getColumn().setText("Test Spec");
		propertyColumn.getColumn().setWidth(400);
		propertyColumn.setLabelProvider(new TestCaseGeneratorResultLabelProvider(Column.PROPERTY, tableViewer));

		TableViewerColumn resultColumn = new TableViewerColumn(tableViewer, SWT.None);
		resultColumn.getColumn().setText("Result");
		resultColumn.getColumn().setWidth(200);
		resultColumn.setLabelProvider(new TestCaseGeneratorResultLabelProvider(Column.RESULT));
		
		TableColumnLayout layout = new TableColumnLayout();
		composite.setLayout(layout);
		layout.setColumnData(propertyColumn.getColumn(), new ColumnWeightData(3));
		layout.setColumnData(resultColumn.getColumn(), new ColumnWeightData(1));
	}
	
	@Override
	public TableViewer getViewer() {
		return tableViewer;
	}
}
