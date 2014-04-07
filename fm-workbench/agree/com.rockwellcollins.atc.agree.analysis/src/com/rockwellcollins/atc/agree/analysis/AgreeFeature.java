package com.rockwellcollins.atc.agree.analysis;

public class AgreeFeature {
	
	public enum ConnType {QUEUE,IMMEDIATE,DELAYED}
	public enum Direction {IN, OUT};
	
	public String lustreString;
	public String aadlString;
	public String varType;
	public ConnType connType;
	public Direction direction;
	public long queueSize;
	
	@Override
	public String toString(){
		return lustreString;
	}

}
