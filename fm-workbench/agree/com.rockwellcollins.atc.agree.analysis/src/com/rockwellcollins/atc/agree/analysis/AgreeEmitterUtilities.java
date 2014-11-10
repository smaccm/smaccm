package com.rockwellcollins.atc.agree.analysis;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ContainedNamedElement;
import org.osate.aadl2.ContainmentPathElement;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataType;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;

public class AgreeEmitterUtilities {

	static public PropertyExpression getPropExpression(NamedElement comp, Property prop) {

		PropertyExpression expr;
		try {
			comp.getPropertyValue(prop); // this just checks to see if the
			// property is associated
			expr = PropertyUtils.getSimplePropertyValue(comp, prop);
			return expr;
		} catch (PropertyDoesNotApplyToHolderException propException) {
			return null;
		} catch (PropertyNotPresentException propNotPresentException) {
			return null;
		}
	}

	// TODO: i'm not sure that this function will work in more complicated cases of property inheritance
	public static PropertyExpression getSimplePropertyValue(final Subcomponent context, final NamedElement target,
			final Property pd) {
		if (context == null)
			return target.getNonModalPropertyValue(pd);
		EList<PropertyAssociation> props = context.getOwnedPropertyAssociations();
		for (PropertyAssociation propertyAssociation : props) {
			if (propertyAssociation.getProperty().equals(pd)) {
				// we found a property with the correct type
				// now we need to check whether the applies to points to the holder
				EList<ContainedNamedElement> appliestos = propertyAssociation.getAppliesTos();
				for (ContainedNamedElement containedNamedElement : appliestos) {
					EList<ContainmentPathElement> cpes = containedNamedElement.getContainmentPathElements();
					NamedElement pathcxt = cpes.get(cpes.size() - 1).getNamedElement();
					if (target.equals(pathcxt)) {
						EList<ModalPropertyValue> vallist = propertyAssociation.getOwnedValues();
						if (!vallist.isEmpty()) {
							ModalPropertyValue elem = vallist.get(0);
							PropertyExpression res = elem.getOwnedValue();
							if (res instanceof NamedValue) {
								AbstractNamedValue nv = ((NamedValue) res).getNamedValue();
								if (nv instanceof Property) {
									res = target.getNonModalPropertyValue((Property) nv);
								} else if (nv instanceof PropertyConstant) {
									res = ((PropertyConstant) nv).getConstantValue();
								}
							}

							return res;
						}

					}
				}
			}
		}
		return null;
	}

	static public AgreeVarDecl dataTypeToVarType(DataSubcomponent sub) {

		DataType type = (DataType) sub.getAllClassifier();
		String varName = sub.getName();

		do {
			String name = type.getQualifiedName();
			switch (name) {
			case "Base_Types::Boolean":
				return new AgreeVarDecl(varName, new NamedType("bool"));
			case "Base_Types::Integer":
			case "Base_Types::Unsigned":
			case "Base_Types::Unsigned_32":
			case "Base_Types::Unsigned_16":
			case "Base_Types::Unsigned_8":
			case "Base_Types::Integer_32":
			case "Base_Types::Integer_16":
			case "Base_Types::Integer_8":
				return new AgreeVarDecl(varName, new NamedType("int"));
			case "Base_Types::Float":
				return new AgreeVarDecl(varName, new NamedType("real"));
			}
			type = (DataType) type.getExtended();

		} while (type != null);

		return null;

	}

	static public String dataTypeToVarType(DataType sub) {
		String name = sub.getQualifiedName();

		switch (name) {
		case "Base_Types::Boolean":
			return "bool";
		case "Base_Types::Integer":
			return "int";
		case "Base_Types::Float":
			return "real";
		}

		return null;
	}

	public String getFnCallExprName(FnCallExpr expr) {
		NestedDotID dotId = expr.getFn();
		NamedElement namedEl = getFinalNestId(dotId);
		return namedEl.getName();
	}

