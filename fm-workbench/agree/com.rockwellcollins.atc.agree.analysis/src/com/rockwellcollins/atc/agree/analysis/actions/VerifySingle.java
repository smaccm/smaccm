package com.rockwellcollins.atc.agree.analysis.actions;

import jkind.lustre.Program;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;
import org.osate.aadl2.SystemImplementation;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.analysis.AgreeEmitter;
import com.rockwellcollins.atc.agree.analysis.AgreeEvaluator;

public class VerifySingle extends AgreeAction {
	@Override
	protected IStatus runJob(Element root, IProgressMonitor monitor) {
		if (!(root instanceof ComponentImplementation)) {
			Dialog.showError("AGREE Error", "Please choose an AADL Component Implementation");
			return Status.CANCEL_STATUS;
		}

		SystemImplementation compImpl = (SystemImplementation) root;
		emitter = new AgreeEmitter(compImpl);
		final Program lustre = emitter.evaluate();

		MessageConsole logConsole = findConsole("Log For '" + compImpl.getName() + "'");
		logConsole.clearConsole();
		MessageConsoleStream logOut = logConsole.newMessageStream();
		logOut.println(emitter.log.getLog());
		
		MessageConsole lustreConsole = findConsole("Lustre For '" + compImpl.getName() + "'");
		lustreConsole.clearConsole();
		MessageConsoleStream lustreOut = lustreConsole.newMessageStream();
		lustreOut.println(lustre.toString());

		MessageConsole kindConsole = findConsole("Kind Output For '" + compImpl.getName() + "'");
		kindConsole.clearConsole();
		kindConsole.addPatternMatchListener(new AgreePatternListener(emitter.refMap));
		MessageConsoleStream kindOut = kindConsole.newMessageStream();
		
		runKindQueryAPI(null, emitter, lustre, kindOut, monitor);
		return Status.OK_STATUS;
	}

}
