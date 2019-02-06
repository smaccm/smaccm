package com.rockwellcollins.atc.agree.analysis.handlers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.util.Pair;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ConsistencyResult;
import com.rockwellcollins.atc.agree.analysis.EphemeralImplementationUtil;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomater;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomaterRegistry;
import com.rockwellcollins.atc.agree.analysis.extentions.ExtensionRegistry;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.RenamingVisitor;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferencesUtil;
import com.rockwellcollins.atc.agree.analysis.saving.AgreeFileUtil;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;
import com.rockwellcollins.atc.agree.analysis.translation.LustreContractAstBuilder;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsView;

import jkind.JKindException;
import jkind.api.JKindApi;
import jkind.api.JRealizabilityApi;
import jkind.api.KindApi;
import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.JRealizabilityResult;
import jkind.lustre.Node;
import jkind.lustre.Program;
public abstract class VerifyHandler extends AadlHandler {
	protected AgreeResultsLinker linker = new AgreeResultsLinker();
	protected Queue<JKindResult> queue = new ArrayDeque<>();
	protected AtomicReference<IProgressMonitor> monitorRef = new AtomicReference<>();

	private static final String RERUN_ID = "com.rockwellcollins.atc.agree.analysis.commands.rerunAgree";
	private IHandlerActivation rerunActivation;
	private IHandlerActivation terminateActivation;
	private IHandlerActivation terminateAllActivation;
	private IHandlerService handlerService;
	private Map<String, String> rerunAdviceMap = new HashMap<>();
	private int adviceCount = 0;
	private boolean calledFromRerun = false;

	private enum AnalysisType {
		AssumeGuarantee, Consistency, Realizability
	};

	protected abstract boolean isRecursive();

	protected abstract boolean isMonolithic();

	protected abstract boolean isRealizability();

	protected SystemInstance getSysInstance(ComponentImplementation ci, EphemeralImplementationUtil implUtil) {
		try {
			return InstantiateModel.buildInstanceModelFile(ci);
		} catch (Exception e) {
			Dialog.showError("Model Instantiate", "Error while re-instantiating the model: " + e.getMessage());
			throw new AgreeException("Error Instantiating model");
		}
	}

	private ComponentImplementation getComponentImplementation(Element root, EphemeralImplementationUtil implUtil) {
		Classifier classifier = getOutermostClassifier(root);
		if (isRealizability()) {
			if (!(classifier instanceof ComponentType)) {
				throw new AgreeException("Must select an AADL Component Type");
			}
			ComponentImplementation result;
			try {
				result = implUtil.generateEphemeralCompImplFromType((ComponentType) classifier);
			} catch (Exception e) {
				e.printStackTrace();
				throw new AgreeException("Error creating component implementation.");
			}
			return result;
		} else {
			if (classifier instanceof ComponentImplementation) {
				return (ComponentImplementation) classifier;
			}
			if (!(classifier instanceof ComponentType)) {
				throw new AgreeException("Must select an AADL Component Type or Implementation");
			}
			ComponentType ct = (ComponentType) classifier;
			List<ComponentImplementation> cis = getComponentImplementations(ct);
			if (cis.size() == 0) {
				throw new AgreeException("AADL Component Type has no implementation to verify");
			} else if (cis.size() == 1) {
				ComponentImplementation ci = cis.get(0);
				Shell shell = getWindow().getShell();
				String message = "User selected " + ct.getFullName() + ".\nRunning analysis on " + ci.getFullName()
				+ " instead.";
				shell.getDisplay()
				.asyncExec(() -> MessageDialog.openInformation(shell, "Analysis information", message));
				return ci;
			} else {
				throw new AgreeException(
						"AADL Component Type has multiple implementations to verify: please select just one");
			}
		}
	}

