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

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.rockwellcollins.atc.tcg.suite.TestSuite;

public class TestSuiteTable {
	private TableViewer tableViewer;
	protected final Composite composite;
	protected final ColumnViewer viewer;
	
	public TestSuiteTable(Composite parent) {
		this.composite = new Composite(parent, SWT.None);
		this.viewer = createViewer();
		viewer.setContentProvider(new TestSuiteContentProvider(this));
	}

	/**
	 * Warning: This method will be called by the JKindColumnViewer constructor
	 * and thus subclasses will not be fully initialized at this time.
	 * Subclasses may use this method to initialize themselves.
	 */
	
	public void update(Object element) {
		viewer.refresh();
		// viewer.update(element, null);
	}

	public void setInput(TestSuite model) {
		viewer.setInput(model);
	}

	public void setLayoutData(Object layoutData) {
		composite.setLayoutData(layoutData);
	}
	
	public Composite getComposite() {
		return composite;
	}
	
	
	public Control getControl() {
		return viewer.getControl();
	}
	

	protected ColumnViewer createViewer() {
		tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		tableViewer.getTable().setHeaderVisible(true);
		createColumns();
		return tableViewer;
	}
	
	public static enum TestColumn {NAME, DESCRIPTION, OBLIGATIONS}; 

	private void createColumns() {
		TableViewerColumn nameColumn = new TableViewerColumn(tableViewer, SWT.None);
		nameColumn.getColumn().setText("Test Name");
		nameColumn.getColumn().setWidth(200);
		nameColumn.setLabelProvider(new TestSuiteLabelProvider(TestColumn.NAME));
		nameColumn.setEditingSupport(new TestSuiteNameEditingSupport(tableViewer));

		TableViewerColumn descriptionColumn = new TableViewerColumn(tableViewer, SWT.None);
		descriptionColumn.getColumn().setText("Test Description");
		descriptionColumn.getColumn().setWidth(200);
		descriptionColumn.setLabelProvider(new TestSuiteLabelProvider(TestColumn.DESCRIPTION));
		descriptionColumn.setEditingSupport(new TestSuiteDescriptionEditingSupport(tableViewer));
		
//		TableViewerColumn obligationsColumn = new TableViewerColumn(tableViewer, SWT.None);
//		obligationsColumn.getColumn().setText("Satisfies Coverage Obligations:");
//		obligationsColumn.getColumn().setWidth(200);
//		obligationsColumn.setLabelProvider(new TestSuiteLabelProvider(TestColumn.OBLIGATIONS));

		TableColumnLayout layout = new TableColumnLayout();
		composite.setLayout(layout);
		layout.setColumnData(nameColumn.getColumn(), new ColumnWeightData(3));
		layout.setColumnData(descriptionColumn.getColumn(), new ColumnWeightData(1));
//		layout.setColumnData(obligationsColumn.getColumn(), new ColumnWeightData(1));
	}
	
	public TableViewer getViewer() {
		return tableViewer;
	}
	
	public TestSuite getInput() {
		return (TestSuite) viewer.getInput();
	}
	
}
