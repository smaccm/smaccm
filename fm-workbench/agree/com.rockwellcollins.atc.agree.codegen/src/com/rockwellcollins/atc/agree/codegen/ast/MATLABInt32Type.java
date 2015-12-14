package com.rockwellcollins.atc.agree.codegen.ast;

import java.math.BigInteger;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;

public class MATLABInt32Type extends MATLABType {

	public final BigInteger defaultValue;
	
	public MATLABInt32Type() {
		super("int32");
		this.defaultValue = BigInteger.valueOf(0);
	}

	@Override
	public <T> T accept(MATLABTypeVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getValueStr() {
		return defaultValue.toString();
	}
	
}
