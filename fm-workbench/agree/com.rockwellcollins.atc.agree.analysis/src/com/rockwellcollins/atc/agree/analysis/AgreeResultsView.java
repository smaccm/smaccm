package com.rockwellcollins.atc.agree.analysis;

import jkind.api.results.AnalysisResult;
import jkind.api.ui.AnalysisResultTree;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

public class AgreeResultsView extends ViewPart {
    public static final String ID = "com.rockwellcollins.atc.agree.analysis.agreeResultsView";
    public final String TERMINATE_ID = "com.rockwellcollins.atc.agree.analysis.commands.terminate";

    private AnalysisResultTree tree;
    private AnalysisTerminateHandler terminateHandler;

    @Override
    public void createPartControl(Composite parent) {
        tree = new AnalysisResultTree(parent);

        terminateHandler = new AnalysisTerminateHandler();
        IHandlerService handlerService = (IHandlerService) getViewSite().getService(
                IHandlerService.class);
        handlerService.activateHandler(TERMINATE_ID, terminateHandler);
    }

    @Override
    public void setFocus() {
        tree.getControl().setFocus();
    }

    public void setInput(AnalysisResult result, IProgressMonitor monitor) {
        tree.setInput(result);
        terminateHandler.setResult(result);
        terminateHandler.setMonitor(monitor);
    }
}
