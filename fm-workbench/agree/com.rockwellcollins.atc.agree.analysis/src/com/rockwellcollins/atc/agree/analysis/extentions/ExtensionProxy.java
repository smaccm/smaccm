package com.rockwellcollins.atc.agree.analysis.extentions;

import org.eclipse.core.runtime.IConfigurationElement;

public abstract class ExtensionProxy {
	protected static final String ATT_ID = "id";
	protected static final String ATT_NAME = "name";
	protected static final String ATT_EXTENTIONNAME = "pluginName";
	protected static final String ATT_CLASS = "class";

	protected final IConfigurationElement configElem;

	protected final String id;
	protected final String name;
	protected final String pluginName;
	protected final String className;

	protected ExtensionProxy(String id, String name, String annexName, String className) {
		this.id = id;
		this.name = name;
		pluginName = annexName;
		this.className = className;
		configElem = null;
	}

	protected ExtensionProxy(IConfigurationElement configElem) {
		this.configElem = configElem;
		id = getAttribute(ATT_ID, null);
		name = getAttribute(ATT_NAME, id);
		pluginName = getAttribute(ATT_EXTENTIONNAME, null);
		className = getAttribute(ATT_CLASS, null);
	}

	private String getAttribute(String name, String defaultValue) {
		if (configElem == null) {
			return null;
		}
		String value = configElem.getAttribute(name);

		if (value != null) {
			return value;
		}
		if (defaultValue != null) {
			return defaultValue;
		}

		throw new IllegalArgumentException("Missing " + name + " attribute");
	}
}
