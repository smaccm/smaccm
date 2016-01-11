package com.rockwellcollins.atc.agree.codegen.ast.expr;

import java.math.BigInteger;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

import jkind.Assert;

public class MATLABIntExpr extends MATLABExpr {
	public final BigInteger value;

	public MATLABIntExpr(BigInteger value) {
		Assert.isNotNull(value);
		this.value = value;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
