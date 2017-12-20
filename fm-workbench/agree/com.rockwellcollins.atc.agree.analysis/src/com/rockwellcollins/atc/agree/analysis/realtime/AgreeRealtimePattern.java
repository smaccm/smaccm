package com.rockwellcollins.atc.agree.analysis.realtime;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.RealExpr;

public class AgreeRealtimePattern extends AgreePattern {
	public final IdExpr event;
	public final Expr period;
	public final Expr jitter;

	public AgreeRealtimePattern(String string, EObject reference, IdExpr event, Expr period, Expr jitter) {
		super(string, null, reference);
		this.event = event;
		this.period = period;
		if (jitter != null) {
			this.jitter = jitter;
		} else {
			this.jitter = new RealExpr(BigDecimal.ZERO);
		}
	}
}
