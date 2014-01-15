package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.BusAccess;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.DataAccess;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EventPort;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.ConnectionReference;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorTransition;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPropagation;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorTypes;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;
import org.osate.xtext.aadl2.errormodel.util.EMV2Util;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.GetProperties;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.ConnectionValue;
import com.rockwellcollins.atc.resolute.analysis.values.IntValue;
import com.rockwellcollins.atc.resolute.analysis.values.NamedElementValue;
import com.rockwellcollins.atc.resolute.analysis.values.RangeValue;
import com.rockwellcollins.atc.resolute.analysis.values.RealValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.analysis.values.SetValue;
import com.rockwellcollins.atc.resolute.analysis.values.StringValue;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFuncCallExpr;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.DefinitionBody;
import com.rockwellcollins.atc.resolute.resolute.ElementSet;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FuncBody;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.LetBinding;
import com.rockwellcollins.atc.resolute.resolute.LetExpr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.QuantArg;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.UnaryExpr;
import com.rockwellcollins.atc.resolute.resolute.util.ResoluteSwitch;

public class ResoluteEvaluator extends ResoluteSwitch<ResoluteValue> {
    // Stack for function, claim, and quantifier arguments
    protected final Deque<Map<NamedElement, ResoluteValue>> varStack = new LinkedList<>();

    // Keeps track of context of the initial prove statement
    protected final EvaluationContext context;

    final private static BoolValue TRUE = new BoolValue(true);
    final private static BoolValue FALSE = new BoolValue(false);

    public ResoluteEvaluator(EvaluationContext context, Map<NamedElement, ResoluteValue> env) {
        this.context = context;
        if (env == null) {
            Map<NamedElement, ResoluteValue> emptyMap = Collections.emptyMap();
            this.varStack.push(emptyMap);
        } else {
            this.varStack.push(new HashMap<>(env));
        }
    }

    public EvaluationContext getEvaluationContext() {
        return context;
    }

    @Override
    public ResoluteValue caseFailExpr(FailExpr object) {
        if (object.getVal() != null) {
            throw new ResoluteFailException(object.getVal().getValue().replace("\"", ""), object);
        } else {
            throw new ResoluteFailException("Fail Statement Reached", object);
        }
    }

    @Override
    public ResoluteValue caseConstantDefinition(ConstantDefinition object) {
        return doSwitch(object.getExpr());
    }

    @Override
    public ResoluteValue caseArg(Arg object) {
        return varStack.peek().get(object);
    }

    @Override
    public ResoluteValue caseBinaryExpr(BinaryExpr object) {
        String op = object.getOp();

        // Short circuit ops first
        ResoluteValue leftValue = doSwitch(object.getLeft());
        switch (op) {
        case "and":
            if (leftValue.getBool()) {
                return doSwitch(object.getRight());
            } else {
                return FALSE;
            }

        case "or":
            if (leftValue.getBool()) {
                return TRUE;
            } else {
                return doSwitch(object.getRight());
            }

        case "=>":
            if (leftValue.getBool()) {
                return doSwitch(object.getRight());
            } else {
                return TRUE;
            }
        }

        ResoluteValue rightValue = doSwitch(object.getRight());
        switch (op) {
        case "=":
            return new BoolValue(leftValue.equals(rightValue));

        case "<>":
            return new BoolValue(!leftValue.equals(rightValue));

        case "+":
            if (leftValue.isInt()) {
                return new IntValue(leftValue.getInt() + rightValue.getInt());
            } else {
                return new RealValue(leftValue.getReal() + rightValue.getReal());
            }

        case "-":
            if (leftValue.isInt()) {
                return new IntValue(leftValue.getInt() - rightValue.getInt());
            } else {
                return new RealValue(leftValue.getReal() - rightValue.getReal());
            }

        case "*":
            if (leftValue.isInt()) {
                return new IntValue(leftValue.getInt() * rightValue.getInt());
            } else {
                return new RealValue(leftValue.getReal() * rightValue.getReal());
            }

        case "/":
            if (leftValue.isInt()) {
                return new IntValue(leftValue.getInt() / rightValue.getInt());
            } else {
                return new RealValue(leftValue.getReal() / rightValue.getReal());
            }

        case "in":
            return new BoolValue(rightValue.getSet().contains(leftValue));

        case "union": {
            Set<ResoluteValue> union = new HashSet<ResoluteValue>();
            union.addAll(leftValue.getSet());
            union.addAll(rightValue.getSet());
            return new SetValue(union);
        }

        case "intersect": {
            Set<ResoluteValue> intersect = new HashSet<ResoluteValue>();
            intersect.addAll(leftValue.getSet());
            intersect.retainAll(rightValue.getSet());
            return new SetValue(intersect);
        }

        case "<":
            return new BoolValue(leftValue.compareTo(rightValue) < 0);

        case ">":
            return new BoolValue(leftValue.compareTo(rightValue) > 0);

        case "<=":
            return new BoolValue(leftValue.compareTo(rightValue) <= 0);

        case ">=":
            return new BoolValue(leftValue.compareTo(rightValue) >= 0);

        default:
            throw new IllegalArgumentException("Unknown binary operator: " + op);
        }
    }

