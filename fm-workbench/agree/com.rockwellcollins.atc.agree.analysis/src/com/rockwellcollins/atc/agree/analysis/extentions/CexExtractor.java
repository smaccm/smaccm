package com.rockwellcollins.atc.agree.analysis.extentions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentImplementation;

import jkind.results.Counterexample;
import jkind.results.Property;

public interface CexExtractor {

	public void receiveCex(ComponentImplementation compImpl, Property property, EObject agreePoperty,
			Counterexample cex, Map<String, EObject> refMap);

	public String getDisplayText();

}
