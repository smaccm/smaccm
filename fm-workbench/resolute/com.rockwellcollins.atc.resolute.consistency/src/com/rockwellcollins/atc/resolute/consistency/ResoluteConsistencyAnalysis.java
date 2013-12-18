package com.rockwellcollins.atc.resolute.consistency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.ResoluteEvaluator;
import com.rockwellcollins.atc.resolute.analysis.ResoluteExternalAnalysis;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;



public class ResoluteConsistencyAnalysis implements ResoluteExternalAnalysis {
    @Override
    public ResoluteValue run(ResoluteEvaluator resEval, List<ResoluteValue> args) 
    {

    	ResoluteValue arg0 = args.get(0);
    	ResoluteValue arg1 = args.get(1);
    	ResoluteValue arg2 = args.get(2);
    	ResoluteValue arg3 = args.get(3);
    	
    	assert (arg0.isString());
    	assert (arg1.isString());
    	assert (arg2.isString());
    	assert (arg3.isString());
    			

        return new BoolValue(true);
    }

}
