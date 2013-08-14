package com.rockwellcollins.atc.agree.analysis.actions;

import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.osate.aadl2.Element;
import org.osate.aadl2.SystemImplementation;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.analysis.AgreeEmitter;
import com.rockwellcollins.atc.agree.analysis.AgreeEvaluator;

//import com.rockwellcollins.atc.jkind.plugin.views.JKindView;

public class VerifyTop extends AgreeAction {


	@Override
	protected IStatus runJob(Element root, IProgressMonitor monitor) {

		if (!(root instanceof SystemImplementation)) {
			Dialog.showError("AGREE Error", "Please choose an AADL System Implementation");
			return Status.CANCEL_STATUS;
		}

		SystemImplementation sysImpl = (SystemImplementation) root;
		emitter = new AgreeEmitter(sysImpl);
		final Program lustre = emitter.evaluate();

		MessageConsole logConsole = findConsole("Log For '" + sysImpl.getName() + "'");
		logConsole.clearConsole();
		MessageConsoleStream logOut = logConsole.newMessageStream();
		logOut.println(emitter.log.getLog());
		
		MessageConsole lustreConsole = findConsole("Lustre For '" + sysImpl.getName() + "'");
		lustreConsole.clearConsole();
		MessageConsoleStream lustreOut = lustreConsole.newMessageStream();
		lustreOut.println(lustre.toString());

		MessageConsole kindConsole = findConsole("Kind Output For '" + sysImpl.getName() + "'");
		kindConsole.clearConsole();
		kindConsole.addPatternMatchListener(new AgreePatternListener(emitter.refMap));
		MessageConsoleStream kindOut = kindConsole.newMessageStream();
		
		runKindQueryAPI(null, emitter, lustre, kindOut, monitor);
		
		return Status.OK_STATUS;
	}

}
