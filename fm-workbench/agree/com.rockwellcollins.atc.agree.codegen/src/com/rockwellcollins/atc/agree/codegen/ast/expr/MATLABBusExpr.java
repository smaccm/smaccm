package com.rockwellcollins.atc.agree.codegen.ast.expr;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABBusExpr extends MATLABExpr {

	public final MATLABIdExpr id;

	public MATLABBusExpr(MATLABExpr id) {
		Assert.isNotNull(id);
		if(id instanceof MATLABIdExpr){
			this.id = (MATLABIdExpr) id;
		}
		else{
			throw new IllegalArgumentException();
		}

	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
