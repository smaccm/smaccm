package com.rockwellcollins.atc.resolute.analysis;

import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFuncCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.UnaryExpr;

public class ResoluteConfidenceEvaluator extends ResoluteEvaluator {

    public ResoluteConfidenceEvaluator(ComponentInstance thisInst, ResoluteProofTree proofTree) {
        super(thisInst, proofTree);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ResoluteValue caseFailExpr(FailExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseFailExpr(object);
    }

    @Override
    public ResoluteValue caseBinaryExpr(BinaryExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseBinaryExpr(object);
    }

    @Override
    public ResoluteValue caseUnaryExpr(UnaryExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseUnaryExpr(object);
    }

    @Override
    public ResoluteValue caseIdExpr(IdExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseIdExpr(object);
    }

    @Override
    public ResoluteValue caseThisExpr(ThisExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseThisExpr(object);
    }

    @Override
    public ResoluteValue caseIntExpr(IntExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseIntExpr(object);
    }

    @Override
    public ResoluteValue caseRealExpr(RealExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseRealExpr(object);
    }

    @Override
    public ResoluteValue caseBoolExpr(BoolExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseBoolExpr(object);
    }

    @Override
    public ResoluteValue caseStringExpr(StringExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseStringExpr(object);
    }

    @Override
    public ResoluteValue caseIfThenElseExpr(IfThenElseExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseIfThenElseExpr(object);
    }

    @Override
    public ResoluteValue caseQuantifiedExpr(QuantifiedExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseQuantifiedExpr(object);
    }

    @Override
    public ResoluteValue caseFnCallExpr(FnCallExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseFnCallExpr(object);
    }

    @Override
    public ResoluteValue caseFilterMapExpr(FilterMapExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseFilterMapExpr(object);
    }

    @Override
    public ResoluteValue caseBuiltInFuncCallExpr(BuiltInFuncCallExpr object) {
        EvaluateConfidenceAnalysis.evaluate(object);
        return super.caseBuiltInFuncCallExpr(object);
    }

}
