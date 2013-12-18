package com.rockwellcollins.atc.resolute.consistency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.osate.aadl2.NamedElement;
import org.osate.aadl2.util.OsateDebug;

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
    	ResoluteValue arg4 = args.get(4);
    	
    	assert (arg0.isString());
    	assert (arg1.isNamedElement());
    	assert (arg2.isString());
    	assert (arg3.isString());
    	assert (arg4.isString());
    	
    	NamedElement component = arg1.getNamedElement();
    	String modelType = arg2.getString();
    	String checkType = arg3.getString();
    	String filename = arg4.getString();
    	
    	OsateDebug.osateDebug("[ResoluteConsistencyAnalysis] component="+component.getName()+";modelType="+modelType+";checkType="+checkType+"filename="+filename);
        
    	if (modelType.equalsIgnoreCase("geometry_stp"))
    	{
    		/*
    		 * Geometry-specific semantics checks
    		 */
    	}
    	
    	if (modelType.equalsIgnoreCase("semantics checks"))
    	{
    		/*
    		 * Geometry-specific semantics checks
    		 */
    	}
    	return new BoolValue(true);
    }

}
