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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import java.util.Map.Entry;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.DefaultAnnexLibrary;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.workspace.WorkspacePlugin;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Program;
import jkind.lustre.values.ArrayValue;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.EnumValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.RecordValue;
import jkind.lustre.values.TupleValue;
import jkind.lustre.values.Value;
import jkind.util.BigFraction;
import edu.uah.rsesc.aadlsimulator.Rational;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.VariableType;
import edu.uah.rsesc.aadlsimulator.agree.LustreProgramProvider;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProperty;
import edu.uah.rsesc.aadlsimulator.agree.eval.EvaluationException;
import edu.uah.rsesc.aadlsimulator.agree.eval.Evaluator;
import edu.uah.rsesc.aadlsimulator.agree.sim.AGREESimulatorException;
import edu.uah.rsesc.aadlsimulator.agree.sim.FrameAssertionHelper;
import edu.uah.rsesc.aadlsimulator.agree.sim.SimulationFrameResults;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;

// Immutable data structure for storing the AGREE simulation engine state
// State elements are guaranteed not to change between simulation frames.
public class AGREESimulationState implements SimulationEngineState, LustreProgramProvider {
	private final SimulationProgram simulationProgram;
	private final String statusDescription;
	private final boolean isError;
	private final boolean canStepForward;
	private final boolean canStepBackward;
	private final List<AGREESimulationStateElement> rootElements;
	private final List<FrameInfo> frameInfos;
	private final Map<String, AGREESimulationStateElement> instanceObjectPathToStateElementMap;
	private final Map<String, AGREESimulationStateElement> variablePathToStateElementMap; 
	private final Set<SimulationProperty> disabledProperties;
	private final Map<String, Map<String, ConstStatement>> packageToConstantsMap; // Mapping between lowercase package names to a mapping from constant names(case sensitive) to ConstStatement objects
	private final StepArguments nextStepArguments;
	private final Evaluator baseEvaluator;
	private Evaluator evaluator;
	private boolean evaluatorInitialized = false;
	
	public AGREESimulationState(final SimulationProgram simulationProgram, final String statusDescription, final boolean isError, final boolean canStepForward, final boolean canStepBackward, final List<AGREESimulationStateElement> rootElements, final List<FrameInfo> frameInfos) {
		this.simulationProgram = Objects.requireNonNull(simulationProgram, "simulationProgram must not be null");
		this.statusDescription = Objects.requireNonNull(statusDescription, "statusDescription must not be null");
		this.isError = isError;
		this.canStepForward = canStepForward;
		this.canStepBackward = canStepBackward;
		this.rootElements = Collections.unmodifiableList(new ArrayList<AGREESimulationStateElement>(Objects.requireNonNull(rootElements, "rootElements must not be null")));
		this.frameInfos = Collections.unmodifiableList(new ArrayList<FrameInfo>(Objects.requireNonNull(frameInfos, "frameInfos must not be null")));
		this.instanceObjectPathToStateElementMap = Collections.unmodifiableMap(createInstanceObjectPathToStateElementMap(rootElements));
		this.variablePathToStateElementMap = Collections.unmodifiableMap(createVariablePathToStateElementMap(rootElements));
		this.disabledProperties = Collections.emptySet();
		this.packageToConstantsMap = Collections.unmodifiableMap(buildPackageToConstantsMap(getRelevantProjects(simulationProgram.getComponentInstance().eResource()).toArray(new IProject[0])));
		this.nextStepArguments = new StepArguments(Collections.emptyMap(), Collections.emptyMap());
		this.baseEvaluator = new Evaluator(simulationProgram.getLustreProgram());
	}

