package com.rockwellcollins.atc.agree.analysis;
 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.BusAccess;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ConnectionEnd;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataAccess;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.Property;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.analysis.AgreeFeature.ConnType;
import com.rockwellcollins.atc.agree.analysis.AgreeFeature.Direction;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout.SigType;

public class AgreeAnnexEmitter  {

    //this set keeps track of all the left hand sides of connection
    //expressions
    public final Set<String> connLHS = new HashSet<>();
    public static final String sysGuarTag = "_SYS_GUARANTEE_";
    
    //used for preventing name collision, and for pretty printing aadl variables
    private static final String clockIDPrefix = "__CLOCK_";
    private static final String eventPrefix = "__EVENT_";

    //the special string used to replace the "." characters in aadl
    private static final String dotChar = "__";
    
    //the depth of which to check consistency
    private int consistUnrollDepth = 5;
    private List<IdExpr> calendar = new ArrayList<IdExpr>();
    private final Map<FeatureInstance, List<AgreeFeature>> featInstToAgreeFeatMap = new HashMap<>();
	public List<String> consistProps;
	public List<String> assumProps;
	public List<String> guarProps;


	private void recordFeatures(AgreeEmitterState state) {
		ComponentInstance compInst = state.curInst;
		for(FeatureInstance featInst : compInst.getFeatureInstances()){
        	List<AgreeFeature> featList = recordFeatures_Helper(state, state.thisPrefix, featInst);
        	//add features to the correct input var or output var location
        	addFeatureInputsOutputs(state, featList, state.category, false);
        }
        
        //get information about all the features of this components subcomponents 
        for(ComponentInstance subCompInst : compInst.getComponentInstances()){
        	for(FeatureInstance featInst : subCompInst.getFeatureInstances()){
            	List<AgreeFeature> featList = recordFeatures_Helper(state, state.thisPrefix + subCompInst.getName() + dotChar,
            			featInst);
            	addFeatureInputsOutputs(state, featList, subCompInst.getName(), true);
        	}
        }
	}

	private void addFeatureInputsOutputs(AgreeEmitterState state, List<AgreeFeature> featList, String category, boolean fSubcomponent) {
		for(AgreeFeature agreeFeat : featList){
			AgreeVarDecl varDecl = new AgreeVarDecl(agreeFeat.lustreString, agreeFeat.varType);
			String renameStr = state.agreeRename.rename(agreeFeat.lustreString);
			String eventStr = null;
			
			AgreeVarDecl eventDecl = null;
			String eventRenameStr = null;
			if(agreeFeat.connType == ConnType.EVENT){
				eventStr = eventPrefix+agreeFeat.lustreString;
				eventDecl = new AgreeVarDecl(eventStr, NamedType.BOOL);
				eventRenameStr = state.agreeRename.rename(eventStr);
			}
			
			switch(agreeFeat.direction){
			case IN:
				state.inputVars.add(varDecl);
				state.layout.addElement(category, renameStr, AgreeLayout.SigType.INPUT);
				if(eventStr != null){
					state.inputVars.add(eventDecl);
					state.layout.addElement(category, eventRenameStr, AgreeLayout.SigType.INPUT);
				}
				break;
			case OUT:
				if(!fSubcomponent){//if we are not in a subcomponent these also need to look like inputs
					state.inputVars.add(varDecl);
				}
				state.outputVars.add(varDecl);
				state.layout.addElement(category, renameStr, AgreeLayout.SigType.OUTPUT);
				if(eventStr != null){
					if(!fSubcomponent){
						state.inputVars.add(eventDecl);
					}
					state.outputVars.add(eventDecl);
					state.layout.addElement(category, eventRenameStr, AgreeLayout.SigType.INPUT);
				}
			}
			
			AgreeStateUtils.addToRefMap(agreeFeat.lustreString, agreeFeat.feature, state.agreeRename, state.refMap);
			if(eventStr != null){
				AgreeStateUtils.addToRefMap(eventStr, agreeFeat.feature, state.agreeRename, state.refMap);
			}
		}
	}

