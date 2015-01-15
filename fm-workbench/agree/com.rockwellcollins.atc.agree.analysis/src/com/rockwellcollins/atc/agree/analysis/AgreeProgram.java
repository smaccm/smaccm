package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.List;

import jkind.lustre.Program;

public class AgreeProgram {
	public Program guaranteeProgram;
	public Program consistProgram;
    public Program assumeProgram;

	public List<String> assumeGuaranteeProps = new ArrayList<>();
	public List<String> consistProps = new ArrayList<>();
	
	public AgreeEmitterState state;

}
