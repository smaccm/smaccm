package com.rockwellcollins.atc.agree.analysis.handlers;

public class VerifyRealizabilityHandler extends VerifyHandler {

    @Override
    protected boolean isRecursive() {
        return false;
    }

    @Override
    protected boolean isMonolithic() {
        return false;
    }

    @Override
    protected String getJobName() {
        return "AGREE - Verify Realizability";
    }

    @Override
    protected boolean isRealizability() {
        return true;
    }

}
