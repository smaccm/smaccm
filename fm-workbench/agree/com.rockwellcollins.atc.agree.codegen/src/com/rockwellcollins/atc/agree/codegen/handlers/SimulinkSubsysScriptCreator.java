package com.rockwellcollins.atc.agree.codegen.handlers;

import java.util.ArrayList;
import java.util.List;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPort;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;
import jkind.Assert;

public class SimulinkSubsysScriptCreator extends ScriptCreator{
	
	final String outputDir;
	final String mdlName;
	final String subSysName;
	List<MATLABPort> ports = new ArrayList<>();
	List<String> inportnames = new ArrayList<>();
	List<String> inporttypes = new ArrayList<>();
	List<String> outportnames = new ArrayList<>();
	List<String> outporttypes = new ArrayList<>();
	
	public SimulinkSubsysScriptCreator (String outputDir, String mdlName, String subSysName, List<MATLABPort> ports) {
		Assert.isNotNull(outputDir);		
		Assert.isNotNull(mdlName);
		Assert.isNotNull(subSysName);
		Assert.isNotNull(ports);
		this.mdlName = mdlName;
		this.subSysName = subSysName;
		this.outputDir = outputDir;
		this.ports = ports;
		preparePorts();
		createScript();
	}
	
	private void preparePorts(){
    	for(MATLABPort port : ports){
    		if (port.direction.equals("in")){
    			inportnames.add(port.name);
    			inporttypes.add(portTypeNameUpdate(port.type));
    		}
    		else{
    			outportnames.add(port.name);
    			outporttypes.add(portTypeNameUpdate(port.type));			
    		}
    	}
	}
	
	private String portTypeNameUpdate(MATLABType portType){
		String typeName = "";
		String originalTypeName = portType.toString();
		if(portType instanceof MATLABBusType){
			typeName += "Bus: ";
		}
	
		String[] parts = originalTypeName.split("__");	
		int size = parts.length;
		if((originalTypeName.endsWith("__Impl")) && (size >= 2)){
			typeName += parts[size-2];
		}
		else if(size >= 1){
			typeName += parts[size-1];
		}
		else{
			typeName += originalTypeName;
		}
		return typeName;
	}
	
