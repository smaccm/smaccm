Panel = Simulink.Signal;
Panel.DataType = 'Bus: PanelMsgImpl';
Panel.Dimensions = 1;
Panel.Complexity = 'real';
Panel.SamplingMode = 'Sample based';

Pedal = Simulink.Signal;
Pedal.DataType = 'int32';
Pedal.Min = 0;
Pedal.Max = hex2dec('7FFFFFFF');
Pedal.Dimensions = 1;
Pedal.Complexity = 'real';
Pedal.SamplingMode = 'Sample based';

Fail = Simulink.Signal;
Fail.DataType = 'boolean';
Fail.Min = 0;
Fail.Max = 1;
Fail.Dimensions = 1;
Fail.Complexity = 'real';
Fail.SamplingMode = 'Sample based';

CMD = Simulink.Signal;
CMD.DataType = 'int32';
CMD.Min = 0;
CMD.Max = hex2dec('7FFFFFFF');
CMD.Dimensions = 1;
CMD.Complexity = 'real';
CMD.SamplingMode = 'Sample based';

Sync_From = Simulink.Signal;
Sync_From.DataType = 'Bus: SyncMsgImpl';
Sync_From.Dimensions = 1;
Sync_From.Complexity = 'real';
Sync_From.SamplingMode = 'Sample based';

Sync_To = Simulink.Signal;
Sync_To.DataType = 'Bus: SyncMsgImpl';
Sync_To.Dimensions = 1;
Sync_To.Complexity = 'real';
Sync_To.SamplingMode = 'Sample based';

Status = Simulink.Signal;
Status.DataType = 'Bus: ChannelStatusImpl';
Status.Dimensions = 1;
Status.Complexity = 'real';
Status.SamplingMode = 'Sample based';

CMD_From_COM = Simulink.Signal;
CMD_From_COM.DataType = 'int32';
CMD_From_COM.Min = 0;
CMD_From_COM.Max = hex2dec('7FFFFFFF');
CMD_From_COM.Dimensions = 1;
CMD_From_COM.Complexity = 'real';
CMD_From_COM.SamplingMode = 'Sample based';

