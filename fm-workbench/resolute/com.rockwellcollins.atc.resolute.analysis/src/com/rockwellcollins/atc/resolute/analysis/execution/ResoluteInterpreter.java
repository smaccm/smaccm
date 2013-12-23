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
        ResoluteResult subResult = evaluateProveStatementBody(proveStatement,
                getAllModeInstances(proveStatement));
        Map<String, EObject> references = Collections.emptyMap();
        if (proveStatement.getModes().size() == 1) {
            String mode = NodeModelUtils.getNode(proveStatement.getModes().get(0)).getText();
            proveText += " (mode: " + mode + ")";
        }
        return new ClaimResult(proveText, subResult, references, proveStatement);
    }

    private List<SystemOperationMode> getAllModeInstances(ProveStatement proveStatement) {
        List<NestedDotID> nestModesIds = proveStatement.getModes();
        if (nestModesIds == null || nestModesIds.isEmpty()) {
            return Collections.emptyList();
        }

        SystemInstance sysInst = (SystemInstance) compInst.getElementRoot();
        return sysInst.getSystemOperationModesFor(getCompInstSubModes(nestModesIds));
    }

    private List<ModeInstance> getCompInstSubModes(List<NestedDotID> ids) {
        List<ModeInstance> result = new ArrayList<>();
        for (NestedDotID id : ids) {
            result.add(getCompInstSubMode(compInst, id));
        }
        return result;
    }

    private ModeInstance getCompInstSubMode(ComponentInstance compInst, NestedDotID modeId) {
        if (modeId.getSub() != null) {
            Subcomponent subComp = (Subcomponent) modeId.getBase();

            for (ComponentInstance subCompInst : compInst.getAllComponentInstances()) {
                if (subCompInst.getSubcomponent().equals(subComp)) {
                    return getCompInstSubMode(subCompInst, modeId.getSub());
                }
            }
            throw new IllegalArgumentException("Failed to find subcomponent instance: " + subComp.getName());
        }

        Mode thisMode = (Mode) modeId.getBase();
        for (ModeInstance modeInst : compInst.getModeInstances()) {
            if (modeInst.getMode().equals(thisMode)) {
                return modeInst;
            }
        }
        throw new IllegalArgumentException("Failed to find mode instance: " + thisMode.getName());
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
            return new FailResult("Prove statement yields multiple system modes: " + sb,
                    proveStatement);
        }
    }
}
