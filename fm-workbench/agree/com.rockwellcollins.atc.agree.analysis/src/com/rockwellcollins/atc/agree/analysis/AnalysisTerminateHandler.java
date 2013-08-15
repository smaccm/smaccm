package com.rockwellcollins.atc.agree.analysis;

import jkind.api.results.AnalysisResult;
import jkind.api.results.MultiStatus;
import jkind.api.results.ResultsUtil;
import jkind.api.results.Status;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;

public class AnalysisTerminateHandler extends AbstractHandler {
	private AnalysisResult result;
	private IProgressMonitor monitor;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		monitor.setCanceled(true);
		return null;
	}

	@Override
	public boolean isEnabled() {
		if (monitor.isCanceled()) {
			return false;
		}

		MultiStatus status = ResultsUtil.getMultiStatus(result);
		return status.getCount(Status.WORKING) > 0 || status.getCount(Status.WAITING) > 0;
	}

	public void setResult(AnalysisResult result) {
		this.result = result;
	}

	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}
}
