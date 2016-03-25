package com.rockwellcollins.atc.agree.analysis.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

import com.rockwellcollins.atc.agree.analysis.AgreeUtils;

public class AgreeConsoleHyperLink implements IHyperlink {
    private static GlobalURIEditorOpener globalURIEditorOpener = AgreeUtils.getGlobalURIEditorOpener();
    private EObject ref;

    public AgreeConsoleHyperLink(EObject ref) {
        this.ref = ref;
    }

    @Override
    public void linkEntered() {
    }

    @Override
    public void linkExited() {
    }

    @Override
    public void linkActivated() {
        globalURIEditorOpener.open(EcoreUtil.getURI(ref), true);
    }
}
