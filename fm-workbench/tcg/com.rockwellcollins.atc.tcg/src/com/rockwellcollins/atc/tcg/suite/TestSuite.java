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

package com.rockwellcollins.atc.tcg.suite;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TestSuite {
	private String name;
	private String description; 
	private String SystemImplUnderTest; 
	private List<TestCase> testCases;
	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public enum State { EMPTY, LOADED, INSTANTIATED, MAPPED }; 
	private State state;

	public TestSuite() {
		super();
		this.SystemImplUnderTest = "<UNKNOWN SYSTEM>";
		this.name = "Test Suite";
		this.description = "";
		this.testCases = new ArrayList<>();
		this.state = State.EMPTY;
	}
	
	public TestSuite(String systemImplUnderTest, String name, String description, List<TestCase> testCase, State state) {
		super();
		this.SystemImplUnderTest = systemImplUnderTest;
		this.name = name;
		this.description = description;
		this.testCases = testCase;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		pcs.firePropertyChange("name", this.name, this.name = name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		pcs.firePropertyChange("description", this.description, this.description = description);
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCase) {
		pcs.firePropertyChange("testCases", this.testCases, this.testCases = testCase);
	}

	
	public void addTestCase(TestCase testCase) {
		this.testCases.add(testCase);
		pcs.firePropertyChange("addTestCase", null, testCase);
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		pcs.firePropertyChange("state", this.state, this.state = state);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public String getSystemImplUnderTest() {
		return SystemImplUnderTest;
	}

	public void setSystemImplUnderTest(String systemImplUnderTest) {
		SystemImplUnderTest = systemImplUnderTest;
	}
	
	
}
