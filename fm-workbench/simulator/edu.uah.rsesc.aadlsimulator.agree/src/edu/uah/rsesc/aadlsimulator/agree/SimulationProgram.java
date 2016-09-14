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
package edu.uah.rsesc.aadlsimulator.agree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;

import jkind.lustre.Expr;
import jkind.lustre.Program;

public final class SimulationProgram {
	private final SimulationProgramType type;
	private final ComponentInstance componentInstance;
	private final Program lustreProgram;
	private final Collection<Expr> initialConstraints; // Constraints that should be used when executing first step of the simulation.
	private final Collection<CarryVariable> carryVariables;
	private final Collection<SimulationVariable> variables;
	private final Collection<SimulationProperty> simulationProperties;
	private final AgreeRenaming agreeRenaming;
	private final Map<ComponentInstance, AgreeNode> componentInstanceToAgreeNodeMap;
	
	public SimulationProgram(final SimulationProgramType type, final ComponentInstance componentInstance, final Program lustreProgram,
			final Collection<Expr> initialConstraints,
			final Collection<CarryVariable> carryVariables, 
			final Collection<SimulationVariable> variables,
			final Collection<SimulationProperty> simulationProperties,
			final AgreeRenaming agreeRenaming,
			final Map<ComponentInstance, AgreeNode> componentInstanceToAgreeNodeMap) {
		this.type = Objects.requireNonNull(type, "type must not be null");
		this.componentInstance = Objects.requireNonNull(componentInstance, "componentInstance must not be null");
		this.lustreProgram = Objects.requireNonNull(lustreProgram, "lustreProgram must not be null");
		this.initialConstraints = Collections.unmodifiableCollection(new ArrayList<Expr>(Objects.requireNonNull(initialConstraints, "initialConstraints must not be null")));
		this.carryVariables = Collections.unmodifiableCollection(new ArrayList<CarryVariable>(Objects.requireNonNull(carryVariables, "carryVariables must not be null")));
		this.variables = Collections.unmodifiableCollection(new ArrayList<SimulationVariable>(Objects.requireNonNull(variables, "variables must not be null")));
		this.simulationProperties = Collections.unmodifiableCollection(new ArrayList<SimulationProperty>(Objects.requireNonNull(simulationProperties, "simulationProperties must not be null")));
		this.agreeRenaming = Objects.requireNonNull(agreeRenaming, "agreeRenaming must not be null");
		this.componentInstanceToAgreeNodeMap = Collections.unmodifiableMap(new HashMap<>(Objects.requireNonNull(componentInstanceToAgreeNodeMap, "componentInstanceToAgreeNodeMap must not be null")));
	}
	
	public SimulationProgramType getType() {
		return type;
	}
	
	public ComponentInstance getComponentInstance() {
		return componentInstance;
	}
		
	public Program getLustreProgram() {
		return lustreProgram;
	}
	
	public Collection<Expr> getInitialConstraints() {
		return initialConstraints;
	}
	
	public Collection<CarryVariable> getCarryVariables() {
		return carryVariables;
	}
	
	public final Collection<SimulationVariable> getVariables() {
		return variables;
	}
	
	public final Collection<SimulationProperty> getSimulationProperties() {
		return simulationProperties;
	}
	
	public final AgreeRenaming getAgreeRenaming() {
		return agreeRenaming;
	}
	
	public Map<ComponentInstance, AgreeNode> getComponentInstanceToAgreeNodeMap() {
		return componentInstanceToAgreeNodeMap;
	}
	
	public Collection<AgreeNode> getAllAgreeNodes() {
		return componentInstanceToAgreeNodeMap.values();
	}
}
