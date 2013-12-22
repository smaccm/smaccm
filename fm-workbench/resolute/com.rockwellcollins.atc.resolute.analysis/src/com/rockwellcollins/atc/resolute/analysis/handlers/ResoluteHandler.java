package com.rockwellcollins.atc.resolute.analysis.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteInterpreter;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteQuantifiableAadlObjects;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.analysis.views.AssuranceCaseView;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;

public class ResoluteHandler extends AadlHandler {
    @Override
    protected String getJobName() {
        return "Resolute Analysis";
    }

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        clearProofs();
        SystemInstance si;

        long start = System.currentTimeMillis();
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
        ResoluteQuantifiableAadlObjects.clearAllSets();
        initializeComponentLists(si);
        List<ResoluteResult> proofTrees = new ArrayList<>();
        for (NamedElement el : ResoluteQuantifiableAadlObjects.componentSet) {
            ComponentInstance compInst = (ComponentInstance) el;
            for (Element child : compInst.getComponentClassifier().getChildren()) {
                if (child instanceof ResoluteSubclause) {
                    ResoluteInterpreter resInterp = new ResoluteInterpreter(compInst);
                    ResoluteSubclause subClause = (ResoluteSubclause) child;
                    proofTrees.addAll(resInterp.evaluateSubclause(subClause));
                }
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Evaluation time: " + (stop - start) / 1000.0 + "s");

        drawProofs(proofTrees);
        return Status.OK_STATUS;
    }

    private void initializeComponentLists(ComponentInstance compInst) {
        if (compInst == null) {
            return;
        }

        switch (compInst.getCategory()) {
        case ABSTRACT:
            ResoluteQuantifiableAadlObjects.abstractSet.add(compInst);
            break;
        case BUS:
            ResoluteQuantifiableAadlObjects.busSet.add(compInst);
            break;
        case DATA:
            ResoluteQuantifiableAadlObjects.dataSet.add(compInst);
            break;
        case DEVICE:
            ResoluteQuantifiableAadlObjects.deviceSet.add(compInst);
            break;
        case MEMORY:
            ResoluteQuantifiableAadlObjects.memorySet.add(compInst);
            break;
        case PROCESSOR:
            ResoluteQuantifiableAadlObjects.processorSet.add(compInst);
            break;
        case PROCESS:
            ResoluteQuantifiableAadlObjects.processSet.add(compInst);
            break;
        case SUBPROGRAM_GROUP:
            ResoluteQuantifiableAadlObjects.subprogramGroupSet.add(compInst);
            break;
        case SUBPROGRAM:
            ResoluteQuantifiableAadlObjects.subprogramSet.add(compInst);
            break;
        case SYSTEM:
            ResoluteQuantifiableAadlObjects.systemSet.add(compInst);
            break;
        case THREAD_GROUP:
            ResoluteQuantifiableAadlObjects.threadGroupSet.add(compInst);
            break;
        case THREAD:
            ResoluteQuantifiableAadlObjects.threadSet.add(compInst);
            break;
        case VIRTUAL_BUS:
            ResoluteQuantifiableAadlObjects.virtualBusSet.add(compInst);
            break;
        case VIRTUAL_PROCESSOR:
            ResoluteQuantifiableAadlObjects.virtualProcessorSet.add(compInst);
            break;
        default:
            assert false;
        }

        ResoluteQuantifiableAadlObjects.componentSet.add(compInst);

        for (ComponentInstance comp : compInst.getAllComponentInstances()) {
            if (!comp.equals(compInst)) {
                initializeComponentLists(comp);
            }
        }

        for (ConnectionInstance conn : compInst.getConnectionInstances()) {
            ResoluteQuantifiableAadlObjects.connectionSet.add(conn);
        }
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
