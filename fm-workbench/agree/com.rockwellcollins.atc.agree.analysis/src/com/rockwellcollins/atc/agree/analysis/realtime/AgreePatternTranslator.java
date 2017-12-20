package com.rockwellcollins.atc.agree.analysis.realtime;

import static jkind.lustre.parsing.LustreParseUtil.equation;
import static jkind.lustre.parsing.LustreParseUtil.expr;
import static jkind.lustre.parsing.LustreParseUtil.to;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentClassifier;

import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeEquation;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNodeBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreeCauseEffectPattern.TriggerType;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePatternInterval.IntervalType;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

public class AgreePatternTranslator {

	public static final String TIME_SUFFIX = "__TIME__";
	public static final String RISE_SUFFIX = "__RISE__";
	public static final String FALL_SUFFIX = "__FALL__";
	private static final String TIME_WILL_PREFIX = "__TIME_WILL__";
	private static final String EFFECT_TIME_RANGE_PREFIX = "__EFFECT_TIME_RANGE__";
	private static final String TIMEOUT_PREFIX = "__TIMEOUT__";
	private static final String CAUSE_CONDITION_HELD_PREFIX = "__CAUSE_CONDITION_HELD__";
	private static final String CAUSE_CONDITION_TIMEOUT_PREFIX = "__CAUSE_CONDITION_TIMEOUT__";
	private static final String JITTER_PREFIX = "__JITTER__";
	private static final String PERIOD_PREFIX = "__PERIOD__";
	private static final String TIMER_PREFIX = "__TIMER__";
	private static final String RUNNING_PREFIX = "__RUNNING__";
	private static final String RECORD_PREFIX = "__RECORD__";
	private static final String WINDOW_PREFIX = "__WINDOW__";
	private static final String NEW_CAUSE_PREFIX = "__NEW_CAUSE__";
	private static final String END_INTERVAL = "__END_INTERVAL__";

	private static final Expr NEG_ONE = new RealExpr(BigDecimal.valueOf(-1));

	private int patternIndex = 0;
	public static final IdExpr timeExpr = new IdExpr("time");
	private static boolean containsRealTimePatterns = false;

	public static AgreeProgram translate(AgreeProgram program) {
		List<Node> patternLustreNodes = new ArrayList<>();
		// reset the static variable before refreshing its values in translateNode call
		containsRealTimePatterns = false;
		AgreeNode topNode = new AgreePatternTranslator().translateNode(program.topNode, true);
		List<AgreeNode> agreeNodes = gatherNodes(topNode);
		patternLustreNodes.addAll(program.globalLustreNodes);

		return new AgreeProgram(agreeNodes, patternLustreNodes, program.globalTypes, topNode, containsRealTimePatterns);
	}

	private AgreeNode translateNode(AgreeNode node, boolean isTopNode) {

		AgreeNodeBuilder builder = new AgreeNodeBuilder(node);
		// this has to be done first because the pattern translation
		// for guarantees/lemmas/assumptions add additional assertions
		builder.clearAssertions();

		createTimeFunctions(node, builder);

		for (AgreeStatement statement : node.assertions) {
			if (statement instanceof AgreePattern) {
				containsRealTimePatterns = true;
				Expr transExpr = translatePattern((AgreePattern) statement, builder, false);
				statement = new AgreeStatement(statement.string, transExpr, statement.reference);
			}
			builder.addAssertion(statement);
		}

		builder.clearGuarantees();
		for (AgreeStatement statement : node.guarantees) {
			if (statement instanceof AgreePattern) {
				containsRealTimePatterns = true;
				Expr transExpr = translatePattern((AgreePattern) statement, builder, isTopNode);
				statement = new AgreeStatement(statement.string, transExpr, statement.reference);
			}
			builder.addGuarantee(statement);
		}

		builder.clearLemmas();
		for (AgreeStatement statement : node.lemmas) {
			if (statement instanceof AgreePattern) {
				containsRealTimePatterns = true;
				Expr transExpr = translatePattern((AgreePattern) statement, builder, isTopNode);
				statement = new AgreeStatement(statement.string, transExpr, statement.reference);
			}
			builder.addLemma(statement);
		}

		builder.clearAssumptions();
		for (AgreeStatement statement : node.assumptions) {
			if (statement instanceof AgreePattern) {
				containsRealTimePatterns = true;
				Expr transExpr = translatePattern((AgreePattern) statement, builder, !isTopNode);
				statement = new AgreeStatement(statement.string, transExpr, statement.reference);
			}
			builder.addAssumption(statement);
		}

		builder.clearSubNodes();
		for (AgreeNode subNode : node.subNodes) {
			builder.addSubNode(new AgreePatternTranslator().translateNode(subNode, false));
		}

		builder.addInput(new AgreeVar(timeExpr.id, NamedType.REAL, null, node.compInst, null));
		return builder.build();
	}

