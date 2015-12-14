package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;

public class MATLABBoolType extends MATLABType {

	public final boolean defaultValue;
	
	public MATLABBoolType() {
		super("boolean");
		this.defaultValue = false;
	}

	public String getValueStr(){
		return Boolean.toString(defaultValue);
	}
	
	@Override
	public <T> T accept(MATLABTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
