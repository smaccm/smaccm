package com.rockwellcollins.atc.agree.ast;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Type;
import jkind.lustre.VarDecl;

public class AgreeVar extends VarDecl{

	public final EObject reference;
	public AgreeVar(String name, Type type, EObject reference){
		super(name, type);
		this.reference = reference;
	}
	
	@Override
	public int hashCode(){
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof AgreeVar)){
			return false;
		}
		return ((AgreeVar)obj).id.equals(id);
	}
}
