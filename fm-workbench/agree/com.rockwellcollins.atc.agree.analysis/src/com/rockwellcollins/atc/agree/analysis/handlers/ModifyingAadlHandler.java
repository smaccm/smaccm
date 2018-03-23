package com.rockwellcollins.atc.agree.analysis.handlers;

import java.io.IOException;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;

public abstract class ModifyingAadlHandler extends AadlHandler {
	@Override
	protected WorkspaceJob getWorkspaceJob(XtextEditor xtextEditor, URI uri) {
		return new WorkspaceJob(getJobName()) {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) {
				// modification may update text editors so we need to run in the SWT thread
				IStatus[] status = new IStatus[1];
				getWindow().getShell().getDisplay().syncExec(() -> {
					if (xtextEditor == null) {
						final ResourceSet rs = OsateResourceUtil.getResourceSet();
						status[0] = getWorker(uri, monitor).apply(rs);

						// Save the model
						final EObject eobj = rs.getEObject(uri, true);
						if (eobj != null) {
							final Resource resource = eobj.eResource();
							if (resource != null) {
								try {
									resource.save(SaveOptions.newBuilder().format().getOptions().toOptionsMap());
								} catch (final IOException e) {
									throw new RuntimeException(e);
								}
							}
						}
					} else {
						status[0] = xtextEditor.getDocument().modify(getUnitOfWork(uri, monitor));
					}
				});
				return status[0];
			};
		};
	}
}
