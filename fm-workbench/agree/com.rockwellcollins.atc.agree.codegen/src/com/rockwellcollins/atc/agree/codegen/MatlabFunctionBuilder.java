package com.rockwellcollins.atc.agree.codegen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jkind.lustre.BinaryExpr;
import jkind.lustre.Expr;

import org.osate.aadl2.impl.DataPortImpl;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

public class MatlabFunctionBuilder {

	public static void createMatlabFunction(AgreeProgram agreeProgram) {

		AgreeNode topAgreeNode = agreeProgram.topNode;

		if (topAgreeNode.subNodes.isEmpty()) {
			createFunctionPerNode(topAgreeNode);
		} else {
			for (AgreeNode agreeNode : topAgreeNode.subNodes) {
				createFunctionPerNode(agreeNode);
			}
		}
	}

	private static void createFunctionPerNode(AgreeNode agreeNode) {
		int index = 0;
		boolean firstVar = true;

		System.out.print("function check_" + agreeNode.id + "_properties (");

		for (AgreeVar var : agreeNode.inputs) {
			if (!firstVar) {
				System.out.print(", ");
			}
			firstVar = false;
			System.out.print(var.id);
		}

		for (AgreeVar var : agreeNode.outputs) {
			if (var.reference instanceof DataPortImpl) {
				System.out.print(", ");
				System.out.print(var.id);
			}
		}
		System.out.println(")");

		for (AgreeStatement statement : agreeNode.assertions) {
			BinaryExpr assertion = (BinaryExpr) statement.expr;
			String assertionName = assertion.left.toString();
			if (assertion.op.name().equals("EQUAL")) {
				System.out.println(assertionName + " = "
						+ assertion.right.toString() + ";");
			}
		}

		for (AgreeStatement statement : agreeNode.guarantees) {
			String guaranteeStr = statement.expr.toString();
			Pattern pattern = Pattern.compile("([^><!]{1})(={1})([^><!]{1})");
			Matcher matcher = pattern.matcher(guaranteeStr);
			String guaranteeStrNew = matcher.replaceAll("$1==$3");
			String guaranteeName = "guarantee" + index++;
			System.out.println(guaranteeName + " = " + guaranteeStrNew + ";");
			System.out.println("sldv.prove(" + guaranteeName + ");");
		}

		index = 0;
		for (AgreeStatement statement : agreeNode.assumptions) {
			String assuptionName = "assumption" + index++;
			System.out.println(assuptionName + " = (" + statement.expr + ");");
			System.out.println("sldv.assume(" + assuptionName + ");");
		}
		System.out.println();
	}

}
