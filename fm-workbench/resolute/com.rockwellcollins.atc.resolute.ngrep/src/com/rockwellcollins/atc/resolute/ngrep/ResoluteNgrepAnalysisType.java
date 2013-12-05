package com.rockwellcollins.atc.resolute.ngrep;

import java.util.ArrayList;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalAnalysisType;
import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ResoluteNgrepAnalysisType implements ResoluteExternalAnalysisType {
    @Override
    public ResoluteType getType() {
        return BaseType.BOOL;
    }

    @Override
    public List<ResoluteType> getArgTypes() {
    	ArrayList<ResoluteType> list;
    	list = new ArrayList<ResoluteType>();
    	list.add(BaseType.STRING);
    	list.add(BaseType.STRING);
    	list.add(BaseType.INT);
        return list;
    }
}
