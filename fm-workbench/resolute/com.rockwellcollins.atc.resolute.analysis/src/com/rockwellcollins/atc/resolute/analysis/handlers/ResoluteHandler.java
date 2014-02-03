package com.rockwellcollins.atc.resolute.analysis.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.FeatureToConnectionsMap;
import com.rockwellcollins.atc.resolute.analysis.execution.NamedElementComparator;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteInterpreter;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.analysis.views.AssuranceCaseView;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;

public class ResoluteHandler extends AadlHandler {
    @Override
    protected String getJobName() {
        return "Resolute Analysis";
    }

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        clearProofs();

        long start = System.currentTimeMillis();
        SystemInstance si;
        if (root instanceof SystemImplementation) {
            SystemImplementation sysimpl = (SystemImplementation) root;
            try {
                si = InstantiateModel.buildInstanceModelFile(sysimpl);
            } catch (Exception e) {
                Dialog.showError("Model Instantiate", "Error while re-instantiating the model: "
                        + e.getMessage());
                return Status.CANCEL_STATUS;
            }
        } else {
            Dialog.showError("Model Instantiate",
                    "You must select a System Implementation to instantiate");
            return Status.CANCEL_STATUS;
        }
        long stop = System.currentTimeMillis();
        System.out.println("Instantiation time: " + (stop - start) / 1000.0 + "s");

        start = System.currentTimeMillis();

        Map<String, SortedSet<NamedElement>> sets = new HashMap<>();
        initializeSets(si, sets);
        FeatureToConnectionsMap featToConnsMap = new FeatureToConnectionsMap(si);

        List<ResoluteResult> proofTrees = new ArrayList<>();
        for (NamedElement el : sets.get("component")) {
            ComponentInstance compInst = (ComponentInstance) el;
            for (Element child : compInst.getComponentClassifier().getChildren()) {
                if (child instanceof ResoluteSubclause) {
                    EvaluationContext context = new EvaluationContext(compInst, sets,
                            featToConnsMap);
                    ResoluteInterpreter interpreter = new ResoluteInterpreter(context);
                    for (Element element : child.getChildren()) {
                        if (element instanceof ProveStatement) {
                            proofTrees.add(interpreter
                                    .evaluateProveStatement((ProveStatement) element));
                            drawProofs(proofTrees);
                        }
                    }
                }
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Evaluation time: " + (stop - start) / 1000.0 + "s");

        return Status.OK_STATUS;
    }

    private void initializeSets(ComponentInstance ci, Map<String, SortedSet<NamedElement>> sets) {
        if (ci == null) {
            return;
        }

        addToSet(sets, getCategoryName(ci.getCategory()), ci);
        addToSet(sets, "component", ci);

        for (ComponentInstance sub : ci.getComponentInstances()) {
            initializeSets(sub, sets);
        }

        for (ConnectionInstance conn : ci.getConnectionInstances()) {
            addToSet(sets, "connection", conn);
        }
    }

    private String getCategoryName(ComponentCategory category) {
        return category.getName().replace(" ", "_");
    }

    private void addToSet(Map<String, SortedSet<NamedElement>> sets, String name, NamedElement ne) {
        SortedSet<NamedElement> set = sets.get(name);
        if (set == null) {
            set = new TreeSet<>(new NamedElementComparator());
            sets.put(name, set);
        }
        set.add(ne);
    }

    private void drawProofs(final List<ResoluteResult> proofTrees) {
        final IWorkbenchPage page = getWindow().getActivePage();

        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                displayView(proofTrees, page);
            }
        });
    }

    private void displayView(final List<ResoluteResult> proofTrees, final IWorkbenchPage page) {
        try {
            AssuranceCaseView view = (AssuranceCaseView) page.showView(AssuranceCaseView.ID);
            view.setProofs(proofTrees);
            view.setFocus();
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    private void clearProofs() {
        drawProofs(Collections.<ResoluteResult> emptyList());
    }
}
