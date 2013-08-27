package com.rockwellcollins.atc.resolute.analysis.handlers;

import com.rockwellcollins.atc.resolute.analysis.ProofType;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofTree;

public class ResoluteVerboseHandler extends ResoluteHandler {
    @Override
    protected String getJobName() {
        return "Resolute Verbose";
    }
    
    @Override
    protected void pruneProof(ResoluteProofTree proof) {
        proof.pruneQuants();
    }

    @Override
    protected ProofType getProofType() {
        return ProofType.VERBOSE;
    }
}
