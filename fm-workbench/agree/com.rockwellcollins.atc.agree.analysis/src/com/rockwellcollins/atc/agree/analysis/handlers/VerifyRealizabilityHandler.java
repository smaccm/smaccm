package com.rockwellcollins.atc.agree.analysis.handlers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jkind.JKindException;
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
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.SystemType;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeEmitterUtilities;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeGenerator;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;

public class VerifyRealizabilityHandler extends VerifyHandler{

	@Override
	protected boolean isRecursive() {
		return false;
	}

	@Override
	protected String getJobName() {
		return "AGREE - Analyze Realizability";
	}
	
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
            
           
            wrapper.addChild(createRealizabilityVerification(si));
            showView(wrapper, linker);
            return doAnalysis(root, monitor);
        } catch (Throwable e) {
            String messages = getNestedMessages(e);
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
        }
    }
	
	 private JRealizabilityApi getJRealizabilityApi() {
	    	JRealizabilityApi api = new JRealizabilityApi();
	        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();

	           // api.setSolver(SolverOption.Z3);
	        
	        api.setN(prefs.getInt(PreferenceConstants.PREF_DEPTH));
	        api.setTimeout(prefs.getInt(PreferenceConstants.PREF_TIMEOUT));
	        return api;
	}
	
    private IStatus doAnalysis(Element root, IProgressMonitor monitor) {
        JRealizabilityApi rapi = getJRealizabilityApi();
        while (!queue.isEmpty() && !monitor.isCanceled()) {
            AnalysisResult result = queue.remove();
            Program program = linker.getProgram(result);
            try{
            	rapi.execute(program, (JKindResultRealizability)result, monitor);
            	System.out.println(((JKindResultRealizability) result).getText());
            	System.out.println("******** HERE IS THE LUSTRE ********");
            	System.out.println(program);
            } catch (JKindException e){
              	System.out.println(((JKindResultRealizability)result).getText());
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
	
    private AnalysisResult createRealizabilityVerification(ComponentInstance ci) {
        
        AgreeGenerator emitter = new AgreeGenerator(ci);
        Program program = emitter.evaluate_realizability();
        if(program == null){
        	return null;
        }

        //List<String> properties = emitter.getGuarProps();
        Node oldNode = program.getMainNode();
        Node newNode = new Node(//oldNode.location, 
                oldNode.id,
                oldNode.inputs, 
                oldNode.outputs, 
                oldNode.locals,
                oldNode.equations,
                oldNode.properties,
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

}
