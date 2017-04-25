/*
Copyright (c) 2016, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/

package com.rockwellcollins.atc.tcg.obligations.ufc;

import java.util.ArrayList;
import java.util.List;

import jkind.lustre.Expr;
import jkind.lustre.Location;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

public class ObligationSet {
	public List<Obligation> positivePolarity = new ArrayList<Obligation>();
	public List<Obligation> negativePolarity = new ArrayList<Obligation>();
	
	public ObligationSet() {}
	
	public ObligationSet(Expr leaf) {
		add(leaf);
	}

	public void swapPolarities() {
		List<Obligation> temp = positivePolarity;
		positivePolarity = negativePolarity;
		negativePolarity = temp;
	}

	public void add(Expr leaf) {
		positivePolarity.add(new Obligation(leaf));
		negativePolarity.add(new Obligation(leaf, new UnaryExpr(Location.NULL, UnaryOp.NOT, leaf)));
	}
	
	public void addAll(ObligationSet other) {
		positivePolarity.addAll(other.positivePolarity);
		negativePolarity.addAll(other.negativePolarity);
	}

	private void extendListContext(Expr context, List<Obligation> toExtend) {
		for (int i = 0; i < toExtend.size(); i++) {
			toExtend.set(i, Obligation.extendContext(toExtend.get(i), context));
		}
		
	}
	public void extendContext(Expr context) {
		extendListContext(context, positivePolarity);
		extendListContext(context, negativePolarity);
	}
	
	private void listMakeInitStepOnly(List<Obligation> toExtend) {
		for (int i = 0; i < toExtend.size(); i++) {
			toExtend.set(i, Obligation.makeInitStepOnly(toExtend.get(i)));
		}
	}
	public void makeInitStepOnly() {
		listMakeInitStepOnly(positivePolarity);
		listMakeInitStepOnly(negativePolarity);
	}
	
	private void listMakeAfter1Only(List<Obligation> toExtend) {
		for (int i = 0; i < toExtend.size(); i++) {
			toExtend.set(i, Obligation.makeSubsequentStepOnly(toExtend.get(i)));
		}
	}
	public void makeAfter1Only() {
		listMakeAfter1Only(positivePolarity);
		listMakeAfter1Only(negativePolarity); 
	}
	
	private void listAddPre(List<Obligation> toExtend) {
		for (int i = 0; i < toExtend.size(); i++) {
			toExtend.set(i,  Obligation.makePreObligation(toExtend.get(i)));
		}
	}
	
	public void extendPre() {
		listAddPre(positivePolarity);
		listAddPre(negativePolarity); 
	}
	
	public void listSetEqId(List<Obligation> toModify, String eqId) {
		for (int i = 0; i < toModify.size(); i++) {
			toModify.get(i).setEqAssignId(eqId);
		}
	}
	
	public void setEqId(String eqId) {
		listSetEqId(positivePolarity, eqId);
		listSetEqId(negativePolarity, eqId);
	}
}
