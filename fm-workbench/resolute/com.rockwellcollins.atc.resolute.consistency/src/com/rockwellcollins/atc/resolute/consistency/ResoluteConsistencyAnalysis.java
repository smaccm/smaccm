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
import com.rockwellcollins.atc.resolute.consistency.Utils.ModelType;
import com.rockwellcollins.atc.resolute.consistency.checker.AbstractChecker;
import com.rockwellcollins.atc.resolute.consistency.checker.GeometryChecker;
import com.rockwellcollins.atc.resolute.consistency.checker.SimulinkChecker;



public class ResoluteConsistencyAnalysis implements ResoluteExternalAnalysis {
    @Override
    public ResoluteValue run(ResoluteEvaluator resEval, List<ResoluteValue> args) 
    {

    	ResoluteValue arg0 = args.get(0);
    	ResoluteValue arg1 = args.get(1);
    	ResoluteValue arg2 = args.get(2);
    	ResoluteValue arg3 = args.get(3);
    	ResoluteValue arg4 = args.get(4);
    	ResoluteValue arg5 = args.get(5);
    	
    	assert (arg0.isString());
    	assert (arg1.isNamedElement());
    	assert (arg2.isString());
    	assert (arg3.isString());
    	assert (arg4.isString());
    	assert (arg5.isString());
    	
    	NamedElement component 					= arg1.getNamedElement();
    	String modelTypeStr 					= arg2.getString();
    	String checkTypeStr 					= arg3.getString();
    	String artifact 						= arg4.getString();
    	String filename 						= arg5.getString();
        Utils.ModelType modelType 				= Utils.getModelType(modelTypeStr);
        Utils.ConsistencyType consistencyType 	= Utils.getConsistencyType(checkTypeStr);
    	AbstractChecker checker					= null;
    	
    	OsateDebug.osateDebug("[ResoluteConsistencyAnalysis] component="+component.getName()+";modelType="+modelTypeStr+";checkType="+checkTypeStr+"filename="+filename);

    	if (modelType == ModelType.GEOMETRY_STP)
    	{
    		/*
    		 * Geometry-specific semantics checks
    		 */
    		checker = new GeometryChecker (component, consistencyType, artifact, filename);
    	}
    	
    	if (modelType == ModelType.SIMULINK)
    	{
    		/*
    		 * Geometry-specific semantics checks
    		 */
    		checker = new SimulinkChecker (component, consistencyType, artifact, filename);
    	}
    	
    	
    	if (checker == null)
    	{
    		OsateDebug.osateDebug("[ResoluteConsistencyAnalysis] no appropriate checker found for the model type " + modelTypeStr);
    		return new BoolValue (false);
    	}
    	return new BoolValue(checker.check());
    }

}