	private List<ComponentImplementation> getComponentImplementations(ComponentType ct) {
		List<ComponentImplementation> result = new ArrayList<>();
		AadlPackage pkg = AadlUtil.getContainingPackage(ct);
		for (ComponentImplementation ci : EcoreUtil2.getAllContentsOfType(pkg, ComponentImplementation.class)) {
			if (ci.getType().equals(ct)) {
				result.add(ci);
			}
		}
		return result;
	}

	@Override
	protected IStatus runJob(Element root, IProgressMonitor monitor) {
		EphemeralImplementationUtil implUtil = new EphemeralImplementationUtil(monitor);
		// this flag is set by the rerun handler to prevent clearing the advice map
		if (!calledFromRerun) {
			rerunAdviceMap.clear();
		}
		calledFromRerun = false;

		disableRerunHandler();
		handlerService = getWindow().getService(IHandlerService.class);

		try {

			// Make sure the user selected a component implementation
			ComponentImplementation ci = getComponentImplementation(root, implUtil);

			SystemInstance si = getSysInstance(ci, implUtil);

			AnalysisResult result;
			CompositeAnalysisResult wrapper = new CompositeAnalysisResult("");

			ComponentType sysType = AgreeUtils.getInstanceType(si);
			EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType,
					AgreePackage.eINSTANCE.getAgreeContractSubclause());

			if (annexSubClauses.size() == 0) {
				throw new AgreeException("There is not an AGREE annex in the '" + sysType.getName() + "' system type.");
			}

			if (isRecursive()) {
				if (AgreeUtils.usingKind2()) {
					throw new AgreeException("Kind2 only supports monolithic verification");
				}
				result = buildAnalysisResult(ci.getName(), si);
				wrapper.addChild(result);
				result = wrapper;
			} else if (isRealizability()) {
				AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si, false);

				Program program = LustreAstBuilder.getRealizabilityLustreProgram(agreeProgram);
				wrapper.addChild(createVerification("Realizability Check", si, program, agreeProgram,
						AnalysisType.Realizability));
				result = wrapper;
			} else {
				CompositeAnalysisResult wrapperTop = new CompositeAnalysisResult(
						"Verification for " + ci.getName());
				wrapVerificationResult(si, wrapperTop);
				wrapper.addChild(wrapperTop);
				result = wrapper;
			}