	// this code adds all of the logic for the timeOf, timeRise, and timeFall
	// expressions
	private void createTimeFunctions(AgreeNode oldNode, AgreeNodeBuilder newBuilder) {
		newBuilder.clearTimeOfs();
		newBuilder.clearTimeRises();
		newBuilder.clearTimeFalls();

		// create all of the timeOf, timeRise, and timeFall references
		for (Entry<String, AgreeVar> entry : oldNode.timeOfMap.entrySet()) {
			getTimeOf(entry.getKey(), newBuilder, entry.getValue().reference);
		}

		for (Entry<String, AgreeVar> entry : oldNode.timeRiseMap.entrySet()) {
			getTimeRise(entry.getKey(), newBuilder, entry.getValue().reference);
		}

		for (Entry<String, AgreeVar> entry : oldNode.timeFallMap.entrySet()) {
			getTimeFall(entry.getKey(), newBuilder, entry.getValue().reference);
		}
	}

	private static List<AgreeNode> gatherNodes(AgreeNode node) {
		List<AgreeNode> nodes = new ArrayList<>();

		for (AgreeNode subNode : node.subNodes) {
			nodes.addAll(gatherNodes(subNode));
			nodes.add(subNode);
		}
		return nodes;
	}

	private Expr translatePattern(AgreePattern pattern, AgreeNodeBuilder builder, boolean isProperty) {
		patternIndex++;

		if (pattern instanceof AgreeCauseEffectPattern) {
			return translatePattern((AgreeCauseEffectPattern) pattern, builder, isProperty);
		} else if (pattern instanceof AgreeRealtimePattern) {
			return translatePattern((AgreeRealtimePattern) pattern, builder, isProperty);
		}
		throw new AgreeException("Unhandled Pattern: " + pattern.getClass().toString());
	}

	private Expr translatePattern(AgreeRealtimePattern pattern, AgreeNodeBuilder builder, boolean isProperty) {
		if (pattern instanceof AgreePeriodicPattern) {
			return translatePattern((AgreePeriodicPattern) pattern, builder, isProperty);
		} else if (pattern instanceof AgreeSporadicPattern) {
			return translatePattern((AgreeSporadicPattern) pattern, builder, isProperty);
		}
		throw new AgreeException("Unhandled Pattern: " + pattern.getClass().toString());
	}

	private Expr translatePattern(AgreeSporadicPattern pattern, AgreeNodeBuilder builder, boolean isProperty) {

		EObject varReference = pattern.reference;
		while (!(varReference instanceof ComponentClassifier)) {
			varReference = varReference.eContainer();
		}

		if (isProperty) {
			return translatePatternProperty(pattern, builder, varReference);
		} else {
			return translatePatternConstraint(pattern, builder, varReference);
		}
	}

	private Expr translatePatternProperty(AgreeSporadicPattern pattern, AgreeNodeBuilder builder,
			EObject varReference) {
		if (!((RealExpr) pattern.jitter).value.equals(BigDecimal.ZERO)) {
			throw new AgreeException(
					"We currently do not handle non-zero jitter values correctly for sporadic patterns");
		}

		AgreeVar timeofEvent = getTimeOf(pattern.event.id, builder, null);

		Expr propExpr = expr("(true -> (not ((pre laste) = -1.0) => event => time - (pre laste) >= period))",
				to("laste", timeofEvent), to("event", pattern.event), to("time", timeExpr),
				to("period", pattern.period));

		return propExpr;
	}

	private Expr translatePatternConstraint(AgreeSporadicPattern pattern, AgreeNodeBuilder builder,
			EObject varReference) {
		AgreeVar jitterVar = new AgreeVar(JITTER_PREFIX + patternIndex, NamedType.REAL, varReference);
		AgreeVar periodVar = new AgreeVar(PERIOD_PREFIX + patternIndex, NamedType.REAL, varReference);
		AgreeVar timeoutVar = new AgreeVar(TIMEOUT_PREFIX + patternIndex, NamedType.REAL, varReference);

		builder.addOutput(jitterVar);
		builder.addOutput(periodVar);
		builder.addOutput(timeoutVar);

		IdExpr jitterId = new IdExpr(jitterVar.id);
		IdExpr periodId = new IdExpr(periodVar.id);
		IdExpr timeoutId = new IdExpr(timeoutVar.id);

		builder.addEventTime(timeoutVar);

		// -j <= jitter <= j
		Expr jitterLow = new BinaryExpr(new UnaryExpr(UnaryOp.NEGATIVE, pattern.jitter), BinaryOp.LESSEQUAL, jitterId);
		Expr jitterHigh = new BinaryExpr(jitterId, BinaryOp.LESSEQUAL, pattern.jitter);
		builder.addAssertion(
				new AgreeStatement(null, new BinaryExpr(jitterLow, BinaryOp.AND, jitterHigh), pattern.reference));

		// pnext >= 0 -> if pre ((pnext + jitter) = t) then pnext >= p +
		// pre(pnext) else pre(pnext)

		Expr prePNext = new UnaryExpr(UnaryOp.PRE, periodId);
		Expr pNextInit = new BinaryExpr(periodId, BinaryOp.GREATEREQUAL, new RealExpr(BigDecimal.ZERO));
		Expr pNextCond = new BinaryExpr(periodId, BinaryOp.PLUS, jitterId);
		pNextCond = new BinaryExpr(pNextCond, BinaryOp.EQUAL, timeExpr);
		pNextCond = new UnaryExpr(UnaryOp.PRE, pNextCond);
		Expr pNextThen = new BinaryExpr(pattern.period, BinaryOp.PLUS, prePNext);
		pNextThen = new BinaryExpr(periodId, BinaryOp.GREATEREQUAL, pNextThen);
		Expr pNextHold = new BinaryExpr(periodId, BinaryOp.EQUAL, prePNext);
		Expr pNextIf = new IfThenElseExpr(pNextCond, pNextThen, pNextHold);
		Expr pNext = new BinaryExpr(pNextInit, BinaryOp.ARROW, pNextIf);

		builder.addAssertion(new AgreeStatement(null, pNext, pattern.reference));

		// timeout = pnext + jitter
		Expr timeoutExpr = new BinaryExpr(periodId, BinaryOp.PLUS, jitterId);
		timeoutExpr = new BinaryExpr(timeoutId, BinaryOp.EQUAL, timeoutExpr);
		builder.addAssertion(new AgreeStatement(null, timeoutExpr, pattern.reference));

		// event = (t = timeout)
		Expr eventExpr = new BinaryExpr(timeExpr, BinaryOp.EQUAL, timeoutId);
		eventExpr = new BinaryExpr(pattern.event, BinaryOp.EQUAL, eventExpr);

		return eventExpr;
	}

