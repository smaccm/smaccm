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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

import edu.uah.rsesc.aadlsimulator.services.SimulationService;

public class ServiceContextFunction extends ContextFunction {
	private Map<String, Object> serviceMap = new HashMap<String, Object>();
	
	@Override
	public Object compute(final IEclipseContext context, final String contextKey) {
	    Object result = null;
	    result = serviceMap.get(contextKey);
	    
	    if(result == null) {
	    	result = handleService(result, context, contextKey, SimulationService.class, DefaultSimulationService.class);
	    	
	    	if(result != null) {
	    		serviceMap.put(contextKey, result);
	    	}
	    }

	    return result;
	}
	
	/**
	 * Helper method that is designed to make it easy to extends the context function to handle multiple services.
	 * @param currentResult
	 * @param context
	 * @param contextKey
	 * @param serviceInterface
	 * @param impl
	 * @return
	 */
	private <T> Object handleService(final Object currentResult, final IEclipseContext context, final String contextKey, final Class<T> serviceInterface, final Class<? extends T> impl) {
		if(currentResult != null) {
			return currentResult;
		}
		
		if(!contextKey.equals(serviceInterface.getName())) {
			return null;
		}
		
		T service = ContextInjectionFactory.make(impl, context);			
		context.set(contextKey, service);	

	    return service;
	}
}
