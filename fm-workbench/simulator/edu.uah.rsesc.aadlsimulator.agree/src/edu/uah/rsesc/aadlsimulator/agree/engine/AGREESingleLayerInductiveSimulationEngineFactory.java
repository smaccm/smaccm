package edu.uah.rsesc.aadlsimulator.agree.engine;

import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;
import edu.uah.rsesc.aadlsimulator.ExceptionHandler;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineFactory;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramType;

public class AGREESingleLayerInductiveSimulationEngineFactory implements SimulationEngineFactory {
	@Override
	public boolean isCompatible(ComponentImplementation ci) {
		return AGREESimulationEngineFactoryHelper.isCompatible(ci);
	}
	
	@Override
	public SimulationEngine create(final SystemInstance systemInstance, final ExceptionHandler exceptionHandler) {
		return AGREESimulationEngineFactoryHelper.createSimulationEngine(systemInstance, exceptionHandler, SimulationProgramType.SINGLE_LAYER_INDUCTIVE);
	}
}
