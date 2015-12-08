package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codgen.visitors.MATLABAstVisitor;

/**
 * if isempty(preVar)
 *    preVar = coder.nullcopy(var);
 * end
 */
public class MATLABPreVarInit extends MATLABPersistentVarInit {

	public final String preVar;
	public final String var; 

	public MATLABPreVarInit(String preVar, String var) {
		Assert.isNotNull(preVar);
		Assert.isNotNull(var);
		this.preVar = preVar;
		this.var = var;
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
