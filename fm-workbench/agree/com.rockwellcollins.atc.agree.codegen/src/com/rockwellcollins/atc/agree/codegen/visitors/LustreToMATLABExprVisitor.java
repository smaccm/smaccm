package com.rockwellcollins.atc.agree.codegen.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFirstTimeVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABImpliesFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;
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
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusElementUpdateExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeInitExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABUnaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABUnaryOp;

public class LustreToMATLABExprVisitor implements ExprVisitor<MATLABExpr> {

	public HashSet<String> inputSet = new HashSet<String>();
    public HashMap<String, MATLABFunction> functionMap = new HashMap<String, MATLABFunction>();
    public HashMap<String, MATLABExpr> persistentVarMap = new HashMap<String, MATLABExpr>();
    public HashMap<String, MATLABType> localVarTypeMap = new HashMap<String, MATLABType>();
    public List<MATLABPersistentVarInit> persistentVarInits = new ArrayList<>();
	
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
						persistentVarMap.put(firstTimeVar, new MATLABBoolExpr(true));
						persistentVarInits.add(new MATLABFirstTimeVarInit(firstTimeVar));
					}
					return new MATLABArrowFunctionCall(functionMap.get(functionName).name,"first_time", leftExpr,rightExpr);
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
		return new MATLABIdExpr(updateName(e.id));
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
		MATLABIdExpr elementExpr = new MATLABIdExpr(updateName(e.field));
		return new MATLABBusElementExpr(e.record.accept(this), elementExpr);
	}

	@Override
	//only need to handle assignment (including from arrow and pre operators)
	//and equation of record types
	//others are not defined in AGREE
	public MATLABExpr visit(RecordExpr e) {
		return new MATLABIdExpr(e.id);
	}

	@Override
	//only need to handle assignment (including from arrow and pre operators)
	//and equation of record types
	//others are not defined in AGREE
	public MATLABExpr visit(RecordUpdateExpr e) {
		return new MATLABBusElementUpdateExpr(e.record.accept(this), new MATLABIdExpr(updateName(e.field)), e.value.accept(this));
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
					String preVarName = updateName("pre_"+varName);
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
								//the preVar assignment at the end of the function needs to
								//conduct explicit type cast
								MATLABTypeCastExpr typeCastExpr = new MATLABTypeCastExpr(type, new MATLABIdExpr(varName));
								persistentVarMap.put(preVarName, typeCastExpr);
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
	
	public String updateName(String name){
		//remove leading _ 
		//replace ~ with _
		name = name.replaceAll("~", "_").replaceAll("^_+", "");
		//check if the name is an input or a local
		//if local, replace . with _
		if(!inputSet.contains(name)){
			name = name.replaceAll("\\.","_");
		}
		return name;
	}
	
	private void addFunctions(){
		//add the fixed functions all the time
		//but their definitions will be printed only 
		//when they have been called in the code
		functionMap.put("arrowFunction", new MATLABArrowFunction());
		functionMap.put("ifFunction", new MATLABIfFunction());
		functionMap.put("impliesFunction", new MATLABImpliesFunction());
	}
	
}
