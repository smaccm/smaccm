package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;

import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;

public class AgreeNodeBuilder {
    
    private String id;
    private List<AgreeVar> inputs = new ArrayList<>();
    private List<AgreeVar> outputs = new ArrayList<>();
    private List<AgreeVar> locals = new ArrayList<>();
    private List<AgreeEquation> equations = new ArrayList<>();
    private List<AgreeConnection> connections = new ArrayList<>();
    private List<AgreeNode> subNodes = new ArrayList<>();
    private List<AgreeStatement> assertions = new ArrayList<>();
    private List<AgreeStatement> assumptions = new ArrayList<>();
    private List<AgreeStatement> guarantees = new ArrayList<>();
    private List<AgreeStatement> lemmas = new ArrayList<>();
    private List<AgreeEquation> localEquations = new ArrayList<>();
    private List<AgreeStatement> patternProps = new ArrayList<>();
    private Expr clockConstraint;
    private Expr initialConstraint;
    private AgreeVar clockVar;
    private EObject reference;
    private TimingModel timing;
    private ComponentInstance compInst;
    private Set<AgreeVar> eventTimes = new HashSet<>();
    private Map<String, AgreeVar> timeOfMap = new HashMap<>();
    
    public AgreeNodeBuilder(String id){
        this.id = id;
    }
    
    public AgreeNodeBuilder(AgreeNode node){
        this.id = node.id;
        this.inputs = new ArrayList<>(node.inputs);
        this.outputs = new ArrayList<>(node.outputs);
        this.locals = new ArrayList<>(node.locals);
        this.equations = new ArrayList<>(node.localEquations);
        this.connections = new ArrayList<>(node.connections);
        this.subNodes = new ArrayList<>(node.subNodes);
        this.assertions = new ArrayList<>(node.assertions);
        this.assumptions = new ArrayList<>(node.assumptions);
        this.guarantees = new ArrayList<>(node.guarantees);
        this.lemmas = new ArrayList<>(node.lemmas);
        this.localEquations = new ArrayList<>(node.localEquations);
        this.patternProps = new ArrayList<>(node.patternProps);
        this.eventTimes = new HashSet<>(node.eventTimes);
        this.timeOfMap = new HashMap<>(node.timeOfMap);
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
    
    public String getId(){
        return this.id;
    }
        
    public void addPatternProp(AgreeStatement statement){
        this.patternProps.add(statement);
    }
    
    public void addPatternProp(List<AgreeStatement> statements){
        this.patternProps.addAll(statements);
    }
    
    public void clearPatternProps(){
        this.patternProps.clear();
    }
    
    public void addEventTime(AgreeVar event){
        this.eventTimes.add(event);
    }
    
    public void addEventTime(Set<AgreeVar> events){
        this.eventTimes.addAll(events);
    }
    
    public void clearEventTimes(){
        this.eventTimes.clear();
    }
    
    public void addInput(AgreeVar var){
        this.inputs.add(var);
    }
    
    public void addInput(List<AgreeVar> vars){
        this.inputs.addAll(vars);
    }

    public void clearInputs() {
        this.inputs.clear();
    }
    
    public void addOutput(AgreeVar var){
        this.outputs.add(var);
    }
    
    public void addOutput(List<AgreeVar> vars){
        this.outputs.addAll(vars);
    }
    
    public void clearOutputs(){
        this.outputs.clear();
    }
    
    public void addLocal(AgreeVar var){
        this.locals.add(var);
    }
    
    public void addLocal(List<AgreeVar> var){
        this.locals.addAll(var);
    }
    
    public void clearLocals(){
        this.locals.clear();
    }
    
    public void addLocalEquation(AgreeEquation eq){
        this.equations.add(eq);
    }
    
    public void addLocalEquation(List<AgreeEquation> eq){
        this.equations.addAll(eq);
    }
    
    public void clearLocalEquations(){
        this.equations.clear();
    }
    
    public void addConnection(AgreeConnection conn){
        this.connections.add(conn);
    }
    
    public void addConnection(List<AgreeConnection> conn){
        this.connections.addAll(conn);
    }
    
    public void clearConnections(){
        this.connections.clear();
    }
    
    public void addSubNode(AgreeNode node){
        this.subNodes.add(node);
    }
    
    public void addSubNode(List<AgreeNode> node){
        this.subNodes.addAll(node);
    }
    
    public void clearSubNodes(){
        this.subNodes.clear();
    }
    
    public void addAssertion(AgreeStatement statement){
        this.assertions.add(statement);
    }
    
    public void addAssertion(List<AgreeStatement> statement){
        this.assertions.addAll(statement);
    }
    
    public void clearAssertions(){
        this.assertions.clear();
    }
    
    public void addAssumption(AgreeStatement statement){
        this.assumptions.add(statement);
    }
    
    public void addAssumption(List<AgreeStatement> statement){
        this.assumptions.addAll(statement);
    }
    
    public void clearAssumptions(){
        this.assumptions.clear();
    }
    
    public void addGuarantee(AgreeStatement statement){
        this.guarantees.add(statement);
    }
    
    public void addGuarantee(List<AgreeStatement> statement){
        this.guarantees.addAll(statement);
    }
    
    public void clearGuarantees(){
        this.guarantees.clear();
    }
    
    public void addLemma(AgreeStatement statement){
        this.lemmas.add(statement);
    }
    
    public void addLemma(List<AgreeStatement> statement){
        this.lemmas.addAll(statement);
    }
    
    public void addTimeOf(String name, AgreeVar var){
    	timeOfMap.put(name, var);
    }
    
    public void addTimeOf(Map<String, AgreeVar> map){
    	timeOfMap.putAll(map);
    }
    
    public void clearTimeOfs(){
    	timeOfMap.clear();
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

    public void setCompInst(ComponentInstance compInst) {
        this.compInst = compInst;
    }
    
    public AgreeNode build() {
        return new AgreeNode(id, inputs, outputs, locals, equations, connections, subNodes, assertions,
                assumptions, guarantees, lemmas, patternProps, clockConstraint, initialConstraint, clockVar, reference,
                timing, eventTimes, timeOfMap, compInst);
    }

}
