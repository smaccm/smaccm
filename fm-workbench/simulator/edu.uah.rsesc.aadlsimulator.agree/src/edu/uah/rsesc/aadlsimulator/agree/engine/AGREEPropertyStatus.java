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
package edu.uah.rsesc.aadlsimulator.agree.engine;

import edu.uah.rsesc.aadlsimulator.PropertyStatus;

public enum AGREEPropertyStatus implements PropertyStatus {
	INITIAL_STEP("", Style.INFO),
	HALTED_SET_OF_SUPPORT_UNAVAILABLE("Unknown", Style.INFO), // Simulation is halted and a set of support is not available
	HALTED_NOT_IN_SET_OF_SUPPORT("", Style.INFO), // Simulation is halted and the property is not part of the set of support.
	SATISFIED("Satisfied", Style.OK),
	UNSATISFIED_WARNING("Unsatisfied", Style.WARNING),
	UNSATISFIED_ERROR("Unsatisfied", Style.ERROR), // Simulation is halted but this property is part of the set of support.
	DISABLED("Disabled", Style.INFO); // The simulation has been disabled.
	private final String name;
	private final Style style;
	
	AGREEPropertyStatus(final String name, final Style style) {
		assert name != null;
		assert style != null;
		
		this.name = name;
		this.style = style;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Style getStyle() {
		return style;
	}		
}