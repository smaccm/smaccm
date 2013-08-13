package com.rockwellcollins.atc.agree.analysis.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.analysis.AgreeEvaluator;

public class VerifySingle extends AgreeAction {
	@Override
	protected IStatus runJob(Element root, IProgressMonitor monitor) {
		if (!(root instanceof ComponentImplementation)) {
			Dialog.showError("AGREE Error", "Please choose an AADL Component Implementation");
			return Status.CANCEL_STATUS;
		}

		ComponentImplementation compImpl = (ComponentImplementation) root;
		AgreeEvaluator evaluator = new AgreeEvaluator(compImpl);
		final String lustre = evaluator.evaluate();

		MessageConsole logConsole = findConsole("Log For '" + compImpl.getName() + "'");
		logConsole.clearConsole();
		MessageConsoleStream logOut = logConsole.newMessageStream();
		logOut.println(evaluator.log.getLog());
		
		MessageConsole lustreConsole = findConsole("Lustre For '" + compImpl.getName() + "'");
		lustreConsole.clearConsole();
		MessageConsoleStream lustreOut = lustreConsole.newMessageStream();
		lustreOut.println(lustre);

		MessageConsole kindConsole = findConsole("Kind Output For '" + compImpl.getName() + "'");
		kindConsole.clearConsole();
		kindConsole.addPatternMatchListener(new AgreePatternListener(evaluator.refMap));
		MessageConsoleStream kindOut = kindConsole.newMessageStream();
		// out.println(result);
		
		runKindQueryAPI(null, evaluator, lustre, kindOut, monitor);

		return Status.OK_STATUS;
	}

}
