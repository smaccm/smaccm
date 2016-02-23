package com.rockwellcollins.atc.agree.codegen.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordUpdateExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.visitors.ExprVisitor;
import jkind.util.StringNaturalOrdering;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFirstTimeVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABImpliesFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABLocalBusVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreInputVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreLocalVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrayAccessExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryOp;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBoolExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusElementExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeInitExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABUnaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABUnaryOp;
import com.rockwellcollins.atc.agree.codegen.util.UniqueID;

public class LustreToMATLABExprVisitor implements ExprVisitor<MATLABExpr> {

	public HashSet<String> inputSet = new HashSet<String>();
    public HashMap<String, MATLABFunction> functionMap = new HashMap<String, MATLABFunction>();
    public HashMap<String, MATLABExpr> persistentVarMap = new HashMap<String, MATLABExpr>();
    public HashMap<String, MATLABType> localVarTypeMap = new HashMap<String, MATLABType>();
    public List<MATLABPersistentVarInit> persistentVarInits = new ArrayList<>();
    public List<MATLABLocalBusVarInit> localBusVarInits = new ArrayList<>();
    public HashMap<UniqueID, UniqueID> idMap = new HashMap<UniqueID, UniqueID>();
    private int localVarIndex = 0;
	
	public LustreToMATLABExprVisitor() {
		addFunctions();
	} 
	
	@Override
	public MATLABExpr visit(ArrayAccessExpr e) {
		return new MATLABArrayAccessExpr(e.array.accept(this), e.index.accept(this)); 
	}

	@Override
	public MATLABExpr visit(ArrayExpr e) {
		throw new IllegalArgumentException();
	}

	@Override
	public MATLABExpr visit(ArrayUpdateExpr e) {
		throw new IllegalArgumentException();
	}

	@Override
	public MATLABExpr visit(BinaryExpr e){
		MATLABExpr leftExpr = e.left.accept(this);
		String opName = e.op.name();
		String opFuncStr = e.op.toString();
		MATLABBinaryOp op = MATLABBinaryOp.fromName(opName);
		MATLABExpr rightExpr = e.right.accept(this);
		if(op == null){
			if(opName.equals("INT_DIVIDE")){
				MATLABTypeCastExpr castLeftExpr = new MATLABTypeCastExpr(new MATLABInt32Type(),leftExpr);
				MATLABTypeCastExpr castRightExpr = new MATLABTypeCastExpr(new MATLABInt32Type(),rightExpr);
				MATLABBinaryOp castOp = MATLABBinaryOp.fromName("DIVIDE");
				return new MATLABBinaryExpr(castLeftExpr, castOp, castRightExpr);
			}
			else{		
				String functionName = null;
				if(opName.equals("IMPLIES")){
					functionName = "impliesFunction";
					//mark that this function is getting called
					functionMap.get(functionName).functionCalled = true;
					return new MATLABBinaryFunctionCall(functionMap.get(functionName).name,leftExpr,rightExpr);
				}
				else if(opName.equals("ARROW")){
					functionName = "arrowFunction";
					//mark that this function is getting called
					functionMap.get(functionName).functionCalled = true;
					String firstTimeVar = ((MATLABArrowFunction)functionMap.get(functionName)).firstTimeVar;
					//no duplicate addition
					if(!persistentVarMap.containsKey(firstTimeVar)){
						persistentVarMap.put(firstTimeVar, new MATLABBoolExpr(false));
						persistentVarInits.add(new MATLABFirstTimeVarInit(firstTimeVar));
					}
					return new MATLABArrowFunctionCall(functionMap.get(functionName).name, firstTimeVar, leftExpr, rightExpr);
				}
				else if(opName.equals("EQUAL")){
					return new MATLABBinaryFunctionCall("isequal",leftExpr,rightExpr);
				}
				else{
					return new MATLABBinaryFunctionCall(opFuncStr,leftExpr,rightExpr);
				}		
			}
		}
		else
			return new MATLABBinaryExpr(leftExpr, op, rightExpr);
	}

