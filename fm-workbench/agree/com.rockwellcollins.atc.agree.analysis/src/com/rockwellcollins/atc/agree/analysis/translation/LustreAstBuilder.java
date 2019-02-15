package com.rockwellcollins.atc.agree.analysis.translation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.EventDataPortImpl;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.InputStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeAADLConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeEquation;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNodeBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeOverriddenConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeInlineLatchedConnections;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.LustreCondactNodeVisitor;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePatternTranslator;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreeRealtimeCalendarBuilder;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;

public class LustreAstBuilder {

	protected static List<Node> nodes;
	protected static final String guarSuffix = "__GUARANTEE";
	public static final String assumeSuffix = "__ASSUME";
	protected static final String lemmaSuffix = "__LEMMA";
	protected static final String assertSuffix = "__ASSERT";
	protected static final String historyNodeName = "__HIST";
	public static final String assumeHistSufix = assumeSuffix + historyNodeName;
	protected static final String patternPropSuffix = "__PATTERN";

	// private static AgreeProgram translate(AgreeProgram program){
	// return AgreeInlineLatchedConnections.translate(program);
	// }

	public static Program getRealizabilityLustreProgram(AgreeProgram agreeProgram) {

		List<TypeDef> types = AgreeUtils.getLustreTypes(agreeProgram);

		List<Expr> assertions = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<VarDecl> inputs = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		List<String> properties = new ArrayList<>();

		AgreeNode topNode = agreeProgram.topNode;

		for (AgreeStatement assumption : topNode.assumptions) {
			assertions.add(assumption.expr);
		}

		int i = 0;

		for (AgreeStatement guarantee : topNode.guarantees) {
			String guarName = guarSuffix + i++;
			locals.add(new AgreeVar(guarName, NamedType.BOOL, guarantee.reference, topNode.compInst, null));
			equations.add(new Equation(new IdExpr(guarName), guarantee.expr));
			properties.add(guarName);
		}

		List<String> inputStrs = new ArrayList<>();
		for (AgreeVar var : topNode.inputs) {
			inputs.add(var);
			inputStrs.add(var.id);
		}

		for (AgreeVar var : topNode.outputs) {
			inputs.add(var);
		}

		// perhaps we should break out eq statements into implementation
		// equations
		// and type equations. This would clear this up
		for (AgreeStatement statement : topNode.assertions) {
			if (AgreeUtils.referenceIsInContract(statement, topNode.compInst)) {

				// this is a strange hack we have to do. we have to make
				// equation and property
				// statements not assertions. They should all be binary
				// expressions with an
				// equals operator. We will need to removing their corresponding
				// variable
				// from the inputs and add them to the local variables
				BinaryExpr binExpr;
				IdExpr varId;
				try {
					binExpr = (BinaryExpr) statement.expr;
					varId = (IdExpr) binExpr.left;
				} catch (ClassCastException e) {
					// some equation variables are assertions for
					// subrange types. do not translate these to
					// local equations. Just add them to assertions
					assertions.add(statement.expr);
					continue;
				}

				boolean found = false;
				int index;
				for (index = 0; index < inputs.size(); index++) {
					VarDecl var = inputs.get(index);
					if (var.id.equals(varId.id)) {
						found = true;
						break;
					}

				}
				if (!found || binExpr.op != BinaryOp.EQUAL) {
					throw new AgreeException(
							"Something went very wrong with the lustre generation in the realizability analysis");
				}
				locals.add(inputs.remove(index));
				equations.add(new Equation(varId, binExpr.right));
			}
		}

		NodeBuilder builder = new NodeBuilder("main");
		builder.addInputs(inputs);
		builder.addLocals(locals);
		builder.addEquations(equations);
		builder.addProperties(properties);
		builder.addAssertions(assertions);
		builder.setRealizabilityInputs(inputStrs);

		Node main = builder.build();
		List<Node> nodes = new ArrayList<>();
		nodes.add(main);
		nodes.addAll(agreeProgram.globalLustreNodes);

		Program program = new ProgramBuilder().addTypes(types).addNodes(nodes).setMain(main.id).build();

		return program;

	}

