package com.rockwellcollins.atc.agree.analysis.ast.visitors;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeEquation;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

import jkind.lustre.Expr;
import jkind.lustre.visitors.ExprVisitor;

public interface AgreeAstVisitor<T> extends ExprVisitor<Expr> {
    public T visit(AgreeProgram program);
    public T visit(AgreeNode node);
    public T visit(AgreeVar var);
    public T visit(AgreeEquation eq);
    public T visit(AgreeConnection conn);

}
