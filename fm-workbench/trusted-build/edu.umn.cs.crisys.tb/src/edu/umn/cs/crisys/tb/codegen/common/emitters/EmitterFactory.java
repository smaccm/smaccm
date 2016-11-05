package edu.umn.cs.crisys.tb.codegen.common.emitters;


import java.util.Map;

import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortEmitterRPCDataport;
import edu.umn.cs.crisys.tb.codegen.common.names.*;
import edu.umn.cs.crisys.tb.model.port.*;
import edu.umn.cs.crisys.tb.model.rpc.*;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.*;
import edu.umn.cs.crisys.tb.model.thread.*;
import edu.umn.cs.crisys.tb.model.type.Type;

public class EmitterFactory {
   
   public static PortEmitter port(PortFeature dp) {
//      if (dp instanceof InputDataPort || 
//          dp instanceof OutputDataPort)
//         return new RPCDataPortEmitter((InputDataPort)dp);
      // other types here...
//      else 
          return new PortNames(dp);
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

   public static MemoryRegionNames memoryRegion(String name, String region) {
      return new MemoryRegionNames(name, region);
   }
   
   public static ModelNames model(OSModel model) {
      return new ModelNames(model);
   }
   
   public static OutgoingDispatchContractNames outgoingDispatchContract(DispatchableInputPort dip, OutgoingDispatchContract ctrct) {
      return new OutgoingDispatchContractNames(dip, ctrct); 
   }
   
   public static PortConnectionNames portConnection(PortConnection pc) {
      return new PortConnectionNames(pc); 
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
   
   // we do not anticipate differences between these emitters.
   public static TypeNames type(Type arg) {
      return new TypeNames(arg);
   }
}
