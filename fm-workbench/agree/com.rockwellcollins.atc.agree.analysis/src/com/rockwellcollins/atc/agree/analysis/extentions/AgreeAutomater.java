package com.rockwellcollins.atc.agree.analysis.extentions;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;

public interface AgreeAutomater {
    public AgreeProgram transform(AgreeProgram program);
}
