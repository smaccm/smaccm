package com.rockwellcollins.atc.agree.analysis.ast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ConnectionEnd;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.EventPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.AnnexInstance;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordType;
import jkind.lustre.TupleExpr;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssignStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.AsynchStatement;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CalenStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.FloorCast;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.InitialStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LatchedStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.MNSynchStatement;
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
import com.rockwellcollins.atc.agree.agree.RealCast;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.analysis.AgreeCalendarUtils;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeRecordUtils;
import com.rockwellcollins.atc.agree.analysis.AgreeVarDecl;
import com.rockwellcollins.atc.agree.analysis.MNSynchronyElement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection.ConnectionType;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomater;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomaterRegistry;
import com.rockwellcollins.atc.agree.analysis.extentions.CexExtractor;
import com.rockwellcollins.atc.agree.analysis.extentions.CexExtractorRegistry;
import com.rockwellcollins.atc.agree.analysis.extentions.ExtensionRegistry;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.IdGatherer;

public class AgreeASTBuilder extends AgreeSwitch<Expr> {

    public static final String clockIDSuffix = "___CLOCK_";
    public static final String eventSuffix = "___EVENT_";
    public static final String dotChar = "__";

    private static List<Node> globalNodes;
    private static Set<RecordType> globalTypes;
    private static Map<NamedElement, String> typeMap;
    private ComponentInstance curInst; // used for Get_Property Expressions

    public AgreeProgram getAgreeProgram(ComponentInstance compInst) {

        globalNodes = new ArrayList<>();
        globalTypes = new HashSet<>();
        typeMap = new HashMap<>();

        AgreeNode topNode = getAgreeNode(compInst);
        List<AgreeNode> agreeNodes = gatherNodes(topNode);

        // have to convert the types. The reason we use Record types in the
        // first
        // place rather than the more general types is so we can check set
        // containment
        // easily
        AgreeProgram program = new AgreeProgram(agreeNodes, new ArrayList<>(globalNodes),
                new ArrayList<>(globalTypes), topNode);

        // go through the extension registries and transform the program
        AgreeAutomaterRegistry aAReg = (AgreeAutomaterRegistry) ExtensionRegistry
                .getRegistry(ExtensionRegistry.AGREE_AUTOMATER_EXT_ID);
        List<AgreeAutomater> automaters = aAReg.getAgreeAutomaters();

        for (AgreeAutomater aa : automaters) {
            program = aa.transform(program);
        }

        return program;
    }

    private List<AgreeNode> gatherNodes(AgreeNode node) {
        List<AgreeNode> nodes = new ArrayList<>();

        for (AgreeNode subNode : node.subNodes) {
            nodes.addAll(gatherNodes(subNode));
            nodes.add(subNode);
        }
        return nodes;
    }

    private AgreeNode getAgreeNode(ComponentInstance compInst) {
        List<AgreeVar> inputs = new ArrayList<>();
        List<AgreeVar> outputs = new ArrayList<>();
        List<AgreeVar> locals = new ArrayList<>();
        List<AgreeConnection> connections = new ArrayList<>();
        List<AgreeNode> subNodes = new ArrayList<>();
        List<AgreeStatement> assertions = new ArrayList<>();
        List<AgreeStatement> assumptions = new ArrayList<>();
        List<AgreeStatement> guarantees = new ArrayList<>();
        List<AgreeStatement> lemmas = new ArrayList<>();
        Expr clockConstraint = new BoolExpr(true);
        Expr initialConstraint = new BoolExpr(true);
        String id = compInst.getName();
        AgreeVar clockVar =
                new AgreeVar(id + clockIDSuffix, NamedType.BOOL, compInst.getSubcomponent(), compInst);
        EObject reference = compInst.getSubcomponent();
        TimingModel timing = null;

        boolean foundSubNode = false;
        boolean hasDirectAnnex = false;
        ComponentClassifier compClass = compInst.getComponentClassifier();
        if (compClass instanceof ComponentImplementation) {
            AgreeContractSubclause annex = getAgreeAnnex(compClass);

            for (ComponentInstance subInst : compInst.getComponentInstances()) {
                curInst = subInst;
                AgreeNode subNode = getAgreeNode(subInst);
                if (subNode != null) {
                    foundSubNode = true;
                    subNodes.add(subNode);
                }
            }
            boolean latched = false;
            if (annex != null) {
                hasDirectAnnex = true;
                AgreeContract contract = (AgreeContract) annex.getContract();

                curInst = compInst;
                assertions.addAll(getAssertionStatements(contract.getSpecs()));
                assertions.addAll(getEquationStatements(contract.getSpecs()));
                assertions.addAll(getPropertyStatements(contract.getSpecs()));
                assertions.addAll(getAssignmentStatements(contract.getSpecs()));
                
                lemmas.addAll(getLemmaStatements(contract.getSpecs()));
                addLustreNodes(contract.getSpecs());
                gatherLustreTypes(contract.getSpecs());
                // the clock constraints contain other nodes that we add
                clockConstraint = getClockConstraint(contract.getSpecs(), subNodes);
                timing = getTimingModel(contract.getSpecs());

                outputs.addAll(getEquationVars(contract.getSpecs(), compInst));

                for (SpecStatement spec : contract.getSpecs()) {
                    if (spec instanceof LatchedStatement) {
                        latched = true;
                        break;
                    }
                }

            }
            connections.addAll(getConnections(((ComponentImplementation) compClass).getAllConnections(),
                    compInst, subNodes, latched));
            
            // make compClass the type so we can get it's other contract
            // elements
            compClass = ((ComponentImplementation) compClass).getType();
        }
        curInst = compInst;

        if (timing == null) {
            timing = TimingModel.SYNC;
        }

        AgreeContractSubclause annex = getAgreeAnnex(compClass);
        if (annex != null) {
            hasDirectAnnex = true;
            AgreeContract contract = (AgreeContract) annex.getContract();
            assumptions.addAll(getAssumptionStatements(contract.getSpecs()));
            guarantees.addAll(getGuaranteeStatements(contract.getSpecs()));
            
            // we count eqstatements with expressions as assertions
            assertions.addAll(getEquationStatements(contract.getSpecs()));
            assertions.addAll(getPropertyStatements(contract.getSpecs()));
            outputs.addAll(getEquationVars(contract.getSpecs(), compInst));
            initialConstraint = getInitialConstraint(contract.getSpecs());

            addLustreNodes(contract.getSpecs());
            gatherLustreTypes(contract.getSpecs());
        }

        if (!(foundSubNode || hasDirectAnnex)) {
            return null;
        }
        gatherOutputsInputsTypes(outputs, inputs, compInst.getFeatureInstances(), typeMap, globalTypes);

        // verify that every variable that is reasoned about is
        // in a component containing an annex
        assertReferencedSubcomponentHasAnnex(compInst, inputs, outputs, subNodes, assertions, lemmas);

        return new AgreeNode(id, inputs, outputs, locals, connections, subNodes, assertions, assumptions,
                guarantees, lemmas, clockConstraint, initialConstraint, clockVar, reference, timing,
                compInst);
    }