	public static Program getAssumeGuaranteeLustreProgram(AgreeProgram agreeProgram) {

		nodes = new ArrayList<>();

		AgreeNode flatNode = flattenAgreeNode(agreeProgram, agreeProgram.topNode, "_TOP__");
		List<Expr> assertions = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<VarDecl> inputs = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		List<String> properties = new ArrayList<>();
		List<String> ivcs = new ArrayList<>();

		int j = 0;
		for (AgreeStatement assumption : flatNode.assumptions) {
			String assumName = assumeSuffix + j++;
			locals.add(new AgreeVar(assumName, NamedType.BOOL, assumption.reference, flatNode.compInst, null));
			IdExpr assumId = new IdExpr(assumName);
			equations.add(new Equation(assumId, assumption.expr));
			assertions.add(assumId);
			ivcs.add(assumId.id);
		}

		for (AgreeStatement assertion : flatNode.assertions) {
			assertions.add(assertion.expr);
		}

		// add assumption and monolithic lemmas first (helps with proving)
		for (AgreeVar var : flatNode.outputs) {
			if (var.reference instanceof AssumeStatement || var.reference instanceof LemmaStatement) {
				properties.add(var.id);
			}
			inputs.add(var);
		}

		// add property that all assumption history is true
		Expr assumeConj = new BoolExpr(true);
		for (AgreeNode subNode : agreeProgram.topNode.subNodes) {
			assumeConj = new BinaryExpr(new IdExpr(subNode.id + "__" + assumeHistSufix), BinaryOp.AND, assumeConj);
		}
		AgreeVar assumeHistVar = new AgreeVar(assumeHistSufix, NamedType.BOOL,
				agreeProgram.topNode.compInst.getComponentClassifier(), agreeProgram.topNode.compInst, null);
		locals.add(assumeHistVar);
		equations.add(new Equation(new IdExpr(assumeHistVar.id), assumeConj));
		properties.add(assumeHistVar.id);

		int k = 0;
		for (AgreeStatement patternPropState : flatNode.patternProps) {
			String patternVarName = patternPropSuffix + k++;
			locals.add(new AgreeVar(patternVarName, NamedType.BOOL, patternPropState, flatNode.compInst, null));
			equations.add(new Equation(new IdExpr(patternVarName), patternPropState.expr));
			properties.add(patternVarName);
		}

		int i = 0;
		for (AgreeStatement guarantee : flatNode.lemmas) {
			String guarName = guarSuffix + i++;
			locals.add(new AgreeVar(guarName, NamedType.BOOL, guarantee.reference, flatNode.compInst, null));
			equations.add(new Equation(new IdExpr(guarName), guarantee.expr));
			properties.add(guarName);
		}

		for (AgreeStatement guarantee : flatNode.guarantees) {
			String guarName = guarSuffix + i++;
			locals.add(new AgreeVar(guarName, NamedType.BOOL, guarantee.reference, flatNode.compInst, null));
			equations.add(new Equation(new IdExpr(guarName), guarantee.expr));
			properties.add(guarName);
		}

		for (AgreeVar var : flatNode.inputs) {
			inputs.add(var);
		}

		for (AgreeVar var : flatNode.locals) {
			locals.add(var);
		}

		equations.addAll(flatNode.localEquations);
		assertions.add(AgreeRealtimeCalendarBuilder.getTimeConstraint(flatNode.eventTimes));

		NodeBuilder builder = new NodeBuilder("main");
		builder.addInputs(inputs);
		builder.addLocals(locals);
		builder.addEquations(equations);
		builder.addProperties(properties);
		builder.addAssertions(assertions);
		builder.addIvcs(ivcs);

		Node main = builder.build();

		nodes.add(main);
		nodes.addAll(agreeProgram.globalLustreNodes);
		nodes.add(getHistNode());

		// add realtime constraint nodes
		nodes.addAll(AgreeRealtimeCalendarBuilder.getRealTimeNodes());
		List<TypeDef> types = AgreeUtils.getLustreTypes(agreeProgram);

		Program program = new ProgramBuilder().addTypes(types).addNodes(nodes).setMain(main.id).build();

		return program;

	}

