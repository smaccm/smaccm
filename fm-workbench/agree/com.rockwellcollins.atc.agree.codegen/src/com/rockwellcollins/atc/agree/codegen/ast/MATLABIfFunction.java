package com.rockwellcollins.atc.agree.codegen.ast;

import com.rockwellcollins.atc.agree.codegen.visitors.MATLABAstVisitor;

/**function output = ifFunction(cond, ifBranch, elseBranch)
 * if(cond)
 *    output = ifBranch;
 * else
 *    output = elseBranch;
 * end
 */

public class MATLABIfFunction extends MATLABSubFunction {
	
	public MATLABIfFunction() {
		this.name = "ifFunction";
	}
	
	@Override
	public <T, S extends T> T accept(MATLABAstVisitor<T, S> visitor) {
		return visitor.visit(this);
	}

}