    private void assertReferencedSubcomponentHasAnnex(ComponentInstance compInst, List<AgreeVar> inputs,
            List<AgreeVar> outputs, List<AgreeNode> subNodes, List<AgreeStatement> assertions,
            List<AgreeStatement> lemmas) {
        Set<String> allExprIds = new HashSet<>();
        IdGatherer visitor = new IdGatherer();
        for (AgreeStatement statement : assertions) {
            allExprIds.addAll(statement.expr.accept(visitor));
        }
        for (AgreeStatement statement : lemmas) {
            allExprIds.addAll(statement.expr.accept(visitor));
        }
        for (String idStr : allExprIds) {
            if (idStr.contains(dotChar)) {
                String prefix = idStr.substring(0, idStr.indexOf(dotChar));
                boolean found = false;
                for (AgreeVar var : inputs) {
                    if (var.id.startsWith(prefix)) {
                        found = true;
                        break;
                    }
                }
                for (AgreeVar var : outputs) {
                    if (var.id.startsWith(prefix)) {
                        found = true;
                        break;
                    }
                }
                for (AgreeNode subNode : subNodes) {
                    if (subNode.id.equals(prefix)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new AgreeException("Variable '" + idStr.replace(dotChar, ".")
                            + "' appears in an assertion, lemma " + "or equation statement in component '"
                            + compInst.getInstanceObjectPath() + "' but subcomponent '" + prefix + "' does "
                            + "not contain an AGREE annex");
                }
            }
        }
    }

    private List<AgreeStatement> getLemmaStatements(EList<SpecStatement> specs) {
        List<AgreeStatement> lemmas = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof LemmaStatement) {
                LemmaStatement lemma = (LemmaStatement) spec;
                lemmas.add(new AgreeStatement(lemma.getStr(), doSwitch(lemma.getExpr()), spec));
            }
        }
        return lemmas;
    }

    private TimingModel getTimingModel(EList<SpecStatement> specs) {
        for (SpecStatement spec : specs) {
            if (spec instanceof MNSynchStatement) {
                return TimingModel.ASYNC;
            }
            if (spec instanceof CalenStatement) {
                throw new AgreeException("The use of calendar statements has been depricated");
            }
            if (spec instanceof AsynchStatement) {
                return TimingModel.ASYNC;
            }
            if (spec instanceof LatchedStatement) {
                return TimingModel.LATCHED;
            }
            if (spec instanceof SynchStatement) {
                int val = Integer.valueOf(((SynchStatement) spec).getVal());
                if (val != 0) {
                    return TimingModel.ASYNC;
                }
            }
        }
        return TimingModel.SYNC;
    }

    private List<AgreeVar> getEquationVars(EList<SpecStatement> specs, ComponentInstance compInst) {
        List<AgreeVar> agreeVars = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof EqStatement) {
                EList<Arg> args = ((EqStatement) spec).getLhs();
                List<VarDecl> vars = agreeVarsFromArgs(args, compInst);
                for (VarDecl var : vars) {
                    agreeVars.add((AgreeVar) var);
                }
            } else if (spec instanceof PropertyStatement) {
                agreeVars.add(
                        new AgreeVar(((PropertyStatement) spec).getName(), NamedType.BOOL, spec, compInst));
            }
        }
        return agreeVars;
    }

    private void gatherOutputsInputsTypes(List<AgreeVar> outputs, List<AgreeVar> inputs,
            EList<FeatureInstance> features, Map<NamedElement, String> typeMap,
            Set<RecordType> typeExpressions) {
        for (FeatureInstance feature : features) {
            featureToAgreeVars(outputs, inputs, feature, typeMap, typeExpressions);
        }

    }

    private void featureToAgreeVars(List<AgreeVar> outputs, List<AgreeVar> inputs, FeatureInstance feature,
            Map<NamedElement, String> typeMap, Set<RecordType> typeExpressions) {

        switch (feature.getCategory()) {
        case FEATURE_GROUP:
            List<AgreeVar> newInputs = new ArrayList<>();
            List<AgreeVar> newOutputs = new ArrayList<>();
            gatherOutputsInputsTypes(newOutputs, newInputs, feature.getFeatureInstances(), typeMap,
                    typeExpressions);
            for (AgreeVar agreeVar : newInputs) {
                String newName = feature.getName() + dotChar + agreeVar.id;
                inputs.add(new AgreeVar(newName, agreeVar.type, feature.getFeature(),
                        feature.getComponentInstance()));
            }
            for (AgreeVar agreeVar : newOutputs) {
                String newName = feature.getName() + dotChar + agreeVar.id;
                outputs.add(new AgreeVar(newName, agreeVar.type, feature.getFeature(),
                        feature.getComponentInstance()));
            }
            return;
        case DATA_PORT:
        case EVENT_DATA_PORT:
            portToAgreeVar(outputs, inputs, feature, typeMap, typeExpressions);
            return;
        case DATA_ACCESS:
            break;
        default:
            break; // TODO: handle other types
        }

        return;
    }

    private void portToAgreeVar(List<AgreeVar> outputs, List<AgreeVar> inputs, FeatureInstance feature,
            Map<NamedElement, String> typeMap, Set<RecordType> typeExpressions) {

        DataSubcomponentType dataClass;
        Feature dataFeature = feature.getFeature();
        if (dataFeature instanceof DataPort) {
            DataPort dataPort = (DataPort) dataFeature;
            dataClass = dataPort.getDataFeatureClassifier();
        } else {
            EventDataPort eventDataPort = (EventDataPort) dataFeature;
            dataClass = eventDataPort.getDataFeatureClassifier();
        }

        String name = feature.getName();
        boolean isEvent = feature.getCategory() == FeatureCategory.EVENT_DATA_PORT;
        if (isEvent) {
            AgreeVar var = new AgreeVar(name + eventSuffix, NamedType.BOOL, feature.getFeature(),
                    feature.getComponentInstance());
            switch (feature.getDirection()) {
            case IN:
                inputs.add(var);
                break;
            case OUT:
                outputs.add(var);
                break;
            default:
                throw new AgreeException("Unable to reason about bi-directional event port: "+name);
            }
        }

        if(dataClass == null){
            //we do not reason about this type
            return;
        }
        String typeName = AgreeRecordUtils.getRecordTypeName(dataClass, typeMap, typeExpressions);
        if (typeName == null) {
            //we do not reason about this type
            return;
        }

        Type type = getNamedType(typeName);
        for(RecordType recType : typeExpressions){
            if(recType.id.equals(typeName)){
                type = recType;
                break;
            }
        }
        
        if(type == null){
            throw new AgreeException("The type name should have been created");
        }
       
        AgreeVar agreeVar = new AgreeVar(name, type, feature.getFeature(), feature.getComponentInstance());

        switch (feature.getDirection()) {
        case IN:
            inputs.add(agreeVar);
            break;
        case OUT:
            outputs.add(agreeVar);
            break;
        default:
            break;
        }
    }

    private List<AgreeConnection> getConnections(EList<Connection> connections, ComponentInstance compInst,
            List<AgreeNode> subNodes, boolean latched) {

        Property commTimingProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                OsateResourceUtil.getResourceSet(), "Communication_Properties::Timing");
        List<AgreeConnection> agreeConns = new ArrayList<>();
        for (Connection conn : connections) {

            ConnectedElement absConnDest = conn.getDestination();
            ConnectedElement absConnSour = conn.getSource();
            boolean delayed = false;
            try {
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(conn, commTimingProp);
                delayed = lit.getName().equals("delayed");
            } catch (PropertyDoesNotApplyToHolderException e) {
                delayed = false;
            }
            Context destContext = absConnDest.getContext();
            Context sourContext = absConnSour.getContext();
            // only make connections to things that have annexs
            if (destContext != null && destContext instanceof Subcomponent) {
                ComponentInstance subInst = compInst.findSubcomponentInstance((Subcomponent) destContext);
                if (!AgreeUtils.containsTransitiveAgreeAnnex(subInst)) {
                    continue;
                }
            }
            if (sourContext != null && sourContext instanceof Subcomponent) {
                ComponentInstance subInst = compInst.findSubcomponentInstance((Subcomponent) sourContext);
                if (!AgreeUtils.containsTransitiveAgreeAnnex(subInst)) {
                    continue;
                }
            }

            // agreeConns.add(makeConnectionExpressions(absConnSour,
            // absConnDest, latched, delayed))
            AgreeNode sourceNode = agreeNodeFromNamedEl(subNodes, sourContext);
            AgreeNode destNode = agreeNodeFromNamedEl(subNodes, destContext);

            ConnectionEnd destPort = absConnDest.getConnectionEnd();
            ConnectionEnd sourPort = absConnSour.getConnectionEnd();

            AgreeConnection.ConnectionType connType;
            if (destPort instanceof EventDataPort) {
                if (!(sourPort instanceof EventDataPort)) {
                    AgreeLogger.logWarning("Connection '" + conn.getName() + "' has ports '"
                            + destPort.getName() + "' and '" + sourPort.getName() + "' of differing type");
                    continue;
                }
                connType = ConnectionType.EVENT;

                agreeConns.add(new AgreeConnection(sourceNode, destNode, sourPort.getName() + eventSuffix,
                        destPort.getName() + eventSuffix, connType, latched, delayed, conn));

            }

            if (!(((destPort instanceof DataPort) && (sourPort instanceof DataPort))
                    || ((destPort instanceof EventDataPort) && (sourPort instanceof EventDataPort)))) {
                AgreeLogger.logWarning("Connection '" + conn.getName() + "' has ports '" + destPort.getName()
                        + "' and '" + sourPort.getName() + "' of differing type");
                continue;
            }
            connType = ConnectionType.DATA;

            DataSubcomponentType dataClass;
            if (destPort instanceof DataPort) {
                DataPort dataPort = (DataPort) destPort;
                dataClass = dataPort.getDataFeatureClassifier();
            } else {
                EventDataPort eventDataPort = (EventDataPort) destPort;
                dataClass = eventDataPort.getDataFeatureClassifier();
            }

            if (dataClass == null || getNamedType(AgreeRecordUtils.getRecordTypeName(dataClass, typeMap, globalTypes)) == null) {
                // we don't reason about this type
                continue;
            }

            agreeConns.add(new AgreeConnection(sourceNode, destNode, sourPort.getName(), destPort.getName(),
                    connType, latched, delayed, conn));
        }
        return agreeConns;
    }

    private AgreeNode agreeNodeFromNamedEl(List<AgreeNode> nodes, NamedElement comp) {
        if (comp == null) {
            return null;
        }
        for (AgreeNode node : nodes) {
            if (comp.getName().equals(node.id)) {
                return node;
            }
        }
        return null;
    }

    private Expr getInitialConstraint(EList<SpecStatement> specs) {
        for (SpecStatement spec : specs) {
            if (spec instanceof InitialStatement) {
                return doSwitch(((InitialStatement) spec).getExpr());
            }
        }
        return new BoolExpr(true);
    }

    private Expr getClockConstraint(EList<SpecStatement> specs, List<AgreeNode> subNodes) {
        // NOTE: we generate the constraint that "someone ticks" during the
        // lustre generation
        for (SpecStatement spec : specs) {
            if (spec instanceof MNSynchStatement) {
                return getMNSynchConstraint((MNSynchStatement) spec);
            }
            if (spec instanceof CalenStatement) {
                throw new AgreeException("The use of calendar statements has been depricated");
            }
            if (spec instanceof AsynchStatement) {
                return new BoolExpr(true);
            }
            if (spec instanceof LatchedStatement) {
                return new BoolExpr(true);
            }
            if (spec instanceof SynchStatement) {
                return getSynchConstraint((SynchStatement) spec, subNodes);
            }
        }

        return new BoolExpr(true);
    }

    private Expr getSynchConstraint(SynchStatement spec, List<AgreeNode> subNodes) {

        int val1 = Integer.decode(spec.getVal());
        if (val1 == 0) {
            return new BoolExpr(true);
        }

        List<Expr> clockIds = new ArrayList<>();
        Expr clockAssertion;
        for (AgreeNode subNode : subNodes) {
            clockIds.add(new IdExpr(subNode.clockVar.id));
        }

        String dfaPrefix = AgreeRecordUtils.getObjectLocationPrefix(spec);
        
        if (spec.getVal2() == null) {
            Node dfaNode = AgreeCalendarUtils.getDFANode(dfaPrefix + "__DFA_NODE", val1);
            Node calNode = AgreeCalendarUtils.getCalendarNode(
                    dfaPrefix + "__CALENDAR_NODE", dfaNode.id,
                    clockIds.size());

            //we do not need to make copies of the nodes if they exist
            if (!nodeNameExists(dfaNode.id)) {
                if(nodeNameExists(calNode.id)){
                    throw new AgreeException("The calander node should not exist if the dfa node does not exist");
                }
                addToNodeList(dfaNode);
                addToNodeList(calNode);
            }

            clockAssertion = new NodeCallExpr(calNode.id, clockIds);
        } else {
            int val2 = Integer.decode(spec.getVal2());

            String nodeName = "__calendar_node_" + val1 + "_" + val2;
            nodeName = dfaPrefix + nodeName;
            Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, val1, val2);
            
            if (!nodeNameExists(calNode.id)) {
                addToNodeList(calNode);
            }

            clockAssertion = new BoolExpr(true);
            int i, j;
            for (i = 0; i < clockIds.size(); i++) {
                Expr clock1 = clockIds.get(i);
                for (j = i + 1; j < clockIds.size(); j++) {
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

    private Expr getMNSynchConstraint(MNSynchStatement sync) {

        Set<String> nodeNames = new HashSet<>();
        Expr clockAssertion = new BoolExpr(true);
        for (int i = 0; i < sync.getComp1().size(); i++) {
            Subcomponent maxComp = (Subcomponent) sync.getComp1().get(i);
            Subcomponent minComp = (Subcomponent) sync.getComp2().get(i);

            Expr maxClock = new IdExpr(maxComp.getName() + clockIDSuffix);
            Expr minClock = new IdExpr(minComp.getName() + clockIDSuffix);
            int max = Integer.valueOf(sync.getMax().get(i));
            int min = Integer.valueOf(sync.getMin().get(i));

            MNSynchronyElement elem = new MNSynchronyElement(maxClock, minClock, max, min);

            String nodeName = "__calendar_node_" + elem.max + "_" + elem.min;
            nodeName = AgreeRecordUtils.getObjectLocationPrefix(sync) + nodeName;
            if (!nodeNames.contains(nodeName)) {
                nodeNames.add(nodeName);
                Node calNode = AgreeCalendarUtils.getMNCalendar(nodeName, elem.max, elem.min);
                addToNodeList(calNode);
            }

            NodeCallExpr nodeCall = new NodeCallExpr(nodeName, elem.maxClock, elem.minClock);
            clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
            nodeCall = new NodeCallExpr(nodeName, elem.minClock, elem.maxClock);
            clockAssertion = new BinaryExpr(clockAssertion, BinaryOp.AND, nodeCall);
        }

        return clockAssertion;
    }

    private List<Type> gatherLustreTypes(EList<SpecStatement> specs) {
        List<Type> types = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof RecordDefExpr) {
                // this will record them to the global types
                AgreeRecordUtils.getRecordTypeName((NamedElement) spec, typeMap, globalTypes);
            }
        }
        return types;
    }

    private List<Node> addLustreNodes(EList<SpecStatement> specs) {
        List<Node> nodes = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof NodeDefExpr || spec instanceof FnDefExpr) {
                doSwitch(spec);
            }
        }
        return nodes;
    }

    private List<VarDecl> agreeVarsFromArgs(EList<Arg> args, ComponentInstance compInst) {
        List<VarDecl> agreeVars = new ArrayList<>();
        for (Arg arg : args) {
            // TODO: decide whether or not to make these VarDecls or AgreeVars
            NamedType type =
                    getNamedType(AgreeRecordUtils.getRecordTypeName(arg.getType(), typeMap, globalTypes));
            agreeVars.add(new AgreeVar(arg.getName(), type, arg, compInst));
        }
        return agreeVars;
    }

    private List<AgreeStatement> getAssertionStatements(EList<SpecStatement> specs) {
        List<AgreeStatement> asserts = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof AssertStatement) {
                AssertStatement assertState = (AssertStatement) spec;
                asserts.add(new AgreeStatement(assertState.getStr(), doSwitch(assertState.getExpr()),
                        assertState));
            }
        }
        return asserts;
    }
    
    private List<AgreeStatement> getAssignmentStatements(EList<SpecStatement> specs) {
        List<AgreeStatement> assigns = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof AssignStatement) {
                Expr expr = doSwitch(((AssignStatement) spec).getExpr());
                expr = new BinaryExpr(new IdExpr(((AssignStatement) spec).getId().getBase().getName()), BinaryOp.EQUAL, expr);
                assigns.add(new AgreeStatement("", expr, spec));
            }
        }
        return assigns;
    }
    
    private List<AgreeStatement> getPropertyStatements(EList<SpecStatement> specs) {
        List<AgreeStatement> props = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof PropertyStatement) {
                Expr expr = doSwitch(((PropertyStatement) spec).getExpr());
                expr = new BinaryExpr(new IdExpr(((PropertyStatement) spec).getName()), BinaryOp.EQUAL, expr);
                props.add(new AgreeStatement("", expr, spec));
            }
        }
        return props;
    }

    private List<AgreeStatement> getEquationStatements(EList<SpecStatement> specs) {
        List<AgreeStatement> eqs = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof EqStatement) {
                EqStatement eq = (EqStatement) spec;
                EList<Arg> lhs = eq.getLhs();
                if (eq.getExpr() != null) {
                    Expr expr = doSwitch(eq.getExpr());

                    if (lhs.size() != 1) {
                        List<Expr> ids = new ArrayList<>();
                        for (Arg arg : lhs) {
                            ids.add(new IdExpr(arg.getName()));
                        }
                        TupleExpr tuple = new TupleExpr(ids);
                        expr = new BinaryExpr(tuple, BinaryOp.EQUAL, expr);
                    } else {
                        expr = new BinaryExpr(new IdExpr(lhs.get(0).getName()), BinaryOp.EQUAL, expr);
                    }
                    eqs.add(new AgreeStatement("", expr, spec));
                }
                eqs.addAll(getVariableRangeConstraints(lhs, eq));
            }
        }
        return eqs;
    }
    
    
    
    private List<AgreeStatement> getVariableRangeConstraints(List<Arg> args, EqStatement eq) {
        List<AgreeStatement> constraints = new ArrayList<>();
        for (Arg arg : args) {
            if (arg.getType() instanceof PrimType) {
                PrimType primType = (PrimType) arg.getType();
                String lowStr = primType.getRangeLow();
                String highStr = primType.getRangeHigh();

                if (lowStr != null && highStr != null) {
                    IdExpr id = new IdExpr(arg.getName());
                    int lowSign = primType.getLowNeg() == null ? 1 : -1;
                    int highSign = primType.getHighNeg() == null ? 1 : -1;
                    Expr lowVal = null;
                    Expr highVal = null;

                    switch (primType.getString()) {
                    case "int":
                        long lowl = Long.valueOf(lowStr) * lowSign;
                        long highl = Long.valueOf(highStr) * highSign;
                        lowVal = new IntExpr(BigInteger.valueOf(lowl));
                        highVal = new IntExpr(BigInteger.valueOf(highl));
                        break;
                    case "real":
                        double lowd = Double.valueOf(lowStr) * lowSign;
                        double highd = Double.valueOf(highStr) * highSign;
                        lowVal = new RealExpr(BigDecimal.valueOf(lowd));
                        highVal = new RealExpr(BigDecimal.valueOf(highd));
                        break;
                    default:
                        throw new AgreeException(
                                "Unhandled type '" + primType.getString() + "' in ranged type");
                    }
                    Expr lowBound = new BinaryExpr(lowVal, BinaryOp.LESSEQUAL, id);
                    Expr highBound = new BinaryExpr(id, BinaryOp.LESSEQUAL, highVal);
                    Expr bound = new BinaryExpr(lowBound, BinaryOp.AND, highBound);
                    // must have reference to eq statement so we don't throw
                    // them away later
                    constraints.add(new AgreeStatement("", bound, eq));
                }
            }
        }

        return constraints;
    }

    private List<AgreeStatement> getAssumptionStatements(EList<SpecStatement> specs) {
        List<AgreeStatement> assumptions = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof AssumeStatement) {
                AssumeStatement assumption = (AssumeStatement) spec;
                assumptions.add(
                        new AgreeStatement(assumption.getStr(), doSwitch(assumption.getExpr()), assumption));
            }
        }
        return assumptions;
    }

    private List<AgreeStatement> getGuaranteeStatements(EList<SpecStatement> specs) {
        List<AgreeStatement> guarantees = new ArrayList<>();
        for (SpecStatement spec : specs) {
            if (spec instanceof GuaranteeStatement) {
                GuaranteeStatement guarantee = (GuaranteeStatement) spec;
                guarantees.add(
                        new AgreeStatement(guarantee.getStr(), doSwitch(guarantee.getExpr()), guarantee));
            }
        }
        return guarantees;
    }

    private AgreeContractSubclause getAgreeAnnex(ComponentClassifier comp) {
        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(comp,
                AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                // in newer versions of osate the annex this returns annexes in
                // the type
                // as well as the implementation. We want the annex in the
                // specific component
                EObject container = annex.eContainer();
                while (!(container instanceof ComponentClassifier)) {
                    container = container.eContainer();
                }
                if (container == comp) {
                    return (AgreeContractSubclause) annex;
                }
            }
        }
        return null;
    }

    private static NamedType getNamedType(String name) {
        if (name == null) {
            return null;
        }
        switch (name) {
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

    // BEGIN CASE EXPRESSION STATEMENTS
    @Override
    public Expr caseRecordUpdateExpr(RecordUpdateExpr upExpr) {
        EList<NamedElement> args = upExpr.getArgs();
        EList<com.rockwellcollins.atc.agree.agree.Expr> argExprs = upExpr.getArgExpr();

        Expr lustreExpr = doSwitch(upExpr.getRecord());
        for (int i = 0; i < args.size(); i++) {
            com.rockwellcollins.atc.agree.agree.Expr argExpr = argExprs.get(i);
            NamedElement arg = args.get(i);
            Expr lustreArgExpr = doSwitch(argExpr);
            lustreExpr = new jkind.lustre.RecordUpdateExpr(lustreExpr, arg.getName(), lustreArgExpr);
        }

        return lustreExpr;
    }

    @Override
    public Expr caseRecordExpr(RecordExpr recExpr) {

        EList<NamedElement> agreeArgs = recExpr.getArgs();
        EList<com.rockwellcollins.atc.agree.agree.Expr> agreeArgExprs = recExpr.getArgExpr();
        Map<String, Expr> argExprMap = new HashMap<String, Expr>();

        for (int i = 0; i < agreeArgs.size(); i++) {
            NamedElement agreeArg = agreeArgs.get(i);
            com.rockwellcollins.atc.agree.agree.Expr agreeExpr = agreeArgExprs.get(i);

            Expr lustreExpr = doSwitch(agreeExpr);
            String argName = agreeArg.getName();

            argExprMap.put(argName, lustreExpr);

        }

        NestedDotID recId = recExpr.getRecord();
        while (recId.getSub() != null) {
            recId = recId.getSub();
        }
        String recName = AgreeRecordUtils.getIDTypeStr(recId.getBase());
        return new jkind.lustre.RecordExpr(recName, argExprMap);

    }

    @Override
    public Expr caseFloorCast(FloorCast floor) {
        Expr expr = doSwitch(floor.getExpr());
        Expr castExpr = new CastExpr(NamedType.INT, expr);
        return castExpr;
    }

    @Override
    public Expr caseRealCast(RealCast real) {
        Expr expr = doSwitch(real.getExpr());
        Expr castExpr = new CastExpr(NamedType.REAL, expr);
        return castExpr;
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

        assert(binOp != null);
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
        NamedElement namedEl = AgreeUtils.getFinalNestId(dotId);

        String fnName = AgreeRecordUtils.getNodeName(namedEl);

        boolean found = false;
        for (Node node : globalNodes) {
            if (node.id.equals(fnName)) {
                found = true;
                break;
            }
        }

        if (!found) {
            NestedDotID fn = expr.getFn();
            doSwitch(AgreeUtils.getFinalNestId(fn));
        }

        List<Expr> argResults = new ArrayList<Expr>();

        for (com.rockwellcollins.atc.agree.agree.Expr argExpr : expr.getArgs()) {
            argResults.add(doSwitch(argExpr));
        }

        // TODO are node lemmas depreicated?
        // keep track of node lemmas for node calls
        // if(namedEl instanceof NodeDefExpr){
        // Integer count = nodeCallCount.get(fnName);
        // if(count == null){
        // count = Integer.valueOf(0);
        // }else{
        // count = count + 1;
        // }
        // nodeCallCount.put(fnName, count);
        //
        // Map<String, String> lemmaNames = nodeLemmaNames.get(fnName);
        // for(Entry<String, String> lemmaRenames : lemmaNames.entrySet()){
        // String lemmaName = fnName+"~"+count+"."+lemmaRenames.getKey();
        // refMap.put(lemmaName, expr);
        // this.renaming.addExplicitRename(lemmaName, "("+fnName+"["+count+"]) :
        // "+"\""+lemmaRenames.getValue()+"\"");
        // nodeLemmaProps.add(lemmaName);
        // }
        // }

        NodeCallExpr nodeCall = new NodeCallExpr(fnName, argResults);
        return nodeCall;
    }

    @Override
    public Expr caseFnDefExpr(FnDefExpr expr) {
        String nodeName = AgreeRecordUtils.getNodeName(expr);
        for (Node node : globalNodes) {
            if (node.id.equals(nodeName)) {
                return null;
            }
        }
        List<VarDecl> inputs = agreeVarsFromArgs(expr.getArgs(), null);
        Expr bodyExpr = doSwitch(expr.getExpr());
        NamedType outType =
                getNamedType(AgreeRecordUtils.getRecordTypeName(expr.getType(), typeMap, globalTypes));
        VarDecl outVar = new VarDecl("_outvar", outType);
        List<VarDecl> outputs = Collections.singletonList(outVar);
        Equation eq = new Equation(new IdExpr("_outvar"), bodyExpr);
        List<Equation> eqs = Collections.singletonList(eq);
        
        NodeBuilder builder = new NodeBuilder(nodeName);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addEquations(eqs);
        
        Node node = builder.build();
        addToNodeList(node);
        return null;
    }

    @Override
    public Expr caseNodeDefExpr(NodeDefExpr expr) {
        // System.out.println("Visiting caseNodeDefExpr");

        String nodeName = AgreeRecordUtils.getNodeName(expr);

        for (Node node : globalNodes) {
            if (node.id.equals(nodeName)) {
                return null;
            }
        }

        List<VarDecl> inputs = agreeVarsFromArgs(expr.getArgs(), null);
        List<VarDecl> outputs = agreeVarsFromArgs(expr.getRets(), null);
        NodeBodyExpr body = expr.getNodeBody();
        List<VarDecl> internals = agreeVarsFromArgs(body.getLocs(), null);
        List<Equation> eqs = new ArrayList<Equation>();
        List<String> props = new ArrayList<String>();

        // TODO are node lemmas depricated?
        String lemmaName = "__nodeLemma";
        int lemmaIndex = 0;
        for (NodeStmt stmt : body.getStmts()) {
            if (stmt instanceof NodeLemma) {
                NodeLemma nodeLemma = (NodeLemma) stmt;
                String propName = lemmaName + lemmaIndex++;
                IdExpr eqId = new IdExpr(propName);
                internals.add(new VarDecl(eqId.id, NamedType.BOOL));
                Expr eqExpr = doSwitch(nodeLemma.getExpr());
                Equation eq = new Equation(eqId, eqExpr);
                eqs.add(eq);
                props.add(eqId.id);
            } else if (stmt instanceof NodeEq) {
                eqs.add(nodeEqToEq((NodeEq) stmt));
            }
        }

        // nodeLemmaNames.put(nodeName, lemmaNames);

        NodeBuilder builder = new NodeBuilder(nodeName);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(internals);
        builder.addEquations(eqs);
        builder.addProperties(props);
        
        addToNodeList(builder.build());
        return null;
    }

    // helper method for above
    private Equation nodeEqToEq(NodeEq nodeEq) {
        Expr expr = doSwitch(nodeEq.getExpr());
        List<IdExpr> ids = new ArrayList<IdExpr>();
        for (Arg arg : nodeEq.getLhs()) {
            ids.add(new IdExpr(arg.getName()));
        }
        Equation eq = new Equation(ids, expr);
        return eq;
    }

    @Override
    public Expr caseGetPropertyExpr(GetPropertyExpr expr) {

        NamedElement propName = expr.getProp();
        NamedElement compName = AgreeRecordUtils.namedElFromId(expr.getComponent(), curInst);

        Property prop = (Property) propName;

        PropertyExpression propVal = AgreeUtils.getPropExpression(compName, prop);

        if (propVal == null) {
            throw new AgreeException("Could not locate property value '" + prop.getFullName()
                    + "' in component '" + compName.getName() + "'.  Is it possible "
                    + "that a 'this' statement is used in a context in which it wasn't supposed to?");
        }
        Expr res = null;
        if (propVal != null) {
            if (propVal instanceof StringLiteral) {
                // StringLiteral value = (StringLiteral) propVal;
                // nodeStr += value.getValue() + ")";
                throw new AgreeException(
                        "Property value for '" + prop.getFullName() + "' cannot be of string type");
            } else if (propVal instanceof NamedValue) {
                // NamedValue namedVal = (NamedValue) propVal;
                // AbstractNamedValue absVal = namedVal.getNamedValue();
                // assert (absVal instanceof EnumerationLiteral);
                // EnumerationLiteral enVal = (EnumerationLiteral) absVal;
                throw new AgreeException(
                        "Property value for '" + prop.getFullName() + "' cannot be of enumeration type");
            } else if (propVal instanceof BooleanLiteral) {
                BooleanLiteral value = (BooleanLiteral) propVal;
                res = new BoolExpr(value.getValue());
            } else if (propVal instanceof IntegerLiteral) {
                IntegerLiteral value = (IntegerLiteral) propVal;
                res = new IntExpr(BigInteger.valueOf((long) value.getScaledValue()));
            } else {
                assert(propVal instanceof RealLiteral);
                RealLiteral value = (RealLiteral) propVal;
                res = new RealExpr(BigDecimal.valueOf(value.getValue()));
            }
        }
        assert(res != null);

        return res;
    }

    @Override
    public Expr caseThisExpr(ThisExpr expr) {
        throw new AgreeException("A 'this' expression should never be called in a case statement");
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

        while (id.getSub() != null && (base instanceof FeatureGroup || base instanceof AadlPackage
                || base instanceof Subcomponent)) {
            jKindVar += base.getName() + dotChar;
            aadlVar += base.getName() + ".";
            id = id.getSub();
            base = id.getBase();
        }

        NamedElement namedEl = id.getBase();

        String tag = id.getTag();
        if (tag != null) {

            switch (tag) {
            case "_CLK":
                // a variable of the same name as this should be created by
                // setEventPortQueues()
                // in the AgreeAnnexEmitter which created "this"
                // AgreeAnnexEmitter
                AgreeVarDecl clockVar = new AgreeVarDecl(namedEl.getName() + clockIDSuffix, NamedType.BOOL);

                IdExpr clockId = new IdExpr(clockVar.id);
                return clockId;
            default:
                throw new AgreeException(
                        "use of uknown tag: '" + tag + "' in expression: '" + aadlVar + tag + "'");
            }
        }

        Expr result;
        if (namedEl instanceof ConstStatement) {
            // evaluate the constant
            result = doSwitch(((ConstStatement) namedEl).getExpr());
        } else {
            jKindVar = jKindVar + namedEl.getName();
            result = new IdExpr(jKindVar);
        }

        // this is a record accessrecord
        while (id.getSub() != null) {
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
    public Expr caseEventExpr(EventExpr expr) {

        IdExpr nestIdExpr = (IdExpr) doSwitch(expr.getId());
        String eventStr = nestIdExpr.id + eventSuffix;
        return new IdExpr(eventStr);
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
            assert(false);
        }

        return res;
    }

    private static void addToNodeList(Node node) {
        for (Node inList : globalNodes) {
            if (inList.id.equals(node.id)) {
                throw new AgreeException(
                        "AGREE AST generator tried adding multiple nodes of name '" + node.id + "'");
            }
        }
        globalNodes.add(node);
    }
    
    private static boolean nodeNameExists(String name){
        for(Node inList : globalNodes){
            if(inList.id.equals(name)){
                return true;
            }
        }
        return false;
    }

}
