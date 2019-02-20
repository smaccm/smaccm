package com.rockwellcollins.atc.agree.analysis.ast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AadlBoolean;
import org.osate.aadl2.AadlInteger;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AadlReal;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ConnectionEnd;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.EventPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.FeatureGroupType;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.NumberValue;
import org.osate.aadl2.Operation;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.AgreeTypeSystem;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr;
import com.rockwellcollins.atc.agree.agree.ArraySubExpr;
import com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssignStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.AsynchStatement;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CalenStatement;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.ComponentRef;
import com.rockwellcollins.atc.agree.agree.ConnectionStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.EnumLitExpr;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.ExistsExpr;
import com.rockwellcollins.atc.agree.agree.FlatmapExpr;
import com.rockwellcollins.atc.agree.agree.FloorCast;
import com.rockwellcollins.atc.agree.agree.FnDef;
import com.rockwellcollins.atc.agree.agree.FoldLeftExpr;
import com.rockwellcollins.atc.agree.agree.FoldRightExpr;
import com.rockwellcollins.atc.agree.agree.ForallExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IndicesExpr;
import com.rockwellcollins.atc.agree.agree.InitialStatement;
import com.rockwellcollins.atc.agree.agree.InputStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LatchedExpr;
import com.rockwellcollins.atc.agree.agree.LatchedStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.MNSynchStatement;
import com.rockwellcollins.atc.agree.agree.NamedElmExpr;
import com.rockwellcollins.atc.agree.agree.NamedID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.PatternStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealCast;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordDef;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SelectionExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.TagExpr;
import com.rockwellcollins.atc.agree.agree.ThisRef;
import com.rockwellcollins.atc.agree.agree.TimeExpr;
import com.rockwellcollins.atc.agree.agree.TimeFallExpr;
import com.rockwellcollins.atc.agree.agree.TimeOfExpr;
import com.rockwellcollins.atc.agree.agree.TimeRiseExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeCalendarUtils;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.MNSynchronyElement;
import com.rockwellcollins.atc.agree.analysis.TypeTable;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeAADLConnection.ConnectionType;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;
import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeInlineLatchedConnections;
import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeMakeClockedLustreNodes;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomater;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomaterRegistry;
import com.rockwellcollins.atc.agree.analysis.extentions.ExtensionRegistry;
import com.rockwellcollins.atc.agree.analysis.linearization.LinearizationRewriter;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.IdGatherer;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreeCauseEffectPattern;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePatternBuilder;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePatternTranslator;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePeriodicPattern;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreeSporadicPattern;
import com.rockwellcollins.atc.agree.analysis.translation.LustreExprFactory;

import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.FunctionCallExpr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;


public class AgreeASTBuilder extends AgreeSwitch<Expr> {

	public static final String clockIDSuffix = "___CLOCK_";
	public static final String eventSuffix = "___EVENT_";
	public static final String dotChar = "__";
	public static final String unspecifiedAadlPropertyPrefix = "_unspec_property_";

	public static List<Node> globalNodes;
	private static Set<Type> globalTypes;

	// EGM: array-backend

	// Symbol table to gather all the globalTypes
	private static TypeTable symbolTable;

	// EGM: end-array-backend

	private static Map<String, AgreeVar> timeOfVarMap;
	private static Map<String, AgreeVar> timeRiseVarMap;
	private static Map<String, AgreeVar> timeFallVarMap;

	// TODO: a number of the maps in this class are static but appear to more
	// appropriately and more safely be instance variables.
	private static Map<String, String> renamings;
	private static Map<String, EObject> refMap;
	private static Map<String, GetPropertyExpr> unspecifiedAadlProperties;

	private ComponentInstance curInst; // used for Get_Property Expressions
	private boolean isMonolithic = false;
	private LinearizationRewriter linearizationRewriter = new LinearizationRewriter();

	static class GatheredVariablesAndConstraints {
		public List<AgreeVar> variables = new ArrayList<>();
		public List<AgreeStatement> assertions = new ArrayList<>();
		public List<AgreeStatement> obligations = new ArrayList<>();

		public void addAllTo(List<AgreeVar> variables, List<AgreeStatement> assertions,
				List<AgreeStatement> obligations) {
			variables.addAll(this.variables);
			assertions.addAll(this.assertions);
			obligations.addAll(this.obligations);
		}
	}

	public AgreeProgram getAgreeProgram(ComponentInstance compInst, boolean isMonolithic) {
		boolean containsRTPatterns = false;

		this.isMonolithic = isMonolithic;
		globalNodes = new ArrayList<>();
		globalTypes = new HashSet<>();
		renamings = new HashMap<>();
		refMap = new HashMap<>();

		// EGM: array-backend
		symbolTable = new TypeTable();

		AgreeNode topNode = getAgreeNode(compInst, true);
		List<AgreeNode> agreeNodes = gatherNodes(topNode);

		// EGM: array-backend
		List<Type> lustreTypes = symbolTable.getLustreTypes();


		// have to convert the types. The reason we use Record types in the
		// first place rather than the more general types is so we can check set
		// containment easily
		AgreeProgram program = new AgreeProgram(agreeNodes, new ArrayList<>(globalNodes), lustreTypes,
				topNode);

		// if there are any patterns in the AgreeProgram we need to inline them
		program = AgreePatternTranslator.translate(program);
		containsRTPatterns = program.containsRealTimePatterns;

		program = AgreeInlineLatchedConnections.translate(program);
		program = AgreeMakeClockedLustreNodes.translate(program);

		// go through the extension registries and transform the program
		AgreeAutomaterRegistry aAReg = (AgreeAutomaterRegistry) ExtensionRegistry
				.getRegistry(ExtensionRegistry.AGREE_AUTOMATER_EXT_ID);
		List<AgreeAutomater> automaters = aAReg.getAgreeAutomaters();

		for (AgreeAutomater aa : automaters) {
			program = aa.transform(program);
		}

		program.containsRealTimePatterns(containsRTPatterns);

		// EGM: DEBUG
		System.out.println("getAgreeProgram");
		com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTPrettyprinter pp = new com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTPrettyprinter();
		program.accept(pp);
		System.out.println(pp.toString());
		System.out.println(AgreeLogger.getLog());

		return program;
	}

	private List<AgreeNode> gatherNodes(AgreeNode node) {
		List<AgreeNode> nodes = new ArrayList<>();

		for (AgreeNode subNode : node.subNodes) {
			nodes.addAll(gatherNodes(subNode));
			nodes.add(subNode);
		}
		return nodes;
	}

	private AgreeNode getAgreeNode(ComponentInstance compInst, boolean isTop) {
		List<AgreeVar> inputs = new ArrayList<>();
		List<AgreeVar> outputs = new ArrayList<>();
		List<AgreeVar> locals = new ArrayList<>();
		List<AgreeAADLConnection> aadlConnections = new ArrayList<>();
		List<AgreeOverriddenConnection> userDefinedConections = new ArrayList<>();
		List<AgreeConnection> connections = new ArrayList<>();
		List<AgreeNode> subNodes = new ArrayList<>();
		List<AgreeStatement> assertions = new ArrayList<>();
		List<AgreeStatement> assumptions = new ArrayList<>();
		List<AgreeStatement> guarantees = new ArrayList<>();
		List<AgreeStatement> lemmas = new ArrayList<>();
		List<AgreeEquation> localEquations = new ArrayList<>();
		List<AgreeStatement> patternProps = Collections.emptyList();
		timeOfVarMap = new HashMap<>();
		timeRiseVarMap = new HashMap<>();
		timeFallVarMap = new HashMap<>();
		unspecifiedAadlProperties = new HashMap<>();

		Expr clockConstraint = new BoolExpr(true);
		Expr initialConstraint = new BoolExpr(true);
		String id = compInst.getName();
		AgreeVar clockVar = new AgreeVar(id + clockIDSuffix, NamedType.BOOL, compInst.getSubcomponent(), compInst,
				null);
		EObject reference = isTop ? compInst.getComponentClassifier() : compInst.getSubcomponent();
		TimingModel timing = null;

		boolean foundSubNode = false;
		boolean hasDirectAnnex = false;
		boolean hasSubcomponents = false;
		ComponentClassifier compClass = compInst.getComponentClassifier();
		if (compClass instanceof ComponentImplementation && (isTop || isMonolithic)) {
			AgreeContractSubclause annex = getAgreeAnnex(compClass);

			for (ComponentInstance subInst : compInst.getComponentInstances()) {
				hasSubcomponents = true;
				curInst = subInst;
				AgreeNode subNode = getAgreeNode(subInst, false);
				if (subNode != null) {
					foundSubNode = true;
					subNodes.add(subNode);
				}
			}
			boolean latched = false;
			if (annex != null) {
				hasDirectAnnex = true;
				AgreeContract contract = (AgreeContract) annex.getContract();

				curInst = compInst;
				assertions.addAll(getAssertionStatements(contract.getSpecs()));
				getEquationStatements(contract.getSpecs()).addAllTo(locals, assertions, guarantees);
				assertions.addAll(getPropertyStatements(contract.getSpecs()));
				assertions.addAll(getAssignmentStatements(contract.getSpecs()));
				userDefinedConections.addAll(getConnectionStatements(contract.getSpecs()));

				lemmas.addAll(getLemmaStatements(contract.getSpecs()));
				addLustreNodes(contract.getSpecs());
				gatherLustreTypes(contract.getSpecs());
				// the clock constraints contain other nodes that we add
				clockConstraint = getClockConstraint(contract.getSpecs(), subNodes);
				timing = getTimingModel(contract.getSpecs());

				outputs.addAll(getEquationVars(contract.getSpecs(), compInst));

				for (SpecStatement spec : contract.getSpecs()) {
					if (spec instanceof LatchedStatement) {
						latched = true;
						break;
					}
				}

			}
			aadlConnections.addAll(getConnections(((ComponentImplementation) compClass).getAllConnections(), compInst,
					subNodes, latched));

			connections.addAll(filterConnections(aadlConnections, userDefinedConections));

			// make compClass the type so we can get it's other contract
			// elements
			compClass = ((ComponentImplementation) compClass).getType();
		} else if (compClass instanceof ComponentImplementation) {
			compClass = ((ComponentImplementation) compClass).getType();
		}
		curInst = compInst;

		if (timing == null) {
			timing = TimingModel.SYNC;
		}

		AgreeContractSubclause annex = getAgreeAnnex(compClass);
		if (annex != null) {
			hasDirectAnnex = true;
			AgreeContract contract = (AgreeContract) annex.getContract();
			// this makes files for monolithic verification a bit smaller
			if (isTop || !hasSubcomponents) {
				assumptions.addAll(getAssumptionStatements(contract.getSpecs()));
				guarantees.addAll(getGuaranteeStatements(contract.getSpecs()));
			}
			// we count eqstatements with expressions as assertions
			getEquationStatements(contract.getSpecs()).addAllTo(locals, assertions, guarantees);
			assertions.addAll(getPropertyStatements(contract.getSpecs()));
			outputs.addAll(getEquationVars(contract.getSpecs(), compInst));
			getAgreeInputVars(contract.getSpecs(), compInst).addAllTo(inputs, assumptions, guarantees);
			initialConstraint = getInitialConstraint(contract.getSpecs());

			addLustreNodes(contract.getSpecs());
			gatherLustreTypes(contract.getSpecs());
		}

		gatherUnspecifiedAadlProperties(unspecifiedAadlProperties, inputs, assumptions, guarantees);

		if (!(foundSubNode || hasDirectAnnex)) {
			return null;
		}
		gatherOutputsInputsAndTypes(outputs, inputs, compInst.getFeatureInstances(), assumptions, guarantees);

		// verify that every variable that is reasoned about is
		// in a component containing an annex
		assertReferencedSubcomponentHasAnnex(compInst, inputs, outputs, subNodes, assertions, lemmas);

		AgreeNodeBuilder builder = new AgreeNodeBuilder(id);
		builder.addInput(inputs);
		builder.addOutput(outputs);
		builder.addLocal(locals);
		builder.addLocalEquation(localEquations);
		builder.addConnection(connections);
		builder.addSubNode(subNodes);

		// Clean up any vacuous true predicates
		Predicate<AgreeStatement> isBoolExprAndisTrue = st -> (st.expr instanceof BoolExpr)
				&& ((BoolExpr) st.expr).value;
		assertions.removeIf(isBoolExprAndisTrue);
		assumptions.removeIf(isBoolExprAndisTrue);
		guarantees.removeIf(isBoolExprAndisTrue);

		builder.addAssertion(assertions);
		builder.addAssumption(assumptions);
		builder.addGuarantee(guarantees);
		builder.addLemma(lemmas);
		builder.addPatternProp(patternProps);
		builder.setClockConstraint(clockConstraint);
		builder.setInitialConstraint(initialConstraint);
		builder.setClockVar(clockVar);
		builder.setReference(reference);
		builder.setTiming(timing);
		builder.setCompInst(compInst);
		builder.addTimeOf(timeOfVarMap);
		builder.addTimeRise(timeRiseVarMap);
		builder.addTimeFall(timeFallVarMap);

		AgreeNode result = builder.build();

		renamings.put(id, compInst.getName());
		refMap.put(id, compInst);

		return linearizationRewriter.visit(result);
	}

