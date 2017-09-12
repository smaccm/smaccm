/*
Copyright (c) 2015, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
// Forked from JKind on 2015-03-03
// See JKIND_LICENSE file for license details.
// Retains AgreeVar information while inlining.
package edu.uah.rsesc.aadlsimulator.agree.transformation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.TupleExpr;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.visitors.ExprMapVisitor;
import jkind.translation.SubstitutionVisitor;
import jkind.util.Util;

public class InlineNodeCalls extends ExprMapVisitor {
	public static SimulationProgram transform(final SimulationProgram program) {
		final Program lustreProgram = program.getLustreProgram();
		final InlineNodeCalls inliner = new InlineNodeCalls(Util.getNodeTable(lustreProgram.nodes));
		Node main = lustreProgram.getMainNode();
		
		// Build a single node that contains the result of inlining of all node calls
		final NodeBuilder nodeBuilder = new NodeBuilder(main);
		nodeBuilder.clearAssertions();
		nodeBuilder.addAssertions(inliner.visitExprs(main.assertions));
		nodeBuilder.clearEquations();
		nodeBuilder.addEquations(inliner.visitEquationsQueue(main.equations));
		nodeBuilder.addLocals(inliner.newLocals);
		nodeBuilder.addProperties(inliner.newProperties);
		
		// Build a new program
		final ProgramBuilder programBuilder = new ProgramBuilder(lustreProgram);
		programBuilder.clearNodes();
		programBuilder.addNode(nodeBuilder.build());
		
		// Build the simulation program
		final SimulationProgramBuilder simulationProgramBuilder = new SimulationProgramBuilder(program);
		simulationProgramBuilder.setLustreProgram(programBuilder.build());
		return simulationProgramBuilder.build();
	}
	
	// Used in place of actual tilde and dot characters since those can cause invalid id errors from JKind in some circumstances
	private final String tildeSeparator = "__TILDE__";
	private final String dotSeparator = "__DOT__";	

	private final Map<String, Node> nodeTable;
	private final List<VarDecl> newLocals = new ArrayList<>();
	private final List<String> newProperties = new ArrayList<>();
	private final Map<String, Integer> usedPrefixes = new HashMap<>();
	private final Queue<Equation> queue = new ArrayDeque<>();
	private final Map<String, Expr> inlinedCalls = new HashMap<>();

	private InlineNodeCalls(Map<String, Node> nodeTable) {
		this.nodeTable = nodeTable;
	}

	public List<Equation> visitEquationsQueue(List<Equation> equations) {
		queue.addAll(equations);
		List<Equation> result = new ArrayList<>();

		while (!queue.isEmpty()) {
			Equation eq = queue.poll();
			result.add(new Equation(eq.location, eq.lhs, eq.expr.accept(this)));
		}
		return result;
	}

	@Override
	public Expr visit(NodeCallExpr e) {
		// Detect duplicate node calls to reduce code size
		String key = getKey(e);
		if (inlinedCalls.containsKey(key)) {
			return inlinedCalls.get(key);
		} else {
			Expr result = TupleExpr.compress(visitNodeCallExpr(e));
			inlinedCalls.put(key, result);
			return result;
		}
	}

	private String getKey(NodeCallExpr e) {
		return new NodeCallExpr(getOriginalName(e), e.args).toString();
	}

	private String getOriginalName(NodeCallExpr e) {
		int substrIndex = e.node.lastIndexOf(dotSeparator);
		if(substrIndex == -1) {
			substrIndex += 1;
		} else {
			substrIndex += dotSeparator.length();
		}

		return e.node.substring(substrIndex);
	}

	public List<IdExpr> visitNodeCallExpr(NodeCallExpr e) {
		String prefix = newPrefix(e.node);
		Node node = nodeTable.get(getOriginalName(e));

		Map<String, IdExpr> translation = getTranslation(prefix, node);

		createInputEquations(node.inputs, e.args, translation);
		createAssignmentEquations(prefix, node.equations, translation);
		accumulateProperties(node.properties, translation);

		List<IdExpr> result = new ArrayList<>();
		for (VarDecl decl : node.outputs) {
			result.add(translation.get(decl.id));
		}
		return result;
	}

	private Map<String, IdExpr> getTranslation(String prefix, Node node) {
		Map<String, IdExpr> translation = new HashMap<>();
		for (VarDecl decl : Util.getVarDecls(node)) {
			String id = prefix + decl.id;
			
			if(decl instanceof SimulationPropertyVar) {
				final SimulationPropertyVar var = (SimulationPropertyVar)decl;
				newLocals.add(new SimulationPropertyVar(id, decl.type, var.reference, var.compInst, var.featInst));
			} else if(decl instanceof AgreeVar) {
				final AgreeVar var = (AgreeVar)decl;
				newLocals.add(new AgreeVar(id, decl.type, var.reference, var.compInst, var.featInst));
			} else {
				newLocals.add(new VarDecl(id, decl.type));
			}
			
			translation.put(decl.id, new IdExpr(id));
		}
		return translation;
	}

	private void createInputEquations(List<VarDecl> inputs, List<Expr> args,
			Map<String, IdExpr> translation) {
		for (int i = 0; i < inputs.size(); i++) {
			IdExpr idExpr = translation.get(inputs.get(i).id);
			Expr arg = args.get(i);
			queue.add(new Equation(idExpr, arg));
		}
	}

	private void createAssignmentEquations(final String prefix, List<Equation> equations,
			Map<String, IdExpr> translation) {
		SubstitutionVisitor substitution = new SubstitutionVisitor(translation) {
			@Override
			public Expr visit(NodeCallExpr e) {
				return new NodeCallExpr(e.location, prefix + e.node, visitExprs(e.args));
			}
		};

		for (Equation eq : equations) {
			List<IdExpr> lhs = new ArrayList<>();
			for (IdExpr idExpr : eq.lhs) {
				lhs.add(translation.get(idExpr.id));
			}
			Expr expr = eq.expr.accept(substitution);
			queue.add(new Equation(eq.location, lhs, expr));
		}
	}

	private String newPrefix(String prefix) {
		int i = 0;
		if (usedPrefixes.containsKey(prefix)) {
			i = usedPrefixes.get(prefix);
		}
		usedPrefixes.put(prefix, i + 1);
		return prefix + tildeSeparator + i + dotSeparator;
	}

	private void accumulateProperties(List<String> properties, Map<String, IdExpr> translation) {
		for (String property : properties) {
			newProperties.add(translation.get(property).id);
		}
	}
}
