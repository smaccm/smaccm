package edu.umn.cs.crisys.smaccm.aadl2rtos.cli;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;

import edu.umn.cs.crisys.tb.ConsoleLogger;
import edu.umn.cs.crisys.tb.Logger;
import edu.umn.cs.crisys.tb.TbAction;
import fr.tpt.aadl.ramses.control.support.analysis.AnalysisException;
import fr.tpt.aadl.ramses.control.support.config.RamsesConfiguration;
import fr.tpt.aadl.ramses.control.support.generator.GenerationException;
import fr.tpt.aadl.ramses.control.support.generator.Generator;
import fr.tpt.aadl.ramses.control.support.generator.TransformationException;
import fr.tpt.aadl.ramses.control.workflow.WorkflowPilot;

public class RtosGenerator implements Generator {

	private final String NAME = "rtos";
	
	@Override
	public String getRegistryName() {
		return NAME;
	}

	@Override
	public String getPluginName() {
		return NAME;
	}

	@Override
	public String getPluginId() {
		return NAME;
	}

	@Override
	public void generate(SystemInstance systemInstance,
			RamsesConfiguration config, File[] includeDirs,
			AnalysisErrorReporterManager errManager, IProgressMonitor monitor)
			throws AnalysisException, GenerationException,
			TransformationException {
		// TODO Auto-generated method stub

	  // it appears that the last include dir is the directory containing the system instance.
	  File rootDir = new File(System.getProperty("user.dir"));
	  String str = config.getTargetId();
	  
	  IStatus execStatus = (new TbAction()).execute(systemInstance, 
				systemInstance.getSystemImplementation(), monitor, 
				rootDir, config.getRamsesOutputDir(), new ConsoleLogger(Logger.INFO));
	  if (execStatus != Status.OK_STATUS)
		  throw new GenerationException("Error during RTOS code generation");
	}

	@Override
	public void generateWorkflow(SystemInstance systemInstance,
			RamsesConfiguration config, WorkflowPilot xmlPilot,
			File[] includeDirs, AnalysisErrorReporterManager errManager,
			IProgressMonitor monitor) throws AnalysisException,
			GenerationException, TransformationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("workflow");
	}

	@Override
	public boolean runtimePathChecker(File runtimePath) {
		return true;
//		throw new UnsupportedOperationException("path check");
	}

	@Override
	public String getRuntimePathEnvVar() {
		return null;
		// throw new UnsupportedOperationException("env var");	
	}

	@Override
	public List<String> getTransformationModuleList() {
		throw new UnsupportedOperationException("trans list");	
	}

	@Override
	public List<String> getValidationModuleList() {
		throw new UnsupportedOperationException("val list");	
	}

	@Override
	public void setParameters(Map<String, Object> parameters) {
		return;
//		throw new UnsupportedOperationException("set param");
	}

}