	@Override
	public MATLABExpr visit(BoolExpr e) {
		return new MATLABBoolExpr(e.value);
	}

	@Override
	public MATLABExpr visit(CastExpr e) {
		LustreToMATLABTypeVisitor typeVisitor = new LustreToMATLABTypeVisitor();
		return new MATLABTypeCastExpr(e.type.accept(typeVisitor), e.expr.accept(this));
	}

	@Override
	public MATLABExpr visit(CondactExpr e) {
		throw new IllegalArgumentException();
	}

	@Override
	public MATLABExpr visit(IdExpr e) {
		return new MATLABIdExpr(updateName(e.id, ""));
	}

	@Override
	public MATLABExpr visit(IfThenElseExpr e) {
		MATLABExpr cond = e.cond.accept(this);
		MATLABExpr ifExpr = e.thenExpr.accept(this);
		MATLABExpr elseExpr = e.elseExpr.accept(this);
		functionMap.get("ifFunction").functionCalled = true;
		return new MATLABIfFunctionCall(cond, ifExpr, elseExpr);
	}

	@Override
	public MATLABExpr visit(IntExpr e) {
		return new MATLABIntExpr(e.value);
	}

	@Override
	public MATLABExpr visit(NodeCallExpr e) {
		throw new IllegalArgumentException();
	}

	@Override
	public MATLABExpr visit(RealExpr e) {
		return new MATLABDoubleExpr(e.value.doubleValue());
	}

	@Override
	public MATLABExpr visit(RecordAccessExpr e) {
		MATLABExpr busExpr = e.record.accept(this);
		MATLABIdExpr elementExpr = new MATLABIdExpr(updateName(e.field, busExpr.toString()));
		return new MATLABBusElementExpr(busExpr, elementExpr);
	}

	@Override
	public MATLABExpr visit(RecordExpr e) {
		//Create a local variable with the record type id as prefix
		localVarIndex++;
		String localVarName = updateName(e.id + "_var"+localVarIndex,"");
		//add assignment to assign the fields of the variable
		//to the value specified in the RecordExpr
		SortedMap<String, MATLABExpr> fields = new TreeMap<>(new StringNaturalOrdering());
		
		Iterator<Entry<String, Expr>> iterator = e.fields.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Expr> entry = iterator.next();
			//conduct explicit type cast if a field value is a constant of double type or int type 
			MATLABTypeCastExprVisitor typeCastVisitor = new MATLABTypeCastExprVisitor();
			MATLABExpr fieldExpr = typeCastVisitor.visit(entry.getValue().accept(this));
			fields.put(updateName(entry.getKey(),e.id), fieldExpr);
		}
		
