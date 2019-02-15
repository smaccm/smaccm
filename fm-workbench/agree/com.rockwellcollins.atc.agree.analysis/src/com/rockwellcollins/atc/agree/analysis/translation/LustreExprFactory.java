package com.rockwellcollins.atc.agree.analysis.translation;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;

public class LustreExprFactory {

	public static Expr makeANDExpr(Expr left, Expr right) {
		if (left instanceof BoolExpr) {
			if (((BoolExpr) left).value == true) {
				return right;
			}
			return new BoolExpr(false);
		}

		if (right instanceof BoolExpr) {
			if (((BoolExpr) right).value == true) {
				return left;
			}
			return new BoolExpr(false);
		}

		return new BinaryExpr(left, BinaryOp.AND, right);
	}

	public static Expr makeORExpr(Expr left, Expr right) {
		if (left instanceof BoolExpr) {
			if (((BoolExpr) left).value == false) {
				return right;
			}
			return new BoolExpr(true);
		}

		if (right instanceof BoolExpr) {
			if (((BoolExpr) right).value == false) {
				return left;
			}
			return new BoolExpr(true);
		}

		return new BinaryExpr(left, BinaryOp.OR, right);
	}

}
