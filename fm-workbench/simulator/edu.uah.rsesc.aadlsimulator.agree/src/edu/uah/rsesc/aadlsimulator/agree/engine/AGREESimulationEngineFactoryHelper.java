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
package edu.uah.rsesc.aadlsimulator.agree.engine;

import java.util.Objects;

import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.annexsupport.AnnexUtil;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;

import edu.uah.rsesc.aadlsimulator.ExceptionHandler;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramType;
import edu.uah.rsesc.aadlsimulator.agree.sim.Simulation;
import edu.uah.rsesc.aadlsimulator.agree.transformation.AgreeProgramToSimulationProgram;

class AGREESimulationEngineFactoryHelper {
	public static boolean isCompatible(final ComponentImplementation ci) {
		// Check that the component type contains an AGREE annex subclause
		final ComponentType componentType = ci.getType();
		return AnnexUtil.getAllAnnexSubclauses(componentType, AgreePackage.eINSTANCE.getAgreeContractSubclause()).size() > 0;
	}
	
	public static SimulationEngine createSimulationEngine(final SystemInstance systemInstance, final ExceptionHandler exceptionHandler, final SimulationProgramType type) {
		Objects.requireNonNull(systemInstance, "systemInstance must not be null");
		Objects.requireNonNull(exceptionHandler, "exceptionHandler must not be null");
		
		// Check that the component type is compatible
		if(!isCompatible(systemInstance.getComponentImplementation())) {
			final ComponentType componentType = systemInstance.getComponentClassifier().getType();
			throw new RuntimeException(componentType.getName() + " does not contain an AGREE annex subclause.");
		}
		
		final AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(systemInstance, type.isMonolithic());
		final SimulationProgram simulationProgram = AgreeProgramToSimulationProgram.transform(agreeProgram, type);
		final Simulation simulation = new Simulation(simulationProgram);
		return new AGREESimulationEngine(simulation, systemInstance, exceptionHandler);
	}
}
