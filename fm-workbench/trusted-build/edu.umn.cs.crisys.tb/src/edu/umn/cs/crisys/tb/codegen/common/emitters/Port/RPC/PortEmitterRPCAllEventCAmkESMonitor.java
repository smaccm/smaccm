package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
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
    return port.getType().toString();
  }

  public ST getTemplateST(String stName) {
    STGroupFile template = Util.createTemplate("PortEmitterRPCAllEventCamkesMonitor.stg");
    return template.getInstanceOf(stName); 
  }

  private void writeToFile(File file, String str, String exnPrefix) throws TbFailure{
    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(file))) { 
      hwriter.append(str);
    } catch (IOException e) {
      // TODO: Get access to the log.
      System.out.println(exnPrefix + e);
      throw new TbFailure();
    }
  }

  @Override
  public void getWriteCamkesPortComponents(File componentsDirectory) throws TbFailure {
    if(port instanceof InputEventPort) {
      for ( PortConnection cnxshn : port.getConnections()) {
        // Create component directory for connections.
        String monitorComponentName = "Monitor_"+cnxshn.getName();
        File componentDirectory = new File(componentsDirectory, monitorComponentName);
        componentDirectory.mkdirs();

        // Write camkes specification for this component.
        File camkesspec = new File(componentDirectory,monitorComponentName+".camkes");
        ST st = this.getTemplateST("monitorCamkesWriter");
        st.add("cname", monitorComponentName);
        st.add("iname", "Monitor_"+port.getType());
        String err = "IOException occurred during getWriteCamkesPortComponents"
          +" while writing "+camkesspec+":";
        writeToFile(camkesspec,st.render(),err);

        // Make source directory in the component directory.
        File componentSrcDirectory = new File(componentDirectory,"src");
        componentSrcDirectory.mkdirs();

        // Write source file for this component.
        File sourcefile = new File(componentSrcDirectory,monitorComponentName+".c");
        st = this.getTemplateST("monitorCamkesCWriter");
        st.add("tname", port.getType());
        // TODO: This should be pulled in from some parameter in the AADL model.
        st.add("qsz", "1");
        err = "IOException occurred during getWriteCamkesPortComponents"
          +" while writing "+sourcefile+":";
        writeToFile(sourcefile,st.render(),err);
      }
    } else {
      // TODO: Is there something that should be done here? If not remove me.
    }
  }

  @Override
  public void getWriteCamkesPortIdls(File interfacesDirectory) throws TbFailure {
    if(port instanceof InputEventPort) {
      String idlname = "Monitor_" + port.getType();
      String idlfilename = idlname + ".idl4";
      File idlfile = new File(interfacesDirectory, idlfilename);
      ST st = this.getTemplateST("monitorCamkesIdl4Writer");
      st.add("iname", idlname);
      st.add("tname", port.getType());
      writeToFile(idlfile,st.render(),"IOException occurred during getWriteCamkesPortIdls: ");
    } else {
      
    }
   }

   @Override
   public String getWritePortHPrototypes() {return "";}

  @Override
  public String getWritePortDeclarations() {
    if(port instanceof InputEventPort) {
      assert(port.getParent() instanceof ThreadImplementation);
      ThreadImplementationNames ti = new ThreadImplementationNames((ThreadImplementation) port.getParent());
      ST st = this.getTemplateST("monitorCamkesEventHandler");
      st.add("port", port);
      st.add("unlock", ti.getCamkesDispatcherUnlockStmt());
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
      result = port.getType()+" "+port.getName()+";\n"+result;
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
   public String getCamkesAddAssemblyFilePortDeclarations() {
     String retval = "";
     if(port instanceof InputEventPort) {
       for (PortConnection cnxshn: port.getConnections()) {
         String name = "Monitor_"+cnxshn.getName();
         retval += "import \"components/"+name+"/"+name+".camkes\";\n";
       }
     }
     return retval;
    }

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
   public String getQueueReadName() { return this.getName()+"_broken"; }
   public String getQueueWriteName() { return this.getName()+"_broken"; }

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
      return port.getName()+"_mon_dequeue";
   }
   
   public String getLocalWriterName() {
      return  port.getName()+"_mon_enqueue";
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
  public String getCamkesAddComponentPortImports() {
    return "Monitor_" + port.getType() + ".idl4";
  }

}
