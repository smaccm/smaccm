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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;

import jkind.lustre.Expr;
import jkind.lustre.Program;
import jkind.lustre.builders.ProgramBuilder;

public class SimulationProgramBuilder {
	private final SimulationProgramType type;
	private final ComponentInstance componentInstance;
	private Program lustreProgram;
	private final Collection<Expr> initialConstraints;
	private final Collection<CarryVariable> carryVariables;
	private final Collection<SimulationVariable> variables;
	private final Collection<SimulationProperty> simulationProperties;
	private final AgreeRenaming agreeRenaming;
	private final Map<ComponentInstance, AgreeNode> componentInstanceToAgreeNodeMap;
	
	public SimulationProgramBuilder(final SimulationProgram program) {
		Objects.requireNonNull(program, "program must not be null");
		this.type = program.getType();
		this.componentInstance = program.getComponentInstance();
		this.lustreProgram = program.getLustreProgram();
		this.initialConstraints = new ArrayList<Expr>(program.getInitialConstraints());
		this.carryVariables = new ArrayList<CarryVariable>(program.getCarryVariables());
		this.variables = new ArrayList<SimulationVariable>(program.getVariables());
		this.simulationProperties = new ArrayList<SimulationProperty>(program.getSimulationProperties());
		this.agreeRenaming = program.getAgreeRenaming();
		this.componentInstanceToAgreeNodeMap = new HashMap<>(program.getComponentInstanceToAgreeNodeMap());
	}
	
	public SimulationProgramBuilder(final SimulationProgramType type, final ComponentInstance componentInstance, final Program lustreProgram, final AgreeRenaming agreeRenaming) {
		this.type = Objects.requireNonNull(type, "type must not be null");
		this.componentInstance = Objects.requireNonNull(componentInstance, "componentInstance must not be null");
		this.lustreProgram = new ProgramBuilder(Objects.requireNonNull(lustreProgram, "lustreProgram must not be null")).build();
		this.initialConstraints = new ArrayList<Expr>();
		this.carryVariables = new ArrayList<CarryVariable>();
		this.variables = new ArrayList<SimulationVariable>();
		this.simulationProperties = new ArrayList<SimulationProperty>();
		this.agreeRenaming = Objects.requireNonNull(agreeRenaming, "agreeRenaming must not be null");
		this.componentInstanceToAgreeNodeMap = new HashMap<>();
	}
	
	public void addInitialConstraint(final Expr c) {
		Objects.requireNonNull(c, "c must not be null");
		this.initialConstraints.add(c);
	}
	
	public void addCarryVariable(final CarryVariable var) {
		Objects.requireNonNull(var, "var must not be null");
		this.carryVariables.add(var);
	}
	
	public void addCarryVariables(final Iterable<CarryVariable> vars) {
		Objects.requireNonNull(vars, "vars must not be null");
		for(final CarryVariable var : vars) {
			addCarryVariable(var);
		}
	}
	
	public void setLustreProgram(final Program value) {
		Objects.requireNonNull(value, "value must not be null");
		this.lustreProgram = value;
	}
	
	public void addSimulationVariable(final SimulationVariable variable) {
		Objects.requireNonNull(variable, "variable must not be null");
		variables.add(variable);
	}
	
	public void addSimulationProperty(final SimulationProperty prop) {
		Objects.requireNonNull(prop, "prop must not be null");
		simulationProperties.add(prop);
	}

	public void addMapping(final ComponentInstance componentInstance, final AgreeNode agreeNode) {
		this.componentInstanceToAgreeNodeMap.put(componentInstance, agreeNode);
	}
	
	public SimulationProgram build() {
		return new SimulationProgram(type, componentInstance, lustreProgram, initialConstraints, carryVariables, variables, simulationProperties, agreeRenaming, componentInstanceToAgreeNodeMap);
	}
}
