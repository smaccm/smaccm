package com.rockwellcollins.atc.agree.analysis.realtime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.agree.OpenTimeInterval;
import com.rockwellcollins.atc.agree.agree.TimeInterval;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeEquation;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNodeBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreeCauseEffectPattern.TriggerType;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePatternInterval.IntervalType;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

public class AgreePatternTranslator {

    private static final String TIME_PREFIX = "__TIME__";
    private static final String EFFECT_PREFIX = "__EVENT__";
    private static final String CAUSE_PREFIX = "__CAUSE__";
    private static final String EFFECT_TIME_RANGE_PREFIX = "__EFFECT_TIME_RANGE__";
    private static final String TIMEOUT_PREFIX = "__TIMEOUT__";
    private static final String CAUSE_CONDITION_RISE_PREFIX = "__CAUSE_CONDITION_RISE__";
    private static final String CAUSE_CONDITION_FALL_PREFIX = "__CAUSE_CONDITION_FALL__";
    private static final String CAUSE_CONDITION_HELD_PREFIX = "__CAUSE_CONDITION_HELD__";
    private static final String CAUSE_CONDITION_TIMEOUT_PREFIX = "__CAUSE_CONDITION_TIMEOUT__";
    private static final String JITTER_PREFIX = "__JITTER__";
    private static final String PERIOD_PREFIX = "__PERIOD__";
    private static final String TIMER_PREFIX = "__TIMER__";
    private static final String RUNNING_PREFIX = "__RUNNING__";
    private static final String RECORD_PREFIX = "__RECORD__";
    
    private static final Expr NEG_ONE = new RealExpr(BigDecimal.valueOf(-1));

    private static int eventIndex;
    private static int patternIndex;
    private static List<Node> patternLustreNodes;
    private static Map<AgreeNode, List<AgreeVar>> nodeEvents;
    public static final IdExpr timeExpr = new IdExpr("time");

    public static AgreeProgram translate(AgreeProgram program) {
        patternLustreNodes = new ArrayList<>();
        nodeEvents = new HashMap<>();

        AgreeNode topNode = translateNode(program.topNode, true);
        List<AgreeNode> agreeNodes = gatherNodes(topNode);
        patternLustreNodes.addAll(program.globalLustreNodes);

        return new AgreeProgram(agreeNodes, patternLustreNodes, program.globalTypes, topNode);
    }

