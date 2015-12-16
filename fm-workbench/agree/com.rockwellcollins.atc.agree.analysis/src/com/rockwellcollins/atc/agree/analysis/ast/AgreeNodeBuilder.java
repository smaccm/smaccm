package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;

import jkind.lustre.Expr;

public class AgreeNodeBuilder {
    
    private String id;
    private List<AgreeVar> inputs = new ArrayList<>();
    private List<AgreeVar> outputs = new ArrayList<>();
    private List<AgreeVar> locals = new ArrayList<>();
    private List<AgreeConnection> connections = new ArrayList<>();
    private List<AgreeNode> subNodes = new ArrayList<>();
    private List<AgreeStatement> assertions = new ArrayList<>();
    private List<AgreeStatement> assumptions = new ArrayList<>();
    private List<AgreeStatement> guarantees = new ArrayList<>();
    private List<AgreeStatement> lemmas = new ArrayList<>();
    private Expr clockConstraint;
    private Expr initialConstraint;
    private AgreeVar clockVar;
    private EObject reference;
    private TimingModel timing;
    private ComponentInstance compInst;
    
    public AgreeNodeBuilder(String id){
        this.id = id;
    }
    
    public AgreeNodeBuilder(AgreeNode node){
        this.id = node.id;
        this.inputs = new ArrayList<>(node.inputs);
        this.outputs = new ArrayList<>(node.outputs);
        this.locals = new ArrayList<>(node.locals);
        this.connections = new ArrayList<>(node.connections);
        this.subNodes = new ArrayList<>(node.subNodes);
        this.assertions = new ArrayList<>(node.assertions);
        this.assumptions = new ArrayList<>(node.assumptions);
        this.guarantees = new ArrayList<>(node.guarantees);
        this.lemmas = new ArrayList<>(node.lemmas);
        this.clockConstraint = node.clockConstraint;
        this.initialConstraint = node.initialConstraint;
        this.clockVar = node.clockVar;
        this.reference = node.reference;
        this.timing = node.timing;
        this.compInst = node.compInst;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void addInput(AgreeVar var){
        this.inputs.add(var);
    }

    public void clearInputs() {
        this.inputs.clear();
    }
    
    public void addOutput(AgreeVar var){
        this.outputs.add(var);
    }
    
    public void clearOutputs(){
        this.outputs.clear();
    }
    
    public void addLocal(AgreeVar var){
        this.locals.add(var);
    }
    
    public void clearLocals(){
        this.locals.clear();
    }
    
    public void addConnection(AgreeConnection conn){
        this.connections.add(conn);
    }
    
    public void clearConnections(){
        this.connections.clear();
    }
    
    public void addSubNode(AgreeNode node){
        this.subNodes.add(node);
    }
    
    public void clearSubNodes(){
        this.subNodes.clear();
    }
    
    public void addAssertion(AgreeStatement statement){
        this.assertions.add(statement);
    }
    
    public void clearAssertions(){
        this.assertions.clear();
    }
    
    public void addAssumption(AgreeStatement statement){
        this.assumptions.add(statement);
    }
    
    public void clearAssumptions(){
        this.assumptions.clear();
    }
    
    public void addGuarantee(AgreeStatement statement){
        this.guarantees.add(statement);
    }
    
    public void clearGuarantees(){
        this.guarantees.clear();
    }
    
    public void addLemma(AgreeStatement statement){
        this.lemmas.add(statement);
    }
    
    public void clearLemmas(){
        this.lemmas.clear();
    }
    
    public void setClockConstraint(Expr clock){
        this.clockConstraint = clock;
    }
    
    public void setInitialConstraint(Expr init){
        this.initialConstraint = init;
    }
    
    public void setClockVar(AgreeVar var){
        this.clockVar = var;
    }
    
    public void setReference(EObject ref){
        this.reference = ref;
    }
    
    public void setTiming(TimingModel time){
        this.timing = time;
    }
    
    public void setCompInst(ComponentInstance compInst){
        this.compInst = compInst;
    }
    
    public AgreeNode build() {
        return new AgreeNode(id, inputs, outputs, locals, connections, subNodes, assertions, assumptions,
                guarantees, lemmas, clockConstraint, initialConstraint, clockVar, reference, timing,
                compInst);
    }
    
}
