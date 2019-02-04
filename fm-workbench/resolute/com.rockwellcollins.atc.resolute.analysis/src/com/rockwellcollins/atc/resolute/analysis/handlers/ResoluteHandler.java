package com.rockwellcollins.atc.resolute.analysis.handlers;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.EndToEndFlowInstance;
import org.osate.aadl2.instance.FlowSpecificationInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.aadl2.util.Aadl2Util;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.FeatureToConnectionsMap;
import com.rockwellcollins.atc.resolute.analysis.execution.NamedElementComparator;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteInterpreter;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.analysis.views.AssuranceCaseView;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.ResoluteFactory;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.validation.BaseType;

public class ResoluteHandler extends AadlHandler {
	private static final String RERUN_ID = "com.rockwellcollins.atc.resolute.analysis.commands.rerunResolute";
	private IHandlerActivation rerunActivation;

	@Override
	protected String getJobName() {
		return "Resolute Analysis";
	}

	private static FunctionDefinition resolveResoluteFunction(EObject context, String resoluteFunctionName) {

		// psNode.setText(resoluteFunctionName);
		// val List<EObject> boundList =
		// resoluteLinkingService.getLinkedObjects(context,
		// ResolutePackage.eINSTANCE.getFnCallExpr_Fn(), psNode);
		// if (boundList.size() > 0) {
		// return boundList.get(0) as FunctionDefinition;
		// }
		EObject res = getNamedElementByType(context, resoluteFunctionName,
				ResolutePackage.eINSTANCE.getFunctionDefinition());

		return ((FunctionDefinition) res);
	}

	private static EObject getNamedElementByType(EObject context, String name, EClass eclass) {

		// This code will only link to objects in the projects visible from the
		// current project
		Iterable<IEObjectDescription> allObjectTypes = Aadl2GlobalScopeUtil.getAllEObjectDescriptions(context,
				eclass);
		String contextProject = context.eResource().getURI().segment(1);
		List<String> visibleProjects = getVisibleProjects(contextProject);

		for (IEObjectDescription eod : allObjectTypes) {
			if (eod.getName().getLastSegment().equalsIgnoreCase(name)) {
				EObject res = eod.getEObjectOrProxy();
				res = EcoreUtil.resolve(res, context.eResource().getResourceSet());
				if (!Aadl2Util.isNull(res)) {
					URI linkUri = res.eResource().getURI();
					String linkProject = linkUri.segment(1);
					if (visibleProjects.contains(linkProject)) {
						return res;
					}
				}
			}
		}

		return null;
	}

