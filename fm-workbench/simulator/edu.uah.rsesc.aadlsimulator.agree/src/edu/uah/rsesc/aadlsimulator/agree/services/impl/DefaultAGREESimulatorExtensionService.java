/*
Copyright (c) 2015, Rockwell Collins.
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
package edu.uah.rsesc.aadlsimulator.agree.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import edu.uah.rsesc.aadlsimulator.agree.ext.AGREESimulationExporter;
import edu.uah.rsesc.aadlsimulator.agree.services.AGREESimulatorExtensionService;

public class DefaultAGREESimulatorExtensionService implements AGREESimulatorExtensionService {
	private static final String EXPORTERS_EXTENSION_POINT_ID = "edu.uah.rsesc.aadlsimulator.agree.exporters";

	private Collection<ExporterProxy> exporters;	
	
	private static class SimpleExporterProxy implements ExporterProxy {
		private static final String ATTRIBUTE_NAME = "name";
		private static final String ATTRIBUTE_CLASS = "class";
		
		private IConfigurationElement configElement;
		private AGREESimulationExporter exporter;
		
		public SimpleExporterProxy(final IConfigurationElement configElement) {
			this.configElement = Objects.requireNonNull(configElement, "configElement must not be null");
		}
		
		@Override
		public String getName() {
			return configElement.getAttribute(ATTRIBUTE_NAME);
		}
		
		@Override
		public void export(final ExportArguments args) {
			getExporter().export(args);			
		}
		
		private AGREESimulationExporter getExporter() {
			if(exporter == null) {
				try {
					exporter = (AGREESimulationExporter)configElement.createExecutableExtension(ATTRIBUTE_CLASS);
				} catch (final CoreException e) {
					throw new RuntimeException(e);
				}
			}
			
			return exporter;
		}
	}

	public DefaultAGREESimulatorExtensionService() {
		exporters = createExporterProxies();
	} 
	
	@Override
	public Collection<ExporterProxy> getExporters() {
		return exporters;
	}
	
	private Collection<ExporterProxy> createExporterProxies() {
		final Collection<ExporterProxy> exporterProxies = new ArrayList<ExporterProxy>();
		final IExtensionRegistry registry = Platform.getExtensionRegistry();	
		final IExtensionPoint point = registry.getExtensionPoint(EXPORTERS_EXTENSION_POINT_ID);
		if(point != null) {
			// Create proxies for each extension
			for(final IExtension extension : point.getExtensions()) {
				for(final IConfigurationElement ce : extension.getConfigurationElements()) {
					exporterProxies.add(new SimpleExporterProxy(ce));
				}
			}
		}
		
		return Collections.unmodifiableCollection(exporterProxies);
	}
}
