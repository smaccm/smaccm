package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.Expr;

public class MNSynchronyElement {
	public final Expr maxClock;
	public final Expr minClock;
	public final int max;
	public final int min;

	public MNSynchronyElement(Expr maxClock, Expr minClock, int max, int min) {
		this.maxClock = maxClock;
		this.minClock = minClock;
		this.max = max;
		this.min = min;
	}
}