	private List<AgreeConnection> filterConnections(List<AgreeAADLConnection> aadlConnections,
			List<AgreeOverriddenConnection> userDefinedConections) {
		List<AgreeConnection> conns = new ArrayList<>();
		// TODO right now for event ports this will copy an overridden
		// connection twice
		for (AgreeAADLConnection aadlConn : aadlConnections) {
			EObject aadlRef = aadlConn.reference;
			AgreeConnection replacementConn = aadlConn;
			for (AgreeOverriddenConnection agreeConn : userDefinedConections) {
				EObject agreeRef = agreeConn.aadlConn;
				if (aadlRef == agreeRef) {
					replacementConn = agreeConn;
					break;
				}
			}
			conns.add(replacementConn);
		}

		// throw errors for non-override connections with multiple fanin
		Set<AgreeVar> destinations = new HashSet<>();

		for (AgreeConnection conn : conns) {
			if (conn instanceof AgreeAADLConnection) {
				AgreeAADLConnection aadlConn = (AgreeAADLConnection) conn;
				if (!destinations.add(aadlConn.destinationVarName)) {
					String message = "Multiple connections to feature '" + aadlConn.destinationVarName + "'. Remove "
							+ "the additional AADL connections or override them with a connection statement "
							+ "in the AGREE annex.";
					throw new AgreeException(message);
				}
			}

		}

		return conns;
	}

	private GatheredVariablesAndConstraints getAgreeInputVars(List<SpecStatement> specs, ComponentInstance compInst) {
		GatheredVariablesAndConstraints result = new GatheredVariablesAndConstraints();
		for (SpecStatement spec : specs) {
			if (spec instanceof InputStatement) {
				EList<Arg> args = ((InputStatement) spec).getLhs();
				List<VarDecl> vars = agreeVarsFromArgs(args, compInst);
				for (VarDecl var : vars) {
					result.variables.add((AgreeVar) var);
				}
				result.assertions.addAll(getConstraintsFromArgs(args, spec));
			}
		}
		return result;
	}

	private void assertReferencedSubcomponentHasAnnex(ComponentInstance compInst, List<AgreeVar> inputs,
			List<AgreeVar> outputs, List<AgreeNode> subNodes, List<AgreeStatement> assertions,
			List<AgreeStatement> lemmas) {
		Set<String> allExprIds = new HashSet<>();
		for (AgreeStatement statement : assertions) {
			allExprIds.addAll(gatherStatementIds(statement));
		}
		for (AgreeStatement statement : lemmas) {
			allExprIds.addAll(gatherStatementIds(statement));
		}
		for (String idStr : allExprIds) {
			if (idStr.contains(dotChar) && !(idStr.endsWith(AgreePatternTranslator.FALL_SUFFIX)
					|| idStr.endsWith(AgreePatternTranslator.RISE_SUFFIX)
					|| idStr.endsWith(AgreePatternTranslator.TIME_SUFFIX))) {
				String prefix = idStr.substring(0, idStr.indexOf(dotChar));
				boolean found = false;
				for (AgreeVar var : inputs) {
					if (var.id.startsWith(prefix + dotChar)) {
						found = true;
						break;
					}
				}
				for (AgreeVar var : outputs) {
					if (var.id.startsWith(prefix + dotChar)) {
						found = true;
						break;
					}
				}
				for (AgreeNode subNode : subNodes) {
					if (subNode.id.equals(prefix)) {
						found = true;
						break;
					}
				}
				if (!found) {
					throw new AgreeException(
							"Variable '" + idStr.replace(dotChar, ".") + "' appears in an assertion, lemma "
									+ "or equation statement in component '" + compInst.getInstanceObjectPath()
									+ "' but subcomponent '" + prefix + "' does " + "not contain an AGREE annex");
				}
			}
		}
	}

	private Set<String> gatherStatementIds(AgreeStatement statement) {
		IdGatherer visitor = new IdGatherer();
		Set<String> ids = new HashSet<>();
		if (statement instanceof AgreeCauseEffectPattern) {
			AgreeCauseEffectPattern pattern = (AgreeCauseEffectPattern) statement;
			ids.addAll(pattern.cause.accept(visitor));
			ids.addAll(pattern.effect.accept(visitor));
		} else if (statement instanceof AgreePeriodicPattern) {
			AgreePeriodicPattern pattern = (AgreePeriodicPattern) statement;
			ids.addAll(pattern.event.accept(visitor));
		} else if (statement instanceof AgreeSporadicPattern) {
			AgreeSporadicPattern pattern = (AgreeSporadicPattern) statement;
			ids.addAll(pattern.event.accept(visitor));
		} else {
			ids.addAll(statement.expr.accept(visitor));
		}
		return ids;
	}

