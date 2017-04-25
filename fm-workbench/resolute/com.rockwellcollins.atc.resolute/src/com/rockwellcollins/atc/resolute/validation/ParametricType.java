package com.rockwellcollins.atc.resolute.validation;

import com.rockwellcollins.atc.resolute.resolute.Type;

public class ParametricType extends ResoluteType{

	private final ResoluteType paramType;
	private final BaseType baseType;

	public ParametricType(BaseType baseType, ResoluteType paramType){
		this.paramType = paramType;
		this.baseType = baseType;
	}
	
	@Override
	public boolean subtypeOf(ResoluteType otherType) {
		if(otherType instanceof ParametricType){
			ParametricType otherParamType = (ParametricType)otherType;
			return otherParamType.getBaseType().equals(this.baseType) && this.paramType.subtypeOf(otherParamType.getParamType());
		}else if(otherType instanceof BaseType){
			return otherType.equals(this.baseType);
		}
		
		return false;
	}

	@Override
	public ResoluteType join(ResoluteType otherType) {
		return null;
	}

	@Override
	public boolean similar(Type otherType) {
		return false;
	}
	
	public ResoluteType getParamType(){
		return this.paramType;
	}

	public BaseType getBaseType(){
		return this.baseType;
	}
	
	@Override
	public int hashCode(){
		return baseType.hashCode() + paramType.hashCode();
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof ParametricType)){
			return false;
		}
		ParametricType otherType = (ParametricType)other;
		return otherType.getBaseType().equals(baseType) && otherType.getParamType().equals(paramType);
	}
	
	@Override
	public String toString(){
		return baseType.toString() + "<" + paramType.toString() + ">";
	}
}
