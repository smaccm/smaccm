package com.rockwellcollins.atc.agree.analysis.ast;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTVisitor;

public class AgreeAADLConnection implements AgreeConnection {

	public enum ConnectionType {
		EVENT, DATA
	}

	public final AgreeNode sourceNode;
	public final AgreeNode destinationNode;
	public final AgreeVar sourceVarName;
	public final AgreeVar destinationVarName;
	public final ConnectionType type;
	public final EObject reference;
	public final boolean latched;
	public final boolean delayed;

	public AgreeAADLConnection(AgreeNode sourceNode, AgreeNode destinationNode, AgreeVar sourceVarName,
			AgreeVar destinationVarName, ConnectionType type, boolean latched, boolean delayed, EObject reference) {

		this.sourceNode = sourceNode;
		this.destinationNode = destinationNode;
		this.sourceVarName = sourceVarName;
		this.destinationVarName = destinationVarName;
		this.type = type;
		this.latched = latched;
		this.delayed = delayed;
		this.reference = reference;
	}

	@Override
	public <T> T accept(AgreeASTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}