package com.rockwellcollins.atc.resolute.analysis.export;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.xtext.ui.util.ResourceUtil;
import org.osate.aadl2.util.OsateDebug;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.FailResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.DcaseLink001;
import net.dependableos.dcase.Evidence;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;
import net.dependableos.dcase.diagram.part.Messages;

public class Dcase {

	private static Argument model;
	
    public static void tryLoad() throws NoClassDefFoundError {
        // Nothing needed since static initialization of this class already tries to load CertWare
    }

	public static void claimToGSN(ClaimResult claim) {
		OsateDebug.osateDebug("GSN export", "Export to GSN");

		long start = System.currentTimeMillis();
		long stop = System.currentTimeMillis();
		OsateDebug.osateDebug("Evaluation time: " + (stop - start) / 1000.0 + "s");
		model = createInitialModel();

		Goal goal = DcaseFactory.eINSTANCE.createGoal();
		goal.setDesc(claim.getText());
		// goal.setMessage("my message");
		model.getRootBasicNode().add(goal);

		for (ResoluteResult rr : claim.getChildren()) {
			export(goal, rr);
		}

		URI diagramURI;
		URI modelURI;

		diagramURI = getDiagramURI(claim);
		modelURI = getModelURI(claim);
		Resource diagram = createDiagram(model, diagramURI, modelURI);
		System.out.println("diagram = " + diagram);
	}

	private static void export(BasicNode parent, ResoluteResult resoluteResult) {
		Goal subgoal = null;

		/**
		 * If we have a fail result, we map it into a goal
		 */
		if (resoluteResult instanceof FailResult) {
			subgoal = DcaseFactory.eINSTANCE.createGoal();
			FailResult fr = (FailResult) resoluteResult;
			subgoal.setMessage(fr.getText());

		}

		/**
		 * A claimresult is also mapped into a goal
		 */
		if (resoluteResult instanceof ClaimResult) {
			subgoal = DcaseFactory.eINSTANCE.createGoal();
			ClaimResult cr = (ClaimResult) resoluteResult;
			subgoal.setMessage(resoluteResult.toString());
			subgoal.setMessage(cr.getText());
		}

		/**
		 * Here, it means we had either a goal or a fail result. it means that
		 * the ResoluteResult was a variable that was either a ClaimResult or
		 * FailResult, not just a ResoluteResult.
		 */
		if (subgoal != null) {
			model.getRootBasicNode().add(subgoal);

			DcaseLink001 link = DcaseFactory.eINSTANCE.createDcaseLink001();
			link.setTarget(subgoal);
			link.setSource(parent);

			model.getRootBasicLink().add(link);

			for (ResoluteResult rr : resoluteResult.getChildren()) {
				export(subgoal, rr);
			}
		}

		/**
		 * Then, we process the sub results - if we created a new subgoal, we
		 * will associate it with it - otherwise, we keep the parent.
		 */
		for (ResoluteResult rr : resoluteResult.getChildren()) {
			export(subgoal != null ? subgoal : parent, rr);
		}

		/**
		 * Here, if the result has no child, then, we create an evidence
		 * connected to the goal created
		 */
		if (resoluteResult.getChildren().size() == 0) {
			Evidence evidence = DcaseFactory.eINSTANCE.createEvidence();
			DcaseLink001 linkEvidence = DcaseFactory.eINSTANCE.createDcaseLink001();
			linkEvidence.setTarget(evidence);
			linkEvidence.setSource(parent);

			if (resoluteResult.isValid()) {
				evidence.setName("Validated");
			} else {
				evidence.setName("Not validated");
			}

			model.getRootBasicLink().add(linkEvidence);
			model.getRootBasicNode().add(evidence);

		}

	}

	private static Argument createInitialModel() {
		return DcaseFactory.eINSTANCE.createArgument();
	}

	private static URI getModelURI(ClaimResult claim) {
		IFile file = ResourceUtil.getFile(claim.getLocation().eResource());
		IPath path = file.getFullPath();
		return URI.createPlatformResourceURI(path.addFileExtension("dcase_model").toString(), true);
	}

	private static URI getDiagramURI(ClaimResult claim) {
		// EObject obj = cr.getLocation();
		Resource res = claim.getLocation().eResource();
		IFile file = ResourceUtil.getFile(res);
		IPath path = file.getFullPath();
		return URI.createPlatformResourceURI(path.addFileExtension("dcase_diagram").toString(), true);
	}

	public static Resource createDiagram(final Argument arg, URI diagramURI, URI modelURI) {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();

		final Resource diagramResource = editingDomain.getResourceSet().createResource(diagramURI);
		final Resource modelResource = editingDomain.getResourceSet().createResource(modelURI);
		final String diagramName = diagramURI.lastSegment();
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain,
				Messages.DcaseDiagramEditorUtil_CreateDiagramCommandLabel, Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				attachModelToResource(arg, modelResource);

				Diagram diagram = ViewService.createDiagram(arg, ArgumentEditPart.MODEL_ID,
						DcaseDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				if (diagram != null) {
					diagramResource.getContents().add(diagram);
					diagram.setName(diagramName);
					diagram.setElement(arg);
				}

				try {
					modelResource.save(net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil.getSaveOptions());
					diagramResource.save(net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil.getSaveOptions());
				} catch (IOException e) {

					DcaseDiagramEditorPlugin.getInstance().logError("Unable to store model and diagram resources", e); //$NON-NLS-1$
				}
				return CommandResult.newOKCommandResult();
			}
		};
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, null, null);
		} catch (ExecutionException e) {
			DcaseDiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e); //$NON-NLS-1$
		}
		setCharset(WorkspaceSynchronizer.getFile(modelResource));
		setCharset(WorkspaceSynchronizer.getFile(diagramResource));
		return diagramResource;
	}

	public static void setCharset(IFile file) {
		if (file == null) {
			return;
		}
		try {
			file.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
		} catch (CoreException e) {
			DcaseDiagramEditorPlugin.getInstance().logError("Unable to set charset for file " + file.getFullPath(), e); //$NON-NLS-1$
		}
	}

	private static void attachModelToResource(Argument model, Resource resource) {
		resource.getContents().add(model);
	}

}