	private Expr translatePattern(AgreePeriodicPattern pattern, AgreeNodeBuilder builder, boolean isProperty) {

		EObject varReference = pattern.reference;
		while (!(varReference instanceof ComponentClassifier)) {
			varReference = varReference.eContainer();
		}

		if (isProperty) {
			return translatePatternProperty(pattern, builder, varReference);
		} else {
			return translatePatternCondition(pattern, builder, varReference);
		}
	}

	private Expr translatePatternProperty(AgreePeriodicPattern pattern, AgreeNodeBuilder builder,
			EObject varReference) {

		AgreeVar periodVar = new AgreeVar(PERIOD_PREFIX + patternIndex, NamedType.REAL, null);
		builder.addLocal(periodVar);

		AgreeEquation eq = new AgreeEquation(equation(
				"period = if event then (if time <= P then time  else (0.0 -> pre period)) + P else (P -> pre period);",
				to("event", pattern.event), to("period", periodVar), to("P", pattern.period)), varReference);

		builder.addLocalEquation(eq);
		Expr prop = expr("true -> (time >= P + j => event => (pre period) - j <= time and time <= (pre period) + j)",
				to("time", timeExpr), to("period", periodVar), to("P", pattern.period), to("j", pattern.jitter),
				to("event", pattern.event));

		return prop;
	}

	private Expr translatePatternCondition(AgreePeriodicPattern pattern, AgreeNodeBuilder builder,
			EObject varReference) {
		AgreeVar jitterVar = new AgreeVar(JITTER_PREFIX + patternIndex, NamedType.REAL, varReference);
		AgreeVar periodVar = new AgreeVar(PERIOD_PREFIX + patternIndex, NamedType.REAL, varReference);
		AgreeVar timeoutVar = new AgreeVar(TIMEOUT_PREFIX + patternIndex, NamedType.REAL, varReference);

		builder.addOutput(jitterVar);
		builder.addOutput(periodVar);
		builder.addOutput(timeoutVar);

		IdExpr jitterId = new IdExpr(jitterVar.id);
		IdExpr periodId = new IdExpr(periodVar.id);
		IdExpr timeoutId = new IdExpr(timeoutVar.id);

		builder.addEventTime(timeoutVar);

		// -j <= jitter <= j
		Expr jitterLow = new BinaryExpr(new UnaryExpr(UnaryOp.NEGATIVE, pattern.jitter), BinaryOp.LESSEQUAL, jitterId);
		Expr jitterHigh = new BinaryExpr(jitterId, BinaryOp.LESSEQUAL, pattern.jitter);
		builder.addAssertion(
				new AgreeStatement(null, new BinaryExpr(jitterLow, BinaryOp.AND, jitterHigh), pattern.reference));

		Expr expr = expr(
				"(0.0 <= period) and (period < p) -> " + "(period = (pre period) + (if pre(e) then p else 0.0))",
				to("period", periodVar), to("p", pattern.period), to("e", pattern.event));

		builder.addAssertion(new AgreeStatement(null, expr, pattern.reference));

		// helper assertion (should be true)
		Expr lemma = expr("period - time < p - j and period >= time", to("period", periodVar), to("p", pattern.period),
				to("time", timeExpr), to("j", pattern.jitter));

		builder.addAssertion(new AgreeStatement(null, lemma, pattern.reference));
		AgreeVar timeofEvent = getTimeOf(pattern.event.id, builder, null);

		lemma = expr(
				"(timeOfEvent >= 0.0 and timeOfEvent <> time => timeout - timeOfEvent >= p - j) and "
						+ "(true -> (period <> pre(period) => period - pre(period) <= p + j)) and "
						+ "(timeOfEvent >= 0.0 => timeout - timeOfEvent <= p + j)",
				to("timeOfEvent", timeofEvent), to("time", timeExpr), to("timeout", timeoutId), to("p", pattern.period),
				to("j", pattern.jitter), to("period", periodVar));

		builder.addPatternProp(
				new AgreeStatement("periodic lemma 1 for pattern " + patternIndex, lemma, pattern.reference));

		lemma = expr("true -> timeout <> pre(timeout) => timeout - pre(timeout) >= p - j", to("timeout", timeoutId),
				to("p", pattern.period), to("j", pattern.jitter));

		builder.addPatternProp(
				new AgreeStatement("periodic lemma 2 for pattern " + patternIndex, lemma, pattern.reference));

		// timeout = pnext + jitter
		Expr timeoutExpr = new BinaryExpr(periodId, BinaryOp.PLUS, jitterId);
		timeoutExpr = new BinaryExpr(timeoutId, BinaryOp.EQUAL, timeoutExpr);
		builder.addAssertion(new AgreeStatement(null, timeoutExpr, pattern.reference));

		// event = (t = timeout)
		Expr eventExpr = new BinaryExpr(timeExpr, BinaryOp.EQUAL, timeoutId);
		eventExpr = new BinaryExpr(pattern.event, BinaryOp.EQUAL, eventExpr);

		return eventExpr;
	}

