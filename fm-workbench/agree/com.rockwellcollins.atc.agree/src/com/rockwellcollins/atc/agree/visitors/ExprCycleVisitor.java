package com.rockwellcollins.atc.agree.visitors;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FloorCast;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.RealCast;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;

public class ExprCycleVisitor extends AgreeSwitch<Set<EObject>> {

	private String id;

	public ExprCycleVisitor(String id) {
		this.id = id;
	}

	@Override
	public Set<EObject> caseExpr(Expr expr) {
		return new HashSet<>();
	}

	@Override
	public Set<EObject> caseBinaryExpr(BinaryExpr e) {
		Set<EObject> result = new HashSet<>();
		result.addAll(doSwitch(e.getLeft()));
		result.addAll(doSwitch(e.getRight()));
		return result;
	}

	@Override
	public Set<EObject> caseBoolLitExpr(BoolLitExpr e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> caseCallExpr(CallExpr e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> caseRecordLitExpr(RecordLitExpr e) {
		Set<EObject> result = new HashSet<>();
		for (Expr expr : e.getArgExpr()) {
			result.addAll(doSwitch(expr));
		}
		return result;
	}

	@Override
	public Set<EObject> caseEventExpr(EventExpr e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> caseFloorCast(FloorCast e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> caseGetPropertyExpr(GetPropertyExpr e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> caseIfThenElseExpr(IfThenElseExpr e) {
		Set<EObject> result = new HashSet<>();
		result.addAll(doSwitch(e.getA()));
		result.addAll(doSwitch(e.getB()));
		result.addAll(doSwitch(e.getC()));

		return result;
	}

	@Override
	public Set<EObject> caseIntLitExpr(IntLitExpr e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> casePreExpr(PreExpr e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> casePrevExpr(PrevExpr e) {
		Set<EObject> result = new HashSet<>();
		result.addAll(doSwitch(e.getInit()));
		return result;
	}

	@Override
	public Set<EObject> caseRealCast(RealCast e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> caseRealLitExpr(RealLitExpr e) {
		return Collections.emptySet();
	}

	@Override
	public Set<EObject> caseRecordUpdateExpr(RecordUpdateExpr e) {
		Set<EObject> result = new HashSet<>();
		result.addAll(doSwitch(e.getExpr()));
		return result;
	}


	@Override
	public Set<EObject> caseUnaryExpr(UnaryExpr e) {
		Set<EObject> result = new HashSet<>();
		result.addAll(doSwitch(e.getExpr()));
		return result;
	}
}
