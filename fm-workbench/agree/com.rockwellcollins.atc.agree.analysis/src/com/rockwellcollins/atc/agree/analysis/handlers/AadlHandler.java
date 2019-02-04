package com.rockwellcollins.atc.agree.analysis.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.Element;
import org.osate.aadl2.Realization;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.ge.BusinessObjectSelection;

public abstract class AadlHandler extends AbstractHandler {
	protected static final String TERMINATE_ID = "com.rockwellcollins.atc.agree.analysis.commands.terminate";
	protected static final String TERMINATE_ALL_ID = "com.rockwellcollins.atc.agree.analysis.commands.terminateAll";
	private IWorkbenchWindow window;

	abstract protected IStatus runJob(Element sel, IProgressMonitor monitor);

	abstract protected String getJobName();

	@Override
	public Object execute(ExecutionEvent event) {
		URI uri = getSelectionURI(HandlerUtil.getCurrentSelection(event));
		if (uri == null) {
			return null;
		}

		window = HandlerUtil.getActiveWorkbenchWindow(event);
		if (window == null) {
			return null;
		}

		return executeURI(uri);
	}

	public Object executeURI(final URI uri) {
		if (!saveChanges(window.getActivePage().getDirtyEditors())) {
			return null;
		}

		final WorkspaceJob job;
		final XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor();
		job = getWorkspaceJob(xtextEditor, uri);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();

		return null;
	}

	protected WorkspaceJob getWorkspaceJob(XtextEditor xtextEditor, URI uri) {
		return new WorkspaceJob(getJobName()) {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) {
				if (xtextEditor == null) {
					return getWorker(uri, monitor).apply(OsateResourceUtil.getResourceSet());
				} else {
					return xtextEditor.getDocument().readOnly(getUnitOfWork(uri, monitor));
				}
			}
		};
	}

	protected IUnitOfWork<IStatus, XtextResource> getUnitOfWork(URI uri, IProgressMonitor monitor) {
		return resource -> {
			return getWorker(uri, monitor).apply(resource.getResourceSet());
		};
	}

	/**
	 * Returns a function which performs the actual work when provided with a resource set. Shared between Xtext editor and non xtext editor code paths.
	 * @param uri
	 * @param monitor
	 * @return
	 */
	protected Function<ResourceSet, IStatus> getWorker(final URI uri, final IProgressMonitor monitor) {
		return resourceSet -> {
			final EObject eobj = resourceSet.getEObject(uri, true);
			if (eobj instanceof Element) {
				return runJob((Element) eobj, monitor);
			} else {
				return Status.CANCEL_STATUS;
			}
		};
	}

	private boolean saveChanges(IEditorPart[] dirtyEditors) {
		if (dirtyEditors.length == 0) {
			return true;
		}

		if (MessageDialog.openConfirm(window.getShell(), "Save editors", "Save editors and continue?")) {
			NullProgressMonitor monitor = new NullProgressMonitor();
			for (IEditorPart e : dirtyEditors) {
				e.doSave(monitor);
			}
			return true;
		} else {
			return false;
		}
	}

	private URI getSelectionURI(ISelection currentSelection) {
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) currentSelection;
			if (iss.size() == 1 && iss.getFirstElement() instanceof EObjectNode) {
				EObjectNode node = (EObjectNode) iss.getFirstElement();
				return node.getEObjectURI();
			} else {
				final BusinessObjectSelection bos = Adapters.adapt(currentSelection, BusinessObjectSelection.class);
				if (bos != null) {
					if (bos.boStream(EObject.class).count() == 1) {
						return bos.boStream(EObject.class).findFirst()
								.map(e -> EcoreUtil.getURI(e)).orElse(null);
					}
				}
			}
		} else if (currentSelection instanceof TextSelection) {
			// Selection may be stale, get latest from editor
			XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor();
			TextSelection ts = (TextSelection) xtextEditor.getSelectionProvider().getSelection();
			return xtextEditor.getDocument().readOnly(resource -> {
				EObject e = new EObjectAtOffsetHelper().resolveContainedElementAt(resource, ts.getOffset());
				if (e instanceof Realization) {
					e = e.eContainer();
				}
				return EcoreUtil.getURI(e);
			});
		}
		return null;
	}

	protected Classifier getOutermostClassifier(Element element) {
		List<EObject> containers = new ArrayList<>();
		EObject curr = element;
		while (curr != null) {
			containers.add(curr);
			curr = curr.eContainer();
		}
		Collections.reverse(containers);
		for (EObject container : containers) {
			if (container instanceof Classifier) {
				// System.out.println(container);
				return (Classifier) container;
			}
		}
		return null;
	}

	protected IWorkbenchWindow getWindow() {
		return window;
	}
}
