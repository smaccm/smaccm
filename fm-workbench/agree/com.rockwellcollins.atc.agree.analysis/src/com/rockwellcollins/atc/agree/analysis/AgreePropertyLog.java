package com.rockwellcollins.atc.agree.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
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

	// Field delimiter used in the log
	private final static String LOG_RESULT_DELIMITER = ":";
	// Replace any LOG_RESULT_DELIMITER occurrences in the hash string with this alternative character
	private final static String LOG_RESULT_DELIMITER_ALT = ";";
	private final static Semaphore mutex = new Semaphore(1);

	/**
	 * Prints a timestamped AGREE analysis result to the property log file
	 * (specified in AGREE preferences).
	 * @param result - The result of the AGREE analysis.
	 * @param timestamp - The time at which the analysis started
	 * @param hash - The hashcode for the combined model files
	 */
	public static void print(JKindResult result, long timestamp, String hash) {

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
		String hash = "";

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
				hash = parts[parts.length - 1];
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
				+ Long.toString(result.getTimestamp()) + LOG_RESULT_DELIMITER + result.getHash();
	}

	/**
	 * Collects files that contain the current model and its references.
	 * Returns a combined hash.
	 * @throws Exception
	 */
	public static String getModelHash(final Element root) throws Exception {

		SortedSet<IFile> files = new TreeSet<IFile>((f1, f2) -> f2.getName().compareTo(f1.getName()));
//		long hash = 0;

		// Get the file containing the component instance under evaluation
		final Resource thisResource = root.eResource();
		if (thisResource == null) {
			throw new Exception("Resource not found.");
		}

		// Get the hash for this resource, as well as all resources
		// referred to by this resource (recursively)
		collectResourceFiles(thisResource, files);

		MessageDigest md = MessageDigest.getInstance("MD5");
		Vector<InputStream> fileStreams = new Vector<>();
		for (IFile f : files) {
			fileStreams.add(f.getContents());
//			File ff = f.getRawLocation().makeAbsolute().toFile();

//			f.getContents()
//			if (f.getRawLocation() != null) {
//				md.update(Files.readAllBytes(f.getRawLocation().makeAbsolute().toFile().toPath()));
//			}
//			else {
//				System.out.println(f.getName());
//			}
		}

		DigestInputStream dis = null;
		try {
			SequenceInputStream sequenceInputStream = new SequenceInputStream(fileStreams.elements());
//			md.update(f);
			dis = new DigestInputStream(sequenceInputStream, md);
			dis.on(true);
			while (dis.read() >= 0) {
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dis.close();
		}

		return new String(md.digest()).trim().replace(LOG_RESULT_DELIMITER, LOG_RESULT_DELIMITER_ALT);
	}

	/**
	 * Recursive function to recursively collect all referenced files of the current resource.
	 * @param resource - The resource representing the file.
	 * @param files - Set of resource path/file names.
	 * @throws Exception
	 */
	private static void collectResourceFiles(Resource resource, Set<IFile> files) throws Exception {

		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		URI uri = resource.getURI();
		IFile file = workspaceRoot.getFile(new Path(uri.toPlatformString(true)));

//		try {
		if (uri.segment(0).equals("plugin")) {
			// We're going to ignore these
			return;
		} else if (!file.isAccessible() || !file.exists()) {
			throw new Exception("Unable to access file " + file.getName() + ".");
		} else {
			files.add(file);
		}
//		} catch (Exception e) {
//			return;
//		}

		// Get the resources that are referred to (via the 'with' statement)
		EList<EObject> resourceContents = resource.getContents();
		for (EObject eObj : resourceContents) {
			if (eObj instanceof AadlPackageImpl) {
				AadlPackageImpl aadlPackageImpl = (AadlPackageImpl) eObj;

				// Resources included in the public section
				if (aadlPackageImpl.getOwnedPublicSection() != null) {
					final EList<ModelUnit> importedUnits = aadlPackageImpl.getPublicSection().getImportedUnits();
					for (ModelUnit mUnit : importedUnits) {
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
