package com.rockwellcollins.atc.resolute.analysis.external;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

public class EvaluateLibraryTypeExtension {

	final private static HashMap<String, ResoluteExternalFunctionLibraryType> externalLibraryTypeMap = new HashMap<>();
	final private static String EXTENSION_ID = "com.rockwellcollins.atc.resolute.analysis.external.resoluteExternalFunctionLibrary";

	static public void init(IExtensionRegistry registry) {

		externalLibraryTypeMap.clear();
		evaluate(registry);
	}

	private static void evaluate(IExtensionRegistry registry) {
		try {
			for (IConfigurationElement e : registry.getConfigurationElementsFor(EXTENSION_ID)) {
				String name = e.getAttribute("name");
				Object o = e.createExecutableExtension("typeClass");
				externalLibraryTypeMap.put(name, (ResoluteExternalFunctionLibraryType) o);
			}
		} catch (CoreException e) {
			throw new IllegalStateException(e);
		}
	}

	public static ResoluteExternalFunctionLibraryType getLibraryType(String str) {
		return externalLibraryTypeMap.get(str);
	}

}