	public static List<Pair<String, Program>> getConsistencyChecks(AgreeProgram agreeProgram) {

		List<Pair<String, Program>> programs = new ArrayList<>();
		List<TypeDef> types = AgreeUtils.getLustreTypes(agreeProgram);

		nodes = new ArrayList<>();

		Node topConsist = getConsistencyLustreNode(agreeProgram.topNode, false);
		// we don't want node lemmas to show up in the consistency check
		for (Node node : agreeProgram.globalLustreNodes) {
			nodes.add(removeProperties(node));
		}
		nodes.add(topConsist);
		nodes.add(getHistNode());
		nodes.addAll(AgreeRealtimeCalendarBuilder.getRealTimeNodes());

		Program topConsistProg = new ProgramBuilder().addTypes(types).addNodes(nodes).setMain(topConsist.id).build();
//		String topComponentName = agreeProgram.topNode.id.replace("_Instance", "");
//		programs.add(Tuples.create(topComponentName + " consistent", topConsistProg));
		programs.add(Tuples.create("This component consistent", topConsistProg));

		for (AgreeNode subNode : agreeProgram.topNode.subNodes) {
			nodes = new ArrayList<>();
			subNode = flattenAgreeNode(agreeProgram, subNode, "_TOP__");
			Node subConsistNode = getConsistencyLustreNode(subNode, true);
			for (Node node : agreeProgram.globalLustreNodes) {
				nodes.add(removeProperties(node));
			}
			nodes.add(subConsistNode);
			nodes.add(getHistNode());
			nodes.addAll(AgreeRealtimeCalendarBuilder.getRealTimeNodes());

			Program subConsistProg = new ProgramBuilder().addTypes(types).addNodes(nodes).setMain(subConsistNode.id)
					.build();

			programs.add(Tuples.create(subNode.id + " consistent", subConsistProg));
		}

		nodes = new ArrayList<>();
		// agreeProgram = translate(agreeProgram);
		AgreeNode compositionNode = flattenAgreeNode(agreeProgram, agreeProgram.topNode, "_TOP__");

		Node topCompositionConsist = getConsistencyLustreNode(compositionNode, true);
		for (Node node : agreeProgram.globalLustreNodes) {
			nodes.add(removeProperties(node));
		}
		// nodes.addAll(agreeProgram.globalLustreNodes);
		nodes.add(topCompositionConsist);
		nodes.add(getHistNode());
		nodes.addAll(AgreeRealtimeCalendarBuilder.getRealTimeNodes());

		Program topCompositConsistProg = new ProgramBuilder().addTypes(types).addNodes(nodes)
				.setMain(topCompositionConsist.id).build();

		programs.add(Tuples.create("Component composition consistent", topCompositConsistProg));

		return programs;
	}

	protected static Node removeProperties(Node node) {
		NodeBuilder builder = new NodeBuilder(node.id);
		builder.addInputs(node.inputs);
		builder.addOutputs(node.outputs);
		builder.addLocals(node.locals);
		builder.addEquations(node.equations);

		return builder.build();
	}

