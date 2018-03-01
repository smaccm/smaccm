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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Expr;
import jkind.lustre.FunctionCallExpr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordUpdateExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;
import jkind.lustre.visitors.ExprVisitor;
import jkind.util.BigFraction;

class NumericEvaluator {
	private static int MAX_SOLVE_DEPTH = 4; // The maximum number of solve variables

	private final Visitor visitor;

	public NumericEvaluator(final VariableMap variableMap) {
		this.visitor = new Visitor(variableMap);
	}

	/**
	 * Evaluates an expression by attempting to solve the related expressions for variables stored in the variable map
	 * Throws an EvaluationException if conflict values are evaluated.
	 */
	public Value eval(final Expr e) {
		return visitor.eval(e);
	}

	public void clearCache() {
		visitor.clearCache();
	}

	private static class Visitor implements ExprVisitor<Value> {
		private final VariableMap variableMap;
		private final SymbolicEvaluator symbolicEvaluator;
		private Set<Variable> solveVariables = new HashSet<>(); // Set of all variables which are currently being solved
		private final Map<Variable, Value> cache = new HashMap<>();
		private final ArrayDeque<Set<Variable>> nullVariableSetStack = new ArrayDeque<Set<Variable>>();

		public Visitor(final VariableMap variableMap) {
			this.variableMap = Objects.requireNonNull(variableMap, "variableMap must not be null");
			this.symbolicEvaluator = new SymbolicEvaluator(variableMap, expr -> expr.accept(Visitor.this));

			nullVariableSetStack.add(new HashSet<Variable>());
		}

		public final Value eval(final Expr e) {
			// Check if the visitor is already evaluating a variable
			if(!solveVariables.isEmpty()) {
				throw new EvaluationException("Already evaluating");
			}

			final Value value = e.accept(this);
			return value;
		}

		public void clearCache() {
			cache.clear();

			// Clear the sets in the null variable set stack
			for(final Set<Variable> nullVariableSet : nullVariableSetStack) {
				nullVariableSet.clear();
			}
		}

		@Override
		public Value visit(final BinaryExpr e) {
			final Value leftValue = e.left.accept(this);
			final Value rightValue = e.right.accept(this);

			if(leftValue == null || rightValue == null) {
				return null;
			}

			return leftValue.applyBinaryOp(e.op, rightValue);
		}

		@Override
		public Value visit(final BoolExpr e) {
			return BooleanValue.fromBoolean(e.value);
		}

		@Override
		public Value visit(final RecordAccessExpr e) {
			return evaluateAndCache(e.toString());
		}

		@Override
		public Value visit(final IdExpr e) {
			return evaluateAndCache(e.id);
		}

		@Override
		public Value visit(final ArrayAccessExpr e) {
			return evaluateAndCache(e.toString());
		}

		public Value evaluateAndCache(final String id) throws EvaluationException {
			// Get the variable
			final Variable var = variableMap.get(id);
			if(var == null) {
				return null;
			}

			// Check the cache
			if(cache.containsKey(var)) {
				return cache.get(var);
			}

			// Check the null variable set
			final Set<Variable> nullVariableSet = nullVariableSetStack.peek();
			if(nullVariableSet.contains(var)) {
				return null;
			}

			final Value value = solveForValue(var);

			// Add the value to the cache or the top null variable set as appropriate
			if(value == null) {
				nullVariableSet.add(var);
			} else {
				cache.put(var, value);
			}

			return value;
		}

		private Value solveForValue(final Variable var) throws EvaluationException {
			// Manage solve variables
			if(solveVariables.contains(var) || solveVariables.size() >= MAX_SOLVE_DEPTH) {
				return null;
			}
			solveVariables.add(var);

			// Create new null variable set
			nullVariableSetStack.push(new HashSet<>(nullVariableSetStack.peek()));
			try {
				Value value = null;

				// Try to solve using related expressions
				for(final BinaryExpr relatedExpr : var.relatedExpressions) {
					value = updateValue(value, solveRelatedExpression(var, relatedExpr));

				}

				return value;
			} finally {
				nullVariableSetStack.pop();
				solveVariables.remove(var);
			}
		}

