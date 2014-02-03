package com.rockwellcollins.atc.resolute.analysis.results;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class ClaimResult extends ResoluteResult {
    final private String text;
    final private Map<String, EObject> references;
    final private EObject location;

    public ClaimResult(String text, ResoluteResult body, Map<String, EObject> references,
            EObject location) {
        super(body);
        this.text = text;
        this.references = references;
        this.location = location;
    }
    
    // Used by FailResult
    protected ClaimResult(String text, EObject location) {
        super(false);
        this.text = text;
        this.references = Collections.emptyMap();
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public Map<String, EObject> getReferences() {
        return references;
    }
    
    public EObject getLocation() {
        return location;
    }
}
