package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

import jkind.Assert;

public class MATLABAssignment extends MATLABStatement {
	public final MATLABIdExpr varToAssign;
	public final MATLABExpr expr;

	public MATLABAssignment(MATLABIdExpr varExpr, MATLABExpr expr) {
		Assert.isNotNull(varExpr);
		Assert.isNotNull(expr);
		varToAssign = varExpr;
		this.expr = expr;
	}

	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
