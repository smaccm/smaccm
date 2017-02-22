package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Special;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterEChronos;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterLinux;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterVxWorks;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.DispatchableInputPortCommon;
import edu.umn.cs.crisys.tb.codegen.eChronos.EChronosUtil;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.OSModel.OSTarget;
import edu.umn.cs.crisys.tb.model.port.InputIrqPort;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterInputPeriodic extends DispatchableInputPortCommon implements PortEmitterCamkes, PortEmitterEChronos, PortEmitterVxWorks, PortEmitterLinux {

   public static boolean isApplicable(PortFeature pf) {
      // right kind of port
      boolean ok = (pf instanceof InputPeriodicPort);
      return ok;
   }
   
   Type indexType = new IntType(32, false); 
   STGroupFile template; 
   
   public PortEmitterInputPeriodic(PortFeature pf) {
      super(pf, Util.getElementOSModel(pf));
      template = Util.createTemplate("PortEmitterInputPeriodic.stg");
   }
   
   @Override
   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
      // no-op for the moment; 
   }

   @Override
   public PortFeature getModelElement() {
      return this.port;
   }

   
   public ST getTemplateST(String stName) {
      return template.getInstanceOf(stName); 
   }

   /*************************************************
    * 
    * C code related declarations
    * 
    *************************************************/
   
   @Override
   public void getWritePortCFiles(File directory) {
      // no-op for InputPeriodicPorts
   }

   @Override
   public void getWritePortHFiles(File directory) {
      // no-op for InputPeriodicPorts
   }
   
   @Override
   public String getWritePortHPrototypes() {
      ST st = getTemplateST("writeUdePrototype");
      st.add("dispatcher", this);
      return st.render(); 
   }


   @Override
   public String getWritePortDeclarations() {
      String result = ""; 
      ST st = getTemplateST("dispatcherComponentPeriodicEventDecls");
      st.add("dispatcher", this);
      result += st.render(); 

      // For VxWorks, we define the OS trigger in-situ.
      if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         st = getTemplateST("vxWorksDispatcherComponentPeriodicEventDecls");
         st.add("dispatcher", this);
         result += st.render(); 
      } else if (model.getOsTarget() == OSTarget.linux) {
         st = getTemplateST("linuxDispatcherComponentPeriodicEventDecls");
         st.add("dispatcher", this);
         result += st.render(); 
      } else if (model.getOsTarget() == OSTarget.CAmkES) {
         st = getTemplateST("camkesDispatcherComponentPeriodicEventDecls");
         st.add("dispatcher", this);
         result += st.render();
      }
      
      return result; 
   }

   @Override
   public String getWritePortPreEntrypoint() {
      // no-op for InputPeriodicPorts
      return "";
   }

   @Override
   public String getWritePortPostEntrypoint() {
      // no-op for InputPeriodicPorts
      return "";
   }

   @Override
   public String getWritePortEventResponder() {
      ST st = getTemplateST("periodicDispatcher");
      st.add("dispatcher", this);
      return st.render(); 
   }

   @Override
   public String getWritePortThreadInitializer() {
      String result = ""; 
      // For VxWorks, we define the OS trigger in-situ.
      if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         ST st = getTemplateST("createWatchdog");
         st.add("dispatcher", this);
         result += st.render(); 
      } else if (model.getOsTarget() == OSModel.OSTarget.linux) {
         ST st = getTemplateST("dispatcherComponentInit");
         st.add("dispatcher", this);
         result += st.render();
      } else if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         ST st = getTemplateST("camkesComponentCInitializer");
         st.add("port", this);
         result += st.render();
      }
      return result;
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
    * VxWorks-specific functions 
    * 
    ************************************************************/

   
   @Override
   public String getVxWorksAddCommonHFileDeclarations() {
      // no-op for InputPeriodicPorts - no need for mutex
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
      // Need to add support for IRQs for VxWorks
      return ""; 
   }

   @Override
   public String getVxWorksAddMainCFileDestructors() {
      // Need to add support for IRQs for VxWorks
      return "";
   }

   /************************************************************
    * 
    * eChronos-specific functions 
    * 
    ************************************************************/

   public String getEChronosAddPrxMutexes() {
      return EChronosUtil.addPrxMutex(this.getMutex());
   }
   
   public String getEChronosAddPrxSemaphores() {
      return "";
   }

   @Override
   public String getEChronosAddCommonHFileDeclarations() {
      return EChronosUtil.eChronosPortWriterPrototype(
            getIncomingWriterName(), this.getModelElement().getType()); 
   }

   @Override
   public String getEChronosAddTrampolines() { return ""; }
   
   @Override
   public String getEChronosAddInternalIrqs() { return ""; }
   
   @Override
   public String getEChronosAddExternalIrqs() { return ""; }
   
   /************************************************************
    * 
    * CAmkES-specific functions 
    * 
    ************************************************************/

   @Override
   public String getCamkesAddComponentPortLevelDeclarations() {
      ST st = getTemplateST("componentPortLevelDeclarations");
      st.add("port", this);
      return st.render();
   }

   @Override
   public String getCamkesAddAssemblyFilePortDeclarations() {
      return ""; 
   }
   
   @Override
   public String getCamkesAddAssemblyFileCompositionPortDeclarations() {
      String result = ""; 
      ST st = getTemplateST("camkesAssemblyPeriodicDispatcherConnections");
      st.add("port", this);
      result += st.render();
      
      return result;
   }
   
   @Override
   public String getCamkesAddAssemblyFileConfigDeclarations() {
      // no-op for Input Periodic Ports for Camkes
      // sender_inst.periodic_dispatcher
      String result = ""; 
      return result;
   }


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
    * Names functions used by the .stg templates for periodic dispatchers
    *
    * type
    * name
    * threadImplementation
    * externalHandlers
    * dispatchOccurredVar
    * periodicTimeVar
    * periodicDispatcherPeriod
    * hasData
    * incomingActiveThreadDispatchName <--> incomingWriterName <-- OS-specific.
    *
    * 
    * the entrypointCallerName is only dispatched remotely for 
    *    passive threads.  For active threads it will always be 
    *    dispatched from the main thread dispatch loop.
    * 
    * 
    ************************************************************/

   public String getDispatchOccurredVar() {
      return getPrefix() + "_occurred_" + getName(); 
   }

   public String getPeriodicTimeVar() {
      return getPrefix() + "_time_" + getName(); 
   }

   public int getPeriodicDispatcherPeriod() {
      InputPeriodicPort ipp = 
            (InputPeriodicPort)this.getModelElement();
      return ipp.getPeriod();
   }
   
      
   public String getIdlDispatcherName() {
      return "dispatch_" + getName();
   }

   public String getFirstLevelInterruptHandler() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement();
      return iip.getFirstLevelInterruptHandler(); 
   }
   
   public String getSignalName() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement(); 
      return iip.getSignalName(); 
   }

   public String getSignalNumber() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement(); 
      return Integer.toString(iip.getNumber()); 
   }

   public List<NameEmitter> getMemoryRegions() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement(); 
      List<NameEmitter> regions = new ArrayList<>();
      for (Map.Entry<String, String> entry : iip.getMemoryRegions().entrySet()) {
         NameEmitter region = EmitterFactory.memoryRegion(this.port, entry.getKey(), entry.getValue());
         regions.add(region);
      }
      return regions; 
   }

   public String getIrqObject() {
      return getName() + "_obj";
   }

   public String getIrqComponent() {
      return getName() + "_hw"; 
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
  List<String> list = new LinkedList<String>();
  return list;
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
