/**
 * Copyright (c) 2016, Rockwell Collins.
 *
 * Developed with the sponsorship of Defense Advanced Research Projects Agency
 * (DARPA).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this data, including any software or models in source or binary form, as
 * well as any drawings, specifications, and documentation (collectively
 * "the Data"), to deal in the Data without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Data, and to permit persons to whom the
 * Data is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Data.
 *
 * THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE
 * USE OR OTHER DEALINGS IN THE DATA.
 */

package com.rockwellcollins.atc.agree.analysis.linearization;

import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SelectionExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;


public class MatlabPrintSwitch extends AgreeSwitch<String> {

	@Override
	public String caseLinearizationDef(LinearizationDef ctx) {
		return doSwitch(ctx.getExprBody());
	}

	@Override
	public String caseBinaryExpr(BinaryExpr ctx) {
		String left = doSwitch(ctx.getLeft());
		String op = ctx.getOp();
		String r = doSwitch(ctx.getRight());

		return "(" + left + " ." + op + " " + r + ")";
	}

	@Override
	public String caseUnaryExpr(UnaryExpr ctx) {
		String body = doSwitch(ctx.getExpr());
		return "(-" + body + ")";
	}

	@Override
	public String caseSelectionExpr(SelectionExpr ctx) {
		return ctx.getField().getName();
	}

	@Override
	public String caseCallExpr(CallExpr ctx) {
		String fn = ctx.getRef().getElm().getName();
		String arg = doSwitch(ctx.getArgs().get(0));
		return fn + "(" + arg + ")";
	}

	@Override
	public String caseIntLitExpr(IntLitExpr ctx) {
		return ctx.getVal();
	}

	@Override
	public String caseRealLitExpr(RealLitExpr ctx) {
		return ctx.getVal();
	}

}