	public static NamedElement getFinalNestId(NestedDotID dotId) {
		while (dotId.getSub() != null) {
			dotId = dotId.getSub();
		}
		return dotId.getBase();
	}

	public static ComponentImplementation getInstanceImplementation(ComponentInstance compInst) {
		if (compInst instanceof SystemInstance) {
			ComponentImplementation ci;
			ci = ((SystemInstance) compInst).getComponentImplementation();
			return ci;
		}
		try {
			return (ComponentImplementation) compInst.getComponentClassifier();
		} catch (ClassCastException e) {
			return null;
		}
	}

	public static ComponentType getInstanceType(ComponentInstance compInst) {
		if (compInst instanceof SystemInstance) {

			ComponentType ct;
			ct = ((SystemInstance) compInst).getComponentImplementation().getType();
			return ct;

		}
		try {
			return ((ComponentImplementation) compInst.getComponentClassifier()).getType();
		} catch (ClassCastException e) {
			return (ComponentType) compInst.getComponentClassifier();
		}
	}

	// warns the user if there is a cycle
	static public void logCycleWarning(List<Equation> eqs, AgreeRenaming agreeRename, boolean throwException) {
		Map<String, Set<String>> idGraph = new HashMap<>();
		List<String> ids = new ArrayList<>();
		AgreeCycleVisitor visitor = new AgreeCycleVisitor();

		for (Equation eq : eqs) {
			for (IdExpr id : eq.lhs) {
				ids.add(id.id);
				idGraph.put(id.id, eq.expr.accept(visitor));
			}
		}

		Set<String> discovered = new HashSet<>();

		StringBuilder exceptionStr = new StringBuilder();
		for (String id : ids) {
			if (discovered.contains(id)) {
				continue;
			}
			List<String> cycle = cycleWarning_Helper(id, new HashSet<String>(), idGraph);

			if (cycle != null) {
				discovered.addAll(cycle);
				String aadlString = agreeRename.rename(id);
				StringBuilder cycleStr = new StringBuilder("Possible cycle: " + aadlString);

				String sep = " -> ";
				for (String cycleId : cycle) {
					cycleStr.append(sep);
					aadlString = agreeRename.rename(cycleId);
					cycleStr.append(aadlString);
				}

				AgreeLogger.logWarning(cycleStr.toString());
				exceptionStr.append(cycleStr);
			}
		}
		if (throwException && !discovered.isEmpty()) {
			throw new AgreeCombinationalCycleException(exceptionStr.toString());
		}
	}

	static public LinkedList<String> cycleWarning_Helper(String visit, Set<String> visited,
			Map<String, Set<String>> graph) {

		if (visited.contains(visit)) {
			return null;
		}

		visited.add(visit);

		Set<String> toVisit = graph.get(visit);

		if (toVisit != null) {

			LinkedList<String> intersection = new LinkedList<>(toVisit);
			intersection.retainAll(visited);
			if (intersection.size() != 0) {
				String firstLink = intersection.getFirst();
				return new LinkedList<>(Collections.singletonList(firstLink));
			}

			for (String nextVisit : toVisit) {
				LinkedList<String> cycle = cycleWarning_Helper(nextVisit, visited, graph);
				if (cycle != null) {
					cycle.push(nextVisit);
					return cycle;
				}
			}
		}
		visited.remove(visit);

		return null;
	}

	static public AgreeEmitterState getSubcomponentEmitter(Subcomponent sub, List<AgreeEmitterState> subStates) {
		for (AgreeEmitterState subState : subStates) {
			if (subState.curComp == sub) {
				return subState;
			}
		}
		return null;
	}

	static public Expr conjoin(List<Expr> exprs) {
		if (exprs.isEmpty()) {
			return new BoolExpr(true);
		}

		Iterator<Expr> iterator = exprs.iterator();
		Expr result = iterator.next();
		while (iterator.hasNext()) {
			result = new BinaryExpr(result, BinaryOp.AND, iterator.next());
		}
		return result;
	}

