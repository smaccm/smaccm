package com.rockwellcollins.atc.agree.analysis.extentions;

import java.util.Map;

import jkind.results.Counterexample;

import org.eclipse.emf.ecore.EObject;

public interface CexExtractor {
	
	public void receiveCex(EObject property, Counterexample cex, Map<String, EObject> refMap);
	public String getDisplayText();
	
	
}
