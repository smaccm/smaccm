package com.rockwellcollins.atc.agree.analysis.realtime;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Expr;
import jkind.lustre.IdExpr;

public class AgreeSporadicPattern extends AgreeRealtimePattern {

	public AgreeSporadicPattern(String string, EObject reference, IdExpr event, Expr period, Expr jitter) {
		super(string, reference, event, period, jitter);
	}
}
