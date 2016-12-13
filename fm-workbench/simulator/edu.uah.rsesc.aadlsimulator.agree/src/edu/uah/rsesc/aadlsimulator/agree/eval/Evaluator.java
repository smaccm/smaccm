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
package edu.uah.rsesc.aadlsimulator.agree.eval;

import java.util.Collection;

import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Program;
import jkind.lustre.values.Value;

public class Evaluator {
	private final VariableMap variableMap;
	private final NumericEvaluator numericEvaluator;
	
	public Evaluator(final Program program) {
		this.variableMap = new VariableMap(program, new VariableMap.InnerEvaluator() {			
			@Override
			public Value eval(final Expr expr) {
				return numericEvaluator.eval(expr);
			}
		}, new VariableMap.ChangeListener() {			
			@Override
			public void onChange(final VariableMap vm) {
				numericEvaluator.clearCache();
			}
		});
				
		this.numericEvaluator = new NumericEvaluator(variableMap);
		
		// Populate the variable map
		variableMap.processEquations(); 		
		
		// Assert the simulation assertions to avoid needing to assert the negation of the simulation property 
		// which includes extra expressions intended to ensure all variables have values during simulation 
		variableMap.processAssertions();
		variableMap.processIndeterminateAssertions();
	}

	/**
	 * Constructs an evaluator based on an existing evaluator and a collection of additional assertions
	 * @param base
	 * @param additionalAssertions
	 */
	public Evaluator(final Evaluator base, Collection<Expr> additionalAssertions) {
		// Make a copy of the variable map
		this.variableMap = new VariableMap(base.variableMap, new VariableMap.InnerEvaluator() {			
			@Override
			public Value eval(final Expr expr) {
				return numericEvaluator.eval(expr);
			}
		}, new VariableMap.ChangeListener() {			
			@Override
			public void onChange(final VariableMap vm) {
				numericEvaluator.clearCache();
			}
		});
		
		this.numericEvaluator = new NumericEvaluator(variableMap);
		
		// Process assertions
		for(final Expr assertion : additionalAssertions) {
			variableMap.processAssertion(assertion);
		}
		
		// Process Indeterminate Assertions
		variableMap.processIndeterminateAssertions();
	}
	
	/**
	 * Returns the value for the specified variable or null if an EvaluationException was thrown
	 * @param id
	 * @return
	 */
	public Value evalOrNull(final String id) {
		try {
			return evalOrThrow(new IdExpr(id));
		} catch(EvaluationException e) { 
			return null;
		}
	}	

	private Value evalOrThrow(final Expr e) {
		return numericEvaluator.eval(e);
	}
}