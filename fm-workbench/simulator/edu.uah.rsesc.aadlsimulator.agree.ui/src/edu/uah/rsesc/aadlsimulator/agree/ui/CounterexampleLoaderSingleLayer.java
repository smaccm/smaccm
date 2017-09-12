/*
Copyright (c) 2015, Rockwell Collins.
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
package edu.uah.rsesc.aadlsimulator.agree.ui;

import java.util.Map;

import jkind.results.Counterexample;
import jkind.results.Property;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentImplementation;

import com.rockwellcollins.atc.agree.analysis.extentions.CexExtractor;

import edu.uah.rsesc.aadlsimulator.agree.ui.CounterexampleLoaderHelper.Mode;

public class CounterexampleLoaderSingleLayer implements CexExtractor {
	@Override
	public void receiveCex(final ComponentImplementation compImpl, Property property, EObject agreeProperty, final Counterexample cex, final Map<String, EObject> refMap) {
		new CounterexampleLoaderHelper().receiveCex(compImpl, property, agreeProperty, cex, refMap, Mode.SINGLE_LAYER);
	}

	@Override
	public String getDisplayText() {
		return "Simulator - Single Layer";
	}
}
