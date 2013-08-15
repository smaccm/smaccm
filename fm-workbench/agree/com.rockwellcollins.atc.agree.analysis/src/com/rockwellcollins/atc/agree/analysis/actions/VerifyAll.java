package com.rockwellcollins.atc.agree.analysis.actions;

import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.analysis.AgreeEmitter;

//import com.rockwellcollins.atc.jkind.plugin.views.JKindView;

public class VerifyAll extends AgreeAction {
    private void verifyAllSubsystems(ComponentImplementation compImpl, Subcomponent subContext,
            IProgressMonitor monitor) {
        emitter = new AgreeEmitter(compImpl);
        emitter.curComp = subContext;
        final Program lustre = emitter.evaluate();
        String consoleName = null;
        if (subContext == null) {
            consoleName = compImpl.getName();
        } else {
            Classifier prevComp = subContext.getContainingClassifier();
            assert (prevComp instanceof SystemImplementation);
            consoleName = prevComp.getName() + "." + subContext.getName();
        }

        MessageConsole logConsole = findConsole("Log For '" + consoleName + "'");
        logConsole.clearConsole();
        MessageConsoleStream logOut = logConsole.newMessageStream();
        logOut.println(emitter.log.getLog());

        MessageConsole lustreConsole = findConsole("Lustre For '" + consoleName + "'");
        lustreConsole.clearConsole();
        MessageConsoleStream lustreOut = lustreConsole.newMessageStream();
        lustreOut.println(lustre.toString());

        MessageConsole kindConsole = findConsole("Kind Output For '" + consoleName + "'");
        kindConsole.clearConsole();
        kindConsole.addPatternMatchListener(new AgreePatternListener(emitter.refMap));
        MessageConsoleStream kindOut = kindConsole.newMessageStream();

        runKindQueryAPI(subContext, emitter, lustre, kindOut, monitor);

        for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
            ComponentImplementation subCompImpl = subComp.getComponentImplementation();
            verifyAllSubsystems(subCompImpl, subComp, monitor);
        }

    }

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        if (!(root instanceof ComponentImplementation)) {
            Dialog.showError("AGREE Error", "Please choose an AADL Component Implementation");
            return Status.CANCEL_STATUS;
        }

        verifyAllSubsystems((ComponentImplementation) root, null, monitor);
        return Status.OK_STATUS;
    }

}
