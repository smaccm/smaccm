package com.rockwellcollins.atc.agree.validation;

import java.util.ArrayList;
import java.util.List;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.SpecStatement;

public class EqCycleFinder {

	public static List<EqStatement> noDelayCycles(AgreeContract contract) {
		List<EqStatement> eqs = new ArrayList<>();
		for (SpecStatement spec : contract.getSpecs()) {
			if (spec instanceof EqStatement) {
				eqs.add((EqStatement) spec);
			}
		}
		return getFirstCycle(eqs);
	}

	private static List<EqStatement> getFirstCycle(List<EqStatement> eqs) {
		// TODO Auto-generated method stub
		return null;
	}

}
