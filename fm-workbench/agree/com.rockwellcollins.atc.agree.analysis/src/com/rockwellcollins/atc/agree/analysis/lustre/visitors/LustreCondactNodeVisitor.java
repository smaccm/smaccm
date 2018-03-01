package com.rockwellcollins.atc.agree.analysis.lustre.visitors;

import static jkind.lustre.parsing.LustreParseUtil.equation;
import static jkind.lustre.parsing.LustreParseUtil.expr;
import static jkind.lustre.parsing.LustreParseUtil.to;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeMakeClockedLustreNodes;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CondactExpr;
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

public class LustreCondactNodeVisitor extends ExprMapVisitor {

	private final static String statVarPrefix = "_STATE";
	private final static String clockVarName = "_CLK";
	private final static String initVarName = "_INIT";
	private final static String tickedVarName = "_TICKED";
	private TypeReconstructor typeReconstructor;

	private int numStateVars = 0;
	private Set<Equation> stateVarEqs = new HashSet<>();
	private List<VarDecl> stateVars = new ArrayList<>();
	// used to hash expressions so we do not make more state vars than we need
	private Map<String, IdExpr> stateVarMap = new HashMap<>();
	private Set<String> globalLustreNodeNames = new HashSet<>();

	public static Node translate(AgreeProgram agreeProgram, AgreeNode agreeNode, Node node) {
		if (node.outputs.size() != 1) {
			throw new AgreeException("We expect that this node only has a single output representing "
					+ "all constraints for the contract");
		}

		LustreCondactNodeVisitor visitor = new LustreCondactNodeVisitor(agreeProgram, node);

		NodeBuilder builder = new NodeBuilder(node);
		builder.clearEquations();

		builder.addInput(new AgreeVar(clockVarName, NamedType.BOOL, null));
		addTickedEq(builder);
		addInitEq(builder);

		Expr holdExpr = new BoolExpr(true);
		// make clock hold exprs
		for (AgreeVar var : agreeNode.outputs) {
			Expr varId = new IdExpr(var.id);
			Expr preVar = new UnaryExpr(UnaryOp.PRE, varId);
			holdExpr = new BinaryExpr(holdExpr, BinaryOp.AND, new BinaryExpr(varId, BinaryOp.EQUAL, preVar));
		}
		holdExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, holdExpr);

		for (int i = 0; i < agreeNode.assumptions.size(); i++) {
			Expr varId = new IdExpr(LustreAstBuilder.assumeSuffix + i);
			Expr preVar = new UnaryExpr(UnaryOp.PRE, varId);
			preVar = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, preVar);
			holdExpr = new BinaryExpr(holdExpr, BinaryOp.AND, new BinaryExpr(varId, BinaryOp.EQUAL, preVar));
		}

		holdExpr = expr("(not clk => holdExpr)", to("clk", clockVarName), to("holdExpr", holdExpr));

		// make the constraint for the initial outputs
		Expr initConstr = expr("not ticked => initExpr", to("ticked", tickedVarName),
				to("initExpr", agreeNode.initialConstraint));

		// re-write the old expression using the visitor
		for (Equation eq : node.equations) {
			if (eq.lhs.size() != 1) {
				throw new AgreeException("we expect that all eqs have a single lhs now");
			}
			IdExpr var = eq.lhs.get(0);
			boolean isLocal = false;
			for (VarDecl local : node.locals) {
				if (local.id.equals(var.id)) {
					isLocal = true;
					break;
				}
			}
			if (isLocal) {
				Expr newExpr = eq.expr.accept(visitor);
				newExpr = new IfThenElseExpr(new IdExpr(clockVarName), newExpr, new UnaryExpr(UnaryOp.PRE, var));
				builder.addEquation(new Equation(eq.lhs, newExpr));
			} else {
				// this is the only output
				Expr newExpr = eq.expr.accept(visitor);
				newExpr = new BinaryExpr(new IdExpr(clockVarName), BinaryOp.IMPLIES, newExpr);
				builder.addEquation(new Equation(eq.lhs,
						new BinaryExpr(initConstr, BinaryOp.AND, new BinaryExpr(holdExpr, BinaryOp.AND, newExpr))));
			}
		}
		// this var equations should be populated by the visitor call above
		builder.addEquations(visitor.stateVarEqs);
		builder.addLocals(visitor.stateVars);

		return builder.build();
	}

	private static void addInitEq(NodeBuilder builder) {
		builder.addLocal(new AgreeVar(initVarName, NamedType.BOOL, null));
		builder.addEquation(equation("initVar = clk and (true -> not pre(ticked));", to("initVar", initVarName),
				to("ticked", tickedVarName), to("clk", clockVarName)));
	}

	private static void addTickedEq(NodeBuilder builder) {
		builder.addLocal(new AgreeVar(tickedVarName, NamedType.BOOL, null));
		builder.addEquation(
				equation("ticked = clk -> clk or pre(ticked);", to("ticked", tickedVarName), to("clk", clockVarName)));
	}

	private LustreCondactNodeVisitor(AgreeProgram agreeProgram, Node node) {
		List<TypeDef> types = AgreeUtils.getLustreTypes(agreeProgram);
		Program program = new ProgramBuilder().addTypes(types).addNodes(agreeProgram.globalLustreNodes)
				.setMain(agreeProgram.topNode.id).build();
		globalLustreNodeNames
		.addAll(agreeProgram.globalLustreNodes.stream().map(n -> n.id).collect(Collectors.toSet()));
		typeReconstructor = new TypeReconstructor(program);
		typeReconstructor.setNodeContext(node);
	}

	@Override
	public Expr visit(BinaryExpr e) {
		if (e.op == BinaryOp.ARROW) {
			return new IfThenElseExpr(new IdExpr(initVarName), e.left.accept(this), e.right.accept(this));
		} else {
			return new BinaryExpr(e.left.accept(this), e.op, e.right.accept(this));
		}
	}

	@Override
	public Expr visit(CondactExpr e) {
		throw new AgreeException("There should not be be any condacts present in the generated lustre");
	}

	@Override
	public Expr visit(NodeCallExpr e) {
		if (globalLustreNodeNames.contains(e.node)) {
			List<Expr> newArgs = new ArrayList<>();
			newArgs.add(new IdExpr(clockVarName));
			newArgs.add(new IdExpr(initVarName));
			newArgs.addAll(acceptList(e.args));
			return new NodeCallExpr(AgreeMakeClockedLustreNodes.clockedNodePrefix + e.node, newArgs);
		} else {
			return new NodeCallExpr(e.node, acceptList(e.args));
		}
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

			stateVars.add(new AgreeVar(stateVarId.id, e.accept(typeReconstructor), null));
			stateVarEqs.add(new Equation(stateVarId, stateVarExpr));

			return stateVarId;
		}
		return new UnaryExpr(e.op, e.expr.accept(this));
	}

	private List<Expr> acceptList(List<Expr> exprs) {
		List<Expr> result = new ArrayList<>();

		for (Expr expr : exprs) {
			result.add(expr.accept(this));
		}

		return result;
	}
}
