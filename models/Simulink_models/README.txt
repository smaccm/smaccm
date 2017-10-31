Simulink models and project created and verifed in MATLAB 2016a.

To check the models, follow the steps below:

1. Extract each Simulink Project's zip file (e.g., GPCA.zip) into a separate folder (e.g., GPCA)

2. Start MATLAB 2016a, and explore to Simulink Project folder extracted above (e.g,. GPCA)

3. Double click the .prj file in the folder (e.g., GPCA.prj) in MATLAB, and the Simulink Project will be 
automatically loaded, with the files added to path and environment (e.g., buses, parameters, configuration set) loaded

To use the AGREE/AADL to Simulink Exporter feature, follow the steps below.
See AGREE Users Guide Section 4.3.5 "AGREE/AADL to Simulink Exporter" for more information.

1. In OSATE, import the AADL projects (from https://github.com/smaccm/smaccm/tree/master/models) 
  that correspond to each Simulink project, e.g.,
https://github.com/smaccm/smaccm/tree/develop/models/GPCA AADL Project for GPCA.zip Simulink Project 
https://github.com/smaccm/smaccm/tree/develop/models/BSCU AADL Project for BSCU.zip Simulink Project

2. In OSATE, open the AADL file for each component in the project, and right click the System type 
in the Outline panel in OSATE. Select "AGREE" -> "Generate Simulink Models".

3. In the "Script Generation Settings for AADL/AGREE Export to Simulink" Dialog that opens,

1) Browse to select Output Directory Path (e.g., path to GPCA\scripts)

2) Browse to select or specify the Implementation Model Path (e.g., path to GPCA\models\Subsystems\Alarms\ALARM_Verification.slx)
This is the location of the implementation model in Simulink. If the model does not exist, 
the script can generate an empty subsystem with the interface consistent with the AADL model. 

3) Specify the name for the Verification Model (i.e., the Model to Insert Observer)
Note that the implementation model must exist before the verification model can be generated.  

4) Specify the name of the Subsystem to Verify (This is the subsystem in the model
where the Simulink observer will be connected to)

5) Clicking the "Export Contracts" button will will generate the MATLAB script containing the AGREE contracts, 
translated into MATLAB properties. This script will be wrapped inside an observer block in the generated Verification model. 
The script will be placed in the Output Directory.

6) Clicking the "Generate Implementation" button will generate the script to create an implementation model. 
When the script is running in MATLAB, an error will be reported if the implementation model already exists. 
The script will be placed in the Output Directory. 

7) Clicking the "Generate Verification" button will generate the script containing the AGREE contracts 
(as in Export Contracts) as well as the script to create a verification model. 
The generated scripts will be placed in the Output Directory.  

8) Clicking the "Verify Subsystem" button will generate the script containing the AGREE contracts (as in Export Contracts), 
as well as the script to create a verification model (as in Generate Verification) 
and run Simulink Design Verifier on the generated verification model. 

4. In MATLAB 2016a, open the Simulink Project corresponds to the AADL project, run the second MATLAB script file generated
in the above step (the one that inserts the observer). This will automatically insert the Simulink observer into the
"Model to Insert Observer", and connected to the "Subsystem to Verify" (as specified in the dialog).

5. In MATLAB 2016a, run Simulink Design Verifier (SLDV) on the model with the Simulink observer inserted, to check
if the properties translated from AGREE contracts are satisfied in the model or not.
