package com.rockwellcollins.atc.agree.analysis.ast;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.AgreeException;

import jkind.lustre.Expr;

public class AgreeStatement {
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

}
