package com.rockwellcollins.atc.resolute.analysis.actions;

import com.rockwellcollins.atc.resolute.analysis.ProofType;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofTree;

public class ResoluteVerbose extends ResoluteAction {
    @Override
    protected void pruneProof(ResoluteProofTree proof) {
        proof.pruneQuants();
    }

    @Override
    protected ProofType getProofType() {
        return ProofType.VERBOSE;
    }
}
