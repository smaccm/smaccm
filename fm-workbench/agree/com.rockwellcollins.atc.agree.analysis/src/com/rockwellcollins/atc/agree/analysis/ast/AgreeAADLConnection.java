package com.rockwellcollins.atc.agree.analysis.ast;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeAstVisitor;

public class AgreeAADLConnection implements AgreeConnection{
	
	public enum ConnectionType {
        EVENT, DATA
    }
	public final String sourceNode;
	public final String destinationNode;
	public final AgreeVar sourVar;
	public final AgreeVar destVar;
	public final ConnectionType type;
	public final EObject reference;
	public final boolean latched;
	public final boolean delayed;

	public AgreeAADLConnection(String sourceNode, String destinationNode, AgreeVar sourceVarName,
			AgreeVar destinationVarName, ConnectionType type, boolean latched, boolean delayed, EObject reference) {
		this.sourceNode = sourceNode;
		this.destinationNode = destinationNode;
		this.sourVar = sourceVarName;
		this.destVar = destinationVarName;
		this.type = type;
        this.latched = latched;
        this.delayed = delayed;
        this.reference = reference;
    }

    @Override
    public <T> T accept(AgreeAstVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