	private List<AgreeStatement> getLemmaStatements(EList<SpecStatement> specs) {
		List<AgreeStatement> lemmas = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof LemmaStatement) {
				LemmaStatement lemma = (LemmaStatement) spec;
				if (lemma.getExpr() != null) {
					lemmas.add(new AgreeStatement(lemma.getStr(), doSwitch(lemma.getExpr()), spec));
				} else {
					PatternStatement pattern = lemma.getPattern();
					lemmas.add(new AgreePatternBuilder(lemma.getStr(), lemma, this).doSwitch(pattern));
				}
			}
		}
		return lemmas;
	}

	private TimingModel getTimingModel(EList<SpecStatement> specs) {
		for (SpecStatement spec : specs) {
			if (spec instanceof MNSynchStatement) {
				return TimingModel.ASYNC;
			}
			if (spec instanceof CalenStatement) {
				throw new AgreeException("The use of calendar statements has been depricated");
			}
			if (spec instanceof AsynchStatement) {
				return TimingModel.ASYNC;
			}
			if (spec instanceof LatchedStatement) {
				return TimingModel.LATCHED;
			}
			if (spec instanceof SynchStatement) {
//				int val = Integer.valueOf(((SynchStatement) spec).getVal());
//				if (val != 0) {
//					return TimingModel.ASYNC;
//				}
				return TimingModel.ASYNC;
			}
		}
		return TimingModel.SYNC;
	}

	private List<AgreeVar> getEquationVars(EList<SpecStatement> specs, ComponentInstance compInst) {
		List<AgreeVar> agreeVars = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof EqStatement) {
				EList<Arg> args = ((EqStatement) spec).getLhs();
				List<VarDecl> vars = agreeVarsFromArgs(args, compInst);
				for (VarDecl var : vars) {
					agreeVars.add((AgreeVar) var);
				}
			} else if (spec instanceof PropertyStatement) {
				agreeVars.add(new AgreeVar(((PropertyStatement) spec).getName(), NamedType.BOOL, spec, compInst, null));
			}
		}
		return agreeVars;
	}

	private void gatherOutputsInputsAndTypes(List<AgreeVar> outputs, List<AgreeVar> inputs,
			EList<FeatureInstance> features, List<AgreeStatement> assumptions, List<AgreeStatement> guarantees) {
		for (FeatureInstance feature : features) {
			featureToAgreeVars(outputs, inputs, feature, assumptions, guarantees);
		}

	}

	private void featureToAgreeVars(List<AgreeVar> outputs, List<AgreeVar> inputs, FeatureInstance feature,
			List<AgreeStatement> assumptions, List<AgreeStatement> guarantees) {

		switch (feature.getCategory()) {
		case FEATURE_GROUP:
			List<AgreeVar> newInputs = new ArrayList<>();
			List<AgreeVar> newOutputs = new ArrayList<>();
			gatherOutputsInputsAndTypes(newOutputs, newInputs, feature.getFeatureInstances(), assumptions, guarantees);
			for (AgreeVar agreeVar : newInputs) {
				String newName = feature.getName() + dotChar + agreeVar.id;
				inputs.add(new AgreeVar(newName, agreeVar.type, feature.getFeature(), feature.getComponentInstance(),
						feature));
			}
			for (AgreeVar agreeVar : newOutputs) {
				String newName = feature.getName() + dotChar + agreeVar.id;
				outputs.add(new AgreeVar(newName, agreeVar.type, feature.getFeature(), feature.getComponentInstance(),
						feature));
			}
			return;
		case DATA_PORT:
		case EVENT_PORT:
		case EVENT_DATA_PORT:
			portToAgreeVar(outputs, inputs, feature, assumptions, guarantees);
			return;
		case DATA_ACCESS:
			break;
		default:
			break; // TODO: handle other types
		}

		return;
	}


	private void portToAgreeVar(List<AgreeVar> outputs, List<AgreeVar> inputs, FeatureInstance feature,
			List<AgreeStatement> assumptions,
			List<AgreeStatement> guarantees) {

		Feature dataFeature = feature.getFeature();
		NamedElement dataClass;
		if (dataFeature instanceof DataPort) {
			DataPort dataPort = (DataPort) dataFeature;
			dataClass = dataPort.getDataFeatureClassifier();
		} else if (dataFeature instanceof EventDataPort) {
			EventDataPort eventDataPort = (EventDataPort) dataFeature;
			dataClass = eventDataPort.getDataFeatureClassifier();

		} else {
			dataClass = null;
		}

		String name = feature.getName();
		boolean isEvent = feature.getCategory() == FeatureCategory.EVENT_DATA_PORT
				|| feature.getCategory() == FeatureCategory.EVENT_PORT;
		if (isEvent) {
			AgreeVar var = new AgreeVar(name + eventSuffix, NamedType.BOOL, feature.getFeature(),
					feature.getComponentInstance(), feature);
			switch (feature.getDirection()) {
			case IN:
				inputs.add(var);
				break;
			case OUT:
				outputs.add(var);
				break;
			default:
				throw new AgreeException(
						"Unable to reason about bi-directional event port: " + dataFeature.getQualifiedName());
			}
		}

		if (dataClass == null) {
			// we do not reason about this type
			return;
		}

		Type type = symbolTable.updateLustreTypeMap(AgreeTypeSystem.typeDefFromNE(dataClass));

		if (type == null) {
			// we do not reason about this type
			return;
		}

		AgreeVar agreeVar = new AgreeVar(name, type, feature.getFeature(), feature.getComponentInstance(), feature);

		switch (feature.getDirection()) {
		case IN:
			inputs.add(agreeVar);
			if (dataClass instanceof DataClassifier) {

				List<Expr> constraints = getConstraintsFromTypeDef(name, AgreeTypeSystem.typeDefFromNE(dataClass));
				if (!constraints.isEmpty()) {
					assumptions.add(getDataClassifierTypePredicate(feature.getName(), constraints, dataFeature));
				}
			}
			break;
		case OUT:
			outputs.add(agreeVar);
			if (dataClass instanceof DataClassifier) {
				List<Expr> constraints = getConstraintsFromTypeDef(name, AgreeTypeSystem.typeDefFromNE(dataClass));
				if (!constraints.isEmpty()) {
					guarantees.add(getDataClassifierTypePredicate(feature.getName(), constraints, dataFeature));
				}
			}
			break;
		default:
			throw new AgreeException(
					"Unable to reason about bi-directional event port: " + dataFeature.getQualifiedName());
		}
	}

	private static AgreeStatement getDataClassifierTypePredicate(String name, List<Expr> constraints,
			EObject reference) {
		// must have reference so we don't throw them away later
		return new AgreeStatement("Type predicate on '" + name + "'",
				constraints.stream().reduce(new BoolExpr(true), (a, b) -> new BinaryExpr(a, BinaryOp.AND, b)),
				reference);
	}

	private List<AgreeAADLConnection> getConnections(EList<Connection> connections, ComponentInstance compInst,
			List<AgreeNode> subNodes, boolean latched) {

//		Set<AgreeVar> destinationSet = new HashSet<>();

		Property commTimingProp = Aadl2GlobalScopeUtil.get(compInst, Aadl2Package.eINSTANCE.getProperty(),
				"Communication_Properties::Timing");
		List<AgreeAADLConnection> agreeConns = new ArrayList<>();
		for (Connection conn : connections) {

			ConnectedElement absConnDest = conn.getDestination();
			ConnectedElement absConnSour = conn.getSource();
			boolean delayed = false;
			try {
				EnumerationLiteral lit = PropertyUtils.getEnumLiteral(conn, commTimingProp);
				delayed = lit.getName().equals("delayed");
			} catch (PropertyDoesNotApplyToHolderException e) {
				delayed = false;
			}
			Context destContext = absConnDest.getContext();
			Context sourContext = absConnSour.getContext();
			// only make connections to things that have annexs
			if (destContext != null && destContext instanceof Subcomponent) {
				ComponentInstance subInst = compInst.findSubcomponentInstance((Subcomponent) destContext);
				if (!AgreeUtils.containsTransitiveAgreeAnnex(subInst, isMonolithic)) {
					continue;
				}
			}
			if (sourContext != null && sourContext instanceof Subcomponent) {
				ComponentInstance subInst = compInst.findSubcomponentInstance((Subcomponent) sourContext);
				if (!AgreeUtils.containsTransitiveAgreeAnnex(subInst, isMonolithic)) {
					continue;
				}
			}

			AgreeNode sourceNode = agreeNodeFromNamedEl(subNodes, sourContext);
			AgreeNode destNode = agreeNodeFromNamedEl(subNodes, destContext);

			ConnectionEnd destPort = absConnDest.getConnectionEnd();
			ConnectionEnd sourPort = absConnSour.getConnectionEnd();

			if (sourPort instanceof DataSubcomponent || destPort instanceof DataSubcomponent) {
				AgreeLogger.logWarning("unable to reason about connection '" + conn.getName()
				+ "' because it connects to a data subcomponent");
				continue;
			}

			// weirdness with feature groups
			String sourPrefix = null;
			if (sourContext instanceof FeatureGroup) {
				sourPrefix = sourContext.getName();
			}
			String destPrefix = null;
			if (destContext instanceof FeatureGroup) {
				destPrefix = destContext.getName();
			}

			List<AgreeVar> sourVars = getAgreePortNames(sourPort, sourPrefix,
					sourceNode == null ? null : sourceNode.compInst);
			List<AgreeVar> destVars = getAgreePortNames(destPort, destPrefix,
					destNode == null ? null : destNode.compInst);

			if (sourVars.size() != destVars.size()) {
				throw new AgreeException("The number of AGREE variables differ for connection '"
						+ conn.getQualifiedName()
						+ "'. Do the types of the source and destination differ? Perhaps one is an implementation and the other is a type?");
			}

			for (int i = 0; i < sourVars.size(); i++) {
				AgreeVar sourVar = sourVars.get(i);
				AgreeVar destVar = destVars.get(i);

				if (!matches((ConnectionEnd) sourVar.reference, (ConnectionEnd) destVar.reference)) {
					AgreeLogger.logWarning(
							"Connection '" + conn.getName() + "' has ports '" + sourVar.id.replace(dotChar, ".")
							+ "' and '" + destVar.id.replace(dotChar, ".") + "' of differing type");
					continue;
				}

				if (!sourVar.type.equals(destVar.type)) {
					throw new AgreeException("Type mismatch during connection generation");
				}

				ConnectionType connType;

				if (sourVar.id.endsWith(eventSuffix)) {
					connType = ConnectionType.EVENT;
				} else {
					connType = ConnectionType.DATA;
				}

				AgreeAADLConnection agreeConnection = new AgreeAADLConnection(sourceNode, destNode, sourVar, destVar,
						connType, latched, delayed, conn);

				agreeConns.add(agreeConnection);
			}

		}
		return agreeConns;
	}

	private Type getConnectionEndType(ConnectionEnd port) {
		NamedElement dataClass = getConnectionEndDataClass(port);
		if (dataClass == null) {
			return null;
		}
		// EGM: array-backend
		// Type lustreType = AgreeTypeUtils.getType(dataClass, typeMap, globalTypes);
		Type lustreType = symbolTable.updateLustreTypeMap(AgreeTypeSystem.typeDefFromNE(dataClass));
		return lustreType;
	}

	private List<AgreeVar> getAgreePortNames(ConnectionEnd port, String prefix, ComponentInstance compInst) {
		String portName = port.getName();
		List<AgreeVar> subVars = new ArrayList<>();

		// if the port is a datasubcomponent then it is a member
		// of a record type. Otherwise it is the first member of a feature group
		if (prefix == null) {
			prefix = "";
		} else if (port instanceof DataSubcomponent) {
			prefix = prefix + ".";
		} else {
			prefix = prefix + dotChar;
		}

		if (port instanceof FeatureGroup) {
			FeatureGroup featGroup = (FeatureGroup) port;
			FeatureGroupType featType = featGroup.getFeatureGroupType();
			for (FeatureGroup subFeatGroup : featType.getOwnedFeatureGroups()) {
				subVars.addAll(getAgreePortNames(subFeatGroup, null, compInst));
			}
			for (DataPort subPort : featType.getOwnedDataPorts()) {
				subVars.addAll(getAgreePortNames(subPort, null, compInst));
			}
			for (EventDataPort subPort : featType.getOwnedEventDataPorts()) {
				subVars.addAll(getAgreePortNames(subPort, null, compInst));
			}
			for (EventPort subPort : featType.getOwnedEventPorts()) {
				subVars.addAll(getAgreePortNames(subPort, null, compInst));
			}

			List<AgreeVar> prefixedStrs = new ArrayList<>();
			for (AgreeVar subVar : subVars) {
				prefixedStrs.add(
						new AgreeVar(prefix + portName + dotChar + subVar.id, subVar.type, subVar.reference, compInst));
			}
			subVars = prefixedStrs;
		}
		if (port instanceof DataPort || port instanceof EventDataPort || port instanceof DataSubcomponent) {
			Type type = getConnectionEndType(port);
			if (type != null) {
				subVars.add(new AgreeVar(prefix + portName, type, port, compInst));
			}
		}
		if (port instanceof EventDataPort || port instanceof EventPort) {
			subVars.add(new AgreeVar(prefix + portName + eventSuffix, NamedType.BOOL, port, compInst));
		}

		return subVars;

	}

	private DataSubcomponentType getConnectionEndDataClass(ConnectionEnd port) {
		DataSubcomponentType dataClass = null;
		if (port instanceof DataPort) {
			DataPort dataPort = (DataPort) port;
			dataClass = dataPort.getDataFeatureClassifier();
		} else if (port instanceof EventDataPort) {
			EventDataPort eventDataPort = (EventDataPort) port;
			dataClass = eventDataPort.getDataFeatureClassifier();
		} else if (port instanceof DataSubcomponent) {
			dataClass = ((DataSubcomponent) port).getDataSubcomponentType();
		}
		if (dataClass == null) {
			AgreeLogger.logWarning("Unable to determine the type of port '" + port + "'");
		}
		return dataClass;
	}

	private boolean matches(ConnectionEnd a, ConnectionEnd b) {
		if (a instanceof EventDataPort ^ b instanceof EventDataPort) {
			return false;
		}
		return true;
	}

	private AgreeNode agreeNodeFromNamedEl(List<AgreeNode> nodes, NamedElement comp) {
		if (comp == null) {
			return null;
		}
		for (AgreeNode node : nodes) {
			if (comp.getName().equals(node.id)) {
				return node;
			}
		}
		return null;
	}

	private Expr getInitialConstraint(EList<SpecStatement> specs) {
		for (SpecStatement spec : specs) {
			if (spec instanceof InitialStatement) {
				return doSwitch(((InitialStatement) spec).getExpr());
			}
		}
		return new BoolExpr(true);
	}

	private Expr getClockConstraint(EList<SpecStatement> specs, List<AgreeNode> subNodes) {
		// NOTE: we generate the constraint that "someone ticks" during the
		// lustre generation
		for (SpecStatement spec : specs) {
			if (spec instanceof MNSynchStatement) {
				return getMNSynchConstraint((MNSynchStatement) spec);
			}
			if (spec instanceof CalenStatement) {
				throw new AgreeException("The use of calendar statements has been depricated");
			}
			if (spec instanceof AsynchStatement) {
				return new BoolExpr(true);
			}
			if (spec instanceof LatchedStatement) {
				return new BoolExpr(true);
			}
			if (spec instanceof SynchStatement) {
				return getSynchConstraint((SynchStatement) spec, subNodes);
			}
		}

		return new BoolExpr(true);
	}

	private static String getObjectLocationPrefix(EObject obj) {
		String objPrefix = "";
		EObject container = obj.eContainer();

		while (!(container instanceof AadlPackage)) {
			if (container instanceof ComponentClassifier) {
				objPrefix = ((ComponentClassifier) container).getName();
				objPrefix = dotChar + objPrefix;
				objPrefix = objPrefix.replace(".", dotChar);
			}
			container = container.eContainer();
		}
		objPrefix = ((AadlPackage) container).getName() + objPrefix + dotChar;

		return objPrefix;
	}

	private Expr getSynchConstraint(SynchStatement spec, List<AgreeNode> subNodes) {

		int val1 = Integer.decode(spec.getVal());
		if (val1 == 0) {
			return new BoolExpr(true);
		}

		List<Expr> clockIds = new ArrayList<>();
		Expr clockAssertion;
		for (AgreeNode subNode : subNodes) {
			clockIds.add(new IdExpr(subNode.clockVar.id));
		}

		String dfaPrefix = getObjectLocationPrefix(spec);

		if (spec.getVal2() == null) {
			Node dfaNode = AgreeCalendarUtils.getDFANode(dfaPrefix + "__DFA_NODE", val1);
			Node calNode = AgreeCalendarUtils.getCalendarNode(dfaPrefix + "__CALENDAR_NODE", dfaNode.id,
					clockIds.size());

			// we do not need to make copies of the nodes if they exist
			if (!nodeNameExists(dfaNode.id)) {
				if (nodeNameExists(calNode.id)) {
					throw new AgreeException("The calander node should not exist if the dfa node does not exist");
				}
				addToNodeList(dfaNode);
				addToNodeList(calNode);
			}

			clockAssertion = new NodeCallExpr(calNode.id, clockIds);
		} else {
			int val2 = Integer.decode(spec.getVal2());

			String nodeName = "__calendar_node_" + val1 + "_" + val2;
			nodeName = dfaPrefix + nodeName;
			Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, val1, val2);

			if (!nodeNameExists(calNode.id)) {
				addToNodeList(calNode);
			}

			clockAssertion = new BoolExpr(true);
			int i, j;
			for (i = 0; i < clockIds.size(); i++) {
				Expr clock1 = clockIds.get(i);
				for (j = i + 1; j < clockIds.size(); j++) {
					Expr clock2 = clockIds.get(j);
					NodeCallExpr nodeCall = new NodeCallExpr(nodeName, clock1, clock2);
					clockAssertion = LustreExprFactory.makeANDExpr(clockAssertion, nodeCall);
					nodeCall = new NodeCallExpr(nodeName, clock2, clock1);
					clockAssertion = LustreExprFactory.makeANDExpr(clockAssertion, nodeCall);
				}
			}
		}
		return clockAssertion;
	}

	private Expr getMNSynchConstraint(MNSynchStatement sync) {

		Set<String> nodeNames = new HashSet<>();
		Expr clockAssertion = new BoolExpr(true);
		for (int i = 0; i < sync.getComp1().size(); i++) {
			Subcomponent maxComp = (Subcomponent) sync.getComp1().get(i);
			Subcomponent minComp = (Subcomponent) sync.getComp2().get(i);

			Expr maxClock = new IdExpr(maxComp.getName() + clockIDSuffix);
			Expr minClock = new IdExpr(minComp.getName() + clockIDSuffix);
			int max = Integer.valueOf(sync.getMax().get(i));
			int min = Integer.valueOf(sync.getMin().get(i));

			MNSynchronyElement elem = new MNSynchronyElement(maxClock, minClock, max, min);

			String nodeName = "__calendar_node_" + elem.max + "_" + elem.min;
			nodeName = getObjectLocationPrefix(sync) + nodeName;
			if (!nodeNames.contains(nodeName)) {
				nodeNames.add(nodeName);
				Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, elem.max, elem.min);
				addToNodeList(calNode);
			}

			NodeCallExpr nodeCall = new NodeCallExpr(nodeName, elem.maxClock, elem.minClock);
			clockAssertion = LustreExprFactory.makeANDExpr(clockAssertion, nodeCall);
			nodeCall = new NodeCallExpr(nodeName, elem.minClock, elem.maxClock);
			clockAssertion = LustreExprFactory.makeANDExpr(clockAssertion, nodeCall);
		}

		return clockAssertion;
	}

	private List<Type> gatherLustreTypes(EList<SpecStatement> specs) {
		List<Type> types = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof RecordDef) {
				// this will record them to the global types
				// EGM: array-backend
				// AgreeTypeUtils.getType((NamedElement) spec, typeMap, globalTypes);
				symbolTable.updateLustreTypeMap(AgreeTypeSystem.typeDefFromNE((NamedElement) spec));
			}
		}
		return types;
	}

	private List<Node> addLustreNodes(EList<SpecStatement> specs) {
		List<Node> nodes = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof NodeDef || spec instanceof FnDef) {
				doSwitch(spec);
			}
		}
		return nodes;
	}

	public VarDecl agreeVarFromArg(Arg arg, ComponentInstance compInst) {
		// EGM: array-backend
		// String typeStr = AgreeTypeUtils.getTypeName(arg.getType(), typeMap, globalTypes);
		Type type = symbolTable.updateLustreTypeMap(AgreeTypeSystem.typeDefFromType(arg.getType()));
		if (type != null) {
			return new AgreeVar(arg.getName(), type, arg, compInst, null);
		} else {
			return null;
		}
	}

	// MWW: made this public.
	public List<VarDecl> agreeVarsFromArgs(EList<Arg> args, ComponentInstance compInst) {
		List<VarDecl> agreeVars = new ArrayList<>();
		for (Arg arg : args) {
			agreeVars.add(agreeVarFromArg(arg, compInst));
		}
		return agreeVars;
	}

	private List<AgreeStatement> getAssertionStatements(EList<SpecStatement> specs) {
		List<AgreeStatement> asserts = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof AssertStatement) {
				AssertStatement assertState = (AssertStatement) spec;
				String str = assertState.getStr();

				if (assertState.getExpr() != null) {
					asserts.add(new AgreeStatement(str, doSwitch(assertState.getExpr()), assertState));
				} else {
					PatternStatement pattern = assertState.getPattern();
					asserts.add(new AgreePatternBuilder(str, assertState, this).doSwitch(pattern));
				}
			}
		}
		return asserts;
	}

	private List<AgreeOverriddenConnection> getConnectionStatements(EList<SpecStatement> specs) {
		List<AgreeOverriddenConnection> conns = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof ConnectionStatement) {
				Expr expr = doSwitch(((ConnectionStatement) spec).getExpr());
				Connection conn = (Connection) ((ConnectionStatement) spec).getConn();
				AgreeOverriddenConnection agreeConn = new AgreeOverriddenConnection(new AgreeStatement("", expr, spec),
						conn);
				conns.add(agreeConn);
			}
		}
		return conns;
	}

	private List<AgreeStatement> getAssignmentStatements(EList<SpecStatement> specs) {
		List<AgreeStatement> assigns = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof AssignStatement) {
				Expr expr = doSwitch(((AssignStatement) spec).getExpr());
				NamedElement id = ((AssignStatement) spec).getId();
				expr = new BinaryExpr(new IdExpr(id.getName()), BinaryOp.EQUAL,
						expr);
				assigns.add(new AgreeStatement("", expr, spec));
			}
		}
		return assigns;
	}

	private List<AgreeStatement> getPropertyStatements(EList<SpecStatement> specs) {
		List<AgreeStatement> props = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof PropertyStatement) {
				Expr expr = doSwitch(((PropertyStatement) spec).getExpr());
				expr = new BinaryExpr(new IdExpr(((PropertyStatement) spec).getName()), BinaryOp.EQUAL, expr);
				props.add(new AgreeStatement("", expr, spec));
			}
		}
		return props;
	}

	private GatheredVariablesAndConstraints getEquationStatements(EList<SpecStatement> specs) {
		GatheredVariablesAndConstraints result = new GatheredVariablesAndConstraints();
		for (SpecStatement spec : specs) {
			if (spec instanceof EqStatement) {
				EqStatement eq = (EqStatement) spec;
				EList<Arg> lhs = eq.getLhs();
				if (eq.getExpr() != null) {

					Expr expr = doSwitch(eq.getExpr());

					if (lhs.size() != 1) {
						List<Expr> ids = new ArrayList<>();
						for (Arg arg : lhs) {
							ids.add(new IdExpr(arg.getName()));
						}
						TupleExpr tuple = new TupleExpr(ids);
						expr = new BinaryExpr(tuple, BinaryOp.EQUAL, expr);
					} else {
						expr = new BinaryExpr(new IdExpr(lhs.get(0).getName()), BinaryOp.EQUAL, expr);
					}
					result.assertions.add(new AgreeStatement("", expr, spec));
				}
				result.obligations.addAll(getConstraintsFromArgs(lhs, eq));
			}
		}
		return result;
	}




	private PropertyExpression getPropertyExpression (AbstractNamedValue nv) {
		if (nv instanceof PropertyConstant) {
			return ((PropertyConstant) nv).getConstantValue();
		}

		// TODO: EGM need to pull out the property value rather that the default value,
		// but I am not sure how to do that.
		if (nv instanceof Property) {
			assert ("ERROR: default property value in AgreeASTBuilder::PropertyExpression" == null);
			return ((Property) nv).getDefaultValue();
		}

		// nv is an enumeration literal
		assert ("ERROR: enumeration literal in AgreeASTBuildter::getPropertyExpression" == null);
		return null;
	}

	// EGM-COMMENT: RangeValueImpl::getNumberValue(pe) does not cover the NamedValue instance
	// of a property expression. The getNumberValue here duplicates that code and adds the
	// missing type. RangeValueImpl::getNumberValue(pe) is used by RangeValueImpl::getMavimumValue()
	// and its minimum value counterpart. These are needed for the range constraints.
	private NumberValue getNumberValue(PropertyExpression pe) {
		if (pe instanceof Operation) {
			pe = ((Operation) pe).getOwnedPropertyExpressions().get(0);
		}

		if (pe instanceof NumberValue) {
			return (NumberValue) pe;
		}

		if (pe instanceof PropertyConstant) {
			return (NumberValue) ((PropertyConstant) pe).getConstantValue();
		}

		if (pe instanceof NamedValue) {
			pe = getPropertyExpression(((NamedValue) pe).getNamedValue());
			return getNumberValue(pe);
		}

		assert ("ERROR: missing type in AgreeASTBuilder::getNumberValue" == null);
		return null;
	}

