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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.Aadl2Factory;
import org.osate.aadl2.BooleanLiteral;

import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.NamedElmExpr;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.linearization.Linearize.BoundingSegments;
import com.rockwellcollins.atc.agree.analysis.linearization.Linearize.Segment;

import jkind.lustre.Equation;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;

public class LinearizationRewriter {

	private static final AgreeFactory af = AgreeFactory.eINSTANCE;
	private static final Aadl2Factory aadlF = Aadl2Factory.eINSTANCE;

	private static final String dotChar = "__";
	private static final String constraintPrefix = dotChar + "constraint" + dotChar;

	public static String getConstraintFormName(String fnName) {
		return constraintPrefix.concat(fnName).toUpperCase();
	}

	private static final String inputId = "input";
	private static final String outputId = "output";
	private static final String constraintId = "constraint";
	private static final String domainCheckLemmaId = dotChar + "domainCheckLemma";

	private static jkind.lustre.Expr generateLustreLinearBoundImplicationExpr(jkind.lustre.BinaryOp op, Segment seg) {
		jkind.lustre.RealExpr inputMinExpr = new jkind.lustre.RealExpr(BigDecimal.valueOf(seg.startX));
		jkind.lustre.RealExpr inputMaxExpr = new jkind.lustre.RealExpr(BigDecimal.valueOf(seg.stopX));
		jkind.lustre.RealExpr resultOriginExpr = new jkind.lustre.RealExpr(BigDecimal.valueOf(seg.startY));
		jkind.lustre.RealExpr resultSlopeExpr = new jkind.lustre.RealExpr(
				BigDecimal.valueOf((seg.stopY - seg.startY) / (seg.stopX - seg.startX)));

		jkind.lustre.IdExpr inputIdExpr = new jkind.lustre.IdExpr(inputId);
		jkind.lustre.IdExpr outputIdExpr = new jkind.lustre.IdExpr(outputId);

		jkind.lustre.Expr domainMinExpr = new jkind.lustre.BinaryExpr(inputIdExpr, jkind.lustre.BinaryOp.GREATEREQUAL,
				inputMinExpr);
		jkind.lustre.Expr domainMaxExpr = new jkind.lustre.BinaryExpr(inputIdExpr, jkind.lustre.BinaryOp.LESSEQUAL,
				inputMaxExpr);
		jkind.lustre.Expr domainExpr = new jkind.lustre.BinaryExpr(domainMinExpr, jkind.lustre.BinaryOp.AND,
				domainMaxExpr);

		jkind.lustre.Expr shiftExpr = new jkind.lustre.BinaryExpr(inputIdExpr, jkind.lustre.BinaryOp.MINUS,
				inputMinExpr);
		jkind.lustre.Expr multiplyExpr = new jkind.lustre.BinaryExpr(resultSlopeExpr, jkind.lustre.BinaryOp.MULTIPLY,
				shiftExpr);
		jkind.lustre.Expr additionExpr = new jkind.lustre.BinaryExpr(resultOriginExpr, jkind.lustre.BinaryOp.PLUS,
				multiplyExpr);
		jkind.lustre.Expr linearBoundExpr = new jkind.lustre.BinaryExpr(outputIdExpr, op, additionExpr);

		return new jkind.lustre.BinaryExpr(domainExpr, jkind.lustre.BinaryOp.IMPLIES, linearBoundExpr);
	}

