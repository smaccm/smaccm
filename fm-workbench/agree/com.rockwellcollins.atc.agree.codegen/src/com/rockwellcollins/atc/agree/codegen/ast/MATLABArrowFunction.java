package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;


/**
 * %At the beginning of the primary function
 * persistent first_time
 * function output = arrowFunction(first_time, left, right)
 * if isempty(first_time)
 *    output = left;
 * else
 *    output = right;
 * end
 * %At the end of the primary function 
 * first_time = false;
 */

public class MATLABArrowFunction extends MATLABSubFunction {
	
	public String firstTimeVar = "first_time";
	
	public MATLABArrowFunction() {
		this.name = "arrowFunction";
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
