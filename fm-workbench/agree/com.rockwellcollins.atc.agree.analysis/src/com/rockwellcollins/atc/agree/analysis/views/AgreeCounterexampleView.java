package com.rockwellcollins.atc.agree.analysis.views;

import java.util.Map;
import java.util.Stack;

import jkind.api.ui.counterexample.CounterexampleTreeViewer;
import jkind.api.ui.counterexample.SignalGroup;
import jkind.results.Counterexample;
import jkind.results.layout.Layout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

import com.rockwellcollins.atc.agree.analysis.AgreeUtils;

public class AgreeCounterexampleView extends ViewPart {
    public static final String ID = "com.rockwellcollins.atc.agree.analysis.views.agreeCounterexampleView";
    private static final GlobalURIEditorOpener globalURIEditorOpener = AgreeUtils.getGlobalURIEditorOpener();

    private CounterexampleTreeViewer tree;
    private Map<String, EObject> refMap;

    @Override
    public void createPartControl(Composite parent) {
        tree = new CounterexampleTreeViewer(parent);
    }

    @Override
    public void setFocus() {
        tree.setFocus();
    }

    public void setInput(Counterexample cex, Layout layout, Map<String, EObject> refMap) {
        tree.setInput(cex, layout);
        this.refMap = refMap;
        tree.getTreeViewer().addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                if (event.getSelection() instanceof IStructuredSelection) {
                    IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                    if (!selection.isEmpty() && selection.getFirstElement() instanceof SignalGroup) {
                        open((SignalGroup) selection.getFirstElement());
                    }
                }
            }
        });
    }

    private void open(SignalGroup group) {
        if (!group.isSingleton()) {
            return;
        }

        EObject e = findRelevantObject(group);
        if (e != null) {
            globalURIEditorOpener.open(EcoreUtil.getURI(e), true);
        }
    }

    private EObject findRelevantObject(SignalGroup curr) {
        Stack<String> names = new Stack<>();
        while (curr != null) {
            names.add(curr.getName());
            curr = curr.getParent();
        }

        EObject result = null;
        String name = "";
        while (!names.isEmpty()) {
            String next = names.pop();
            name += divider(name, next) + next;
            if (refMap.containsKey(name)) {
                result = refMap.get(name);
            }
        }
        return result;
    }

    private String divider(String front, String back) {
        if (front.isEmpty() || back.isEmpty() || back.startsWith("[")) {
            return "";
        }
        return ".";
    }
}
