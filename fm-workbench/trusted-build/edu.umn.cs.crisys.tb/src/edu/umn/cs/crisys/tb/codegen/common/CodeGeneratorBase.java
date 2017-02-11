package edu.umn.cs.crisys.tb.codegen.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.Logger;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterListRegistry;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
//import edu.umn.cs.crisys.tb.codegen.common.names.PortNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.port.*;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;
import edu.umn.cs.crisys.tb.model.type.*;
import edu.umn.cs.crisys.tb.util.Util;

// TODO: need to discuss where to place .c / .h files for User-provided thread functions

/**********************************************************************
 * 
 * Scheme for template file management [PROPOSAL]:
 * ===============================================
 * 
 * There is a great deal of commonality between the eChronos and 
 * CAmkES generation processes, modulo a handful of overridden definitions, 
 * mostly related to naming.  To manage this, we have a naming scheme for 
 * templates to manage this.  
 * 
 * What we do is, given a root file name YYY, we try to load files in the 
 * following order:
 *     CommonYYY
 *     <OSSpecific>YYY
 *     <OSAndHWSpecific>YYY
 *  
 *  Where <OSSpecific> is the names of the OS: {Camkes, eChronos}, and 
 *  <OSAndHWSpecific> contains both the OS name and the hardware platform:
 *   e.g. {CamkesODROID, eChronosPixhawk}
 *  
 *  If the OS-specific file is missing, an error is raised.
 *  If the <Common> or <OSAndHWSpecific> files are missing, they are assumed 
 *  to be unnecessary and silently ignored.
 *  
 *  
 **********************************************************************/


public abstract class CodeGeneratorBase {
   protected OSModel model;
   protected Logger log;
   protected File outputDirectory;
   protected File aadlDirectory;
   protected File componentsDirectory;
   protected File includeDirectory;
   protected File makeTemplateDirectory; 
   protected String date;
   protected TbSTErrorListener listener; 
   protected String osPrefix;
   protected String hwPrefix; 
   protected String commonPrefix;

   public List<ThreadImplementation> allThreads;

   // so write threadName_write_portName for each port.

   protected abstract File getGlobalIncludeDirectory(File rootDirectory); 
   protected abstract File getGlobalTemplateDirectory(File rootDirectory);
   protected abstract File getGlobalComponentDirectory(File rootDirectory); 
   protected abstract File getComponentDirectory(File globalComponentDirectory, String name);
   protected abstract File getComponentHeaderDirectory(File ComponentDirectory);
   protected abstract File getComponentSourceDirectory(File ComponentDirectory); 

   public CodeGeneratorBase(Logger log, OSModel model, File aadlDirectory, File outputDir, 
         String osPrefix) {
      this.log = log;
      this.model = model;
      this.outputDirectory = new File(outputDir, model.getPathName());
      this.aadlDirectory = aadlDirectory;
      this.commonPrefix = "Common";
      this.osPrefix = osPrefix;
      this.hwPrefix = model.getHWTarget(); 

      // throw exception at first error.
      listener = new TbSTErrorListener(log);

      Util.setListener(listener);
      //this.templates.verbose = true;


      // Create directories
      componentsDirectory = getGlobalComponentDirectory(outputDirectory); 
      componentsDirectory.mkdirs(); 

      includeDirectory = getGlobalIncludeDirectory(outputDirectory);
      includeDirectory.mkdirs();

      makeTemplateDirectory = getGlobalTemplateDirectory(outputDirectory);
      makeTemplateDirectory.mkdirs();

      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date d = new Date();
      date = dateFormat.format(d);  
   }


   ///////////////////////////////////////////////////////////////////////////
   // 
   // first try to find templates in file system, then in plugin directory
   //
   ///////////////////////////////////////////////////////////////////////////

   protected STGroupFile createTemplateInternal(String fname) {
      String path = Util.findConfigFileLocation(fname);
      if (path == null) {
         path = Util.tb_resource + "/" + fname;
      }
      STGroupFile templates = new STGroupFile(path);
      templates.setListener(listener);
      return templates;

   }

   protected STGroupFile createTemplate(String fname) {
      return createTemplateInternal(this.osPrefix + fname);
   }