	protected static Node getConsistencyLustreNode(AgreeNode agreeNode, boolean withAssertions) {
		final String stuffPrefix = "__STUFF";

		List<Expr> assertions = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<VarDecl> inputs = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		List<String> properties = new ArrayList<>();
		List<String> ivcs = new ArrayList<>();

		Expr stuffConj = new BoolExpr(true);

		int stuffAssumptionIndex = 0;
		for (AgreeStatement assumption : agreeNode.assumptions) {
			AgreeVar stuffAssumptionVar = new AgreeVar(stuffPrefix + assumeSuffix + stuffAssumptionIndex++,
					NamedType.BOOL, assumption.reference, agreeNode.compInst, null);
			locals.add(stuffAssumptionVar);
			ivcs.add(stuffAssumptionVar.id);
			IdExpr stuffAssumptionId = new IdExpr(stuffAssumptionVar.id);
			equations.add(new Equation(stuffAssumptionId, assumption.expr));

			stuffConj = LustreExprFactory.makeANDExpr(stuffConj, stuffAssumptionId);
		}

		int stuffGuaranteeIndex = 0;
		for (AgreeStatement guarantee : agreeNode.guarantees) {
			AgreeVar stuffGuaranteeVar = new AgreeVar(stuffPrefix + guarSuffix + stuffGuaranteeIndex++,
					NamedType.BOOL, guarantee.reference, agreeNode.compInst, null);
			locals.add(stuffGuaranteeVar);
			ivcs.add(stuffGuaranteeVar.id);
			IdExpr stuffGuaranteeId = new IdExpr(stuffGuaranteeVar.id);
			equations.add(new Equation(stuffGuaranteeId, guarantee.expr));

			stuffConj = LustreExprFactory.makeANDExpr(stuffConj, stuffGuaranteeId);
		}

		if (withAssertions) {
			equations.addAll(agreeNode.localEquations);
		} else {
			for (AgreeEquation eq : agreeNode.localEquations) {
				if (AgreeUtils.referenceIsInContract(eq.reference, agreeNode.compInst)) {
					equations.add(eq);
				}
			}
		}
		// TODO should we include lemmas in the consistency check?
		// for(AgreeStatement guarantee : agreeNode.lemmas){
		// histConj = new BinaryExpr(histConj, BinaryOp.AND, guarantee.expr);
		// }

		int stuffAssertionIndex = 0;
		if (withAssertions) {
			for (AgreeStatement assertion : agreeNode.assertions) {
				AgreeVar stuffAssertionVar = new AgreeVar(stuffPrefix + assertSuffix + stuffAssertionIndex++,
						NamedType.BOOL, assertion.reference, null, null);
				locals.add(stuffAssertionVar);
				IdExpr stuffAssertionId = new IdExpr(stuffAssertionVar.id);
				equations.add(new Equation(stuffAssertionId, assertion.expr));

				stuffConj = LustreExprFactory.makeANDExpr(stuffConj, stuffAssertionId);
			}
		} else {
			// perhaps we should break out eq statements into implementation
			// equations  and type equations. That would clear this up.
			for (AgreeStatement assertion : agreeNode.assertions) {
				if (AgreeUtils.referenceIsInContract(assertion.reference, agreeNode.compInst)) {
					AgreeVar stuffAssertionVar = new AgreeVar(stuffPrefix + assertSuffix + stuffAssertionIndex++,
							NamedType.BOOL, assertion.reference, null, null);
					locals.add(stuffAssertionVar);
					IdExpr stuffAssertionId = new IdExpr(stuffAssertionVar.id);
					equations.add(new Equation(stuffAssertionId, assertion.expr));

					stuffConj = LustreExprFactory.makeANDExpr(stuffConj, stuffAssertionId);
				}
			}
		}

		// add realtime constraints
		Set<AgreeVar> eventTimes = new HashSet<>();
		if (withAssertions) {
			eventTimes.addAll(agreeNode.eventTimes);
		} else {
			for (AgreeVar eventVar : agreeNode.eventTimes) {
				if (AgreeUtils.referenceIsInContract(eventVar.reference, agreeNode.compInst)) {
					eventTimes.add(eventVar);
				}
			}
		}
		assertions.add(AgreeRealtimeCalendarBuilder.getTimeConstraint(eventTimes));

		for (AgreeVar var : agreeNode.inputs) {
			inputs.add(var);
		}

		for (AgreeVar var : agreeNode.outputs) {
			inputs.add(var);
		}

		for (AgreeVar var : agreeNode.locals) {
			if (withAssertions) {
				locals.add(var);
			} else {
				if (AgreeUtils.referenceIsInContract(var.reference, agreeNode.compInst)) {
					locals.add(var);
				}
			}
		}

		EObject classifier = agreeNode.compInst.getComponentClassifier();

		AgreeVar countVar = new AgreeVar("__COUNT", NamedType.INT, null, null, null);
		AgreeVar stuffVar = new AgreeVar(stuffPrefix, NamedType.BOOL, null, null, null);
		AgreeVar histVar = new AgreeVar("__HIST", NamedType.BOOL, null, null, null);
		AgreeVar propVar = new AgreeVar("__PROP", NamedType.BOOL, classifier, agreeNode.compInst, null);

		locals.add(countVar);
		locals.add(stuffVar);
		locals.add(histVar);
		locals.add(propVar);

		IdExpr countId = new IdExpr(countVar.id);
		IdExpr stuffId = new IdExpr(stuffVar.id);
		IdExpr histId = new IdExpr(histVar.id);
		IdExpr propId = new IdExpr(propVar.id);

		equations.add(new Equation(stuffId, stuffConj));

		Expr histExpr = new UnaryExpr(UnaryOp.PRE, histId);
		histExpr = LustreExprFactory.makeANDExpr(histExpr, stuffId);
		histExpr = new BinaryExpr(stuffId, BinaryOp.ARROW, histExpr);
		equations.add(new Equation(histId, histExpr));

		Expr countExpr = new UnaryExpr(UnaryOp.PRE, countId);
		countExpr = new BinaryExpr(countExpr, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
		countExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, countExpr);
		equations.add(new Equation(countId, countExpr));

		IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
		int consistDetph = prefs.getInt(PreferenceConstants.PREF_CONSIST_DEPTH);

		Expr propExpr = new BinaryExpr(countId, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(consistDetph)));
		propExpr = new BinaryExpr(propExpr, BinaryOp.AND, histId);
		equations.add(new Equation(propId, new UnaryExpr(UnaryOp.NOT, propExpr)));
		properties.add(propId.id);

