package com.rockwellcollins.atc.resolute.analysis.results;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResoluteResult {
	private boolean valid;
	final private List<ResoluteResult> children;

	public ResoluteResult(boolean valid, List<ResoluteResult> children) {
		this.valid = valid;
		this.children = Collections.unmodifiableList(children);
	}

	public ResoluteResult(List<ResoluteResult> children) {
		this(allValid(children), children);
	}

	public ResoluteResult(ResoluteResult... results) {
		this(Arrays.asList(results));
	}

	public ResoluteResult(boolean valid, ResoluteResult... results) {
		this(valid, Arrays.asList(results));
	}

	private static boolean allValid(List<ResoluteResult> children) {
		for (ResoluteResult child : children) {
			if (!child.isValid()) {
				return false;
			}
		}
		return true;
	}

	public boolean isValid() {
		return valid;
	}

	public List<ResoluteResult> getChildren() {
		return children;
	}

	public void setValid(boolean v) {
		this.valid = v;
	}
}
