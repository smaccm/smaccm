package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.VxWorks;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CodeGeneratorBase;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ModelNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ThreadCalendarNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;

public class VxWorks_CodeGenerator extends CodeGeneratorBase {

	// so write threadName_write_portName for each port.

  public VxWorks_CodeGenerator(Logger log, Model model, File aadlDirectory, File outputDir) {
		super(log, model, aadlDirectory, outputDir, "VxWorks");
	}

  protected File getGlobalIncludeDirectory(File rootDirectory) {
    return new File(outputDirectory, "gen");
  }
  
  protected File getGlobalTemplateDirectory(File rootDirectory) {
    return new File(outputDirectory, "make_template");
  }
  protected File getGlobalComponentDirectory(File rootDirectory) {
    return new File(outputDirectory, "gen");
  }
  
  protected File getComponentDirectory(File globalComponentDirectory, String name) {
    return globalComponentDirectory;
  }
  protected File getComponentHeaderDirectory(File componentDirectory) {
    return componentDirectory;
  }
  protected File getComponentSourceDirectory(File componentDirectory) {
    return componentDirectory;
  }

	
	 public void createAssemblyHeader() throws Aadl2RtosFailure {
	   ModelNames mn = new ModelNames(model);
	   String fileName = "smaccm_decls.h";
	   writeGeneric(includeDirectory, "AssemblyHeader.stg", "headerBody", "model", mn, model.getSystemInstanceName(), false, fileName);
   }

	 
	 
 public void createMainFile() throws Aadl2RtosFailure {
     ModelNames mn = new ModelNames(model);
     String name = "smaccm_main.c";
     
     writeGeneric(componentsDirectory, "MainC.stg", "body", "model", mn, name, false, name);
 }


  @Override
  public void osSpecificComponentFiles(ThreadImplementation ti,
      File componentDirectory, File srcDirectory, File includeDirectory)
      throws Aadl2RtosFailure {

    // This space (currently) for rent...
  }

  public void createPeriodicDispatcherCFile(File srcDirectory) throws Aadl2RtosFailure {
	  // MWW: TODO
	  /*
	ModelNames mn = new ModelNames(model);
    ThreadCalendarNames tcn = new ThreadCalendarNames(model.getThreadCalendar());

    writeGeneric(srcDirectory, "PeriodicDispatcherC.stg", "periodicComponentCBody", 
        new String[] {"model", "threadCalendar"}, 
        new Object[] {mn, tcn}, 
        tcn.getPeriodicDispatcherComponentName(), false, tcn.getPeriodicDispatcherCFileName());
    
    model.getSourceFiles().add(tcn.getPeriodicDispatcherCFileName());
*/
  }
  
  // create this only if we have periodic threads.
  
  @Override
  public void createPeriodicDispatcherComponent() throws Aadl2RtosFailure {
    ModelNames mn = new ModelNames(model); 
    
    File componentDirectory = getComponentDirectory(componentsDirectory, mn.getThreadCalendar().getPeriodicDispatcherComponentName());
    componentDirectory.mkdirs();
  
    File srcDirectory = getComponentSourceDirectory(componentDirectory);
    srcDirectory.mkdirs();
    
    File includeDirectory = getComponentHeaderDirectory(componentDirectory);
    includeDirectory.mkdirs();
    
    createClockDriver(srcDirectory, includeDirectory);
    createPeriodicDispatcherCFile(srcDirectory); 
  }


  @Override
  public void write() throws Aadl2RtosFailure {
    createTypesHeader();
    createComponents();
    createAssemblyHeader();
    createMainFile();

    // final check for errors from string template.
    if (listener.isErrorOccurred()) {
      throw new Aadl2RtosFailure();
    }
  }

  protected Set<Type> getUserTypes() {
    // write dispatcher types
    Set<Type> rwTypeSet = new HashSet<Type>();
    
    // "for free types" that are always necessary; void for event ports
    // and uint32_t for periodic dispatchers.  Note if the dispatcher 
    // time type changes, it may break code, so perhaps we should 
    // store the time type somewhere (model?); 
    // MWW: updated: store this in the periodic dispatcher class.
    
    rwTypeSet.add(new UnitType());
    //rwTypeSet.add(new IntType(32, false));  
    rwTypeSet.add(InputPeriodicPort.getPortType());  
    
    for (ThreadImplementation ti : model.getAllThreadImplementations()) {
      for (OutputDataPort d : ti.getOutputDataPortList()) {
        rwTypeSet.add(d.getType());
      }
      for (OutputEventPort d : ti.getOutputEventDataPortList()) {
        rwTypeSet.add(d.getType());
      }
      for (InputDataPort d : ti.getInputDataPortList()) {
        rwTypeSet.add(d.getType());
      }
      for (InputEventPort d : ti.getInputEventDataPortList()) {
        rwTypeSet.add(d.getType());
      }
    }
    for (SharedData d : model.getSharedDataList()) {
       rwTypeSet.add(d.getType());
    }
    return rwTypeSet ; 
  }
}