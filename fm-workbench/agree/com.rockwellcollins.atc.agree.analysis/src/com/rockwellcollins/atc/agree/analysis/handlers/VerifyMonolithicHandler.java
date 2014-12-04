package com.rockwellcollins.atc.agree.analysis.handlers;

public class VerifyMonolithicHandler extends VerifyHandler {

	@Override
	protected boolean isRecursive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getJobName() {
		return "AGREE - Verify Monolithically";
	}

	@Override
	protected boolean isMonolithic() {
		return true;
	}

}
