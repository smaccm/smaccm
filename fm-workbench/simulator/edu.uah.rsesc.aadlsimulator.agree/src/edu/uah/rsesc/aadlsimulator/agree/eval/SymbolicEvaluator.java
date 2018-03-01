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
import java.util.Deque;
import java.util.Objects;

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
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;
import jkind.lustre.visitors.ExprVisitor;
import jkind.util.BigFraction;

class SymbolicEvaluator {
	private final Visitor visitor;

	public static interface InnerNumericEvaluator {
		public Value eval(final Expr expr);
	}

	public SymbolicEvaluator(final VariableMap variableMap, final InnerNumericEvaluator numericEvaluator) {
		this.visitor = new Visitor(variableMap, numericEvaluator);
	}

	public SymbolicValue eval(final Variable solveVariable, final Expr expr) {
		return visitor.eval(solveVariable, expr);
	}

	private static class Visitor implements ExprVisitor<SymbolicValue>  {
		private final VariableMap variableMap;
		private final InnerNumericEvaluator numericEvaluator;
		private final Deque<Variable> solveVariables = new ArrayDeque<>();

		public Visitor(final VariableMap variableMap, final InnerNumericEvaluator numericEvaluator) {
			this.variableMap = Objects.requireNonNull(variableMap, "variableMap must not be null");
			this.numericEvaluator = Objects.requireNonNull(numericEvaluator, "numericEvaluator must not be null");
		}

		public final SymbolicValue eval(final Variable solveVariable, final Expr expr) {
			try {
				solveVariables.push(solveVariable);
				return expr.accept(this);
			} finally {
				solveVariables.pop();
			}
		}

		@Override
		public SymbolicValue visit(final BinaryExpr e) {
			if(e.op == BinaryOp.IMPLIES) {
				return null;
			}

			final SymbolicValue leftValue = e.left.accept(this);
			if(leftValue == null) {
				return null;
			}

			final SymbolicValue rightValue = e.right.accept(this);
			if(rightValue == null) {
				return null;
			}

			return leftValue.applyBinaryOp(e.op, rightValue);
		}

		@Override
		public SymbolicValue visit(final BoolExpr e) {
			return new SymbolicValue(null, null, BooleanValue.fromBoolean(e.value));
		}

		@Override
		public SymbolicValue visit(final IdExpr e) {
			return eval(e);
		}

		@Override
		public SymbolicValue visit(final RecordAccessExpr e) {
			return eval(e);
		}

		@Override
		public SymbolicValue visit(final ArrayAccessExpr e) {
			return eval(e);
		}

		private SymbolicValue eval(final Expr idExpr) {
			// Get the variable
			final String id = idExpr.toString();
			final Variable var = variableMap.get(id);

			if(var == null) {
				return null;
			} else if(solveVariables.peek() == var) {
				return createDefaultValue(var);
			}

			final SymbolicValue value;
			final Value numericalValue = numericEvaluator.eval(idExpr);
			if(numericalValue != null) {
				value = new SymbolicValue(null, null, numericalValue);
			} else {
				value = createDefaultValue(var);
			}

			return value;
		}

		@Override
		public SymbolicValue visit(final IfThenElseExpr e) {
			final SymbolicValue conditionResult = e.cond.accept(this);
			if(conditionResult == null || !(conditionResult.constant instanceof BooleanValue)) {
				return null;
			}

			if(((BooleanValue)conditionResult.constant).value) {
				return e.thenExpr.accept(this);
			} else {
				return e.elseExpr.accept(this);
			}
		}

		@Override
		public SymbolicValue visit(final IntExpr e) {
			return new SymbolicValue(null, null, new IntegerValue(e.value));
		}

		@Override
		public SymbolicValue visit(final RealExpr e) {
			final BigDecimal bd = e.value.scale() < 0 ? e.value.setScale(0) : e.value;
			return new SymbolicValue(null, null, new RealValue(new BigFraction(bd.unscaledValue(), BigInteger.TEN.pow(bd.scale()))));
		}

		@Override
		public SymbolicValue visit(final UnaryExpr e) {
			final SymbolicValue value = e.expr.accept(this);
			if(value == null) {
				return null;
			} else {
				return value.applyUnaryOp(e.op);
			}
		}

		private SymbolicValue createDefaultValue(final Variable var) {
			if(var.type == NamedType.INT || var.type == NamedType.BOOL) {
				return new SymbolicValue(new IntegerValue(BigInteger.ONE), var, null);
			} else if(var.type == NamedType.REAL) {
				return new SymbolicValue(new RealValue(new BigFraction(BigInteger.ONE)), var, null);
			} else {
				return null;
			}
		}

		@Override
		public SymbolicValue visit(final RecordUpdateExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final TupleExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final ArrayExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final ArrayUpdateExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final RecordExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final CastExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final CondactExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final NodeCallExpr e) {
			return null;
		}

		@Override
		public SymbolicValue visit(final FunctionCallExpr e) {
			return null;
		}
	}
}