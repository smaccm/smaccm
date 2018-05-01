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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.FailResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.analysis.values.IntValue;
import com.rockwellcollins.atc.resolute.analysis.values.NamedElementValue;
import com.rockwellcollins.atc.resolute.analysis.values.RealValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.analysis.values.StringValue;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.ClaimString;
import com.rockwellcollins.atc.resolute.resolute.ClaimText;
import com.rockwellcollins.atc.resolute.resolute.ClaimTextVar;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.DefinitionBody;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.LetBinding;
import com.rockwellcollins.atc.resolute.resolute.LetExpr;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.util.ResoluteSwitch;

public class ResoluteProver extends ResoluteSwitch<ResoluteResult> {
	// Stack for function, claim, and quantifier arguments
	protected final Deque<Map<NamedElement, ResoluteValue>> varStack = new LinkedList<>();

	// Claims have been called with what arguments, used to detect cycles
	protected final Set<ClaimCallContext> claimCallContexts = new HashSet<>();

	protected final EvaluationContext context;

	public ResoluteProver(EvaluationContext context) {
		this.context = context;
	}

	/* This will be overridden by children to instantiate modified evaluators */
	protected ResoluteEvaluator createResoluteEvaluator() {
		return new ResoluteEvaluator(context, varStack.peek());
	}

	protected ResoluteValue eval(EObject e) {
		return createResoluteEvaluator().doSwitch(e);
	}

	protected List<ResoluteValue> getArgSet(Arg arg) {
		return createResoluteEvaluator().getArgSet(arg);
	}

	private List<ResoluteValue> evalList(List<? extends EObject> list) {
		return createResoluteEvaluator().doSwitchList(list);
	}

	/**
	 * The ResoluteProver will only be called on formulas. Everything else is
	 * handled by the ResoluteEvaluator.
	 */
	@Override
	public ResoluteResult defaultCase(EObject object) {
		return new ResoluteResult(eval(object).getBool());
	}

	@Override
	public ResoluteResult caseBinaryExpr(BinaryExpr object) {
		String op = object.getOp();

		if (op.equals("and") || op.equals("or") || op.equals("andthen") || op.equals("orelse") || op.equals("=>")) {
			ResoluteResult leftResult = doSwitch(object.getLeft());
			switch (op) {
			case "and": {
				ResoluteResult rightResult = doSwitch(object.getRight());
				ResoluteResult result = new ResoluteResult(leftResult, rightResult);
				result.setValid(leftResult.isValid() && rightResult.isValid());
				return result;
			}

			case "or": {
				ResoluteResult rightResult = doSwitch(object.getRight());
				ResoluteResult result = new ResoluteResult(leftResult, rightResult);
				result.setValid(leftResult.isValid() || rightResult.isValid());
				return result;
			}

			case "andthen":
				if (!leftResult.isValid()) {
					return new ResoluteResult(leftResult);
				} else {
					ResoluteResult rightResult = doSwitch(object.getRight());
					ResoluteResult result = new ResoluteResult(leftResult, rightResult);
					result.setValid(rightResult.isValid());
					return result;
				}

			case "orelse":
				if (leftResult.isValid()) {
					return new ResoluteResult(leftResult);
				} else {
					ResoluteResult rightResult = doSwitch(object.getRight());
					ResoluteResult result = new ResoluteResult(leftResult, rightResult);
					result.setValid(rightResult.isValid());
					return result;
				}

			case "=>":
				if (!leftResult.isValid()) {
					return new ResoluteResult(true, leftResult);
				} else {
					ResoluteResult rightResult = doSwitch(object.getRight());
					ResoluteResult result = new ResoluteResult(leftResult, rightResult);
					result.setValid(rightResult.isValid());
					return result;
				}

			default:
				throw new IllegalArgumentException();
			}
		} else {
			return new ResoluteResult(eval(object).getBool());
		}
	}

