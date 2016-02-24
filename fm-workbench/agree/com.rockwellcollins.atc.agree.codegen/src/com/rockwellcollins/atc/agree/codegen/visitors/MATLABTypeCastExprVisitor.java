package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;
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
		e.ifExpr = e.ifExpr.accept(this);
		e.elseExpr = e.elseExpr.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABIntExpr e) {
		return new MATLABTypeCastExpr(new MATLABInt32Type(),e);
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
		return new MATLABTypeCastExpr(new MATLABDoubleType(),e);
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