	public AGREESimulationState(final AGREESimulationState baseState, final String statusDescription, final boolean isError) {
		Objects.requireNonNull(baseState, "baseState must not be null");
		this.simulationProgram = baseState.simulationProgram;
		this.statusDescription = Objects.requireNonNull(statusDescription, "statusDescription must not be null");
		this.isError = isError;
		this.canStepForward = baseState.canStepForward;
		this.canStepBackward = baseState.canStepBackward;
		this.rootElements = baseState.rootElements;
		this.frameInfos = baseState.frameInfos;
		this.instanceObjectPathToStateElementMap = baseState.instanceObjectPathToStateElementMap;
		this.variablePathToStateElementMap = baseState.variablePathToStateElementMap;
		this.disabledProperties = baseState.disabledProperties;
		this.packageToConstantsMap = baseState.packageToConstantsMap;
		this.nextStepArguments = baseState.nextStepArguments;
		this.baseEvaluator = baseState.baseEvaluator;
	}
	
	public AGREESimulationState(final AGREESimulationState baseState, final Set<SimulationProperty> disabledProperties) {
		Objects.requireNonNull(baseState, "baseState must not be null");
		this.simulationProgram = baseState.simulationProgram;
		this.statusDescription = baseState.statusDescription;
		this.isError = baseState.isError;
		this.canStepForward = baseState.canStepForward;
		this.canStepBackward = baseState.canStepBackward;
		this.rootElements = baseState.rootElements;
		this.frameInfos = baseState.frameInfos;
		this.instanceObjectPathToStateElementMap = baseState.instanceObjectPathToStateElementMap;
		this.variablePathToStateElementMap = baseState.variablePathToStateElementMap;
		this.disabledProperties = Collections.unmodifiableSet(new HashSet<>(disabledProperties));
		this.packageToConstantsMap = baseState.packageToConstantsMap;
		this.nextStepArguments = baseState.nextStepArguments;
		this.baseEvaluator = baseState.baseEvaluator;
	}
	
	public AGREESimulationState(final AGREESimulationState baseState, final StepArguments nextStepArguments) {
		Objects.requireNonNull(baseState, "baseState must not be null");
		this.simulationProgram = baseState.simulationProgram;
		this.statusDescription = baseState.statusDescription;
		this.isError = baseState.isError;
		this.canStepForward = baseState.canStepForward;
		this.canStepBackward = baseState.canStepBackward;
		this.rootElements = baseState.rootElements;
		this.frameInfos = baseState.frameInfos;
		this.instanceObjectPathToStateElementMap = baseState.instanceObjectPathToStateElementMap;
		this.variablePathToStateElementMap = baseState.variablePathToStateElementMap;
		this.disabledProperties = baseState.disabledProperties;
		this.packageToConstantsMap = baseState.packageToConstantsMap;
		this.nextStepArguments = Objects.requireNonNull(nextStepArguments, "nextStepArguments must not be null");
		this.baseEvaluator = baseState.baseEvaluator;
	}
	
	public AGREESimulationState(final AGREESimulationState baseState, final String statusDescription, final boolean isError, final boolean canStepForward, final boolean canStepBackward, final List<FrameInfo> frameInfos, final StepArguments nextStepArguments) {
		Objects.requireNonNull(baseState, "baseState must not be null");
		this.simulationProgram = baseState.simulationProgram;
		this.statusDescription = statusDescription;
		this.isError = isError;
		this.canStepForward = canStepForward;
		this.canStepBackward = canStepBackward;
		this.rootElements = baseState.rootElements;
		this.frameInfos = Collections.unmodifiableList(new ArrayList<FrameInfo>(Objects.requireNonNull(frameInfos, "frameInfos must not be null")));
		this.instanceObjectPathToStateElementMap = baseState.instanceObjectPathToStateElementMap;
		this.variablePathToStateElementMap = baseState.variablePathToStateElementMap;
		this.disabledProperties = baseState.disabledProperties;
		this.packageToConstantsMap = baseState.packageToConstantsMap;
		this.nextStepArguments = Objects.requireNonNull(nextStepArguments, "nextStepArguments must not be null");
		this.baseEvaluator = baseState.baseEvaluator;
	}
	
	@Override
	public String getStatusDescription() {
		return statusDescription;
	}

