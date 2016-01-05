package com.rockwellcollins.atc.agree.codegen.ast;

import java.util.ArrayList;
import java.util.List;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

public class MATLABPrimaryFunction extends MATLABFunction {
	
    public List<MATLABIdExpr> inputs = new ArrayList<>();
    public List<MATLABStatement> statements = new ArrayList<>();
    public List<MATLABFunction> functions = new ArrayList<>();
    public List<MATLABPersistentVarDecl> persistentVarDecl = new ArrayList<>();
	
	public MATLABPrimaryFunction(String name, List<MATLABIdExpr> inputs, List<MATLABPersistentVarDecl> persistentVarDecl, List<MATLABStatement> statements, List<MATLABFunction> functions) {
		Assert.isNotNull(name);
		Assert.isNotNull(inputs);
		Assert.isNotNull(statements);
		Assert.isNotNull(functions);
		Assert.isNotNull(persistentVarDecl);
		this.name = name;
		this.inputs = inputs;
		this.statements = statements;
		this.functions = functions;
		this.persistentVarDecl = persistentVarDecl;
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}
	
}
