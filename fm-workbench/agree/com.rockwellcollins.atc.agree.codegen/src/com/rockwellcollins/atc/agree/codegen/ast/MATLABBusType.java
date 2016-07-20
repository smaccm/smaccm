package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;

public class MATLABBusType extends MATLABType {
	
	public final String name;
	
	public MATLABBusType(String name) {
		Assert.isNotNull(name);
		this.name = name;
	}

	@Override
	public <T> T accept(MATLABTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getValueStr() {
		return null;
	}
	
	public String toString() {
		return name;
	}
	
}
