package com.rockwellcollins.atc.agree.analysis.realtime;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.IdExpr;

public class AgreeCauseEffectPattern extends AgreePattern {

	public enum TriggerType {
		EVENT, CONDITION
	};

	public final boolean effectIsExclusive;
	public final IdExpr cause;
	public final IdExpr effect;

	public final AgreePatternInterval causeInterval;
	public final AgreePatternInterval effectInterval;

	public final TriggerType causeType;
	public final TriggerType effectType;

	public AgreeCauseEffectPattern(String string, EObject reference, boolean effectIsExclusive, IdExpr cause,
			IdExpr effect, AgreePatternInterval causeInterval, AgreePatternInterval effectInterval,
			TriggerType causeType, TriggerType effectType) {
		super(string, null, reference);
		this.effectIsExclusive = effectIsExclusive;
		this.cause = cause;
		this.effect = effect;
		this.causeInterval = causeInterval;
		this.effectInterval = effectInterval;
		this.causeType = causeType;
		this.effectType = effectType;
	}

}
