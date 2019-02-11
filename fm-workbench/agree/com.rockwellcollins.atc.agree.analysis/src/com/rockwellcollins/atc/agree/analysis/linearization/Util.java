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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.osate.aadl2.NamedElement;

import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SelectionExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.validation.AgreeJavaValidator;

public class Util {

	public static Function<Double, Double> translateNonlinearExpr(Expr fctx, String varName) {
		Function<Map<String, Double>, Double> fun = new EvaluateSwitch(varName).doSwitch(fctx);
		return x -> fun.apply(map(varName, x));
	}

	public static BiFunction<Double, Double, Double> translateNonlinearExpr(Expr fctx, String varName1,
			String varName2) {
		Function<Map<String, Double>, Double> fun = new EvaluateSwitch(varName1, varName2).doSwitch(fctx);
		return (x, y) -> fun.apply(map(varName1, x, varName2, y));
	}

	public static String translateNonlinearExprToMatlab(Expr fctx) {
		return new MatlabPrintSwitch().doSwitch(fctx);
	}

	private static Map<String, Double> map(String k, Double v) {
		return Collections.singletonMap(k, v);
	}

	private static Map<String, Double> map(String k1, Double v1, String k2, Double v2) {
		HashMap<String, Double> result = new HashMap<>();
		result.put(k1, v1);
		result.put(k2, v2);
		return result;
	}

	public static Double getDoubleValue(Expr expr) {
		Double result = Double.valueOf(0.0);
		assert (AgreeJavaValidator.exprIsConst(expr));
		if (expr instanceof NamedElement) {
			if (expr instanceof ConstStatement) {
				result = getDoubleValue(((ConstStatement) expr).getExpr());
			}
		} else if (expr instanceof SelectionExpr) {
			NamedElement finalId = ((SelectionExpr) expr).getField();
			if (finalId instanceof ConstStatement) {
				result = getDoubleValue(((ConstStatement) finalId).getExpr());
			}
		} else if (expr instanceof RealLitExpr) {
			result = Double.valueOf(((RealLitExpr) expr).getVal());
		} else if (expr instanceof IntLitExpr) {
			result = Double.valueOf(((IntLitExpr) expr).getVal());
		} else if (expr instanceof BinaryExpr) {
			BinaryExpr binExpr = (BinaryExpr) expr;
			Double left = getDoubleValue(binExpr.getLeft());
			Double right = getDoubleValue(binExpr.getRight());
			switch (binExpr.getOp()) {
			case "+":
				result = left + right;
				break;
			case "-":
				result = left - right;
				break;
			case "*":
				result = left * right;
				break;
			case "/":
				result = left / right;
				break;
			case "^":
				result = Math.pow(left, right);
				break;
			default:
				throw new AgreeException("binary expression is not evaluable as integer constant");
			}
			return result;
		} else if (expr instanceof UnaryExpr) {
			UnaryExpr unExpr = (UnaryExpr) expr;
			Double right = getDoubleValue(unExpr.getExpr());
			switch (unExpr.getOp()) {
			case "-":
				result = -right;
				break;
			default:
				throw new AgreeException("unary expression is not evaluable as integer constant");
			}
		} else {
			throw new AgreeException("expression is not evaluable as integer constant");
		}
		return result;
	}

}