    @Override
    public ResoluteValue caseUnaryExpr(UnaryExpr object) {
        switch (object.getOp()) {
        case "not":
            return new BoolValue(!doSwitch(object.getExpr()).getBool());

        case "-": {
            ResoluteValue value = doSwitch(object.getExpr());
            if (value.isInt()) {
                return new IntValue(-value.getInt());
            } else {
                return new RealValue(-value.getReal());
            }
        }

        default:
            throw new IllegalArgumentException("Unknown unary operator: " + object.getOp());
        }
    }

    @Override
    public ResoluteValue caseIdExpr(IdExpr object) {
        NamedElement ref = object.getId();
        ElementSet subEls = object.getSubelements();
        if (subEls != null) {
            return subelements(ref, subEls.getName());
        } else if (ref instanceof Classifier || ref instanceof Property) {
            return new NamedElementValue(ref);
        } else {
            return doSwitch(ref);
        }
    }

    private ResoluteValue subelements(NamedElement id, String setName) {
        ComponentInstance compInst = (ComponentInstance) doSwitch(id).getNamedElement();

        if (setName.equals("connections")) {
            Set<ConnectionInstance> connSet = new HashSet<ConnectionInstance>();
            // if (compInst.getCategory() == ComponentCategory.THREAD) {
            for (FeatureInstance feature : compInst.getFeatureInstances()) {
                addAllFeatureGroupConns(feature, connSet);
            }
            // }

            for (ConnectionInstance conn : compInst.getSrcConnectionInstances()) {
                connSet.add(conn);
            }
            for (ConnectionInstance conn : compInst.getDstConnectionInstances()) {
                connSet.add(conn);
            }

            // go through the connection set and make doubles of bidrectional connections
            Set<ResoluteValue> returnSet = new HashSet<ResoluteValue>();
            for (ConnectionInstance conn : connSet) {
                if (conn.isBidirectional()) {
                    returnSet.add(new ConnectionValue(conn, true));
                }
                returnSet.add(new ConnectionValue(conn, false));
            }
            return new SetValue(returnSet);
        }

        if (setName.equals("features")) {
            List<FeatureInstance> features = compInst.getFeatureInstances();
            Set<ResoluteValue> returnSet = new HashSet<ResoluteValue>();

            for (FeatureInstance feature : features) {
                addAllFeatureGroupFeatures(feature, returnSet);
            }
            return new SetValue(returnSet);
        }

        Set<ResoluteValue> resultSet = new HashSet<ResoluteValue>();
        ComponentCategory category = getCategory(setName);
        for (ComponentInstance child : compInst.getComponentInstances()) {
            if (category == child.getCategory() || setName.equals("components")) {
                resultSet.add(new NamedElementValue(child));
            }
        }
        return new SetValue(resultSet);
    }

    private ComponentCategory getCategory(String name) {
        switch (name) {
        case "threads":
            return ComponentCategory.THREAD;
        case "data":
            return ComponentCategory.DATA;
        case "memory":
            return ComponentCategory.MEMORY;
        case "thread_groups":
            return ComponentCategory.THREAD_GROUP;
        case "processes":
            return ComponentCategory.PROCESS;
        case "subprograms":
            return ComponentCategory.SUBPROGRAM;
        case "subprogram_groups":
            return ComponentCategory.SUBPROGRAM_GROUP;
        case "processors":
            return ComponentCategory.PROCESSOR;
        case "virtual_processors":
            return ComponentCategory.VIRTUAL_PROCESSOR;
        case "buses":
            return ComponentCategory.BUS;
        case "virtual_buses":
            return ComponentCategory.VIRTUAL_BUS;
        case "devices":
            return ComponentCategory.DEVICE;
        case "systems":
            return ComponentCategory.SYSTEM;
        case "abstracts":
            return ComponentCategory.ABSTRACT;
        case "components":
            return null;
        default:
            throw new IllegalArgumentException("Unknown category: " + name);
        }
    }