    public void doLift(Subcomponent subComp, AgreeEmitterState parentState){

    	//if there are multiple lifts within a component
    	//we do not want to record its features or set
    	//its connection expressions more than once
    	if(!parentState.connectionExpressionsSet){
    		recordFeatures(parentState);
    		setConnExprs(parentState);
    	}
    	

    	ComponentInstance subCompInst = parentState.curInst.findSubcomponentInstance(subComp);
        ComponentImplementation subCompImpl = subComp.getComponentImplementation();
        ComponentType subCompType = subComp.getComponentType();

        AgreeEmitterState subState = new AgreeEmitterState(parentState.topLevelPrefix, 
        		parentState.thisPrefix + subComp.getName() + dotChar, parentState.layout,
        		parentState.thisPrefix + subComp.getName(), subCompInst, subComp);

    	recordFeatures(subState);

        
        if(subCompImpl != null){
        	for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
        		if (annex instanceof AgreeContractSubclause) {
        			Contract contract = ((AgreeContractSubclause) annex).getContract();
        			if(contract instanceof AgreeContract){
        				for(SpecStatement spec :  ((AgreeContract) contract).getSpecs()){
        					if(spec instanceof LiftStatement){
        						NestedDotID subSubID = ((LiftStatement) spec).getSubcomp();
        						Subcomponent subSubComp = (Subcomponent) subSubID.getBase();
        						doLift(subSubComp, subState);
        					}else if(spec instanceof LemmaStatement){//TODO might not be compositional
        						subState.doSwitch(spec);
                			}else if(spec instanceof EqStatement){//TODO might not be compositional
                				subState.doSwitch(spec);
                			}
        				}
        			}
        		}
        		break;
        	}
        }

        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompType, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                subState.doSwitch(annex);
                break;
            }
        }
        
        AgreeNode agreeNode = getComponentNode(subState);
        
        //don't add duplicate nodes
        for(Node node : agreeNode.nodes){
            boolean inSet = false;
            for(Node subnode : parentState.nodeDefExpressions){
                if(node.id.equals(subnode.id)){
                    inSet = true;
                    break;
                }
            }
            if(!inSet){
            	parentState.nodeDefExpressions.add(node);
            }
        }
        
        //internalVars.addAll(subEmitter.internalVars);
        for(VarDecl varDec : agreeNode.outputs){
        	AgreeVarDecl agreeVar = new AgreeVarDecl(varDec.id, varDec.type);
        	parentState.internalVars.add(agreeVar);
        }
        //internalVars.addAll(agreeNode.outputs);
        for(VarDecl varDec : agreeNode.inputs){
        	AgreeVarDecl agreeVar = new AgreeVarDecl(varDec.id, varDec.type);
        	parentState.inputVars.add(agreeVar);
        	parentState.outputVars.add(agreeVar);
        }
       //inputVars.addAll(agreeNode.inputs);
        
        for(Expr expr : agreeNode.assertions){
            IdExpr varId = (IdExpr)expr;
            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
            parentState.assertExpressions.add(varId);
            parentState.internalVars.add(agreeVar);
        }    
 
        for(Expr expr : agreeNode.assumptions){
            IdExpr varId = (IdExpr)expr;
            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
            parentState.assumpExpressions.add(varId);
            parentState.internalVars.add(agreeVar);
        }    
 
        for(Expr expr : agreeNode.guarantees){
            IdExpr varId = (IdExpr)expr;
            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
            Equation dumbEq = new Equation(varId, varId);
            parentState.guarExpressions.add(dumbEq);
            parentState.internalVars.add(agreeVar);
        }    

        List<Expr> nodeInputs = new ArrayList<>();
        for(VarDecl var : agreeNode.inputs){
			nodeInputs.add(new IdExpr(var.id));
        }
        
        List<IdExpr> nodeOutputs = new ArrayList<>();
        for(VarDecl var : agreeNode.outputs){
            nodeOutputs.add(new IdExpr(var.id));
        }

        //if the node has no outputs, then don't include it
        //perhaps it does not have guarantees?
        if(nodeOutputs.size() != 0){
        	NodeCallExpr nodeCall = new NodeCallExpr(agreeNode.mainNode.id, nodeInputs);
        	Equation nodeEq = new Equation(nodeOutputs, nodeCall);

        	parentState.eqExpressions.add(nodeEq);
        }
        //connExpressions.addAll(subEmitter.connExpressions);
        parentState.typeExpressions.addAll(subState.typeExpressions);
