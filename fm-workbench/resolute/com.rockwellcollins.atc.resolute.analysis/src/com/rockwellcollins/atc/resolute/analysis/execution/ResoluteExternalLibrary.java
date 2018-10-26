package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public interface ResoluteExternalLibrary {
	public ResoluteValue run(EvaluationContext context, String function, List<ResoluteValue> args);
}