	private Expr translatePattern(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder, boolean isProperty) {

		if (pattern instanceof AgreeTimesPattern) {
			return translatePattern((AgreeTimesPattern) pattern, builder);
		}

		EObject varReference = pattern.reference;
		while (!(varReference instanceof ComponentClassifier)) {
			varReference = varReference.eContainer();
		}

		IdExpr causeId = pattern.cause;
		IdExpr effectId = pattern.effect;

		if (pattern.causeType == TriggerType.CONDITION) {
			causeId = translateCauseCondtionPattern(pattern, causeId, builder);
		}

		if (isProperty) {
			return translatePatternProperty(pattern, builder, causeId, effectId);
		} else {
			return translatePatternConstraint(pattern, builder, causeId, effectId);
		}

	}

	private Expr translatePatternConstraint(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder, IdExpr causeId,
			IdExpr effectId) {
		addPatternConstraintProperty(pattern, builder, causeId, effectId);
		switch (pattern.effectType) {
		case EVENT:
			return translatePatternEffectEventConstraint(pattern, builder, causeId, effectId);
		case CONDITION:
			return translatePatternEffectHoldConstraint(pattern, builder, causeId, effectId);
		default:
			throw new AgreeException("Unknown trigger type for pattern");
		}
	}

	private AgreeVar getTimeOf(String varName, AgreeNodeBuilder builder, EObject reference) {
		Map<String, AgreeVar> timeOfMap = builder.build().timeOfMap;
		if (timeOfMap.containsKey(varName)) {
			return timeOfMap.get(varName);
		}

		AgreeVar timeCause = new AgreeVar(varName + TIME_SUFFIX, NamedType.REAL, reference);
		builder.addOutput(timeCause);

		Expr timeVarExpr = expr("timeCause = (if cause then time else (-1.0 -> pre timeCause))",
				to("timeCause", timeCause), to("cause", varName), to("time", timeExpr));
		builder.addAssertion(new AgreeStatement(null, timeVarExpr, reference));

		Expr lemmaExpr = expr("timeCause <= time and timeCause >= -1.0", to("timeCause", timeCause),
				to("time", timeExpr));

		// add this assertion to help with proofs (it should always be true)
		builder.addAssertion(new AgreeStatement("", lemmaExpr, reference));
		builder.addTimeOf(varName, timeCause);

		return timeCause;
	}

	private AgreeVar getTimeRise(String varName, AgreeNodeBuilder builder, EObject reference) {
		Map<String, AgreeVar> timeRiseMap = builder.build().timeRiseMap;
		if (timeRiseMap.containsKey(varName)) {
			return timeRiseMap.get(varName);
		}

		AgreeVar timeRise = new AgreeVar(varName + RISE_SUFFIX, NamedType.REAL, reference);
		builder.addOutput(timeRise);

		Expr rise = new NodeCallExpr(AgreeRealtimeCalendarBuilder.RISE_NODE_NAME, new IdExpr(varName));
		Expr timeVarExpr = expr("timeRise = (if rise then time else (-1.0 -> pre timeRise))", to("timeRise", timeRise),
				to("rise", rise), to("time", timeExpr));
		builder.addAssertion(new AgreeStatement(null, timeVarExpr, reference));

		Expr lemmaExpr = expr("timeRise <= time and timeRise >= -1.0", to("timeRise", timeRise), to("time", timeExpr));

		// add this assertion to help with proofs (it should always be true)
		builder.addAssertion(new AgreeStatement("", lemmaExpr, reference));
		builder.addTimeRise(varName, timeRise);

		return timeRise;
	}

