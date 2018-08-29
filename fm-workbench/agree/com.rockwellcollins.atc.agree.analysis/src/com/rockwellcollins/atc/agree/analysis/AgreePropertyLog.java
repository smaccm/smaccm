package com.rockwellcollins.atc.agree.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

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
	public static void print(JKindResult result) {

		try {

			// Get the current time
			final Date timestamp = new Date(System.currentTimeMillis());

			// Get each property result from the log file
			// Malformed property results will be ignored and eventually removed (overwritten)
			final File logFile = getLogFile();
			if (logFile == null) {
				// Log file name was not specified in preferences
				throw new Exception();
			}

			// This class can be called concurrently from multiple threads
			// so wrap the following read/write behavior in a mutex
			mutex.acquire();

			try {

				final Set<AgreePropertyResult> logFileProps = new TreeSet<AgreePropertyResult>((agreeLogResult1,
						agreeLogResult2) -> agreeLogResult1.getName().compareTo(agreeLogResult2.getName()));

				// Get the results from the current agree analysis
				final List<PropertyResult> pResults = result.getPropertyResults();

				// Update the log file results with the new agree results
				for (PropertyResult pr : pResults) {

					// Find property in log file, if it exists
					// For consistency results, the name will just be "Result", so
					// let's use the JKindResult name to add context
					String propName = pr.getName();
					if (propName.equals("Result")) {
						propName = result.getName();
					}

					// Add the properties to the property set
					logFileProps.add(new AgreePropertyResult(propName, pr.getStatus(), timestamp));

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
					.write(unparseLogResult(agreeLogResult) + System.getProperty("line.separator"));
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
	 * The format is <AGREE Property Name>:<Status>:<Timestamp>.
	 * @param result - A string containing a single result from the property log file.
	 * @return AgreePropertyResult - structure containing AGREE property name,
	 * pass/fail status, and timestamp.
	 */
	public static AgreePropertyResult parseLogResult(String result) {
		String name = "";
		Status status = null;
		Date timestamp = null;

		try {

			String[] parts = result.split(LOG_RESULT_DELIMITER);
			if (parts.length < 3 || parts[0].isEmpty() || parts[parts.length - 2].isEmpty()
					|| parts[parts.length - 1].isEmpty()) {
				// This is a malformed result; ignore
				return null;
			} else {
				// Because the delimiter could be part of the property name, we may need to reassemble
				for (int i = 0; i < parts.length - 2; i++) {
					name = name + parts[i] + (i < parts.length - 3 ? LOG_RESULT_DELIMITER : "");
				}
				name = name.trim();
				status = Status.valueOf(parts[parts.length - 2].trim().toUpperCase());
				timestamp = new Date(Long.parseLong(parts[parts.length - 1].trim()));
			}
		} catch (Exception e) {
			return null;
		}

		return new AgreePropertyResult(name, status, timestamp);
	}

	/**
	 * Creates a string representing an AGREE analysis result, formatted for the log file.
	 * @param result - An AgreePropertyResult containing a single result.
	 * @return String - A log file formatted string representing the AGREE analysis result.
	 */
	private static String unparseLogResult(AgreePropertyResult result) {
		return result.getName() + LOG_RESULT_DELIMITER + result.getStatus().toString() + LOG_RESULT_DELIMITER
				+ Long.toString(result.getTimestamp().getTime());
	}

}
