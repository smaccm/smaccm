package com.rockwellcollins.atc.agree.analysis.ast;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTVisitor;

import jkind.lustre.Expr;

public class AgreeStatement implements AgreeASTElement {
    public final String string;
    public final Expr expr;
    public final EObject reference;

    public AgreeStatement(String string, Expr expr, EObject reference) {
        this.string = string;
        this.expr = expr;
        this.reference = reference;
        if (expr == null) {
            throw new AgreeException("AgreeStatement created with null expression");
        }
    }

    @Override
    public <T> T accept(AgreeASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
