package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTVisitor;

import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Location;

public class AgreeEquation extends Equation implements AgreeASTElement {

	public final EObject reference;

	public AgreeEquation(List<IdExpr> lhs, Expr expr, EObject reference) {
		super(Location.NULL, lhs, expr);
		this.reference = reference;
	}

	public AgreeEquation(IdExpr id, Expr expr, EObject reference) {
		super(Location.NULL, id, expr);
		this.reference = reference;
	}

	public AgreeEquation(Equation eq, EObject reference) {
		this(eq.lhs, eq.expr, reference);
	}

	@Override
	public <T> T accept(AgreeASTVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
