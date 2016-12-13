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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayType;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordType;
import jkind.lustre.Type;
import jkind.lustre.TypeDef;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;

import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramType;
import edu.uah.rsesc.aadlsimulator.agree.SimulationVariable;

class AGREESimulationStateElementFactory {
	/**
	 * Creates and returns the root state elements for a specified simulation program.
	 * @param program
	 * @return
	 */
	public static List<AGREESimulationStateElement> createStateElements(final SimulationProgram program) {
		Objects.requireNonNull(program, "program must not be null");
		
		// Build a component instance to simulation variable collection map
		final Map<ComponentInstance, Collection<SimulationVariable>> componentInstanceToVariablesMap = new HashMap<ComponentInstance, Collection<SimulationVariable>>();
		for(final SimulationVariable var : program.getVariables()) {
			if(!componentInstanceToVariablesMap.containsKey(var.getComponentInstance())) {
				componentInstanceToVariablesMap.put(var.getComponentInstance(), new ArrayList<SimulationVariable>());
			}
			
			componentInstanceToVariablesMap.get(var.getComponentInstance()).add(var);
		}
		
		// Build a type id to type map
		final Map<String, jkind.lustre.Type> typeIdToTypeMap = new HashMap<String, jkind.lustre.Type>();
		for(final TypeDef typeDef : program.getLustreProgram().types) {
			typeIdToTypeMap.put(typeDef.id, typeDef.type);
		}
		
		return Collections.unmodifiableList(createVariableStateElements(null, program.getType(), program.getComponentInstance(), null, componentInstanceToVariablesMap, typeIdToTypeMap, program.getComponentInstanceToAgreeNodeMap(), true));
	}

	private static List<AGREESimulationStateElement> createVariableStateElements(final AGREESimulationStateElement parent, final SimulationProgramType programType, final ComponentInstance componentInstance, final TimingModel parentTimingModel,
			final Map<ComponentInstance, Collection<SimulationVariable>> componentInstanceToVariablesMap, 
			final Map<String, jkind.lustre.Type> typeIdToTypeMap, final Map<ComponentInstance, AgreeNode> componentInstanceToAgreeNodeMap,
			boolean includeSubcomponents) {
		assert componentInstance != null;
		assert componentInstanceToVariablesMap != null;
		assert typeIdToTypeMap != null;
		
		final List<AGREESimulationStateElement> elements = new ArrayList<AGREESimulationStateElement>();

		// Get the timing model for the component instance
		final AgreeNode agreeNode = componentInstanceToAgreeNodeMap.get(componentInstance);
		final TimingModel timingModel = agreeNode == null ? null : agreeNode.timing;
		
		// Create elements for subcomponents
		if(includeSubcomponents) {
			for(final ComponentInstance child : componentInstance.getComponentInstances()) {
				final AGREESimulationStateElement newElement = new AGREESimulationStateElement(parent, child.getFullName(), edu.uah.rsesc.aadlsimulator.VariableType.NONE, null, child, child.getSubcomponent(), false);
				newElement.setChildren(createVariableStateElements(newElement, programType, child, timingModel, componentInstanceToVariablesMap, typeIdToTypeMap, componentInstanceToAgreeNodeMap, programType.isMonolithic()));
				elements.add(newElement);
			}
		}

		// Create elements for simulation variables
		final Collection<SimulationVariable> variables = componentInstanceToVariablesMap.get(componentInstance);
		if(variables != null) {
			final boolean showClockVariables = parentTimingModel == TimingModel.ASYNC;
			for(SimulationVariable var : variables) {
				final boolean isClock = var.getLustreId().endsWith(AgreeASTBuilder.clockIDSuffix);
				boolean hidden = (var.getLustreId().contains("___") || var.getLustreId().startsWith("_")) && (!isClock || !showClockVariables);
				addChildElementsForVariable(elements, parent, var.getName(), var.getType(), new IdExpr(var.getLustreId()), typeIdToTypeMap, var.getFeatureInstance(), var.getDeclarativeReference(), hidden);
			}
		}		
		
		return elements;
	}

	private static void addChildElementsForVariable(final Collection<AGREESimulationStateElement> elements, final AGREESimulationStateElement parent, final String variableName, jkind.lustre.Type variableType, final Expr lustreExpr, final Map<String, jkind.lustre.Type> typeIdToTypeMap, final FeatureInstance featureInstance, final EObject declReference, final boolean hidden) {
		assert elements != null;
		assert variableName != null;
		assert variableType != null;
		assert lustreExpr != null;
		
		variableType = resolveType(variableType, typeIdToTypeMap);
		if(variableType == NamedType.INT || variableType == NamedType.REAL || variableType == NamedType.BOOL) {
			elements.add(new AGREESimulationStateElement(parent, variableName, lustreTypeToSimType(variableType), lustreExpr, featureInstance, declReference, hidden));
		} else if(variableType instanceof RecordType) {
			final AGREESimulationStateElement newElement = new AGREESimulationStateElement(parent, variableName, edu.uah.rsesc.aadlsimulator.VariableType.NONE, null, featureInstance, declReference, hidden);
			
			final RecordType recordType = (RecordType)variableType;
			final List<AGREESimulationStateElement> recordElements = new ArrayList<AGREESimulationStateElement>();
			for(final Entry<String, jkind.lustre.Type> field : recordType.fields.entrySet()) {
				addChildElementsForVariable(recordElements, newElement, field.getKey(), field.getValue(), new RecordAccessExpr(lustreExpr, field.getKey()), typeIdToTypeMap, null, null, hidden);
			}
			
			newElement.setChildren(recordElements);
			elements.add(newElement);
		} else if(variableType instanceof ArrayType) {
			final ArrayType arrayType = (ArrayType)variableType;
			final Type elementType = arrayType.base;
			for(int i = 0; i < arrayType.size; i++) {
				final String indexStr = "[" + i + "]";				
				addChildElementsForVariable(elements, parent, variableName + indexStr, elementType, new ArrayAccessExpr(lustreExpr, i), typeIdToTypeMap, featureInstance, declReference, hidden);
			}
		} else {
			throw new RuntimeException("Unsupported variable type: " + variableType);
		}
	}

	private static edu.uah.rsesc.aadlsimulator.VariableType lustreTypeToSimType(final jkind.lustre.Type lustreType) {
		if(lustreType == NamedType.INT) {
			return edu.uah.rsesc.aadlsimulator.VariableType.INTEGER;
		} else if(lustreType == NamedType.REAL) {
			return edu.uah.rsesc.aadlsimulator.VariableType.REAL;
		} else if(lustreType == NamedType.BOOL) {
			return edu.uah.rsesc.aadlsimulator.VariableType.BOOLEAN;
		} else { // Unsupported type
			return edu.uah.rsesc.aadlsimulator.VariableType.NONE;
		}
	}
	
	/**
	 * Attempts to resolve a specified named type with a RecordType if applicable.
	 * @param typeIdToTypeMap
	 * @param type
	 * @return
	 */
	private static jkind.lustre.Type resolveType(jkind.lustre.Type type, final Map<String, jkind.lustre.Type> typeIdToTypeMap) {
		//  Try to resolve named types based on any typedefs
		while(type instanceof NamedType) {
			final jkind.lustre.Type newType = typeIdToTypeMap.get(((NamedType)type).name);
			if(newType == null) {
				break;
			}
			
			type = newType;
		}
		
		return type;
	}
}
