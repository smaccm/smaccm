package com.rockwellcollins.atc.agree.analysis.handlers;

public class VerifyMonolithicHandler extends VerifyHandler {

    @Override
    protected boolean isRecursive() {
        return false;
    }

    @Override
    protected String getJobName() {
        return "AGREE - Verify Monolithically";
    }

    @Override
    public boolean isMonolithic() {
        return true;
    }

    @Override
    protected boolean isRealizability() {
        return false;
    }

}