	private AgreeVar getTimeFall(String varName, AgreeNodeBuilder builder, EObject reference) {
		Map<String, AgreeVar> timeFallMap = builder.build().timeFallMap;
		if (timeFallMap.containsKey(varName)) {
			return timeFallMap.get(varName);
		}

		AgreeVar timeFall = new AgreeVar(varName + FALL_SUFFIX, NamedType.REAL, reference);
		builder.addOutput(timeFall);

		Expr Fall = new NodeCallExpr(AgreeRealtimeCalendarBuilder.FALL_NODE_NAME, new IdExpr(varName));
		Expr timeVarExpr = expr("timeFall = (if Fall then time else (-1.0 -> pre timeFall))", to("timeFall", timeFall),
				to("Fall", Fall), to("time", timeExpr));
		builder.addAssertion(new AgreeStatement(null, timeVarExpr, reference));

		Expr lemmaExpr = expr("timeFall <= time and timeFall >= -1.0", to("timeFall", timeFall), to("time", timeExpr));

		// add this assertion to help with proofs (it should always be true)
		builder.addAssertion(new AgreeStatement("", lemmaExpr, reference));
		builder.addTimeFall(varName, timeFall);

		return timeFall;
	}

	private Expr translatePatternProperty(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder, IdExpr causeId,
			IdExpr effectId) {
		switch (pattern.effectType) {
		case EVENT:
			return translatePatternEventProperty(pattern, builder, causeId, effectId);
		case CONDITION:
			return translatePatternConditionProperty(pattern, builder, causeId, effectId);
		default:
			throw new AgreeException("Unknown trigger type for pattern");
		}

	}

	private Expr translatePatternEventProperty(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder,
			IdExpr causeId, IdExpr effectId) {
		EObject varReference = pattern.reference;
		AgreeVar timerVar = new AgreeVar(TIMER_PREFIX + patternIndex, NamedType.REAL, varReference);
		AgreeVar runVar = new AgreeVar(RUNNING_PREFIX + patternIndex, NamedType.BOOL, varReference);
		AgreeVar recordVar = new AgreeVar(RECORD_PREFIX + patternIndex, NamedType.BOOL, varReference);
		builder.addLocal(timerVar);
		builder.addLocal(runVar);
		builder.addInput(recordVar);
		IdExpr timerId = new IdExpr(timerVar.id);
		IdExpr runId = new IdExpr(runVar.id);
		IdExpr recordId = new IdExpr(recordVar.id);

		// run = record -> if pre(run) and e and l <= timer <= h then
		// false
		// else
		// if record then
		// true
		// else
		// pre(run)

		Expr preRun = new UnaryExpr(UnaryOp.PRE, runId);

		{
			Expr if2 = new IfThenElseExpr(recordId, new BoolExpr(true), preRun);
			BinaryOp left = getIntervalLeftOp(pattern.effectInterval);
			BinaryOp right = getIntervalRightOp(pattern.effectInterval);
			Expr timerLow = new BinaryExpr(pattern.effectInterval.low, left, timerId);
			Expr timerHigh = new BinaryExpr(timerId, right, pattern.effectInterval.high);
			Expr cond1 = new BinaryExpr(preRun, BinaryOp.AND, effectId);
			cond1 = new BinaryExpr(cond1, BinaryOp.AND, timerLow);
			cond1 = new BinaryExpr(cond1, BinaryOp.AND, timerHigh);
			Expr if1 = new IfThenElseExpr(cond1, new BoolExpr(false), if2);
			Expr runExpr = new BinaryExpr(recordId, BinaryOp.ARROW, if1);
			builder.addLocalEquation(new AgreeEquation(runId, runExpr, varReference));
		}

		// timer = (0 -> if pre(run) then pre(timer) + (t - pre(t)) else 0)
		{
			Expr preTimer = new UnaryExpr(UnaryOp.PRE, timerId);
			Expr preT = new UnaryExpr(UnaryOp.PRE, timeExpr);
			Expr elapsed = new BinaryExpr(timeExpr, BinaryOp.MINUS, preT);
			Expr total = new BinaryExpr(preTimer, BinaryOp.PLUS, elapsed);
			Expr timerExpr = new IfThenElseExpr(preRun, total, new RealExpr(BigDecimal.ZERO));
			timerExpr = new BinaryExpr(new RealExpr(BigDecimal.ZERO), BinaryOp.ARROW, timerExpr);
			builder.addLocalEquation(new AgreeEquation(timerId, timerExpr, varReference));
		}

		// property that should be true for timer to help induction
		{
			Expr expr = new BinaryExpr(timerId, BinaryOp.GREATEREQUAL, new RealExpr(BigDecimal.ZERO));
			builder.addAssertion(new AgreeStatement(null, expr, varReference));
		}

		// record => cause and not (e and (l = 0))

		{
			Expr causeExpr;
			if (pattern.effectInterval.type == IntervalType.OPEN_LEFT
					|| pattern.effectInterval.type == IntervalType.OPEN) {
				causeExpr = causeId;
			} else {
				Expr eAndLZero = new BinaryExpr(pattern.effectInterval.low, BinaryOp.EQUAL,
						new RealExpr(BigDecimal.ZERO));
				eAndLZero = new BinaryExpr(effectId, BinaryOp.AND, eAndLZero);
				Expr notEAndLZero = new UnaryExpr(UnaryOp.NOT, eAndLZero);
				causeExpr = new BinaryExpr(causeId, BinaryOp.AND, notEAndLZero);
			}
			Expr recordExpr = new BinaryExpr(recordId, BinaryOp.IMPLIES, causeExpr);
			AgreeStatement statement = new AgreeStatement(null, recordExpr, varReference);
			builder.addAssertion(statement);
		}

		// lemma to help induction
		AgreeVar timeOfCause = getTimeOf(causeId.id, builder, pattern);
		AgreeVar timeOfEffect = getTimeOf(effectId.id, builder, pattern);
		// Expr expr = expr("(timer > 0.0 => timeOfCause > 0.0) and "
		// + "(timeOfEffect < timeOfCause => timer <= time - timeOfCause) and "
		// + "(cause => timeOfCause = time) and"
		// + "(true -> ((pre (timeOfEffect - low > timeOfCause)) => timer =
		// 0.0))",
		// to("timer", timerVar),
		// to("timeOfCause", timeOfCause),
		// to("time", timeExpr),
		// to("cause", causeId),
		// to("timeOfEffect", timeOfEffect),
		// to("low", pattern.effectInterval.low));

		Expr expr = expr("(timer > 0.0 => timeOfCause >= 0.0) and " + "(timer <= time) and"
				+ "(timeOfEffect >= timeOfCause and timer <= high and timeOfEffect >= time - timer + low => not run) and"
				+ "(true -> (pre(timeOfEffect >= timeOfCause + low and timeOfEffect <= timeOfCause + high and timer <= high) => timer = 0.0)) and"
				+ "(timer = 0.0 or timer >= time - timeOfCause)", to("timer", timerVar), to("timeOfCause", timeOfCause),
				to("timeOfEffect", timeOfEffect), to("time", timeExpr), to("low", pattern.effectInterval.low),
				to("high", pattern.effectInterval.high), to("run", runVar));
		builder.addPatternProp(new AgreeStatement("Timer Lemma for Pattern " + patternIndex, expr, pattern));

		// timer <= h
		BinaryOp right = getIntervalRightOp(pattern.effectInterval);
		return new BinaryExpr(timerId, right, pattern.effectInterval.high);
	}