	@Override
	public boolean isStatusError() {
		return isError;
	}
	
	@Override
	public boolean canStepForward() {
		return canStepForward;
	}
	
	@Override
	public boolean canStepBackward() {
		return canStepBackward;
	}
	
	@Override
	public Collection<?> getRootElements() {
		return rootElements;
	}

	@Override
	public Object getParentElement(final Object element) {
		return asElement(element).getParent();
	}
	
	@Override
	public Collection<?> getChildElements(final Object element) {
		return asElement(element).getChildren();
	}

	@Override
	public String getElementName(final Object element) {
		return asElement(element).getName();
	}

	@Override
	public VariableType getElementType(final Object element) {
		return asElement(element).getType();
	}
	
	@Override
	public InstanceObject getElementInstanceObject(final Object element) {
		return asElement(element).getInstanceObject();
	}
	
	@Override
	public EObject getElementDeclarativeObject(final Object element) {
		return asElement(element).getDeclarativeObject();
	}
	
	@Override
	public final int getNumberOfFrames() {
		return frameInfos.size();
	}

	@Override
	public InputConstraint getElementInputConstraintForNextFrame(final Object element) {
		return nextStepArguments.getElementToNextInputConstraintMap().get(element);
	}

	@Override
	public Object getElementValue(final int frameIndex, final Object element) {
		final Value value = getElementLustreValue(frameIndex, element);
		if(value == null) {
			return null;
		}
		
		return convertLustreValue(value);
	}
	
	// Returns the raw value stored in the counterexample provided by JKind
	public Value getElementLustreValue(final int frameIndex, final Object element) {
		if(frameIndex < 0 || frameIndex > frameInfos.size()) {
			throw new IllegalArgumentException("frameIndex is outside the valid range");
		}
		
		final String lustreId = asElement(element).getLustreId();		
		if(lustreId == null) {
			return null;
		}		
		
		// Get the next value
		if(frameIndex == frameInfos.size()) {
			// Don't try to evaluate the next step if the simulation is in a state such that it cannot step forward.
			// Evaluation depends on frame results which are not available when the simulation cannot step forward.
			if(!canStepForward) {
				return null;
			}

			// Lazy create the evaluator
			if(!evaluatorInitialized) {				
				evaluator = createEvaluator(nextStepArguments.getElementToNextLustreConstraintMap().values(), disabledProperties);
				evaluatorInitialized = true; // Evaluator may be null if evaluator creation failed
			}
			
			if(evaluator == null) {
				return null;
			}
			
			return evaluator.evalOrNull(lustreId);
		} else {
			// Get a previous value
			return frameInfos.get(frameIndex).getFrameResults().getValue(lustreId);
		}
	}
	
	@Override
	public boolean isElementHidden(final Object element) {
		return asElement(element).isHidden();
	}

	@Override
	public boolean isElement(final Object obj) {
		return obj instanceof AGREESimulationStateElement;
	}
	
	public String getElementLustreId(final Object element) {
		return asElement(element).getLustreId();
	}
	
	public String getAgreeName(final Object element) {
		final String lustreId = getElementLustreId(element);
		return lustreId == null ? null : simulationProgram.getAgreeRenaming().rename(lustreId);
	}
	
	private AGREESimulationStateElement asElement(final Object element) {
		if(!(element instanceof AGREESimulationStateElement)) {
			throw new IllegalArgumentException("element is not a AGREESimulationFrameStateElement");
		}
		
		return (AGREESimulationStateElement)element;
	}
	
