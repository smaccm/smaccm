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
        Node subNode = nodeFromState(state);
    	
    	List<Node> nodes = new ArrayList<>(state.nodeDefExpressions);
    	
    	List<Expr> assertions = new ArrayList<Expr>();
    	if(subNode.outputs.size() != 1){
    		throw new AgreeException("Something went wrong with node generation");
    	}
    	assertions.add(new IdExpr(subNode.outputs.get(0).id));
    	
    	Node mainNode = new Node(subNode.location, subNode.id, subNode.inputs, subNode.outputs,
    			subNode.locals, subNode.equations, subNode.properties, assertions,
    			subNode.assumptions, subNode.guarantees);
    	
    	nodes.add(mainNode);
    	
    	//have to convert the type expressions
    	List<TypeDef> typeDefs = new ArrayList<>();
    	for(RecordType type : state.typeExpressions){
    		TypeDef typeDef = new TypeDef(type.id, type);
    		typeDefs.add(typeDef);
    	}
    	
    	//add the guarantees to the renaming
    	int i = 0;
    	for(Equation eq : state.guarExpressions){
    		state.renaming.addExplicitRename("~~GUARANTEE"+i++, eq.lhs.get(0).id);
    	}
    	
    	return new Program(typeDefs, null, nodes);
    }
    
    public static Program getLustre(ComponentInstance compInst){
    	
    	AgreeEmitterState state = generate(compInst, null);
    	return getLustre(state);
    }
    
    public static AgreeEmitterState generate(ComponentInstance compInst, Subcomponent comp){
    	 
    	ComponentType compType = AgreeEmitterUtilities.getInstanceType(compInst);
    	ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
       
    	AgreeEmitterState state = 
        		new AgreeEmitterState(compInst, comp);
    	
    	FeatureUtils.recordFeatures(state);
    	boolean result = doSwitchAgreeAnnex(state, compType); 
        if(!result){
        	//throw new AgreeException("Could not find annex in component '"+compType.getName()+"'");
        	return null;
        }
        
        if(compImpl != null){
        	ConnectionUtils.recordConnections(state);
        	doSwitchAgreeAnnex(state, compImpl);

        	//go through the component implementation and build a program for each subcomponent
        	for(Subcomponent subComp : compImpl.getAllSubcomponents()){
        		ComponentInstance subCompInst = compInst.findSubcomponentInstance(subComp);
        		String subCompPrefix = subComp.getName()+"__";
        		AgreeEmitterState subState = generate(subCompInst, subComp);
        		if(subState != null){
        			Node subNode = nodeFromState(subState);
        			addSubcomponentNodeCall(subCompPrefix, state, subState, subNode);
        		}
        	}
        }
    	
    	return state;
    }

	private static void addSubcomponentNodeCall(final String prefix,
			AgreeEmitterState state, AgreeEmitterState subState, Node subNode) {
		
		for(String category : subState.layout.getCategories()){
			state.layout.addCategory(prefix+category);
		}
		
		for(AgreeVarDecl output : subState.outputVars){
			state.subcompOutputVars.add(new AgreeVarDecl(prefix+output.id, output.type));
			state.outputVars.add(new AgreeVarDecl(prefix+output.id, output.type));
			state.layout.addElement(prefix+subState.layout.getCategory(output.id), prefix+output.id, AgreeLayout.SigType.OUTPUT);
		}
//		for(AgreeVarDecl output : subState.subcompOutputVars){
//			state.subcompOutputVars.add(new AgreeVarDecl(prefix+output.id, output.type));
//		}
		for(AgreeVarDecl input : subState.inputVars){
			state.inputVars.add(new AgreeVarDecl(prefix+input.id, input.type));
			state.layout.addElement(prefix+subState.layout.getCategory(input.id), prefix+input.id, AgreeLayout.SigType.INPUT);
		}
		state.nodeDefExpressions.addAll(subState.nodeDefExpressions);
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
		state.typeExpressions.addAll(subState.typeExpressions);
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
    	
    	for(Equation guarEq : subState.guarExpressions){
    		guarantees.add(guarEq.expr);
    	}
    	assumptions.addAll(subState.assumpExpressions);
    	equations.addAll(subState.eqExpressions);
    	
    	IdExpr assertId = new IdExpr("_ASSERT");
    	outputs.add(new VarDecl(assertId.id, NamedType.BOOL));
    	

    	ComponentClassifier compClass = subState.curInst.getComponentClassifier();
    	Expr finalAssert;
    	//if this subcomponent is a component type (not an implementation)
    	//the contract bottoms out here and we need to assert it
    	if(compClass instanceof ComponentType){
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

	private static Expr assertContract(List<VarDecl> locals,
			List<Equation> equations, List<Expr> assumptions,
			List<Expr> guarantees) {
		//the component has bottomed out so we should assert the contract
		IdExpr assumpHistId = new IdExpr("_HIST_ASSUM");
		VarDecl assumpHistVar = new VarDecl(assumpHistId.id, NamedType.BOOL);
		IdExpr assumpId = new IdExpr("_ASSUM");
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
		
		IdExpr guarId = new IdExpr("_GUAR");
		VarDecl guarVar = new VarDecl(guarId.id, NamedType.BOOL);
		locals.add(guarVar);
		
		Expr guarExpr = new BoolExpr(true);
		for(Expr guar : guarantees){
			guarExpr = new BinaryExpr(guarExpr, BinaryOp.AND, guar);
		}
		
		Equation guarEq = new Equation(guarId, guarExpr);
		equations.add(guarEq);
		
		return new BinaryExpr(assumpHistId, BinaryOp.IMPLIES, guarId);
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
