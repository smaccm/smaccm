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

import java.util.Objects;
import jkind.lustre.BinaryOp;
import jkind.lustre.UnaryOp;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.Value;

class SymbolicValue {	
	public final Value coefficient;
	public final Variable variable;
	public final Value constant;
	
	public SymbolicValue(final Value coefficient, final Variable variable, final Value constant) {
		this.coefficient = coefficient;
		this.variable = variable;
		this.constant = constant;

		if(coefficient == null && variable == null && constant == null) {
			throw new RuntimeException("coefficient, variable, and constant must not all be null");
		}
		
		// Require null variableId for boolean coefficients
		if(constant instanceof BooleanValue) {
			if(coefficient != null || variable != null) {
				throw new RuntimeException("coefficient and variableId must be null when yIntercept is a boolean.");
			}
		}
		
		if(coefficient instanceof BooleanValue) {
			throw new RuntimeException("coefficient must not be a boolean value");
		}
		
		if((coefficient == null) != (variable == null)) {
			throw new RuntimeException("coefficient and variableId must both be null or both be non-null.");
		}
	}
	
	public SymbolicValue applyBinaryOp(final BinaryOp op, SymbolicValue value) {
		final Value newCoefficient;
		final Variable newVariable;
		final Value newConstant;
		
		if(op == BinaryOp.EQUAL) {
			// Equality comparison is only supported when the variables are the same
			if(variable == value.variable) {
				boolean equal = Objects.equals(coefficient, value.coefficient) &&
						Objects.equals(variable, value.variable) &&
						Objects.equals(constant, value.constant);
				return new SymbolicValue(null, null, equal ? BooleanValue.TRUE : BooleanValue.FALSE);
			} else {
				return null;
			}
		} else if(op == BinaryOp.LESS || op == BinaryOp.LESSEQUAL || op == BinaryOp.GREATER || op == BinaryOp.GREATEREQUAL){
			// Comparison operators are only supported when the symbolic value are constant values
			if(variable == null && value.variable == null && constant != null && value.constant != null) {
				final Value result = constant.applyBinaryOp(op, value.constant);
				if(result instanceof BooleanValue) {
					return new SymbolicValue(null, null, (BooleanValue)result);
				}
			}
			
			return null;
		} else if(op == BinaryOp.PLUS || op == BinaryOp.MINUS) {
			if(variable == null && value.variable == null) {
				newCoefficient = null;
				newVariable = null;
			} else if(variable != null && value.variable != null) {
				if(variable == value.variable) {
					newCoefficient = coefficient.applyBinaryOp(op, value.coefficient);
					newVariable = variable;
				} else {
					// Unsupported
					return null;
				}
			} else {
				if(variable == null) { // value.variable != null
					if(op == BinaryOp.PLUS) {
						newCoefficient = value.coefficient;
					} else {
						newCoefficient = value.coefficient.applyUnaryOp(UnaryOp.NEGATIVE);
					}
					
					newVariable = value.variable;
				} else {
					newCoefficient = coefficient;
					newVariable = variable;
				}
			}
			
			if(constant == null && value.constant == null) {
				newConstant = null;
			} else if(constant != null && value.constant != null) {
				newConstant = constant.applyBinaryOp(op, value.constant);
			} else {
				if(constant == null) { // value.constant != null
					if(op == BinaryOp.PLUS) {
						newConstant = value.constant;
					} else {
						newConstant = value.constant.applyUnaryOp(UnaryOp.NEGATIVE);
					}
					
				} else {
					newConstant = constant;
				}
			}
		} else if(op == BinaryOp.MULTIPLY || op == BinaryOp.DIVIDE) {		
			if(variable == null && value.variable == null) {
				newCoefficient = null;
				newVariable = null;
			} else if(variable != null && value.variable != null) {
				// At most one may have a variable id
				return null;
			} else {
				if(variable == null) { // value.variable != null
					newCoefficient = value.coefficient.applyBinaryOp(op, constant);
					newVariable = value.variable;
				} else { 
					newCoefficient = coefficient.applyBinaryOp(op, value.constant);
					newVariable = variable;
				}
			}
			
			// Since multiplying values which both contain coefficients are not allowed, at least one value must have a constant			
			if(constant != null && value.constant != null) {
				newConstant = constant.applyBinaryOp(op, value.constant);
			} else {
				newConstant = null;
			}
		} else if(op == BinaryOp.AND) {
			if(variable == null && value.variable == null) {
				newCoefficient = null;
				newVariable = null;
				newConstant = constant.applyBinaryOp(op, value.constant);
			} else if(variable != null && value.variable != null) {
				if(variable == value.variable && coefficient.equals(value.coefficient)) {
					newCoefficient = coefficient;
					newVariable = variable;
					newConstant = null;
				} else {
					// Unsupported
					return null;
				}
			} else {
				if(variable == null) { // value.variable != null
					if(constant == BooleanValue.TRUE) {
						newCoefficient = value.coefficient;
						newVariable = value.variable;
						newConstant = null;
					} else {
						newCoefficient = null;
						newVariable = null;
						newConstant = BooleanValue.FALSE;
					}					
				} else { 
					if(value.constant == BooleanValue.TRUE) {
						newCoefficient = coefficient;
						newVariable = variable;
						newConstant = null;
					} else {
						newCoefficient = null;
						newVariable = null;
						newConstant = BooleanValue.FALSE;
					}	
				}
			}
		} else if(op == BinaryOp.OR) {
			if(variable == null && value.variable == null) {
				newCoefficient = null;
				newVariable = null;
				newConstant = constant.applyBinaryOp(op, value.constant);
			} else if(variable != null && value.variable != null) {
				if(variable == value.variable && coefficient.equals(value.coefficient)) {
					newCoefficient = coefficient;
					newVariable = variable;
					newConstant = null;
				} else {
					// Unsupported
					return null;
				}
			} else {
				if(variable == null) { // value.variable != null
					if(constant == BooleanValue.TRUE) {
						newCoefficient = null;
						newVariable = null;
						newConstant = BooleanValue.TRUE;
					} else {
						newCoefficient = value.coefficient;
						newVariable = value.variable;
						newConstant = null;
					}					
				} else { 
					if(value.constant == BooleanValue.TRUE) {
						newCoefficient = null;
						newVariable = null;
						newConstant = BooleanValue.TRUE;
					} else {
						newCoefficient = coefficient;
						newVariable = variable;
						newConstant = null;
					}	
				}
			}
		} else {
			return null;
		}
		
		// Don't create a new symbolic value if both the variable and coefficient are not valid.
		// This will occur if unsupported operations are performed on the coefficient.
		if((newCoefficient == null) != (newVariable == null)) {
			return null;
		}
		
		return new SymbolicValue(newCoefficient, newVariable, newConstant);
	}
	
	public SymbolicValue applyUnaryOp(final UnaryOp op) {
		if(op == UnaryOp.NEGATIVE) {			
			final Value newSlope = coefficient == null ? null : coefficient.applyUnaryOp(op);
			final Value newYIntercept = constant == null ? null : constant.applyUnaryOp(op);
			return new SymbolicValue(newSlope, variable, newYIntercept);
		} else if(op == UnaryOp.NOT) {
			if(coefficient != null) {
				return null;
			}
			
			return new SymbolicValue(null, null, constant.applyUnaryOp(op));
		}
	
		return null;
	}	
		
	@Override
	public String toString() {
		String str = "{SYM: ";
		if(coefficient != null) {
			str += coefficient;
			str += "*";
			str += variable.ids.iterator().next();
			
			if(constant != null) {
				str += " + ";
			}
		}
		
		if(constant != null) {
			str += constant;
		}
		
		str += "}";
		
		return str;
	}

}
