/*
Copyright (c) 2013, Rockwell Collins and the University of Minnesota.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
 */

package edu.umn.cs.crisys.smaccm.aadl2rtos;

import java.io.File;
import java.util.List;


import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osate.aadl2.Element;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
//import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;

import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES.CAmkES_CodeGenerator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos.EChronos_CodeGenerator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.AadlModelParser;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;
//import fr.tpt.aadl.ramses.control.support.analysis.AnalysisException;
//import fr.tpt.aadl.ramses.control.support.config.RamsesConfiguration;
@Deprecated 
public class Aadl2RtosAction extends AadlAction {
	private Logger log;

	// Model object contains information from the AST nicely collated.
	// TODO: Figure out a principled way to deal with file locations.
	// Allow overriding file names as properties?

	@Override
	public IStatus runJob(Element sel, IProgressMonitor monitor, Logger logger) {
		log = logger;

		if (!(sel instanceof SystemImplementation)) {
			log.error("Aadl2RtosAction.runJob: Must select a system implementation");
			return Status.CANCEL_STATUS;
		}

		IStatus execStatus = execute(null, (SystemImplementation) sel, monitor, null, null, logger); 
				
		if (execStatus == Status.OK_STATUS) 
			try {
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(0, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}

		return execStatus;
		
	}
	
	public IStatus execute(SystemInstance si, SystemImplementation sysimpl, IProgressMonitor monitor, File aadlDir, File outputDir, Logger logger) {
		log = logger;
		log.info("This is the sysimpl name: "+ sysimpl.getName());
		
		monitor.beginTask("Generating Configuration for AADL Model", IProgressMonitor.UNKNOWN);

		log.status("*****AADL to RTOS Translator******");
		try {
			// Create instance model

		  if (si == null) {
				InstantiateModel im = new InstantiateModel(new NullProgressMonitor(), getErrorManager());
				URI uri = OsateResourceUtil.getInstanceModelURI(sysimpl);
				Resource resource = OsateResourceUtil.getEmptyAaxl2Resource(uri);
			
				// Aha!  The big time sink is here!
			 	si = im.createSystemInstance(sysimpl, resource);
			}

			// Now harvest the stuff we need from the OSATE AST.
		  
			Model model = new Model(sysimpl.getName(), si.getName());
			
			// AadlModelParser initializes the Model class from the OSATE hierarchy
			// (it is a factory).
			logger.status("Generating and typechecking domain model for code generation...");
			new AadlModelParser(sysimpl, si, model, logger);
			
			logger.status("Generating code...");

      // split on whether eChronos or CAmkES is the target.
      // Print out C skeletons
			if (aadlDir == null) {
			  aadlDir = Util.getDirectory(sysimpl);
			}

			// for output directory: choose command line outputDir first, then 
			// AADL property outputDir, then directory containing AADL file.
			
			if (outputDir != null) {
			  model.setOutputDirectory(outputDir.getPath());
			}
			else if (model.getOutputDirectory() != null) {
			  outputDir = new File(model.getOutputDirectory());
			} else {
			  outputDir = aadlDir;
			  model.setOutputDirectory(aadlDir.getPath());
			}
      outputDir.mkdirs(); 
      
			if (model.getOsTarget() == Model.OSTarget.eChronos) {
  			EChronos_CodeGenerator gen = new EChronos_CodeGenerator(log, model, aadlDir, outputDir);
  			gen.write();
			} else if (model.getOsTarget() == Model.OSTarget.CAmkES ){
			  CAmkES_CodeGenerator gen = new CAmkES_CodeGenerator(log, model, aadlDir, outputDir);
			  gen.write();
			} else {
			  logger.error("aadl2rtos OS target: [" + model.getOsTarget() + "] not recognized.");
			}
			logger.status("aadl2rtos code generation complete.");
		} catch (Aadl2RtosFailure f) {
			log.error("Analysis Exception");
			List<String> msgs = f.getMessages();
			for (String msg : msgs) {
				log.error(msg);
			}
      log.error("Context: " + Util.stackTraceString(f));
			return Status.CANCEL_STATUS;
		} catch (ParserConfigurationException pce) {
      log.error("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
      log.error("Context: " + Util.stackTraceString(pce));
    } catch (Exception e) {
			log.error(e);
      log.error("Context: " + Util.stackTraceString(e));
			return Status.CANCEL_STATUS;
		}


		return Status.OK_STATUS;
	}

/*
	@Override
	public void setParameters(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getTransformationModuleList() {
		// TODO Auto-generated method stub
		return null;
	}

	public static final String PLUGIN_NAME = "AADL2RTOS";
	
	@Override
	public String getRegistryName() {
		// TODO Auto-generated method stub
		return PLUGIN_NAME;
	}

	@Override
	public String getPluginName() {
		// TODO Auto-generated method stub
		return PLUGIN_NAME;
	}

	@Override
	public String getPluginId() {
		// TODO Auto-generated method stub
		return PLUGIN_NAME;
	}
	
	@Override
	public void performAnalysis(SystemInstance instance,
								RamsesConfiguration config,
	                             AnalysisErrorReporterManager errReporterManager,
	                             IProgressMonitor monitor) throws AnalysisException {

		this.execute(instance, instance.getSystemImplementation(), monitor, config.getRamsesOutputDir(), new ConsoleLogger(Logger.INFO));
	}  
  */
}