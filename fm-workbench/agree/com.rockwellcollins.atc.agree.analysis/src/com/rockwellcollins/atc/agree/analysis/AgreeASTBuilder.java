package com.rockwellcollins.atc.agree.analysis;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.type.PrimitiveType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.annexsupport.AnnexUtil;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RecordType;
import jkind.lustre.Type;
import jkind.lustre.VarDecl;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.AsynchStatement;
import com.rockwellcollins.atc.agree.agree.CalenStatement;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.LatchedStatement;
import com.rockwellcollins.atc.agree.agree.MNSynchStatement;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.ast.AgreeNode;
import com.rockwellcollins.atc.agree.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.ast.AgreeVar;

public class AgreeASTBuilder extends AgreeSwitch<Expr>{
	
	public final String clockIDSuffix = "___CLOCK_";
	
	public AgreeProgram getAgreeProgram(ComponentInstance compInst){
	
		return null;
	}
	
	private AgreeNode getAgreeNode(ComponentInstance compInst){
		List<AgreeVar> inputs;
		List<AgreeVar> outputs;
		List<AgreeConnection> connections;
		List<AgreeNode> subNodes;
		List<Node> lustreNodes;
		List<AgreeStatement> assertions;
		List<AgreeStatement> assumptions;
		List<AgreeStatement> guarantees;
		List<Type> types;
		Expr clockConstraint;
		Expr initialConstraint;
		AgreeVar clockVar;
		EObject reference;
		
		ComponentClassifier compClass = compInst.getComponentClassifier();
		if(compClass instanceof ComponentImplementation){
			AgreeContractSubclause annex = getAgreeAnnex(compClass);
			AgreeContract contract = (AgreeContract) annex.getContract();
			
			subNodes = new ArrayList<>();
			List<IdExpr> clockVars = new ArrayList<>();
			for(ComponentInstance subInst : compInst.getAllComponentInstances()){
				AgreeNode subNode = getAgreeNode(subInst);
				subNodes.add(subNode);
			}
			
			assertions = getAssertions(contract.getSpecs());
			lustreNodes = getLustreNodes(contract.getSpecs());
			types = getLustreTypes(contract.getSpecs());
			//the clock constraints contain other nodes that we add
			clockConstraint = getClockConstraint(contract.getSpecs(), subNodes, lustreNodes);
		}
		
		return null;
	}
	
	private Expr getClockConstraint(EList<SpecStatement> specs, List<AgreeNode> subNodes, List<Node> lustreNodes) {
		for(SpecStatement spec : specs){
			if(spec instanceof MNSynchStatement){
				return getMNSynchConstraint((MNSynchStatement)spec, lustreNodes);
			}
			if(spec instanceof CalenStatement){
				throw new AgreeException("The use of calendar statements has been depricated");
			}
			if(spec instanceof AsynchStatement){
				return new BoolExpr(true);
			}
			if(spec instanceof LatchedStatement){
				
			}
			if(spec instanceof SynchStatement){
				
			}
		}
		
		
		return null;
	}

	private Expr getMNSynchConstraint(MNSynchStatement sync, List<Node> lustreNodes) {

		Set<String> nodeNames = new HashSet<>();
	    Expr clockAssertion = new BoolExpr(true);
		for(int i = 0; i < sync.getComp1().size(); i++){
    		Subcomponent maxComp = (Subcomponent) sync.getComp1().get(i);
    		Subcomponent minComp = (Subcomponent) sync.getComp2().get(i);
    		
    		Expr maxClock = new IdExpr(maxComp.getName()+clockIDSuffix);
    		Expr minClock = new IdExpr(minComp.getName()+clockIDSuffix);
    		int max = Integer.valueOf(sync.getMax().get(i));
    		int min = Integer.valueOf(sync.getMin().get(i));
    		
    		MNSynchronyElement elem = new MNSynchronyElement(maxClock, minClock, max, min);
    		
    		String nodeName = "__calendar_node_"+elem.max+"_"+elem.min;
	        if(!nodeNames.contains(nodeName)){
	            nodeNames.add(nodeName);
	            Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, elem.max, elem.min);
	            lustreNodes.add(calNode);
	        }
	        
	        NodeCallExpr nodeCall = new NodeCallExpr(nodeName, elem.maxClock, elem.minClock);
	        clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
	        nodeCall = new NodeCallExpr(nodeName, elem.minClock, elem.maxClock);
	        clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
    	}
		
