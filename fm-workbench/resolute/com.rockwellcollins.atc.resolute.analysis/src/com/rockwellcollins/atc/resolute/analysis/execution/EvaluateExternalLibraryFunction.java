package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public class EvaluateExternalLibraryFunction {

	final private static String EXTENSION_ID = "com.rockwellcollins.atc.resolute.analysis.external.resoluteExternalFunctionLibrary";
	private static Map<String, ResoluteExternalFunctionLibrary> libraryMap;

	static public void init(IExtensionRegistry registry) {
		libraryMap = new HashMap<>();
		evaluate(registry);
	}

	private static void evaluate(IExtensionRegistry registry) {
		try {
			for (IConfigurationElement e : registry.getConfigurationElementsFor(EXTENSION_ID)) {
				String name = e.getAttribute("name");
				Object o = e.createExecutableExtension("libraryClass");
				libraryMap.put(name, (ResoluteExternalFunctionLibrary) o);
			}
		} catch (CoreException e) {
			throw new IllegalStateException(e);
		}
	}

	public static ResoluteValue evaluate(String name, EvaluationContext context, String function,
			List<ResoluteValue> args) {
		return libraryMap.get(name).run(context, function, args);
	}

}
