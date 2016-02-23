package com.rockwellcollins.atc.agree.codegen.ast.expr;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public class MATLABTypeCastExpr extends MATLABExpr {

	public final MATLABType type;
	public final MATLABExpr expr;

	public MATLABTypeCastExpr(MATLABType type, MATLABExpr expr) {
		Assert.isNotNull(type);		
		Assert.isNotNull(expr);
		this.expr = expr;
		this.type = type;
	}
	
	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
