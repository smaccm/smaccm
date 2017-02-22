package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Special;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterEChronos;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterVxWorks;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.DispatchableInputPortCommon;
import edu.umn.cs.crisys.tb.codegen.common.names.MemoryRegionNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.port.InputIrqPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.ArrayType;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.RecordType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterInputIrq extends DispatchableInputPortCommon implements PortEmitterCamkes, PortEmitterEChronos, PortEmitterVxWorks {

   public static boolean isApplicable(PortFeature pf) {
      // right kind of port
      boolean ok = (pf instanceof InputIrqPort);
      return ok;
   }
   
   Type indexType = new IntType(32, false); 
   STGroupFile template; 
   
   public PortEmitterInputIrq(PortFeature pf) {
      super(pf, Util.getElementOSModel(pf));
      template = Util.createTemplate("PortEmitterInputIrq.stg");
   }
   

   
   @Override
   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
      for (MemoryRegionNames memRegion: this.getMemoryRegions()) {
         RecordType sizeRecordType = new RecordType();
         ArrayType byteArrayType = new ArrayType(new IntType(8, false), memRegion.getSize());
         sizeRecordType.addField("memRegion", byteArrayType);
         model.getAstTypes().put(
               memRegion.getRegionTypeName(), sizeRecordType);
      }
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
      // no-op for InputIrqPorts
   }

   @Override
   public void getWritePortHFiles(File directory) {
      // no-op for InputIrqPorts
   }
   
   @Override
   public String getWritePortHPrototypes() {
      ST st = getTemplateST("writeUdePrototype");
      st.add("dispatcher", this);
      return st.render(); 
   }


   @Override
   public String getWritePortDeclarations() {
      // These are OS-dependent.
      String result = ""; 
      ST st = getTemplateST("dispatcherComponentIRQEventDecls");
      st.add("dispatcher", this);
      result += st.render(); 
      if (this.model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         st = getTemplateST("camkesIrqDispatcher");
      } else if (this.model.getOsTarget() == OSModel.OSTarget.eChronos || 
            this.model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         st = getTemplateST("eChronosVxWorksIrqDispatcher");
      } else {
         throw new TbException("InputIrqPortEmitter::writePortDeclarations: OS " + model.getOsTarget() + " currently unsupported.");
      }
      st.add("dispatcher", this);
      result += st.render();
      return result; 
   }

   @Override
   public String getWritePortPreEntrypoint() {
      // no-op for InputIrqPorts
      return "";
   }

   @Override
   public String getWritePortPostEntrypoint() {
      // no-op for InputIrqPorts
      return "";
   }

   @Override
   public String getWritePortEventResponder() {
      ST st = getTemplateST("slihIrqDispatcher");
      st.add("dispatcher", this);
      return st.render(); 
   }

   @Override
   public String getWritePortThreadInitializer() {
      String result = "";
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         // no-op in Camkes next; now automatically handled by middleware  
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
         // no-op.  I know that this is indeed a no-op; it is handled
         // in the prx file.
      } else {
         // I have a VxWorks no-op.  This seems odd.
         // perhaps I have not implemented it yet.
         // Best to throw an exception.
         throw new TbException("InputIrqPort::writePortThreadInitializer: OS " + model.getOsTarget() + " is not yet supported.");
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
      // This is where they would be registered in VxWorks, 
      // once we add support!
      throw new TbException("InputIrqPortEmitter::vxWorksAddMainCFileDeclarations: IRQs are currently unsupported on VxWorks");
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
   public String getEChronosAddTrampolines() { 
      ST st = getTemplateST("writeDispatcherTrampoline");
      st.add("dispatcher", this);
      return st.render();
   }
   
   @Override
   public String getEChronosAddInternalIrqs() { 
      String result = "";
      InputIrqPort iip = (InputIrqPort)this.getModelElement();
      if (iip.getNumber() == InputIrqPort.NO_SIGNAL_NUMBER) { 
         ST st = getTemplateST("writeInternalInterruptHandler");
         st.add("dispatcher", this);
         result += st.render();
      }
      return result;
   }
   
   @Override
   public String getEChronosAddExternalIrqs() { 
      String result = ""; 
      InputIrqPort iip = (InputIrqPort)this.getModelElement(); 
      if (iip.getNumber() != InputIrqPort.NO_SIGNAL_NUMBER) {
         ST st = getTemplateST("writeExternalInterruptHandler");
         st.add("dispatcher", this);
         result += st.render(); 
      }
      return result;
   }
   
   /************************************************************
    * 
    * CAmkES-specific functions 
    * 
    ************************************************************/

   @Override
   public String getCamkesAddComponentPortLevelDeclarations() {
      ST st = getTemplateST("camkesIrqComponentDecl");
      st.add("dispatcher", this);
      return st.render(); 
   }

   @Override
   public String getCamkesAddAssemblyFilePortDeclarations() {
      ST st = getTemplateST("constructIRQComponent");
      st.add("dispatcher", this);
      return st.render(); 
   }
   
   @Override
   public String getCamkesAddAssemblyFileCompositionPortDeclarations() {
      String result = "";
      ST st = getTemplateST("assemblyIrqComponent"); 
      st.add("dispatcher", this);
      result += st.render();
      
      st = getTemplateST("constructIRQConnections");
      st.add("dispatcher", this);
      result += st.render(); 
      
      return result;
   }
   
   @Override
   public String getCamkesAddAssemblyFileConfigDeclarations() {
      String result = ""; 
      ST st = getTemplateST("constructIRQConfig");
      st.add("dispatcher", this);
      result += st.render();
      return result;
   }

   /************************************************************
    * 
    * Names functions used by the .stg templates for IRQ dispatchers
    *
    * type
    * name
    * threadImplementation
    * firstLevelInterruptHandler
    * dispatchOccurredVar
    * activeThreadInternalDispatcherName
    * externalHandlers
    * 
    * signalName
    * signalNumber
    * memoryRegions
    * irqComponent
    * irqObject
    * 
    * the entrypointCallerName is only dispatched remotely for 
    *    passive threads.  For active threads it will always be 
    *    dispatched from the main thread dispatch loop.
    *
    * firstLevelInterruptHandlerPrototype() <-- OS-specific
    * 
    * 
    ************************************************************/

   
   public String getIdlDispatcherName() {
      return "dispatch_" + getName();
   }

   public String getFirstLevelInterruptHandler() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement();
      return iip.getFirstLevelInterruptHandler(); 
   }
