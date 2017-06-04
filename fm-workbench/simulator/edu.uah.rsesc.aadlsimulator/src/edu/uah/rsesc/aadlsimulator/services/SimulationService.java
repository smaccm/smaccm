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
package edu.uah.rsesc.aadlsimulator.services;

import java.util.Collection;

import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;

import edu.uah.rsesc.aadlsimulator.ExceptionHandler;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;

public interface SimulationService {
	/**
	 * Returns an exception handler that may be used to handle exceptions.
	 * Guaranteed not to return null.
	 */
	ExceptionHandler getExceptionHandler();
	
	Collection<? extends EngineType> getEngineTypes();
	
	/**
	 * Finds an engine type with the specified id. Throws an exception if the engine type can not be retrieved.
	 * @param name
	 * @return the engine type with the specified id.
	 */
	EngineType getEngineTypeById(String id);
	
	/**
	 * Returns the first compatible engine type for a component implementation or null if a compatible simulation engine is not available.
	 * @return
	 */
	EngineType getCompatibleEngineType(final ComponentImplementation ci);
	
	void addSimulationEngineChangeListener(final SimulationEngineChangeListener listener);
	void removeSimulationEngineChangeListener(final SimulationEngineChangeListener listener);
	
	/**
	 * Instantiates a simulation engine. 
	 * Note that the Simulation UI Service only allows one simulation engine to be handled at time. 
	 * It throws an exception if this method is called when a simulation engine is already being handled by the UI service.
	 * @param engineType
	 * @param systemInstance
	 * @return the new simulation engine.
	 */
	SimulationEngine createEngine(EngineType engineType, SystemInstance systemInstance);
	
	/**
	 * Notifies listeners of the change and then closes the simulation engine.  
	 * @param engine the engine to be disposed.
	 */
	void dispose(final SimulationEngine engine);
	
}
