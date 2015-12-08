package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

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
