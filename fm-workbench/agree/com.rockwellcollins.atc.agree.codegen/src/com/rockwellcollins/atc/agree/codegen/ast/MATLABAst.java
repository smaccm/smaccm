package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABPrettyPrintVisitor;

public abstract class MATLABAst {
	
	@Override
	public String toString() {
		MATLABPrettyPrintVisitor visitor = new MATLABPrettyPrintVisitor();
		accept(visitor);
		return visitor.toString();
	}
	
	/**
	 * Accept.
	 *
	 * @param <T> the generic type
	 * @param <S> the generic type
	 * @param visitor the visitor
	 * @return the t
	 */
	public abstract <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor);
	
}
