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
package edu.uah.rsesc.aadlsimulator.agree.sim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import edu.uah.rsesc.aadlsimulator.agree.CarryVariable;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import jkind.lustre.ArrayExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.values.ArrayValue;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.EnumValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.TupleValue;
import jkind.lustre.values.Value;
import jkind.util.BigFraction;

public class FrameAssertionHelper {
	/**
	 * Adds the frame specific assertions for the next frame to an assertion collection. Does not add assertions related to property enablement.
	 * @param program
	 * @param lastResults
	 * @param assertions is the collection to which assertions will be added.
	 */
	public static void addNextFrameAssertions(final SimulationProgram program, final SimulationFrameResults lastResults, final Collection<Expr> assertions) {
		if(lastResults == null) {
			for(final Expr c : program.getInitialConstraints()) {
				assertions.add(c);
			}
		} else {
			for(final CarryVariable cv : program.getCarryVariables()) {
				final Value value = lastResults.getValue(cv.getOutputVariableExpression().toString());
				if(value == null) {
					throw new RuntimeException("Unable to get value for: " + cv.getOutputVariableExpression().toString());
				}
				
				final Expr valueExpression = valueToExpr(value);
				assertions.add(new BinaryExpr(cv.getInputVariableExpression(), BinaryOp.EQUAL, valueExpression));
			}					
		}
	}
	
	private static Expr valueToExpr(final Value value) {
		assert value != null;
		if(value instanceof ArrayValue) {
			final ArrayValue arrayValue = (ArrayValue)value;
			final ArrayList<Expr> exprList = new ArrayList<Expr>(arrayValue.elements.size());
			for(final Value childValue : arrayValue.elements) {
				exprList.add(valueToExpr(childValue));
			}
			return new ArrayExpr(exprList);
		}  else if(value instanceof BooleanValue) {
			return new BoolExpr(((BooleanValue)value).value);
		}  else  if(value instanceof EnumValue) {
			return new IdExpr(((EnumValue)value).value);
		}  else if(value instanceof IntegerValue) {
			return new IntExpr(((IntegerValue)value).value);
		} else if(value instanceof RealValue) {
			final BigFraction fraction = ((RealValue)value).value;
			return new BinaryExpr(new RealExpr(new BigDecimal(fraction.getNumerator())), BinaryOp.DIVIDE, new RealExpr(new BigDecimal(fraction.getDenominator())));
		} if(value instanceof TupleValue) {
			final TupleValue tupleValue = (TupleValue)value;
			final ArrayList<Expr> exprList = new ArrayList<Expr>(tupleValue.elements.size());
			for(final Value childValue : tupleValue.elements) {
				exprList.add(valueToExpr(childValue));
			}

			return new TupleExpr(exprList);
		} else {
			throw new RuntimeException("Unhandled case. Value is of type: " + value.getClass());
		}
	}
}
