package com.rockwellcollins.atc.agree.analysis.handlers;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.editor.XtextEditor;

public abstract class ModifyingAadlHandler extends AadlHandler {
	@Override
	protected WorkspaceJob getWorkspaceJob(XtextEditor xtextEditor, URI uri) {
		return new WorkspaceJob(getJobName()) {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) {
				// modification may update text editors so we need to run in the SWT thread
				IStatus[] status = new IStatus[1];
				getWindow().getShell().getDisplay().syncExec(() -> {
					status[0] = xtextEditor.getDocument().modify(getUnitOfWork(uri, monitor));
				});
				return status[0];
			};
		};
	}
}