		private static Value updateValue(final Value currentValue, final Value newValue) {
			if(newValue == null) {
				return currentValue;
			} else {
				if(currentValue == null || newValue.equals(currentValue)) {
					return newValue;
				} else {
					throw new EvaluationException("Conflicting value");
				}
			}
		}

		private Value solveRelatedExpression(final Variable solveVariable, final BinaryExpr relatedExpr) {
			Value value = null;
			if(relatedExpr.op == BinaryOp.EQUAL) {
				final SymbolicValue leftValue = symbolicEvaluator.eval(solveVariable, relatedExpr.left);
				final SymbolicValue rightValue = symbolicEvaluator.eval(solveVariable, relatedExpr.right);

				if(leftValue != null && rightValue != null) {
					final boolean hasVariable;
					final boolean isBoolean;
					if(leftValue.variable == solveVariable) {
						isBoolean = leftValue.variable.type == NamedType.BOOL;
						hasVariable = true;
					} else if(rightValue.variable == solveVariable) {
						isBoolean = rightValue.variable.type == NamedType.BOOL;
						hasVariable = true;
					} else {
						isBoolean = false;
						hasVariable = false;
					}

					if(hasVariable) {
						if(isBoolean) {
							// Ensure that there is a single variable
							if(leftValue.variable == null) {
								value = leftValue.constant;
							} else if(rightValue.variable == null) {
								value = rightValue.constant;
							}
						} else {
							// Solve for the variable
							final SymbolicValue leftMinusRight = leftValue.applyBinaryOp(BinaryOp.MINUS, rightValue);
							if(leftMinusRight != null && leftMinusRight.constant != null && leftMinusRight.variable != null && leftMinusRight.variable == solveVariable) {
								final Value negatedConstant = leftMinusRight.constant.applyUnaryOp(UnaryOp.NEGATIVE);
								if(negatedConstant instanceof IntegerValue) {
									if(((IntegerValue)leftMinusRight.coefficient).value != BigInteger.ZERO) {
										final BigInteger[] result = ((IntegerValue)negatedConstant).value.divideAndRemainder(((IntegerValue)leftMinusRight.coefficient).value);
										final boolean valid = (result[1] == BigInteger.ZERO);
										value = valid ? new IntegerValue(result[0]) : null;
									}
								} else if(negatedConstant instanceof RealValue) {
									if(!((RealValue)leftMinusRight.coefficient).value.equals(BigFraction.ZERO)) {
										final BigFraction result = ((RealValue)negatedConstant).value.divide(((RealValue)leftMinusRight.coefficient).value);
										value = new RealValue(result);
									}
								}
							}
						}
					}
				}
			}

			return value;
		}

		@Override
		public Value visit(final IfThenElseExpr e) {
			final Value conditionResult = e.cond.accept(this);
			if(!(conditionResult instanceof BooleanValue)) {
				return null;
			}

			if(((BooleanValue)conditionResult).value) {
				return e.thenExpr.accept(this);
			} else {
				return e.elseExpr.accept(this);
			}
		}

		@Override
		public Value visit(final IntExpr e) {
			return new IntegerValue(e.value);
		}

		@Override
		public Value visit(final RealExpr e) {
			final BigDecimal bd = e.value.scale() < 0 ? e.value.setScale(0) : e.value;
			return new RealValue(new BigFraction(bd.unscaledValue(), BigInteger.TEN.pow(bd.scale())));
		}

		@Override
		public Value visit(final UnaryExpr e) {
			final Value value = e.expr.accept(this);
			return value == null ? null : value.applyUnaryOp(e.op);
		}

		@Override
		public Value visit(final ArrayExpr e) {
			return null;
		}

		@Override
		public Value visit(final ArrayUpdateExpr e) {
			return null;
		}

		@Override
		public Value visit(final CastExpr e) {
			return null;
		}

		@Override
		public Value visit(final CondactExpr e) {
			return null;
		}

		@Override
		public Value visit(final FunctionCallExpr e) {
			return null;
		}

		@Override
		public Value visit(final NodeCallExpr e) {
			return null;
		}

		@Override
		public Value visit(final RecordExpr e) {
			return null;
		}

		@Override
		public Value visit(final RecordUpdateExpr e) {
			return null;
		}

		@Override
		public Value visit(final TupleExpr e) {
			return null;
		}

	}
}
