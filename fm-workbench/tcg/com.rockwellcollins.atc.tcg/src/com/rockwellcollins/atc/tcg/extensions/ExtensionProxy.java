/*
Copyright (c) 2016, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/

package com.rockwellcollins.atc.tcg.extensions;

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
        this.pluginName = annexName;
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