	protected void createScript() {
		boolean firstPort = true;
		write("%");
		newline();
		write("%This generates (from scratch) an implementation model for the given subsystem");
		newline();
		write("%");
		newline();
		newline();
		write("%% Initialize variables %%");
		newline();
		write("%these come from the dialog");
		newline();
		write("% 'Output Directory'");
		newline();
		write("dir_path = "+"'"+outputDir+"';");		
		newline();
		write("% 'Model to Insert Observer'");
		newline();
		write("[~,mdlNameNoExtension,~] = fileparts('" + mdlName + "');");
		newline();
		write("% 'Subsystem to verify'");
		newline();
		write("subsysName = "+"'"+subSysName+"';");
		newline();
		write("% these are computed or built from AADL model elements");
		newline();
		write("mdl_path = fullfile(dir_path,mdlNameNoExtension);");
		newline();
		write("%% the real I/O from AADL");
		newline();
		firstPort = true;
		write("inportnames = {");
    	for(String inName : inportnames){
    		if(!firstPort){
    			write(", ");
    		}    		
    		write("'"+inName+"'");
    		firstPort = false;
    	}		
    	write("};");
		newline();
		firstPort = true;
		write("inporttypes = {");
    	for(String inType : inporttypes){
    		if(!firstPort){
    			write(", ");
    		}    		
    		write("'"+inType+"'");
    		firstPort = false;
    	}		
    	write("};");
		newline();
		newline();
		firstPort = true;
		write("outportnames = {");
    	for(String outName : outportnames){
    		if(!firstPort){
    			write(", ");
    		}    		
    		write("'"+outName+"'");
    		firstPort = false;
    	}		
    	write("};");
		newline();
		firstPort = true;
		write("outporttypes = {");
    	for(String outType : outporttypes){
    		if(!firstPort){
    			write(", ");
    		}    		
    		write("'"+outType+"'");
    		firstPort = false;
    	}		
    	write("};");
		newline();
		newline(); 
		write("% screen positioning of the generated blocks");
		newline();
		write("px = 300;       % subsystem x position");
		newline();		
		write("py = 150;       % subsystem y position");
		newline();
		write("offset = 200;   % distance from the subsystem to the port blocks");
		newline();		
		newline();
		write("% design goal:  everything after this point uses the variables above");
		newline();
		newline();
		write("%% create the new implmentation model and open it");
		newline();
		write("try");
		newline();		
		write("    new_system(mdlNameNoExtension,'ErrorIfShadowed');");
		newline();
		write("catch");
		newline();
		write("    errmsg = ['The model already exists; please ' ...");
		newline();	
		write("              'delete the existing model if you ' ...");
		newline();
		write("              'wish to recreate it.'];");
		newline();		
		write("    errordlg(errmsg,'Creation Error');");
		newline();		
		write("    return;");
		newline();	
		write("end");
		newline();			
		newline();
		write("%% add the subsystem block");
		newline();
		write("dst = horzcat(bdroot,'/',subsysName);");
		newline();
		write("bh = add_block('simulink/Ports & Subsystems/Subsystem',dst);");
		newline();
		newline();
		write("% delete the default IO ports (warning: assumed implmentation");
		newline();
		write("% of the library block here; may not work in future versions)");
		newline();
		write("delete_line(dst,'In1/1','Out1/1');");
		newline();
		write("delete_block(horzcat(dst,'/In1'));");
		newline();
		write("delete_block(horzcat(dst,'/Out1'));");
		newline();
		newline();   
		write("% reposition and resize the subsystem based on number of ports");
		newline();
		newline();
		write("set_param(bh, 'position', [px,py,px+200,py+36+max(numel(inportnames),numel(outportnames))*36]);");
		newline();		
		newline();
		write("%% add the actual I/O specified in AADL model");
		newline();
		newline();		
		write("%% input ports and wiring");		    
		newline();	
		write("for i = 1:numel(inportnames)");		    
		newline();			
		write("    % inside the subsystem");		    
		newline();			
		write("    add_block('simulink/Sources/In1',horzcat(dst,'/', inportnames{i}));");		    
		newline();			    
		write("    % at the root level");		    
		newline();			    
		write("    rh = add_block('simulink/Sources/In1',horzcat(bdroot,'/', inportnames{i}));");		    
		newline();			    		        
		newline();	
		write("    % set the port (signal) type");		    
		newline();	
		write("    set_param(rh,'OutDataTypeStr', inporttypes{i});");		    
		newline();			    
		write("    % now connect them with wires");		    
		newline();			    
		write("    SubsysPortHandles = get_param(dst,'PortHandles');");		    
		newline();			    
		write("    RootPortHandles = get_param(rh, 'PortHandles');");		    
		newline();		    
		write("    lh = add_line(bdroot,RootPortHandles.Outport(1), SubsysPortHandles.Inport(i));");		    
		newline();			    
		newline();		    
		newline();	
		write("    % name the signal line");		    
		newline();	
		write("    set_param(lh,'Name',inportnames{i})");		    
		newline();			    
		write("end");		    
		newline();			    
		newline();		    
		write("%    SubsysPortHandles = get_param(dst,'PortHandles');");		    
		newline();
		newline();		
		write("%% output ports and wiring");		    
		newline();		    
		write("for i = 1:numel(outportnames)");		    
		newline();		
		write("    % inside the subsystem");		    
		newline();		
		write("    add_block('simulink/Sinks/Out1',horzcat(dst,'/', outportnames{i}));");		    
		newline();			    
		write("    % at the root level");		    
		newline();			    
		write("    rh = add_block('simulink/Sinks/Out1',horzcat(bdroot,'/', outportnames{i}));");		    
		newline();		    	    
		write("    % set the port (signal) type");		    
		newline();		        
		write("    set_param(rh,'OutDataTypeStr', outporttypes{i});");		    
		newline();			    
		newline();			    
		write("    % now connect them with wires");		    
		newline();			    
		write("    SubsysPortHandles = get_param(dst,'PortHandles');");		    
		newline();			    
		write("    RootPortHandles = get_param(rh,'PortHandles');");		    
		newline();		    
		write("    lh = add_line(bdroot,SubsysPortHandles.Outport(i),RootPortHandles.Inport(1));");		    
		newline();		    
		newline();			    
		write("    % name the signal line");		    
		newline();		    
		write("    set_param(lh,'Name',inportnames{i})");		    
		newline();		    
		write("end");		    
		newline();			        
		newline();
		write("%% align the port blocks to the subsystem block");
		newline();
		newline();
		write("% get the subsystem port handle positions and iterate each one");
		newline();		
		write("pc = get_param(bh,'PortConnectivity');");
		newline();			
		newline();			
		write("for i = 1:numel(pc)");
		newline();	
		newline();		
		write("    % position of port connector");
		newline();	
		write("    y = pc(i).Position(2);");
		newline();	
		write("    x = pc(i).Position(1);");
		newline();	
		newline();
		write("    % port block handle and size");
		newline();	
		write("    if isempty(pc(i).SrcBlock)");
		newline();
		write("        ph = pc(i).DstBlock;");
		newline();
		write("        off = -(offset-50);");
		newline();
		write("    else");
		newline();
		write("        ph = pc(i).SrcBlock;");
		newline();
		write("        off = offset;");
		newline();
		write("    end");
		newline();
		newline();
		write("    % port block dimmensions");
		newline();
		write("    pp = get_param(ph,'Position');");
		newline();
		write("    w = pp(3)-pp(1);");
		newline();
		write("    h = pp(4)-pp(2);");
		newline();
		newline();
		write("    % adjust position of port block");
		newline();
		write("    set_param(ph,'Position',[x-off,y-h/2,x-off+w,y+h-h/2]);");
		newline();
		write("end");
		newline();
		newline();
		write("%% set model parameters to desired settings");		    
		newline();			   
		newline();
		write("set_param(bdroot,'Solver','FixedStepDiscrete');");		    
		newline();	
		write("set_param(bdroot,'ShowPortDataTypes', 'on');");		    
		newline();			
		write("set_param(bdroot,'SaveFormat','Structure');");		    
		newline();		
		write("set_param(bdroot, 'FixedStep','10');");		    
		newline();		
		newline();		
		write("%% all done");		    
		newline();		
		write("open_system(bdroot);");		    
		newline();		
		write("save_system;");		    
		newline();		
	}
	
}
