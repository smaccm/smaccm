package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;

public class ResoluteInterpreter {
    final private EvaluationContext globalContext;

    public ResoluteInterpreter(EvaluationContext globalContext) {
        this.globalContext = globalContext;
    }

    public ResoluteResult evaluateProveStatement(ProveStatement proveStatement) {
        String proveText = ResoluteProver.proveStatementToString(proveStatement,
                globalContext.getThisInstance());
        ResoluteResult subResult = evaluateProveStatementBody(proveStatement);
        Map<String, EObject> references = Collections.emptyMap();
        return new ClaimResult(proveText, subResult, references, proveStatement);
    }

    private ResoluteResult evaluateProveStatementBody(ProveStatement proveStatement) {
        ResoluteProver prover = new ResoluteProver(globalContext);
        return prover.doSwitch(proveStatement.getExpr());
    }
}
