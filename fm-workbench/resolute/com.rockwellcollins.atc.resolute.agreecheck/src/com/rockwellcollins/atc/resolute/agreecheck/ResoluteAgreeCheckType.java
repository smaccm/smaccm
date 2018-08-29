package com.rockwellcollins.atc.resolute.agreecheck;

import java.util.ArrayList;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalAnalysisType;
import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ResoluteAgreeCheckType implements ResoluteExternalAnalysisType {
	@Override
	public ResoluteType getType() {
		return BaseType.BOOL;
	}

	@Override
	public List<ResoluteType> getArgTypes() {
		ArrayList<ResoluteType> list = new ArrayList<ResoluteType>();
		return list;
	}
}
