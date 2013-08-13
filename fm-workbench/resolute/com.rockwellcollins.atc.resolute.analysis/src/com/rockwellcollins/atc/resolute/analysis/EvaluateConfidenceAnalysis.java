package com.rockwellcollins.atc.resolute.analysis;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

import com.rockwellcollins.atc.resolute.resolute.Expr;

public class EvaluateConfidenceAnalysis {
	final private static String EXTENTION_ID = "com.rockwellcollins.atc.resolute.analysis.resoluteConfidenceAnalysis";
	static Set<ResoluteConfidenceInterface> interfaces;
	static public void init(IExtensionRegistry registry) {
		interfaces = new HashSet<ResoluteConfidenceInterface>();
		evaluate(registry);
	}

	private static void evaluate(IExtensionRegistry registry) {
		try {
			for (IConfigurationElement e : registry.getConfigurationElementsFor(EXTENTION_ID)) {
				Object o = e.createExecutableExtension("analysisClass");
				ResoluteConfidenceInterface analysis = (ResoluteConfidenceInterface) o;
				interfaces.add(analysis);
			}
		} catch (CoreException e) {
			throw new IllegalStateException(e);
		}
	}

	public static void evaluate(Expr expr) {
		for(ResoluteConfidenceInterface i : interfaces){
			i.resoluteExprCall(expr);
		}
	}
}