package com.rockwellcollins.atc.resolute.agreecheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.osate.aadl2.ModelUnit;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.impl.AadlPackageImpl;
import org.osate.aadl2.instance.impl.ComponentInstanceImpl;
import org.osate.aadl2.instance.impl.SystemInstanceImpl;

import com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl;
import com.rockwellcollins.atc.agree.analysis.AgreePropertyLog;
import com.rockwellcollins.atc.agree.analysis.AgreePropertyResult;
import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteExternalAnalysis;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteFailException;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

import jkind.api.results.Status;

public class ResoluteAgreeCheck implements ResoluteExternalAnalysis {

	private long modelTimestamp;
	private Set<AgreePropertyResult> agreePropertyResults;
	private EvaluationContext evalContext;

	/**
	 * The purpose of this plugin is to check that, for a given component or implementation,
	 *   1) AGREE was run on the latest version of the model, and
	 *   2) the AGREE Assume contracts of the component are valid and consistent
	 * In order to successfully use this plugin, "Generate property analysis log" must be checked
	 * in the AGREE Analysis preferences, and a log file must be specified.
	 * @param context - The context for which this plugin was called
	 * @param argss - Plugin arguments.  It should be an empty list.
	 */
	@Override
	public ResoluteValue run(EvaluationContext context, List<ResoluteValue> args) {

		this.evalContext = context;
		this.agreePropertyResults = new HashSet<>();

		// Get the date the model was last modified
		getModelTimestamp();

		// Get the AGREE analysis timestamps from log
		getAgreePropertyResults();

		// If this instance is a component, just examine
		// AGREE contracts specified in that component.
		// If it is a component implementation, examine all components referred to in
		// that implementation.
		if (evalContext.getThisInstance() instanceof SystemInstanceImpl) {
			Set<NamedElement> components = evalContext.getSet("component");
			for (NamedElement component : components) {
				if (!checkComponentAgreeProperties((ComponentInstanceImpl) component)) {
					return new BoolValue(false);
				}
			}
		} else if (evalContext.getThisInstance() instanceof ComponentInstanceImpl) {
			if (!checkComponentAgreeProperties((ComponentInstanceImpl) evalContext.getThisInstance())) {
				return new BoolValue(false);
			}
		} else {
			throw new ResoluteFailException(
					"Resolute AgreeCheck can only be called from a component or component implementation.",
					evalContext.getThisInstance());
		}

		return new BoolValue(true);

	}

	/**
	 * Collects timestamps (last modified date) from all files that contain the current model and its references.
	 * Stores the most recent modified date.
	 */
	private void getModelTimestamp() {

		long mostRecentTimestamp = 0;
		final HashMap<String, Long> resourceTimestamps = new HashMap<>();

		// Get the file containing the component instance under evaluation
		final Resource thisResource = evalContext.getThisInstance().getComponentClassifier().eResource();

		// Get the timestamps for this resource, as well as all resources
		// referred to by this resource (recursively)
		collectResourceTimestamps(thisResource, resourceTimestamps);

		// Find the most recently saved file
		for (Long timestamp : resourceTimestamps.values()) {
			if (timestamp > mostRecentTimestamp) {
				mostRecentTimestamp = timestamp;
			}
		}

		modelTimestamp = mostRecentTimestamp;

	}