	@SuppressWarnings("unused")
	private static jkind.lustre.Node generateLustreConstraintForm(LinearizationDef linDef, BoundingSegments segs) {
		String nodeName = getConstraintFormName(AgreeUtils.getNodeName(linDef));

		List<VarDecl> inputs = new ArrayList<>();
		inputs.add(new jkind.lustre.VarDecl(inputId, jkind.lustre.NamedType.REAL));
		inputs.add(new jkind.lustre.VarDecl(outputId, jkind.lustre.NamedType.REAL));

		List<VarDecl> outputs = new ArrayList<>();
		inputs.add(new jkind.lustre.VarDecl(constraintId, jkind.lustre.NamedType.BOOL));

		List<VarDecl> locals = new ArrayList<>();
		locals.add(new jkind.lustre.VarDecl(domainCheckLemmaId, jkind.lustre.NamedType.BOOL));

		jkind.lustre.BinaryExpr domainCheckLowerExpr = new jkind.lustre.BinaryExpr(
				new jkind.lustre.RealExpr(BigDecimal.valueOf(segs.lower.getFirst().startX)),
				jkind.lustre.BinaryOp.LESSEQUAL, new jkind.lustre.IdExpr(inputId));
		jkind.lustre.BinaryExpr domainCheckUpperExpr = new jkind.lustre.BinaryExpr(new jkind.lustre.IdExpr(inputId),
				jkind.lustre.BinaryOp.LESSEQUAL,
				new jkind.lustre.RealExpr(BigDecimal.valueOf(segs.lower.getLast().stopX)));
		jkind.lustre.BinaryExpr domainCheckExpr = new jkind.lustre.BinaryExpr(domainCheckLowerExpr,
				jkind.lustre.BinaryOp.AND, domainCheckUpperExpr);

		jkind.lustre.Expr upperBoundExpr = new jkind.lustre.BoolExpr(true);
		for (Segment seg : segs.upper) {
			upperBoundExpr = new jkind.lustre.BinaryExpr(upperBoundExpr, jkind.lustre.BinaryOp.AND,
					generateLustreLinearBoundImplicationExpr(jkind.lustre.BinaryOp.LESSEQUAL, seg));
		}

		jkind.lustre.Expr lowerBoundExpr = new jkind.lustre.BoolExpr(true);
		for (Segment seg : segs.upper) {
			lowerBoundExpr = new jkind.lustre.BinaryExpr(lowerBoundExpr, jkind.lustre.BinaryOp.AND,
					generateLustreLinearBoundImplicationExpr(jkind.lustre.BinaryOp.GREATEREQUAL, seg));
		}

		jkind.lustre.Expr constraintExpr = new jkind.lustre.BinaryExpr(domainCheckExpr, jkind.lustre.BinaryOp.AND,
				new jkind.lustre.BinaryExpr(upperBoundExpr, jkind.lustre.BinaryOp.AND, lowerBoundExpr));

		List<Equation> equations = new ArrayList<>();
		equations.add(new jkind.lustre.Equation(new jkind.lustre.IdExpr(constraintId), constraintExpr));
		equations.add(new jkind.lustre.Equation(new jkind.lustre.IdExpr(domainCheckLemmaId), domainCheckExpr));

		List<String> properties = new ArrayList<>();
		properties.add(domainCheckLemmaId);

		NodeBuilder builder = new NodeBuilder(nodeName);
		builder.addInputs(inputs);
		builder.addOutputs(outputs);
		builder.addLocals(locals);
		builder.addEquations(equations);
		builder.addProperties(properties);

		return builder.build();
	}

