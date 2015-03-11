package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.Expr;
import jkind.lustre.Type;

import org.osate.aadl2.Feature;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

public class AgreeFeature {
	
	public enum ConnType {EVENT,IMMEDIATE,DELAYED}
	public enum Direction {IN, OUT};
	
	public Feature feature;
	public FeatureInstance featureInstance;
	public String lustreString;
	public Type varType;
	public ConnType connType;
	public Direction direction;
	public long queueSize;
	public ComponentInstance compInst;
	Expr initState;
	
	public AgreeFeature(){};
	
	@Override
	public String toString(){
		return lustreString;
	}

}
