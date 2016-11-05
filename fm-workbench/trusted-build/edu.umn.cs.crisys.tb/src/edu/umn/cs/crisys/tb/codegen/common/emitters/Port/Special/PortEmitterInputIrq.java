package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Special;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterEChronos;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterVxWorks;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InitializerPort;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputIrqPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.BoolType;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

public class PortEmitterInputIrq implements PortEmitterCamkes, PortEmitterEChronos, PortEmitterVxWorks {

   public static boolean isApplicable(PortFeature pf) {
      // right kind of port
      boolean ok = (pf instanceof InputIrqPort);
      return ok;
   }
   
   private PortFeature port;
   private OSModel model; 
   Type indexType = new IntType(32, false); 
   STGroupFile template; 
   
   public PortEmitterInputIrq(PortFeature pf) {
      template = Util.createTemplate("InputIrqPortEmitter.stg");
      this.port = pf;
      this.model = Util.getElementOSModel(pf);
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
   public void writePortCFiles(File directory) {
      // no-op for InputIrqPorts
   }

   @Override
   public void writePortHFiles(File directory) {
      // no-op for InputIrqPorts
   }
   
   @Override
   public String writePortHPrototypes() {
      ST st = getTemplateST("writeUdePrototype");
      st.add("dispatcher", this);
      return st.render(); 
   }


   @Override
   public String writePortDeclarations() {
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
   public String writePortPreEntrypoint() {
      // no-op for InputIrqPorts
      return "";
   }

   @Override
   public String writePortPostEntrypoint() {
      // no-op for InputIrqPorts
      return "";
   }

   @Override
   public String writePortEventResponder() {
      String result = ""; 
      ST st = getTemplateST("irqDispatcher");
      st.add("dispatcher", this);
      return st.render(); 
   }

   @Override
   public String writePortThreadInitializer() {
      String result = "";
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         result += this.getName() + "_reg_callback(" + 
               this.getActiveThreadInternalDispatcherFnName() + ", NULL); \n";
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
   public String vxWorksAddCommonHFileDeclarations() {
      // no-op for InitializerPorts
      return "";
   }

   @Override
   public String vxWorksAddMainCFileIncludes() {
      // no-op for InitializerPorts
      return "";
   }

   @Override
   public String vxWorksAddMainCFileDeclarations() {
      // This is where they would be registered in VxWorks, 
      // once we add support!
      throw new TbException("InputIrqPortEmitter::vxWorksAddMainCFileDeclarations: IRQs are currently unsupported on VxWorks");
   }

   @Override
   public String vxWorksAddMainCFileInitializers() {
      // Need to add support for IRQs for VxWorks
      return ""; 
   }

   @Override
   public String vxWorksAddMainCFileDestructors() {
      // Need to add support for IRQs for VxWorks
      return "";
   }

   /************************************************************
    * 
    * eChronos-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/

   public String eChronosAddPrxMutexes() {
      return "";
   }
   
   public String eChronosAddPrxSemaphores() {
      return "";
   }

   @Override
   public String eChronosAddCommonHFileDeclarations() {
      return "";
   }

   @Override
   public String addTrampolines() { 
      ST st = getTemplateST("writeDispatcherTrampoline");
      st.add("dispatcher", this);
      return st.render();
   }
   
   @Override
   public String addInternalIrqs() { 
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
   public String addExternalIrqs() { 
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
   public String addComponentPortLevelDeclarations() {
      ST st = getTemplateST("camkesIrqComponentDecl");
      st.add("dispatcher", this);
      return st.render(); 
   }

   @Override
   public String addAssemblyFilePortDeclarations() {
      ST st = getTemplateST("constructIRQComponent");
      st.add("dispatcher", this);
      return st.render(); 
   }
   
   @Override
   public String addAssemblyFileCompositionPortDeclarations() {
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
   public String addAssemblyFileConfigDeclarations() {
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
    * mainLockReleaseStmt <-- OS-specific
    * activeThreadInternalDispatcherName
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
    * 
    ************************************************************/

   public ThreadImplementationNames getThreadImplementation() {
      return EmitterFactory.threadImplementation(this.getModelElement().getOwner());
   }
   
   public TypeNames getType() { 
      return EmitterFactory.type(this.getModelElement().getType()); 
   }

   public String getIncomingWriterName() { return "!DUMMY!"; }
   
   public String getPrefix() { return Util.getPrefix(); }
   
   public String getIdlDispatcherName() {
      return "dispatch_" + getName();
   }

   public String getFirstLevelInterruptHandler() {
      InputIrqPort iip = (InputIrqPort)this.getModelElement();
      return iip.getFirstLevelInterruptHandler(); 
   }
   
   public String getActiveThreadInternalDispatcherFnName() {
      return this.getPrefix() + "_process_irq_" + this.getName(); 
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
         NameEmitter region = EmitterFactory.memoryRegion(entry.getKey(), entry.getValue());
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

}