    private void addAllFeatureGroupConns(FeatureInstance feature, Set<ConnectionInstance> returnSet) {
        for (FeatureInstance featInst : feature.getFeatureInstances()) {
            addAllFeatureGroupConns(featInst, returnSet);
        }
        for (ConnectionInstance conn : feature.getSrcConnectionInstances()) {
            returnSet.add(conn);
        }
        for (ConnectionInstance conn : feature.getDstConnectionInstances()) {
            returnSet.add(conn);
        }
    }

    private void addAllFeatureGroupFeatures(FeatureInstance feature, Set<ResoluteValue> returnSet) {
        for (FeatureInstance featInst : feature.getFeatureInstances()) {
            addAllFeatureGroupFeatures(featInst, returnSet);
        }
        if (feature.getFeatureInstances().size() == 0) {
            returnSet.add(new NamedElementValue(feature));
        }
    }

    @Override
    public ResoluteValue caseThisExpr(ThisExpr object) {
        ComponentInstance curr = context.getThisInstance();
        for (NestedDotID id = object.getSub(); id != null; id = id.getSub()) {
            curr = getInstanceChild(curr, id.getBase());
        }
        return new NamedElementValue(curr);
    }

    public ComponentInstance getInstanceChild(ComponentInstance instance, NamedElement subcomponent) {
        for (ComponentInstance child : instance.getComponentInstances()) {
            if (child.getSubcomponent().equals(subcomponent)) {
                return child;
            }
        }
        throw new IllegalArgumentException("Unable to find subcomponent " + subcomponent.getName()
                + " in instance of " + instance.getComponentClassifier().getName());
    }

    @Override
    public ResoluteValue caseIntExpr(IntExpr object) {
        return new IntValue(object.getVal().getValue());
    }

    @Override
    public ResoluteValue caseRealExpr(RealExpr object) {
        return new RealValue(object.getVal().getValue());
    }

    @Override
    public ResoluteValue caseBoolExpr(BoolExpr object) {
        return new BoolValue(object.getVal().getValue());
    }

    @Override
    public ResoluteValue caseStringExpr(StringExpr object) {
        // there are an extra set of quotes ("") around a StringExpr
        // that need to be removed
        return new StringValue(object.getVal().getValue().replace("\"", ""));
    }

    @Override
    public ResoluteValue caseIfThenElseExpr(IfThenElseExpr object) {
        if (doSwitch(object.getCond()).getBool()) {
            return doSwitch(object.getThen());
        } else {
            return doSwitch(object.getElse());
        }
    }

    @Override
    public ResoluteValue caseQuantifiedExpr(QuantifiedExpr object) {
        switch (object.getQuant()) {
        case "exists":
            return exists(object.getArgs(), object.getExpr());

        case "forall":
            return forall(object.getArgs(), object.getExpr());

        default:
            throw new IllegalArgumentException("Unknown quantifier: " + object.getQuant());
        }
    }

    private ResoluteValue exists(List<Arg> args, Expr body) {
        if (args.isEmpty()) {
            return doSwitch(body);
        } else {
            Arg arg = args.get(0);
            List<Arg> rest = args.subList(1, args.size());
            for (ResoluteValue value : getArgSet(arg)) {
                varStack.peek().put(arg, value);
                if (exists(rest, body).getBool()) {
                    return TRUE;
                }
            }
            return FALSE;
        }
    }

    private ResoluteValue forall(List<Arg> args, Expr body) {
        if (args.isEmpty()) {
            return doSwitch(body);
        } else {
            Arg arg = args.get(0);
            List<Arg> rest = args.subList(1, args.size());
            for (ResoluteValue value : getArgSet(arg)) {
                varStack.peek().put(arg, value);
                if (!forall(rest, body).getBool()) {
                    return FALSE;
                }
            }
            return TRUE;
        }
    }

    public Set<ResoluteValue> getArgSet(Arg arg) {
        if (arg instanceof QuantArg) {
            QuantArg quantArg = (QuantArg) arg;
            return doSwitch(quantArg.getExpr()).getSet();
        } else {
            Set<ResoluteValue> values = new HashSet<ResoluteValue>();
            for (NamedElement ne : context.getSet(arg.getType().getName())) {
                values.add(new NamedElementValue(ne));
            }
            return values;
        }
    }

