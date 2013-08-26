package com.rockwellcollins.atc.resolute.analysis.views;

import java.util.ArrayList;
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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

import com.google.inject.Inject;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofNode;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofNodeContentProvider;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofNodeLabelProvider;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofTree;
import com.rockwellcollins.atc.resolute.analysis.ResoluteTooltipListener;

public class AssuranceCaseView extends ViewPart {
    public static final String ID = "com.rockwellcollins.atc.resolute.views.assuranceCaseView";
    private TreeViewer treeViewer;

    @Inject
    GlobalURIEditorOpener globalURIEditorOpener;

    @Override
    public void createPartControl(Composite parent) {
        treeViewer = new TreeViewer(parent, SWT.SINGLE);
        treeViewer.setContentProvider(new ResoluteProofNodeContentProvider());
        treeViewer.setLabelProvider(new ResoluteProofNodeLabelProvider());
        ResoluteTooltipListener.createAndRegister(treeViewer);

        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);

        manager.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
                if (!selection.isEmpty()) {
                    ResoluteProofNode proofNode = (ResoluteProofNode) selection.getFirstElement();
                    String text;
                    if (proofNode.getParent() == null) {
                        text = "Open Prove Declaration";
                    } else {
                        text = "Open Claim Declaration";
                    }
                    manager.add(createHyperlinkAction(text, proofNode.getEObject()));
                    Map<String, EObject> references = proofNode.getClaimReferences();
                    for (String name : new TreeSet<String>(references.keySet())) {
                        manager.add(createHyperlinkAction("Go to '" + name + "'",
                                references.get(name)));
                    }
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

    public void addProofs(List<ResoluteProofTree> proofTrees) {
        List<ResoluteProofNode> roots = new ArrayList<>();
        for (ResoluteProofTree tree : proofTrees) {
            roots.add(tree.getRoot());
        }
        treeViewer.setInput(roots);
    }

    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }
}