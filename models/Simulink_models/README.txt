Simulink models and project created and verifed in MATLAB 2016a.

To check the models, follow the steps below:

1. Extract each Simulink Project's zip file (e.g., GPCA.zip) into a separate folder (e.g., GPCA)

2. Start MATLAB 2016a, and explore to Simulink Project folder extracted above (e.g,. GPCA)

3. Double click the .prj file in the folder (e.g., GPCA.prj) in MATLAB, and the Simulink Project will be 
automatically loaded, with the files added to path and environment (e.g., buses, parameters, configuration set) loaded

To get AGREE contracts exported to Simulink observers over the models, follow the steps below:

1. In OSATE, import the AADL projects (from https://github.com/smaccm/smaccm/tree/master/models) 
  that correspond to each Simulink project, e.g.,
https://github.com/smaccm/smaccm/tree/develop/models/GPCA AADL Project for GPCA.zip Simulink Project 
https://github.com/smaccm/smaccm/tree/develop/models/BSCU AADL Project for BSCU.zip Simulink Project

2. In OSATE, open the AADL file for each component in the project, and right click the System type 
in the Outline panel in OSATE. Select "AGREE" -> "Generate Simulink Observer".

3. In the "Model Info for Inserting Simulink Observer (Script Generator)" Dialog that opens,

1) Browse to select Output Directory Path (e.g., path to GPCA\scripts)

2) Browse to select the Original Model Path (e.g., path to GPCA\models\Subsystems\Alarms\ALARM_Verification.slx)

3) Specify the name for the Model to Insert Observer (e.g., ALARM_Verification.slx, or ALARM_Verification_After.slx. 
The former will have the same model updated)

4) Specify the name of the Subsystem to Verify (e.g., GPCA_SW_Functional. This is the subsystem in the model
where the Simulink observer will be connected to)

5) Clicking the "Export Contracts" button will translate the AGREE contracts into a MATLAB script file.

6) Clicking the "Insert Observer" button will both translate the AGREE contracts into a MATLAB script file, and
also create another MATLAB script file that connects a Verification Subsystem block to the subsystem 
to verify in the Simulink model. The verification subsystem block contains a MATLAB function block which encapsulates
the content of the first MATLAB script file (translated from AGREE contracts).    

7) Clicking the "Verify Subsystem" button will both translate the AGREE contracts into a MATLAB script file, create
another MATLAB script file that first inserts the observer as in "Insert Observer" and then invokes Simulink Design 
Verifier (SLDV) to verify the subsystem.

4. In MATLAB 2016a, open the Simulink Project corresponds to the AADL project, run the second MATLAB script file generated
in the above step (the one that inserts the observer). This will automatically insert the Simulink observer into the
"Model to Insert Observer", and connected to the "Subsystem to Verify" (as specified in the dialog).

5. In MATLAB 2016a, run Simulink Design Verifier (SLDV) on the model with the Simulink observer inserted, to check
if the properties translated from AGREE contracts are satisfied in the model or not.