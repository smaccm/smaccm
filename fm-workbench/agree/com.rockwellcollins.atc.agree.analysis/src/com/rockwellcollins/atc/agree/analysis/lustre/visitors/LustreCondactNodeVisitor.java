package com.rockwellcollins.atc.agree.analysis.lustre.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.rockwellcollins.atc.agree.analysis.AgreeException;

import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordUpdateExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.visitors.ExprVisitor;
import jkind.lustre.visitors.TypeAwareAstMapVisitor;

import static jkind.lustre.parsing.LustreParseUtil.equation;
import static jkind.lustre.parsing.LustreParseUtil.expr;
import static jkind.lustre.parsing.LustreParseUtil.to;

public class LustreCondactNodeVisitor extends TypeAwareAstMapVisitor {

	private final static String statVarPrefix = "_STATE";
	private final static String clockVarName = "_CLK";
	private final static String initVarName = "_INIT";
	private final static String tickedVarName = "_TICKED";

	private int numStateVars = 0;
	private Set<Equation> stateVarEqs = new HashSet<>();
	private List<VarDecl> stateVars = new ArrayList<>();

	public static Node translate(Node node) {
		if (node.equations.size() != 1) {
			throw new AgreeException("We expect that this node only has a single equation representing "
					+ "all constraints for the contract");
		}

		LustreCondactNodeVisitor visitor = new LustreCondactNodeVisitor();
		NodeBuilder builder = new NodeBuilder(node.id);
		builder.clearEquations();

		builder.addInput(new VarDecl(clockVarName, NamedType.BOOL));
		builder.addLocal(new VarDecl(initVarName, NamedType.BOOL));
		builder.addLocal(new VarDecl(tickedVarName, NamedType.BOOL));
		builder.addEquation(
				equation("ticked = clk -> clk or pre(ticked);", 
						to("ticked", tickedVarName), 
						to("clk", clockVarName)));
		builder.addEquation(equation("initVar = clk and (true -> not pre(ticked))", 
				to("initVar", initVarName),
				to("ticked", tickedVarName), 
				to("clk", clockVarName)));

		Equation eq = node.equations.get(0);
		Expr eqExpr = eq.expr.accept(visitor);

		builder.addEquation(new Equation(eq.lhs, eqExpr));
		builder.addEquations(visitor.stateVarEqs);

		return builder.build();
	}

	private LustreCondactNodeVisitor() {
	}

	@Override
	public Expr visit(ArrayAccessExpr e) {
		throw new AgreeException("We do not support array expressions");
	}

	@Override
	public Expr visit(ArrayExpr e) {
		throw new AgreeException("We do not support array expressions");
	}

	@Override
	public Expr visit(ArrayUpdateExpr e) {
		throw new AgreeException("We do not support array expressions");
	}

	@Override
	public Expr visit(BinaryExpr e) {
		return new BinaryExpr(e.left.accept(this), e.op, e.right.accept(this));
	}

	@Override
	public Expr visit(BoolExpr e) {
		return new BoolExpr(e.value);
	}

	@Override
	public Expr visit(CastExpr e) {
		return new CastExpr(e.type, e.expr.accept(this));
	}

	@Override
	public Expr visit(CondactExpr e) {
		return new CondactExpr(e.clock.accept(this), (NodeCallExpr) e.call.accept(this), acceptList(e.args));
	}

	@Override
	public Expr visit(IdExpr e) {
		return e;
	}

	@Override
	public Expr visit(IfThenElseExpr e) {
		return new IfThenElseExpr(e.cond.accept(this), e.thenExpr.accept(this), e.elseExpr.accept(this));
	}

	@Override
	public Expr visit(IntExpr e) {
		return new IntExpr(e.value);
	}

	@Override
	public Expr visit(NodeCallExpr e) {
		return new NodeCallExpr(e.node, acceptList(e.args));
	}

	@Override
	public Expr visit(RealExpr e) {
		return new RealExpr(e.value);
	}

	@Override
	public Expr visit(RecordAccessExpr e) {
		return new RecordAccessExpr(e.record.accept(this), e.field);
	}

	@Override
	public Expr visit(RecordExpr e) {
		Map<String, Expr> newFields = new HashMap<>();
		for (Entry<String, Expr> entry : e.fields.entrySet()) {
			newFields.put(entry.getKey(), entry.getValue().accept(this));
		}
		return new RecordExpr(e.id, newFields);
	}

	@Override
	public Expr visit(RecordUpdateExpr e) {
		return new RecordUpdateExpr(e.record.accept(this), e.field, e.value.accept(this));
	}

	@Override
	public Expr visit(TupleExpr e) {
		throw new AgreeException("We do not currently support typles");
	}

	@Override
	public Expr visit(UnaryExpr e) {
		if(e.op == UnaryOp.PRE){
			
		}
		IdExpr stateVarId = new IdExpr(statVarPrefix + numStateVars++);
		stateVarEqs.add(new Equation(stateVarId, new UnaryExpr(UnaryOp.PRE, e.expr.accept(this))));
		stateVars.add(new VarDecl(stateVarId.id, type))
		
		return stateVarId;
	}

	private List<Expr> acceptList(List<Expr> exprs) {
		List<Expr> result = new ArrayList<>();

		for (Expr expr : exprs) {
			result.add(expr.accept(this));
		}

		return result;
	}
}
