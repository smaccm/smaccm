package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.Type;

import org.osate.aadl2.Feature;

public class AgreeFeature {
	
	public enum ConnType {EVENT,IMMEDIATE,DELAYED}
	public enum Direction {IN, OUT};
	
	public Feature feature;
	public String lustreString;
	public Type varType;
	public ConnType connType;
	public Direction direction;
	public long queueSize;
	
	@Override
	public String toString(){
		return lustreString;
	}

}
