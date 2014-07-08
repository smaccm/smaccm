package com.rockwellcollins.atc.agree.analysis;
 
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.Type;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.BusAccess;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ConnectionEnd;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataAccess;
import org.osate.aadl2.DataImplementation;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.DataType;
import org.osate.aadl2.DirectedFeature;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.FeatureGroupType;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
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
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CalenStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.analysis.AgreeFeature.ConnType;
import com.rockwellcollins.atc.agree.analysis.AgreeFeature.Direction;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout.SigType;

public class AgreeAnnexEmitter extends AgreeSwitch<Expr> {

    //lists of all the jkind expressions from the annex
    public final List<Expr> assumpExpressions = new ArrayList<>();
    public final List<Equation> guarExpressions = new ArrayList<>();
    public final List<Expr> assertExpressions = new ArrayList<>();
    public final List<Equation> propExpressions = new ArrayList<>();
    public final List<Equation> eqExpressions = new ArrayList<>();
    public final List<Equation> constExpressions = new ArrayList<>();
    public final List<Node> nodeDefExpressions = new ArrayList<>();
    public final List<Equation> connExpressions = new ArrayList<>();
    public final Set<jkind.lustre.RecordType> typeExpressions = new HashSet<>();
    //this set keeps track of all the left hand sides of connection
    //expressions
    public final Set<String> connLHS = new HashSet<>();
    public final String sysGuarTag = "_SYS_GUARANTEE_";
    
    //reference map used for hyperlinking from the console
    public final Map<String, EObject> refMap = new HashMap<>();
    
    //used for preventing name collision, and for pretty printing aadl variables
    private final String clockIDPrefix = "__CLOCK_";
    private final String queueInClockPrefix ="__QUEUE_IN_CLOCK_";
    private final String queueOutClockPrefix ="__QUEUE_OUT_CLOCK_";
    private final String queueCountPrefix = "__QUEUE_COUNT_";
    private final Map<EventDataPort, List<IdExpr>> queueInClockMap = new HashMap<>();
    private final Map<EventDataPort, List<IdExpr>> queueOutClockMap = new HashMap<>();
    private final Map<EventDataPort, List<IdExpr>> queueCountMap = new HashMap<>();
    private final Map<NamedElement, String> typeMap = new HashMap<>();
    
    //used for pretty printing jkind -> aadl variables
    public final Map<String, String> varRenaming = new HashMap<>();
    public final AgreeRenaming agreeRename;
    
    //used for printing results
    public final AgreeLayout layout;
    private final String category;
    
    //keeps track of new variables
    private final Set<AgreeVarDecl> inputVars = new HashSet<>();
    private final Set<AgreeVarDecl> internalVars = new HashSet<>();
    private final Set<AgreeVarDecl> outputVars = new HashSet<>();
    private final Map<String, List<AgreeQueueElement>> queueMap = new HashMap<>();
    
    //the special string used to replace the "." characters in aadl
    private final String dotChar = "__";
    
    //the current subcomponent
    public Subcomponent curComp; 
    
    //the current implementation
    private ComponentInstance curInst;
    
    //print errors and warnings here
    public final AgreeLogger log = new AgreeLogger();
    
    //used to keep track of the different properties
    public List<String> assumProps = null;
    public List<String> consistProps = null;
    public List<String> guarProps = null;
    
    //during recursive analysis we do not want to use lifts
    //when proving top level components
    private boolean ignoreLifts;
    
    //the depth of which to check consistency
    private int consistUnrollDepth = 5;
    
    //TODO: do something more robust about this later
    //holds initial values for types
    private Map<String, Expr> initTypeMap = new HashMap<>();
    private BoolExpr initBool = new BoolExpr(true);
    private RealExpr initReal = new RealExpr(new BigDecimal("0.0"));
    private IntExpr initInt = new IntExpr(BigInteger.ZERO);
    
    //the node generated by the getComponentNode() call;
    public AgreeNode agreeNode = null;
    private int synchrony = 0;
    private List<IdExpr> calendar = new ArrayList<IdExpr>();
    private boolean simultaneity = true;
    private final Map<FeatureInstance, List<AgreeFeature>> featInstToAgreeFeatMap = new HashMap<>();
	private String thisPrefix;
	private String topLevelPrefix;
	private boolean connectionExpressionsSet = false;

    public AgreeAnnexEmitter(ComponentInstance compInst,
            AgreeLayout layout,
            String category,
            String topLevelPrefix,
            String thisPrefix,
            boolean ignoreLifts,
            boolean topLevel){
    	
        this.layout = layout;
        this.curInst = compInst;
        this.category = category;
        this.ignoreLifts  = ignoreLifts;
        this.thisPrefix = thisPrefix;
        this.topLevelPrefix = topLevelPrefix;
        
        initTypeMap.put("bool", initBool);
        initTypeMap.put("real", initReal);
        initTypeMap.put("int", initInt);
        
        if(!layout.getCategories().contains(category)){
            layout.addCategory(category);
        }
        
        curComp = curInst.getSubcomponent();
        agreeRename =  new AgreeRenaming(topLevelPrefix, varRenaming);
                
        ComponentClassifier compClass = compInst.getComponentClassifier();
                
        //get information about all the features of this component and its subcomponents
        recordFeatures(compInst);
        
        if(topLevel && compClass instanceof ComponentImplementation){
        	//create subcomponent clock variables
        	for(Subcomponent subComp : ((ComponentImplementation)compClass).getAllSubcomponents()){
        		getClockID(subComp);
        	}

        	setConnExprs((ComponentImplementation)compClass);
        	//this function must be called after the previous function
        	//setEventPortQueues();
        }
    }
    
    private void addAllToRefMap(Map<String, EObject> refs){
    	refMap.putAll(refs);
    }

    private void addToRefMap(String refString, EObject reference){
    	
    	String renaming = agreeRename.rename(refString);
    	refMap.put(renaming, reference);
    	
    }
    
	private void recordFeatures(ComponentInstance compInst) {
		for(FeatureInstance featInst : compInst.getFeatureInstances()){
        	List<AgreeFeature> featList = recordFeatures_Helper(thisPrefix, featInst);
        	//add features to the correct input var or output var location
        	for(AgreeFeature agreeFeat : featList){
        		AgreeVarDecl varDecl = new AgreeVarDecl(agreeFeat.lustreString, agreeFeat.varType);
        		String renameStr = agreeRename.rename(agreeFeat.lustreString);
        		switch(agreeFeat.direction){
        		case IN:
        			inputVars.add(varDecl);
        			layout.addElement(category, renameStr, AgreeLayout.SigType.INPUT);
        			break;
        		case OUT:
        			inputVars.add(varDecl);
        			outputVars.add(varDecl);
        			layout.addElement(category, renameStr, AgreeLayout.SigType.OUTPUT);
        		}
        		
        		//addToRenaming(agreeFeat.lustreString, agreeFeat.aadlString);
	   			addToRefMap(agreeFeat.lustreString, agreeFeat.feature);
        	}
        }
        
        //get information about all the features of this components subcomponents 
        for(ComponentInstance subCompInst : compInst.getComponentInstances()){
        	for(FeatureInstance featInst : subCompInst.getFeatureInstances()){
            	List<AgreeFeature> featList = recordFeatures_Helper(thisPrefix + subCompInst.getName() + dotChar,
            			featInst);
            	for(AgreeFeature agreeFeat : featList){
            		AgreeVarDecl varDecl = new AgreeVarDecl(agreeFeat.lustreString, agreeFeat.varType);
            		String renameStr = agreeRename.rename(agreeFeat.lustreString);
            		switch(agreeFeat.direction){
            		case IN:
            			inputVars.add(varDecl);
            			layout.addElement(subCompInst.getName(), renameStr, AgreeLayout.SigType.INPUT);
            			break;
            		case OUT:
            			outputVars.add(varDecl);
            			layout.addElement(subCompInst.getName(), renameStr, AgreeLayout.SigType.OUTPUT);
            		}
            		//addToRenaming(agreeFeat.lustreString, agreeFeat.aadlString);
            		addToRefMap(agreeFeat.lustreString, agreeFeat.feature);
            	}
            	//featInstToAgreeFeatMap.put(featInst, featList);
        	}
        }
	}
    
    private void addToRenamingAll(Map<String, String> renamings) {
		for(Entry<String,String> entry :renamings.entrySet()){
			addToRenaming(entry.getKey(),entry.getValue());
		}
	}
    
