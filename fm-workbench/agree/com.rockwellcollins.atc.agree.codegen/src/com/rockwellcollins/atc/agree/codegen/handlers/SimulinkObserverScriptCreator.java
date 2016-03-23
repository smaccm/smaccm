package com.rockwellcollins.atc.agree.codegen.handlers;

import jkind.Assert;

public class SimulinkObserverScriptCreator extends ScriptCreator{
	
	final String origMdlName;
	final String updatedMdlName;
	final String subSysName;
	final String scriptName;
	final String updatedMdlNameNoExtension;
	final boolean invokeSLDV;
	
	public SimulinkObserverScriptCreator (String origMdlName, String updatedMdlName, String subSysName, String scriptName, boolean invokeSLDV) {
		Assert.isNotNull(origMdlName);
		Assert.isNotNull(updatedMdlName);
		Assert.isNotNull(subSysName);
		Assert.isNotNull(scriptName);
		Assert.isNotNull(invokeSLDV);
		this.origMdlName = origMdlName;
		this.updatedMdlName = updatedMdlName;
		this.subSysName = subSysName;
		this.scriptName = scriptName;
		this.updatedMdlNameNoExtension = updatedMdlName.substring(0,updatedMdlName.lastIndexOf('.'));
		this.invokeSLDV = invokeSLDV;
		createScript();
	}
	
	public void createScript() {
		updateModelScript();
		if(invokeSLDV){
			invokeSLDVScript();
		}
	}
	
	public void updateModelScript() {
		write("%% Initialize variables");
		newline();
		write("src_path = "+"'"+origMdlName+"';");
		newline();
		write("destMdlName = "+"'"+updatedMdlName+"';");
		newline();
		write("verifySysName = "+"'"+subSysName+"';");
		newline();
		write("scriptName = " +"'"+scriptName+"';");
		newline();
		write("close_system(src_path,0);");
		newline();
		write("[dir_path,originalMdlNameNoExtension,~] = fileparts(src_path);");
		newline();
		write("[~,updatedMdlNameNoExtension,~] = fileparts(destMdlName);");
		newline();
		write("%% Copy model if requested and open system");
		newline();
		write("dest_path = fullfile(dir_path,destMdlName);");
		newline();
		write("if(~strcmp(originalMdlNameNoExtension,updatedMdlNameNoExtension))");
		newline();
		write("  close_system(dest_path,0);");
		newline();
		write("  copyfile(src_path,dest_path,'f');");
		newline();
		write("end");
		newline();
		write("load_system(src_path);");
		newline();
		write("open_system(dest_path);");
		newline();
		write("%% Add Verification Block");
		newline();
		write("dest_sys = regexp(destMdlName,'(\\w+).(\\w+)', 'tokens', 'once');");
		newline();
		write("dest_sys = dest_sys{1};");
		newline();
		write("sysToVerify = strcat(dest_sys, '/', verifySysName);");
		newline();
		write("code = fileread(scriptName);");
		newline();
		write("%% Find Name");
		newline();
		write("name = regexp(code,'function\\s+(\\w+)\\s*\\(','tokens','once');");
		newline();
		write("name = name{1};");
		newline();
		write("%% Add Block");
		newline();
		write("parent = get_param(sysToVerify,'Parent');");
		newline();
		write("src = 'simulink/User-Defined Functions/MATLAB Function';");
		newline();
		write("dst = horzcat(parent,'/',name);");
		newline();
		write("sysThatVerifies = add_block(src,dst,...");
		newline();
		write("'MakeNameUnique','on',...");
		newline();
		write("'MaskType','VerificationSubsystem',...");
		newline();
		write("'Position',[560 450 695 500]);");
		newline();
		write("sysThatVerifies = getfullname(sysThatVerifies);");
		newline();
		write("%% Add Code");
		newline();
		write("root = sfroot;");
		newline();
		write("chart = root.find('-isa','Stateflow.EMChart','Path',sysThatVerifies);");
		newline();
		write("chart.Script = code;");
		newline();
		write("%% Connect I/O");
		newline();
		write("src = get_param(sysToVerify,'LineHandles');");
		newline();
		write("src = horzcat(src.Inport,src.Outport);");
		newline();
		write("src = arrayfun(@(x) get_param(x,'SrcPortHandle'),src);");
		newline();
		write("dst = get_param(sysThatVerifies,'PortHandles');");
		newline();
		write("dst = horzcat(dst.Inport,dst.Outport);");
		newline();
		write("for i = 1:numel(src)");
		newline();
		write("  add_line(parent,src(i),dst(i),'autorouting','on');");
		newline();
		write("end");
		newline();
		write("save_system;");
		newline();
	}
	
	public void invokeSLDVScript() {
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
