package com.rockwellcollins.atc.resolute.analysis.external;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

public class EvaluateTypeExtention {
	final private static HashMap<String, ResoluteExternalAnalysisType> externalAnalysisTypeMap = new HashMap<>();
    final private static String EXTENTION_ID = "com.rockwellcollins.atc.resolute.analysis.external.resoluteExternalAnalysis";

    static public void init(IExtensionRegistry registry) {

		externalAnalysisTypeMap.clear();
        evaluate(registry);
    }

    private static void evaluate(IExtensionRegistry registry) {
        try {
            for (IConfigurationElement e : registry.getConfigurationElementsFor(EXTENTION_ID)) {
                String name = e.getAttribute("name");
                Object o = e.createExecutableExtension("typeClass");
				externalAnalysisTypeMap.put(name, (ResoluteExternalAnalysisType) o);
            }
        } catch (CoreException e) {
            throw new IllegalStateException(e);
        }
    }

	public static ResoluteExternalAnalysisType getAnalysisType(String str) {
		return externalAnalysisTypeMap.get(str);
	}

}