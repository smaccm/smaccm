package com.rockwellcollins.atc.agree.analysis.ast;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTVisitor;

import jkind.lustre.Type;
import jkind.lustre.VarDecl;

public class AgreeVar extends VarDecl implements AgreeASTElement {

	public final EObject reference;
	public final ComponentInstance compInst;
	public final FeatureInstance featInst;

	public AgreeVar(String name, Type type, EObject reference, ComponentInstance compInst, FeatureInstance featInst) {
		super(name, type);
		this.reference = reference;
		this.compInst = compInst;
		this.featInst = featInst;
	}

	public AgreeVar(String name, Type type, EObject reference, ComponentInstance compInst) {
		this(name, type, reference, compInst, null);
	}

	public AgreeVar(String name, Type type, EObject reference) {
		this(name, type, reference, null, null);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			return id.equals(obj);
		}

		if (!(obj instanceof AgreeVar)) {
			return false;
		}
		AgreeVar otherVar = (AgreeVar) obj;
		return otherVar.compInst == compInst && otherVar.id.equals(id);
	}

	@Override
	public <T> T accept(AgreeASTVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		if (compInst != null) {
			return compInst.getInstanceObjectPath() + "." + id;
		}
		return id;
	}

}
