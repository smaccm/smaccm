package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codgen.visitors.MATLABExprVisitor;

import jkind.Assert;

public class MATLABBinaryExpr extends MATLABExpr {
	public final MATLABExpr left;
	public final MATLABBinaryOp op;
	public final MATLABExpr right;

	public MATLABBinaryExpr(MATLABExpr left, MATLABBinaryOp op, MATLABExpr right) {
		Assert.isNotNull(left);
		Assert.isNotNull(op);
		Assert.isNotNull(right);
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}