		return clockAssertion;
	}

	private List<Type> getLustreTypes(EList<SpecStatement> specs){
		List<Type> types = new ArrayList<>();
		for(SpecStatement spec : specs){
			if(spec instanceof RecordDefExpr){
				
				String name = ((RecordDefExpr) spec).getName();
				List<VarDecl> args = agreeVarsFromArgs(((RecordDefExpr) spec).getArgs());
				Map<String, Type> typeMap = new HashMap<>();
				
				for(VarDecl var : args){
					typeMap.put(var.id, var.type);
				}
				Type lustreType = new RecordType(name, typeMap);
			}
		}
		return types;
	}
	
	private List<Node> getLustreNodes(EList<SpecStatement> specs){
		List<Node> nodes = new ArrayList<>();
		for(SpecStatement spec : specs){
			if(spec instanceof NodeDefExpr){
				nodes.add(getNodeFromDefExpr((NodeDefExpr)spec));
			}
		}
		return nodes;
	}
	
	private Node getNodeFromDefExpr(NodeDefExpr spec) {
		List<VarDecl> inputs = agreeVarsFromArgs(spec.getArgs());
		List<VarDecl> outputs = agreeVarsFromArgs(spec.getRets());
		
		NodeBodyExpr body = spec.getNodeBody();
		List<VarDecl> locals = agreeVarsFromArgs(body.getLocs());
		
		List<Equation> equations = new ArrayList<>();
		for(NodeStmt statement : body.getStmts()){
			if(statement instanceof NodeEq){
				List<VarDecl> args = agreeVarsFromArgs(((NodeEq) statement).getLhs());
				List<IdExpr> eqIds = new ArrayList<>();
				for(VarDecl var : args){
					eqIds.add(new IdExpr(var.id));
				}
				Expr expr = doSwitch(statement.getExpr());
				equations.add(new Equation(eqIds, expr));
			}
		}
		return new Node(spec.getName(), inputs, outputs, locals, equations);
	}

	private List<VarDecl> agreeVarsFromArgs(EList<Arg> args){
		List<VarDecl> agreeVars = new ArrayList<>();
		for(Arg arg : args){
			//TODO: decide whether or not to make these VarDecls or AgreeVars
			agreeVars.add(new AgreeVar(arg.getName(), agreeTypeToLustreType(arg.getType()), arg));
		}
		return agreeVars;
	}

	private Type agreeTypeToLustreType(com.rockwellcollins.atc.agree.agree.Type type){
		if(type instanceof PrimType){
			String typeStr = ((PrimType) type).getString();
			switch(typeStr){
			case "real": return NamedType.REAL;
			case "int": return NamedType.INT;
			case "bool": return NamedType.BOOL;
			}
		}else{
			if(!(type instanceof com.rockwellcollins.atc.agree.agree.RecordType)){
				throw new AgreeException("Agree does not know to reason about types of class: "+type.getClass().getSimpleName());
			}
			com.rockwellcollins.atc.agree.agree.RecordType record = (com.rockwellcollins.atc.agree.agree.RecordType)type;
			NestedDotID id = record.getRecord();
			String recordName = "";
			String delim = "";
			while(id != null){
				recordName = delim+id.getBase().getName();
				id = id.getSub();
				delim = ".";
			}
			
			return new NamedType(record.getRecord().getBase().getName());
		}
		return null;
	}
	
	private List<AgreeStatement> getAssertions(EList<SpecStatement> specs) {
		List<AgreeStatement> asserts = new ArrayList<>();
		for(SpecStatement spec : specs){
			if(spec instanceof AssertStatement){
				AssertStatement assertState = (AssertStatement)spec;
				asserts.add(new AgreeStatement(assertState.getStr(), doSwitch(assertState.getExpr()), assertState));
			}
		}
		return asserts;
	}
	
	private List<AgreeStatement> getAssumptions(EList<SpecStatement> specs) {
		List<AgreeStatement> assumptions = new ArrayList<>();
		for(SpecStatement spec : specs){
			if(spec instanceof AssumeStatement){
				AssumeStatement assumption = (AssumeStatement)spec;
				assumptions.add(new AgreeStatement(assumption.getStr(), doSwitch(assumption.getExpr()), assumption));
			}
		}
		return assumptions;
	}
	
	private List<AgreeStatement> getGuarantees(EList<SpecStatement> specs) {
		List<AgreeStatement> guarantees = new ArrayList<>();
		for(SpecStatement spec : specs){
			if(spec instanceof AssumeStatement){
				AssumeStatement guarantee = (AssumeStatement)spec;
				guarantees.add(new AgreeStatement(guarantee.getStr(), doSwitch(guarantee.getExpr()), guarantee));
			}
		}
		return guarantees;
	}

	private AgreeContractSubclause getAgreeAnnex(ComponentClassifier comp){
   	 for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(comp, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                //in newer versions of osate the annex this returns annexes in the type
                //as well as the implementation. We want the annex in the specific component
                EObject container = annex.eContainer();
                while(!(container instanceof ComponentClassifier)){
                    container = container.eContainer();
                }
                if(container == comp){
                    return (AgreeContractSubclause) annex;
                }
            }
        }
   	 return null;
   }

}