    @Override
    public ResoluteValue caseFnCallExpr(FnCallExpr object) {
        if (object.getFn().getBody() instanceof FuncBody) {
            return functionCall(object);
        } else {
            throw new ResoluteFailException("Encountered claim call in evaluator", object);
        }
    }

    private ResoluteValue functionCall(FnCallExpr object) {
        FunctionDefinition funcDef = object.getFn();
        DefinitionBody body = funcDef.getBody();
        List<ResoluteValue> argVals = doSwitchList(object.getArgs());

        varStack.push(pairArguments(funcDef.getArgs(), argVals));
        ResoluteValue value = doSwitch(body.getExpr());
        varStack.pop();

        return value;
    }

    public static Map<NamedElement, ResoluteValue> pairArguments(List<Arg> args,
            List<ResoluteValue> argVals) {
        Map<NamedElement, ResoluteValue> result = new HashMap<>();
        for (int i = 0; i < args.size(); i++) {
            result.put(args.get(i), argVals.get(i));
        }
        return result;
    }

    @Override
    public ResoluteValue caseFilterMapExpr(FilterMapExpr object) {
        return new SetValue(filterMap(object.getArgs(), object.getMap(), object.getFilter()));
    }

    private Set<ResoluteValue> filterMap(List<Arg> args, Expr map, Expr filter) {
        if (args.isEmpty()) {
            return filter(map, filter);
        } else {
            Arg arg = args.get(0);
            List<Arg> rest = args.subList(1, args.size());
            Set<ResoluteValue> result = new HashSet<ResoluteValue>();
            for (ResoluteValue value : getArgSet(arg)) {
                varStack.peek().put(arg, value);
                result.addAll(filterMap(rest, map, filter));
            }
            return result;
        }
    }