	/**
	 * Recursive function to record the timestamp (last modified date) of a file.  It will then recursively call
	 * itself to record the timestamps of all files that are referred to by the resource.
	 * @param resource - The resource representing the file
	 * @param resourceTimestamps - Set of resources and their last modified date.
	 */
	private void collectResourceTimestamps(Resource resource, HashMap<String, Long> resourceTimestamps) {

		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		URI uri = resource.getURI();
		IFile file = workspaceRoot.getFile(new Path(uri.toPlatformString(true)));

		if (uri.segment(0).equals("plugin")) {
			// plugin resources won't have timestamps,
			// so just set it to zero (otherwise it will be IResource.NULL_STAMP)
			resourceTimestamps.put(file.getName(), 0L);
		} else if (!file.isAccessible()) {
			throw new ResoluteFailException("Unable to access file " + file.getName() + ".",
					evalContext.getThisInstance());
		} else if (file.getLocalTimeStamp() == IResource.NULL_STAMP) {
			throw new ResoluteFailException(file.getName() + " does not have a valid modification timestamp.",
					evalContext.getThisInstance());
		} else {
			resourceTimestamps.put(file.getName(), file.getLocalTimeStamp());
		}

		// Get the resources that are referred to (via the 'with' statement)
		EList<EObject> resourceContents = resource.getContents();
		for (EObject eObj : resourceContents) {
			if (eObj instanceof AadlPackageImpl) {
				AadlPackageImpl aadlPackageImpl = (AadlPackageImpl) eObj;

				// Resources included in the public section
				if (aadlPackageImpl.getOwnedPublicSection() != null) {
					final EList<ModelUnit> importedUnits = aadlPackageImpl.getPublicSection().getImportedUnits();
					for (ModelUnit mUnit : importedUnits) {
						String resourceName = mUnit.eResource().getURI().lastSegment();
						if (!resourceTimestamps.containsKey(resourceName)) {
							collectResourceTimestamps(mUnit.eResource(), resourceTimestamps);
						}
					}
				}
				// Resources included in the private section
				if (aadlPackageImpl.getOwnedPrivateSection() != null) {
					final EList<ModelUnit> importedUnits = aadlPackageImpl.getPrivateSection().getImportedUnits();
					for (ModelUnit mUnit : importedUnits) {
						String resourceName = mUnit.eResource().getURI().lastSegment();
						if (!resourceTimestamps.containsKey(resourceName)) {
							collectResourceTimestamps(mUnit.eResource(), resourceTimestamps);
						}
					}
				}
			}
		}

		return;
	}

	/**
	 * Accesses the Agree property result file that is generated when AGREE runs.
	 * Reads the contents and stores them in the class variable 'agreePropertyResults' for comparison later.
	 */
	private void getAgreePropertyResults() {

		try {

			// Get agree property log file
			File logFile = AgreePropertyLog.getLogFile();
			if (logFile == null) {
				// Log file name was not specified in preferences
				throw new ResoluteFailException("The AGREE log file is not specified in AGREE Preferences.",
						evalContext.getThisInstance());
			}

			// Read in the agree property log file
			BufferedReader bufferedReader = new BufferedReader(new FileReader(logFile));

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				AgreePropertyResult agreeLogResult = AgreePropertyLog.parseLogResult(line);
				// Add the properties from the log file to the property set
				// If a property from the log file is already in the set, it won't be added
				if (agreeLogResult != null) {
					agreePropertyResults.add(agreeLogResult);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			throw new ResoluteFailException("Could not read AGREE Property Log.", evalContext.getThisInstance());
		}

	}

	/**
	 * Extracts the AGREE Assume contracts from a given component.  Checks that AGREE was run on the latest version
	 * of the model and the contracts are valid.
	 * @param component - The component containing the AGREE Assume contracts.  Can also be a system implementation.
	 */
	private boolean checkComponentAgreeProperties(ComponentInstanceImpl component) {

		// Get AGREE properties specified for this component
		List<AssumeStatementImpl> assumeStatements = EcoreUtil2.getAllContentsOfType(component.getComponentClassifier(),
				AssumeStatementImpl.class);
		String propName;
		for (AssumeStatementImpl assumeStatement : assumeStatements) {
			if (component instanceof SystemInstanceImpl) {
				propName = assumeStatement.getStr();
			} else {
				propName = component.getName().concat(" assume: ").concat(assumeStatement.getStr());
			}
			if (!checkAgreeProperty(propName)) {
				return false;
			}
		}

		// Check component consistency
		if (component instanceof SystemInstanceImpl) {
			propName = "This component consistent";
		} else {
			propName = component.getName().concat(" consistent");
		}
		if (!checkAgreeProperty(propName)) {
			return false;
		}

		return true;
	}

	/**
	 * Checks that AGREE was run on the latest version of the model and the contracts are valid.
	 * @param property - The property to be checked.
	 */
	private boolean checkAgreeProperty(String property) {

		try {
			for (AgreePropertyResult propResult : agreePropertyResults) {
				if (propResult.getName().equals(property)) {
					// Check status and timestamp
					if (propResult.getStatus() == Status.VALID
							&& propResult.getTimestamp().getTime() >= modelTimestamp) {
						return true;
					}
					break;
				}
			}
			return false;
		} catch (Exception e) {
			throw new ResoluteFailException(
					"Unknown error checking AGREE property [ " + property + " ] : " + e.getMessage(),
					evalContext.getThisInstance());
		}
	}

}

