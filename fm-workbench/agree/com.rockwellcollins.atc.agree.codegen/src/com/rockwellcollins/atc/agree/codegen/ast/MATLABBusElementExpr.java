package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codgen.visitors.MATLABExprVisitor;

public class MATLABBusElementExpr extends MATLABExpr {

	public final MATLABBusExpr busExpr;
	public final MATLABIdExpr element;

	public MATLABBusElementExpr(MATLABBusExpr busExpr, MATLABIdExpr element) {
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
