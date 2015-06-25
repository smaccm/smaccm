package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Collections;
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
import com.rockwellcollins.atc.agree.ast.AgreeNode.TimingModel;

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
import jkind.lustre.RecordType;
import jkind.lustre.TupleExpr;
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
	
	private static Node getInputLatchingNode(Expr clockExpr, List<VarDecl> inputs, String nodeName){
		List<VarDecl> outputs = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		
		String clockRiseName = "__RISE";
		locals.add(new VarDecl(clockRiseName, NamedType.BOOL));
		IdExpr clockRiseId = new IdExpr(clockRiseName);
		
		Expr clockRise = new UnaryExpr(UnaryOp.PRE, clockExpr);
		clockRise = new UnaryExpr(UnaryOp.NOT, clockRise);
		clockRise = new BinaryExpr(clockExpr, BinaryOp.ARROW, clockRise);
		
		equations.add(new Equation(clockRiseId, clockRise));
		
		for(VarDecl var : inputs){
			String latchName = "latched__"+var.id;
			IdExpr input = new IdExpr(var.id);
			IdExpr latchId = new IdExpr(latchName);
			outputs.add(new VarDecl(latchName, var.type));
			
			Expr preLatch = new UnaryExpr(UnaryOp.PRE, latchId);
			equations.add(new Equation(latchId, 
					new BinaryExpr(input, BinaryOp.ARROW, 
				      new IfThenElseExpr(clockRiseId, input, preLatch))));
		}
		
//		List<VarDecl> newInputs = new ArrayList<>();
//		for(AgreeVar var : inputs){
//			newInputs.add(var);
//		}
		
		return new Node(nodeName, inputs, outputs, locals, equations);
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
		List<AgreeStatement> assertions = new ArrayList<>();
				
		for(AgreeNode subAgreeNode : agreeNode.subNodes){
			AgreeNode flatNode = flattenAgreeNode(subAgreeNode, 
					nodePrefix+agreeNode.id+AgreeASTBuilder.dotChar);
			
			Node lustreNode = getLustreNode(flatNode, nodePrefix);
			nodes.add(lustreNode);
			String prefix = subAgreeNode.id+AgreeASTBuilder.dotChar;

			int varCount = 0;
			for(AgreeVar var : subAgreeNode.inputs){
				varCount++;
				AgreeVar input = new AgreeVar(prefix+var.id, var.type, var.reference);
				inputs.add(input);
			}
			
			for(AgreeVar var : subAgreeNode.outputs){
				varCount++;
				AgreeVar output = new AgreeVar(prefix+var.id, var.type, var.reference);
				outputs.add(output);
			}

			for(AgreeStatement statement : subAgreeNode.assumptions){
				varCount++;
				AgreeVar output = new AgreeVar(prefix+assumeSuffix, NamedType.BOOL, statement.reference);
				outputs.add(output);
			}
			
			if(lustreNode.inputs.size() != varCount){
				throw new AgreeException("Something went wrong during node flattening");
			}
			
			List<Expr> inputIds = new ArrayList<>();
			for(VarDecl var : lustreNode.inputs){
				inputIds.add(new IdExpr(prefix+var.id));
			}
			
			if(agreeNode.timing == TimingModel.LATCHED){
				addLatchedConstraints(nodePrefix, inputs, assertions,
						subAgreeNode, prefix, inputIds);
			}
			
			Expr clockExpr = getClockExpr(agreeNode, subAgreeNode);
			
			Expr condactExpr = new CondactExpr(clockExpr, 
					new NodeCallExpr(lustreNode.id, inputIds), 
					Collections.singletonList(new BoolExpr(true)));
			
			AgreeStatement condactCall = new AgreeStatement("", condactExpr, null);
			assertions.add(condactCall);

		}
		
		//TODO: add connection constraints
		
		
		return null;
	}

	private static void addLatchedConstraints(String nodePrefix,
			List<AgreeVar> inputs, List<AgreeStatement> assertions,
			AgreeNode subAgreeNode, String prefix, List<Expr> inputIds) {
		String latchNodeString = nodePrefix+subAgreeNode.id+"__LATCHED_INPUTS";
		
		List<Expr> nonLatchedInputs = new ArrayList<>();
		List<Expr> latchedInputs = new ArrayList<>();
		List<VarDecl> latchedVars = new ArrayList<>();
		for(AgreeVar var : subAgreeNode.inputs){
			String latchedName = prefix+"latched__"+var.id;
			AgreeVar latchedVar = new AgreeVar(latchedName, var.type, var.reference);
			inputs.add(latchedVar);
			latchedVars.add(latchedVar);
			nonLatchedInputs.add(new IdExpr(prefix+var.id));
			latchedInputs.add(new IdExpr(latchedName));
		}
		
		Node latchNode = getInputLatchingNode(
				new IdExpr(subAgreeNode.clockVar.id), latchedVars, latchNodeString);
		
		nodes.add(latchNode);
		NodeCallExpr latchedNodeCall = new NodeCallExpr(latchNodeString, nonLatchedInputs);
		Expr latchedInputEq = new TupleExpr(latchedInputs);
		latchedInputEq = new BinaryExpr(latchedInputEq, BinaryOp.EQUAL, latchedNodeCall);
		assertions.add(new AgreeStatement("", latchedInputEq, null));
		
		//remove the references to the non-latched inputs
		List<Expr> inputIdsReplace = new ArrayList<>();
		for(Expr inExpr : inputIds){
			boolean replaced = false;
			for(AgreeVar var : subAgreeNode.inputs){
				if(((IdExpr)inExpr).id.equals(prefix+var.id)){
					inputIdsReplace.add(new IdExpr(prefix+"latched__"+var.id));
					replaced = true;
					break;
				}
			}
			if(!replaced){
				inputIdsReplace.add(inExpr);
			}
		}
		
		inputIds.clear();
		inputIds.addAll(inputIdsReplace);
	}

	private static Expr getClockExpr(AgreeNode agreeNode, AgreeNode subNode) {

		IdExpr clockId = new IdExpr(subNode.clockVar.id);
		switch(agreeNode.timing){
		case SYNC: 
			return new BoolExpr(true);
		case ASYNC:
			return clockId;
		case LATCHED:
			Expr preClock = new UnaryExpr(UnaryOp.PRE, clockId);
			Expr notClock = new UnaryExpr(UnaryOp.NOT, clockId);
			Expr andExpr = new BinaryExpr(preClock, BinaryOp.AND, notClock);
			Expr clockExpr = new BinaryExpr(new BoolExpr(false), BinaryOp.ARROW, andExpr);
			return clockExpr;
		default:
			throw new AgreeException("unhandled timing type: '"+agreeNode.timing+"");
		}

	}



}
