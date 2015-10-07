package com.rockwellcollins.atc.agree.analysis.extentions;

import java.util.Map;

import jkind.results.Counterexample;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.annexsupport.AnnexHighlighter;
import org.osate.annexsupport.AnnexPlugin;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;

public class AgreeAutomaterProxy extends ExtensionProxy implements AgreeAutomater {

    private AgreeAutomater extractor;

    protected AgreeAutomaterProxy(IConfigurationElement configElem) {
        super(configElem);
        // TODO Auto-generated constructor stub
    }

    @Override
    public AgreeProgram transform(AgreeProgram program) {
        AgreeAutomater extractor = getAgreeAutomater();

        if (extractor != null) {
            return extractor.transform(program);
        }
        return null;
    }

    private AgreeAutomater getAgreeAutomater() {
        if (extractor != null) {
            return extractor;
        }
        try {
            extractor = (AgreeAutomater) configElem.createExecutableExtension(ATT_CLASS);
        } catch (Exception e) {
            System.err.println("error instantiating agree automater in plugin "
                    + configElem.getDeclaringExtension().getContributor().getName());
        }
        return extractor;
    }

}
