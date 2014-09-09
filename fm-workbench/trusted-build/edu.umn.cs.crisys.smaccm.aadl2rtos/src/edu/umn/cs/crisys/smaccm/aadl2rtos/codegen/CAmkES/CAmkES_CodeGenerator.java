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
	private File rootDirectory;
	private File componentsDirectory;
	private File interfacesDirectory;
	private STGroup templates;
	private String date;
  private SourceDeclarations sd = new SourceDeclarations(new CAmkESSystemPrimitives());
	
	public List<ThreadImplementation> allThreads;

	// so write threadName_write_portName for each port.

	public CAmkES_CodeGenerator(Logger log, Model model, File dir) {
		this.log = log;
		this.model = model;
		this.rootDirectory = dir;
		
		this.templates = new STGroupFile("templates/CaMKes.stg");
		//templates.registerRenderer(Type.class, new C_Type_Renderer());

		// Create component directory
		componentsDirectory = 
		    new File(rootDirectory, "components");
		
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
      st.add("datatypesHeader", Names.getSystemTypeHeaderName(model));
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
       rwTypeSet.add(d.getDataType());
    }
    return rwTypeSet ; 
  }

  
  public void createReadWriteIdlInterface(Type t) throws Aadl2RtosFailure {
    String name = Names.getReaderWriterInterfaceName(t);
    File interfaceFile = new File(interfacesDirectory, Names.getReaderWriterIdlFileName(t));
    String path = interfaceFile.getAbsolutePath();
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateHeader(name, path, writer, "rwInterfaceIdlPrefix");

      if (t instanceof UnitType) {
        ST st = templates.getInstanceOf("rwUnitProc");
        st.add("name", name);
        writer.append(st.render());
      } else {
        ST st = templates.getInstanceOf("rwProc"); 
        st.add("name", name); 
        st.add("type", t.getCType().typeString());
        st.add("datatypesHeader", Names.getSystemTypeHeaderName(model));
        writer.append(st.render());
      }
      
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
       svTypeSet.add(d.getDataType());
    }
    return svTypeSet ; 
  }

  public void createSharedVariableIdlInterfaces() throws Aadl2RtosFailure {
    for (Type t : getSharedVariableTypes()) {
      String name = Names.getSharedDataInterfaceName(t);
      File interfaceFile = new File(interfacesDirectory, Names.getSharedDataIdlFileName(t));
      String path = interfaceFile.getAbsolutePath();
      
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
        writeBoilerplateHeader(name, path, writer, "svInterfaceIdlPrefix");
        
        ST st = templates.getInstanceOf("svProc"); 
        st.add("name", name); 
        st.add("type", t.getCType().typeString());
        st.add("datatypesHeader", Names.getSystemTypeHeaderName(model));
        writer.append(st.render());
        
        writeBoilerplateFooter(name, path, writer, "svInterfaceIdlPostfix");
        
      } catch (IOException e) {
        log.error("IO Exception occurred when creating a shared variable interface.");
        throw new Aadl2RtosFailure();
      }
    }
  }

  public void createSendReceiveIdlInterfaces() throws Aadl2RtosFailure {
    for (Type t : getSharedVariableTypes()) {
      String name = Names.getSharedDataInterfaceName(t);
      File interfaceFile = new File(interfacesDirectory, Names.getSharedDataIdlFileName(t));
      String path = interfaceFile.getAbsolutePath();
      
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
        writeBoilerplateHeader(name, path, writer, "svInterfaceIdlPrefix");
        
        ST st = templates.getInstanceOf("svProc"); 
        st.add("name", name); 
        st.add("type", t.getCType().typeString());
        st.add("datatypesHeader", Names.getSystemTypeHeaderName(model));
        writer.append(st.render());
        
        writeBoilerplateFooter(name, path, writer, "svInterfaceIdlPostfix");
        
      } catch (IOException e) {
        log.error("IO Exception occurred when creating a shared variable interface.");
        throw new Aadl2RtosFailure();
      }
    }
  }

  public String createDispatcherCamkesPrototype(Dispatcher d) {
    ST dispatcher = templates.getInstanceOf("dispatcherPrototype");
    dispatcher.add("name", d.getName());
    if (!(d.getType() instanceof UnitType)) {
      dispatcher.add("arg", "in " + d.getType().getCType().varString("smaccm_in"));
    }
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(d.getDispatchLimits());
    Type t = new IntType(32, false); 
    for (Map.Entry<OutputEventPort, Integer> entry : odc.getContract().entrySet()) {
      dispatcher.add("arg", "out " + 
          CommonNames.getDispatchArrayTypeName(d.getOwner(), entry) + " " +  
          Names.getDispatcherInterfaceName(entry.getKey()));
      dispatcher.add("arg", "out " + t.getCType().typeString() + " " + 
          Names.getDispatcherInterfaceUsedName(entry.getKey()));
    }
    return dispatcher.render(); 
  }

  public String createDispatcherCPrototype(Dispatcher d) {
    ST dispatcher = templates.getInstanceOf("dispatcherDeclaration");
    dispatcher.add("name", d.getName());
    if (!(d.getType() instanceof UnitType)) {
      dispatcher.add("arg", "const " + CommonNames.createRefParameter(d.getType(), "dispatch_in"));
    }
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(d.getDispatchLimits());
    Type t = new IntType(32, false); 
    for (Map.Entry<OutputEventPort, Integer> entry : odc.getContract().entrySet()) {
      dispatcher.add("arg",  
          CommonNames.getDispatchArrayTypeName(d.getOwner(), entry) + " " +  
          Names.getDispatcherInterfaceName(entry.getKey()));
      dispatcher.add("arg", CommonNames.createRefParameter(t, 
          Names.getDispatcherInterfaceUsedName(entry.getKey())));
    }
    return dispatcher.render(); 
  }
  
  public void createDispatchInterface(ThreadImplementation ti) throws Aadl2RtosFailure {

	  String name = Names.getComponentDispatcherProcName(ti);
	  File interfaceFile = new File(interfacesDirectory, Names.getComponentIdlFileName(ti));
    String path = interfaceFile.getAbsolutePath();
    
	  try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
	    writeBoilerplateHeader(name, path, writer, "dispatchInterfaceIdlPrefix");
	    
  	  // create dispatchers based on 'send' types.
  	  // TODO: We will eventually want add'l dispatchers once we 
  	  // add support for RPCs
	    ST di = templates.getInstanceOf("dispatcherProc");
	    di.add("name", name);
	    di.add("datatypesHeader", Names.getSystemTypeHeaderName(model));
	    
	    for (Dispatcher d : ti.getDispatcherList()) {
	       di.add("dispatchers", createDispatcherCamkesPrototype(d));
  	  }
  	  writer.append(di.render());
  	  writeBoilerplateFooter(name, path, writer, "dispatchInterfaceIdlPostfix"); 
	  } catch (IOException e) {
	    log.error("IO Exception occurred when creating a component dispatch interface.");
	    throw new Aadl2RtosFailure();
	  }
	}
	
	public void createComponentHeader(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
	  String name = ti.getNormalizedName();
    File interfaceFile = new File(componentDirectory, Names.getComponentGlueCodeHFileName(ti));
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateDTHeader(name, path, writer, "componentGlueCodeHeaderPrefix", true);
      HeaderDeclarations.writeReaderWriterDecl(ti, writer);
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
    String name = ti.getNormalizedName();
    String hfile = Names.getComponentGlueCodeHFileName(ti);
    File interfaceFile = new File(componentDirectory, Names.getComponentGlueCodeCFileName(ti));
    String path = interfaceFile.getAbsolutePath();
    
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateHeader(name, path, writer, "componentGlueCodeCFilePrefix");
      writer.append("\n\n");
      
      // generate includes
      sd.writeStdIncludes(writer, hfile);

      // read/write ports
      for (ThreadInstancePort ip : ti.getThreadInstanceInputPorts()) {
        if (ip.getPort() instanceof InputDataPort) {
          sd.writeComment(writer, "Writing the shared data declarations and accessors for " + ip.getPort().getName());
          sd.writeThreadInstancePortSharedVars(writer, ip);
          sd.writeReader(writer, ti, (InputPort)ip.getPort());
        }
      }
      
      // generate local declarations to OutputEventPorts
      Type indexType = new IntType(32, false); 
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

      writeBoilerplateFooter(name, path, writer, "componentGlueCodeHeaderPostfix"); 
      
    } catch (IOException e) {
      log.error("IO Exception occurred when creating a component header.");
      throw new Aadl2RtosFailure();
    }
	}
	
  public void createDispatcherComponentCFile(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    String name = ti.getNormalizedName();
    String hfile = Names.getComponentGlueCodeHFileName(ti);
    File interfaceFile = new File(componentDirectory, Names.getDispatcherComponentGlueCodeCFileName(ti));
    String path = interfaceFile.getAbsolutePath();
    
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateHeader(name, path, writer, "dispatcherComponentGlueCodeCFilePrefix");
      writer.append("\n\n");
      
      // generate includes
      sd.writeStdIncludes(writer, hfile);
      
      // write 'run' function
      ST st = templates.getInstanceOf("dispatcherComponentMainFunction");
      ThreadImplementationNames threadImplementation = new ThreadImplementationNames(ti); 
      st.add("threadImplementation", threadImplementation); 
      writer.append(st.render());
      
      for (Dispatcher d: ti.getDispatcherList()) {
        st = templates.getInstanceOf("dispatcherComponentDataDispatchFunction");
        DispatcherNames dispatcher = new DispatcherNames(d);
        st.add("dispatcher", dispatcher);
        writer.append(st.render()); 
      }
      
      writer.append("\n// Subsequent passive dispatchers after initial dispatch \n\n");
      for (ThreadImplementation other: model.getPassiveThreadImplementations()) {
        for (Dispatcher d: other.getDispatcherList()) {
          st = templates.getInstanceOf("dispatcherComponentDataDispatchFunction");
          DispatcherNames dispatcher = new DispatcherNames(d);
          st.add("dispatcher", dispatcher);
          writer.append(st.render()); 
        }
      }
      
      writeBoilerplateFooter(name, path, writer, "dispatcherComponentGlueCodeCFilePostfix"); 
      
    } catch (IOException e) {
      log.error("IO Exception occurred when creating a component header.");
      throw new Aadl2RtosFailure();
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
	
	void addReaderWriterConnection(ST parent, DataPort dp, String direction) {
    ST stChild = templates.getInstanceOf("camkesDeclaration"); 
    Type t = dp.getType();
    String type = Names.getReaderWriterInterfaceName(t);
    stChild.addAggr("arg.{direction,  type, id}", "provides", type, dp.getName());
    parent.add("connections", stChild);
    parent.add("connections", "has mutex " + Names.getReaderWriterMutexName(dp) + ";");
    parent.add("connectionIdls", Names.getReaderWriterIdlFileName(t));
	}
	
  void addSharedDataConnection(ST parent, SharedDataAccessor dp, String direction) {
    ST stChild = templates.getInstanceOf("camkesDeclaration"); 
    Type t = dp.getSharedData().getDataType();
    String type = Names.getSharedDataInterfaceName(dp.getSharedData().getDataType());
    stChild.addAggr("arg.{direction,  type, id}", "provides", type, dp.getName());
    parent.add("connections", stChild);
    parent.add("connectionIdls", Names.getSharedDataIdlFileName(t));
  }

  // For a given thread implementation, how do we connect it to the other components?
  // We can go from a thread implementation to a thread instance.  In our case (for now), 
  // There is only one thread instance per implementation.  So, we get the thread 
  // instance, follow the connections to other thread instances, and map back up to the 
  // other thread implementations.  From here, we can grab the idl file for that thread implementation.  

	public void createComponentCamkesFile(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    String name = Names.getComponentCamkesName(ti);
    File interfaceFile = new File(componentDirectory, Names.getComponentCamkesFileName(ti));
    String path = interfaceFile.getAbsolutePath();
	  try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
	    writeBoilerplateDTHeader(name, path, writer, "componentCamkesPrefix", true);
      writer.append("\n");
      writer.append("import \"../../interfaces/" + Names.getComponentIdlFileName(ti) + "\";\n");
  
      ST st = templates.getInstanceOf("componentCamkesBody");
      st.add("name", name);
      
      st.add("connections", "provides " + Names.getComponentDispatcherProcName(ti) + " dispatch; ");
      for (InputDataPort idp : ti.getInputDataPortList()) {
        addReaderWriterConnection(st, idp, "provides");
      }
      
      for (OutputDataPort odp : ti.getOutputDataPortList()) {
        addReaderWriterConnection(st, odp, "uses");
      }
      
      for (SharedDataAccessor sda : ti.getSharedDataAccessors()) {
        addSharedDataConnection(st, sda, "uses");
      }
      
      writer.append(st.render());
      
      writeBoilerplateFooter(name, path, writer, "componentCamkesPostfix");
	  } catch (IOException e) {
	    log.error("Problem creating camkes component file.");
	    throw new Aadl2RtosFailure(); 
	  }
	  
	}

	
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
	
	// the dispatcher component implements all the incoming interfaces for the components that are called by the 
	// computation tree possible from the originating active thread as a proxy.  It also uses the same interfaces
	// to make the actual queued calls once the component has completed its execution.
	public void createDispatcherComponent(File componentDirectory, ThreadImplementation ti) throws Aadl2RtosFailure {
    String name = Names.getDispatcherComponentCamkesName(ti);
    File interfaceFile = new File(componentDirectory, Names.getDispatcherComponentCamkesFileName(ti));
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateDTHeader(name, path, writer, "dispatcherComponentCamkesPrefix", true);
      writer.append("\n");

      writer.append("// Passive component dispatch interfaces \n\n");
      for (ThreadImplementation pti : model.getPassiveThreadImplementations()) {
        writer.append("import \"../../interfaces/" + Names.getComponentIdlFileName(pti) + "\";\n");
      }

      writer.append("\n // Send/receive interfaces for active components \n\n");
      for (Type t : getActiveThreadSRTypes()) {
        writer.append("import \"../../interfaces/" + Names.getReaderWriterIdlFileName(t) + "\";\n");
      }
      writer.append("\n\n");
      
      ST st = templates.getInstanceOf("dispatcherComponentCamkesBody");
      
      // We need to provide active thread receivers for this thread
      for (Dispatcher d : ti.getDispatcherList()) {
        st.add("incomingDispatch", "provides " +  
            Names.getReaderWriterInterfaceName(d.getType()) + " " +  
            Names.getDispatcherComponentDispatchName(ti, d) + "; ");
        if (d instanceof InputEventDispatcher) {
          InputEventDispatcher ied = (InputEventDispatcher)d; 
          st.add("incomingDispatch", "has mutex " + 
              Names.getReaderWriterMutexName(ied.getEventPort()));
        } 
      }
      
      // We need to use active thread receivers for all other threads
      st.add("outgoingDispatch", "has mutex " + Names.getDispatcherComponentMutexName(ti) + ";");
      for (ThreadImplementation oti : model.getActiveThreadImplementations()) {
        if (oti != ti) {
          for (Dispatcher d : ti.getDispatcherList()) {
            if (d instanceof InputEventDispatcher) {
              st.add("outgoingDispatch", "uses " +  
                  Names.getReaderWriterInterfaceName(d.getType()) + " " +
                  Names.getDispatcherComponentDispatchName(oti, d) + "; ");
              // System.out.println("outgoing dispatch: " + outgoingDispatch.render()); 
            }
          }
        }
      }
      
      // We finally need to use every passive component dispatch interface
      for (ThreadImplementation pi: model.getThreadImplementations()) {
        st.add("passiveReceivers", "uses " + 
            Names.getComponentDispatcherProcName(pi) + " " +  
           Names.getComponentInstanceName(pi) + "; "); 
        // System.out.println("Passive receivers: " + passiveReceivers.render()); 
      }
      
      // for each send/receive interface, we implement the send/receive interface.
      st.add("name", name);
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
	  componentDirectory.mkdirs();
	  
	  createComponentHeader(componentDirectory, ti);
	  createComponentCFile(componentDirectory, ti);
	  createComponentCamkesFile(componentDirectory, ti);

	  if (!ti.getIsPassive()) {
	    createDispatcherComponent(componentDirectory, ti);
	    createDispatcherComponentCFile(componentDirectory, ti);
	  }
	  
    File CFile = new File(componentDirectory, Names.getComponentGlueCodeCFileName(ti));
    this.model.getSourceFiles().add(CFile.getPath());
    // Generate .c, .h files HERE!
	}
	
	// create this only if we have periodic threads.
	
	public void createPeriodicDispatcherComponent() throws Aadl2RtosFailure {
    String name = Names.getPeriodicDispatcherComponentName(model);

    File componentDirectory = new File(componentsDirectory, name);
    componentDirectory.mkdirs();
    
    File interfaceFile = new File(componentDirectory, Names.getPeriodicDispatcherCamkesFileName(model));
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      writeBoilerplateDTHeader(name, path, writer, "componentCamkesPrefix", true);
      writer.append("\n");
  
      writer.append("import \"../../interfaces/" + 
          Names.getReaderWriterIdlFileName(PeriodicDispatcher.getPeriodicDispatcherType()) + "\";\n");
      writer.append("\n\n");
      ST st = templates.getInstanceOf("periodicDispatcherCamkesBody");
      
      // For each active, periodically dispatched thread: 
  	  //   construct an interface with that thread's periodic dispatcher id.
  	  for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
  	    for (Dispatcher d : ti.getDispatcherList()) {
  	      if (d instanceof PeriodicDispatcher) {
  	        st.add("interface", "uses " +  
  	            Names.getReaderWriterInterfaceName(d.getType()) + " " +  
  	            Names.getDispatcherComponentDispatchName(ti, d) + "; ");
  	        
  	      }
  	    }
  	  }
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

    String hname = Names.getSystemTypeHeaderName(model);
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(interfacesDirectory, hname);
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


	void createAssembly() throws Aadl2RtosFailure {
    String hname = Names.getSystemAssemblyFileName(model);
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(rootDirectory, hname);
    String path = HFile.getAbsolutePath();

    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      writeBoilerplateHeader(sysInstanceName, path, hwriter, "camkesAssemblyPrefix");
      
      ST st = templates.getInstanceOf("camkesAssemblyBody");
      // write the individual components: one per thread + one dispatcher per active thread.
      for (ThreadImplementation ti: model.getThreadImplementations()) {
        createComponentInstance(st, Names.getComponentCamkesName(ti),
                                Names.getComponentInstanceName(ti));
      }
      
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        createComponentInstance(st, Names.getDispatcherComponentCamkesName(ti), 
              Names.getDispatcherComponentInstanceName(ti));
      }

      // create periodic dispatcher component.
      createComponentInstance(st, Names.getPeriodicDispatcherComponentName(model),
          Names.getPeriodicDispatcherInstanceName(model));
      
      // connect all dispatchers to all passive dispatch interfaces.
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        for (ThreadImplementation tj: model.getPassiveThreadImplementations()) {
          String from = Names.getDispatcherComponentInstanceName(ti) + "." + tj.getNormalizedName(); 
          String to = Names.getComponentInstanceName(tj) + ".dispatch"; 
          createConnection(st, from, to);
        }
      }
      
      // connect all dispatchers to all other dispatchable "active" send/receive interfaces
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        for (ThreadImplementation tj: model.getActiveThreadImplementations()) {
          if (ti != tj) {
            for (Dispatcher d : tj.getDispatcherList()) {
              if (d instanceof InputEventDispatcher) {
                String from = Names.getDispatcherComponentInstanceName(ti) + "." +
                    Names.getDispatcherComponentDispatchName(tj, d);
                String to = Names.getDispatcherComponentInstanceName(tj) + "." +
                    Names.getDispatcherComponentDispatchName(tj, d);
                createConnection(st, from, to); 
              }
            }
          }
        }
      }
      
      // Need periodic interface connections for component dispatchers.
      
      // connect smaccm_periodic_dispatcher to periodic dispatchers 
      for (ThreadImplementation ti: model.getActiveThreadImplementations()) {
        for (Dispatcher d: ti.getDispatcherList()) {
          if (d instanceof PeriodicDispatcher) {
            String from = Names.getPeriodicDispatcherInstanceName(model) + "." + 
                Names.getDispatcherComponentDispatchName(ti, d) ; 
            String to = Names.getDispatcherComponentInstanceName(ti) + "." + 
                Names.getDispatcherComponentDispatchName(ti, d);
            createConnection(st, from, to);
          }
        }
      }
      
      // connect all passive component read/write ports
      for (ThreadImplementation ti: model.getThreadImplementations()) {
        List<ThreadInstance> threadInstances = ti.getThreadInstanceList(); 
        if (threadInstances.size() != 1) {
          log.error("Translator currently only supports one thread instance per implementation.\n");
          throw new Aadl2RtosFailure();
        }
        ThreadInstance src = threadInstances.get(0);
        for (Connection c : src.getIsSrcOfConnectionList()) {
          OutputPort srcPort = c.getSourcePort();
          if (srcPort instanceof OutputDataPort) {
            ThreadInstance dst = c.getDestThreadInstance(); 
            InputPort dstPort = c.getDestPort(); 
            ThreadImplementation dstImpl = dst.getThreadImplementation(); 
            String from = Names.getComponentInstanceName(ti) + "." + 
                srcPort.getName();
            String to = Names.getComponentInstanceName(dstImpl) + "." +
                dstPort.getName();
            createConnection(st, from, to);
          }
        }
      }
      
      // connect all passive shared data interfaces (currently unsupported)
      
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