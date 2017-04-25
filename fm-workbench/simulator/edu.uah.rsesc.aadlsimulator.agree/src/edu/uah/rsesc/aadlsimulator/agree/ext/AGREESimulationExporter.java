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
package edu.uah.rsesc.aadlsimulator.agree.ext;

import jkind.api.results.Renaming;
import jkind.results.Counterexample;

import org.osate.aadl2.ComponentImplementation;

public interface AGREESimulationExporter {
	/**
	 * Provides the simulation state to the exporter extension.
	 */
	static interface ExportArguments {
		ComponentImplementation getComponentImplementation();
		
		/**
		 * Provides a counterexample which includes the values for every simulation frame. Signals are named based Lustre variable id's
		 */
		Counterexample getCounterexample();
		
		/**
		 * Provides a Renaming instance that maps from counterexample signal names to AGREE Names
		 */
		Renaming getAgreeRenaming();
	}
	
	/**
	 * Called by the simulator to transfer the AGREE simulation state to an extension.
	 * @param args 
	 */
    void export(ExportArguments args);
}
