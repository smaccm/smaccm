The addVerificationSubSys.m file is used to auto copy the source model to the destination model
and create a Verification Subsystem into the destination model.
The Verification Subsystem is composed of a MATLAB function containing the code read in 
from the MATLAB script file translated from AGREE.

To use, place the addVerificationSubSys.m and the MATLAB script translated from AGREE
into a folder under the MATLAB search path.

Invoke the addVerificationSubSys function via the MATLAB command line like the following:
addVerificationSubSys('srcMdlName', 'destMdlName', 'verifySysName', 'scriptName')

Example:
addVerificationSubSys('MC_ToyC_sl_Before2.slx', 'MC_ToyC_sl_After_Auto2.slx', 'MC_ToyC', 'check_top_level_Impl_Instance.m')

This script has been tested with MATLAB versions 2014b and 2015b.