package com.rockwellcollins.atc.agree.analysis;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;
import jkind.lustre.ExprVisitor;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.ProjectionExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

public class AgreeCycleVisitor implements ExprVisitor<Set<String>>{

    @Override
    public Set<String> visit(BinaryExpr e) {
        // TODO Auto-generated method stub
        Set<String> leftSet = e.left.accept(this);
        Set<String> rightSet = e.right.accept(this);
        
        leftSet.addAll(rightSet);
        return leftSet;
    }

    @Override
    public Set<String> visit(BoolExpr e) {
        // TODO Auto-generated method stub
        return new HashSet<>();
    }

    @Override
    public Set<String> visit(IdExpr e) {
        return new HashSet<>(Collections.singletonList(e.id));
    }

    @Override
    public Set<String> visit(IfThenElseExpr e) {
        Set<String> condSet = e.cond.accept(this);
        Set<String> thenSet = e.thenExpr.accept(this);
        Set<String> elseSet = e.elseExpr.accept(this);
        
        condSet.addAll(thenSet);
        condSet.addAll(elseSet);
        
        return condSet;
    }

    @Override
    public Set<String> visit(IntExpr e) {
        return new HashSet<>();
    }

    @Override
    public Set<String> visit(NodeCallExpr e) {
        //TODO this may generate false positives
        HashSet<String> argSet = new HashSet<>();
        for(Expr expr : e.args){
            argSet.addAll(expr.accept(this));
        }
        return argSet;
    }

    @Override
    public Set<String> visit(ProjectionExpr e) {
        throw new AgreeException("wtf mate? I didn't think we supported record type sin AGREE");
    }

    @Override
    public Set<String> visit(RealExpr e) {
        return new HashSet<>();
    }

    @Override
    public Set<String> visit(RecordExpr e) {
        throw new AgreeException("wtf mate? I didn't think we supported record type sin AGREE");
    }

    @Override
    public Set<String> visit(UnaryExpr e) {
        if(e.op.equals(UnaryOp.PRE)){
            return new HashSet<>();
        }
        return e.expr.accept(this);
    }

}