	static public Expr conjoinEqs(List<Equation> eqs) {
		if (eqs.isEmpty()) {
			return new BoolExpr(true);
		}

		Iterator<Equation> iterator = eqs.iterator();
		Expr result = iterator.next().expr;
		while (iterator.hasNext()) {
			result = new BinaryExpr(result, BinaryOp.AND, iterator.next().expr);
		}
		return result;
	}

	static public Expr conjoin(Expr... exprs) {
		return conjoin(Arrays.asList(exprs));
	}

	static public Expr getLustreAssumptions(AgreeNode agreeNode) {
		Expr assumps = conjoin(agreeNode.assumptions);
		IdExpr clockExpr = new IdExpr(agreeNode.clockVar.id);
		return new BinaryExpr(clockExpr, BinaryOp.IMPLIES, assumps);
	}

	static public Expr getLustreAssumptionsAndAssertions(AgreeNode agreeNode) {
		Expr assumAssert = conjoin(conjoin(agreeNode.assertions), conjoin(agreeNode.assumptions));
		IdExpr clockExpr = new IdExpr(agreeNode.clockVar.id);
		return new BinaryExpr(clockExpr, BinaryOp.IMPLIES, assumAssert);

	}

	static public Expr getLustreContract(AgreeNode agreeNode) {
		Expr contract = conjoin(conjoin(agreeNode.assertions), conjoin(agreeNode.assumptions),
				conjoin(agreeNode.guarantees));
		IdExpr clockExpr = new IdExpr(agreeNode.clockVar.id);
		return new BinaryExpr(clockExpr, BinaryOp.IMPLIES, contract);

	}

	static public Expr getLustreGuarantee(AgreeNode agreeNode) {
		return conjoin(agreeNode.guarantees);
	}

	static public Equation getLustreHistory(Expr expr, IdExpr histId) {

		Expr preHist = new UnaryExpr(UnaryOp.PRE, histId);
		Expr histExpr = new BinaryExpr(expr, BinaryOp.AND, preHist);
		histExpr = new BinaryExpr(expr, BinaryOp.ARROW, histExpr);

		Equation histEq = new Equation(histId, histExpr);

		return histEq;

	}

	// returns an expression for bounded history
	static public Expr getFinteConsistancy(Expr histExpr, IdExpr countId, int n) {
		Expr countExpr = new BinaryExpr(countId, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf((long) n)));

		Expr consistExpr = new BinaryExpr(histExpr, BinaryOp.AND, countExpr);
		consistExpr = new UnaryExpr(UnaryOp.NOT, consistExpr);

		return consistExpr;
	}

	static public void getOutputClosure(List<Connection> conns, Set<Subcomponent> subs) {

		assert (subs.size() == 1);
		Subcomponent orig = (Subcomponent) (subs.toArray()[0]);
		int prevSize = subs.size();
		do {
			prevSize = subs.size();
			for (Connection conn : conns) {
				ConnectedElement absConnDest = conn.getDestination();
				ConnectedElement absConnSour = conn.getSource();

				Context destContext = absConnDest.getContext();
				Context sourContext = absConnSour.getContext();
				if (sourContext != null && subs.contains(sourContext)) {
					if (destContext != null && destContext instanceof Subcomponent) {
						subs.add((Subcomponent) destContext);
					}
				}
			}
		} while (subs.size() != prevSize);

	}

	public static boolean containsAgreeAnnex(Subcomponent subComp) {

		ComponentImplementation compImpl = subComp.getComponentImplementation();
		if (compImpl != null) {
			if (AnnexUtil.getAllAnnexSubclauses(compImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause()).size() > 0) {
				return true;
			}
		}

		ComponentType compType = subComp.getComponentType();
		if (compType != null) {
			if (AnnexUtil.getAllAnnexSubclauses(compType, AgreePackage.eINSTANCE.getAgreeContractSubclause()).size() > 0) {
				return true;
			}
		}
		return false;
	}

}
