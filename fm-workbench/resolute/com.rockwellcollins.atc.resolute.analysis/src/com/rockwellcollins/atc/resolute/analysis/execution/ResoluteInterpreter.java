package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.osate.aadl2.Mode;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ModeInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.FailResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;

public class ResoluteInterpreter {
    final private ComponentInstance compInst;

    public ResoluteInterpreter(ComponentInstance compInst) {
        this.compInst = compInst;
    }

    public ResoluteResult evaluateProveStatement(ProveStatement proveStatement) {
        String proveText = ResoluteProver.proveStatementToString(proveStatement, compInst);
        ResoluteResult subResult = evaluateProveStatementBody(proveStatement, getAllModeInstances(proveStatement));
        Map<String, EObject> references = Collections.emptyMap();
        if (proveStatement.getModes().size() == 1) {
            String mode = NodeModelUtils.getNode(proveStatement.getModes().get(0)).getText();
            proveText += " (mode: " + mode + ")";
        }
        return new ClaimResult(proveText, subResult, references, proveStatement);
    }

    private ModeInstance getCompInstSubMode(ComponentInstance compInst, NestedDotID modeId) {
        if (modeId.getSub() != null) {
            NamedElement el = modeId.getBase();
            assert (el instanceof Subcomponent);
            Subcomponent subComp = (Subcomponent) el;

            for (ComponentInstance subCompInst : compInst.getAllComponentInstances()) {
                if (subCompInst.getSubcomponent().equals(subComp)) {
                    return getCompInstSubMode(subCompInst, modeId.getSub());
                }
            }
            throw new IllegalArgumentException();
        }

        Mode thisMode = (Mode) modeId.getBase();
        for (ModeInstance modeInst : compInst.getModeInstances()) {
            if (modeInst.getMode().equals(thisMode)) {
                return modeInst;
            }
        }
        throw new IllegalArgumentException();
    }

    private List<ModeInstance> getCompInstSubModes(ComponentInstance subCompInst,
            List<NestedDotID> nestModesIds) {
        List<ModeInstance> result = new ArrayList<>();
        if (nestModesIds != null) {
            for (NestedDotID id : nestModesIds) {
                result.add(getCompInstSubMode(subCompInst, id));
            }
        }
        return result;
    }

    private List<SystemOperationMode> getAllModeInstances(ProveStatement proveStatement) {
        List<NestedDotID> nestModesIds = proveStatement.getModes();

        if (nestModesIds == null || nestModesIds.size() == 0) {
            return new ArrayList<>();
        }

        List<ModeInstance> modeInsts = getCompInstSubModes(compInst, nestModesIds);

        NamedElement root = compInst.getElementRoot();
        assert (root instanceof SystemInstance);
        SystemInstance sysInst = (SystemInstance) root;

        List<SystemOperationMode> sysModes = sysInst.getSystemOperationModesFor(modeInsts);

        return sysModes;
    }

    private ResoluteResult evaluateProveStatementBody(ProveStatement proveStatement,
            List<SystemOperationMode> sysModes) {
        if (sysModes.size() <= 1) {
            ResoluteProver prover = new ResoluteProver(compInst, sysModes);
            return prover.doSwitch(proveStatement.getExpr());
        } else {
            StringBuilder sb = new StringBuilder();
            for (SystemOperationMode mode : sysModes) {
                sb.append(mode.toString());
                sb.append("\n");
            }
            return new FailResult("Prove statement yields multiple system modes: " + sb, proveStatement);
        }
    }
}
