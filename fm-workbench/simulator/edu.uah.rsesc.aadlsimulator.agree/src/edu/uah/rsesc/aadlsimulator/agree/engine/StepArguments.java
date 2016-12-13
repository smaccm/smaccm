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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import jkind.lustre.Expr;

/**
 * Immutable data structure for holding the arguments to the step forward operation.
 *
 */
class StepArguments {
	private final Map<AGREESimulationStateElement, InputConstraint> elementToNextInputConstraintMap;
	private final Map<AGREESimulationStateElement, Expr> elementToNextLustreConstraintMap;
	
	public StepArguments(final Map<AGREESimulationStateElement, InputConstraint> elementToNextInputConstraintMap, final Map<AGREESimulationStateElement, Expr> elementToNextLustreConstraintMap) {
		this.elementToNextInputConstraintMap = Collections.unmodifiableMap(new HashMap<>(Objects.requireNonNull(elementToNextInputConstraintMap, "elementToNextInputConstraintMap must not be null")));
		this.elementToNextLustreConstraintMap = Collections.unmodifiableMap(new HashMap<>(Objects.requireNonNull(elementToNextLustreConstraintMap, "elementToNextLustreConstraintMap must not be null")));
	}
	
	/**
	 * Returns an immutable map from elements to the input constraint for the next frame.
	 * @return
	 */
	public final Map<AGREESimulationStateElement, InputConstraint> getElementToNextInputConstraintMap() {
		return elementToNextInputConstraintMap;
	}
	
	public final Map<AGREESimulationStateElement, Expr> getElementToNextLustreConstraintMap() {
		return elementToNextLustreConstraintMap;
	}
}
