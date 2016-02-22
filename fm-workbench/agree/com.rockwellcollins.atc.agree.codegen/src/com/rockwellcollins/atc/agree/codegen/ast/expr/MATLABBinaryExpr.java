package com.rockwellcollins.atc.agree.codegen.ast.expr;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

import jkind.Assert;

public class MATLABBinaryExpr extends MATLABExpr {
	public MATLABExpr left;
	public final MATLABBinaryOp op;
	public MATLABExpr right;

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


