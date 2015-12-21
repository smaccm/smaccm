package com.rockwellcollins.atc.agree.codegen.ast;

import java.util.Map;
import java.util.SortedMap;

import jkind.Assert;
import jkind.util.Util;

import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

public class MATLABLocalBusVarInit extends MATLABStatement {

	public final String localVar;
	public final SortedMap<String, MATLABExpr> fields;

	public MATLABLocalBusVarInit(String localVar, Map<String, MATLABExpr> fields) {
		Assert.isNotNull(localVar);
		Assert.isNotNull(fields);
		Assert.isTrue(fields.size() > 0);
		this.localVar = localVar;
		this.fields = Util.safeStringSortedMap(fields);
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
