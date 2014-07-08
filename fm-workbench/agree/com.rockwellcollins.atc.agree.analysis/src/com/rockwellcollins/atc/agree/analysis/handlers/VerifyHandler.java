package com.rockwellcollins.atc.agree.analysis.handlers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jkind.JKindException;
import jkind.SolverOption;
import jkind.api.JKindApi;
import jkind.api.JRealizabilityApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.JKindResultRealizability;
import jkind.api.results.Renaming;
import jkind.lustre.Node;
import jkind.lustre.Program;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.SystemType;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeEmitterUtilities;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeGenerator;
import com.rockwellcollins.atc.agree.analysis.FeatureToConnectionsMap;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;

public abstract class VerifyHandler extends AadlHandler {
    private AgreeResultsLinker linker = new AgreeResultsLinker();
    private Queue<AnalysisResult> queue = new ArrayDeque<>();
    
    private static final String RERUN_ID = "com.rockwellcollins.atc.agree.analysis.commands.rerunAgree";
    private IHandlerActivation rerunActivation;

    protected abstract boolean isRecursive();

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        disableRerunHandler();
        
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
            
            SystemType sysType = si.getSystemImplementation().getType();
            EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType, AgreePackage.eINSTANCE.getAgreeContractSubclause());
            
            if(annexSubClauses.size() == 0){
            	throw new AgreeException("There is not an AGREE annex in the '"+sysType.getName()+"' system type.");
            }
            
            if (isRecursive()) {
                result = buildAnalysisResult(ci.getName(), si);
                wrapper.addChild(result);
                result = wrapper;
            } else {
            	//wrapper.addChild(createRealizabilityVerification(si));
                wrapper.addChild(createGuaranteeVerification(si));
                wrapper.addChild(createAssumptionVerification(si));
                wrapper.addChild(createConsistVerification(si));
                result = wrapper;
            }
            showView(result, linker);
            return doAnalysis(root, monitor);
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

    private AnalysisResult buildAnalysisResult(String name, ComponentInstance ci) {
        CompositeAnalysisResult result = new CompositeAnalysisResult("Verification for " + name);
        
        AnalysisResult tempResult = createGuaranteeVerification(ci);
        if(tempResult != null){
        	result.addChild(tempResult);
        }
        tempResult = createAssumptionVerification(ci);
        if(tempResult != null){
        	result.addChild(tempResult);
        }
        tempResult = createConsistVerification(ci);
        if(tempResult != null){
        	result.addChild(tempResult);
        }

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(ci);
        for(Subcomponent subComp : compImpl.getAllSubcomponents()){
            ComponentInstance subInst = ci.findSubcomponentInstance(subComp);
            if(subInst != ci){
                if(AgreeEmitterUtilities.getInstanceImplementation(subInst) != null){
                	AnalysisResult buildAnalysisResult = buildAnalysisResult(subInst.getName(), subInst);
                	if(buildAnalysisResult != null){
                		result.addChild(buildAnalysisResult);
                	}
                }
            }
        }
        
        if(result.getChildren().size() != 0){
        	linker.setComponent(result, compImpl);
        	return result;
        }

        return null;
    }
    
    private AnalysisResult createRealizabilityVerification(ComponentInstance ci) {
        
        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate();
        if(program == null){
        	return null;
        }


        List<String> properties = emitter.getGuarProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(//oldNode.location, 
                oldNode.id,
                oldNode.inputs, 
                oldNode.outputs, 
                oldNode.locals,
                oldNode.equations,
                Collections.EMPTY_LIST,
                oldNode.assertions,
                oldNode.realizabilities);
        
        
        List<Node> nodes = new ArrayList<>();
        for(Node node : program.nodes){
            if(node != oldNode)
                nodes.add(node);
        }
        nodes.add(newNode);
        program = new Program(program.types, program.constants, nodes);
        Renaming renaming = emitter.getRenaming();
        JKindResultRealizability result = new JKindResultRealizability("Contract Realizabilities", oldNode.realizabilities, renaming);
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

    private AnalysisResult createGuaranteeVerification(ComponentInstance ci) {
        
        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate();
        if(program == null){
        	return null;
        }


        List<String> properties = emitter.getGuarProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(//oldNode.location, 
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
        program = new Program(program.types, program.constants, nodes);
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
    
    private AnalysisResult createAssumptionVerification(ComponentInstance ci) {
        //AgreeEmitter emitter = new AgreeEmitter(ci, modelParents, context);
        //Program program = emitter.evaluate();

        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate();
        if(program == null){
        	return null;
        }
        
        List<String> properties = emitter.getAssumeProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(//oldNode.location, 
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
        program = new Program(program.types, program.constants, nodes);
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

    private AnalysisResult createConsistVerification(ComponentInstance ci) {
        //AgreeEmitter emitter = new AgreeEmitter(ci, modelParents, context);
        //Program program = emitter.evaluate();
        
        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate();
        if(program == null){
        	return null;
        }

        List<String> properties = emitter.getConsistProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(//oldNode.location, 
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
        program = new Program(program.types, program.constants, nodes);
        List<Boolean> reverseStatus = new ArrayList<>();
        for(int i = 0; i < properties.size(); i ++){
            reverseStatus.add(true);
        }
       
        Renaming renaming = emitter.getRenaming();
        JKindResult result = new JKindResult("Contract Consistency", properties, reverseStatus, renaming);
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
    
    protected void clearView() {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    AgreeResultsView page = (AgreeResultsView) getWindow().getActivePage()
                            .showView(AgreeResultsView.ID);
                    page.setInput(new CompositeAnalysisResult("empty"), null);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private IStatus doAnalysis(Element root, IProgressMonitor monitor) {
    	JKindApi japi = getJKindApi();
        JRealizabilityApi rapi = getJRealizabilityApi();
        while (!queue.isEmpty() && !monitor.isCanceled()) {
            AnalysisResult result = queue.remove();
            Program program = linker.getProgram(result);
            try{
            	if(result instanceof JKindResult){
            	  japi.execute(program, (JKindResult)result, monitor);
            	}else if(result instanceof JKindResultRealizability){
              	  rapi.execute(program, (JKindResultRealizability)result, monitor);
            	}
            		
            } catch (JKindException e){
            	if(result instanceof JKindResult){
            		System.out.println(((JKindResult)result).getText());
              	}else if(result instanceof JKindResultRealizability){
              		System.out.println(((JKindResultRealizability)result).getText());
              	}
            	System.out.println("******** HERE IS THE LUSTRE ********");
            	System.out.println(program);
            	break;
            }
            //System.out.println("whatever");
        }

        while (!queue.isEmpty()) {
        	AnalysisResult remove = queue.remove();
        	if(remove instanceof JKindResult){
        		((JKindResult)remove).cancel();;
          	}else if(remove instanceof JKindResultRealizability){
          		((JKindResultRealizability)remove).cancel();
          	}
        }

        enableRerunHandler(root);
        return monitor.isCanceled() ? Status.CANCEL_STATUS : Status.OK_STATUS;
    }

    private void enableRerunHandler(Element root) {
        IHandlerService handlerService = getHandlerService();
        rerunActivation = handlerService.activateHandler(RERUN_ID, new RerunHandler(root, this));
    }

    private void disableRerunHandler() {
        if (rerunActivation != null) {
            IHandlerService handlerService = getHandlerService();
            handlerService.deactivateHandler(rerunActivation);
            rerunActivation = null;
        }
    }

    private IHandlerService getHandlerService() {
        return (IHandlerService) getWindow().getService(IHandlerService.class);
    }

    private JRealizabilityApi getJRealizabilityApi() {
    	JRealizabilityApi api = new JRealizabilityApi();
        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();

           // api.setSolver(SolverOption.Z3);
        
        api.setN(prefs.getInt(PreferenceConstants.PREF_DEPTH));
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
        return api;
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
        
        switch(prefs.getString(PreferenceConstants.PREF_SOLVER)){
        case "YICES":
            api.setSolver(SolverOption.YICES);
            break;
        case "Z3":
            api.setSolver(SolverOption.Z3);
            break;
        case "CVC4":
            api.setSolver(SolverOption.CVC4);
            break;
        }
        
        api.setN(prefs.getInt(PreferenceConstants.PREF_DEPTH));
        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
        return api;
    }
    
}