package com.rockwellcollins.atc.agree.analysis.handlers;

public class VerifyAllHandler extends VerifyHandler {
	@Override
	protected boolean isRecursive() {
		return true;
	}

	@Override
	protected String getJobName() {
		return "AGREE - Verify All Layers";
	}

	@Override
	protected boolean isMonolithic() {
		return false;
	}

	@Override
	protected boolean isRealizability() {
		return false;
	}

}
