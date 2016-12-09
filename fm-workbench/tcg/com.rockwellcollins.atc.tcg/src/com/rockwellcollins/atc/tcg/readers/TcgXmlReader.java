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

package com.rockwellcollins.atc.tcg.readers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.Signal;
import jkind.util.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.rockwellcollins.atc.tcg.TcgException;
import com.rockwellcollins.atc.tcg.suite.TestCase;
import com.rockwellcollins.atc.tcg.suite.TestSuite;

public class TcgXmlReader extends TcgReader {
	private final InputStream xmlStream;
	private final DocumentBuilderFactory factory;
	private volatile Throwable throwable;

	public TcgXmlReader(InputStream xmlStream) {
		super();
		this.xmlStream = xmlStream;
		this.factory = DocumentBuilderFactory.newInstance();
	}

	public void readSuite(TestSuite ts) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlStream);
			Element docElement = doc.getDocumentElement();
			System.out.println("Root element :" + docElement.getNodeName());
			ts.setName(getTextElement(docElement, "SuiteName"));
			ts.setDescription(getTextElement(docElement, "SuiteDescription"));
			ts.setSystemImplUnderTest(getTextElement(docElement, "ImplementationUnderTest"));
			List<Element> testElements = getElements(docElement, "Test");
			List<TestCase> tests = constructTests(testElements); 
			ts.setTestCases(tests);
			ts.setState(TestSuite.State.LOADED);
		} catch (Exception e) {
			throw new TcgException("Error parsing XML test suite.", e);
		} 
	}
	
	public List<TestCase> constructTests(List<Element> testElements) {
		List<TestCase> testCases = new ArrayList<>();
		for (Element elem: testElements) {
			String name = elem.getAttribute("name");
			String description = getTextElement(elem, "Description");
			int length = Integer.parseInt(getTextElement(elem, "Length"));
			Counterexample cex = getCounterexample(elem,length);
			TestCase tc = new TestCase(name, description, cex);
			testCases.add(tc);
		}
		return testCases;
	}

	private Counterexample getCounterexample(Element cexElement, int k) {
		if (cexElement == null) {
			return null;
		}

		Counterexample cex = new Counterexample(k);
		for (Element signalElement : getElements(cexElement, getSignalTag())) {
			cex.addSignal(getSignal(signalElement));
		}
		return cex;
	}
	
	protected String getSignalTag() {
		return "Signal";
	}

	private Signal<Value> getSignal(Element signalElement) {
		String name = signalElement.getAttribute("name");
		String type = signalElement.getAttribute("type");
		if (type.contains("subrange ")) {
			type = "int";
		}

		Signal<Value> signal = new Signal<>(name);
		for (Element valueElement : getElements(signalElement, "Value")) {
			int time = Integer.parseInt(valueElement.getAttribute(getTimeAttribute()));
			signal.putValue(time, getValue(valueElement, type));
		}
		return signal;
	}

	protected String getTimeAttribute() {
		return "time";
	}

	private Value getValue(Element valueElement, String type) {
		return Util.parseValue(type, valueElement.getTextContent());
	}
	
	private String getTextElement(Element element, String name) {
		return getElement(element, name).getTextContent(); 
	}
	
	private Element getElement(Element element, String name) {
		return (Element) element.getElementsByTagName(name).item(0);
	}

	private List<Element> getElements(Element element, String name) {
		List<Element> elements = new ArrayList<>();
		NodeList nodeList = element.getElementsByTagName(name);
		for (int i = 0; i < nodeList.getLength(); i++) {
			elements.add((Element) nodeList.item(i));
		}
		return elements;
	}


	public Throwable getThrowable() {
		return throwable;
	}

}