//
//	private double getScaledValue(PropertyExpression pe) {
//		return getNumberValue(pe).getScaledValue();
//	}

//	private Expr createExprForBound(String name, RangeValue rv, Function<BigDecimal, Expr> makeExpr) {
//		double min = getScaledValue(rv.getMinimum());
//		double max = getScaledValue(rv.getMaximum());
//		IdExpr id = new IdExpr(name);
//		Expr lowVal = makeExpr.apply(BigDecimal.valueOf(min));
//		Expr highVal = makeExpr.apply(BigDecimal.valueOf(max));
//		Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, id);
//		Expr highBound = new BinaryExpr(id, BinaryOp.LESSEQUAL, highVal);
//		return LustreExprFactory.makeANDExpr(lowBound, highBound);
//	}


//	private List<Expr> getArrayRangeConstraint(String namePrefix, ArrayTypeDef array) {
//		List<Expr> constraints = new ArrayList<>();
//		assert (array.baseType instanceof DataClassifier);
//		Expr expr = getDataClassifierRangeConstraintExprs(AgreeTypeSystem.nameOfTypeDef(array.baseType),
//				(DataClassifier) array.baseType)
//				.stream().reduce(new BoolExpr(true), (a, b) -> new BinaryExpr(a, BinaryOp.AND, b));
//		for (int i = 0; i < array.dimension; ++i) {
//			String name = namePrefix + "[" + i + "]";
//		}
//		return constraints;
//	}

	private List<Expr> getConstraintsFromTypeDef(String name, AgreeTypeSystem.TypeDef typeDef) {
		List<Expr> constraints = new ArrayList<>();

		if (typeDef instanceof AgreeTypeSystem.RangeIntTypeDef) {

			Expr childName = new IdExpr(name);
			long lowl = ((AgreeTypeSystem.RangeIntTypeDef) typeDef).low;
			long highl = ((AgreeTypeSystem.RangeIntTypeDef) typeDef).high;
			Expr lowVal = new IntExpr(BigInteger.valueOf(lowl));
			Expr highVal = new IntExpr(BigInteger.valueOf(highl));
			Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, childName);
			Expr highBound = new BinaryExpr(childName, BinaryOp.LESSEQUAL, highVal);
			constraints.add(lowBound);
			constraints.add(highBound);

		} else if (typeDef instanceof AgreeTypeSystem.RangeRealTypeDef) {

			Expr childName = new IdExpr(name);

			double lowd = ((AgreeTypeSystem.RangeRealTypeDef) typeDef).low;
			double highd = ((AgreeTypeSystem.RangeRealTypeDef) typeDef).high;
			Expr lowVal = new RealExpr(BigDecimal.valueOf(lowd));
			Expr highVal = new RealExpr(BigDecimal.valueOf(highd));
			Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, childName);
			Expr highBound = new BinaryExpr(childName, BinaryOp.LESSEQUAL, highVal);
			constraints.add(lowBound);
			constraints.add(highBound);

		} else if (typeDef instanceof AgreeTypeSystem.ArrayTypeDef) {
			AgreeTypeSystem.TypeDef stemType = ((AgreeTypeSystem.ArrayTypeDef) typeDef).stemType;
			int size = ((AgreeTypeSystem.ArrayTypeDef) typeDef).size;
			for (int i = 0; i < size; ++i) {
				String childName = name + "[" + i + "]";
				constraints.addAll(getConstraintsFromTypeDef(childName, stemType));
			}

		} else if (typeDef instanceof AgreeTypeSystem.RecordTypeDef) {
			Map<String, AgreeTypeSystem.TypeDef> fields = ((AgreeTypeSystem.RecordTypeDef) typeDef).fields;
			for (Entry<String, AgreeTypeSystem.TypeDef> entry : fields.entrySet()) {
				String childName = name + "." + entry.getKey();
				AgreeTypeSystem.TypeDef childType = entry.getValue();
				constraints.addAll(getConstraintsFromTypeDef(childName, childType));
			}

		}

		return constraints;

	}