	@Override
	public ResoluteResult caseLetExpr(LetExpr object) {
		LetBinding binding = object.getBinding();
		ResoluteValue boundValue = eval(binding.getExpr());
		varStack.peek().put(binding, boundValue);
		return doSwitch(object.getExpr());
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
			List<ResoluteResult> children = new ArrayList<>();
			boolean claimCall = false;
			if (body instanceof FnCallExpr) {
				FunctionDefinition fn = ((FnCallExpr) body).getFn();
				DefinitionBody bod = fn.getBody();
				if (bod instanceof ClaimBody) {
					claimCall = true;
				}
			}
			for (ResoluteValue value : getArgSet(arg)) {
				varStack.peek().put(arg, value);
				ResoluteResult subResult = exists(rest, body);
				children.add(subResult);
				if (subResult.isValid() && !claimCall && !containsClaim(subResult)) {
					return subResult;
				}
			}
			return new ResoluteResult(!children.stream().allMatch(child -> !child.isValid()), children);
		}
	}

	private ResoluteResult forall(List<Arg> args, Expr body) {
		if (args.isEmpty()) {
			return doSwitch(body);
		} else {
			Arg arg = args.get(0);
			List<Arg> rest = args.subList(1, args.size());
			List<ResoluteResult> children = new ArrayList<>();
			boolean claimCall = false;
			if (body instanceof FnCallExpr) {
				FunctionDefinition fn = ((FnCallExpr) body).getFn();
				DefinitionBody bod = fn.getBody();
				if (bod instanceof ClaimBody) {
					claimCall = true;
				}
			}
			for (ResoluteValue value : getArgSet(arg)) {
				varStack.peek().put(arg, value);
				ResoluteResult subResult = forall(rest, body);
				children.add(subResult);
				if (!subResult.isValid() && !claimCall && !containsClaim(subResult)) {
					return subResult;
				}
			}
			return new ResoluteResult(children);
		}
	}

	private boolean containsClaim(ResoluteResult result) {
		if (result instanceof ClaimResult) {
			return true;
		}
		for (ResoluteResult cres : result.getChildren()) {
			if (containsClaim(cres)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ResoluteResult caseFailExpr(FailExpr object) {
		return createFailResult("Failure: ", object);
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
		List<ResoluteValue> argVals = evalList(object.getArgs());

		ClaimCallContext context = new ClaimCallContext(funcDef, argVals);
		if (claimCallContexts.contains(context)) {
			return new FailResult("Recursive call to " + funcDef.getName() + " with arguments " + argVals, object);
		}

		claimCallContexts.add(context);
		varStack.push(ResoluteEvaluator.pairArguments(funcDef.getArgs(), argVals));

		String text = createClaimText(body.getClaim());
		Map<String, EObject> references = createClaimReferences(body);
		ResoluteResult subResult;
		try {
			subResult = doSwitch(body.getExpr());
		} catch (ResoluteFailException e) {
			if (e.getLocation() instanceof FailExpr) {
				subResult = createFailResult(e.getMessage(), (FailExpr) e.getLocation());
			} else {
				subResult = new FailResult(e.getMessage(), e.getLocation());
			}
		}

		varStack.pop();
		claimCallContexts.remove(context);

		return new ClaimResult(text, subResult, references, funcDef);
	}

	private ResoluteResult createFailResult(String message, FailExpr object) {
		String str = "unknown failure";

		if (object.getVal() instanceof BinaryExpr) {
			BinaryExpr binExpr = (BinaryExpr) object.getVal();
			Object val = doSwitch(binExpr);
			StringValue strVal = (StringValue) val;
			str = strVal.getString();
		}

		if (object.getVal() instanceof StringExpr) {
			StringExpr stringExpr = (StringExpr) object.getVal();
			str = stringExpr.getVal().getValue();
		}

		if (!object.getFailmsg().isEmpty()) {
			str = createClaimText(object.getFailmsg());
		}

		return new FailResult("Failure: " + str.replaceAll("\"", ""), object);
	}

	private String createClaimText(EList<ClaimText> claimBody) {
		StringBuilder text = new StringBuilder();

		for (Element claim : claimBody) {
			if (claim instanceof ClaimArg) {
				ClaimTextVar claimArg = ((ClaimArg) claim).getArg();
				UnitLiteral claimArgUnit = ((ClaimArg) claim).getUnit();
				// text.append("'");
				@SuppressWarnings("unlikely-arg-type")
				ResoluteValue val = varStack.peek().get(claimArg);
				if (val == null) {
					if (claimArg instanceof ConstantDefinition) {
						val = eval(((ConstantDefinition) claimArg).getExpr());
					} else if (claimArg instanceof LetBinding) {
						val = eval(((LetBinding) claimArg).getExpr());
					}
				}
				if (claimArgUnit != null) {
					if (val instanceof IntValue) {
						IntValue ival = (IntValue) val;
						long sval = ival.getScaledInt(claimArgUnit);
						if (sval != 0 && ival.getInt() != 0) {
							val = new IntValue(sval);
						} else {
							val = new RealValue(ival.getScaledIntAsDouble(claimArgUnit));
						}
					} else if (val instanceof RealValue) {
						val = new RealValue(((RealValue) val).getScaledReal(claimArgUnit));
					}
				}
				text.append(val);
				if (claimArgUnit != null) {
					text.append(" " + claimArgUnit.getName());
				}
				// text.append("'");
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
		for (ClaimText claim : body.getClaim()) {
			if (claim instanceof ClaimArg) {
				ClaimTextVar claimArg = ((ClaimArg) claim).getArg();
				@SuppressWarnings("unlikely-arg-type")
				ResoluteValue argVal = varStack.peek().get(claimArg);
				if (argVal != null && argVal.isNamedElement()) {
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
		} else if (e instanceof FeatureInstance) {
			FeatureInstance feat = (FeatureInstance) e;
			return feat.getFeature();
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
