package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.codegen.linux.LinuxUtil;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

public class DispatchableInputPortCommon {

   protected PortFeature port;
   protected OSModel model;

   public DispatchableInputPortCommon(PortFeature port, OSModel model) {
      this.port = port;
      this.model = model;
   }

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
   public final String getPrefix() { return Util.getPrefix(); }

   public final boolean getHasData() {
      return !(this.getModelElement().getType() instanceof UnitType); 
   }

   public final TypeNames getType() { 
      return EmitterFactory.type(this.getModelElement().getType()); 
   }

   public final String getLpcPortWriterName() {
      return Util.getPrefix_() + getModelElement().getQualifiedName() + 
            writeType();
   }

   public final String writeType() {
      return "_write_" + getModelElement().getType().getCType().typeString();
   }


   public final String getMutex() {
      return (Util.getPrefix_() + getModelElement().getOwner().getNormalizedName() + "_" + getModelElement().getName() + "_mut").toLowerCase();
    }

   public final String getEChronosMutexConst() {
       return (ModelNames.getEChronosPrefix() + "_MUTEX_ID_" + getMutex()).toUpperCase();
    }

   public final String declareMutexStmt() {
      return "";
   }
   
   public final String getLockStmt() {
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         return "MUTEXOP("+getMutex() + "_lock())";
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
         return "rtos_mutex_lock(" + getEChronosMutexConst() + ");";
      } else if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         return "semTake(" + getMutex() + ", WAIT_FOREVER);";
      } else if (model.getOsTarget() == OSModel.OSTarget.linux) {
         return LinuxUtil.lockMutex(getMutex());
      } else {
         throw new TbException("Error: getLockStmt: OS " + model.getOsTarget() + " is not a known OS target.");
      }
   }

   public final String getUnlockStmt() {
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         return "MUTEXOP("+getMutex() + "_unlock())";
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
         return "rtos_mutex_unlock(" + getEChronosMutexConst() + ");";
      } else if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         return "semGive(" + getMutex() + ");";
      } else if (model.getOsTarget() == OSModel.OSTarget.linux) {
         return LinuxUtil.unlockMutex(getMutex());
      } else {
         throw new TbException("Error: getunlockStmt: OS " + model.getOsTarget() + " is not a known OS target.");
      }
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

   public final String getUserEntrypointCallerName() {
         return this.getPrefix() + "_entrypoint_" + 
               this.getQualifiedName(); 
      }
   
   public final String getActiveThreadInternalDispatcherFnName() {
      return getUserEntrypointCallerName(); 
   }

   public final String getUserEntrypointCallerPrototype() {
      String result = ""; 
      result += "void " + getUserEntrypointCallerName();
      if (this.getHasData()) {
         result += "(const " + getType().getAadlInputType().getName() + " in_arg)";
      }
      else {
         result += "(void)";
      }
      return result;
   }

   public final String getIncomingWriterName() {
      PortFeature dp = getModelElement();
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         return dp.getName() + writeType() ;
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos || 
                 model.getOsTarget() == OSModel.OSTarget.VxWorks ||
                 model.getOsTarget() == OSModel.OSTarget.linux) {
         return getLpcPortWriterName();
      } else {
         throw new TbException("Error: getIncomingPortWriterName: OS " + model.getOsTarget() + " is not a known OS target.");
      }
   }
   
   public final List<NameEmitter> getExternalHandlers() {
      DispatchableInputPort dip = ((DispatchableInputPort) this.getModelElement());
      List<NameEmitter> ehl = new ArrayList<>(); 
      for (ExternalHandler eh : dip.getExternalHandlerList()) {
         ehl.add(EmitterFactory.externalHandler(eh)); 
      }
      return ehl;
   }
   
   public String getCapName() {
     ModelNames mn = new ModelNames(model);
     return mn.getPathName().toUpperCase();
   }
   
   public String getSystemTypeHeader() {
     ModelNames mn = new ModelNames(model);
     return mn.getSystemTypeHeaderName();
     
   }

}