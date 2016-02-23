package com.rockwellcollins.atc.agree.analysis.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.rockwellcollins.atc.agree.analysis.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.PREF_MODEL_CHECKER, PreferenceConstants.MODEL_CHECKER_JKIND);
        store.setDefault(PreferenceConstants.PREF_SOLVER, PreferenceConstants.SOLVER_YICES);
        store.setDefault(PreferenceConstants.PREF_INDUCT_CEX, true);
        store.setDefault(PreferenceConstants.PREF_SMOOTH_CEX, false);
        store.setDefault(PreferenceConstants.PREF_DEPTH, 200);
        store.setDefault(PreferenceConstants.PREF_TIMEOUT, 100);
        store.setDefault(PreferenceConstants.PREF_CONSIST_DEPTH, 1);
        store.setDefault(PreferenceConstants.PREF_DEBUG, false);
    }
}
