/**
 * Copyright (c) 2016, Rockwell Collins.
 *
 * Developed with the sponsorship of Defense Advanced Research Projects Agency
 * (DARPA).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this data, including any software or models in source or binary form, as
 * well as any drawings, specifications, and documentation (collectively
 * "the Data"), to deal in the Data without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Data, and to permit persons to whom the
 * Data is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Data.
 *
 * THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE
 * USE OR OTHER DEALINGS IN THE DATA.
 */

/*
 * MWW (9/17/2017)
 * NOTE: this pass only works correctly for *complete* traversals!
 * If you short-circuit a node traversal, the pass behaves incorrectly.
 *
 */

package com.rockwellcollins.atc.agree.analysis.ast.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeAADLConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeAADLConnection.ConnectionType;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTElement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeEquation;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNodeBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeOverriddenConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Node;
import jkind.lustre.Type;

public class AgreeASTMapVisitor extends jkind.lustre.visitors.AstMapVisitor
implements AgreeASTVisitor<AgreeASTElement> {

	protected jkind.lustre.visitors.TypeMapVisitor lustreTypeMapVisitor;

//	protected Map<AgreeNode, AgreeNode> visitedNodes;
	protected Map<ComponentInstance, AgreeNode> visitedNodes;

	public AgreeASTMapVisitor(jkind.lustre.visitors.TypeMapVisitor lustreTypeMapVisitor) {
		visitedNodes = new HashMap<>();
		this.lustreTypeMapVisitor = lustreTypeMapVisitor;
	}

	@Override
	public AgreeProgram visit(AgreeProgram e) {
		List<AgreeNode> agreeNodes = new ArrayList<>();
		for (AgreeNode node : e.agreeNodes) {
			AgreeNode visitedNode = visitedNodes.get(node.compInst);
			if (visitedNode == null) {
				visitedNode = this.visit(node);
			}
			agreeNodes.add(visitedNode);
		}

		List<Node> globalLustreNodes = new ArrayList<>();
		for (Node node : e.globalLustreNodes) {
			globalLustreNodes.add(this.visit(node));
		}

		List<jkind.lustre.Type> globalTypes = new ArrayList<>();
		for (Type ty : e.globalTypes) {
			globalTypes.add(ty.accept(lustreTypeMapVisitor));
		}

		AgreeNode topNode = this.visit(e.topNode);

		return new AgreeProgram(agreeNodes, globalLustreNodes, globalTypes, topNode);
	};

	@Override
	public AgreeConnection visit(AgreeConnection e) {
		if (e instanceof AgreeAADLConnection) {
			AgreeAADLConnection aadlConn = (AgreeAADLConnection) e;
			AgreeNode sourceNode = null;
			if (aadlConn.sourceNode != null) {
				sourceNode = visitedNodes.get(aadlConn.sourceNode.compInst);
			}
			AgreeNode destinationNode = null;
			if (aadlConn.destinationNode != null) {
				destinationNode = visitedNodes.get(aadlConn.destinationNode.compInst);
			}

			AgreeVar sourVar = visit(aadlConn.sourceVarName);
			AgreeVar destVar = visit(aadlConn.destinationVarName);
			ConnectionType type = aadlConn.type;
			boolean latched = aadlConn.latched;
			boolean delayed = aadlConn.delayed;
			EObject reference = aadlConn.reference;

			return new AgreeAADLConnection(sourceNode, destinationNode, sourVar, destVar, type, latched, delayed,
					reference);
		} else if (e instanceof AgreeOverriddenConnection) {
			AgreeOverriddenConnection overriddenCon = (AgreeOverriddenConnection) e;
			AgreeStatement statement = visit(overriddenCon.statement);
			return new AgreeOverriddenConnection(statement, overriddenCon.aadlConn);
		}
		throw new AgreeException("Unhandled Agree connection type " + e.getClass());
	}

	@Override
	public AgreeNode visit(AgreeNode e) {
		String id = e.id;

		List<AgreeVar> inputs = new ArrayList<>();
		for (AgreeVar input : e.inputs) {
			inputs.add(this.visit(input));
		}

		List<AgreeVar> outputs = new ArrayList<>();
		for (AgreeVar output : e.outputs) {
			outputs.add(this.visit(output));
		}

		List<AgreeVar> locals = new ArrayList<>();
		for (AgreeVar local : e.locals) {
			locals.add(this.visit(local));
		}

		// Note that nodes and connections contain cross-references to each
		// other. But, valid model structure requires that connections
		// refer only to features on the this node and the sub-nodes. Thus,
		// we may visit the sub-nodes first, and then use the result of that
		// in visiting the connections.
		//
		List<AgreeNode> subNodes = new ArrayList<>();
		for (AgreeNode subnode : e.subNodes) {
			subNodes.add(this.visit(subnode));
		}

		List<AgreeConnection> connections = new ArrayList<>();
		for (AgreeConnection conn : e.connections) {
			connections.add(this.visit(conn));
		}

		List<AgreeStatement> assertions = new ArrayList<>();
		for (AgreeStatement stmt : e.assertions) {
			assertions.add(this.visit(stmt));
		}

		List<AgreeStatement> assumptions = new ArrayList<>();
		for (AgreeStatement stmt : e.assumptions) {
			assumptions.add(this.visit(stmt));
		}

		List<AgreeStatement> guarantees = new ArrayList<>();
		for (AgreeStatement stmt : e.guarantees) {
			guarantees.add(this.visit(stmt));
		}

		List<AgreeStatement> lemmas = new ArrayList<>();
		for (AgreeStatement stmt : e.lemmas) {
			lemmas.add(this.visit(stmt));
		}

		List<AgreeStatement> patternProps = new ArrayList<>();
		for (AgreeStatement stmt : e.patternProps) {
			patternProps.add(this.visit(stmt));
		}

		List<AgreeEquation> localEqs = new ArrayList<>();
		for (AgreeEquation eq : e.localEquations) {
			localEqs.add(this.visit(eq));
		}

		Expr clockConstraint = e.clockConstraint.accept(this);
		Expr initialConstraint = e.initialConstraint.accept(this);
		AgreeVar clockVar = this.visit(e.clockVar);
		EObject reference = e.reference;
		TimingModel timing = e.timing;
		// ComponentInstance compinst = e.compInst;

		AgreeNodeBuilder builder = new AgreeNodeBuilder(id);
		builder.addInput(inputs);
		builder.addOutput(outputs);
		builder.addLocal(locals);
		builder.addConnection(connections);
		builder.addSubNode(subNodes);
		builder.addAssertion(assertions);
		builder.addAssumption(assumptions);
		builder.addGuarantee(guarantees);
		builder.addLemma(lemmas);
		builder.addLocalEquation(localEqs);
		builder.addPatternProp(patternProps);
		builder.setClockConstraint(clockConstraint);
		builder.setInitialConstraint(initialConstraint);
		builder.setClockVar(clockVar);
		builder.setReference(reference);
		builder.setTiming(timing);
		builder.setCompInst(e.compInst);
		builder.addTimeFall(e.timeFallMap);
		builder.addTimeRise(e.timeRiseMap);
		builder.addTimeOf(e.timeOfMap);

		AgreeNode result = builder.build();
		visitedNodes.put(e.compInst, result);

		return result;
	}

	@Override
	public AgreeStatement visit(AgreeStatement e) {
		String string = e.string;
		Expr expr = e.expr.accept(this);
		EObject reference = e.reference;

		return new AgreeStatement(string, expr, reference);
	}

	@Override
	public AgreeVar visit(AgreeVar e) {
		String name = e.id;
		Type type = e.type.accept(lustreTypeMapVisitor);
		EObject reference = e.reference;
		ComponentInstance compInst = e.compInst;
		FeatureInstance featInst = e.featInst;

		return new AgreeVar(name, type, reference, compInst, featInst);
	}

	@Override
	public AgreeEquation visit(AgreeEquation agreeEquation) {
		EObject reference = agreeEquation.reference;
		List<IdExpr> lhs = new ArrayList<>();
		for (IdExpr e : agreeEquation.lhs) {
			lhs.add((IdExpr) e.accept(this));
		}
		Expr rhs = agreeEquation.expr.accept(this);
		return new AgreeEquation(lhs, rhs, reference);
	}
}
