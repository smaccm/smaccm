package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABArrowFunctionCall extends MATLABFunctionCall {

	public final String name; 

	public final String firstTimeVar;
	
	public final MATLABExpr leftExpr;
	
	public final MATLABExpr rightExpr;
	
	public MATLABArrowFunctionCall(String name, String firstTimeVar, MATLABExpr leftExpr, MATLABExpr rightExpr) {
		Assert.isNotNull(name);
		Assert.isNotNull(firstTimeVar);
		Assert.isNotNull(leftExpr);
		Assert.isNotNull(rightExpr);
		this.name = name;
		this.firstTimeVar = firstTimeVar; 
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
