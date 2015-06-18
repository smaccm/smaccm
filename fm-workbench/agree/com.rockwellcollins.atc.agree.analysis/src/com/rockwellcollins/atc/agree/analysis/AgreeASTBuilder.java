package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.type.PrimitiveType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.annexsupport.AnnexUtil;

import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.Type;
import jkind.lustre.VarDecl;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.ast.AgreeNode;
import com.rockwellcollins.atc.agree.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.ast.AgreeVar;

public class AgreeASTBuilder extends AgreeSwitch<Expr>{
	
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
			
			assertions = getAssertions(contract.getSpecs());
		}
		
		return null;
	}
	
	private List<Node> getLustreNodes(EList<SpecStatement> specs){
		List<Node> nodes = new ArrayList<>();
		for(SpecStatement spec : specs){
			if(spec instanceof NodeDefExpr){
				nodes.add(getNodeFromDefExpr((NodeDefExpr)spec));
			}
		}
	}
	
	private Node getNodeFromDefExpr(NodeDefExpr spec) {
		spec.get
	}
	
	private List<AgreeVar> agreeVarFromArgs(EList<Arg> args){
		List<AgreeVar> agreeVars = new ArrayList<>();
		for(Arg arg : args){
			agreeVars.add(new AgreeVar(arg.getName(), arg.getType(), arg));
		}
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
			if(!(type instanceof RecordType)){
				throw new AgreeException("Agree does not know to reason about types of class: "+type.getClass().getSimpleName());
			}
			RecordType record = (RecordType)type;
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
