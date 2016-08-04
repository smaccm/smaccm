package com.rockwellcollins.atc.agree.analysis.handlers;

import org.eclipse.jface.preference.IPreferenceStore;

import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;

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
        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
        String solver = prefs.getString(PreferenceConstants.PREF_SOLVER);
        switch(solver){
        case PreferenceConstants.SOLVER_Z3:
            return true;
        default:
            throw new AgreeException("You must select Z3 as your solver to check realizability.");
        }
    }

}
