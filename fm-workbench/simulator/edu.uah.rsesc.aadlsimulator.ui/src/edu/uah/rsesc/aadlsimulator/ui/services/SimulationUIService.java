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
package edu.uah.rsesc.aadlsimulator.ui.services;

/**
 * All methods should only be called from the user interface thread.
 *
 */
public interface SimulationUIService {
	/**
	 * 
	 * @return the current state. Never returns null.
	 */
	SimulatorState getCurrentState();
	
	void addStateChangeListener(final SimulatorStateListener listener);
	
	void removeStateChangeListener(final SimulatorStateListener listener);
		
	void setSelectedFrameIndex(final int frameIndex);
	
	boolean canStepForward();

	boolean canStepBackward();
	
	/**
	 * Automatically called by stepForward(). Notifies listener that a step is about to be taken. Throws exception if
	 * the step is canceled.
	 * @throws StepForwardCanceledException
	 */
	void prepareToStepForward() throws StepForwardCanceledException;
	
	/**
	 * Steps the current simulation forward using the currently specified input constraints
	 * @return
	 */
	void stepForward();

	/*
	 * These methods allow disabling the simulation controls. For use by handlers which automate the control of the simulation.
	 */
	void lockUserInterface();
	void unlockUserInterface();
	boolean isUserInterfaceLocked();
}
