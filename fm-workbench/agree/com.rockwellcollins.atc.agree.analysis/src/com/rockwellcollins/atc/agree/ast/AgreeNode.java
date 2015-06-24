package com.rockwellcollins.atc.agree.ast;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Expr;
import jkind.lustre.Node;
import jkind.lustre.Type;

public class AgreeNode {
	public enum TimingModel{SYNC, ASYNC, LATCHED};
	
	public final String id;
	public final List<AgreeVar> inputs;
	public final List<AgreeVar> outputs;
	public final List<AgreeConnection> connections;
	public final List<AgreeNode> subNodes;
	public final List<Node> lustreNodes;
	public final List<AgreeStatement> assertions;
	public final List<AgreeStatement> assumptions;
	public final List<AgreeStatement> guarantees;
	public final Expr clockConstraint;
	public final Expr initialConstraint;
	public final AgreeVar clockVar;
	public final EObject reference;
	public final TimingModel timing;
	
	public AgreeNode(String id, List<AgreeVar> inputs, List<AgreeVar> outputs, 
			List<AgreeConnection> connections, List<AgreeNode> subNodes, List<Node> lustreNodes,
			List<AgreeStatement> assertions, List<AgreeStatement> assumptions, 
			List<AgreeStatement> guarantees, Expr clockConstraint, 
			Expr initialConstraint, AgreeVar clockVar, EObject reference, TimingModel timing){
		this.id = id;
		this.inputs = inputs;
		this.outputs = outputs;
		this.connections = connections;
		this.subNodes = subNodes;
		this.lustreNodes = lustreNodes;
		this.assertions = assertions;
		this.assumptions = assumptions;
		this.guarantees = guarantees;
		this.clockConstraint = clockConstraint;
		this.initialConstraint = initialConstraint;
		this.clockVar = clockVar;
		this.reference = reference;
		this.timing = timing;
	}

}
