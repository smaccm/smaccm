package com.rockwellcollins.atc.resolute.analysis.external;

import java.util.List;

import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public interface ResoluteExternalAnalysisType {

	public ResoluteType getType();

	public List<ResoluteType> getArgTypes();

}