    private void addToRenaming(String key, String value){
    	if(varRenaming.containsValue(value)){
    		//TODO: this could be done another way if its to slow
    		for(Entry<String,String> entry : varRenaming.entrySet()){
    			if(entry.getValue().equals(value) && !entry.getKey().equals(key)){
    				throw new AgreeException("There is a name collision with a multiple variables"
    	    				+ ", assumptions, or guarantees named '"+value+"' which will cause "
    	    			    + "issues with reporting results");
    			}
    		}
    	}
    	varRenaming.put(key, value);
    }
    
//    private void setEventPortQueues() {
//
//    	//queueSignalEqualityAssertion
//    	for(String destStr : queueMap.keySet()){
//		   
//		   	List<AgreeQueueElement> queueList = queueMap.get(destStr);
//		   	AgreeQueueElement firstQueue = queueList.get(0);
//		   	long queueSize = firstQueue.queueSize;
//		   	
//		   	if(queueSize < 0){
//		   		throw new AgreeException("The feature '"+firstQueue.aDest+"' must have a queue size specified");
//		   	}
//		   	
//		   	Type type = firstQueue.type;
//		   	int numInputs = queueList.size(); 
//		   	
//	   		Node queueNode = AgreeCalendarUtils.queueNode("__Queue_Node_"+destStr, type, (int)queueSize);
//	   		Node inputMultiplex = AgreeCalendarUtils.queueMultiplexNode("__Queue_Mult_"+destStr, type, numInputs);
//
//	   		nodeDefExpressions.add(queueNode);
//	   		nodeDefExpressions.add(inputMultiplex);
//	   		
//	   		List<Expr> queueMultInputs = new ArrayList<>();
//	   		List<Expr> queueInputs = new ArrayList<>();
//	   		
//	   		Expr queueClockInExpr = new BoolExpr(false);
//		   	for(AgreeQueueElement agreeQueEl : queueList){
//		   		
//		   		//do some sanity checks
//		   		if(firstQueue.destConn != agreeQueEl.destConn){
//		   			throw new AgreeException("Something went wrong with book keeping during queue creation");
//		   		}
//		   		
//		   		String jCat = agreeQueEl.sourCategory == null ? jKindNameTag : jKindNameTag + agreeQueEl.sourCategory+dotChar;
//		   		String aCat = agreeQueEl.sourCategory == null ? aadlNameTag : aadlNameTag + agreeQueEl.sourCategory+".";
//
//		   		
//		   		IdExpr clockInId = new IdExpr(queueInClockPrefix+jCat+agreeQueEl.sourConn.getName());
//		   		AgreeVarDecl clockInVar = 
//		   				new AgreeVarDecl(clockInId.id,
//		   						queueInClockPrefix+aCat+agreeQueEl.sourConn.getName()
//		   						, NamedType.BOOL.toString());
//				
//		   		//if the clock input hasn't been created yet,
//		   		//do reference mapping and add it to the map
//		   		//of input clocks
//		   		if(!inputVars.contains(clockInVar)){
//		   			inputVars.add(clockInVar);
//		   			//addToRenaming(clockInVar.jKindStr, clockInVar.aadlStr);
//		   			addToRefMap(clockInVar.aadlStr, agreeQueEl.sourConn);
//		   			
//		   			String sourCategory = (agreeQueEl.sourCategory == null) ? category : agreeQueEl.sourCategory;
//		   	        layout.addElement(sourCategory, clockInVar.aadlStr, AgreeLayout.SigType.INPUT);
//
//		   			List<IdExpr> clockInIds = queueInClockMap.get(agreeQueEl.sourConn);
//
//		   			if(clockInIds == null){
//		   				clockInIds = new ArrayList<>();
//		   				queueInClockMap.put(agreeQueEl.sourConn, clockInIds);
//		   			}
//		   			
//		   			clockInIds.add(clockInId);
//		   		}
//	            
//		   		queueMultInputs.add(new IdExpr(agreeQueEl.jSour));
//		   		queueMultInputs.add(clockInId);
//		   		queueClockInExpr = new BinaryExpr(queueClockInExpr, BinaryOp.OR, clockInId);
//		   	}
//		   
//		   	//add all the stuff for the queue multiplexer
//		   	NodeCallExpr queueMultCall = new NodeCallExpr(inputMultiplex.id, queueMultInputs);
//		   	IdExpr queueInputId = new IdExpr("__QUEUE_INPUT_"+destStr);
//		   	AgreeVarDecl queueInputVar = 
//		   			new AgreeVarDecl(queueInputId.id, queueInputId.id, type.toString());
//
//		   	internalVars.add(queueInputVar);
//		   	Equation multEq = new Equation(queueInputId, queueMultCall);
//		   	addConnection(multEq);	   	
//		   	
//		   	//add all the stuff for the queuenode call
//		   	String jCat =  firstQueue.destCategory == null ? jKindNameTag : jKindNameTag + firstQueue.destCategory+dotChar;
//	   		String aCat = firstQueue.destCategory == null ? aadlNameTag : aadlNameTag + firstQueue.destCategory+".";
//		   	
//		   	IdExpr clockOutId = new IdExpr(queueOutClockPrefix+jCat+firstQueue.destConn.getName());
//		   	AgreeVarDecl clockOutVar = 
//		   			new AgreeVarDecl(clockOutId.id,
//		   					queueOutClockPrefix+aCat+firstQueue.destConn.getName(),
//		   					NamedType.BOOL.toString());
//
//	   		//if the clock output hasn't been created yet,
//		   	//do reference mapping and add it to the map
//		   	//of output clocks
//		   	if(!inputVars.contains(clockOutVar)){
//		   		inputVars.add(clockOutVar);
//		   		addToRenaming(clockOutVar.jKindStr, clockOutVar.aadlStr);
//		   		addToRefMap(clockOutVar.aadlStr, firstQueue.destConn);
//	   			String destCategory = (firstQueue.destCategory == null) ? category : firstQueue.destCategory;
//		   		layout.addElement(destCategory, clockOutVar.aadlStr, AgreeLayout.SigType.INPUT);
//
//		   		List<IdExpr> clockOutIds = queueInClockMap.get(firstQueue.destConn);
//
//		   		if(clockOutIds == null){
//		   			clockOutIds = new ArrayList<>();
//		   			queueOutClockMap.put(firstQueue.destConn, clockOutIds);
//		   		}
//
//		   		clockOutIds.add(clockOutId);
//
//		   	}
//
//		   	queueInputs.add(queueInputId);
//		   	queueInputs.add(queueClockInExpr);
//		   	queueInputs.add(clockOutId);
//		   	NodeCallExpr queueCall = new NodeCallExpr(queueNode.id, queueInputs);
//		   	
//			IdExpr queueElsId = new IdExpr(queueCountPrefix+destStr);
//		   	AgreeVarDecl queueElsVar = 
//		   			new AgreeVarDecl(queueElsId.id, 
//		   					queueElsId.id, 
//		   					NamedType.INT.toString());
//		   	
//		   	internalVars.add(queueElsVar);
//		   	List<IdExpr> countIds = queueCountMap.get(firstQueue.destConn);
//		   	if(countIds == null){
//		   		countIds = new ArrayList<>();
//		   		queueCountMap.put(firstQueue.destConn, countIds);
//		   	}
//		   	
//		   	countIds.add(queueElsId);
//		   	
//	   		IdExpr singleQueueCountId = new IdExpr(queueCountPrefix+jCat+firstQueue.destConn.getName());
//	   		AgreeVarDecl singleQueueCountVar = 
//	   				new AgreeVarDecl(singleQueueCountId.id,
//	   						queueCountPrefix+aCat+firstQueue.destConn.getName(),
//	   						NamedType.INT.toString());
//
//	   		if(!inputVars.contains(singleQueueCountVar)){
//	   			inputVars.add(singleQueueCountVar);
//	   			addToRenaming(singleQueueCountVar.jKindStr, singleQueueCountVar.aadlStr);
//	   			addToRefMap(singleQueueCountVar.aadlStr, firstQueue.destConn);
//	   			String destCategory = (firstQueue.destCategory == null) ? category : firstQueue.destCategory;
//	   			layout.addElement(destCategory, singleQueueCountVar.aadlStr, AgreeLayout.SigType.INPUT);
//	   			countIds.add(singleQueueCountId);
//	   		}
//	   		
//		   	//finally, call the node
//		   	List<IdExpr> queueLhs = new ArrayList<>();
//		   	queueLhs.add(queueElsId);
//		   	queueLhs.add(new IdExpr(destStr));
//		   	
//		   	Equation queueEq = new Equation(queueLhs, queueCall);
//		   	connExpressions.add(queueEq);
//
//	   	}
//	   
//    }

// ************** CASE STATEMENTS **********************
    
//    public Expr getQueueCountID(String jVarPrefix, String aVarPrefix, NamedElement comp){
//
//    	EventDataPort port = (EventDataPort)comp;
//
//    	//a variable of the same name as this should be created by setEventPortQueues()
//    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
//    	AgreeVarDecl countVar = new AgreeVarDecl(queueCountPrefix+jVarPrefix,
//    			queueCountPrefix+aVarPrefix,
//        		NamedType.INT.toString());
//    	
//    	IdExpr countIdExpr = new IdExpr(countVar.jKindStr);
//
//    	//if we have already made the expression then don't make it again
//        if(inputVars.contains(countVar)){
//        	return countIdExpr;
//        }
//        inputVars.add(countVar);
//        //addToRenaming(countVar.jKindStr,countVar.aadlStr);
//        addToRefMap(countVar.aadlStr, port);
//        layout.addElement(category, agreeRename.rename(countVar.jKindStr), AgreeLayout.SigType.INPUT);
//        
//    	return countIdExpr;
//
//    }
    
//    public Expr getQueueRemoveID(String jVarPrefix, String aVarPrefix, NamedElement comp){
//
//    	EventDataPort port = (EventDataPort)comp;
//
//    	//a variable of the same name as this should be created by setEventPortQueues()
//    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
//    	AgreeVarDecl removeVar = new AgreeVarDecl(queueOutClockPrefix+jVarPrefix,
//    			queueOutClockPrefix+aVarPrefix,
//        		NamedType.BOOL.toString());
//    	
//    	IdExpr removeIdExpr = new IdExpr(removeVar.jKindStr);
//
//    	//if we have already made the expression then don't make it again
//        if(inputVars.contains(removeVar)){
//        	return removeIdExpr;
//        }
//        inputVars.add(removeVar);
//        //addToRenaming(removeVar.jKindStr,removeVar.aadlStr);
//        addToRefMap(removeVar.aadlStr, port);
//        layout.addElement(category, agreeRename.rename(removeVar.jKindStr), AgreeLayout.SigType.INPUT);
//        
//    	return removeIdExpr;
//    }
    

//    public Expr getQueueInsertID(String jVarPrefix, String aVarPrefix, NamedElement comp){
//    	
//    	EventDataPort port = (EventDataPort)comp;
//    	
//    	//a variable of the same name as this should be created by setEventPortQueues()
//    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
//    	AgreeVarDecl insertVar = new AgreeVarDecl(queueInClockPrefix+jVarPrefix,
//    			queueInClockPrefix+aVarPrefix,
//        		NamedType.BOOL.toString());
//    	
//    	IdExpr insertIdExpr = new IdExpr(insertVar.jKindStr);
//
//    	//if we have already made the expression then don't make it again
//        if(inputVars.contains(insertVar)){
//        	return insertIdExpr;
//        }
//        
//        inputVars.add(insertVar);
//        //addToRenaming(insertVar.jKindStr,insertVar.aadlStr);
//        addToRefMap(insertVar.aadlStr, port);
//        layout.addElement(category, agreeRename.rename(insertVar.jKindStr), AgreeLayout.SigType.INPUT);
//    	
//    	return insertIdExpr;
//    }
    
