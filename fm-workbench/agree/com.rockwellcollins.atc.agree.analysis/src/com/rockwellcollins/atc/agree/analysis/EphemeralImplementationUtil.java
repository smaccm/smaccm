package com.rockwellcollins.atc.agree.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.osate.aadl2.Aadl2Factory;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.BusType;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DeviceType;
import org.osate.aadl2.PackageRename;
import org.osate.aadl2.ProcessType;
import org.osate.aadl2.ProcessorType;
import org.osate.aadl2.PublicPackageSection;
import org.osate.aadl2.SubprogramType;
import org.osate.aadl2.SystemType;
import org.osate.aadl2.ThreadGroupType;
import org.osate.aadl2.ThreadType;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;

public class EphemeralImplementationUtil {

	protected final IProgressMonitor monitor;

	private List<Resource> ephemeralResources = new ArrayList<>();

	public EphemeralImplementationUtil(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	/*
	 * An error message that is filled by potential methods that
	 * instantiate the system and raises an error. This message
	 * is then show in the error dialog when an instantiation error
	 * is raised.
	 */
	private static String errorMessage = null;

	/*
	 * To keep under control the error messages and ease
	 * debug, we encapsulate the error message string
	 * and access it only through methods (setters and getters).
	 */
	public static void setErrorMessage(String s) {
		errorMessage = s;
	}

	public static String getErrorMessage() {
		return errorMessage;
	}

	private static URI getEphemeralImplURI(ComponentType ct) {
		URI ctURI = ct.eResource().getURI();
		String extension = ctURI.fileExtension();
		URI ctBaseURI = ctURI.trimFileExtension();
		URI implURI;
		do {
			implURI = ctBaseURI.trimSegments(1)
					.appendSegment(ctBaseURI.lastSegment() + "_" + UUID.randomUUID().toString().replace("-", "_"))
					.appendFileExtension(extension);
		} while (URIConverter.INSTANCE.exists(implURI, Collections.EMPTY_MAP));
		return implURI;
	}

	@SuppressWarnings("unchecked")
	public ComponentImplementation generateEphemeralCompImplFromType(ComponentType ct)
			throws Exception {
		Resource aadlResource = OsateResourceUtil.getResource(getEphemeralImplURI(ct));
		ephemeralResources.add(aadlResource);
		List<ComponentImplementation> resultList;
		ComponentImplementation result;

		final TransactionalEditingDomain domain = TransactionalEditingDomain.Registry.INSTANCE
				.getEditingDomain("org.osate.aadl2.ModelEditingDomain");
		// We execute this command on the command stack because otherwise, we will not
		// have write permissions on the editing domain.
		Command cmd = new RecordingCommand(domain) {
			public ComponentImplementation implementation;

			@Override
			protected void doExecute() {
				try {
					implementation = createComponentImplementationInternal(ct, aadlResource);
				} catch (InterruptedException e) {
					// Do nothing. Will be thrown after execute.
				}
			}

			@Override
			public List<ComponentImplementation> getResult() {
				return Collections.singletonList(implementation);
			}
		};

		((TransactionalCommandStack) domain.getCommandStack()).execute(cmd, null);
		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}

		try {
			// We're done: Save the model.
			// We don't respond to a cancel at this point
			monitor.subTask("Saving implementation model");
			aadlResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			setErrorMessage(e.getMessage());
			return null;
		}
		resultList = (List<ComponentImplementation>) cmd.getResult();
		result = resultList.get(0);

		return result;
	}

	private ComponentImplementation createComponentImplementationInternal(ComponentType ct, Resource aadlResource)
			throws InterruptedException {
		// Create a package and public section to contain the created
		// component implementation
		AadlPackage aadlPackage = Aadl2Factory.eINSTANCE.createAadlPackage();
		aadlPackage.setName(aadlResource.getURI().trimFragment().trimFileExtension().lastSegment().toString());
		PublicPackageSection publicSection = aadlPackage.createOwnedPublicSection();

		// Add import for package containing ct
		AadlPackage ctPackage = (AadlPackage) AgreeUtils.getClosestContainerOfType(ct, AadlPackage.class);
		publicSection.getImportedUnits().add(ctPackage);

		// Add renames clause to make linking to ct easy
		PackageRename ctRename = publicSection.createOwnedPackageRename();
		ctRename.setName("");
		ctRename.setRenamedPackage(ctPackage);
		ctRename.setRenameAll(true);

		// Create the component implementation in the public section
		ComponentImplementation compImpl;
		if (ct instanceof ThreadType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getThreadImplementation());
		} else if (ct instanceof ThreadGroupType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getThreadGroupImplementation());
		} else if (ct instanceof ProcessType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getProcessImplementation());
		} else if (ct instanceof SubprogramType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getSubprogramImplementation());
		} else if (ct instanceof ProcessorType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getProcessorImplementation());
		} else if (ct instanceof BusType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getBusImplementation());
		} else if (ct instanceof DeviceType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getDeviceImplementation());
		} else if (ct instanceof SystemType) {
			compImpl = (ComponentImplementation) publicSection
					.createOwnedClassifier(Aadl2Package.eINSTANCE.getSystemImplementation());
		} else {
			throw new AgreeException("Unhandled component type: " + ct.getClass().toString());
		}
		compImpl.setType(ct);
		compImpl.setName(ct.getName() + ".wrapper");

		// aadlResource.getContents().add(compImpl);
		aadlResource.getContents().add(aadlPackage);

		// Needed to save the root object because we may attach warnings to the
		// IResource as we build it.
		try {
			aadlResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			setErrorMessage(e.getMessage());
			return null;
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			setErrorMessage(npe.getMessage());

			npe.getMessage();
			return null;
//		} catch (InterruptedException e) {
//			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();

			e.getMessage();
			return null;
		}
		return compImpl;
	}

	public void cleanup() {
		final TransactionalEditingDomain domain = TransactionalEditingDomain.Registry.INSTANCE
				.getEditingDomain("org.osate.aadl2.ModelEditingDomain");
		// We execute this command on the command stack because otherwise, we will not
		// have write permissions on the editing domain.
		Command cmd = new RecordingCommand(domain) {
			@Override
			protected void doExecute() {
				try {
					for (Resource res : ephemeralResources) {
						res.delete(Collections.EMPTY_MAP);
					}
				} catch (IOException e) {
					setErrorMessage(e.getMessage());
					e.printStackTrace();
				}
			}
		};

		try {
			((TransactionalCommandStack) domain.getCommandStack()).execute(cmd, null);
		} catch (InterruptedException | RollbackException e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

}