	/**
	 * Converts a JKind API Lustre value to types that are independent of the JKind API. In cases where there isn't an equivalent type in the standard
	 * Java API, the original type will be preserved. Throws an 
	 * @param value non-null value object.
	 * @throws RuntimeException if the value is not of a supported type.
	 * @return
	 */
	private Object convertLustreValue(final Value value) {
		assert value != null;
		
		if(value instanceof ArrayValue) {
			final ArrayValue arrayValue = (ArrayValue)value;
			final ArrayList<Object> newList = new ArrayList<Object>(arrayValue.elements.size());
			for(final Value childValue : arrayValue.elements) {
				newList.add(convertLustreValue(childValue));
			}
			return newList;
		} else if(value instanceof BooleanValue) {
			return ((BooleanValue)value).value;
		}  else  if(value instanceof EnumValue) {
			return ((EnumValue)value).value;
		}  else if(value instanceof IntegerValue) {
			return ((IntegerValue)value).value;
		} else if(value instanceof RealValue) {
			final BigFraction fraction = ((RealValue)value).value;
			return new Rational(fraction.getNumerator(), fraction.getDenominator());
		} else if(value instanceof RecordValue) {
			final RecordValue recordValue = (RecordValue)value;
			final Map<String, Object> newMap = new HashMap<String, Object>();
			for(final Entry<String, Value> entry : recordValue.fields.entrySet()) {
				newMap.put(entry.getKey(), convertLustreValue(entry.getValue()));
			}
			return newMap;
		}  else if(value instanceof TupleValue) {
			final TupleValue tupleValue = (TupleValue)value;
			final ArrayList<Object> newList = new ArrayList<Object>(tupleValue.elements.size());
			for(final Value childValue : tupleValue.elements) {
				newList.add(convertLustreValue(childValue));
			}
			return newList;
		} else {
			throw new RuntimeException("Unhandled case. Value is of type: " + value.getClass());
		}
	}
	
	@Override
	public Program getLustreProgram() {
		if(frameInfos.size() == 0) {
			return simulationProgram.getLustreProgram();
		}
		
		return frameInfos.get(frameInfos.size()-1).getFrameResults().getLustreProgram();
	}
	
	// A mapping from the instance object map to the state element used because the same exact instance object will not be shared between different components which access the state. For example, the graphical editor.
	@Override
	public Object findElement(final InstanceObject io) {
		Objects.requireNonNull(io, "io must not be null");
		return instanceObjectPathToStateElementMap.get(io.getInstanceObjectPath());
	}
	
	@Override
	public Object findElementByPath(final String path) {
		Objects.requireNonNull(path, "path must not be null");
		return variablePathToStateElementMap.get(path);
	}
	
	private static Map<String, AGREESimulationStateElement> createInstanceObjectPathToStateElementMap(final List<AGREESimulationStateElement> elements) {
		final Map<String, AGREESimulationStateElement> result = new HashMap<>();
		populateInstanceObjectPathToStateElementMap(result, elements);
		return result;
	}
	
	private static void populateInstanceObjectPathToStateElementMap(final Map<String, AGREESimulationStateElement> instanceObjectPathToElementMap, final List<AGREESimulationStateElement> elements) {
		for(final AGREESimulationStateElement e : elements) {
			if(e.getInstanceObject() != null) {
				final InstanceObject io = e.getInstanceObject();
				instanceObjectPathToElementMap.put(io.getInstanceObjectPath(), e);
			}

			populateInstanceObjectPathToStateElementMap(instanceObjectPathToElementMap, e.getChildren());
		}
	}

	private static Map<String, AGREESimulationStateElement> createVariablePathToStateElementMap(final List<AGREESimulationStateElement> elements) {
		final Map<String, AGREESimulationStateElement> result = new HashMap<>();
		populateVariablePathToStateElementMap(result, elements, "");
		return result;
	}
	
	private static void populateVariablePathToStateElementMap(final Map<String, AGREESimulationStateElement> variablePathToElementMap, final List<AGREESimulationStateElement> elements, final String prefix) {
		for(final AGREESimulationStateElement e : elements) {
			final String path = prefix + e.getName();
			variablePathToElementMap.put(path, e);
			populateVariablePathToStateElementMap(variablePathToElementMap, e.getChildren(), path + ".");
		}
	}
	
	@Override
	public Collection<?> getProperties() {
		return simulationProgram.getSimulationProperties();
	}

