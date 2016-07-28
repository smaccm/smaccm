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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LinearizationDefExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.validation.AgreeJavaValidator;

public class EvaluateSwitch extends AgreeSwitch<Function<Map<String, Double>, Double>> {

	private final List<String> vars;

	public EvaluateSwitch(String... vars) {
		this.vars = Arrays.asList(vars);
	}

	@Override
	public Function<Map<String, Double>, Double> caseLinearizationDefExpr(LinearizationDefExpr ctx) {
		return doSwitch(ctx.getExprBody());
	}

	@Override
	public Function<Map<String, Double>, Double> caseBinaryExpr(BinaryExpr ctx) {
		String op = ctx.getOp();
		Function<Map<String, Double>, Double> left = doSwitch(ctx.getLeft());
		Function<Map<String, Double>, Double> right = doSwitch(ctx.getRight());

		switch (op) {
		case "+":
			return x -> left.apply(x) + right.apply(x);

		case "-":
			return x -> left.apply(x) - right.apply(x);

		case "*":
			return x -> left.apply(x) * right.apply(x);

		case "/":
			return x -> left.apply(x) / right.apply(x);

		case "^":
			return x -> Math.pow(left.apply(x), right.apply(x));

		default:
			throw new IllegalArgumentException("Unknown binary operator: " + op);
		}
	}

	@Override
	public Function<Map<String, Double>, Double> caseUnaryExpr(UnaryExpr ctx) {
		Function<Map<String, Double>, Double> body = doSwitch(ctx.getExpr());
		return x -> -body.apply(x);
	}

	@Override
	public Function<Map<String, Double>, Double> caseNestedDotID(NestedDotID ctx) {
		String id = AgreeJavaValidator.getFinalNestId(ctx).getName();
		if (!vars.contains(id)) {
			throw new IllegalArgumentException("Unknown variable: " + id);
		}
		return map -> map.get(id);
	}

	@Override
	public Function<Map<String, Double>, Double> caseFnCallExpr(FnCallExpr ctx) {
		String fn = AgreeJavaValidator.getFinalNestId(ctx.getFn()).getName();
		Function<Map<String, Double>, Double> body = doSwitch(ctx.getArgs().get(0));

		switch (fn) {
		case "sin":
			return x -> Math.sin(body.apply(x));

		case "cos":
			return x -> Math.cos(body.apply(x));

		case "tan":
			return x -> Math.tan(body.apply(x));

		case "asin":
			return x -> Math.asin(body.apply(x));

		case "acos":
			return x -> Math.acos(body.apply(x));

		case "atan":
			return x -> Math.atan(body.apply(x));

		case "sqrt":
			return x -> Math.sqrt(body.apply(x));

		case "abs":
			return x -> Math.abs(body.apply(x));

		case "log":
			return x -> Math.log(body.apply(x));

		default:
			throw new IllegalArgumentException("Unknown function: " + fn);
		}
	}

	@Override
	public Function<Map<String, Double>, Double> caseIntLitExpr(IntLitExpr ctx) {
		return x -> Double.parseDouble(ctx.getVal());
	}

	@Override
	public Function<Map<String, Double>, Double> caseRealLitExpr(RealLitExpr ctx) {
		return x -> Double.parseDouble(ctx.getVal());
	}

}
