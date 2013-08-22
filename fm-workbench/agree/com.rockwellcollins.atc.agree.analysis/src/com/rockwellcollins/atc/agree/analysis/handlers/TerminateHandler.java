package com.rockwellcollins.atc.agree.analysis.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;

public class TerminateHandler extends AbstractHandler {
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        if (part instanceof AgreeResultsView) {
            AgreeResultsView view = (AgreeResultsView) part;
            view.cancel();
        }
        return null;
    }
}
