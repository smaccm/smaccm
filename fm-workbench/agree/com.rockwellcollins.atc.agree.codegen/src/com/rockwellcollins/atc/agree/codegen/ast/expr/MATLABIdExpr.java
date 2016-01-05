package com.rockwellcollins.atc.agree.codegen.ast.expr;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

import jkind.Assert;

public class MATLABIdExpr extends MATLABExpr {
	
	public final String id;
	
	public MATLABIdExpr(String id) {
		Assert.isNotNull(id);
		this.id = id;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