    private Set<ResoluteValue> filter(Expr map, Expr filter) {
        if (filter == null || doSwitch(filter).getBool()) {
            return Collections.singleton(doSwitch(map));
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public ResoluteValue caseLetExpr(LetExpr object) {
        LetBinding binding = object.getBinding();
        ResoluteValue boundValue = doSwitch(binding.getExpr());
        varStack.peek().put(binding, boundValue);
        return doSwitch(object.getExpr());
    }

    @Override
    public ResoluteValue caseLetBinding(LetBinding object) {
        return varStack.peek().get(object);
    }

    @Override
    public ResoluteValue caseBuiltInFuncCallExpr(BuiltInFuncCallExpr object) {
        String funName = object.getFn();
        List<ResoluteValue> argVals = doSwitchList(object.getArgs());

        switch (funName) {
        case "error_state_reachable": {
            ComponentInstance comp = (ComponentInstance) argVals.get(0).getNamedElement();
            String stateName = argVals.get(1).getString();

            for (ErrorBehaviorTransition ebt : EMV2Util.getAllErrorBehaviorTransitions(comp)) {
                if (ebt.getTarget().getName().equalsIgnoreCase(stateName)) {
                    return TRUE;
                }
            }

            return FALSE;
        }

        case "propagate_error": {
            ComponentInstance comp = (ComponentInstance) argVals.get(0).getNamedElement();
            String errorName = argVals.get(1).getString();

            for (ErrorPropagation ep : EMV2Util.getAllOutgoingErrorPropagations(comp
                    .getComponentClassifier())) {
                for (TypeToken tt : ep.getTypeSet().getTypeTokens()) {
                    for (ErrorTypes et : tt.getType()) {
                        if (et.getName().equalsIgnoreCase(errorName)) {
                            return TRUE;
                        }
                    }
                }
            }

            return FALSE;
        }

        case "connected": {
            // TODO: come up with better defined semantics about what it means
            // to be "connected"
            ComponentInstance srcComp = (ComponentInstance) argVals.get(0).getNamedElement();
            ConnectionInstance conn = (ConnectionInstance) argVals.get(1).getNamedElement();
            ComponentInstance dstComp = (ComponentInstance) argVals.get(2).getNamedElement();

            ConnectionInstanceEnd dest = conn.getDestination();
            ConnectionInstanceEnd src = conn.getSource();

            Property accessRightProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                    OsateResourceUtil.getResourceSet(), "Memory_Properties::Access_Right");

            // code for data accesses
            if (dest instanceof FeatureInstance
                    && ((FeatureInstance) dest).getCategory() == FeatureCategory.DATA_ACCESS) {
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(dest, accessRightProp);
                if (dstComp.equals(dest.eContainer()) && srcComp.equals(src.eContainer())) {
                    if (lit.getName().equals("read_only") || lit.getName().equals("read_write")) {
                        return TRUE;
                    }
                }
                if (srcComp.equals(dest.eContainer()) && dstComp.equals(src.eContainer())) {
                    if (lit.getName().equals("write_only") || lit.getName().equals("read_write")) {
                        return TRUE;
                    }
                }
                return FALSE;
            }

            // code for data accesses
            if (src instanceof FeatureInstance
                    && ((FeatureInstance) src).getCategory() == FeatureCategory.DATA_ACCESS) {
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(src, accessRightProp);
                if (dstComp.equals(dest.eContainer()) && srcComp.equals(src.eContainer())) {
                    if (lit.getName().equals("write_only") || lit.getName().equals("read_write")) {
                        return TRUE;
                    }
                }
                if (srcComp.equals(dest.eContainer()) && dstComp.equals(src.eContainer())) {
                    if (lit.getName().equals("read_only") || lit.getName().equals("read_write")) {
                        return TRUE;
                    }
                }
                return FALSE;
            }

            return new BoolValue(src.getComponentInstance().equals(srcComp)
                    && dest.getComponentInstance().equals(dstComp));
        }

        case "property_lookup": {
            NamedElement element = argVals.get(0).getNamedElement();
            Property prop = (Property) argVals.get(1).getNamedElement();

            PropertyExpression expr = getPropExpression(element, prop);
            if (expr == null) {
                throw new ResoluteFailException("Property " + prop.getName() + " not defined on "
                        + element.getContainingClassifier().getName(), object);
            }

            return exprToValue(expr);
        }

        case "upper_bound": {
            RangeValue rv = (RangeValue) argVals.get(0);
            return rv.getMax();
        }

        case "lower_bound": {
            RangeValue rv = (RangeValue) argVals.get(0);
            return rv.getMin();
        }

        case "property_exists": {
            NamedElement element = argVals.get(0).getNamedElement();
            Property prop = (Property) argVals.get(1).getNamedElement();

            if (element instanceof ConnectionInstance) {
                ConnectionInstance conn = (ConnectionInstance) element;
                for (ConnectionReference ref : conn.getConnectionReferences()) {
                    if (getPropExpression(ref, prop) != null) {
                        return TRUE;
                    }
                }
                return FALSE;
            } else {
                return new BoolValue(getPropExpression(element, prop) != null);
            }
        }

        case "class_of": {
            NamedElement ne = argVals.get(0).getNamedElement();
            ComponentClassifier classifier = (ComponentClassifier) argVals.get(1).getNamedElement();
            if (ne instanceof FeatureInstance) {
                ne = ((FeatureInstance) ne).getFeature();
            }

            if (ne instanceof ComponentInstance) {
                ComponentInstance comp = (ComponentInstance) ne;
                return new BoolValue(comp.getComponentClassifier().equals(classifier));
            } else if (ne instanceof BusAccess) {
                BusAccess busAccess = (BusAccess) ne;
                return new BoolValue(busAccess.getBusFeatureClassifier().equals(classifier));
            }

            throw new IllegalArgumentException("Unable to get class of " + ne.getName());
        }

        case "type": {
            NamedElement element = argVals.get(0).getNamedElement();
            NamedElement type = builtinType(element);
            if (type == null) {
                throw new IllegalArgumentException("Unable to get type of: " + element);
            }
            return new NamedElementValue(type);
        }

        case "has_type": {
            NamedElement element = argVals.get(0).getNamedElement();
            NamedElement type = builtinType(element);
            return new BoolValue(type != null);
        }

        case "name": {
            NamedElement ne = argVals.get(0).getNamedElement();
            String name = argVals.get(1).getString();
            return new BoolValue(ne.getName().equalsIgnoreCase(name));
        }

        case "subcomponent_of": {
            ComponentInstance child = (ComponentInstance) argVals.get(0).getNamedElement();
            ComponentInstance ancestor = (ComponentInstance) argVals.get(1).getNamedElement();

            ComponentInstance curr = child;
            while (curr.getContainingComponentInstance() != null && !curr.equals(ancestor)) {
                curr = curr.getContainingComponentInstance();
            }
            return new BoolValue(curr.equals(ancestor));
        }

        case "bound": {
            ComponentInstance logical = (ComponentInstance) argVals.get(0).getNamedElement();
            ComponentInstance physical = (ComponentInstance) argVals.get(1).getNamedElement();
            boolean bound = GetProperties.getActualMemoryBinding(logical).contains(physical)
                    || GetProperties.getActualConnectionBinding(logical).contains(physical)
                    || GetProperties.getActualProcessorBinding(logical).contains(physical);
            return new BoolValue(bound);
        }

        case "contained": {
            NamedElement innerEl = argVals.get(0).getNamedElement();
            ComponentInstance outerComp = (ComponentInstance) argVals.get(1).getNamedElement();

            if (innerEl instanceof ConnectionInstance) {
                ConnectionInstance conn = (ConnectionInstance) innerEl;
                ConnectionInstanceEnd dst = conn.getDestination();
                ConnectionInstanceEnd src = conn.getSource();
                return new BoolValue(dst.getComponentInstance().equals(outerComp)
                        || src.getComponentInstance().equals(outerComp));
            }

            ComponentInstance innerComp = (ComponentInstance) innerEl;
            ComponentInstance innerCompContainer = innerComp.getContainingComponentInstance();
            return new BoolValue(outerComp.equals(innerCompContainer));
        }

        case "conn_source": {
            ConnectionValue resoluteConnVal = (ConnectionValue) argVals.get(0);
            ConnectionInstance conn = (ConnectionInstance) resoluteConnVal.getNamedElement();

            ConnectionInstanceEnd dst;
            ConnectionInstanceEnd src;
            if (resoluteConnVal.fReverseDirection) {
                dst = conn.getSource();
                src = conn.getDestination();
            } else {
                dst = conn.getDestination();
                src = conn.getSource();
            }

            Property accessRightProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                    OsateResourceUtil.getResourceSet(), "Memory_Properties::Access_Right");

            // code for data accesses
            if (dst instanceof FeatureInstance
                    && ((FeatureInstance) dst).getCategory() == FeatureCategory.DATA_ACCESS) {
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(dst, accessRightProp);
                NamedElement namedEl = lit.getName().equals("read_write")
                        || lit.getName().equals("write_only") ? dst : src;
                ComponentInstance comp = EcoreUtil2.getContainerOfType(namedEl,
                        ComponentInstance.class);
                return new NamedElementValue(comp);
            }

            // code for data accesses
            if (src instanceof FeatureInstance
                    && ((FeatureInstance) src).getCategory() == FeatureCategory.DATA_ACCESS) {
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(src, accessRightProp);
                NamedElement namedEl = lit.getName().equals("read_write")
                        || lit.getName().equals("write_only") ? src : dst;
                ComponentInstance comp = EcoreUtil2.getContainerOfType(namedEl,
                        ComponentInstance.class);
                return new NamedElementValue(comp);
            }

            return new NamedElementValue(src.getComponentInstance());
        }

        case "conn_dest": {
            ConnectionValue resoluteConnVal = (ConnectionValue) argVals.get(0);
            ConnectionInstance conn = (ConnectionInstance) resoluteConnVal.getNamedElement();

            ConnectionInstanceEnd dst;
            ConnectionInstanceEnd src;
            if (resoluteConnVal.fReverseDirection) {
                dst = conn.getSource();
                src = conn.getDestination();
            } else {
                dst = conn.getDestination();
                src = conn.getSource();
            }

            Property accessRightProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                    OsateResourceUtil.getResourceSet(), "Memory_Properties::Access_Right");

            // code for data accesses
            if (dst instanceof FeatureInstance
                    && ((FeatureInstance) dst).getCategory() == FeatureCategory.DATA_ACCESS) {
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(dst, accessRightProp);
                NamedElement namedEl = lit.getName().equals("read_write")
                        || lit.getName().equals("read_only") ? dst : src;
                ComponentInstance comp = EcoreUtil2.getContainerOfType(namedEl,
                        ComponentInstance.class);
                return new NamedElementValue(comp);
            }

            // code for data accesses
            if (src instanceof FeatureInstance
                    && ((FeatureInstance) src).getCategory() == FeatureCategory.DATA_ACCESS) {
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(src, accessRightProp);
                NamedElement namedEl = lit.getName().equals("read_write")
                        || lit.getName().equals("read_only") ? src : dst;
                ComponentInstance comp = EcoreUtil2.getContainerOfType(namedEl,
                        ComponentInstance.class);
                return new NamedElementValue(comp);
            }

            return new NamedElementValue(dst.getComponentInstance());
        }

        case "sum": {
            Set<ResoluteValue> set = argVals.get(0).getSet();
            if (set.isEmpty()) {
                return new IntValue(0);
            }

            ResoluteValue first = set.iterator().next();
            if (first.isInt()) {
                long sum = 0;
                for (ResoluteValue item : set) {
                    sum += item.getInt();
                }
                return new IntValue(sum);
            } else {
                double sum = 0;
                for (ResoluteValue item : set) {
                    sum += item.getReal();
                }
                return new RealValue(sum);
            }
        }

        case "analysis": {
            String analysisName = argVals.get(0).getString();
            List<ResoluteValue> analysisArgVals = argVals.subList(1, argVals.size());

            ResoluteValue value = EvaluateExternalAnalysis.evaluate(this, analysisName,
                    analysisArgVals);

            if (value == null) {
                throw new ResoluteFailException("External analysis '" + analysisName + "' failed",
                        object);
            } else {
                return value;
            }
        }

        case "instance": {
            NamedElement decl = argVals.get(0).getNamedElement();
            SystemInstance top = context.getThisInstance().getSystemInstance();
            ComponentInstance result = null;
            for (ComponentInstance ci : top.getAllComponentInstances()) {
                if (isInstanceOf(ci, decl)) {
                    if (result == null) {
                        result = ci;
                    } else {
                        throw new ResoluteFailException(
                                "Found multiple instances of declarative element", object);
                    }
                }
            }
            if (result != null) {
                return new NamedElementValue(result);
            } else {
                throw new ResoluteFailException("Failed to find instance of declarative element",
                        object);
            }
        }

        case "is_bidirectional": {
            ConnectionInstance conn = (ConnectionInstance) argVals.get(0).getNamedElement();
            return new BoolValue(conn.isBidirectional());
        }

        case "is_bus_access": {
            ConnectionInstance conn = (ConnectionInstance) argVals.get(0).getNamedElement();
            FeatureInstance featInst;
            if (conn.getSource() instanceof FeatureInstance) {
                featInst = (FeatureInstance) conn.getSource();
            } else {
                featInst = (FeatureInstance) conn.getDestination();
            }
            return new BoolValue(featInst.getFeature() instanceof BusAccess);
        }

        case "is_data_access": {
            ConnectionInstance conn = (ConnectionInstance) argVals.get(0).getNamedElement();
            FeatureInstance featInst;
            if (conn.getSource() instanceof FeatureInstance) {
                featInst = (FeatureInstance) conn.getSource();
            } else {
                featInst = (FeatureInstance) conn.getDestination();
            }
            return new BoolValue(featInst.getFeature() instanceof DataAccess);
        }

        case "is_data_port": {
            ConnectionInstance conn = (ConnectionInstance) argVals.get(0).getNamedElement();
            FeatureInstance featInst;
            if (conn.getSource() instanceof FeatureInstance) {
                featInst = (FeatureInstance) conn.getSource();
            } else {
                featInst = (FeatureInstance) conn.getDestination();
            }
            return new BoolValue(featInst.getFeature() instanceof DataPort);
        }

        case "is_event_port": {
            ConnectionInstance conn = (ConnectionInstance) argVals.get(0).getNamedElement();
            FeatureInstance featInst;
            if (conn.getSource() instanceof FeatureInstance) {
                featInst = (FeatureInstance) conn.getSource();
            } else {
                featInst = (FeatureInstance) conn.getDestination();
            }
            return new BoolValue(featInst.getFeature() instanceof EventPort);
        }

        case "is_data": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.DATA);
        }

        case "is_thread": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.THREAD);
        }

        case "is_thread_group": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.THREAD_GROUP);
        }

        case "is_process": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.PROCESS);
        }

        case "is_subprogram": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.SUBPROGRAM);
        }

        case "is_subprogram_group": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.SUBPROGRAM_GROUP);
        }

        case "is_processor": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.PROCESSOR);
        }

        case "is_virtual_processor": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.VIRTUAL_PROCESSOR);
        }

        case "is_memory": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.MEMORY);
        }

        case "is_bus": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.BUS);
        }

        case "is_virtual_bus": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.VIRTUAL_BUS);
        }

        case "is_device": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.DEVICE);
        }

        case "is_system": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.SYSTEM);
        }

        case "is_abstract": {
            ComponentInstance ci = (ComponentInstance) argVals.get(0).getNamedElement();
            return new BoolValue(ci.getCategory() == ComponentCategory.ABSTRACT);
        }

        case "is_empty": {
            return new BoolValue(argVals.get(0).getSet().isEmpty());
        }

        case "is_connected": {
            NamedElement namedEl = argVals.get(0).getNamedElement();
            FeatureInstance feat = (FeatureInstance) namedEl;
            List<ConnectionValue> conns = context.getConnectionsForFeature(feat);
            return new BoolValue(!conns.isEmpty());

        }

        case "connections": {
            NamedElement namedEl = argVals.get(0).getNamedElement();
            FeatureInstance feat = (FeatureInstance) namedEl;
            List<ConnectionValue> conns = context.getConnectionsForFeature(feat);
            return new SetValue(conns);
        }

        case "singleton": {
            return new SetValue(Collections.singleton(argVals.get(0)));
        }

        default:
            throw new IllegalArgumentException("Unknown function: " + funName);
        }
    }

    private boolean isInstanceOf(ComponentInstance instance, NamedElement declarative) {
        ComponentClassifier cc = instance.getComponentClassifier();
        if (cc.equals(declarative)) {
            return true;
        }

        if (cc instanceof ComponentImplementation) {
            ComponentImplementation ci = (ComponentImplementation) cc;
            return (ci.getType().equals(declarative));
        }

        return false;
    }

    private static ResoluteValue exprToValue(PropertyExpression expr) {
        if (expr instanceof StringLiteral) {
            StringLiteral value = (StringLiteral) expr;
            return new StringValue(value.getValue());
        } else if (expr instanceof NamedValue) {
            NamedValue namedVal = (NamedValue) expr;
            AbstractNamedValue absVal = namedVal.getNamedValue();
            EnumerationLiteral enVal = (EnumerationLiteral) absVal;
            return new StringValue(enVal.getName());
        } else if (expr instanceof BooleanLiteral) {
            BooleanLiteral value = (BooleanLiteral) expr;
            return new BoolValue(value.getValue());
        } else if (expr instanceof IntegerLiteral) {
            IntegerLiteral value = (IntegerLiteral) expr;
            return new IntValue((long) value.getScaledValue());
        } else if (expr instanceof RealLiteral) {
            RealLiteral value = (RealLiteral) expr;
            return new RealValue(value.getValue());
        } else if (expr instanceof org.osate.aadl2.RangeValue) {
            org.osate.aadl2.RangeValue value = (org.osate.aadl2.RangeValue) expr;
            return new RangeValue(exprToValue(value.getMinimum()), exprToValue(value.getMaximum()));
        } else {
            throw new IllegalArgumentException("Unknown property expression type: "
                    + expr.getClass().getName());
        }
    }

    private static NamedElement builtinType(NamedElement ne) {
        if (ne instanceof ConnectionInstance) {
            ConnectionInstance ci = (ConnectionInstance) ne;
            if (ci.getSource() instanceof FeatureInstance) {
                FeatureInstance src = (FeatureInstance) ci.getSource();
                return (NamedElement) src.getFeature().getFeatureClassifier();
            } else if (ci.getSource() instanceof ComponentInstance) {
                return ci.getSource();
            }
        } else if (ne instanceof DataPort) {
            DataPort dp = (DataPort) ne;
            return dp.getDataFeatureClassifier();
        } else if (ne instanceof FeatureInstance) {
            FeatureInstance fi = (FeatureInstance) ne;
            return (NamedElement) fi.getFeature().getFeatureClassifier();
        }

        return null;
    }

    private static PropertyExpression getPropExpression(NamedElement comp, Property prop) {
        try {
            comp.getPropertyValue(prop); // this just checks to see if the
                                         // property is associated
            return PropertyUtils.getSimplePropertyValue(comp, prop);
        } catch (PropertyDoesNotApplyToHolderException propException) {
            return null;
        } catch (PropertyNotPresentException propNotPresentException) {
            return null;
        }
    }

    public List<ResoluteValue> doSwitchList(List<? extends EObject> list) {
        List<ResoluteValue> values = new ArrayList<>();
        for (EObject e : list) {
            values.add(doSwitch(e));
        }
        return values;
    }
}
