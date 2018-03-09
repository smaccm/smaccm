package edu.uah.rsesc.aadlsimulator.ui.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.ge.BusinessObjectContext;
import org.osgi.framework.FrameworkUtil;

import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.UIConstants;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.views.VariablesViewPart;

public class ShowInSimulationVariablesViewHandler extends AbstractHandler {
	@Override
	public void setEnabled(final Object evaluationContext) {
		final boolean enabled = calculateEnabled(evaluationContext);
		setBaseEnabled(enabled);
	}

	private boolean calculateEnabled(final Object evaluationContext) {
		final SimulationUIService simulationUiService = EclipseContextFactory
				.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext())
				.get(SimulationUIService.class);
		if (simulationUiService == null) {
			return false;
		}

		final List<BusinessObjectContext> selectedBocs = getSelectedDiagramElementsFromContext(evaluationContext);

		if (simulationUiService.getCurrentState().getEngineState() == null || selectedBocs.size() != 1) {
			return false;
		}

		final Object bo = selectedBocs.get(0).getBusinessObject();

		// Make the command available if there is only one business object selected and it is not the root System instance
		if ((bo instanceof FeatureInstance || bo instanceof ComponentInstance)
				&& bo != ((InstanceObject) bo).getSystemInstance()) {
			final InstanceObject io = (InstanceObject) bo;

			// Compare the references from the diagram and simulation system instances. Only show the command if they match
			final URI diagramSystemInstanceUri = EcoreUtil.getURI(io.getSystemInstance());
			final URI simulationSystemInstanceUri = EcoreUtil
					.getURI(simulationUiService.getCurrentState().getSimulationEngine().getSystemInstance());
			return diagramSystemInstanceUri != null && diagramSystemInstanceUri.equals(simulationSystemInstanceUri);
		}

		return false;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final SimulationUIService simulationUiService = EclipseContextFactory
				.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext())
				.get(SimulationUIService.class);

		final List<BusinessObjectContext> selectedBocs = getBusinessObjectContexts(
				HandlerUtil.getCurrentSelection(event));

		try {
			final SimulationEngineState engineState = simulationUiService.getCurrentState().getEngineState();
			Objects.requireNonNull(engineState, "engineState must not be null");
			final Object stateElement = Objects.requireNonNull(
					engineState.findElement((InstanceObject) selectedBocs.get(0).getBusinessObject()),
					"unable to retrieve state element");

			// Ensure variables view is visible
			final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			final IViewPart variablesViewPart = page.showView(UIConstants.VARIABLES_VIEW_ID);
			if (variablesViewPart instanceof VariablesViewPart) {
				// Scroll to and select the state element
				final VariablesViewPart variablesView = (VariablesViewPart) variablesViewPart;
				variablesView.scrollToAndSelectStateElement(stateElement);
			}
		} catch (final PartInitException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	private static List<BusinessObjectContext> getSelectedDiagramElementsFromContext(final Object evaluationContext) {
		if (!(evaluationContext instanceof IEvaluationContext)) {
			return Collections.emptyList();
		}

		final IEvaluationContext context = (IEvaluationContext) evaluationContext;
		final Object selectionObj = context.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
		if (!(selectionObj instanceof ISelection)) {
			return Collections.emptyList();
		}

		final ISelection selection = (ISelection) selectionObj;
		return getBusinessObjectContexts(selection);
	}

	private static List<BusinessObjectContext> getBusinessObjectContexts(final ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) {
			return Collections.emptyList();
		}

		final IStructuredSelection ss = (IStructuredSelection) selection;
		final List<BusinessObjectContext> bocs = new ArrayList<>(ss.size());
		for (final Object sel : ss.toList()) {
			final BusinessObjectContext boc = Adapters.adapt(sel, BusinessObjectContext.class);
			if (boc == null) {
				return Collections.emptyList();
			}

			bocs.add(boc);
		}

		return bocs;
	}

}
