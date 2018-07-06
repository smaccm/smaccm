package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTVisitor;

import jkind.lustre.Expr;

public class AgreeNode implements AgreeASTElement {
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
	public final List<AgreeStatement> patternProps;
	public final List<AgreeEquation> localEquations;
	public final Expr clockConstraint;
	public final Expr initialConstraint;
	public final AgreeVar clockVar;
	public final EObject reference;
	public final TimingModel timing;
	public final ComponentInstance compInst;
	public final Map<String, AgreeVar> timeOfMap;
	public final Map<String, AgreeVar> timeRiseMap;
	public final Map<String, AgreeVar> timeFallMap;
	public final List<String> ivcElements;
	public final Boolean faultTreeFlag;

	public final Set<AgreeVar> eventTimes;

	public AgreeNode(String id, List<AgreeVar> inputs, List<AgreeVar> outputs, List<AgreeVar> locals,
			List<AgreeEquation> localEquations, List<AgreeConnection> connections, List<AgreeNode> subNodes,
			List<AgreeStatement> assertions, List<AgreeStatement> assumptions, List<AgreeStatement> guarantees,
			List<AgreeStatement> lemmas, List<AgreeStatement> patternProps, Expr clockConstraint,
			Expr initialConstraint, AgreeVar clockVar, EObject reference, TimingModel timing, Set<AgreeVar> eventTimes,
			Map<String, AgreeVar> timeOfMap, Map<String, AgreeVar> timeRiseMap, Map<String, AgreeVar> timeFallMap,
			ComponentInstance compinst, List<String> ivcElements, Boolean faultTreeFlag) {
		this.id = id;
		this.inputs = jkind.util.Util.safeList(inputs);
		this.outputs = jkind.util.Util.safeList(outputs);
		this.locals = jkind.util.Util.safeList(locals);
		this.patternProps = jkind.util.Util.safeList(patternProps);
		this.localEquations = jkind.util.Util.safeList(localEquations);
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
		compInst = compinst;
		if (eventTimes == null) {
			this.eventTimes = Collections.emptySet();
		} else {
			this.eventTimes = Collections.unmodifiableSet(eventTimes);
		}
		this.timeOfMap = timeOfMap;
		this.timeRiseMap = timeRiseMap;
		this.timeFallMap = timeFallMap;
		this.ivcElements = ivcElements;
		this.faultTreeFlag = faultTreeFlag;
	}

	@Override
	public <T> T accept(AgreeASTVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return id;
	}

	public List<String> getivcElements() {
		return ivcElements;
	}

	public Boolean getFaultTreeFlag() {
		return faultTreeFlag;
	}

}
