package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jkind.api.results.MapRenaming;
import jkind.api.results.Renaming;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.Location;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
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
        Node subNode = nodeFromState(state);
    	
    	List<Node> nodes = new ArrayList<>(state.nodeDefExpressions);
    	nodes.add(subNode);
    	return new Program(nodes);
    }
    
    public static Program getLustre(ComponentInstance compInst){
    	
    	AgreeEmitterState state = generate(compInst, null);
    	Node subNode = nodeFromState(state);
    	
    	List<Node> nodes = new ArrayList<>(state.nodeDefExpressions);
    	nodes.add(subNode);
    	return new Program(nodes);
    	
    }
    
    public static AgreeEmitterState generate(ComponentInstance compInst, Subcomponent comp){
    	 
    	ComponentType compType = AgreeEmitterUtilities.getInstanceType(compInst);
    	ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
       
    	AgreeEmitterState state = 
        		new AgreeEmitterState(compInst, comp);
    	
    	FeatureUtils.recordFeatures(state);
    	ConnectionUtils.recordConnections(state);
    	boolean result = doSwitchAgreeAnnex(state, compType); 
        if(!result){
        	throw new AgreeException("Could not find annex in component '"+compType.getName()+"'");
        }
        if(compImpl != null){
        	doSwitchAgreeAnnex(state, compImpl);

        	//go through the component implementation and build a program for each subcomponent
        	for(Subcomponent subComp : compImpl.getAllSubcomponents()){
        		ComponentInstance subCompInst = compInst.findSubcomponentInstance(subComp);
        		String subCompPrefix = subComp.getName()+"__";
        		AgreeEmitterState subState = generate(subCompInst, subComp);
        		Node subNode = nodeFromState(subState);
        		addSubcomponentNodeCall(subCompPrefix, state, subState, subNode);
        	}
        }
    	
    	return state;
    }

	private static void addSubcomponentNodeCall(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, Node subNode) {
		for(AgreeVarDecl output : subState.outputVars){
			state.subcompOutputVars.add(new AgreeVarDecl(prefix+output.id, output.type));
		}
		for(AgreeVarDecl output : subState.subcompOutputVars){
			state.subcompOutputVars.add(new AgreeVarDecl(prefix+output.id, output.type));
		}
		for(AgreeVarDecl input : subState.inputVars){
			state.inputVars.add(new AgreeVarDecl(prefix+input.id, input.type));
		}
		for(Node node : subState.nodeDefExpressions){
			state.nodeDefExpressions.add(node);
		}
		//
		IdRewriter prefixRewrite = new IdRewriter() {
			public IdExpr rewrite(IdExpr id) {
				return new IdExpr(prefix + id.id);
			}
		};
		IdRewriteVisitor initialExprRewriter = new IdRewriteVisitor(prefixRewrite);
		
		for(Expr initialExpr : subState.initialExpressions){
			state.initialExpressions.add(initialExpr.accept(initialExprRewriter));
		}
		
		state.nodeDefExpressions.add(subNode);
		
		//create the call to the node and add it to the assertions
		List<Expr> callArgs = new ArrayList<>();
		for(VarDecl input : subNode.inputs){
			callArgs.add(new IdExpr(prefix+input.id));
		}
		
		NodeCallExpr nodeCall = new NodeCallExpr(subNode.id, callArgs);
		state.assertExpressions.add(nodeCall);
	}
    
    public static Node nodeFromState(AgreeEmitterState subState){
    	
    	
    	//create the body for the subnode
    	String nodeId = "_Node_"+subState.curInst.getInstanceObjectPath();
    	nodeId = nodeId.replace(".", "__");
    	List<VarDecl> inputs = new ArrayList<>();
    	List<VarDecl> outputs = new ArrayList<>();
    	List<VarDecl> locals = new ArrayList<>();
    	List<Equation> equations = new ArrayList<>();
    	List<Expr> assumptions = new ArrayList<>();
    	List<Expr> guarantees = new ArrayList<>();
    	
    	
    	inputs.addAll(subState.inputVars);
    	inputs.addAll(subState.outputVars);
    	locals.addAll(subState.internalVars);
    	IdExpr assertId = new IdExpr("_ASSERT");
    	outputs.add(new VarDecl(assertId.id, NamedType.BOOL));
    	
    	//add all the assertions as the single output of the node
    	Expr finalAssert = new BoolExpr(true);
    	for(Expr assertExpr : subState.assertExpressions){
    		finalAssert = new BinaryExpr(finalAssert, BinaryOp.AND, assertExpr);
    	}
    	Equation assertEq = new Equation(assertId, finalAssert);
    	equations.add(assertEq);
    	
    	for(Equation guarEq : subState.guarExpressions){
    		guarantees.add(guarEq.expr);
    	}
    	assumptions.addAll(subState.assumpExpressions);
    	
    	Node subNode = new Node(Location.NULL, nodeId, inputs, outputs, locals, equations,
    			null, null, assumptions, guarantees);
    	
    	return subNode;
    	
    }
    
