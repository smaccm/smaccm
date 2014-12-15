package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.google.common.io.Files;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.PluginActivator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CGUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.C_Type_Writer;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CommonNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.HeaderDeclarations;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.SourceDeclarations;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.ExternalHandler;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.InputEventDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.PeriodicDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.Connection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.OutgoingDispatchContract;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstancePort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;

// TODO: need to discuss where to place .c / .h files for User-provided thread functions

public class CAmkES_CodeGenerator {
	private Model model;
	private Logger log;
	private File outputDirectory;
	private File aadlDirectory;
	private File componentsDirectory;
	private File interfacesDirectory;
	private File includeDirectory;
	private String date;
	private STErrorListener listener; 
  private File pluginDirectory;
	
	public List<ThreadImplementation> allThreads;

	// so write threadName_write_portName for each port.

	public CAmkES_CodeGenerator(Logger log, Model model, File aadlDirectory, File outputDir) {
		this.log = log;
		this.model = model;
		this.outputDirectory = outputDir;
		this.aadlDirectory = aadlDirectory;
		
		try {
		  this.pluginDirectory = Util.getFileFromURL(Util.createURLFromClass(getClass()));
		  System.out.println("CAmkES_CodeGenerator class directory: " + this.pluginDirectory.getPath());
		} catch (URISyntaxException e) {
		  System.out.println("Unable to find plugin directory!  Error: " + e.toString());
		  this.pluginDirectory = null;
		}
		
		listener = new CAmkESSTErrorListener(log);
		
		//this.templates.verbose = true;
		
		//templates.registerRenderer(Type.class, new C_Type_Renderer());

		// Create component directory
		componentsDirectory = 
		    new File(outputDirectory, "components");
		
		includeDirectory = 
		    new File(outputDirectory, "include");
		includeDirectory.mkdirs();
		
		interfacesDirectory = 
		    new File(outputDirectory, "interfaces");
		interfacesDirectory.mkdirs();
		System.out.println(interfacesDirectory.getAbsolutePath());

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date d = new Date();
    date = dateFormat.format(d);  
	}

	///////////////////////////////////////////////////////////////////////////
	// 
	// first try to find templates in file system, then in plugin directory
	//
	///////////////////////////////////////////////////////////////////////////
	
	STGroupFile createTemplate(String fname) {
	  String path = Util.findConfigFileLocation(fname);
	  if (path == null) {
	    path = Util.aadl2rtos_resource + "/" + fname;
	  }
	  STGroupFile templates = new STGroupFile(path);
	  templates.setListener(listener);
	  return templates;
	}
	
	void writeBoilerplateDTHeader(String name, String path, BufferedWriter writer, ST st, boolean usesDTHeader) throws IOException {
    st.add("name", name);
    st.add("date", date);
    st.add("path", path);
    if (usesDTHeader) {
      ModelNames mn = new ModelNames(model); 
      st.add("datatypesHeader", mn.getSystemTypeHeaderName());
    }
    writer.append(st.render());
	  
	  
	}
	
	void writeBoilerplateHeader(String name, String path, BufferedWriter writer, ST st) throws IOException {
	  writeBoilerplateDTHeader(name, path, writer, st, false);
	}

	void writeBoilerplateFooter(String name, String path, BufferedWriter writer, ST st) throws IOException {
	  st.add("name", name);
	  st.add("path", path);
	  writer.append(st.render());
	}
	
	public void createComponentDispatchTypes(BufferedWriter writer) throws IOException {
    // write dispatcher types
    //ST st = templates.getInstanceOf("componentGlueCodeHeaderPurpose");
    //writer.append(st.render());
    
    for (ThreadImplementation ti : model.getThreadImplementations()) {
      for (Dispatcher d : ti.getDispatcherList()) {
        OutgoingDispatchContract maxCalls = CGUtil.maxDispatcherUse(d.getDispatchLimits());
        for (Map.Entry<OutputEventPort, Integer> entry : maxCalls.getContract().entrySet()) {
          if (entry.getKey().hasData()) {
            ArrayType dispatchArrayType = new ArrayType(entry.getKey().getType(), entry.getValue());
            model.getAstTypes().put(CommonNames.getDispatchArrayTypeName(ti, entry), dispatchArrayType);
          }
        }
      }
    }
    
    C_Type_Writer.writeTypes(writer, model, 6);
	}
	
