package com.rockwellcollins.atc.agree.codegen.translation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.preference.IPreferenceStore;

import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.Node;
import jkind.lustre.VarDecl;

import com.rockwellcollins.atc.agree.codegen.Activator;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.codegen.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssumption;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssignment;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABLocalBusVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarDecl;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABProperty;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABStatement;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.visitors.LustreToMATLABExprVisitor;
import com.rockwellcollins.atc.agree.codegen.visitors.LustreToMATLABTypeVisitor;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeCastExprVisitor;

public class LustreToMATLABTranslator {
	public static String intTypeStr = null;
	public static String realTypeStr = null;
	
    public static MATLABPrimaryFunction translate(Node lustreNode, AgreeProgram agreeProgram){
        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
        intTypeStr = prefs.getString(PreferenceConstants.PREF_INT);
        realTypeStr = prefs.getString(PreferenceConstants.PREF_REAL);
    	
        List<MATLABIdExpr> inputs = new ArrayList<>();
        List<MATLABStatement> statements = new ArrayList<>();
        List<MATLABFunction> functions = new ArrayList<>();
        List<MATLABPersistentVarDecl> persistentVarDecl = new ArrayList<>();
        
        LustreToMATLABExprVisitor exprVisitor = new LustreToMATLABExprVisitor();
        LustreToMATLABTypeVisitor typeVisitor = new LustreToMATLABTypeVisitor();
        
        //get function name
    	String functionName = "check_"+lustreNode.id;
    	
    	//add input variables
		for (VarDecl inputVar : lustreNode.inputs){
			//get inputs
			inputs.add(new MATLABIdExpr(exprVisitor.updateName(inputVar.id,"")));
			//translate the Lustre expression to MATLAB expression
			//add input Ids to inputList of the exprVisitor 
			//to help identify local variables
			exprVisitor.inputSet.add(inputVar.id);
		}
		
		//add local variable and their types
		for (VarDecl localVar : lustreNode.locals){
			//get local var Name and Type
			exprVisitor.localVarTypeMap.put(exprVisitor.updateName(localVar.id,""), localVar.type.accept(typeVisitor));	
		}
		
		//translate equations to assignments
		if (!lustreNode.equations.isEmpty()) {
			for (Equation equation : lustreNode.equations) {
				//get the variable to assign
				String varId = exprVisitor.updateName(equation.lhs.get(0).id,"");
				MATLABIdExpr varToAssign = new MATLABIdExpr(varId);
				//get the type for the local variable
				//MATLABType type = exprVisitor.localVarTypeMap.get(varId);
				//translate expressions
				MATLABExpr expr = exprVisitor.visit(equation.expr);
				//conduct explicit type cast if it's a constant of double type or int type
				//no need to type cast for assignment from an input variable
				//or operations (including functions) involving known types
				MATLABTypeCastExprVisitor typeCastVisitor = new MATLABTypeCastExprVisitor();
				expr = typeCastVisitor.visit(expr);
				//add any new preVar init from exprVisitor
				Iterator<MATLABPersistentVarInit> persistentVarInitIterator = exprVisitor.persistentVarInits.iterator();
				while (persistentVarInitIterator.hasNext()) {
					MATLABPersistentVarInit persistentVarInit = persistentVarInitIterator.next();
					//add new preVar init to the statements before the assignment
					statements.add(persistentVarInit);
					//remove the new preVar init from exprVisitor
					persistentVarInitIterator.remove();
				}
				
				//add any new local Bus var init from exprVisitor
				Iterator<MATLABLocalBusVarInit> busVarInitIterator = exprVisitor.localBusVarInits.iterator();
				while (busVarInitIterator.hasNext()) {
					MATLABLocalBusVarInit busVarInit = busVarInitIterator.next();
					//add new local Bus var init to the statements before the assignment
					statements.add(busVarInit);
					//remove the new local Bus var init from exprVisitor
					busVarInitIterator.remove();
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
			propertyStr = exprVisitor.updateName(propertyStr,"");
			MATLABProperty property = new MATLABProperty(propertyStr);
			statements.add(property);
		}
		
		//add definitions for the functions that have been called
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
