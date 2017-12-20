package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;

public class MATLABBoolType extends MATLABType {

	public final boolean defaultValue;

	public final String name = "boolean";

	public MATLABBoolType() {
		defaultValue = false;
	}

	@Override
	public String getValueStr() {
		return Boolean.toString(defaultValue);
	}

	@Override
	public <T> T accept(MATLABTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return name;
	}

}