		NodeBuilder builder = new NodeBuilder("consistency");
		builder.addInputs(inputs);
		builder.addLocals(locals);
		builder.addEquations(equations);
		builder.addProperties(properties);
		builder.addAssertions(assertions);
		builder.addIvcs(ivcs);

		Node node = builder.build();

		return node;

	}

	public static String getRelativeLocation(String rootLocation, String location) {
		if (!location.contains(".") || location.equalsIgnoreCase(rootLocation)) {
			return "";
		}
		if (location.toUpperCase().startsWith(rootLocation.toUpperCase())) {
			return location.substring(rootLocation.length() + 1);
		}
		return "";
	}

	protected static Equation getHist(IdExpr histId, Expr expr) {
		Expr preHist = new UnaryExpr(UnaryOp.PRE, histId);
		Expr preAndNow = LustreExprFactory.makeANDExpr(preHist, expr);
		return new Equation(histId, new BinaryExpr(expr, BinaryOp.ARROW, preAndNow));
	}

	protected static Node getLustreNode(AgreeNode agreeNode, String nodePrefix) {

		List<VarDecl> inputs = new ArrayList<>();
		List<VarDecl> locals = new ArrayList<>();
		List<Equation> equations = new ArrayList<>();
		List<Expr> assertions = new ArrayList<>();
		List<String> ivcs = new ArrayList<>();

		// add assumption history variable
		IdExpr assumHist = new IdExpr(assumeHistSufix);
		inputs.add(new AgreeVar(assumHist.id, NamedType.BOOL, null, agreeNode.compInst, null));

		int i = 0;
		for (AgreeStatement statement : agreeNode.assumptions) {
			String inputName = assumeSuffix + i++;
			inputs.add(new AgreeVar(inputName, NamedType.BOOL, statement.reference, agreeNode.compInst, null));
			IdExpr assumeId = new IdExpr(inputName);
			assertions.add(new BinaryExpr(assumeId, BinaryOp.EQUAL, statement.expr));
		}

		int j = 0;
		for (AgreeStatement statement : agreeNode.lemmas) {
			String inputName = lemmaSuffix + j++;
			inputs.add(new AgreeVar(inputName, NamedType.BOOL, statement.reference, agreeNode.compInst, null));
			IdExpr lemmaId = new IdExpr(inputName);
			assertions.add(new BinaryExpr(lemmaId, BinaryOp.EQUAL, statement.expr));
		}

		int k = 0;
		Expr guarConjExpr = new BoolExpr(true);
		for (AgreeStatement statement : agreeNode.guarantees) {
			String inputName = guarSuffix + k++;
			locals.add(new AgreeVar(inputName, NamedType.BOOL, statement.reference, agreeNode.compInst, null));
			IdExpr guarId = new IdExpr(inputName);
			equations.add(new Equation(guarId, statement.expr));
			ivcs.add(guarId.id);
			guarConjExpr = LustreExprFactory.makeANDExpr(guarId, guarConjExpr);
		}
		for (AgreeStatement statement : agreeNode.lemmas) {
			guarConjExpr = LustreExprFactory.makeANDExpr(statement.expr, guarConjExpr);
		}

		// assert that if the assumptions have held historically, then the
		// gurantees hold
		assertions.add(new BinaryExpr(assumHist, BinaryOp.IMPLIES, guarConjExpr));

		for (AgreeStatement statement : agreeNode.assertions) {
			assertions.add(statement.expr);
		}

		// create properties for the patterns
		int l = 0;
		for (AgreeStatement patternPropState : agreeNode.patternProps) {
			String patternVarName = patternPropSuffix + l++;
			inputs.add(new AgreeVar(patternVarName, NamedType.BOOL, patternPropState, agreeNode.compInst, null));
			assertions.add(new BinaryExpr(new IdExpr(patternVarName), BinaryOp.EQUAL, patternPropState.expr));
		}

		Expr assertExpr = new BoolExpr(true);
		for (Expr expr : assertions) {
			assertExpr = LustreExprFactory.makeANDExpr(expr, assertExpr);
		}

		String outputName = "__ASSERT";
		List<VarDecl> outputs = new ArrayList<>();
		outputs.add(new VarDecl(outputName, NamedType.BOOL));
		equations.add(new Equation(new IdExpr(outputName), assertExpr));

		// gather the remaining inputs
		for (AgreeVar var : agreeNode.inputs) {
			inputs.add(var);
		}
		for (AgreeVar var : agreeNode.outputs) {
			inputs.add(var);
		}
		for (AgreeVar var : agreeNode.locals) {
			locals.add(var);
		}

		for (AgreeEquation equation : agreeNode.localEquations) {
			equations.add(equation);
		}

		NodeBuilder builder = new NodeBuilder(nodePrefix + agreeNode.id);
		builder.addInputs(inputs);
		builder.addOutputs(outputs);
		builder.addLocals(locals);
		builder.addEquations(equations);
		builder.addIvcs(ivcs);

		return builder.build();
	}

	protected static AgreeNode flattenAgreeNode(AgreeProgram agreeProgram, AgreeNode agreeNode, String nodePrefix) {

		List<AgreeVar> inputs = new ArrayList<>();
		List<AgreeVar> outputs = new ArrayList<>();
		List<AgreeVar> locals = new ArrayList<>();
		List<AgreeStatement> patternProps = new ArrayList<>();
		List<AgreeEquation> equations = new ArrayList<>();
		List<AgreeStatement> assertions = new ArrayList<>();
		Set<AgreeVar> timeEvents = new HashSet<>(agreeNode.eventTimes);

		Expr someoneTicks = null;
		for (AgreeNode subAgreeNode : agreeNode.subNodes) {
			String prefix = subAgreeNode.id + AgreeASTBuilder.dotChar;
			Expr clockExpr = getClockExpr(agreeNode, subAgreeNode);

			if (someoneTicks == null) {
				someoneTicks = clockExpr;
			} else {
				someoneTicks = new BinaryExpr(someoneTicks, BinaryOp.OR, clockExpr);
			}

			AgreeNode flatNode = flattenAgreeNode(agreeProgram, subAgreeNode,
					nodePrefix + subAgreeNode.id + AgreeASTBuilder.dotChar);

			Node lustreNode = addSubNodeLustre(agreeProgram, agreeNode, nodePrefix, flatNode);

			addInputsAndOutputs(agreeNode, inputs, outputs, patternProps, flatNode, lustreNode, prefix);

			addTimeEvents(timeEvents, flatNode, prefix, assertions);

			addNodeCall(agreeNode, assertions, prefix, clockExpr, lustreNode);

			addHistoricalAssumptionConstraint(agreeNode, prefix, clockExpr, assertions, lustreNode);

		}

		if (agreeNode.timing == TimingModel.ASYNC) {
			if (someoneTicks == null) {
				throw new AgreeException("Somehow we generated a clock constraint without any clocks."
						+ " Perhaps none of your subcomponents have an agree annex?");
			}
			assertions.add(new AgreeStatement("someone ticks", someoneTicks, null));
		}

		addConnectionConstraints(agreeNode, assertions);

		// add any clock constraints
		assertions.addAll(agreeNode.assertions);
		assertions.add(new AgreeStatement("", agreeNode.clockConstraint, null));
		inputs.addAll(agreeNode.inputs);
		outputs.addAll(agreeNode.outputs);
		locals.addAll(agreeNode.locals);
		equations.addAll(agreeNode.localEquations);
		patternProps.addAll(agreeNode.patternProps);

		AgreeNodeBuilder builder = new AgreeNodeBuilder(agreeNode.id);

		builder.addInput(inputs);
		builder.addOutput(outputs);
		builder.addLocal(locals);
		builder.addLocalEquation(equations);
		builder.addSubNode(agreeNode.subNodes);
		builder.addAssertion(assertions);
		builder.addAssumption(agreeNode.assumptions);
		builder.addGuarantee(agreeNode.guarantees);
		builder.addLemma(agreeNode.lemmas);
		builder.addPatternProp(patternProps);
		builder.setClockConstraint(new BoolExpr(true));
		builder.setInitialConstraint(agreeNode.initialConstraint);
		builder.setClockVar(agreeNode.clockVar);
		builder.setReference(agreeNode.reference);
		builder.setTiming(null);
		builder.addEventTime(timeEvents);
		builder.setCompInst(agreeNode.compInst);

		return builder.build();
	}

	private static void addHistoricalAssumptionConstraint(AgreeNode agreeNode, String prefix, Expr clockExpr,
			List<AgreeStatement> assertions, Node lustreNode) {
		Expr assumConj = new BoolExpr(true);
		for (VarDecl lustreVar : lustreNode.inputs) {
			AgreeVar var = (AgreeVar) lustreVar;
			if (var.reference instanceof AssumeStatement || var.reference instanceof LemmaStatement) {
				Expr varId = new IdExpr(prefix + var.id);
				assumConj = LustreExprFactory.makeANDExpr(varId, assumConj);
			}
		}
		// assumConj = new BinaryExpr(clockExpr, BinaryOp.IMPLIES, assumConj);
		Expr histCall = new NodeCallExpr(historyNodeName, assumConj);
		Expr assertExpr = new BinaryExpr(new IdExpr(prefix + assumeSuffix + "__HIST"), BinaryOp.EQUAL, histCall);
		assertions.add(new AgreeStatement("", assertExpr, null));

	}

	private static void addTimeEvents(Set<AgreeVar> timeEvents, AgreeNode flatNode, String prefix,
			List<AgreeStatement> assertions) {
		for (AgreeVar event : flatNode.eventTimes) {
			timeEvents.add(new AgreeVar(prefix + event.id, event.type, event.reference, event.compInst, null));
		}
		// set the time variable to be equal
		IdExpr timeId = AgreePatternTranslator.timeExpr;
		assertions.add(
				new AgreeStatement("", new BinaryExpr(timeId, BinaryOp.EQUAL, new IdExpr(prefix + timeId.id)), null));
	}

	protected static void addConnectionConstraints(AgreeNode agreeNode, List<AgreeStatement> assertions) {
		for (AgreeConnection conn : agreeNode.connections) {
			if (conn instanceof AgreeAADLConnection) {
				AgreeAADLConnection aadlConn = (AgreeAADLConnection) conn;
				String destName = aadlConn.destinationNode == null ? ""
						: aadlConn.destinationNode.id + AgreeASTBuilder.dotChar;
				destName = destName + aadlConn.destinationVarName.id;

				String sourName = aadlConn.sourceNode == null ? "" : aadlConn.sourceNode.id + AgreeASTBuilder.dotChar;
				sourName = sourName + aadlConn.sourceVarName.id;

				Expr aadlConnExpr;

				if (!aadlConn.delayed) {
					aadlConnExpr = new BinaryExpr(new IdExpr(sourName), BinaryOp.EQUAL, new IdExpr(destName));
				} else {
					// we need to get the correct type for the aadlConnection
					// we can assume that the source and destination types are
					// the same at this point
					Expr initExpr = AgreeUtils.getInitValueFromType(aadlConn.sourceVarName.type);
					Expr preSource = new UnaryExpr(UnaryOp.PRE, new IdExpr(sourName));
					Expr sourExpr = new BinaryExpr(initExpr, BinaryOp.ARROW, preSource);
					aadlConnExpr = new BinaryExpr(sourExpr, BinaryOp.EQUAL, new IdExpr(destName));
				}

				assertions.add(new AgreeStatement("", aadlConnExpr, aadlConn.reference));
			} else {
				AgreeOverriddenConnection agreeConn = (AgreeOverriddenConnection) conn;
				assertions.add(agreeConn.statement);
			}
		}
	}

	protected static void addNodeCall(AgreeNode agreeNode, List<AgreeStatement> assertions, String prefix,
			Expr clockExpr, Node lustreNode) {

		List<Expr> inputIds = new ArrayList<>();
		for (VarDecl var : lustreNode.inputs) {
			String suffix = "";
			// this is gross, we need to add the latched variables to the node
			// call
			// if this component implementation is using the latched semantics
			if (agreeNode.timing == TimingModel.LATCHED) {
				EObject ref = ((AgreeVar) var).reference;
				if (ref instanceof DataPortImpl && ((DataPortImpl) ref).isIn()
						|| (ref instanceof EventDataPortImpl && ((EventDataPortImpl) ref).isIn()
								|| (ref instanceof Arg && ref.eContainer() instanceof InputStatement))) {
					suffix = AgreeInlineLatchedConnections.LATCHED_SUFFIX;
				}
			}
			inputIds.add(new IdExpr(prefix + var.id + suffix));
		}
		if (agreeNode.timing == TimingModel.ASYNC || agreeNode.timing == TimingModel.LATCHED) {
			// the clock expr should be the last input to the node
			inputIds.set(inputIds.size() - 1, clockExpr);
		}

		Expr condactExpr = new NodeCallExpr(lustreNode.id, inputIds);

		AgreeStatement condactCall = new AgreeStatement("", condactExpr, null);
		assertions.add(condactCall);
	}

	protected static void addInputsAndOutputs(AgreeNode parentNode, List<AgreeVar> inputs, List<AgreeVar> outputs,
			List<AgreeStatement> patternProps, AgreeNode subAgreeNode, Node lustreNode, String prefix) {
		int varCount = 0;
		for (AgreeVar var : subAgreeNode.inputs) {
			varCount++;
			AgreeVar input = new AgreeVar(prefix + var.id, var.type, var.reference, var.compInst, var.featInst);
			inputs.add(input);
		}

		for (AgreeVar var : subAgreeNode.outputs) {
			varCount++;
			AgreeVar output = new AgreeVar(prefix + var.id, var.type, var.reference, var.compInst, var.featInst);
			outputs.add(output);
		}

//		for (AgreeVar var : subAgreeNode.locals) {
//			varCount++;
//			AgreeVar local = new AgreeVar(prefix + var.id, var.type, var.reference, var.compInst, var.featInst);
//			outputs.add(local);
//		}

		int i = 0;
		for (AgreeStatement statement : subAgreeNode.assumptions) {
			varCount++;
			AgreeVar output = new AgreeVar(prefix + assumeSuffix + i++, NamedType.BOOL, statement.reference,
					subAgreeNode.compInst, null);
			outputs.add(output);
		}

		int k = 0;
		for (AgreeStatement statement : subAgreeNode.patternProps) {
			varCount++;
			AgreeVar output = new AgreeVar(prefix + patternPropSuffix + k++, NamedType.BOOL, statement,
					subAgreeNode.compInst, null);
			patternProps.add(new AgreeStatement(prefix.replace("__", "") + ": " + statement.string,
					new IdExpr(output.id), statement.reference));
			outputs.add(output);
		}

		// add the assumption history var
		varCount++;
		outputs.add(new AgreeVar(prefix + assumeSuffix + "__HIST", NamedType.BOOL, null, subAgreeNode.compInst, null));

		int j = 0;
		for (AgreeStatement statement : subAgreeNode.lemmas) {
			varCount++;
			AgreeVar output = new AgreeVar(prefix + lemmaSuffix + j++, NamedType.BOOL, statement.reference,
					subAgreeNode.compInst, null);
			outputs.add(output);
		}

		if (parentNode.timing == TimingModel.ASYNC || parentNode.timing == TimingModel.LATCHED) {
			inputs.add(subAgreeNode.clockVar);
			varCount++;
		}

		if (lustreNode.inputs.size() != varCount) {
			throw new AgreeException("Something went wrong during node flattening");
		}
	}

	protected static Node addSubNodeLustre(AgreeProgram agreeProgram, AgreeNode agreeNode, String nodePrefix,
			AgreeNode flatNode) {

		Node lustreNode = getLustreNode(flatNode, nodePrefix);
		if (agreeNode.timing == TimingModel.ASYNC || agreeNode.timing == TimingModel.LATCHED) {
			lustreNode = LustreCondactNodeVisitor.translate(agreeProgram, flatNode, lustreNode);
		}
		addToNodes(lustreNode);
		return lustreNode;
	}

	protected static Expr getClockExpr(AgreeNode agreeNode, AgreeNode subNode) {

		IdExpr clockId = new IdExpr(subNode.clockVar.id);
		switch (agreeNode.timing) {
		case SYNC:
			return new BoolExpr(true);
		case ASYNC:
			return clockId;
		case LATCHED:
			Expr preClock = new UnaryExpr(UnaryOp.PRE, clockId);
			Expr notClock = new UnaryExpr(UnaryOp.NOT, clockId);
			Expr andExpr = new BinaryExpr(preClock, BinaryOp.AND, notClock);
			Expr clockExpr = new BinaryExpr(new BoolExpr(false), BinaryOp.ARROW, andExpr);
			return clockExpr;
		default:
			throw new AgreeException("unhandled timing type: '" + agreeNode.timing + "");
		}

	}

	protected static void addToNodes(Node node) {
		for (Node inList : nodes) {
			if (node.id.equals(inList.id)) {
				throw new AgreeException(
						"AGREE Lustre AST Builder attempted to add multiple nodes of name '" + node.id + "'");
			}
		}
		nodes.add(node);
	}

	protected static Node getHistNode() {
		NodeBuilder builder = new NodeBuilder(historyNodeName);
		builder.addInput(new VarDecl("input", NamedType.BOOL));
		builder.addOutput(new VarDecl("hist", NamedType.BOOL));

		IdExpr histId = new IdExpr("hist");
		IdExpr inputId = new IdExpr("input");
		Expr preHist = new UnaryExpr(UnaryOp.PRE, histId);
		Expr histExpr = new BinaryExpr(preHist, BinaryOp.AND, inputId);
		histExpr = new BinaryExpr(inputId, BinaryOp.ARROW, histExpr);

		builder.addEquation(new Equation(histId, histExpr));
		return builder.build();
	}

}