//	private List<Expr> getDataClassifierRangeConstraintExprs(String name, DataClassifier dataClassifier) {
//		List<Expr> constraints = new ArrayList<>();
//
//		if (dataClassifier instanceof DataImplementation) {
//			constraints.addAll(((DataImplementation) dataClassifier).getAllSubcomponents().stream()
//					.filter(sub -> sub.getSubcomponentType() instanceof DataClassifier)
//					.map(sub -> getDataClassifierRangeConstraintExprs(name + "." + sub.getName(),
//							(DataClassifier) sub.getSubcomponentType()))
//					.flatMap(List::stream).collect(Collectors.toList()));
//		} else if (dataClassifier instanceof DataType) {
//
//			AgreeTypeSystem.TypeDef typeDef = AgreeTypeSystem.typeDefFromClassifier(dataClassifier);
//			if (typeDef instanceof AgreeTypeSystem.ArrayTypeDef) {
//
//				AgreeTypeSystem.TypeDef baseType = ((AgreeTypeSystem.ArrayTypeDef) typeDef).stemType;
//				int size = ((AgreeTypeSystem.ArrayTypeDef) typeDef).size;
//
//				if (baseType instanceof AgreeTypeSystem.RangeIntTypeDef) {
//					for (int i = 0; i < size; ++i) {
//
//						Expr childName = new IdExpr(name + "[" + i + "]");
//						long lowl = ((AgreeTypeSystem.RangeIntTypeDef) baseType).low;
//						long highl = ((AgreeTypeSystem.RangeIntTypeDef) baseType).high;
//						Expr lowVal = new IntExpr(BigInteger.valueOf(lowl));
//						Expr highVal = new IntExpr(BigInteger.valueOf(highl));
//						Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, childName);
//						Expr highBound = new BinaryExpr(childName, BinaryOp.LESSEQUAL, highVal);
//						constraints.add(lowBound);
//						constraints.add(highBound);
//					}
//
//				} else if (baseType instanceof AgreeTypeSystem.RangeRealTypeDef) {
//					for (int i = 0; i < size; ++i) {
//						Expr childName = new IdExpr(name + "[" + i + "]");
//
//						double lowd = ((AgreeTypeSystem.RangeRealTypeDef) baseType).low;
//						double highd = ((AgreeTypeSystem.RangeRealTypeDef) baseType).high;
//						Expr lowVal = new RealExpr(BigDecimal.valueOf(lowd));
//						Expr highVal = new RealExpr(BigDecimal.valueOf(highd));
//						Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, childName);
//						Expr highBound = new BinaryExpr(childName, BinaryOp.LESSEQUAL, highVal);
//						constraints.add(lowBound);
//						constraints.add(highBound);
//
//					}
//				} else {
//
//
//					Optional<NamedElement> elmOp = Optional.empty();
//					if (baseType instanceof AgreeTypeSystem.RecordTypeDef) {
//						elmOp = Optional.of(((AgreeTypeSystem.RecordTypeDef) baseType).namedElement);
//					} else if (baseType instanceof AgreeTypeSystem.ArrayTypeDef) {
//						elmOp = ((AgreeTypeSystem.ArrayTypeDef) baseType).elmOp;
//					} else {
//
//						elmOp.map(elm -> {
//							if (elm instanceof DataClassifier) {
//								for (int i = 0; i < size; ++i) {
//									String childName = name + "[" + i + "]";
//									constraints.addAll(getDataClassifierRangeConstraintExprs(childName, (DataClassifier) elm));
//								}
//
//							}
//							return null;
//						});
//					}
//
//
//				}
//			}
//		}
//		return constraints;
//
//
//	}


//	private List<Expr> getVariableRangeConstraintExprs(String name, com.rockwellcollins.atc.agree.agree.Type type) {
//		List<Expr> result = new ArrayList<>();
//		if (type instanceof PrimType) {
//			PrimType primType = (PrimType) type;
//			String lowStr = primType.getRangeLow();
//			String highStr = primType.getRangeHigh();
//
//			if (lowStr != null && highStr != null) {
//				IdExpr id = new IdExpr(name);
//				int lowSign = primType.getLowNeg() == null ? 1 : -1;
//				int highSign = primType.getHighNeg() == null ? 1 : -1;
//				Expr lowVal = null;
//				Expr highVal = null;
//
//				switch (primType.getName()) {
//				case "int":
//					long lowl = Long.valueOf(lowStr) * lowSign;
//					long highl = Long.valueOf(highStr) * highSign;
//					lowVal = new IntExpr(BigInteger.valueOf(lowl));
//					highVal = new IntExpr(BigInteger.valueOf(highl));
//					break;
//				case "real":
//					double lowd = Double.valueOf(lowStr) * lowSign;
//					double highd = Double.valueOf(highStr) * highSign;
//					lowVal = new RealExpr(BigDecimal.valueOf(lowd));
//					highVal = new RealExpr(BigDecimal.valueOf(highd));
//					break;
//				default:
//					throw new AgreeException("Unhandled type '" + primType.getName() + "' in ranged type");
//				}
//				Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, id);
//				Expr highBound = new BinaryExpr(id, BinaryOp.LESSEQUAL, highVal);
//
//				result.add(LustreExprFactory.makeANDExpr(lowBound, highBound));
//			}
//		} else if (type instanceof DoubleDotRef) {
//			DoubleDotRef recType = (DoubleDotRef) type;
//			NamedElement recordTypeName = recType.getElm();
//			if (recordTypeName instanceof DataClassifier) {
//				result.addAll(getDataClassifierRangeConstraintExprs(name, (DataClassifier) recordTypeName));
//			} else if (recordTypeName instanceof RecordDef) {
//				result.addAll(((RecordDef) recordTypeName).getArgs().stream()
//						.map(arg -> getVariableRangeConstraintExprs(name + "." + arg.getName(), arg.getType()))
//						.flatMap(List::stream).collect(Collectors.toList()));
//			}
//		} else if (type instanceof ArrayType) {
//			ArrayType arrayType = (ArrayType) type;
//			throw new AgreeException("ERROR: " + arrayType.getStem().getOwner().getElementRoot().getFullName()
//					+ " unhandled in ranged type");
//		}
//		return result;
//	}

	private List<AgreeStatement> getConstraintsFromArgs(List<Arg> args, EObject reference) {
		List<AgreeStatement> constraints = new ArrayList<>();
		for (Arg arg : args) {
			List<Expr> argConstraints = getConstraintsFromTypeDef(arg.getName(), AgreeTypeSystem.typeDefFromType(arg.getType()));
			if (!argConstraints.isEmpty()) {
				constraints.add(new AgreeStatement("Type predicate on '" + arg.getName() + "'", argConstraints.stream()
						.reduce(new BoolExpr(true), (a, b) -> new BinaryExpr(a, BinaryOp.AND, b)), reference));
			}
		}
		return constraints;
	}

//	private static boolean hasIntegerRangeProperty(Classifier classifier) {
//		return classifier.getAllPropertyAssociations().stream()
//				.anyMatch(pa -> "Integer_Range".equals(pa.getProperty().getName()));
//	}
//
//	private static boolean hasRealRangeProperty(Classifier classifier) {
//		return classifier.getAllPropertyAssociations().stream()
//				.anyMatch(pa -> "Real_Range".equals(pa.getProperty().getName()));
//	}
//
//	private static List<PropertyAssociation> getIntegerRangePropertyAssociations(
//			Classifier classifier) {
//		return classifier.getAllPropertyAssociations().stream()
//				.filter(pa -> "Integer_Range".equals(pa.getProperty().getName())).collect(Collectors.toList());
//	}
//
//	private static List<PropertyAssociation> getRealRangePropertyAssociations(Classifier classifier) {
//		return classifier.getAllPropertyAssociations().stream()
//				.filter(pa -> "Real_Range".equals(pa.getProperty().getName())).collect(Collectors.toList());
//	}

	private List<AgreeStatement> getAssumptionStatements(EList<SpecStatement> specs) {
		List<AgreeStatement> assumptions = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof AssumeStatement) {
				AssumeStatement assumption = (AssumeStatement) spec;
				String str = assumption.getStr();
				if (assumption.getExpr() != null) {
					assumptions.add(new AgreeStatement(str, doSwitch(assumption.getExpr()), assumption));
				} else {
					PatternStatement pattern = assumption.getPattern();
					assumptions.add(new AgreePatternBuilder(str, assumption, this).doSwitch(pattern));
				}
			}
		}
		return assumptions;
	}

	private List<AgreeStatement> getGuaranteeStatements(EList<SpecStatement> specs) {
		List<AgreeStatement> guarantees = new ArrayList<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof GuaranteeStatement) {
				GuaranteeStatement guarantee = (GuaranteeStatement) spec;
				String str = guarantee.getStr();
				if (guarantee.getExpr() != null) {
					guarantees.add(new AgreeStatement(str, doSwitch(guarantee.getExpr()), guarantee));
				} else {
					PatternStatement pattern = guarantee.getPattern();
					guarantees.add(new AgreePatternBuilder(str, guarantee, this).doSwitch(pattern));
				}
			}
		}
		return guarantees;
	}

	private AgreeContractSubclause getAgreeAnnex(ComponentClassifier comp) {
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(comp,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			if (annex instanceof AgreeContractSubclause) {
				// in newer versions of osate the annex this returns annexes in
				// the type
				// as well as the implementation. We want the annex in the
				// specific component
				EObject container = annex.eContainer();
				while (!(container instanceof ComponentClassifier)) {
					container = container.eContainer();
				}
				if (container == comp) {
					return (AgreeContractSubclause) annex;
				}
			}
		}
		return null;
	}


