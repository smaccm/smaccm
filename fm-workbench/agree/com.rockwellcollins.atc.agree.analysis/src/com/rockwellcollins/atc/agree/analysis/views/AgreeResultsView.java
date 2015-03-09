package com.rockwellcollins.atc.agree.analysis.views;

import jkind.api.results.AnalysisResult;
import jkind.api.ui.results.AnalysisResultTree;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class AgreeResultsView extends ViewPart {
    public static final String ID = "com.rockwellcollins.atc.agree.analysis.views.agreeResultsView";

    private AnalysisResultTree tree;
    private AgreeMenuListener menuListener;

    @Override
    public void createPartControl(Composite parent) {
        tree = new AnalysisResultTree(parent);
        tree.getViewer().setAutoExpandLevel(2);

        menuListener = new AgreeMenuListener(getViewSite().getWorkbenchWindow(), tree);
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(menuListener);
        tree.getControl().setMenu(manager.createContextMenu(tree.getViewer().getTree()));
    }

    @Override
    public void setFocus() {
        tree.getControl().setFocus();
    }

    public void setInput(AnalysisResult result, AgreeResultsLinker linker) {
        tree.setInput(result);
        menuListener.setLinker(linker);
    }
}
