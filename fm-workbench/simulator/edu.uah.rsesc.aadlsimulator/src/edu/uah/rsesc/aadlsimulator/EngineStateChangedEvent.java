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

import java.util.Objects;

public class EngineStateChangedEvent {
	// Causes for the engine state change
	public enum Cause {
		FRAME_START,
		STEP_FORWARD,
		STEP_BACKWARD,
		PROPERTY_ENABLEMENT_CHANGED,
		CONSTRAINTS_CHANGED, // Constraints have been changed. Constraints will also be changed during STEP_FORWARD and STEP_BACKWARD
		NOTIFICATION,
		ERROR
	}	
	
	private final SimulationEngineState state;
	private final Cause cause;

	/**
	 * 
	 * @param cause
	 * @param state
	 */
	public EngineStateChangedEvent(final Cause cause, final SimulationEngineState state) {
		this.cause = Objects.requireNonNull(cause, "cause must not be null");
		this.state = Objects.requireNonNull(state, "state must not be null");
	}
	
	public SimulationEngineState getEngineState() {
		return state;
	}
	
	public Cause getCause() {
		return cause;
	}
}
