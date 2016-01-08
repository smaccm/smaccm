package com.rockwellcollins.atc.agree.analysis.realtime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.agree.OpenTimeInterval;
import com.rockwellcollins.atc.agree.agree.TimeInterval;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNodeBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.RealExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

public class AgreePatternTranslator {

    private static final String TIME_PREFIX = "__TIME__";
    private static final String EVENT_PREFIX = "__EVENT__";
    private static final String EFFECT_TIME_PREFIX = "__EFFECT_TIME__";
    private static final String EFFECT_TIME_RANGE_PREFIX = "__EFFECT_TIME_RANGE__";
    private static final String EFFECT_TIME_INTERVAL_END = "__EFFECT_TIME_INTERVAL_END__";
    
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
        
        builder.clearGuarantees();
        for (AgreeStatement statement : node.guarantees){
            if(statement instanceof AgreePattern){
                Expr transExpr = translatePattern((AgreePattern) statement, builder, isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addGuarantee(statement);
        }
        
        builder.clearLemmas();
        for (AgreeStatement statement : node.lemmas){
            if(statement instanceof AgreePattern){
                Expr transExpr = translatePattern((AgreePattern) statement, builder, isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addLemma(statement);
        }
        
        builder.clearAssumptions();
        for (AgreeStatement statement : node.assumptions){
            if(statement instanceof AgreePattern){
                Expr transExpr = translatePattern((AgreePattern) statement, builder, !isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addAssumption(statement);
        }
        
        builder.clearAssertions();
        for (AgreeStatement statement : node.assertions){
            if(statement instanceof AgreePattern){
                Expr transExpr = translatePattern((AgreePattern) statement, builder, !isTopNode);
                statement = new AgreeStatement(statement.string, transExpr, statement.reference);
            }
            builder.addAssertion(statement);
        }
        
        builder.clearSubNodes();
        for(AgreeNode subNode : node.subNodes){
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
        AgreeNode curNode = builder.build();
        ComponentInstance compInst = curNode.compInst;
        
        if (pattern instanceof AgreeTimesPattern) {
            return translatePattern((AgreeTimesPattern) pattern, builder);
        }

        AgreeVar causeVar = new AgreeVar(EVENT_PREFIX + eventIndex++, NamedType.BOOL, null, compInst);
        AgreeVar effectVar = new AgreeVar(EVENT_PREFIX + eventIndex++, NamedType.BOOL, null, compInst);
        AgreeVar timeCause = new AgreeVar(TIME_PREFIX + causeVar.id, NamedType.REAL, null, compInst);
        AgreeVar timeEffect = new AgreeVar(TIME_PREFIX + effectVar.id, NamedType.REAL, null, compInst);
        AgreeVar effectTimeRangeVar =
                new AgreeVar(EFFECT_TIME_RANGE_PREFIX + patternIndex, NamedType.REAL, null, compInst);
        AgreeVar effectTimeIntervalEndVar =
                new AgreeVar(EFFECT_TIME_INTERVAL_END + patternIndex, NamedType.REAL, null, compInst);

        IdExpr causeId = new IdExpr(causeVar.id);
        IdExpr effectId = new IdExpr(effectVar.id);
        IdExpr timeCauseId = new IdExpr(timeCause.id);
        IdExpr effectTimeRangeId = new IdExpr(effectTimeRangeVar.id);
        IdExpr timeEffectId = new IdExpr(timeEffect.id);
        IdExpr effectTimeIntervalEndId = new IdExpr(effectTimeIntervalEndVar.id);
        
        builder.addLocal(causeVar);
        builder.addLocal(effectVar);
        builder.addOutput(timeEffect);

        builder.addLocalEquation(new Equation(causeId, pattern.cause));
        builder.addLocalEquation(new Equation(effectId, pattern.effect));

        if(isProperty){
            builder.addLocal(timeCause);
            builder.addLocalEquation(getTimeConstrEq(timeCauseId, causeId, timeExpr));
            builder.addLocalEquation(getTimeConstrEq(timeEffectId, effectId, timeExpr));
            builder.addOutput(effectTimeIntervalEndVar);
            Expr tendVal = new BinaryExpr(timeExpr, BinaryOp.PLUS, pattern.effectInterval.high);
            builder.addLocalEquation(getTimeConstrEq(effectTimeIntervalEndId, causeId, tendVal));
            builder.addEventTime(effectTimeIntervalEndId);
            
            Expr effectInterval = getTimeRangeConstraint(timeEffectId, pattern.effectInterval);
            Expr preIntervalEnd = new UnaryExpr(UnaryOp.PRE, effectTimeIntervalEndId);
            Expr timeIsIntervalEnd = new BinaryExpr(timeExpr, BinaryOp.EQUAL, preIntervalEnd);
            Expr timeIsEndImpliesEffect = new BinaryExpr(timeIsIntervalEnd, BinaryOp.IMPLIES, effectInterval);
            return timeIsEndImpliesEffect;
        }else{
            builder.addInput(effectTimeRangeVar);
            Expr effectTimeRangeConstraint = getTimeRangeConstraint(effectTimeRangeId, pattern.effectInterval);
            builder.addAssertion(new AgreeStatement(null, effectTimeRangeConstraint, null));
            //make a constraint that triggers when the event WILL happen
            builder.addLocalEquation(getTimeConstrEq(timeEffectId, causeId, effectTimeRangeId));
            //register the event time
            builder.addEventTime(timeEffectId);
            //make the equation that triggers the event at the correct ime
            Expr preEffectTime = new UnaryExpr(UnaryOp.PRE, timeEffectId);
            Expr timeEqualsPreTime = new BinaryExpr(timeExpr, BinaryOp.EQUAL, preEffectTime);
            Expr impliesEffect = new BinaryExpr(timeEqualsPreTime, BinaryOp.IMPLIES, effectId);
            return impliesEffect;
        }
        
    }

    
    private static Equation getTimeConstrEq(IdExpr timeId, Expr setCond, Expr setVal){
        Expr preTime = new UnaryExpr(UnaryOp.PRE, timeId);
        Expr elseTime = new BinaryExpr(new RealExpr(BigDecimal.valueOf(-1.0)), BinaryOp.ARROW, preTime);
        Expr TimeExpr = new IfThenElseExpr(setCond, setVal, elseTime);
        return new Equation(timeId, timeExpr);
    }
    
    private static Expr getTimeRangeConstraint(Expr timeRangeId, AgreePatternInterval interval) {
        Expr occurs = new BinaryExpr(timeExpr, BinaryOp.MINUS, timeRangeId);
        BinaryOp left;
        BinaryOp right;
        switch (interval.type) {
        case CLOSED:
            left = BinaryOp.LESSEQUAL;
            right = BinaryOp.LESSEQUAL;
            break;
        case OPEN:
            left = BinaryOp.LESS;
            right = BinaryOp.LESS;
            break;
        case OPEN_LEFT:
            left = BinaryOp.LESS;
            right = BinaryOp.LESSEQUAL;
            break;
        case OPEN_RIGHT:
            left = BinaryOp.LESSEQUAL;
            right = BinaryOp.LESS;
            break;
        default:
            throw new AgreeException("Unhandled IntervalType : " + interval.type);
        }
        Expr lower = new BinaryExpr(interval.low, left, occurs);
        Expr higher = new BinaryExpr(occurs, right, interval.high);
        return new BinaryExpr(lower, BinaryOp.AND, higher);
    }

    private static Expr translatePattern(AgreeTimesPattern pattern, AgreeNodeBuilder builder) {
        throw new AgreeException("Implement this");
    }

}
