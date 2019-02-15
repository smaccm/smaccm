package com.rockwellcollins.atc.agree.validation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.rockwellcollins.atc.agree.agree.Abstraction;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FnDef;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;


public class CallRecursionHelper extends AgreeSwitch<Expr> {

	public LinkedList<Abstraction> visited;
	public List<LinkedList<Abstraction>> loops;

	public void doSwitchPreserveVisited(Expr expr) {
		LinkedList<Abstraction> copyOfVisited = new LinkedList<>(visited);
		doSwitch(expr);
		visited = copyOfVisited;
	}

	public CallRecursionHelper() {
		visited = new LinkedList<>();
		loops = new ArrayList<>();
	}

	@Override
	public Expr caseBinaryExpr(BinaryExpr object) {
		doSwitchPreserveVisited(object.getLeft());
		doSwitchPreserveVisited(object.getRight());
		return null;
	}

	@Override
	public Expr caseUnaryExpr(UnaryExpr object) {
		doSwitchPreserveVisited(object.getExpr());
		return null;
	}

	@Override
	public Expr caseIfThenElseExpr(IfThenElseExpr object) {
		doSwitchPreserveVisited(object.getC());
		doSwitchPreserveVisited(object.getB());
		doSwitchPreserveVisited(object.getA());
		return null;
	}

	@Override
	public Expr casePrevExpr(PrevExpr object) {
		doSwitchPreserveVisited(object.getDelay());
		doSwitchPreserveVisited(object.getInit());
		return null;
	}

	@Override
	public Expr casePreExpr(PreExpr object) {
		doSwitchPreserveVisited(object.getExpr());
		return null;
	}

	@Override
	public Expr caseNodeBodyExpr(NodeBodyExpr object) {

		for (NodeStmt stmt : object.getStmts()) {
			doSwitch(stmt);
		}
		return null;
	}

	@Override
	public Expr caseNodeEq(NodeEq object) {
		doSwitchPreserveVisited(object.getExpr());
		return null;
	}

	@Override
	public Expr caseNodeLemma(NodeLemma object) {
		doSwitchPreserveVisited(object.getExpr());
		return null;
	}

	@Override
	public Expr caseNodeDef(NodeDef object) {

		if (visited.contains(object)) {
			LinkedList<Abstraction> loop = new LinkedList<>(visited);
			loop.push(object);
			loops.add(loop);
		} else {
			visited.push(object);
			doSwitch(object.getNodeBody());
		}

		return null;
	}

	@Override
	public Expr caseFnDef(FnDef object) {

		if (visited.contains(object)) {
			LinkedList<Abstraction> loop = new LinkedList<>(visited);
			loop.push(object);
			loops.add(loop);
		} else {
			visited.push(object);
			doSwitchPreserveVisited(object.getExpr());
		}
		return null;
	}

	@Override
	public Expr caseCallExpr(CallExpr object) {
		DoubleDotRef id = object.getRef();

		Abstraction callDef = null;

		try {
			callDef = (Abstraction) id.getElm();
//=======
//	public Expr caseFnCallExpr(FnCallExpr object) {
//
//		CallDef callDef = null;
//
//		try {
//			callDef = (CallDef) object.getFn().getBase();
//>>>>>>> origin/develop:fm-workbench/agree/com.rockwellcollins.atc.agree/src/com/rockwellcollins/atc/agree/validation/FnCallRecursionHelper.java
//
		} catch (ClassCastException e) {
			return null;
		}

		doSwitch(callDef);
		return null;
	}

}
