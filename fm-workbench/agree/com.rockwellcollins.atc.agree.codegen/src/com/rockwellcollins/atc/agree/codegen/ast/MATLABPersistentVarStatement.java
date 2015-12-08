package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

public class MATLABPersistentVarStatement extends MATLABStatement {

	public final MATLABAssignment assignment;
	public final MATLABPersistentVarDecl decl;
	
	public MATLABPersistentVarStatement(MATLABAssignment assignment, MATLABPersistentVarDecl decl) {
		Assert.isNotNull(assignment);
		Assert.isNotNull(decl);
		this.assignment = assignment;
		this.decl = decl;
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
