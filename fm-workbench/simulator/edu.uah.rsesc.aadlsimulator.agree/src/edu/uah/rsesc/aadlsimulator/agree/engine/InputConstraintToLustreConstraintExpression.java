package edu.uah.rsesc.aadlsimulator.agree.engine;

import java.util.Objects;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntervalExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ScalarExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.SetExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.util.InputConstraintSwitch;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Expr;

/**
 * Builds an expression which is true if a constraint for a specified variable is satisfied.
 */
public class InputConstraintToLustreConstraintExpression {
	private final InputConstraintToLustreValueExpression innerExprEvaluator;
	private Expr constrainedVariableExpr;
	private final InputConstraintSwitch<Expr> evalSwitch = new InputConstraintSwitch<Expr>() {
		@Override
		public Expr caseIntervalExpression(final IntervalExpression object) {
			// Build an expression that is true if the constrained value is in the interval
			// It is guaranteed that at least one of left/right will be valid.
			Expr expr = null;
			if(object.getLeft() != null) {
				final BinaryOp op = object.isRightClosed() ? BinaryOp.GREATEREQUAL : BinaryOp.GREATER;
				expr = new BinaryExpr(constrainedVariableExpr, op, innerExprEvaluator.eval(object.getLeft()));				
			}
			
			if(object.getRight() != null) {
				final BinaryOp op = object.isRightClosed() ? BinaryOp.LESSEQUAL : BinaryOp.LESS;
				final Expr rightExpr = new BinaryExpr(constrainedVariableExpr, op, innerExprEvaluator.eval(object.getRight()));	
				if(expr == null) {
					expr = rightExpr;
				} else {
					expr = new BinaryExpr(expr, BinaryOp.AND, rightExpr);
				}
			}
			
			return expr;
		}

		@Override
		public Expr caseSetExpression(final SetExpression object) {
			// Build an expression which is true if the constrained value matches any of the values in the set.
			Expr expr = null;
			for(final ScalarExpression se : object.getMembers()) {
				final Expr newExpr = new BinaryExpr(constrainedVariableExpr, BinaryOp.EQUAL, innerExprEvaluator.eval(se));
				if(expr == null) {
					expr = newExpr;
				} else {
					expr = new BinaryExpr(expr, BinaryOp.OR, newExpr);
				}
			}
			
			return expr;
		}
				
		@Override
		public Expr caseScalarExpression(final ScalarExpression object) {
			return new BinaryExpr(constrainedVariableExpr, BinaryOp.EQUAL, innerExprEvaluator.eval(object));
		}
	};
	
	public InputConstraintToLustreConstraintExpression(final ReferenceEvaluator referenceEvaluator) {
		this.innerExprEvaluator = new InputConstraintToLustreValueExpression(Objects.requireNonNull(referenceEvaluator, "referenceEvaluator must not be null"));
	}
	
	public Expr eval(final InputConstraint ic, final Expr constrainedVariableExpr) {
		this.constrainedVariableExpr = Objects.requireNonNull(constrainedVariableExpr, "constrainedVariableId must not be null");
		return evalSwitch.doSwitch(ic);
	}
}
