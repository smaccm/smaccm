package com.rockwellcollins.atc.agree.analysis;

import java.util.List;

import jkind.lustre.CondactExpr;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Node;
import jkind.lustre.VarDecl;

public class AgreeNode {
    
    public List<VarDecl> inputs;
    public List<VarDecl> outputs;
    public List<Expr> assertions;
    public List<Expr> assumptions;
    public List<Expr> guarantees;
    public List<Node> nodes;
    public Node mainNode;
    public VarDecl clockVar;
    
    public AgreeNode(List<VarDecl> inputs, List<VarDecl> outputs,
            List<Expr> assumptions, List<Expr> assertions,
            List<Expr> guarantees, List<Node> nodes, Node mainNode, VarDecl clockVar){
        
        this.inputs = inputs;
        this.outputs = outputs;
        this.assertions = assertions;
        this.assumptions = assumptions;
        this.guarantees = guarantees;
        this.nodes = nodes;
        this.mainNode = mainNode;
        this.clockVar = clockVar;
    }
    
}
