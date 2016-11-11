dt = 0.01;

Primary_Side = Simulink.Parameter;
Primary_Side.Value = 0;
Primary_Side.DataType = 'boolean';

MANUAL = Simulink.Parameter;
MANUAL.Value = 1;
MANUAL.DataType = 'uint8';

LO = Simulink.Parameter;
LO.Value = 2;
LO.DataType = 'uint8';

MED = Simulink.Parameter;
MED.Value = 3;
MED.DataType = 'uint8';

MAX = Simulink.Parameter;
MAX.Value = 4;
MAX.DataType = 'uint8';

BRAKE_PRESSURE_LO = Simulink.Parameter;
BRAKE_PRESSURE_LO.Value = 170;
BRAKE_PRESSURE_LO.DataType = 'int32';

BRAKE_PRESSURE_MED = Simulink.Parameter;
BRAKE_PRESSURE_MED.Value = 340;
BRAKE_PRESSURE_MED.DataType = 'int32';

BRAKE_PRESSURE_MAX = Simulink.Parameter;
BRAKE_PRESSURE_MAX.Value = 510;
BRAKE_PRESSURE_MAX.DataType = 'int32';