    public Expr getClockID(NamedElement comp){
    	//IdExpr clockId = new IdExpr(clockIDPrefix+comp.getName());
    	
    	
    	//a variable of the same name as this should be created by setEventPortQueues()
    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
    	AgreeVarDecl clockVar = new AgreeVarDecl(clockIDPrefix+comp.getName(),
        		NamedType.BOOL.toString());
    	
    	IdExpr clockId = new IdExpr(clockVar.id);

    	//if we have already made the expression then don't make it again
        if(inputVars.contains(clockVar)){
        	return clockId;
        }
        
        inputVars.add(clockVar);
        //addToRenaming(clockVar.jKindStr,clockVar.aadlStr);
        addToRefMap(clockVar.id, comp);
        layout.addElement(category, agreeRename.rename(clockVar.id), AgreeLayout.SigType.INPUT);
    	
    	return clockId;
    	
    }
    
    
    @Override
    public Expr caseCalenStatement(CalenStatement calen){
    	for(NamedElement namedEl : calen.getEls()){
    		IdExpr clockId = new IdExpr(clockIDPrefix+namedEl.getName());
    		this.calendar.add(clockId);
    	}
        return null;
    }
    
    @Override
    public Expr caseSynchStatement(SynchStatement sync){
    	
    	if(sync instanceof CalenStatement){
    		return null;
    	}
    	
        this.synchrony  = Integer.valueOf(sync.getVal());
        String simVal = sync.getSim();
        
        if(simVal != null){
        	this.simultaneity = !simVal.equals("no_simult");
        }
        
        return null;
    }
    
