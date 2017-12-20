package com.rockwellcollins.atc.agree.analysis;

import java.util.List;

import jkind.api.results.JKindResult;
import jkind.api.results.Renaming;

public class ConsistencyResult extends JKindResult {

	public ConsistencyResult(String name, List<String> properties, List<Boolean> invertStatus, Renaming renaming) {
		super(name, properties, invertStatus, renaming);
	}
}
