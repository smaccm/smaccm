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

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;
import jkind.lustre.Location;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;

/*
 * If we want positional information, we can find it by examining "parents"
 * Start the obligation's value at 1.
 * If it is a LHS expression of an operator:
 *    leave alone
 * If it is a RHS expression of an operator:
 *    we have to know the max element.
 * The good news is that I think this location information comes "for free" in the 
 * position of each expression in the obligation set.
 *     
 */
public class Obligation {
	Expr obligationExpr;
	Expr testedCondition; 
	String eqAssignId;
	
	// for 'leaf level' expression.
	public Obligation(Expr testedCondition) {
		this.testedCondition = testedCondition;
		this.obligationExpr = testedCondition;
	}
	
	public Obligation(Expr testedCondition, Expr obligationExpr) {
		this.testedCondition = testedCondition;
		this.obligationExpr = obligationExpr; 
	}
	
	public static Obligation extendContext(Obligation ob, Expr extend) {
		return new Obligation(ob.testedCondition, 
				new BinaryExpr(ob.obligationExpr, BinaryOp.AND, extend));
		
	}

	public static Obligation makeInitStepOnly(Obligation ob) {
		return new Obligation(ob.testedCondition, 
				new BinaryExpr(ob.obligationExpr, BinaryOp.ARROW, new BoolExpr(Location.NULL, false)));
	}
	
	public static Obligation makeSubsequentStepOnly(Obligation ob) {
		return new Obligation(ob.testedCondition, 
				new BinaryExpr(new BoolExpr(Location.NULL, false), BinaryOp.ARROW, ob.obligationExpr));
	}

	public static Obligation makePreObligation(Obligation ob) {
		return new Obligation(ob.testedCondition, 
				new UnaryExpr(UnaryOp.PRE, ob.obligationExpr));
	}
	
	public String getObligationExplanation() {
		return "Testing: [" + testedCondition.toString() + "] in [" + obligationExpr + "]"; 
	}

	public Expr getObligationExpr() {
		return obligationExpr;
	}

	public void setObligationExpr(Expr obligationExpr) {
		this.obligationExpr = obligationExpr;
	}

	public Expr getTestedCondition() {
		return testedCondition;
	}

	public void setTestedCondition(Expr testedCondition) {
		this.testedCondition = testedCondition;
	}

	public String getEqAssignId() {
		return eqAssignId;
	}

	public void setEqAssignId(String eqAssignId) {
		this.eqAssignId = eqAssignId;
	}

	
}
