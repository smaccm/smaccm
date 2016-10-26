package edu.umn.cs.crisys.tb.codegen.common.emitters;


import java.util.Map;

import edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.RPCDataPortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.RPCInputDataPortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.names.*;
import edu.umn.cs.crisys.tb.model.port.*;
import edu.umn.cs.crisys.tb.model.rpc.*;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.*;
import edu.umn.cs.crisys.tb.model.thread.*;
import edu.umn.cs.crisys.tb.model.type.Type;

public class EmitterFactory {
   
   public static PortEmitter port(PortFeature dp) {
      if (dp instanceof InputDataPort || 
          dp instanceof OutputDataPort)
         return new RPCDataPortEmitter((InputDataPort)dp);
      // other types here...
      else 
          return new PortNames(dp);
   }
   
   public static NameEmitter dispatchContract(DispatchableInputPort owner, Map.Entry<OutputEventPort, Integer> odc) {
      return new DispatchContractNames(owner, odc);
   }
   
   public static NameEmitter endpointConnection(EndpointConnection conn) {
      return new EndpointConnectionNames(conn);
   }

   public static NameEmitter externalHandler(ExternalHandler handler) {
      return new ExternalHandlerNames(handler);
   }

   public static NameEmitter memoryRegion(String name, String region) {
      return new MemoryRegionNames(name, region);
   }
   
   public static NameEmitter model(OSModel model) {
      return new ModelNames(model);
   }
   
   public static NameEmitter outgoingDispatchContract(DispatchableInputPort dip, OutgoingDispatchContract ctrct) {
      return new OutgoingDispatchContractNames(dip, ctrct); 
   }
   
   public static NameEmitter portConnection(PortConnection pc) {
      return new PortConnectionNames(pc); 
   }
   
   public static NameEmitter remoteProcedureGroupEndpoint(RemoteProcedureGroupEndpoint endpoint) {
      return new RemoteProcedureGroupEndpointNames(endpoint);
   }

   public static NameEmitter remoteProcedureGroup(RemoteProcedureGroup arg) {
      return new RemoteProcedureGroupNames(arg);
   }
   
   public static NameEmitter remoteProcedure(RemoteProcedure arg) {
      return new RemoteProcedureNames(arg);
   }
   
   public static NameEmitter remoteProcedureParameter(RemoteProcedureParameter arg) {
      return new RemoteProcedureParameterNames(arg);
   }
   
   public static NameEmitter sharedDataAccessor(SharedDataAccessor arg) {
      return new SharedDataAccessorNames(arg);
   }   
   
   public static NameEmitter sharedData(SharedData arg) {
      return new SharedDataNames(arg);
   }
   
   public static NameEmitter threadCalendar(ThreadCalendar arg) {
      return new ThreadCalendarNames(arg); 
   }
   
   public static NameEmitter threadImplementation(ThreadImplementation arg) {
      return new ThreadImplementationNames(arg); 
   }
   
   // we do not anticipate differences between these emitters.
   public static TypeNames type(Type arg) {
      return new TypeNames(arg);
   }
}
