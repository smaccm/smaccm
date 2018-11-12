package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public abstract class ResoluteExternalFunctionLibrary {
	public abstract ResoluteValue run(EvaluationContext context, String function, List<ResoluteValue> args);
}