//        initTypeMap.putAll(subEmitter.initTypeMap);
        
        //addToRenamingAll(subEmitter.varRenaming);
        AgreeStateUtils.addAllToRefMap(subState.refMap, parentState.refMap); 
       
    }

	
   
    // fills the connExpressions lists with expressions
    // that equate variables that are connected to one another
    private void setConnExprs(AgreeEmitterState state) {

    	ComponentClassifier compClass = state.curInst.getComponentClassifier();
    	ComponentImplementation compImpl = (ComponentImplementation)compClass; //should always been an implementation
    	state.connectionExpressionsSet  = true;
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
            
            makeConnectionExpressions(state, absConnSour, absConnDest); 
        }
        
    }
    
    
    private void makeConnectionExpressions(AgreeEmitterState state,
			ConnectedElement absConnSour, ConnectedElement absConnDest) {
		
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
        List<AgreeFeature> destConns = featInstToAgreeFeatMap.get(destFeatInst);
        List<AgreeFeature> sourConns = featInstToAgreeFeatMap.get(sourFeatInst);
    	
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
        	
        		Equation connEq = new Equation(new IdExpr(lhsLustreName), new IdExpr(rhsLustreName));
        		addConnection(state, connEq);;

        		if(agreeDestConn.connType == ConnType.EVENT){
        			if(agreeSourConn.connType != ConnType.EVENT){
        				throw new AgreeException("The connection between variables '"
        			      +state.agreeRename.renameKeepPrefix(agreeDestConn.lustreString)+"' and '"
        			      +state.agreeRename.renameKeepPrefix(agreeSourConn.lustreString)+"' are of different types");
        			}
            		Equation eventConnEq = new Equation(new IdExpr(eventPrefix+lhsLustreName), new IdExpr(eventPrefix+rhsLustreName));
            		addConnection(state, eventConnEq);
        		}
        }
	}
    
    private List<AgreeFeature> recordFeatures_Helper(AgreeEmitterState state, String jPrefix, FeatureInstance featInst){
    	
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
    	
    	featInstToAgreeFeatMap.put(featInst, agreeConns);
    	return agreeConns;
    }


	private void recordFeatureGroup(AgreeEmitterState state, FeatureInstance featInst,
			List<AgreeFeature> agreeConns) {
		for(FeatureInstance subFeatInst : featInst.getFeatureInstances()){
			agreeConns.addAll(recordFeatures_Helper(state, featInst.getName()+dotChar, subFeatInst));
		}
		

	}

	private void recordPort(AgreeEmitterState state, FeatureInstance featInst,
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
//		    Property queueSizeProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
//		            OsateResourceUtil.getResourceSet(), "Communication_Properties::Queue_Size");
//		    try{
//		    	queueSize = PropertyUtils.getIntegerValue(featInst, queueSizeProp);
//		    }catch(PropertyDoesNotApplyToHolderException e){}
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
		agreeConns.add(agreeConn);

	}

    public static AgreeNode getComponentNode(AgreeEmitterState state){
        
        List<Equation> eqs = new ArrayList<Equation>();
        Set<VarDecl> inputs = new HashSet<VarDecl>();
        Set<VarDecl> outputs = new HashSet<VarDecl>();

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(state.nodeDefExpressions);
        eqs.addAll(state.constExpressions);
        eqs.addAll(state.eqExpressions);
        eqs.addAll(state.propExpressions);
        //eqs.addAll(this.connExpressions);

        inputs.addAll(state.inputVars);
        inputs.addAll(state.outputVars);
        outputs.addAll(state.internalVars);
        outputs.removeAll(inputs);
        
        //create equations for assumptions, assertions, and guarantees
        List<Expr> guarVars = new ArrayList<>();
        int i = 0;
        for(Equation eq : state.guarExpressions){
            IdExpr id = new IdExpr("__GUAR_"+state.category+"_"+i++);
            VarDecl guarVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(guarVar);
            guarVars.add(id);
            eqs.add(new Equation(id, eq.expr));
        }
        
        List<Expr> asserVars = new ArrayList<>();
        i = 0;
        for(Expr expr : state.assertExpressions){
            IdExpr id = new IdExpr("__ASSER_"+state.category+"_"+i++);
            VarDecl asserVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(asserVar);
            asserVars.add(id);
            eqs.add(new Equation(id, expr));
        }
        
        List<Expr> assumVars = new ArrayList<>();
        i = 0;
        for(Expr expr : state.assumpExpressions){
            IdExpr id = new IdExpr("__ASSUM_"+state.category+"_"+i++);
            VarDecl assumVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(assumVar);
            assumVars.add(id);
            eqs.add(new Equation(id, expr));
        }
        

        ArrayList<VarDecl> inputList = new ArrayList<>(inputs);
        ArrayList<VarDecl> outputList = new ArrayList<>(outputs);
        
        Node node = new Node("__NODE_"+state.category, inputList, outputList, Collections.EMPTY_LIST, eqs);
        //stupid hack to get rid of the prefix
        String clockStr = clockIDPrefix+state.thisPrefix.replaceFirst(dotChar+"$", "");
        AgreeVarDecl clockVar = new AgreeVarDecl(clockStr, NamedType.BOOL);
        
        List<Node> agreeNodes = new ArrayList<>(state.nodeDefExpressions);
        agreeNodes.add(node);
        
        AgreeNode agreeNode = new AgreeNode(inputList, outputList, assumVars,
                asserVars, guarVars, agreeNodes, node, clockVar, state.category);
        
        return agreeNode;
    }
    
    public Program getLustreWithCondacts(AgreeEmitterState state, List<AgreeEmitterState> subStates){

    	//get information about all the features of this component and its subcomponents
    	recordFeatures(state);
    	for(AgreeEmitterState subState : subStates){
    		recordFeatures(subState);
    	}
    	
    	setConnExprs(state);
    	
    	AgreeNode stateNode = getComponentNode(state);
    	
    	List<AgreeNode> subStateNodes = new ArrayList<>();
    	for(AgreeEmitterState subState : subStates){
    		subStateNodes.add(getComponentNode(subState));
    	}

    	assumProps = new ArrayList<String>();
    	consistProps = new ArrayList<String>();
    	guarProps = new ArrayList<String>();
        
        // start constructing the top level node
        List<Equation> eqs = new ArrayList<Equation>();
        List<VarDecl> inputs = new ArrayList<VarDecl>();
        List<Expr> clocks = new ArrayList<>();
        List<VarDecl> outputs = new ArrayList<VarDecl>();
        List<VarDecl> internals = new ArrayList<VarDecl>();
        List<String> properties = new ArrayList<String>();
        Set<jkind.lustre.RecordType> types = new HashSet<>();

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(stateNode.nodes);
        nodeSet.remove(stateNode.mainNode);//this node is kind of generated implicitly
        eqs.addAll(stateNode.mainNode.equations);
        types.addAll(state.typeExpressions);
        
        Set<AgreeVarDecl> agreeInputVars = new HashSet<>();
        Set<AgreeVarDecl> agreeInternalVars = new HashSet<>();
        
        for(VarDecl input : stateNode.inputs){
        	agreeInputVars.add(new AgreeVarDecl(input));
        }
        for(VarDecl output : stateNode.outputs){
        	agreeInputVars.add(new AgreeVarDecl(output));
        }
        for(VarDecl internal : stateNode.mainNode.outputs){
        	agreeInternalVars.add(new AgreeVarDecl(internal));
        }
        
//        for(Expr expr : stateNode.assertions){
//            IdExpr varId = (IdExpr)expr;
//            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
//            agreeInternalVars.add(agreeVar);
//            agreeInputVars.remove(agreeVar);
//        }
//        
//        for(Expr expr : stateNode.assumptions){
//            IdExpr varId = (IdExpr)expr;
//            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
//            agreeInternalVars.add(agreeVar);
//            agreeInputVars.remove(agreeVar);
//        }
//        
//        for(Expr expr : stateNode.guarantees){
//            IdExpr varId = (IdExpr)expr;
//            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
//            agreeInternalVars.add(agreeVar);
//            agreeInputVars.remove(agreeVar);
//        }
        
        //make the parent component clock always true
        agreeInternalVars.add(stateNode.clockVar);
        Equation clockEq = new Equation(new IdExpr(stateNode.clockVar.id), new BoolExpr(true));
        eqs.add(clockEq);
        

        addSubEmitterVars(state, subStates, eqs, inputs, clocks, nodeSet, types,
				agreeInputVars, agreeInternalVars); 

        //warn about combinational cycles
        List<Equation> cycleEqs = new ArrayList<>(eqs);
        for(AgreeEmitterState subState : subStates){
        	cycleEqs.addAll(subState.constExpressions);
        	cycleEqs.addAll(subState.eqExpressions);
        	cycleEqs.addAll(subState.propExpressions);
        	cycleEqs.addAll(subState.connExpressions);
        }
        AgreeEmitterUtilities.logCycleWarning(cycleEqs, state.agreeRename, false); 

        
        agreeInputVars.removeAll(agreeInternalVars);
        
        inputs.addAll(agreeInputVars);
        internals.addAll(agreeInternalVars);

        IdExpr totalCompHistId = new IdExpr("_TOTAL_COMP_HIST");
        IdExpr sysAssumpHistId = new IdExpr("_SYSTEM_ASSUMP_HIST");

        internals.add(new VarDecl(totalCompHistId.id, NamedType.BOOL));
        internals.add(new VarDecl(sysAssumpHistId.id, NamedType.BOOL));

        // total component history
        Expr totalCompHist = new BoolExpr(true);

        for(AgreeNode subStateNode : subStateNodes){
            totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, AgreeEmitterUtilities.getLustreContract(subStateNode));
        }

        Expr assumeAsserts = AgreeEmitterUtilities.getLustreAssumptionsAndAssertions(stateNode);
        totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, assumeAsserts);
        
        eqs.add(AgreeEmitterUtilities.getLustreHistory(totalCompHist, totalCompHistId));

        // system assumptions
        Expr sysAssumpHist = AgreeEmitterUtilities.getLustreAssumptionsAndAssertions(stateNode);
        //initial constraints of the subcomponents
        Expr subInitalConstrs = addInitialConstraints(subStates, eqs, internals);
        Expr sysAssumpAssertInitial = new BinaryExpr(subInitalConstrs, BinaryOp.AND, sysAssumpHist);
        
        eqs.add(AgreeEmitterUtilities.getLustreHistory(sysAssumpAssertInitial, sysAssumpHistId));
                
        //make a counter for checking finite consistency
        IdExpr countId = addConsistencyCounter(eqs, internals);
        
        // get the individual history variables and create assumption properties
        addSubcomponentAssumptions(state, subStateNodes, eqs, internals, properties,
				totalCompHistId, sysAssumpHistId);//, closureMap);
        
        
        //add a property that is true if the contract is a contradiction
        addSubcomponentConsistency(state, subStateNodes, eqs, internals, properties, countId);
        
        // create individual properties for guarantees
        int i = 0;
        for (Expr guar : stateNode.guarantees) {
            String guarName = state.guarExpressions.get(i).lhs.get(0).id; //the string from the guarantee
            IdExpr sysGuaranteesId = new IdExpr(sysGuarTag + i);
            internals.add(new VarDecl(sysGuaranteesId.id, NamedType.BOOL));

            Expr totalSysGuarExpr = new BinaryExpr(sysAssumpHistId, BinaryOp.AND, totalCompHistId);
//            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.AND, allClksTickedExpr);
            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.IMPLIES, guar);

            Equation finalGuar = new Equation(sysGuaranteesId, totalSysGuarExpr);
            eqs.add(finalGuar);
            properties.add(sysGuaranteesId.id);
            guarProps.add(sysGuaranteesId.id);
            AgreeStateUtils.addToRenaming(sysGuaranteesId.id, guarName, state.varRenaming);
            state.layout.addElement(state.category, "Component Guarantee " + i++, AgreeLayout.SigType.OUTPUT);
        }
        
        //check for contradiction in total component history
        addTotalCompHistoryConsist(state, eqs, internals, properties, 
        		new BinaryExpr(sysAssumpHistId, BinaryOp.AND, totalCompHistId),
				countId);
        
        //check to see if this specific components contract makes a contradiction
        addThisContractConsist(state, eqs, internals, properties,
				countId);

        //create the assertions for the clocks
        List<Expr> assertions = getClockAssertions(state, clocks, nodeSet);
        
        Node topNode = new Node("_MAIN", inputs, outputs, internals, eqs, properties, assertions);
        nodeSet.add(topNode);
        
        //make the type definitions
        List<TypeDef> typeDefs = new ArrayList<>();
        for(jkind.lustre.RecordType type : types){
        	TypeDef typeDef = new TypeDef(type.id, type);
        	typeDefs.add(typeDef);
        }
        
        return new Program(typeDefs, Collections.EMPTY_LIST, nodeSet);
    }

	private Expr addInitialConstraints(List<AgreeEmitterState> subStates,
			List<Equation> eqs, List<VarDecl> internals) {
		Expr initialConstraints = new BoolExpr(true);
        for(AgreeEmitterState subState : subStates){
        	IdExpr clockId = new IdExpr(getComponentNode(subState).clockVar.id);
        	Expr notClock = new UnaryExpr(UnaryOp.NOT, clockId);
        	IdExpr histNotClock = new IdExpr("_HIST_NOT__"+clockId.id);
        	internals.add(new VarDecl(histNotClock.id, NamedType.BOOL));
        	Equation notHistClock = AgreeEmitterUtilities.getLustreHistory(notClock, histNotClock);
        	eqs.add(notHistClock);
        	Expr intialConstraints = AgreeEmitterUtilities.conjoin(subState.initialExpressions);
        	Expr notClockInitial = new BinaryExpr(histNotClock, BinaryOp.IMPLIES, intialConstraints);
        	
        	initialConstraints = new BinaryExpr(initialConstraints, BinaryOp.AND, notClockInitial);
        }
        return initialConstraints;
	}


	private List<Expr> getClockAssertions(AgreeEmitterState state, List<Expr> clocks, List<Node> nodeSet) {
		
		if(clocks.size() == 0){
			//just return a blank list if there are no clocks
			return new ArrayList<>();
		}
		
		Expr clockAssertion;
        if(state.synchrony > 0){
            Node dfaNode = AgreeCalendarUtils.getDFANode("__dfa_node_"+state.category, state.synchrony); 
            Node calNode = AgreeCalendarUtils.getCalendarNode("__calendar_node_"+state.category, clocks.size());
            nodeSet.add(dfaNode);
            nodeSet.add(calNode);

            clockAssertion = new NodeCallExpr(calNode.id, clocks);
            
            //don't let multiple clocks tick together
            if(!state.simultaneity){
            	Expr onlyOneTick = AgreeCalendarUtils.getSingleTick(clocks);
            	clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, onlyOneTick);
            }
            
        }else if(this.calendar.size() > 0){
        	
        	Node calNode = AgreeCalendarUtils.getExplicitCalendarNode("__calendar_node_"+state.category, calendar, clocks);
        	nodeSet.add(calNode);
        	
        	clockAssertion = new NodeCallExpr(calNode.id, clocks);
        	
        }else{
            clockAssertion = new BoolExpr(true);
            for(Expr expr : clocks){
                clockAssertion = new BinaryExpr(expr, BinaryOp.AND, clockAssertion);
            }
        }
        
        //also assert that some clock ticks
        Expr clockTickAssertion = new BoolExpr(false);
        for(Expr expr : clocks){
            clockTickAssertion = new BinaryExpr(expr, BinaryOp.OR, clockTickAssertion);
        }
                
        List<Expr> assertions = new ArrayList<>();
        assertions.add(clockAssertion);
        assertions.add(clockTickAssertion);

		return assertions;
	}


	private void addTotalCompHistoryConsist(AgreeEmitterState state, List<Equation> eqs,
			List<VarDecl> internals, List<String> properties,
			Expr CompHistSysAssumpAssertInitial, IdExpr countId) {
		IdExpr notTotalCompHistId = new IdExpr("_TOTAL_COMP_FINITE_CONSIST");
        Expr finiteConsist = AgreeEmitterUtilities.getFinteConsistancy(CompHistSysAssumpAssertInitial, countId, consistUnrollDepth);
        Equation contrConsistEq = new Equation(notTotalCompHistId, finiteConsist);
        
        internals.add(new VarDecl(notTotalCompHistId.id, NamedType.BOOL));
        eqs.add(contrConsistEq);
        
        properties.add(notTotalCompHistId.id);
        this.consistProps .add(notTotalCompHistId.id);
        //reversePropStatus.add(true);
        AgreeStateUtils.addToRenaming(notTotalCompHistId.id, "Subcomponent Composition Consistency", state.varRenaming);
        //layout.addElement("Top", "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);
        state.layout.addElement(state.category, "Subcoponent Composition Consistency", AgreeLayout.SigType.OUTPUT);
	}
	
	private void addThisContractConsist(AgreeEmitterState state, List<Equation> eqs,
			List<VarDecl> internals, List<String> properties, IdExpr countId) {
		IdExpr contrHistId = new IdExpr("_THIS_CONTRACT_HIST");
		
		Expr guars = new BoolExpr(true);
		for(Equation guarEq : state.guarExpressions){
			guars = new BinaryExpr(guars, BinaryOp.AND, guarEq.expr);
		}
		
		Expr assumps = new BoolExpr(true);
		for(Expr assump : state.assumpExpressions){
			assumps = new BinaryExpr(assumps, BinaryOp.AND, assump);
		}
		
		Equation contrHistEq = AgreeEmitterUtilities.getLustreHistory(new BinaryExpr(assumps, BinaryOp.AND, guars), contrHistId);
		eqs.add(contrHistEq);
		
        Expr finiteConsist = AgreeEmitterUtilities.getFinteConsistancy(contrHistId, countId, consistUnrollDepth);
        IdExpr contrConsistId = new IdExpr("_THIS_CONTRACT_CONSIST");
        
		Equation contrConsistEq = new Equation(contrConsistId, finiteConsist);
        
        internals.add(new VarDecl(contrConsistId.id, NamedType.BOOL));
        internals.add(new VarDecl(contrHistId.id, NamedType.BOOL));
        eqs.add(contrConsistEq);
        
        properties.add(contrConsistId.id);
        consistProps.add(contrConsistId.id);
        //reversePropStatus.add(true);
        AgreeStateUtils.addToRenaming(contrConsistId.id, "This Contract Consistency", state.varRenaming);
        //layout.addElement("Top", "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);
        state.layout.addElement(state.category, "This Contract Consistency", AgreeLayout.SigType.OUTPUT);
	}


	private IdExpr addConsistencyCounter(List<Equation> eqs,
			List<VarDecl> internals) {
		IdExpr countId = new IdExpr("__CONSIST_COUNTER");
        internals.add(new VarDecl(countId.id, NamedType.INT));
        
        Expr countPre = new BinaryExpr(new UnaryExpr(UnaryOp.PRE, countId), BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        countPre = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, countPre);
        Equation contEq = new Equation(countId, countPre);
        eqs.add(contEq);
		return countId;
	}


	private void addSubEmitterVars(AgreeEmitterState state, List<AgreeEmitterState> subStates,
			List<Equation> eqs, List<VarDecl> inputs, List<Expr> clocks,
			List<Node> nodeSet, Set<jkind.lustre.RecordType> types, Set<AgreeVarDecl> agreeInputVars,
			Set<AgreeVarDecl> agreeInternalVars) {
		for(AgreeEmitterState subState : subStates){
            
            AgreeNode agreeNode = getComponentNode(subState); 
            
            //don't add duplicate nodes
            for(Node node : agreeNode.nodes){
            	boolean inSet = false;
            	for(Node subnode : nodeSet){
            		if(node.id.equals(subnode.id)){
            			inSet = true;
            			break;
            		}
            	}
            	if(!inSet){
            		nodeSet.add(node);
            	}
            }
            //nodeSet.addAll(agreeNode.nodes);
            
            List<Expr> initOutputs = new ArrayList<>();
            List<Expr> nodeInputs = new ArrayList<>();
            List<IdExpr> nodeOutputs = new ArrayList<>();
            
            //if the clock was explicitly referenced it may
            //have already been created. Don't add it to the
            //input list if it was already created
            IdExpr clockExpr = new IdExpr(agreeNode.clockVar.id);
            
            if(!state.inputVars.contains(agreeNode.clockVar)){ 
            	inputs.add(agreeNode.clockVar);
            	//make it so the clock is visible in the counter example
            	//addToRenaming(clockExpr.id, clockExpr.id);
            	AgreeStateUtils.addToRefMap(clockExpr.id, subState.curComp, state.agreeRename, state.refMap);
            	state.layout.addElement(subState.category, state.agreeRename.rename(clockExpr.id), SigType.INPUT);
            }
            
            clocks.add(clockExpr);
            
            agreeInputVars.addAll(subState.inputVars);
            agreeInternalVars.addAll(subState.internalVars); 
            types.addAll(subState.typeExpressions);
            //also add new assumption, assertion, and guarantee vars
            for(Expr expr : agreeNode.assertions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.assumptions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.guarantees){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, NamedType.BOOL);
                agreeInternalVars.add(agreeVar);
            }
            
            //make a dummy variable for the initial value 
            //of every output.  The initial values
            //will be constrained by the "initially"
            //statements in each contract
            
            for(VarDecl var : agreeNode.outputs){
            	NamedType type = (NamedType)var.type;
            	VarDecl initVar = new VarDecl("__INIT_VAL_"+var.id, type);
				inputs.add(initVar);
				initOutputs.add(new IdExpr(initVar.id));
            	nodeOutputs.add(new IdExpr(var.id));
            }

            for(VarDecl var : agreeNode.inputs){
                nodeInputs.add(new IdExpr(var.id));
            }

            if(agreeNode.outputs.size() != 0){// perhaps there are no guarantees?
            	NodeCallExpr nodeCall = new NodeCallExpr(agreeNode.mainNode.id, nodeInputs);

            	CondactExpr condact = new CondactExpr(clockExpr, nodeCall, initOutputs);
            	Equation condactEq = new Equation(nodeOutputs, condact);
            	eqs.add(condactEq);
            }
            //TODO is the varRenaming legacy at this point?
            AgreeStateUtils.addToRenamingAll(subState.varRenaming, state.varRenaming);
            AgreeStateUtils.addAllToRefMap(subState.refMap, state.refMap);
            
            //create the hold equations for the subcomponent outputs
            for(AgreeVarDecl varDec : subState.outputVars){
                AgreeVarDecl dummyAgreeVar = new AgreeVarDecl("___DUMMY_" +varDec.id, varDec.type);
                agreeInputVars.add(dummyAgreeVar);
                agreeInternalVars.add(varDec);
                
                IdExpr dummyId = new IdExpr(dummyAgreeVar.id);
                IdExpr clockId = new IdExpr(agreeNode.clockVar.id);
                IdExpr outId = new IdExpr(varDec.id);
                Expr preExpr = new BinaryExpr(dummyId, BinaryOp.ARROW, new UnaryExpr(UnaryOp.PRE, outId));
                
                Expr ifElse = new IfThenElseExpr(clockId, dummyId, preExpr);
                Equation clockOutEq = new Equation(outId, ifElse);
                eqs.add(clockOutEq);
            }

        }
	}


	private void addSubcomponentAssumptions(AgreeEmitterState state, List<AgreeNode> subStateNodes,
			List<Equation> eqs, List<VarDecl> internals,
			List<String> properties, Expr totalCompHist, Expr sysAssumpHist){//, Map<Subcomponent, Set<Subcomponent>> closureMap) {
		for(int stateIndex = 0; stateIndex < subStateNodes.size(); stateIndex++){
		
		    AgreeNode subStateNode = subStateNodes.get(stateIndex);
            Expr higherContracts = new BoolExpr(true);
            for(int partialOrderIndex = stateIndex+1; partialOrderIndex < subStateNodes.size(); partialOrderIndex++){
                AgreeNode partialStateNode = subStateNodes.get(partialOrderIndex);
                higherContracts = new BinaryExpr(higherContracts, BinaryOp.AND,
                		AgreeEmitterUtilities.getLustreContract(partialStateNode));
            }

            Expr contrAssumps = AgreeEmitterUtilities.getLustreAssumptions(subStateNode);

            IdExpr compId = new IdExpr("_Hist_" + subStateNode.category);
            internals.add(new VarDecl(compId.id, NamedType.BOOL));

            Expr leftSide = new UnaryExpr(UnaryOp.PRE, totalCompHist);
            leftSide = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, leftSide);
//            leftSide = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, leftSide);
            leftSide = new BinaryExpr(sysAssumpHist, BinaryOp.AND, leftSide);
            leftSide = new BinaryExpr(higherContracts, BinaryOp.AND, leftSide);
//            IdExpr clockVarId = new IdExpr(subEmitter.agreeNode.clockVar.id);
//            leftSide = new BinaryExpr(clockVarId, BinaryOp.AND, leftSide);

            Expr contrHistExpr = new BinaryExpr(leftSide, BinaryOp.IMPLIES, contrAssumps);
            Equation contrHist = new Equation(compId, contrHistExpr);
            eqs.add(contrHist);
            properties.add(compId.id);
            this.assumProps .add(compId.id);
            String propertyName = subStateNode.category + " Assumptions";
            AgreeStateUtils.addToRenaming(compId.id, propertyName, state.varRenaming);
            //layout.addElement("Top", propertyName, AgreeLayout.SigType.OUTPUT);
            state.layout.addElement(state.category, propertyName, AgreeLayout.SigType.OUTPUT);
        }
	}

	private void addSubcomponentConsistency(AgreeEmitterState state, List<AgreeNode> subStateNodes, List<Equation> eqs,
			List<VarDecl> internals, List<String> properties, IdExpr countId) {
		
		for(AgreeNode subStateNode: subStateNodes){

			IdExpr contrHistId = new IdExpr("_CONTR_HIST_" + subStateNode.category);
			IdExpr consistId = new IdExpr("_NULL_CONTR_HIST_" + subStateNode.category);

			Expr contExpr = AgreeEmitterUtilities.getLustreContract(subStateNode);
			Equation contHist = AgreeEmitterUtilities.getLustreHistory(contExpr, contrHistId);
			Expr finiteConsist = AgreeEmitterUtilities.getFinteConsistancy(contrHistId, countId, consistUnrollDepth);
			Equation contrConsistEq = new Equation(consistId, finiteConsist);
			eqs.add(contrConsistEq);
			eqs.add(contHist);
			internals.add(new VarDecl(contrHistId.id, NamedType.BOOL));
			internals.add(new VarDecl(consistId.id, NamedType.BOOL));

			properties.add(consistId.id);
			consistProps.add(consistId.id);
			//reversePropStatus.add(true);
			String contractName = subStateNode.category + " Consistent";
			AgreeStateUtils.addToRenaming(consistId.id, contractName, state.varRenaming);
			//layout.addElement("Top", contractName, AgreeLayout.SigType.OUTPUT);
			state.layout.addElement(state.category, contractName, AgreeLayout.SigType.OUTPUT);
		}
	}
    
    private void addConnection(AgreeEmitterState state, Equation connEq){
    	
    	if(connEq.lhs.size() != 1){
    		throw new AgreeException("attemp to add connection expression with a "
    				+ "left hand side not equal to one");
    	}
    	
    	String connStr = connEq.lhs.get(0).id;
    	
    	if(connLHS.contains(connStr)){
    		throw new AgreeException("multiple assignments of connection variable '"+connStr+"'");
    	}

    	connLHS.add(connStr);
    	//connExpressions.add(connEq);
    	state.assertExpressions.add(new BinaryExpr(connEq.lhs.get(0), BinaryOp.EQUAL, connEq.expr));
    	
    }
        
    private NamedType getNamedType(String name){
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
