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
The AGREE plugin is only packaged with the SMTInterpol SMT solver. If you want to use other solvers ([Z3](https://github.com/Z3Prover/z3),
[Yices (version 1)](http://yices.csl.sri.com/download-yices1.shtml), 
[Yices 2](http://yices.csl.sri.com/index.shtml),
[CVC4](http://cvc4.cs.nyu.edu/web/), or
[MathSAT](http://mathsat.fbk.eu/)) you will need to obtain them separately and set your PATH environment variable to point at their location.  In order to perform Realizability Analysis you must have Z3 installed.

**If you have trouble installing the updates you might need to run OSATE as administrator.**
