package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osate.aadl2.instance.ComponentInstance;

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
    private static int eventIndex;
    private static List<Node> patternLustreNodes;
    private static Map<AgreeNode, List<AgreeVar>> nodeEvents;
    
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
        
        //continue here
        

        return null;
    }

    private static AgreeStatement translatePattern(AgreeTimesPattern pattern, AgreeNodeBuilder builder) {
        throw new AgreeException("Implement this");
    }

}
