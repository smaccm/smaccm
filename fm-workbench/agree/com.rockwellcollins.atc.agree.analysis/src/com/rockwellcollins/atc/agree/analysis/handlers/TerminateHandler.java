package com.rockwellcollins.atc.agree.analysis.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;

public class TerminateHandler extends AbstractHandler {
    private final IProgressMonitor monitor;

    public TerminateHandler(IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        monitor.setCanceled(true);
        return null;
    }
}
