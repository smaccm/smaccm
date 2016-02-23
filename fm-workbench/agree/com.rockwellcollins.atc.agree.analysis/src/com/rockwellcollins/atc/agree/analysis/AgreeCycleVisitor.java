package com.rockwellcollins.atc.agree.analysis;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordUpdateExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

public class AgreeCycleVisitor implements jkind.lustre.visitors.ExprVisitor<Set<String>> {

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
        // TODO this may generate false positives
        HashSet<String> argSet = new HashSet<>();
        for (Expr expr : e.args) {
            argSet.addAll(expr.accept(this));
        }
        return argSet;
    }

    @Override
    public Set<String> visit(RecordAccessExpr e) {
        Set<String> visits = e.record.accept(this);

        Set<String> returnSet = new HashSet<>();
        for (String str : visits) {
            returnSet.add(str + "." + e.field);
        }

        return returnSet;
    }

    @Override
    public Set<String> visit(RealExpr e) {
        return new HashSet<>();
    }

    @Override
    public Set<String> visit(RecordExpr e) {
        Set<String> visits = new HashSet<>();

        for (Entry<String, Expr> field : e.fields.entrySet()) {
            visits.addAll(field.getValue().accept(this));
        }
        return visits;
    }

    @Override
    public Set<String> visit(UnaryExpr e) {
        if (e.op.equals(UnaryOp.PRE)) {
            return new HashSet<>();
        }
        return e.expr.accept(this);
    }

    @Override
    public Set<String> visit(CondactExpr e) {
        Set<String> argSet = new HashSet<>();

        for (Expr argExpr : e.args) {
            argSet.addAll(argExpr.accept(this));
        }
        argSet.addAll(e.call.accept(this));

        return argSet;
    }

    @Override
    public Set<String> visit(ArrayAccessExpr e) {
        throw new AgreeException("Array Access Expressions are not handled in the cycle visitor");
    }

    @Override
    public Set<String> visit(ArrayExpr e) {
        throw new AgreeException("Array Expressions are not handled in the cycle visitor");

    }

    @Override
    public Set<String> visit(ArrayUpdateExpr e) {
        throw new AgreeException("Array Update Expressiosn are not handled in the cycle visitor");
    }

    @Override
    public Set<String> visit(CastExpr e) {
        return e.expr.accept(this);
    }

    @Override
    public Set<String> visit(RecordUpdateExpr e) {
        Set<String> visits = new HashSet<>();
        visits.addAll(e.record.accept(this));
        visits.addAll(e.value.accept(this));

        return visits;
    }

    @Override
    public Set<String> visit(TupleExpr e) {
        throw new AgreeException("Type Expressions are not handled in the cycle visitor");

    }

}
