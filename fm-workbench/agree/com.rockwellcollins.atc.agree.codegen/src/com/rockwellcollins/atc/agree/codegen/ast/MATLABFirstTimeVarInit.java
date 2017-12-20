package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

import jkind.Assert;

public class MATLABFirstTimeVarInit extends MATLABPersistentVarInit {

	public final String firstTimeVar;

	public MATLABFirstTimeVarInit(String firstTimeVar) {
		Assert.isNotNull(firstTimeVar);
		this.firstTimeVar = firstTimeVar;
	}

	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