   // construct an dynamic, iterated, template inheritance hierarchy.
   protected STGroupFile createTemplate(List<String> templateNames) {
      STGroupFile prevTemplate = null; 
      STGroupFile template = null; 
      for (String s : templateNames) {
         template = createTemplateInternal(s); 
         if (prevTemplate != null) {
            template.importTemplates(prevTemplate);
         }
      }
      return template;
   }

   protected void writeBoilerplateDTHeader(String name, String path, BufferedWriter writer, ST st, boolean usesDTHeader) throws IOException {
      st.add("name", name);
      st.add("date", date);
      st.add("path", path);
      if (usesDTHeader) {
         ModelNames mn = new ModelNames(model); 
         st.add("datatypesHeader", mn.getSystemTypeHeaderName());
      }
      writer.append(st.render());
   }

   protected void writeBoilerplateHeader(String name, String path, BufferedWriter writer, ST st) throws IOException {
      writeBoilerplateDTHeader(name, path, writer, st, false);
   }

   public static void writeBoilerplateFooter(String name, String path, BufferedWriter writer, ST st) throws IOException {
      st.add("name", name);
      st.add("path", path);
      writer.append(st.render());
   }


   protected void writeGeneric(File directory, String templateFileName, String templateName, String tlTemplateArg[], Object tlNamesClass[], 
         String headerFooterName, boolean headerUsesDT, String fileName) throws TbFailure {

      File genericFile = new File(directory, fileName); 
      String path = genericFile.getAbsolutePath();
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(genericFile))) {
         STGroupFile stg = this.createTemplate(templateFileName);
         writeBoilerplateDTHeader(headerFooterName, path, writer, stg.getInstanceOf("filePrefix"), headerUsesDT);

         ST st = stg.getInstanceOf(templateName);

         assert(tlTemplateArg.length== tlNamesClass.length);

         for (int i = 0; i < tlTemplateArg.length; i++) {
            st.add(tlTemplateArg[i], tlNamesClass[i]);
         }
         writer.append(st.render() + System.lineSeparator());

         writeBoilerplateFooter(headerFooterName, path, writer, stg.getInstanceOf("filePostfix")); 
      } catch (IOException e) {
         log.error("IO Exception occurred when creating file: " + fileName);
         throw new TbFailure();
      }

   }

   protected void writeGeneric(File directory, String templateFileName, String templateName, String tlTemplateArg, Object tlNamesClass, 
         String headerFooterName, boolean headerUsesDT, String fileName) throws TbFailure {
      String argList[] = new String [] { tlTemplateArg };
      Object classList[] = new Object [] { tlNamesClass };
      writeGeneric(directory, templateFileName, templateName, argList, classList, headerFooterName, headerUsesDT, fileName);
   }

   private void createComponentHeader(File componentDirectory, ThreadImplementation ti) throws TbFailure {
      ThreadImplementationNames tin = new ThreadImplementationNames(ti);
      writeGeneric(componentDirectory, "ComponentHeader.stg", "componentGlueCodeHeaderBody", 
            "threadImpl", tin, tin.getNormalizedName(), true, tin.getComponentGlueCodeHFileName());
   }

   private void createComponentCFile(File componentDirectory, ThreadImplementation ti) throws TbFailure {
      ThreadImplementationNames tin = new ThreadImplementationNames(ti);
      String fname = tin.getComponentGlueCodeCFileName();
      if (ti.getIsExternal()) {
         fname += ".template";
      }
      writeGeneric(componentDirectory, "ComponentC.stg", "componentCFileDecls", 
            "threadImpl", tin, tin.getNormalizedName(), false, fname);
   }

   private void copyComponentFiles(File srcDirectory, File includeDirectory, ThreadImplementation ti) throws TbFailure {
      // determine the list of source files.
      Set<String> srcFiles = new HashSet<String>(); 
      for (DispatchableInputPort d: ti.getDispatcherList()) {
         srcFiles.addAll(d.getImplementationFileList());
      }
      srcFiles.addAll(ti.getSourceFileList());

      for (String s : srcFiles) {
         File srcFilePath = new File(aadlDirectory, s); 

         try { 
            if (srcFilePath.isFile()) {
               String srcFileName = srcFilePath.getName();
               String extension = srcFileName.substring(srcFileName.indexOf("."));
               if (extension.equalsIgnoreCase(".h") || extension.equalsIgnoreCase(".hpp")) {
                  File dstPath = new File(includeDirectory, srcFileName);
                  this.copyFile(new FileInputStream(srcFilePath), new FileOutputStream(dstPath));
               } else {
                  File dstPath = new File(srcDirectory, srcFileName);
                  this.copyFile(new FileInputStream(srcFilePath), new FileOutputStream(dstPath));
               }
            } else {
               log.warn("For thread: " + ti.getNormalizedName() + ", File: [" + s + "] does not exist as a relative path from the " + 
                     "directory containing the top-level AADL file, and was not copied into the component src directory");
            }
         } catch (IOException e) {
            log.error("Error copying file [" + s + "] for component " + ti.getNormalizedName() + ".  Error: " + e.toString());
            throw new TbFailure();
         }
      }

   }

   /* 
    * 
    * The clock driver is a generic interface for managing the clock interrupt.
    * This is no longer required in CAmkES due to the introduction of timer components.
    * 
    */

   protected void createClockDriver(File srcDirectory, File includeDirectory) throws TbFailure {

      String concrete_driver = null; 
      if (model.getHWTarget().equalsIgnoreCase("QEMU")) {
         concrete_driver = "qemu_clock_driver.c";
      } else if (model.getHWTarget().equalsIgnoreCase("ODROID")) {
         concrete_driver = "odroid_clock_driver.c";
      } else if (model.getHWTarget().equalsIgnoreCase("PIXHAWK")) {
         concrete_driver = "pixhawk_clock_driver.c";
      }
      else {
         log.warn("Clock driver for HW platform: " + model.getHWTarget() + " is currently unimplemented.  " + 
               "Please implement interface as specified in clock_driver.h for this platform, and place the resulting .c file in the dispatch_periodic directory.");
      }

      model.getSourceFiles().add(concrete_driver);

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

   protected abstract void osSpecificComponentFiles(ThreadImplementation ti, 
         File componentDirectory, 
         File srcDirectory, File includeDirectory) throws TbFailure;

   protected abstract void osSpecificPortComponentFiles(PortFeature pf, PortEmitter pe, File componentDirectory) throws TbFailure;
   
   private void createComponent(ThreadImplementation ti) throws TbFailure { 

      String name = ti.getNormalizedName();

      File componentDirectory = getComponentDirectory(componentsDirectory, name);
      File srcDirectory = getComponentSourceDirectory(componentDirectory);
      File includeDirectory = getComponentHeaderDirectory(componentDirectory);
      srcDirectory.mkdirs();
      includeDirectory.mkdirs();

      createComponentHeader(includeDirectory, ti);
      createComponentCFile(srcDirectory, ti);
      copyComponentFiles(srcDirectory, includeDirectory, ti); 

      ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
      File CFile = new File(componentDirectory, tin.getComponentGlueCodeCFileName());
      this.model.getSourceFiles().add(CFile.getPath());

      osSpecificComponentFiles(ti, componentDirectory, srcDirectory, includeDirectory);
      
      // Emitter-specific code.
      List<PortFeature> ports = ti.getPortList(); 
      List<PortListEmitter> emitters = EmitterListRegistry.getPortListEmitters(); 
      for (PortListEmitter emitter : emitters) {
         for (ThreadInstance tinst: ti.getThreadInstanceList()) {
            emitter.writeThreadCFiles(tinst, srcDirectory, ports);
            emitter.writeThreadHFiles(tinst, includeDirectory, ports);
         }
      }

      for (PortFeature pf: ports) {
         PortEmitter pe = EmitterFactory.port(pf);
         pe.getWritePortHFiles(includeDirectory);
         pe.getWritePortCFiles(srcDirectory);
         osSpecificPortComponentFiles(pf,pe,componentsDirectory);
      }
   }

   public void copyFile(InputStream is, OutputStream os) throws IOException {
      byte[] buffer = new byte[16384];
      int length;
      while ((length = is.read(buffer)) > 0) {
         os.write(buffer, 0, length);
      }
   }


   // create this only if we have periodic threads.
   protected abstract void createPeriodicDispatcherComponent() throws TbFailure;


   protected void createComponents() throws TbFailure {
      List<ThreadImplementation> tis = model.getThreadImplementationList();
      for (ThreadImplementation ti: tis) {
         createComponent(ti);
      }
      if (model.getThreadCalendar().hasDispatchers()) {
         createPeriodicDispatcherComponent();
      }
   }

   private void createPortTypes() {
      for (ThreadImplementation ti : model.getThreadImplementationList()) {
         for (PortFeature d : ti.getPortList()) {
            PortEmitter pe = EmitterFactory.port(d);
            pe.addPortPublicTypeDeclarations(model.getAstTypes());
         }
      }
   }

   protected Set<Type> getSharedVariableTypes() {
      // write dispatcher types
      Set<Type> svTypeSet = new HashSet<Type>();

      for (SharedData d : model.getSharedDataList()) {
         svTypeSet.add(d.getType());
      }
      return svTypeSet ; 
   }


   protected void createTypesHeader() throws TbFailure {
      ModelNames mn = new ModelNames(model); 

      String hname = mn.getSystemTypeHeaderName();
      String sysInstanceName = model.getInstanceName(); 

      File HFile = new File(includeDirectory, hname);
      String path = HFile.getAbsolutePath();

      try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
         STGroupFile stg = this.createTemplate("TypesHeader.stg");
         writeBoilerplateHeader(sysInstanceName, path, hwriter, stg.getInstanceOf("filePrefix"));

         ST st = stg.getInstanceOf("externalTypeDecls");
         st.add("model", mn);
         hwriter.append(st.render()); 
         st = stg.getInstanceOf("mutexOP");
         hwriter.append(st.render());
         st = stg.getInstanceOf("callbackOP");
         hwriter.append(st.render());
         // TODO: MWW: this needs to be refactored for new architecture.
         // createComponentDispatchTypes();
         createPortTypes();
         C_Type_Writer.writeTypes(hwriter, model, 6);
         

         writeBoilerplateFooter(sysInstanceName, hname, hwriter, stg.getInstanceOf("filePostfix"));

      } catch (IOException e) {
         log.error("IOException occurred during file write: " + e);
         throw new TbFailure();
      }
   }



   protected String getLastDir() throws TbFailure {
      String outputDir = model.getOutputDirectory(); 
      if (outputDir == null) {
         log.warn("No output directory specified: setting Makefile path to system impl name.");
         return model.getInstanceName();
      } else {
         Path p = Paths.get(outputDir); 
         return p.getName(p.getNameCount() - 1).toString();
      }
   }

   public abstract void write() throws TbFailure; 

}


