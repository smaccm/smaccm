package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Location;

public class AgreeEquation extends Equation{
    
    public final EObject reference;

    public AgreeEquation(List<IdExpr> lhs, Expr expr, EObject reference) {
        super(Location.NULL, lhs, expr);
        this.reference = reference;
    }

    public AgreeEquation(IdExpr id, Expr expr, EObject reference) {
        super(Location.NULL, id, expr);
        this.reference = reference;
    }

}
