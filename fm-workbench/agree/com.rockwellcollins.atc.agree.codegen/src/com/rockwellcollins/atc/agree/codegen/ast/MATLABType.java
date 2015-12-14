package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;

import jkind.Assert;

public abstract class MATLABType {
	
	public final String name;
	
	public MATLABType(String name) {
		Assert.isNotNull(name);
		this.name = name;
	}
	
	public abstract String getValueStr();
	
	/**
	 * Accept.
	 *
	 * @param <T> the generic type
	 * @param visitor the visitor
	 * @return the t
	 */
	public abstract <T> T accept(MATLABTypeVisitor<T> visitor);

}