	@Override
	public boolean isProperty(Object obj) {
		return obj instanceof SimulationProperty;
	}
	
	@Override
	public String getPropertyName(final Object property) {
		return asProperty(property).getDescription();
	}

	@Override
	public Object getPropertyBusinessObject(final Object property) {
		return asProperty(property).getDeclarativeModelReference();
	}

	@Override
	public AGREEPropertyStatus getPropertyStatus(final Object property) {
		if(frameInfos.size() == 0) {
			return AGREEPropertyStatus.INITIAL_STEP;
		}
		
		// Handle care where counterexample is not returned. Simulation is halted.
		final SimulationFrameResults frameResults = frameInfos.get(frameInfos.size()-1).getFrameResults();
		if(frameResults.getDisabledProperties().contains(property)) {
			return AGREEPropertyStatus.DISABLED;
		}
		
		if(frameResults.hasCounterexample()) {
			for(final String propLustreId : asProperty(property).getLustreIds()) {
				final Value value = frameResults.getValue(propLustreId);
				if(!(value instanceof BooleanValue)) {
					throw new RuntimeException("Value is not a boolean value");
				}
				
				final boolean propertyValue = ((BooleanValue)value).value;
				if(!propertyValue) {
					return AGREEPropertyStatus.UNSATISFIED_WARNING; 
				}
			}

			return AGREEPropertyStatus.SATISFIED;
		} else {
			if(frameResults.hasInductiveValidityCore()) {
				for(final String propLustreId : asProperty(property).getLustreIds()) {
					if(frameResults.isInInductiveValidityCore(propLustreId)) {
						return AGREEPropertyStatus.UNSATISFIED_ERROR;
					}
				}
				
				return AGREEPropertyStatus.HALTED_NOT_IN_SET_OF_SUPPORT;
			} else {
				return AGREEPropertyStatus.HALTED_SET_OF_SUPPORT_UNAVAILABLE;
			}
		}
	}

	@Override
	public String getPropertyDetails(final Object property) {
		final AGREEPropertyStatus status = getPropertyStatus(property);
		switch(status) {
		case INITIAL_STEP:
			return "";
			
		case HALTED_SET_OF_SUPPORT_UNAVAILABLE:
			return "Simulation halted.";
			
		case SATISFIED:
			return "";
			
		case UNSATISFIED_ERROR:
			return "Error: Required property not satisfied. Simulation halted. \n\n" + getPropertyTokenText(asProperty(property));
			
		case UNSATISFIED_WARNING:
			return "Warning: Desired property not satisfied. \n\n" + getPropertyTokenText(asProperty(property));
			
		case DISABLED:
			return "Disabled";
			
		default:
			return "";		
		}
	}
	
	@Override
	public boolean isPropertyEnabledForNextFrame(final Object property) {
		return !disabledProperties.contains(property);
	}
	
	private String getPropertyTokenText(final SimulationProperty simProp) {
		final INode node = NodeModelUtils.getNode(simProp.getDeclarativeModelReference());
		return node == null ? "" : NodeModelUtils.getTokenText(node);
	}
	
	private SimulationProperty asProperty(final Object property) {
		if(!(property instanceof SimulationProperty)) {
			throw new IllegalArgumentException("property is not a SimulationProperty");
		}
		
		return (SimulationProperty)property;
	}
	
	// Constants
	@Override
	public VariableType getConstantType(final Object constant) {
		final ConstStatement constStatement = asConstant(constant);
		return getType(constStatement);
	}
	
	private static VariableType getType(final ConstStatement constStatement) {	
		// Only simple expressions are supported for constants.
		if(constStatement.getType() instanceof PrimType) {
			switch (((PrimType)constStatement.getType()).getName()) {
	        case "int":
	        	return constStatement.getExpr() instanceof IntLitExpr ? VariableType.INTEGER : null;
	        			
	        case "real":
	        	return constStatement.getExpr() instanceof RealLitExpr ? VariableType.REAL : null;
	        	
	        case "bool":
	        	return constStatement.getExpr() instanceof BoolLitExpr ? VariableType.BOOLEAN: null;
			}
		}

		return null;
	}
	
