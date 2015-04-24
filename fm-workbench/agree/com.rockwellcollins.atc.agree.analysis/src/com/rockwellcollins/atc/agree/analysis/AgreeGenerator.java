package com.rockwellcollins.atc.agree.analysis;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.Location;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.RecordType;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.annexsupport.AnnexUtil;

import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;

public class AgreeGenerator {

    private static final String nodeGuarName = "__GUARANTEE";
    private static final String nodeAssumeName = "__ASSUME";
    private static final String nodeLemmaName = "__LEMMA";
    
    private static boolean doSwitchAgreeAnnex(AgreeEmitterState state, ComponentClassifier comp){
    	 for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(comp, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
             if (annex instanceof AgreeContractSubclause) {
                 //in newer versions of osate the annex this returns annexes in the type
                 //as well as the implementation. We want the annex in the specific component
                 EObject container = annex.eContainer();
                 while(!(container instanceof ComponentClassifier)){
                     container = container.eContainer();
                 }
                 if(container == comp){
                     state.doSwitch(annex);
                     return true;
                 }
             }
         }
    	 return false;
    }
    
    private static Program getAssumeGuaranteeProgram(AgreeEmitterState state){
        Node subNode = nodeFromState(state, false);
    	List<String> properties = new ArrayList<>();
    	List<Node> nodes = new ArrayList<>(state.nodeDefExpressions);
    	
    	if(subNode.outputs.size() != 1){
    		throw new AgreeException("Something went wrong with node generation");
    	}
    	
    	//add the assertions to the system level assumptions
    	List<Expr> assertions = new ArrayList<>();
    	int assumeIndex = 0;
    	for(Equation assumEq : state.assumpExpressions){
    		assertions.add(new IdExpr(nodeAssumeName+assumeIndex++));
    	}
    	assertions.addAll(subNode.assertions);
    	
    	Expr clockHolds = getClockHoldExprs(state);
    	assertions.add(clockHolds);

    	//get the guarantees as properties and add them to the renaming
    	int i = 0;
    	List<VarDecl> locals = new ArrayList<>();
    	List<VarDecl> inputs = new ArrayList<>();
    	List<Equation> equations = new ArrayList<>();
    	
    	for(Equation eq : state.guarExpressions){
    		String propName = nodeGuarName+i++;
    		state.renaming.addExplicitRename(propName, eq.lhs.get(0).id);
    		properties.add(propName);
    		state.guarProps.add(propName);
    		//have to add guarantees as local variables for kind 2.0
    		locals.add(new VarDecl(propName, NamedType.BOOL));
    		equations.add(new Equation(new IdExpr(propName), eq.expr));
    	}
    	
    	for(Equation eq : state.lemmaExpressions){
            String propName = nodeGuarName+i++;
            state.renaming.addExplicitRename(propName, eq.lhs.get(0).id);
            properties.add(propName);
            state.guarProps.add(propName);
            //have to add guarantees as local variables for kind 2.0
            locals.add(new VarDecl(propName, NamedType.BOOL));
            equations.add(new Equation(new IdExpr(propName), eq.expr));
        }
    	
    	//remove guarantee variables from inputs for kind 2.0
    	for(VarDecl var : subNode.inputs){
    	    if(!var.id.startsWith("__GUARANTEE")){
    	        inputs.add(var);
    	    }
    	}
    	locals.addAll(subNode.locals);
    	equations.addAll(subNode.equations);
    	Node mainNode = new Node(subNode.location, subNode.id, inputs, subNode.outputs,
    			locals, equations, properties, assertions,
    			null, null, null);
    	
    	nodes.add(mainNode);
    	
    	//have to convert the type expressions
    	List<TypeDef> typeDefs = new ArrayList<>();
    	for(RecordType type : state.typeExpressions){
    		TypeDef typeDef = new TypeDef(type.id, type);
    		typeDefs.add(typeDef);
    	}
    	
    	//also add a new top level category to the layout
    	state.layout.addCategory(state.curInst.getName());
    	    	
    	Program assumeGuaranteeProgram = new Program(typeDefs, null, nodes);
    	
    	return assumeGuaranteeProgram;
    }
    
    private static Program getConsistProgram(AgreeEmitterState state){
    	
    	IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
    	int consistDetph = prefs.getInt(PreferenceConstants.PREF_CONSIST_DEPTH);

    	Node subNode = nodeFromState(state, false);
    	List<VarDecl> locals = new ArrayList<>(subNode.locals);
    	List<String> props = new ArrayList<>();
    	List<Equation> eqs = new ArrayList<>(subNode.equations);
    	
    	//make the counter first
    	VarDecl consistCountVar = new VarDecl("__CONSIST_COUNTER", NamedType.INT);
    	IdExpr consistCountId = new IdExpr(consistCountVar.id);
    	Expr consistCountExpr = new UnaryExpr(UnaryOp.PRE, consistCountId);
    	consistCountExpr = new BinaryExpr(consistCountExpr, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
    	consistCountExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, consistCountExpr);
    	
    	Equation consistCountEq = new Equation(consistCountId, consistCountExpr);
    	locals.add(consistCountVar);
    	eqs.add(consistCountEq);
    	
    	Expr countEqExpr = new BinaryExpr(consistCountId, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(consistDetph)));
    	
    	//get the "this contract consistency" property
    	Expr sysGuarConjExpr = new BoolExpr(true);
    	for(Equation guarEq : state.guarExpressions){
    		sysGuarConjExpr = new BinaryExpr(sysGuarConjExpr, BinaryOp.AND, guarEq.expr);
    	}
    	Expr sysAssumConjExpr = new BoolExpr(true);
    	for(Equation assumEq : state.assumpExpressions){
    		sysAssumConjExpr = new BinaryExpr(sysAssumConjExpr, BinaryOp.AND, assumEq.expr);
    	}
    	
