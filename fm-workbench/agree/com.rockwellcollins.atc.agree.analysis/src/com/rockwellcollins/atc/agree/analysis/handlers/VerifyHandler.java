package com.rockwellcollins.atc.agree.analysis.handlers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import jkind.JKindException;
import jkind.api.JRealizabilityApi;
import jkind.api.KindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.JRealizabilityResult;
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
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.SystemType;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeEmitterUtilities;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeGenerator;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ConsistencyResult;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferencesUtil;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;

public abstract class VerifyHandler extends AadlHandler {
    protected AgreeResultsLinker linker = new AgreeResultsLinker();
    protected Queue<JKindResult> queue = new ArrayDeque<>();
       
    private static final String RERUN_ID = "com.rockwellcollins.atc.agree.analysis.commands.rerunAgree";
    private IHandlerActivation rerunActivation;
    private IHandlerActivation terminateActivation;
    private IHandlerService handlerService;
   
    protected abstract boolean isRecursive();
    protected abstract boolean isMonolithic();
    protected abstract boolean isRealizability();

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        disableRerunHandler();
        handlerService = (IHandlerService) getWindow()
                .getService(IHandlerService.class);

        if (!(root instanceof SystemImplementation)) {
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Must select an AADL System Implementation");
        }

        try {
        	SystemImplementation ci = (SystemImplementation) root;

        	SystemInstance si = null;
        	final SystemImplementation sysimpl = ci;
        	try {
        		si = InstantiateModel.buildInstanceModelFile(sysimpl);
        	} catch (Exception e) {
        		Dialog.showError("Model Instantiate",
        				"Error while re-instantiating the model: " + e.getMessage());
        		return Status.CANCEL_STATUS;
        	}

            AnalysisResult result;
            CompositeAnalysisResult wrapper = new CompositeAnalysisResult("");

//            SystemType sysType = si.getSystemImplementation().getType();
            SystemType sysType = (SystemType) si.getSystemImplementation().getType();
            EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType,
                    AgreePackage.eINSTANCE.getAgreeContractSubclause());

            if (annexSubClauses.size() == 0) {
                throw new AgreeException("There is not an AGREE annex in the '" + sysType.getName()
                        + "' system type.");
            }

