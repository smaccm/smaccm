package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codgen.visitors.MATLABExprVisitor;

public class MATLABIntCastExpr extends MATLABExpr {

	public final MATLABExpr expr;

	public MATLABIntCastExpr(MATLABExpr expr) {
		Assert.isNotNull(expr);
		this.expr = expr;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
