package com.rockwellcollins.atc.agree.codegen.ast;

import jkind.Assert;

public class MATLABPort {
	public final String name;
	public final String direction;
	public final MATLABType type; 
	
	public MATLABPort(String name, String direction, MATLABType type) {
		Assert.isNotNull(name);
		Assert.isNotNull(direction);
		Assert.isNotNull(type);
		this.name = name;
		this.direction = direction;
		this.type = type;		
	}
}
