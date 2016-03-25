SMACCM
======

Install
=====
1. Download the latest version of OSATE: (http://www.aadl.info/aadl/osate/stable/).
2. Start OSATE and go to "Help -> Install New Software..."
3. Click the "Add..." button in the upper right hand corner and add this URL as an update site: (https://raw.githubusercontent.com/smaccm/update-site/master/site.xml)
4. Click the box labeled "SMACCM", click "Finish", and proceed through the dialog.

Notes
=====
The AGREE plugin is only packaged with the SMTInterpol SMT solver. If you want to use other solvers (like Z3, Yices, or CVC4) you will need to obtain them separately and place them in your PATH environment variable.  In order to perform Realizability Analysis you must have Z3 installed.
