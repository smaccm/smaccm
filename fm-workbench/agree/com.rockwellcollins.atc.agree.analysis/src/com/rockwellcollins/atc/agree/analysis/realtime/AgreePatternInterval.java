package com.rockwellcollins.atc.agree.analysis.realtime;

import jkind.lustre.Expr;

public class AgreePatternInterval {
	public enum IntervalType {
		OPEN, OPEN_LEFT, OPEN_RIGHT, CLOSED
	};

	public final IntervalType type;
	public final Expr low;
	public final Expr high;

	public AgreePatternInterval(IntervalType type, Expr low, Expr high) {
		this.type = type;
		this.low = low;
		this.high = high;
	}

}
