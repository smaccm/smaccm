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
package edu.uah.rsesc.aadlsimulator.launch;

import java.util.Objects;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.osate.aadl2.Aadl2Factory;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osgi.framework.FrameworkUtil;

import com.google.inject.Injector;

import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.services.EngineType;
import edu.uah.rsesc.aadlsimulator.services.SimulationEngineChangeListener;
import edu.uah.rsesc.aadlsimulator.services.SimulationService;

public class SimulationLaunchConfigurationDelegate implements ILaunchConfigurationDelegate2 {
	private static final EClass componentImplementationEClass = Aadl2Factory.eINSTANCE.getAadl2Package().getComponentImplementation();

	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch, final IProgressMonitor monitor) throws CoreException {
		SimulationService simulationService = null;
		try {
			simulationService = EclipseContextFactory.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext()).get(SimulationService.class);
			Objects.requireNonNull(configuration, "configuration must not be null");

			// Find the component implementation
			final String componentImplementationName = configuration.getAttribute(SimulationLaunchConfigurationAttributes.COMPONENT_IMPLEMENTATION_NAME, (String)null);
			final String projectName = configuration.getAttribute(SimulationLaunchConfigurationAttributes.PROJECT_NAME, (String)null);

			final IProject project = projectName == null ? null : findProject(projectName);
			final EObject obj = componentImplementationName == null ? null : findComponentImplementation(project, componentImplementationName);
			if(obj instanceof ComponentImplementation) {
				// Refresh the component implementation
				final ComponentImplementation ci = refreshComponentImplementation((ComponentImplementation)obj);

				// (Re)instantiate the model
				final SystemInstance systemInstance = InstantiateModel.buildInstanceModelFile(ci);

				// Get the selected simulation engine type.
				final String selectedEngineTypeId = Objects.requireNonNull(configuration.getAttribute(SimulationLaunchConfigurationAttributes.ENGINE_TYPE_ID, (String)null), "Simulation Engine must be specified");
				final EngineType engineType = Objects.requireNonNull(simulationService.getEngineTypeById(selectedEngineTypeId), "Unable to find specified simulation engine");

				final SimulationEngine newEngine = simulationService.createEngine(engineType, systemInstance);
				final SimulationService simService = simulationService;
				simulationService.addSimulationEngineChangeListener(new SimulationEngineChangeListener() {
					@Override
					public void onSimulationEngineCreated(final SimulationEngine engine) {
					}

					@Override
					public void onSimulationEngineDisposed(final SimulationEngine engine) {
						if(engine == newEngine) {
							DebugPlugin.getDefault().getLaunchManager().removeLaunch(launch);
							simService.removeSimulationEngineChangeListener(this);
						}
					}
				});

				if(launch instanceof SimulationLaunch) {
					((SimulationLaunch) launch).setSimulationEngine(newEngine);
				}
			} else {
				simulationService.getExceptionHandler().handleException(new CoreException(new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Unable to find Component Implementation.")));
			}
		} catch (final Exception e) {
			DebugPlugin.getDefault().getLaunchManager().removeLaunch(launch);
			if(simulationService == null) {
				throw new RuntimeException(e);
			} else {
				simulationService.getExceptionHandler().handleException(e);
			}
		}
	}

	/**
	 * Returns the retrieved project or throws an exception if the project cannot be found.
	 * @param projectName
	 * @return
	 */
	private IProject findProject(final String projectName) {
		final IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(projectName);
		if(member instanceof IProject) {
			return (IProject)member;
		}

		throw new RuntimeException("Unable to find project: " + projectName);
	}

	private ComponentImplementation findComponentImplementation(final IProject project, final String qualifiedNameStr) {
		final QualifiedName qualifiedName = QualifiedName.create(qualifiedNameStr.split("::"));

		final IPath projectPath = project == null ? null : project.getFullPath();

		Injector injector = IResourceServiceProvider.Registry.INSTANCE
				.getResourceServiceProvider(URI.createFileURI("dummy.aadl")).get(Injector.class);
		final ResourceDescriptionsProvider resourceDescProvider = injector.getInstance(ResourceDescriptionsProvider.class);
		final IResourceDescriptions resDescriptions = resourceDescProvider.getResourceDescriptions(new XtextResourceSet());

		for(final IResourceDescription resDesc : resDescriptions.getAllResourceDescriptions()) {
			final IPath resPath = new Path(resDesc.getURI().toPlatformString(true));
			if(project == null || projectPath.isPrefixOf(resPath)) {
				for(IEObjectDescription eod : resDesc.getExportedObjects(componentImplementationEClass, qualifiedName, true)) {
					return (ComponentImplementation)eod.getEObjectOrProxy();
				}
			}
		}

		return null;
	}

	private ComponentImplementation refreshComponentImplementation(final ComponentImplementation ci) {
		final URI uri = EcoreUtil.getURI(ci);
		final ResourceSet rs = OsateResourceUtil.createResourceSet();
		final EObject newEObject = rs.getEObject(uri, true);
		if(!(newEObject instanceof ComponentImplementation)) {
			throw new RuntimeException("Unable to refresh component implementation");
		}

		return (ComponentImplementation)newEObject;
	}

	@Override
	public ILaunch getLaunch(final ILaunchConfiguration configuration, final String mode) throws CoreException {
		return new SimulationLaunch(configuration, mode, null);
	}

	@Override
	public boolean buildForLaunch(final ILaunchConfiguration configuration, final String mode, final IProgressMonitor monitor) throws CoreException {
		return true;
	}

	@Override
	public boolean preLaunchCheck(final ILaunchConfiguration configuration, final String mode, final IProgressMonitor monitor) throws CoreException {
		return true;
	}

	@Override
	public boolean finalLaunchCheck(final ILaunchConfiguration configuration, final String mode, final IProgressMonitor monitor) throws CoreException {
		return  true;
	}
}
