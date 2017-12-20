package com.rockwellcollins.atc.agree.analysis.extentions;

import org.eclipse.core.runtime.IConfigurationElement;

import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;

import jkind.api.results.AnalysisResult;

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

	@Override
	public AgreeRenaming rename(AgreeRenaming renaming) {
		AgreeAutomater extractor = getAgreeAutomater();

		if (extractor != null) {
			return extractor.rename(renaming);
		}
		return null;
	}

	@Override
	public AnalysisResult transformResult(AnalysisResult res) {
		AgreeAutomater extractor = getAgreeAutomater();

		if (extractor != null) {
			return extractor.transformResult(res);
		}
		return null;
	}

	@Override
	public AgreeLayout transformLayout(AgreeLayout layout) {
		AgreeAutomater extractor = getAgreeAutomater();

		if (extractor != null) {
			return extractor.transformLayout(layout);
		}
		return null;
	}

}
