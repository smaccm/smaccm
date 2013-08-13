package com.rockwellcollins.atc.agree.analysis.actions;

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

import com.rockwellcollins.atc.agree.analysis.AgreeEvaluator;

//import com.rockwellcollins.atc.jkind.plugin.views.JKindView;

public class VerifyAll extends AgreeAction {

	private IProgressMonitor monitor;

	private void verifyAllSubsystems(SystemImplementation sysImpl, Subcomponent subContext) {

		evaluator = new AgreeEvaluator(sysImpl);
		evaluator.curComp = subContext;
		final String lustre = evaluator.evaluate();

		String consoleName = null;
		if (subContext == null) {
			consoleName = sysImpl.getName();
		} else {
			Classifier prevComp = subContext.getContainingClassifier();
			assert (prevComp instanceof SystemImplementation);
			consoleName = prevComp.getName() + "." + subContext.getName();
		}

		MessageConsole logConsole = findConsole("Log For '" + consoleName + "'");
		logConsole.clearConsole();
		MessageConsoleStream logOut = logConsole.newMessageStream();
		logOut.println(evaluator.log.getLog());
		
		MessageConsole lustreConsole = findConsole("Lustre For '" + consoleName + "'");
		lustreConsole.clearConsole();
		MessageConsoleStream lustreOut = lustreConsole.newMessageStream();
		lustreOut.println(lustre);

		MessageConsole kindConsole = findConsole("Kind Output For '" + consoleName + "'");
		kindConsole.clearConsole();
		kindConsole.addPatternMatchListener(new AgreePatternListener(evaluator.refMap));
		MessageConsoleStream kindOut = kindConsole.newMessageStream();

		runKindQueryAPI(subContext, evaluator, lustre, kindOut, monitor);


		for (Subcomponent subComp : sysImpl.getAllSubcomponents()) {
			ComponentImplementation compImpl = subComp.getComponentImplementation();
			if (compImpl instanceof SystemImplementation) {
				verifyAllSubsystems((SystemImplementation) compImpl, subComp);
			}
		}

	}

	@Override
	protected IStatus runJob(Element root, IProgressMonitor monitor) {

		this.monitor = monitor;
		if (!(root instanceof SystemImplementation)) {
			Dialog.showError("AGREE Error", "Please choose an AADL System Implementation");
			return Status.CANCEL_STATUS;
		}

		SystemImplementation sysImpl = (SystemImplementation) root;

		verifyAllSubsystems(sysImpl, null);

		return Status.OK_STATUS;
	}

}
