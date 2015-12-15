package com.rockwellcollins.atc.agree.codegen.ast.expr;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABBusElementUpdateExpr extends MATLABExpr {

	
	public final MATLABExpr busExpr;
	public final MATLABIdExpr element;
	public MATLABExpr value;

	public MATLABBusElementUpdateExpr(MATLABExpr busExpr, MATLABIdExpr element, MATLABExpr value) {
		Assert.isNotNull(busExpr);
		Assert.isNotNull(element);
		Assert.isNotNull(value);
		this.busExpr = busExpr;
		this.element = element;
		this.value = value;
	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
