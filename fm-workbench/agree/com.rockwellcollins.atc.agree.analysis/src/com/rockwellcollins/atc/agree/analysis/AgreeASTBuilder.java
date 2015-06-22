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
import org.osate.aadl2.BusAccess;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ConnectionEnd;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataAccess;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.Property;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

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
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
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
import com.rockwellcollins.atc.agree.agree.InitialStatement;
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
import com.rockwellcollins.atc.agree.analysis.AgreeFeature.ConnType;
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
		List<AgreeVar> inputs = new ArrayList<>();
		List<AgreeVar> outputs = new ArrayList<>();
		List<AgreeConnection> connections = new ArrayList<>();
		List<AgreeNode> subNodes = new ArrayList<>();
		List<Node> lustreNodes = new ArrayList<>();
		List<AgreeStatement> assertions = new ArrayList<>();
		List<AgreeStatement> assumptions = new ArrayList<>();
		List<AgreeStatement> guarantees = new ArrayList<>();
		List<Type> types = new ArrayList<>();
		Expr clockConstraint = new BoolExpr(true);
		Expr initialConstraint = new BoolExpr(true);
		AgreeVar clockVar = new AgreeVar(compInst.getName()+"___CLOCK_", NamedType.BOOL, compInst.getSubcomponent());
		EObject reference = compInst.getSubcomponent();
		
		ComponentClassifier compClass = compInst.getComponentClassifier();
		if(compClass instanceof ComponentImplementation){
			AgreeContractSubclause annex = getAgreeAnnex(compClass);
			AgreeContract contract = (AgreeContract) annex.getContract();
			
			for(ComponentInstance subInst : compInst.getAllComponentInstances()){
				AgreeNode subNode = getAgreeNode(subInst);
				subNodes.add(subNode);
			}
			
			assertions.addAll(getAssertions(contract.getSpecs()));
			lustreNodes.addAll(getLustreNodes(contract.getSpecs()));
			types.addAll(getLustreTypes(contract.getSpecs()));
			//the clock constraints contain other nodes that we add
			clockConstraint = getClockConstraint(contract.getSpecs(), subNodes, lustreNodes);
			initialConstraint = getInitialConstraint(contract.getSpecs());
			connections.addAll(getConnections(((ComponentImplementation) compClass).getAllConnections(), contract.getSpecs()));
			
			//make compClass the type so we can get it's other contract elements
			compClass = ((ComponentImplementation) compClass).getType();
		}
		
		AgreeContractSubclause annex = getAgreeAnnex(compClass);
		AgreeContract contract = (AgreeContract) annex.getContract();
		assumptions.addAll(getAssumptions(contract.getSpecs()));
		guarantees.addAll(getGuarantees(contract.getSpecs()));
		
		
		return new Agree;
	}
	
	private List<AgreeConnection> getConnections(
			EList<Connection> connections, EList<SpecStatement> specs) {
		
		//figure out if this is latched
		boolean latched = false;
		for(SpecStatement spec : specs){
			if(spec instanceof LatchedStatement){
				latched = true;
				break;
			}
		}
		
		Property commTimingProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
				OsateResourceUtil.getResourceSet(), "Communication_Properties::Timing");
		List<AgreeConnection> agreeConns = new ArrayList<>();
		for(Connection conn : connections){
			
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
			
			//agreeConns.add(makeConnectionExpressions(absConnSour, absConnDest, latched, delayed))
			throw new AgreeException("We have to implement connections");
		}
		return null;
	}
		
