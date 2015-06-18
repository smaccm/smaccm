package com.rockwellcollins.atc.agree.ast;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Type;

public class AgreeVar {
	public final Type type;
	public final String name;
	public final EObject reference;
	
	public AgreeVar(String name, Type type, EObject reference){
		this.name = name;
		this.type = type;
		this.reference = reference;
	}
}
