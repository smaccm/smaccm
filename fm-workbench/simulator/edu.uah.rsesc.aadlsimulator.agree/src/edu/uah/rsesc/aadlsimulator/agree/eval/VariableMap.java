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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import edu.uah.rsesc.aadlsimulator.agree.transformation.CreateSimulationGuarantee;
import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.FunctionCallExpr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordUpdateExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.Value;
import jkind.lustre.visitors.ExprVisitor;
import jkind.lustre.visitors.TypeReconstructor;

/**
 * Stores information regarding the mapping between identifiers and Variables. A single variable may have multiple identifiers.
 */
class VariableMap {
	private final Map<String, Variable> variables = new HashMap<>();
	private final Program program;
	private final InnerEvaluator evaluator;
	private final ChangeListener changeListener;
	private final AssertionVisitor assertionVisitor;
	private final RelatedExpressionBuilderVisitor relatedExpressionBuilderVisitor;
	private final TypeReconstructor typeReconstructor;
	private final ExpressionFlattener expressionFlattener;
	private final LinkedList<Expr> indeterminateAssertions = new LinkedList<>();
	private final Map<String, Expr> idToEquationExpressionMap;
	private BinaryExpr currentRelatedExpression;
	private boolean leftOfCurrentRelatedExpressionIsBoolean = false;
	private boolean processIndeterminateAssertions = false;

	public static interface ChangeListener {
		void onChange(VariableMap vm);
	}

	public static interface InnerEvaluator {
		/**
		 * Evaluates an expressions. Throws an exception if an error is encountered during evaluation.
		 * @param expr
		 * @return
		 */
		Value eval(Expr expr);
	}

	public VariableMap(final Program program, final InnerEvaluator evaluator, final ChangeListener changeListener) {
		this.program = Objects.requireNonNull(program, "program must not be null");
		this.evaluator = Objects.requireNonNull(evaluator, "evaluator must not be null");
		this.changeListener = changeListener;
		this.assertionVisitor = new AssertionVisitor();
		this.relatedExpressionBuilderVisitor = new RelatedExpressionBuilderVisitor();
		this.typeReconstructor = new TypeReconstructor(program);
		this.typeReconstructor.setNodeContext(program.getMainNode());
		this.expressionFlattener = new ExpressionFlattener(typeReconstructor);
		this.idToEquationExpressionMap = buildIdToEquationExpressionMap(program.getMainNode());

		if(program.nodes.size() != 1) {
			throw new RuntimeException("The program must have exactly 1 node.");
		}
	}

	private static Map<String, Expr> buildIdToEquationExpressionMap(final Node node) {
		final Map<String, Expr> map = new HashMap<>();

		// Build the map
		for(final Equation eq : node.equations) {
			// Only equations with one value on the left hand size are supported
			if(eq.lhs.size() == 1) {
				map.put(eq.lhs.get(0).id, eq.expr);
			}
		}

		return Collections.unmodifiableMap(map);
	}

	public VariableMap(final VariableMap base, final InnerEvaluator evaluator, final ChangeListener changeListener) {
		this.program = base.program;
		this.evaluator = Objects.requireNonNull(evaluator, "evaluator must not be null");
		this.changeListener = changeListener;
		this.assertionVisitor = new AssertionVisitor();
		this.relatedExpressionBuilderVisitor = new RelatedExpressionBuilderVisitor();
		this.typeReconstructor = base.typeReconstructor;
		this.expressionFlattener = base.expressionFlattener;
		this.idToEquationExpressionMap = base.idToEquationExpressionMap;

		// Clone the variable map. Ensure that each unique variable is only cloned once.
		for(final Variable var : new HashSet<>(base.variables.values())) {
			final Variable clonedVar = new Variable(var);
			for(final String id : clonedVar.ids) {
				variables.put(id, clonedVar);
			}
		}

		this.indeterminateAssertions.addAll(base.indeterminateAssertions);
		this.currentRelatedExpression = base.currentRelatedExpression;
		this.processIndeterminateAssertions = base.processIndeterminateAssertions;
	}

