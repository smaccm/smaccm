package com.rockwellcollins.atc.agree.codegen.ast.expr;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABBinaryFunctionCall extends MATLABFunctionCall {
	
	public final String name; 
	
	public MATLABExpr leftExpr;
	
	public MATLABExpr rightExpr;
	
	public MATLABBinaryFunctionCall(String name, MATLABExpr leftExpr, MATLABExpr rightExpr) {
		Assert.isNotNull(name);
		Assert.isNotNull(leftExpr);
		Assert.isNotNull(rightExpr);
		this.name = name;
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
