package com.rockwellcollins.atc.agree.analysis.extentions;

import java.util.Map;

import jkind.results.Counterexample;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentImplementation;

public interface CexExtractor {

    public void receiveCex(ComponentImplementation compImpl, EObject property, Counterexample cex,
            Map<String, EObject> refMap);

    public String getDisplayText();

}