	private Expr translatePatternConditionProperty(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder,
			IdExpr causeId, IdExpr effectId) {
		EObject varReference = pattern.reference;
		AgreeVar recordVar = new AgreeVar(RECORD_PREFIX + patternIndex, NamedType.BOOL, varReference);
		AgreeVar windowVar = new AgreeVar(WINDOW_PREFIX + patternIndex, NamedType.BOOL, varReference);

		builder.addInput(recordVar);
		builder.addLocal(windowVar);
		AgreeVar tRecord = getTimeOf(recordVar.id, builder, pattern);

		Expr expr = expr("record => cause", to("record", recordVar), to("cause", causeId));
		builder.addAssertion(new AgreeStatement(null, expr, varReference));

		BinaryOp left = getIntervalLeftOp(pattern.effectInterval);
		BinaryOp right = getIntervalRightOp(pattern.effectInterval);

		Equation eq = equation(
				"in_window = (trecord <> -1.0) and " + "(l + trecord " + left + " time) and (time " + right
						+ " h + trecord);",
				to("in_window", windowVar), to("trecord", tRecord), to("time", timeExpr),
				to("l", pattern.effectInterval.low), to("h", pattern.effectInterval.high));
		builder.addLocalEquation(new AgreeEquation(eq, varReference));

		return expr("in_window => effect", to("in_window", windowVar), to("effect", effectId));
	}

