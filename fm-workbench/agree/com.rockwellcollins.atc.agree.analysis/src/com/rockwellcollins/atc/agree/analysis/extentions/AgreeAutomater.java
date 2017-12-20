package com.rockwellcollins.atc.agree.analysis.extentions;

import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;

import jkind.api.results.AnalysisResult;

public interface AgreeAutomater {
	public AgreeProgram transform(AgreeProgram program);

	public AgreeRenaming rename(AgreeRenaming renaming);

	public AnalysisResult transformResult(AnalysisResult res);

	public AgreeLayout transformLayout(AgreeLayout layout);
}
