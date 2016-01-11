package com.rockwellcollins.atc.agree.codegen.ast.expr;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABBusElementExpr extends MATLABExpr {

	public final MATLABExpr busExpr;
	public final MATLABIdExpr element;

	public MATLABBusElementExpr(MATLABExpr busExpr, MATLABIdExpr element) {
		Assert.isNotNull(busExpr);
		Assert.isNotNull(element);
		this.busExpr = busExpr;
		this.element = element;
	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
