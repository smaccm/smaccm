package com.rockwellcollins.atc.agree.codegen.handlers;


public class ModelInfo {
	public final String outputDirPath;
	public final String originalModelName;
	public final String updatedModelName;
	public final String subsystemName;
	public final boolean exportPressed;
	public final boolean updatePressed;
	public final boolean verifyPressed;

	public ModelInfo() {
		this.outputDirPath = "";
		this.originalModelName = "";
		this.updatedModelName = "";
		this.subsystemName = "";
		this.exportPressed = false;
		this.updatePressed = false;
		this.verifyPressed = false;
	}
	
	public ModelInfo(String outputDirPath, String originalModelName,
			String updatedModelName, String subsystemName) {
		this.outputDirPath = outputDirPath;
		this.originalModelName = originalModelName;
		this.updatedModelName = updatedModelName;
		this.subsystemName = subsystemName;
		this.exportPressed = false;
		this.updatePressed = false;
		this.verifyPressed = false;
	}
	
	public ModelInfo(String outputDirPath, String originalModelName,
			String updatedModelName, String subsystemName,
			boolean exportPressed, boolean updatePressed, boolean verifyPressed) {
		this.outputDirPath = outputDirPath;
		this.originalModelName = originalModelName;
		this.updatedModelName = updatedModelName;
		this.subsystemName = subsystemName;
		this.exportPressed = exportPressed;
		this.updatePressed = updatePressed;
		this.verifyPressed = verifyPressed;
	}
}
