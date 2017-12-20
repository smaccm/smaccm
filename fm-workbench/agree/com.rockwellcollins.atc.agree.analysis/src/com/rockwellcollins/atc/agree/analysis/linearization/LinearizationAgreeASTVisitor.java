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

package com.rockwellcollins.atc.agree.analysis.linearization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNodeBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTMapVisitor;

import jkind.lustre.Expr;
import jkind.lustre.NodeCallExpr;

public class LinearizationAgreeASTVisitor extends AgreeASTMapVisitor {

	private static final String ephemeralBaseName = "__EPHEMERAL__";

	private int ephemeralIndex = 0;

	private Map<String, String> linearizationMap;

	private class LinearizationContext {
		public List<AgreeVar> addedVars = new ArrayList<>();
		public List<NodeCallExpr> liftedConstraintExprs = new ArrayList<>();
		public final ComponentInstance componentInstance;

		public LinearizationContext(ComponentInstance componentInstance) {
			this.componentInstance = componentInstance;
		}
	}

	private Stack<LinearizationContext> contextStack = new Stack<>();

	public LinearizationAgreeASTVisitor(Map<String, String> linearizationMap) {
		super(new jkind.lustre.visitors.TypeMapVisitor());
		this.linearizationMap = linearizationMap;
	}

	private class LinearizationResult {
		public final List<AgreeVar> addedLocals;
		public final List<AgreeStatement> liftedConstraints;
		public final AgreeStatement linearizedStatement;

		public LinearizationResult(List<AgreeVar> addedLocals, List<AgreeStatement> liftedConstraints,
				AgreeStatement linearizedStatement) {
			this.addedLocals = Collections.unmodifiableList(addedLocals);
			this.liftedConstraints = Collections.unmodifiableList(liftedConstraints);
			this.linearizedStatement = linearizedStatement;
		}
	}

	private LinearizationResult linearizeStatement(AgreeNode parent, AgreeStatement stmt,
			jkind.lustre.BinaryOp connective) {
		contextStack.push(new LinearizationContext(parent.compInst));
		List<AgreeVar> addedLocals = new ArrayList<>();
		List<AgreeStatement> addedAssertions = new ArrayList<>();

		AgreeStatement linearizedStatement = this.visit(stmt);

		// Incorporate the additions resulting from the linearization
		LinearizationContext ctx = contextStack.peek();
		addedLocals.addAll(contextStack.peek().addedVars);
		for (int index = 0; index < ctx.liftedConstraintExprs.size(); ++index) {
			AgreeVar ephemeral = ctx.addedVars.get(index);
			String statementName = stmt.string + " (linearize constraint " + ephemeral.id + ")";
			addedAssertions.add(new AgreeStatement(statementName, ctx.liftedConstraintExprs.get(index), null));
		}

		contextStack.pop();

		return new LinearizationResult(addedLocals, addedAssertions, linearizedStatement);
	}

	@Override
	public AgreeNode visit(AgreeNode e) {
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
			if (stmt.expr != null) {
				LinearizationResult linResult = linearizeStatement(e, stmt, jkind.lustre.BinaryOp.AND);
				outputs.addAll(linResult.addedLocals);
				assertions.addAll(linResult.liftedConstraints);
				assertions.add(linResult.linearizedStatement);
			} else {
				assertions.add(stmt);
			}
		}

		List<AgreeStatement> assumptions = new ArrayList<>();
		for (AgreeStatement stmt : e.assumptions) {
			if (stmt.expr != null) {
				LinearizationResult linResult = linearizeStatement(e, stmt, jkind.lustre.BinaryOp.AND);
				outputs.addAll(linResult.addedLocals);
				assertions.addAll(linResult.liftedConstraints);
				assumptions.add(linResult.linearizedStatement);
			} else {
				assumptions.add(stmt);
			}
		}

		List<AgreeStatement> guarantees = new ArrayList<>();
		for (AgreeStatement stmt : e.guarantees) {
			if (stmt.expr != null) {
				LinearizationResult linResult = linearizeStatement(e, stmt, jkind.lustre.BinaryOp.IMPLIES);
				outputs.addAll(linResult.addedLocals);
				guarantees.addAll(linResult.liftedConstraints);
				guarantees.add(linResult.linearizedStatement);
			} else {
				guarantees.add(stmt);
			}
		}

		List<AgreeStatement> lemmas = new ArrayList<>();
		for (AgreeStatement stmt : e.lemmas) {
			if (stmt.expr != null) {
				LinearizationResult linResult = linearizeStatement(e, stmt, jkind.lustre.BinaryOp.IMPLIES);
				outputs.addAll(linResult.addedLocals);
				assertions.addAll(linResult.liftedConstraints);
				lemmas.add(linResult.linearizedStatement);
			} else {
				lemmas.add(stmt);
			}
		}

		Expr clockConstraint = e.clockConstraint.accept(this);
		Expr initialConstraint = e.initialConstraint.accept(this);
		AgreeVar clockVar = this.visit(e.clockVar);

		AgreeNodeBuilder builder = new AgreeNodeBuilder(e);
		builder.clearInputs();
		builder.addInput(inputs);
		builder.clearOutputs();
		builder.addOutput(outputs);
		builder.clearLocals();
		builder.addLocal(locals);
		builder.clearConnections();
		builder.addConnection(connections);
		builder.clearSubNodes();
		builder.addSubNode(subNodes);
		builder.clearAssertions();
		builder.addAssertion(assertions);
		builder.clearAssumptions();
		builder.addAssumption(assumptions);
		builder.clearGuarantees();
		builder.addGuarantee(guarantees);
		builder.clearLemmas();
		builder.addLemma(lemmas);
		builder.setClockConstraint(clockConstraint);
		builder.setInitialConstraint(initialConstraint);
		builder.setClockVar(clockVar);

		AgreeNode result = builder.build();
		visitedNodes.put(e.compInst, result);

		return result;
	}

	@Override
	public Expr visit(NodeCallExpr e) {
		// If the call is to a linearization, generate ephemeral
		// var and constraint then substitute an id expr to the ephemeral
		// var for the fn call.
		String linCallDef = linearizationMap.get(e.node);

		if (linCallDef != null) {
			AgreeVar ephemeral = new AgreeVar(ephemeralBaseName + Integer.toString(ephemeralIndex),
					jkind.lustre.NamedType.REAL, null, contextStack.peek().componentInstance, null);
			++ephemeralIndex;

			List<Expr> args = visitExprs(e.args);
			args.add(new jkind.lustre.IdExpr(ephemeral.id));
			jkind.lustre.NodeCallExpr constraintExpr = new jkind.lustre.NodeCallExpr(linCallDef, args);

			contextStack.peek().addedVars.add(ephemeral);
			contextStack.peek().liftedConstraintExprs.add(constraintExpr);

			return new jkind.lustre.IdExpr(ephemeral.id);
		}

		return new NodeCallExpr(e.location, e.node, visitExprs(e.args));
	}

}
