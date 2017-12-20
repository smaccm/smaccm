package com.rockwellcollins.atc.agree.analysis.ast;

import jkind.lustre.Expr;

public class AgreeInterval {
	public final Expr low;
	public final Expr high;

	public AgreeInterval(Expr low, Expr high) {
		this.low = low;
		this.high = high;
	}
}
