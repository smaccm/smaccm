package com.rockwellcollins.atc.agree.analysis;

import java.util.Date;

import jkind.api.results.Status;

public class AgreePropertyResult {

	private String name;
	private Status status;
	private Date timestamp;

	public AgreePropertyResult(String name, Status status, Date timestamp) {
		this.name = name;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