	public final Variable get(final String id) {
		return variables.get(id);
	}

	// Prints the data structure to the standard out
	public void dump() {
		final Set<Variable> uniqueVariables = new HashSet<>(variables.values());
		System.out.println("Unique Variable Count: " + uniqueVariables.size());
		for(final Variable var : uniqueVariables) {
			System.out.println("Variable:");
			for(final String id : var.ids) {
				System.out.println("\tID: " + id);
			}

			for(final BinaryExpr relatedExpr : var.relatedExpressions) {
				System.out.println("\tRE: " + relatedExpr);
			}
		}
	}

	public void processAssertions() {
		for(final Expr assertion : program.getMainNode().assertions) {
			processAssertion(assertion);
		}
	}

	public void processEquations() {
		for(final Equation eq : program.getMainNode().equations) {
			processEquation(eq);
		}
	}

	public final void processAssertion(final Expr assertion) {
		assertion.accept(assertionVisitor);
	}

	public final void processEquation(final Equation eq) {
		if(eq.lhs.size() == 1) {
			final IdExpr leftIdExpr  = eq.lhs.get(0);
			// Ignore equations for the simulation guarantees because those are related to dummy variables and not required for evaluating values.
			if(!leftIdExpr.id.startsWith(CreateSimulationGuarantee.SIMULATION_GUARANTEE_ID)) {
				final BinaryExpr binaryExpr = new BinaryExpr(leftIdExpr, BinaryOp.EQUAL, eq.expr);
				processAssertion(binaryExpr);
			}
		}
	}

	public void processIndeterminateAssertions() {
		// Process indeterminate assertions if the variable map has changed since the last attempt.
		while(processIndeterminateAssertions && indeterminateAssertions.size() > 0) {
			// Move all the unprocessed assertions to a new list
			final List<Expr> pendingAssertions = new ArrayList<>(indeterminateAssertions);
			indeterminateAssertions.clear();
			processIndeterminateAssertions = false;

			for(final Expr assertion : pendingAssertions) {
				processAssertion(assertion);
			}
		}
	}

	private void markVariableMapAsDirty() {
		processIndeterminateAssertions = true;
		if(changeListener != null) {
			changeListener.onChange(this);
		}
	}

	private void addEquality(final Expr idExpr1, final Expr idExpr2) {
		Objects.requireNonNull(idExpr1, "id1 must not be null");
		Objects.requireNonNull(idExpr2, "id2 must not be null");

		markVariableMapAsDirty();

		final Type id1ExprType = idExpr1.accept(typeReconstructor);
		if(id1ExprType instanceof NamedType){ // Named type equality
			final String id1 = idExpr1.toString();
			final String id2 = idExpr2.toString();
			final Variable var1 = variables.get(id1);
			final Variable var2 = variables.get(id2);

			if(var1 == null && var2 == null) {
				final Variable newVar = new Variable((NamedType)id1ExprType);
				newVar.ids.add(id1);
				newVar.ids.add(id2);
				variables.put(id1, newVar);
				variables.put(id2, newVar);
			} else if(var1 == null) {
				var2.ids.add(id1);
				variables.put(id1, var2);
			} else if(var2 == null) {
				var1.ids.add(id2);
				variables.put(id2, var1);
			} else {
				// Decide which variable to keep
				final Variable keepVar;
				final Variable removeVar;
				if(var1.ids.size() <= var2.ids.size()) {
					keepVar = var1;
					removeVar = var2;
				} else {
					keepVar = var2;
					removeVar = var1;
				}

				// Merge the remove var fields into the keep var
				keepVar.ids.addAll(removeVar.ids);
				keepVar.relatedExpressions.addAll(removeVar.relatedExpressions);

				// Update the variable map to only reference the keep var
				for(final String removeId : removeVar.ids) {
					variables.put(removeId, keepVar);
				}
			}
		}
	}

