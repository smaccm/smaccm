package com.rockwellcollins.atc.agree.codegen.ast;

public enum MATLABFunctionName {
	IMPLIES ("impliesFunction"),
	IF ("ifFunction"),
	ARROW ("arrowFunction");
	
	private String str;
	
	private MATLABFunctionName(String str) {
		this.str = str;
	}
	
	@Override
	public String toString() {
		return str;
	}
	
	public static MATLABFunctionName fromName(String name) {
		for (MATLABFunctionName functionName : MATLABFunctionName.values()) {
			if (functionName.name().equals(name)) {
				return functionName;
			}
		}
		return null;
	}
}
