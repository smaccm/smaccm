package com.rockwellcollins.atc.resolute.schedule.analysis;

import java.util.Collections;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalAnalysisType;
import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ResoluteScheduleAnalysisType implements ResoluteExternalAnalysisType {
    @Override
    public ResoluteType getType() {
        return BaseType.BOOL;
    }

    @Override
    public List<ResoluteType> getArgTypes() {
        return Collections.emptyList();
    }
}
