package com.rockwellcollins.atc.agree.analysis.saving;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;

import jkind.api.results.AnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.MultiStatus;
import jkind.api.results.PropertyResult;
import jkind.api.results.Status;

public class AgreeFileUtil {

	private final static Semaphore mutex = new Semaphore(1);
	private static final java.lang.reflect.Type AGREE_PROP_LOG_TYPE = new TypeToken<List<AgreeLogResult>>() {
	}.getType();

	/**
	 * Prints the AGREE analysis result in json format to the property log file
	 * (specified in AGREE preferences), along with a timestamp and hashcode.
	 * @param result - The result of the AGREE analysis.
	 * @param timestamp - The time at which the analysis started
	 * @param hash - The hashcode for the combined model files
	 */
	public static void printLog(JKindResult result, long timestamp, String hashcode) {

		try {

			// This class could be called concurrently from multiple threads
			// so wrap the following read/write behavior in a mutex
			mutex.acquire();

			try {

				// Figure out the top-level analysis this result is part of
				// and keep track of ancestor trace so we know where to insert this analysis
				AnalysisResult topLevelAnalysis = result;
				List<String> ancestorTrace = new ArrayList<>();
				while (topLevelAnalysis.getParent() != null && !topLevelAnalysis.getParent().getName().isEmpty()) {
					topLevelAnalysis = topLevelAnalysis.getParent();
					ancestorTrace.add(0, topLevelAnalysis.getName());
				}

				// Get the results from the current agree analysis
				AgreeLogResult agreeLogResult = parsePropertyResult(result);
				agreeLogResult.setTimestamp(timestamp);
				agreeLogResult.setHashcode(hashcode);

				// Read in the agree property log file
				List<AgreeLogResult> agreeLog = readAgreeLog();

				// Insert the agree result in the agree log structure
				insertAgreeLogResult(agreeLogResult, agreeLog, ancestorTrace);

				// Write the agree property results back to the file
				// The contents of the file will be overwritten
				writeAgreeLog(agreeLog);

			} finally {
				mutex.release();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not write to the AGREE analysis log.\n");
		}

		return;
	}

	/**
	 * Gets the AGREE analysis log file pathname, specified in the AGREE preferences.
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
	 * Read in the AGREE analysis log file, specified in the AGREE preferences.
	 * @return List<AgreePropertyResult> - AGREE results from log file
	 */
	public static List<AgreeLogResult> readAgreeLog() throws Exception {

		final File logFile = getLogFile();
		if (logFile == null) {
			// Log file name was not specified in preferences
			throw new Exception("AGREE log file name was not specified in AGREE preferences.");
		}

		// Read in the agree property log file
		try {
			Gson gson = new Gson();
			JsonReader jsonReader = new JsonReader(new FileReader(logFile));
			List<AgreeLogResult> agreeLog = gson.fromJson(jsonReader, AGREE_PROP_LOG_TYPE);
			jsonReader.close();
			if (agreeLog == null) {
				agreeLog = new ArrayList<AgreeLogResult>();
			}
			return agreeLog;
		} catch (FileNotFoundException e) {
			throw new Exception(
					"Could not find AGREE log. Make sure the file location specified in AGREE preferences is correct.");
		} catch (IOException e) {
			throw new Exception("Error reading AGREE log.");
		}
	}

	/**
	 * Write the AGREE analysis log file, specified in the AGREE preferences.
	 * @param agreePropertyResult - AGREE results to write to log file
	 */
	public static void writeAgreeLog(List<AgreeLogResult> agreePropertyResult) throws Exception {
		final File logFile = getLogFile();
		if (logFile == null) {
			// Log file name was not specified in preferences
			throw new Exception("AGREE log file name was not specified in AGREE preferences.");
		}

		// Write the agree property results back to the file
		// The contents of the file will be overwritten
		try {
			Gson gson = new Gson();
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(logFile));
			jsonWriter.setIndent("    ");
			gson.toJson(agreePropertyResult, AGREE_PROP_LOG_TYPE, jsonWriter);
			jsonWriter.close();
		} catch (FileNotFoundException e) {
			throw new Exception(
					"Could not find AGREE log. Make sure the file location specified in AGREE preferences is correct.");
		} catch (IOException e) {
			throw new Exception("Error writing AGREE log.");
		}

	}

