package com.rockwellcollins.atc.agree.analysis.actions;


public class VerifySingle extends VerifyAction {
    @Override
    protected boolean isRecursive() {
       return false;
    }

    @Override
    protected String getJobName() {
        return "AGREE - Verify Single Layer";
    }
}
