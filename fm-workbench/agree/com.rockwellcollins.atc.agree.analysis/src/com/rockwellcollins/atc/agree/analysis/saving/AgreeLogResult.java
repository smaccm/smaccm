package com.rockwellcollins.atc.agree.analysis.saving;

import java.util.List;

public class AgreeLogResult {

	private String name = null;
	private String result = null;
	private Long timestamp = null;
	private String hashcode = null;
	private List<AgreeLogResult> analyses = null;

	public AgreeLogResult() {

	}

	public String getName() {
		return name;
	}

	public String getResult() {
		return result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getHashcode() {
		return hashcode;
	}

	public List<AgreeLogResult> getAnalyses() {
		return analyses;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

	public void setAnalyses(List<AgreeLogResult> analyses) {
		this.analyses = analyses;
	}

}
