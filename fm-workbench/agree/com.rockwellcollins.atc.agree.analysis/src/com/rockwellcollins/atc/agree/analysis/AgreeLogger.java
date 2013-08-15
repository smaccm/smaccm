package com.rockwellcollins.atc.agree.analysis;

public class AgreeLogger {

    StringBuilder logStr = new StringBuilder();

    public void logWarning(String str) {
        logStr.append("[WARING]: " + str + "\n");
    }

    public void logError(String str) {
        logStr.append("[ERROR]: " + str + "\n");
    }

    public String getLog() {
        return logStr.toString();
    }

}
