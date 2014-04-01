package com.rockwellcollins.atc.aadl2sexp.handlers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osate.aadl2.Element;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.aadl2sexp.Activator;

public class Aadl2SexpHandler extends AadlHandler {
    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        if (!(root instanceof SystemImplementation)) {
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Must select an AADL System Implementation");
        }

        try {
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
            
            System.out.println(InstanceToSexp.convert(si));
        } catch (Throwable e) {
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, e.getMessage(), e);
        }
        
        return Status.OK_STATUS;
    }

    @Override
    protected String getJobName() {
        return "AADL to Sexp";
    }
}
