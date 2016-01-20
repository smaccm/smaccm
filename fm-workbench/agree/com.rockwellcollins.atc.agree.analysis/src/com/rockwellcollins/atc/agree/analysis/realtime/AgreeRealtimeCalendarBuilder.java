package com.rockwellcollins.atc.agree.analysis.realtime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.mwe.core.container.IfComponent;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;

public class AgreeRealtimeCalendarBuilder {

    private static final String MIN_POS_NODE_NAME = "__MinPos";
    public static final String OCCURRED_IN_PAST_NODE_NAME = "__OccuredInPast";
    
    public static List<Node> getRealTimeNodes(){
        List<Node> nodes = new ArrayList<>();
        nodes.add(getMinPosNode());
        nodes.add(getOccuredInPastNode());
        return nodes;
    }
    
    private static Node getOccuredInPastNode() {
        NodeBuilder builder = new NodeBuilder(OCCURRED_IN_PAST_NODE_NAME);
        builder.addInput(new VarDecl("input", NamedType.BOOL));
        builder.addOutput(new VarDecl("output", NamedType.BOOL));
        
        IdExpr inputId = new IdExpr("input");
        IdExpr outputId = new IdExpr("output");
        
        Expr preOutput = new UnaryExpr(UnaryOp.PRE, outputId);
        Expr outputExpr = new BinaryExpr(preOutput, BinaryOp.OR, inputId);
        outputExpr = new BinaryExpr(inputId, BinaryOp.ARROW, outputExpr);
        builder.addEquation(new Equation(outputId, outputExpr));
        
        return builder.build();
    }

    private static Node getMinPosNode(){
        NodeBuilder builder = new NodeBuilder(MIN_POS_NODE_NAME);
        IdExpr a = new IdExpr("a");
        IdExpr b = new IdExpr("b");
        IdExpr ret = new IdExpr("ret");
        builder.addInput(new VarDecl(a.id, NamedType.REAL));
        builder.addInput(new VarDecl(b.id, NamedType.REAL));
        builder.addOutput(new VarDecl(ret.id, NamedType.REAL));
        
        Expr aLessB = new BinaryExpr(a, BinaryOp.LESSEQUAL, b);
        Expr bNeg = new BinaryExpr(b, BinaryOp.LESSEQUAL, new RealExpr(BigDecimal.ZERO));
        Expr aNeg = new BinaryExpr(a, BinaryOp.LESSEQUAL, new RealExpr(BigDecimal.ZERO));
        Expr ifALessB = new IfThenElseExpr(aLessB, a, b);
        Expr ifBNeg = new IfThenElseExpr(bNeg, a, ifALessB);
        Expr ifANeg = new IfThenElseExpr(aNeg, b, ifBNeg);
        
        builder.addEquation(new Equation(ret, ifANeg));
        return builder.build();
    }
    
    public static Expr getTimeConstraint(Set<IdExpr> events){
        
        IdExpr timeId = AgreePatternTranslator.timeExpr;
        Expr nodeCall = new RealExpr(BigDecimal.valueOf(-1.0));
        for(IdExpr event : events){
            nodeCall = new NodeCallExpr(MIN_POS_NODE_NAME, new BinaryExpr(event, BinaryOp.MINUS, timeId), nodeCall);
        }
        
        nodeCall = new BinaryExpr(timeId, BinaryOp.PLUS, nodeCall);
        nodeCall = new UnaryExpr(UnaryOp.PRE, nodeCall);
        Expr timeExpr = new BinaryExpr(timeId, BinaryOp.EQUAL, nodeCall);
        timeExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, timeExpr);
        Expr timeInitZero = new BinaryExpr(timeId, BinaryOp.EQUAL, new RealExpr(BigDecimal.ZERO));
        timeInitZero = new BinaryExpr(timeInitZero, BinaryOp.ARROW, new BoolExpr(true));
        return new BinaryExpr(timeInitZero, BinaryOp.AND, timeExpr);
    }
    
}