    	VarDecl sysAssumConjVar = new VarDecl("___SYS_ASSUM_CONJ", NamedType.BOOL);
    	VarDecl sysGuarConjVar = new VarDecl("___SYS_GUAR_CONJ", NamedType.BOOL);
    	VarDecl thisConsistVar = new VarDecl("___CONTR_CONSIST", NamedType.BOOL);
    	VarDecl sysAssumHist = new VarDecl("___SYS_ASSUM_HIST", NamedType.BOOL);
    	VarDecl sysGuarHist = new VarDecl("___SYS_GUAR_HIST", NamedType.BOOL);
    	locals.add(sysAssumHist);
    	locals.add(sysAssumConjVar);
    	locals.add(thisConsistVar);
    	locals.add(sysGuarHist);
    	locals.add(sysGuarConjVar);
    	
    	IdExpr assumId = new IdExpr(sysAssumConjVar.id);
    	Equation sysAssumConjEq = new Equation(assumId, sysAssumConjExpr);
    	eqs.add(sysAssumConjEq);
    	
    	IdExpr guarId = new IdExpr(sysGuarConjVar.id);
    	Equation sysGuarConjEq = new Equation(guarId, sysGuarConjExpr);
    	eqs.add(sysGuarConjEq);	
    	
    	IdExpr sysAssumHistId = new IdExpr(sysAssumHist.id);
    	Equation assumHistEq = getHistEq(sysAssumHist, assumId);
    	eqs.add(assumHistEq);
    	
    	IdExpr sysGuarHistId = new IdExpr(sysGuarHist.id);
    	Equation guarHistEq = getHistEq(sysGuarHist, guarId);
    	eqs.add(guarHistEq);
    	
    	Expr consistExpr = new BinaryExpr(sysAssumHistId, BinaryOp.AND, sysGuarHistId);
    	consistExpr = new BinaryExpr(consistExpr, BinaryOp.AND, countEqExpr);
    	consistExpr = new UnaryExpr(UnaryOp.NOT, consistExpr);
    	
    	Equation thisConsistEq = new Equation(new IdExpr(thisConsistVar.id), consistExpr);
    	eqs.add(thisConsistEq);

    	props.add(thisConsistVar.id);
    	
    	state.renaming.addExplicitRename(thisConsistVar.id, "This Contract Consistency");
    	
    	if( state.subcomponentConsistClocks.size() != state.subcomponentExprs.size()){
    	    throw new AgreeException("Something went wrong generating the constraints for consistency checking");
    	}
    	
    	//get the composition and individual consistency checks
    	Expr subExprConjExpr = new BoolExpr(true);
    	int subIndex = 0;
    	for(Equation subEq : state.subcomponentExprs){
    		subExprConjExpr = new BinaryExpr(subExprConjExpr, BinaryOp.AND, subEq.expr);
    		    		
    		String compName = subEq.lhs.get(0).id;
    		Expr condactClock = state.subcomponentConsistClocks.get(subIndex++);

    		VarDecl subHistVar = new VarDecl("___SUB__"+compName+"__HIST", NamedType.BOOL);
    		IdExpr subHistId = new IdExpr(subHistVar.id);
    		//for consistency of a single subcomponent we assert that the clock is always ticking
    		Equation subHistEq = getHistEq(subHistVar, new BinaryExpr(subEq.expr, BinaryOp.AND, condactClock));
    		locals.add(subHistVar);
    		eqs.add(subHistEq);
    		
    		VarDecl subConsistVar = new VarDecl("___CONSIST__"+compName, NamedType.BOOL);
    		locals.add(subConsistVar);
    		Expr subConsistExpr = new BinaryExpr(countEqExpr, BinaryOp.AND, subHistId);
    		subConsistExpr = new UnaryExpr(UnaryOp.NOT, subConsistExpr);
    		Equation subConsistEq = new Equation(new IdExpr(subConsistVar.id), subConsistExpr);
    		eqs.add(subConsistEq);
    		props.add(subConsistVar.id);
    		state.renaming.addExplicitRename(subConsistVar.id, compName + " Consistency");
    	}
    	
    	//get the subcomponent composition consistency
    	//first get the assertion history
    	VarDecl assertConjVar = new VarDecl("__ASSERT_CONJ", NamedType.BOOL);
    	IdExpr assertConjId = new IdExpr(assertConjVar.id);
    	Expr assertConjExpr = new BoolExpr(true);
    	for(Expr assertExpr : state.assertExpressions){
    		assertConjExpr = new BinaryExpr(assertConjExpr, BinaryOp.AND, assertExpr);
    	}
    	
    	locals.add(assertConjVar);
    	Equation assertConjEq = new Equation(assertConjId, assertConjExpr);
    	eqs.add(assertConjEq);
    	
    	VarDecl assertHistVar = new VarDecl("___ASSERT_HIST", NamedType.BOOL);
    	IdExpr assertHistId = new IdExpr(assertHistVar.id);
    	Equation assertHistEq = getHistEq(assertHistVar, assertConjId);
    	locals.add(assertHistVar);
    	eqs.add(assertHistEq);
    	
    	VarDecl subCompConjVar = new VarDecl("___SUBCOMP_CONJ", NamedType.BOOL);
    	IdExpr subCompConjId = new IdExpr(subCompConjVar.id);
    	Equation subCompConjEq = new Equation(subCompConjId, subExprConjExpr);
    	locals.add(subCompConjVar);
    	eqs.add(subCompConjEq);
    	
    	VarDecl subCompExprHistVar = new VarDecl("__SUBCOMP_HIST", NamedType.BOOL);
    	IdExpr subCompExprHistId = new IdExpr(subCompExprHistVar.id);
    	Equation subCompHistEq = getHistEq(subCompExprHistVar, subCompConjId);
    	locals.add(subCompExprHistVar);
    	eqs.add(subCompHistEq);
    	
    	Expr clockHolds = getClockHoldExprs(state);
    	
