package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeInitExpr;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

public class MATLABPreLocalVarInit extends MATLABPersistentVarInit {

	public final String preVar;
	public final MATLABTypeInitExpr typeInitExpr; 

	public MATLABPreLocalVarInit(String preVar, MATLABTypeInitExpr typeInitExpr) {
		Assert.isNotNull(preVar);
		Assert.isNotNull(typeInitExpr);
		this.preVar = preVar;
		this.typeInitExpr = typeInitExpr;
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}
}
