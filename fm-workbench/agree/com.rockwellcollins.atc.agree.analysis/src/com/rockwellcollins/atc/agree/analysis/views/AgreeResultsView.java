package com.rockwellcollins.atc.agree.analysis.views;

import jkind.api.results.AnalysisResult;
import jkind.api.ui.AnalysisResultTree;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

public class AgreeResultsView extends ViewPart {
    public static final String ID = "com.rockwellcollins.atc.agree.analysis.views.agreeResultsView";
    public final String TERMINATE_ID = "com.rockwellcollins.atc.agree.analysis.commands.terminate";

    private AnalysisResultTree tree;
    private AnalysisTerminateHandler terminateHandler;
    private AgreeMenuListener menuListener;

    @Override
    public void createPartControl(Composite parent) {
        tree = new AnalysisResultTree(parent);
        tree.getViewer().setAutoExpandLevel(2);

        terminateHandler = new AnalysisTerminateHandler();
        IHandlerService handlerService = (IHandlerService) getViewSite().getService(
                IHandlerService.class);
        handlerService.activateHandler(TERMINATE_ID, terminateHandler);

        menuListener = new AgreeMenuListener(tree);
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(menuListener);
        tree.getControl().setMenu(manager.createContextMenu(tree.getViewer().getTree()));
    }

    @Override
    public void setFocus() {
        tree.getControl().setFocus();
    }

    public void setInput(AnalysisResult result, IProgressMonitor monitor, AgreeResultsLinker linker) {
        tree.setInput(result);
        menuListener.setLinker(linker);
        terminateHandler.setResult(result);
        terminateHandler.setMonitor(monitor);
    }
}
