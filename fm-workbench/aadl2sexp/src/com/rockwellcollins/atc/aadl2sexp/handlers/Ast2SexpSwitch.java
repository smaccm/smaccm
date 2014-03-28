package com.rockwellcollins.atc.aadl2sexp.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.util.Aadl2Switch;

public class Ast2SexpSwitch extends Aadl2Switch<Sexp> {
    public static Sexp convert(EObject e) {
        return new Ast2SexpSwitch().doSwitch(e);
    }
    
    @Override
    public Sexp caseNamedElement(NamedElement e) {
        List<Sexp> args = new ArrayList<>();

        args.add(new Symbol(e.getName()));
        
        for (EObject cross : e.eCrossReferences()) {
            if (cross instanceof NamedElement) {
                NamedElement namedCross = (NamedElement) cross;
                args.add(new Symbol(namedCross.getName()));
            } else {
                args.add(new Symbol("UNNAMED_CROSS_REFERENCE"));
            }
        }
        
        for (EObject sub : e.eContents()) {
            args.add(doSwitch(sub));
        }

        return new Cons(e.eClass().getName(), args);
    }
    
    @Override
    public Sexp defaultCase(EObject e) {
        return new Symbol("UNNAMED_OBJECT");
    }
}
