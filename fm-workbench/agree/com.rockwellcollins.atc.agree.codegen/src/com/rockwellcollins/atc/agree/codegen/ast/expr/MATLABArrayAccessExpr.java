package com.rockwellcollins.atc.agree.codegen.ast.expr;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABExprVisitor;

import jkind.Assert;

public class MATLABArrayAccessExpr extends MATLABExpr {

	/** The array. */
	public MATLABExpr array;

	/** The index. */
	public MATLABExpr index;

	/**
	 * Instantiates a new array access expr.
	 *
	 * @param location the location
	 * @param array the array
	 * @param index the index
	 */
	public MATLABArrayAccessExpr(MATLABExpr array, MATLABExpr index) {
		Assert.isNotNull(array);
		Assert.isNotNull(index);
		this.array = array;
		this.index = index;
	}

	@Override
	public <T> T accept(MATLABExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
