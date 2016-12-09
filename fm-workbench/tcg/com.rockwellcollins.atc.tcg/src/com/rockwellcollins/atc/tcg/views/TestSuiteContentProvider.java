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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import com.rockwellcollins.atc.tcg.suite.TestCase;
import com.rockwellcollins.atc.tcg.suite.TestSuite;

public class TestSuiteContentProvider implements IStructuredContentProvider, PropertyChangeListener {
	private TestSuiteTable viewer;

	public TestSuiteContentProvider(TestSuiteTable viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (oldInput instanceof TestSuite) {
			stopListening((TestSuite) oldInput);
		}

		if (newInput instanceof TestSuite) {
			startListening((TestSuite) newInput);
		}
	}

	private void stopListening(TestSuite result) {
		result.removePropertyChangeListener(this);
		for (TestCase test: result.getTestCases()) {
			test.removePropertyChangeListener(this);
		}
	}

	private void startListening(TestSuite result) {
		result.addPropertyChangeListener(this);
		for (TestCase test : result.getTestCases()) {
			test.addPropertyChangeListener(this);
		}
	}

	
	@Override
	public Object[] getElements(Object parentElement) {
		if (parentElement instanceof TestSuite) {
			TestSuite ts = (TestSuite)parentElement;
			return ts.getTestCases().toArray();
		} else {
			System.out.println("Warning: parentElement is not a TestSuite in getChildren()");
			return new Object[0];
		}
	}


	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() instanceof TestSuite) {
			final TestSuite result = (TestSuite) event.getSource();
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					viewer.update(result);
				}
			});
		}
	}
}
