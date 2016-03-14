package com.rockwellcollins.atc.agree.analysis.realtime;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Expr;

public class AgreeSporadicPattern extends AgreeRealtimePattern {

    public AgreeSporadicPattern(String string, EObject reference, Expr event, Expr period, Expr jitter) {
        super(string, reference, event, period, jitter);
    }
}
