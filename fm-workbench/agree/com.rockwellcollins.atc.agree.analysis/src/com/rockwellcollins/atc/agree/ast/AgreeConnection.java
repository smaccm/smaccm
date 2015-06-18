package com.rockwellcollins.atc.agree.ast;

import org.eclipse.emf.ecore.EObject;

public class AgreeConnection {
	public enum ConnectionType{ EVENT, DATA }
	public final AgreeNode sourceNode;
	public final AgreeNode destinationNode;
	public final AgreeVar sourceVar;
	public final AgreeVar destinationVar;
	public final ConnectionType type;
	public final EObject reference;
	
	public AgreeConnection(AgreeNode sourceNode, AgreeNode destinationNode,
			AgreeVar sourceVar, AgreeVar destinationVar, ConnectionType type,
			EObject reference){
		this.sourceNode = sourceNode;
		this.destinationNode = destinationNode;
		this.sourceVar = sourceVar;
		this.destinationVar = destinationVar;
		this.type = type;
		this.reference = reference;
	}
	
	

}
