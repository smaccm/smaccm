package com.rockwellcollins.atc.resolute.analysis.external;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

public class EvaluateTypeExtention {
    final private static HashMap<String, ResoluteExternalAnalysisType> externalTypeMap = new HashMap<>();
    final private static String EXTENTION_ID = "com.rockwellcollins.atc.resolute.analysis.external.resoluteExternalAnalysis";

    static public void init(IExtensionRegistry registry) {
        externalTypeMap.clear();
        evaluate(registry);
    }

    private static void evaluate(IExtensionRegistry registry) {
        try {
            for (IConfigurationElement e : registry.getConfigurationElementsFor(EXTENTION_ID)) {
                String name = e.getAttribute("name");
                Object o = e.createExecutableExtension("typeClass");
                ResoluteExternalAnalysisType exType = (ResoluteExternalAnalysisType) o;
                externalTypeMap.put(name, exType);
            }
        } catch (CoreException e) {
            throw new IllegalStateException(e);
        }
    }

    public static ResoluteExternalAnalysisType getType(String str) {
        return externalTypeMap.get(str);
    }
}