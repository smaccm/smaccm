package com.rockwellcollins.atc.agree.analysis.actions;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import jkind.api.JKindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.MapRenaming;
import jkind.api.results.Renaming;
import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;
import org.osate.aadl2.Subcomponent;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.analysis.AgreeEmitter;

public class VerifyAll extends AgreeAction {
    private IProgressMonitor monitor;
    private Queue<WorkItem> queue = new ArrayDeque<>();

    private static class WorkItem {
        private Program program;
        private JKindResult result;

        public WorkItem(Program program, JKindResult result) {
            this.program = program;
            this.result = result;
        }
    }

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        if (!(root instanceof ComponentImplementation)) {
            Dialog.showError("AGREE Error", "Please choose an AADL Component Implementation");
            return Status.CANCEL_STATUS;
        }
        this.monitor = monitor;

        ComponentImplementation ci = (ComponentImplementation) root;
        AnalysisResult result = buildAnalysisResult(ci.getName(), ci, null);
        CompositeAnalysisResult wrapper = new CompositeAnalysisResult("");
        wrapper.addChild(result);
        showView(wrapper, monitor);
        return doAnalysis();
    }

    private IStatus doAnalysis() {
        while (!queue.isEmpty() && !monitor.isCanceled()) {
            WorkItem item = queue.remove();
            new JKindApi().execute(item.program, item.result, monitor);
        }
        return monitor.isCanceled() ? Status.CANCEL_STATUS : Status.OK_STATUS;
    }

    private AnalysisResult buildAnalysisResult(String name, ComponentImplementation ci,
            Subcomponent context) {
        CompositeAnalysisResult result = new CompositeAnalysisResult("Verification for " + name);

        result.addChild(createContractVerification(ci, context));
        for (Subcomponent sub : ci.getAllSubcomponents()) {
            ComponentImplementation subCi = sub.getComponentImplementation();
            if (subCi != null) {
                result.addChild(buildAnalysisResult(sub.getName(), subCi, sub));
            }
        }

        return result;
    }

    private AnalysisResult createContractVerification(ComponentImplementation ci,
            Subcomponent context) {
        final AgreeEmitter emitter = new AgreeEmitter(ci, context);
        Program program = emitter.evaluate();
        List<String> properties = program.getMainNode().properties;
        Renaming renaming = new MapRenaming(emitter.varRenaming, MapRenaming.Mode.NULL);
        JKindResult result = new JKindResult("Contract", properties, renaming);
        queue.add(new WorkItem(program, result));
        return result;
    }
}
