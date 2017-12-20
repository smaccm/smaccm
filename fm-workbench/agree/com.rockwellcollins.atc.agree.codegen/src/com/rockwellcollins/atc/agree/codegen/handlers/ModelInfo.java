package com.rockwellcollins.atc.agree.codegen.handlers;

public class ModelInfo {
	public final String outputDirPath;
	public final String implMdlPath;
	public final String verifyMdlName;
	public final String subsystemName;
	public final boolean exportPressed;
	public final boolean generatePressed;
	public final boolean updatePressed;
	public final boolean verifyPressed;

	public ModelInfo() {
		outputDirPath = "";
		implMdlPath = "";
		verifyMdlName = "";
		subsystemName = "";
		exportPressed = false;
		generatePressed = false;
		updatePressed = false;
		verifyPressed = false;
	}

	public ModelInfo(String outputDirPath, String originalModelName, String updatedModelName, String subsystemName) {
		this.outputDirPath = outputDirPath;
		implMdlPath = originalModelName;
		verifyMdlName = updatedModelName;
		this.subsystemName = subsystemName;
		exportPressed = false;
		generatePressed = false;
		updatePressed = false;
		verifyPressed = false;
	}

	public ModelInfo(String outputDirPath, String originalModelName, String updatedModelName, String subsystemName,
			boolean exportPressed, boolean generatePressed, boolean updatePressed, boolean verifyPressed) {
		this.outputDirPath = outputDirPath;
		implMdlPath = originalModelName;
		verifyMdlName = updatedModelName;
		this.subsystemName = subsystemName;
		this.exportPressed = exportPressed;
		this.generatePressed = generatePressed;
		this.updatePressed = updatePressed;
		this.verifyPressed = verifyPressed;
	}
}
