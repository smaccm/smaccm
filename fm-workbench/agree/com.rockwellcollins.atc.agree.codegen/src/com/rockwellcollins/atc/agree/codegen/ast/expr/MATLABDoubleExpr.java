package com.rockwellcollins.atc.agree.codegen.ast.expr;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABDoubleExpr extends MATLABExpr {

	public final double value;

	public MATLABDoubleExpr(double value) {
		Assert.isNotNull(value);
		this.value = value;
	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
