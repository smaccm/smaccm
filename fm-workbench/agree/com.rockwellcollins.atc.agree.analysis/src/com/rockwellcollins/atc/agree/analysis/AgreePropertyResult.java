package com.rockwellcollins.atc.agree.analysis;

import jkind.api.results.Status;

public class AgreePropertyResult {

	private String name;
	private Status status;
	private long timestamp;
	private String hash;

//	public AgreePropertyResult(String name, Status status, long timestamp) {
	public AgreePropertyResult(String name, Status status, long timestamp, String hash) {
		this.name = name;
		this.status = status;
		this.timestamp = timestamp;
		this.hash = hash;
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getHash() {
		return hash;
	}

}
