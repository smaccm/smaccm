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
import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryOp;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBoolExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusElementExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFirstTimeVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABImpliesFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIntCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUnaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUnaryOp;

public class LustreToMATLABExprVisitor implements ExprVisitor<MATLABExpr> {

	public HashSet<String> inputSet = new HashSet<String>();
    public HashMap<String, MATLABFunction> functionMap = new HashMap<String, MATLABFunction>();
    public HashMap<String, MATLABExpr> persistentVarMap = new HashMap<String, MATLABExpr>();
    public List<MATLABPersistentVarInit> persistentVarInits = new ArrayList<>();
	
	public LustreToMATLABExprVisitor() {
		addFunctions();
	} 
	
	@Override
	public MATLABExpr visit(ArrayAccessExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MATLABExpr visit(ArrayExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MATLABExpr visit(ArrayUpdateExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MATLABExpr visit(BinaryExpr e){
		MATLABExpr leftExpr = e.left.accept(this);
		String opName = e.op.name();
		MATLABBinaryOp op = MATLABBinaryOp.fromName(opName);
		MATLABExpr rightExpr = e.right.accept(this);
		if(op == null){
			if(opName.equals("INT_DIVIDE")){
				MATLABIntCastExpr castLeftExpr = new MATLABIntCastExpr(leftExpr);
				MATLABIntCastExpr castRightExpr = new MATLABIntCastExpr(rightExpr);
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
				else{
					throw new IllegalArgumentException();
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
		// TODO Auto-generated method stub
		return null;
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
		MATLABBusExpr busExpr = new MATLABBusExpr(e.record.accept(this));
		MATLABIdExpr elementExpr = new MATLABIdExpr(updateName(e.field));
		return new MATLABBusElementExpr(busExpr, elementExpr);
	}

	@Override
	public MATLABExpr visit(RecordExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MATLABExpr visit(RecordUpdateExpr e) {
		// TODO Auto-generated method stub
		return null;
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
						persistentVarMap.put(preVarName, new MATLABIdExpr(varName));
						persistentVarInits.add(new MATLABPreVarInit(preVarName, varName));
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
