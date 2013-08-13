package com.rockwellcollins.atc.resolute.analysis.actions;

import com.rockwellcollins.atc.resolute.analysis.ProofType;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofTree;


public class ResoluteClaims extends ResoluteAction {
	@Override
	protected void pruneProof(ResoluteProofTree proof) {
		proof.pruneFalseClaims();
	}

	@Override
	protected ProofType getProofType() {
		return ProofType.CLAIMS_ONLY;
	}
}