    	Expr compositionConsistExpr = new BinaryExpr(assertHistId, BinaryOp.AND, sysAssumHistId);
    	compositionConsistExpr = new BinaryExpr(compositionConsistExpr, BinaryOp.AND, sysGuarHistId);
    	compositionConsistExpr = new BinaryExpr(compositionConsistExpr, BinaryOp.AND, subCompExprHistId);
    	compositionConsistExpr = new BinaryExpr(compositionConsistExpr, BinaryOp.AND, countEqExpr);
    	compositionConsistExpr = new BinaryExpr(compositionConsistExpr, BinaryOp.AND, clockHolds);
    	compositionConsistExpr = new UnaryExpr(UnaryOp.NOT, compositionConsistExpr);
    	
    	VarDecl compositionConsistVar = new VarDecl("___COMPOSITION_CONSIST", NamedType.BOOL);
    	Equation compositionConsistEq = new Equation(new IdExpr(compositionConsistVar.id), compositionConsistExpr);
    	locals.add(compositionConsistVar);
    	eqs.add(compositionConsistEq);
    	props.add(compositionConsistVar.id);
    	
    	state.renaming.addExplicitRename(compositionConsistVar.id, "Subcomponent Composition Consistency");
    	
    	Node consistNode = new Node(subNode.id, subNode.inputs, subNode.outputs, locals, eqs, props);
    	List<Node> nodes = new ArrayList<>();
    	//strip the properties from the other nodes
    	for(Node node : state.nodeDefExpressions){
    		nodes.add(new Node(node.id, node.inputs, node.outputs, node.locals, 
    				node.equations, null));
    	}
    	
    	nodes.add(consistNode);
    	
    	//have to convert the type expressions
    	List<TypeDef> typeDefs = new ArrayList<>();
    	for(RecordType type : state.typeExpressions){
    		TypeDef typeDef = new TypeDef(type.id, type);
    		typeDefs.add(typeDef);
    	}
    	
    	Program program = new Program(typeDefs, null, nodes);
    	state.consistProps.addAll(props);
    	
