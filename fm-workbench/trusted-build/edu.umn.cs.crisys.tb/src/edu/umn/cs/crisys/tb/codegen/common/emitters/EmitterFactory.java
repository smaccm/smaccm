package edu.umn.cs.crisys.tb.codegen.common.emitters;


import java.util.Map;

import edu.umn.cs.crisys.tb.TbException;
//import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.MailBox.PortConnectionEmitterMailBoxDataport;
//import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.MailBox.PortEmitterMailBoxDataport;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Notification.PortEmitterNotification;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortConnectionEmitterRPCImpl;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortEmitterRPCAllEvent;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortEmitterRPCAllEventCAmkESMonitor;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortEmitterRPCDataport;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortEmitterRPCDataportCAmkESMonitor;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.SharedMem.PortConnectionEmitterSharedMemDataport;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.SharedMem.PortEmitterSharedMemDataport;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Special.PortEmitterInitializer;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Special.PortEmitterInputIrq;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Special.PortEmitterInputPeriodic;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.VMSharedMem.PortConnectionEmitterVMDataport;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.VMSharedMem.PortEmitterSimpleVMDataport;
import edu.umn.cs.crisys.tb.codegen.common.names.*;
import edu.umn.cs.crisys.tb.model.port.*;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.rpc.*;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.*;
import edu.umn.cs.crisys.tb.model.thread.*;
import edu.umn.cs.crisys.tb.model.type.Type;

public class EmitterFactory {
   
   public static PortEmitter port(PortFeature dp) {

      // Mailbox port emitter here!
      
      
//      if (PortEmitterMailBoxDataport.isApplicable(dp)) {
//         return new PortEmitterMailBoxDataport(dp);
//      }
      // Cross VM dataport emitter (placeholder for mailboxes)
      if (PortEmitterSimpleVMDataport.isApplicable(dp)) {
         return new PortEmitterSimpleVMDataport(dp);
      }
      // Shared memory only emitter (no RPCs) for linux 
      else if (PortEmitterSharedMemDataport.isApplicable(dp)) {
         return new PortEmitterSharedMemDataport(dp);
      }
      // A notification-based event port for Camkes
      if (PortEmitterNotification.isApplicable(dp)) {
         return new PortEmitterNotification(dp);
      // Camkes port emitters...
      } else if (PortEmitterRPCAllEventCAmkESMonitor.isApplicable(dp)) {
          return new PortEmitterRPCAllEventCAmkESMonitor(dp);
      } else if (PortEmitterRPCDataportCAmkESMonitor.isApplicable(dp)) {
        return new PortEmitterRPCDataportCAmkESMonitor(dp); 
      // Default port emitters
      } else if (PortEmitterRPCAllEvent.isApplicable(dp)) {
         return new PortEmitterRPCAllEvent(dp); 
      } else if (PortEmitterRPCDataport.isApplicable(dp)) {
         return new PortEmitterRPCDataport(dp);
      } else if (PortEmitterInitializer.isApplicable(dp)) {
         return new PortEmitterInitializer(dp); 
      } else if (PortEmitterInputIrq.isApplicable(dp)) {
         return new PortEmitterInputIrq(dp);
      } else if (PortEmitterInputPeriodic.isApplicable(dp)) {
         return new PortEmitterInputPeriodic(dp); 
      } else {
         throw new TbException("EmitterFactory::port: No emitter found that is compatible with: " + dp.getName());
      }
      
   }
   
   public static PortConnectionEmitter portConnection(PortConnection pc) {
      
      
      // Specialized VM connection emitter
//      if (PortConnectionEmitterMailBoxDataport.isApplicable(pc)) {
//         return new PortConnectionEmitterMailBoxDataport(pc);
//      }
      if (PortConnectionEmitterVMDataport.isApplicable(pc)) {
         return new PortConnectionEmitterVMDataport(pc);
      }
      
      // 'standard' emitters - the RPC emitter also works for notifications. 
      else if (PortConnectionEmitterSharedMemDataport.isApplicable(pc)) {
         return new PortConnectionEmitterSharedMemDataport(pc);
      } else if (PortConnectionEmitterRPCImpl.isApplicable(pc)) {
         return new PortConnectionEmitterRPCImpl(pc);
      } else {
         throw new TbException("EmitterFactory::port: No emitter found that is compatible with: " + pc.getName());
      }
   }
   

   public static DispatchContractNames dispatchContract(DispatchableInputPort owner, Map.Entry<OutputEventPort, Integer> odc) {
      return new DispatchContractNames(owner, odc);
   }
   
   public static EndpointConnectionNames endpointConnection(EndpointConnection conn) {
      return new EndpointConnectionNames(conn);
   }

   public static ExternalHandlerNames externalHandler(ExternalHandler handler) {
      return new ExternalHandlerNames(handler);
   }

   public static MemoryRegionNames memoryRegion(PortFeature pf, String name, String region) {
      return new MemoryRegionNames(port(pf), name, region);
   }
   
   public static ModelNames model(OSModel model) {
      return new ModelNames(model);
   }
   
   public static OutgoingDispatchContractNames outgoingDispatchContract(DispatchableInputPort dip, OutgoingDispatchContract ctrct) {
      return new OutgoingDispatchContractNames(dip, ctrct); 
   }
   
   public static RemoteProcedureGroupEndpointNames remoteProcedureGroupEndpoint(RemoteProcedureGroupEndpoint endpoint) {
      return new RemoteProcedureGroupEndpointNames(endpoint);
   }

   public static RemoteProcedureGroupNames remoteProcedureGroup(RemoteProcedureGroup arg) {
      return new RemoteProcedureGroupNames(arg);
   }
   
   public static RemoteProcedureNames remoteProcedure(RemoteProcedure arg) {
      return new RemoteProcedureNames(arg);
   }
   
   public static RemoteProcedureParameterNames remoteProcedureParameter(RemoteProcedureParameter arg) {
      return new RemoteProcedureParameterNames(arg);
   }
   
   public static SharedDataAccessorNames sharedDataAccessor(SharedDataAccessor arg) {
      return new SharedDataAccessorNames(arg);
   }   
   
   public static SharedDataNames sharedData(SharedData arg) {
      return new SharedDataNames(arg);
   }
   
   public static ThreadCalendarNames threadCalendar(ThreadCalendar arg) {
      return new ThreadCalendarNames(arg); 
   }
   
   public static ThreadImplementationNames threadImplementation(ThreadImplementation arg) {
      return new ThreadImplementationNames(arg); 
   }
   
   public static ProcessImplementationNames processImplementation(ProcessImplementation arg) {
      return new ProcessImplementationNames(arg);
   }
   
   // we do not anticipate differences between these emitters.
   public static TypeNames type(Type arg) {
      return new TypeNames(arg);
   }
}
