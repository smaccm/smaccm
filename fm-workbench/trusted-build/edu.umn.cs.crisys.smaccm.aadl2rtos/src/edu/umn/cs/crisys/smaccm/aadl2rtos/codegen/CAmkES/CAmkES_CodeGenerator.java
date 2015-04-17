package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

/**
 * CAmkES Code generator class
 * 
 * "top level" writer for CAmkES code generator.  
 * Uses StringTemplate .stg files to generate code for CAmkES.
 * 
 * TODO: normalize template stg interface so that we can combine
 * many of these writer functions together.  There is quite a bit of
 * code bloat here. 
 * 
 * TODO: normalize the interface for name prefixes.  It seems to 
 * be "smaccm_", but this is not uniformly applied.
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ModelNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.RemoteProcedureGroupNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ThreadCalendarNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ThreadImplementationNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.TypeNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;

// TODO: need to discuss where to place .c / .h files for User-provided thread functions

public class CAmkES_CodeGenerator extends CodeGeneratorBase {
	private File interfacesDirectory;
	private File makeTemplateDirectory; 

	// so write threadName_write_portName for each port.

	public CAmkES_CodeGenerator(Logger log, Model model, File aadlDirectory, File outputDir) {
	  super(log, model, aadlDirectory, outputDir, "Camkes");

    makeTemplateDirectory = 
      new File(outputDirectory, "make_template");
    makeTemplateDirectory.mkdirs();

		interfacesDirectory = 
		    new File(outputDirectory, "interfaces");
		interfacesDirectory.mkdirs();
		System.out.println(interfacesDirectory.getAbsolutePath());

	}
	
  public void createReadWriteIdlInterface(Type t) throws Aadl2RtosFailure {
    TypeNames type = new TypeNames(t); 
    ModelNames m = new ModelNames(model); 
    
    File interfaceFile = new File(interfacesDirectory, type.getReaderWriterIdlFileName());
    String path = interfaceFile.getAbsolutePath();
    String name = type.getReaderWriterInterfaceName(); 
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("Idl4ReaderWriter.stg");
      ST st; 
      writeBoilerplateHeader(name, path, writer, stg.getInstanceOf("rwInterfaceIdlPrefix"));
      st = stg.getInstanceOf("idlProc");
      st.add("type", type);
      st.add("model", m);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("rwInterfaceIdlPostfix"));
      
    } catch (IOException e) {
      log.error("IO Exception occurred when creating a read/write interface.");
      throw new Aadl2RtosFailure();
    }
  }

  public void createReadWriteIdlInterfaces() throws Aadl2RtosFailure {
    for (Type t : getUserTypes()) {
      createReadWriteIdlInterface(t); 
    }
  }
  
  public void createRpcIdlInterface(RemoteProcedureGroup rpg) throws Aadl2RtosFailure {
    RemoteProcedureGroupNames rpgn = new RemoteProcedureGroupNames(rpg); 
    ModelNames m = new ModelNames(model); 
    
    File interfaceFile = new File(interfacesDirectory, rpgn.getIdlName());
    String path = interfaceFile.getAbsolutePath();
    String name = rpgn.getName(); 
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("Idl4Rpc.stg");
      ST st; 
      writeBoilerplateHeader(name, path, writer, stg.getInstanceOf("rpcInterfaceIdlPrefix"));
      st = stg.getInstanceOf("rpgDeclaration");
      st.add("remoteProcedureGroup", rpgn);
      st.add("model", m);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("rpcInterfaceIdlPostfix"));
      
    } catch (IOException e) {
      log.error("IO Exception occurred when creating a remote procedure interface.");
      throw new Aadl2RtosFailure();
    }
  }
  
  public void createRpcIdlInterfaces() throws Aadl2RtosFailure {
    for (RemoteProcedureGroup t : model.getRemoteProcedureGroupMap().values()) {
      createRpcIdlInterface(t); 
    }
  }

  public Set<Type> getSharedVariableTypes() {
    // write dispatcher types
    Set<Type> svTypeSet = new HashSet<Type>();
    
    for (SharedData d : model.getSharedDataList()) {
       svTypeSet.add(d.getType());
    }
    return svTypeSet ; 
  }

  public void createSharedVariableIdlInterfaces() throws Aadl2RtosFailure {
    ModelNames m = new ModelNames(model); 
    
    for (Type t : getSharedVariableTypes()) {
      TypeNames type = new TypeNames(t);
      
      String name = type.getSharedDataInterfaceName();
      File interfaceFile = new File(interfacesDirectory, type.getSharedDataIdlFileName());
      String path = interfaceFile.getAbsolutePath();
      
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
        STGroupFile stg = this.createTemplate("Idl4SharedVar.stg");
        ST st; 
        writeBoilerplateHeader(name, path, writer, stg.getInstanceOf("svInterfaceIdlPrefix"));
        
        st = stg.getInstanceOf("svProc"); 
        st.add("type", type);
        st.add("model", m);
        writer.append(st.render());
        
        writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("svInterfaceIdlPostfix"));
        
      } catch (IOException e) {
        log.error("IO Exception occurred when creating a shared variable interface.");
        throw new Aadl2RtosFailure();
      }
    }
  }


  public void createDispatchInterface(ThreadImplementation ti) throws Aadl2RtosFailure {

    ThreadImplementationNames tin = new ThreadImplementationNames(ti);
    ModelNames m = new ModelNames(model); 
	  String name = tin.getIdlName();
	  File interfaceFile = new File(interfacesDirectory, tin.getIdlFileName());
    String path = interfaceFile.getAbsolutePath();
    
	  try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("Idl4Dispatch.stg");
	    writeBoilerplateHeader(name, path, writer, stg.getInstanceOf("dispatchInterfaceIdlPrefix"));
	    
	    ST di = stg.getInstanceOf("dispatcherProc");
	    di.add("threadImpl", tin);
	    di.add("datatypesHeader", m.getSystemTypeHeaderName());
  	  writer.append(di.render());
  	  writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("dispatchInterfaceIdlPostfix")); 
	  } catch (IOException e) {
	    log.error("IO Exception occurred when creating a component dispatch interface.");
	    throw new Aadl2RtosFailure();
	  }
	}
	

	
  // For a given thread implementation, how do we connect it to the other components?
  // We can go from a thread implementation to a thread instance.  In our case (for now), 
  // There is only one thread instance per implementation.  So, we get the thread 
  // instance, follow the connections to other thread instances, and map back up to the 
  // other thread implementations.  From here, we can grab the idl file for that thread implementation.  

	public void createComponentCamkesFile(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
      ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
	  String name = tin.getComponentName();
	  String fname = tin.getComponentCamkesFileName(); 
	  if (ti.getIsExternal()) {
		  fname += ".template";
	  }
    File interfaceFile = new File(componentDirectory, fname);
    String path = interfaceFile.getAbsolutePath();
	  try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("ComponentCamkes.stg");
      writeBoilerplateDTHeader(name, path, writer, stg.getInstanceOf("componentCamkesPrefix"), true);
  
      ST st = stg.getInstanceOf("componentCamkesBody");
      st.add("threadImpl", tin);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("componentCamkesPostfix"));
	  } catch (IOException e) {
	    log.error("Problem creating camkes component file.");
	    throw new Aadl2RtosFailure(); 
	  }
	  
	}

	@Override
  public void osSpecificComponentFiles(ThreadImplementation ti, 
      File componentDirectory, 
      File srcDirectory, File includeDirectory) throws Aadl2RtosFailure {
	  createDispatchInterface(ti);
    createComponentCamkesFile(componentDirectory, ti);
    
  }
		
  public void createClockDriver(File srcDirectory, File includeDirectory) throws Aadl2RtosFailure {
	  
    String concrete_driver = null; 
    if (model.getHWTarget().equalsIgnoreCase("QEMU")) {
      concrete_driver = "qemu_clock_driver.c";
    } else if (model.getHWTarget().equalsIgnoreCase("ODROID")) {
      concrete_driver = "odroid_clock_driver.c";
    }
    else {
      log.warn("Clock driver for HW platform: " + model.getHWTarget() + " is currently unimplemented.  " + 
          "Please implement interface as specified in clock_driver.h for this platform, and place the resulting .c file in the dispatch_periodic directory.");
    }
    
    InputStream cSrcFileStream = null;
    InputStream hSrcFileStream = null;
    OutputStream cDstFileStream = null;
    OutputStream hDstFileStream = null;
    
    // write the .c / .h files to the destination component
    try {
      try {
        if (concrete_driver != null) {
          File cdest = new File(srcDirectory, concrete_driver);
          cSrcFileStream = Util.findConfigFile(concrete_driver);
          cDstFileStream = new FileOutputStream(cdest);
          copyFile(cSrcFileStream, cDstFileStream);
        }
        
        File hdest = new File(includeDirectory, "clock_driver.h");
        hSrcFileStream = Util.findConfigFile("clock_driver.h");
        hDstFileStream = new FileOutputStream(hdest); 
        copyFile(hSrcFileStream, hDstFileStream);
        
      } catch (IOException ioe) {
        log.error("IOException occurred during clock driver write: " + ioe);
        log.error("Continuing anyway...");
        // throw new Aadl2RtosFailure();
      } finally {
        if (cSrcFileStream != null) { cSrcFileStream.close(); }
        if (hSrcFileStream != null) { hSrcFileStream.close(); }
        if (cDstFileStream != null) { cDstFileStream.close(); }
        if (hDstFileStream != null) { hDstFileStream.close(); }
      }
    } catch (IOException ioe) {
      log.error("IOException occurred during clock driver close: " + ioe);
      throw new Aadl2RtosFailure();
    }
  }
  
  public void createPeriodicDispatcherCFile(File srcDirectory) throws Aadl2RtosFailure {
    ModelNames mn = new ModelNames(model);
    ThreadCalendarNames tcn = new ThreadCalendarNames(model.getThreadCalendar());

    String name = tcn.getPeriodicDispatcherComponentName();
    File interfaceFile = new File(srcDirectory, tcn.getPeriodicDispatcherCFileName());
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("PeriodicDispatcherC.stg");

      writeBoilerplateHeader(name, path, writer, stg.getInstanceOf("periodicDispatcherCPrefix"));
      writer.append("\n"); 
      writer.append("#include <" + mn.getSystemTypeHeaderName() + ">\n");
      writer.append("#include <clock_driver.h>\n\n");
      writer.append("#include <string.h>\n\n");
      writer.append("#include <" + tcn.getPeriodicDispatcherCamkesHFileName() +  ">\n");
      
      ST st = stg.getInstanceOf("periodicComponentCBody"); 
      st.add("threadCalendar", tcn);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("periodicDispatcherCPostfix"));
    
    } catch (IOException e) {
      log.error("Problem creating periodic component c file.");
      throw new Aadl2RtosFailure(); 
    }
    
  }
	
	// create this only if we have periodic threads.
	
	public void createPeriodicDispatcherComponent() throws Aadl2RtosFailure {
	  ModelNames mn = new ModelNames(model); 
    String name = mn.getThreadCalendar().getPeriodicDispatcherComponentName();
    TypeNames tn = new TypeNames(PeriodicDispatcher.getPeriodicDispatcherType());
    
    File componentDirectory = new File(componentsDirectory, mn.getThreadCalendar().getPeriodicDispatcherComponentName());
    componentDirectory.mkdirs();
  
    File srcDirectory = new File(componentDirectory, "src");
    srcDirectory.mkdirs();
    
    File includeDirectory = new File(componentDirectory, "include");
    includeDirectory.mkdirs();
    createClockDriver(srcDirectory, includeDirectory);
    createPeriodicDispatcherCFile(srcDirectory); 
    
    File interfaceFile = new File(componentDirectory, mn.getThreadCalendar().getPeriodicDispatcherCamkesFileName());
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("PeriodicDispatcherCamkes.stg");
      writeBoilerplateDTHeader(name, path, writer, stg.getInstanceOf("componentCamkesPrefix"), true);
      writer.append("\n");
  
      writer.append("import \"../../interfaces/" + 
          tn.getReaderWriterIdlFileName() + "\";\n");
      writer.append("\n\n");
      ST st = stg.getInstanceOf("periodicDispatcherCamkesBody");
      st.add("model", mn);
      st.add("type", tn);
  	  writer.append(st.render());
      writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("componentCamkesPostfix"));

    } catch (IOException e) {
      log.error("Problem creating camkes component dispatcher file.");
      throw new Aadl2RtosFailure(); 
    }
	  
	}
	

	void createAssembly() throws Aadl2RtosFailure {
	  ModelNames mn = new ModelNames(model); 
	  String hname = mn.getCamkesSystemAssemblyFileName();
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(outputDirectory, hname);
    String path = HFile.getAbsolutePath();

    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      STGroupFile stg = this.createTemplate("Assembly.stg");
      writeBoilerplateHeader(sysInstanceName, path, hwriter, stg.getInstanceOf("camkesAssemblyPrefix"));

      ST st = stg.getInstanceOf("camkesAssemblyBody");
      st.add("model", mn);
      
      hwriter.append(st.render());
      writeBoilerplateFooter(sysInstanceName, hname, hwriter, stg.getInstanceOf("stdFilePostfix"));

    } catch (IOException e) {
      log.error("IOException occurred during CAmkES write: " + e);
      throw new Aadl2RtosFailure();
    }
  }
	
	public void createTemplateMakefile() throws Aadl2RtosFailure {
    ModelNames mn = new ModelNames(model); 
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(makeTemplateDirectory, "Makefile");
    String path = HFile.getAbsolutePath();
    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      STGroupFile stg = this.createTemplate("Makefile.stg");
      writeBoilerplateHeader(sysInstanceName, path, hwriter, stg.getInstanceOf("camkesMakefilePrefix"));

      ST st = stg.getInstanceOf("camkesMakefileBody");
      st.add("model", mn);
      
      hwriter.append(st.render());
      writeBoilerplateFooter(sysInstanceName, "unused", hwriter, stg.getInstanceOf("camkesMakefilePostfix"));

    } catch (IOException e) {
      log.error("IOException occurred during CAmkES write: " + e);
      throw new Aadl2RtosFailure();
    }
	  
	}
	
  public void createTemplateFile(String fileName, String templateName) throws Aadl2RtosFailure {
    File HFile = new File(makeTemplateDirectory, fileName);
    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      STGroupFile stg = this.createTemplate("Makefile.stg");
      String name = getLastDir(); 
      String CapName = name.toUpperCase();
      ST st = stg.getInstanceOf(templateName);
      st.add("name", name);
      st.add("CapName", CapName);
      hwriter.append(st.render());
    } catch (IOException e) {
      log.error("IOException occurred during CAmkES write: " + e);
      throw new Aadl2RtosFailure();
    }
  }

  public void createTemplateKbuild() throws Aadl2RtosFailure {
    createTemplateFile("Kbuild", "camkesKbuild");
  }

  public void createTemplateKconfig() throws Aadl2RtosFailure {
    createTemplateFile("Kconfig", "camkesKconfig");
  }
  
	public void write() throws Aadl2RtosFailure {
	  createTypesHeader();
    createReadWriteIdlInterfaces();
    createRpcIdlInterfaces();
    createSharedVariableIdlInterfaces();
    createComponents();
    createAssembly(); 
    createTemplateMakefile(); 
    createTemplateKconfig(); 
    createTemplateKbuild();
    
    // final check for errors from string template.
    if (listener.isErrorOccurred()) {
      throw new Aadl2RtosFailure();
    }
	}

}

