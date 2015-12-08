package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBoolExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusElementExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIntCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUnaryExpr;

public interface MATLABExprVisitor<T> {
	
	public T visit(MATLABBinaryExpr e);
	public T visit(MATLABBinaryFunctionCall e);
	public T visit(MATLABBoolExpr e);
	public T visit(MATLABIdExpr e);
	public T visit(MATLABIfFunctionCall e);
	public T visit(MATLABIntExpr e);
	public T visit(MATLABUnaryExpr e);
	public T visit(MATLABIntCastExpr matlabIntCastExpr);
	public T visit(MATLABArrowFunctionCall matlabArrowFunctionCall);
	public T visit(MATLABDoubleExpr matlabDoubleExpr);
	public T visit(MATLABBusElementExpr matlabBusElementExpr);
	public T visit(MATLABBusExpr matlabBusExpr);
}
