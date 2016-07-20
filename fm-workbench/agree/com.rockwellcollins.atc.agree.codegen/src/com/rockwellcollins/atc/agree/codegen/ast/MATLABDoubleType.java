package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;


public class MATLABDoubleType extends MATLABType {

	public final double defaultValue;
	public final String name = "double";
	
	public MATLABDoubleType() {
		this.defaultValue = 0;
	}

	@Override
	public <T> T accept(MATLABTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getValueStr() {
		return Double.toString(defaultValue);
	}
	
	public String toString() {
		return name;
	}
	
}
