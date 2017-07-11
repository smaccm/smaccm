package edu.uah.rsesc.aadlsimulator.agree.engine;

import java.util.Objects;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.values.Value;

public class SimulationStateReferenceEvaluator implements ReferenceEvaluator {
	public static interface SimulationStateProvider {
		AGREESimulationState getSimulationState();
	}
	
	private final SimulationStateProvider simStateProvider;
		
	public SimulationStateReferenceEvaluator(final SimulationStateProvider simStateProvider) {
		this.simStateProvider = Objects.requireNonNull(simStateProvider, "simStateProvider must not be null");
	}
	
	@Override
	public Expr getLustreExpression(final ElementRefExpression ref) {
		final AGREESimulationState simulationState = simStateProvider.getSimulationState();
		final String reference = String.join(".", ref.getIds());
		return new IdExpr(simulationState.getElementLustreId(simulationState.findElementByPath(reference)));
	}

	@Override
	public Value getLustreValue(final ElementRefExpression ref, int preLevel) {
		final AGREESimulationState simulationState = simStateProvider.getSimulationState();
		final int frameIndex = simulationState.getNumberOfFrames() - preLevel;
		if(frameIndex < 0) {
			throw new RuntimeException("Invalid value for preLevel: " + preLevel);
		}

		if(frameIndex >= simulationState.getNumberOfFrames()) {
			throw new RuntimeException("Unable to retrieve value for step: " + (frameIndex+1));
		}
		
		final String reference = String.join(".", ref.getIds());
		return simulationState.getElementLustreValue(frameIndex, simulationState.findElementByPath(reference));
	}
	
	@Override
	public Value getLustreValue(final ConstRefExpression ref, int preLevel) {
		final AGREESimulationState simulationState = simStateProvider.getSimulationState();
		final int frameIndex = simulationState.getNumberOfFrames() - preLevel;
		if(frameIndex < 0) {
			throw new RuntimeException("Invalid value for preLevel: " + preLevel);
		}

		if(frameIndex > simulationState.getNumberOfFrames()) {
			throw new RuntimeException("Unable to retrieve value for step: " + (frameIndex+1));
		}
		
		final String constantId = String.join("::", ref.getPackageSegments()) + "::" + ref.getConstantName();
		return simulationState.getConstantLustreValue(frameIndex, simulationState.findConstantById(constantId));
	}

}
