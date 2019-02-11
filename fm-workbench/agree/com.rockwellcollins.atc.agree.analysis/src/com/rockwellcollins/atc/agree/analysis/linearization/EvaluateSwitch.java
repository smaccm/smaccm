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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SelectionExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;
import com.rockwellcollins.atc.agree.analysis.AgreeException;


public class EvaluateSwitch extends AgreeSwitch<Function<Map<String, Double>, Double>> {

	private final List<String> vars;

	public EvaluateSwitch(String... vars) {
		this.vars = Arrays.asList(vars);
	}

	@Override
	public Function<Map<String, Double>, Double> caseLinearizationDef(LinearizationDef ctx) {
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
	public Function<Map<String, Double>, Double> caseSelectionExpr(SelectionExpr ctx) {
		String id = ctx.getField().getName();
		if (!vars.contains(id)) {
			throw new IllegalArgumentException("Unknown variable: " + id);
		}
		return map -> map.get(id);
	}

	private static double invokeStaticMethod(java.lang.reflect.Method method, Object... args) {
		try {
			return (double) method.invoke(null, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new AgreeException("Cannot invoke method '" + method.getName() + "': " + e.getMessage());
		}
	}

	private Map<com.rockwellcollins.atc.agree.agree.LibraryFnDef, java.lang.reflect.Method> nativeCallMap = new HashMap<>();

	private java.lang.reflect.Method lookupMethod(com.rockwellcollins.atc.agree.agree.LibraryFnDef nativeDef) {
		java.lang.reflect.Method result = nativeCallMap.get(nativeDef);
		final String containingClassName = "java.lang.Math";

		if (result == null) {
			String methodName = nativeDef.getName();

			try {
				Class<?> containingClass = java.lang.Class.forName(containingClassName);
				// Class<?> returnClass = double.class;
				Class<?>[] parameterTypes = new Class<?>[] { double.class };
				result = containingClass.getMethod(methodName, parameterTypes);
				if (Modifier.isStatic(result.getModifiers())) {
					nativeCallMap.put(nativeDef, result);
				} else {
					throw new AgreeException("Method '" + methodName + "' is not static");
				}
			} catch (SecurityException e) {
				e.printStackTrace();
				throw new AgreeException(
						"Cannot resolve method '" + methodName + "' due to security violation: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new AgreeException("Cannot resolve class '" + containingClassName + "': " + e.getMessage());
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				throw new AgreeException("Cannot resolve method '" + methodName + "': " + e.getMessage());
			}
		}

		return result;
	}

	@Override
	public Function<Map<String, Double>, Double> caseCallExpr(CallExpr ctx) {
		org.osate.aadl2.NamedElement fn = ctx.getRef().getElm();
		Function<Map<String, Double>, Double> body = doSwitch(ctx.getArgs().get(0));

		if (fn instanceof com.rockwellcollins.atc.agree.agree.LibraryFnDef) {
			java.lang.reflect.Method method = lookupMethod((com.rockwellcollins.atc.agree.agree.LibraryFnDef) fn);
			return x -> invokeStaticMethod(method, body.apply(x));
		} else {
			throw new AgreeException("Call to '" + fn.getName() + "' in linearization body is not a native method");
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
