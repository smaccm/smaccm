package com.rockwellcollins.atc.agree.codegen.handlers;

import jkind.Assert;

public class SLDVInvocationScriptCreator extends ScriptCreator{
	
	final String origMdlName;
	final String updatedMdlName;
	final String subSysName;
	final String scriptName;
	final String updatedMdlNameNoExtension;
	
	
	public SLDVInvocationScriptCreator (String origMdlName, String updatedMdlName, String subSysName, String scriptName) {
		Assert.isNotNull(origMdlName);
		Assert.isNotNull(updatedMdlName);
		Assert.isNotNull(subSysName);
		Assert.isNotNull(scriptName);
		this.origMdlName = origMdlName;
		this.updatedMdlName = updatedMdlName;
		this.subSysName = subSysName;
		this.scriptName = scriptName;
		this.updatedMdlNameNoExtension = updatedMdlName.substring(0,updatedMdlName.lastIndexOf('.'));
		createScript();
	}
	
	public void createScript() {
		write("addVerificationSubSys('"+ origMdlName + "',");
		write("'"+updatedMdlName+"',");
		write("'"+subSysName+"',");
		write("'"+scriptName+"');");
		newline();
		write("save_system;");
		newline();
		write("opts = sldvoptions;");
		newline();
		write("opts.Mode = 'PropertyProving';");
		newline();
		write("opts.Assertions = 'EnableAll';");
		newline();
		write("opts.AutomaticStubbing = 'on';");
		newline();
		write("opts.ProofAssumptions = 'EnableAll';");
		newline();
		write("opts.ProvingStrategy = 'Prove';");
		newline();
		write("opts.SaveHarnessModel = 'on';");
		newline();
		write("opts.SaveReport = 'on';");
		newline();
		write("opts.OutputDir = './sldv_output';");
		newline();
		write("[status, files] = sldvrun('"+updatedMdlNameNoExtension+"', opts);");
		newline();
	}
	
}
