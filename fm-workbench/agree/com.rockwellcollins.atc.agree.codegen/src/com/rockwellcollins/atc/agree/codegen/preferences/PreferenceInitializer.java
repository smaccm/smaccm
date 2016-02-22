package com.rockwellcollins.atc.agree.codegen.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.rockwellcollins.atc.agree.codegen.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.PREF_INT, PreferenceConstants.INT_INT32);
        store.setDefault(PreferenceConstants.PREF_REAL, PreferenceConstants.REAL_DOUBLE);
    }
}