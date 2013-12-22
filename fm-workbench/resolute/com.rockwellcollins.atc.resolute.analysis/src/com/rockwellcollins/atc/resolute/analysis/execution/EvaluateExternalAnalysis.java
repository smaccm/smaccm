package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public class EvaluateExternalAnalysis {
    final private static String EXTENTION_ID = "com.rockwellcollins.atc.resolute.analysis.external.resoluteExternalAnalysis";
    private static Map<String, ResoluteExternalAnalysis> analysisMap;
    
    static public void init(IExtensionRegistry registry) {
        analysisMap = new HashMap<>();
        evaluate(registry);
    }

    private static void evaluate(IExtensionRegistry registry) {
        try {
            for (IConfigurationElement e : registry.getConfigurationElementsFor(EXTENTION_ID)) {
                String name = e.getAttribute("name");
                Object o = e.createExecutableExtension("analysisClass");
                ResoluteExternalAnalysis analysis = (ResoluteExternalAnalysis) o;
                analysisMap.put(name, analysis);
            }
        } catch (CoreException e) {
            throw new IllegalStateException(e);
        }
    }

    public static ResoluteValue evaluate(ResoluteEvaluator eval, String name,
            List<ResoluteValue> args) {
        return analysisMap.get(name).run(eval, args);
    }
}