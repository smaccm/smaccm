package com.rockwellcollins.atc.agree.codegen.ast;

public abstract class MATLABFunction extends MATLABAst {
	public String name;
	public boolean functionCalled = false;
	
	public void updateName(String newName){
		name = newName;
	}
	
}