package com.rockwellcollins.atc.agree.analysis.handlers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import jkind.JKindException;
import jkind.api.JRealizabilityApi;
import jkind.api.KindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.JRealizabilityResult;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.VarDecl;
import jkind.results.layout.Layout;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.Element;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.FeatureGroup;
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
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeEmitterUtilities;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeGenerator;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.LustreAstBuilder;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout.SigType;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeAnalysis;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.ConsistencyResult;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferencesUtil;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;
import com.rockwellcollins.atc.agree.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.ast.AgreeVar;

public abstract class VerifyHandler extends AadlHandler {
    protected AgreeResultsLinker linker = new AgreeResultsLinker();
    protected Queue<JKindResult> queue = new ArrayDeque<>();
    protected AtomicReference<IProgressMonitor> monitorRef = new AtomicReference<>();
       
    private static final String RERUN_ID = "com.rockwellcollins.atc.agree.analysis.commands.rerunAgree";
    private IHandlerActivation rerunActivation;
    private IHandlerActivation terminateActivation;
    private IHandlerActivation terminateAllActivation;
    private IHandlerService handlerService;
   
    protected abstract boolean isRecursive();
    protected abstract boolean isMonolithic();
    protected abstract boolean isRealizability();

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        disableRerunHandler();
        handlerService = (IHandlerService) getWindow()
                .getService(IHandlerService.class);

        if (!(root instanceof ComponentImplementation)) {
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Must select an AADL Component Implementation");
        }

