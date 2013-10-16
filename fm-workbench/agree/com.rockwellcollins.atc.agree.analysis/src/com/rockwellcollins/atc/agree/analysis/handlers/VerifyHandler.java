package com.rockwellcollins.atc.agree.analysis.handlers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jkind.api.JKindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.Renaming;
import jkind.lustre.Node;
import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PartInitException;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeAnnexEmitter;
import com.rockwellcollins.atc.agree.analysis.AgreeEmitter;
import com.rockwellcollins.atc.agree.analysis.AgreeEmitterUtilities;
import com.rockwellcollins.atc.agree.analysis.AgreeGenerator;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;

public abstract class VerifyHandler extends AadlHandler {
    private AgreeResultsLinker linker = new AgreeResultsLinker();
    private Queue<JKindResult> queue = new ArrayDeque<>();

    protected abstract boolean isRecursive();

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        if (!(root instanceof SystemImplementation)) {
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Must select an AADL System Implementation");
        }

        try {
            ComponentImplementation ci = (ComponentImplementation) root;
            
            SystemInstance si = null;
            if (root instanceof SystemImplementation) {
                final SystemImplementation sysimpl = (SystemImplementation) root;
                try {
                    si = InstantiateModel.buildInstanceModelFile(sysimpl);
                } catch (Exception e) {
                    Dialog.showError("Model Instantiate", "Error while re-instantiating the model: "
                            + e.getMessage());
                    return Status.CANCEL_STATUS;
                }
            }
            
            AnalysisResult result;
            CompositeAnalysisResult wrapper = new CompositeAnalysisResult("");
            LinkedList<NamedElement> modelParents = new LinkedList<>();
            
            if (isRecursive()) {
                result = buildAnalysisResult(ci.getName(), si, modelParents, null);
                wrapper.addChild(result);
                result = wrapper;
            } else {
                wrapper.addChild(createConsistVerification(si, modelParents, null));
                wrapper.addChild(createAssumptionVerification(si, modelParents, null));
                wrapper.addChild(createGuaranteeVerification(si, modelParents, null));
                result = wrapper;
            }
            showView(result, linker);
            return doAnalysis(monitor);
        } catch (Throwable e) {
            String messages = getNestedMessages(e);
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
        }
    }

    private String getNestedMessages(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        while (e != null) {
            if (e.getMessage() != null && !e.getMessage().isEmpty()) {
                pw.println(e.getMessage());
            }
            e = e.getCause();
        }
        pw.close();
        return sw.toString();
    }

    private AnalysisResult buildAnalysisResult(String name, ComponentInstance ci,
            LinkedList<NamedElement> modelParents, Subcomponent context) {
        CompositeAnalysisResult result = new CompositeAnalysisResult("Verification for " + name);
        
        result.addChild(createGuaranteeVerification(ci, modelParents, context));
        result.addChild(createAssumptionVerification(ci, modelParents, context));
        result.addChild(createConsistVerification(ci, modelParents, context));
        
        modelParents.push(ci);
        
        for (ComponentInstance sub : ci.getAllComponentInstances()) {
            if(sub != ci){
                if(AgreeEmitterUtilities.getInstanceImplementation(sub) != null){
                    result.addChild(buildAnalysisResult(sub.getName(), sub, modelParents, null));
                }
            }
        }
        linker.setComponent(result, ci.getContainingComponentImpl());

        return result;
    }

    private AnalysisResult createGuaranteeVerification(ComponentInstance ci,
            List<NamedElement> modelParents, Subcomponent context) {
        //AgreeEmitter emitter = new AgreeEmitter(ci, modelParents, context); 
        //Program program = emitter.evaluate();
        
        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate();


        List<String> properties = emitter.getGuarProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(oldNode.location, 
                oldNode.id,
                oldNode.inputs, 
                oldNode.outputs, 
                oldNode.locals,
                oldNode.equations,
                properties,
                oldNode.assertions);
        
        
        List<Node> nodes = new ArrayList<>();
        for(Node node : program.nodes){
            if(node != oldNode)
                nodes.add(node);
        }
        nodes.add(newNode);
        program = new Program(nodes);
        Renaming renaming = emitter.getRenaming();
        JKindResult result = new JKindResult("Contract Guarantees", properties, renaming);
        queue.add(result);

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(ci);
        linker.setProgram(result, program);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
        linker.setLayout(result, emitter.getLayout());
        linker.setReferenceMap(result, emitter.getReferenceMap());
        linker.setLog(result, emitter.getLog());

        return result;
    }
    
    private AnalysisResult createAssumptionVerification(ComponentInstance ci,
            List<NamedElement> modelParents, Subcomponent context) {
        //AgreeEmitter emitter = new AgreeEmitter(ci, modelParents, context);
        //Program program = emitter.evaluate();

        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate();

        List<String> properties = emitter.getAssumeProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(oldNode.location, 
                oldNode.id,
                oldNode.inputs, 
                oldNode.outputs, 
                oldNode.locals,
                oldNode.equations,
                properties,
                oldNode.assertions);
        
        List<Node> nodes = new ArrayList<>();
        for(Node node : program.nodes){
            if(node != oldNode)
                nodes.add(node);
        }
        nodes.add(newNode);
        program = new Program(nodes);
        Renaming renaming = emitter.getRenaming();
        JKindResult result = new JKindResult("Contract Assumptions", properties, renaming);
        queue.add(result);

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(ci);
        linker.setProgram(result, program);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
        linker.setLayout(result, emitter.getLayout());
        linker.setReferenceMap(result, emitter.getReferenceMap());
        linker.setLog(result, emitter.getLog());

        return result;
    }

    private AnalysisResult createConsistVerification(ComponentInstance ci,
            List<NamedElement> modelParents, Subcomponent context) {
        //AgreeEmitter emitter = new AgreeEmitter(ci, modelParents, context);
        //Program program = emitter.evaluate();
        
        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate();


        List<String> properties = emitter.getConsistProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(oldNode.location, 
                oldNode.id,
                oldNode.inputs, 
                oldNode.outputs, 
                oldNode.locals,
                oldNode.equations,
                properties,
                oldNode.assertions);
        
        List<Node> nodes = new ArrayList<>();
        for(Node node : program.nodes){
            if(node != oldNode)
                nodes.add(node);
        }
        nodes.add(newNode);
        program = new Program(nodes);
        List<Boolean> reverseStatus = new ArrayList<>();
        for(int i = 0; i < properties.size(); i ++){
            reverseStatus.add(true);
        }
       
        Renaming renaming = emitter.getRenaming();
        JKindResult result = new JKindResult("Contract Consistancy", properties, reverseStatus, renaming);
        queue.add(result);

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(ci);
        linker.setProgram(result, program);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
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
            //System.out.println("whatever");
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
        if (prefs.getBoolean(PreferenceConstants.PREF_BLAME_CEX)) {
            api.setIntervalGeneralization();
        }
        api.setN(prefs.getInt(PreferenceConstants.PREF_DEPTH));
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
        return api;
    }
    
}