	private void addRelatedExpression(final Expr idExpr, final BinaryExpr relatedExpr) {
		Objects.requireNonNull(relatedExpr, "relatedExpr must not be null");
		final Type type = idExpr.accept(typeReconstructor);

		if(type instanceof NamedType) {
			// Only add the related expression if the expression can be used to solve the variable.
			if((type != NamedType.BOOL && !leftOfCurrentRelatedExpressionIsBoolean) || (type == NamedType.BOOL && leftOfCurrentRelatedExpressionIsBoolean)) {
				final Variable var = getOrCreate(idExpr, (NamedType)type);
				var.relatedExpressions.add(relatedExpr);
				markVariableMapAsDirty();
			}
		}
	}

	private Variable getOrCreate(final Expr id, final NamedType type) {
		final String variableId = id.toString();
		Variable var = variables.get(variableId);

		// In case var is null
		if(var == null) {
			var = new Variable(type);
			var.ids.add(variableId);
			variables.put(variableId, var);
			markVariableMapAsDirty();
		}

		return var;
	}

	private static boolean isVariableIdExpr(final Expr expr) {
		return expr instanceof IdExpr || expr instanceof RecordAccessExpr || expr instanceof ArrayAccessExpr;
	}

	// This visitor is meant to be used on expressions which are asserted in order to build a variable map.
	// The visited expressions are assumed to be true and the variable map is updated accordingly.
	private class AssertionVisitor implements ExprVisitor<Object> {
		@Override
		public Object visit(final BinaryExpr e) {
			if(e.op == BinaryOp.AND) {
				e.left.accept(this);
				e.right.accept(this);
			} else if(e.op == BinaryOp.EQUAL) {
				for(final BinaryExpr flattenedExpression : expressionFlattener.flattenEqualsExpression(e)) {
					final boolean leftIsId = isVariableIdExpr(flattenedExpression.left);
					final boolean rightIsId = isVariableIdExpr(flattenedExpression.right);

					if(leftIsId && rightIsId) {
						// Add to variable map
						addEquality(flattenedExpression.left, flattenedExpression.right);
					} else {
						// Build related expressions
						processRelatedExpression(flattenedExpression);
					}
				}
			} else if(e.op == BinaryOp.OR) {
				final Value leftValue = evaluator.eval(e.left);
				if(leftValue instanceof BooleanValue && !((BooleanValue)leftValue).value) {
					// Treat the right side as an assertion
					e.right.accept(this);
				} else {
					final Value rightValue = evaluator.eval(e.right);
					if(leftValue == null && rightValue == null) {
						indeterminateAssertions.add(e);
					} else if(rightValue instanceof BooleanValue && !((BooleanValue)rightValue).value) {
						// Treat the left side as an assertion
						e.left.accept(this);
					}
				}
			} else if(e.op == BinaryOp.IMPLIES) {
				// Evaluate left... if left is true, treat right as assertion.
				final Value leftValue = evaluator.eval(e.left);
				if(leftValue == null) {
					indeterminateAssertions.add(e);
				} else if(leftValue instanceof BooleanValue && ((BooleanValue)leftValue).value) {
					// Treat the right side as an assertion
					e.right.accept(this);
				}
			}

			return null;
		}

		// Visit the related expression using the related expression builder
		private void processRelatedExpression(final BinaryExpr e) {
			currentRelatedExpression = e;
			leftOfCurrentRelatedExpressionIsBoolean = e.left.accept(typeReconstructor) == NamedType.BOOL;

			try {
				e.accept(relatedExpressionBuilderVisitor);
			} finally {
				currentRelatedExpression = null;
				leftOfCurrentRelatedExpressionIsBoolean = false;
			}
		}

		@Override
		public Object visit(final IdExpr e) {
			// Find the references equation so that the expression can be asserted
			final Expr eqExpr = idToEquationExpressionMap.get(e.id);
			if(eqExpr != null) {
				eqExpr.accept(this);
			}

			return null;
		}

