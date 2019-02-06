package com.rockwellcollins.atc.agree.scoping;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.osate.aadl2.DataImplementation;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.NamedElement;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.RecordDef;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.Type;

public class RecordExprScoper {

	public static IScope getScope(Expr expr, IScope outerScope) {

		if (expr instanceof BinaryExpr) {
			return getScope((BinaryExpr) expr, outerScope);
		} else if (expr instanceof PreExpr) {
			return getScope((PreExpr) expr, outerScope);
		} else if (expr instanceof IfThenElseExpr) {
			return getScope((IfThenElseExpr) expr, outerScope);
		} else if (expr instanceof RecordUpdateExpr) {
			return getScope((RecordUpdateExpr) expr, outerScope);
		} else if (expr instanceof RecordLitExpr) {
			return getScope((RecordLitExpr) expr, outerScope);
		}

		return IScope.NULLSCOPE;
	}

	public static IScope getScope(RecordLitExpr expr, IScope outerScope) {
		return getScope(expr.getRecordType().getElm(), outerScope);
	}

	public static IScope getScope(BinaryExpr binExpr, IScope outerScope) {
		// just assume its an arrow expr, the validiator
		// will throw an error otherwise. Just assume
		// that the lhs and rhs times match as well
		return getScope(binExpr.getLeft(), outerScope);
	}

	public static IScope getScope(RecordUpdateExpr upExpr, IScope outerScope) {
		return getScope(upExpr.getRecord(), outerScope);
	}

	public static IScope getScope(PreExpr preExpr, IScope outerScope) {
		return getScope(preExpr.getExpr(), outerScope);
	}

	public static IScope getScope(IfThenElseExpr iteExpr, IScope outerScope) {
		// just assume the then and else types match
		// the validator will check this
		return getScope(iteExpr.getB(), outerScope);
	}

	public static IScope getScope(NamedElement recStatement, IScope outerScope) {
		if (recStatement instanceof Arg) {
			Type type = ((Arg) recStatement).getType();

			if (type instanceof DoubleDotRef) {
				return getRecordComponents(((DoubleDotRef) type).getElm(), outerScope);
//				=======
//						if (type instanceof RecordType) {
//							DoubleDotRef nestExpr = ((RecordType) type).getRecord();
//							return getRecordComponents(nestExpr.getElm(), outerScope);
//							>>>>>>> origin/develop
			}
		} else if (recStatement instanceof DataPort) {
			DataSubcomponentType dataClass = ((DataPort) recStatement).getDataFeatureClassifier();
			if (dataClass instanceof DataImplementation) {
				return getRecordComponents(dataClass, outerScope);
			}
		} else if (recStatement instanceof EventDataPort) {
			DataSubcomponentType dataClass = ((EventDataPort) recStatement).getDataFeatureClassifier();
			if (dataClass instanceof DataImplementation) {
				return getRecordComponents(dataClass, outerScope);
			}
		} else if (recStatement instanceof RecordDef) {
			return getRecordComponents(recStatement, outerScope);
		}
		return IScope.NULLSCOPE;
	}

	public static IScope getRecordComponents(NamedElement recDef, IScope outerScope) {
		Set<Element> components = new HashSet<>();
		if (recDef instanceof DataImplementation) {
			components.addAll(((DataImplementation) recDef).getAllSubcomponents());
			return Scopes.scopeFor(components, outerScope);
		} else if (recDef instanceof RecordDef) {
			components.addAll(((RecordDef) recDef).getArgs());
			return Scopes.scopeFor(components, outerScope);
		}
		return IScope.NULLSCOPE;
	}

}