	private static List<String> getVisibleProjects(String contextProjectName) {
		List<String> result = new ArrayList<String>();
		result.add(contextProjectName);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject contextProject = root.getProject(URI.decode(contextProjectName));
		if (!contextProject.exists() || !contextProject.isAccessible() || !contextProject.isOpen()) {
			return result;
		}
		try {
			IProjectDescription description = contextProject.getDescription();
			for (IProject referencedProject : description.getReferencedProjects()) {
				result.add(URI.encodeSegment(referencedProject.getName(), false));
			}
		} catch (CoreException ex) {
			System.out.println("CORE EXCEPTION");
			ex.printStackTrace();
		}

		return result;
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

	private Classifier getOutermostClassifier(Element element) {
		List<EObject> containers = new ArrayList<>();
		EObject curr = element;
		while (curr != null) {
			containers.add(curr);
			curr = curr.eContainer();
		}
		Collections.reverse(containers);
		for (EObject container : containers) {
			if (container instanceof Classifier) {
				// System.out.println(container);
				return (Classifier) container;
			}
		}
		return null;
	}

	private ComponentImplementation getComponentImplementation(Element root) {
		Classifier classifier = getOutermostClassifier(root);

		if (classifier instanceof ComponentImplementation) {
			return (ComponentImplementation) classifier;
		}
		if (!(classifier instanceof ComponentType)) {
			Dialog.showError("Model Instantiate", "Must select an AADL Component Type or Implementation");
			return null;
		}
		ComponentType ct = (ComponentType) classifier;
		List<ComponentImplementation> cis = getComponentImplementations(ct);
		if (cis.size() == 0) {
			Dialog.showError("Model Instantiate", "AADL Component Type has no implementation to check");
			return null;
		} else if (cis.size() == 1) {
			ComponentImplementation ci = cis.get(0);
			String message = "User selected " + ct.getFullName() + ". Run checks on " + ci.getFullName() + " instead?";
			if (Dialog.askQuestion("Model Instantiation", message)) {
				return ci;
			} else {
				return null;
			}
		} else {
			Dialog.showError("Model Instantiate",
					"AADL Component Type has multiple implementation to verify: please select just one");
			return null;
		}

	}

	@Override
	protected IStatus runJob(Element root, IProgressMonitor monitor) {
		clearProofs();
		disableRerunHandler();
		String theorem;

		theorem = this.getExecutionEvent().getParameter("com.rockwellcollins.atc.resolute.analysis.theorem");

		long start = System.currentTimeMillis();
		SystemInstance si;
		ComponentImplementation compImpl = getComponentImplementation(root);
		if (compImpl == null) {
			return Status.CANCEL_STATUS;
		} else {
			try {
				si = InstantiateModel.buildInstanceModelFile(compImpl);
			} catch (Exception e) {
				Dialog.showError("Model Instantiate", "Error while re-instantiating the model: " + e.getMessage());
				return Status.CANCEL_STATUS;
			}
		}
		long stop = System.currentTimeMillis();
		System.out.println("Instantiation time: " + (stop - start) / 1000.0 + "s");

		start = System.currentTimeMillis();

		Map<String, SortedSet<NamedElement>> sets = new HashMap<>();
		initializeSets(si, sets);
		FeatureToConnectionsMap featToConnsMap = new FeatureToConnectionsMap(si);

		List<ResoluteResult> proofTrees = new ArrayList<>();

		if (theorem != null) {

			EvaluationContext context = new EvaluationContext(si, sets, featToConnsMap);
			FunctionDefinition functionDefinition = resolveResoluteFunction(si, theorem);

			ResoluteSubclause resoluteSubclause = ResoluteFactory.eINSTANCE.createResoluteSubclause();
			ProveStatement proveStatement;
			FnCallExpr fnCallExpr;

			proveStatement = ResoluteFactory.eINSTANCE.createProveStatement();
			fnCallExpr = ResoluteFactory.eINSTANCE.createFnCallExpr();
			fnCallExpr.getArgs().add(ResoluteFactory.eINSTANCE.createThisExpr());
			fnCallExpr.setFn(functionDefinition);

			// Arg a = ResoluteFactory.eINSTANCE.createArg();
			// com.rockwellcollins.atc.resolute.resolute.BaseType t =
			// ResoluteFactory.eINSTANCE.createBaseType();
			// a.setName("s");
			// t.setType("system");
			// a.setType(t);
			// fnCallExpr.getFn().getArgs().clear();
			// fnCallExpr.getFn().getArgs().add(a);
			proveStatement.setExpr(fnCallExpr);
			resoluteSubclause.getProves().add(proveStatement);
			si.getComponentClassifier().getOwnedAnnexSubclauses().add(resoluteSubclause);
			//
			ResoluteInterpreter interpreter = new ResoluteInterpreter(context);

			for (ProveStatement ps : resoluteSubclause.getProves()) {
				try {
					proofTrees.add(interpreter.evaluateProveStatement(ps));
				} catch (Exception e) {
					handleProveStatementException(ps, e);
				}
				drawProofs(proofTrees);
			}

		} else {
			for (NamedElement el : sets.get("component")) {
				ComponentInstance compInst = (ComponentInstance) el;
				EClass resoluteSubclauseEClass = ResolutePackage.eINSTANCE.getResoluteSubclause();
				for (AnnexSubclause subclause : AnnexUtil.getAllAnnexSubclauses(compInst.getComponentClassifier(),
						resoluteSubclauseEClass)) {

					if (subclause instanceof ResoluteSubclause) {
						ResoluteSubclause resoluteSubclause = (ResoluteSubclause) subclause;
						EvaluationContext context = new EvaluationContext(compInst, sets, featToConnsMap);
						ResoluteInterpreter interpreter = new ResoluteInterpreter(context);
						for (ProveStatement ps : resoluteSubclause.getProves()) {
							try {
								proofTrees.add(interpreter.evaluateProveStatement(ps));
							} catch (Exception e) {
								handleProveStatementException(ps, e);
							}
							drawProofs(proofTrees);
						}
					}
				}
			}
		}

		// else {
		// ResoluteSubclause subclause;
		// ProveStatement proveStatement;
		// FnCallExpr fnCallExpr;
		//
		// subclause = ResoluteFactory.eINSTANCE.createResoluteSubclause();
		// proveStatement = ResoluteFactory.eINSTANCE.createProveStatement();
		// fnCallExpr = ResoluteFactory.eINSTANCE.createFnCallExpr();
		// fnCallExpr.getArgs().add(ResoluteFactory.eINSTANCE.createThisExpr());
		// subclause.s
		// for (IEObjectDescription tmp :
		// EMFIndexRetrieval.getAllEObjectsOfTypeInWorkspace(ResolutePackage.eINSTANCE
		// .getFunctionDefinition())) {
		//
		// if (tmp.getName().getLastSegment().equalsIgnoreCase(theorem)) {
		// EObject eobj = tmp.getEObjectOrProxy();
		//
		// EObject resolved = EcoreUtil.resolve(eobj,
		// root.eResource().getResourceSet());
		//// System.out.println("resolved=" + resolved);
		//
		// FunctionDefinition functionDefinition = (FunctionDefinition)
		// resolved;
		//
		// fnCallExpr.setFn(functionDefinition);
		//
		// }
		// }
		// Arg a = ResoluteFactory.eINSTANCE.createArg();
		// com.rockwellcollins.atc.resolute.resolute.BaseType t =
		// ResoluteFactory.eINSTANCE.createBaseType();
		// a.setName("s");
		// t.setType("system");
		// a.setType(t);
		// fnCallExpr.getFn().getArgs().clear();
		// fnCallExpr.getFn().getArgs().add(a);
		// proveStatement.setExpr(fnCallExpr);
		// subclause.getProves().add(proveStatement);
		//
		// EvaluationContext context = new EvaluationContext((ComponentInstance)
		// si.getComponentInstance(), sets,
		// featToConnsMap);
		// ResoluteInterpreter interpreter = new ResoluteInterpreter(context);
		// for (ProveStatement ps : subclause.getProves()) {
		// try {
		// proofTrees.add(interpreter.evaluateProveStatement(ps));
		// drawProofs(proofTrees);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// }

		stop = System.currentTimeMillis();
		System.out.println("Evaluation time: " + (stop - start) / 1000.0 + "s");

		enableRerunHandler(root);
		System.out.println(EcoreUtil.getURI(root));

		return Status.OK_STATUS;
	}

	private void handleProveStatementException(ProveStatement ps, Exception e) {
		String bodyText = simpleSerializer(ps.getExpr());
		getWindow().getShell().getDisplay().syncExec(() -> {
			MessageDialog.openError(getWindow().getShell(), "Error in prove statement: " + bodyText, e.getMessage());
		});
		e.printStackTrace();
	}

	private String simpleSerializer(EObject e) {
		if (e instanceof FnCallExpr) {
			FnCallExpr fce = (FnCallExpr) e;
			String args = fce.getArgs().stream().map(this::simpleSerializer).collect(joining(", "));
			return fce.getFn().getName() + "(" + args + ")";
		} else if (e instanceof ThisExpr) {
			return "this";
		} else if (e instanceof IdExpr) {
			IdExpr ide = (IdExpr) e;
			return ide.getId().getFullName();
		} else {
			return e.toString();
		}
	}

	private void enableRerunHandler(final Element root) {
		getWindow().getShell().getDisplay().syncExec(() -> {
			IHandlerService handlerService = getHandlerService();
			rerunActivation = handlerService.activateHandler(RERUN_ID,
					new RerunHandler(root, ResoluteHandler.this));
		});
	}

	private void disableRerunHandler() {
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

	private void initializeSets(ComponentInstance ci, Map<String, SortedSet<NamedElement>> sets) {
		if (ci == null) {
			return;
		}

		addToSet(sets, ci);
		for (InstanceObject io : EcoreUtil2.getAllContentsOfType(ci, InstanceObject.class)) {
			addToSet(sets, io);
		}

		for (FlowSpecificationInstance flowSpec : ci.getFlowSpecifications()) {
			addToSet(sets, "flow_specification", flowSpec);
		}

		for (EndToEndFlowInstance etef : ci.getEndToEndFlows()) {
			addToSet(sets, "end_to_end_flow", etef);
		}
	}

	private void addToSet(Map<String, SortedSet<NamedElement>> sets, InstanceObject io) {
		BaseType type = new BaseType(io);
		for (BaseType superType : type.getAllSuperTypes()) {
			addToSet(sets, superType.name, io);
		}

	}

	private void addToSet(Map<String, SortedSet<NamedElement>> sets, String name, NamedElement ne) {
		SortedSet<NamedElement> set = sets.get(name);
		if (set == null) {
			set = new TreeSet<>(new NamedElementComparator());
			sets.put(name, set);
		}
		set.add(ne);
	}

	private void drawProofs(final List<ResoluteResult> proofTrees) {
		final IWorkbenchPage page = getWindow().getActivePage();

		Display.getDefault().asyncExec(() -> displayView(proofTrees, page));
	}

	private void displayView(final List<ResoluteResult> proofTrees, final IWorkbenchPage page) {
		try {
			AssuranceCaseView view = (AssuranceCaseView) page.showView(AssuranceCaseView.ID);
			view.setProofs(proofTrees);
			view.setFocus();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	protected void clearProofs() {
		drawProofs(Collections.<ResoluteResult>emptyList());
	}
}
