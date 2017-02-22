package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Notification;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterNotification implements PortEmitterCamkes {

   public static boolean isApplicable(PortFeature pf) {
      // right kind of port
      if (!(pf instanceof InputEventPort ||
            pf instanceof OutputEventPort)) return false;
      
      OSModel model = Util.getElementOSModel(pf);
      OSModel.OSTarget target = model.getOsTarget();

      // currently Camkes event ports only w/no fan-in or fan-out.
      if (target != OSModel.OSTarget.CAmkES || 
          !(pf.getType() instanceof UnitType)) return false;

      // source port has to have queue size 1.
      // Also, we allow fan-out but not fan-in; the problem with 
      // fan in is that the output port may write to multiple 
      // ports that might not all be notifier ports.
      // 
      // Alternately, we could require a "Notifier" flag on the port;
      // Then it would be sufficient to check consistency between 
      // notifier flags in source / destination ports.
      // 
      if (pf instanceof InputEventPort && 
          (pf.getConnections().size() != 1 || 
           (((InputEventPort)pf).getQueueSize() != 1))) {
         return false;
      } else if (pf instanceof OutputEventPort) {
         for (PortConnection conn: pf.getConnections()) {
            PortFeature input = conn.getDestPort();
            if (((InputEventPort)input).getQueueSize() != 1) {
               return false;
            }
         }
      }
      return true;
   }
   
   protected PortFeature port;
   protected OSModel model;

   public PortEmitterNotification(PortFeature pf) {
      port = pf; 
      model = Util.getElementOSModel(pf); 
   }
   
   @Override
   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
      // No-op
   }

   @Override
   public void getWritePortCFiles(File directory) {
      // no-op
   }

   @Override
   public void getWritePortHFiles(File directory) {
      // no-op
   }
   
   public ST getTemplateST(String stName) {
      STGroupFile template = Util.createTemplate("PortEmitterNotification.stg");
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
         st = getTemplateST("InputEventPortDecls");
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
   

   public final boolean getHasDispatcher() {
      if (this.getModelElement() instanceof DispatchableInputPort) {
         return  ((DispatchableInputPort)this.getModelElement()).getExternalHandlerList().size() != 0;
      }
      else return false;
   }

   public final ThreadImplementationNames getThreadImplementation() {
      return EmitterFactory.threadImplementation(this.port.getOwner());
   }

   @Override
   public String getWritePortEventResponder() {
      ST st; 
      PortFeature p = getModelElement();
      if (p instanceof InputEventPort && this.getHasDispatcher()) {
         st = getTemplateST("eventDispatcher");
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
      String result = ""; 
      if (this.port instanceof InputEventPort && this.getHasDispatcher()) {
         ST st = getTemplateST("portInitializer");
         st.add("port", this);
         result += st.render();
      }
      result += writeOptPortThreadInitializerPrototype(""); 
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
      String result = ""; 
      PortFeature pf = this.port;
      if (pf instanceof InputPort) {
         ST st = getTemplateST("assemblyConfig");
         st.add("port", this);
         result += st.render();
      }
      return result;
   }
   
   public String getMonitorInputCamkesNamePrefix() { return null; }
   
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
   public final String getUserEntrypointCallerPrototype() {
      String result = ""; 
      result += "void " + getUserEntrypointCallerName() + "(void)";
      return result;
   }

   public final String getIncomingWriterName() {
      // O.k., here we need to know the port name and 
      // the camkes extension it uses for the handler.
      return getName() + "_callback"; 
   }

   public final String getPrefix() { return Util.getPrefix(); }

   public final String getUserEntrypointCallerName() {
      return this.getPrefix() + "_entrypoint_" + 
            this.getQualifiedName();
   }

   public final String getActiveThreadInternalDispatcherFnName() {
      return getUserEntrypointCallerName(); 
   }

   public final List<NameEmitter> getExternalHandlers() {
      DispatchableInputPort dip = ((DispatchableInputPort) this.getModelElement());
      List<NameEmitter> ehl = new ArrayList<>(); 
      for (ExternalHandler eh : dip.getExternalHandlerList()) {
         ehl.add(EmitterFactory.externalHandler(eh)); 
      }
      return ehl;
   }

   public boolean getHasData() { return false; }
   
   public String getIndex() {
      return getName() + "_index";
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
   public PortFeature getModelElement() {
      return this.port;
   }
   
   public ModelNames getModel() {
      return EmitterFactory.model(model);
   }

   public String getName() {
      return this.getModelElement().getName();
   }

   public String getQualifiedName() {
      return this.getModelElement().getQualifiedName();
   }

   public final TypeNames getType() { 
      return EmitterFactory.type(this.getModelElement().getType()); 
   }

   @Override
   public void getWriteCamkesPortComponents(File componentsDirectory) throws TbFailure {
      // no-op
   }

   @Override
   public void getWriteCamkesPortIdls(File interfacesDirectory) throws TbFailure {
      // no-op
   }

   @Override
   public String getCamkesAddPreInitStatements() {
      return ""; 
   }

   @Override
   public String getCamkesAddAssemblyFileCompositionPortConnections() {
      return ""; 
   }

   @Override
   public List<String> getCamkesAddComponentPortImports() {
      return new ArrayList<String>();
   }

  @Override
  public String getCamkesAddMakeFilePortDefinitions() { return ""; }

}
