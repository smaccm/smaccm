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
package edu.uah.rsesc.aadlsimulator;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class FrameStatePrinter extends NullSimulationEngineEventListener {
	private final PrintStream ps;
	private int frame = 0;
	
	public FrameStatePrinter(final PrintStream printStream) {
		this.ps = printStream;
	}
	
	public FrameStatePrinter(final OutputStream outputStream) {
		this(new PrintStream(outputStream));
	}
	
	@Override
	public void onSimulationEngineStateChanged(final EngineStateChangedEvent e) {
		Objects.requireNonNull(e, "e must not be null");
		
		ps.println("==================================================================================");
		ps.println("FRAME: " + frame++);
		ps.println("----------------------------------------------------------------------------------");
		int depth = 0;
		for(final Object element : e.getEngineState().getRootElements()) {
			printElement(e.getEngineState(), element, depth);
		}
	}
	
	private void printElement(final SimulationEngineState state, final Object element, final int depth) {
		// Indent
		for(int i = 0; i < depth; i++) {
			ps.print("    ");
		}
									
		final Object value = state.getElementValue(state.getNumberOfFrames()-1, element);
		ps.println("-" + state.getElementName(element) + (value == null ? "" : " : " + value));
		for(final Object childElement : state.getChildElements(element)) {
			printElement(state, childElement, depth + 1);
		}
	}
}

