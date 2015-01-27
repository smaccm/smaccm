package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.List;

import jkind.lustre.NamedType;

import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.agree.analysis.AgreeFeature.ConnType;
import com.rockwellcollins.atc.agree.analysis.AgreeFeature.Direction;

public class FeatureUtils {

	private static final String dotChar = "__";

	public static  void recordFeatures(AgreeEmitterState state, boolean recordSubFeatures) {
		ComponentInstance compInst = state.curInst;
		for(FeatureInstance featInst : compInst.getFeatureInstances()){
			List<AgreeFeature> featList = recordFeatures_Helper(state, "", featInst);
			//add features to the correct input var or output var location
			addFeatureInputsOutputs(state, featList, false);
		}
		//get information about all the features of this components subcomponents
		if(recordSubFeatures){
		    for(ComponentInstance subCompInst : compInst.getComponentInstances()){
		        for(FeatureInstance featInst : subCompInst.getFeatureInstances()){
		            List<AgreeFeature> featList = recordFeatures_Helper(state,  subCompInst.getName() + dotChar,
		                    featInst);
		            addFeatureInputsOutputs(state, featList, true);
		        }
		    }
		}
	}
	private static void addFeatureInputsOutputs(AgreeEmitterState state, List<AgreeFeature> featList, boolean fSubcomponent) {
		for(AgreeFeature agreeFeat : featList){
			AgreeVarDecl varDecl = new AgreeVarDecl(agreeFeat.lustreString, agreeFeat.varType);
			String eventStr = null;
			AgreeVarDecl eventDecl = null;
			if(agreeFeat.connType == ConnType.EVENT){
				eventStr = agreeFeat.lustreString+state.eventSuffix;
				eventDecl = new AgreeVarDecl(eventStr, NamedType.BOOL);
			}
			switch(agreeFeat.direction){
			case IN:
				state.inputVars.add(varDecl);
				if(eventStr != null){
					state.inputVars.add(eventDecl);
				}
				break;
			case OUT:
				state.outputVars.add(varDecl);
				if(eventStr != null){
					state.outputVars.add(eventDecl);
				}
			}
			state.refMap.put(agreeFeat.lustreString, agreeFeat.feature);
			if(eventStr != null){
				state.refMap.put(eventStr, agreeFeat.feature);
			}
		}
	}

	private static List<AgreeFeature> recordFeatures_Helper(AgreeEmitterState state, String jPrefix, FeatureInstance featInst){
		List<AgreeFeature> agreeConns = new ArrayList<>();
		switch(featInst.getCategory()){
		case FEATURE_GROUP:
			recordFeatureGroup(state, featInst, agreeConns);
			break;
		case DATA_PORT:
		case EVENT_DATA_PORT:
			recordPort(state, featInst, agreeConns);
			break;
		case DATA_ACCESS:
			//recordDataAccess(featInst, agreeConns);
			break;
		default:
			break; //TODO: handle other types
		}
		for(AgreeFeature agreeConn : agreeConns){
			if(agreeConn.lustreString == ""){
				agreeConn.lustreString = jPrefix;
			}else{
				agreeConn.lustreString = jPrefix + agreeConn.lustreString;
			}
		}
		state.featInstToAgreeFeatMap.put(featInst, agreeConns);
		return agreeConns;
	}
	
	private static void recordFeatureGroup(AgreeEmitterState state, FeatureInstance featInst,
			List<AgreeFeature> agreeConns) {
		for(FeatureInstance subFeatInst : featInst.getFeatureInstances()){
			agreeConns.addAll(recordFeatures_Helper(state, featInst.getName()+dotChar, subFeatInst));
		}
	}
	
	private static void recordPort(AgreeEmitterState state, FeatureInstance featInst,
			List<AgreeFeature> agreeConns) {
		DataSubcomponentType dataClass;
		long queueSize = -1;
		Feature dataFeature = featInst.getFeature();
		if(dataFeature instanceof DataPort){
			DataPort dataPort = (DataPort)dataFeature;
			dataClass = dataPort.getDataFeatureClassifier();
		}else{
			EventDataPort eventDataPort = (EventDataPort)dataFeature;
			dataClass = eventDataPort.getDataFeatureClassifier();
		}
		if(dataClass == null){
			//throw new AgreeException("data feature '"+dataFeature.getName()+"' does not have a specified type");
			//we don't handle untyped connections
			return;
		}
		Direction direction = AgreeFeature.Direction.IN;
		if(featInst.getDirection() == DirectionType.IN){
			direction = AgreeFeature.Direction.IN;
		}else if (featInst.getDirection() == DirectionType.OUT){
			direction = AgreeFeature.Direction.OUT;
		}else{
			//we don't handle in-out data ports
			return;
			//throw new AgreeException("unable to handle in-out direction type on port '"+featInst.getName()+"'");
		}
		ConnType connType;
		if(featInst.getCategory() == FeatureCategory.EVENT_DATA_PORT){
			connType = AgreeFeature.ConnType.EVENT;
			// Property queueSizeProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
			// OsateResourceUtil.getResourceSet(), "Communication_Properties::Queue_Size");
			// try{
			// queueSize = PropertyUtils.getIntegerValue(featInst, queueSizeProp);
			// }catch(PropertyDoesNotApplyToHolderException e){}
			//
		}else{
			connType = AgreeFeature.ConnType.IMMEDIATE;
		}
		AgreeFeature agreeConn = new AgreeFeature();
		agreeConn.feature = featInst.getFeature();
		agreeConn.varType = getNamedType(AgreeStateUtils.getRecordTypeName(dataClass, state.typeMap, state.typeExpressions));
		agreeConn.lustreString = featInst.getName();
		agreeConn.connType = connType;
		agreeConn.direction = direction;
		agreeConn.queueSize = queueSize;
		agreeConn.initState = AgreeStateUtils.getInitialType(agreeConn.varType.toString(), state.typeExpressions);
		agreeConns.add(agreeConn);
	}

	private static NamedType getNamedType(String name){
		switch(name){
		case "bool":
			return NamedType.BOOL;
		case "real":
			return NamedType.REAL;
		case "int":
			return NamedType.INT;
		default:
			return new NamedType(name);
		}
	}
	

}