	/**
	 * Inserts an AGREE analysis result into the Results list at the appropriate level, as specified
	 * by the ancestor trace.
	 * @param agreePropertyResult - The AGREE analysis result to insert.
	 * @param agreePropertyResults - The Results list.
	 * @param ancestorTrace - This is a top-down list that specifies the ancestor hierarchy of where
	 * the current result is to be placed.
	 */
	private static void insertAgreeLogResult(AgreeLogResult agreeLogResult, List<AgreeLogResult> agreeLogResults,
			List<String> ancestorTrace) {

		if (ancestorTrace == null || ancestorTrace.size() == 0) {
			agreeLogResults.add(agreeLogResult);
			return;
		}

		for (AgreeLogResult logResult : agreeLogResults) {
			if (logResult.getName().equalsIgnoreCase(ancestorTrace.get(0))) {

				ancestorTrace.remove(0);

				if (logResult.getAnalyses() == null) {
					// This means that the JKind result name is in the log file,
					// but has no associated analyses
					logResult.setAnalyses(new ArrayList<AgreeLogResult>());
				}

				if (ancestorTrace.size() > 0) {
					// Go down to the next level
					insertAgreeLogResult(agreeLogResult, logResult.getAnalyses(), ancestorTrace);
				} else {
					// Look through the log result to see if the current JKind result is in there
					// If so, overwrite with the new result
					boolean analysisFound = false;
					List<AgreeLogResult> analyses = logResult.getAnalyses();
					for (int i = 0; i < analyses.size(); i++) {
						if (analyses.get(i).getName().equalsIgnoreCase(agreeLogResult.getName())) {
							analyses.set(i, agreeLogResult);
							analysisFound = true;
							break;
						}
					}
					// If this analysis does not exist in the log file, add it
					if (!analysisFound) {
						logResult.getAnalyses().add(agreeLogResult);
					}
				}

				// Set timestamp and hashcode of the current JKind result
				logResult.setTimestamp(agreeLogResult.getTimestamp());
				logResult.setHashcode(agreeLogResult.getHashcode());

				// Set the status for this analysis based on the status of each sub-analysis
				// that has the same timestamp as this analysis
				MultiStatus status = new MultiStatus();
				for (AgreeLogResult analysis : logResult.getAnalyses()) {
					if (analysis.getTimestamp() >= logResult.getTimestamp()) {
						status.add(Status.valueOf(analysis.getResult().toUpperCase()));
					}
				}
				logResult.setResult(status.getOverallStatus().toString());

				break;
			}
		}

		// If ancestorTrace is not empty, one of the result's ancestors doesn't exist in the log. Add it.
		if (ancestorTrace.size() > 0) {
			AgreeLogResult newAncestor = new AgreeLogResult();
			newAncestor.setName(ancestorTrace.get(0));
			agreeLogResults.add(newAncestor);
			insertAgreeLogResult(agreeLogResult, agreeLogResults, ancestorTrace);
		}

	}

	/**
	 * Converts an AGREE analysis result to a simple format used for saving AGREE analyses.
	 * @param result - The AGREE result.
	 * @return AgreePropertyResult
	 */
	private static AgreeLogResult parsePropertyResult(AnalysisResult result) {

		AgreeLogResult agreeResult = new AgreeLogResult();

		agreeResult.setName(result.getName());

		if (result instanceof PropertyResult) {
			PropertyResult propResult = (PropertyResult) result;
			agreeResult.setResult(propResult.getStatus().toString());
		} else if (result instanceof JKindResult) {
			JKindResult jkindResult = (JKindResult) result;
			ArrayList<AgreeLogResult> propResults = new ArrayList<>();
			agreeResult.setResult(jkindResult.getMultiStatus().getOverallStatus().toString());
			for (PropertyResult propResult : jkindResult.getPropertyResults()) {
				propResults.add(parsePropertyResult(propResult));
			}
			agreeResult.setAnalyses(propResults);
		}

		return agreeResult;
	}


	/**
	 * Collects files that contain the current model and its references.
	 * @return String - A combined hash for all model files.
	 * @throws Exception
	 */
	public static String getModelHashcode(final Element root) throws Exception {

		SortedSet<IFile> files = new TreeSet<IFile>((f1, f2) -> f2.getName().compareTo(f1.getName()));

		// Get the file containing the component instance under evaluation
		final Resource thisResource = root.eResource();
		if (thisResource == null) {
			throw new Exception("Could not generate hashcode because resource not found.");
		}

		// Get the hash for this resource, as well as all resources
		// referred to by this resource (recursively)
		collectResourceFiles(thisResource, files);

		MessageDigest md = MessageDigest.getInstance("MD5");
		Vector<InputStream> fileStreams = new Vector<>();
		for (IFile f : files) {
			fileStreams.add(f.getContents());
		}

		DigestInputStream dis = null;
		try {
			SequenceInputStream sequenceInputStream = new SequenceInputStream(fileStreams.elements());
			dis = new DigestInputStream(sequenceInputStream, md);
			dis.on(true);
			while (dis.read() >= 0) {
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dis.close();
		}

		return new String(md.digest()).trim();
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

		if (uri.segment(0).equals("plugin")) {
			// We're going to ignore these
			return;
		} else if (!file.isAccessible() || !file.exists()) {
			throw new Exception("Unable to access file " + file.getName() + ".");
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
