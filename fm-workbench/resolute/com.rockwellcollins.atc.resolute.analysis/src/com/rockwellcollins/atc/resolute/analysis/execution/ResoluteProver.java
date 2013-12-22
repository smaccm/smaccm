package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.SystemOperationMode;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.FailResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.analysis.values.NamedElementValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.ClaimString;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.util.ResoluteSwitch;

public class ResoluteProver extends ResoluteSwitch<ResoluteResult> {
    // Stack for function, claim, and quantifier arguments, shared with ResoluteEvaluator
    private Deque<Map<Arg, ResoluteValue>> argMapStack;

    // Claims have been called with what arguments, used to detect cycles
    private Set<ClaimCallContext> claimCallContexts;

    private final ResoluteEvaluator evaluator;

    public ResoluteProver(ComponentInstance thisInst, List<SystemOperationMode> sysModes) {
        this.argMapStack = new LinkedList<>();
        this.evaluator = new ResoluteEvaluator(thisInst, this.argMapStack, sysModes);
        this.claimCallContexts = new HashSet<>();
    }

    /**
     * The ResoluteProver will only be called on formulas. Everything else is handled by the
     * ResoluteEvaluator.
     */
    @Override
    public ResoluteResult defaultCase(EObject object) {
        return new ResoluteResult(evaluator.doSwitch(object).getBool());
    }

    @Override
    public ResoluteResult caseBinaryExpr(BinaryExpr object) {
        String op = object.getOp();

        if (op.equals("and") || op.equals("or") || op.equals("=>")) {
            ResoluteResult leftResult = doSwitch(object.getLeft());
            switch (op) {
            case "and":
                if (leftResult.isValid()) {
                    return new ResoluteResult(leftResult, doSwitch(object.getRight()));
                } else {
                    return leftResult;
                }

            case "or":
                if (leftResult.isValid()) {
                    return leftResult;
                } else {
                    return doSwitch(object.getRight());
                }

            case "=>":
                if (!leftResult.isValid()) {
                    return new ResoluteResult(true);
                } else {
                    return doSwitch(object.getRight());
                }

            default:
                throw new IllegalArgumentException();
            }
        } else {
            return new ResoluteResult(evaluator.doSwitch(object).getBool());
        }
    }

    @Override
    public ResoluteResult caseQuantifiedExpr(QuantifiedExpr object) {
        switch (object.getQuant()) {
        case "exists":
            return exists(object.getArgs(), object.getExpr());

        case "forall":
            return forall(object.getArgs(), object.getExpr());

        default:
            throw new IllegalArgumentException("Unknown quantifier: " + object.getQuant());
        }
    }

    private ResoluteResult exists(List<Arg> args, Expr body) {
        if (args.isEmpty()) {
            return doSwitch(body);
        } else {
            Arg arg = args.get(0);
            List<Arg> rest = args.subList(1, args.size());
            for (ResoluteValue value : evaluator.getArgSet(arg)) {
                argMapStack.getFirst().put(arg, value);
                ResoluteResult subResult = exists(rest, body);
                if (subResult.isValid()) {
                    return subResult;
                }
            }
            return new ResoluteResult(false);
        }
    }

    private ResoluteResult forall(List<Arg> args, Expr body) {
        if (args.isEmpty()) {
            return doSwitch(body);
        } else {
            Arg arg = args.get(0);
            List<Arg> rest = args.subList(1, args.size());
            List<ResoluteResult> children = new ArrayList<>();
            for (ResoluteValue value : evaluator.getArgSet(arg)) {
                argMapStack.getFirst().put(arg, value);
                ResoluteResult subResult = forall(rest, body);
                children.add(subResult);
                if (!subResult.isValid()) {
                    break;
                }
            }
            return new ResoluteResult(children);
        }
    }

    @Override
    public ResoluteResult caseFnCallExpr(FnCallExpr object) {
        if (object.getFn().getBody() instanceof ClaimBody) {
            return claimCall(object);
        } else {
            // Regular function calls are deferred to ResoluteEvaluator
            return null;
        }
    }

    private ResoluteResult claimCall(FnCallExpr object) {
        FunctionDefinition funcDef = object.getFn();
        ClaimBody body = (ClaimBody) funcDef.getBody();
        List<ResoluteValue> argVals = evaluator.doSwitchList(object.getArgs());

        ClaimCallContext context = new ClaimCallContext(funcDef, argVals);
        if (claimCallContexts.contains(context)) {
            return new FailResult("Recursive call to " + funcDef.getName() + " with arguments " + argVals, object);
        }

        claimCallContexts.add(context);
        argMapStack.push(ResoluteEvaluator.pairArguments(funcDef.getArgs(), argVals));

        String text = createClaimText(body);
        Map<String, EObject> references = createClaimReferences(body);
        ResoluteResult subResult;
        try {
            subResult = doSwitch(body.getExpr());
        } catch (ResoluteFailException e) {
            subResult = new FailResult(e.getMessage(), e.getLocation());
        }

        argMapStack.pop();
        claimCallContexts.remove(context);

        return new ClaimResult(text, subResult, references, funcDef);
    }

    private String createClaimText(ClaimBody claimBody) {
        StringBuilder text = new StringBuilder();

        for (Element claim : claimBody.getClaim()) {
            if (claim instanceof ClaimArg) {
                Arg claimArg = ((ClaimArg) claim).getArg();
                text.append("'");
                text.append(evaluator.doSwitch(claimArg));
                text.append("'");
            } else if (claim instanceof ClaimString) {
                text.append(((ClaimString) claim).getStr());
            } else {
                throw new IllegalArgumentException("Unknown claim type: " + claim.getClass());
            }
        }

        return text.toString();
    }

    private Map<String, EObject> createClaimReferences(ClaimBody body) {
        Map<String, EObject> result = new HashMap<>();
        for (ClaimString claim : body.getClaim()) {
            if (claim instanceof ClaimArg) {
                Arg claimArg = ((ClaimArg) claim).getArg();
                ResoluteValue argVal = evaluator.doSwitch(claimArg);
                if (argVal.isNamedElement()) {
                    EObject modelElement = getModelElement(argVal.getNamedElement());
                    if (modelElement != null) {
                        result.put(argVal.toString(), modelElement);
                    }
                }
            }
        }

        return result;
    }

    private EObject getModelElement(NamedElement e) {
        if (e instanceof ComponentInstance) {
            ComponentInstance ci = (ComponentInstance) e;
            return ci.getSubcomponent();
        } else if (e instanceof ConnectionInstance) {
            ConnectionInstance ci = (ConnectionInstance) e;
            return ci.getConnectionReferences().get(0).getConnection();
        } else {
            return null;
        }
    }

    public static String proveStatementToString(ProveStatement ps, ComponentInstance thisInst) {
        FnCallExpr fnCall = (FnCallExpr) ps.getExpr();

        StringBuilder text = new StringBuilder();
        text.append(fnCall.getFn().getName());
        text.append("(");
        Iterator<Expr> iterator = fnCall.getArgs().iterator();
        while (iterator.hasNext()) {
            Expr arg = iterator.next();
            if (arg instanceof ThisExpr) {
                text.append(new NamedElementValue(thisInst));
            } else {
                text.append(exprToString(arg));
            }
            if (iterator.hasNext()) {
                text.append(", ");
            }
        }
        text.append(")");
        return text.toString();
    }

    private static String exprToString(Expr expr) {
        ICompositeNode compNode = NodeModelUtils.getNode(expr);
        if (compNode != null) {
            return NodeModelUtils.getTokenText(compNode);
        }
        return null;
    }
}
