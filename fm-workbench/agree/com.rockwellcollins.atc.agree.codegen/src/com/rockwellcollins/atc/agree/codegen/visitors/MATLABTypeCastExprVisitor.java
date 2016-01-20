package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt16Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt64Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt8Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABSingleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt16Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt64Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt8Type;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrayAccessExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryFunctionCall;
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
import com.rockwellcollins.atc.agree.codegen.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.codegen.translation.LustreToMATLABTranslator;

public class MATLABTypeCastExprVisitor implements MATLABExprVisitor<MATLABExpr> {
	
	@Override
	public MATLABExpr visit(MATLABBinaryExpr e) {
		e.left = e.left.accept(this);
		e.right = e.right.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABBinaryFunctionCall e) {
		e.leftExpr = e.leftExpr.accept(this);
		e.rightExpr = e.rightExpr.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABBoolExpr e) {
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABIdExpr e) {
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABIfFunctionCall e) {
		e.cond = e.cond.accept(this);
		e.ifExpr = e.ifExpr.accept(this);
		e.elseExpr = e.elseExpr.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABIntExpr e) {
		switch (LustreToMATLABTranslator.intTypeStr) {
		case PreferenceConstants.INT_INT8:
			return new MATLABTypeCastExpr(new MATLABInt8Type(),e);
		case PreferenceConstants.INT_UINT8:
			return new MATLABTypeCastExpr(new MATLABUInt8Type(),e);
		case PreferenceConstants.INT_INT16:
			return new MATLABTypeCastExpr(new MATLABInt16Type(),e);
		case PreferenceConstants.INT_UINT16:
			return new MATLABTypeCastExpr(new MATLABUInt16Type(),e);
		case PreferenceConstants.INT_INT32:
			return new MATLABTypeCastExpr(new MATLABInt32Type(),e);
		case PreferenceConstants.INT_UINT32:
			return new MATLABTypeCastExpr(new MATLABUInt32Type(),e);
		case PreferenceConstants.INT_INT64:
			return new MATLABTypeCastExpr(new MATLABInt64Type(),e);
		case PreferenceConstants.INT_UINT64:  
			return new MATLABTypeCastExpr(new MATLABUInt64Type(),e);
		default:
			throw new IllegalArgumentException("Unknown int type: "+LustreToMATLABTranslator.intTypeStr);
		}
	}

	@Override
	public MATLABExpr visit(MATLABUnaryExpr e) {
		e.expr = e.expr.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABArrowFunctionCall e) {
		e.leftExpr = e.leftExpr.accept(this);
		e.rightExpr = e.rightExpr.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABDoubleExpr e) {
        switch (LustreToMATLABTranslator.realTypeStr) {
    		case PreferenceConstants.REAL_SINGLE:
    			return new MATLABTypeCastExpr(new MATLABSingleType(),e);
    		case PreferenceConstants.REAL_DOUBLE:
    			return new MATLABTypeCastExpr(new MATLABDoubleType(),e);
    		default:
    			throw new IllegalArgumentException("Unknown real type: "+LustreToMATLABTranslator.realTypeStr);
        	}
	}

	@Override
	public MATLABExpr visit(MATLABBusElementExpr e) {
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABTypeCastExpr e) {
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABTypeInitExpr e) {
		return e;
	}
	
	public MATLABExpr visit(MATLABExpr e) {
		return e.accept(this);
	}

	@Override
	public MATLABExpr visit(MATLABArrayAccessExpr e) {
		e.array = e.array.accept(this);
		e.index = e.index.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(
			MATLABBusElementUpdateExpr e) {
		e.value = e.value.accept(this);
		return e;
	}

}
