package com.rockwellcollins.atc.agree.analysis.handlers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.xtext.util.Pair;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.Element;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.FeatureGroup;
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
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.impl.AssignStatementImpl;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout.SigType;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeSupportRenaming;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ConsistencyResult;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferencesUtil;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;
import com.rockwellcollins.atc.agree.analysis.translation.LustreContractAstBuilder;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;

import jkind.JKindException;
import jkind.api.JRealizabilityApi;
import jkind.api.KindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.JRealizabilityResult;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.VarDecl;

public abstract class VerifyHandler extends AadlHandler {
    protected AgreeResultsLinker linker = new AgreeResultsLinker();
    protected Queue<JKindResult> queue = new ArrayDeque<>();
    protected AtomicReference<IProgressMonitor> monitorRef = new AtomicReference<>();

    private static final String RERUN_ID = "com.rockwellcollins.atc.agree.analysis.commands.rerunAgree";
    private IHandlerActivation rerunActivation;
    private IHandlerActivation terminateActivation;
    private IHandlerActivation terminateAllActivation;
    private IHandlerService handlerService;
    
    protected static final String topPrefix = "_TOP__";

    private enum AnalysisType {
        AssumeGuarantee, Consistency, Realizability
    };

    protected abstract boolean isRecursive();

    protected abstract boolean isMonolithic();

    protected abstract boolean isRealizability();

    @Override
    protected IStatus runJob(Element root, IProgressMonitor monitor) {
        disableRerunHandler();
        handlerService = (IHandlerService) getWindow().getService(IHandlerService.class);

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
            ComponentType sysType = AgreeUtils.getInstanceType(si);
            EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType,
                    AgreePackage.eINSTANCE.getAgreeContractSubclause());

            if (annexSubClauses.size() == 0) {
                throw new AgreeException(
                        "There is not an AGREE annex in the '" + sysType.getName() + "' system type.");
            }
            
