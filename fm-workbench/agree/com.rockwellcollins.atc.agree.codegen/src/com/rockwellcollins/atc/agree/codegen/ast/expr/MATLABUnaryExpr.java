package com.rockwellcollins.atc.agree.codegen.ast.expr;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

import jkind.Assert;

public class MATLABUnaryExpr extends MATLABExpr {
	public final MATLABUnaryOp op;
	public MATLABExpr expr;

	public MATLABUnaryExpr(MATLABUnaryOp op, MATLABExpr expr) {
		Assert.isNotNull(op);
		Assert.isNotNull(expr);
		this.op = op;
		this.expr = expr;
	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
}

