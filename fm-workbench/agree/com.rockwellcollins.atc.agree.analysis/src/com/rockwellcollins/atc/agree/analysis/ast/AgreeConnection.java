package com.rockwellcollins.atc.agree.analysis.ast;

import org.eclipse.emf.ecore.EObject;

public class AgreeConnection {
    public enum ConnectionType {
        EVENT, DATA
    }

    public final AgreeNode sourceNode;
    public final AgreeNode destinationNode;
    public final String sourceVarName;
    public final String destinationVarName;
    public final ConnectionType type;
    public final EObject reference;
    public final boolean latched;
    public final boolean delayed;

    public AgreeConnection(AgreeNode sourceNode, AgreeNode destinationNode, String sourceVarName,
            String destinationVarName, ConnectionType type, boolean latched, boolean delayed,
            EObject reference) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.sourceVarName = sourceVarName;
        this.destinationVarName = destinationVarName;
        this.type = type;
        this.latched = latched;
        this.delayed = delayed;
        this.reference = reference;
    }

}
