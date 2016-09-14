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
package edu.uah.rsesc.aadlsimulator;

import org.osate.aadl2.instance.SystemInstance;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;

public interface SimulationEngine {
	/**
	 * Called by the simulation service to release resources owned by the simulation engine. Should only be called by the owner of the simulation engine.
	 * Under most circumstances this is the simulation service.
	 * Methods should not be called after a simulation engine is disposed.
	 */
	void dispose();
	
	/**
	 * Creates a copy of the simulation engine which is owned by the caller.
	 * The copy is expected to share constants, state elements, and property objects.
	 * The new simulation engine must retain the same state as this simulation engine.
	 * The new simulation engine will be detached from the simulation service and must be disposed() by the owner.
	 * The simulation engine will not have any event listeners registered.
	 * @return the new simulation engine
	 */
	SimulationEngine fork();
	
	/**
	 * Returns a reference to the component instance being simulated.
	 * @return
	 */
	SystemInstance getSystemInstance(); 
	
	/**
	 * Returns a description of the type of simulation the engine is executing.
	 * @return
	 */
	String getSimulationType();
		
	void stepForward();
	void stepBackward();
	
	/**
	 * Queues a requests that the simulation engine notify the specified handler when it reaches that point in the simulation. The notification handler will be called in the simulation thread
	 * so the simulation will not continue until the notification handler returns. The simulation engine will notify event listeners of a state change once the notification handler has returned.
	 * @param handler
	 */
	void queueNotification(NotificationHandler handler);
	void clearPendingCommands();
	
	void addEventListener(final SimulationEngineEventListener listener);
	void removeEventListener(final SimulationEngineEventListener listener);
	
	SimulationEngineState getCurrentState();

	/**
	 * Sets the input constraint used to constrain the value of the element for the next frame. 
	 * @param element
	 * @param constraint may be null.
	 */
	void setInputConstraint(final Object element, final InputConstraint constraint);
	
	/**
	 * Resets input constraints while will be used for subsequent frames
	 */
	void resetInputConstraints();
}
