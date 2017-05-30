package com.rockwellcollins.atc.tcg.handlers;

public class VerifyAllHandler extends VerifyHandler {
    @Override
    public boolean isRecursive() {
        return true;
    }

    @Override
    protected String getJobName() {
        return "AGREE - Verify All Layers";
    }

}
