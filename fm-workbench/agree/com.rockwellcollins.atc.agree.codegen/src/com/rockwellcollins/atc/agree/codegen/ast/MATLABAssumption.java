package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

public class MATLABAssumption extends MATLABStatement {
	
	public final MATLABExpr expr; 
	
	public MATLABAssumption(MATLABExpr expr) {
		Assert.isNotNull(expr);
		this.expr = expr;
	}


	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}
}
