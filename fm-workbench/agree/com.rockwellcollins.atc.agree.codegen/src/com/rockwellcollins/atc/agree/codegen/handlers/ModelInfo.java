package com.rockwellcollins.atc.agree.codegen.handlers;


public class ModelInfo {
	public final String originalModelName;
	public final String updatedModelName;
	public final String subsystemName;

	public ModelInfo() {
		this.originalModelName = "";
		this.updatedModelName = "";
		this.subsystemName = "";
	}
	
	public ModelInfo(String originalModelName, String updatedModelName, String subsystemName) {
		this.originalModelName = originalModelName;
		this.updatedModelName = updatedModelName;
		this.subsystemName = subsystemName;
	}
}
