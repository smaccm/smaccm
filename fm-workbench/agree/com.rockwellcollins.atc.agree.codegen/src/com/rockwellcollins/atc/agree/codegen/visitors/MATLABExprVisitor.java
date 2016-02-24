package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrayAccessExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBoolExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusElementExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusElementUpdateExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeInitExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABUnaryExpr;

public interface MATLABExprVisitor<T> {
	
	public T visit(MATLABBinaryExpr e);
	public T visit(MATLABBinaryFunctionCall e);
	public T visit(MATLABBoolExpr e);
	public T visit(MATLABIdExpr e);
	public T visit(MATLABIfFunctionCall e);
	public T visit(MATLABIntExpr e);
	public T visit(MATLABUnaryExpr e);
	public T visit(MATLABArrowFunctionCall matlabArrowFunctionCall);
	public T visit(MATLABDoubleExpr matlabDoubleExpr);
	public T visit(MATLABBusElementExpr matlabBusElementExpr);
	public T visit(MATLABTypeCastExpr matlabTypeCastExpr);
	public T visit(MATLABTypeInitExpr matlabTypeInitExpr);
	public T visit(MATLABArrayAccessExpr matlabArrayAccessExpr);
	public T visit(MATLABBusElementUpdateExpr matlabBusElementUpdateExpr);
}
