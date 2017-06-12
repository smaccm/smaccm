package com.rockwellcollins.atc.tcg.util;

import com.rockwellcollins.atc.tcg.views.TcgMultiStatus;

import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;

public class TcgResultsUtil {
	public static TcgMultiStatus getMultiStatus(AnalysisResult result) {
		if (result instanceof CompositeAnalysisResult) {
			return new TcgMultiStatus(((CompositeAnalysisResult) result).getMultiStatus());
		} else if (result instanceof JKindResult) {
			return new TcgMultiStatus(((JKindResult) result).getMultiStatus());
		} else {
			return null;
		}
	}
}
