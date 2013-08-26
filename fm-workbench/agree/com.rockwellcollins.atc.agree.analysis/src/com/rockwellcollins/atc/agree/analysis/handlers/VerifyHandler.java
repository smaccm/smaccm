package com.rockwellcollins.atc.agree.analysis.handlers;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import jkind.api.JKindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.Renaming;
import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PartInitException;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.Subcomponent;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeEmitter;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;

public abstract class VerifyHandler extends AadlHandler {
    private AgreeResultsLinker linker = new AgreeResultsLinker();
    private Queue<JKindResult> queue = new ArrayDeque<>();

    protected abstract boolean isRecursive();

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        if (!(root instanceof ComponentImplementation)) {
            Dialog.showError("AGREE Error", "Please choose an AADL Component Implementation");
            return Status.CANCEL_STATUS;
        }

        ComponentImplementation ci = (ComponentImplementation) root;
        AnalysisResult result;
        if (isRecursive()) {
            result = buildAnalysisResult(ci.getName(), ci, null);
            CompositeAnalysisResult wrapper = new CompositeAnalysisResult("");
            wrapper.addChild(result);
            result = wrapper;
        } else {
            result = createContractVerification(ci, null);
        }
        showView(result, linker);
        return doAnalysis(monitor);
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
        linker.setComponent(result, ci);

        return result;
    }

    private AnalysisResult createContractVerification(ComponentImplementation ci,
            Subcomponent context) {
        AgreeEmitter emitter = new AgreeEmitter(ci, context);
        Program program = emitter.evaluate();
        List<String> properties = program.getMainNode().properties;
        Renaming renaming = emitter.getRenaming();
        JKindResult result = new JKindResult("Contract", properties, renaming);
        queue.add(result);

        linker.setProgram(result, program);
        linker.setComponent(result, ci);
        linker.setContract(result, getContract(ci));
        linker.setLayout(result, emitter.getLayout());
        linker.setReferenceMap(result, emitter.getReferenceMap());
        linker.setLog(result, emitter.getLog());

        return result;
    }

    private AgreeSubclause getContract(ComponentImplementation ci) {
        ComponentType ct = ci.getOwnedRealization().getImplemented();
        for (AnnexSubclause annex : ct.getOwnedAnnexSubclauses()) {
            if (annex instanceof AgreeSubclause) {
                return (AgreeSubclause) annex;
            }
        }
        return null;
    }

    protected void showView(final AnalysisResult result, final AgreeResultsLinker linker) {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    AgreeResultsView page = (AgreeResultsView) getWindow().getActivePage()
                            .showView(AgreeResultsView.ID);
                    page.setInput(result, linker);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private IStatus doAnalysis(IProgressMonitor monitor) {
        JKindApi api = getJKindApi();
        while (!queue.isEmpty() && !monitor.isCanceled()) {
            JKindResult result = queue.remove();
            Program program = linker.getProgram(result);
            api.execute(program, result, monitor);
        }

        while (!queue.isEmpty()) {
            queue.remove().cancel();
        }

        return monitor.isCanceled() ? Status.CANCEL_STATUS : Status.OK_STATUS;
    }

    private JKindApi getJKindApi() {
        JKindApi api = new JKindApi();
        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
        if (prefs.getBoolean(PreferenceConstants.PREF_INDUCT_CEX)) {
            api.setInductiveCounterexamples();
        }
        if (prefs.getBoolean(PreferenceConstants.PREF_SMOOTH_CEX)) {
            api.setSmoothCounterexamples();
        }
        api.setN(prefs.getInt(PreferenceConstants.PREF_DEPTH));
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
        return api;
    }
}