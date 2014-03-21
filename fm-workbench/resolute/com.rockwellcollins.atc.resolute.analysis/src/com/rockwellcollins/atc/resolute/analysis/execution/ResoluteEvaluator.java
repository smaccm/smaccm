package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.IntValue;
import com.rockwellcollins.atc.resolute.analysis.values.NamedElementValue;
import com.rockwellcollins.atc.resolute.analysis.values.RealValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.analysis.values.SetValue;
import com.rockwellcollins.atc.resolute.analysis.values.StringValue;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BaseType;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.CastExpr;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.DefinitionBody;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionBody;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.InstanceOfExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.LetBinding;
import com.rockwellcollins.atc.resolute.resolute.LetExpr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.QuantArg;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.SetExpr;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.UnaryExpr;
import com.rockwellcollins.atc.resolute.resolute.util.ResoluteSwitch;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ResoluteEvaluator extends ResoluteSwitch<ResoluteValue> {
    // Stack for function, claim, and quantifier arguments
    protected final Deque<Map<NamedElement, ResoluteValue>> varStack = new LinkedList<>();

    // Keeps track of context of the initial prove statement
    protected final EvaluationContext context;

    private static final BoolValue TRUE = new BoolValue(true);
    private static final BoolValue FALSE = new BoolValue(false);

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
        if (ref instanceof Classifier || ref instanceof Property) {
            return new NamedElementValue(ref);
        } else {
            return doSwitch(ref);
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
        return new IntValue((long) object.getVal().getScaledValue());
    }

    @Override
    public ResoluteValue caseRealExpr(RealExpr object) {
        return new RealValue(object.getVal().getScaledValue());
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

    public SortedSet<ResoluteValue> getArgSet(Arg arg) {
        if (arg instanceof QuantArg) {
            QuantArg quantArg = (QuantArg) arg;
            return doSwitch(quantArg.getExpr()).getSet();
        } else {
            SortedSet<ResoluteValue> values = new TreeSet<ResoluteValue>();
            BaseType type = (BaseType) arg.getType();
            for (NamedElement ne : context.getSet(type.getType())) {
                values.add(new NamedElementValue(ne));
            }
            return values;
        }
    }

    @Override
    public ResoluteValue caseFnCallExpr(FnCallExpr object) {
        if (object.getFn().getBody() instanceof FunctionBody) {
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

    private SortedSet<ResoluteValue> filterMap(List<Arg> args, Expr map, Expr filter) {
        if (args.isEmpty()) {
            return filter(map, filter);
        } else {
            Arg arg = args.get(0);
            List<Arg> rest = args.subList(1, args.size());
            SortedSet<ResoluteValue> result = new TreeSet<ResoluteValue>();
            for (ResoluteValue value : getArgSet(arg)) {
                varStack.peek().put(arg, value);
                result.addAll(filterMap(rest, map, filter));
            }
            return result;
        }
    }

    private SortedSet<ResoluteValue> filter(Expr map, Expr filter) {
        TreeSet<ResoluteValue> result = new TreeSet<ResoluteValue>();
        if (filter == null || doSwitch(filter).getBool()) {
            result.add(doSwitch(map));
        }
        return result;
    }

    @Override
    public ResoluteValue caseSetExpr(SetExpr object) {
        return new SetValue(doSwitchList(object.getExprs()));
    }

    @Override
    public ResoluteValue caseInstanceOfExpr(InstanceOfExpr object) {
        ResoluteType valueType = doSwitch(object.getExpr()).getType();
        ResoluteType checkType = new com.rockwellcollins.atc.resolute.validation.BaseType(object
                .getType().getType());
        return bool(valueType.subtypeOf(checkType));
    }

    @Override
    public ResoluteValue caseCastExpr(CastExpr object) {
        return doSwitch(object.getExpr());
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
    public ResoluteValue caseBuiltInFnCallExpr(BuiltInFnCallExpr object) {
        List<ResoluteValue> args = doSwitchList(object.getArgs());
        return new ResoluteBuiltInFnCallEvaluator(context).evaluate(object, args);
    }

    private static ResoluteValue bool(boolean bool) {
        return bool ? TRUE : FALSE;
    }

    public List<ResoluteValue> doSwitchList(List<? extends EObject> list) {
        List<ResoluteValue> values = new ArrayList<>();
        for (EObject e : list) {
            values.add(doSwitch(e));
        }
        return values;
    }
}
