package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

import jkind.Assert;

public class MATLABProperty extends MATLABStatement {

	public final String id;

	public MATLABProperty(String id) {
		Assert.isNotNull(id);
		this.id = id;
	}

	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
