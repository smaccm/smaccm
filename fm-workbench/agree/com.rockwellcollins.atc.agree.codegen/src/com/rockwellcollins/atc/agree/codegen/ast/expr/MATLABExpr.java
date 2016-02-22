package com.rockwellcollins.atc.agree.codegen.ast.expr;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABAst;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

public abstract class MATLABExpr extends MATLABAst {

	@SuppressWarnings("unchecked")
	@Override
	public <T, S extends T> S accept(MATLABAstVisitor<T, S> visitor) {
		return accept((MATLABExprVisitor<S>) visitor);
	}
	
	public abstract <T> T accept(MATLABExprVisitor<T> visitor);

}