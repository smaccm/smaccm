package com.rockwellcollins.atc.resolute.analysis.handlers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.resolute.analysis.ProofType;
import com.rockwellcollins.atc.resolute.analysis.ResoluteInterpreter;
import com.rockwellcollins.atc.resolute.analysis.ResoluteProofTree;
import com.rockwellcollins.atc.resolute.analysis.ResoluteQuantifiableAadlObjects;
import com.rockwellcollins.atc.resolute.analysis.views.AssuranceCaseView;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;

public abstract class ResoluteHandler extends AadlHandler {
    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        SystemInstance si = null;

        if (root instanceof SystemImplementation) {
            final SystemImplementation sysimpl = (SystemImplementation) root;
            try {
                si = InstantiateModel.buildInstanceModelFile(sysimpl);
            } catch (Exception e) {
                Dialog.showError("Model Instantiate", "Error while re-instantiating the model: "
                        + e.getMessage());
                return Status.CANCEL_STATUS;
            }
        }

        assert (si != null);
        ResoluteQuantifiableAadlObjects.clearAllSets();
        initializeComponentLists(si);
        List<ResoluteProofTree> proofTrees = new LinkedList<ResoluteProofTree>();
        for (NamedElement el : ResoluteQuantifiableAadlObjects.componentSet) {
            ComponentInstance compInst = (ComponentInstance) el;

            ComponentClassifier compClass = compInst.getComponentClassifier();
            EList<Element> componentChildren = compClass.getChildren();
            for (Element child : componentChildren) {
                if (child instanceof ResoluteSubclause) {
                    ResoluteInterpreter resInterp = new ResoluteInterpreter(compInst,
                            getProofType());
                    ResoluteSubclause subClause = (ResoluteSubclause) child;
                    List<ResoluteProofTree> proofs = resInterp.evaluateSubclause(subClause);
                    for (ResoluteProofTree proof : proofs) {
                        pruneProof(proof);
                    }
                    proofTrees.addAll(proofs);
                }
            }
        }

        drawProofs(proofTrees);
        return Status.OK_STATUS;
    }

    protected abstract ProofType getProofType();

    protected abstract void pruneProof(ResoluteProofTree proof);

    private void initializeComponentLists(ComponentInstance compInst) {
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
            ResoluteQuantifiableAadlObjects.virtualProcessorType.add(compInst);
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

    private void drawProofs(final List<ResoluteProofTree> proofTrees) {
        final IWorkbenchPage page = getWindow().getActivePage();

        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                displayView(proofTrees, page);
            }
        });
    }

    private void displayView(final List<ResoluteProofTree> proofTrees, final IWorkbenchPage page) {
        try {
            AssuranceCaseView view = (AssuranceCaseView) page.showView(AssuranceCaseView.ID);
            view.addProofs(proofTrees);
            view.setFocus();
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }
}
