package com.rockwellcollins.atc.agree.analysis.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.osate.aadl2.Element;

public abstract class AadlHandler extends AbstractHandler {
    protected static final String TERMINATE_ID = "com.rockwellcollins.atc.agree.analysis.commands.terminate";
    private IWorkbenchWindow window;

    abstract protected IStatus runJob(Element sel, IProgressMonitor monitor);

    abstract protected String getJobName();

    @Override
    public Object execute(ExecutionEvent event) {
        EObjectNode node = getEObjectNode(HandlerUtil.getCurrentSelection(event));
        if (node == null) {
            return null;
        }
        final URI uri = node.getEObjectURI();

        final XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor(event);
        if (xtextEditor == null) {
            return null;
        }
        
        window = HandlerUtil.getActiveWorkbenchWindow(event);
        if (window == null) {
            return null;
        }
        
        final IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
        WorkspaceJob job = new WorkspaceJob(getJobName()) {
            @Override
            public IStatus runInWorkspace(final IProgressMonitor monitor) {
                final IHandlerActivation activation = handlerService.activateHandler(TERMINATE_ID, new TerminateHandler(monitor));
                addJobChangeListener(new JobChangeAdapter() {
                    @Override
                    public void done(IJobChangeEvent event) {
                        handlerService.deactivateHandler(activation);
                    }
                });
                
                return xtextEditor.getDocument().readOnly(
                        new IUnitOfWork<IStatus, XtextResource>() {
                            @Override
                            public IStatus exec(XtextResource resource) throws Exception {
                                EObject eobj = resource.getResourceSet().getEObject(uri, true);
                                if (eobj instanceof Element) {
                                    return runJob((Element) eobj, monitor);
                                } else {
                                    return Status.CANCEL_STATUS;
                                }
                            }
                        });
            }
        };
 
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();
        return null;
    }

    private EObjectNode getEObjectNode(ISelection currentSelection) {
        if (currentSelection instanceof IStructuredSelection) {
            IStructuredSelection iss = (IStructuredSelection) currentSelection;
            if (iss.size() == 1) {
                return (EObjectNode) iss.getFirstElement();
            }
        }
        return null;
    }
    
    protected IWorkbenchWindow getWindow() {
        return window;
    }
}
