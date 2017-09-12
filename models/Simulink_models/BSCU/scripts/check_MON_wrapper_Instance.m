function check_MON_wrapper_Instance(Panel, CMD_From_COM, Sync_From, Pedal, Fail, Sync_To, Status)

persistent pre_c_Agree_Nodes__Duration_0
persistent pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0
persistent pre_mode_Agree_Nodes__Brake_Mode_0
persistent pre_Active
persistent first_time
persistent pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0
persistent pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0
persistent pre_r_Agree_Nodes__Latch_0

Primary_Side = false;
max_Agree_Nodes__Brake_Mode_0 = Panel.MAX;
p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 = max_Agree_Nodes__Brake_Mode_0;

if isempty(pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0)
	pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 = (false);
end


if isempty(first_time)
	first_time = true;
end

r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 = arrowFunction(first_time,false, (not(pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0) && p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0));
lo_Agree_Nodes__Brake_Mode_0 = Panel.LO;
p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 = lo_Agree_Nodes__Brake_Mode_0;

if isempty(pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0)
	pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 = (false);
end

r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 = arrowFunction(first_time,false, (not(pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0) && p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0));
med_Agree_Nodes__Brake_Mode_0 = Panel.MED;
p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 = med_Agree_Nodes__Brake_Mode_0;

if isempty(pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0)
	pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 = (false);
end

r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 = arrowFunction(first_time,false, (not(pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0) && p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0));

if isempty(pre_mode_Agree_Nodes__Brake_Mode_0)
	pre_mode_Agree_Nodes__Brake_Mode_0 = int32(0);
end

r_Agree_Nodes__Brake_Mode_0 = arrowFunction(first_time,int32(1), ifFunction((isequal(pre_mode_Agree_Nodes__Brake_Mode_0, int32(2)) && r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0), int32(1), ifFunction((isequal(pre_mode_Agree_Nodes__Brake_Mode_0, int32(3)) && r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0), int32(1), ifFunction((isequal(pre_mode_Agree_Nodes__Brake_Mode_0, int32(4)) && r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0), int32(1), ifFunction(((pre_mode_Agree_Nodes__Brake_Mode_0 ~= int32(2)) && r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0), int32(2), ifFunction(((pre_mode_Agree_Nodes__Brake_Mode_0 ~= int32(3)) && r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0), int32(3), ifFunction(((pre_mode_Agree_Nodes__Brake_Mode_0 ~= int32(4)) && r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0), int32(4), pre_mode_Agree_Nodes__Brake_Mode_0)))))));
Mode = r_Agree_Nodes__Brake_Mode_0;
p_Agree_Nodes__Duration_0 = not(isequal(Status.Cmd, CMD_From_COM));
b_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0 = p_Agree_Nodes__Duration_0;
i_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0 = ifFunction(b_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0, int32(1), int32(0));

if isempty(pre_c_Agree_Nodes__Duration_0)
	pre_c_Agree_Nodes__Duration_0 = int32(0);
end

c_Agree_Nodes__Duration_0 = arrowFunction(first_time,i_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0, ifFunction(p_Agree_Nodes__Duration_0, (pre_c_Agree_Nodes__Duration_0 + int32(1)), int32(0)));
p_Agree_Nodes__Latch_0 = (c_Agree_Nodes__Duration_0 > int32(15));

if isempty(pre_r_Agree_Nodes__Latch_0)
	pre_r_Agree_Nodes__Latch_0 = (false);
end

r_Agree_Nodes__Latch_0 = arrowFunction(first_time,p_Agree_Nodes__Latch_0, (p_Agree_Nodes__Latch_0 || pre_r_Agree_Nodes__Latch_0));
Error = r_Agree_Nodes__Latch_0;

if isempty(pre_Active)
	pre_Active = (false);
end

Active = arrowFunction(first_time,not(Sync_From.Active), ifFunction(Error, false, ifFunction((((pre_Active && Sync_From.Active) && not(Sync_From.Error)) && not(Primary_Side)), false, ifFunction((not(pre_Active) && (not(Sync_From.Active) || Sync_From.Error)), true, pre_Active))));
pedal_Agree_Nodes__Brake_Cmd_0 = Pedal;
mode_Agree_Nodes__Brake_Cmd_0 = Mode;
r_Agree_Nodes__Brake_Cmd_0 = ifFunction(isequal(mode_Agree_Nodes__Brake_Cmd_0, int32(2)), int32(170), ifFunction(isequal(mode_Agree_Nodes__Brake_Cmd_0, int32(3)), int32(340), ifFunction(isequal(mode_Agree_Nodes__Brake_Cmd_0, int32(4)), int32(510), pedal_Agree_Nodes__Brake_Cmd_0)));
GUARANTEE0 = impliesFunction(not(Fail), isequal(Status.Cmd, r_Agree_Nodes__Brake_Cmd_0));
GUARANTEE1 = isequal(Status.Error, Error);
GUARANTEE2 = isequal(Sync_To.Error, Error);
GUARANTEE3 = isequal(Status.Active, Active);
GUARANTEE4 = isequal(Sync_To.Active, Active);
mode_Agree_Nodes__Brake_Mode_0 = Mode;
nodeLemma0_Agree_Nodes__Duration_0 = (c_Agree_Nodes__Duration_0 >= int32(0));
pre_c_Agree_Nodes__Duration_0 = c_Agree_Nodes__Duration_0;
pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 = p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0;
pre_mode_Agree_Nodes__Brake_Mode_0 = mode_Agree_Nodes__Brake_Mode_0;
pre_Active = Active;
first_time = false;
pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 = p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0;
pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 = p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0;
pre_r_Agree_Nodes__Latch_0 = r_Agree_Nodes__Latch_0;

sldv.prove(GUARANTEE0)

sldv.prove(GUARANTEE1)

sldv.prove(GUARANTEE2)

sldv.prove(GUARANTEE3)

sldv.prove(GUARANTEE4)

sldv.prove(nodeLemma0_Agree_Nodes__Duration_0)

end

function output = ifFunction(cond, ifBranch, elseBranch)
if (cond)
	output = ifBranch;
else
	output = elseBranch;
end
end

function output = arrowFunction(first_time, left, right)
if (first_time)
	output = left;
else
	output = right;
end
end

function output = impliesFunction(left, right)
	output = (not(left) || right);
end

