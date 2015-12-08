package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codgen.visitors.MATLABExprVisitor;

public class MATLABIfFunctionCall extends MATLABFunctionCall {
	
	public String name = "ifFunction";
	
	public final MATLABExpr cond; 
	
	public final MATLABExpr ifExpr;
	
	public final MATLABExpr elseExpr;
	
	public MATLABIfFunctionCall(MATLABExpr cond, MATLABExpr ifExpr, MATLABExpr elseExpr) {
		Assert.isNotNull(cond);
		Assert.isNotNull(ifExpr);
		Assert.isNotNull(elseExpr);
		this.cond = cond;
		this.ifExpr = ifExpr;
		this.elseExpr = elseExpr;
	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
