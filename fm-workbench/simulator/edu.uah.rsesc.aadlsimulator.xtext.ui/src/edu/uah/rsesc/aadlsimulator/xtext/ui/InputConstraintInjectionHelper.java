package edu.uah.rsesc.aadlsimulator.xtext.ui;

import edu.uah.rsesc.aadlsimulator.xtext.ui.internal.InputConstraintActivator;

public class InputConstraintInjectionHelper {
	public static <C> C getInstance(final Class<C> c) {
		return InputConstraintActivator.getInstance().getInjector(InputConstraintActivator.EDU_UAH_RSESC_AADLSIMULATOR_XTEXT_INPUTCONSTRAINT).getInstance(c);
	}
}
