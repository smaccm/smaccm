package com.rockwellcollins.atc.agree.codegen.handlers;

import jkind.Assert;

public class MATLABInvocationScriptCreator extends ScriptCreator {
	
	final String dirPath;
	final String scriptName;
	
	public MATLABInvocationScriptCreator (String dirPath, String scriptName) {
		Assert.isNotNull(dirPath);
		Assert.isNotNull(scriptName);
		this.dirPath = dirPath;
		this.scriptName = scriptName;
		createScript();
	}
	
	public void createScript() {
		write("cd "+ dirPath);
		newline();
		write("matlab -nodisplay -nosplash -nodesktop -r ");
		write("run('"+dirPath+"\\"+scriptName);
		write("')");
	}
	
}