            if (isRecursive()) {
                result = buildAnalysisResult(ci.getName(), si);
                wrapper.addChild(result);
                result = wrapper;
            }else if (isRealizability()){
                AgreeProgram agreeProgram = AgreeGenerator.getRealizabilityLustre(si);
                result = createRealizabilityVerification(agreeProgram);
                //result = wrapper;
            } else {
            	AgreeProgram agreeProgram = AgreeGenerator.getLustre(si, isMonolithic());//, isMonolithic());
                wrapper.addChild(createGuaranteeVerification(agreeProgram));
                //wrapper.addChild(createAssumptionVerification(si));
                wrapper.addChild(creatConsistencyVerification(agreeProgram));
                result = wrapper;
            }
            showView(result, linker);
            return doAnalysis(root, monitor);
        } catch (Throwable e) {
            String messages = getNestedMessages(e);
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
        }
    }

    private AnalysisResult createRealizabilityVerification(
            AgreeProgram agreeProgram) {
        List<String> props = new ArrayList<>();
        props.addAll(agreeProgram.state.guarProps);
        
        JRealizabilityResult result = new JRealizabilityResult("Realizability Analysis", agreeProgram.state.renaming);
        queue.add(result);

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(agreeProgram.state.curInst);
        linker.setProgram(result, agreeProgram.realizeProgram);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
        linker.setLayout(result, agreeProgram.state.layout);
        linker.setReferenceMap(result, agreeProgram.state.refMap);
        linker.setLog(result, AgreeLogger.getLog());

        return result;
    }
    protected String getNestedMessages(Throwable e) {
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
        
        if(containsAGREEAnnex(ci)){
        	AgreeProgram agreeProgram = AgreeGenerator.getLustre(ci, isMonolithic());//, isMonolithic());
        	AnalysisResult tempResult = createGuaranteeVerification(agreeProgram);
        	if (tempResult != null) {
        		result.addChild(tempResult);
        	}
        	tempResult = creatConsistencyVerification(agreeProgram);
        	if (tempResult != null) {
        		result.addChild(tempResult);
        	}

        	ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(ci);
        	for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
        		ComponentInstance subInst = ci.findSubcomponentInstance(subComp);
        		if (subInst != ci) {
        			if (AgreeEmitterUtilities.getInstanceImplementation(subInst) != null) {
        				AnalysisResult buildAnalysisResult = buildAnalysisResult(subInst.getName(),
        						subInst);
        				if (buildAnalysisResult != null) {
        					result.addChild(buildAnalysisResult);
        				}
        			}
        		}
        	}

        	if (result.getChildren().size() != 0) {
        		linker.setComponent(result, compImpl);
        		return result;
        	}
        }
        return null;
    }

    private boolean containsAGREEAnnex(ComponentInstance ci) {
		ComponentClassifier compClass = ci.getComponentClassifier();
		if(compClass instanceof ComponentImplementation){
			compClass = ((ComponentImplementation)compClass).getType();
		}
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(compClass, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
            	return true;
            }
		}
		return false;
	}
    
	private AnalysisResult createGuaranteeVerification(AgreeProgram agreeProgram) {

    	List<String> props = new ArrayList<>();
    	props.addAll(agreeProgram.state.guarProps);
    	
        JKindResult result = new JKindResult("Contract Guarantees", props, agreeProgram.state.renaming);
        queue.add(result);

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(agreeProgram.state.curInst);
        linker.setProgram(result, agreeProgram.assumeGuaranteeProgram);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
        linker.setLayout(result, agreeProgram.state.layout);
        linker.setReferenceMap(result, agreeProgram.state.refMap);
        linker.setLog(result, AgreeLogger.getLog());

        return result;
    }
    
    private AnalysisResult creatConsistencyVerification(AgreeProgram agreeProgram) {
    	
    	IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
    	int consistDetph = prefs.getInt(PreferenceConstants.PREF_CONSIST_DEPTH);
    	
        JKindResult result = new ConsistencyResult("Contract Consistency", agreeProgram.state.consistProps, agreeProgram.state.renaming, consistDetph);
        queue.add(result);

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(agreeProgram.state.curInst);
        linker.setProgram(result, agreeProgram.consistProgram);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
        linker.setLayout(result, agreeProgram.state.layout);
        linker.setReferenceMap(result, agreeProgram.state.refMap);
        linker.setLog(result, AgreeLogger.getLog());

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

    private IStatus doAnalysis(final Element root, final IProgressMonitor monitor) {
    	    	
    	Thread analysisThread = new Thread(){
    		public void run(){
    			
                activateTerminateHandler(monitor);
    			KindApi api = PreferencesUtil.getKindApi();
    			KindApi consistApi = PreferencesUtil.getConsistencyApi();
    			JRealizabilityApi realApi = PreferencesUtil.getJRealizabilityApi();
    			
    			while (!queue.isEmpty() && !monitor.isCanceled()) {
    			    JKindResult result = queue.peek();
//    				JKindResult result = queue.remove();
    				Program program = linker.getProgram(result);
    				try {
    					if(result instanceof ConsistencyResult){
    						consistApi.execute(program, result, monitor);
    					}else if (result instanceof JRealizabilityResult){
    					    realApi.execute(program, (JRealizabilityResult) result, monitor);
    					}else{
    						api.execute(program, result, monitor);
    					}
    				} catch (JKindException e) {
    					System.out.println(result.getText());
    					System.out.println("******** HERE IS THE LUSTRE ********");
    					System.out.println(program);
    					break;
    				}
    				queue.remove();
    			}

    			while (!queue.isEmpty()) {
    				queue.remove().cancel();
    			}

    			deactivateTerminateHandler();
    			enableRerunHandler(root);
    			
    		}
    	};
    	analysisThread.start();
        return Status.OK_STATUS;
    }

    private void activateTerminateHandler(final IProgressMonitor monitor) {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                terminateActivation = handlerService.activateHandler(TERMINATE_ID,
                        new TerminateHandler(monitor, queue));
            }
        });
    }
    
    private void deactivateTerminateHandler() {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                handlerService.deactivateHandler(terminateActivation);
            }
        });
    }
    
    private void enableRerunHandler(final Element root) {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                IHandlerService handlerService = getHandlerService();
                rerunActivation = handlerService.activateHandler(RERUN_ID, new RerunHandler(root,
                        VerifyHandler.this));
            }
        });
    }

    protected void disableRerunHandler() {
        if (rerunActivation != null) {
            getWindow().getShell().getDisplay().syncExec(new Runnable() {
                @Override
                public void run() {
                    IHandlerService handlerService = getHandlerService();
                    handlerService.deactivateHandler(rerunActivation);
                    rerunActivation = null;
                }
            });
        }
    }

    private IHandlerService getHandlerService() {
        return (IHandlerService) getWindow().getService(IHandlerService.class);
    }
}