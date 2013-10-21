package com.rockwellcollins.atc.agree.analysis;

import java.util.List;

import jkind.lustre.CondactExpr;
import jkind.lustre.Node;
import jkind.lustre.VarDecl;

public class AgreeNode {
    
    public List<VarDecl> aadlInputs;
    public List<VarDecl> aadlOutputs;
    public List<VarDecl> assertions;
    public List<VarDecl> assumptions;
    public List<VarDecl> guarantees;
    public List<Node> nodes;
    public Node mainNode;
    public VarDecl clockVar;
    
    public AgreeNode(List<VarDecl> aadlInputs, List<VarDecl> aadlOutputs,
            List<VarDecl> assumptions, List<VarDecl> assertions,
            List<VarDecl> guarantees, List<Node> nodes, Node mainNode, VarDecl clockVar){
        
        this.aadlInputs = aadlInputs;
        this.aadlOutputs = aadlOutputs;
        this.assertions = assertions;
        this.assumptions = assumptions;
        this.guarantees = guarantees;
        this.nodes = nodes;
        this.mainNode = mainNode;
        this.clockVar = clockVar;
    }
    
}
