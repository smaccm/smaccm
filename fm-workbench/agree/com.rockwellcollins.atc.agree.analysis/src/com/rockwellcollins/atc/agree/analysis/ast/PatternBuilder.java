package com.rockwellcollins.atc.agree.analysis.ast;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.agree.AlwaysStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.PatternStatement;
import com.rockwellcollins.atc.agree.agree.TimeInterval;
import com.rockwellcollins.atc.agree.agree.WhenHoldsStatement;
import com.rockwellcollins.atc.agree.agree.WhenOccursStatment;
import com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement;
import com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement;
import com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement;
import com.rockwellcollins.atc.agree.agree.WheneverOccursStatement;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.AgreePattern.TriggerType;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Expr;
import jkind.lustre.IntExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

public class PatternBuilder extends AgreeSwitch<AgreeStatement> {

    private final String str;
    private final EObject ref;
    private final AgreeASTBuilder builder;

    public PatternBuilder(String str, EObject ref, AgreeASTBuilder builder) {
        this.str = str;
        this.ref = ref;
        this.builder = builder;
    }

    @Override
    public AgreeStatement caseAlwaysStatement(AlwaysStatement object) {
        return new AgreeStatement(str, builder.doSwitch(object.getExpr()), ref);
    }

    @Override
    public AgreeStatement caseWheneverHoldsStatement(WheneverHoldsStatement object) {
        Expr cause = builder.doSwitch(object.getCause());
        Expr effect = builder.doSwitch(object.getEffect());
        TimeInterval effectInterval = object.getInterval();

        return new AgreePattern(str, ref, cause, effect, null, effectInterval, TriggerType.EVENT,
                TriggerType.CONDITION);
    }

    @Override
    public AgreeStatement caseWheneverImpliesStatement(WheneverImpliesStatement object) {
        Expr cause = builder.doSwitch(object.getCause());
        Expr lhs = builder.doSwitch(object.getLhs());
        Expr rhs = builder.doSwitch(object.getRhs());
        TimeInterval effectInterval = object.getInterval();

        Expr effect = new BinaryExpr(lhs, BinaryOp.IMPLIES, rhs);
        return new AgreePattern(str, ref, cause, effect, null, effectInterval, TriggerType.EVENT,
                TriggerType.CONDITION);
    }

    @Override
    public AgreeStatement caseWheneverOccursStatement(WheneverOccursStatement object) {
        Expr cause = builder.doSwitch(object.getCause());
        Expr effect = builder.doSwitch(object.getEffect());
        TimeInterval effectInterval = object.getInterval();

        return new AgreePattern(str, ref, cause, effect, null, effectInterval, TriggerType.EVENT,
                TriggerType.EVENT);
    }

    @Override
    public AgreeStatement caseWheneverBecomesTrueStatement(WheneverBecomesTrueStatement object) {
        Expr cause = builder.doSwitch(object.getCause());
        Expr effect = builder.doSwitch(object.getEffect());
        TimeInterval effectInterval = object.getInterval();

        // make the effect rising edge sensitive
        Expr preEffect = new UnaryExpr(UnaryOp.PRE, effect);
        Expr notPreEffect = new UnaryExpr(UnaryOp.NOT, preEffect);
        Expr edgeEffect = new BinaryExpr(notPreEffect, BinaryOp.AND, effect);
        effect = new BinaryExpr(effect, BinaryOp.ARROW, edgeEffect);
        return new AgreePattern(str, ref, cause, effect, null, effectInterval, TriggerType.EVENT,
                TriggerType.EVENT);
    }

    @Override
    public AgreeStatement caseWhenHoldsStatement(WhenHoldsStatement object) {
        Expr condition = builder.doSwitch(object.getCondition());
        Expr effect = builder.doSwitch(object.getEvent());
        TimeInterval conditionInterval = object.getConditionInterval();
        TimeInterval effectInterval = object.getEventInterval();

        return new AgreePattern(str, ref, condition, effect, conditionInterval, effectInterval,
                TriggerType.CONDITION, TriggerType.EVENT);
    }

    @Override
    public AgreeStatement caseWhenOccursStatment(WhenOccursStatment object) {
        Expr condition = builder.doSwitch(object.getCondition());
        Expr effect = builder.doSwitch(object.getEvent());
        Expr timesExpr = builder.doSwitch(object.getTimes());
        if (!(timesExpr instanceof IntExpr)) {
            throw new AgreeException("Expected an integer literal in 'When Occurs' pattern");
        }
        BigInteger times = ((IntExpr) timesExpr).value;
        TimeInterval interval = object.getInterval();

        return new AgreeTimesPattern(str, ref, condition, effect, interval, null, TriggerType.CONDITION,
                TriggerType.CONDITION, times, null);
    }

}
