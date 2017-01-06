package edu.umn.cs.crisys.tb.codegen.linux;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.tb.Logger;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.CodeGeneratorBase;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterListRegistry;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ProcessImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.process.ProcessInstance;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

public class Linux_CodeGenerator extends CodeGeneratorBase {

	// so write threadName_write_portName for each port.

  public Linux_CodeGenerator(Logger log, OSModel model, File aadlDirectory, File outputDir) {
		super(log, model, aadlDirectory, outputDir, "linux");
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
  
  protected File getGlobalProcessDiretory(File rootDirectory) {
     return new File(outputDirectory, "gen");
  }
  
  protected File getProcessDirectory(File globalComponentDirectory, String name) {
     return globalComponentDirectory;
   }

  protected File getProcessHeaderDirectory(File componentDirectory) {
     return componentDirectory;
   }
   protected File getProcessSourceDirectory(File componentDirectory) {
     return componentDirectory;
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
	   String fileName = model.getPrefix() + "_decls.h";
	   writeGeneric(includeDirectory, "AssemblyHeader.stg", "headerBody", "model", mn, model.getInstanceName(), false, fileName);
   }

	 
	 
 public void createMainFile() throws TbFailure {
     ModelNames mn = new ModelNames(model);
     String name = model.getPrefix() + "_main.c";
     
     writeGeneric(componentsDirectory, "MainC.stg", "body", "model", mn, name, false, name);
 }


  @Override
  public void osSpecificComponentFiles(ThreadImplementation ti,
      File componentDirectory, File srcDirectory, File includeDirectory)
      throws TbFailure {

    // This space (currently) for rent...
  }

  public void createPeriodicDispatcherCFile(File srcDirectory) throws TbFailure {
     // No op 
  }
  
  // create this only if we have periodic threads.
 
  // this is a no-op for VxWorks.  For Linux, we need to copy the 
  // platform support files.
  
  @Override
  public void createPeriodicDispatcherComponent() throws TbFailure {   
     InputStream cSrcFileStream = null;
     InputStream hSrcFileStream = null;
     OutputStream cDstFileStream = null;
     OutputStream hDstFileStream = null;
     String c_file_name = "tb_linux_support.c";
     String h_file_name = "tb_linux_support.h";     
     File cdest = new File(this.componentsDirectory, c_file_name);
     File hdest = new File(this.componentsDirectory, h_file_name);
     
     
     // write the .c / .h files to the destination component
     try {
       try {
        cSrcFileStream = Util.findConfigFile(c_file_name);
        cDstFileStream = new FileOutputStream(cdest);
        copyFile(cSrcFileStream, cDstFileStream);
        hSrcFileStream = Util.findConfigFile(h_file_name);
        hDstFileStream = new FileOutputStream(hdest); 
        copyFile(hSrcFileStream, hDstFileStream);
         
       } catch (IOException ioe) {
         log.error("IOException occurred during write of linux_timer_support: " + ioe);
         log.error("Continuing anyway...");
         // throw new TbFailure();
       } finally {
         if (cSrcFileStream != null) { cSrcFileStream.close(); }
         if (hSrcFileStream != null) { hSrcFileStream.close(); }
         if (cDstFileStream != null) { cDstFileStream.close(); }
         if (hDstFileStream != null) { hDstFileStream.close(); }
       }
     } catch (IOException ioe) {
       log.error("IOException occurred during clock driver close: " + ioe);
       throw new TbFailure();
     }   
  }

  private void createProcessHeader(File componentDirectory, ProcessImplementation ti) throws TbFailure {
     ProcessImplementationNames tin = new ProcessImplementationNames(ti);
      writeGeneric(componentDirectory, "ProcessHeader.stg", "headerBody", 
            "process", tin, tin.getName(), false, tin.getHeaderName());
    }

    private void createProcessCFile(File componentDirectory, ProcessImplementation ti) throws TbFailure {
     ProcessImplementationNames tin = new ProcessImplementationNames(ti);
      String fname = tin.getCFileName();
      if (ti.getIsExternal()) {
         fname += ".template";
      }
      writeGeneric(componentDirectory, "ProcessC.stg", "body", 
           "process", tin, tin.getName(), false, fname);
    }

    public void createTemplateMakefile() throws TbFailure {
       ModelNames mn = new ModelNames(model); 
       writeGeneric(makeTemplateDirectory, "Makefile.stg", "MakefileBody", "model", mn,  
           model.getInstanceName(), false, "Makefile");
      }
    
  public void createProcess(ProcessImplementation pi) throws TbFailure {
     String name = pi.getNormalizedName();
     
     File componentDirectory = getProcessDirectory(componentsDirectory, name);
     File srcDirectory = getProcessSourceDirectory(componentDirectory);
     File includeDirectory = getProcessHeaderDirectory(componentDirectory);
     srcDirectory.mkdirs();
     includeDirectory.mkdirs();
    
     createProcessHeader(includeDirectory, pi);
     createProcessCFile(srcDirectory, pi);
     
     // Emitter-specific code.
     List<PortFeature> ports = pi.getPortList();
     for (PortListEmitter emitter: EmitterListRegistry.getPortListEmitters()) {
        for (ProcessInstance pinst: pi.getProcessInstanceList()) {
           emitter.writeProcessCFiles(pinst, srcDirectory, ports);
           emitter.writeProcessHFiles(pinst, includeDirectory, ports);
        }
     }
  }
  
  public void createProcesses() throws TbFailure {
     List<ProcessImplementation> tis = model.getProcessImplementationList();
     for (ProcessImplementation ti: tis) {
      createProcess(ti);
     }
     if (model.getThreadCalendar().hasDispatchers()) {
       createPeriodicDispatcherComponent();
     }
   }

  public void createVMDeclarations() {
     ModelNames mn = EmitterFactory.model(model);
     mn.getAddVMLinuxFiles(this.componentsDirectory);
  }
  
  @Override
  public void write() throws TbFailure {
    createTypesHeader();
    createComponents();
    createAssemblyHeader();
    createMainFile();
    createProcesses(); 
    createTemplateMakefile();
    // declarations added if part of a VM.
    createVMDeclarations(); 
    
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