package com.rockwellcollins.atc.agree.analysis.realtime;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.IdExpr;

public class AgreeTimesPattern extends AgreeCauseEffectPattern {

	public final BigInteger causeTimes;
	public final BigInteger effectTimes;

	public AgreeTimesPattern(String string, EObject reference, boolean effectIsExclusive, IdExpr cause, IdExpr effect,
			AgreePatternInterval causeInterval, AgreePatternInterval effectInterval, TriggerType causeType,
			TriggerType effectType, BigInteger causeTimes, BigInteger effectTimes) {
		super(string, reference, effectIsExclusive, cause, effect, causeInterval, effectInterval, causeType,
				effectType);
		this.causeTimes = causeTimes;
		this.effectTimes = effectTimes;
	}

}
