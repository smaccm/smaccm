/*
Copyright (c) 2018, Rockwell Collins.
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

package com.rockwellcollins.atc.tcg.writers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import com.rockwellcollins.atc.tcg.TcgException;
import com.rockwellcollins.atc.tcg.suite.TestCase;
import com.rockwellcollins.atc.tcg.suite.TestSuite;

import jkind.lustre.NamedType;
import jkind.lustre.Type;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.Signal;

public class TcgXmlWriter extends TcgWriter {

	private final PrintWriter out;
	private final List<Type> types;

	public String escapeXml(String target) throws Exception {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Text text = document.createTextNode(target);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(text);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(source, result);
		return writer.toString();
	}

	public TcgXmlWriter(String filename, List<Type> types, boolean useStdout)
			throws FileNotFoundException {
		if (useStdout) {
			this.out = new PrintWriter(System.out, true);
		} else {
			this.out = new PrintWriter(new FileOutputStream(filename), true);
		}
		this.types = types;
	}

	private void begin(String name, String description, String implUnderTest) {
		try {
			out.println("<?xml version=\"1.0\"?>");
			out.println("<TestSuite xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
			out.println("  <SuiteName>" + escapeXml(name) + "</SuiteName>");
			out.println("  <SuiteDescription>" + escapeXml(description) + "</SuiteDescription>");
			out.println("  <ImplementationUnderTest>" + escapeXml(implUnderTest) + "</ImplementationUnderTest>");
			out.println("  <Tests>");
		} catch (Exception e) {
			throw new TcgException("Error writing XML Test Suite", e);
		}
	}

	private void end() {
		out.println("  </Tests>");
		out.println("</TestSuite>");
	}

	@Override
	public void writeSuite(TestSuite ts) {
		begin(ts.getName(), ts.getDescription(), ts.getSystemImplUnderTest());
		for (TestCase test : ts.getTestCases()) {
			writeTest(test);
		}
		end();
	}

	@Override
	public void writeTest(TestCase tc) {
		writeTest(tc.getName(), tc.getDescription(), tc.getCex());
	}

	private void writeTest(String name, String description, Counterexample cex) {
		try {
			out.println("    <Test name=\"" + escapeXml(name) + "\">");
			out.println("      <Description>" + escapeXml(description) + "</Description>");
			out.println("      <Length>" + cex.getLength() + "</Length>");
			writeCounterexample(cex);
			out.println("    </Test>");
		} catch (Exception e) {
			throw new TcgException("Error writing XML Test Suite", e);
		}
	}

	private void writeCounterexample(Counterexample cex) throws Exception {
		out.println("    <Signals>");
		for (Signal<Value> signal : cex.getSignals()) {
			writeSignal(cex.getLength(), signal);
		}
		out.println("    </Signals>");
	}

	private void writeSignal(int k, Signal<Value> signal) throws Exception {
		String name = escapeXml(signal.getName());
		// TODO: fix this with the type map!
		Type type;
		if (signal.getValues().isEmpty()) {
			throw new TcgException("Unable to assertain signal type in XmlWriter");
		} else {
			Map.Entry<Integer, Value> entry = signal.getValues().entrySet().iterator().next();
			Value v = entry.getValue();
			if (v instanceof IntegerValue) {
				type = NamedType.INT;
			} else if (v instanceof RealValue) {
				type = NamedType.REAL;
			} else if (v instanceof BooleanValue) {
				type = NamedType.BOOL;
			} else {
				throw new TcgException("Unexpected signal type in XmlWriter");
			}
		}
		out.println("      <Signal name=\"" + name + "\" type=\"" + type + "\">");
		for (int i = 0; i < k; i++) {
			out.println("        <Value time=\"" + i + "\">" + formatValue(signal.getValue(i)) + "</Value>");
		}
		out.println("      </Signal>");
	}

	private String formatValue(Value value) {
		return value.toString();
	}
}