        try {
        	ComponentImplementation ci = (ComponentImplementation) root;

        	SystemInstance si = null;
        	try {
        		si = InstantiateModel.buildInstanceModelFile(ci);
        	} catch (Exception e) {
        		Dialog.showError("Model Instantiate",
        				"Error while re-instantiating the model: " + e.getMessage());
        		return Status.CANCEL_STATUS;
        	}

            AnalysisResult result;
            CompositeAnalysisResult wrapper = new CompositeAnalysisResult("");

//            SystemType sysType = si.getSystemImplementation().getType();
            ComponentType sysType = AgreeEmitterUtilities.getInstanceType(si);
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
                AgreeAnalysis agreeProgram = AgreeGenerator.getRealizabilityLustre(si);
                result = createRealizabilityVerification(agreeProgram);
                //result = wrapper;
            } else {
            	AgreeAnalysis agreeProgram = AgreeGenerator.getLustre(si, isMonolithic());//, isMonolithic());
                //wrapper.addChild(createGuaranteeVerification(agreeProgram));
                wrapper.addChild(createGuaranteeVerification(si));
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
            AgreeAnalysis agreeProgram) {
        
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
        	AgreeAnalysis agreeProgram = AgreeGenerator.getLustre(ci, isMonolithic());//, isMonolithic());
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
    
    private AnalysisResult createGuaranteeVerification(ComponentInstance compInst){
        
        AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(compInst);
    	Program program = LustreAstBuilder.getLustreProgram(agreeProgram);
    	
    	Map<String, EObject> refMap = new HashMap<>();
    	AgreeRenaming renaming = new AgreeRenaming(refMap);
    	AgreeLayout layout = new AgreeLayout();
    	Node mainNode = null;
    	for(Node node : program.nodes){
    		if(node.id.equals(program.main)){
    			mainNode = node;
    			break;
    		}
    	}
    	if(mainNode == null){
    		throw new AgreeException("Could not find main lustre node after translation");
    	}
    	
    	for(VarDecl var : mainNode.inputs){
    		if(var instanceof AgreeVar){
    			String refStr = getReferenceStr((AgreeVar)var);
    			//TODO verify which reference should be put here
    			refMap.put(refStr, ((AgreeVar) var).reference);
    			refMap.put(var.id, ((AgreeVar) var).reference);
    			//TODO we could clean up the agree renaming as well
    			renaming.addExplicitRename(var.id, refStr);
    			String category = getCategory((AgreeVar)var);
    			if(!layout.getCategories().contains(category)){
    				layout.addCategory(category);
    			}
    			layout.addElement(category, refStr, SigType.INPUT);
    		}
    	}
    	
    	JKindResult result = new JKindResult("Contract Guarantees", mainNode.properties, renaming);
        queue.add(result);

        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
        linker.setProgram(result, program);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
        linker.setLayout(result, layout);
        linker.setReferenceMap(result, refMap);
        linker.setLog(result, AgreeLogger.getLog());

        return result;
    	
    }
    
	private String getCategory(AgreeVar var) {
		if(var.compInst == null || var.reference == null){
			return null;
		}
		return var.compInst.getInstanceObjectPath();
	}
	private String getReferenceStr(AgreeVar var) {

		String prefix = getCategory(var);
		if(prefix == null){
			return null;
		}
		EObject reference = var.reference;
		if(reference instanceof GuaranteeStatement){
			return prefix + ": "+ ((GuaranteeStatement)reference).getStr();
		}else if(reference instanceof AssumeStatement){
			return prefix + ": "+ ((AssumeStatement)reference).getStr();
		}else if(reference instanceof AssertStatement){
			throw new AgreeException("We really didn't expect to see an assert statement here");
		}else if(reference instanceof Arg){
			return prefix + "." + ((Arg)reference).getName();
		}else if (reference instanceof DataPort){
			return prefix + "." + ((DataPort)reference).getName();
		}else if (reference instanceof EventDataPort){
			return prefix + "." + ((EventDataPort)reference).getName();
		}else if (reference instanceof FeatureGroup){
			return prefix + "." + ((FeatureGroup)reference).getName();
		}
		throw new AgreeException("Unhandled reference type: '"+reference.getClass().getName()+"'");
	}
	private AnalysisResult createGuaranteeVerification(AgreeAnalysis agreeProgram) {

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
    
    private AnalysisResult creatConsistencyVerification(AgreeAnalysis agreeProgram) {
    	
    	IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
    	int consistDetph = prefs.getInt(PreferenceConstants.PREF_CONSIST_DEPTH);
    	
    	List<Boolean> invertStatuses = new ArrayList<>();
    	int i;
    	for(i = 0; i < agreeProgram.state.consistProps.size(); i++){
    		invertStatuses.add(true);
    	}
        JKindResult result = new ConsistencyResult("Contract Consistency", agreeProgram.state.consistProps, invertStatuses, agreeProgram.state.renaming);
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

    private IStatus doAnalysis(final Element root, final IProgressMonitor globalMonitor) {
    	    	
    	Thread analysisThread = new Thread(){
    		public void run(){
                activateTerminateHandlers(globalMonitor);
    			KindApi api = PreferencesUtil.getKindApi();
    			KindApi consistApi = PreferencesUtil.getConsistencyApi();
    			JRealizabilityApi realApi = PreferencesUtil.getJRealizabilityApi();
    			
    			while (!queue.isEmpty() && !globalMonitor.isCanceled()) {
    			    JKindResult result = queue.peek();
    			    NullProgressMonitor subMonitor = new NullProgressMonitor();
    			    monitorRef.set(subMonitor);
    			    
    				Program program = linker.getProgram(result);
    				try {
    					if(result instanceof ConsistencyResult){
    						consistApi.execute(program, result, subMonitor);
    					}else if (result instanceof JRealizabilityResult){
    					    realApi.execute(program, (JRealizabilityResult) result, subMonitor);
    					}else{
    						api.execute(program, result, subMonitor);
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

    			deactivateTerminateHandlers();
    			enableRerunHandler(root);
    			
    		}
    	};
    	analysisThread.start();
        return Status.OK_STATUS;
    }

    private void activateTerminateHandlers(final IProgressMonitor globalMonitor) {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                terminateActivation = handlerService.activateHandler(TERMINATE_ID,
                        new TerminateHandler(monitorRef));
                terminateAllActivation = handlerService.activateHandler(TERMINATE_ALL_ID,
                        new TerminateHandler(monitorRef, globalMonitor));
            }
        });
    }
    
    private void deactivateTerminateHandlers() {
        getWindow().getShell().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                handlerService.deactivateHandler(terminateActivation);
                handlerService.deactivateHandler(terminateAllActivation);
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