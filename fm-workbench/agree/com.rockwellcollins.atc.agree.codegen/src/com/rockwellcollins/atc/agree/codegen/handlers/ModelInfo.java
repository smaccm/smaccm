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
		this.outputDirPath = "";
		this.implMdlPath = "";
		this.verifyMdlName = "";
		this.subsystemName = "";
		this.exportPressed = false;
		this.generatePressed = false;
		this.updatePressed = false;
		this.verifyPressed = false;
	}
	
	public ModelInfo(String outputDirPath, String originalModelName,
			String updatedModelName, String subsystemName) {
		this.outputDirPath = outputDirPath;
		this.implMdlPath = originalModelName;
		this.verifyMdlName = updatedModelName;
		this.subsystemName = subsystemName;
		this.exportPressed = false;
		this.generatePressed = false;
		this.updatePressed = false;
		this.verifyPressed = false;
	}
	
	public ModelInfo(String outputDirPath, String originalModelName,
			String updatedModelName, String subsystemName,
			boolean exportPressed, boolean generatePressed, boolean updatePressed, boolean verifyPressed) {
		this.outputDirPath = outputDirPath;
		this.implMdlPath = originalModelName;
		this.verifyMdlName = updatedModelName;
		this.subsystemName = subsystemName;
		this.exportPressed = exportPressed;
		this.generatePressed = generatePressed;
		this.updatePressed = updatePressed;
		this.verifyPressed = verifyPressed;
	}
}
