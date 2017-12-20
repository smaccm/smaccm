package com.rockwellcollins.atc.agree.analysis.realtime;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.agree.AlwaysStatement;
import com.rockwellcollins.atc.agree.agree.ClosedTimeInterval;
import com.rockwellcollins.atc.agree.agree.OpenLeftTimeInterval;
import com.rockwellcollins.atc.agree.agree.OpenRightTimeInterval;
import com.rockwellcollins.atc.agree.agree.OpenTimeInterval;
import com.rockwellcollins.atc.agree.agree.PeriodicStatement;
import com.rockwellcollins.atc.agree.agree.SporadicStatement;
import com.rockwellcollins.atc.agree.agree.TimeInterval;
import com.rockwellcollins.atc.agree.agree.WhenHoldsStatement;
import com.rockwellcollins.atc.agree.agree.WhenOccursStatment;
import com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement;
import com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement;
import com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement;
import com.rockwellcollins.atc.agree.agree.WheneverOccursStatement;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreeCauseEffectPattern.TriggerType;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePatternInterval.IntervalType;

import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;

public class AgreePatternBuilder extends AgreeSwitch<AgreeStatement> {

	private final String str;
	private final EObject ref;
	private final AgreeASTBuilder builder;

	public AgreePatternBuilder(String str, EObject ref, AgreeASTBuilder builder) {
		this.str = str;
		this.ref = ref;
		this.builder = builder;
	}

	@Override
	public AgreeStatement caseAlwaysStatement(AlwaysStatement object) {
		return new AgreeStatement(str, builder.doSwitch(object.getExpr()), ref);
	}

	@Override
	public AgreeStatement casePeriodicStatement(PeriodicStatement object) {
		IdExpr event = (IdExpr) builder.doSwitch(object.getEvent());

		com.rockwellcollins.atc.agree.agree.Expr jitter = object.getJitter();
		Expr jitterExpr = null;
		if (jitter != null) {
			jitterExpr = builder.doSwitch(jitter);
		}
		Expr period = builder.doSwitch(object.getPeriod());

		return new AgreePeriodicPattern(str, ref, event, period, jitterExpr);
	}

	@Override
	public AgreeStatement caseSporadicStatement(SporadicStatement object) {
		IdExpr event = (IdExpr) builder.doSwitch(object.getEvent());
		com.rockwellcollins.atc.agree.agree.Expr jitter = object.getJitter();
		Expr jitterExpr = null;
		if (jitter != null) {
			jitterExpr = builder.doSwitch(jitter);
		}
		Expr iat = builder.doSwitch(object.getIat());

		return new AgreeSporadicPattern(str, ref, event, iat, jitterExpr);
	}

	@Override
	public AgreeStatement caseWheneverHoldsStatement(WheneverHoldsStatement object) {
		IdExpr cause = (IdExpr) builder.doSwitch(object.getCause());
		IdExpr effect = (IdExpr) builder.doSwitch(object.getEffect());
		boolean exclusive = object.getExcl() != null;

		AgreePatternInterval interval = getIntervalType(object.getInterval());

		return new AgreeCauseEffectPattern(str, ref, exclusive, cause, effect, null, interval, TriggerType.EVENT,
				TriggerType.CONDITION);
	}

	@Override
	public AgreeStatement caseWheneverImpliesStatement(WheneverImpliesStatement object) {
		throw new AgreeException("We do not support this pattern currently");
		// IdExpr cause = (IdExpr) builder.doSwitch(object.getCause());
		// IdExpr lhs = (IdExpr) builder.doSwitch(object.getLhs());
		// IdExpr rhs = (IdExpr) builder.doSwitch(object.getRhs());
		// boolean exclusive = object.getExcl() != null;
		// AgreePatternInterval effectInterval = getIntervalType(object.getInterval());

		// Expr effect = new BinaryExpr(lhs, BinaryOp.IMPLIES, rhs);
		// return new AgreeCauseEffectPattern(str, ref, exclusive, cause, effect, null, effectInterval, TriggerType.EVENT,
		// TriggerType.CONDITION);
	}