	private static Expr generateAgreeLinearBoundImplicationExpr(Arg inputArg, Arg resultArg, String relop,
			Segment seg) {
		RealLitExpr inputMinExpr = af.createRealLitExpr();
		inputMinExpr.setVal(Double.toString(seg.startX));

		RealLitExpr inputMaxExpr = af.createRealLitExpr();
		inputMaxExpr.setVal(Double.toString(seg.stopX));

		RealLitExpr resultOriginExpr = af.createRealLitExpr();
		resultOriginExpr.setVal(Double.toString(seg.startY));

		RealLitExpr resultSlopeExpr = af.createRealLitExpr();
		resultSlopeExpr.setVal(Double.toString((seg.stopY - seg.startY) / (seg.stopX - seg.startX)));

		NamedElmExpr inputId = af.createNamedElmExpr();
		inputId.setElm(EcoreUtil.copy(inputArg));


		NamedElmExpr resultId = af.createNamedElmExpr();
		resultId.setElm(EcoreUtil.copy(resultArg));
//=======
//
//		NestedDotID resultId = af.createNestedDotID();
//		resultId.setBase(EcoreUtil.copy(resultArg));
//>>>>>>> origin/develop


		BinaryExpr rangeMinExpr = af.createBinaryExpr();
		rangeMinExpr.setOp(">=");
		rangeMinExpr.setLeft(EcoreUtil.copy(inputId));
		rangeMinExpr.setRight(EcoreUtil.copy(inputMinExpr));

		BinaryExpr rangeMaxExpr = af.createBinaryExpr();
		rangeMaxExpr.setOp("<=");
		rangeMaxExpr.setLeft(EcoreUtil.copy(inputId));
		rangeMaxExpr.setRight(EcoreUtil.copy(inputMaxExpr));

		BinaryExpr rangeExpr = af.createBinaryExpr();
		rangeExpr.setOp("and");
		rangeExpr.setLeft(EcoreUtil.copy(rangeMinExpr));
		rangeExpr.setRight(EcoreUtil.copy(rangeMaxExpr));

		BinaryExpr shiftExpr = af.createBinaryExpr();
		shiftExpr.setOp("-");
		shiftExpr.setLeft(EcoreUtil.copy(inputId));
		shiftExpr.setRight(EcoreUtil.copy(inputMinExpr));

		BinaryExpr multiplyExpr = af.createBinaryExpr();
		multiplyExpr.setOp("*");
		multiplyExpr.setLeft(EcoreUtil.copy(resultSlopeExpr));
		multiplyExpr.setRight(shiftExpr);

		BinaryExpr additionExpr = af.createBinaryExpr();
		additionExpr.setOp("+");
		additionExpr.setLeft(EcoreUtil.copy(resultOriginExpr));
		additionExpr.setRight(multiplyExpr);

		BinaryExpr linearBoundExpr = af.createBinaryExpr();
		linearBoundExpr.setOp(relop);
		linearBoundExpr.setLeft(EcoreUtil.copy(resultId));
		linearBoundExpr.setRight(additionExpr);

		BinaryExpr result = af.createBinaryExpr();
		result.setOp("=>");
		result.setLeft(rangeExpr);
		result.setRight(linearBoundExpr);

		return result;
	}

