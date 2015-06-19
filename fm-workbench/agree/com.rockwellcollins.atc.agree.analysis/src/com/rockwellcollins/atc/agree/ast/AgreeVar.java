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
}