    	return program;
    }
    
 public static AgreeProgram getRealizabilityLustre(ComponentInstance compInst){
        
        
        ComponentType compType = AgreeEmitterUtilities.getInstanceType(compInst);
        AgreeEmitterState state = new AgreeEmitterState(compInst, null);
        FeatureUtils.recordFeatures(state, true);
        boolean result = doSwitchAgreeAnnex(state, compType);
        
        if(!result){
            throw new AgreeException("No contract to verify for analysis");

        }
        
        Node subNode = nodeFromState(state, false);
        
        List<Node> nodes = new ArrayList<>(state.nodeDefExpressions);
        
        if(subNode.outputs.size() != 1){
            throw new AgreeException("Something went wrong with node generation");
        }
        
        //add the assertions to the system level assumptions
        List<Expr> assertions = new ArrayList<>();
        for(Equation assumEq : state.assumpExpressions){
            assertions.add(assumEq.expr);
        }
        assertions.addAll(state.assertExpressions);
        assertions.addAll(subNode.assertions);

        //get the guarantees as properties and add them to the renaming
        int i = 0;
        for(Equation eq : state.guarExpressions){
            String propName = nodeGuarName+i++;
            state.renaming.addExplicitRename(propName, eq.lhs.get(0).id);
            state.guarProps.add(propName);
        }
        
        //get the names of the inputs for realizability checking
        List<String> inputStrs = new ArrayList<String>();
        for(VarDecl inputVar : state.inputVars){
            inputStrs.add(inputVar.id);
        }
        
        Node mainNode = new Node(subNode.location, subNode.id, subNode.inputs, subNode.outputs,
                subNode.locals, subNode.equations, state.guarProps, assertions,
                null, null, Optional.of(inputStrs));
        
        nodes.add(mainNode);
        
        //have to convert the type expressions
        List<TypeDef> typeDefs = new ArrayList<>();
        for(RecordType type : state.typeExpressions){
            TypeDef typeDef = new TypeDef(type.id, type);
            typeDefs.add(typeDef);
        }
        
        //also add a new top level category to the layout
        state.layout.addCategory(state.curInst.getName());
        Program realizeProgram = new Program(typeDefs, null, nodes);
//        realizeProgram = InlineAssumptionGuarantees.program(realizeProgram);
        
        AgreeProgram agreeProgram = new AgreeProgram();
        agreeProgram.realizeProgram = realizeProgram;
        agreeProgram.state = state;
        
        return agreeProgram;

    }
    
    private static Equation getHistEq(VarDecl var, Expr expr){
    	IdExpr varId = new IdExpr(var.id);
    	Expr histExpr = new UnaryExpr(UnaryOp.PRE, varId);
    	histExpr = new BinaryExpr(histExpr, BinaryOp.AND, expr);
    	histExpr = new BinaryExpr(expr, BinaryOp.ARROW, histExpr);
    	Equation histEq = new Equation(varId, histExpr);
    	return histEq;
    }

    public static AgreeProgram getLustre(ComponentInstance compInst, boolean monolithic){
    	
    	AgreeProgram agreeProgram = new AgreeProgram();
    	AgreeEmitterState state;
    	
    	Subcomponent subComp = compInst.getSubcomponent();
    	
    	if(monolithic){
    		state = generateMonolithic(compInst, subComp);
    	}else{
    		state = generateSingleLayer(compInst, subComp);
    	}
    	
    	agreeProgram.state = state;
    	agreeProgram.assumeGuaranteeProgram = getAssumeGuaranteeProgram(state);
    	agreeProgram.consistProgram = getConsistProgram(state);
    	
    	return agreeProgram;
    }
    
    
    private static AgreeEmitterState generateSingleLayer(ComponentInstance compInst,
    		Subcomponent comp){
    	 
    	ComponentType compType = AgreeEmitterUtilities.getInstanceType(compInst);
    	ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
       
    	AgreeEmitterState state = 
        		new AgreeEmitterState(compInst, comp);
    	
    	FeatureUtils.recordFeatures(state, true);
    	boolean result = doSwitchAgreeAnnex(state, compType);
    	
        if(!result){
        	throw new AgreeException("No contract to verify for analysis");

        }
        if(compImpl == null){
        	throw new AgreeException("No implementation to verify");
        }

        doSwitchAgreeAnnex(state, compImpl);
        
        //note that doSwitch needs to be called on the annex of
        //the implementation before the connections are recorded
        //this is only becaus the semantics of event ports changes
        //depending on if you are doing "asynchronous" or "latched" analysis
        ConnectionUtils.recordConnections(state);

        List<String> ordering = new ArrayList<>();
        //go through the component implementation and build a program for each subcomponent
        for(Subcomponent subComp : compImpl.getAllSubcomponents()){
        	ComponentInstance subCompInst = compInst.findSubcomponentInstance(subComp);
        	String subCompPrefix = subComp.getName()+"__";
        	AgreeEmitterState subState = new AgreeEmitterState(subCompInst, subComp);
        	ComponentType subCompType = AgreeEmitterUtilities.getInstanceType(subCompInst);

        	FeatureUtils.recordFeatures(subState, false);
        	boolean subResult = doSwitchAgreeAnnex(subState, subCompType);
        	if(subResult){
        		Node subNode = nodeFromState(subState, true);
        		ordering.add(subNode.id);
        		addSubcomponentNodeCall(subCompPrefix, state, subState, subNode, false);
        	}
        }

        //handle any quasi-synchronous constraints
        addQSConstraints(state);	

    	return state;
    }
    
    
    private static AgreeEmitterState generateMonolithic(ComponentInstance compInst,
    		Subcomponent comp){
    	 
    	ComponentType compType = AgreeEmitterUtilities.getInstanceType(compInst);
    	ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
       
    	AgreeEmitterState state = 
        		new AgreeEmitterState(compInst, comp);
    	
    	FeatureUtils.recordFeatures(state, true);
    	boolean result = doSwitchAgreeAnnex(state, compType);
    	
        if(!result && compImpl == null){
        	//we cannot drill down any further
        	return null;
        }
        
        if(compImpl != null){
        	ConnectionUtils.recordConnections(state);
        	 boolean foundSubAnnex = doSwitchAgreeAnnex(state, compImpl);

        	//go through the component implementation and build a program for each subcomponent
        	for(Subcomponent subComp : compImpl.getAllSubcomponents()){
        		ComponentInstance subCompInst = compInst.findSubcomponentInstance(subComp);
        		String subCompPrefix = subComp.getName()+"__";
        		AgreeEmitterState subState = generateMonolithic(subCompInst, subComp);
        		if(subState != null){
        			foundSubAnnex = true;
        			Node subNode = nodeFromState(subState, false);
        			addSubcomponentNodeCall(subCompPrefix, state, subState, subNode, true);
        		}
        	}
        	if(!foundSubAnnex && !result){
        		return null;
        	}
        	//handle any quasi-synchronous constraints
            addQSConstraints(state);	
        }
    	
    	return state;
    }

	private static void addQSConstraints(AgreeEmitterState state) {
    	String dfaNodeName = "__DFA_NODE_"+state.curInst.getComponentInstancePath();
    	dfaNodeName = dfaNodeName.replace(".", "__");
    	String calenNodeName = "__CALENDAR_NODE_"+state.curInst.getInstanceObjectPath();
    	calenNodeName = calenNodeName.replace(".", "__");
    	
    	Expr clockAssertion = null;
    	if(state.latchedClocks || state.asynchClocks){
    	    //the only constraint that will get made is one
    	    //asserting that atleast one clock ticks.  This should
    	    //happen after this if then else block
    	    clockAssertion = new BoolExpr(true);
    	}else if(state.mnSyncEls.size() != 0){
    	    //this set is used to make sure that we do not make a definition for the same
    	    //calendar twice (a user might have multiple 3-2 synchrony constraints for example)
    	    Set<String> nodeNames = new HashSet<>();
    	    clockAssertion = new BoolExpr(true);

    	    for(MNSynchronyElement elem : state.mnSyncEls){
    	        String nodeName = "__calendar_node_"+elem.max+"_"+elem.min;
    	        if(!nodeNames.contains(nodeName)){
    	            nodeNames.add(nodeName);
    	            Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, elem.max, elem.min);
    	            state.nodeDefExpressions.add(calNode);
    	        }
    	        NodeCallExpr nodeCall = new NodeCallExpr(nodeName, elem.maxClock, elem.minClock);
    	        clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
    	        nodeCall = new NodeCallExpr(nodeName, elem.minClock, elem.maxClock);
    	        clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
    	    }
    	}else if(state.calendar.size() != 0){
    	    List<Expr> clockIds = new ArrayList<>();
        	for(AgreeVarDecl clockVar : state.clockVars){
        		clockIds.add(new IdExpr(clockVar.id));
        	}
        	
        	Node calNode = AgreeCalendarUtils.getExplicitCalendarNode(calenNodeName, state.calendar, clockIds);
        	state.nodeDefExpressions.add(calNode);
        	state.assertExpressions.add(new NodeCallExpr(calNode.id, clockIds));
        	
        }else if(state.synchrony != 0){
            List<Expr> clockIds = new ArrayList<>();
            for(AgreeVarDecl clockVar : state.clockVars){
                clockIds.add(new IdExpr(clockVar.id));
            }
            if(state.synchrony2 == 0){
                Node dfaNode = AgreeCalendarUtils.getDFANode(dfaNodeName, state.synchrony); 
                Node calNode = AgreeCalendarUtils.getCalendarNode(calenNodeName, dfaNode.id, state.clockVars.size());
                state.nodeDefExpressions.add(dfaNode);
                state.nodeDefExpressions.add(calNode);

                clockAssertion = new NodeCallExpr(calNode.id, clockIds);
            }else{
                String nodeName = "__calendar_node_"+state.synchrony+"_"+state.synchrony2;
                Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, state.synchrony, state.synchrony2);
                state.nodeDefExpressions.add(calNode);
                clockAssertion = new BoolExpr(true);
                int i,j;
                for(i = 0; i < clockIds.size(); i++){
                    Expr clock1 = clockIds.get(i);
                    for(j = i+1; j < clockIds.size(); j++){
                        Expr clock2 = clockIds.get(j);
                        NodeCallExpr nodeCall = new NodeCallExpr(nodeName, clock1, clock2);
                        clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
                        nodeCall = new NodeCallExpr(nodeName, clock2, clock1);
                        clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
                    }
                }
            }
            //don't let multiple clocks tick together
            if(!state.simultaneity){
                Expr onlyOneTick = AgreeCalendarUtils.getSingleTick(clockIds);
                clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, onlyOneTick);
            }
        }else{ //completely synchronous
            clockAssertion = new BoolExpr(true);
        	for(AgreeVarDecl clockVar : state.clockVars){
        		clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, new IdExpr(clockVar.id));
        	}
        }
    	//assert that at least one clock ticks
    	if(!(state.latchedClocks || state.asynchClocks)){
    	    if(state.clockVars.size() != 0){
    	        Expr oneMustTick = new BoolExpr(false);
    	        for(AgreeVarDecl clockVar : state.clockVars){
    	            oneMustTick = new BinaryExpr(oneMustTick, BinaryOp.OR, new IdExpr(clockVar.id));
    	        }
    	        clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, oneMustTick);
    	    }
    	    state.assertExpressions.add(clockAssertion);
    	}
	}

	private static void addSubcomponentNodeCall(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, Node subNode, boolean monolithic) {
		
		//get the id for the subcomponent clock
		IdExpr clockId = addSubcompClock(state, subState);
		
		//add the and output variables and hold equations
		//create the hold equation for subcomponent outputs
		addOutputsAndHolds(prefix, state, subState, clockId);
		
		//add the input variables
		addInputs(prefix, state, subState);
		
		//add assumption and guarantee variables
		addAssumeGuaranteesLemmaNodeProps(prefix, state, subState, clockId, monolithic);
		
		//add the all of the sub categories
		addCategories(prefix, state, subState);
		
		//add all the nodes defined by substates
		addNodes(state, subState);
		
		//add all the "initially" expressions
		addInitialConstraints(prefix, state, subState, clockId);
		
		//add the call to the subcomponent node and wrap it in a condact
		//this call also records information for consistency checking
		addNodeAndCondactCall(prefix, state, subState, subNode, clockId);
		
		//add subcomponent variable references (for cex output)
		addReferences(prefix, state, subState);
		
		//add node lemma renamings
		//addNodeLemmaRenamings(state, subState);
		
		//includes renaming of assumptions for subnodes
		state.renaming.addRenamings(subState.renaming);

	}

    private static void addAssumeGuaranteesLemmaNodeProps(String prefix,
            AgreeEmitterState state, AgreeEmitterState subState, Expr clockId, boolean monolithic) {
        int i = 0;
        
        if(state.latchedClocks){
            //change the clock to only be high on the falling edge
            Expr fallingEdge = new UnaryExpr(UnaryOp.NOT, clockId);
            fallingEdge = new BinaryExpr(new UnaryExpr(UnaryOp.PRE, clockId), BinaryOp.AND, fallingEdge);
            fallingEdge = new BinaryExpr(new BoolExpr(false), BinaryOp.ARROW, fallingEdge);
            clockId = fallingEdge;
        }
        
        ComponentClassifier compClass = subState.curInst.getComponentClassifier();
        for(Equation guarEq : subState.guarExpressions){
            AgreeVarDecl guarVar = new AgreeVarDecl(prefix+nodeGuarName+i++, NamedType.BOOL);
            state.inputVars.add(guarVar);
            if(monolithic && compClass instanceof ComponentImplementation){
                //add guarantees as lemmas if we are doing monolithic verification
                //and this is a component implementation
                Expr lemmaExpr = new BinaryExpr(clockId, BinaryOp.IMPLIES, new IdExpr(guarVar.id));
                String lemmaStr = guarEq.lhs.get(0).id;
                //stupid hack for renaming
                
                lemmaStr = prefix.replace("__", " guarantee: ")+ "\""+lemmaStr+"\"";
                state.lemmaExpressions.add(new Equation(new IdExpr(lemmaStr), lemmaExpr));
                state.refMap.put(guarVar.id, subState.refMap.get(guarEq.lhs.get(0).id));
            }
        }
        
        for(Equation lemmaEq : subState.lemmaExpressions){
            AgreeVarDecl lemmaVar = new AgreeVarDecl(prefix+nodeGuarName+i++, NamedType.BOOL);
            state.inputVars.add(lemmaVar);
            Expr lemmaExpr = new BinaryExpr(clockId, BinaryOp.IMPLIES, new IdExpr(lemmaVar.id));
            String lemmaStr = lemmaEq.lhs.get(0).id;
            //stupid hack for renaming
            String replaceStr = lemmaStr.startsWith("lemma :") ? " " : ".";
            lemmaStr = prefix.replace("__", replaceStr)+lemmaStr;
            state.lemmaExpressions.add(new Equation(new IdExpr(lemmaStr), lemmaExpr));
            state.refMap.put(lemmaVar.id, subState.refMap.get(lemmaEq.lhs.get(0).id));
        }
        i = 0; 
        for(Equation assumeEq : subState.assumpExpressions){
            AgreeVarDecl assumeVar = new AgreeVarDecl(prefix+nodeAssumeName+i++, NamedType.BOOL);
            state.inputVars.add(assumeVar);
            Expr assumeExpr = new BinaryExpr(clockId, BinaryOp.IMPLIES, new IdExpr(assumeVar.id));
            String assumeStr = assumeEq.lhs.get(0).id;
            //stupid hack for renaming
            String replaceStr = assumeStr.startsWith("assume :") ? " " : ".";
            assumeStr = prefix.replace("__", replaceStr)+assumeStr;
            state.lemmaExpressions.add(new Equation(new IdExpr(assumeStr), assumeExpr));
            state.refMap.put(assumeStr, subState.refMap.get(assumeEq.lhs.get(0).id));
            state.renaming.addExplicitRename(assumeVar.id, assumeStr);
        }
    }

    private static void addNodes(AgreeEmitterState state,
            AgreeEmitterState subState) {

        state.nodeDefExpressions.addAll(subState.nodeDefExpressions);
    }

	private static Expr getClockHoldExprs(AgreeEmitterState state){
        //for monolithic verification we need to add assertions that
        //no clock within a subcomponent ticks unless it's parent clock
        //ticks
        
        //first get all the clock variables
        List<IdExpr> clockIds = new ArrayList<>();
        for(AgreeVarDecl var : state.inputVars){
            if(var.id.endsWith(state.clockIDSuffix)){
                clockIds.add(new IdExpr(var.id));
            }
        }

        Expr clockHolds = new BoolExpr(true);
        boolean foundChildClocks = false;
        for(IdExpr parentClockId : clockIds){
            Expr notParent = new UnaryExpr(UnaryOp.NOT, parentClockId);
            String prefix = parentClockId.id.replace(state.clockIDSuffix, "")+"__";
            Expr notClocks = new BoolExpr(true);
            for(IdExpr childClockId : clockIds){
                if(childClockId.id.startsWith(prefix) && !childClockId.id.equals(parentClockId.id)){
                    Expr notThisClock = new UnaryExpr(UnaryOp.NOT, new IdExpr(childClockId.id));
                    notClocks = new BinaryExpr(notClocks, BinaryOp.AND, notThisClock);
                    foundChildClocks = true;
                }
            }
            notClocks = new BinaryExpr(notParent, BinaryOp.IMPLIES, notClocks);
            clockHolds = new BinaryExpr(clockHolds, BinaryOp.AND, notClocks);
        }
        
        //this should always be true for the single layers case
        if(!foundChildClocks){
        	return new BoolExpr(true);
        }

        return clockHolds;
    }
	

	private static IdExpr addSubcompClock(AgreeEmitterState state,
			AgreeEmitterState subState) {
		IdExpr clockId = new IdExpr(subState.curComp.getName()+state.clockIDSuffix);
		//the clockId may or may not already be a part of the inputs
		AgreeVarDecl clockVar = new AgreeVarDecl(clockId.id, NamedType.BOOL);
		state.refMap.put(clockId.id, subState.curComp);
		state.inputVars.add(clockVar);
		state.clockVars.add(clockVar);
		return clockId;
	}

	private static void addReferences(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState) {
		for(Entry<String, EObject> entry : subState.refMap.entrySet()){
			String newName = prefix+entry.getKey();
			state.refMap.put(newName, entry.getValue());
		}
		for(Entry<String, FeatureInstance> entry : subState.featInstMap.entrySet()){
		    String newName = prefix+entry.getKey();
		    state.featInstMap.put(newName, entry.getValue());
		}
	}

	private static void addNodeAndCondactCall(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, Node subNode,
			IdExpr clockId) {

		//each node should just have a single boolean output
		//which is initially true
		List<Expr> condactArgs = new ArrayList<>();
        condactArgs.add(new BoolExpr(true));
        Expr nodeCallExpr = null;
        Expr consistClockExpr = null;
		if(state.latchedClocks){
		    
		    //create the input holding node
		    
		    //get the list of internal nodes
		    List<VarDecl> locals = new ArrayList<>();
		    List<VarDecl> inputs = new ArrayList<>();
		    List<VarDecl> outputs = new ArrayList<>();
		    List<Equation> equations = new ArrayList<>();
		    
		    outputs.addAll(subNode.outputs);
	        inputs.addAll(subNode.inputs);
            inputs.add(new VarDecl(clockId.id, NamedType.BOOL));
		    
            List<Expr> callArgs = new ArrayList<>();
            for(VarDecl holdVar : subNode.inputs){
                boolean varIsInput = false;
                for(VarDecl inputVar : subState.inputVars){
                    if(holdVar.id.equals(inputVar.id)){
                        IdExpr holdId = new IdExpr("__HOLD_"+holdVar.id);
                        IdExpr freeId = new IdExpr(holdVar.id);
                        locals.add(new VarDecl(holdId.id, holdVar.type));
                        
                        Expr holdExpr = new IfThenElseExpr(new UnaryExpr(UnaryOp.PRE, clockId),
                                                           new UnaryExpr(UnaryOp.PRE, holdId),
                                                           freeId);
                        holdExpr = new BinaryExpr(freeId, BinaryOp.ARROW, holdExpr);
                        equations.add(new Equation(holdId, holdExpr));
                        callArgs.add(holdId);
                        varIsInput = true;
                        break;
                    }
                }
                if(!varIsInput){
                    callArgs.add(new IdExpr(holdVar.id));
                }
            }

            
            //call the subnode in a condact with the hold equations and new clock expression
            Expr edgeClock = new UnaryExpr(UnaryOp.PRE, clockId);
            edgeClock = new BinaryExpr(edgeClock, BinaryOp.AND, new UnaryExpr(UnaryOp.NOT, clockId));
            edgeClock = new BinaryExpr(new BoolExpr(false), BinaryOp.ARROW, edgeClock);
            
            NodeCallExpr nodeCall = new NodeCallExpr(subNode.id, callArgs);
            CondactExpr condactCall = new CondactExpr(edgeClock, nodeCall, condactArgs);
            
            if(subNode.outputs.size() != 1){
                throw new AgreeException("The subnode should only have a single output");
            }
            VarDecl outputVar = subNode.outputs.get(0);
            
            equations.add(new Equation(new IdExpr(outputVar.id), condactCall));
		    Node holdNode = new Node("__HOLD_"+subNode.id, inputs, outputs, locals, equations);
		    state.nodeDefExpressions.add(holdNode);
		    
		    List<Expr> holdCallArgs = new ArrayList<>();
		    for(VarDecl input : subNode.inputs){
                holdCallArgs.add(new IdExpr(prefix+input.id));
            }
		    holdCallArgs.add(clockId);
		    
		    nodeCallExpr = new NodeCallExpr(holdNode.id, holdCallArgs);
		    consistClockExpr = new UnaryExpr(UnaryOp.PRE, clockId);
		    consistClockExpr = new BinaryExpr(consistClockExpr, BinaryOp.NOTEQUAL, clockId);
		    consistClockExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, consistClockExpr);
		}else{
		    List<Expr> callArgs = new ArrayList<>();

		    for(VarDecl input : subNode.inputs){
	            callArgs.add(new IdExpr(prefix+input.id));
	        }
		    Expr clockExpr;

		    NodeCallExpr nodeCall = new NodeCallExpr(subNode.id, callArgs);

		    if(state.synchrony == 0 && state.calendar.size() == 0 && !state.asynchClocks){
		        clockExpr = new BoolExpr(true);
		    }else{
		        clockExpr = clockId;
		    }
		    nodeCallExpr = new CondactExpr(clockExpr, nodeCall, condactArgs);
		    consistClockExpr = clockId;
		}
		
		 state.assertExpressions.add(nodeCallExpr);
         state.typeExpressions.addAll(subState.typeExpressions);
         state.nodeDefExpressions.add(subNode);
         Equation subCompEq = new Equation(new IdExpr(subState.curComp.getName()), nodeCallExpr);
         state.subcomponentExprs.add(subCompEq); //these are kept track of for checking consistency
         state.subcomponentConsistClocks.add(consistClockExpr);
	}

	private static void addInitialConstraints(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, IdExpr clockId) {
		//re-write the "intially" expression to have the ids in
		//the namespace of the parent
		IdRewriter prefixRewrite = new IdRewriter() {
			public IdExpr rewrite(IdExpr id) {
				return new IdExpr(prefix + id.id);
			}
		};
		IdRewriteVisitor initialExprRewriter = new IdRewriteVisitor(prefixRewrite);

		Expr clockExpr;
		if(state.latchedClocks){
		    //change the clock condition for latched clocks
		    clockExpr = new UnaryExpr(UnaryOp.PRE, clockId);
		    clockExpr = new BinaryExpr(clockExpr, BinaryOp.AND, new UnaryExpr(UnaryOp.NOT, clockId));
		    clockExpr = new BinaryExpr(new BoolExpr(false), BinaryOp.ARROW, clockExpr);
		}else{
		    clockExpr = clockId;
		}
		
        AgreeVarDecl initVar = new AgreeVarDecl("__INITIALIZED_"+subState.curComp.getName(), NamedType.BOOL);
        IdExpr initId = new IdExpr(initVar.id);
        Expr initializedExpr = new UnaryExpr(UnaryOp.PRE, initId);
        initializedExpr = new BinaryExpr(clockExpr, BinaryOp.OR, initializedExpr);
        initializedExpr = new BinaryExpr(clockExpr, BinaryOp.ARROW, initializedExpr);
        Equation initializedEq = new Equation(initId, initializedExpr);
        Expr notInitialzedConstraint = new UnaryExpr(UnaryOp.NOT, initId);
        
        state.internalVars.add(initVar);
        state.eqExpressions.add(initializedEq);
        Expr initConjExpr = new BoolExpr(true);
		for(Expr subInitExpr : subState.initialExpressions){
			Expr newInitExpr = subInitExpr.accept(initialExprRewriter);
			initConjExpr = new BinaryExpr(initConjExpr, BinaryOp.AND, newInitExpr);
		}
		notInitialzedConstraint = new BinaryExpr(notInitialzedConstraint, BinaryOp.IMPLIES, initConjExpr);

        state.assertExpressions.add(notInitialzedConstraint);
        state.initialExpressions.add(initConjExpr);
	}

	private static void addCategories(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState) {
		for(String category : subState.layout.getCategories()){
			String newCat = prefix+category;
			newCat = newCat.replace("__", ".");
			state.layout.addCategory(newCat);
		}
		state.layout.addCategory(prefix.replace("__",""));
	}

	private static void addInputs(final String prefix, AgreeEmitterState state,
			AgreeEmitterState subState) {
		for(AgreeVarDecl input : subState.inputVars){
			state.inputVars.add(new AgreeVarDecl(prefix+input.id, input.type));
		}
		
	}

	private static void addOutputsAndHolds(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, IdExpr clockId) {
		Expr finalSameAsPrev = new BoolExpr(true);

		    for(AgreeVarDecl output : subState.outputVars){
		        String outputStr = prefix+output.id;
		        AgreeVarDecl outputVar = new AgreeVarDecl(outputStr, output.type);

		        //add hold equations for subcomponent outputs
		        IdExpr outputId = new IdExpr(outputVar.id);
		        Expr sameAsPrev = new UnaryExpr(UnaryOp.PRE, outputId);
		        sameAsPrev = new BinaryExpr(sameAsPrev, BinaryOp.EQUAL, outputId);
		        finalSameAsPrev = new BinaryExpr(finalSameAsPrev, BinaryOp.AND, sameAsPrev);
		        state.outputVars.add(outputVar);
		    }

            //add the final hold equations
		    Expr holdPrevExpr = new BoolExpr(true);
		    if(state.latchedClocks){
		        //output vars should always be latched as long as there is not a falling edge
		        Expr latchExpr = new UnaryExpr(UnaryOp.PRE, clockId);
		        latchExpr = new BinaryExpr(latchExpr, BinaryOp.AND, new UnaryExpr(UnaryOp.NOT, clockId));
		        latchExpr = new UnaryExpr(UnaryOp.NOT, latchExpr);
		        holdPrevExpr = new BinaryExpr(latchExpr, BinaryOp.IMPLIES, finalSameAsPrev);
		        holdPrevExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, holdPrevExpr);
		    }else{
		        //output vars should always be latched as long as the clock is low
		        holdPrevExpr = new BinaryExpr(new UnaryExpr(UnaryOp.NOT, clockId), BinaryOp.IMPLIES, finalSameAsPrev);
		        holdPrevExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, holdPrevExpr);
		    }
		    
            state.assertExpressions.add(holdPrevExpr);
		
	}
    
    public static Node nodeFromState(AgreeEmitterState subState, boolean assertConract){
    	
    	//create the body for the subnode
    	String nodeId = getLustreNodeName(subState);
    	List<VarDecl> inputs = new ArrayList<>();
    	List<VarDecl> outputs = new ArrayList<>();
    	List<VarDecl> locals = new ArrayList<>();
    	List<Equation> equations = new ArrayList<>();
    	List<Expr> assumptions = new ArrayList<>();
    	List<Expr> guarantees = new ArrayList<>();
    	List<Expr> assertions = new ArrayList<>();

    	inputs.addAll(subState.inputVars);
    	inputs.addAll(subState.outputVars);
    	locals.addAll(subState.internalVars);
    	
    	ComponentClassifier compClass = subState.curInst.getComponentClassifier();
    	
    	//throw away intermediate guarantees
    	int guarIndex = 0;
    	for(Equation guarEq : subState.guarExpressions){
    	    String guarName = nodeGuarName+guarIndex++;
    	    IdExpr guarId = new IdExpr(guarName);
            guarantees.add(guarId);
    	    Expr guarEquality = new BinaryExpr(guarId, BinaryOp.EQUAL, guarEq.expr);
    	    assertions.add(guarEquality);
    	    inputs.add(new VarDecl(guarName, NamedType.BOOL));
    	}

    	for(Equation lemmaEq : subState.lemmaExpressions){
    	    String lemmaName = nodeGuarName+guarIndex++;
    	    Expr lemmaEquality = new BinaryExpr(new IdExpr(lemmaName), BinaryOp.EQUAL, lemmaEq.expr);
    	    assertions.add(lemmaEquality);        
            inputs.add(new VarDecl(lemmaName, NamedType.BOOL));
    	}

    	int assumeIndex = 0;
    	for(Equation assumEq : subState.assumpExpressions){
    		String assumeName = nodeAssumeName+assumeIndex++;
    		IdExpr assumeId = new IdExpr(assumeName);
            assumptions.add(assumeId);
            Expr assumeEquality = new BinaryExpr(assumeId, BinaryOp.EQUAL, assumEq.expr);
            assertions.add(assumeEquality);
            inputs.add(new VarDecl(assumeName, NamedType.BOOL));
    	}
    	equations.addAll(subState.eqExpressions);
    	assertions.addAll(subState.assertExpressions);
    	
    	IdExpr assertId = new IdExpr("__ASSERT");
    	outputs.add(new VarDecl(assertId.id, NamedType.BOOL));

    	Expr finalAssert;
    	//if this subcomponent is a component type (not an implementation)
    	//the contract bottoms out here and we need to assert it
    	if(compClass instanceof ComponentType || assertConract){
    		finalAssert = assertContract(locals, equations, assumptions, guarantees);	
//    		guarantees = null;
//    		assumptions = null;
    	}else{
    		finalAssert = new BoolExpr(true);
    	}
    	
    	for(Expr assertExpr : assertions){
    		finalAssert = new BinaryExpr(finalAssert, BinaryOp.AND, assertExpr);
    	}
    	
    	Equation assertEq = new Equation(assertId, finalAssert);
    	equations.add(assertEq);
    	
    	Node subNode = new Node(Location.NULL, nodeId, inputs, outputs, locals, equations,
    			null, assertions, null, null, null);
    	
    	return subNode;
    	
    }

	private static String getLustreNodeName(AgreeEmitterState subState) {
		String nodeId = "___Node_"+subState.curInst.getInstanceObjectPath();
		nodeId = nodeId.replace(".", "__");
		return nodeId;
	}
	
	private static Expr assertContract(List<VarDecl> locals,
			List<Equation> equations, List<Expr> assumptions,
			List<Expr> guarantees) {
		//the component has bottomed out so we should assert the contract
	    
//	    IdExpr guarId = new IdExpr("__GUAR_CONJ");
//        VarDecl guarVar = new VarDecl(guarId.id, NamedType.BOOL);
//        locals.add(guarVar);
        
        Expr guarExpr = new BoolExpr(true);
        for(Expr guar : guarantees){
            guarExpr = new BinaryExpr(guarExpr, BinaryOp.AND, guar);
        }
        
//        Equation guarEq = new Equation(guarId, guarExpr);
//        equations.add(guarEq);
	    
	    if(assumptions.size() != 0){
	        IdExpr assumpHistId = new IdExpr("__HIST__ASSUM");
	        VarDecl assumpHistVar = new VarDecl(assumpHistId.id, NamedType.BOOL);
	        IdExpr assumpId = new IdExpr("__ASSUM_CONJ");
	        VarDecl assumpVar = new VarDecl(assumpId.id, NamedType.BOOL);
	        locals.add(assumpHistVar);
	        locals.add(assumpVar);

	        Expr assumpExpr = new BoolExpr(true);
	        for(Expr assum : assumptions){
	            assumpExpr = new BinaryExpr(assumpExpr, BinaryOp.AND, assum);
	        }
	        Equation assumEq = new Equation(assumpId, assumpExpr);
	        equations.add(assumEq);

	        Expr assumpHistExpr = new UnaryExpr(UnaryOp.PRE, assumpHistId);
	        assumpHistExpr = new BinaryExpr(assumpHistExpr, BinaryOp.AND, assumpId);
	        assumpHistExpr = new BinaryExpr(assumpId, BinaryOp.ARROW, assumpHistExpr);
	        Equation assumpHistEq = new Equation(assumpHistId, assumpHistExpr);
	        equations.add(assumpHistEq);
	        return new BinaryExpr(assumpHistId, BinaryOp.IMPLIES, guarExpr);
	    }

		return guarExpr;
		
	}
    
}
