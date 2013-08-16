package com.rockwellcollins.atc.agree.analysis.actions;


public class VerifyAll extends VerifyAction {
    @Override
    protected boolean isRecursive() {
       return true;
    }

    @Override
    protected String getJobName() {
        return "AGREE - Verify All Layers";
    }
}
