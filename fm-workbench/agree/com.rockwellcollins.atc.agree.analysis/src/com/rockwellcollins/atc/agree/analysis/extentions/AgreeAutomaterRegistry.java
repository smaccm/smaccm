package com.rockwellcollins.atc.agree.analysis.extentions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IConfigurationElement;

public class AgreeAutomaterRegistry extends ExtensionRegistry {

	public AgreeAutomaterRegistry() {
		initialize(AGREE_AUTOMATER_EXT_ID);
	}

	public List<AgreeAutomater> getAgreeAutomaters() {

		List<AgreeAutomater> cexExtractors = new ArrayList<>();
		for (Entry<String, ExtensionProxy> entry : extensions.entrySet()) {
			if (entry.getValue() instanceof AgreeAutomater) {
				cexExtractors.add((AgreeAutomater) entry.getValue());
			}
		}
		return cexExtractors;
	}

	@Override
	protected ExtensionProxy createProxy(IConfigurationElement configElem) {
		return new AgreeAutomaterProxy(configElem);
	}

}
