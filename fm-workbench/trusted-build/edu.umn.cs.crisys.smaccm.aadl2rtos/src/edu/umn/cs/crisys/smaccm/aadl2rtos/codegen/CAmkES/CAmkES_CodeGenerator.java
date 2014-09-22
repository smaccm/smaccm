package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.PluginActivator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CGUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.C_Type_Writer;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CommonNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.HeaderDeclarations;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.SourceDeclarations;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
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
	private File rootDirectory;
	private File componentsDirectory;
	private File interfacesDirectory;
	private File includeDirectory;
	private STGroup templates;
	private String date;
  private SourceDeclarations sd = new SourceDeclarations(new CAmkESSystemPrimitives());
	private STErrorListener listener; 
  
	public List<ThreadImplementation> allThreads;

	// so write threadName_write_portName for each port.

	public CAmkES_CodeGenerator(Logger log, Model model, File dir) {
		this.log = log;
		this.model = model;
		this.rootDirectory = dir;
		
		listener = new CAmkESSTErrorListener(log);
		this.templates = new STGroupFile("templates/CaMKes.stg");
		this.templates.setListener(listener);
		
		//this.templates.verbose = true;
		
		//templates.registerRenderer(Type.class, new C_Type_Renderer());

		// Create component directory
		componentsDirectory = 
		    new File(rootDirectory, "components");
		
		includeDirectory = 
		    new File(rootDirectory, "include");
		includeDirectory.mkdirs();
		
		interfacesDirectory = 
		    new File(rootDirectory, "interfaces");
		interfacesDirectory.mkdirs();
		System.out.println(interfacesDirectory.getAbsolutePath());

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date d = new Date();
    date = dateFormat.format(d);  
	}

	void writeBoilerplateDTHeader(String name, String path, BufferedWriter writer, String templateId, boolean usesDTHeader) throws IOException {
    ST st = templates.getInstanceOf(templateId);
    st.add("name", name);
    st.add("date", date);
    st.add("path", path);
    if (usesDTHeader) {
      ModelNames mn = new ModelNames(model); 
      st.add("datatypesHeader", mn.getSystemTypeHeaderName());
    }
    writer.append(st.render());
	  
	  
	}
	
	void writeBoilerplateHeader(String name, String path, BufferedWriter writer, String templateId) throws IOException {
	  writeBoilerplateDTHeader(name, path, writer, templateId, false);
	}

	void writeBoilerplateFooter(String name, String path, BufferedWriter writer, String templateId) throws IOException {
	  ST st = templates.getInstanceOf(templateId);
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
          ArrayType dispatchArrayType = new ArrayType(entry.getKey().getType(), entry.getValue());
          model.getAstTypes().put(CommonNames.getDispatchArrayTypeName(ti, entry), dispatchArrayType);
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
      writeBoilerplateHeader(name, path, writer, "rwInterfaceIdlPrefix");
      ST st = templates.getInstanceOf("idlProc");
      st.add("type", type);
      st.add("model", m);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, "rwInterfaceIdlPostfix");
      
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
        writeBoilerplateHeader(name, path, writer, "svInterfaceIdlPrefix");
        
        ST st = templates.getInstanceOf("svProc"); 
        st.add("type", type);
        st.add("model", m);
        writer.append(st.render());
        
        writeBoilerplateFooter(name, path, writer, "svInterfaceIdlPostfix");
        
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
	    writeBoilerplateHeader(name, path, writer, "dispatchInterfaceIdlPrefix");
	    
	    ST di = templates.getInstanceOf("dispatcherProc");
	    di.add("threadImpl", tin);
	    di.add("datatypesHeader", m.getSystemTypeHeaderName());
  	  writer.append(di.render());
  	  writeBoilerplateFooter(name, path, writer, "dispatchInterfaceIdlPostfix"); 
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
      writeBoilerplateDTHeader(name, path, writer, "componentGlueCodeHeaderPrefix", true);
      
      ST st = templates.getInstanceOf("componentGlueCodeHeaderBody");
      st.add("threadImpl", tin);
      writer.append(st.render() + "\n");
      
      writeBoilerplateFooter(name, path, writer, "componentGlueCodeHeaderPostfix"); 
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
      writeBoilerplateHeader(name, path, writer, "componentGlueCodeCFilePrefix");
      writer.append("\n\n");
      
      ST st = templates.getInstanceOf("componentCFileDecls");
      st.add("threadImpl", tin);
      writer.append(st.render()); 
      
      writeBoilerplateFooter(name, path, writer, "componentGlueCodeCFilePostfix"); 
      
    } catch (IOException e) {
      log.error("IO Exception occurred when creating a component header.");
      throw new Aadl2RtosFailure();
    }
	}

  public void createDispatcherComponentCFile(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    String name = ti.getNormalizedName();
    ThreadImplementationNames tin = new ThreadImplementationNames(ti);
    ModelNames mn = new ModelNames(model);
    
    File interfaceFile = new File(componentDirectory, tin.getDispatcherComponentGlueCodeCFileName());
    String path = interfaceFile.getAbsolutePath();
    
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateHeader(name, path, writer, "dispatcherComponentGlueCodeCFilePrefix");
      
      ST st = templates.getInstanceOf("dispatcherComponentCDecls");
      st.add("threadImpl", tin);
      st.add("model", mn);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, "dispatcherComponentGlueCodeCFilePostfix"); 
      
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
	    writeBoilerplateDTHeader(name, path, writer, "componentCamkesPrefix", true);
  
      ST st = templates.getInstanceOf("componentCamkesBody");
      st.add("threadImpl", tin);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, "componentCamkesPostfix");
	  } catch (IOException e) {
	    log.error("Problem creating camkes component file.");
	    throw new Aadl2RtosFailure(); 
	  }
	  
	}

	
	// the dispatcher component implements all the incoming interfaces for the components that are called by the 
	// computation tree possible from the originating active thread as a proxy.  It also uses the same interfaces
	// to make the actual queued calls once the component has completed its execution.
	public void createDispatcherComponent(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    ThreadImplementationNames tin = new ThreadImplementationNames(ti);
    String name = tin.getDispatcherComponentName();
    File interfaceFile = new File(componentDirectory, tin.getDispatcherComponentCamkesFileName());
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateDTHeader(name, path, writer, "dispatcherComponentCamkesPrefix", true);
      writer.append("\n");

      ST st = templates.getInstanceOf("dispatchComponentCDecls"); 
      st.add("model", new ModelNames(model));
      st.add("threadImpl", tin);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, "dispatcherComponentCamkesPostfix");
    
    } catch (IOException e) {
      log.error("Problem creating camkes component dispatcher file.");
      throw new Aadl2RtosFailure(); 
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

	  if (!ti.getIsPassive()) {
	    File dispatchersDirectory = new File(componentsDirectory, "dispatch_" + name);
	    File dispatchersSrcDirectory = new File(dispatchersDirectory, "src"); 
	    dispatchersSrcDirectory.mkdirs(); 
	    createDispatcherComponent(dispatchersDirectory, ti);
	    createDispatcherComponentCFile(dispatchersSrcDirectory, ti);
	  }
	  
	  ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
    File CFile = new File(componentDirectory, tin.getComponentGlueCodeCFileName());
    this.model.getSourceFiles().add(CFile.getPath());
	}

	@SuppressWarnings("resource")
  public void createClockDriver(File srcDirectory, File includeDirectory) throws Aadl2RtosFailure {
	  
	  File cdest = new File(srcDirectory, "qemu_clock_driver.c");
    File hdest = new File(includeDirectory, "clock_driver.h");
    FileChannel cFileSrcChannel = null;
    FileChannel hFileSrcChannel = null;
    FileChannel cFileDstChannel = null;
    FileChannel hFileDstChannel = null; 
    // write the .c / .h files to the destination component
    try {
      try {
        InputStream cFileStream = FileLocator.openStream(
            PluginActivator.getDefault().getBundle(), new Path("templates/qemu_clock_driver.c"), false);
        InputStream hFileStream = FileLocator.openStream(
            PluginActivator.getDefault().getBundle(), new Path("templates/clock_driver.h"), false);
        cFileSrcChannel = ((FileInputStream)cFileStream).getChannel();
        hFileSrcChannel = ((FileInputStream)hFileStream).getChannel();
        cFileDstChannel = (new FileOutputStream(cdest)).getChannel();
        hFileDstChannel = (new FileOutputStream(hdest)).getChannel(); 
        
        cFileDstChannel.transferFrom(cFileSrcChannel, 0, cFileSrcChannel.size());
        hFileDstChannel.transferFrom(hFileSrcChannel, 0, hFileSrcChannel.size());
      } catch (IOException ioe) {
        log.error("IOException occurred during clock driver write: " + ioe);
        log.error("Continuing anyway...");
        // throw new Aadl2RtosFailure();
      } finally {
        if (cFileSrcChannel != null) { cFileSrcChannel.close(); }
        if (hFileSrcChannel != null) { hFileSrcChannel.close(); }
        if (cFileDstChannel != null) { cFileDstChannel.close(); }
        if (hFileDstChannel != null) { hFileDstChannel.close(); }
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

      writeBoilerplateHeader(name, path, writer, "periodicDispatcherCPrefix");
      writer.append("\n"); 
      writer.append("#include <" + mn.getSystemTypeHeaderName() + ">\n");
      writer.append("#include <clock_driver.h>\n\n");
      writer.append("#include <string.h>\n\n");
      writer.append("#include <" + tcn.getPeriodicDispatcherCamkesHFileName() +  ">\n");
      
      ST st = templates.getInstanceOf("periodicComponentCBody"); 
      st.add("threadCalendar", tcn);
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, "periodicDispatcherCPostfix");
    
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
      writeBoilerplateDTHeader(name, path, writer, "componentCamkesPrefix", true);
      writer.append("\n");
  
      writer.append("import \"../../interfaces/" + 
          tn.getReaderWriterIdlFileName() + "\";\n");
      writer.append("\n\n");
      ST st = templates.getInstanceOf("periodicDispatcherCamkesBody");
      st.add("model", mn);
      st.add("type", tn);
  	  writer.append(st.render());
      writeBoilerplateFooter(name, path, writer, "componentCamkesPostfix");

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
      writeBoilerplateHeader(sysInstanceName, path, hwriter, "datatypesPrefix");
      
      ST st = templates.getInstanceOf("componentGlueCodeHeaderPurpose");
      ST st2 = templates.getInstanceOf("blockComment");
      st2.add("arg", st.render());
      hwriter.append(st2.render());
      
      createComponentDispatchTypes(hwriter);
      
      writeBoilerplateFooter(sysInstanceName, hname, hwriter, "datatypesPostfix");

    } catch (IOException e) {
      log.error("IOException occurred during CAmkES write: " + e);
      throw new Aadl2RtosFailure();
    }
	}

	void createAssembly() throws Aadl2RtosFailure {
	  ModelNames mn = new ModelNames(model); 
	  String hname = mn.getSystemAssemblyFileName();
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(rootDirectory, hname);
    String path = HFile.getAbsolutePath();

    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      writeBoilerplateHeader(sysInstanceName, path, hwriter, "camkesAssemblyPrefix");

      ST st = templates.getInstanceOf("camkesAssemblyBody");
      st.add("model", mn);
      
      hwriter.append(st.render());
      writeBoilerplateFooter(sysInstanceName, hname, hwriter, "stdFilePostfix");

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