    @Override
    public Expr caseLiftStatement(LiftStatement lift){
        
        if(ignoreLifts){
            return null;
        }
        
        //if connections have not been set we need to set them
        if(!connectionExpressionsSet){
        	ComponentClassifier compImpl = this.curInst.getComponentClassifier();
        	setConnExprs((ComponentImplementation) compImpl);
        }
        
        NestedDotID nestId = lift.getSubcomp();
        
        Subcomponent subComp = (Subcomponent)nestId.getBase();
        ComponentInstance subCompInst = curInst.findSubcomponentInstance(subComp);
        ComponentImplementation subCompImpl = subComp.getComponentImplementation();
        ComponentType subCompType = subCompImpl.getType();

        AgreeAnnexEmitter subEmitter = new AgreeAnnexEmitter(
                subCompInst, layout, thisPrefix + subComp.getName(),
                topLevelPrefix,
                thisPrefix + subComp.getName() + dotChar, false, true);
        
        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) { 
                subEmitter.doSwitch(annex);
                break;
            }
        }

        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompType, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                subEmitter.doSwitch(annex);
                break;
            }
        }        
        
        AgreeNode agreeNode = subEmitter.getComponentNode();
        
        //don't add duplicate nodes
        for(Node node : agreeNode.nodes){
            boolean inSet = false;
            for(Node subnode : nodeDefExpressions){
                if(node.id.equals(subnode.id)){
                    inSet = true;
                    break;
                }
            }
            if(!inSet){
            	nodeDefExpressions.add(node);
            }
        }
        
        //internalVars.addAll(subEmitter.internalVars);
        for(VarDecl varDec : agreeNode.outputs){
        	AgreeVarDecl agreeVar = new AgreeVarDecl(varDec.id, varDec.type);
        	internalVars.add(agreeVar);
        }
        //internalVars.addAll(agreeNode.outputs);
        for(VarDecl varDec : agreeNode.inputs){
        	AgreeVarDecl agreeVar = new AgreeVarDecl(varDec.id, varDec.type);
        	inputVars.add(agreeVar);
        	outputVars.add(agreeVar);
        }
       //inputVars.addAll(agreeNode.inputs);
        
        for(Expr expr : agreeNode.assertions){
            IdExpr varId = (IdExpr)expr;
            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, "bool");
            assertExpressions.add(varId);
            internalVars.add(agreeVar);
        }    
 
        for(Expr expr : agreeNode.assumptions){
            IdExpr varId = (IdExpr)expr;
            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, "bool");
            assumpExpressions.add(varId);
            internalVars.add(agreeVar);
        }    
 
        for(Expr expr : agreeNode.guarantees){
            IdExpr varId = (IdExpr)expr;
            AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, "bool");
            Equation dumbEq = new Equation(varId, varId);
            guarExpressions.add(dumbEq);
            internalVars.add(agreeVar);
        }    

        List<Expr> nodeInputs = new ArrayList<>();
        for(VarDecl var : agreeNode.inputs){
			nodeInputs.add(new IdExpr(var.id));
        }
        
        List<IdExpr> nodeOutputs = new ArrayList<>();
        for(VarDecl var : agreeNode.outputs){
            nodeOutputs.add(new IdExpr(var.id));
        }

        NodeCallExpr nodeCall = new NodeCallExpr(agreeNode.mainNode.id, nodeInputs);
        Equation nodeEq = new Equation(nodeOutputs, nodeCall);
        
        eqExpressions.add(nodeEq);
        //connExpressions.addAll(subEmitter.connExpressions);
        typeExpressions.addAll(subEmitter.typeExpressions);
        initTypeMap.putAll(subEmitter.initTypeMap);
        
        //addToRenamingAll(subEmitter.varRenaming);
        addAllToRefMap(subEmitter.refMap);
       
        return null;
    }

	@Override
    public Expr caseAgreeContractSubclause(AgreeContractSubclause contract) {

        return doSwitch(contract.getContract());
    }

    @Override
    public Expr caseAgreeContract(AgreeContract contract) {

        for (SpecStatement spec : contract.getSpecs()) {
            doSwitch(spec);
        }

        return null;
    }

    @Override
    public Expr caseAssumeStatement(AssumeStatement state) {

        Expr expr = doSwitch(state.getExpr());
        assumpExpressions.add(expr);
        return expr;
    }

    @Override
    public Expr caseLemmaStatement(LemmaStatement state) {
        Expr expr = doSwitch(state.getExpr());
        String guarStr = state.getStr();
        guarStr = guarStr.replace("\"", "");
        addToRefMap(guarStr, state);
        IdExpr strId = new IdExpr(guarStr);
        Equation eq = new Equation(strId, expr);
        guarExpressions.add(eq);
        return expr;
    }

    @Override
    public Expr caseGuaranteeStatement(GuaranteeStatement state) {
        Expr expr = doSwitch(state.getExpr());
        String guarStr = state.getStr();
        guarStr = guarStr.replace("\"", "");
        addToRefMap(guarStr, state);
        IdExpr strId = new IdExpr(guarStr);
        Equation eq = new Equation(strId, expr);
        guarExpressions.add(eq);
        return expr;
    }

    @Override
    public Expr caseAssertStatement(AssertStatement state) {

        Expr expr = doSwitch(state.getExpr());
        assertExpressions.add(expr);

        return expr;
    }

    @Override
    public Expr casePropertyStatement(PropertyStatement state) {

        Expr expr = doSwitch(state.getExpr());

        AgreeVarDecl varDecl = new AgreeVarDecl(thisPrefix+state.getName(), "bool");

        layout.addElement(category, agreeRename.rename(varDecl.id), AgreeLayout.SigType.OUTPUT);

        //addToRenaming(varDecl.jKindStr, varDecl.aadlStr);
        addToRefMap(varDecl.id, state);
        internalVars.add(varDecl);

        IdExpr id = new IdExpr(varDecl.id);
        Equation eq = new Equation(id, expr);
        propExpressions.add(eq);
        return expr;
        
    }
    
    @Override
    public Expr caseEqStatement(EqStatement state) {

        Expr expr = null;
        if(state.getExpr() != null){
            //this is an explicitly defined variable
            expr = doSwitch(state.getExpr());
        }
        List<IdExpr> varIds = new ArrayList<IdExpr>();

        for (Arg arg : state.getLhs()) {
            String baseName = arg.getName();
            AgreeVarDecl varDecl = new AgreeVarDecl(thisPrefix + baseName,
            		 getRecordTypeName(arg.getType()));

            IdExpr idExpr = new IdExpr(varDecl.id);
            varIds.add(idExpr);

            layout.addElement(category, agreeRename.rename(varDecl.id), AgreeLayout.SigType.OUTPUT);
            addToRefMap(varDecl.id, state);
            
            if(expr != null){
                internalVars.add(varDecl);
            }else{
                outputVars.add(varDecl);
            }
        }

        if(expr != null){
            Equation eq = new Equation(varIds, expr);
            eqExpressions.add(eq);
        }

        return null;
    }

    @Override
    public Expr caseRecordUpdateExpr(RecordUpdateExpr upExpr){
    	EList<NamedElement> args = upExpr.getArgs();
    	EList<com.rockwellcollins.atc.agree.agree.Expr> argExprs = upExpr.getArgExpr();
    	
    	Expr lustreExpr = doSwitch(upExpr.getRecord());
    	for(int i= 0; i < args.size(); i++){
    		com.rockwellcollins.atc.agree.agree.Expr argExpr = argExprs.get(i);
    		NamedElement arg = args.get(i);
    		Expr lustreArgExpr = doSwitch(argExpr);
    		lustreExpr = new jkind.lustre.RecordUpdateExpr(lustreExpr, arg.getName(), lustreArgExpr);
    	}
    	
    	return lustreExpr;
    }
    
    @Override
    public Expr caseRecordExpr(RecordExpr recExpr){
    	
    	EList<NamedElement> agreeArgs = recExpr.getArgs();
    	EList<com.rockwellcollins.atc.agree.agree.Expr> agreeArgExprs = recExpr.getArgExpr();
    	Map<String, Expr> argExprMap = new HashMap<String, Expr>();
    	
    	for(int i = 0; i < agreeArgs.size(); i++){
    		NamedElement agreeArg = agreeArgs.get(i);
    		com.rockwellcollins.atc.agree.agree.Expr agreeExpr = agreeArgExprs.get(i);
    		
    		Expr lustreExpr = doSwitch(agreeExpr);
    		String argName = agreeArg.getName();
    		
    		argExprMap.put(argName, lustreExpr);
    		
    	}
    	
    	NestedDotID recId = recExpr.getRecord();
    	String recName = getRecordTypeName(recId);
    	return new jkind.lustre.RecordExpr(recName, argExprMap);

    }
    
    private String getRecordTypeName(com.rockwellcollins.atc.agree.agree.Type type){
    	if(type instanceof PrimType){
    		return ((PrimType) type).getString();
    	}else{
    		return getRecordTypeName(((RecordType)type).getRecord());
    	}
    }
    
    private String getRecordTypeName(NestedDotID recId){
    	NamedElement finalId = AgreeEmitterUtilities.getFinalNestId(recId);
    	return getRecordTypeName(finalId);
    }
    
    private String getRecordTypeName(NamedElement finalId){

    	if(typeMap.containsKey(finalId)){
    		return typeMap.get(finalId);
    	}
    	recordRecType(finalId);
    	return typeMap.get(finalId);
    }
    
    private void recordRecType(NamedElement el){
    	Map<String, Type> subTypeMap = new HashMap<String, Type>();
    	if(el instanceof ComponentImplementation){
    		ComponentImplementation compImpl = (ComponentImplementation)el;
    		String typeStr = null;
    		if(compImpl.getAllSubcomponents().size() == 0){
    			typeStr = getIDTypeStr(compImpl.getType());
            	typeMap.put(el, typeStr);
            	return;
    		}
    		for(Subcomponent subComp : compImpl.getAllSubcomponents()){
    			ComponentImplementation subCompImpl = subComp.getComponentImplementation();
    			if(subCompImpl == null){
        			ComponentType subCompType = subComp.getComponentType();
        			typeStr = getRecordTypeName(subCompType);
    			}else{
    				typeStr = getRecordTypeName(subCompImpl);
    			}
    			subTypeMap.put(subComp.getName(), new NamedType(typeStr));
    		}
    	}else if(el instanceof RecordDefExpr){
    		RecordDefExpr agreeRecDef = (RecordDefExpr)el;
    		for(Arg arg : agreeRecDef.getArgs()){
    			
    			com.rockwellcollins.atc.agree.agree.Type argType = arg.getType();
    			String typeStr = null;
    			if(argType instanceof PrimType){
    				typeStr = ((PrimType) argType).getString();
    			}else{
    				NestedDotID nestId = ((RecordType) argType).getRecord();
    				NamedElement namedEl = AgreeEmitterUtilities.getFinalNestId(nestId);
    				typeStr = getRecordTypeName(namedEl);
    			}
    			subTypeMap.put(arg.getName(), new NamedType(typeStr));
    		}
    		
    	}else if(el instanceof ComponentType){
    		String typeStr = getIDTypeStr(el);
        	typeMap.put(el, typeStr);
        	return;
    	}
    	String typeStr = getIDTypeStr(el);
    	typeMap.put(el, typeStr);
    	jkind.lustre.RecordType lustreRecord = new jkind.lustre.RecordType(typeStr, subTypeMap);
    	getInitType(lustreRecord);
    	typeExpressions.add(lustreRecord);
    	
    }
    private Expr getInitType(Type type){    	
    	if(type instanceof jkind.lustre.RecordType){
    		jkind.lustre.RecordType lustreRecord = (jkind.lustre.RecordType)type;
    		Expr cachedExpr = initTypeMap.get(lustreRecord.id);
    		if(cachedExpr != null){
    			return cachedExpr;
    		}
    		Map<String, Expr> initFields = new HashMap<String, Expr>();
    		for(Entry<String, Type> field : lustreRecord.fields.entrySet()){
    			Expr fieldInitValue = getInitType(field.getValue());
    			if(fieldInitValue == null){
    				throw new AgreeException("null init type found");
    			}
				initFields.put(field.getKey(), fieldInitValue);
    		}
    		jkind.lustre.RecordExpr lustreExpr = new jkind.lustre.RecordExpr(lustreRecord.id, initFields);
    		initTypeMap.put(lustreRecord.id, lustreExpr);
    		return lustreExpr;
    	}else if(type instanceof NamedType){
    		return initTypeMap.get(((NamedType)type).name);
    		
    	}
    	throw new AgreeException("AGREE does not reason about type"+type.getClass().toString());
    }
    
    private String getIDTypeStr(NamedElement record) {
    	String typeStr = null;
    	EObject container = record.eContainer();
    	
    	if(record instanceof ComponentType){
    		ComponentType type = (ComponentType)record;
    		do {
                String name = type.getQualifiedName();
                switch (name) {
                case "Base_Types::Boolean":
                    return "bool";
                case "Base_Types::Integer":
                case "Base_Types::Unsigned":
                case "Base_Types::Unsigned_32":
                case "Base_Types::Unsigned_16":
                case "Base_Types::Unsigned_8":
                case "Base_Types::Integer_32":
                case "Base_Types::Integer_16":
                case "Base_Types::Integer_8":
                    return "int";
                case "Base_Types::Float":
                    return "real";
                }
                type = (DataType) type.getExtended();

            } while (type != null);
    		log.logWarning("Reference to component type '"
    				+record.getName()+"' is not among the types reasoned about by AGREE");
    		return "bool";
    	}else if(record instanceof ComponentImplementation){
    		typeStr = record.getName();
    	}else{
    		while(!(container instanceof ComponentClassifier) && !(container instanceof AadlPackage)){
    			container = container.eContainer();
    		}
    		if(container instanceof ComponentClassifier){
    			ComponentClassifier compClass = (ComponentClassifier)container;
    			typeStr = compClass.getName() + "__" + record.getName();
    		}else{
    			typeStr = record.getName();
    		}
    	}
    	//get the name of the containing package
    	while(!(container instanceof AadlPackage)){
    		container = container.eContainer();
    	}
    	typeStr = ((AadlPackage)container).getName() + "__" + typeStr;
    	typeStr = typeStr.replace(".", "__");

    	return typeStr;
    }
    

    public Expr caseBinaryExpr(com.rockwellcollins.atc.agree.agree.BinaryExpr expr) {

        Expr leftExpr = doSwitch(expr.getLeft());
        Expr rightExpr = doSwitch(expr.getRight());

        String op = expr.getOp();

        BinaryOp binOp = null;
        switch (op) {
        case "+":
            binOp = BinaryOp.PLUS;
            break;
        case "-":
            binOp = BinaryOp.MINUS;
            break;
        case "*":
            binOp = BinaryOp.MULTIPLY;
            break;
        case "/":
            binOp = BinaryOp.DIVIDE;
            break;
        case "mod":
            binOp = BinaryOp.MODULUS;
            break;
        case "div":
            binOp = BinaryOp.INT_DIVIDE;
            break;
        case "<=>":
        case "=":
            binOp = BinaryOp.EQUAL;
            break;
        case "!=":
        case "<>":
            binOp = BinaryOp.NOTEQUAL;
            break;
        case ">":
            binOp = BinaryOp.GREATER;
            break;
        case "<":
            binOp = BinaryOp.LESS;
            break;
        case ">=":
            binOp = BinaryOp.GREATEREQUAL;
            break;
        case "<=":
            binOp = BinaryOp.LESSEQUAL;
            break;
        case "or":
            binOp = BinaryOp.OR;
            break;
        case "and":
            binOp = BinaryOp.AND;
            break;
        case "xor":
            binOp = BinaryOp.XOR;
            break;
        case "=>":
            binOp = BinaryOp.IMPLIES;
            break;
        case "->":
            binOp = BinaryOp.ARROW;
            break;
        }

        assert (binOp != null);
        BinaryExpr binExpr = new BinaryExpr(leftExpr, binOp, rightExpr);

        return binExpr;
    }

    @Override
    public Expr caseBoolLitExpr(BoolLitExpr expr) {
        return new BoolExpr(expr.getVal().getValue());
    }

    @Override
    public Expr caseFnCallExpr(FnCallExpr expr) {
        NestedDotID dotId = expr.getFn();
        NamedElement namedEl = AgreeEmitterUtilities.getFinalNestId(dotId);
     
        String fnName = getNodeName(namedEl);

        boolean found = false;
        for(Node node : nodeDefExpressions){
            if(node.id.equals(fnName)){
                found = true;
                break;
            }
        }
        
        if(!found){
            NestedDotID fn = expr.getFn();
            doSwitch(AgreeEmitterUtilities.getFinalNestId(fn));
        }
        
     
        List<Expr> argResults = new ArrayList<Expr>();

        for (com.rockwellcollins.atc.agree.agree.Expr argExpr : expr.getArgs()) {
            argResults.add(doSwitch(argExpr));
        }

        NodeCallExpr nodeCall = new NodeCallExpr(fnName, argResults);

        return nodeCall;
    }

    private String getNodeName(NamedElement nodeDef){
    	String nodeName = "";
    	EObject container = nodeDef.eContainer();
    	
    	while(!(container instanceof AadlPackage)){
    		if(container instanceof ComponentClassifier){
    			nodeName = ((ComponentClassifier) container).getName();
    			nodeName = dotChar + nodeName;
    			nodeName = nodeName.replace(".", dotChar);
    		}
    		container = container.eContainer();
    	}
    	nodeName = ((AadlPackage)container).getName() + nodeName + dotChar + nodeDef.getName();
    	
    	return nodeName;
    }
    
    // TODO: ordering nodes/functions in dependency order
    @Override
    public Expr caseNodeDefExpr(NodeDefExpr expr) {
        // System.out.println("Visiting caseNodeDefExpr");

        String nodeName = getNodeName(expr);
        
        for(Node node : nodeDefExpressions){
            if(node.id.equals(nodeName)){
                return null;
            }
        }

        List<VarDecl> inputs = argsToVarDeclList(thisPrefix, expr.getArgs());
        List<VarDecl> outputs = argsToVarDeclList(thisPrefix, expr.getRets());
        NodeBodyExpr body = expr.getNodeBody();
        List<VarDecl> internals = argsToVarDeclList(thisPrefix, body.getLocs());
        List<Equation> eqs = new ArrayList<Equation>();
        List<String> props = new ArrayList<String>();

        String lemmaName = "nodeLemma";
        int lemmaIndex = 0;
        for (NodeStmt stmt : body.getStmts()) {
            if (stmt instanceof NodeLemma) {
                NodeLemma nodeLemma = (NodeLemma) stmt;
                //String propName = ((NodeLemma) stmt).getStr();
                String propName = lemmaName + lemmaIndex++;
                props.add(propName);
                IdExpr eqId = new IdExpr(propName);
                Expr eqExpr = doSwitch(nodeLemma.getExpr());
                Equation eq = new Equation(eqId, eqExpr);
                eqs.add(eq);
                VarDecl lemmaVar = new VarDecl(propName, NamedType.BOOL);
                internals.add(lemmaVar);
            } else if (stmt instanceof NodeEq) {
                eqs.add(nodeEqToEq((NodeEq) stmt));
            }
        }

        nodeDefExpressions.add(new Node(nodeName, inputs, outputs, internals, eqs, props));
        return null;
    }
    
   private List<VarDecl> argsToVarDeclList(String nameTag, EList<Arg> args) {
        List<VarDecl> varList = new ArrayList<VarDecl>();
        for (Arg arg : args) {
            Type type = new NamedType(getRecordTypeName(arg.getType()));
            VarDecl varDecl = new VarDecl(nameTag + arg.getName(), type);
            varList.add(varDecl);
        }

        return varList;
    }
    
    //helper method for the above method
    private Equation nodeEqToEq(NodeEq nodeEq) {
        Expr expr = doSwitch(nodeEq.getExpr());
        List<IdExpr> ids = new ArrayList<IdExpr>();
        for (Arg arg : nodeEq.getLhs()) {
            ids.add(new IdExpr(thisPrefix + arg.getName()));
        }
        Equation eq = new Equation(ids, expr);
        return eq;
    }

    @Override
    public Expr caseFnDefExpr(FnDefExpr expr) {

        String nodeName = getNodeName(expr);

        for(Node node : nodeDefExpressions){
            if(node.id.equals(nodeName)){
                return null;
            }
        }

        List<VarDecl> inputs = argsToVarDeclList(thisPrefix, expr.getArgs());
        Expr bodyExpr = doSwitch(expr.getExpr());

        Type outType = new NamedType(getRecordTypeName(expr.getType()));
        VarDecl outVar = new VarDecl("_outvar", outType);
        List<VarDecl> outputs = Collections.singletonList(outVar);
        Equation eq = new Equation(new IdExpr("_outvar"), bodyExpr);
        List<Equation> eqs = Collections.singletonList(eq);

        Node node = new Node(nodeName, inputs, outputs, Collections.<VarDecl> emptyList(), eqs);

        nodeDefExpressions.add(node);

        return null;
    }

    // TODO: place node definition here.

    @Override
    public Expr caseGetPropertyExpr(GetPropertyExpr expr) {

        NamedElement propName = expr.getProp();
        NamedElement compName = namedElFromId(expr.getComponent());
        
        Property prop = (Property) propName;

        PropertyExpression propVal = AgreeEmitterUtilities.getPropExpression(compName, prop);

        if(propVal == null){
            throw new AgreeException("Could not locate property value '"+
                    prop.getFullName()+"' in component '"+compName.getName()+"'.  Is it possible "
                    + "that a 'this' statement is used in a context in which it wasn't supposed to?");
        }
        Expr res = null;
        if (propVal != null) {
            if (propVal instanceof StringLiteral) {
                // StringLiteral value = (StringLiteral) propVal;
                // nodeStr += value.getValue() + ")";
                throw new AgreeException("Property value for '" + prop.getFullName()
                        + "' cannot be of string type");
            } else if (propVal instanceof NamedValue) {
                // NamedValue namedVal = (NamedValue) propVal;
                // AbstractNamedValue absVal = namedVal.getNamedValue();
                // assert (absVal instanceof EnumerationLiteral);
                // EnumerationLiteral enVal = (EnumerationLiteral) absVal;
                throw new AgreeException("Property value for '" + prop.getFullName()
                        + "' cannot be of enumeration type");
            } else if (propVal instanceof BooleanLiteral) {
                BooleanLiteral value = (BooleanLiteral) propVal;
                res = new BoolExpr(value.getValue());
            } else if (propVal instanceof IntegerLiteral) {
                IntegerLiteral value = (IntegerLiteral) propVal;
                res = new IntExpr(BigInteger.valueOf((long) value.getScaledValue()));
            } else {
                assert (propVal instanceof RealLiteral);
                RealLiteral value = (RealLiteral) propVal;
                res = new RealExpr(BigDecimal.valueOf(value.getValue()));
            }
        }
        assert (res != null);

        return res;
    }
    
    //helper method for above
    private NamedElement namedElFromId(EObject obj) {
        if (obj instanceof NestedDotID) {
            return AgreeEmitterUtilities.getFinalNestId((NestedDotID) obj);
        } else {
            assert (obj instanceof ThisExpr);
           
            ThisExpr thisExpr = (ThisExpr)obj;
            
            ComponentInstance compInst = curInst;
            NestedDotID nestId = thisExpr.getSubThis();
            
            while(nestId != null){
                NamedElement base = nestId.getBase();
                
                if(base instanceof Subcomponent){
                    compInst = compInst.findSubcomponentInstance((Subcomponent)base);
                    nestId = nestId.getSub();
                }else if (base instanceof FeatureGroup){
                    assert(base instanceof FeatureGroup);
                    FeatureInstance featInst = compInst.findFeatureInstance((Feature)base);
                    
                    while(nestId.getSub() != null){
                    	nestId = nestId.getSub();
                    	assert(nestId.getBase() instanceof Feature);
                    	Feature subFeat = (Feature)nestId.getBase();
                    	FeatureInstance eqFeatInst = null;
                    	for(FeatureInstance subFeatInst : featInst.getFeatureInstances()){
                    		if(subFeatInst.getFeature().equals(subFeat)){
                    			eqFeatInst = subFeatInst;
                    			break;
                    		}
                    	}
                    	featInst = eqFeatInst;
                    }
                    
                    return featInst;
                }else{
                	assert(base instanceof DataPort);
                	return compInst.findFeatureInstance((DataPort)base);
                }
                
            }
            return compInst;
        }
    }

    @Override
    public Expr caseThisExpr(ThisExpr expr) {
        assert (false);
        return null;
        // return new NamedElementExpr(curComp);
    }

    @Override
    public Expr caseIfThenElseExpr(com.rockwellcollins.atc.agree.agree.IfThenElseExpr expr) {
        Expr condExpr = doSwitch(expr.getA());
        Expr thenExpr = doSwitch(expr.getB());
        Expr elseExpr = doSwitch(expr.getC());

        Expr result = new IfThenElseExpr(condExpr, thenExpr, elseExpr);

        return result;
    }

    @Override
    public Expr caseIntLitExpr(IntLitExpr expr) {
        return new IntExpr(BigInteger.valueOf(Integer.parseInt(expr.getVal())));
    }

    @Override
    public Expr caseNestedDotID(NestedDotID id) {

        String jKindVar = "";
        String aadlVar = "";
        NamedElement base = id.getBase();
        
        while (id.getSub() != null &&
        		(base instanceof FeatureGroup
        		|| base instanceof AadlPackage
        		|| base instanceof Subcomponent)) {
            jKindVar += base.getName() + dotChar;
            aadlVar += base.getName() + ".";
            id = id.getSub();
            base = id.getBase();
        }

        NamedElement namedEl = id.getBase();
        
        String tag = id.getTag();
        if(tag != null){
        	
        	String jVarPrefix = this.thisPrefix + jKindVar + namedEl.getName();
        	
        	switch(tag){
        	case "_CLK":
        		return getClockID(namedEl);
        	case "_COUNT":
        		//return getQueueCountID(jVarPrefix, aVarPrefix, namedEl);
        	case "_INSERT":
        		//return getQueueInsertID(jVarPrefix, aVarPrefix, namedEl);
        	case "_REMOVE":
        		//return getQueueRemoveID(jVarPrefix, aVarPrefix, namedEl);
        	default:
        		throw new AgreeException("use of uknown tag: '"+tag+"' in expression: '"+aadlVar+tag+"'");
        	}
        }
        

        //special case for constants
        if(namedEl instanceof ConstStatement){
            return doSwitch(((ConstStatement)namedEl).getExpr());
        }
        
        jKindVar = thisPrefix + jKindVar + namedEl.getName();
    	Expr result = new IdExpr(jKindVar);
    	//this is a record accessrecord
    	while(id.getSub() != null){
    		id = id.getSub();
    		namedEl = id.getBase();
    		result = new RecordAccessExpr(result, namedEl.getName());
    	}


        return result;
    }

    @Override
    public Expr casePreExpr(PreExpr expr) {
        Expr res = doSwitch(expr.getExpr());

        return new UnaryExpr(UnaryOp.PRE, res);
    }

    @Override
    public Expr casePrevExpr(PrevExpr expr) {
        Expr delayExpr = doSwitch(expr.getDelay());
        Expr initExpr = doSwitch(expr.getInit());

        Expr preExpr = new UnaryExpr(UnaryOp.PRE, delayExpr);

        Expr res = new BinaryExpr(initExpr, BinaryOp.ARROW, preExpr);

        return res;
    }

    @Override
    public Expr caseRealLitExpr(RealLitExpr expr) {
        return new RealExpr(BigDecimal.valueOf(Double.parseDouble(expr.getVal())));
    }

    @Override
    public Expr caseUnaryExpr(com.rockwellcollins.atc.agree.agree.UnaryExpr expr) {
        Expr subExpr = doSwitch(expr.getExpr());

        Expr res = null;
        switch (expr.getOp()) {
        case "-":
            res = new UnaryExpr(UnaryOp.NEGATIVE, subExpr);
            break;
        case "not":
            res = new UnaryExpr(UnaryOp.NOT, subExpr);
            break;
        default:
            assert (false);
        }

        return res;
    }
    
    //********* end case statements ************//
    
    //********* begin lustre generation *******//

    // fills the connExpressions lists with expressions
    // that equate variables that are connected to one another
    private void setConnExprs(ComponentImplementation compImpl) {

    	connectionExpressionsSet  = true;
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
            
            makeConnectionExpressions(absConnSour, absConnDest); 
        }
        
    }
    
    
    private void makeConnectionExpressions(
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
        	sourInst = curInst;
        }else if(sourContext instanceof Subcomponent){
        	sourInst = curInst.findSubcomponentInstance((Subcomponent)sourContext);
        }else{
        	sourBaseFeatInst = curInst.findFeatureInstance((FeatureGroup)sourContext);
        }

        ComponentInstance destInst = null;
        FeatureInstance destBaseFeatInst = null;
        if(destContext == null){
        	destInst = curInst;
        }else if(destContext instanceof Subcomponent){
        	destInst = curInst.findSubcomponentInstance((Subcomponent)destContext);
        }else{
        	destBaseFeatInst = curInst.findFeatureInstance((FeatureGroup)destContext);
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
        	assert(agreeDestConn.varType == agreeSourConn.varType);
        	
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
        	
        	if(agreeDestConn.connType == AgreeFeature.ConnType.QUEUE){
        		
        		String sourInstName;
        		if(sourInst == null){
        			sourInstName = sourBaseFeatInst.getName();
        		}else if(sourInst == curInst){
        			sourInstName = null;
        		}else{
        			sourInstName = sourInst.getName();
        		}
        		
        		String destInstName;
        		if(destInst == null){
        			destInstName = destBaseFeatInst.getName();
        		}else if(destInst == curInst){
        			destInstName = null;
        		}else{
        			destInstName = destInst.getName();
        		}
        		
        		AgreeQueueElement agreeQueueElem = new AgreeQueueElement(rhsLustreName, 
        				rhsAadlName, 
        				lhsLustreName, 
        				lhsAadlName, 
        				new NamedType(agreeDestConn.varType),
        				(EventDataPort)agreeSourConn.feature,
        				(EventDataPort)agreeDestConn.feature,
        				sourInstName, 
        				destInstName, 
        				agreeDestConn.queueSize);
        		
        		if(queueMap.containsKey(lhsLustreName)){
        			List<AgreeQueueElement> queues = queueMap.get(lhsLustreName);
        			queues.add(agreeQueueElem);
        		}else{
        			List<AgreeQueueElement> queues = new ArrayList<AgreeQueueElement>();
        			queues.add(agreeQueueElem);
        			queueMap.put(lhsLustreName, queues);
        		}
        	}else{
        		Equation connEq = new Equation(new IdExpr(lhsLustreName), new IdExpr(rhsLustreName));
        		addConnection(connEq);;
        	}
        	//add left hand side expressions as internal variables
    		//AgreeVarDecl agreeVar = new AgreeVarDecl(lhsLustreName, agreeSourConn.varType);
    		//internalVars.add(agreeVar);
        }
	}
    
    private List<AgreeFeature> recordFeatures_Helper(String jPrefix, FeatureInstance featInst){
    	
    	List<AgreeFeature> agreeConns = new ArrayList<>();    	
    	
    	switch(featInst.getCategory()){
    	case FEATURE_GROUP:
    		recordFeatureGroup(featInst, agreeConns);
    		break;
    	case DATA_PORT:
    	case EVENT_DATA_PORT:
    		recordPort(featInst, agreeConns);
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


	private void recordFeatureGroup(FeatureInstance featInst,
			List<AgreeFeature> agreeConns) {
		for(FeatureInstance subFeatInst : featInst.getFeatureInstances()){
			agreeConns.addAll(recordFeatures_Helper(featInst.getName()+dotChar, subFeatInst));
		}
		

	}

	private void recordPort(FeatureInstance featInst,
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
//		if(featInst.getCategory() == FeatureCategory.EVENT_DATA_PORT){
//			connType = AgreeFeature.ConnType.QUEUE;
//		    Property queueSizeProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
//		            OsateResourceUtil.getResourceSet(), "Communication_Properties::Queue_Size");
//		    try{
//		    	queueSize = PropertyUtils.getIntegerValue(featInst, queueSizeProp);
//		    }catch(PropertyDoesNotApplyToHolderException e){}
//		    
//		}else{
			connType = AgreeFeature.ConnType.IMMEDIATE;
//		}
		

		AgreeFeature agreeConn = new AgreeFeature();
		agreeConn.feature = featInst.getFeature();
		agreeConn.varType = getRecordTypeName(dataClass); 
		//			agreeConn.lustreString = (var.jKindStr == "") ? featInst.getName() : featInst.getName() + dotChar + var.jKindStr;
		//			agreeConn.aadlString = (var.aadlStr == "") ? featInst.getName() : featInst.getName() + "." + var.aadlStr;
		agreeConn.lustreString = featInst.getName();
		agreeConn.connType = connType;
		agreeConn.direction = direction; 
		agreeConn.queueSize = queueSize;
		agreeConns.add(agreeConn);

	}

    public AgreeNode getComponentNode(){
        
        if(agreeNode != null){
            return agreeNode;
        }
        
        List<Equation> eqs = new ArrayList<Equation>();
        Set<VarDecl> inputs = new HashSet<VarDecl>();
        Set<VarDecl> outputs = new HashSet<VarDecl>();

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(this.nodeDefExpressions);
        eqs.addAll(this.constExpressions);
        eqs.addAll(this.eqExpressions);
        eqs.addAll(this.propExpressions);
        //eqs.addAll(this.connExpressions);

        inputs.addAll(inputVars);
        inputs.addAll(outputVars);
        outputs.addAll(internalVars);
        outputs.removeAll(inputs);
        
        //create equations for assumptions, assertions, and guarantees
        List<Expr> guarVars = new ArrayList<>();
        int i = 0;
        for(Equation eq : this.guarExpressions){
            IdExpr id = new IdExpr("__GUAR_"+category+"_"+i++);
            VarDecl guarVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(guarVar);
            guarVars.add(id);
            eqs.add(new Equation(id, eq.expr));
        }
        
        List<Expr> asserVars = new ArrayList<>();
        i = 0;
        for(Expr expr : this.assertExpressions){
            IdExpr id = new IdExpr("__ASSER_"+category+"_"+i++);
            VarDecl asserVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(asserVar);
            asserVars.add(id);
            eqs.add(new Equation(id, expr));
        }
        
        List<Expr> assumVars = new ArrayList<>();
        i = 0;
        for(Expr expr : this.assumpExpressions){
            IdExpr id = new IdExpr("__ASSUM_"+category+"_"+i++);
            VarDecl assumVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(assumVar);
            assumVars.add(id);
            eqs.add(new Equation(id, expr));
        }

        ArrayList<VarDecl> inputList = new ArrayList<>(inputs);
        ArrayList<VarDecl> outputList = new ArrayList<>(outputs);
        
        Node node = new Node("__NODE_"+this.category, inputList, outputList, Collections.EMPTY_LIST, eqs);
        AgreeVarDecl clockVar = new AgreeVarDecl(clockIDPrefix+this.curComp.getName(), NamedType.BOOL);
        
        List<Node> agreeNodes = new ArrayList<>(this.nodeDefExpressions);
        agreeNodes.add(node);
        
        agreeNode = new AgreeNode(inputList, outputList, assumVars,
                asserVars, guarVars, agreeNodes, node, clockVar);
        
        return agreeNode;
    }
    
    public Program getLustreWithCondacts(List<AgreeAnnexEmitter> subEmitters){
        // first print out the functions which will be
        // other nodes

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

        nodeSet.addAll(this.nodeDefExpressions);
        eqs.addAll(this.constExpressions);
        eqs.addAll(this.eqExpressions);
        eqs.addAll(this.propExpressions);
        eqs.addAll(this.connExpressions);
        types.addAll(this.typeExpressions);
        
        Set<VarDecl> agreeInputVars = new HashSet<>();
        Set<VarDecl> agreeInternalVars = new HashSet<>();
        
        agreeInputVars.addAll(this.inputVars);
        agreeInputVars.addAll(this.outputVars);
        agreeInternalVars.addAll(this.internalVars);

        addSubEmitterVars(subEmitters, eqs, inputs, clocks, nodeSet, types,
				agreeInputVars, agreeInternalVars);

        //warn about combinational cycles
        List<Equation> cycleEqs = new ArrayList<>(eqs);
        for(AgreeAnnexEmitter subEmitter : subEmitters){
        	cycleEqs.addAll(subEmitter.constExpressions);
        	cycleEqs.addAll(subEmitter.eqExpressions);
        	cycleEqs.addAll(subEmitter.propExpressions);
        	cycleEqs.addAll(subEmitter.connExpressions);
        }
        AgreeEmitterUtilities.logCycleWarning(cycleEqs, log, agreeRename, false);

        
        agreeInputVars.removeAll(agreeInternalVars);
        
        inputs.addAll(agreeInputVars);
        internals.addAll(agreeInternalVars);

        IdExpr totalCompHistId = new IdExpr("_TOTAL_COMP_HIST");
        IdExpr sysAssumpHistId = new IdExpr("_SYSTEM_ASSUMP_HIST");

        internals.add(new VarDecl(totalCompHistId.id, new NamedType("bool")));
        internals.add(new VarDecl(sysAssumpHistId.id, new NamedType("bool")));

        // total component history
        Expr totalCompHist = new BoolExpr(true);

        for(AgreeAnnexEmitter subEmitter : subEmitters){
            totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, AgreeEmitterUtilities.getLustreContract(subEmitter));
        }

        Expr assumeAsserts = AgreeEmitterUtilities.getLustreAssumptionsAndAssertions(this);
        totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, assumeAsserts);
        
        eqs.add(AgreeEmitterUtilities.getLustreHistory(totalCompHist, totalCompHistId));

        // system assumptions
        Expr sysAssumpHist = AgreeEmitterUtilities.getLustreAssumptionsAndAssertions(this);
        eqs.add(AgreeEmitterUtilities.getLustreHistory(sysAssumpHist, sysAssumpHistId));
        
        //make the closure map for proving assumptions
        Map<Subcomponent, Set<Subcomponent>> closureMap = new HashMap<>();
        for(AgreeAnnexEmitter subEmitter : subEmitters){
            Set<Subcomponent> outputClosure = new HashSet<Subcomponent>();
            outputClosure.add(subEmitter.curComp);
            ComponentImplementation compImpl = (ComponentImplementation) curInst.getComponentClassifier();
            AgreeEmitterUtilities.getOutputClosure(compImpl.getAllConnections(), outputClosure);
            closureMap.put(subEmitter.curComp, outputClosure);
        }
        
        //make a counter for checking finite consistency
        IdExpr countId = addConsistencyCounter(eqs, internals);
        
        // get the individual history variables and create assumption properties
        addSubcomponentAssumptions(subEmitters, eqs, internals, properties,
				totalCompHist, sysAssumpHistId, closureMap);
        
        
        //add a property that is true if the contract is a contradiction
        addSubcomponentConsistency(subEmitters, eqs, internals, properties, countId);
        
        // create individual properties for guarantees
        int i = 0;
        for (Equation guar : guarExpressions) {
            String guarName = guar.lhs.get(0).id;
            IdExpr sysGuaranteesId = new IdExpr(sysGuarTag + i);
            internals.add(new VarDecl(sysGuaranteesId.id, new NamedType("bool")));

            Expr totalSysGuarExpr = new BinaryExpr(sysAssumpHistId, BinaryOp.AND, totalCompHistId);
//            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.AND, allClksTickedExpr);
            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.IMPLIES, guar.expr);

            Equation finalGuar = new Equation(sysGuaranteesId, totalSysGuarExpr);
            eqs.add(finalGuar);
            properties.add(sysGuaranteesId.id);
            guarProps.add(sysGuaranteesId.id);
            addToRenaming(sysGuaranteesId.id, guarName);
            layout.addElement(category, "Component Guarantee " + i++, AgreeLayout.SigType.OUTPUT);
        }
        
        //check for contradiction in total component history
        addTotalCompHistoryConsist(eqs, internals, properties, totalCompHistId,
				countId);
        
        //check to see if this specific components contract makes a contradiction
        addThisContractConsist(eqs, internals, properties,
				countId);

        //create the assertions for the clocks
        List<Expr> assertions = getClockAssertions(clocks, nodeSet);
        
        //assert equivalence between queue clocks and such
        Expr queueClockVarEquivAssertion = getQueueClockCountVarEquivAssertion();
        assertions.add(queueClockVarEquivAssertion);
        
        //assert that inserts and removes don't take place while a component isn't being clocked
        Expr queueInsertRemoveAtomicAssertion = getQueueInsertRemoveAtomicAssertion(subEmitters);
        assertions.add(queueInsertRemoveAtomicAssertion);
        
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


	private List<Expr> getClockAssertions(List<Expr> clocks, List<Node> nodeSet) {
		
		if(clocks.size() == 0){
			//just return a blank list if there are no clocks
			return new ArrayList<>();
		}
		
		Expr clockAssertion;
        if(this.synchrony > 0){
            Node dfaNode = AgreeCalendarUtils.getDFANode("__dfa_node_"+this.category, this.synchrony);
            Node calNode = AgreeCalendarUtils.getCalendarNode("__calendar_node_"+this.category, clocks.size());
            nodeSet.add(dfaNode);
            nodeSet.add(calNode);

            clockAssertion = new NodeCallExpr(calNode.id, clocks);
            
            //don't let multiple clocks tick together
            if(!this.simultaneity){
            	Expr onlyOneTick = AgreeCalendarUtils.getSingleTick(clocks);
            	clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, onlyOneTick);
            }
            
        }else if(this.calendar.size() > 0){
        	
        	Node calNode = AgreeCalendarUtils.getExplicitCalendarNode("__calendar_node_"+this.category, calendar, clocks);
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


	private void addTotalCompHistoryConsist(List<Equation> eqs,
			List<VarDecl> internals, List<String> properties,
			IdExpr totalCompHistId, IdExpr countId) {
		IdExpr notTotalCompHistId = new IdExpr("_TOTAL_COMP_FINITE_CONSIST");
        Expr finiteConsist = AgreeEmitterUtilities.getFinteConsistancy(totalCompHistId, countId, consistUnrollDepth);
        Equation contrConsistEq = new Equation(notTotalCompHistId, finiteConsist);
        
        internals.add(new VarDecl(notTotalCompHistId.id, new NamedType("bool")));
        eqs.add(contrConsistEq);
        
        properties.add(notTotalCompHistId.id);
        consistProps.add(notTotalCompHistId.id);
        //reversePropStatus.add(true);
        addToRenaming(notTotalCompHistId.id, "Subcomponent Composition Consistency");
        //layout.addElement("Top", "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);
        layout.addElement(category, "Subcoponent Composition Consistency", AgreeLayout.SigType.OUTPUT);
	}
	
	private void addThisContractConsist(List<Equation> eqs,
			List<VarDecl> internals, List<String> properties, IdExpr countId) {
		IdExpr contrHistId = new IdExpr("_THIS_CONTRACT_HIST");
		
		Expr guars = new BoolExpr(true);
		for(Equation guarEq : guarExpressions){
			guars = new BinaryExpr(guars, BinaryOp.AND, guarEq.expr);
		}
		
		Expr assumps = new BoolExpr(true);
		for(Expr assump : assumpExpressions){
			assumps = new BinaryExpr(assumps, BinaryOp.AND, assump);
		}
		
		Equation contrHistEq = AgreeEmitterUtilities.getLustreHistory(new BinaryExpr(assumps, BinaryOp.AND, guars), contrHistId);
		eqs.add(contrHistEq);
		
        Expr finiteConsist = AgreeEmitterUtilities.getFinteConsistancy(contrHistId, countId, consistUnrollDepth);
        IdExpr contrConsistId = new IdExpr("_THIS_CONTRACT_CONSIST");
        
		Equation contrConsistEq = new Equation(contrConsistId, finiteConsist);
        
        internals.add(new VarDecl(contrConsistId.id, new NamedType("bool")));
        internals.add(new VarDecl(contrHistId.id, new NamedType("bool")));
        eqs.add(contrConsistEq);
        
        properties.add(contrConsistId.id);
        consistProps.add(contrConsistId.id);
        //reversePropStatus.add(true);
        addToRenaming(contrConsistId.id, "This Contract Consistency");
        //layout.addElement("Top", "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);
        layout.addElement(category, "This Contract Consistency", AgreeLayout.SigType.OUTPUT);
	}


	private IdExpr addConsistencyCounter(List<Equation> eqs,
			List<VarDecl> internals) {
		IdExpr countId = new IdExpr("__CONSIST_COUNTER");
        internals.add(new VarDecl(countId.id, new NamedType("int")));
        
        Expr countPre = new BinaryExpr(new UnaryExpr(UnaryOp.PRE, countId), BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        countPre = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, countPre);
        Equation contEq = new Equation(countId, countPre);
        eqs.add(contEq);
		return countId;
	}


	private void addSubEmitterVars(List<AgreeAnnexEmitter> subEmitters,
			List<Equation> eqs, List<VarDecl> inputs, List<Expr> clocks,
			List<Node> nodeSet, Set<jkind.lustre.RecordType> types, Set<VarDecl> agreeInputVars,
			Set<VarDecl> agreeInternalVars) {
		for(AgreeAnnexEmitter subEmitter : subEmitters){
            
            AgreeNode agreeNode = subEmitter.getComponentNode();
            
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
            
            if(!this.inputVars.contains(agreeNode.clockVar)){ 
            	inputs.add(agreeNode.clockVar);
            	//make it so the clock is visible in the counter example
            	//addToRenaming(clockExpr.id, clockExpr.id);
            	addToRefMap(clockExpr.id, subEmitter.curComp);
            	layout.addElement(subEmitter.category, agreeRename.rename(clockExpr.id), SigType.INPUT);
            }
            
            clocks.add(clockExpr);
            
            agreeInputVars.addAll(subEmitter.inputVars);
            agreeInternalVars.addAll(subEmitter.internalVars); 
            types.addAll(subEmitter.typeExpressions);
            //also add new assumption, assertion, and guarantee vars
            for(Expr expr : agreeNode.assertions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, "bool");
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.assumptions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, "bool");
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.guarantees){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl(varId.id, "bool");
                agreeInternalVars.add(agreeVar);
            }
            
            //TODO: set different initial values for outputs
            for(VarDecl var : agreeNode.outputs){
            	NamedType type = (NamedType)var.type;
            	Expr initVal = initTypeMap.get(type.name);
				initOutputs.add(initVal);
            	nodeOutputs.add(new IdExpr(var.id));
            }

            for(VarDecl var : agreeNode.inputs){
                nodeInputs.add(new IdExpr(var.id));
            }

            NodeCallExpr nodeCall = new NodeCallExpr(agreeNode.mainNode.id, nodeInputs);
            
            CondactExpr condact = new CondactExpr(clockExpr, nodeCall, initOutputs);
            Equation condactEq = new Equation(nodeOutputs, condact);
            eqs.add(condactEq);
            
            addToRenamingAll(subEmitter.varRenaming);
            addAllToRefMap(subEmitter.refMap);
            
            //create the hold equations for the subcomponent outputs
            for(AgreeVarDecl varDec : subEmitter.outputVars){
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


	private void addSubcomponentAssumptions(List<AgreeAnnexEmitter> subEmitters,
			List<Equation> eqs, List<VarDecl> internals,
			List<String> properties, Expr totalCompHist, Expr sysAssumpHist, Map<Subcomponent, Set<Subcomponent>> closureMap) {
		for(AgreeAnnexEmitter subEmitter : subEmitters){

            Expr higherContracts = new BoolExpr(true);
            Set<Subcomponent> closureSubs = closureMap.get(subEmitter.curComp);
            for(AgreeAnnexEmitter closureEmitter : subEmitters){
                if(closureSubs.contains(closureEmitter.curComp)){
                    continue;
                }
                higherContracts = new BinaryExpr(higherContracts, BinaryOp.AND,
                		AgreeEmitterUtilities.getLustreContract(closureEmitter));
            }

            Expr contrAssumps = AgreeEmitterUtilities.getLustreAssumptions(subEmitter);

            IdExpr compId = new IdExpr("_Hist_" + subEmitter.category);
            internals.add(new VarDecl(compId.id, new NamedType("bool")));

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
            assumProps.add(compId.id);
            String propertyName = subEmitter.category + " Assumptions";
            addToRenaming(compId.id, propertyName);
            //layout.addElement("Top", propertyName, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, propertyName, AgreeLayout.SigType.OUTPUT);
        }
	}

	private void addSubcomponentConsistency(List<AgreeAnnexEmitter> subEmitters, List<Equation> eqs,
			List<VarDecl> internals, List<String> properties, IdExpr countId) {
		
		for(AgreeAnnexEmitter subEmitter : subEmitters){

			IdExpr contrHistId = new IdExpr("_CONTR_HIST_" + subEmitter.category);
			IdExpr consistId = new IdExpr("_NULL_CONTR_HIST_" + subEmitter.category);

			Expr contExpr = AgreeEmitterUtilities.getLustreContract(subEmitter);
			Equation contHist = AgreeEmitterUtilities.getLustreHistory(contExpr, contrHistId);
			Expr finiteConsist = AgreeEmitterUtilities.getFinteConsistancy(contrHistId, countId, consistUnrollDepth);
			Equation contrConsistEq = new Equation(consistId, finiteConsist);
			eqs.add(contrConsistEq);
			eqs.add(contHist);
			internals.add(new VarDecl(contrHistId.id, new NamedType("bool")));
			internals.add(new VarDecl(consistId.id, new NamedType("bool")));

			properties.add(consistId.id);
			consistProps.add(consistId.id);
			//reversePropStatus.add(true);
			String contractName = subEmitter.category + " Consistent";
			addToRenaming(consistId.id, contractName);
			//layout.addElement("Top", contractName, AgreeLayout.SigType.OUTPUT);
			layout.addElement(category, contractName, AgreeLayout.SigType.OUTPUT);
		}
	}
    
    
    private Expr getQueueInsertRemoveAtomicAssertion(List<AgreeAnnexEmitter> subEmitters){
    	
    	Expr assertExpr = new BoolExpr(true);
    	for(AgreeAnnexEmitter subEmitter : subEmitters){
    		Subcomponent subComp = subEmitter.curComp;
    		IdExpr clockId = new IdExpr(clockIDPrefix+subComp.getName());
    		Expr notClock = new UnaryExpr(UnaryOp.NOT, clockId);
    		
    		for(Feature feat : subComp.getComponentType().getAllFeatures()){
    			if(feat instanceof EventDataPort){
    				EventDataPort eventPort = (EventDataPort)feat;
    				List<IdExpr> inClocks = queueInClockMap.get(eventPort);
    				if(inClocks != null){
    					for(IdExpr id : inClocks){
    						Expr notId = new UnaryExpr(UnaryOp.NOT, id);
    						Expr impExpr = new BinaryExpr(notClock, BinaryOp.IMPLIES, notId);
    						assertExpr = new BinaryExpr(assertExpr, BinaryOp.AND, impExpr);
    					}
    				}
    				List<IdExpr> outClocks = queueOutClockMap.get(eventPort);
    				if(outClocks != null){
    					for(IdExpr id : outClocks){
    						Expr notId = new UnaryExpr(UnaryOp.NOT, id);
    						Expr impExpr = new BinaryExpr(notClock, BinaryOp.IMPLIES, notId);
    						assertExpr = new BinaryExpr(assertExpr, BinaryOp.AND, impExpr);
    					}
    				}
    			}
    		}
    	}
    	
    	return assertExpr;
    }
    
    
    private Expr getQueueClockCountVarEquivAssertion(){

    	Expr andEquivExpr = new BoolExpr(true);
    	
    	for(Entry<EventDataPort, List<IdExpr>> entries : queueInClockMap.entrySet()){
    		List<IdExpr> Ids = entries.getValue();
    		Expr prevEquivExpr = Ids.get(0);
    		
    		for(IdExpr id : Ids){
    			Expr eqExpr = new BinaryExpr(prevEquivExpr, BinaryOp.EQUAL, id);
    			andEquivExpr = new BinaryExpr(andEquivExpr, BinaryOp.AND, eqExpr);
    			prevEquivExpr = id;
    		}
    	}
    	
    	for(Entry<EventDataPort, List<IdExpr>> entries : queueOutClockMap.entrySet()){
    		List<IdExpr> Ids = entries.getValue();
    		Expr prevEquivExpr = Ids.get(0);
    		
    		for(IdExpr id : Ids){
    			Expr eqExpr = new BinaryExpr(prevEquivExpr, BinaryOp.EQUAL, id);
    			andEquivExpr = new BinaryExpr(andEquivExpr, BinaryOp.AND, eqExpr);
    			prevEquivExpr = id;
    		}
    	}
    	
    	for(Entry<EventDataPort, List<IdExpr>> entries : queueCountMap.entrySet()){
    		List<IdExpr> Ids = entries.getValue();
    		Expr prevEquivExpr = Ids.get(0);
    		
    		for(IdExpr id : Ids){
    			Expr eqExpr = new BinaryExpr(prevEquivExpr, BinaryOp.EQUAL, id);
    			andEquivExpr = new BinaryExpr(andEquivExpr, BinaryOp.AND, eqExpr);
    			prevEquivExpr = id;
    		}
    	}

    	return andEquivExpr;
    }
    
    private void addConnection(Equation connEq){
    	
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
    	assertExpressions.add(new BinaryExpr(connEq.lhs.get(0), BinaryOp.EQUAL, connEq.expr));
    	
    }
        
}
