package com.rockwellcollins.atc.agree.analysis.extentions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IConfigurationElement;

public class CexExtractorRegistry extends ExtensionRegistry {

	public CexExtractorRegistry() {
		initialize(CEX_EXTRACTOR_EXT_ID);
	}

	public List<CexExtractor> getCexExtractors() {

		List<CexExtractor> cexExtractors = new ArrayList<>();
		for (Entry<String, ExtensionProxy> entry : extensions.entrySet()) {
			if (entry.getValue() instanceof CexExtractor) {
				cexExtractors.add((CexExtractor) entry.getValue());
			}
		}
		return cexExtractors;
	}

	@Override
	protected ExtensionProxy createProxy(IConfigurationElement configElem) {
		return new CexExtractorProxy(configElem);
	}

}
