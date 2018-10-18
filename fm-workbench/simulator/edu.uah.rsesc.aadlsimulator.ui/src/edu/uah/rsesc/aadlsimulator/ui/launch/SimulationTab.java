/*
Copyright (c) 2018, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package edu.uah.rsesc.aadlsimulator.ui.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.osate.aadl2.Aadl2Package;

import com.google.inject.Injector;

import edu.uah.rsesc.aadlsimulator.launch.SimulationLaunchConfigurationAttributes;
import edu.uah.rsesc.aadlsimulator.services.EngineType;
import edu.uah.rsesc.aadlsimulator.services.SimulationService;

public class SimulationTab extends AbstractLaunchConfigurationTab {
	private final SimulationService simulationService;
	private ComboViewer engineTypeCmb;
	private Text projectTxt;
	private Text componentImplementationTxt;

	public SimulationTab(final SimulationService simulationService) {
		this.simulationService = simulationService;
	}

	@Override
	public void createControl(final Composite parent) {
		final Composite root = new Composite(parent, SWT.NONE);
		setControl(root);
		root.setLayout(new GridLayout(3, false));

		// Component Implementation Selection
		final Label projectLbl = new Label(root, SWT.NONE);
		projectLbl.setText("Project:");
		projectTxt = new Text(root, SWT.SINGLE | SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).applyTo(projectTxt);
		projectTxt.addModifyListener(e -> updateLaunchConfigurationDialog());

		final Button projectSelectBtn = createPushButton(root, "Select...", null);
		projectSelectBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				// Show selection dialog
				final ElementListSelectionDialog dlg = new ElementListSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), new LabelProvider() {
					@Override
					public String getText(final Object element) {
						if(element instanceof IProject){
							return ((IProject)element).getName();
						} else {
							return element.toString();
						}
					}
				});

				dlg.setTitle("Project Selection");
				dlg.setMessage("Select the project containing the component implementation to simulate.");
				dlg.setElements(ResourcesPlugin.getWorkspace().getRoot().getProjects());
				if(dlg.open() == Window.CANCEL) {
					return;
				}

				final Object selection = dlg.getFirstResult();
				if(selection instanceof IProject) {
					projectTxt.setText(((IProject)selection).getName());
				}
			}
		});

		final Label componentImplementationLbl = new Label(root, SWT.NONE);
		componentImplementationLbl.setText("Component Implementation:");
		componentImplementationTxt = new Text(root, SWT.SINGLE | SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).applyTo(componentImplementationTxt);
		componentImplementationTxt.addModifyListener(e -> updateLaunchConfigurationDialog());

		final Button componentImplSelectBtn = createPushButton(root, "Select...", null);
		componentImplSelectBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				// Get list of component implementations
				final List<IEObjectDescription> componentImplementationDescriptions = new ArrayList<IEObjectDescription>();
				Injector injector = IResourceServiceProvider.Registry.INSTANCE
						.getResourceServiceProvider(URI.createFileURI("dummy.aadl")).get(Injector.class);
				final ResourceDescriptionsProvider resourceDescriptionsProvider = injector
						.getInstance(ResourceDescriptionsProvider.class);
				final IResourceDescriptions resourceDescriptions = resourceDescriptionsProvider
						.getResourceDescriptions(new XtextResourceSet());
				final IProject selectedProject = getSelectedProject();
				final IPath selectedProjectPath = selectedProject == null ? null : selectedProject.getFullPath();
				if(selectedProjectPath != null) {
					for (IEObjectDescription eobjDesc : resourceDescriptions
							.getExportedObjectsByType(Aadl2Package.eINSTANCE.getComponentImplementation())) {
						final URI objUri = eobjDesc.getEObjectURI();
						final IPath objPath = new Path(objUri.toPlatformString(true));
						if (selectedProjectPath.isPrefixOf(objPath)) {
							componentImplementationDescriptions.add(eobjDesc);
						}
					}
				}

				// Show selection dialog
				final ElementListSelectionDialog dlg = new ElementListSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), new LabelProvider() {
					@Override
					public String getText(final Object element) {
						if(element instanceof IEObjectDescription){
							return getQualifiedName((IEObjectDescription)element);
						} else {
							return element.toString();
						}
					}
				});

				dlg.setTitle("Component Implementation Selection");
				dlg.setMessage("Select a component implementation to simulate.");
				dlg.setElements(componentImplementationDescriptions.toArray());
				if(dlg.open() == Window.CANCEL) {
					return;
				}

				final Object selection = dlg.getFirstResult();
				if(selection instanceof IEObjectDescription) {
					componentImplementationTxt.setText(getQualifiedName((IEObjectDescription) selection));
				}
			}
		});

		// Engine Type Selection
		final Label engineTypeLbl = new Label(root, SWT.NONE);
		engineTypeLbl.setText("Engine Type:");
		engineTypeCmb = new ComboViewer(root, SWT.DROP_DOWN | SWT.READ_ONLY);
		engineTypeCmb.setContentProvider(new ArrayContentProvider());
		engineTypeCmb.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(final Object element) {
				assert(element instanceof EngineType);
				return ((EngineType) element).getName();
			}
		});
		engineTypeCmb.setInput(simulationService.getEngineTypes().toArray());
		engineTypeCmb.addSelectionChangedListener(event -> updateLaunchConfigurationDialog());
	}

	private IProject getSelectedProject() {
		final IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(projectTxt.getText());
		if(member instanceof IProject) {
			return (IProject)member;
		}

		return null;
	}

	private String getQualifiedName(final IEObjectDescription desc) {
		return desc.getName().toString("::");
	}

	@Override
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		try {
			projectTxt.setText(configuration.getAttribute(SimulationLaunchConfigurationAttributes.PROJECT_NAME, ""));
			componentImplementationTxt.setText(configuration.getAttribute(SimulationLaunchConfigurationAttributes.COMPONENT_IMPLEMENTATION_NAME, ""));
			final String engineTypeId = configuration.getAttribute(SimulationLaunchConfigurationAttributes.ENGINE_TYPE_ID, (String)null);
			engineTypeCmb.setSelection(engineTypeId == null ? StructuredSelection.EMPTY : new StructuredSelection(simulationService.getEngineTypeById(engineTypeId)));
		} catch (final CoreException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		// Apply the component implementation selection
		configuration.setAttribute(SimulationLaunchConfigurationAttributes.PROJECT_NAME, projectTxt.getText());
		configuration.setAttribute(SimulationLaunchConfigurationAttributes.COMPONENT_IMPLEMENTATION_NAME, componentImplementationTxt.getText());

		// Apply the engine type selection
		final StructuredSelection engineTypeSelection = (StructuredSelection)engineTypeCmb.getSelection();
		final EngineType selectedEngineType = (EngineType)engineTypeSelection.getFirstElement();
		if(selectedEngineType == null) {
			configuration.removeAttribute(SimulationLaunchConfigurationAttributes.ENGINE_TYPE_ID);
		} else {
			configuration.setAttribute(SimulationLaunchConfigurationAttributes.ENGINE_TYPE_ID, selectedEngineType.getId());
		}
	}

	@Override
	public String getName() {
		return "Simulation";
	}

}
