/*
Copyright (c) 2015, Rockwell Collins.
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
import java.util.Objects;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchShortcut2;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osgi.framework.FrameworkUtil;

import edu.uah.rsesc.aadlsimulator.launch.SimulationLaunchConfigurationAttributes;
import edu.uah.rsesc.aadlsimulator.launch.SimulationLaunchConfigurationTypeConstants;
import edu.uah.rsesc.aadlsimulator.services.EngineType;
import edu.uah.rsesc.aadlsimulator.services.SimulationService;
import edu.uah.rsesc.aadlsimulator.ui.GraphicalEditorSelectionHelper;
import edu.uah.rsesc.aadlsimulator.ui.handlers.StopHandler;

public class SimulationLaunchShortcut implements ILaunchShortcut2 {
	private final XtextSelectionHelper selectionHelper = new XtextSelectionHelper();
	private final GraphicalEditorSelectionHelper graphicalEditorSelectionHelper = new GraphicalEditorSelectionHelper();
	
	@Override
	public void launch(final ISelection selection, final String mode) {
		launch(selection, null, mode);
	}
	
	public void launch(final ISelection selection, final String engineTypeId, final String mode) {
		try {
			final ComponentImplementation componentImplementation = getComponentImplementation(selection);
			if(componentImplementation != null) {
				launch(componentImplementation, engineTypeId, mode);
			}
		} catch (final CoreException e) {
			StatusManager.getManager().handle(new Status(e.getStatus().getSeverity(), FrameworkUtil.getBundle(getClass()).getSymbolicName(), e.getLocalizedMessage(), e), StatusManager.LOG | StatusManager.BLOCK);
		}
	}
	
	@Override
	public void launch(final IEditorPart editor, final String mode) {
		try {
			final ComponentImplementation componentImplementation = selectionHelper.getCurrentComponentImplementation(editor);
			if(componentImplementation != null) {
				launch(componentImplementation, null, mode);
			}
		} catch (final CoreException e) {
			StatusManager.getManager().handle(new Status(e.getStatus().getSeverity(), FrameworkUtil.getBundle(getClass()).getSymbolicName(), e.getLocalizedMessage(), e), StatusManager.LOG | StatusManager.BLOCK);
		}
	}
	
	private ComponentImplementation getComponentImplementation(final ISelection selection) {
		ComponentImplementation componentImplementation = null;
		
		// Determine a component implementation based on the current selection
		if(selection instanceof IStructuredSelection) {
			final Object selectedObject = ((IStructuredSelection) selection).getFirstElement();
			if(selectedObject instanceof IFile) {
				final IFile iFile = (IFile)selectedObject;
				final ResourceSet resourceSet = new ResourceSetImpl();
				final Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(iFile.getFullPath().toString(), true), true);
				if(resource != null) {
					if(resource.getContents().size() > 0) {
						final SystemInstance systemInstance = (SystemInstance)resource.getContents().get(0);
						componentImplementation = systemInstance.getComponentImplementation();
					}
				}
			} else if(selectedObject instanceof EObjectNode) {
				final EObject selectedEObject = selectionHelper.getEObject((EObjectNode)selectedObject);
				if(selectedEObject instanceof ComponentImplementation) {
					componentImplementation = (ComponentImplementation)selectedEObject;
				}
			} else if(graphicalEditorSelectionHelper.isApplicable(selectedObject)) {
				componentImplementation = graphicalEditorSelectionHelper.getComponentImplementationByApplicableObject(selectedObject);
			}			
		}
		
		return componentImplementation;
	}
	
	public ILaunch launch(final ComponentImplementation componentImplementation, String engineTypeId, final String mode) throws CoreException {
		if(engineTypeId == null) {
			final SimulationService simulationService = Objects.requireNonNull((SimulationService)PlatformUI.getWorkbench().getService(SimulationService.class), "Unable to retrieve simulation service");
			final EngineType engineType = simulationService.getCompatibleEngineType(componentImplementation);
			if(engineType == null ){
				StatusManager.getManager().handle(new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Unable to find compatible simulation engine for " + componentImplementation.getQualifiedName()), StatusManager.BLOCK);
				return null;
			}
			engineTypeId = engineType.getId();
		}
		
		// Stop the current simulation
		new StopHandler().execute();
		
		final ILaunchConfigurationType configType = getLaunchConfigurationType();
	
		// Search for an existing launch configuration				
		final List<ILaunchConfiguration> launchConfigs = getLaunchConfigurations(configType, componentImplementation, engineTypeId); 
		ILaunchConfiguration launchConfig = launchConfigs.size() > 0 ? launchConfigs.get(0) : null; 
		if(launchConfig == null) {
			final ILaunchConfigurationWorkingCopy wcLaunchConf = configType.newInstance(null, DebugPlugin.getDefault().getLaunchManager().generateLaunchConfigurationName(componentImplementation.getQualifiedName()));
			// Set attributes, etc for the launch configuration
			wcLaunchConf.setAttribute(SimulationLaunchConfigurationAttributes.COMPONENT_IMPLEMENTATION_NAME, componentImplementation.getQualifiedName());
			wcLaunchConf.setAttribute(SimulationLaunchConfigurationAttributes.PROJECT_NAME, getProjectName(componentImplementation.eResource()));
			if(engineTypeId != null) {
				wcLaunchConf.setAttribute(SimulationLaunchConfigurationAttributes.ENGINE_TYPE_ID, engineTypeId);	
			}
			
			launchConfig = wcLaunchConf;
		} 
		
		// Launch the launch configuration 
		return DebugUITools.buildAndLaunch(launchConfig, mode, new NullProgressMonitor());			
	}
	
	/**
	 * Returns the name of the project that contains the resource or null if the project name could not be determined.
	 * @param resource
	 * @return
	 */
	public static String getProjectName(final Resource resource) {
		if(resource != null && resource.getURI() != null) {
			final IPath projectPath = new Path(resource.getURI().toPlatformString(true)).uptoSegment(1);
			final IResource projectResource = ResourcesPlugin.getWorkspace().getRoot().findMember(projectPath);
			if(projectResource instanceof IProject) {
				return projectResource.getName();
			}
		}

		return null;
	}
	
	private List<ILaunchConfiguration> getLaunchConfigurations(final ILaunchConfigurationType configType, final ComponentImplementation ci, final String engineTypeId) throws CoreException {
		Objects.requireNonNull(ci, "ci must not be null");
		final String componentImplementationName = ci.getQualifiedName();
		final List<ILaunchConfiguration> configs = new ArrayList<ILaunchConfiguration>();
		String projectName = getProjectName(ci.eResource());
		projectName = projectName == null ? "" : projectName;
		
		for(final ILaunchConfiguration launchConfiguration : DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations(configType)) {
			// Check if the launch configuration's component implementation name matches the selected component implementation
			if(componentImplementationName.equalsIgnoreCase(launchConfiguration.getAttribute(SimulationLaunchConfigurationAttributes.COMPONENT_IMPLEMENTATION_NAME, "")) &&
					projectName.equals(launchConfiguration.getAttribute(SimulationLaunchConfigurationAttributes.PROJECT_NAME, "")) &&
					(engineTypeId == null || engineTypeId.equals(launchConfiguration.getAttribute(SimulationLaunchConfigurationAttributes.ENGINE_TYPE_ID, "")))) {
				configs.add(launchConfiguration);
			}
		}
		
		return configs;
	}
	
	private ILaunchConfigurationType getLaunchConfigurationType() {
		final ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		final ILaunchConfigurationType launchConfigurationType = launchManager.getLaunchConfigurationType(SimulationLaunchConfigurationTypeConstants.id);
		Objects.requireNonNull(launchConfigurationType, "Unable to retrieve simulation launch configuration type");
		return launchConfigurationType;
	}

	@Override
	public ILaunchConfiguration[] getLaunchConfigurations(final ISelection selection) {
		// Get a component implementation based on the specified selection
		final ILaunchConfigurationType configType = getLaunchConfigurationType();
		final ComponentImplementation ci = getComponentImplementation(selection);
		if(ci == null) {
			return null;
		}
		
		// Return launch configurations associated with the selection
		try {
			return getLaunchConfigurations(configType, ci, null).toArray(new ILaunchConfiguration[0]);
		} catch (final CoreException e) {
			StatusManager.getManager().handle(new Status(e.getStatus().getSeverity(), FrameworkUtil.getBundle(getClass()).getSymbolicName(), e.getLocalizedMessage(), e), StatusManager.LOG | StatusManager.BLOCK);
			return null;
		}
	}

	@Override
	public ILaunchConfiguration[] getLaunchConfigurations(final IEditorPart editorpart) {
		return null;
	}

	@Override
	public IResource getLaunchableResource(final ISelection selection) {
		return null;
	}

	@Override
	public IResource getLaunchableResource(final IEditorPart editorpart) {
		return null;
	}
}
