package com.rockwellcollins.atc.agree.validation;

public class AgreeType {

	private String typeStr;
	
	public AgreeType(String str){
		typeStr = str;
	}
	
	public boolean matches(AgreeType otherType){
		return this.equals(otherType);
	}
	
	
	public boolean isPrimType(){
		return typeStr.equals("bool")
			|| typeStr.equals("int")
			|| typeStr.equals("real");
	}
	
	
	/********* begin overriden functions ********/
	@Override
	public boolean equals(Object o){
		
		if(o instanceof String)
			return this.equals(new AgreeType((String)o));
		
		if(!(o instanceof AgreeType))
			return false;
		
		AgreeType otherType = (AgreeType)o;
		
		return otherType.toString().equals(this.toString());
	}
	
	@Override public int hashCode(){
		return typeStr.hashCode();
	}
	
	@Override
	public String toString(){
		return typeStr;
	}
	
	
}
