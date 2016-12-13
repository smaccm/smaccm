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
package edu.uah.rsesc.aadlsimulator.services.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.FrameworkUtil;

import edu.uah.rsesc.aadlsimulator.ExceptionHandler;

/**
 * ExceptionHandler implementation that delegates exceptions to exception handler extensions.
 * If an exception handler for the exception class is registered, the exception is given to the StatusManager for logging and passed to the exception handler. 
 * Otherwise, the exception is rethrown as a RuntimeException
 *
 */
class MasterExceptionHandler implements ExceptionHandler {
	private final String exceptionHandlersExtensionPointId = "edu.uah.rsesc.aadlsimulator.exceptionHandlers";
	private final Map<Class<?>, ExceptionHandler> classToExceptionHandlerMap = new LinkedHashMap<Class<?>, ExceptionHandler>();
	
	public MasterExceptionHandler() {
		final IExtensionRegistry reg = Platform.getExtensionRegistry();
	    final IConfigurationElement[] elements = reg.getConfigurationElementsFor(exceptionHandlersExtensionPointId);
	    for(final IConfigurationElement handlerElement : elements) {
			try {
				final ExceptionHandler handler = (ExceptionHandler)handlerElement.createExecutableExtension("class");
				for(final IConfigurationElement exceptionTypeElement : handlerElement.getChildren("exceptionType")) {
					final String exceptionClassName = exceptionTypeElement.getAttribute("class");
					final Class<?> exceptionClass = Platform.getBundle(handlerElement.getContributor().getName()).loadClass(exceptionClassName);

					// Add the class to handler mapping to the map
					classToExceptionHandlerMap.put(exceptionClass, handler);
				}
				
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
	    }
	    
	}
	
	@Override
	public void handleException(final Exception ex) {
		Objects.requireNonNull(ex, "ex must not be null");
		
		final ExceptionHandler handler = getExceptionHandler(ex.getClass());
		if(handler == null) {
			// Report the error to the status manager for logging and display
			final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Simulator Error", ex);
			StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
		} else {			
			// Report the error to the status manager for logging
			final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Simulator Error", ex);
			StatusManager.getManager().handle(status, StatusManager.LOG);
			
			handler.handleException(ex);
		}		
	}
	
	private ExceptionHandler getExceptionHandler(final Class<?> exceptionClass) {
		assert exceptionClass != null;
		for(final Entry<Class<?>, ExceptionHandler> handlerEntry : classToExceptionHandlerMap.entrySet()) {
			if(handlerEntry.getKey().isAssignableFrom(exceptionClass)) {
				return handlerEntry.getValue();				
			}
		}
		
		return null;
	}
}
