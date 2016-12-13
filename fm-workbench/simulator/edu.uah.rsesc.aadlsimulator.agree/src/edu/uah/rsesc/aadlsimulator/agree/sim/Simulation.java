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
package edu.uah.rsesc.aadlsimulator.agree.sim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jkind.JKindException;
import jkind.api.JKindApi;
import jkind.api.KindApi;
import jkind.api.results.JKindResult;
import jkind.api.results.PropertyResult;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Program;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.values.BooleanValue;
import jkind.results.Counterexample;
import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.Signal;
import jkind.results.UnknownProperty;
import jkind.results.ValidProperty;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferencesUtil;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProperty;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.transformation.CreateSimulationGuarantee;

public class Simulation {
	private final SimulationProgram program; 
	private final Deque<SimulationFrameResults> results; // Stack for storing results for each simulation frame.
	
	public Simulation(final SimulationProgram program) {
		this.program = Objects.requireNonNull(program, "program must not be null");
		this.results = new LinkedList<SimulationFrameResults>();
	}
	
	public Simulation(Simulation sim) {
		this.program = sim.program;
		this.results = new LinkedList<SimulationFrameResults>(sim.results);
	}
	
	public SimulationProgram getProgram() {
		return program;
	}
	
	public boolean canStepForward() {
		final SimulationState state = getState();
		return state == SimulationState.WAITING_FOR_COMMANDS || state == SimulationState.WARNING_PROPERTY_NOT_SATISFIED;
	}
	
	public void stepForward(final Collection<Expr> constraints, final Set<SimulationProperty> disabledProperties) throws InterruptedException {
		Objects.requireNonNull(constraints, "constraints must not be null");

		final List<Expr> assertions = new ArrayList<Expr>();
		FrameAssertionHelper.addNextFrameAssertions(program, getLastResults(), assertions);
		assertions.addAll(constraints);
		results.addFirst(executeFrame(assertions, disabledProperties));
	}
	
	public void stepBackward() {
		results.removeFirst();
	}
	
	public boolean canStepBackward() {
		return !results.isEmpty();
	}
	
	public SimulationFrameResults getLastResults() {
		return results.peekFirst();
	}
	
	public SimulationState getState() {
		return results.isEmpty() ? SimulationState.WAITING_FOR_COMMANDS : getLastResults().getState();
	}
	
	private SimulationFrameResults executeFrame(final List<Expr> assertions, final Set<SimulationProperty> disabledProperties) throws InterruptedException {
		assert assertions != null;

		// Build the final constrained lustre specification for the frame.
		final ProgramBuilder programBuilder = new ProgramBuilder(program.getLustreProgram());
		programBuilder.clearNodes();
		final NodeBuilder nodeBuilder = new NodeBuilder(program.getLustreProgram().getMainNode());

		// Add assignments for the sim assertions signal 
		// Actual assertions are not used because they can result in an inconsistent Lustre program which will prevent
		// the set of support from being generated when using yices.
		Expr prevSimAssertionExpr = new BoolExpr(true);
		for(int assertionIndex = 0; assertionIndex < assertions.size(); assertionIndex++) {
			final String simAssertionSignalId = CreateSimulationGuarantee.SIMULATION_ASSERTIONS_ID + assertionIndex;
			final IdExpr simAssertionExpr = new IdExpr(simAssertionSignalId);
			nodeBuilder.addLocal(new VarDecl(simAssertionSignalId, NamedType.BOOL));
			nodeBuilder.addEquation(new Equation(simAssertionExpr, new BinaryExpr(prevSimAssertionExpr, BinaryOp.AND, assertions.get(assertionIndex))));
			prevSimAssertionExpr = simAssertionExpr;
		}
		nodeBuilder.addEquation(new Equation(new IdExpr(CreateSimulationGuarantee.SIMULATION_ASSERTIONS_ID), prevSimAssertionExpr));
		
		// Add assignments for property enablement variables
		for(final SimulationProperty simProp : program.getSimulationProperties()) {
			if(simProp.getEnablementVariableId() != null) {
				nodeBuilder.addEquation(new Equation(new IdExpr(simProp.getEnablementVariableId()), new BoolExpr(disabledProperties.contains(simProp) ? false : true)));
			}
		}
		
		// Build the lustre program for the frame		
		programBuilder.addNode(nodeBuilder.build());
		final Program constrainedLustreProgram = programBuilder.build();

		// Prepare to execute JKind
		final KindApi api = PreferencesUtil.getKindApi();
		
		// Enable IVC Reduction capability if using JKind
		if(api instanceof JKindApi) {
			final JKindApi jkindApi = (JKindApi)api;
			jkindApi.setIvcReduction();
		}
		
		// Execute JKind
		final JKindResult result = new JKindResult("Simulation");
		try {			
			final IProgressMonitor currentMonitor = new NullProgressMonitor();
			api.execute(constrainedLustreProgram, result, currentMonitor);

			// Create a model state from the results.
			final PropertyResult propertyResult = result.getPropertyResult(CreateSimulationGuarantee.SIMULATION_GUARANTEE_ID);
			final Property property = propertyResult.getProperty();
			if(property == null) {
				throw new AGREESimulatorException("Unexpected case. Unable to read property results", constrainedLustreProgram);
			} else if(property instanceof InvalidProperty) {
				final InvalidProperty invalidProperty = (InvalidProperty)property;
				final Counterexample counterexample = invalidProperty.getCounterexample();
				if(counterexample.getLength() != 1) {
					throw new AGREESimulatorException("Unexpected case. Counterexample has " + counterexample.getLength() + " steps", constrainedLustreProgram);
				}
				
				SimulationState newState = SimulationState.WAITING_FOR_COMMANDS;
				
				// Check that all simulation properties have been satisfied. If a required property is not satisfied then the simulation property would not be false
				// and a counterexample will not have been generated. This should only occur when a disabled property, lemma, top-level guarantee, or a non-top-level assumption is false.
				for(final SimulationProperty simulationProp : program.getSimulationProperties()) {
					if(!disabledProperties.contains(simulationProp)) {
						for(final String propLustreId : simulationProp.getLustreIds()) {
							final Signal<BooleanValue> signal = counterexample.getBooleanSignal(propLustreId);
							if(signal == null) {
								throw new AGREESimulatorException("Unable to get signal for guarantee property: " + propLustreId, constrainedLustreProgram);
							} else {
								if(!signal.getValue(0).value) {
									newState = SimulationState.WARNING_PROPERTY_NOT_SATISFIED;
									break;
								}
							}
						}
					}
				}
				
				return new SimulationFrameResults(constrainedLustreProgram, counterexample, disabledProperties, newState);
			} else if(property instanceof UnknownProperty) {
				return new SimulationFrameResults(constrainedLustreProgram, assertions, disabledProperties, SimulationState.HALTED_UNABLE_TO_SATISFY_CONSTRAINTS);
			} else if(property instanceof ValidProperty) {
				return new SimulationFrameResults(constrainedLustreProgram, assertions, disabledProperties, ((ValidProperty) property).getIvc(), SimulationState.HALTED_UNABLE_TO_SATISFY_CONSTRAINTS);
			} else {
				throw new AGREESimulatorException("Unhandled case. Property is of type: " + property.getClass(), constrainedLustreProgram);
			}
		} catch(JKindException ex) {
			if(ex.getCause() instanceof InterruptedException) {
				throw (InterruptedException)ex.getCause();
			}
			
			throw new AGREESimulatorException(constrainedLustreProgram, ex, result.getText());
		}
	}
}
