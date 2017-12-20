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

import jkind.lustre.Expr;

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
	private List<AgreeStatement> patternProps = new ArrayList<>();
	private Expr clockConstraint;
	private Expr initialConstraint;
	private AgreeVar clockVar;
	private EObject reference;
	private TimingModel timing;
	private ComponentInstance compInst;
	private Set<AgreeVar> eventTimes = new HashSet<>();
	private Map<String, AgreeVar> timeOfMap = new HashMap<>();
	private Map<String, AgreeVar> timeRiseMap = new HashMap<>();
	private Map<String, AgreeVar> timeFallMap = new HashMap<>();

	public AgreeNodeBuilder(String id) {
		this.id = id;
	}

	public AgreeNodeBuilder(AgreeNode node) {
		id = node.id;
		inputs = new ArrayList<>(node.inputs);
		outputs = new ArrayList<>(node.outputs);
		locals = new ArrayList<>(node.locals);
		equations = new ArrayList<>(node.localEquations);
		connections = new ArrayList<>(node.connections);
		subNodes = new ArrayList<>(node.subNodes);
		assertions = new ArrayList<>(node.assertions);
		assumptions = new ArrayList<>(node.assumptions);
		guarantees = new ArrayList<>(node.guarantees);
		lemmas = new ArrayList<>(node.lemmas);
		patternProps = new ArrayList<>(node.patternProps);
		eventTimes = new HashSet<>(node.eventTimes);
		timeOfMap = new HashMap<>(node.timeOfMap);
		timeRiseMap = new HashMap<>(node.timeRiseMap);
		timeFallMap = new HashMap<>(node.timeFallMap);
		clockConstraint = node.clockConstraint;
		initialConstraint = node.initialConstraint;
		clockVar = node.clockVar;
		reference = node.reference;
		timing = node.timing;
		compInst = node.compInst;

	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void addPatternProp(AgreeStatement statement) {
		patternProps.add(statement);
	}

	public void addPatternProp(List<AgreeStatement> statements) {
		patternProps.addAll(statements);
	}

	public void clearPatternProps() {
		patternProps.clear();
	}

	public void addEventTime(AgreeVar event) {
		eventTimes.add(event);
	}

	public void addEventTime(Set<AgreeVar> events) {
		eventTimes.addAll(events);
	}

	public void clearEventTimes() {
		eventTimes.clear();
	}

	public void addInput(AgreeVar var) {
		inputs.add(var);
	}

	public void addInput(List<AgreeVar> vars) {
		inputs.addAll(vars);
	}

	public void clearInputs() {
		inputs.clear();
	}

	public void addOutput(AgreeVar var) {
		outputs.add(var);
	}

	public void addOutput(List<AgreeVar> vars) {
		outputs.addAll(vars);
	}

	public void clearOutputs() {
		outputs.clear();
	}

	public void addLocal(AgreeVar var) {
		locals.add(var);
	}

	public void addLocal(List<AgreeVar> var) {
		locals.addAll(var);
	}

	public void clearLocals() {
		locals.clear();
	}

	public void addLocalEquation(AgreeEquation eq) {
		equations.add(eq);
	}

	public void addLocalEquation(List<AgreeEquation> eq) {
		equations.addAll(eq);
	}

	public void clearLocalEquations() {
		equations.clear();
	}

	public void addConnection(AgreeConnection conn) {
		connections.add(conn);
	}

	public void addConnection(List<AgreeConnection> conn) {
		connections.addAll(conn);
	}

	public void clearConnections() {
		connections.clear();
	}

	public void addSubNode(AgreeNode node) {
		subNodes.add(node);
	}

	public void addSubNode(List<AgreeNode> node) {
		subNodes.addAll(node);
	}

	public void clearSubNodes() {
		subNodes.clear();
	}

	public void addAssertion(AgreeStatement statement) {
		assertions.add(statement);
	}

	public void addAssertion(List<AgreeStatement> statement) {
		assertions.addAll(statement);
	}

	public void clearAssertions() {
		assertions.clear();
	}

	public void addAssumption(AgreeStatement statement) {
		assumptions.add(statement);
	}

	public void addAssumption(List<AgreeStatement> statement) {
		assumptions.addAll(statement);
	}

	public void clearAssumptions() {
		assumptions.clear();
	}

	public void addGuarantee(AgreeStatement statement) {
		guarantees.add(statement);
	}

	public void addGuarantee(List<AgreeStatement> statement) {
		guarantees.addAll(statement);
	}

	public void clearGuarantees() {
		guarantees.clear();
	}

	public void addLemma(AgreeStatement statement) {
		lemmas.add(statement);
	}

	public void addLemma(List<AgreeStatement> statement) {
		lemmas.addAll(statement);
	}

	public void addTimeOf(String name, AgreeVar var) {
		timeOfMap.put(name, var);
	}

	public void addTimeOf(Map<String, AgreeVar> map) {
		timeOfMap.putAll(map);
	}

	public void addTimeRise(String name, AgreeVar var) {
		timeRiseMap.put(name, var);
	}

	public void addTimeRise(Map<String, AgreeVar> map) {
		timeRiseMap.putAll(map);
	}

	public void addTimeFall(String name, AgreeVar var) {
		timeFallMap.put(name, var);
	}

	public void addTimeFall(Map<String, AgreeVar> map) {
		timeFallMap.putAll(map);
	}

	public void clearTimeOfs() {
		timeOfMap.clear();
	}

	public void clearTimeRises() {
		timeRiseMap.clear();
	}

	public void clearTimeFalls() {
		timeFallMap.clear();
	}

	public void clearLemmas() {
		lemmas.clear();
	}

	public void setClockConstraint(Expr clock) {
		clockConstraint = clock;
	}

	public void setInitialConstraint(Expr init) {
		initialConstraint = init;
	}

	public void setClockVar(AgreeVar var) {
		clockVar = var;
	}

	public void setReference(EObject ref) {
		reference = ref;
	}

	public void setTiming(TimingModel time) {
		timing = time;
	}

	public void setCompInst(ComponentInstance compInst) {
		this.compInst = compInst;
	}

	public AgreeNode build() {
		return new AgreeNode(id, inputs, outputs, locals, equations, connections, subNodes, assertions, assumptions,
				guarantees, lemmas, patternProps, clockConstraint, initialConstraint, clockVar, reference, timing,
				eventTimes, timeOfMap, timeRiseMap, timeFallMap, compInst);
	}

}