  public Set<Type> getUserTypes() {
    // write dispatcher types
    Set<Type> rwTypeSet = new HashSet<Type>();
    
    // "for free types" that are always necessary; void for event ports
    // and uint32_t for periodic dispatchers.  Note if the dispatcher 
    // time type changes, it may break code, so perhaps we should 
    // store the time type somewhere (model?)
    
    rwTypeSet.add(new UnitType());
    rwTypeSet.add(new IntType(32, false));  
    
    for (ThreadImplementation ti : model.getThreadImplementations()) {
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

  
  public void createReadWriteIdlInterface(Type t) throws Aadl2RtosFailure {
    TypeNames type = new TypeNames(t); 
    ModelNames m = new ModelNames(model); 
    
    File interfaceFile = new File(interfacesDirectory, type.getReaderWriterIdlFileName());
    String path = interfaceFile.getAbsolutePath();
    String name = type.getReaderWriterInterfaceName(); 
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("CamkesIdl4ReaderWriter.stg");
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
        STGroupFile stg = this.createTemplate("CamkesIdl4SharedVar.stg");
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
      STGroupFile stg = this.createTemplate("CamkesIdl4Dispatch.stg");
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
	
	public void createComponentHeader(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    ThreadImplementationNames tin = new ThreadImplementationNames(ti);
    //ModelNames m = new ModelNames(model); 

    String name = tin.getNormalizedName();
    File interfaceFile = new File(componentDirectory, tin.getComponentGlueCodeHFileName());
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("CamkesComponentHeader.stg");
      writeBoilerplateDTHeader(name, path, writer, stg.getInstanceOf("componentGlueCodeHeaderPrefix"), true);
      
      ST st = stg.getInstanceOf("componentGlueCodeHeaderBody");
      st.add("threadImpl", tin);
      writer.append(st.render() + "\n");
      
      writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("componentGlueCodeHeaderPostfix")); 
    } catch (IOException e) {
      log.error("IO Exception occurred when creating a component header.");
      throw new Aadl2RtosFailure();
    }
	}

	public void createComponentCFile(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    ThreadImplementationNames tin = new ThreadImplementationNames(ti);
	  
	  String name = tin.getNormalizedName();
    File interfaceFile = new File(componentDirectory, tin.getComponentGlueCodeCFileName());
    String path = interfaceFile.getAbsolutePath();
    
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("CamkesComponentC.stg");
      writeBoilerplateHeader(name, path, writer, stg.getInstanceOf("componentGlueCodeCFilePrefix"));
      writer.append("\n\n");
      
      ST st = stg.getInstanceOf("componentCFileDecls");
      st.add("threadImpl", tin);
      writer.append(st.render()); 
      
      writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("componentGlueCodeCFilePostfix")); 
      
    } catch (IOException e) {
      log.error("IO Exception occurred when creating a component header.");
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
    File interfaceFile = new File(componentDirectory, tin.getComponentCamkesFileName());
    String path = interfaceFile.getAbsolutePath();
	  try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      STGroupFile stg = this.createTemplate("CamkesComponentCamkes.stg");
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

	
	
	public void copyComponentFiles(File dstDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
	  // determine the list of source files.
	  Set<String> srcFiles = new HashSet<String>(); 
	  for (Dispatcher d: ti.getDispatcherList()) {
	    srcFiles.addAll(d.getImplementationFileList());
	  }
	  
	  for (String s : srcFiles) {
	    File srcFilePath = new File(aadlDirectory, s); 
	    
	    try { 
	      if (srcFilePath.isFile()) {
  	      String srcFileName = srcFilePath.getName();
  	      File dstPath = new File(dstDirectory, srcFileName);
  	      this.copyFile(new FileInputStream(srcFilePath), new FileOutputStream(dstPath));
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
	
	public void createComponent(ThreadImplementation ti) throws Aadl2RtosFailure { 
	  
	  createDispatchInterface(ti);
	  String name = ti.getNormalizedName();
	  
	  File componentDirectory = new File(componentsDirectory, name);
	  File srcDirectory = new File(componentDirectory, "src");
	  File includeDirectory = new File(componentDirectory, "include");
    srcDirectory.mkdirs();
	  includeDirectory.mkdirs();
    
	  createComponentHeader(includeDirectory, ti);
	  createComponentCFile(srcDirectory, ti);
	  createComponentCamkesFile(componentDirectory, ti);
	  copyComponentFiles(srcDirectory, ti); 
	  
	  ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
    File CFile = new File(componentDirectory, tin.getComponentGlueCodeCFileName());
    this.model.getSourceFiles().add(CFile.getPath());
	}

	public void copyFile(InputStream is, OutputStream os) throws IOException {
    byte[] buffer = new byte[16384];
    int length;
    while ((length = is.read(buffer)) > 0) {
        os.write(buffer, 0, length);
    }
	}
	
  public void createClockDriver(File srcDirectory, File includeDirectory) throws Aadl2RtosFailure {
	  
    String concrete_driver = null; 
    if (model.getHWTarget().equalsIgnoreCase("QEMU")) {
      concrete_driver = "qemu_clock_driver.c";
    } else {
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
      STGroupFile stg = this.createTemplate("CamkesPeriodicDispatcherC.stg");

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
      STGroupFile stg = this.createTemplate("CamkesPeriodicDispatcherCamkes.stg");
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
	
	public void createComponents() throws Aadl2RtosFailure {
	  List<ThreadImplementation> tis = model.getThreadImplementations();
	  for (ThreadImplementation ti: tis) {
	    createComponent(ti);
	  }
	  if (model.getThreadCalendar().hasDispatchers()) {
	    createPeriodicDispatcherComponent();
	  }
	}
	
	public void createTypesHeader() throws Aadl2RtosFailure {
	  ModelNames mn = new ModelNames(model); 
	  
    String hname = mn.getSystemTypeHeaderName();
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(includeDirectory, hname);
    String path = HFile.getAbsolutePath();

    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      STGroupFile stg = this.createTemplate("CamkesTypesHeader.stg");
      writeBoilerplateHeader(sysInstanceName, path, hwriter, stg.getInstanceOf("datatypesPrefix"));
      
      createComponentDispatchTypes(hwriter);
      
      writeBoilerplateFooter(sysInstanceName, hname, hwriter, stg.getInstanceOf("datatypesPostfix"));

    } catch (IOException e) {
      log.error("IOException occurred during CAmkES write: " + e);
      throw new Aadl2RtosFailure();
    }
	}

	void createAssembly() throws Aadl2RtosFailure {
	  ModelNames mn = new ModelNames(model); 
	  String hname = mn.getSystemAssemblyFileName();
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(outputDirectory, hname);
    String path = HFile.getAbsolutePath();

    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      STGroupFile stg = this.createTemplate("CamkesAssembly.stg");
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
	
	public void write() throws Aadl2RtosFailure {
	  createTypesHeader();
    createReadWriteIdlInterfaces();
    createSharedVariableIdlInterfaces();
    createComponents();
    createAssembly(); 
	}
}

