package com.rockwellcollins.atc.agree.codegen.translation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.Node;
import jkind.lustre.VarDecl;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssumption;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssignment;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarDecl;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABProperty;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABStatement;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.visitors.LustreToMATLABExprVisitor;

public class LustreToMATLABTranslator {

    public static MATLABPrimaryFunction translate(Node lustreNode){
        List<MATLABIdExpr> inputs = new ArrayList<>();
        List<MATLABStatement> statements = new ArrayList<>();
        List<MATLABFunction> functions = new ArrayList<>();
        List<MATLABPersistentVarDecl> persistentVarDecl = new ArrayList<>();
        
		LustreToMATLABExprVisitor exprVisitor = new LustreToMATLABExprVisitor();
        //inputs.addAll(agreeNode.inputs);
        //get function name
    	String functionName = "check_"+lustreNode.id;
    	
		for (VarDecl inputVar : lustreNode.inputs){
			//get inputs
			inputs.add(new MATLABIdExpr(inputVar.id));
			//translate the Lustre expression to MATLAB expression
			//add input Ids to inputList of the exprVisitor 
			//to help identify local variables
			exprVisitor.inputSet.add(inputVar.id);
		}
		
		//translate equations to assignments
		if (!lustreNode.equations.isEmpty()) {
			for (Equation equation : lustreNode.equations) {
				//get the variable to assign
				String varId = exprVisitor.updateName(equation.lhs.get(0).id);
				MATLABIdExpr varToAssign = new MATLABIdExpr(varId);
				//translate expressions
				MATLABExpr expr = exprVisitor.visit(equation.expr);
				//add any new preVar init from exprVisitor
				Iterator<MATLABPersistentVarInit> persistentVarInitIterator = exprVisitor.persistentVarInits.iterator();
				while (persistentVarInitIterator.hasNext()) {
					MATLABPersistentVarInit persistentVarInit = persistentVarInitIterator.next();
					//add new preVar init to the statements before the assignment
					statements.add(persistentVarInit);
					//remove the new preVar init from exprVisitor
					persistentVarInitIterator.remove();
				}
				//add assignment
				MATLABAssignment assignment = new MATLABAssignment(varToAssign,expr);
				statements.add(assignment);
			}
		}
		
		//add persistentVar decl and assignments
		Iterator<Entry<String, MATLABExpr>> it = exprVisitor.persistentVarMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, MATLABExpr> pair = (Entry<String, MATLABExpr>)it.next();
	        String varToAssign = pair.getKey();
	        MATLABExpr expr = pair.getValue();
	        statements.add(new MATLABAssignment(new MATLABIdExpr(varToAssign), expr));
	        persistentVarDecl.add(new MATLABPersistentVarDecl(varToAssign));
	    }
		
		//translate assertions
		for (Expr assertionExpr : lustreNode.assertions) {
			MATLABExpr expr = exprVisitor.visit(assertionExpr);
			//add assertions
			MATLABAssumption assumption = new MATLABAssumption(expr);
			statements.add(assumption);
		}
		
		//translate properties
		for (String propertyStr : lustreNode.properties) {
			propertyStr = exprVisitor.updateName(propertyStr);
			MATLABProperty property = new MATLABProperty(propertyStr);
			statements.add(property);
		}
		
		//add definitions for the functions that have been called
		//update the names of the funcitons included in matlabFunction
    	//to make sure they are unique, and the function names to the idList 
		for(Map.Entry<String, MATLABFunction> functionEntry: exprVisitor.functionMap.entrySet()){
			MATLABFunction function = functionEntry.getValue();
			if(function.functionCalled){
				functions.add(function);
			}
		}
		
		//Create primary function AST
    	MATLABPrimaryFunction primaryFunction = new MATLABPrimaryFunction(functionName, inputs, persistentVarDecl, statements, functions);
        return primaryFunction; 	
    }
	
}
