package com.rockwellcollins.atc.resolute.analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.resolute.analysis.values.FailValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;

public class ResoluteInterpreter {
	private ComponentInstance compInst;
	private ProofType proofType;

	public ResoluteInterpreter(ComponentInstance compInst, ProofType proofType) {
		this.compInst = compInst;
		this.proofType = proofType;

		// initialize the interfaces for all external analysis
		EvaluateExternalAnalysis.init(Platform.getExtensionRegistry());
		if(proofType == ProofType.CONFIDENCE)
			EvaluateConfidenceAnalysis.init(Platform.getExtensionRegistry());
	}

	public List<ResoluteProofTree> evaluateSubclause(ResoluteSubclause resSubclause) {
		List<ResoluteProofTree> result = new ArrayList<>();

		for (Element element : resSubclause.getChildren()) {
			if (element instanceof ProveStatement) {
				result.add(evaluateProveStatement((ProveStatement) element));
			}
		}

		return result;
	}

	public ResoluteProofTree evaluateProveStatement(ProveStatement proveStatement) {
		Expr claim = proveStatement.getExpr();

		ResoluteProofTree proofTree = new ResoluteProofTree(proofType);
		ResoluteEvaluator evaluator;
		
		if(proofType == ProofType.CONFIDENCE){
			evaluator = new ResoluteConfidenceEvaluator(compInst, proofTree);
		}else{
			evaluator = new ResoluteEvaluator(compInst, proofTree);
		}
		
		String text = evaluator.proveStatementToString(proveStatement);
		proofTree.addNewCurrent(proveStatement, text);
		proofTree.setRoot(proofTree.getCurNode());

		try {
			ResoluteValue resVal = evaluator.doSwitch(claim);
			proofTree.setCurReturnVal(proveStatement, text, resVal);
		} catch (ResoluteFailException e) {
			proofTree.getCurNode().setExprStr(e.getMessage());

			ResoluteProofNode curNode = proofTree.getCurNode();
			ResoluteValue fail = new FailValue();
			while (curNode != null) {
				proofTree.setCurReturnVal(curNode.getEObject(), curNode.getExprStr(), fail);
				curNode = proofTree.getCurNode();
			}
			proofTree.setCurReturnVal(proveStatement, text, fail);
		}

		return proofTree;
	}
}
