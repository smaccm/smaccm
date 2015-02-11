package com.rockwellcollins.atc.agree.analysis;

import java.util.List;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

import org.osate.aadl2.BusAccess;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ConnectionEnd;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataAccess;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.Property;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.analysis.AgreeFeature.ConnType;

public class ConnectionUtils {

	// fills the connExpressions lists with expressions
	// that equate variables that are connected to one another
	public static void recordConnections(AgreeEmitterState state) {
		ComponentClassifier compClass = state.curInst.getComponentClassifier();
		ComponentImplementation compImpl = (ComponentImplementation)compClass; //should always been an implementation
		state.connectionExpressionsSet = true;
		// use for checking delay
		Property commTimingProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
				OsateResourceUtil.getResourceSet(), "Communication_Properties::Timing");
		for (Connection conn : compImpl.getAllConnections()) {
			ConnectedElement absConnDest = conn.getDestination();
			ConnectedElement absConnSour = conn.getSource();
			boolean delayed = false;
			try{
				EnumerationLiteral lit = PropertyUtils.getEnumLiteral(conn, commTimingProp);
				delayed = lit.getName().equals("delayed");
			}catch(PropertyDoesNotApplyToHolderException e){
				delayed = false;
			}
			Context destContext = absConnDest.getContext();
			Context sourContext = absConnSour.getContext();
			//only make connections to things that have annexs
			if(destContext != null && destContext instanceof Subcomponent){
				if(!AgreeEmitterUtilities.containsAgreeAnnex((Subcomponent)destContext)){
					continue;
				}
			}
			if(sourContext != null && sourContext instanceof Subcomponent){
				if(!AgreeEmitterUtilities.containsAgreeAnnex((Subcomponent)sourContext)){
					continue;
				}
			}
			makeConnectionExpressions(state, absConnSour, absConnDest, delayed);
		}
	}
	private static void makeConnectionExpressions(AgreeEmitterState state,
			ConnectedElement absConnSour, ConnectedElement absConnDest, boolean delayed) {
		ConnectionEnd destConn = absConnDest.getConnectionEnd();
		ConnectionEnd sourConn = absConnSour.getConnectionEnd();
		//we currently don't handle data accesses or buss accesses
		if(destConn instanceof DataAccess
				|| sourConn instanceof DataAccess
				|| destConn instanceof BusAccess
				|| sourConn instanceof BusAccess){
			return;
		}
		Context destContext = absConnDest.getContext();
		Context sourContext = absConnSour.getContext();
		ComponentInstance sourInst = null;
		FeatureInstance sourBaseFeatInst = null;
		if(sourContext == null){
			sourInst = state.curInst;
		}else if(sourContext instanceof Subcomponent){
			sourInst = state.curInst.findSubcomponentInstance((Subcomponent)sourContext);
		}else{
			sourBaseFeatInst = state.curInst.findFeatureInstance((FeatureGroup)sourContext);
		}
		ComponentInstance destInst = null;
		FeatureInstance destBaseFeatInst = null;
		if(destContext == null){
			destInst = state.curInst;
		}else if(destContext instanceof Subcomponent){
			destInst = state.curInst.findSubcomponentInstance((Subcomponent)destContext);
		}else{
			destBaseFeatInst = state.curInst.findFeatureInstance((FeatureGroup)destContext);
		}
		//get the corresponding feature instances
		FeatureInstance sourFeatInst = null;
		FeatureInstance destFeatInst = null;
		List<FeatureInstance> sourFeatInsts = (sourInst == null) ?
				sourBaseFeatInst.getFeatureInstances() :
				sourInst.getFeatureInstances();
				
		List<FeatureInstance> destFeatInsts = (destInst == null) ?
				destBaseFeatInst.getFeatureInstances() :
				destInst.getFeatureInstances();
				
		for(FeatureInstance featInst : sourFeatInsts){
			if(featInst.getFeature() == sourConn){
				sourFeatInst = featInst;
				break;
			}
		}
		for(FeatureInstance featInst : destFeatInsts){
			if(featInst.getFeature() == destConn){
					destFeatInst = featInst;
					break;
			}
		}
		//grabs the subnames for all the connections
		List<AgreeFeature> destConns = state.featInstToAgreeFeatMap.get(destFeatInst);
		List<AgreeFeature> sourConns = state.featInstToAgreeFeatMap.get(sourFeatInst);
		String lhsLustreName = null;
		String rhsLustreName = null;
		String lhsAadlName = null;
		String rhsAadlName = null;
		int i;
		assert(destConns.size() == sourConns.size());
		for(i = 0; i < destConns.size(); i++){
			AgreeFeature agreeDestConn = destConns.get(i);
			AgreeFeature agreeSourConn = sourConns.get(i);
			if(!agreeDestConn.varType.equals(agreeSourConn.varType)){
				throw new AgreeException("Variables: '"+agreeDestConn.lustreString.replace("__", ".")+
						"' and '"+agreeSourConn.lustreString.replace("__", ".")+"' are of different types");
			}
			if(destContext == null || destContext instanceof FeatureGroup){
				switch(agreeDestConn.direction){
				case IN:
					lhsLustreName = agreeSourConn.lustreString;
					rhsLustreName = agreeDestConn.lustreString;
					break;
				case OUT:
					lhsLustreName = agreeDestConn.lustreString;
					rhsLustreName = agreeSourConn.lustreString;
				}
			}else{
				switch(agreeDestConn.direction){
				case IN:
					lhsLustreName = agreeDestConn.lustreString;
					rhsLustreName = agreeSourConn.lustreString;
					break;
				case OUT:
					lhsLustreName = agreeSourConn.lustreString;
					rhsLustreName = agreeDestConn.lustreString;
				}
			}
			Equation connEq;
			
			if(delayed){
			    Expr connExpr = new UnaryExpr(UnaryOp.PRE, new IdExpr(rhsLustreName));
			    connExpr = new BinaryExpr(agreeSourConn.initState, BinaryOp.ARROW, connExpr);
			    connEq = new Equation(new IdExpr(lhsLustreName), connExpr);
			}else{
			    connEq = new Equation(new IdExpr(lhsLustreName), new IdExpr(rhsLustreName));
			}
			addConnection(state, connEq);
			if(agreeDestConn.connType == ConnType.EVENT){
				if(agreeSourConn.connType != ConnType.EVENT){
					throw new AgreeException("The connection between variables '"
							+agreeDestConn.lustreString+"' and '"
							+agreeSourConn.lustreString+"' are of different types");
				}
				Equation eventConnEq;
				if(delayed){
				    Expr connExpr = new UnaryExpr(UnaryOp.PRE, new IdExpr(rhsLustreName+state.eventSuffix));
	                connExpr = new BinaryExpr(agreeSourConn.initState, BinaryOp.ARROW, connExpr);
	                eventConnEq = new Equation(new IdExpr(lhsLustreName+state.eventSuffix), connExpr);
				}else{
				    eventConnEq = new Equation(new IdExpr(lhsLustreName+state.eventSuffix), new IdExpr(rhsLustreName+state.eventSuffix));
				}
				addConnection(state, eventConnEq);
			}
		}

	}


	private static void addConnection(AgreeEmitterState state, Equation connEq){
		if(connEq.lhs.size() != 1){
			throw new AgreeException("attemp to add connection expression with a "
					+ "left hand side not equal to one");
		}
		String connStr = connEq.lhs.get(0).id;
		if(state.connLHS.contains(connStr)){
			throw new AgreeException("multiple assignments of connection variable '"+connStr+"'");
		}
		state.connLHS.add(connStr);
		//connExpressions.add(connEq);
		state.assertExpressions.add(new BinaryExpr(connEq.lhs.get(0), BinaryOp.EQUAL, connEq.expr));
	}

}
