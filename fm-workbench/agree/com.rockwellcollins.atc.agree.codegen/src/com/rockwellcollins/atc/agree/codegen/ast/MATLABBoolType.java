package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;

public class MATLABBoolType extends MATLABType {

	public final boolean defaultValue;
	
	public final String name = "boolean";
	
	public MATLABBoolType() {
		this.defaultValue = false;
	}

	public String getValueStr(){
		return Boolean.toString(defaultValue);
	}
	
	@Override
	public <T> T accept(MATLABTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	public String toString() {
		return name;
	}
	
}
