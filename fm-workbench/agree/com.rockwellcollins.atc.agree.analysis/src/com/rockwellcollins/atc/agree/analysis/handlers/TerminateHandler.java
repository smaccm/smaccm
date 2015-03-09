package com.rockwellcollins.atc.agree.analysis.handlers;

import java.util.Queue;

import jkind.api.results.JKindResult;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IProgressMonitor;

public class TerminateHandler extends AbstractHandler {
    private final IProgressMonitor monitor;
    private final Queue<JKindResult> queue;

    public TerminateHandler(IProgressMonitor monitor, Queue<JKindResult> queue) {
        this.monitor = monitor;
        this.queue = queue;
    }

    @Override
    public Object execute(ExecutionEvent event) {
//        monitor.setCanceled(true);
        JKindResult result = queue.peek();
        result.cancel();
        return null;
    }
}