		@Override
		public Object visit(final IfThenElseExpr e) {
			final Value condValue = evaluator.eval(e.cond);

			if(condValue instanceof BooleanValue) {
				if(((BooleanValue)condValue).value) {
					e.thenExpr.accept(this);
				} else {
					e.elseExpr.accept(this);
				}
			} else { // Unable to evaluate conditional
				// Add assertion to list of indeterminate assertions
				indeterminateAssertions.add(e);
			}

			return null;
		}

		@Override
		public Object visit(final BoolExpr e) {
			return null;
		}

		@Override
		public Object visit(final CastExpr e) {
			return null;
		}

		@Override
		public Object visit(final CondactExpr e) {
			return null;
		}

		@Override
		public Object visit(final IntExpr e) {
			return null;
		}

		@Override
		public Object visit(final FunctionCallExpr e) {
			return null;
		}

		@Override
		public Object visit(final NodeCallExpr e) {
			return null;
		}

		@Override
		public Object visit(final RealExpr e) {
			return null;
		}

		@Override
		public Object visit(final RecordAccessExpr e) {
			return null;
		}

		@Override
		public Object visit(final RecordExpr e) {
			return null;
		}

		@Override
		public Object visit(final RecordUpdateExpr e) {
			return null;
		}

		@Override
		public Object visit(final TupleExpr e) {
			return null;
		}

		@Override
		public Object visit(final UnaryExpr e) {
			return null;
		}

		@Override
		public Object visit(final ArrayAccessExpr e) {
			return null;
		}

		@Override
		public Object visit(final ArrayExpr e) {
			return null;
		}

		@Override
		public Object visit(final ArrayUpdateExpr e) {
			return null;
		}
	}

	private class RelatedExpressionBuilderVisitor implements ExprVisitor<Object> {
		@Override
		public Object visit(final IdExpr e) {
			addRelatedExpression(e, currentRelatedExpression);
			return null;
		}

		@Override
		public Object visit(final ArrayAccessExpr e) {
			addRelatedExpression(e, currentRelatedExpression);
			return null;
		}

		@Override
		public Object visit(final RecordAccessExpr e) {
			addRelatedExpression(e, currentRelatedExpression);
			return null;
		}

		@Override
		public Object visit(final BinaryExpr e) {
			// Do not process contents of operations which are not solveable
			if(e.op != BinaryOp.GREATER &&
					e.op != BinaryOp.GREATEREQUAL &&
					e.op != BinaryOp.LESSEQUAL &&
					e.op != BinaryOp.LESS &&
					e.op != BinaryOp.XOR) {
				e.left.accept(this);
				e.right.accept(this);
			}

			return null;
		}

		@Override
		public Object visit(UnaryExpr e) {
			e.expr.accept(this);
			return null;
		}

		@Override
		public Object visit(IfThenElseExpr e) {
			e.thenExpr.accept(this);
			e.elseExpr.accept(this);
			return null;
		}

		@Override
		public Object visit(ArrayExpr e) {
			return null;
		}

		@Override
		public Object visit(ArrayUpdateExpr e) {
			return null;
		}

		@Override
		public Object visit(BoolExpr e) {
			return null;
		}

		@Override
		public Object visit(CastExpr e) {
			return null;
		}

		@Override
		public Object visit(CondactExpr e) {
			return null;
		}

		@Override
		public Object visit(IntExpr e) {
			return null;
		}

		@Override
		public Object visit(FunctionCallExpr e) {
			return null;
		}

		@Override
		public Object visit(NodeCallExpr e) {
			return null;
		}

		@Override
		public Object visit(RealExpr e) {
			return null;
		}

		@Override
		public Object visit(RecordExpr e) {
			return null;
		}

		@Override
		public Object visit(RecordUpdateExpr e) {
			return null;
		}

		@Override
		public Object visit(TupleExpr e) {
			return null;
		}
	}
}