		localBusVarInits.add(new MATLABLocalBusVarInit(localVarName, null, fields));
		//In the expression that uses the RecordExpr, just reference the local variable
		return new MATLABIdExpr(localVarName);
	}

	@Override
	public MATLABExpr visit(RecordUpdateExpr e) {
		MATLABIdExpr recordIdExpr = (MATLABIdExpr) e.record.accept(this);
		
		//Assign the specific field of the variable created from the recordExpr associated with it
		//to the value specified in the RecordUpdateExpr
		SortedMap<String, MATLABExpr> fields = new TreeMap<>(new StringNaturalOrdering());
		//conduct explicit type cast if a field value is a constant of double type or int type 
		MATLABTypeCastExprVisitor typeCastVisitor = new MATLABTypeCastExprVisitor();
		MATLABExpr fieldExpr = typeCastVisitor.visit(e.value.accept(this));
		fields.put(updateName(e.field,recordIdExpr.id), fieldExpr);
		
		String originalVar = null;
		if(e.record instanceof IdExpr){
			originalVar = updateName(e.record.toString(),"");
		}
		else{
			originalVar = updateName(recordIdExpr.id.split("_var")[0]+"_var"+localVarIndex,"");
		}
		localVarIndex++;
		String newVar = updateName(recordIdExpr.id.split("_var")[0]+"_var"+localVarIndex,"");
		localBusVarInits.add(new MATLABLocalBusVarInit(originalVar, newVar, fields));
		//In the expression that uses the RecordUpdateExpr, just reference the variable
		return new MATLABIdExpr(newVar);
	}

	@Override
	public MATLABExpr visit(TupleExpr e) {
		throw new IllegalArgumentException();
	}

	@Override
	public MATLABExpr visit(UnaryExpr e) {
		MATLABExpr expr = e.expr.accept(this);
		String opName = e.op.name();
		MATLABUnaryOp op = MATLABUnaryOp.fromName(opName);
		if(op == null){
			if(opName.equals("PRE")){
				if(expr instanceof MATLABIdExpr){
					//function call for the following AGREE unary operator
					//PRE ("pre");
					String varName = ((MATLABIdExpr) expr).id;
					String preVarName = updateName("pre_"+varName,"");
					//no duplicate addition
					if(!persistentVarMap.containsKey(preVarName)){
						//add preVarInit
						//if the var is an input variable
						//init based on varName
						if(inputSet.contains(varName)){
							persistentVarInits.add(new MATLABPreInputVarInit(preVarName, varName));
							persistentVarMap.put(preVarName, new MATLABIdExpr(varName));
						}
						//if the var is a local variable
						//the init needs to be associated with a default value of the type
						//instead of varName, as the varName may be assigned in the same equation
						//the preVar first gets used
						else{
							//get type of varName
							//add preVar init based on default value of the type
							if(localVarTypeMap.containsKey(varName)){
								MATLABType type = localVarTypeMap.get(varName);
								persistentVarInits.add(new MATLABPreLocalVarInit(preVarName, new MATLABTypeInitExpr(type)));
								persistentVarMap.put(preVarName, new MATLABIdExpr(varName));
							}
							else{
								throw new IllegalArgumentException();
							}
							
						}
					}
					return new MATLABIdExpr(preVarName);
				}
				else{
					throw new IllegalArgumentException();
				}
			}
			else{
				throw new IllegalArgumentException();
			}			
		}
		else
			return new MATLABUnaryExpr(op, expr);
	}

	public MATLABExpr visit(Expr expr) {
		return expr.accept(this);
	}
	
	public String updateName(String name, String recordId){
		String updatedName = null;
		String nameToCheck = null;
		int varIndex = 0;
		UniqueID originalNameId = new UniqueID(name,recordId);
		//first check if the original name and recordId tuple is already in the keys of the map
		//if yes, retrieve the updated name from its value
		if(idMap.containsKey(originalNameId)){
			updatedName = idMap.get(originalNameId).id;
		}
		//if not, update the name
		else{
			//remove leading _ 
			//replace ~ with _
			updatedName = name.replaceAll("~", "_").replaceAll("^_+", "");
			//check if the name is an input or a local
			//if local, replace . with _
			if(!inputSet.contains(name)){
				updatedName = updatedName.replaceAll("\\.","_");
			}
			nameToCheck = updatedName;
			//check if the updated name and recordId tuple is in the map values
			//if yes, update the name further so it's unique from existing values
			while(idMap.containsValue(new UniqueID(nameToCheck, recordId))){
				varIndex++;
				nameToCheck = updatedName + "_"+varIndex;
			}
			updatedName = nameToCheck;
			//add a new entry into the map
			idMap.put(originalNameId, new UniqueID(updatedName, recordId));
		}
		
		return updatedName;
	}
	
	private void addFunctions(){
		//add the fixed functions all the time
		//but their definitions will be printed only 
		//when they have been called in the code
		functionMap.put("arrowFunction", new MATLABArrowFunction());
		functionMap.put("ifFunction", new MATLABIfFunction());
		functionMap.put("impliesFunction", new MATLABImpliesFunction());
		//add the names of the functions and first_time variable to the idMap
		updateName("arrowFunction","");
		updateName("first_time","");
		updateName("ifFunction","");
		updateName("impliesFunction","");	
	}
	
}
