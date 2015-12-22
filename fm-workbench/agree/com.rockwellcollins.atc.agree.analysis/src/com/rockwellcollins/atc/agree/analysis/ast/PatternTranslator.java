package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.agree.OpenTimeInterval;
import com.rockwellcollins.atc.agree.agree.TimeInterval;
import com.rockwellcollins.atc.agree.analysis.AgreeException;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;

public class PatternTranslator {

    private static final String TIME_PREFIX = "__TIME__";
    private static final String EVENT_PREFIX = "__EVENT__";
    private static final String EFFECT_TIME_PREFIX = "__EFFECT_TIME__";
    private static final String EFFECT_TIME_RANGE_PREFIX = "__EFFECT_TIME_RANGE__";
    
    private static int eventIndex;
    private static int patternIndex;
    private static List<Node> patternLustreNodes;
    private static Map<AgreeNode, List<AgreeVar>> nodeEvents;
    private static IdExpr timeExpr = new IdExpr("time");
    
    public static AgreeProgram translate(AgreeProgram program) {
        patternLustreNodes = new ArrayList<>();
        nodeEvents = new HashMap<>();
        
        AgreeNode topNode = translateNode(program.topNode);
        List<AgreeNode> agreeNodes = gatherNodes(topNode);
        patternLustreNodes.addAll(program.globalLustreNodes);

        return new AgreeProgram(agreeNodes, patternLustreNodes, program.globalTypes, topNode);
    }

    private static AgreeNode translateNode(AgreeNode node) {
        eventIndex = 0;
        patternIndex = 0;
        AgreeNodeBuilder builder = new AgreeNodeBuilder(node);

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
    
    
    private static AgreeStatement translatePattern(AgreePattern pattern, AgreeNodeBuilder builder) {

        AgreeNode curNode = builder.build();
        ComponentInstance compInst = curNode.compInst;
        
        if (pattern instanceof AgreeTimesPattern) {
            return translatePattern((AgreeTimesPattern) pattern, builder);
        }

        AgreeVar causeVar = new AgreeVar(EVENT_PREFIX + eventIndex++, NamedType.BOOL, null, compInst);
        AgreeVar effectVar = new AgreeVar(EVENT_PREFIX + eventIndex++, NamedType.BOOL, null, compInst);
        AgreeVar timeCause = new AgreeVar(TIME_PREFIX + causeVar.id, NamedType.REAL, null, compInst);
        AgreeVar timeEffect = new AgreeVar(TIME_PREFIX + effectVar.id, NamedType.REAL, null, compInst);
        
        builder.addLocal(causeVar);
        builder.addLocal(effectVar);
        builder.addLocal(timeCause);
        builder.addLocal(timeEffect);
        
        builder.addLocalEquation(new Equation(new IdExpr(causeVar.id), pattern.cause));
        builder.addLocalEquation(new Equation(new IdExpr(effectVar.id), pattern.effect));

        // create a variable that is always within the range of when the effect
        // may occur. This is regardless of whether or not the cause has
        // actually happen
        AgreeVar causeTimeRangeVar =
                new AgreeVar(EFFECT_TIME_RANGE_PREFIX + patternIndex, NamedType.REAL, null, compInst);
        builder.addInput(causeTimeRangeVar);
        Expr causeTimeRangeId = new IdExpr(causeTimeRangeVar.id);

        Expr causeTimeRangeConstraint = getCauseTimeRangeConstraint(causeTimeRangeId, pattern.causeInterval);
        builder.addAssertion(new AgreeStatement(null, causeTimeRangeConstraint, null));

        // create a variable that will "predict" when the effect will occur by
        // looking and the prediction of when the effect will occur when the
        // cause occurs
        AgreeVar causeTimeVar =
                new AgreeVar(EFFECT_TIME_PREFIX + patternIndex, NamedType.REAL, null, compInst);
        builder.addLocal(causeTimeVar);
        Expr causeTimeId = new IdExpr(causeTimeVar.id);
        
        //START HERE: set the causeTimeId to lock in the value of causeTimeRangeId
        
        return null;
    }

    private static Expr getCauseTimeRangeConstraint(Expr causeTimeRangeId, AgreePatternInterval causeInterval) {
        Expr occurs = new BinaryExpr(timeExpr, BinaryOp.MINUS, causeTimeRangeId);
        BinaryOp left;
        BinaryOp right;
        switch (causeInterval.type) {
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
            throw new AgreeException("Unhandled IntervalType : " + causeInterval.type);
        }
        Expr lower = new BinaryExpr(causeInterval.low, left, occurs);
        Expr higher = new BinaryExpr(occurs, right, causeInterval.high);
        return new BinaryExpr(lower, BinaryOp.AND, higher);
    }

    private static AgreeStatement translatePattern(AgreeTimesPattern pattern, AgreeNodeBuilder builder) {
        throw new AgreeException("Implement this");
    }

}
