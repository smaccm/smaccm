package com.rockwellcollins.atc.agree.analysis.handlers;

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IProgressMonitor;

public class TerminateHandler extends AbstractHandler {
    private final AtomicReference<IProgressMonitor> monitorRef;

    public TerminateHandler(AtomicReference<IProgressMonitor> monitorRef) {
        this.monitorRef = monitorRef;
    }

    @Override
    public Object execute(ExecutionEvent event) {
        IProgressMonitor monitor = monitorRef.get();
        if (monitor != null) {
            monitor.setCanceled(true);
        }
        return null;
    }
}
