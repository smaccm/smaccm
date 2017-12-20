package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;

public class MATLABSingleType extends MATLABType {

	public final float defaultValue;
	public final String name = "single";

	public MATLABSingleType() {
		defaultValue = 0;
	}

	@Override
	public <T> T accept(MATLABTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getValueStr() {
		return Float.toString(defaultValue);
	}

	@Override
	public String toString() {
		return name;
	}

}