	@Override
	public AgreeStatement caseWheneverOccursStatement(WheneverOccursStatement object) {
		IdExpr cause = (IdExpr) builder.doSwitch(object.getCause());
		IdExpr effect = (IdExpr) builder.doSwitch(object.getEffect());
		boolean exclusive = object.getExcl() != null;
		AgreePatternInterval effectInterval = getIntervalType(object.getInterval());

		return new AgreeCauseEffectPattern(str, ref, exclusive, cause, effect, null, effectInterval, TriggerType.EVENT,
				TriggerType.EVENT);
	}

	@Override
	public AgreeStatement caseWheneverBecomesTrueStatement(WheneverBecomesTrueStatement object) {
		IdExpr cause = (IdExpr) builder.doSwitch(object.getCause());
		IdExpr effect = (IdExpr) builder.doSwitch(object.getEffect());
		boolean exclusive = object.getExcl() != null;
		AgreePatternInterval effectInterval = getIntervalType(object.getInterval());

		// make the effect rising edge sensitive
//        Expr preEffect = new UnaryExpr(UnaryOp.PRE, effect);
//        Expr notPreEffect = new UnaryExpr(UnaryOp.NOT, preEffect);
//        Expr edgeEffect = new BinaryExpr(notPreEffect, BinaryOp.AND, effect);
//        effect = new BinaryExpr(effect, BinaryOp.ARROW, edgeEffect);
		return new AgreeCauseEffectPattern(str, ref, exclusive, cause, effect, null, effectInterval, TriggerType.EVENT,
				TriggerType.EVENT);
	}

	@Override
	public AgreeStatement caseWhenHoldsStatement(WhenHoldsStatement object) {
		IdExpr condition = (IdExpr) builder.doSwitch(object.getCondition());
		IdExpr effect = (IdExpr) builder.doSwitch(object.getEvent());
		boolean exclusive = object.getExcl() != null;
		AgreePatternInterval conditionInterval = getIntervalType(object.getConditionInterval());
		AgreePatternInterval effectInterval = getIntervalType(object.getEventInterval());

		return new AgreeCauseEffectPattern(str, ref, exclusive, condition, effect, conditionInterval, effectInterval,
				TriggerType.CONDITION, TriggerType.EVENT);
	}

	@Override
	public AgreeStatement caseWhenOccursStatment(WhenOccursStatment object) {
		IdExpr condition = (IdExpr) builder.doSwitch(object.getCondition());
		IdExpr effect = (IdExpr) builder.doSwitch(object.getEvent());
		Expr timesExpr = builder.doSwitch(object.getTimes());
		boolean exclusive = object.getExcl() != null;
		if (!(timesExpr instanceof IntExpr)) {
			throw new AgreeException("Expected an integer literal in 'When Occurs' pattern");
		}
		BigInteger times = ((IntExpr) timesExpr).value;
		AgreePatternInterval interval = getIntervalType(object.getInterval());

		return new AgreeTimesPattern(str, ref, exclusive, condition, effect, interval, null, TriggerType.CONDITION,
				TriggerType.CONDITION, times, null);
	}

	private AgreePatternInterval getIntervalType(TimeInterval interval) {
		if (interval == null) {
			return null;
		}
		Expr low = builder.doSwitch(interval.getLow());
		Expr high = builder.doSwitch(interval.getHigh());
		IntervalType type;

		if (interval instanceof OpenTimeInterval) {
			type = IntervalType.OPEN;
		} else if (interval instanceof OpenLeftTimeInterval) {
			type = IntervalType.OPEN_LEFT;
		} else if (interval instanceof OpenRightTimeInterval) {
			type = IntervalType.OPEN_RIGHT;
		} else if (interval instanceof ClosedTimeInterval) {
			type = IntervalType.CLOSED;
		} else {
			throw new AgreeException("Unhandled TimeInterval type: " + interval.getClass());
		}
		return new AgreePatternInterval(type, low, high);
	}

}
