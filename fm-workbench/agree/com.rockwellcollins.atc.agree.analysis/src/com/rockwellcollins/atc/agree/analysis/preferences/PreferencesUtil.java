package com.rockwellcollins.atc.agree.analysis.preferences;

import jkind.SolverOption;
import jkind.api.JKindApi;
import jkind.api.JRealizabilityApi;
import jkind.api.Kind2Api;
import jkind.api.Kind2WebApi;
import jkind.api.KindApi;

import org.eclipse.jface.preference.IPreferenceStore;

import com.rockwellcollins.atc.agree.analysis.Activator;

public class PreferencesUtil {
    public static KindApi getKindApi() {
        IPreferenceStore prefs = getPreferenceStore();
        String modelChecker = prefs.getString(PreferenceConstants.PREF_MODEL_CHECKER);
        String remoteUrl = prefs.getString(PreferenceConstants.PREF_REMOTE_URL);
        KindApi api = getKindApi(modelChecker, remoteUrl);
        if (prefs.getBoolean(PreferenceConstants.PREF_DEBUG)) {
        	api.setApiDebug();
        }
        return api;
    }

    private static IPreferenceStore getPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }

    public static KindApi getKindApi(String modelChecker, String remoteUrl) {
        switch (modelChecker) {
        case PreferenceConstants.MODEL_CHECKER_JKIND:
            return getJKindApi();
        case PreferenceConstants.MODEL_CHECKER_KIND2:
            return getKind2Api();
        case PreferenceConstants.MODEL_CHECKER_KIND2WEB:
            return getKind2WebApi(remoteUrl);
        default:
            throw new IllegalArgumentException("Unknown model checker setting: " + modelChecker);
        }
    }

    public static KindApi getConsistencyApi() {
        KindApi api = getKindApi();

        if (api instanceof JKindApi) {
            IPreferenceStore prefs = getPreferenceStore();
            int depth = prefs.getInt(PreferenceConstants.PREF_CONSIST_DEPTH) + 1;
            ((JKindApi) api).setN(depth);
            ((JKindApi) api).disableInvariantGeneration();
            // ((JKindApi) api).disableKInduction();
            ((JKindApi) api).setPdrMax(0);
        }
        return api;
    }

    private static JKindApi getJKindApi() {
        IPreferenceStore prefs = getPreferenceStore();
        JKindApi api = new JKindApi();

        String solverString =
                prefs.getString(PreferenceConstants.PREF_SOLVER).toUpperCase().replaceAll(" ", "");
        SolverOption solver = SolverOption.valueOf(solverString);
        api.setSolver(solver);

        if (prefs.getBoolean(PreferenceConstants.PREF_INDUCT_CEX)) {
            api.setInductiveCounterexamples();
        }
        if (prefs.getBoolean(PreferenceConstants.PREF_SMOOTH_CEX) && solver == SolverOption.YICES) {
            api.setSmoothCounterexamples();
        }
        //Anitha added this to get set of supportoption if its enabled in the preferneces
        if (prefs.getBoolean(PreferenceConstants.PREF_SUPPORT)) {
        	api.setReduceSupport();
        }
        api.setN(prefs.getInt(PreferenceConstants.PREF_DEPTH));
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
        api.setPdrMax(prefs.getInt(PreferenceConstants.PREF_PDR_MAX));
        if (prefs.getBoolean(PreferenceConstants.PREF_NO_KINDUCTION)) {
            api.disableKInduction();
        }
        return api;
    }

    public static JRealizabilityApi getJRealizabilityApi() {
        IPreferenceStore prefs = getPreferenceStore();
        JRealizabilityApi api = new JRealizabilityApi();

        api.setN(prefs.getInt(PreferenceConstants.PREF_DEPTH));
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));

        return api;
    }

    private static Kind2Api getKind2Api() {
        IPreferenceStore prefs = getPreferenceStore();
        Kind2Api api = new Kind2Api();
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
        return api;
    }

    private static Kind2WebApi getKind2WebApi(String uri) {
        IPreferenceStore prefs = getPreferenceStore();
        Kind2WebApi api = new Kind2WebApi(uri);
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
        return api;
    }
}
