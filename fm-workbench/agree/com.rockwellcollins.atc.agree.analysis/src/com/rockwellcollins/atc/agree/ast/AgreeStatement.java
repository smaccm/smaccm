package com.rockwellcollins.atc.agree.ast;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Expr;

public class AgreeStatement {
	public final String string;
	public final Expr expr;
	public final EObject reference;
	
	public AgreeStatement(String string, Expr expr, EObject reference){
		this.string = string;
		this.expr = expr;
		this.reference = reference;
	}

}