/* 
protected STGroupFile createTemplate(String fileNameRoot) {
  String commonFileName = this.commonPrefix + fileNameRoot; 
  String osFileName = this.osPrefix + fileNameRoot;
  String osHwFileName = this.osPrefix + this.hwPrefix + fileNameRoot ; 

  STGroupFile commonTemplate = null;
  STGroupFile osTemplate = createTemplateElem(osFileName); 
  STGroupFile osHwTemplate = null;

  try {
    commonTemplate = createTemplateElem(commonFileName);


  osTemplate.importTemplates(commonTemplate);

  // try to construct OS/HW specific template.  If it doesn't exist,
  // it is o.k.
  try { 
    osHwTemplate = createTemplateElem(osHwFileName); 
    osHwTemplate.importTemplates(commonTemplate);
    osHwTemplate.importTemplates(osTemplate);
  } catch(Exception e) {
    System.out.println("Unable to create OS/HW template file.  Exception: " + e);
  }

  if (osHwTemplate != null) {
    return osHwTemplate;
  } else {
    return osTemplate;
  }
}
 */

/*
//ModelNames m = new ModelNames(model); 

File interfaceFile = new File(componentDirectory, tin.getComponentGlueCodeHFileName());
String path = interfaceFile.getAbsolutePath();
try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
  STGroupFile stg = this.createTemplate("ComponentHeader.stg");
  writeBoilerplateDTHeader(name, path, writer, stg.getInstanceOf("componentGlueCodeHeaderPrefix"), true);

  ST st = stg.getInstanceOf("componentGlueCodeHeaderBody");
  st.add("threadImpl", tin);
  writer.append(st.render() + System.lineSeparator());

  writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("componentGlueCodeHeaderPostfix")); 
} catch (IOException e) {
  log.error("IO Exception occurred when creating a component header.");
  throw new TbFailure();
}
 */
