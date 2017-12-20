package com.rockwellcollins.atc.agree.analysis.handlers;

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IProgressMonitor;

public class TerminateHandler extends AbstractHandler {
	private final AtomicReference<IProgressMonitor> monitorRef;
	private final IProgressMonitor globalMonitor;

	public TerminateHandler(AtomicReference<IProgressMonitor> monitorRef) {
		this.monitorRef = monitorRef;
		globalMonitor = null;
	}

	public TerminateHandler(AtomicReference<IProgressMonitor> monitorRef, IProgressMonitor globalMonitor) {
		this.monitorRef = monitorRef;
		this.globalMonitor = globalMonitor;
	}

	@Override
	public Object execute(ExecutionEvent event) {
		if (globalMonitor != null) {
			globalMonitor.setCanceled(true);
		}
		IProgressMonitor monitor = monitorRef.get();
		if (monitor != null) {
			monitor.setCanceled(true);
		}
		return null;
	}
}
