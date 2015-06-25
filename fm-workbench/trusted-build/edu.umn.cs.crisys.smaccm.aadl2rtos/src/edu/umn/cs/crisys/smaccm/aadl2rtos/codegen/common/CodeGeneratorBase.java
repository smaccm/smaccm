package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ModelNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.PortNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ThreadImplementationNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.TypeNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;

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
	protected Model model;
	protected Logger log;
	protected File outputDirectory;
	protected File aadlDirectory;
	protected File componentsDirectory;
	protected File includeDirectory;
	protected File makeTemplateDirectory; 
	protected String date;
	protected Aadl2RtosSTErrorListener listener; 
	protected File pluginDirectory;
	protected String osPrefix;
	protected String hwPrefix; 
	protected String commonPrefix;
	
	public List<ThreadImplementation> allThreads;

	// so write threadName_write_portName for each port.

	public CodeGeneratorBase(Logger log, Model model, File aadlDirectory, File outputDir, 
	    String osPrefix) {
		this.log = log;
		this.model = model;
		this.outputDirectory = outputDir;
		this.aadlDirectory = aadlDirectory;
		this.commonPrefix = "Common";
		this.osPrefix = osPrefix;
		this.hwPrefix = model.getHWTarget(); 
		
		try {
		  this.pluginDirectory = Util.getFileFromURL(Util.createURLFromClass(getClass()));
		  // System.out.println("CAmkES_CodeGenerator class directory: " + this.pluginDirectory.getPath());
		} catch (URISyntaxException e) {
		  log.error("Unable to find plugin directory.  Error: " + e.toString());
		  System.out.println("Unable to find plugin directory.  Error: " + e.toString());
		  this.pluginDirectory = null;
		}
		
		// throw exception at first error.
		listener = new Aadl2RtosSTErrorListener(log);
		//this.templates.verbose = true;
		

		// Create directories
		componentsDirectory = 
		    new File(outputDirectory, "components");
		
		includeDirectory = 
		    new File(outputDirectory, "include");
		includeDirectory.mkdirs();

    makeTemplateDirectory = 
       new File(outputDirectory, "make_template");
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
      path = Util.aadl2rtos_resource + "/" + fname;
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

	protected void writeBoilerplateFooter(String name, String path, BufferedWriter writer, ST st) throws IOException {
	  st.add("name", name);
	  st.add("path", path);
	  writer.append(st.render());
	}
	
	/* MWW TODO: Perhaps this method should be refactored to go in the C_Type_Writer class */
	
	private void createComponentDispatchTypes(BufferedWriter writer) throws IOException {
    // write dispatcher types
	  // Note: for new-style "struct" return blocks
	  
    for (ThreadImplementation ti : model.getAllThreadImplementations()) {
      for (DispatchableInputPort d : ti.getDispatcherList()) {
        OutgoingDispatchContract maxCalls = 
           OutgoingDispatchContract.maxUsedDispatchers(d.getDispatchLimits());
        RecordType dispatchRecordType = new RecordType();
        //if (d.hasData()) {
        //  dispatchRecordType.addField("input", d.getType());
        //}
        for (Map.Entry<OutputEventPort, Integer> entry : maxCalls.getContract().entrySet()) {
          OutputEventPort outp = entry.getKey();
          if (outp.hasData()) {
            ArrayType dispatchArrayType = new ArrayType(outp.getType(), entry.getValue());
            PortNames outpn = new PortNames(outp);
            
            dispatchRecordType.addField(outpn.getData(), dispatchArrayType);
            // dispatchRecordType.addField(outpn.getDataMaxsizeName(), new IntType(32, false));
            dispatchRecordType.addField(outpn.getIndex(), new IntType(32, false));
          }
        }
        if (dispatchRecordType.isEmpty()) {
          dispatchRecordType.addField("unused", new IntType(32, false));
        }
        model.getAstTypes().put((new PortNames(d)).getDispatchStructTypeName(), dispatchRecordType);
      }
    }
    C_Type_Writer.writeTypes(writer, model, 6);
	}
	
	
	protected Set<Type> getSharedVariableTypes() {
    // write dispatcher types
    Set<Type> svTypeSet = new HashSet<Type>();
    
    for (SharedData d : model.getSharedDataList()) {
       svTypeSet.add(d.getType());
    }
    return svTypeSet ; 
  }

  protected void writeGeneric(File directory, String templateFileName, String templateName, String tlTemplateArg[], Object tlNamesClass[], 
      String headerFooterName, boolean headerUsesDT, String fileName) throws Aadl2RtosFailure {

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
      writer.append(st.render() + "\n");

      writeBoilerplateFooter(headerFooterName, path, writer, stg.getInstanceOf("filePostfix")); 
    } catch (IOException e) {
      log.error("IO Exception occurred when creating file: " + fileName);
      throw new Aadl2RtosFailure();
    }

  }
  
  protected void writeGeneric(File directory, String templateFileName, String templateName, String tlTemplateArg, Object tlNamesClass, 
                            String headerFooterName, boolean headerUsesDT, String fileName) throws Aadl2RtosFailure {
    String argList[] = new String [] { tlTemplateArg };
    Object classList[] = new Object [] { tlNamesClass };
    writeGeneric(directory, templateFileName, templateName, argList, classList, headerFooterName, headerUsesDT, fileName);
  }
	
	private void createComponentHeader(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    ThreadImplementationNames tin = new ThreadImplementationNames(ti);
	  writeGeneric(componentDirectory, "ComponentHeader.stg", "componentGlueCodeHeaderBody", 
	        "threadImpl", tin, tin.getNormalizedName(), true, tin.getComponentGlueCodeHFileName());
	}

	private void createComponentCFile(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    ThreadImplementationNames tin = new ThreadImplementationNames(ti);
	  String fname = tin.getComponentGlueCodeCFileName();
	  if (ti.getIsExternal()) {
		  fname += ".template";
	  }
	  writeGeneric(componentDirectory, "ComponentC.stg", "componentCFileDecls", 
	       "threadImpl", tin, tin.getNormalizedName(), false, fname);
	}
	
	private void copyComponentFiles(File srcDirectory, File includeDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
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
	      throw new Aadl2RtosFailure();
	    }
	  }
	  
	}
	
	protected abstract void osSpecificComponentFiles(ThreadImplementation ti, 
	    File componentDirectory, 
	    File srcDirectory, File includeDirectory) throws Aadl2RtosFailure;
	
	private void createComponent(ThreadImplementation ti) throws Aadl2RtosFailure { 
	  
	  String name = ti.getNormalizedName();
	  
	  File componentDirectory = new File(componentsDirectory, name);
	  File srcDirectory = new File(componentDirectory, "src");
	  File includeDirectory = new File(componentDirectory, "include");
    srcDirectory.mkdirs();
	  includeDirectory.mkdirs();
    
	  createComponentHeader(includeDirectory, ti);
	  createComponentCFile(srcDirectory, ti);
    copyComponentFiles(srcDirectory, includeDirectory, ti); 
	  
	  ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
    File CFile = new File(componentDirectory, tin.getComponentGlueCodeCFileName());
    this.model.getSourceFiles().add(CFile.getPath());
    
    osSpecificComponentFiles(ti, componentDirectory, srcDirectory, includeDirectory);
	}

	public void copyFile(InputStream is, OutputStream os) throws IOException {
    byte[] buffer = new byte[16384];
    int length;
    while ((length = is.read(buffer)) > 0) {
        os.write(buffer, 0, length);
    }
	}
	
	
	// create this only if we have periodic threads.
  protected abstract void createPeriodicDispatcherComponent() throws Aadl2RtosFailure;
	
	
	protected void createComponents() throws Aadl2RtosFailure {
	  List<ThreadImplementation> tis = model.getAllThreadImplementations();
	  for (ThreadImplementation ti: tis) {
      createComponent(ti);
	  }
	  if (model.getThreadCalendar().hasDispatchers()) {
	    createPeriodicDispatcherComponent();
	  }
	}
	
	protected void createTypesHeader() throws Aadl2RtosFailure {
	  ModelNames mn = new ModelNames(model); 
	  
    String hname = mn.getSystemTypeHeaderName();
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(includeDirectory, hname);
    String path = HFile.getAbsolutePath();

    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      STGroupFile stg = this.createTemplate("TypesHeader.stg");
      writeBoilerplateHeader(sysInstanceName, path, hwriter, stg.getInstanceOf("filePrefix"));
      
      ST st = stg.getInstanceOf("externalTypeDecls");
      st.add("model", mn);
      hwriter.append(st.render()); 
      
      createComponentDispatchTypes(hwriter);
      
      writeBoilerplateFooter(sysInstanceName, hname, hwriter, stg.getInstanceOf("filePostfix"));

    } catch (IOException e) {
      log.error("IOException occurred during file write: " + e);
      throw new Aadl2RtosFailure();
    }
	}

	

	protected String getLastDir() throws Aadl2RtosFailure {
	  String outputDir = model.getOutputDirectory(); 
	  if (outputDir == null) {
	    log.warn("No output directory specified: setting Makefile path to system impl name.");
	    return model.getSystemImplementationName();
	  } else {
	    Path p = Paths.get(outputDir); 
	    return p.getName(p.getNameCount() - 1).toString();
	  }
	}

	public abstract void write() throws Aadl2RtosFailure; 
	
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
  writer.append(st.render() + "\n");
  
  writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("componentGlueCodeHeaderPostfix")); 
} catch (IOException e) {
  log.error("IO Exception occurred when creating a component header.");
  throw new Aadl2RtosFailure();
}
*/
