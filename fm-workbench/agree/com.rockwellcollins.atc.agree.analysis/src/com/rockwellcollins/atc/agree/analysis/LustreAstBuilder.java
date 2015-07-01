package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.IdRewriteVisitor;
import com.rockwellcollins.atc.agree.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.ast.AgreeConnection.ConnectionType;
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
		
		AgreeNode flatNode = flattenAgreeNode(agreeProgram.topNode, "_TOP__");
		List<Expr> assertions = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<VarDecl> inputs = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		List<String> properties = new ArrayList<>();
		
		for(AgreeStatement assumption : flatNode.assumptions){
			assertions.add(assumption.expr);
		}
		
		for(AgreeStatement assertion : flatNode.assertions){
			assertions.add(assertion.expr);
		}
		
		int i = 0;
		for(AgreeStatement guarantee : flatNode.lemmas){
			String guarName = guarSuffix+i++;
			locals.add(new AgreeVar(guarName, NamedType.BOOL, guarantee.reference, flatNode.compInst));
			equations.add(new Equation(new IdExpr(guarName), guarantee.expr));
			properties.add(guarName);
		}
		
		for(AgreeStatement guarantee : flatNode.guarantees){
			String guarName = guarSuffix+i++;
			locals.add(new AgreeVar(guarName, NamedType.BOOL, guarantee.reference, flatNode.compInst));
			equations.add(new Equation(new IdExpr(guarName), guarantee.expr));
			properties.add(guarName);
		}
		
		for(AgreeVar var : flatNode.inputs){
			inputs.add(var);
		}
		int j = 0;
		for(AgreeVar var : flatNode.outputs){
			if(var.reference instanceof AssumeStatement){
				//remove the reference so that we don't check the ungaurded
				//variable as an assumption
				inputs.add(new VarDecl(var.id, NamedType.BOOL));
				//stupid hack to get the assumption properties right
				Expr clockExpr = new BoolExpr(true);
				ComponentInstance curInst = var.compInst;
				while(curInst != flatNode.compInst){
					String compLocation = curInst.getInstanceObjectPath();
					compLocation = getRelativeLocation(compLocation);
					compLocation.replace(".", AgreeASTBuilder.dotChar);
					Expr clockId = new IdExpr(compLocation+AgreeASTBuilder.clockIDSuffix);
					clockExpr = new BinaryExpr(clockExpr, BinaryOp.AND, clockId);
					
					curInst = curInst.getContainingComponentInstance();
				}
				
				clockExpr = new BinaryExpr(clockExpr, BinaryOp.IMPLIES, new IdExpr(var.id));
				AgreeVar assumeCheckVar = new AgreeVar(assumeSuffix+j++, NamedType.BOOL, var.reference, var.compInst);
				locals.add(assumeCheckVar);
				equations.add(new Equation(new IdExpr(assumeCheckVar.id), clockExpr));
				properties.add(assumeCheckVar.id);
			}else{
				inputs.add(var);
			}
		}
		
		Node main = new Node("main", inputs, null, locals, equations, properties, assertions);
		nodes.add(main);
		nodes.addAll(agreeProgram.globalLustreNodes);
		Program program = new Program(types, null, nodes, main.id);
				
		return program;
		
	}
	
	public static String getRelativeLocation(String location){
		int dotIndex = location.indexOf(".");
		if(dotIndex < 0){
			return "";
		}
		return location.substring(dotIndex+1);
	}

	private static Equation getHist(IdExpr histId, Expr expr){
		Expr preHist = new UnaryExpr(UnaryOp.PRE, histId);
		Expr preAndNow = new BinaryExpr(preHist, BinaryOp.AND, expr);
		return new Equation(histId, new BinaryExpr(expr, BinaryOp.ARROW, preAndNow));
	}
	
	private static Node getInputLatchingNode(IdExpr clockExpr, List<VarDecl> inputs, String nodeName){
		List<VarDecl> outputs = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		
		String clockRiseName = "__RISE";
		locals.add(new VarDecl(clockRiseName, NamedType.BOOL));
		IdExpr clockRiseId = new IdExpr(clockRiseName);
		
		Expr preClock = new UnaryExpr(UnaryOp.PRE, clockExpr);
		Expr notPreClock = new UnaryExpr(UnaryOp.NOT, preClock);
		Expr clockRise = new BinaryExpr(notPreClock, BinaryOp.AND, clockExpr);
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
		
		inputs.add(new VarDecl(clockExpr.id, NamedType.BOOL));
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
			inputs.add(new AgreeVar(inputName, NamedType.BOOL, statement.reference, agreeNode.compInst));
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
		for(AgreeStatement statement : agreeNode.lemmas){
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
		
		List<AgreeVar> inputs = new ArrayList<>();
		List<AgreeVar> outputs = new ArrayList<>();
		List<AgreeVar> locals = new ArrayList<>();
		List<AgreeStatement> assertions = new ArrayList<>();
				
		Expr someoneTicks = null;
		for(AgreeNode subAgreeNode : agreeNode.subNodes){
			String prefix = subAgreeNode.id+AgreeASTBuilder.dotChar;
			Expr clockExpr = getClockExpr(agreeNode, subAgreeNode);
			
			if(someoneTicks == null){
				someoneTicks = clockExpr;
			}else{
				someoneTicks = new BinaryExpr(someoneTicks, BinaryOp.OR, clockExpr);
			}

			AgreeNode flatNode = flattenAgreeNode(subAgreeNode, 
					nodePrefix+subAgreeNode.id+AgreeASTBuilder.dotChar);
			Node lustreNode = addSubNodeLustre(agreeNode, nodePrefix, flatNode);
			
			addInputsAndOutputs(inputs, outputs, flatNode, lustreNode, prefix);
			
			addCondactCall(agreeNode, nodePrefix, inputs, assertions,
					flatNode, prefix, clockExpr, lustreNode);
			
			addClockHolds(agreeNode, assertions, flatNode, clockExpr);
			
			addInitConstraint(agreeNode, outputs, assertions, flatNode,
					prefix, clockExpr);

		}
		
		if(agreeNode.timing != TimingModel.SYNC){
			if(someoneTicks == null){
				throw new AgreeException("Somehow we generated a clock constraint without any clocks");
			}
			assertions.add(new AgreeStatement("someone ticks", someoneTicks, null));
		}
		
		addConnectionConstraints(agreeNode, assertions);
		
		//add any clock constraints
		assertions.addAll(agreeNode.assertions);
		assertions.add(new AgreeStatement("", agreeNode.clockConstraint, null));
		inputs.addAll(agreeNode.inputs);
		outputs.addAll(agreeNode.outputs);
		locals.addAll(agreeNode.locals);
		
		return new AgreeNode(agreeNode.id, inputs, outputs, locals, null, 
				agreeNode.subNodes, assertions, agreeNode.assumptions, agreeNode.guarantees, agreeNode.lemmas,
				new BoolExpr(true), agreeNode.initialConstraint, agreeNode.clockVar, agreeNode.reference, null, agreeNode.compInst);
	}

	private static void addConnectionConstraints(AgreeNode agreeNode,
			List<AgreeStatement> assertions) {
		for(AgreeConnection conn : agreeNode.connections){
			String destName = conn.destinationNode == null ? "" : conn.destinationNode.id + AgreeASTBuilder.dotChar;
			destName = destName + conn.destinationVarName;
			
			String sourName = conn.sourceNode == null ? "" : conn.sourceNode.id + AgreeASTBuilder.dotChar;
			sourName = sourName + conn.sourceVarName;
			
			Expr connExpr = new BinaryExpr(new IdExpr(sourName), BinaryOp.EQUAL, new IdExpr(destName));
			
			assertions.add(new AgreeStatement("", connExpr, conn.reference));
			//the event connections are added seperatly in the agree ast
//			if(conn.type == ConnectionType.EVENT){
//				Expr eventExpr = new BinaryExpr(
//						new IdExpr(sourName+AgreeASTBuilder.eventSuffix), 
//						BinaryOp.EQUAL, 
//						new IdExpr(destName+AgreeASTBuilder.eventSuffix));
//				assertions.add(new AgreeStatement("", eventExpr, null));
//			}
		}
	}

	private static void addInitConstraint(AgreeNode agreeNode,
			List<AgreeVar> outputs, List<AgreeStatement> assertions,
			AgreeNode subAgreeNode, String prefix, Expr clockExpr) {
		if(agreeNode.timing != TimingModel.SYNC){
			String tickedName = subAgreeNode.id+"___TICKED";
			outputs.add(new AgreeVar(tickedName, NamedType.BOOL, null, agreeNode.compInst));
			Expr tickedId = new IdExpr(tickedName);
			Expr preTicked = new UnaryExpr(UnaryOp.PRE, tickedId);
			Expr tickedOrPre = new BinaryExpr(clockExpr, BinaryOp.OR, preTicked);
			Expr initOrTicked = new BinaryExpr(clockExpr, BinaryOp.ARROW, tickedOrPre);
			Expr tickedEq = new BinaryExpr(tickedId, BinaryOp.EQUAL, initOrTicked);
			
			assertions.add(new AgreeStatement("", tickedEq, null));
			
			//we have two re-write the ids in the initial expressions
			IdRewriter rewriter = new IdRewriter() {
				
				@Override
				public IdExpr rewrite(IdExpr id) {
					// TODO Auto-generated method stub
					return new IdExpr(prefix+id.id);
				}
			};
			Expr newInit = subAgreeNode.initialConstraint.accept(new IdRewriteVisitor(rewriter));
			
			Expr initConstr = new BinaryExpr(new UnaryExpr(UnaryOp.NOT, tickedId), BinaryOp.IMPLIES, newInit);
			assertions.add(new AgreeStatement("", initConstr, null));
		}
	}

	private static void addClockHolds(AgreeNode agreeNode,
			List<AgreeStatement> assertions, AgreeNode subAgreeNode,
			Expr clockExpr) {
		if(agreeNode.timing != TimingModel.SYNC){
			Expr hold = new BoolExpr(true);
			for(AgreeVar outVar : subAgreeNode.outputs){
				Expr varId = new IdExpr(subAgreeNode.id+AgreeASTBuilder.dotChar+outVar.id);
				Expr pre = new UnaryExpr(UnaryOp.PRE, varId);
				Expr eqPre = new BinaryExpr(varId, BinaryOp.EQUAL, pre);
				hold = new BinaryExpr(hold, BinaryOp.AND, eqPre);
			}

			Expr notClock = new UnaryExpr(UnaryOp.NOT, clockExpr);
			Expr notClockHold = new BinaryExpr(notClock, BinaryOp.IMPLIES, hold);
			notClockHold = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, notClockHold);
			assertions.add(new AgreeStatement("", notClockHold, null));
		}
	}

	private static void addCondactCall(AgreeNode agreeNode, String nodePrefix,
			List<AgreeVar> inputs, List<AgreeStatement> assertions,
			AgreeNode subAgreeNode, String prefix, Expr clockExpr,
			Node lustreNode) {
		List<Expr> inputIds = new ArrayList<>();
		for(VarDecl var : lustreNode.inputs){
			inputIds.add(new IdExpr(prefix+var.id));
		}
		
		if(agreeNode.timing == TimingModel.LATCHED){
			addLatchedConstraints(nodePrefix, inputs, assertions,
					subAgreeNode, prefix, inputIds);
		}
		
		Expr condactExpr = new CondactExpr(clockExpr, 
				new NodeCallExpr(lustreNode.id, inputIds), 
				Collections.singletonList(new BoolExpr(true)));
		
		AgreeStatement condactCall = new AgreeStatement("", condactExpr, null);
		assertions.add(condactCall);
	}

	private static void addInputsAndOutputs(List<AgreeVar> inputs,
			List<AgreeVar> outputs, AgreeNode subAgreeNode, Node lustreNode,
			String prefix) {
		int varCount = 0;
		for(AgreeVar var : subAgreeNode.inputs){
			varCount++;
			AgreeVar input = new AgreeVar(prefix+var.id, var.type, var.reference, subAgreeNode.compInst);
			inputs.add(input);
		}
		
		for(AgreeVar var : subAgreeNode.outputs){
			varCount++;
			AgreeVar output = new AgreeVar(prefix+var.id, var.type, var.reference, subAgreeNode.compInst);
			outputs.add(output);
		}

		//right now we do not support local variables in our translation
		for(AgreeVar var : subAgreeNode.locals){
			varCount++;
			AgreeVar local = new AgreeVar(prefix+var.id, var.type, var.reference, subAgreeNode.compInst);
			outputs.add(local);
		}
		
		int i = 0;
		for(AgreeStatement statement : subAgreeNode.assumptions){
			varCount++;
			AgreeVar output = new AgreeVar(prefix+assumeSuffix+i++, NamedType.BOOL, statement.reference, subAgreeNode.compInst);
			outputs.add(output);
		}
		
		inputs.add(subAgreeNode.clockVar);
		
		if(lustreNode.inputs.size() != varCount){
			throw new AgreeException("Something went wrong during node flattening");
		}
	}

	private static Node addSubNodeLustre(AgreeNode agreeNode,
			String nodePrefix, AgreeNode flatNode) {
		
		Node lustreNode = getLustreNode(flatNode, nodePrefix);
		addToNodes(lustreNode);
		return lustreNode;
	}

	private static void addLatchedConstraints(String nodePrefix,
			List<AgreeVar> inputs, List<AgreeStatement> assertions,
			AgreeNode subAgreeNode, String prefix, List<Expr> inputIds) {
		String latchNodeString = nodePrefix+subAgreeNode.id+"__LATCHED_INPUTS";
		
		List<Expr> nonLatchedInputs = new ArrayList<>();
		List<Expr> latchedInputs = new ArrayList<>();
		List<VarDecl> latchedVars = new ArrayList<>();
		for(AgreeVar var : subAgreeNode.inputs){
			String latchedName = prefix+"latched___"+var.id;
			AgreeVar latchedVar = new AgreeVar(latchedName, var.type, var.reference, subAgreeNode.compInst);
			inputs.add(latchedVar);
			latchedVars.add(latchedVar);
			nonLatchedInputs.add(new IdExpr(prefix+var.id));
			latchedInputs.add(new IdExpr(latchedName));
		}
		
		//have to add the clock variable to the node call as well
		nonLatchedInputs.add(new IdExpr(subAgreeNode.clockVar.id));
		Node latchNode = getInputLatchingNode(
				new IdExpr(subAgreeNode.clockVar.id), latchedVars, latchNodeString);
		
		addToNodes(latchNode);
		NodeCallExpr latchedNodeCall = new NodeCallExpr(latchNodeString, nonLatchedInputs);
		Expr latchedInputEq;
		if(latchedInputs.size() != 1){
			latchedInputEq = new TupleExpr(latchedInputs);
		}else{
			latchedInputEq = latchedInputs.get(0);
		}
		latchedInputEq = new BinaryExpr(latchedInputEq, BinaryOp.EQUAL, latchedNodeCall);
		assertions.add(new AgreeStatement("", latchedInputEq, null));
		
		//remove the references to the non-latched inputs
		List<Expr> inputIdsReplace = new ArrayList<>();
		for(Expr inExpr : inputIds){
			boolean replaced = false;
			for(AgreeVar var : subAgreeNode.inputs){
				if(((IdExpr)inExpr).id.equals(prefix+var.id)){
					inputIdsReplace.add(new IdExpr(prefix+"latched___"+var.id));
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
	
	private static void addToNodes(Node node){
		for(Node inList : nodes){
			if(node.id.equals(inList.id)){
				throw new AgreeException("AGREE Lustre AST Builder attempted to add multiple nodes of name '"+node.id+"'");
			}
		}
		nodes.add(node);
	}



}