	private static NodeDef generateAgreeConstraintForm(LinearizationDef linDef, BoundingSegments segs) {
		NodeDef result = af.createNodeDef();
		result.setName(getConstraintFormName(AgreeUtils.getNodeName(linDef)));

		Arg inputArg = af.createArg();
		PrimType inputArgType = af.createPrimType();
		inputArgType.setName("real");
		inputArg.setType(inputArgType);
		inputArg.setName("inp");
		result.getArgs().add(inputArg);

		Arg outputArg = af.createArg();
		PrimType outputArgType = af.createPrimType();
		outputArgType.setName("real");
		outputArg.setType(outputArgType);
		outputArg.setName("result");
		result.getArgs().add(outputArg);

		Arg constraintArg = af.createArg();
		PrimType constraintArgType = af.createPrimType();
		constraintArgType.setName("bool");
		constraintArg.setType(constraintArgType);
		constraintArg.setName("constraint");
		result.getRets().add(constraintArg);

		NamedElmExpr inputId = af.createNamedElmExpr();
		inputId.setElm(EcoreUtil.copy(inputArg));

		RealLitExpr domainCheckLowerLit = af.createRealLitExpr();
		domainCheckLowerLit.setVal(Double.toString(segs.lower.getFirst().startX));

		BinaryExpr domainCheckLowerExpr = af.createBinaryExpr();
		domainCheckLowerExpr.setOp("<=");
		domainCheckLowerExpr.setLeft(domainCheckLowerLit);
		domainCheckLowerExpr.setRight(EcoreUtil.copy(inputId));

		RealLitExpr domainCheckUpperLit = af.createRealLitExpr();
		domainCheckUpperLit.setVal(Double.toString(segs.lower.getLast().stopX));

		BinaryExpr domainCheckUpperExpr = af.createBinaryExpr();
		domainCheckUpperExpr.setOp("<=");
		domainCheckUpperExpr.setLeft(EcoreUtil.copy(inputId));
		domainCheckUpperExpr.setRight(domainCheckUpperLit);

		BinaryExpr domainCheckExpr = af.createBinaryExpr();
		domainCheckExpr.setOp("and");
		domainCheckExpr.setLeft(domainCheckLowerExpr);
		domainCheckExpr.setRight(domainCheckUpperExpr);

		BoolLitExpr trueLitExpr = af.createBoolLitExpr();
		BooleanLiteral trueLitValue = aadlF.createBooleanLiteral();
		trueLitValue.setValue(true);
		trueLitExpr.setVal(trueLitValue);

		Expr upperBoundExpr = EcoreUtil.copy(trueLitExpr);
		for (Segment seg : segs.upper) {
			BinaryExpr andExpr = af.createBinaryExpr();
			andExpr.setOp("and");
			andExpr.setLeft(upperBoundExpr);
			andExpr.setRight(generateAgreeLinearBoundImplicationExpr(inputArg, outputArg, "<=", seg));

			upperBoundExpr = andExpr;
		}

		Expr lowerBoundExpr = EcoreUtil.copy(trueLitExpr);
		for (Segment seg : segs.lower) {
			BinaryExpr andExpr = af.createBinaryExpr();
			andExpr.setOp("and");
			andExpr.setLeft(lowerBoundExpr);
			andExpr.setRight(generateAgreeLinearBoundImplicationExpr(inputArg, outputArg, ">=", seg));

			lowerBoundExpr = andExpr;
		}

		BinaryExpr boundsCheckExpr = af.createBinaryExpr();
		boundsCheckExpr.setOp("and");
		boundsCheckExpr.setLeft(upperBoundExpr);
		boundsCheckExpr.setRight(lowerBoundExpr);

		BinaryExpr constraintExpr = af.createBinaryExpr();
		constraintExpr.setOp("and");
		constraintExpr.setLeft(domainCheckExpr);
		constraintExpr.setRight(boundsCheckExpr);

		NodeEq constraintEq = af.createNodeEq();
		constraintEq.getLhs().add(constraintArg);
		constraintEq.setExpr(constraintExpr);

		NodeBodyExpr nodeBody = af.createNodeBodyExpr();
		nodeBody.getStmts().add(constraintEq);
		result.setNodeBody(nodeBody);

		NodeLemma domainCheckLemma = af.createNodeLemma();
		domainCheckLemma.setStr(result.getName() + " domain check");
		domainCheckLemma.setExpr(EcoreUtil.copy(domainCheckExpr));
		nodeBody.getStmts().add(domainCheckLemma);

		return result;
	}

	private HashMap<LinearizationDef, NodeDef> linearizationDefToConstraintNodeDefMap;

	public LinearizationRewriter() {
		linearizationDefToConstraintNodeDefMap = new HashMap<>();
	}

	protected Map<LinearizationDef, NodeDef> getLinearizationDefToConstraintNodeDefMap() {
		return linearizationDefToConstraintNodeDefMap;
	}

	public NodeDef getAgreeConstraintNodeDefFor(LinearizationDef linDef) {
		return linearizationDefToConstraintNodeDefMap.get(linDef);
	}

	public NodeDef addLinearization(LinearizationDef linDef) {
		Linearize linearization = new Linearize(linDef);
		NodeDef nodeDef = generateAgreeConstraintForm(linDef, linearization.segments);
		linearizationDefToConstraintNodeDefMap.put(linDef, nodeDef);
		return nodeDef;
	}

	public AgreeNode visit(AgreeNode agreeNode) {
		Map<String, String> linearizationMap = new HashMap<>();
		for (Map.Entry<LinearizationDef, NodeDef> entry : linearizationDefToConstraintNodeDefMap.entrySet()) {
			linearizationMap.put(AgreeUtils.getNodeName(entry.getKey()), entry.getValue().getName());
		}
		LinearizationAgreeASTVisitor visitor = new LinearizationAgreeASTVisitor(linearizationMap);
		return visitor.visit(agreeNode);
	}

}
