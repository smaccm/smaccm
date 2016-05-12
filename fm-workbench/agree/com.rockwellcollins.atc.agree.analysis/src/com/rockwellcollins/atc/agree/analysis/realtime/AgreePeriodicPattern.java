package com.rockwellcollins.atc.agree.analysis.realtime;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Expr;

public class AgreePeriodicPattern extends AgreeRealtimePattern{

    public AgreePeriodicPattern(String string, EObject reference, Expr event, Expr period, Expr jitter) {
        super(string, reference, event, period, jitter);
    }

}