	@Override
	public EObject getConstantDeclarativeObject(Object constant) {
		return asConstant(constant);
	}
	
	@Override
	public Object getConstantValue(final int frameIndex, final Object constant) {
		final Value value = getConstantLustreValue(frameIndex, constant);
		if(value == null) {
			return null;
		}
		
		return convertLustreValue(value);
	}
	
	// Returns the raw value stored in the counterexample provided by JKind
	public Value getConstantLustreValue(final int frameIndex, final Object constant) {
		if(frameIndex < 0 || frameIndex > frameInfos.size()) {
			throw new IllegalArgumentException("frameIndex is outside the valid range");
		}
		
		final ConstStatement constStatement = asConstant(constant);
		final com.rockwellcollins.atc.agree.agree.Expr agreeExpr = constStatement.getExpr();
		if(agreeExpr instanceof IntLitExpr) {
			return new IntegerValue(new BigInteger(((IntLitExpr) agreeExpr).getVal(), 10));
		} else if(agreeExpr instanceof RealLitExpr) {
			BigDecimal bd = new BigDecimal(((RealLitExpr) agreeExpr).getVal());
			if(bd.scale() < 0) {
				bd = bd.setScale(0);
			}
			
			return new RealValue(new BigFraction(bd.unscaledValue(), BigInteger.TEN.pow(bd.scale())));
		} else if(agreeExpr instanceof BoolLitExpr) {
			return BooleanValue.fromBoolean(((BoolLitExpr) agreeExpr).getVal().getValue());
		} else {
			return null;
		}
	}
	
	private ConstStatement asConstant(final Object constant) {
		if(!(constant instanceof ConstStatement)) {
			throw new IllegalArgumentException("constant is not a ConstStatement");
		}
		
		return (ConstStatement)constant;
	}
	
	/**
	 * Retrieved an element using an absolute path.
	 * @param packageName is the AADL package name. Case insensitive. For example a::b
	 * @return
	 */
	@Override
	public Object findConstantById(String id) {
		id = id.toLowerCase();
		final int index = id.lastIndexOf("::");
		if(index < 0) {
			return null;
		}
		
		final String pkgName = id.substring(0, index);
		final String constantName = id.substring(index+2, id.length());
		final Map<String, ConstStatement> constMap = packageToConstantsMap.get(pkgName);
		return constMap == null ? null : constMap.get(constantName);
	}
	
	@Override
	public Stream<String> getConstantIds() {
		return packageToConstantsMap.entrySet().stream().flatMap(e -> e.getValue().values().stream().map(c -> e.getKey() + "::" + c.getName()));
	}
	
	private Evaluator createEvaluator(final Collection<Expr> constraints, final Set<SimulationProperty> disabledProperties) {
		try {
			final SimulationFrameResults lastFrameResults = frameInfos.size() == 0 ? null : frameInfos.get(frameInfos.size()-1).getFrameResults();	
			final List<Expr> assertions = new ArrayList<Expr>();
			FrameAssertionHelper.addNextFrameAssertions(simulationProgram, lastFrameResults, assertions);
			assertions.addAll(constraints);
			
			// Add assertions for property enablement variables
			for(final SimulationProperty simProp : simulationProgram.getSimulationProperties()) {
				if(simProp.getEnablementVariableId() != null) {
					assertions.add(new BinaryExpr(new IdExpr(simProp.getEnablementVariableId()), BinaryOp.EQUAL, new BoolExpr(disabledProperties.contains(simProp) ? false : true)));
				}
			}
			
			// Create the new evaluator
			return new Evaluator(baseEvaluator, assertions);
		} catch(EvaluationException ex) {
			return null;
		}
	}
	
