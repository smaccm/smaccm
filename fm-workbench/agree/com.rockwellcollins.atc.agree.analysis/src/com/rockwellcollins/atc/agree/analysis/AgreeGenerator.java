package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jkind.api.results.MapRenaming;
import jkind.api.results.Renaming;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
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
import jkind.results.layout.Layout;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.BusSubcomponent;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.annexsupport.AnnexUtil;

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

public class AgreeGenerator {

    private static boolean doSwitchAgreeAnnex(AgreeEmitterState state, ComponentClassifier comp){
    	 for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(comp, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
             if (annex instanceof AgreeContractSubclause) {
                 state.doSwitch(annex);
                 return true;
             }
         }
    	 return false;
    }
    
    public static Program getLustre(AgreeEmitterState state){
        Node subNode = nodeFromState(state, false);
    	
    	List<Node> nodes = new ArrayList<>(state.nodeDefExpressions);
    	
    	if(subNode.outputs.size() != 1){
    		throw new AgreeException("Something went wrong with node generation");
    	}
    	
    	//add the assertions to the system level assumptions
    	IdExpr assertId = new IdExpr(subNode.outputs.get(0).id);
    	List<Expr> assumptions = new ArrayList<>();
    	for(Equation assumEq : state.assumpExpressions){
    		assumptions.add(assumEq.expr);
    	}
    	assumptions.addAll(state.assertExpressions);
    	assumptions.add(assertId);

    	//get the guarantees as properties and add them to the renaming
    	int i = 0;
    	for(Equation eq : state.guarExpressions){
    		String propName = "~~GUARANTEE"+i++;
    		state.renaming.addExplicitRename(propName, eq.lhs.get(0).id);
    	}
    	
    	Node mainNode = new Node(subNode.location, subNode.id, subNode.inputs, subNode.outputs,
    			subNode.locals, subNode.equations, subNode.properties, null,
    			assumptions, subNode.guarantees);
    	
    	nodes.add(mainNode);
    	
    	//have to convert the type expressions
    	List<TypeDef> typeDefs = new ArrayList<>();
    	for(RecordType type : state.typeExpressions){
    		TypeDef typeDef = new TypeDef(type.id, type);
    		typeDefs.add(typeDef);
    	}
    	
    	//also add a new top level category to the layout
    	state.layout.addCategory(state.curInst.getName());
    	
    	return new Program(typeDefs, null, nodes);
    }
    
    public static Program getLustre(ComponentInstance compInst){
    	
    	AgreeEmitterState state = generate(compInst, null, false);
    	return getLustre(state);
    }
    
