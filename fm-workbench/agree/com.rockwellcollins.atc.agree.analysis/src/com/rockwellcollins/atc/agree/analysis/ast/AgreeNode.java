package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;

import jkind.lustre.Expr;
import jkind.lustre.Node;
import jkind.lustre.Type;

public class AgreeNode {
    public enum TimingModel {
        SYNC, ASYNC, LATCHED
    };

    public final String id;
    public final List<AgreeVar> inputs;
    public final List<AgreeVar> outputs;
    public final List<AgreeVar> locals;
    public final List<AgreeConnection> connections;
    public final List<AgreeNode> subNodes;
    public final List<AgreeStatement> assertions;
    public final List<AgreeStatement> assumptions;
    public final List<AgreeStatement> guarantees;
    public final List<AgreeStatement> lemmas;
    public final Expr clockConstraint;
    public final Expr initialConstraint;
    public final AgreeVar clockVar;
    public final EObject reference;
    public final TimingModel timing;
    public final ComponentInstance compInst;

    public AgreeNode(String id, List<AgreeVar> inputs, List<AgreeVar> outputs, List<AgreeVar> locals,
            List<AgreeConnection> connections, List<AgreeNode> subNodes, List<AgreeStatement> assertions,
            List<AgreeStatement> assumptions, List<AgreeStatement> guarantees, List<AgreeStatement> lemmas,
            Expr clockConstraint, Expr initialConstraint, AgreeVar clockVar, EObject reference,
            TimingModel timing, ComponentInstance compinst) {
        this.id = id;
        this.inputs = jkind.util.Util.safeList(inputs);
        this.outputs = jkind.util.Util.safeList(outputs);
        this.locals = jkind.util.Util.safeList(locals);
        this.connections = jkind.util.Util.safeList(connections);
        this.subNodes = jkind.util.Util.safeList(subNodes);
        this.assertions = jkind.util.Util.safeList(assertions);
        this.assumptions = jkind.util.Util.safeList(assumptions);
        this.guarantees = jkind.util.Util.safeList(guarantees);
        this.lemmas = jkind.util.Util.safeList(lemmas);
        this.clockConstraint = clockConstraint;
        this.initialConstraint = initialConstraint;
        this.clockVar = clockVar;
        this.reference = reference;
        this.timing = timing;
        this.compInst = compinst;
    }

}