            if (isRecursive()) {
                if(AgreeUtils.usingKind2()){
                    throw new AgreeException("Kind2 only supports monolithic verification");
                }
                result = buildAnalysisResult(ci.getName(), si);
                wrapper.addChild(result);
                result = wrapper;
            } else if (isRealizability()) {
                AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si);
                Program program = LustreAstBuilder.getRealizabilityLustreProgram(agreeProgram);
                wrapper.addChild(
                        createVerification("Realizability Check", si, program, agreeProgram, AnalysisType.Realizability));
                result = wrapper;
            } else {
            	 wrapVerificationResult(si, wrapper);
                result = wrapper;
            }
            showView(result, linker);
            return doAnalysis(root, monitor);
        } catch (Throwable e) {
            String messages = getNestedMessages(e);
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
        }
    }

    private void wrapVerificationResult(ComponentInstance si, CompositeAnalysisResult wrapper) {
        AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si);
 
        // generate different lustre depending on which model checker we are
        // using
      
        Program program;
        if (AgreeUtils.usingKind2()) {
            if(!isMonolithic()){
                throw new AgreeException("Kind2 now only supports monolithic verification");
            }
            program = LustreContractAstBuilder.getContractLustreProgram(agreeProgram);
        } else {
            program = LustreAstBuilder.getAssumeGuaranteeLustreProgram(agreeProgram, isMonolithic());
        }
        List<Pair<String, Program>> consistencies =
                LustreAstBuilder.getConsistencyChecks(agreeProgram, isMonolithic());

        wrapper.addChild(
                createVerification("Contract Guarantees", si, program, agreeProgram, AnalysisType.AssumeGuarantee));
        for (Pair<String, Program> consistencyAnalysis : consistencies) {
            wrapper.addChild(createVerification(consistencyAnalysis.getFirst(), si,
                    consistencyAnalysis.getSecond(), agreeProgram, AnalysisType.Consistency));
        }
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

        if (containsAGREEAnnex(ci)) {
            wrapVerificationResult(ci, result);
            ComponentImplementation compImpl = AgreeUtils.getInstanceImplementation(ci);
            for (ComponentInstance subInst : ci.getComponentInstances()) {
                if (AgreeUtils.getInstanceImplementation(subInst) != null) {
                    AnalysisResult buildAnalysisResult = buildAnalysisResult(subInst.getName(), subInst);
                    if (buildAnalysisResult != null) {
                        result.addChild(buildAnalysisResult);
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
        if (compClass instanceof ComponentImplementation) {
            compClass = ((ComponentImplementation) compClass).getType();
        }
        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(compClass,
                AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                return true;
            }
        }
        return false;
    }

    private AnalysisResult createVerification(String resultName, ComponentInstance compInst, Program lustreProgram, AgreeProgram agreeProgram,
            AnalysisType analysisType) {

        AgreeSupportRenaming renaming = new AgreeSupportRenaming();
        
        AgreeLayout layout = new AgreeLayout();
        Node mainNode = null;
        List<String> properties = new ArrayList<>();
        
        for (Node node : lustreProgram.nodes) {
            if (node.id.equals(lustreProgram.main)) {
                mainNode = node;
            }
            addRenamings(renaming, properties, layout, node, agreeProgram);
        }
        
       addSupportRenamings(renaming, layout, agreeProgram);
        
        if (mainNode == null) {
            throw new AgreeException("Could not find main lustre node after translation");
        }

        JKindResult result;
        switch (analysisType) {
        case Consistency:
            result = new ConsistencyResult(resultName, mainNode.properties, Collections.singletonList(true),
                    renaming);
            break;
        case Realizability:
            result = new JRealizabilityResult(resultName, renaming);
            break;
        case AssumeGuarantee:
            result = new JKindResult(resultName, properties, renaming);
            break;
        default:
            throw new AgreeException("Unhandled Analysis Type");
        }
        queue.add(result);

        ComponentImplementation compImpl = AgreeUtils.getInstanceImplementation(compInst);
        linker.setProgram(result, lustreProgram);
        linker.setComponent(result, compImpl);
        linker.setContract(result, getContract(compImpl));
        linker.setLayout(result, layout);
        linker.setReferenceMap(result, renaming.getRefMap());
        linker.setLog(result, AgreeLogger.getLog());
        return result;
    }
    
    private void addRenamings(AgreeSupportRenaming renaming, List<String> properties, AgreeLayout layout, Node node, AgreeProgram agreeProgram) {
    	
        for (VarDecl var : node.inputs) {
            if (var instanceof AgreeVar) {
                addReference(renaming, layout, var);
                
            }
        }      	
        for (VarDecl var : node.locals) {
            if (var instanceof AgreeVar) {
		        	addReference(renaming, layout, var);
		        	// Anitha added this for support string renaming <componentname.localvarname>
		            if (!AgreeUtils.usingKind2() ){ //&& !isMonolithic()){
		            	addReferenceForSupport(node, renaming, layout, var, agreeProgram);                		
		            }
            }
        }
        
        for (VarDecl var : node.outputs) {
            if (var instanceof AgreeVar) {
            	addReference(renaming, layout, var);
            }
        }
        
        //there is a special case in the AgreeRenaming which handles this translation
        if(AgreeUtils.usingKind2()){
            addKind2Properties(agreeProgram.topNode, properties, renaming, "_TOP", "");
        }else{
            properties.addAll(node.properties);
        }
    }

    private void addSupportRenamings(AgreeSupportRenaming renaming, AgreeLayout layout,
            AgreeProgram agreeProgram) {
    	for (AgreeNode subNode : agreeProgram.agreeNodes) { 
		  		ComponentClassifier compClass = subNode.compInst.getComponentClassifier();
		  		AgreeVar nodeIdVar= new AgreeVar(subNode.id, NamedType.BOOL,null, subNode.compInst);
		  		String componentName = (compClass.getQualifiedName()).substring(0,(compClass.getQualifiedName()).indexOf(':'));
		  		addSpecificReference(renaming, layout, nodeIdVar,componentName);
			}
    }
    
    void addKind2Properties(AgreeNode agreeNode, List<String> properties, AgreeSupportRenaming renaming, String prefix, String userPropPrefix){
        int i = 0;
        
        String propPrefix = (userPropPrefix.equals("")) ? "" : userPropPrefix + ": ";
        for(AgreeStatement statement : agreeNode.lemmas){
            renaming.addExplicitRename(prefix+"["+(++i)+"]", propPrefix + statement.string);
            properties.add(prefix.replaceAll("\\.", AgreeASTBuilder.dotChar)+"["+i+"]");
        }
        for(AgreeStatement statement : agreeNode.guarantees){
            renaming.addExplicitRename(prefix+"["+(++i)+"]", propPrefix + statement.string);
            properties.add(prefix.replaceAll("\\.", AgreeASTBuilder.dotChar)+"["+i+"]");
        }
        
        userPropPrefix = userPropPrefix.equals("") ? "" : userPropPrefix + ".";
        for(AgreeNode subNode : agreeNode.subNodes){
            addKind2Properties(subNode, properties, renaming, prefix+"."+subNode.id, userPropPrefix + subNode.id);
        }
    }    
    
    //Anitha: adding these additional references to rename support elements of form ComponentName.SupportElement 
    private void addReferenceForSupport(Node node, AgreeSupportRenaming renaming, AgreeLayout layout,
           VarDecl var, AgreeProgram agreeProgram) {
    	   String componentName =  node.id; //layout.getCategory(node.id);
    	   String varId=var.id; 	  
    	   String varReference=getReferenceStr((AgreeVar) var);
    	   
		   componentName = layout.getCategory(node.id);
		   varReference=getReferenceStr((AgreeVar) var);
		   if (!componentName.isEmpty() && varId != null && varId.contains(componentName)) {	
			   varId = topPrefix+componentName+"."+varId;
			  // varReference=componentName+"."+varReference;
			   
		   }
		  
		    renaming.addExplicitRename(varId, varReference);	
		    //renaming.addSupportToRefMap(varReference, ((AgreeVar) var).reference);
			renaming.addSupportToRefMap(varId, ((AgreeVar) var).reference);
			
			String category = getCategory((AgreeVar) var);
			if (category != null && !layout.getCategories().contains(category)) {
			    layout.addCategory(category);
			}
			layout.addElement(category, varReference, SigType.INPUT);	
			
		   return;
    }

    private void addReference(AgreeSupportRenaming renaming, AgreeLayout layout, VarDecl var) {
        String refStr = getReferenceStr((AgreeVar) var);
        addSpecificReference(renaming, layout, var, refStr);
    }

    private void addSpecificReference(AgreeSupportRenaming renaming, AgreeLayout layout,
            VarDecl var, String refStr) {
    	
    	renaming.addExplicitRename(var.id, refStr);
        renaming.addToRefMap(var.id, ((AgreeVar) var).reference);
        
        String category = getCategory((AgreeVar) var);
        if (category != null && !layout.getCategories().contains(category)) {
            layout.addCategory(category);
        }
        layout.addElement(category, refStr, SigType.INPUT);
        return;
    }
    
    private String getCategory(AgreeVar var) {
        if (var.compInst == null || var.reference == null) {
            return null;
        }
        return LustreAstBuilder.getRelativeLocation(var.compInst.getInstanceObjectPath());
    }

    private String getReferenceStr(AgreeVar var) {
    	
    	if(var.equals(null)){
    		return null;
    	}
    	
        String prefix = getCategory(var);
        if (prefix == null) {
            return null;
        }
        if (var.id.endsWith(AgreeASTBuilder.clockIDSuffix)) {
            return null;
        }

        String seperator = (prefix == "" ? "" : ".");
        EObject reference = var.reference;
        if (reference instanceof GuaranteeStatement) {
            return prefix + seperator +((GuaranteeStatement) reference).getStr();
        } else if (reference instanceof AssumeStatement) {
        	return prefix + "assumption: " + ((AssumeStatement) reference).getStr();
        	//Anitha: commented this and added the above line
			// return ((AssumeStatement) reference).getStr();
        } else if (reference instanceof LemmaStatement) {
            return prefix + " lemma: " + ((LemmaStatement) reference).getStr();
        } else if (reference instanceof AssertStatement) {
        	String referenceString =  "Assertion";
        	//TODO: Anitha: I am not able to get a reference name for assert ?
        	if (var.id.contains("_TOP__ASSERT__")) {
        		referenceString = referenceString +"__" +var.id.substring(var.id.indexOf("ASSERT__")+8,var.id.length());
        	}else{
        		if (var.id.contains("ASSERT__")) {
            		referenceString = referenceString +"__" +var.id.substring(var.id.indexOf("ASSERT__")+8,var.id.length());
        		}
        	}
        	
        	return (referenceString);
        	//throw new AgreeException("We really didn't expect to see an assert statement here");
        } else if (reference instanceof Arg) {
            return prefix + seperator + ((Arg) reference).getName();
        } else if (reference instanceof DataPort) {
            return prefix + seperator + ((DataPort) reference).getName();
        } else if (reference instanceof EventDataPort) {
            return prefix + seperator + ((EventDataPort) reference).getName()+"._EVENT_";
        } else if (reference instanceof FeatureGroup) {
            return prefix + seperator + ((FeatureGroup) reference).getName();
        } else if (reference instanceof PropertyStatement) {
            return prefix + seperator + ((PropertyStatement) reference).getName();
        } else if (reference instanceof ComponentType || reference instanceof ComponentImplementation) {
            return "Result";
        }
        //Anitha Added this to include reference for equations
        else if (reference instanceof EqStatement) {
        	 EList<Arg> args = ((EqStatement) reference).getLhs();
        	 if (!args.isEmpty() && args.size() >= 0) {
        		 return (prefix + seperator + (args.get(0).getName()));         
        	 } else{
        		 return (prefix + seperator + ((EqStatement) reference).getLhs().toString()); 
        	 }
        } else if (reference instanceof AssignStatementImpl) {
        	String referenceString = "Assignment";
        	//TODO: Anitha: I am not able to get a reference name for assert ?
        	if (var.id.contains("_TOP__ASSERT__")) {
        		referenceString = referenceString +"__" +var.id.substring(var.id.indexOf("ASSERT__")+8,var.id.length());
        	}else{
        		if (var.id.contains("ASSERT__")) {
            		referenceString = referenceString +"__" +var.id.substring(var.id.indexOf("ASSERT__")+8,var.id.length());
        		}
        	}
        	return (referenceString);
       }
       throw new AgreeException("Unhandled reference type: '" + reference.getClass().getName() + "'");
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
		/*
		 * This command is executed while the xtext document is locked. Thus it must be async
		 * otherwise we can get a deadlock condition if the UI tries to lock the document,
		 * e.g., to pull up hover information.
		 */
        getWindow().getShell().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    AgreeResultsView page =
                            (AgreeResultsView) getWindow().getActivePage().showView(AgreeResultsView.ID);
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
                    AgreeResultsView page =
                            (AgreeResultsView) getWindow().getActivePage().showView(AgreeResultsView.ID);
                    page.setInput(new CompositeAnalysisResult("empty"), null);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private IStatus doAnalysis(final Element root, final IProgressMonitor globalMonitor) {

        Thread analysisThread = new Thread() {
            public void run() {
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
                        if (result instanceof ConsistencyResult) {
                            consistApi.execute(program, result, subMonitor);
                        } else if (result instanceof JRealizabilityResult) {
                            realApi.execute(program, (JRealizabilityResult) result, subMonitor);
                        } else {
                            api.execute(program, result, subMonitor);
                        }
                    } catch (JKindException e) {
                        System.out.println("******** JKindException Text ********");
                        e.printStackTrace(System.out);
                        System.out.println("******** JKind Output ********");
                        System.out.println(result.getText());
                        System.out.println("******** Agree Lustre ********");
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
                terminateActivation =
                        handlerService.activateHandler(TERMINATE_ID, new TerminateHandler(monitorRef));
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
                rerunActivation =
                        handlerService.activateHandler(RERUN_ID, new RerunHandler(root, VerifyHandler.this));
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