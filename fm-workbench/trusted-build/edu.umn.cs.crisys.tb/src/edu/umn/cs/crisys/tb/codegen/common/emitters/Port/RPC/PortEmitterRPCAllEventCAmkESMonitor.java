package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.BoolType;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterRPCAllEventCAmkESMonitor extends DispatchableInputPortCommon implements PortEmitterCamkes, PortEmitterRPC {

  public static boolean isApplicable(PortFeature pf) {
    // right kind of port
    boolean ok = (pf instanceof InputEventPort ||
        pf instanceof OutputEventPort);
    OSModel model = Util.getElementOSModel(pf);
    OSModel.OSTarget target = model.getOsTarget();

    // linux is not yet supported until we get our act together.
    ok &= (target == OSModel.OSTarget.CAmkES );

    return ok;
  }

  Type indexType = new IntType(32, false); 

  public PortEmitterRPCAllEventCAmkESMonitor(PortFeature pf) {
    super(pf, Util.getElementOSModel(pf)); 
  }

  @Override
  public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
    // no-op for the moment; I could add support for 
    // auto-declaration of types, but right now this is already 
    // handled.
  }

  @Override
  public void getWritePortCFiles(File directory) {
    // no-op for RPCEventDataPorts
  }

  @Override
  public void getWritePortHFiles(File directory) {
    // no-op for RPCEventDataPorts
  }

  public String getTypeName() { 
    return (new TypeNames(port.getType())).getCamkesName();
  }

  public ST getTemplateST(String stName) {
    STGroupFile template = Util.createTemplate("PortEmitterRPCAllEventCamkesMonitor.stg");
    return template.getInstanceOf(stName); 
  }

  private void writeFile(File file, String purpose, String contents, String exnPrefix) throws TbFailure{
    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(file))) { 
      ST st = getTemplateST("filePrefix");
      st.add("purpose",purpose);
      st.add("date", new Date());
      hwriter.append(st.render());
      hwriter.append(contents);
    } catch (IOException e) {
      // TODO: Get access to the log.
      System.out.println(exnPrefix + e);
      throw new TbFailure();
    }
  }
  
  public String getMonitorInterfaceNamePrefix() {
    return "Monitor_" + getTypeName() + "_" +this.getQueueSize();
  }
  
  public String getInputMonitorInterfaceName() {
    return getMonitorInterfaceNamePrefix() + "_Dequeue";
  }
  
  public String getOutputMonitorInterfaceName() {
    return getMonitorInterfaceNamePrefix() + "_Enqueue";
  }
  
  public String getMonitorInputCamkesNamePrefix() {
    if(port instanceof InputEventPort) {
      return "Monitor_" + port.getQualifiedName();
    } else {
      assert(true);
      return "unreachable";
    }
  }

  @Override
  public void getWriteCamkesPortComponents(File componentsDirectory) throws TbFailure {
    if(port instanceof InputEventPort) {
      // Create component directory for connections.
      String monitorComponentName = getMonitorInputCamkesNamePrefix();
      File componentDirectory = new File(componentsDirectory, monitorComponentName);
      componentDirectory.mkdirs();

      // Write camkes specification for this component.
      File camkesspec = new File(componentDirectory,monitorComponentName+".camkes");
      {
        ST camkestmplt = this.getTemplateST("monitorCamkesWriter");
        camkestmplt.add("str_component_name", monitorComponentName);
        camkestmplt.add("port",this);
        String err = "IOException occurred during getWriteCamkesPortComponents"
            +" while writing "+camkesspec+":";
        ST purposetmplt = this.getTemplateST("inputPortCamkesMonitorPurpose");
        purposetmplt.add("port", this);
        writeFile(camkesspec,purposetmplt.render(),camkestmplt.render(), err);
      }
      // Make source directory in the component directory.
      File componentSrcDirectory = new File(componentDirectory,"src");
      componentSrcDirectory.mkdirs();

      // Write source file for this component.
      {
        File sourcefile = new File(componentSrcDirectory,monitorComponentName+".c");
        ST ctmplt = this.getTemplateST("monitorCamkesCWriter");
        ctmplt.add("port", this);
        ctmplt.add("str_types_include",(new ModelNames(model)).getSystemTypeHeaderName());
        String err = "IOException occurred during getWriteCamkesPortComponents"
            +" while writing "+sourcefile+":";
        ST purposetmplt = this.getTemplateST("inputPortCMonitorPurpose");
        purposetmplt.add("port", this);
        writeFile(sourcefile,purposetmplt.render(),ctmplt.render(), err);
      }
    } else {
      // TODO: Is there something that should be done here? If not remove me.
    }
  }

  @Override
  // XXX: The generation of interfaces on a per-port basis is sloppy. For each
  // port a Enqueue and Dequeue monitor interface is generated uniquely named by
  // its type and queue size. This means an Enqueue/Dequeue interface file may be 
  // over-written by an identical file provided another port share the same type 
  // and queue size. Besides being sloppy, the lacking functionality to aggregate
  // all ports that will use the same interface prevents me from providing more
  // useful purpose comment information. Specifically, it would be nice if those
  // purpose comments contained a list of all components that use them.
  public void getWriteCamkesPortIdls(File interfacesDirectory) throws TbFailure {
    if(port instanceof InputEventPort) {
      List<String> suffixes = new ArrayList<String>();
      Collections.addAll(suffixes, "Enqueue","Dequeue");
      for (String suffix : suffixes) {
        String idlname = getMonitorInterfaceNamePrefix();
        String idlfilename = "";
        if(suffix == "Enqueue") {
          idlfilename = this.getOutputMonitorInterfaceName();
        } else if (suffix == "Dequeue") {
          idlfilename = this.getInputMonitorInterfaceName();
        } else {
          assert(false);
        }
        idlfilename += ".idl4";
        File idlfile = new File(interfacesDirectory, idlfilename);
        ST idl4tmplt = this.getTemplateST("camkesMonitor"+suffix+"Idl4");
        idl4tmplt.add("str_interface_name", idlname);
        idl4tmplt.add("str_type_name", getTypeName());
        idl4tmplt.add("str_types_include",(new ModelNames(model)).getSystemTypeHeaderName());
        ST purposetmplt = this.getTemplateST("inputPortIDL4MonitorPurpose");
        purposetmplt.add("port", this);
        purposetmplt.add("str_direction", suffix);
        writeFile(idlfile,purposetmplt.render(),idl4tmplt.render(), "IOException occurred during getWriteCamkesPortIdls: ");
      }
    } else {

    }
  }

  @Override
  public String getWritePortHPrototypes() {return "";}

  @Override
  public String getWritePortDeclarations() {
    if(port instanceof InputEventPort && !((InputEventPort)port).getExternalHandlerList().isEmpty()) {
      assert(port.getParent() instanceof ThreadImplementation);
      ThreadImplementationNames ti = new ThreadImplementationNames((ThreadImplementation) port.getParent());
      ST st = this.getTemplateST("monitorCamkesEventHandler");
      st.add("port", this);
      st.add("unlock", ti.getCamkesDispatcherUnlockStmt());
      return st.render();
    } else if (port instanceof OutputEventPort) {
      ST st = getTemplateST("componentRemoteWriterDecl");
      st.add("port", this);
      return st.render();
    }
    return "";
  }

  @Override
  public String getWritePortPreEntrypoint() {
    // nothing to do for "active" threads; for the moment
    // I'm not going to support "passive" threads.
    return "";
  }

  @Override
  public String getWritePortPostEntrypoint() {
    // nothing to do for "active" threads; for the moment
    // I'm not going to support "passive" threads.
    // However, this should make it easy!!
    return "";
  }

  @Override
  public String getWritePortEventResponder() {
    ST st; 
    PortFeature p = getModelElement();
    if (p instanceof InputEventPort && this.getHasDispatcher()) {
      if (p.hasData()) {
        st = getTemplateST("eventDataDispatcher");
      } else {
        st = getTemplateST("eventDispatcher");
      }
      st.add("dispatcher", EmitterFactory.port(p));
      return st.render(); 
    } else {
      return "";
    }
  }

  public String writeOptPortThreadInitializerPrototype(String v) {
    String result = ""; 
    ExternalHandler eh = this.getModelElement().getInitializeEntrypointSourceText(); 
    if (eh != null) {
      result += eh.getHandlerName() + "(" + v + ");" + System.lineSeparator();
    }
    return result;
  }

  @Override
  public String getWritePortThreadInitializer() {
    String result = writeOptPortThreadInitializerPrototype("");
    if (this.getModelElement() instanceof InputPort) {
      result = getTypeName()+" "+port.getName()+";\n"+result;
    } 
    return result;
  }

  /************************************************************
   * 
   * CAmkES-specific functions (implementing RPCDataPortCamkes)
   * 
   ************************************************************/

  private String addComponentInputPortDeclarations() {
    ST st = getTemplateST("inputPortDeclarations"); 
    st.add("port", this);
    return st.render();
  }

  private String addComponentOutputDataDeclarations() {
    ST st = getTemplateST("outputPortDeclarations"); 
    st.add("port", this);
    return st.render();
  }

  @Override
  public String getCamkesAddComponentPortLevelDeclarations() {
    PortFeature pf = this.getModelElement();
    if (pf instanceof InputPort) {
      return addComponentInputPortDeclarations();
    } else if (pf instanceof OutputPort) {
      return addComponentOutputDataDeclarations();
    } else {
      throw new TbException("RPCEventDataPortEmitter::addComponentPortLevelDeclarations: event data port emitter used with non-event data port class: " + pf.getName());
    }
  }

  public String addAssemblyConnection(PortConnection conn, OSModel model) {
    ST st = getTemplateST("connectReaderWriter"); 
    st.add("connection", EmitterFactory.portConnection(conn));
    st.add("model", EmitterFactory.model(model));
    return st.render();
  }


  @Override
  public String getCamkesAddAssemblyFileCompositionPortDeclarations() {
    PortFeature pf = this.getModelElement();
    if (pf instanceof InputPort) {
      String name = getMonitorInputCamkesNamePrefix();
      String result = "component " + name + " " + name.toLowerCase() + ";\n";
      return result;         
    } else {
      String result = "";
      return result;
    }
  }
  
  @Override
  public String getCamkesAddAssemblyFileCompositionPortConnections() {
    PortFeature pf = this.getModelElement();
    if (pf instanceof InputPort) {
      String result = "";
      result += "connection seL4RPCCall conn" + model.getGenerateConnectionNumber()
              + " (from " + getThreadImplementation().getComponentInstanceName()
              + "." + getName()
              + ", to " + getMonitorInputCamkesNamePrefix().toLowerCase()
              + ".deq);\n";
      result += "connection seL4Notification conn" + model.getGenerateConnectionNumber()
      + " (from " + getMonitorInputCamkesNamePrefix().toLowerCase()
      + ".qd, to " + getThreadImplementation().getComponentInstanceName()
      + ".q" + getName()
      + ");\n";
      return result;         
    } else {
      String result = "";
      List<PortConnection> ports = port.getConnections();
      int sz = ports.size();
      for (int i = 0; i < sz; i++) {
        PortEmitterRPCAllEventCAmkESMonitor pe = new PortEmitterRPCAllEventCAmkESMonitor(ports.get(i).getDestPort());
        String suffix = "";
        if(sz>1) {
          suffix += i;
        }
        result += "connection seL4RPCCall conn" + model.getGenerateConnectionNumber()
        + " (from " + getThreadImplementation().getComponentInstanceName()
        + "." + getName() + suffix;
        result += ", to monitor_" + pe.getQualifiedName()
        + ".enq);\n";
        
      }
      return result;
    }
  }
  
  public List<String> getInputMonitorNames() {
    return getMonitorNamesModal(false,false);
  }
  
  public List<String> getInputMonitorQualifiedNames() {
    return getMonitorNamesModal(true,false);
  }
  
  public List<String> getOutputMonitorNames() {
    return getMonitorNamesModal(false,true);
  }
  
  public List<String> getOutputMonitorQualifiedNames() {
    return getMonitorNamesModal(true,true);
  }
  
  private List<String> getMonitorNamesModal(boolean qualified,boolean source) {
    List<String> list = new LinkedList<String>();
    List<PortConnection> ports = port.getConnections();
    int sz = ports.size();
    for (int i = 0; i < sz; i++) {
      PortFeature porti = null;
      if(source) {
        porti = ports.get(i).getSourcePort();
      } else {
        porti = ports.get(i).getDestPort();
      }
      PortEmitterRPCAllEventCAmkESMonitor emitter = new PortEmitterRPCAllEventCAmkESMonitor(porti);
      String name = "";
      if(qualified) {
        name = emitter.getQualifiedName().toLowerCase();
      } else {
        name = emitter.getName().toLowerCase();
      }
      if(sz>1) {
        name += i;
      }
      list.add(name);
    }
    return list;  
  }

  @Override
  public String getCamkesAddAssemblyFileConfigDeclarations() {
    return "";
  }

  @Override
  public String getCamkesAddAssemblyFilePortDeclarations() {
    String retval = "";
    if(port instanceof InputEventPort) {
      String name = getMonitorInputCamkesNamePrefix();
      return "import \"components/"+name+"/"+name+".camkes\";\n";
    } else {
      return "";
    }
  }

  // relevant to only event data ports
  public String getQueueSize() {
    PortFeature dp = this.getModelElement();
    int size = 0;
    if (dp instanceof InputEventPort) {
      size = ((InputEventPort)dp).getQueueSize();       
    } else if (dp instanceof OutputEventPort) {
      size = 
          ((InputEventPort)((OutputEventPort)dp).getConnections().get(0).getDestPort()).getQueueSize();
    } else {
      throw new TbException("Error: getQueueSize: call on non-event port " + dp.getName() + ".");
    }
    return Integer.toString(size);
  }


  // local reader/writer name does not have to be compatible with any CAmkES stuff.
  public String getLocalReaderWriterName(String readWrite) {
    PortFeature dp = getModelElement();
    if (dp.getCommprimFnNameOpt() != null) {
      return dp.getCommprimFnNameOpt();
    } else {
      String result = Util.getPrefix_() +
          dp.getOwner().getNormalizedName() + "_" + readWrite + "_" + dp.getName();
      return result;
    }
  }   

  public String getLocalReaderName() {
    return port.getName()+"_dequeue";
  }

  public String getLocalWriterName() {
    return  getLocalReaderWriterName("write");
  }   

  public List<PortConnectionEmitter> getConnections() {
    List<PortConnectionEmitter> pcl = new ArrayList<>(); 
    for (PortConnection pc: this.getModelElement().getConnections()) {
      pcl.add(EmitterFactory.portConnection(pc));
    }
    return pcl;
  }

  @Override
  public List<String> getCamkesAddComponentPortImports() {
    List<String> list = new LinkedList<String>();
    list.add(getInputMonitorInterfaceName()+".idl4");
    list.add(getOutputMonitorInterfaceName()+".idl4");
    return list;
  }

  @Override
  public String getCamkesAddPreInitStatements() {
    if(!((InputEventPort)port).getExternalHandlerList().isEmpty()) {
      ST st = this.getTemplateST("preinitStatements");
      st.add("port", this);
      return st.render();
    } else {
      return "";
    }
  }
}

