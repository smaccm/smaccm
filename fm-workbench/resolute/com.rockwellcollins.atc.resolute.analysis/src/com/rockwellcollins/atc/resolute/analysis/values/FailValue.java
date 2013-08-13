package com.rockwellcollins.atc.resolute.analysis.values;

public class FailValue extends ResoluteValue {
	@Override
	public boolean isFail() {
		return true;
	}

	@Override
	public String toString() {
		return "FAIL";
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof FailValue;
	}
}
