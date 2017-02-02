package edu.umn.cs.crisys.tb.codegen.eChronos;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import edu.umn.cs.crisys.tb.Logger;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.CodeGeneratorBase;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadCalendarNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;

public class EChronos_CodeGenerator extends CodeGeneratorBase {

	// so write threadName_write_portName for each port.

	public EChronos_CodeGenerator(Logger log, OSModel model, File aadlDirectory, File outputDir) {
		super(log, model, aadlDirectory, outputDir, "eChronos");
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

	
	 public void createAssemblyHeader() throws TbFailure {
	   ModelNames mn = new ModelNames(model);
	   String fileName = mn.getPrefix() + "_decls.h";
	   writeGeneric(includeDirectory, "AssemblyHeader.stg", "headerBody", "model", mn, model.getInstanceName(), false, fileName);
   }

	 
	 
 public void createPrxFile() throws TbFailure {
     ModelNames mn = new ModelNames(model);
     String name = mn.getEChronosPrxFileName();
     String platform; 
     if (model.getHWTarget().equalsIgnoreCase("pixhawk")) {
       platform = "Prx-stm32f4-discovery.stg";
     } else {
       log.error("eChronos code generator: currently only pixhawk hardware platform is supported." + System.lineSeparator());
       throw new TbFailure();
     }
     writeGeneric(outputDirectory, platform, "prxBody", "model", mn, name, false, name);
 }


  @Override
  public void osSpecificComponentFiles(ThreadImplementation ti,
      File componentDirectory, File srcDirectory, File includeDirectory)
      throws TbFailure {

    // This space (currently) for rent...
  }

  public void createPeriodicDispatcherCFile(File srcDirectory) throws TbFailure {
    ModelNames mn = new ModelNames(model);
    ThreadCalendarNames tcn = new ThreadCalendarNames(model.getThreadCalendar());

    writeGeneric(srcDirectory, "PeriodicDispatcherC.stg", "periodicComponentCBody", 
        new String[] {"model", "threadCalendar"}, 
        new Object[] {mn, tcn}, 
        tcn.getPeriodicDispatcherComponentName(), false, tcn.getPeriodicDispatcherCFileName());
    
    model.getSourceFiles().add(tcn.getPeriodicDispatcherCFileName());
  }
  
  // create this only if we have periodic threads.
  
  @Override
  public void createPeriodicDispatcherComponent() throws TbFailure {
    ModelNames mn = new ModelNames(model); 
    ThreadCalendarNames cn = mn.getThreadCalendar(); 

    File componentDirectory = getComponentDirectory(componentsDirectory, cn.getPeriodicDispatcherComponentName());
    componentDirectory.mkdirs();
  
    File srcDirectory = getComponentSourceDirectory(componentDirectory);
    srcDirectory.mkdirs();
    
    File includeDirectory = getComponentHeaderDirectory(componentDirectory);
    includeDirectory.mkdirs();
    
    createClockDriver(srcDirectory, includeDirectory);
    createPeriodicDispatcherCFile(srcDirectory); 
  }


  @Override
  public void write() throws TbFailure {
    createTypesHeader();
    createComponents();
    createAssemblyHeader();
    createPrxFile();

    // final check for errors from string template.
    if (listener.isErrorOccurred()) {
      throw new TbFailure();
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
    
    for (ThreadImplementation ti : model.getThreadImplementationList()) {
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

@Override
protected void osSpecificPortComponentFiles(PortFeature pf, PortEmitter pe, File componentDirectory) {
	// TODO Auto-generated method stub
	
}
}