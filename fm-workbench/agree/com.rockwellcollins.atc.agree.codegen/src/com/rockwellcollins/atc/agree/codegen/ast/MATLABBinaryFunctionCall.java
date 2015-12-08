package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codgen.visitors.MATLABExprVisitor;

public class MATLABBinaryFunctionCall extends MATLABFunctionCall {
	
	public final String name; 
	
	public final MATLABExpr leftExpr;
	
	public final MATLABExpr rightExpr;
	
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