//    
//    
//    
//    
//    public Program evaluate(){
//        
//        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
//        ComponentType ct = AgreeEmitterUtilities.getInstanceType(compInst);
//        AgreeLayout layout = new AgreeLayout();
//        String topCategory = compInst.getName();
//        
//        AgreeEmitterState state = new AgreeEmitterState(topCategory + dotChar, topCategory + dotChar,
//        		layout, topCategory, compInst, null);
//        this.topState = state;
//        AgreeAnnexEmitter emitter = new AgreeAnnexEmitter();
//        this.topEmitter = emitter;
//                
//        boolean foundAnnex = false;
//        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(compImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
//            if (annex instanceof AgreeContractSubclause) {
//                state.doSwitch(annex);
//                foundAnnex = true;
//                break;
//            }
//        }
//        
//        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(ct, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
//            if (annex instanceof AgreeContractSubclause) {
//                state.doSwitch(annex);
//                foundAnnex = true;
//                break;
//            }
//        }
//        
//        List<AgreeEmitterState> subStates = new ArrayList<>();
//        for(Subcomponent subComp : compImpl.getAllSubcomponents()){
//        	//don't check data subcomponents
//        	if(subComp instanceof DataSubcomponent
//        			|| subComp instanceof DataSubcomponentType
//        			|| subComp instanceof BusSubcomponent){
//        		continue;
//        	}
//        	
//        	String category;
//        	boolean foundSubAnnex = false;
//            ComponentInstance subCompInst = compInst.findSubcomponentInstance(subComp);
//            ComponentType subCt = AgreeEmitterUtilities.getInstanceType(subCompInst);
//            ComponentImplementation subCompImpl = AgreeEmitterUtilities.getInstanceImplementation(subCompInst);
//            category = subCompInst.getQualifiedName();
//            
//            AgreeEmitterState subState = new AgreeEmitterState(topCategory + dotChar, topCategory + dotChar + subComp.getName() + dotChar,
//            		layout, category, subCompInst, subComp);
//
//            //special code for lifting
//            if(subCompImpl != null){
//                for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
//                    if (annex instanceof AgreeContractSubclause) {
//                    	Contract contract = ((AgreeContractSubclause) annex).getContract();
//                    	if(contract instanceof AgreeContract){
//                    		for(SpecStatement spec :  ((AgreeContract) contract).getSpecs()){
//                    			if(spec instanceof LiftStatement){
//                    				NestedDotID subSubID = ((LiftStatement) spec).getSubcomp();
//            						Subcomponent subSubComp = (Subcomponent) subSubID.getBase();
//                    				emitter.doLift(subSubComp, subState);
//                    			}else if(spec instanceof LemmaStatement){//TODO might not be compositional
//                    				subState.doSwitch(spec);
//                    			}else if(spec instanceof EqStatement){//TODO might not be compositional
//                    				subState.doSwitch(spec);
//                    			}
//                    		}
//                    	}
//                        foundSubAnnex = foundAnnex = true;
//                    }
//                    break;
//                }
//            }
//
//            for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCt, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
//                if (annex instanceof AgreeContractSubclause) {
//                    subState.doSwitch(annex);
//                    foundSubAnnex = foundAnnex = true;
//                    break;
//                }
//            }
//
//            if(foundSubAnnex){
//            	subStates.add(subState);
//            }
//
//        }
//        if(!foundAnnex){
//        	return null;
//        }
//        
//        return emitter.getLustreWithCondacts(state, subStates);
//
//    }
//
//    public Map<String, EObject> getReferenceMap() {
//        return topState.refMap;
//    }


}
