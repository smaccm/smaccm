package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import com.rockwellcollins.atc.agree.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.ast.AgreeNode;
import com.rockwellcollins.atc.agree.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.ast.AgreeVar;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.RecordType;
import jkind.lustre.Type;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

public class LustreAstBuilder {
	
	private static List<Node> nodes;
	private static final String guarSuffix = "__GUARANTEE";
	private static final String assumeSuffix = "__ASSUME";
	
	public static Program getLustreProgram(AgreeProgram agreeProgram){
		
		nodes = new ArrayList<>();
		List<TypeDef> types = new ArrayList<>();
		for(Type type : agreeProgram.globalTypes){
			RecordType recType = (RecordType)type;
			types.add(new TypeDef(recType.id, type));
		}
		
		
		//Node main = getLustreNode(agreeProgram.topNode);
		
		return null;
	}

	private static Equation getHist(IdExpr histId, Expr expr){
		Expr preHist = new UnaryExpr(UnaryOp.PRE, histId);
		Expr preAndNow = new BinaryExpr(preHist, BinaryOp.AND, expr);
		return new Equation(histId, new BinaryExpr(expr, BinaryOp.ARROW, preAndNow));
	}
	
	private static Node getLustreNode(AgreeNode agreeNode, String nodePrefix){
		
		List<VarDecl> inputs = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		List<Expr> assertions = new ArrayList<>();
		
		Expr assumeConjExpr = new BoolExpr(true);
		int i = 0;
		for(AgreeStatement statement : agreeNode.assumptions){
			String inputName = assumeSuffix+i++;
			inputs.add(new AgreeVar(inputName, NamedType.BOOL, statement.reference));
			IdExpr assumeId = new IdExpr(inputName);
			assertions.add(new BinaryExpr(assumeId, BinaryOp.EQUAL, statement.expr));
			assumeConjExpr = new BinaryExpr(assumeId, BinaryOp.AND, assumeConjExpr);
		}
		
		String assumeHistName = assumeSuffix+"__HIST";
		String assumeConjName = assumeSuffix+"__CONJ";
		IdExpr assumeHistId = new IdExpr(assumeHistName);
		IdExpr assumeConjId = new IdExpr(assumeConjName);
		
		locals.add(new VarDecl(assumeHistName, NamedType.BOOL));
		locals.add(new VarDecl(assumeConjName, NamedType.BOOL));
		
		equations.add(new Equation(assumeConjId, assumeConjExpr));
		equations.add(getHist(assumeHistId, assumeConjId));
		
		Expr guarConjExpr = new BoolExpr(true);
		for(AgreeStatement statement : agreeNode.guarantees){
			guarConjExpr = new BinaryExpr(statement.expr, BinaryOp.AND, guarConjExpr);
		}
		
		assertions.add(new BinaryExpr(assumeHistId, BinaryOp.IMPLIES, guarConjExpr));
		
		for(AgreeStatement statement : agreeNode.assertions){
			assertions.add(statement.expr);
		}
		
		Expr assertExpr = new BoolExpr(true);
		for(Expr expr : assertions){
			assertExpr = new BinaryExpr(expr, BinaryOp.AND, assertExpr);
		}
		
		String outputName = "__ASSERT";
		List<VarDecl> outputs = new ArrayList<>();
		outputs.add(new VarDecl(outputName, NamedType.BOOL));
		equations.add(new Equation(new IdExpr(outputName), assertExpr));
		
		//gather the remaining inputs
		for(AgreeVar var : agreeNode.inputs){
			inputs.add(var);
		}
		for(AgreeVar var : agreeNode.outputs){
			inputs.add(var);
		}
		
		return new Node(nodePrefix+agreeNode.id, inputs, outputs, locals, equations);
	}
	
	private static AgreeNode flattenAgreeNode(AgreeNode agreeNode, String nodePrefix) {
		
		
		List<Node> subNodes = new ArrayList<>();
		List<AgreeVar> inputs = new ArrayList<>();
		List<AgreeVar> outputs = new ArrayList<>();
		List<AgreeVar> locals = new ArrayList<>();
		
		Expr assertion = new BoolExpr(true);
		
		for(AgreeNode subAgreeNode : agreeNode.subNodes){
			AgreeNode flatNode = flattenAgreeNode(subAgreeNode, 
					nodePrefix+agreeNode.id+AgreeASTBuilder.dotChar);
			
			Node lustreNode = getLustreNode(flatNode, nodePrefix);
			String prefix = subAgreeNode.id+AgreeASTBuilder.dotChar;

			for(VarDecl var : lustreNode.inputs){
				
				AgreeVar output = new AgreeVar(prefix+var.id, var.type, var.reference);
				outputs.add(output);
			}
			for(AgreeVar var : subAgreeNode.inputs){
				AgreeVar input = new AgreeVar(prefix+var.id, var.type, var.reference);
				inputs.add(input);
			}

			for(AgreeStatement statement : subAgreeNode.guarantees){
				outputs.add(e)
			}
			
			
		}
		
		List<Expr> clockExprs = getClockExprs(agreeNode);
		
		return null;
	}

	private static List<Expr> getClockExprs(AgreeNode agreeNode) {
		List<Expr> clockExprs = new ArrayList<>();
		
		for(AgreeNode subNode : agreeNode.subNodes){
			IdExpr clockId = new IdExpr(subNode.clockVar.id);

			switch(agreeNode.timing){
			case SYNC: 
				clockExprs.add(new BoolExpr(true));
				break;
			case ASYNC:
				clockExprs.add(clockId);
				break;
			case LATCHED:
				Expr preClock = new UnaryExpr(UnaryOp.PRE, clockId);
				Expr notClock = new UnaryExpr(UnaryOp.NOT, clockId);
				Expr andExpr = new BinaryExpr(preClock, BinaryOp.AND, notClock);
				Expr clockExpr = new BinaryExpr(new BoolExpr(false), BinaryOp.ARROW, andExpr);
				clockExprs.add(clockExpr);
				break;
			default:
				throw new AgreeException("unhandled timing type: '"+agreeNode.timing+"");
			}
		}
		
		return clockExprs;
	}



}
