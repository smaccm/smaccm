package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Special;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterEChronos;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterLinux;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterVxWorks;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.DispatchableInputPortCommon;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InitializerPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterInitializer extends DispatchableInputPortCommon implements PortEmitterCamkes, PortEmitterEChronos, PortEmitterVxWorks, PortEmitterLinux {

   public static boolean isApplicable(PortFeature pf) {
      // right kind of port
      boolean ok = (pf instanceof InitializerPort);
      return ok;
   }
   
   public PortEmitterInitializer(PortFeature pf) {
      super(pf, Util.getElementOSModel(pf));
   }
   
   @Override
   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
      // no-op for the moment; I could add support for 
      // auto-declaration of types, but right now this is already 
      // handled.
   }

   @Override
   public PortFeature getModelElement() {
      return this.port;
   }

   @Override
   public void getWritePortCFiles(File directory) {
      // no-op for InitializerPorts
   }

   @Override
   public void getWritePortHFiles(File directory) {
      // no-op for InitializerPorts
   }

   public boolean getHasDummyArg() { return !(port.getType() instanceof UnitType); }
   
   public String writeExternalHandlerPrototype(ExternalHandler hdlr) {
      String result = ""; 
      String arg = (getHasDummyArg()) ? "const int64_t *arg" : "void";
      result += "void " + hdlr.getHandlerName() + "(" + arg + ");" + System.lineSeparator();
      return result;
   }
   
   
   @Override
   public String getWritePortHPrototypes() {
      String result = ""; 
      InitializerPort p = (InitializerPort)this.getModelElement(); 
      for (ExternalHandler hdlr : p.getExternalHandlerList()) {
         result += this.writeExternalHandlerPrototype(hdlr);
      }
      return result;
   }


   @Override
   public String getWritePortDeclarations() {
      // no-op for InitializerPorts
      return ""; 
   }

   @Override
   public String getWritePortPreEntrypoint() {
      // no-op for InitializerPorts
      return "";
   }

   @Override
   public String getWritePortPostEntrypoint() {
      // no-op for InitializerPorts
      return "";
   }

   @Override
   public String getWritePortEventResponder() {
      // no-op for InitializerPorts
      return "";
   }

   @Override
   public String getWritePortThreadInitializer() {
      // no-op for InitializerPorts
      return "";
   }

   @Override
   public String getName() {
      return this.getModelElement().getName();
   }

   @Override
   public String getQualifiedName() {
      return this.getModelElement().getQualifiedName();
   }

   
   /************************************************************
    * 
    * VxWorks-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/

   
   @Override
   public String getVxWorksAddCommonHFileDeclarations() {
      // no-op for InitializerPorts
      return "";
   }
   
   @Override
   public String getVxWorksAddMainCFileIncludes() {
      // no-op for InitializerPorts
      return "";
   }

   @Override
   public String getVxWorksAddMainCFileDeclarations() {
      // no-op for InitializerPorts
      return "";
   }

 
   @Override
   public String getVxWorksAddMainCFileInitializers() {
      return ""; 
   }

   @Override
   public String getVxWorksAddMainCFileDestructors() {
      return "";
   }

   /************************************************************
    * 
    * eChronos-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/

   public String getEChronosAddPrxMutexes() {
      return "";
   }
   
   public String getEChronosAddPrxSemaphores() {
      return "";
   }

   @Override
   public String getEChronosAddCommonHFileDeclarations() {
      return "";
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

   @Override
   public String getCamkesAddComponentPortLevelDeclarations() {
      return ""; 
   }

   @Override
   public String getCamkesAddAssemblyFileCompositionPortDeclarations() {
      return ""; 
   }
   
   @Override
   public String getCamkesAddAssemblyFileConfigDeclarations() {
      return "";
   }
   
   @Override
   public String getCamkesAddAssemblyFilePortDeclarations() { return ""; }

   /************************************************************
    * 
    * linux-specific functions 
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
      return "";
   }

   @Override
   public String getLinuxAddProcessCFileIncludes() {
      return "";
   }

   @Override
   public String getLinuxAddProcessCFileDeclarations() {
      return "";
   }

   @Override
   public String getLinuxAddProcessCFileInitializers() {
      return "";
   }

   @Override
   public String getLinuxAddProcessCFileDestructors() {
      return "";
   }

   /************************************************************
    * 
    * Names functions used by the .stg templates for input queues
    *
    * type
    * name
    * incomingPortWriterName <--> incomingWriterName --> OS-specific
    * incomingUserEntrypointCallerName <--> entrypointCallerName --> OS-specific
    * activeThreadInternalDispatcherFnName
    * 
    * the entrypointCallerName is only dispatched remotely for 
    *    passive threads.  For active threads it will always be 
    *    dispatched from the main thread dispatch loop.
    * 
    * 
    ************************************************************/
   
   public String getIdlDispatcherName() {
      return "dispatch_" + getName();
   }

@Override
public void getWriteCamkesPortComponents(File componentsDirectory) {
	// TODO Auto-generated method stub
	
}

@Override
public void getWriteCamkesPortIdls(File interfacesDirectory) {
	// TODO Auto-generated method stub
	
}

@Override
public List<String> getCamkesAddComponentPortImports() {
  // TODO Auto-generated method stub
  return (new LinkedList<String>());
}

@Override
public String getCamkesAddPreInitStatements() {
  // TODO Auto-generated method stub
  return "";
}

@Override
public String getCamkesAddAssemblyFileCompositionPortConnections() {
  // TODO Auto-generated method stub
  return "";
}

@Override
public String getCamkesAddMakeFilePortDefinitions() { return ""; }

}
