package com.rockwellcollins.atc.agree.codegen.handlers;


public class ModelInfo {
	public final String outputDirPath;
	public final String originalModelName;
	public final String updatedModelName;
	public final String subsystemName;

	public ModelInfo() {
		this.outputDirPath = "";
		this.originalModelName = "";
		this.updatedModelName = "";
		this.subsystemName = "";
	}
	
	public ModelInfo(String outputDirPath, String originalModelName, String updatedModelName, String subsystemName) {
		this.outputDirPath = outputDirPath;
		this.originalModelName = originalModelName;
		this.updatedModelName = updatedModelName;
		this.subsystemName = subsystemName;
	}
}
