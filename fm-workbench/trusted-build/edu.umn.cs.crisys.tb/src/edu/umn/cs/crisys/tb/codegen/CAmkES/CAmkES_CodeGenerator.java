package edu.umn.cs.crisys.tb.codegen.CAmkES;

/**
 * CAmkES Code generator class
 * 
 * "top level" writer for CAmkES code generator.  
 * Uses StringTemplate .stg files to generate code for CAmkES.
 * 
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
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.Logger;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.*;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterListRegistry;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.RemoteProcedureGroupNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadCalendarNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.rpc.InternalRemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;


public class CAmkES_CodeGenerator extends CodeGeneratorBase {
   private File interfacesDirectory;
   private File makeTemplateDirectory;
   private File vmMakeTemplateDirectory;

   protected File getGlobalIncludeDirectory(File rootDirectory) {
      return new File(rootDirectory, "include");
   }

   protected File getGlobalTemplateDirectory(File rootDirectory) {
      return new File(rootDirectory, "make_template");
   }

   protected File getGlobalVMTemplateDirectory(File rootDirectory) {
      return new File(rootDirectory, "make_template_vm");
   }

   protected File getGlobalComponentDirectory(File rootDirectory) {
      return new File(rootDirectory, "components");
   }

   protected File getComponentDirectory(File globalComponentDirectory, String name) {
      return new File(globalComponentDirectory, name);
   }
   protected File getComponentHeaderDirectory(File componentDirectory) {
      return new File(componentDirectory, "include");
   }
   protected File getComponentSourceDirectory(File componentDirectory) {
      return new File(componentDirectory, "src");
   }

   public CAmkES_CodeGenerator(Logger log, OSModel model, File aadlDirectory, File outputDir) {
      super(log, model, aadlDirectory, outputDir, "Camkes");

      makeTemplateDirectory = 
            new File(outputDirectory, "make_template");
      makeTemplateDirectory.mkdirs();

      vmMakeTemplateDirectory = 
            new File(outputDirectory, "make_vm_template");
      vmMakeTemplateDirectory.mkdirs();

      interfacesDirectory = 
            new File(outputDirectory, "interfaces");
      interfacesDirectory.mkdirs();
      System.out.println(interfacesDirectory.getAbsolutePath());

   }

   void createPortIdlInterfaces() {
      List<PortFeature> features = model.getPortFeatureList();
      List<PortListEmitter> emitters = EmitterListRegistry.getPortListEmitters(); 
      for (PortListEmitter emitter: emitters) {
         if (emitter instanceof PortListEmitterCamkes) {
            PortListEmitterCamkes camkesEmitter = (PortListEmitterCamkes)emitter;
            camkesEmitter.camkesGenerateModelIdl(interfacesDirectory, model, features);
         }
      }
   }

   /* Now handled by the RPC interface ports directly...see createPortIdlInterfaces

  public void createReadWriteIdlInterface(Type t) throws TbFailure {
    TypeNames type = new TypeNames(t); 
    ModelNames m = new ModelNames(model); 

    writeGeneric(interfacesDirectory, "Idl4ReaderWriter.stg", "idlProc", 
        new String[] {"type", "model"}, 
        new Object[] {type, m}, 
        type.getReaderWriterInterfaceName(), false, type.getReaderWriterIdlFileName());
  }

  public void createReadWriteIdlInterfaces() throws TbFailure {
    for (Type t : getUserTypes()) {
      createReadWriteIdlInterface(t); 
    }
  }
    */

   public void createRpcIdlInterface(InternalRemoteProcedureGroup rpg) throws TbFailure {
      RemoteProcedureGroupNames rpgn = new RemoteProcedureGroupNames(rpg); 
      ModelNames m = new ModelNames(model); 
      writeGeneric(interfacesDirectory, "Idl4Rpc.stg", "rpgDeclaration", 
            new String[] {"remoteProcedureGroup", "model"}, 
            new Object[] {rpgn, m}, 
            rpgn.getName(), false, rpgn.getIdlName());
   }

   public void createRpcIdlInterfaces() throws TbFailure {
      for (RemoteProcedureGroup t : model.getRemoteProcedureGroupMap().values()) {
         if (t instanceof InternalRemoteProcedureGroup) {
            createRpcIdlInterface((InternalRemoteProcedureGroup)t);
         }
      }
   }
   
   @Override
   public void osSpecificComponentFiles(ThreadImplementation ti, 
         File componentDirectory, 
         File srcDirectory, File includeDirectory) throws TbFailure {
      //createDispatchInterface(ti);
      createComponentCamkesFile(componentDirectory, ti);
   }


   public Set<Type> getSharedVariableTypes() {
      // write dispatcher types
      Set<Type> svTypeSet = new HashSet<Type>();

      for (SharedData d : model.getSharedDataList()) {
         svTypeSet.add(d.getType());
      }
      return svTypeSet ; 
   }

   public void createSharedVariableIdlInterfaces() throws TbFailure {
      ModelNames m = new ModelNames(model); 

      for (Type t : getSharedVariableTypes()) {
         TypeNames type = new TypeNames(t);
         writeGeneric(interfacesDirectory, "Idl4SharedVar.stg", "svProc", 
               new String[] {"type", "model"}, 
               new Object[] {type, m}, 
               type.getSharedDataInterfaceName(), false, type.getSharedDataIdlFileName());
      }
   }


   public void createDispatchInterface(ThreadImplementation ti) throws TbFailure {
      ThreadImplementationNames tin = new ThreadImplementationNames(ti);
      ModelNames m = new ModelNames(model); 
      writeGeneric(interfacesDirectory, "Idl4Dispatch.stg", "dispatcherProc", 
            new String[] {"threadImpl", "datatypesHeader"}, 
            new Object[] {tin, m.getSystemTypeHeaderName()}, 
            tin.getIdlName(), false, tin.getIdlFileName());
   }



   // For a given thread implementation, how do we connect it to the other components?
   // We can go from a thread implementation to a thread instance.  In our case (for now), 
   // There is only one thread instance per implementation.  So, we get the thread 
   // instance, follow the connections to other thread instances, and map back up to the 
   // other thread implementations.  From here, we can grab the idl file for that thread implementation.  

   public void createComponentCamkesFile(File componentDirectory, ThreadImplementation ti) throws TbFailure {
      ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
      String fname = tin.getComponentCamkesFileName(); 
      if (ti.getIsExternal()) { fname += ".template"; }
      writeGeneric(componentDirectory, "ComponentCamkes.stg", "componentCamkesBody", 
            "threadImpl", tin, 
            tin.getComponentName(), false, fname);
   }

   public void createClockDriver(File srcDirectory, File includeDirectory) throws TbFailure {

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



   public void createPeriodicDispatcherCFile(File srcDirectory) throws TbFailure {
      ModelNames mn = new ModelNames(model);
      ThreadCalendarNames tcn = new ThreadCalendarNames(model.getThreadCalendar());

      writeGeneric(srcDirectory, "PeriodicDispatcherC.stg", "periodicComponentCBody", 
            new String[] {"model", "threadCalendar"}, 
            new Object[] {mn, tcn}, 
            tcn.getPeriodicDispatcherComponentName(), false, tcn.getPeriodicDispatcherCFileName());
   }

   // create this only if we have periodic threads.

   public void createPeriodicDispatcherComponent() throws TbFailure {
      ModelNames mn = new ModelNames(model); 
      TypeNames tn = new TypeNames(InputPeriodicPort.getPortType());

      ThreadCalendarNames cn = mn.getThreadCalendar(); 


      File componentDirectory = new File(componentsDirectory, cn.getPeriodicDispatcherComponentName());
      componentDirectory.mkdirs();

      File srcDirectory = new File(componentDirectory, "src");
      srcDirectory.mkdirs();

      File includeDirectory = new File(componentDirectory, "include");
      includeDirectory.mkdirs();

      // MWW: removed to work with new periodic driver architecture.
      // createClockDriver(srcDirectory, includeDirectory);
      createPeriodicDispatcherCFile(srcDirectory); 

      writeGeneric(componentDirectory, "PeriodicDispatcherCamkes.stg", "periodicDispatcherCamkesBody", 
            new String[] {"model", "type"}, 
            new Object[] {mn, tn}, 
            cn.getPeriodicDispatcherComponentName(), false, cn.getPeriodicDispatcherCamkesFileName());
   }

   void createAssembly() throws TbFailure {
      ModelNames mn = new ModelNames(model); 
      writeGeneric(outputDirectory, "Assembly.stg", "camkesAssemblyBody", "model", mn,  
            model.getInstanceName(), false, mn.getCamkesSystemAssemblyFileName());
   }

   public void createTemplateFile(File directory, String fileName, String templateFile, String templateName) throws TbFailure {
      File HFile = new File(directory, fileName);
      try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
         STGroupFile stg = this.createTemplate(templateFile);
         String name = getLastDir(); 
         String CapName = name.toUpperCase();
         ST st = stg.getInstanceOf(templateName);
         st.add("name", name);
         st.add("CapName", CapName);
         hwriter.append(st.render());
      } catch (IOException e) {
         log.error("IOException occurred during CAmkES write: " + e);
         throw new TbFailure();
      }
   }

   public void createTemplateMakefiles() throws TbFailure {
      ModelNames mn = new ModelNames(model); 

      // original-style makefiles
      createTemplateFile(makeTemplateDirectory, "Kbuild", "Makefile.stg", "camkesKbuild");
      createTemplateFile(makeTemplateDirectory, "Kconfig", "Makefile.stg", "camkesKconfig");
      writeGeneric(makeTemplateDirectory, "Makefile.stg", "camkesMakefileBody", "model", mn,  
            model.getInstanceName(), false, "Makefile");
      
      // vm makefiles
      createTemplateFile(vmMakeTemplateDirectory, "Kbuild", "MakefileVM.stg", "camkesKbuild");
      createTemplateFile(vmMakeTemplateDirectory, "Kconfig", "MakefileVM.stg", "camkesKconfig");
      writeGeneric(vmMakeTemplateDirectory, "MakefileVM.stg", "camkesMakefileBody", "model", mn,  
            model.getInstanceName(), false, "Makefile");
   }
   
   public void createVMComponent(OSModel vm) {
      ModelNames mn = EmitterFactory.model(vm);
      File componentDirectory = getComponentDirectory(componentsDirectory, mn.getVmComponentName());
      mn.getAddVMComponentFiles(componentDirectory);
   }


   public void createVMComponents() {
      model.getVirtualMachineList().stream().forEach(vm -> createVMComponent(vm));
   }


   public void write() throws TbFailure {
      createTypesHeader();
      createRpcIdlInterfaces();
      createPortIdlInterfaces(); 
      createSharedVariableIdlInterfaces();
      createComponents();
      createVMComponents(); 
      createAssembly(); 
      createTemplateMakefiles();

      // final check for errors from string template.
      if (listener.isErrorOccurred()) {
         throw new TbFailure();
      }
   }

   protected Set<Type> getUserTypes() {
      // write dispatcher types
      Set<Type> rwTypeSet = new HashSet<Type>();

      rwTypeSet.add(new UnitType());
      //rwTypeSet.add(new IntType(32, false));  
      //rwTypeSet.add(InputPeriodicPort.getPortType());  

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
protected void osSpecificPortComponentFiles(PortFeature pf, PortEmitter pe, File componentDirectory) throws TbFailure {
	assert(pe instanceof PortEmitterCamkes);
	PortEmitterCamkes pec = (PortEmitterCamkes)pe;
	pec.getWriteCamkesPortComponents(componentDirectory);
	pec.getWriteCamkesPortIdls(interfacesDirectory);

}

}