    public static AgreeEmitterState generate(ComponentInstance compInst,
    		Subcomponent comp, boolean singleLayer){
    	 
    	ComponentType compType = AgreeEmitterUtilities.getInstanceType(compInst);
    	ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
       
    	AgreeEmitterState state = 
        		new AgreeEmitterState(compInst, comp);
    	
    	FeatureUtils.recordFeatures(state);
    	boolean result = doSwitchAgreeAnnex(state, compType); 
        if(!result){
        	//there is no annex in this component so we do not care
        	//TODO dig into components to check for annexes
        	return null;
        }
        
        if(compImpl != null && !singleLayer){
        	ConnectionUtils.recordConnections(state);
        	doSwitchAgreeAnnex(state, compImpl);

        	//go through the component implementation and build a program for each subcomponent
        	for(Subcomponent subComp : compImpl.getAllSubcomponents()){
        		ComponentInstance subCompInst = compInst.findSubcomponentInstance(subComp);
        		String subCompPrefix = subComp.getName()+"__";
        		AgreeEmitterState subState = generate(subCompInst, subComp, true);
        		if(subState != null){
        			Node subNode = nodeFromState(subState, true);
        			addSubcomponentNodeCall(subCompPrefix, state, subState, subNode);
        		}
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

		if(state.calendar.size() != 0){
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
        	if(clockIds.size() <= 0){
        		throw new AgreeException("Somehow there are no clocks in the system implementation?");
        	}
        	
        	Node dfaNode = AgreeCalendarUtils.getDFANode(
        			dfaNodeName, state.synchrony);
        	Node calNode = AgreeCalendarUtils.getCalendarNode(
        			calenNodeName, dfaNode.id, clockIds.size());
        	state.nodeDefExpressions.add(dfaNode);
        	state.nodeDefExpressions.add(calNode);
        	state.assertExpressions.add(new NodeCallExpr(calNode.id, clockIds));
        	
        	//assert that at least someone ticks
        	Expr atLeastOneExpr = new BoolExpr(false);
        	for(Expr clock : clockIds){
        		atLeastOneExpr = new BinaryExpr(atLeastOneExpr, BinaryOp.OR, clock);
        	}
        	state.assertExpressions.add(atLeastOneExpr);
        	
        	if(!state.simultaneity){
        		//assert that no two clocks tick at the same time
        		Expr singleTick = AgreeCalendarUtils.getSingleTick(clockIds);
        		state.assertExpressions.add(singleTick);
        	}
        	
        }else{
        	for(AgreeVarDecl clockVar : state.clockVars){
        		state.assertExpressions.add(new IdExpr(clockVar.id));
        	}
        }		
		
	}

	private static void addSubcomponentNodeCall(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, Node subNode) {
		
		//get the id for the subcomponent clock
		IdExpr clockId = addSubcompClock(state, subState);
		
		//add the and output variables and hold equations
		//create the hold equation for subcomponent outputs
		addOutputsAndHolds(prefix, state, subState, clockId);
		
		//add the input variables
		addInputs(prefix, state, subState);
		
		//add the all of the sub categories
		addCategories(prefix, state, subState);
		
		//add all the nodes defined by substates
		state.nodeDefExpressions.addAll(subState.nodeDefExpressions);
		
		//add all the "initially" expressions
		addInitialConstraints(prefix, state, subState, clockId);
		
		//add the call to the subcomponent node and wrap it in a condact
		addNodeAndCondactCall(prefix, state, subState, subNode, clockId);
		
		//add subcomponent variable references (for cex output)
		addReferences(prefix, state, subState);
		
		//add assumption renamings
		int i = 0;
		for(Equation assumEq : subState.assumpExpressions){
			String lustreVarName = getLustreNodeName(subState);
			lustreVarName = lustreVarName+"~condact~0.~~ASSUME"+i+"~clocked_property";
			String assumeDisplayText = assumEq.lhs.get(0).id;
			assumeDisplayText = state.renaming.rename(
					subState.curInst.getInstanceObjectPath()+" : \""+assumeDisplayText+"\"");
			assumeDisplayText = assumeDisplayText.replaceAll(".*\\.", "");
			state.renaming.addExplicitRename(lustreVarName, assumeDisplayText);
		}
		//includes renaming of assumptions for subnodes
		state.renaming.addRenamings(subState.renaming);
		
		
	}

	private static IdExpr addSubcompClock(AgreeEmitterState state,
			AgreeEmitterState subState) {
		IdExpr clockId = new IdExpr(subState.curComp.getName()+state.clockIDSuffix);
		//the clockId may or may not already be a part of the inputs
		AgreeVarDecl clockVar = new AgreeVarDecl(clockId.id, NamedType.BOOL);
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
	}

	private static void addNodeAndCondactCall(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, Node subNode,
			IdExpr clockId) {
		
		//create the call to the node and add it to the assertions
		List<Expr> callArgs = new ArrayList<>();
		for(VarDecl input : subNode.inputs){
			callArgs.add(new IdExpr(prefix+input.id));
		}
		
		//just assert that the assertion is true initially in the condact expression
		List<Expr> args = new ArrayList<>();
		args.add(new BoolExpr(true));
		
		NodeCallExpr nodeCall = new NodeCallExpr(subNode.id, callArgs);
		CondactExpr condactCall = new CondactExpr(clockId, nodeCall, args);
		
		state.assertExpressions.add(condactCall);
		state.typeExpressions.addAll(subState.typeExpressions);
		state.nodeDefExpressions.add(subNode);

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
		
		for(Expr subInitExpr : subState.initialExpressions){
			Expr newInitExpr = subInitExpr.accept(initialExprRewriter);
			AgreeVarDecl initVar = new AgreeVarDecl("__INITIALIZED_"+subState.curComp.getName(), NamedType.BOOL);
			IdExpr initId = new IdExpr(initVar.id);
			Expr initializedExpr = new UnaryExpr(UnaryOp.PRE, initId);
			initializedExpr = new BinaryExpr(clockId, BinaryOp.OR, initializedExpr);
			initializedExpr = new BinaryExpr(clockId, BinaryOp.ARROW, initializedExpr);
			Equation initializedEq = new Equation(initId, initializedExpr);
			
			Expr notInitialzedConstraint = new UnaryExpr(UnaryOp.NOT, initId);
			notInitialzedConstraint = new BinaryExpr(notInitialzedConstraint, BinaryOp.IMPLIES, newInitExpr);
			
			state.internalVars.add(initVar);
			state.eqExpressions.add(initializedEq);
			state.assertExpressions.add(notInitialzedConstraint);
		}
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
		Expr holdPrevExpr = new BinaryExpr(new UnaryExpr(UnaryOp.NOT, clockId), BinaryOp.IMPLIES, finalSameAsPrev);
		holdPrevExpr = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, holdPrevExpr);
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
    	
    	
    	inputs.addAll(subState.inputVars);
    	inputs.addAll(subState.outputVars);
    	locals.addAll(subState.internalVars);
    	
    	for(Equation guarEq : subState.guarExpressions){
    		guarantees.add(guarEq.expr);
    	}
    	for(Equation assumEq : subState.assumpExpressions){
    		assumptions.add(assumEq.expr);
    	}
    	equations.addAll(subState.eqExpressions);
    	
    	IdExpr assertId = new IdExpr("__ASSERT");
    	outputs.add(new VarDecl(assertId.id, NamedType.BOOL));
    	

    	ComponentClassifier compClass = subState.curInst.getComponentClassifier();
    	Expr finalAssert;
    	//if this subcomponent is a component type (not an implementation)
    	//the contract bottoms out here and we need to assert it
    	if(compClass instanceof ComponentType || assertConract){
    		finalAssert = assertContract(locals, equations, assumptions, guarantees);	
    	}else{
    		finalAssert = new BoolExpr(true);
    	}
    	for(Expr assertExpr : subState.assertExpressions){
    		finalAssert = new BinaryExpr(finalAssert, BinaryOp.AND, assertExpr);
    	}
    	
    	Equation assertEq = new Equation(assertId, finalAssert);
    	equations.add(assertEq);
    	
    	Node subNode = new Node(Location.NULL, nodeId, inputs, outputs, locals, equations,
    			null, null, assumptions, guarantees);
    	
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
//		//the component has bottomed out so we should assert the contract
//		IdExpr assumpHistId = new IdExpr("__HIST__ASSUM");
//		VarDecl assumpHistVar = new VarDecl(assumpHistId.id, NamedType.BOOL);
//		IdExpr assumpId = new IdExpr("__ASSUM");
//		VarDecl assumpVar = new VarDecl(assumpId.id, NamedType.BOOL);
//		locals.add(assumpHistVar);
//		locals.add(assumpVar);
//		
//		Expr assumpExpr = new BoolExpr(true);
//		for(Expr assum : assumptions){
//			assumpExpr = new BinaryExpr(assumpExpr, BinaryOp.AND, assum);
//		}
//		Equation assumEq = new Equation(assumpId, assumpExpr);
//		equations.add(assumEq);
//		
//		Expr assumpHistExpr = new UnaryExpr(UnaryOp.PRE, assumpHistId);
//		assumpHistExpr = new BinaryExpr(assumpHistExpr, BinaryOp.AND, assumpId);
//		assumpHistExpr = new BinaryExpr(assumpId, BinaryOp.ARROW, assumpHistExpr);
//		Equation assumpHistEq = new Equation(assumpHistId, assumpHistExpr);
//		equations.add(assumpHistEq);
//		
//		IdExpr guarId = new IdExpr("__GUAR");
//		VarDecl guarVar = new VarDecl(guarId.id, NamedType.BOOL);
//		locals.add(guarVar);
		
		Expr guarExpr = new BoolExpr(true);
		for(Expr guar : guarantees){
			guarExpr = new BinaryExpr(guarExpr, BinaryOp.AND, guar);
		}
		
//		Equation guarEq = new Equation(guarId, guarExpr);
//		equations.add(guarEq);
//		
//		return new BinaryExpr(assumpHistId, BinaryOp.IMPLIES, guarId);
		
		return guarExpr;
		
	}
    
}