/*   
   public String getFirstLevelInterruptHandlerPrototype() {
      String result = ""; 
      ST st; 
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         st = getTemplateST("camkesFirstLevelHandlerPrototype");
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
         st = getTemplateST("eChronosFirstLevelHandlerPrototype");
      } else {
         throw new TbException("InputIrqPort::getFirstLevelInterruptHandlerPrototype: OS " + model.getOsTarget() + " is not yet supported.");         
      }
      st.add("dispatcher", this);
      result += st.render();
      return result;
   }
*/
   
   public String getSignalName() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement(); 
      return iip.getSignalName(); 
   }

   public String getSignalNumber() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement(); 
      return Integer.toString(iip.getNumber()); 
   }

   public List<MemoryRegionNames> getMemoryRegions() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement(); 
      List<MemoryRegionNames> regions = new ArrayList<>();
      for (Map.Entry<String, String> entry : iip.getMemoryRegions().entrySet()) {
         MemoryRegionNames region = EmitterFactory.memoryRegion(this.port, entry.getKey(), entry.getValue());
         regions.add(region);
      }
      return regions; 
   }

   // add support for Tower's *$&% stuff later if necessary.
   public boolean getUseTowerPrototypes() {
      return false;
   }
   
   public String getIrqObject() {
      return getName() + "_obj";
   }

   public String getIrqComponent() {
      return getName() + "_hw"; 
   }


   public String getDispatchOccurredVar() {
      return getPrefix() + "_occurred_" + getName(); 
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
  return null;
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
