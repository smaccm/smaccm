package com.rockwellcollins.atc.agree.analysis.ast;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.agree.TimeInterval;

import jkind.lustre.Expr;
import jkind.lustre.IdExpr;

public class AgreePattern extends AgreeStatement {

    public static final IdExpr TIME_ID = new IdExpr("time");
    public enum TriggerType {
        EVENT, CONDITION
    };

    public final Expr cause;
    public final Expr effect;

    public final TimeInterval causeInterval;
    public final TimeInterval effectInterval;

    public final TriggerType causeType;
    public final TriggerType effectType;

    public AgreePattern(String string, EObject reference, Expr cause, Expr effect,
            TimeInterval causeInterval, TimeInterval effectInterval, TriggerType causeType,
            TriggerType effectType) {
        super(string, null, reference);
        this.cause = cause;
        this.effect = effect;
        this.causeInterval = causeInterval;
        this.effectInterval = effectInterval;
        this.causeType = causeType;
        this.effectType = effectType;
    }

   
}
