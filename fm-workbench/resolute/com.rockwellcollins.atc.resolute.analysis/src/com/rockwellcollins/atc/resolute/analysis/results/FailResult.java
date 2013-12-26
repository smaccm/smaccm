package com.rockwellcollins.atc.resolute.analysis.results;

import org.eclipse.emf.ecore.EObject;

public class FailResult extends ClaimResult {
    public FailResult(String text, EObject location) {
        super(text, location);
    }
}
