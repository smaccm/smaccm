package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterRPCAllEventCAmkESMonitor extends DispatchableInputPortCommon implements PortEmitterCamkes, PortEmitterRPC {

  public String outid = "";
  public int outq = 0;
    
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
    return (new TypeNames(port.getType())).getName();
  }
  
  public String getSystemTypesInclude() {
    return (new ModelNames(model)).getSystemTypeHeaderName();
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
  
  private void writeFileNoPrefix(File file, String contents, String exnPrefix) throws TbFailure{
    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(file))) { 
      hwriter.append(contents);
    } catch (IOException e) {
      // TODO: Get access to the log.
      System.out.println(exnPrefix + e);
      throw new TbFailure();
    }
  }
  
  public String getMonitorInterfaceName() {
    return "tb_Monitor_" + getTypeName() + "_" +this.getQueueSize();
  }
  
  public String getMonitorInputCamkesNamePrefix() {
    String prefix = "";
    if(port instanceof InputEventPort) {
      prefix = getQualifiedName();
    } else if(port instanceof OutputEventPort) {
      if(outid.isEmpty()) {
        prefix = EmitterFactory.port((port.getConnections().get(0).getDestPort())).getQualifiedName();
      } else {
        prefix = EmitterFactory.port((port.getConnections().get(new Integer(outid)).getDestPort())).getQualifiedName();
      }
    } else {
      throw new TbException("PortEmitterRPCAllEventCAmkESMonitor::getMonitorInputCamkesNamePrefix: not input/output even port " + port.getName());
    }
    return prefix + "_Monitor";
  }

  @Override
  public void getWriteCamkesPortComponents(File componentsDirectory) throws TbFailure {
    if(port instanceof InputEventPort) {
      // Create component directory for connections.
      String monitorComponentName = getMonitorInputCamkesNamePrefix();
      File componentDirectory = new File(new File(componentsDirectory, "tb_Monitors"), monitorComponentName);
      componentDirectory.mkdirs();
      // Write camkes specification for this component.

      {
        File camkesspec = new File(componentDirectory,monitorComponentName+".camkes");
        ST camkestmplt = this.getTemplateST("monitorCamkesWriter");
        camkestmplt.add("str_component_name", monitorComponentName);
        camkestmplt.add("port",this);
        String err = "IOException occurred during getWriteCamkesPortComponents"
            +" while writing "+camkesspec+":";
        ST purposetmplt = this.getTemplateST("inputPortCamkesMonitorPurpose");
        purposetmplt.add("port", this);
        writeFile(camkesspec,purposetmplt.render(),camkestmplt.render(), err);
      }
      // Write source file for this component.
      {
        // Make source directory in the component directory.
        File componentSrcDirectory = new File(componentDirectory,"src");
        componentSrcDirectory.mkdirs();

        File sourcefile = new File(componentSrcDirectory,monitorComponentName+".c");
        ST ctmplt = this.getTemplateST("monitorCamkesCWriter");
        ctmplt.add("port", this);
        String err = "IOException occurred during getWriteCamkesPortComponents"
            +" while writing "+sourcefile+":";
        ST purposetmplt = this.getTemplateST("inputPortCMonitorPurpose");
        purposetmplt.add("port", this);
        writeFile(sourcefile,purposetmplt.render(),ctmplt.render(), err);
      }
      

      // Write header file for this component.
      {
        // Make source directory in the component directory.
        File componentSrcDirectory = new File(componentDirectory,"include");
        componentSrcDirectory.mkdirs();

        File sourcefile = new File(componentSrcDirectory,monitorComponentName+".h");
        ST ctmplt = this.getTemplateST("monitorCamkesHWriter");
        String mguard = ("__"+monitorComponentName+"_h__").toUpperCase();
        ctmplt.add("mguard", mguard);
        ctmplt.add("port", this);
        String err = "IOException occurred during getWriteCamkesPortComponents"
            +" while writing "+sourcefile+":";
        String purpose = "Implementation file corresponding to monitor " + monitorComponentName + ".";
        writeFile(sourcefile,purpose,ctmplt.render(), err);
      }

      // Write theory file for this component.
      {
        File sourcefile = new File(componentDirectory,monitorComponentName+".thy");
        ST ctmplt;
        if(getType().getIsUnit()){
          ctmplt = this.getTemplateST("writeEventTheoryFile");
        } else {
          ctmplt = this.getTemplateST("writeEventDataTheoryFile");
        }
        ctmplt.add("port", this);
        String err = "IOException occurred during getWriteCamkesPortComponents"
            +" while writing "+sourcefile+":";
        writeFileNoPrefix(sourcefile,ctmplt.render(), err);
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
      String idlname = getMonitorInterfaceName();
      String idlfilename = idlname + ".idl4";
      File idlfile = new File(interfacesDirectory, idlfilename);
      ST idl4tmplt = this.getTemplateST("camkesMonitorIdl4");
      idl4tmplt.add("str_interface_name", idlname);
      idl4tmplt.add("port",this);
      ST purposetmplt = this.getTemplateST("portIDL4MonitorPurpose");
      purposetmplt.add("port", this);
      writeFile(idlfile,purposetmplt.render(),idl4tmplt.render(), "IOException occurred during getWriteCamkesPortIdls: ");
    } else {

    }
  }

  @Override
  public String getWritePortHPrototypes() {
    String result = ""; 
    ST st; 
    PortFeature p = getModelElement();
    if (p instanceof OutputEventPort) {
       st = getTemplateST("writePortWriterPrototype");         
       st.add("port", this);
       result += st.render();
    } else if (p instanceof InputEventPort) {
       st = getTemplateST("writePortReaderPrototype");         
       st.add("port", this);
       result += st.render();
       
       st = getTemplateST("writeUdePrototype");
       st.add("dispatcher", this);
       result += st.render();
    } else {
       throw new TbException("Error: writePortHPrototypes: port " + this.getName() + " is not an event data port.");
    }
    
    result += writeOptPortThreadInitializerPrototype("void"); 
    return result;  
  }

  @Override
  public String getWritePortDeclarations() {
    if(port instanceof InputEventPort && !((InputEventPort)port).getExternalHandlerList().isEmpty()) {
      assert(port.getParent() instanceof ThreadImplementation);
      ThreadImplementationNames ti = new ThreadImplementationNames((ThreadImplementation) port.getParent());
      ST st = this.getTemplateST("monitorCamkesEventHandler");
      st.add("port", this);
      st.add("unlock", ti.getCamkesDispatcherUnlockStmt());
      return st.render();
    } else if(port instanceof InputEventPort && port.getCommprimFnNameOpt() != null) {
        assert(port.getParent() instanceof ThreadImplementation);
        ST st = this.getTemplateST("generateLocalReaderWrapper");
        st.add("port", this);
        return st.render();
    } else if (port instanceof OutputEventPort) {
      ST st = getTemplateST("componentRemoteWriterDecl");
      st.add("port", this);
      return st.render();
    }
    return "";
  }
  
  public String getCommprimFnNameOpt() {
    return port.getCommprimFnNameOpt();
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
    if (this.getModelElement() instanceof InputPort && !port.getType().getIsUnit() && getHasDispatcher()) {
      result = getTypeName()+" "+getName()+";\n"+result;
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
    if (port instanceof InputPort) {
      return addComponentInputPortDeclarations();
    } else if (port instanceof OutputPort) {
      return addComponentOutputDataDeclarations();
    } else {
      throw new TbException("RPCEventDataPortEmitter::addComponentPortLevelDeclarations: event data port emitter used with non-event data port class: " + port.getName());
    }
  }

  @Override
  public String getCamkesAddAssemblyFileCompositionPortDeclarations() {
    PortFeature pf = this.getModelElement();
    if (pf instanceof InputPort) {
      String name = getMonitorInputCamkesNamePrefix();
      String result = "component " + name + " " + getMonitorAssemblyInstance() + ";\n";
      return result;         
    } else {
      String result = "";
      return result;
    }
  }
  
  @Override
  public String getCamkesAddAssemblyFileCompositionPortConnections() {
    if (port instanceof InputPort) {
      String result = "";
      result += "connection seL4RPCCall conn" + model.getGenerateConnectionNumber()
              + " (from " + getThreadImplementation().getComponentInstanceName()
              + "." + getName()
              + ", to " + getMonitorAssemblyInstance()
              + ".mon);\n";
      result += "connection seL4Notification conn" + model.getGenerateConnectionNumber()
      + " (from " + getMonitorAssemblyInstance()
      + ".monsig, to " + getThreadImplementation().getComponentInstanceName()
      + "." + getNotificationName()
      + ");\n";
      return result;         
    } else {
      String result = "";
      for (PortEmitterRPCAllEventCAmkESMonitor p : getOutputPorts()) {
        result += "connection seL4RPCCall conn" + model.getGenerateConnectionNumber()
        + " (from " + getThreadImplementation().getComponentInstanceName()
        + "." + p.getName();
        result += ", to " + p.getMonitorAssemblyInstance()
        + ".mon);\n";
        
      }
      return result;
    }
  }
  public List<PortEmitterRPCAllEventCAmkESMonitor> getOutputPorts() {
    List<PortEmitterRPCAllEventCAmkESMonitor> list = new LinkedList<PortEmitterRPCAllEventCAmkESMonitor>();
    List<PortConnection> ports = port.getConnections();
    for (int i = 0; i < ports.size(); i++) {
      PortFeature porti = ports.get(i).getSourcePort();
      PortEmitterRPCAllEventCAmkESMonitor emitter = new PortEmitterRPCAllEventCAmkESMonitor(porti);
      emitter.outid = (new Integer(i)).toString();
      emitter.outq = ((InputEventPort)ports.get(i).getDestPort()).getQueueSize();
      list.add(emitter);
    }
    return list;  
  }

  private String getBadgeNamePrefix() {
    return "TB_MONITOR";
  }
  
  public String getReadBadgeName() {
    return getBadgeNamePrefix() + "_READ_ACCESS";
  }
  
  public String getWriteBadgeName() {
    return getBadgeNamePrefix() + "_WRITE_ACCESS";
  }
  
  public int getReadBadgeID() {
    return 111;
  }

  public int getWriteBadgeID() {
    return 222;
  }

  @Override
  public String getCamkesAddAssemblyFileConfigDeclarations() {
    if (port instanceof InputPort) {
      return getMonitorAssemblyInstance()+".priority = 230;\n"
             + getThreadImplementation().getComponentInstanceName() +"." + getName() + "_attributes = " + getReadBadgeName() + ";\n";
    } else {
      String result = "";
      for (PortEmitterRPCAllEventCAmkESMonitor p : getOutputPorts()) {
        result += getThreadImplementation().getComponentInstanceName()
        + "." + p.getName() + "_attributes = " + p.getWriteBadgeName() + ";\n";
      }
      return result;
    }
    
  }
  
  public String getMonitorAssemblyInstance() {
    return this.getMonitorInputCamkesNamePrefix().toLowerCase();
  }

  @Override
  public String getCamkesAddAssemblyFilePortDeclarations() {
    if(port instanceof InputEventPort) {
      String name = getMonitorInputCamkesNamePrefix();
      return "import \"components/tb_Monitors/"+name+"/"+name+".camkes\";\n";
    } else {
      return "";
    }
  }

  // relevant to only event data ports
  public String getQueueSize() {
    int size = 0;
    if (port instanceof InputEventPort) {
      size = ((InputEventPort) port).getQueueSize();
    } else if (port instanceof OutputEventPort) {
      if(outq < 1) {
        throw new TbException("Error: getQueueSize: uninitialized outq on port " + port.getName() + ".");
      } else {
        size = outq;
      }
    } else {
      throw new TbException("Error: getQueueSize: call on non-event port " + port.getName() + ".");
    }
    return Integer.toString(size);
  }

  // local reader/writer name does not have to be compatible with any CAmkES stuff.
  public String getLocalReaderWriterName(String readWrite) {
    PortFeature dp = getModelElement();
    String result = Util.getPrefix_() +
        dp.getOwner().getNormalizedName() + "_" + readWrite + "_" + dp.getName();
    return result;
  }   

  public String getLocalReaderName() {
    return this.getName()+"_dequeue";
  }

  public String getLocalWriterName() {
    return this.getName()+"_enqueue";
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
    for( PortEmitterRPCAllEventCAmkESMonitor port : getOutputPorts()) {
      list.add(port.getMonitorInterfaceName()+".idl4");
    }
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
  
  @Override
  public String getName() {
    return Util.getPrefix() + "_" + super.getName() + outid/* + "_" + getTypeName() */;
  }
  
  @Override
  public String getQualifiedName() {
    return Util.getPrefix() + "_" + super.getQualifiedName() + outid/* + "_" + getTypeName() */;
  }
  
  public String getNotificationName() {
    return Util.getPrefix() + "_" + super.getName() + "_notification";
  }

  @Override
  public String getCamkesAddMakeFilePortDefinitions() {
    if (port instanceof InputEventPort) {
      ST st = this.getTemplateST("writeMonitorMakeTargets");
      st.add("name",getMonitorInputCamkesNamePrefix());
      return st.render();
    } else {
      return "";
    }
  }
  
  
}

