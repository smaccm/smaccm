package com.rockwellcollins.atc.agree.codegen.ast;

import java.util.SortedMap;

import jkind.Assert;

public class MATLABPort {
	public final String name;
	public final String direction;
	public final MATLABType type;
	public final SortedMap<String, MATLABType> fields;

	public MATLABPort(String name, String direction, MATLABType type, SortedMap<String, MATLABType> fields) {
		Assert.isNotNull(name);
		Assert.isNotNull(direction);
		Assert.isNotNull(type);
		this.name = name;
		this.direction = direction;
		this.type = type;
		this.fields = fields;
	}
}