//		private static void makeConnectionExpressions(ConnectedElement absConnSour,
//				ConnectedElement absConnDest, boolean latched, boolean delayed) {
//			ConnectionEnd destConn = absConnDest.getConnectionEnd();
//			ConnectionEnd sourConn = absConnSour.getConnectionEnd();
//			//we currently don't handle data accesses or bus accesses
//			if(destConn instanceof DataAccess
//					|| sourConn instanceof DataAccess
//					|| destConn instanceof BusAccess
//					|| sourConn instanceof BusAccess
//					|| destConn instanceof DataSubcomponent
//					|| sourConn instanceof DataSubcomponent){
//				return;
//			}
//			Context destContext = absConnDest.getContext();
//			Context sourContext = absConnSour.getContext();
//			ComponentInstance sourInst = null;
//			FeatureInstance sourBaseFeatInst = null;
//			if(sourContext == null){
//				sourInst = null;
//			}else if(sourContext instanceof Subcomponent){
//				sourInst = sourContext.get.findSubcomponentInstance((Subcomponent)sourContext);
//			}else{
//				sourBaseFeatInst = state.curInst.findFeatureInstance((FeatureGroup)sourContext);
//			}
//			ComponentInstance destInst = null;
//			FeatureInstance destBaseFeatInst = null;
//			if(destContext == null){
//				destInst = state.curInst;
//			}else if(destContext instanceof Subcomponent){
//				destInst = state.curInst.findSubcomponentInstance((Subcomponent)destContext);
//			}else{
//				destBaseFeatInst = state.curInst.findFeatureInstance((FeatureGroup)destContext);
//			}
//			//get the corresponding feature instances
//			FeatureInstance sourFeatInst = null;
//			FeatureInstance destFeatInst = null;
//			List<FeatureInstance> sourFeatInsts = (sourInst == null) ?
//					sourBaseFeatInst.getFeatureInstances() :
//					sourInst.getFeatureInstances();
//					
//			List<FeatureInstance> destFeatInsts = (destInst == null) ?
//					destBaseFeatInst.getFeatureInstances() :
//					destInst.getFeatureInstances();
//					
//			for(FeatureInstance featInst : sourFeatInsts){
//				if(featInst.getFeature() == sourConn){
//					sourFeatInst = featInst;
//					break;
//				}
//			}
//			for(FeatureInstance featInst : destFeatInsts){
//				if(featInst.getFeature() == destConn){
//						destFeatInst = featInst;
//						break;
//				}
//			}
//			//grabs the subnames for all the connections
//			List<AgreeFeature> destConns = state.featInstToAgreeFeatMap.get(destFeatInst);
//			List<AgreeFeature> sourConns = state.featInstToAgreeFeatMap.get(sourFeatInst);
//			String lhsLustreName = null;
//			String rhsLustreName = null;
//			int i;
//			assert(destConns.size() == sourConns.size());
//			for(i = 0; i < destConns.size(); i++){
//				AgreeFeature agreeDestConn = destConns.get(i);
//				AgreeFeature agreeSourConn = sourConns.get(i);
//				if(agreeDestConn.varType == null || agreeSourConn.varType == null){
//				    if(agreeDestConn.varType != agreeSourConn.varType){
//				        throw new AgreeException("Either variable: '"+agreeDestConn.lustreString.replace("__", ".")+
//		                        "' or '"+agreeSourConn.lustreString.replace("__", ".")+"' is reasoned about by AGREE and the other is not.");
//				    }
//				}else if(!agreeDestConn.varType.equals(agreeSourConn.varType)){
//					throw new AgreeException("Variables: '"+agreeDestConn.lustreString.replace("__", ".")+
//							"' and '"+agreeSourConn.lustreString.replace("__", ".")+"' are of different types");
//				}
//				
//				Expr lhsClock = null;
//				Expr rhsClock = null;
//				Subcomponent sourSub = agreeSourConn.compInst.getSubcomponent();
//				Subcomponent destSub = agreeDestConn.compInst.getSubcomponent();
//				
//				//check to see if the destination or source subcomponent is actually the parent
//				//if this is the case then we are doing monolithic verification and the parent
//				//subcomponent clock should just be true
//				if(sourSub != null && destSub != null){
//				    if(subcomponentContainsSubcomponent(sourSub, destSub)){
//				        sourSub = null;
//				    }else if(subcomponentContainsSubcomponent(destSub, sourSub)){
//				        destSub = null;
//				    }
//				}
//				
//				String sourConnName = sourSub == null ? null : sourSub.getName();
//				String destConnName = destSub == null ? null : destSub.getName();
//				if(destContext == null || destContext instanceof FeatureGroup){
//					switch(agreeDestConn.direction){
//					case IN:
//						lhsLustreName = agreeSourConn.lustreString;
//						rhsLustreName = agreeDestConn.lustreString;
//						lhsClock = sourConnName == null ? new BoolExpr(true) : new IdExpr(sourConnName+state.clockIDSuffix);
//	                    rhsClock = destConnName == null ? new BoolExpr(true) : new IdExpr(destConnName+state.clockIDSuffix);
//						break;
//					case OUT:
//						lhsLustreName = agreeDestConn.lustreString;
//						rhsLustreName = agreeSourConn.lustreString;
//	                    lhsClock = destConnName == null ? new BoolExpr(true) : new IdExpr(destConnName+state.clockIDSuffix);
//	                    rhsClock = sourConnName == null ? new BoolExpr(true) : new IdExpr(sourConnName+state.clockIDSuffix);
//	                    break;
//					}
//				}else{
//					switch(agreeDestConn.direction){
//					case IN:
//						lhsLustreName = agreeDestConn.lustreString;
//						rhsLustreName = agreeSourConn.lustreString;
//	                    lhsClock = destConnName == null ? new BoolExpr(true) : new IdExpr(destConnName+state.clockIDSuffix);
//	                    rhsClock = sourConnName == null ? new BoolExpr(true) : new IdExpr(sourConnName+state.clockIDSuffix);
//						break;
//					case OUT:
//						lhsLustreName = agreeSourConn.lustreString;
//						rhsLustreName = agreeDestConn.lustreString;
//	                    lhsClock = sourConnName == null ? new BoolExpr(true) : new IdExpr(sourConnName+state.clockIDSuffix);
//	                    rhsClock =destConnName == null ? new BoolExpr(true) : new IdExpr(destConnName+state.clockIDSuffix);
//	                    break;
//					}
//				}
//				Equation connEq;
//				
//				//this is a stupid hack to deal with the new asynchronous analysis
//				if(state.latchedClocks){
//				    //if we are using latched clocks the rhs clock should only trigger on the
//				    //falling edge
//				    if(rhsClock instanceof IdExpr){
//				        rhsClock = new BinaryExpr(new UnaryExpr(UnaryOp.NOT, rhsClock), 
//				                BinaryOp.AND, 
//				                new UnaryExpr(UnaryOp.PRE, rhsClock));
//				        rhsClock = new BinaryExpr(new BoolExpr(false), BinaryOp.ARROW, rhsClock);
//				    }
//				    
//				    //if we are using latched clocks the lhs clock should only trigger on the rising edge
//				    if(lhsClock instanceof IdExpr){
//				        Expr lhsClockRise = new BinaryExpr(new UnaryExpr(UnaryOp.PRE, new UnaryExpr(UnaryOp.NOT, lhsClock)), 
//	                            BinaryOp.AND, lhsClock);
//	                    lhsClock = new BinaryExpr(lhsClock, BinaryOp.ARROW, lhsClockRise);
//				    }
//				}
//				
//				if(agreeDestConn.varType != null && agreeSourConn.varType != null){
//				    //we only make these connections of the features are reasoned about by agree
//				    if(delayed){
//				        Expr connExpr = new UnaryExpr(UnaryOp.PRE, new IdExpr(rhsLustreName));
//				        connExpr = new BinaryExpr(agreeSourConn.initState, BinaryOp.ARROW, connExpr);
//				        connEq = new Equation(new IdExpr(lhsLustreName), connExpr);
//				    }else{
//				        connEq = new Equation(new IdExpr(lhsLustreName), new IdExpr(rhsLustreName));
//				    }
//				    addConnection(state, connEq);
//				}
//				if(agreeDestConn.connType == ConnType.EVENT){
//					if(agreeSourConn.connType != ConnType.EVENT){
//						throw new AgreeException("The connection between variables '"
//								+agreeDestConn.lustreString+"' and '"
//								+agreeSourConn.lustreString+"' are of different types");
//					}
//					addEventConnection(state, delayed, lhsLustreName,
//	                        rhsLustreName, lhsClock, rhsClock);
//				}
//			}
//
//		}
//		
//		return null;
//	}

	private Expr getInitialConstraint(EList<SpecStatement> specs) {
		for(SpecStatement spec : specs){
			if(spec instanceof InitialStatement){
				return doSwitch(((InitialStatement) spec).getExpr());
			}
		}
		return new BoolExpr(true);
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
				return new BoolExpr(true);
			}
			if(spec instanceof SynchStatement){
				return getSynchConstraint((SynchStatement)spec, subNodes, lustreNodes);
			}
		}
		
		
		return null;
	}

	private Expr getSynchConstraint(SynchStatement spec, List<AgreeNode> subNodes, List<Node> lustreNodes) {
		List<Expr> clockIds = new ArrayList<>();
		Expr clockAssertion;
        for(AgreeNode subNode : subNodes){
            clockIds.add(new IdExpr(subNode.clockVar.id));
        }
        
        int val1 = Integer.decode(spec.getVal());
        if(spec.getVal2() == null){
            Node dfaNode = AgreeCalendarUtils.getDFANode("__DFA_NODE", val1); 
            Node calNode = AgreeCalendarUtils.getCalendarNode("__CALENDAR_NODE", dfaNode.id, clockIds.size());
            lustreNodes.add(dfaNode);
            lustreNodes.add(calNode);

            clockAssertion = new NodeCallExpr(calNode.id, clockIds);
        }else{
        	int val2 = Integer.decode(spec.getVal2());

        	String nodeName = "__calendar_node_"+val1+"_"+val2;
        	Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, val1, val2);
        	lustreNodes.add(calNode);
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
        return clockAssertion;
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
