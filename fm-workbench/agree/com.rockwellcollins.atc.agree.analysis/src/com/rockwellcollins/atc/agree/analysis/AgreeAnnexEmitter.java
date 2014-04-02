package com.rockwellcollins.atc.agree.analysis;
 
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
import jkind.lustre.RealExpr;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

import org.eclipse.emf.ecore.EObject;
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
import org.osate.aadl2.ThreadSubcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.google.inject.name.Named;
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
import com.rockwellcollins.atc.agree.agree.ParamStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
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
    public final String sysGuarTag = "__SYS_GUARANTEE_";
    
    //reference map used for hyperlinking from the console
    public final Map<String, EObject> refMap = new HashMap<>();
    
    //used for preventing name collision, and for pretty printing aadl variables
    private final String jKindNameTag;
    private final String aadlNameTag;
    private final String clockIDPrefix = "__CLOCK_";
    private final String queueInClockPrefix ="__QUEUE_IN_CLOCK_";
    private final String queueOutClockPrefix ="__QUEUE_OUT_CLOCK_";
    private final String queueCountPrefix = "__QUEUE_COUNT_";
    private final Map<EventDataPort, List<IdExpr>> queueInClockMap = new HashMap<>();
    private final Map<EventDataPort, List<IdExpr>> queueOutClockMap = new HashMap<>();
    private final Map<EventDataPort, List<IdExpr>> queueCountMap = new HashMap<>();
    
    //used for pretty printing jkind -> aadl variables
    public final Map<String, String> varRenaming = new HashMap<>();
    
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

    public AgreeAnnexEmitter(ComponentInstance compInst,
            AgreeLayout layout,
            String category,
            String jPrefix,
            String aPrefix,
            boolean ignoreLifts,
            boolean topLevel){
    	
        this.layout = layout;
        this.curInst = compInst;
        this.category = category;
        this.ignoreLifts  = ignoreLifts;
        
        initTypeMap.put("bool", initBool);
        initTypeMap.put("real", initReal);
        initTypeMap.put("int", initInt);
        
        if(!layout.getCategories().contains(category)){
            layout.addCategory(category);
        }
        
        curComp = curInst.getSubcomponent();
        
        jKindNameTag = jPrefix;
        aadlNameTag = aPrefix;
        
        ComponentClassifier compClass = compInst.getComponentClassifier();
        //populates the connection equivalences if we are at the top level
        //this is only here to make sure that "LIFT" statements work correctly
        if(topLevel){
            setConnExprs((ComponentImplementation)compClass, jPrefix, aPrefix);
            //this function must be called after the previous function
            setEventPortQueues();
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
    
    private void setEventPortQueues() {

    	//queueSignalEqualityAssertion
    	for(String destStr : queueMap.keySet()){
		   
		   	List<AgreeQueueElement> queueList = queueMap.get(destStr);
		   	AgreeQueueElement firstQueue = queueList.get(0);
		   	long queueSize = firstQueue.queueSize;
		   	Type type = firstQueue.type;
		   	int numInputs = queueList.size(); 
		   	
	   		Node queueNode = AgreeCalendarUtils.queueNode("__Queue_Node_"+destStr, type, (int)queueSize);
	   		Node inputMultiplex = AgreeCalendarUtils.queueMultiplexNode("__Queue_Mult_"+destStr, type, numInputs);

	   		nodeDefExpressions.add(queueNode);
	   		nodeDefExpressions.add(inputMultiplex);
	   		
	   		List<Expr> queueMultInputs = new ArrayList<>();
	   		List<Expr> queueInputs = new ArrayList<>();
	   		
	   		Expr queueClockInExpr = new BoolExpr(false);
		   	for(AgreeQueueElement agreeQueEl : queueList){
		   		
		   		//do some sanity checks
		   		if(firstQueue.destConn != agreeQueEl.destConn){
		   			throw new AgreeException("Something went wrong with book keeping during queue creation");
		   		}
		   		
		   		String jCat = agreeQueEl.sourCategory == null ? "" : agreeQueEl.sourCategory+dotChar;
		   		String aCat = agreeQueEl.sourCategory == null ? "" : agreeQueEl.sourCategory+".";
		   		
		   		IdExpr clockInId = new IdExpr(queueInClockPrefix+jCat+agreeQueEl.sourConn.getName());
		   		AgreeVarDecl clockInVar = 
		   				new AgreeVarDecl(clockInId.id,
		   						queueInClockPrefix+aCat+agreeQueEl.sourConn.getName()
		   						, NamedType.BOOL.toString());
				
		   		//if the clock input hasn't been created yet,
		   		//do reference mapping and add it to the map
		   		//of input clocks
		   		if(!inputVars.contains(clockInVar)){
		   			inputVars.add(clockInVar);
		   			addToRenaming(clockInVar.jKindStr, clockInVar.aadlStr);
		   			refMap.put(clockInVar.aadlStr, agreeQueEl.sourConn);
		   	        layout.addElement(category, clockInVar.aadlStr, AgreeLayout.SigType.INPUT);

		   			List<IdExpr> clockInIds = queueInClockMap.get(agreeQueEl.sourConn);

		   			if(clockInIds == null){
		   				clockInIds = new ArrayList<>();
		   				queueInClockMap.put(agreeQueEl.sourConn, clockInIds);
		   			}
		   			
		   			clockInIds.add(clockInId);
		   			
		   		}
	            
		   		queueMultInputs.add(new IdExpr(agreeQueEl.jSour));
		   		queueMultInputs.add(clockInId);
		   		queueClockInExpr = new BinaryExpr(queueClockInExpr, BinaryOp.OR, clockInId);
		   	}
		   	
		   
		   	//add all the stuff for the queue multiplexer
		   	NodeCallExpr queueMultCall = new NodeCallExpr(inputMultiplex.id, queueMultInputs);
		   	IdExpr queueInputId = new IdExpr("__QUEUE_INPUT_"+destStr);
		   	AgreeVarDecl queueInputVar = 
		   			new AgreeVarDecl(queueInputId.id, queueInputId.id, type.toString());

		   	internalVars.add(queueInputVar);
		   	Equation multEq = new Equation(queueInputId, queueMultCall);
		   	connExpressions.add(multEq);	   	
		   	
		   	//add all the stuff for the queuenode call
		   	String jCat =  firstQueue.destCategory == null ? "" : firstQueue.destCategory+dotChar;
	   		String aCat = firstQueue.destCategory == null ? "" : firstQueue.destCategory+".";
		   	
		   	IdExpr clockOutId = new IdExpr(queueOutClockPrefix+jCat+firstQueue.destConn.getName());
		   	AgreeVarDecl clockOutVar = 
		   			new AgreeVarDecl(clockOutId.id,
		   					queueOutClockPrefix+aCat+firstQueue.destConn.getName(),
		   					NamedType.BOOL.toString());

	   		//if the clock output hasn't been created yet,
		   	//do reference mapping and add it to the map
		   	//of output clocks
		   	if(!inputVars.contains(clockOutVar)){
		   		inputVars.add(clockOutVar);
		   		addToRenaming(clockOutVar.jKindStr, clockOutVar.aadlStr);
		   		refMap.put(clockOutVar.aadlStr, firstQueue.destConn);
		   		layout.addElement(category, clockOutVar.aadlStr, AgreeLayout.SigType.INPUT);

		   		List<IdExpr> clockOutIds = queueInClockMap.get(firstQueue.destConn);

		   		if(clockOutIds == null){
		   			clockOutIds = new ArrayList<>();
		   			queueOutClockMap.put(firstQueue.destConn, clockOutIds);
		   		}

		   		clockOutIds.add(clockOutId);

		   	}

		   	queueInputs.add(queueInputId);
		   	queueInputs.add(queueClockInExpr);
		   	queueInputs.add(clockOutId);
		   	NodeCallExpr queueCall = new NodeCallExpr(queueNode.id, queueInputs);
		   	
			IdExpr queueElsId = new IdExpr(queueCountPrefix+destStr);
		   	AgreeVarDecl queueElsVar = 
		   			new AgreeVarDecl(queueElsId.id, 
		   					queueElsId.id, 
		   					NamedType.INT.toString());
		   	
		   	internalVars.add(queueElsVar);
		   	List<IdExpr> countIds = queueCountMap.get(firstQueue.destConn);
		   	if(countIds == null){
		   		countIds = new ArrayList<>();
		   		queueCountMap.put(firstQueue.destConn, countIds);
		   	}
		   	
		   	countIds.add(queueElsId);
		   	
	   		IdExpr singleQueueCountId = new IdExpr(queueCountPrefix+jCat+firstQueue.destConn.getName());
	   		AgreeVarDecl singleQueueCountVar = 
	   				new AgreeVarDecl(singleQueueCountId.id,
	   						queueCountPrefix+aCat+firstQueue.destConn.getName(),
	   						NamedType.INT.toString());

	   		if(!inputVars.contains(singleQueueCountVar)){
	   			inputVars.add(singleQueueCountVar);
	   			addToRenaming(singleQueueCountVar.jKindStr, singleQueueCountVar.aadlStr);
	   			refMap.put(singleQueueCountVar.aadlStr, firstQueue.destConn);
	   			layout.addElement(category, singleQueueCountVar.aadlStr, AgreeLayout.SigType.INPUT);
	   			countIds.add(singleQueueCountId);
	   		}

	   		
		   	//finally, call the node
		   	List<IdExpr> queueLhs = new ArrayList<>();
		   	queueLhs.add(queueElsId);
		   	queueLhs.add(new IdExpr(destStr));
		   	Equation queueEq = new Equation(queueLhs, queueCall);
		   	connExpressions.add(queueEq);

	   	}
	   
    }

// ************** CASE STATEMENTS **********************
    
    public Expr getQueueCountID(String jVarName, String aVarName, NamedElement comp){

    	EventDataPort port = (EventDataPort)comp;

    	//a variable of the same name as this should be created by setEventPortQueues()
    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
    	AgreeVarDecl countVar = new AgreeVarDecl(queueCountPrefix+jVarName,
    			queueCountPrefix+aVarName,
        		NamedType.INT.toString());
    	
    	IdExpr countIdExpr = new IdExpr(countVar.jKindStr);

    	//if we have already made the expression then don't make it again
        if(inputVars.contains(countVar)){
        	return countIdExpr;
        }
        inputVars.add(countVar);
        addToRenaming(countVar.jKindStr,countVar.aadlStr);
        refMap.put(countVar.aadlStr, port);
        layout.addElement(category, countVar.aadlStr, AgreeLayout.SigType.INPUT);
        
    	return countIdExpr;

    }
    
    
    public Expr getQueueRemoveID(String jVarName, String aVarName, NamedElement comp){

    	EventDataPort port = (EventDataPort)comp;

    	//a variable of the same name as this should be created by setEventPortQueues()
    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
    	AgreeVarDecl removeVar = new AgreeVarDecl(queueOutClockPrefix+jVarName,
    			queueOutClockPrefix+aVarName,
        		NamedType.BOOL.toString());
    	
    	IdExpr removeIdExpr = new IdExpr(removeVar.jKindStr);

    	//if we have already made the expression then don't make it again
        if(inputVars.contains(removeVar)){
        	return removeIdExpr;
        }
        inputVars.add(removeVar);
        addToRenaming(removeVar.jKindStr,removeVar.aadlStr);
        refMap.put(removeVar.aadlStr, port);
        layout.addElement(category, removeVar.aadlStr, AgreeLayout.SigType.INPUT);
        
    	return removeIdExpr;
    }
    

    public Expr getQueueInsertID(String jVarName, String aVarName, NamedElement comp){
    	
    	EventDataPort port = (EventDataPort)comp;
    	
    	//a variable of the same name as this should be created by setEventPortQueues()
    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
    	AgreeVarDecl insertVar = new AgreeVarDecl(queueInClockPrefix+jVarName,
    			queueInClockPrefix+aVarName,
        		NamedType.BOOL.toString());
    	
    	IdExpr insertIdExpr = new IdExpr(insertVar.jKindStr);

    	//if we have already made the expression then don't make it again
        if(inputVars.contains(insertVar)){
        	return insertIdExpr;
        }
        
        inputVars.add(insertVar);
        addToRenaming(insertVar.jKindStr,insertVar.aadlStr);
        refMap.put(insertVar.aadlStr, port);
        layout.addElement(category, insertVar.aadlStr, AgreeLayout.SigType.INPUT);
    	
    	return insertIdExpr;
    }
    
    public Expr getClockID(String jVarName, String aVarName, NamedElement comp){
    	//IdExpr clockId = new IdExpr(clockIDPrefix+comp.getName());
    	
    	
    	//a variable of the same name as this should be created by setEventPortQueues()
    	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
    	AgreeVarDecl clockVar = new AgreeVarDecl(clockIDPrefix+jVarName,
    			clockIDPrefix+aVarName,
        		NamedType.BOOL.toString());
    	
    	IdExpr clockId = new IdExpr(clockVar.jKindStr);

    	//if we have already made the expression then don't make it again
        if(inputVars.contains(clockVar)){
        	return clockId;
        }
        
        inputVars.add(clockVar);
        addToRenaming(clockVar.jKindStr,clockVar.aadlStr);
        refMap.put(clockVar.aadlStr, comp);
        layout.addElement(category, clockVar.aadlStr, AgreeLayout.SigType.INPUT);
    	
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
        
        NestedDotID nestId = lift.getSubcomp();
        
        Subcomponent subComp = (Subcomponent)nestId.getBase();
        ComponentInstance subCompInst = curInst.findSubcomponentInstance(subComp);
        ComponentImplementation subCompImpl = subComp.getComponentImplementation();
        ComponentType subCompType = subCompImpl.getType();

        AgreeAnnexEmitter subEmitter = new AgreeAnnexEmitter(
                subCompInst, layout, category,
                jKindNameTag + subComp.getName() + dotChar,
                aadlNameTag + subComp.getFullName() + ".", false, true);
        
        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) { 
                subEmitter.doSwitch(annex);
            }
        }

        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompType, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                subEmitter.doSwitch(annex);
            }
        }
        
        connExpressions.addAll(subEmitter.connExpressions);
        guarExpressions.addAll(subEmitter.guarExpressions);
        assumpExpressions.addAll(subEmitter.assumpExpressions);
        constExpressions.addAll(subEmitter.constExpressions);
        assertExpressions.addAll(subEmitter.assertExpressions);
        eqExpressions.addAll(subEmitter.eqExpressions);
        nodeDefExpressions.addAll(subEmitter.nodeDefExpressions);
        propExpressions.addAll(subEmitter.propExpressions);
        
        subEmitter.inputVars.removeAll(internalVars);
        subEmitter.outputVars.removeAll(internalVars);
        inputVars.removeAll(subEmitter.internalVars);
        outputVars.removeAll(subEmitter.internalVars);
        
        inputVars.addAll(subEmitter.inputVars);
        outputVars.addAll(subEmitter.outputVars);
        internalVars.addAll(subEmitter.internalVars);
        
        addToRenamingAll(subEmitter.varRenaming);
        refMap.putAll(subEmitter.refMap);
       
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
        refMap.put(guarStr, state);
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
        refMap.put(guarStr, state);
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

        AgreeVarDecl varDecl = new AgreeVarDecl();
        varDecl.jKindStr = jKindNameTag + state.getName();
        varDecl.aadlStr = aadlNameTag + state.getName();
        varDecl.type = "bool";

        layout.addElement(category, varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);

        addToRenaming(varDecl.jKindStr, varDecl.aadlStr);
        refMap.put(varDecl.aadlStr, state);
        internalVars.add(varDecl);

        IdExpr id = new IdExpr(varDecl.jKindStr);
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
            AgreeVarDecl varDecl = new AgreeVarDecl();
            varDecl.jKindStr = jKindNameTag + baseName;

            IdExpr idExpr = new IdExpr(varDecl.jKindStr);
            varIds.add(idExpr);

            varDecl.aadlStr = aadlNameTag + baseName;
            varDecl.type = arg.getType().getString();

            layout.addElement(category, varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);


            addToRenaming(varDecl.jKindStr, varDecl.aadlStr);
            refMap.put(varDecl.aadlStr, state);
            
            if(expr != null){
                internalVars.add(varDecl);
            }else{
                //the variable is implicitly defined
                inputVars.add(varDecl);
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
     
        String fnName = jKindNameTag + namedEl.getName();

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

    // TODO: ordering nodes/functions in dependency order
    @Override
    public Expr caseNodeDefExpr(NodeDefExpr expr) {
        // System.out.println("Visiting caseNodeDefExpr");

        String nodeName = jKindNameTag + expr.getName();
        
        for(Node node : nodeDefExpressions){
            if(node.id.equals(nodeName)){
                return null;
            }
        }

        List<VarDecl> inputs = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, expr.getArgs());
        List<VarDecl> outputs = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, expr.getRets());
        NodeBodyExpr body = expr.getNodeBody();
        List<VarDecl> internals = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, body.getLocs());
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
    
    //helper method for the above method
    private Equation nodeEqToEq(NodeEq nodeEq) {
        Expr expr = doSwitch(nodeEq.getExpr());
        List<IdExpr> ids = new ArrayList<IdExpr>();
        for (Arg arg : nodeEq.getLhs()) {
            ids.add(new IdExpr(jKindNameTag + arg.getName()));
        }
        Equation eq = new Equation(ids, expr);
        return eq;
    }

    @Override
    public Expr caseFnDefExpr(FnDefExpr expr) {

        String nodeName = jKindNameTag + expr.getName();

        for(Node node : nodeDefExpressions){
            if(node.id.equals(nodeName)){
                return null;
            }
        }

        List<VarDecl> inputs = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, expr.getArgs());
        Expr bodyExpr = doSwitch(expr.getExpr());

        Type outType = new NamedType(expr.getType().getString());
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

        NamedElement propName = namedElFromId(expr.getProp());
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
        } else if (obj instanceof com.rockwellcollins.atc.agree.agree.IdExpr) {
            return ((com.rockwellcollins.atc.agree.agree.IdExpr) obj).getId();
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
                }else{
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
                }
                
            }
            return compInst;
        }
    }

    @Override
    public Expr caseIdExpr(com.rockwellcollins.atc.agree.agree.IdExpr expr) {
        //I'm pretty sure this is dead code now
        assert(false);
        // just make an expression of the NamedElement
        return new IdExpr(jKindNameTag + expr.getId().getName());
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
    public Expr caseNestedDotID(NestedDotID Id) {

        NestedDotID orgId = Id;
        String jKindVar = "";
        String aadlVar = "";
        while (Id.getSub() != null) {
            jKindVar += Id.getBase().getName() + dotChar;
            aadlVar += Id.getBase().getName() + ".";
            Id = Id.getSub();
        }

        NamedElement namedEl = Id.getBase();
        
        String tag = Id.getTag();
        if(tag != null){
        	
        	String jVarPrefix = this.jKindNameTag + jKindVar + namedEl.getName();
        	String aVarPrefix = this.aadlNameTag + aadlVar + namedEl.getName();
        	
        	switch(tag){
        	case "CLK":
        		return getClockID(jVarPrefix, aVarPrefix, namedEl);
        	case "COUNT":
        		return getQueueCountID(jVarPrefix, aVarPrefix, namedEl);
        	case "INSERT":
        		return getQueueInsertID(jVarPrefix, aVarPrefix, namedEl);
        	case "REMOVE":
        		return getQueueRemoveID(jVarPrefix, aVarPrefix, namedEl);
        	default:
        		throw new AgreeException("use of uknown tag: '"+tag+"' in expression: '"+aadlVar+tag+"'");
        	}
        }
        

        //special case for constants
        if(namedEl instanceof ConstStatement){
            return doSwitch(((ConstStatement)namedEl).getExpr());
        }
        
        String baseName = namedEl.getName();
        jKindVar = jKindNameTag + jKindVar + Id.getBase().getName();
        aadlVar = aadlNameTag + aadlVar + Id.getBase().getName();
        
        IdExpr result = new IdExpr(jKindVar);

        // hack for making sure all inputs have been created
        if (namedEl instanceof DataSubcomponent || namedEl instanceof DataPort) {
            AgreeVarDecl tempStrType = new AgreeVarDecl();
            tempStrType.jKindStr = jKindVar;

            if (!inputVars.contains(tempStrType) && !internalVars.contains(tempStrType)) {

                log.logWarning("In component '"
                        + orgId.getBase().getContainingClassifier().getFullName() + "', Port '"
                        + jKindVar + "' is not connected to anything. Considering it to be"
                        + " an unconstrained primary input.");

                // this code just creates a new PI
                if(namedEl instanceof DataSubcomponent){
                    tempStrType = AgreeEmitterUtilities.dataTypeToVarType((DataSubcomponent) namedEl);
                }else{
                    DataType dataType = (DataType) ((DataPort)namedEl).getDataFeatureClassifier();
                            
                    String typeStr = AgreeEmitterUtilities.dataTypeToVarType(dataType);
                    tempStrType = new AgreeVarDecl();
                    tempStrType.type = typeStr;
                }

                tempStrType.jKindStr = jKindVar;
                tempStrType.aadlStr = aadlVar;
                
                layout.addElement(category, aadlVar, AgreeLayout.SigType.INPUT);
             
                addToRenaming(jKindVar, aadlVar);
                refMap.put(aadlVar, Id);
                inputVars.add(tempStrType);
                
                //have to keep track of outputs for condacts
                boolean reverseDirection = false;
            	if(orgId.getBase() instanceof FeatureGroup){
            		FeatureGroup featGroup = (FeatureGroup)orgId.getBase();
            		reverseDirection = featGroup.isInverse();
            	}
            	if(namedEl instanceof DataSubcomponent){
            		NestedDotID tempId = orgId;
            		while(!(tempId.getBase() instanceof DirectedFeature)){
            			tempId = orgId.getSub();
            		}
            		DirectionType direction = ((DirectedFeature)tempId.getBase()).getDirection();
            		if((direction == DirectionType.OUT && !reverseDirection)
            				|| (direction == DirectionType.IN && reverseDirection)){
            			outputVars.add(tempStrType);
            		}
            	}else{
            		DirectionType direction = ((DirectedFeature)namedEl).getDirection();
            		if((direction == DirectionType.OUT && !reverseDirection)
            				|| (direction == DirectionType.IN && reverseDirection)){
            			outputVars.add(tempStrType);
            		}
            	}
                
            }
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
    private void setConnExprs(ComponentImplementation compImpl, String initJStr, String initAStr) {

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

            ConnectionEnd destConn = absConnDest.getConnectionEnd();
            ConnectionEnd sourConn = absConnSour.getConnectionEnd();
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
            
            Feature port = null;
            if (destConn != null) {
            	if(!(destConn instanceof Feature)){ //we don't handle data accesses
            		continue;
            	}
            	
                port = (Feature)destConn;
                if(port instanceof FeatureGroup){
                    FeatureGroupType featType = ((FeatureGroup)port).getAllFeatureGroupType();
                    for(DataPort dPort: featType.getOwnedDataPorts()){
                        port = dPort;
                        setConnExpr(port.getName(), initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                    }
                }else{
                    setConnExpr("", initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                }
            } else if (sourConn != null) {
            	if(!(sourConn instanceof Feature)){ //we don't handle data accesses
            		continue;
            	}
                port = (Feature)sourConn;
                if(port instanceof FeatureGroup){
                    FeatureGroupType featType = ((FeatureGroup)port).getAllFeatureGroupType();
                    for(DataPort dPort: featType.getOwnedDataPorts()){
                        port = dPort;
                        setConnExpr(port.getName(), initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                    }
                }else{
                    setConnExpr("", initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                }
            }
        }
    }
    
    
    private void setConnExpr(String prefix,
            String initJStr,
            String initAStr,
            Feature port,
            Context sourContext,
            ConnectionEnd sourConn,
            Context destContext, 
            ConnectionEnd destConn,
            boolean delayed){
        
        
        DataSubcomponentType dataSub;
        long queueSize = -1;
        if(port instanceof DataPort){
            dataSub = ((DataPort)port).getDataFeatureClassifier();
        }else if (port instanceof EventDataPort){
            dataSub = ((EventDataPort)port).getDataFeatureClassifier();
            Property queueSizeProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                    OsateResourceUtil.getResourceSet(), "Communication_Properties::Queue_Size");
            try{
            	queueSize = PropertyUtils.getIntegerValue(port, queueSizeProp);
            }catch(PropertyDoesNotApplyToHolderException e){
                delayed = false;
            }
        }else if (port instanceof BusAccess || port instanceof DataAccess){
        	//TODO: we don't currently support data or bus accesses
        	return;
        }else{
            dataSub = ((DataAccess)port).getDataFeatureClassifier();
        }
        
        if(dataSub instanceof DataType){
            //we only want to reason about known types
            if(AgreeEmitterUtilities.dataTypeToVarType((DataType)dataSub) == null){
                return;
            }
        }

        if (!(dataSub instanceof DataImplementation || dataSub instanceof DataType)) {
            return;
        }

        String sourStr;
        String destStr;
        String aadlSourStr;
        String aadlDestStr;
        if (sourContext != null) { // source is not an end point
            assert (sourContext instanceof Subcomponent || sourContext instanceof FeatureGroup);
            sourStr = sourContext.getName() + dotChar + sourConn.getName();
            aadlSourStr = sourContext.getName() + "." + sourConn.getName();
        } else {
            sourStr = sourConn.getName();
            aadlSourStr = sourConn.getName();
        }

        if (destContext != null) { // destination is not an end point
            assert (destContext instanceof Subcomponent || destContext instanceof ThreadSubcomponent);
            destStr = destContext.getName() + dotChar + destConn.getName();
            aadlDestStr = destContext.getName() + "." + destConn.getName();
        } else {
            destStr = destConn.getName();
            aadlDestStr = destConn.getName();
        }
        
        if(destConn instanceof FeatureGroup){
        	destStr = destStr + dotChar + port.getName();
        	aadlDestStr = aadlDestStr + "." + port.getName();
        }
        
        if(sourConn instanceof FeatureGroup){
        	sourStr = sourStr + dotChar + port.getName();
        	aadlSourStr = aadlSourStr + "." + port.getName();
        }
        
        if(dataSub instanceof DataImplementation){
            Set<AgreeVarDecl> tempSet = new HashSet<AgreeVarDecl>();
            getAllDataNames((DataImplementation) dataSub, tempSet);

            for (AgreeVarDecl destVarType : tempSet) {
                //stupid hack to handle feature groups correctly
                if(!prefix.equals("")){
                    destVarType.jKindStr = prefix + dotChar + destVarType.jKindStr;
                    destVarType.aadlStr = prefix + "." + destVarType.aadlStr;
                }
                String newDestStr = initJStr + destStr + dotChar + destVarType.jKindStr;
                String newSourStr = initJStr + sourStr + dotChar + destVarType.jKindStr;
                String newAADLDestStr = initAStr + aadlDestStr + "." + destVarType.aadlStr;
                String newAADLSourStr = initAStr + aadlSourStr + "." + destVarType.aadlStr;

                // make an internal var for this
                destVarType.jKindStr = newDestStr;
                destVarType.aadlStr = newAADLDestStr;
                
                AgreeVarDecl sourVarType = new AgreeVarDecl();
                sourVarType.jKindStr = newSourStr;
                sourVarType.aadlStr = newAADLSourStr;
                sourVarType.type = destVarType.type;

                setConnExpr_Helper(sourContext, sourConn, destContext, destConn, 
                        destVarType, sourVarType, delayed, queueSize);
            }
        }else{
            AgreeVarDecl destVarType = new AgreeVarDecl();
            
            String newDestStr = initJStr + destStr;
            String newSourStr = initJStr + sourStr;
            String newAADLDestStr = initAStr + aadlDestStr;
            String newAADLSourStr = initAStr + aadlSourStr;
            
            destVarType.jKindStr = newDestStr;
            destVarType.aadlStr = newAADLDestStr;
            destVarType.type = AgreeEmitterUtilities.dataTypeToVarType((DataType)dataSub);
            
            AgreeVarDecl sourVarType = new AgreeVarDecl();
            sourVarType.jKindStr = newSourStr;
            sourVarType.aadlStr = newAADLSourStr;
            sourVarType.type = destVarType.type;
            
            setConnExpr_Helper(sourContext, sourConn, destContext, destConn, 
                    destVarType, sourVarType, delayed, queueSize);

        }

    }
    
    private void setConnExpr_Helper(Context sourContext,
            ConnectionEnd sourConn,
            Context destContext, 
            ConnectionEnd destConn,
            AgreeVarDecl destVar,
            AgreeVarDecl sourVar,
            boolean delayed,
            long queueSize){
        
    	if(delayed && queueSize != -1){
    		throw new AgreeException("Connection: "+sourVar.aadlStr+
    				" is delayed, and also an event port, don't do that please...");
    	}
    	
    	//if this is an event port, save them to turn them into queues later
    	if(queueSize != -1){
    		String destCategory = destContext == null ? null : destContext.getName();
    		String sourCategory = sourContext == null ? null : sourContext.getName();

    		AgreeQueueElement agreeQueueElem = new AgreeQueueElement(sourVar.jKindStr, 
    				sourVar.aadlStr, 
    				destVar.jKindStr, 
    				destVar.aadlStr, 
    				new NamedType(destVar.type),
    				(EventDataPort)sourConn,
    				(EventDataPort)destConn,
    				sourCategory, 
    				destCategory, 
    				queueSize);
    		
    		if(queueMap.containsKey(destVar.jKindStr)){
    			List<AgreeQueueElement> queues = queueMap.get(destVar.jKindStr);
    			queues.add(agreeQueueElem);
    		}else{
    			List<AgreeQueueElement> queues = new ArrayList<AgreeQueueElement>();
    			queues.add(agreeQueueElem);
    			queueMap.put(destVar.jKindStr, queues);
    		}
       	}
    	
        if (destContext != null) {
            layout.addElement(destContext.getName(), destVar.aadlStr,
                    AgreeLayout.SigType.OUTPUT);
        }else{
        	layout.addElement(this.category, destVar.aadlStr,
                    AgreeLayout.SigType.OUTPUT);
        }

        refMap.put(destVar.aadlStr, destConn);
        addToRenaming(destVar.jKindStr, destVar.aadlStr);
        internalVars.add(destVar);


        layout.addElement(category, sourVar.aadlStr,
                AgreeLayout.SigType.INPUT);
        addToRenaming(sourVar.jKindStr, sourVar.aadlStr);
        
        refMap.put(sourVar.aadlStr, sourConn);
        inputVars.add(sourVar);

        if(sourConn != null){
            if(sourConn instanceof DirectedFeature){
                if(((DirectedFeature)sourConn).getDirection() == DirectionType.OUT){
                    outputVars.add(sourVar);
                }
            }else{
                assert(sourConn instanceof DataSubcomponent);
                outputVars.add(sourVar);
            }
        }

        
    	if(queueSize != -1){
    		//we have added the source and destination vars
    		//to the reference map and input/output/internal vars
    		//section.  The queue between these variables
    		//will be built by a call to "setEventPortQueues()"
    		//later, so don't add these to the connExpressions yet
    		return;
    	}
        
        Expr connExpr = null;
        IdExpr sourId = new IdExpr(sourVar.jKindStr);

        if (sourContext != null && destContext != null && delayed) {
            // this is not an input, and the output is not a terminal
            Expr initValExpr = initTypeMap.get(destVar.type);
            connExpr = new UnaryExpr(UnaryOp.PRE, sourId);
            connExpr = new BinaryExpr(initValExpr, BinaryOp.ARROW, connExpr);
            
        } else {
            connExpr = sourId;
        }
        IdExpr destId = new IdExpr(destVar.jKindStr);
        Equation connEq = new Equation(destId, connExpr);

        System.out.println(connEq);
        connExpressions.add(connEq);
        
    }
    
    //helper method to above
    private void getAllDataNames(DataImplementation dataImpl, Set<AgreeVarDecl> subStrTypes) {

        for (Subcomponent sub : dataImpl.getAllSubcomponents()) {
            if (sub instanceof DataSubcomponent) {
                Set<AgreeVarDecl> newStrTypes = new HashSet<AgreeVarDecl>();
                ComponentClassifier subImpl = sub.getAllClassifier();
                if (subImpl instanceof DataImplementation) {
                    getAllDataNames((DataImplementation) subImpl, newStrTypes);
                    for (AgreeVarDecl strType : newStrTypes) {
                        AgreeVarDecl newStrType = new AgreeVarDecl();
                        newStrType.jKindStr = sub.getName() + dotChar + strType.jKindStr;
                        newStrType.aadlStr = sub.getName() + "." + strType.aadlStr;
                        newStrType.type = strType.type;
                        subStrTypes.add(newStrType);
                    }
                } else {
                    assert (subImpl instanceof DataType);
                    AgreeVarDecl newStrType = AgreeEmitterUtilities.dataTypeToVarType((DataSubcomponent) sub);
                    if (newStrType != null && newStrType.type != null) {
                        subStrTypes.add(newStrType);
                    }
                }
            }
        }
    }
    
    public AgreeNode getComponentNode(){
        
        if(agreeNode != null){
            return agreeNode;
        }
        
        List<Equation> eqs = new ArrayList<Equation>();
        List<VarDecl> inputs = new ArrayList<VarDecl>();
        List<VarDecl> outputs = new ArrayList<VarDecl>();

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(this.nodeDefExpressions);
        eqs.addAll(this.constExpressions);
        eqs.addAll(this.eqExpressions);
        eqs.addAll(this.propExpressions);
        eqs.addAll(this.connExpressions);
                
        Set<AgreeVarDecl> agreeInputVars = new HashSet<>();
        Set<AgreeVarDecl> agreeInternalVars = new HashSet<>();
        
        agreeInputVars.addAll(this.inputVars);
        agreeInternalVars.addAll(this.internalVars);
        
        //convert the variables
        for(AgreeVarDecl aVar : agreeInputVars){
            inputs.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }
        
        for(AgreeVarDecl aVar : agreeInternalVars){
            outputs.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }
        
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

        Node node = new Node("__NODE_"+this.category, inputs, outputs, Collections.EMPTY_LIST, eqs);
        VarDecl clockVar = new VarDecl(clockIDPrefix+this.curComp.getName(), NamedType.BOOL);
        
        List<Node> agreeNodes = new ArrayList<>(this.nodeDefExpressions);
        agreeNodes.add(node);
        
        agreeNode = new AgreeNode(inputs, outputs, assumVars,
                asserVars, guarVars, agreeNodes, node, clockVar);
        
        return agreeNode;
    }
    
    public List<Node> getLustreWithCondacts(List<AgreeAnnexEmitter> subEmitters){
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

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(this.nodeDefExpressions);
        eqs.addAll(this.constExpressions);
        eqs.addAll(this.eqExpressions);
        eqs.addAll(this.propExpressions);
        eqs.addAll(this.connExpressions);
        
        Set<AgreeVarDecl> agreeInputVars = new HashSet<>();
        Set<AgreeVarDecl> agreeInternalVars = new HashSet<>();
        
        agreeInputVars.addAll(this.inputVars);
        agreeInternalVars.addAll(this.internalVars);

        addSubEmitterVars(subEmitters, eqs, inputs, clocks, nodeSet,
				agreeInputVars, agreeInternalVars);

        //warn about combinational cycles
        AgreeEmitterUtilities.logCycleWarning(this, eqs, false);
        
        agreeInputVars.removeAll(agreeInternalVars);
        
        //convert the variables
        for(AgreeVarDecl aVar : agreeInputVars){
            inputs.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }
        for(AgreeVarDecl aVar : agreeInternalVars){
            internals.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }

        IdExpr totalCompHistId = new IdExpr("_TOTAL_COMP_HIST");
        IdExpr sysAssumpHistId = new IdExpr("_SYSTEM_ASSUMP_HIST");

        internals.add(new VarDecl(totalCompHistId.id, new NamedType("bool")));
        internals.add(new VarDecl(sysAssumpHistId.id, new NamedType("bool")));

        // total component history
        Expr totalCompHist = new BoolExpr(true);

        for(AgreeAnnexEmitter subEmitter : subEmitters){
            totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, AgreeEmitterUtilities.getLustreContract(subEmitter));
        }

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
        IdExpr countId = addConsistencyConstraints(eqs, internals);
        

        //get the equations that guarantee that every clock has ticked atleast once
        List<Equation> tickEqs = AgreeCalendarUtils.getAllClksHaveTicked("__ALL_TICKED", "__CLK_TICKED", clocks);
        
        //add all the new clock tick variables to the internal variables list
        for(Equation eq : tickEqs){
            internals.add(new VarDecl(eq.lhs.get(0).id, NamedType.BOOL));
        }
        
        eqs.addAll(tickEqs);
        IdExpr allClksTickedExpr = tickEqs.get(tickEqs.size()-1).lhs.get(0);
        
        // get the individual history variables and create assumption properties
        addSubcomponentAssumptions(subEmitters, eqs, internals, properties,
				totalCompHist, sysAssumpHistId, allClksTickedExpr, closureMap, countId);
        
        
        // create individual properties for guarantees
        int i = 0;
        for (Equation guar : guarExpressions) {
            String guarName = guar.lhs.get(0).id;
            IdExpr sysGuaranteesId = new IdExpr(sysGuarTag + i);
            internals.add(new VarDecl(sysGuaranteesId.id, new NamedType("bool")));

            Expr totalSysGuarExpr = new BinaryExpr(sysAssumpHistId, BinaryOp.AND, totalCompHistId);
            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.AND, allClksTickedExpr);
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

        //create the assertions for the clocks
        List<Expr> assertions = getClockAssertions(clocks, nodeSet);
        
        //assert equivalence between queue clocks and such
        Expr queueClockVarEquivAssertion = getQueueClockCountVarEquivAssertion();
        assertions.add(queueClockVarEquivAssertion);
        
        //assert that inserts and removes don't take place while a component isn't being clocked
        Expr queueInsertRemoveAtomicAssertion = getQueueInsertRemoveAtomicAssertion(subEmitters);
        assertions.add(queueInsertRemoveAtomicAssertion);
        
        //make assertions implicit by adding them to the LHS of properties
        //List<String> assertProps = new ArrayList<>();
        
        // Expr totalAssertExpr = new BoolExpr(true);
        //for(Expr assertExpr : assertions){
        //	totalAssertExpr = new BinaryExpr(totalAssertExpr, BinaryOp.AND, assertExpr);
       // }
        //IdExpr assertId = new IdExpr("__ASSERTIONS_");
        //internals.add(new VarDecl(assertId.id, NamedType.BOOL));
        //Equation assertEq = new Equation(assertId, totalAssertExpr);
        //eqs.add(assertEq);
        
        //for(String propStr : guarProps){
        //	IdExpr propId = new IdExpr(propStr);
        //	IdExpr assertProp = new IdExpr("__ASSERT_"+propStr);
        //	internals.add(new VarDecl(assertProp.id, NamedType.BOOL));
        //	
        //	Equation propEq = new Equation(assertProp, new BinaryExpr(assertId, BinaryOp.IMPLIES, propId));
        //	eqs.add(propEq);
        //	assertProps.add(assertProp.id);
        //	addToRenaming(assertProp.id, assertProp.id);
        //}
        //
        //guarProps = assertProps;
        
        Node topNode = new Node("_MAIN", inputs, outputs, internals, eqs, properties, assertions);
        nodeSet.add(topNode);
        
        return nodeSet;
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
        addToRenaming(notTotalCompHistId.id, "Total Contract Consistent");
        //layout.addElement("Top", "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);
        layout.addElement(category, "Total Contract Consistents", AgreeLayout.SigType.OUTPUT);
	}


	private IdExpr addConsistencyConstraints(List<Equation> eqs,
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
			List<Node> nodeSet, Set<AgreeVarDecl> agreeInputVars,
			Set<AgreeVarDecl> agreeInternalVars) {
		for(AgreeAnnexEmitter subEmitter : subEmitters){
            
            AgreeNode agreeNode = subEmitter.getComponentNode();
            nodeSet.addAll(agreeNode.nodes);
            
            List<Expr> initOutputs = new ArrayList<>();
            List<Expr> nodeInputs = new ArrayList<>();
            List<IdExpr> nodeOutputs = new ArrayList<>();
            
            //if the clock was explicitly referenced it may
            //have already been created. Don't add it to the
            //input list if it was already created
            IdExpr clockExpr = new IdExpr(agreeNode.clockVar.id);
            AgreeVarDecl agreeInputVar = new AgreeVarDecl(agreeNode.clockVar.id, null, null);
            
            if(!this.inputVars.contains(agreeInputVar)){
            	inputs.add(agreeNode.clockVar);
            	//make it so the clock is visible in the counter example
            	addToRenaming(clockExpr.id, clockExpr.id);
            	refMap.put(clockExpr.id, subEmitter.curComp);
            	layout.addElement(subEmitter.category, clockExpr.id, SigType.INPUT);
            }
            
            clocks.add(clockExpr);

            
            agreeInputVars.addAll(subEmitter.inputVars);
            agreeInternalVars.addAll(subEmitter.internalVars); 
            //also add new assumption, assertion, and guarantee vars
            for(Expr expr : agreeNode.assertions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl();
                agreeVar.aadlStr = agreeVar.jKindStr = varId.id;
                agreeVar.type = "bool";
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.assumptions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl();
                agreeVar.aadlStr = agreeVar.jKindStr = varId.id;
                agreeVar.type = "bool";
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.guarantees){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl();
                agreeVar.aadlStr = agreeVar.jKindStr = varId.id;
                agreeVar.type = "bool";
                agreeInternalVars.add(agreeVar);
            }
            
            //TODO: set different initial values for outputs
            for(VarDecl var : agreeNode.outputs){
            	NamedType type = (NamedType)var.type;
            	//queue in and out variables need to be false initially
            	//if(var.id.startsWith(queueInClockPrefix)
            	//		|| var.id.startsWith(queueOutClockPrefix)){
            	//	initOutputs.add(new BoolExpr(false));
            	//}else{

            		initOutputs.add(initTypeMap.get(type.name));
            		nodeOutputs.add(new IdExpr(var.id));
            	//}
            }

            for(VarDecl var : agreeNode.inputs){
                nodeInputs.add(new IdExpr(var.id));
            }

            NodeCallExpr nodeCall = new NodeCallExpr(agreeNode.mainNode.id, nodeInputs);
            
            CondactExpr condact = new CondactExpr(clockExpr, nodeCall, initOutputs);
            Equation condactEq = new Equation(nodeOutputs, condact);
            eqs.add(condactEq);
            
            addToRenamingAll(subEmitter.varRenaming);
            refMap.putAll(subEmitter.refMap);
            
            //create the hold equations for the subcomponent outputs
            for(AgreeVarDecl varDec : subEmitter.outputVars){
                AgreeVarDecl dummyAgreeVar = new AgreeVarDecl();
                dummyAgreeVar.type = varDec.type;
                dummyAgreeVar.jKindStr = "___DUMMY_" + varDec.jKindStr;
                agreeInputVars.add(dummyAgreeVar);
                agreeInternalVars.add(varDec);
                
                IdExpr dummyId = new IdExpr(dummyAgreeVar.jKindStr);
                IdExpr clockId = new IdExpr(agreeNode.clockVar.id);
                IdExpr outId = new IdExpr(varDec.jKindStr);
                Expr preExpr = new BinaryExpr(dummyId, BinaryOp.ARROW, new UnaryExpr(UnaryOp.PRE, outId));
                
                Expr ifElse = new IfThenElseExpr(clockId, dummyId, preExpr);
                Equation clockOutEq = new Equation(outId, ifElse);
                eqs.add(clockOutEq);
            }

        }
	}


	private void addSubcomponentAssumptions(List<AgreeAnnexEmitter> subEmitters,
			List<Equation> eqs, List<VarDecl> internals,
			List<String> properties, Expr totalCompHist, Expr sysAssumpHist,
			IdExpr allClksTickedExpr, Map<Subcomponent, Set<Subcomponent>> closureMap, IdExpr countId) {
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
            leftSide = new BinaryExpr(sysAssumpHist, BinaryOp.AND, leftSide);
            leftSide = new BinaryExpr(higherContracts, BinaryOp.AND, leftSide);
            IdExpr clockVarId = new IdExpr(subEmitter.agreeNode.clockVar.id);
            leftSide = new BinaryExpr(clockVarId, BinaryOp.AND, leftSide);

            Expr contrHistExpr = new BinaryExpr(leftSide, BinaryOp.IMPLIES, contrAssumps);
            Equation contrHist = new Equation(compId, contrHistExpr);
            eqs.add(contrHist);
            properties.add(compId.id);
            assumProps.add(compId.id);
            String propertyName = subEmitter.category + " Assumptions";
            addToRenaming(compId.id, propertyName);
            //layout.addElement("Top", propertyName, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, propertyName, AgreeLayout.SigType.OUTPUT);
            
            
            //add a property that is true if the contract is a contradiction
            IdExpr contrHistId = new IdExpr("__CONTR_HIST_" + subEmitter.category);
            IdExpr consistId = new IdExpr("__NULL_CONTR_HIST_" + subEmitter.category);

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
        
}
