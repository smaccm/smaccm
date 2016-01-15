package com.rockwellcollins.atc.agree.analysis.realtime;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.agree.TimeInterval;

import jkind.lustre.Expr;

public class AgreeTimesPattern extends AgreeCauseEffectPattern{

    public final BigInteger causeTimes;
    public final BigInteger effectTimes;

    public AgreeTimesPattern(String string, EObject reference, Expr cause, Expr effect,
            AgreePatternInterval causeInterval, AgreePatternInterval effectInterval, TriggerType causeType,
            TriggerType effectType, BigInteger causeTimes, BigInteger effectTimes) {
        super(string, reference, cause, effect, causeInterval, effectInterval, causeType, effectType);
        this.causeTimes = causeTimes;
        this.effectTimes = effectTimes;
    }

}
