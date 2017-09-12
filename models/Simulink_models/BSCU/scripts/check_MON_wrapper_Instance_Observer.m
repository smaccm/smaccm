%% Initialize variables
src_path = 'C:\Users\jdbackes\git\smaccm\models\Simulink_models\BSCU\models\MON_sl.slx';
destMdlName = 'MON_sl_after.slx';
verifySysName = 'MON_Functional';
scriptName = 'check_MON_wrapper_Instance.m';
[dir_path,originalMdlNameNoExtension,~] = fileparts(src_path);
[~,updatedMdlNameNoExtension,~] = fileparts(destMdlName);
%% Copy model if requested and open system
dest_path = fullfile(dir_path,destMdlName);
if(~strcmp(originalMdlNameNoExtension,updatedMdlNameNoExtension))
  copyfile(src_path,dest_path,'f');
end
load_system(src_path);
open_system(dest_path);
%% Add Verification Block
dest_sys = regexp(destMdlName,'(\w+).(\w+)', 'tokens', 'once');
dest_sys = dest_sys{1};
sysToVerify = strcat(dest_sys, '/', verifySysName);
code = fileread(scriptName);
%% Find Name
name = regexp(code,'function\s+(\w+)\s*\(','tokens','once');
name = name{1};
%% Add Block
parent = get_param(sysToVerify,'Parent');
src = 'simulink/User-Defined Functions/MATLAB Function';
dst = horzcat(parent,'/',name);
sysThatVerifies = add_block(src,dst,...
'MakeNameUnique','on',...
'MaskType','VerificationSubsystem',...
'Position',[560 450 695 500]);
sysThatVerifies = getfullname(sysThatVerifies);
%% Add Code
root = sfroot;
chart = root.find('-isa','Stateflow.EMChart','Path',sysThatVerifies);
chart.Script = code;
%% Connect I/O
src = get_param(sysToVerify,'LineHandles');
src = horzcat(src.Inport,src.Outport);
src = arrayfun(@(x) get_param(x,'SrcPortHandle'),src);
dst = get_param(sysThatVerifies,'PortHandles');
dst = horzcat(dst.Inport,dst.Outport);
for i = 1:numel(src)
  add_line(parent,src(i),dst(i),'autorouting','on');
end
save_system;
