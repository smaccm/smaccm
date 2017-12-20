package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

import jkind.Assert;

public class MATLABPersistentVarDecl extends MATLABStatement {

	public final String var;

	public MATLABPersistentVarDecl(String var) {
		Assert.isNotNull(var);
		this.var = var;
	}

	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}