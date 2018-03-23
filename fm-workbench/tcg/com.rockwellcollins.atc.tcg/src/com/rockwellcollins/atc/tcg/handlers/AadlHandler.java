/*
Copyright (c) 2016, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/

package com.rockwellcollins.atc.tcg.handlers;

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
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.osate.aadl2.Element;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.ge.BusinessObjectSelection;

public abstract class AadlHandler extends AbstractHandler {
    protected static final String TERMINATE_ID = "tcg.commands.terminate";
	private IWorkbenchWindow window;

	abstract protected IStatus runJob(Element sel, IProgressMonitor monitor);

	abstract protected String getJobName();

	@Override
	public Object execute(ExecutionEvent event) {
		final URI uri = getSelectionURI(HandlerUtil.getCurrentSelection(event));
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

		final XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor();
		WorkspaceJob job = new WorkspaceJob(getJobName()) {
			@Override
			public IStatus runInWorkspace(final IProgressMonitor monitor) {
				final Function<ResourceSet, IStatus> worker = resourceSet -> {
					EObject eobj = resourceSet.getEObject(uri, true);
					if (eobj instanceof Element) {
						return runJob((Element) eobj, monitor);
					} else {
						return Status.CANCEL_STATUS;
					}
				};

				if (xtextEditor == null) {
					return worker.apply(OsateResourceUtil.getResourceSet());
				} else {
					return xtextEditor.getDocument().readOnly(resource -> {
						return worker.apply(resource.getResourceSet());
					});
				}
			}
		};

		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
		return null;
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
					if (bos.boStream(Element.class).count() == 1) {
						return bos.boStream(EObject.class).findFirst().map(e -> EcoreUtil.getURI(e)).orElse(null);
					}
				}
			}
		} else if (currentSelection instanceof TextSelection) {
			// Selection may be stale, get latest from editor
			XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor();
			TextSelection ts = (TextSelection) xtextEditor.getSelectionProvider().getSelection();
			return xtextEditor.getDocument().readOnly(resource -> {
				EObject e = new EObjectAtOffsetHelper().resolveContainedElementAt(resource, ts.getOffset());
				return EcoreUtil.getURI(e);
			});
		}
		return null;
	}

	protected IWorkbenchWindow getWindow() {
		return window;
	}
}
