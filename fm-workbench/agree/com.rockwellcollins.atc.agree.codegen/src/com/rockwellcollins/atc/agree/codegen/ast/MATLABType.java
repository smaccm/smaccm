package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeVisitor;


public abstract class MATLABType {
	
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
