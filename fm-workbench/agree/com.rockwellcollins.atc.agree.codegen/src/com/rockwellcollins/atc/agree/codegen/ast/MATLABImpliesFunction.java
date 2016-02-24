package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

/**function output = impliesFunction(left, right)
 * 		output = (not(left) || right);
 * end
 */
public class MATLABImpliesFunction extends MATLABSubFunction {
	
	public MATLABImpliesFunction() {
		this.name = "impliesFunction";
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}