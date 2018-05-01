package com.rockwellcollins.atc.agree.analysis.ast.visitors;

import static jkind.lustre.parsing.LustreParseUtil.expr;
import static jkind.lustre.parsing.LustreParseUtil.to;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.visitors.ExprMapVisitor;
import jkind.lustre.visitors.TypeReconstructor;

public class AgreeMakeClockedLustreNodes extends ExprMapVisitor {

	private final static String statVarPrefix = "_STATE";
	private final static String clockVarName = "_CLK";
	private final static String initVarName = "_INIT";
	public final static String clockedNodePrefix = "_CLOCKED_";

	private int numStateVars = 0;
	private Set<Equation> stateVarEqs = new HashSet<>();
	private List<VarDecl> stateVars = new ArrayList<>();
	// used to hash expressions so we do not make more state vars than we need
	private Map<String, IdExpr> stateVarMap = new HashMap<>();
	private Map<String, Node> clockedNodeMap = new HashMap<>();
	private TypeReconstructor typeReconstructor;
	private final AgreeProgram origProgram;

	public static AgreeProgram translate(AgreeProgram program) {
		AgreeMakeClockedLustreNodes visitor = new AgreeMakeClockedLustreNodes(program);

		List<Node> nodeList = new ArrayList<>();
		for (Node node : program.globalLustreNodes) {
			nodeList.add(node);
			nodeList.add(visitor.getClockedNode(node.id));
		}

		return new AgreeProgram(program.agreeNodes, nodeList, program.globalTypes, program.topNode);
	}

	private AgreeMakeClockedLustreNodes(AgreeProgram program) {
		origProgram = program;
	}

	private AgreeMakeClockedLustreNodes(AgreeMakeClockedLustreNodes visitor, Node node) {
		origProgram = visitor.origProgram;
		List<TypeDef> types = AgreeUtils.getLustreTypes(origProgram);
		Program lustreProgram = new ProgramBuilder().addTypes(types).addNodes(origProgram.globalLustreNodes)
				.setMain(origProgram.topNode.id).build();
		typeReconstructor = new TypeReconstructor(lustreProgram);
		typeReconstructor.setNodeContext(node);
		clockedNodeMap.putAll(visitor.clockedNodeMap);
	}

	@Override
	public Expr visit(NodeCallExpr e) {
		// check to see if we have already made a clocked version of this node
		Node clockedNode = getClockedNode(e.node);
		List<Expr> argList = new ArrayList<>();
		argList.add(new IdExpr(clockVarName));
		argList.add(new IdExpr(initVarName));
		argList.addAll(e.args);
		return new NodeCallExpr(clockedNode.id, acceptList(argList));
	}

	private Node getClockedNode(String node) {
		Node clockedNode = clockedNodeMap.get(node);
		if (clockedNode == null) {
			Node originalNode = null;
			for (Node progNode : origProgram.globalLustreNodes) {
				if (progNode.id == node) {
					originalNode = progNode;
					break;
				}
			}
			NodeBuilder builder = new NodeBuilder(originalNode);
			builder.setId(clockedNodePrefix + originalNode.id);
			builder.clearEquations();
			builder.clearInputs();
			builder.addInput(new VarDecl(clockVarName, NamedType.BOOL));
			builder.addInput(new VarDecl(initVarName, NamedType.BOOL));
			builder.addInputs(originalNode.inputs);

			AgreeMakeClockedLustreNodes visitor = new AgreeMakeClockedLustreNodes(this, originalNode);
			for (Equation eq : originalNode.equations) {
				Expr newExpr = eq.expr.accept(visitor);
				// this will make an unguarded pre expression, but any non initialized
				// outputs should be masked by the init expression in the calling agree node
				newExpr = new IfThenElseExpr(new IdExpr(clockVarName), newExpr,
						new UnaryExpr(UnaryOp.PRE, eq.lhs.get(0)));
				builder.addEquation(new Equation(eq.lhs, newExpr));
				clockedNodeMap.putAll(visitor.clockedNodeMap);
			}
			builder.addLocals(visitor.stateVars);
			builder.addEquations(visitor.stateVarEqs);
			clockedNode = builder.build();
			clockedNodeMap.put(node, clockedNode);
		}
		return clockedNode;
	}

	private List<Expr> acceptList(List<Expr> argList) {
		List<Expr> visited = new ArrayList<>();
		for (Expr argExpr : argList) {
			visited.add(argExpr.accept(this));
		}
		return visited;
	}

	@Override
	public Expr visit(UnaryExpr e) {
		if (e.op == UnaryOp.PRE) {
			IdExpr stateVarId = stateVarMap.get(e.toString());
			if (stateVarId != null) {
				return stateVarId;
			}

			stateVarId = new IdExpr(statVarPrefix + numStateVars++);
			stateVarMap.put(e.toString(), stateVarId);

			Expr stateVarExpr = new UnaryExpr(UnaryOp.PRE, e.expr.accept(this));
			stateVarExpr = expr("if clk then stateVarExpr else (pre stateVar)", to("stateVar", stateVarId),
					to("stateVarExpr", stateVarExpr), to("clk", clockVarName));

			stateVars.add(new AgreeVar(stateVarId.id, e.accept(typeReconstructor), null, null, null));
			stateVarEqs.add(new Equation(stateVarId, stateVarExpr));

			return stateVarId;
		}
		return new UnaryExpr(e.op, e.expr.accept(this));
	}

	@Override
	public Expr visit(BinaryExpr e) {
		if (e.op == BinaryOp.ARROW) {
			return new IfThenElseExpr(new IdExpr(initVarName), e.left.accept(this), e.right.accept(this));
		} else {
			return new BinaryExpr(e.left.accept(this), e.op, e.right.accept(this));
		}
	}

}