// BEGIN CASE EXPRESSION STATEMENTS
	@Override
	public Expr caseRecordUpdateExpr(RecordUpdateExpr upExpr) {

		Expr lustreExpr = doSwitch(upExpr.getRecord());
		Expr lustreArgExpr = doSwitch(upExpr.getExpr());
		lustreExpr = new jkind.lustre.RecordUpdateExpr(lustreExpr, upExpr.getKey().getName(), lustreArgExpr);

		return lustreExpr;
	}

	@Override
	public Expr caseTimeOfExpr(TimeOfExpr timeExpr) {
		NamedElement namedEl = timeExpr.getId();
		String idStr = namedEl.getName();

		AgreeVar var = timeOfVarMap.get(idStr);
		if (var == null) {
			String varStr = idStr + AgreePatternTranslator.TIME_SUFFIX;
			var = new AgreeVar(varStr, NamedType.REAL, namedEl);
			timeOfVarMap.put(idStr, var);
		}

		return new IdExpr(var.id);
	}

	@Override
	public Expr caseTimeRiseExpr(TimeRiseExpr timeExpr) {
		NamedElement namedEl = timeExpr.getId();
		String idStr = namedEl.getName();

		AgreeVar var = timeRiseVarMap.get(idStr);
		if (var == null) {
			String varStr = idStr + AgreePatternTranslator.RISE_SUFFIX;
			var = new AgreeVar(varStr, NamedType.REAL, namedEl);
			timeRiseVarMap.put(idStr, var);
		}

		return new IdExpr(var.id);
	}

	@Override
	public Expr caseTimeFallExpr(TimeFallExpr timeExpr) {
		NamedElement namedEl = timeExpr.getId();
		String idStr = namedEl.getName();

		AgreeVar var = timeFallVarMap.get(idStr);
		if (var == null) {
			String varStr = idStr + AgreePatternTranslator.FALL_SUFFIX;
			var = new AgreeVar(varStr, NamedType.REAL, namedEl);
			timeFallVarMap.put(idStr, var);
		}

		return new IdExpr(var.id);
	}

	@Override
	public Expr caseRecordLitExpr(RecordLitExpr recExpr) {

		EList<NamedElement> agreeArgs = recExpr.getArgs();
		EList<com.rockwellcollins.atc.agree.agree.Expr> agreeArgExprs = recExpr.getArgExpr();
		Map<String, Expr> argExprMap = new HashMap<>();

		for (int i = 0; i < agreeArgs.size(); i++) {
			NamedElement agreeArg = agreeArgs.get(i);
			com.rockwellcollins.atc.agree.agree.Expr agreeExpr = agreeArgExprs.get(i);

			Expr lustreExpr = doSwitch(agreeExpr);
			String argName = agreeArg.getName();

			argExprMap.put(argName, lustreExpr);

		}

		DoubleDotRef recId = recExpr.getRecordType();
		String recName = recId.getElm().getQualifiedName().replace("::", "__").replace(".", dotChar);
		return new jkind.lustre.RecordExpr(recName, argExprMap);

	}

	@Override
	public Expr caseFloorCast(FloorCast floor) {
		Expr expr = doSwitch(floor.getExpr());
		Expr castExpr = new CastExpr(NamedType.INT, expr);
		return castExpr;
	}

	@Override
	public Expr caseRealCast(RealCast real) {
		Expr expr = doSwitch(real.getExpr());
		Expr castExpr = new CastExpr(NamedType.REAL, expr);
		return castExpr;
	}

	/*
	 * @Override
	 * public Expr caseBinaryNonLinearExpr(com.rockwellcollins.atc.agree.agree.BinaryNonLinearExpr expr) {
	 * Expr leftExpr = doSwitch(expr.getLeft());
	 * Expr rightExpr = doSwitch(expr.getRight());
	 * String op = expr.getOp();
	 * BinaryOp binOp = null;
	 * switch (op) {
	 * case "pow": binOp = BinaryOp.POW; break;
	 * case "arctan2" : binOp = BinaryOp.ARCTAN2; break;
	 * }
	 * assert (binOp != null);
	 * BinaryExpr binExpr = new BinaryExpr(leftExpr, binOp, rightExpr);
	 *
	 * return binExpr;
	 * }
	 *
	 * @Override
	 * public Expr caseUnaryNonLinearExpr(com.rockwellcollins.atc.agree.agree.UnaryNonLinearExpr expr) {
	 * Expr sub = doSwitch(expr.getExpr());
	 * String op = expr.getOp();
	 * UnaryOp unyOp = null;
	 * switch (op) {
	 * case "exp": unyOp = UnaryOp.EXP; break;
	 * case "log": unyOp = UnaryOp.LOG; break;
	 * case "sqrt": unyOp = UnaryOp.SQRT; break;
	 * case "sin": unyOp = UnaryOp.SIN; break;
	 * case "cos": unyOp = UnaryOp.COS; break;
	 * case "tan": unyOp = UnaryOp.TAN; break;
	 * case "asin":
	 * case "arcsin": unyOp = UnaryOp.ARCSIN; break;
	 * case "acos":
	 * case "arccos": unyOp = UnaryOp.ARCCOS; break;
	 * case "atan":
	 * case "arctan": unyOp = UnaryOp.ARCTAN; break;
	 * case "sinh" : unyOp = UnaryOp.SINH; break;
	 * case "cosh" : unyOp = UnaryOp.COSH; break;
	 * case "tanh" : unyOp = UnaryOp.TANH; break;
	 * case "matan":
	 * case "marctan": unyOp = UnaryOp.MATAN; break;
	 * }
	 * assert (unyOp != null);
	 * UnaryExpr unyExpr = new UnaryExpr(unyOp, sub);
	 *
	 * return unyExpr;
	 *
	 * }
	 */

	@Override
	public Expr caseBinaryExpr(com.rockwellcollins.atc.agree.agree.BinaryExpr expr) {

		Expr leftExpr = doSwitch(expr.getLeft());
		Expr rightExpr = doSwitch(expr.getRight());

		String op = expr.getOp();

		BinaryOp binOp = null;
		switch (op) {
		case "+":
			binOp = BinaryOp.PLUS;
			break;
		case "-":
			binOp = BinaryOp.MINUS;
			break;
		case "*":
			binOp = BinaryOp.MULTIPLY;
			break;
		case "/":
			binOp = BinaryOp.DIVIDE;
			break;
		case "mod":
			binOp = BinaryOp.MODULUS;
			break;
		case "div":
			binOp = BinaryOp.INT_DIVIDE;
			break;
		case "<=>":
		case "=":
			binOp = BinaryOp.EQUAL;
			break;
		case "!=":
		case "<>":
			binOp = BinaryOp.NOTEQUAL;
			break;
		case ">":
			binOp = BinaryOp.GREATER;
			break;
		case "<":
			binOp = BinaryOp.LESS;
			break;
		case ">=":
			binOp = BinaryOp.GREATEREQUAL;
			break;
		case "<=":
			binOp = BinaryOp.LESSEQUAL;
			break;
		case "or":
			binOp = BinaryOp.OR;
			break;
		case "and":
			binOp = BinaryOp.AND;
			return LustreExprFactory.makeANDExpr(leftExpr, rightExpr);
		case "xor":
			binOp = BinaryOp.XOR;
			break;
		case "=>":
			binOp = BinaryOp.IMPLIES;
			break;
		case "->":
			binOp = BinaryOp.ARROW;
			break;
		}

		assert (binOp != null);
		BinaryExpr binExpr = new BinaryExpr(leftExpr, binOp, rightExpr);

		return binExpr;
	}

	@Override
	public Expr caseBoolLitExpr(BoolLitExpr expr) {
		return new BoolExpr(expr.getVal().getValue());
	}

	@Override
	public Expr caseCallExpr(CallExpr expr) {
		NamedElement namedEl = expr.getRef().getElm();
		String fnName = AgreeUtils.getNodeName(namedEl);
		boolean found = false;
		for (Node node : globalNodes) {
			if (node.id.equals(fnName)) {
				found = true;
				break;
			}
		}

		if (!found) {
			DoubleDotRef fn = expr.getRef();
			doSwitch(fn.getElm());
			// for dReal integration
			if (fnName.substring(0, 7).equalsIgnoreCase("dreal__")) {
				fnName = namedEl.getName();
			}
		}

		List<Expr> argResults = new ArrayList<>();

		for (com.rockwellcollins.atc.agree.agree.Expr argExpr : expr.getArgs()) {
			argResults.add(doSwitch(argExpr));
		}

		NodeCallExpr nodeCall = new NodeCallExpr(fnName.replace("::", "__"), argResults);
		return nodeCall;
	}

	@Override
	public Expr caseFnDef(FnDef fnDef) {

		String nodeName = AgreeUtils.getNodeName(fnDef).replace("::", "__");

		for (Node node : globalNodes) {
			if (node.id.equals(nodeName)) {
				return null;
			}
		}
		List<VarDecl> inputs = agreeVarsFromArgs(fnDef.getArgs(), null);
		Expr bodyExpr = doSwitch(fnDef.getExpr());

		// EGM: array-backend
		// Type outType = getNamedType(AgreeTypeUtils.getTypeName(fnDef.getType(), typeMap, globalTypes));
		Type outType = symbolTable.updateLustreTypeMap(AgreeTypeSystem.typeDefFromType(fnDef.getType()));

		if (outType != null) {

			VarDecl outVar = new VarDecl("_outvar", outType);
			List<VarDecl> outputs = Collections.singletonList(outVar);
			Equation eq = new Equation(new IdExpr("_outvar"), bodyExpr);
			List<Equation> eqs = Collections.singletonList(eq);

			NodeBuilder builder = new NodeBuilder(nodeName);
			builder.addInputs(inputs);
			builder.addOutputs(outputs);
			builder.addEquations(eqs);

			Node node = builder.build();
			addToNodeList(node);
		}

		return null;
	}

	@Override
	public Expr caseLinearizationDef(LinearizationDef expr) {
		NodeDef linearization = linearizationRewriter.addLinearization(expr);
		caseNodeDef(linearization);
		return null;
	}

	@Override
	public Expr caseNodeDef(NodeDef expr) {

		String nodeName = AgreeUtils.getNodeName(expr);

		for (Node node : globalNodes) {
			if (node.id.equals(nodeName)) {
				return null;
			}
		}

		List<VarDecl> inputs = agreeVarsFromArgs(expr.getArgs(), null);
		List<VarDecl> outputs = agreeVarsFromArgs(expr.getRets(), null);
		NodeBodyExpr body = expr.getNodeBody();
		List<VarDecl> internals = agreeVarsFromArgs(body.getLocs(), null);
		List<Equation> eqs = new ArrayList<>();
		List<String> props = new ArrayList<>();

		// TODO are node lemmas depricated?
		String lemmaName = "__nodeLemma";
		int lemmaIndex = 0;
		for (NodeStmt stmt : body.getStmts()) {
			if (stmt instanceof NodeLemma) {
				NodeLemma nodeLemma = (NodeLemma) stmt;
				String propName = lemmaName + lemmaIndex++;
				IdExpr eqId = new IdExpr(propName);
				internals.add(new VarDecl(eqId.id, NamedType.BOOL));
				Expr eqExpr = doSwitch(nodeLemma.getExpr());
				Equation eq = new Equation(eqId, eqExpr);
				eqs.add(eq);
				props.add(eqId.id);
			} else if (stmt instanceof NodeEq) {
				eqs.add(nodeEqToEq((NodeEq) stmt));
			}
		}

		// nodeLemmaNames.put(nodeName, lemmaNames);

		NodeBuilder builder = new NodeBuilder(nodeName);
		builder.addInputs(inputs);
		builder.addOutputs(outputs);
		builder.addLocals(internals);
		builder.addEquations(eqs);
		builder.addProperties(props);

		Node n = builder.build();
		addToNodeList(n);
		return null;
	}

