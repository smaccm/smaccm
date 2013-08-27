package com.rockwellcollins.atc.resolute.analysis.handlers;

import com.rockwellcollins.atc.resolute.analysis.ProofType;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofTree;

public class ResoluteClaimsHandler extends ResoluteHandler {
    @Override
    protected String getJobName() {
        return "Resolute Claims";
    }
    
    @Override
    protected void pruneProof(ResoluteProofTree proof) {
        proof.pruneFalseClaims();
    }

    @Override
    protected ProofType getProofType() {
        return ProofType.CLAIMS_ONLY;
    }
}