	private static Map<String, Map<String, ConstStatement>> buildPackageToConstantsMap(final IProject[] projects) {
		// Build a mapping between lower case package names and a map between constant names to constant statements
		final Map<String, Map<String, ConstStatement>> packageToConstantsMap = new HashMap<>();
		try {
			final Set<IFile> aadlFiles = new HashSet<>();
			getAadlFiles(projects, aadlFiles);
			
			for(final IFile aadlFile : aadlFiles) {
				final Resource aadlRes = OsateResourceUtil.getResource(aadlFile);
				if(aadlRes != null && !aadlRes.getContents().isEmpty()) {
					final EObject content = aadlRes.getContents().get(0);
					if(content instanceof AadlPackage) {
						// Find all AGREE Libraries
						final AadlPackage pkg = (AadlPackage)content;
						if(pkg.getPublicSection() != null && pkg.getName() != null) {
							final Map<String, ConstStatement> constants = new HashMap<>();
							packageToConstantsMap.put(pkg.getQualifiedName().toLowerCase(), constants);
							for(final AnnexLibrary lib : pkg.getPublicSection().getOwnedAnnexLibraries()) {
								// Look for AGREE annex libraries
								if(lib instanceof DefaultAnnexLibrary && "agree".equalsIgnoreCase(lib.getName())) {
									final AnnexLibrary parsedLib = ((DefaultAnnexLibrary)lib).getParsedAnnexLibrary();
									if(parsedLib instanceof AgreeContractLibrary) {
										final AgreeContractLibrary agreeContractLib = (AgreeContractLibrary)parsedLib;
										if(agreeContractLib.getContract() instanceof AgreeContract) {
											final AgreeContract agreeContract = (AgreeContract)agreeContractLib.getContract();
											for(final SpecStatement spec : agreeContract.getSpecs()) {
												if(spec instanceof ConstStatement) {
													final ConstStatement constStatement = (ConstStatement)spec;
													// Check that the statement is of a supported type
													if(getType(constStatement) != null) {
														constants.put(constStatement.getName().toLowerCase(), constStatement);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (CoreException e) {
			throw new AGREESimulatorException(null, e, "Unable to build constants map");
		}
		
		return packageToConstantsMap;
	}
	
	private static void getAadlFiles(final IResource[] resources, final Set<IFile> aadlFiles) throws CoreException {
		for(final IResource res : resources) {
			if(res.isAccessible()) {
				if(res instanceof IContainer) {
					getAadlFiles(((IContainer)res).members(), aadlFiles);
				} else if(res instanceof IFile) {
					final IFile file = (IFile)res;
					if(WorkspacePlugin.SOURCE_FILE_EXT.equalsIgnoreCase(file.getFileExtension())) {
						aadlFiles.add(file);
					}
				}
			}
		}		
	}
	
	/**
	 * Returns the set of projects that can be referenced from the project containing the diagram. 
	 * Recursive(Projects referenced by referenced projects are also included).
	 * @return
	 */
	private static Set<IProject> getRelevantProjects(final Resource resource) {
		try {
			final Set<IProject> projects = new HashSet<IProject>();
			final IProject diagramProject = getProject(resource);
			projects.add(diagramProject);
			addReferencedProjects(diagramProject, projects);

			return projects;
		} catch(final CoreException ex) {
			throw new RuntimeException(ex);
		}		 
	}
	
	private static void addReferencedProjects(final IProject project, final Set<IProject> results) throws CoreException {
		for(final IProject rp : project.getReferencedProjects()) {
			if(!results.contains(rp)) {
				results.add(rp);
				addReferencedProjects(rp, results);
			}
		}
	}

	private static IProject getProject(final Resource resource) {
		if(resource != null && resource.getURI() != null) {
			final IPath projectPath = new Path(resource.getURI().toPlatformString(true)).uptoSegment(1);
			final IResource projectResource = ResourcesPlugin.getWorkspace().getRoot().findMember(projectPath);
			if(projectResource instanceof IProject) {
				return (IProject)projectResource;
			}
		}

		return null;
	}
}
