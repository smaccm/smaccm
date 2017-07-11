package edu.uah.rsesc.aadlsimulator.xtext.ui;

import edu.uah.rsesc.aadlsimulator.xtext.ui.internal.XtextActivator;

public class InputConstraintInjectionHelper {
	public static <C> C getInstance(final Class<C> c) {
		return XtextActivator.getInstance().getInjector(XtextActivator.EDU_UAH_RSESC_AADLSIMULATOR_XTEXT_INPUTCONSTRAINT).getInstance(c);
	}
}
