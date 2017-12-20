package com.rockwellcollins.atc.agree.analysis;

public class AgreeLogger {
	private static StringBuilder log = new StringBuilder();
	private static String newline = System.getProperty("line.separator");

	public static void logWarning(String message) {
		log.append("[WARNING]: ");
		log.append(message);
		log.append(newline);
	}

	public static void logError(String message) {
		log.append("[ERROR]: ");
		log.append(message);
		log.append(newline);
	}

	public static String getLog() {
		return log.toString();
	}

	@Override
	public String toString() {
		return log.toString();
	}
}
