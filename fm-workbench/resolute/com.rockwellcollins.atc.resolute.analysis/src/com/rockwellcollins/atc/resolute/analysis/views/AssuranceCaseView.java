package com.rockwellcollins.atc.resolute.analysis.views;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

import com.google.inject.Inject;
import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.FailResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;

public class AssuranceCaseView extends ViewPart {
    public static final String ID = "com.rockwellcollins.atc.resolute.views.assuranceCaseView";
    private TreeViewer treeViewer;

    @Inject
    GlobalURIEditorOpener globalURIEditorOpener;

    @Override
    public void createPartControl(Composite parent) {
        treeViewer = new TreeViewer(parent, SWT.SINGLE);
        treeViewer.setContentProvider(new ResoluteResultContentProvider());
        treeViewer.setLabelProvider(new ResoluteResultLabelProvider());
        ResoluteTooltipListener.createAndRegister(treeViewer);

        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);

        manager.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
                if (!selection.isEmpty()) {
                    final ClaimResult claim = (ClaimResult) selection.getFirstElement();

                    EObject location = claim.getLocation();
                    if (claim instanceof FailResult) {
                        manager.add(createHyperlinkAction("Open Failure Location", location));
                    } else if (location instanceof ProveStatement) {
                        manager.add(createHyperlinkAction("Open Prove Statement", location));
                    } else {
                        manager.add(createHyperlinkAction("Open Claim Definition", location));
                    }

                    Map<String, EObject> references = claim.getReferences();
                    for (String name : new TreeSet<String>(references.keySet())) {
                        manager.add(createHyperlinkAction("Go to '" + name + "'",
                                references.get(name)));
                    }

                    manager.add(new Action("Copy Claim Text") {
                        @Override
                        public void run() {
                            Transferable text = new StringSelection(claim.getText());
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(text, null);
                        }
                    });
                }
            }
        });
        treeViewer.getControl().setMenu(manager.createContextMenu(treeViewer.getTree()));
    }

    private IAction createHyperlinkAction(String text, final EObject eObject) {
        return new Action(text) {
            @Override
            public void run() {
                globalURIEditorOpener.open(EcoreUtil.getURI(eObject), true);
            }
        };
    }

    public void setProofs(List<ResoluteResult> proofTrees) {
        Object[] expandedElements = treeViewer.getExpandedElements();
        TreePath[] expandedTreePaths = treeViewer.getExpandedTreePaths();
        treeViewer.setInput(proofTrees);
        treeViewer.setExpandedElements(expandedElements);
        treeViewer.setExpandedTreePaths(expandedTreePaths);
    }

    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }
}