// helper method for above
	private Equation nodeEqToEq(NodeEq nodeEq) {
		Expr expr = doSwitch(nodeEq.getExpr());
		List<IdExpr> ids = new ArrayList<>();
		for (Arg arg : nodeEq.getLhs()) {
			ids.add(new IdExpr(arg.getName()));
		}
		Equation eq = new Equation(ids, expr);
		return eq;
	}

	@Override
	public Expr caseGetPropertyExpr(GetPropertyExpr expr) {

		NamedElement propName = expr.getProp();
		PropertyExpression propVal;

		if (propName instanceof Property) {

			ComponentRef cr = expr.getComponentRef();
			NamedElement compName = null;
			if (cr instanceof DoubleDotRef) {
				compName = ((DoubleDotRef) cr).getElm();
			} else if (cr instanceof ThisRef) {
				compName = curInst;
			}

			Property prop = (Property) propName;
			propVal = AgreeUtils.getPropExpression(compName, prop);

			if (propVal == null) {
				if (Activator.getDefault().getPreferenceStore()
						.getBoolean(PreferenceConstants.PREF_UNSPECIFIED_AADL_PROPERTIES)) {
					String propInputName = unspecifiedAadlPropertyPrefix + compName.getName() + dotChar
							+ prop.getName();
					unspecifiedAadlProperties.put(propInputName, expr);
					return new IdExpr(propInputName);
				} else {
					throw new AgreeException("Could not locate property value '" + prop.getQualifiedName()
					+ "' in component '" + compName.getName() + "'.  Is it possible "
					+ "that a 'this' statement is used in a context in which it wasn't supposed to?"
					+ "  Analysis of unspecified AADL properties as inputs may be enabled in the AGREE preferences.");
				}
			}

		} else {
			propVal = AgreeUtils.getPropExpression((PropertyConstant) propName);

			if (propVal == null) {
				throw new AgreeException("Could not locate property value '" + propName.getQualifiedName());
			}
		}

		Expr res = null;
		if (propVal != null) {
			if (propVal instanceof StringLiteral) {
				// StringLiteral value = (StringLiteral) propVal;
				// nodeStr += value.getValue() + ")";
				throw new AgreeException(
						"Property value for '" + propName.getQualifiedName() + "' cannot be of string type");
			} else if (propVal instanceof NamedValue) {
				// NamedValue namedVal = (NamedValue) propVal;
				// AbstractNamedValue absVal = namedVal.getNamedValue();
				// assert (absVal instanceof EnumerationLiteral);
				// EnumerationLiteral enVal = (EnumerationLiteral) absVal;
				throw new AgreeException(
						"Property value for '" + propName.getQualifiedName() + "' cannot be of enumeration type");
			} else if (propVal instanceof BooleanLiteral) {
				BooleanLiteral value = (BooleanLiteral) propVal;
				res = new BoolExpr(value.getValue());
			} else if (propVal instanceof IntegerLiteral) {
				IntegerLiteral value = (IntegerLiteral) propVal;
				res = new IntExpr(BigInteger.valueOf((long) value.getScaledValue()));
			} else {
				assert (propVal instanceof RealLiteral);
				RealLiteral value = (RealLiteral) propVal;
				res = new RealExpr(BigDecimal.valueOf(value.getValue()));
			}
		}
		assert (res != null);

		return res;
	}

	private void gatherUnspecifiedAadlProperties(Map<String, GetPropertyExpr> unspecifiedAadlProperties,
			List<AgreeVar> inputs, List<AgreeStatement> assumptions, List<AgreeStatement> guarantees) {

		for (Entry<String, GetPropertyExpr> entry : unspecifiedAadlProperties.entrySet()) {
			String propInputName = entry.getKey();
			GetPropertyExpr expr = entry.getValue();
			Property prop = (Property) expr.getProp();
			Expr propInputIdExpr = new IdExpr(propInputName);

			Type type;
			Expr bound = null;
			if (prop.getReferencedPropertyType() instanceof AadlBoolean) {
				type = NamedType.BOOL;
			} else if (prop.getReferencedPropertyType() instanceof AadlInteger) {
				AadlInteger aadlInteger = (AadlInteger) prop.getReferencedPropertyType();
				type = NamedType.INT;

				if (aadlInteger.getRange() != null) {
					PropertyExpression lowerBound = aadlInteger.getRange().getLowerBound();
					PropertyExpression upperBound = aadlInteger.getRange().getUpperBound();

					Expr lowVal = new IntExpr(
							BigDecimal.valueOf(((IntegerLiteral) lowerBound).getScaledValue()).toBigInteger());
					Expr highVal = new IntExpr(
							BigDecimal.valueOf(((IntegerLiteral) upperBound).getScaledValue()).toBigInteger());
					Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, propInputIdExpr);
					Expr highBound = new BinaryExpr(propInputIdExpr, BinaryOp.LESSEQUAL, highVal);
					bound = LustreExprFactory.makeANDExpr(lowBound, highBound);
				}
			} else if (prop.getReferencedPropertyType() instanceof AadlReal) {
				AadlReal aadlReal = (AadlReal) prop.getReferencedPropertyType();
				type = NamedType.REAL;

				if (aadlReal.getRange() != null) {
					PropertyExpression lowerBound = aadlReal.getRange().getLowerBound();
					PropertyExpression upperBound = aadlReal.getRange().getUpperBound();

					Expr lowVal = new RealExpr(BigDecimal.valueOf(((RealLiteral) lowerBound).getValue()));
					Expr highVal = new RealExpr(BigDecimal.valueOf(((RealLiteral) upperBound).getValue()));
					Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, propInputIdExpr);
					Expr highBound = new BinaryExpr(propInputIdExpr, BinaryOp.LESSEQUAL, highVal);
					bound = LustreExprFactory.makeANDExpr(lowBound, highBound);
				}
			} else {
				throw new AgreeException(
						"Could not locate property value '\" + prop.getFullName() + \"' in component '\"\n"
								+ "//						+ compName.getName() + \"'.   Analysis on abstract values not supported for "
								+ "AADL property type " + prop.getReferencedPropertyType() + ".");
			}
			// NamedType type = getNamedType(AgreeTypeUtils.getTypeName(arg.getType(), typeMap, globalTypes));

			AgreeVar propInputVar = new AgreeVar(propInputName, type, expr, curInst, null);

			Expr constraint = getUnchangingConstraintExpr(propInputIdExpr);
			if (bound != null) {
				constraint = LustreExprFactory.makeANDExpr(constraint, bound);
			}

			inputs.add(propInputVar);
			assumptions.add(new AgreeStatement("", constraint, prop));
		}
	}

	private static Expr getUnchangingConstraintExpr(Expr expr) {
		return new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW,
				new BinaryExpr(expr, BinaryOp.EQUAL, new UnaryExpr(UnaryOp.PRE, expr)));
	}

	@Override
	public Expr caseIfThenElseExpr(com.rockwellcollins.atc.agree.agree.IfThenElseExpr expr) {
		Expr condExpr = doSwitch(expr.getA());
		Expr thenExpr = doSwitch(expr.getB());
		Expr elseExpr = doSwitch(expr.getC());

		Expr result = new IfThenElseExpr(condExpr, thenExpr, elseExpr);

		return result;
	}

	@Override
	public Expr caseIntLitExpr(IntLitExpr expr) {
		return new IntExpr(BigInteger.valueOf(Integer.parseInt(expr.getVal())));
	}

	private String dottedNameToString(com.rockwellcollins.atc.agree.agree.Expr e) {
		if (e instanceof NamedElmExpr) {
			return ((NamedElmExpr) e).getElm().getName();
		} else if (e instanceof SelectionExpr) {
			return dottedNameToString(((SelectionExpr) e).getTarget()) + dotChar
					+ ((SelectionExpr) e).getField().getName();
		}

		throw new AgreeException("Pattern");
	}

	@Override
	public Expr caseTagExpr(TagExpr e) {

		String stemString = null;
		if (e.getStem() instanceof NamedElmExpr) {
			stemString = ((NamedElmExpr) e.getStem()).getElm().getName();
		} else if (e.getStem() instanceof SelectionExpr) {
			stemString = dottedNameToString(e.getStem());
		} else {
			throw new AgreeException("Pattern");
//			=======
//					while (id.getSub() != null
//					&& (base instanceof FeatureGroup || base instanceof AadlPackage || base instanceof Subcomponent)) {
//						jKindVar += base.getName().replace("::", "__") + dotChar;
//						aadlVar += base.getName() + ".";
//						id = id.getSub();
//						base = id.getBase();
//						>>>>>>> origin/develop
		}

		String tag = e.getTag();
		if (tag != null) {

			switch (tag) {
			case "_CLK":
				IdExpr clockId = new IdExpr(stemString + clockIDSuffix);
				return clockId;
			default:
				throw new AgreeException("use of uknown tag: '" + tag + "' in expression following " + stemString);
			}
		}

		throw new AgreeException("Pattern");

	}

	@Override
	public Expr caseSelectionExpr(SelectionExpr e) {

		if (e.getTarget() instanceof NamedElmExpr) {
			NamedElement base = ((NamedElmExpr) e.getTarget()).getElm();
			if (base instanceof AadlPackage || base instanceof Subcomponent || base instanceof FeatureGroup) {
				NamedElement field = e.getField();
				if (field instanceof ConstStatement) {
					// constant propagation
					return doSwitch(((ConstStatement) field).getExpr());
				} else {
					return new IdExpr(base.getName() + dotChar + field.getName());
				}
			} else {
				return new RecordAccessExpr(doSwitch(e.getTarget()), e.getField().getName());
			}
		} else {
			return new RecordAccessExpr(doSwitch(e.getTarget()), e.getField().getName());
		}

	}

	@Override
	public Expr caseNamedElmExpr(NamedElmExpr nelmExpr) {
		NamedElement ne = nelmExpr.getElm();
		if (ne instanceof ConstStatement) {
			// constant propagation
			return doSwitch(((ConstStatement) ne).getExpr());
		}

		return new IdExpr(ne.getName());
	}

	@Override
	public Expr caseArrayLiteralExpr(ArrayLiteralExpr agreeExpr) {
		List<Expr> elems = new ArrayList<>();
		for (com.rockwellcollins.atc.agree.agree.Expr agreeElem : agreeExpr.getElems()) {
			Expr elem = doSwitch(agreeElem);
			elems.add(elem);
		}
		return new ArrayExpr(elems);
	}


	@Override
	public Expr caseArrayUpdateExpr(ArrayUpdateExpr expr) {
		Expr arrayExpr = doSwitch(expr.getArray());
		for (int i = 0; i < expr.getIndices().size(); i++) {
			Expr indexExpr = doSwitch(expr.getIndices().get(i));
			Expr newExpr = doSwitch(expr.getValueExprs().get(i));
			arrayExpr = new jkind.lustre.ArrayUpdateExpr(arrayExpr, indexExpr, newExpr);

		}
		return arrayExpr;
	}

	@Override
	public Expr caseArraySubExpr(ArraySubExpr expr) {
		Expr index = doSwitch(expr.getIndex());
		Expr array = doSwitch(expr.getExpr());
		return new ArrayAccessExpr(array, index);
	}

	@Override
	public Expr caseIndicesExpr(IndicesExpr expr) {
		AgreeTypeSystem.TypeDef arrayTypeDef = AgreeTypeSystem.infer(expr.getArray());

		if (arrayTypeDef instanceof AgreeTypeSystem.ArrayTypeDef) {
			int size = ((AgreeTypeSystem.ArrayTypeDef) arrayTypeDef).size;
			List<Expr> elems = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				elems.add(new IntExpr(i));
			}

			return new ArrayExpr(elems);
		}
		throw new RuntimeException("Error caseIndicesExpr");
	}

	private Expr substitute(Expr context, String name, Expr newExpr) {

		if (context instanceof IdExpr) {
			if (((IdExpr) context).id.equals(name)) {
				return newExpr;
			} else {
				return context;
			}
		} else if (context instanceof ArrayAccessExpr) {

			Expr arrayExpr = substitute(((ArrayAccessExpr) context).array, name, newExpr);
			Expr indexExpr = substitute(((ArrayAccessExpr) context).index, name, newExpr);
			return new ArrayAccessExpr(arrayExpr, indexExpr);

		} else if (context instanceof ArrayExpr) {
			List<Expr> elems = new ArrayList<>();
			for (Expr raw : ((ArrayExpr) context).elements) {
				Expr elem = substitute(raw, name, newExpr);
				elems.add(elem);
			}
			return new ArrayExpr(elems);

		} else if (context instanceof jkind.lustre.ArrayUpdateExpr) {
			Expr arrayExpr = substitute(((jkind.lustre.ArrayUpdateExpr) context).array, name, newExpr);
			Expr indexExpr = substitute(((jkind.lustre.ArrayUpdateExpr) context).index, name, newExpr);
			Expr valExpr = substitute(((jkind.lustre.ArrayUpdateExpr) context).value, name, newExpr);
			return new jkind.lustre.ArrayUpdateExpr(arrayExpr, indexExpr, valExpr);

		} else if (context instanceof BinaryExpr) {
			Expr left = substitute(((BinaryExpr) context).left, name, newExpr);
			Expr right = substitute(((BinaryExpr) context).right, name, newExpr);
			return new BinaryExpr(left, ((BinaryExpr) context).op, right);

		} else if (context instanceof BoolExpr) {
			return context;
		} else if (context instanceof CastExpr) {
			Expr expr = substitute(((CastExpr) context).expr, name, newExpr);
			return new CastExpr(((CastExpr) context).type, expr);
		} else if (context instanceof CondactExpr) {
			Expr clock = substitute(((CondactExpr) context).clock, name, newExpr);
			Expr call = substitute(((CondactExpr) context).call, name, newExpr);
			List<Expr> args = new ArrayList<>();
			for (Expr raw : ((CondactExpr) context).args) {
				Expr arg = substitute(raw, name, newExpr);
				args.add(arg);
			}
			return new CondactExpr(clock, (NodeCallExpr) call, args);


		} else if (context instanceof FunctionCallExpr) {
			List<Expr> args = new ArrayList<>();
			for (Expr raw : ((FunctionCallExpr) context).args) {
				Expr arg = substitute(raw, name, newExpr);
				args.add(arg);
			}
			return new FunctionCallExpr(((FunctionCallExpr) context).function, args);

		} else if (context instanceof IfThenElseExpr) {
			Expr cond = substitute(((IfThenElseExpr) context).cond, name, newExpr);
			Expr thenExpr = substitute(((IfThenElseExpr) context).thenExpr, name, newExpr);
			Expr elseExpr = substitute(((IfThenElseExpr) context).elseExpr, name, newExpr);
			return new IfThenElseExpr(cond, thenExpr, elseExpr);

		} else if (context instanceof IntExpr) {
			return context;

		} else if (context instanceof NodeCallExpr) {
			List<Expr> args = new ArrayList<>();
			for (Expr raw : ((NodeCallExpr) context).args) {
				Expr arg = substitute(raw, name, newExpr);
				args.add(arg);
			}
			return new FunctionCallExpr(((NodeCallExpr) context).node, args);

		} else if (context instanceof RealExpr) {
			return context;

		} else if (context instanceof RecordAccessExpr) {
			Expr rec = substitute(((RecordAccessExpr) context).record, name, newExpr);
			return new RecordAccessExpr(rec, ((RecordAccessExpr) context).field);

		} else if (context instanceof RecordExpr) {

			Map<String, Expr> fields = new TreeMap<>();
			for (Entry<String, Expr> raw : ((RecordExpr) context).fields.entrySet()) {
				String key = raw.getKey();
				Expr fieldExpr = substitute(raw.getValue(), name, newExpr);
				fields.put(key, fieldExpr);
			}

			return new RecordExpr(((RecordExpr) context).id, fields);

		} else if (context instanceof jkind.lustre.RecordUpdateExpr) {

			Expr rec = substitute(((jkind.lustre.RecordUpdateExpr) context).record, name, newExpr);
			Expr valueExpr = substitute(((jkind.lustre.RecordUpdateExpr) context).value, name, newExpr);
			return new jkind.lustre.RecordUpdateExpr(rec, ((jkind.lustre.RecordUpdateExpr) context).field, valueExpr);

		} else if (context instanceof TupleExpr) {

			List<Expr> elems = new ArrayList<>();
			for (Expr raw : ((TupleExpr) context).elements) {
				Expr elem = substitute(raw, name, newExpr);
				elems.add(elem);
			}
			return new TupleExpr(elems);

		} else if (context instanceof UnaryExpr) {
			Expr expr = substitute(((UnaryExpr) context).expr, name, newExpr);
			return new UnaryExpr(((UnaryExpr) context).op, expr);
		}

		throw new RuntimeException("Error: substitute - " + context);
	}

	@Override
	public Expr caseForallExpr(ForallExpr expr) {
		com.rockwellcollins.atc.agree.agree.Expr arrayExpr = expr.getArray();
		Expr array = doSwitch(arrayExpr);

		AgreeTypeSystem.TypeDef agreeType = AgreeTypeSystem.infer(arrayExpr);
		int size = 0;
		if (agreeType instanceof AgreeTypeSystem.ArrayTypeDef) {
			size = ((AgreeTypeSystem.ArrayTypeDef) agreeType).size;
		} else {
			throw new AgreeException("ERROR: caseForallExpr - '" + agreeType.getClass() + "' not handled");
		}
		NamedID binding = expr.getBinding();
		Expr final_expr = new BoolExpr(true);

		for (int i = 0; i < size; ++i) {
			Expr arrayAccess = new ArrayAccessExpr(array, i);
			Expr body = substitute(doSwitch(expr.getExpr()), binding.getName(), arrayAccess);
			final_expr = LustreExprFactory.makeANDExpr(final_expr, body);
		}

		return final_expr;
	}

	@Override
	public Expr caseExistsExpr(ExistsExpr expr) {
		com.rockwellcollins.atc.agree.agree.Expr arrayExpr = expr.getArray();
		Expr array = doSwitch(arrayExpr);

		AgreeTypeSystem.TypeDef agreeType = AgreeTypeSystem.infer(arrayExpr);
		int size = 0;
		if (agreeType instanceof AgreeTypeSystem.ArrayTypeDef) {
			size = ((AgreeTypeSystem.ArrayTypeDef) agreeType).size;
		} else {
			throw new AgreeException("ERROR: caseExistsExpr - '" + agreeType.getClass() + "' not handled");
		}
		NamedID binding = expr.getBinding();
		Expr final_expr = new BoolExpr(true);

		for (int i = 0; i < size; ++i) {
			Expr arrayAccess = new ArrayAccessExpr(array, i);
			Expr body = substitute(doSwitch(expr.getExpr()), binding.getName(), arrayAccess);
			final_expr = LustreExprFactory.makeORExpr(final_expr, body);
		}

		return final_expr;
	}

	@Override
	public Expr caseFlatmapExpr(FlatmapExpr expr) {
		AgreeTypeSystem.TypeDef agreeType = AgreeTypeSystem.infer(expr.getArray());
		int size = 0;
		if (agreeType instanceof AgreeTypeSystem.ArrayTypeDef) {
			size = ((AgreeTypeSystem.ArrayTypeDef) agreeType).size;
		} else {
			throw new AgreeException("ERROR: caseFlatmapExpr");
		}
		Expr array = doSwitch(expr.getArray());
		NamedID binding = expr.getBinding();
		List<Expr> elems = new ArrayList<>();
		for (int i = 0; i < size; ++i) {
			Expr arrayAccess = new ArrayAccessExpr(array, i);
			Expr body = substitute(doSwitch(expr.getExpr()), binding.getName(), arrayAccess);

			AgreeTypeSystem.TypeDef innerArrType = AgreeTypeSystem.infer(expr.getExpr());
			if (innerArrType instanceof AgreeTypeSystem.ArrayTypeDef) {
				int innerSize = ((AgreeTypeSystem.ArrayTypeDef) innerArrType).size;
				for (int j = 0; j < innerSize; j++) {
					Expr innerAccess = new ArrayAccessExpr(body, j);
					elems.add(innerAccess);
				}
			}

		}
		return new ArrayExpr(elems);

	}

	@Override
	public Expr caseFoldLeftExpr(FoldLeftExpr expr) {
		AgreeTypeSystem.TypeDef agreeType = AgreeTypeSystem.infer(expr.getArray());

		int size = 0;
		if (agreeType instanceof AgreeTypeSystem.ArrayTypeDef) {
			size = ((AgreeTypeSystem.ArrayTypeDef) agreeType).size;
		} else {
			throw new AgreeException("ERROR: caseFoldLeftExpr");
		}
		Expr array = doSwitch(expr.getArray());
		Expr accExpr = doSwitch(expr.getInitial());
		NamedID binding = expr.getBinding();
		NamedID accBinding = expr.getAccumulator();
		for (int i = 0; i < size; i++) {
			Expr arrayAccess = new ArrayAccessExpr(array, i);
			accExpr = substitute(substitute(doSwitch(expr.getExpr()), binding.getName(), arrayAccess),
					accBinding.getName(), accExpr);
		}
		return accExpr;
	}

	@Override
	public Expr caseFoldRightExpr(FoldRightExpr expr) {
		AgreeTypeSystem.TypeDef agreeType = AgreeTypeSystem.infer(expr.getArray());

		int size = 0;
		if (agreeType instanceof AgreeTypeSystem.ArrayTypeDef) {
			size = ((AgreeTypeSystem.ArrayTypeDef) agreeType).size;
		} else {
			throw new AgreeException("ERROR: caseFoldRightExpr");
		}
		Expr array = doSwitch(expr.getArray());
		Expr accExpr = doSwitch(expr.getInitial());
		NamedID binding = expr.getBinding();
		NamedID accBinding = expr.getAccumulator();
		for (int i = size - 1; i >= 0; i--) {
			Expr arrayAccess = new ArrayAccessExpr(array, i);
			accExpr = substitute(substitute(doSwitch(expr.getExpr()), binding.getName(), arrayAccess),
					accBinding.getName(), accExpr);
		}
		return accExpr;
	}


	@Override
	public Expr caseEnumLitExpr(EnumLitExpr aadlEnum) {
		NamedElement ne = aadlEnum.getEnumType().getElm();
		String typeStr = ne.getQualifiedName().replace("::", "__").replace(".", dotChar);
		return new IdExpr(typeStr + "_" + aadlEnum.getValue());
	}

	@Override
	public Expr casePreExpr(PreExpr expr) {
		Expr res = doSwitch(expr.getExpr());

		return new UnaryExpr(UnaryOp.PRE, res);
	}

	@Override
	public Expr caseEventExpr(EventExpr expr) {
		String eventStr = expr.getId().getName() + eventSuffix;
		return new IdExpr(eventStr);
	}

	@Override
	public Expr caseLatchedExpr(LatchedExpr expr) {

		IdExpr nestIdExpr = (IdExpr) doSwitch(expr.getExpr());
		String latchedStr = nestIdExpr.id + AgreeInlineLatchedConnections.LATCHED_SUFFIX;
		return new IdExpr(latchedStr);
	}

	@Override
	public Expr casePrevExpr(PrevExpr expr) {
		Expr delayExpr = doSwitch(expr.getDelay());
		Expr initExpr = doSwitch(expr.getInit());

		Expr preExpr = new UnaryExpr(UnaryOp.PRE, delayExpr);

		Expr res = new BinaryExpr(initExpr, BinaryOp.ARROW, preExpr);

		return res;
	}

	@Override
	public Expr caseRealLitExpr(RealLitExpr expr) {
		return new RealExpr(BigDecimal.valueOf(Double.parseDouble(expr.getVal())));
	}

	@Override
	public Expr caseTimeExpr(TimeExpr time) {
		return AgreePatternTranslator.timeExpr;
	}

	@Override
	public Expr caseUnaryExpr(com.rockwellcollins.atc.agree.agree.UnaryExpr expr) {
		Expr subExpr = doSwitch(expr.getExpr());

		Expr res = null;
		switch (expr.getOp()) {
		case "-":
			res = new UnaryExpr(UnaryOp.NEGATIVE, subExpr);
			break;
		case "not":
			res = new UnaryExpr(UnaryOp.NOT, subExpr);
			break;
		default:
			assert (false);
		}

		return res;
	}

	private static void addToNodeList(Node node) {
		for (Node inList : globalNodes) {
			if (inList.id.equals(node.id)) {
				throw new AgreeException("AGREE AST generator tried adding multiple nodes of name '" + node.id + "'");
			}
		}
		globalNodes.add(node);
	}

	private static boolean nodeNameExists(String name) {
		for (Node inList : globalNodes) {
			if (inList.id.equals(name)) {
				return true;
			}
		}
		return false;
	}

}
