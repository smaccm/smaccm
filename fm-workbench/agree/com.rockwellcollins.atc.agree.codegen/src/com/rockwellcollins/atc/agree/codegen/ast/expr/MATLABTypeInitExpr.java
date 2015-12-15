package com.rockwellcollins.atc.agree.codegen.ast.expr;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABTypeInitExpr extends MATLABExpr {

	public final MATLABType type;

	public MATLABTypeInitExpr(MATLABType type) {
		Assert.isNotNull(type);		
		this.type = type;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
