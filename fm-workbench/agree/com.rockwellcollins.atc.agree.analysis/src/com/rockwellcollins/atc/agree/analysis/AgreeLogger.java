package com.rockwellcollins.atc.agree.analysis;

public class AgreeLogger {
    private StringBuilder log = new StringBuilder();
    private static String newline = System.getProperty("line.separator");

    public void logWarning(String message) {
        log.append("[WARNING]: ");
        log.append(message);
        log.append(newline);
    }

    public void logError(String message) {
        log.append("[ERROR]: ");
        log.append(message);
        log.append(newline);
    }

    @Override
    public String toString() {
        return log.toString();
    }
}
