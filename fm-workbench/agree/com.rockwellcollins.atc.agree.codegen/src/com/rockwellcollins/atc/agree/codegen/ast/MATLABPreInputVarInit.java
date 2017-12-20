package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

import jkind.Assert;

public class MATLABPreInputVarInit extends MATLABPersistentVarInit {

	public final String preVar;
	public final String var;

	public MATLABPreInputVarInit(String preVar, String var) {
		Assert.isNotNull(preVar);
		Assert.isNotNull(var);
		this.preVar = preVar;
		this.var = var;
	}

	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