	private void addPatternConstraintProperty(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder, IdExpr causeId,
			IdExpr effectId) {
		AgreeVar newCause = new AgreeVar(NEW_CAUSE_PREFIX + causeId.id + patternIndex, NamedType.BOOL, pattern);
		builder.addLocal(newCause);

		AgreeVar timeCauseVar = getTimeOf(causeId.id, builder, pattern);
		IdExpr timeCauseId = new IdExpr(timeCauseVar.id);
		IdExpr newCauseId = new IdExpr(newCause.id);

		Expr preTimeCause = new UnaryExpr(UnaryOp.PRE, timeCauseId);
		Expr newCauseExpr = new BinaryExpr(preTimeCause, BinaryOp.NOTEQUAL, timeCauseId);
		newCauseExpr = new BinaryExpr(newCauseExpr, BinaryOp.AND,
				new BinaryExpr(preTimeCause, BinaryOp.GREATEREQUAL, new RealExpr(BigDecimal.ZERO)));
		builder.addLocalEquation(new AgreeEquation(newCauseId, newCauseExpr, pattern));

		if (pattern.effectType == TriggerType.EVENT) {

			AgreeVar timeEffectVar = getTimeOf(effectId.id, builder, pattern);
			IdExpr timeEffectId = new IdExpr(timeEffectVar.id);

			Expr preTimeCausePlusL = new BinaryExpr(preTimeCause, BinaryOp.PLUS, pattern.effectInterval.low);
			BinaryOp left = getIntervalLeftOp(pattern.effectInterval);
			Expr inInterval = new BinaryExpr(preTimeCausePlusL, left, timeEffectId);
			Expr propExpr = new BinaryExpr(newCauseId, BinaryOp.IMPLIES, inInterval);
			propExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, propExpr);
			AgreeStatement statement = new AgreeStatement(" pattern " + patternIndex + " in bounds", propExpr, pattern);
			builder.addPatternProp(statement);
		} else {
			AgreeVar timeEndVar = new AgreeVar(END_INTERVAL + patternIndex, NamedType.REAL, pattern);
			builder.addLocal(timeEndVar);

			Equation eq = equation("timeEnd = timeCause + h;", to("timeEnd", timeEndVar), to("timeCause", timeCauseId),
					to("h", pattern.effectInterval.high));
			builder.addLocalEquation(new AgreeEquation(eq, pattern));

			Expr expr = expr("true -> (newCause => pre(timeEnd) < time)", to("timeEnd", timeEndVar),
					to("newCause", newCauseId), to("time", timeExpr));
			AgreeStatement statement = new AgreeStatement(" pattern " + patternIndex + " in bounds", expr, pattern);
			builder.addPatternProp(statement);
		}

	}

	private Expr translatePatternEffectHoldConstraint(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder,
			IdExpr causeId, IdExpr effectId) {

		AgreeVar timeCauseVar = getTimeOf(causeId.id, builder, pattern);
		AgreeVar timeoutVar = new AgreeVar(TIMEOUT_PREFIX + patternIndex, NamedType.REAL, pattern);

		builder.addOutput(timeoutVar);

		Expr timeoutExpr = expr("timeout = if timeCause >= 0.0 then (timeCause + l) else -1.0",
				to("timeout", timeoutVar), to("timeCause", timeCauseVar), to("l", pattern.effectInterval.low));

		builder.addAssertion(new AgreeStatement(null, timeoutExpr, pattern.reference));
		builder.addEventTime(timeoutVar);

		BinaryOp left = getIntervalLeftOp(pattern.effectInterval);
		BinaryOp right = getIntervalRightOp(pattern.effectInterval);

		Expr intervalLeft = expr("timeCause + l", to("timeCause", timeCauseVar), to("l", pattern.effectInterval.low));
		Expr intervalRight = expr("timeCause + h", to("timeCause", timeCauseVar), to("h", pattern.effectInterval.high));

		intervalLeft = new BinaryExpr(intervalLeft, left, timeExpr);
		intervalRight = new BinaryExpr(timeExpr, right, intervalRight);

		Expr inInterval = new BinaryExpr(intervalLeft, BinaryOp.AND, intervalRight);

		String constrString;
		if (pattern.effectIsExclusive) {
			constrString = "if timeCause > -1.0 and inInterval then effectTrue else not effectTrue";
		} else {
			constrString = "timeCause > -1.0 => inInterval => effectTrue";
		}

		Expr expr = expr(constrString, to("timeCause", timeCauseVar), to("inInterval", inInterval),
				to("effectTrue", effectId));

		return expr;
	}

	private Expr translatePatternEffectEventConstraint(AgreeCauseEffectPattern pattern, AgreeNodeBuilder builder,
			IdExpr causeId, IdExpr effectId) {
		AgreeVar effectTimeRangeVar = new AgreeVar(EFFECT_TIME_RANGE_PREFIX + patternIndex, NamedType.REAL, pattern);
		builder.addInput(effectTimeRangeVar);
		IdExpr effectTimeRangeId = new IdExpr(effectTimeRangeVar.id);

		AgreeVar timeEffectVar = new AgreeVar(TIME_WILL_PREFIX + patternIndex, NamedType.REAL, pattern);
		builder.addOutput(timeEffectVar);
		IdExpr timeEffectId = new IdExpr(timeEffectVar.id);

		Expr effectTimeRangeConstraint = getTimeRangeConstraint(effectTimeRangeId, pattern.effectInterval);
		builder.addAssertion(new AgreeStatement(null, effectTimeRangeConstraint, pattern.reference));
		// make a constraint that triggers when the event WILL happen

		Expr expr = expr("timeEffect = if causeId then effectTimeRangeId else (-1.0 -> pre timeEffect)",
				to("timeEffect", timeEffectId), to("causeId", causeId), to("effectTimeRangeId", effectTimeRangeId));

		builder.addAssertion(new AgreeStatement(null, expr, pattern));

		// a lemma that may be helpful

		Expr lemma = expr("timeEffect <= time + intHigh", to("timeEffect", timeEffectVar), to("time", timeExpr),
				to("intHigh", pattern.effectInterval.high));

		builder.addAssertion(new AgreeStatement(null, lemma, pattern));

		lemma = expr("timeWill <= causeTime + high and (causeTime >= 0.0 => causeTime + low <= timeWill)",
				to("timeWill", timeEffectVar), to("causeTime", getTimeOf(causeId.id, builder, pattern)),
				to("high", pattern.effectInterval.high), to("low", pattern.effectInterval.low));

		builder.addAssertion(new AgreeStatement(null, lemma, pattern));

		lemma = expr("timeWill <= time => timeWill <= timeEffect", to("timeWill", timeEffectVar),
				to("timeEffect", getTimeOf(effectId.id, builder, pattern)));

		builder.addAssertion((new AgreeStatement(null, lemma, pattern)));

		// Expr lemmaExpr = expr("timeEffect <= effectTimeRangeId and timeEffect
		// >= -1.0",
		// to("timeEffect", timeEffectId),
		// to("effectTimeRangeId", effectTimeRangeId));
		//
		// //add this assertion to help with proofs (it should always be true)
		// builder.addAssertion(new AgreeStatement(null, lemmaExpr, pattern));

		// register the event time
		builder.addEventTime(timeEffectVar);
		// make the equation that triggers the event at the correct ime
		Expr timeEqualsEffectTime = new BinaryExpr(timeExpr, BinaryOp.EQUAL, timeEffectId);
		// if the event is exclusive it only occurs when scheduled
		BinaryOp effectOp = pattern.effectIsExclusive ? BinaryOp.EQUAL : BinaryOp.IMPLIES;
		Expr impliesEffect = new BinaryExpr(timeEqualsEffectTime, effectOp, effectId);
		return impliesEffect;
	}

	// this method registers a timeout and creates an event that is true iff the
	// condition
	// holds during the given interval. This is meant to essentially translate a
	// condition
	// pattern to a purely event based pattern. it returns an IdExpr
	// corresponding to the
	// event that triggers when the condition is held for the interval
	private IdExpr translateCauseCondtionPattern(AgreeCauseEffectPattern pattern, IdExpr causeId,
			AgreeNodeBuilder builder) {

		AgreeVar causeRiseTimeVar = getTimeRise(causeId.id, builder, pattern);
		AgreeVar causeFallTimeVar = getTimeFall(causeId.id, builder, pattern);
		AgreeVar causeHeldVar = new AgreeVar(CAUSE_CONDITION_HELD_PREFIX + causeId.id, NamedType.BOOL, pattern);
		AgreeVar causeHeldTimeoutVar = new AgreeVar(CAUSE_CONDITION_TIMEOUT_PREFIX + causeId.id, NamedType.REAL,
				pattern);

		builder.addLocal(causeHeldVar);
		builder.addInput(causeHeldTimeoutVar);

		IdExpr causeFallTimeId = new IdExpr(causeFallTimeVar.id);
		IdExpr causeHeldId = new IdExpr(causeHeldVar.id);
		IdExpr causeRiseTimeId = new IdExpr(causeRiseTimeVar.id);
		IdExpr causeHeldTimeoutId = new IdExpr(causeHeldTimeoutVar.id);

		{
			// timeout = if causeRiseTime > -1 and causeRiseTime > causeFallTime
			// then
			// causeRiseTime + h
			// else
			// -1
			Expr posRise = new BinaryExpr(causeRiseTimeId, BinaryOp.GREATER, NEG_ONE);
			Expr gtFall = new BinaryExpr(causeRiseTimeId, BinaryOp.GREATER, causeFallTimeId);
			Expr cond = new BinaryExpr(posRise, BinaryOp.AND, gtFall);

			Expr heldTime = new BinaryExpr(causeRiseTimeId, BinaryOp.PLUS, pattern.causeInterval.high);
			Expr ifExpr = new IfThenElseExpr(cond, heldTime, NEG_ONE);
			// builder.addLocalEquation(new AgreeEquation(causeHeldTimeoutId,
			// ifExpr, pattern));
			builder.addAssertion(
					new AgreeStatement(null, new BinaryExpr(causeHeldTimeoutId, BinaryOp.EQUAL, ifExpr), pattern));
			builder.addEventTime(causeHeldTimeoutVar);
		}

		{
			// causeHeld = (t = causeHeldTimeout)
			Expr causeHeldExpr = new BinaryExpr(timeExpr, BinaryOp.EQUAL, causeHeldTimeoutId);
			builder.addLocalEquation(new AgreeEquation(causeHeldId, causeHeldExpr, pattern));
		}

		return causeHeldId;
	}

	private static BinaryOp getIntervalLeftOp(AgreePatternInterval interval) {
		switch (interval.type) {
		case CLOSED:
			return BinaryOp.LESSEQUAL;
		case OPEN:
			return BinaryOp.LESS;
		case OPEN_LEFT:
			return BinaryOp.LESS;
		case OPEN_RIGHT:
			return BinaryOp.LESSEQUAL;
		default:
			throw new AgreeException("Unhandled IntervalType : " + interval.type);
		}
	}

	private static BinaryOp getIntervalRightOp(AgreePatternInterval interval) {
		switch (interval.type) {
		case CLOSED:
			return BinaryOp.LESSEQUAL;
		case OPEN:
			return BinaryOp.LESS;
		case OPEN_LEFT:
			return BinaryOp.LESSEQUAL;
		case OPEN_RIGHT:
			return BinaryOp.LESS;
		default:
			throw new AgreeException("Unhandled IntervalType : " + interval.type);
		}
	}

	private static Expr getTimeRangeConstraint(Expr timeRangeId, AgreePatternInterval interval) {
		Expr occurs = new BinaryExpr(timeRangeId, BinaryOp.MINUS, timeExpr);
		BinaryOp left = getIntervalLeftOp(interval);
		BinaryOp right = getIntervalRightOp(interval);

		Expr lower = new BinaryExpr(interval.low, left, occurs);
		Expr higher = new BinaryExpr(occurs, right, interval.high);
		return new BinaryExpr(lower, BinaryOp.AND, higher);
	}

	private static Expr translatePattern(AgreeTimesPattern pattern, AgreeNodeBuilder builder) {
		throw new AgreeException("Implement this");
	}

}
