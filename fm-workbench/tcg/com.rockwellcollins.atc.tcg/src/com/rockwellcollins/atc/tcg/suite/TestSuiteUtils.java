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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jkind.api.results.JKindResult;
import jkind.api.results.PropertyResult;
import jkind.results.InvalidProperty;

public class TestSuiteUtils {

	public static TestSuite testSuiteFromJKindResult(JKindResult result, String implUnderTest, String name, String description) {
		List<TestCase> tests = new ArrayList<>();
		for (PropertyResult pr : result.getPropertyResults()) {
			if (pr.getProperty() instanceof InvalidProperty) {
				InvalidProperty invalidProp = (InvalidProperty)pr.getProperty();
				tests.add(new TestCase(pr.getName(), "", invalidProp.getCounterexample()));
			}
		}
		return new TestSuite(implUnderTest, name, description, tests, TestSuite.State.INSTANTIATED); 
	}
	
	public static <T> Set<T> safeSet(Set<T> theSet) {
		return (null == theSet) ? new HashSet<T>() : theSet;
	}
	
	public static <T> List<T> safeList(List<T> theList) {
		return (null == theList) ? new ArrayList<T>() : theList;
	}
	
	public static String safeString(String s) {
		return (null == s) ? "" : s;
	}
}
