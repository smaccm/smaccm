package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codgen.visitors.MATLABExprVisitor;

import jkind.Assert;

public class MATLABBoolExpr extends MATLABExpr {
	public final boolean value;

	public MATLABBoolExpr(boolean value) {
		Assert.isNotNull(value);
		this.value = value;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