			showView(result, linker);
			return doAnalysis(ci, monitor);
		} catch (Throwable e) {
			String messages = getNestedMessages(e);
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
		} finally {
			implUtil.cleanup();
		}
	}

	private void wrapVerificationResult(ComponentInstance si, CompositeAnalysisResult wrapper) {
		AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si, isMonolithic());

		// generate different lustre depending on which model checker we are
		// using

		Program program;
		if (AgreeUtils.usingKind2()) {
			if (!isMonolithic()) {
				throw new AgreeException("Kind2 now only supports monolithic verification");
			}
			program = LustreContractAstBuilder.getContractLustreProgram(agreeProgram);
		} else {
			program = LustreAstBuilder.getAssumeGuaranteeLustreProgram(agreeProgram);
		}
		List<Pair<String, Program>> consistencies = LustreAstBuilder.getConsistencyChecks(agreeProgram);

		wrapper.addChild(
				createVerification("Contract Guarantees", si, program, agreeProgram, AnalysisType.AssumeGuarantee));
		for (Pair<String, Program> consistencyAnalysis : consistencies) {
			wrapper.addChild(createVerification(consistencyAnalysis.getFirst(), si, consistencyAnalysis.getSecond(),
					agreeProgram, AnalysisType.Consistency));
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

	private AnalysisResult createVerification(String resultName, ComponentInstance compInst, Program lustreProgram,
			AgreeProgram agreeProgram, AnalysisType analysisType) {

		/////////
//		Logger logger = Logger.getLogger("MyLog");
//		FileHandler fh;
//		try {

		// This block configure the logger with handler and formatter
//			fh = new FileHandler("/home/thomas/zzz.log");
//			logger.addHandler(fh);
//			SimpleFormatter formatter = new SimpleFormatter();
//			fh.setFormatter(formatter);
//			logger.info("Agree Program:");
//			AgreeASTPrettyprinter pp = new AgreeASTPrettyprinter();
//			pp.visit(agreeProgram);
//			logger.info(pp.toString());

//		} catch (SecurityException ex) {
//			ex.printStackTrace();
//		}
//		catch (IOException ex) {
//			ex.printStackTrace();
//		}
		////////////////

		AgreeAutomaterRegistry aAReg = (AgreeAutomaterRegistry) ExtensionRegistry
				.getRegistry(ExtensionRegistry.AGREE_AUTOMATER_EXT_ID);
		List<AgreeAutomater> automaters = aAReg.getAgreeAutomaters();
		AgreeRenaming renaming = new AgreeRenaming();
		AgreeLayout layout = new AgreeLayout();
		Node mainNode = null;
		for (Node node : lustreProgram.nodes) {
			if (node.id.equals(lustreProgram.main)) {
				mainNode = node;
				break;
			}
		}
		if (mainNode == null) {
			throw new AgreeException("Could not find main lustre node after translation");
		}

		List<String> properties = new ArrayList<>();

		RenamingVisitor.addRenamings(lustreProgram, renaming, compInst, layout);
		addProperties(renaming, properties, mainNode, agreeProgram);


		for (AgreeAutomater aa : automaters) {
			renaming = aa.rename(renaming);
			layout = aa.transformLayout(layout);
		}

		JKindResult result;
		switch (analysisType) {
		case Consistency:
			result = new ConsistencyResult(resultName, mainNode.properties, Collections.singletonList(true), renaming);
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
		linker.setRenaming(result, renaming);


		// System.out.println(program);
		return result;

	}

	private void addProperties(AgreeRenaming renaming, List<String> properties, Node mainNode,
			AgreeProgram agreeProgram) {

		// there is a special case in the AgreeRenaming which handles this
		// translation
		if (AgreeUtils.usingKind2()) {
			addKind2Properties(agreeProgram.topNode, properties, renaming, "_TOP", "");
		} else {
			properties.addAll(mainNode.properties);
		}

		Set<String> strs = new HashSet<>();
		for (String prop : properties) {
			String renamed = renaming.rename(prop);
			if (!strs.add(renamed)) {
				throw new AgreeException("Multiple properties named \"" + renamed + "\"");
			}
		}

	}

	void addKind2Properties(AgreeNode agreeNode, List<String> properties, AgreeRenaming renaming, String prefix,
			String userPropPrefix) {
		int i = 0;

		String propPrefix = (userPropPrefix.equals("")) ? "" : userPropPrefix + ": ";
		for (AgreeStatement statement : agreeNode.lemmas) {
			renaming.addExplicitRename(prefix + "[" + (++i) + "]", propPrefix + statement.string);
			properties.add(prefix.replaceAll("\\.", AgreeASTBuilder.dotChar) + "[" + i + "]");
		}
		for (AgreeStatement statement : agreeNode.guarantees) {
			renaming.addExplicitRename(prefix + "[" + (++i) + "]", propPrefix + statement.string);
			properties.add(prefix.replaceAll("\\.", AgreeASTBuilder.dotChar) + "[" + i + "]");
		}

		userPropPrefix = userPropPrefix.equals("") ? "" : userPropPrefix + ".";
		for (AgreeNode subNode : agreeNode.subNodes) {
			addKind2Properties(subNode, properties, renaming, prefix + "." + subNode.id, userPropPrefix + subNode.id);
		}
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
		getWindow().getShell().getDisplay().asyncExec(() -> {
			try {
				AgreeResultsView page = (AgreeResultsView) getWindow().getActivePage()
						.showView(AgreeResultsView.ID);
				page.setInput(result, linker);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		});
	}

	protected void clearView() {
		getWindow().getShell().getDisplay().syncExec(() -> {
			try {
				AgreeResultsView page = (AgreeResultsView) getWindow().getActivePage()
						.showView(AgreeResultsView.ID);
				page.setInput(new CompositeAnalysisResult("empty"), null);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		});
	}

	private IStatus doAnalysis(final Element root, final IProgressMonitor globalMonitor) {

		Thread analysisThread = new Thread() {
			@Override
			public void run() {

				// Record the analysis start time and get model hashcode for
				// saving to property analysis log, if necessary
				String modelHash = "";
				long startTime = 0;
				if (Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.PREF_PROP_LOG)) {
					try {
						modelHash = AgreeFileUtil.getModelHashcode(root);
						startTime = System.currentTimeMillis();
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
				}

				activateTerminateHandlers(globalMonitor);
				KindApi api = PreferencesUtil.getKindApi();
				KindApi consistApi = PreferencesUtil.getConsistencyApi();
				JRealizabilityApi realApi = PreferencesUtil.getJRealizabilityApi();

				while (!queue.isEmpty() && !globalMonitor.isCanceled()) {
					JKindResult result = queue.peek();
					NullProgressMonitor subMonitor = new NullProgressMonitor();
					monitorRef.set(subMonitor);

					Program program = linker.getProgram(result);

					if (api instanceof JKindApi) {
						String resultName = result.getName();
						String adviceFileName = rerunAdviceMap.get(resultName);
						if (adviceFileName == null) {
							adviceFileName = "agree_advice" + adviceCount++;
							rerunAdviceMap.put(resultName, adviceFileName);
						} else {
							((JKindApi) api).setReadAdviceFile(adviceFileName);
						}
						((JKindApi) api).setWriteAdviceFile(adviceFileName);
					}


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


//						System.out.println("******** JKind Output ********");
//						System.out.println(result.getText());
//						System.out.println("******** Agree Lustre ********");
//						System.out.println(program);


						String errStr = e.getMessage();
						int l = Math.min(errStr.length(), 300);
						System.out.println(e.getMessage().substring(0, l));

//=======
//						System.out.println("******** JKind Output ********");
//						System.out.println(result.getText());
//						System.out.println("******** Agree Lustre ********");
//						System.out.println(program);
//
//
//						System.out.println(e.getMessage());
//>>>>>>> origin/develop
						break;
					}

					// Print to property analysis log, if necessary
					if (Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.PREF_PROP_LOG)) {
						AgreeFileUtil.printLog(result, startTime, modelHash);
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
		getWindow().getShell().getDisplay().syncExec(() -> {
			terminateActivation = handlerService.activateHandler(TERMINATE_ID, new TerminateHandler(monitorRef));
			terminateAllActivation = handlerService.activateHandler(TERMINATE_ALL_ID,
					new TerminateHandler(monitorRef, globalMonitor));
		});
	}

	private void deactivateTerminateHandlers() {
		getWindow().getShell().getDisplay().syncExec(() -> {
			handlerService.deactivateHandler(terminateActivation);
			handlerService.deactivateHandler(terminateAllActivation);
		});
	}

	private void enableRerunHandler(final Element root) {

		getWindow().getShell().getDisplay().syncExec(() -> {
			IHandlerService handlerService = getHandlerService();
			rerunActivation = handlerService.activateHandler(RERUN_ID,
					new RerunHandler(root, VerifyHandler.this));
		});
	}

	protected void disableRerunHandler() {
		if (rerunActivation != null) {
			getWindow().getShell().getDisplay().syncExec(() -> {
				IHandlerService handlerService = getHandlerService();
				handlerService.deactivateHandler(rerunActivation);
				rerunActivation = null;
			});
		}
	}

	private IHandlerService getHandlerService() {
		return getWindow().getService(IHandlerService.class);
	}

	public void setCalledFromRerun() {
		calledFromRerun = true;
	}

}