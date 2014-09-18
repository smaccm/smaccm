package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
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
    
    Map<String, Type> typeMap = new HashMap<String, Type>();      
    for (ThreadImplementation ti : model.getThreadImplementations()) {
      for (Dispatcher d : ti.getDispatcherList()) {
        OutgoingDispatchContract maxCalls = CGUtil.maxDispatcherUse(d.getDispatchLimits());
        for (Map.Entry<OutputEventPort, Integer> entry : maxCalls.getContract().entrySet()) {
          ArrayType dispatchArrayType = new ArrayType(entry.getKey().getType(), entry.getValue());
          typeMap.put(CommonNames.getDispatchArrayTypeName(ti, entry), dispatchArrayType);
        }
      }
    }
    C_Type_Writer.writeTypes(writer, typeMap, 2);
	}
	
  public Set<Type> getReaderWriterTypes() {
    // write dispatcher types
    Set<Type> rwTypeSet = new HashSet<Type>();
    rwTypeSet.add(new UnitType());
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
    for (Type t : getReaderWriterTypes()) {
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

	public void writeDispatcherCFunction(BufferedWriter writer, Dispatcher d, Type indexType) throws IOException {
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(d.getDispatchLimits());
    OutgoingDispatchContractNames odcNames = new OutgoingDispatchContractNames(odc); 
    
    List<DispatchContractNames> dispatchContracts = odcNames.getContracts(); 
    DispatcherNames dispatcher = new DispatcherNames(d);
    ST st = templates.getInstanceOf("componentDispatcherFn");
    st.add("dispatcher", dispatcher); 
    st.add("dispatchContracts", dispatchContracts); 
    writer.append(st.render());
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
      writer.append("\n\n");
      writer.append("#include <" + mn.getSystemTypeHeaderName() + ">\n");
      writer.append("#include <" + tin.getDispatcherComponentHFileName() + ">\n\n");
      
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
	
	// create this only if we have periodic threads.
	
	public void createPeriodicDispatcherComponent() throws Aadl2RtosFailure {
	  ModelNames mn = new ModelNames(model); 
    String name = mn.getThreadCalendar().getPeriodicDispatcherComponentName();
    TypeNames tn = new TypeNames(PeriodicDispatcher.getPeriodicDispatcherType());
    
    File componentDirectory = new File(componentsDirectory, mn.getThreadCalendar().getPeriodicDispatcherComponentName());
    componentDirectory.mkdirs();
    
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
      
      C_Type_Writer.writeTypes(hwriter, model, 6);
      
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

// read/write ports
/*
for (ThreadInstancePort ip : ti.getThreadInstanceInputPorts()) {
  if (ip.getPort() instanceof InputDataPort) {
    sd.writeComment(writer, "Writing the shared data declarations and accessors for " + ip.getPort().getName());
    sd.writeThreadInstancePortSharedVars(writer, ip);
    sd.writeReader(writer, ti, (InputPort)ip.getPort());
  }
}
    // generate local declarations to OutputEventPorts
    for (OutputEventPort oep : ti.getAllOutputEventPorts()) {
      ST st = templates.getInstanceOf("cPortTempVarDeclarations");
      st.add("port", new PortNames(oep)); 
      writer.append(st.render());
    }
    // generate dispatchers
    for (Dispatcher d : ti.getDispatcherList()) {
      writeDispatcherCFunction(writer, d, indexType);
    }
    
    // generate senders to OutputEventPorts
    for (OutputEventPort oep : ti.getAllOutputEventPorts()) {
      ST st = templates.getInstanceOf("componentSendFunction");
      st.add("port", new PortNames(oep));
      writer.append(st.render());
    }
          // generate internal reader/writer functions for input data ports
    for (InputDataPort idp : ti.getInputDataPortList()) {
      ST st = templates.getInstanceOf("componentLocalReaderDecl");
      st.add("port", new PortNames(idp));
      writer.append(st.render());
    }

*/


/*
 * 
 *       if (!ti.getInputEventPortList().isEmpty()) {
      writer.append("\n// Functions for managing event queues ");
    }
    for (InputEventPort iep : ti.getInputEventPortList()) {
      st = templates.getInstanceOf("dispatcherComponentReceiverDecls");
      PortNames port = new PortNames(iep); 
      st.add("port", port);
      writer.append(st.render()); 
    }

    writer.append("\n// passive dispatchers to components \n\n");
    for (ThreadImplementation other: model.getThreadImplementations()) {
      for (Dispatcher d: other.getDispatcherList()) {
        st = templates.getInstanceOf("dispatcherComponentDataDispatchFunction");
        DispatcherNames dispatcher = new DispatcherNames(d);
        st.add("dispatcher", dispatcher);
        writer.append(st.render()); 
      }
    }
    writer.append("\n// Functions for managing input dispatchers ");
    for (Dispatcher d : ti.getDispatcherList()) {
      st = templates.getInstanceOf("dispatcherComponentEventDecls");
      st.add("dispatcher", new DispatcherNames(d));
      writer.append(st.render());
    }
    // write 'run' function
    st = templates.getInstanceOf("dispatcherComponentMainFunction");
    ThreadImplementationNames threadImplementation = new ThreadImplementationNames(ti); 
    st.add("threadImpl", threadImplementation); 
    writer.append(st.render());

    

 */


/*
void addReaderWriterConnection(ST parent, DataPort dp, String direction) {
  PortNames pn = new PortNames(dp); 
  TypeNames tn = pn.getType();

  ST stChild = templates.getInstanceOf("camkesDeclaration"); 
  String type = tn.getReaderWriterInterfaceName();
  stChild.addAggr("arg.{direction,  type, id}", "provides", type, dp.getName());
  parent.add("connections", stChild);
  parent.add("connections", "has mutex " + pn.getMutex() + ";");
  parent.add("connectionIdls", tn.getReaderWriterIdlFileName());
}

void addSharedDataConnection(ST parent, SharedDataAccessor sda, String direction) {
  SharedDataAccessorNames sdan = new SharedDataAccessorNames(sda); 
  TypeNames tn = sdan.getType();
  
  ST stChild = templates.getInstanceOf("camkesDeclaration"); 
  String type = tn.getSharedDataInterfaceName();
  stChild.addAggr("arg.{direction,  type, id}", "provides", type, sdan.getName());
  parent.add("connections", stChild);
  parent.add("connectionIdls", tn.getSharedDataIdlFileName());
}
    st.add("connections", "provides " + tin.getIdlName() + " " +  
        tin.getComponentDispatcherInterfaceVarIdName() +  "; ");
    for (InputDataPort idp : ti.getInputDataPortList()) {
      addReaderWriterConnection(st, idp, "provides");
    }
    
    for (OutputDataPort odp : ti.getOutputDataPortList()) {
      addReaderWriterConnection(st, odp, "uses");
    }
    
    for (SharedDataAccessor sda : ti.getSharedDataAccessors()) {
      addSharedDataConnection(st, sda, "uses");
    }
*/

/*
Set<Type> getActiveThreadSRTypes() {
  Set<Type> srTypes = new HashSet<Type>(); 
  srTypes.add(new UnitType());
  for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
    for (DataPort dp : ti.getOutputEventDataPortList()) {
      srTypes.add(dp.getType());
    }
    for (DataPort dp : ti.getInputEventDataPortList()) {
      srTypes.add(dp.getType());
    }
  }
  return srTypes;
}

 *       writer.append("// Passive component dispatch interfaces \n\n");
    for (ThreadImplementation pti : model.getThreadImplementations()) {
      ThreadImplementationNames ptin = new ThreadImplementationNames(pti); 
      writer.append("import \"../../interfaces/" + ptin.getIdlFileName() + "\";\n");
    }

    writer.append("\n // Send/receive interfaces for active components \n\n");
    for (Type t : getActiveThreadSRTypes()) {
      TypeNames tn = new TypeNames(t); 
      writer.append("import \"../../interfaces/" + tn.getReaderWriterIdlFileName() + "\";\n");
    }
    writer.append("\n\n");
    
    ST st = templates.getInstanceOf("dispatcherComponentCamkesBody");
    
    // We need to provide active thread receivers for this thread
    for (Dispatcher d : ti.getDispatcherList()) {
     DispatcherNames dn = new DispatcherNames(d); 
     TypeNames tn = dn.getType();
      st.add("incomingDispatch", "provides " +  
          tn.getReaderWriterInterfaceName() + " " +  
          dn.getDispatcherComponentDispatchName() + "; ");
      if (d instanceof InputEventDispatcher) {
        st.add("incomingDispatch", "has mutex " + 
            dn.getInputEventDispatcherPort().getMutex());
      } 
    }
    st.add("outgoingDispatch", "has mutex " + tin.getDispatcherComponentMutexName() + ";");
    for (ThreadImplementation oti : model.getThreadImplementations()) {
      if (oti != ti) {
        for (Dispatcher d : ti.getDispatcherList()) {
          if (d instanceof InputEventDispatcher) {
            DispatcherNames dn = new DispatcherNames(d); 
            st.add("outgoingDispatch", "uses " + 
                dn.getType().getReaderWriterInterfaceName() + " " +
                dn.getDispatcherComponentDispatchName() + "; ");
          }
        }
      }
    }

    // We finally need to use every active component dispatch interface
    for (ThreadImplementation pi: model.getThreadImplementations()) {
      ThreadImplementationNames pin = new ThreadImplementationNames(pi); 
      st.add("passiveReceivers", "uses " + 
          pin.getIdlName() + " " +  
         pin.getInterfaceInstanceName() + "; "); 
    }
    
    // for each send/receive interface, we implement the send/receive interface.
    st.add("name", name);
    writer.append(st.render());
 */
/*
  void createComponentInstance(ST parent, String type, String name) {
    ST child = templates.getInstanceOf("componentDef");
    child.add("ctype", type);
    child.add("cname", name);
    parent.add("components", child);
  }
  
  int connNumber = 1; 
  void createConnection(ST parent, String from, String to) {
    ST child = templates.getInstanceOf("rpcConnection");
    child.add("cid", "conn_" + connNumber);
    connNumber++; 
    child.add("from", from);
    child.add("to", to);
    parent.add("connections", child);
  }

 *       hwriter.append("import <std_connector.camkes>;\n");
      if (model.getThreadCalendar().hasDispatchers()) {
        hwriter.append("import \"" + mn.getThreadCalendar().getPeriodicDispatcherPathName() + "\";\n\n"); 
      }

      // write CAmkES component and connector imports.
      for (ThreadImplementation ti : model.getThreadImplementations()) {
        ThreadImplementationNames threadImplementation = new ThreadImplementationNames(ti); 
        hwriter.append("import \"" + threadImplementation.getRootToCamkesComponentFilePath() + "\";\n");
        if (!ti.getIsPassive()) {
          hwriter.append("import \"" + threadImplementation.getRootToCamkesDispatcherComponentFilePath() + "\";\n");
        }
      }
       // write the individual components: one per thread + one dispatcher per active thread.
      for (ThreadImplementation ti: model.getThreadImplementations()) {
        ThreadImplementationNames tin = new ThreadImplementationNames(ti);
        createComponentInstance(st, tin.getComponentName(),
                                tin.getComponentInstanceName());
      }
      
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        ThreadImplementationNames tin = new ThreadImplementationNames(ti);
        createComponentInstance(st, tin.getDispatcherComponentName(), 
              tin.getDispatcherComponentInstanceName());
      }

      // create periodic dispatcher component (if any periodic dispatches)
      if (model.getThreadCalendar().hasDispatchers()) {
        createComponentInstance(st, mn.getThreadCalendar().getPeriodicDispatcherComponentName(),
            mn.getThreadCalendar().getPeriodicDispatcherInstanceName());
      }
      // connect all dispatchers to all passive dispatch interfaces.
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
        for (ThreadImplementation tj: model.getThreadImplementations()) {
          ThreadImplementationNames tjn = new ThreadImplementationNames(tj);
          String from = tin.getDispatcherComponentInstanceName() + "." + tjn.getNormalizedName(); 
          String to = tjn.getComponentInstanceName() + "." + tjn.getComponentDispatcherInterfaceVarIdName(); 
          createConnection(st, from, to);
        }
      }
      
      // connect all dispatchers to all other dispatchable "active" send/receive interfaces
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
        for (ThreadImplementation tj: model.getActiveThreadImplementations()) {
          if (ti != tj) {
            ThreadImplementationNames tjn = new ThreadImplementationNames(tj);
            for (Dispatcher d : tj.getDispatcherList()) {
              DispatcherNames dn = new DispatcherNames(d);
              if (d instanceof InputEventDispatcher) {
                String from = tin.getDispatcherComponentInstanceName() + "." +
                    dn.getDispatcherComponentDispatchName();
                String to = tjn.getDispatcherComponentInstanceName() + "." +
                    dn.getDispatcherComponentDispatchName();
                createConnection(st, from, to); 
              }
            }
          }
        }
      }
      // connect smaccm_periodic_dispatcher to periodic dispatchers 
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
        for (Dispatcher d: ti.getDispatcherList()) {
          if (d instanceof PeriodicDispatcher) {
            DispatcherNames dn = new DispatcherNames(d);
            String from = mn.getThreadCalendar().getPeriodicDispatcherInstanceName() + "." + 
                dn.getDispatcherComponentDispatchName() ; 
            String to = tin.getDispatcherComponentInstanceName() + "." + 
                dn.getDispatcherComponentDispatchName();
            createConnection(st, from, to);
          }
        }
      }
      
  public ThreadInstance getThreadInstance(ThreadImplementation ti) throws Aadl2RtosFailure {
    List<ThreadInstance> tii = ti.getThreadInstanceList();
    if (tii.size() != 1) {
      log.error("Currently multiple thread instances per implementation are not supported.");
      throw new Aadl2RtosFailure();
    }
    return tii.get(0);
  }
  
      // connect all passive component read/write ports
      for (ThreadImplementation ti: model.getThreadImplementations()) {
        ThreadInstance src = this.getThreadInstance(ti);
        for (Connection c : src.getIsSrcOfConnectionList()) {
          OutputPort srcPort = c.getSourcePort();
          PortNames srcpn = new PortNames(srcPort); 
          if (srcPort instanceof OutputDataPort) {
            ThreadInstance dst = c.getDestThreadInstance(); 
            InputPort dstPort = c.getDestPort(); 
            PortNames dstpn = new PortNames(dstPort); 
            ThreadImplementation dstImpl = dst.getThreadImplementation(); 
            
            ThreadImplementationNames tin = new ThreadImplementationNames(ti); 
            ThreadImplementationNames dstin = new ThreadImplementationNames(dstImpl);  
            
            String from = tin.getComponentInstanceName() + "." + 
                srcpn.getName();
            String to = dstin.getComponentInstanceName() + "." +
                dstpn.getName();
            createConnection(st, from, to);
          }
        }
      }
      
      
*/
