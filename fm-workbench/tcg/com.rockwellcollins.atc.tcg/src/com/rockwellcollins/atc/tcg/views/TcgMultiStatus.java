package com.rockwellcollins.atc.tcg.views;

import java.util.EnumMap;
import java.util.StringJoiner;

import jkind.api.results.MultiStatus;
import jkind.api.results.Status;

public class TcgMultiStatus {
	final private MultiStatus ms;
	
	public TcgMultiStatus(MultiStatus ms) {
		this.ms = ms;
	}

	public int getCount(Status status) {
		return ms.getCount(status);
	}

	public void add(Status status) {
		ms.add(status);
	}

	public void add(TcgMultiStatus other) {
		ms.add(other.ms);
	}

	public void remove(Status status) {
		ms.remove(status);
	}

	public void remove(TcgMultiStatus other) {
		ms.remove(other.ms);
	}

	private static final Status[] PRECEDENCE = new Status[] { Status.WORKING, Status.WAITING,
			Status.ERROR, Status.INVALID, Status.INCONSISTENT, Status.UNKNOWN, Status.CANCELED,
			Status.VALID };

	public Status getOverallStatus() {
		for (Status status : PRECEDENCE) {
			if (getCount(status) > 0) {
				return status;
			}
		}
		return null;
	}

	public String tcgStatus(Status status) {
		switch (status) {
		case INVALID: return "test case found" ;
		case VALID: return "no test case possible" ; 
		default: return status.toString() ;
		}
	}
	
	@Override
	public String toString() {
		StringJoiner text = new StringJoiner(", ");
		for (Status status : PRECEDENCE) {
			int count = getCount(status);
			if (count > 0) {
				text.add(count + " " + tcgStatus(status));
			}
		}

		return text.toString();
	}
}
