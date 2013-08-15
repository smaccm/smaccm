package com.rockwellcollins.atc.agree.analysis.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

public class AgreeConsoleHyperLink implements IHyperlink {
    private static GlobalURIEditorOpener uriEd;
    private EObject ref;

    public AgreeConsoleHyperLink(EObject o) {
        ref = o;
    }

    @Override
    public void linkEntered() {
    }

    @Override
    public void linkExited() {
    }

    @Override
    public void linkActivated() {
        uriEd.open(EcoreUtil.getURI(ref), true);

    }

    public static void setGlobalURIEditorOpener(GlobalURIEditorOpener g) {
        uriEd = g;
    }
}
