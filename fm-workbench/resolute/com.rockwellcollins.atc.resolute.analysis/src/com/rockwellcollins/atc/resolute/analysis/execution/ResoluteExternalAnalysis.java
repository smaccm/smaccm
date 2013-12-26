package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public interface ResoluteExternalAnalysis {
    public ResoluteValue run(ResoluteEvaluator eval, List<ResoluteValue> args);
}