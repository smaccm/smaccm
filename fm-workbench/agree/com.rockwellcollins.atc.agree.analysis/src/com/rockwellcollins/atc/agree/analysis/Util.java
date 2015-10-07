package com.rockwellcollins.atc.agree.analysis;

import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

import com.google.inject.Injector;
import com.rockwellcollins.atc.agree.ui.internal.AgreeActivator;

public class Util {
    private static GlobalURIEditorOpener globalURIEditorOpener;

    public static GlobalURIEditorOpener getGlobalURIEditorOpener() {
        if (globalURIEditorOpener == null) {
            AgreeActivator activator = AgreeActivator.getInstance();
            String language = AgreeActivator.COM_ROCKWELLCOLLINS_ATC_AGREE_AGREE;
            Injector injector = activator.getInjector(language);
            globalURIEditorOpener = injector.getInstance(GlobalURIEditorOpener.class);
        }
        return globalURIEditorOpener;
    }
}
