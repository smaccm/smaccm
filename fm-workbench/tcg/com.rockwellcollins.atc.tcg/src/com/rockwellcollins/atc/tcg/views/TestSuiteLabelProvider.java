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

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rockwellcollins.atc.tcg.suite.TestCase;
import com.rockwellcollins.atc.tcg.views.TestSuiteTable.TestColumn;

/*
 * This class provides the names
 */
public class TestSuiteLabelProvider extends ColumnLabelProvider {
	private final TestColumn column;
	
	public TestSuiteLabelProvider(TestColumn column) {
		this.column = column;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof TestCase) {
			TestCase tc = (TestCase) element;
			switch (column) {
			case NAME:
				return tc.getName();
			case DESCRIPTION:
				return tc.getDescription();
			case OBLIGATIONS: 
				return "<None - Currently Unimplemented>"; 
			}
		} 

		return "<Unexpected Result...non TestCase>" + element.toString();
	}


	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public void dispose() {
	}
	
}