    private static AgreeNode translateNode(AgreeNode node, boolean isTopNode) {
        eventIndex = 0;
        patternIndex = 0;
        AgreeNodeBuilder builder = new AgreeNodeBuilder(node);

        // this has to be done first because the pattern translation
        // for guarantees/lemmas/assumptions add additional assertions
        builder.clearAssertions();
        for (AgreeStatement statement : node.assertions) {
            if (statement instanceof AgreePattern) {
                Expr transExpr = translatePattern((AgreePattern) statement, builder, !isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addAssertion(statement);
        }

        builder.clearGuarantees();
        for (AgreeStatement statement : node.guarantees) {
            if (statement instanceof AgreePattern) {
                Expr transExpr = translatePattern((AgreePattern) statement, builder, isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addGuarantee(statement);
        }

        builder.clearLemmas();
        for (AgreeStatement statement : node.lemmas) {
            if (statement instanceof AgreePattern) {
                Expr transExpr = translatePattern((AgreePattern) statement, builder, isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addLemma(statement);
        }

        builder.clearAssumptions();
        for (AgreeStatement statement : node.assumptions) {
            if (statement instanceof AgreePattern) {
                Expr transExpr = translatePattern((AgreePattern) statement, builder, !isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addAssumption(statement);
        }

        builder.clearSubNodes();
        for (AgreeNode subNode : node.subNodes) {
            builder.addSubNode(translateNode(subNode, false));
        }

        builder.addInput(new AgreeVar(timeExpr.id, NamedType.REAL, null, node.compInst));
        return builder.build();
    }

    private static List<AgreeNode> gatherNodes(AgreeNode node) {
        List<AgreeNode> nodes = new ArrayList<>();

        for (AgreeNode subNode : node.subNodes) {
            nodes.addAll(gatherNodes(subNode));
            nodes.add(subNode);
        }
        return nodes;
    }
    
    private static Expr translatePattern(AgreePattern pattern, AgreeNodeBuilder builder, boolean isProperty) {
        patternIndex++;

        if(pattern instanceof AgreeCauseEffectPattern){
            return translatePattern((AgreeCauseEffectPattern)pattern, builder, isProperty);
        }else if(pattern instanceof AgreePeriodicPattern){
            return translatePattern((AgreePeriodicPattern)pattern, builder, isProperty);
        }
        throw new AgreeException("Unhandled pattern: "+pattern.getClass().toString());
    }
    
    private static Expr translatePattern(AgreePeriodicPattern pattern, AgreeNodeBuilder builder, boolean isProperty) {
        
        if(isProperty){
            throw new AgreeException("We have not translated this pattern to properties yet");
        }
        
        EObject varReference = pattern.reference;
        while(!(varReference instanceof ComponentClassifier)){
            varReference = varReference.eContainer();
        }
        
        AgreeVar jitterVar = new AgreeVar(JITTER_PREFIX+patternIndex, NamedType.REAL, varReference);
        AgreeVar periodVar = new AgreeVar(PERIOD_PREFIX+patternIndex, NamedType.REAL, varReference);
        AgreeVar timeoutVar = new AgreeVar(TIMEOUT_PREFIX+patternIndex, NamedType.REAL, varReference);
        
        builder.addOutput(jitterVar);
        builder.addLocal(periodVar);
        builder.addOutput(timeoutVar);
        
        IdExpr jitterId = new IdExpr(jitterVar.id);
        IdExpr periodId = new IdExpr(periodVar.id);
        IdExpr timeoutId = new IdExpr(timeoutVar.id);

        builder.addEventTime(timeoutVar);

        // -j <= jitter <= j
        Expr jitterLow =
                new BinaryExpr(new UnaryExpr(UnaryOp.NEGATIVE, pattern.jitter), BinaryOp.LESSEQUAL, jitterId);
        Expr jitterHigh = new BinaryExpr(jitterId, BinaryOp.LESSEQUAL, pattern.jitter);
        builder.addAssertion(new AgreeStatement(null, new BinaryExpr(jitterLow, BinaryOp.AND, jitterHigh), pattern.reference));
        
        //pnext = p -> pre pnext + if e then p else 0
        Expr ifElse = new IfThenElseExpr(pattern.event, pattern.period, new RealExpr(BigDecimal.ZERO));
        Expr prePnext = new UnaryExpr(UnaryOp.PRE, periodId);
        prePnext = new BinaryExpr(prePnext, BinaryOp.PLUS, ifElse);
        prePnext = new BinaryExpr(pattern.period, BinaryOp.ARROW, prePnext);
        builder.addLocalEquation(new AgreeEquation(periodId, prePnext, pattern.reference));
        
        //timeout = if e then pnext + jitter else 0 -> pre timeout
        Expr preTimeout = new UnaryExpr(UnaryOp.PRE, timeoutId);
        preTimeout = new BinaryExpr(new RealExpr(BigDecimal.ZERO), BinaryOp.ARROW, preTimeout);
        Expr periodPlusJit = new BinaryExpr(periodId, BinaryOp.PLUS, jitterId);
        ifElse = new IfThenElseExpr(pattern.event, periodPlusJit, preTimeout);
        builder.addAssertion(new AgreeStatement(null, new BinaryExpr(timeoutId, BinaryOp.EQUAL, ifElse), pattern.reference));
        
        //event = (t = (0 -> pre timeout))
        Expr timeEq = new BinaryExpr(timeExpr, BinaryOp.EQUAL, preTimeout);
        Expr expr = new BinaryExpr(pattern.event, BinaryOp.EQUAL, timeEq);
        
        //assert that the timeout is always greater than the current time
        builder.addAssertion(new AgreeStatement(null, new BinaryExpr(timeoutId, BinaryOp.GREATER, timeExpr), pattern.reference));
        
        return expr;
    }
    

    private static Expr translatePattern(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder, boolean isProperty) {

        if (pattern instanceof AgreeTimesPattern) {
            return translatePattern((AgreeTimesPattern) pattern, builder);
        }
        
        EObject varReference = pattern.reference;
        while(!(varReference instanceof ComponentClassifier)){
            varReference = varReference.eContainer();
        }
        

        AgreeVar causeVar = new AgreeVar(CAUSE_PREFIX + eventIndex, NamedType.BOOL, varReference);
        AgreeVar effectVar = new AgreeVar(EFFECT_PREFIX + eventIndex++, NamedType.BOOL, varReference);
        IdExpr causeId = new IdExpr(causeVar.id);
        IdExpr effectId = new IdExpr(effectVar.id);
        builder.addLocalEquation(new AgreeEquation(causeId, pattern.cause, pattern.reference));
        builder.addLocalEquation(new AgreeEquation(effectId, pattern.effect, pattern.reference));
        builder.addLocal(causeVar);
        builder.addLocal(effectVar);
        
        if(pattern.causeType == TriggerType.CONDITION){
            causeId = translateCauseCondtionPattern(pattern, causeId, builder);
        }
                
        if (isProperty) {
            return translatePatternProperty(pattern, builder, causeId, effectId);
        } else {
            addPatternConstraintProperty(pattern, builder, causeId, effectId);
            return translatePatternConstraint(pattern, builder, causeId, effectId);
        }

    }

    private static Expr translatePatternProperty(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder,
            IdExpr causeId, IdExpr effectId) {
        EObject varReference = pattern.reference;
        AgreeVar timerVar = new AgreeVar(TIMER_PREFIX+patternIndex, NamedType.REAL, varReference);
        AgreeVar runVar = new AgreeVar(RUNNING_PREFIX+patternIndex, NamedType.BOOL, varReference);
        AgreeVar recordVar = new AgreeVar(RECORD_PREFIX+patternIndex, NamedType.BOOL, varReference);
        builder.addLocal(timerVar);
        builder.addLocal(runVar);
        builder.addInput(recordVar);
        IdExpr timerId = new IdExpr(timerVar.id);
        IdExpr runId = new IdExpr(runVar.id);
        IdExpr recordId = new IdExpr(recordVar.id);
        
        //run = record -> if pre(run) and e and l <= timer <= h then
        //                   false
        //                else
        //                   if record then
        //                      true
        //                   else
        //                      pre(run)
        
        Expr preRun = new UnaryExpr(UnaryOp.PRE, runId);
        
        {
        Expr if2 = new IfThenElseExpr(recordId, new BoolExpr(true), preRun);
        BinaryOp left = getIntervalLeftOp(pattern.effectInterval);
        BinaryOp right = getIntervalRightOp(pattern.effectInterval);
        Expr timerLow = new BinaryExpr(pattern.effectInterval.low, left, timerId);
        Expr timerHigh = new BinaryExpr(timerId, right, pattern.effectInterval.high);
        Expr cond1 = new BinaryExpr(preRun, BinaryOp.AND, effectId);
        cond1 = new BinaryExpr(cond1, BinaryOp.AND, timerLow);
        cond1 = new BinaryExpr(cond1, BinaryOp.AND, timerHigh);
        Expr if1 = new IfThenElseExpr(cond1, new BoolExpr(false), if2);
        Expr runExpr = new BinaryExpr(recordId, BinaryOp.ARROW, if1);
        builder.addLocalEquation(new AgreeEquation(runId, runExpr, varReference));
        }
        
        //timer = (0 -> if pre(run) then pre(timer) + (t - pre(t)) else 0)
        {
        Expr preTimer = new UnaryExpr(UnaryOp.PRE, timerId);
        Expr preT = new UnaryExpr(UnaryOp.PRE, timeExpr);
        Expr elapsed = new BinaryExpr(timeExpr, BinaryOp.MINUS, preT);
        Expr total = new BinaryExpr(preTimer, BinaryOp.PLUS, elapsed);
        Expr timerExpr = new IfThenElseExpr(preRun, total, new RealExpr(BigDecimal.ZERO));
        timerExpr = new BinaryExpr(new RealExpr(BigDecimal.ZERO), BinaryOp.ARROW, timerExpr);
        builder.addLocalEquation(new AgreeEquation(timerId, timerExpr, varReference));
        }
        
        //record => cause and not (e and (l = 0))
        
        {
        Expr causeExpr;
        if (pattern.effectInterval.type == IntervalType.OPEN_LEFT
                || pattern.effectInterval.type == IntervalType.OPEN) {
            causeExpr = causeId;
        } else {
            Expr eAndLZero =
                    new BinaryExpr(pattern.effectInterval.low, BinaryOp.EQUAL, new RealExpr(BigDecimal.ZERO));
            eAndLZero = new BinaryExpr(effectId, BinaryOp.AND, eAndLZero);
            Expr notEAndLZero = new UnaryExpr(UnaryOp.NOT, eAndLZero);
            causeExpr = new BinaryExpr(causeId, BinaryOp.AND, notEAndLZero);
        }
        Expr recordExpr = new BinaryExpr(recordId, BinaryOp.IMPLIES, causeExpr);
        AgreeStatement statement = new AgreeStatement("", recordExpr, varReference);
        builder.addAssertion(statement);
        }
        //timer <= h
        BinaryOp right = getIntervalRightOp(pattern.effectInterval);
        return new BinaryExpr(timerId, right, pattern.effectInterval.high);
        
    }
    
    private static void addPatternConstraintProperty(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder,
            IdExpr causeId, IdExpr effectId) {
        AgreeVar timeEffect = new AgreeVar("__OCURRED__"+TIME_PREFIX + effectId.id, NamedType.REAL, pattern);
        AgreeVar timeCause = new AgreeVar("__OCURRED__"+TIME_PREFIX + causeId.id, NamedType.REAL, pattern);
        builder.addLocal(timeEffect);
        builder.addLocal(timeCause);
        
        IdExpr timeEffectId = new IdExpr(timeEffect.id);
        IdExpr timeCauseId = new IdExpr(timeCause.id);
        
        {
        Expr preTimeEffect = new UnaryExpr(UnaryOp.PRE, timeEffectId);
        preTimeEffect = new BinaryExpr(NEG_ONE, BinaryOp.ARROW, preTimeEffect);
        Expr timeEffectExpr = new IfThenElseExpr(effectId, timeExpr, preTimeEffect);
        builder.addLocalEquation(new AgreeEquation(timeEffectId, timeEffectExpr, pattern));
        }
        {
        Expr preTimeCause = new UnaryExpr(UnaryOp.PRE, timeCauseId);
        preTimeCause = new BinaryExpr(NEG_ONE, BinaryOp.ARROW, preTimeCause);
        Expr timeCauseExpr = new IfThenElseExpr(causeId, timeExpr, preTimeCause);
            builder.addLocalEquation(new AgreeEquation(timeCauseId, timeCauseExpr, pattern));
        }

        Expr preTimeCause = new UnaryExpr(UnaryOp.PRE, timeCauseId);
        Expr newCause = new BinaryExpr(preTimeCause, BinaryOp.NOTEQUAL, timeCauseId);
        newCause = new BinaryExpr(newCause, BinaryOp.AND,
                new BinaryExpr(preTimeCause, BinaryOp.GREATEREQUAL, new RealExpr(BigDecimal.ZERO)));
        Expr preTimeCausePlusL = new BinaryExpr(preTimeCause, BinaryOp.PLUS, pattern.effectInterval.low);
        BinaryOp left = getIntervalLeftOp(pattern.effectInterval);
        Expr inInterval = new BinaryExpr(preTimeCausePlusL, left, timeEffectId);
        Expr propExpr = new BinaryExpr(newCause, BinaryOp.IMPLIES, inInterval);
        propExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, propExpr);
        AgreeStatement statement = new AgreeStatement(" pattern "+patternIndex+" in bounds", propExpr, pattern);
        builder.addPatternProp(statement);
        
    }

    private static Expr translatePatternConstraint(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder,
            IdExpr causeId, IdExpr effectId) {
        AgreeVar timeEffect = new AgreeVar(TIME_PREFIX + effectId.id, NamedType.REAL, pattern);
        AgreeVar effectTimeRangeVar =
                new AgreeVar(EFFECT_TIME_RANGE_PREFIX + patternIndex, NamedType.REAL, pattern);
        builder.addInput(timeEffect);
        builder.addInput(effectTimeRangeVar);

        IdExpr timeEffectId = new IdExpr(timeEffect.id);
        IdExpr effectTimeRangeId = new IdExpr(effectTimeRangeVar.id);

        Expr effectTimeRangeConstraint = getTimeRangeConstraint(effectTimeRangeId, pattern.effectInterval);
        builder.addAssertion(new AgreeStatement(null, effectTimeRangeConstraint, pattern.reference));
        // make a constraint that triggers when the event WILL happen
        builder.addAssertion(
                getTimeConstrStatement(timeEffectId, causeId, effectTimeRangeId, pattern.reference));
        // register the event time
        builder.addEventTime(timeEffect);
        // make the equation that triggers the event at the correct ime
        Expr timeEqualsPreTime = new BinaryExpr(timeExpr, BinaryOp.EQUAL, timeEffectId);
        // if the event is exclusive it only occurs when scheduled
        BinaryOp effectOp = pattern.effectIsExclusive ? BinaryOp.EQUAL : BinaryOp.IMPLIES;
        Expr impliesEffect = new BinaryExpr(timeEqualsPreTime, effectOp, effectId);
        return impliesEffect;
    }
    
    // this method registers a timeout and creates an event that is true iff the condition
    // holds during the given interval. This is meant to essentially translate a condition
    // pattern to a purely event based pattern. it returns an IdExpr corresponding to the
    // event that triggers when the condition is held for the interval
    private static IdExpr translateCauseCondtionPattern(AgreeCauseEffectPattern pattern, IdExpr causeId,
            AgreeNodeBuilder builder) {
        
        AgreeVar causeRiseTimeVar = new AgreeVar(CAUSE_CONDITION_RISE_PREFIX + causeId.id, NamedType.REAL, pattern);
        AgreeVar causeFallTimeVar = new AgreeVar(CAUSE_CONDITION_FALL_PREFIX + causeId.id, NamedType.REAL, pattern);
        AgreeVar causeHeldVar = new AgreeVar(CAUSE_CONDITION_HELD_PREFIX + causeId.id, NamedType.BOOL, pattern);
        AgreeVar causeHeldTimeoutVar = new AgreeVar(CAUSE_CONDITION_TIMEOUT_PREFIX + causeId.id, NamedType.REAL, pattern);
        
        builder.addLocal(causeRiseTimeVar);
        builder.addLocal(causeFallTimeVar);
        builder.addLocal(causeHeldVar);
        builder.addLocal(causeHeldTimeoutVar);
        
        IdExpr causeFallTimeId = new IdExpr(causeFallTimeVar.id);
        IdExpr causeHeldId = new IdExpr(causeHeldVar.id);
        IdExpr causeRiseTimeId = new IdExpr(causeRiseTimeVar.id);
        IdExpr causeHeldTimeoutId = new IdExpr(causeHeldTimeoutVar.id);
        
        {
        //causeRiseTime = if rise(cause) then t else -1 -> pre(causeRiseTime)
        Expr elseRise = new UnaryExpr(UnaryOp.PRE, causeRiseTimeId);
        elseRise = new BinaryExpr(NEG_ONE, BinaryOp.ARROW, elseRise);
        Expr rise = new NodeCallExpr(AgreeRealtimeCalendarBuilder.RISE_NODE_NAME, causeId);
        Expr ifRise = new IfThenElseExpr(rise, timeExpr, elseRise);
        builder.addLocalEquation(new AgreeEquation(causeRiseTimeId, ifRise, pattern));
        }
        
        {
        //causeFallTime = if fall(cause) then t else -1 -> pre(causeFallTime)
        Expr elseFall = new UnaryExpr(UnaryOp.PRE, causeFallTimeId);
        elseFall = new BinaryExpr(NEG_ONE, BinaryOp.ARROW, elseFall);
        Expr fall = new NodeCallExpr(AgreeRealtimeCalendarBuilder.FALL_NODE_NAME, causeId);
        Expr ifFall = new IfThenElseExpr(fall, timeExpr, elseFall);
        builder.addLocalEquation(new AgreeEquation(causeFallTimeId, ifFall, pattern));
        }
        
        {
        //timeout = if causeRiseTime > -1 and causeRiseTime > causeFallTime then 
        //             causeRiseTime + h
        //          else
        //             -1
        Expr posRise = new BinaryExpr(causeRiseTimeId, BinaryOp.GREATER, NEG_ONE);
        Expr gtFall = new BinaryExpr(causeRiseTimeId, BinaryOp.GREATER, causeFallTimeId);
        Expr cond = new BinaryExpr(posRise, BinaryOp.AND, gtFall);
        
        Expr heldTime = new BinaryExpr(causeRiseTimeId, BinaryOp.PLUS, pattern.causeInterval.high);
        Expr ifExpr = new IfThenElseExpr(cond, heldTime, NEG_ONE);
        builder.addLocalEquation(new AgreeEquation(causeHeldTimeoutId, ifExpr, pattern));
        builder.addEventTime(causeHeldTimeoutVar);
        }
        
        {
        //causeHeld = (t = causeHeldTimeout)
        Expr causeHeldExpr = new BinaryExpr(timeExpr, BinaryOp.EQUAL, causeHeldTimeoutId);
        builder.addLocalEquation(new AgreeEquation(causeHeldId, causeHeldExpr, pattern));
        }
        
        return causeHeldId;
    }

    private static AgreeStatement getTimeConstrStatement(IdExpr timeId, Expr setCond, Expr setVal, EObject reference) {
        return new AgreeStatement("", getTimeConstrExpr(timeId, setCond, setVal, reference), reference);
    }

    private static Expr getTimeConstrExpr(IdExpr timeId, Expr setCond, Expr setVal, EObject reference) {
        Equation eq = getTimeConstrEq(timeId, setCond, setVal, reference);
        return new BinaryExpr(timeId, BinaryOp.EQUAL, eq.expr);
    }

    private static AgreeEquation getTimeConstrEq(IdExpr timeId, Expr setCond, Expr setVal, EObject reference ) {
        Expr preTime = new UnaryExpr(UnaryOp.PRE, timeId);
        Expr elseTime = new BinaryExpr(new RealExpr(BigDecimal.valueOf(-1.0)), BinaryOp.ARROW, preTime);
        Expr timeConstr = new IfThenElseExpr(setCond, setVal, elseTime);
        return new AgreeEquation(timeId, timeConstr, reference);
    }

    private static BinaryOp getIntervalLeftOp(AgreePatternInterval interval) {
        switch (interval.type) {
        case CLOSED:
            return BinaryOp.LESSEQUAL;
        case OPEN:
            return BinaryOp.LESS;
        case OPEN_LEFT:
            return BinaryOp.LESS;
        case OPEN_RIGHT:
            return BinaryOp.LESSEQUAL;
        default:
            throw new AgreeException("Unhandled IntervalType : " + interval.type);
        }
    }

    private static BinaryOp getIntervalRightOp(AgreePatternInterval interval) {
        switch (interval.type) {
        case CLOSED:
            return BinaryOp.LESSEQUAL;
        case OPEN:
            return BinaryOp.LESS;
        case OPEN_LEFT:
            return BinaryOp.LESSEQUAL;
        case OPEN_RIGHT:
            return BinaryOp.LESS;
        default:
            throw new AgreeException("Unhandled IntervalType : " + interval.type);
        }
    }

    private static Expr getTimeRangeConstraint(Expr timeRangeId, AgreePatternInterval interval) {
        Expr occurs = new BinaryExpr(timeRangeId, BinaryOp.MINUS, timeExpr);
        BinaryOp left = getIntervalLeftOp(interval);
        BinaryOp right = getIntervalRightOp(interval);

        Expr lower = new BinaryExpr(interval.low, left, occurs);
        Expr higher = new BinaryExpr(occurs, right, interval.high);
        return new BinaryExpr(lower, BinaryOp.AND, higher);
    }

    private static Expr translatePattern(AgreeTimesPattern pattern, AgreeNodeBuilder builder) {
        throw new AgreeException("Implement this");
    }

}
