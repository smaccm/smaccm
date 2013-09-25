package com.rockwellcollins.atc.resolute.analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.Element;
import org.osate.aadl2.Mode;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ModeInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;

import com.rockwellcollins.atc.resolute.analysis.values.FailValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;

public class ResoluteInterpreter {
    private ComponentInstance compInst;
    private ProofType proofType;

    public ResoluteInterpreter(ComponentInstance compInst, ProofType proofType) {
        this.compInst = compInst;
        this.proofType = proofType;
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
    
    public ModeInstance getCompInstSubMode(ComponentInstance compInst, NestedDotID modeId){
        
        if(modeId.getSub() != null){
            NamedElement el = modeId.getBase();
            assert(el instanceof Subcomponent);
            Subcomponent subComp = (Subcomponent)el;
            
            for(ComponentInstance subCompInst : compInst.getAllComponentInstances()){
                if(subCompInst.getSubcomponent().equals(subComp)){
                    return getCompInstSubMode(subCompInst, modeId.getSub());
                }
            }
            assert(false);
        }
        
        Mode thisMode = (Mode)modeId.getBase();
        for(ModeInstance modeInst : compInst.getModeInstances()){
            if(modeInst.getMode().equals(thisMode)){
                return modeInst;
            }
        }
        assert(false);
        return null;
    }
        
    
    public List<ModeInstance> getCompInstSubModes(ComponentInstance subCompInst, List<NestedDotID> nestModesIds){

        List<ModeInstance> result = new ArrayList<>();
        if(nestModesIds != null){
            for(NestedDotID id : nestModesIds){
                result.add(getCompInstSubMode(subCompInst, id));
            }
        }
        return result;
    }
    
    public List<SystemOperationMode> getAllModeInstances(ProveStatement proveStatement){
        
        EList<NestedDotID> nestModesIds = proveStatement.getModes();
        List<ModeInstance> modeInsts = getCompInstSubModes(compInst, nestModesIds);

        NamedElement root = compInst.getElementRoot();
        assert(root instanceof SystemInstance);
        SystemInstance sysInst = (SystemInstance)root;

        List<SystemOperationMode> sysModes = sysInst.getSystemOperationModesFor(modeInsts);

        return sysModes;
    }
    

    public ResoluteProofTree evaluateProveStatement(ProveStatement proveStatement) {
        Expr claim = proveStatement.getExpr();

        ResoluteProofTree proofTree = new ResoluteProofTree(proofType);
        ResoluteEvaluator evaluator;

        if (proofType == ProofType.CONFIDENCE) {
            evaluator = new ResoluteConfidenceEvaluator(compInst, proofTree);
        } else {
            evaluator = new ResoluteEvaluator(compInst, proofTree);
        }

        List<SystemOperationMode> sysModes = getAllModeInstances(proveStatement);
        
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
