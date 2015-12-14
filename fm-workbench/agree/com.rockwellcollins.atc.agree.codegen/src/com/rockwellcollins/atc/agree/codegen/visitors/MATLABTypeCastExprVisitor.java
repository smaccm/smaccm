package com.rockwellcollins.atc.agree.codegen.visitors;

import jkind.Assert;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBoolExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusElementExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeInitExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABUnaryExpr;

public class MATLABTypeCastExprVisitor implements MATLABExprVisitor<MATLABExpr> {

	public final MATLABType type; 
	
	public MATLABTypeCastExprVisitor(MATLABType type) {
		Assert.isNotNull(type);
		this.type = type;
	}
	
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
		e.ifExpr = new MATLABTypeCastExpr(type,e.ifExpr.accept(this));
		e.elseExpr = new MATLABTypeCastExpr(type,e.elseExpr.accept(this));
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABIntExpr e) {
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABUnaryExpr e) {
		e.expr = e.expr.accept(this);
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABArrowFunctionCall e) {
		e.leftExpr = new MATLABTypeCastExpr(type,e.leftExpr.accept(this));
		e.rightExpr = new MATLABTypeCastExpr(type,e.rightExpr.accept(this));
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABDoubleExpr e) {
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABBusElementExpr e) {
		return e;
	}

	@Override
	public MATLABExpr visit(MATLABBusExpr e) {
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
		e = new MATLABTypeCastExpr(type, e.accept(this));
		return e;
	}

}
