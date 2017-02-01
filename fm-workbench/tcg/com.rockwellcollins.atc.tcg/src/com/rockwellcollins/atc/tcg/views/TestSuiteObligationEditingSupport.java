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

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rockwellcollins.atc.tcg.views.ExampleData.Value;

public final class TestSuiteObligationEditingSupport extends EditingSupport {
    
    private ComboBoxViewerCellEditor cellEditor = null;
        
    private TestSuiteObligationEditingSupport(ColumnViewer viewer) {
        super(viewer);
        cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.READ_ONLY);
        cellEditor.setLabelProvider(new LabelProvider());
        cellEditor.setContenProvider(new ArrayContentProvider());
        cellEditor.setInput(Value.values());
    }
     
    @Override
    protected CellEditor getCellEditor(Object element) {
        return cellEditor;
    }
     
    @Override
    protected boolean canEdit(Object element) {
        return true;
    }
     
    @Override
    protected Object getValue(Object element) {
        if (element instanceof ExampleData) {
            ExampleData data = (ExampleData)element;
            return data.getData();
        }
        return null;
    }
     
    @Override
    protected void setValue(Object element, Object value) {
        if (element instanceof ExampleData && value instanceof Value) {
            ExampleData data = (ExampleData) element;
            Value newValue = (Value) value;
            /* only set new value if it differs from old one */
            if (!data.getData().equals(newValue)) {
                data.setData(newValue);
            }
        }
    }
     
}