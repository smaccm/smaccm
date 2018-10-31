package com.rockwellcollins.atc.agree.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.osate.aadl2.Element;
import org.osate.aadl2.ModelUnit;
import org.osate.aadl2.impl.AadlPackageImpl;

import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;

import jkind.api.results.JKindResult;
import jkind.api.results.PropertyResult;
import jkind.api.results.Status;

public class AgreePropertyLog {

	private final static String LOG_RESULT_DELIMITER = ":";
	private final static Semaphore mutex = new Semaphore(1);

	/**
	 * Prints a timestamped AGREE analysis result to the property log file
	 * (specified in AGREE preferences).
	 * @param result - The result of the AGREE analysis.
	 */
	public static void print(final Element root, JKindResult result, long timestamp) {

		try {

			// Get each property result from the log file
			// Malformed property results will be ignored and eventually removed (overwritten)
			final File logFile = getLogFile();
			if (logFile == null) {
				// Log file name was not specified in preferences
				throw new Exception();
			}

			// This class could be called concurrently from multiple threads
			// so wrap the following read/write behavior in a mutex
			mutex.acquire();

			try {

				final Set<AgreePropertyResult> logFileProps = new TreeSet<AgreePropertyResult>((agreeLogResult1,
						agreeLogResult2) -> agreeLogResult1.getName().compareTo(agreeLogResult2.getName()));

				// Get the results from the current agree analysis
				final List<PropertyResult> pResults = result.getPropertyResults();

				// Update the log file results with the new agree results
				for (PropertyResult pr : pResults) {

					String propName = pr.getName();

					// For consistency results, the name will just be "Result", so
					// let's use the JKindResult name to add context
					if (propName.equals("Result")) {
						propName = result.getName();
					}

					// Get hash of model files
					long hash = getModelHash(root);

					// Add the properties to the property set
					logFileProps.add(new AgreePropertyResult(propName, pr.getStatus(), timestamp, hash));

				}

				// Read in the agree property log file
				BufferedReader bufferedReader = new BufferedReader(new FileReader(logFile));
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					AgreePropertyResult agreeLogResult = parseLogResult(line);
					// Add the properties from the log file to the property set
					// If a property from the log file is already in the set, it won't be added
					if (agreeLogResult != null) {
						logFileProps.add(agreeLogResult);
					}
				}
				bufferedReader.close();

				// Write the agree property results back to the file
				// The contents of the file will be overwritten
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, false));
				for (AgreePropertyResult agreeLogResult : logFileProps) {
					bufferedWriter
					.write(unparseLogResult(agreeLogResult) + System.lineSeparator());
				}
				bufferedWriter.close();
			} finally {
				mutex.release();
			}

		} catch (Exception e) {
			System.out.println("Could not write to the AGREE analysis log.\n");
		}

		return;
	}

	/**
	 * Gets the AGREE analysis log file, specified in the AGREE preferences.
	 * @return File
	 */
	public static File getLogFile() {
		// get log filename/path from preferences
		String fileName = Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.PREF_PROP_LOG_FILENAME);
		if (fileName.isEmpty()) {
			return null;
		} else {
			return new File(fileName);
		}
	}

	/**
	 * Parses an AGREE analysis log result.
	 * The format is <AGREE Property Name>:<Status>:<Hash>.
	 * @param result - A string containing a single result from the property log file.
	 * @return AgreePropertyResult - structure containing AGREE property name,
	 * pass/fail status, and hash.
	 */
	public static AgreePropertyResult parseLogResult(String result) {
		String name = "";
		Status status = null;
		long timestamp = 0;
		long hash = 0;

		try {

			String[] parts = result.split(LOG_RESULT_DELIMITER);
			if (parts.length < 4 || parts[0].isEmpty() || parts[parts.length - 3].isEmpty()
					|| parts[parts.length - 2].isEmpty()
					|| parts[parts.length - 1].isEmpty()) {
				// This is a malformed result; ignore
				return null;
			} else {
				// Because the delimiter could be part of the property name, we may need to reassemble
				for (int i = 0; i < parts.length - 3; i++) {
					name = name + parts[i] + (i < parts.length - 4 ? LOG_RESULT_DELIMITER : "");
				}
				name = name.trim();
				status = Status.valueOf(parts[parts.length - 3].trim().toUpperCase());
				timestamp = Long.parseLong(parts[parts.length - 2].trim());
				hash = Long.parseLong(parts[parts.length - 1].trim());
			}
		} catch (Exception e) {
			return null;
		}

//		return new AgreePropertyResult(name, status, timestamp);
		return new AgreePropertyResult(name, status, timestamp, hash);
	}

	/**
	 * Creates a string representing an AGREE analysis result, formatted for the log file.
	 * @param result - An AgreePropertyResult containing a single result.
	 * @return String - A log file formatted string representing the AGREE analysis result.
	 */
	private static String unparseLogResult(AgreePropertyResult result) {
//		return result.getName() + LOG_RESULT_DELIMITER + result.getStatus().toString() + LOG_RESULT_DELIMITER
//				+ Long.toString(result.getTimestamp());
		return result.getName() + LOG_RESULT_DELIMITER + result.getStatus().toString() + LOG_RESULT_DELIMITER
				+ Long.toString(result.getTimestamp()) + Long.toString(result.getHash());
	}

	/**
	 * Collects files that contain the current model and its references.
	 * Returns a combined hash.
	 */
	public static long getModelHash(final Element root) {

		Set<IFile> files = new HashSet<>();
		long hash = 0;

		// Get the file containing the component instance under evaluation
//		final Resource thisResource = evalContext.getThisInstance().getComponentClassifier().eResource();
		final Resource thisResource = root.eResource();
		if (thisResource == null) {
			// TODO: address this error
		}

		// Get the hash for this resource, as well as all resources
		// referred to by this resource (recursively)
		collectResourceFiles(thisResource, files);

		for (IFile f : files) {

		}

		return hash;
	}

	/**
	 * Recursive function to recursively collect all referenced files of the current resource.
	 * @param resource - The resource representing the file.
	 * @param files - Set of resource path/file names.
	 */
	private static void collectResourceFiles(Resource resource, Set<IFile> files) {

		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		URI uri = resource.getURI();
		IFile file = workspaceRoot.getFile(new Path(uri.toPlatformString(true)));

		if (uri.segment(0).equals("plugin")) {
			files.add(file);
		} else if (!file.isAccessible()) {
			// TODO: Address this error
//			throw new ResoluteFailException("Unable to access file " + file.getName() + ".",
//					evalContext.getThisInstance());
		} else {
			files.add(file);
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
//						String resourceName = mUnit.eResource().getURI().lastSegment();
						IFile resourceFile = workspaceRoot
								.getFile(new Path(mUnit.eResource().getURI().toPlatformString(true)));
						if (!files.contains(resourceFile)) {
							collectResourceFiles(mUnit.eResource(), files);
						}
					}
				}
				// Resources included in the private section
				if (aadlPackageImpl.getOwnedPrivateSection() != null) {
					final EList<ModelUnit> importedUnits = aadlPackageImpl.getPrivateSection().getImportedUnits();
					for (ModelUnit mUnit : importedUnits) {
//						String resourceName = mUnit.eResource().getURI().lastSegment();
						IFile resourceFile = workspaceRoot
								.getFile(new Path(mUnit.eResource().getURI().toPlatformString(true)));
						if (!files.contains(resourceFile)) {
							collectResourceFiles(mUnit.eResource(), files);
						}
					}
				}
			}
		}

		return;
	}

}
