package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.VxWorks.VxWorksUtil;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterEChronos;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterLinux;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterVxWorks;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.codegen.eChronos.EChronosUtil;
import edu.umn.cs.crisys.tb.codegen.linux.LinuxUtil;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.BoolType;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterRPCAllEvent extends DispatchableInputPortCommon implements PortEmitterCamkes, PortEmitterEChronos, PortEmitterVxWorks, PortEmitterLinux, PortEmitterRPC {

   public static boolean isApplicable(PortFeature pf) {
      // right kind of port
      boolean ok = (pf instanceof InputEventPort ||
                    pf instanceof OutputEventPort);
      
      OSModel model = Util.getElementOSModel(pf);
      OSModel.OSTarget target = model.getOsTarget();

      // linux is not yet supported until we get our act together.
      ok &= (target == OSModel.OSTarget.CAmkES || 
             target == OSModel.OSTarget.eChronos ||
             target == OSModel.OSTarget.VxWorks || 
             target == OSModel.OSTarget.linux);
      
      if (target == OSModel.OSTarget.linux) {
         // TODO: check here to see if source and target process 
         // are the same for all connections from this port.
         ok &= Util.allConnectionsInProcess(pf);
      }
      
      return ok;
   }
   
   Type indexType = new IntType(32, false); 
   
   public PortEmitterRPCAllEvent(PortFeature pf) {
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
   
   public ST getTemplateST(String stName) {
      STGroupFile template = Util.createTemplate("PortEmitterRPCAllEvent.stg");
      return template.getInstanceOf(stName); 
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
      ST st; 
      PortFeature p = getModelElement();
      if (p instanceof OutputEventPort) {
         st = getTemplateST("componentRemoteWriterDecl");   
      } else if (p instanceof InputEventPort) {
         if (this.getHasData()) {
            st = getTemplateST("InputEventDataPortDecls");
         } else {
            st = getTemplateST("InputEventPortDecls");
         }
      } else {
         throw new TbException("Error: writePortDeclarations: port " + this.getName() + " is not an event data port.");
      }
      st.add("port", this);
      return st.render();
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
      return writeOptPortThreadInitializerPrototype(""); 
   }

   /************************************************************
    * 
    * VxWorks-specific functions 
    * 
    ************************************************************/

   
   @Override
   public String getVxWorksAddCommonHFileDeclarations() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += VxWorksUtil.writeExternMutexDecl(this.getMutex());
         toReturn += getEChronosAddCommonHFileDeclarations();
      } 
      return toReturn;
   }

   @Override
   public String getVxWorksAddMainCFileIncludes() {
      return "";
   }

   @Override
   public String getVxWorksAddMainCFileDeclarations() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += VxWorksUtil.writeMutexDecl(this.getMutex());
      }
      return toReturn;
   }

   @Override
   public String getVxWorksAddMainCFileInitializers() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += this.getMutex() + " = " + VxWorksUtil.createMutex() + ";" + System.lineSeparator();
         toReturn += "assert(" + this.getMutex() + " != NULL );" + System.lineSeparator();
      }
      ExternalHandler initializer = 
            this.getModelElement().getInitializeEntrypointSourceText();
      if (initializer != null) {
         toReturn += initializer.getHandlerName() + "();"+ System.lineSeparator();
      }

      return toReturn;
   }

   @Override
   public String getVxWorksAddMainCFileDestructors() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += "semDelete(" + this.getMutex() + ");" + System.lineSeparator();
      }
      return toReturn;
   }

   /************************************************************
    * 
    * linux-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/
   @Override
   public String getLinuxAddCommonHFileDeclarations() {
      return "";
   }

   @Override
   public String getLinuxAddMainCFileIncludes() {
      return "";
   }

   @Override
   public String getLinuxAddMainCFileDeclarations() {
      return "";
   }

   @Override
   public String getLinuxAddMainCFileInitializers() {
      return "";
   }

   @Override
   public String getLinuxAddMainCFileDestructors() {
      return ""; 
   }

   @Override
   public String getLinuxAddProcessHFileDeclarations() {
      // TODO Auto-generated method stub
      String toReturn = "" + System.lineSeparator(); 
   if (this.getModelElement() instanceof InputPort) {
      toReturn += LinuxUtil.writeExternMutexDecl(this.getMutex());
      toReturn += getEChronosAddCommonHFileDeclarations();
   } 
   return toReturn + System.lineSeparator();
   }

   @Override
   public String getLinuxAddProcessCFileIncludes() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getLinuxAddProcessCFileDeclarations() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += LinuxUtil.writeMutexDecl(this.getMutex());
      }
      return toReturn;
   }

   @Override
   public String getLinuxAddProcessCFileInitializers() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += LinuxUtil.createInterprocMutex(this.getMutex());
      }
      ExternalHandler initializer = 
            this.getModelElement().getInitializeEntrypointSourceText();
      if (initializer != null) {
         toReturn += initializer.getHandlerName() + "();" + System.lineSeparator();
      }
      return toReturn;
   }

   @Override
   public String getLinuxAddProcessCFileDestructors() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += LinuxUtil.deleteMutex(this.getMutex());
      }
      return toReturn;
   }

   /************************************************************
    * 
    * eChronos-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/

   public String getEChronosAddPrxMutexes() {
      String toReturn = "";
      if (this.getModelElement() instanceof InputPort) {
         toReturn += EChronosUtil.addPrxMutex(this.getMutex());
      }
      return toReturn;
   }
   
   
   
   public String getEChronosAddPrxSemaphores() {
      return "";
   }

   @Override
   public String getEChronosAddCommonHFileDeclarations() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += EChronosUtil.eChronosPortWriterPrototype(
            getIncomingWriterName(), this.getModelElement().getType()); 
      }
      return toReturn;
   }

   @Override
   public String getEChronosAddTrampolines() { return ""; }
   
   @Override
   public String getEChronosAddInternalIrqs() { return ""; }
   
   @Override
   public String getEChronosAddExternalIrqs() { return ""; }

   
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
      // TODO Auto-generated method stub
      PortFeature pf = this.getModelElement();
      if (pf instanceof InputPort) {
         return addComponentInputPortDeclarations();
      } else if (pf instanceof OutputPort) {
         return addComponentOutputDataDeclarations();
      } else {
         throw new TbException("RPCEventDataPortEmitter::addComponentPortLevelDeclarations: event data port emitter used with non-event data port class: " + pf.getName());
      }
   }
   
   @Override
   public void getWriteCamkesPortComponents(File componentsDirectory) {
   	// TODO Auto-generated method stub
   	
   }

   @Override
   public void getWriteCamkesPortIdls(File interfacesDirectory) {
   	// TODO Auto-generated method stub
   	
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
      if (pf instanceof OutputPort) {
         String result = ""; 
         for (PortConnection conn : pf.getConnections()) {
            result += addAssemblyConnection(conn, this.model); 
         }
         return result;         
      } else {
         return "";
      }
   }
   
   @Override
   public String getCamkesAddAssemblyFileConfigDeclarations() {
      return "";
   }
   
   @Override
   public String getCamkesAddAssemblyFilePortDeclarations() { return ""; }

   /************************************************************
    * 
    * Names functions used by the .stg templates for input queues
    *
    * type
    * name
    * incomingPortWriterName <--> incomingWriterName --> OS-specific
    * portLockStmt <--> lockStmt                     --> OS-specific
    * portUnlockStmt <--> unlockStmt                 --> OS-specific
    * aadlReaderFnName <--> getLocalReaderName       
    * hasDispatcher
    * 
    * ...and for output queues
    * outgoingWriterName
    * 
    * ...and for event dispatchers
    * varDecl
    * userEntrypointCallerPrototype
    * activeThreadInternalDispatcherFnName
    * incomingUserEntrypointCallerName <--> userEntrypointCallerName --> OS-specific
    * 
    * connections
    * 
    * the entrypointCallerName is only dispatched remotely for 
    *    passive threads.  For active threads it will always be 
    *    dispatched from the main thread dispatch loop.
    * 
    * For now, activeThreadInternalDispatcherFnName is just the 
    * name of the entrypointCallerName; once (if) we add back in 
    * passive threads, we will adjust the name and emit more declarations.
    * 
    ************************************************************/

   public String getQueueFrontName() { return getPrefix() + "_queue_front_" + this.getName(); }
   public String getQueueBackName()  { return getPrefix() + "_queue_back_" + this.getName(); }
   public String getQueueDataName()  { return getPrefix() + "_queue_" + this.getName(); }
   public String getQueueFullName() { return getPrefix() + "_queue_full_" + this.getName(); }
   public String getQueueIsFullName() { return getPrefix() + "_queue_is_full_" + this.getName(); }
   public String getQueueIsEmptyName() { return getPrefix() + "_queue_is_empty_" + this.getName(); }
   public String getQueueReadName() { return getPrefix() + "_queue_read_" + this.getName(); }
   public String getQueueWriteName() { return getPrefix() + "_queue_write_" + this.getName(); }

   public String getQueueDataDecl()  { 
      if (!(this.getModelElement() instanceof InputEventPort)) {
         throw new TbException("Error: getQueueDecl: port " + this.getModelElement().getName() + " is not an input event port so has no queue.");
      }
      InputEventPort iep = (InputEventPort)this.getModelElement(); 
      return iep.getQueueType().getCType().varString(getQueueDataName());
   }

   public String getQueueFrontDecl() {
      return indexType.getCType().varString(getQueueFrontName());
   }

   public String getQueueBackDecl() {
      return indexType.getCType().varString(getQueueBackName()); 
   }

   public String getQueueFullDecl() {
      return (new BoolType()).getCType().varString(getQueueFullName()); 
   }

   public TypeNames getIndexType() {
      return EmitterFactory.type(this.indexType); 
   }

   public String getIndex() {
      return getName() + "_index";
   }

   // relevant (kinda) to event ports
   // we should never enqueue more than 32k messages!
   public String getIndexMax() {
      return "32767";
   }
   
   // relevant to only event data ports
   public String getQueueSize() {
      PortFeature dp = this.getModelElement();
      if (!(dp instanceof InputEventPort)) {
         throw new TbException("Error: getQueueSize: port " + dp.getName() + " is not an input event port so has no queue.");
      }
      InputEventPort iep = (InputEventPort)dp; 
      return Integer.toString(iep.getQueueSize());
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
      return getLocalReaderWriterName("read");
   }
   
   public String getLocalWriterName() {
      return getLocalReaderWriterName("write");
   }   

   
   public String getVarDecl() {
      return this.getModelElement().getType().getCType().varString(getName());
   }

   /*
   public String getIdlDispatcherName() {
      return "dispatch_" + getName();
   }
   */

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
    list.add(port.getType() + "_writer.idl4");
    return list;
  }

  @Override
  public String getCamkesAddPreInitStatements() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getCamkesAddAssemblyFileCompositionPortConnections() {
    // TODO Auto-generated method stub
    return "";
  }

  @Override
  public String getCamkesAddMakeFilePortDefinitions() { return ""; }

}
