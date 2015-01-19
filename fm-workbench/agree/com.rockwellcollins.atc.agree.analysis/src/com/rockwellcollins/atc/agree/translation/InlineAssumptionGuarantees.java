package com.rockwellcollins.atc.agree.translation;

import java.util.ArrayList;
import java.util.List;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Constant;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.visitors.AstMapVisitor;
import jkind.util.Util;

public class InlineAssumptionGuarantees extends AstMapVisitor {
	
	final String assumeVarPrefix = "___ASSUME";
	final String guaranteeVarPrefix = "___GUARANTEE";
	
	public static Program program(Program program){
		return new InlineAssumptionGuarantees().visit(program);
	}
	
	@Override 
	public Program visit(Program program){
		List<TypeDef> types = visitTypeDefs(program.types);
		List<Constant> constants = visitConstants(program.constants);
		List<Node> nodes = visitNodes(program.nodes);	
		//quick hack to strip the assumptions and guarantees from all nodes
		//and add assumption properties to all but the main node
		List<Node> finalNodes = new ArrayList<>();
		for(Node node : nodes){
			Node agNode;
			if(!node.id.equals(program.main)){
				//add assumption properties too
				List<String> properties = new ArrayList<>(node.properties);
				for(int i = 0; i < node.assumptions.size(); i++){
					properties.add(assumeVarPrefix+i);
				}
				agNode = new Node(node.location, node.id, node.inputs,
						node.outputs, node.locals,node.equations, properties, 
						node.assertions, null, null, null);
			}else{
				agNode = new AstMapVisitor().visit(node);
			}
			finalNodes.add(agNode);
		}
		return new Program(program.location, types, constants, finalNodes, program.main);
	}

	@Override
	public Node visit(Node node){
		
		//things we do not modify
		List<Expr> assumptions = Util.safeList(node.assumptions);
		List<Expr> guarantees = Util.safeList(node.guarantees);
		List<Expr> assertions = Util.safeList(node.assertions);
		List<VarDecl> inputs = Util.safeList(node.inputs);
		List<VarDecl> outputs = Util.safeList(node.outputs);
		
		//things we do modify
		ArrayList<VarDecl> locals = new ArrayList<>(node.locals);
		ArrayList<Equation> equations = new ArrayList<>(node.equations);
		ArrayList<String> properties = new ArrayList<>(node.properties);
		
		VarDecl assumeHistVar = new VarDecl(assumeVarPrefix+"__HIST", NamedType.BOOL);
		IdExpr assumeHistVarId = new IdExpr(assumeHistVar.id);
		locals.add(assumeHistVar);
		
		Expr assumeConjExpr = new BoolExpr(true);
		int i = 0;
		for(Expr assumeExpr : assumptions){
			
			VarDecl assumeVar = new VarDecl(assumeVarPrefix+i++, NamedType.BOOL);
			IdExpr assumeVarId = new IdExpr(assumeVar.id);
			locals.add(assumeVar);
			Equation assumEq = new Equation(assumeVarId, assumeExpr);
			equations.add(assumEq);
			
			assumeConjExpr = new BinaryExpr(assumeConjExpr, BinaryOp.AND, assumeVarId);
		}
		
		VarDecl assumeVarConj = new VarDecl(assumeVarPrefix+"__CONJ", NamedType.BOOL);
		locals.add(assumeVarConj);
		IdExpr assumeVarConjId = new IdExpr(assumeVarConj.id);
		equations.add(new Equation(assumeVarConjId, assumeConjExpr));

		Expr assumeHistExpr =  new UnaryExpr(UnaryOp.PRE, assumeHistVarId);
		assumeHistExpr = new BinaryExpr(assumeHistExpr, BinaryOp.AND, assumeVarConjId);
		assumeHistExpr = new BinaryExpr(assumeVarConjId, BinaryOp.ARROW, assumeHistExpr);
		Equation assumeHistEq = new Equation(assumeHistVarId, assumeHistExpr);
		equations.add(assumeHistEq);

		i = 0;
		for(Expr guarExpr : guarantees){
			VarDecl guarVar = new VarDecl(guaranteeVarPrefix+i++, NamedType.BOOL);
			Expr propExpr = new BinaryExpr(assumeHistVarId, BinaryOp.IMPLIES, guarExpr);
			Equation guarEq = new Equation(new IdExpr(guarVar.id), propExpr);
			locals.add(guarVar);
			equations.add(guarEq);
			properties.add(guarVar.id);
		}
		
		return new Node(node.location, node.id, inputs, outputs, locals, equations, 
				properties, assertions, node.assumptions, node.guarantees, null);
	}

}
