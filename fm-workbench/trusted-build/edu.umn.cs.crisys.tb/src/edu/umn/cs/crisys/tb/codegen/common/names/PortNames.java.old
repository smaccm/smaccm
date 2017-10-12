/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InitializerPort;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputIrqPort;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.port.OutgoingDispatchContract;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.type.BoolType;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;

/**
 * @author Whalen
 *
 * This class is going away, once we get emitters sorted out.
 */
public class PortNames implements PortEmitter {
   PortFeature dp; 
   Type indexType = new IntType(32, false); 

   public PortNames(PortFeature dp) {
      this.dp = dp;
   }

   //////////////////////////////////////////////////////////
   //
   // implementations of the CWriter interface elements
   // 
   //////////////////////////////////////////////////////////

   /*
    * How are we going to transition?
    * 
    * So, we are planning on having a separate file per OS.
    * 
    */

   //////////////////////////////////////////////////////////
   //
   // Constructors for type names related to port
   // 
   //////////////////////////////////////////////////////////

   // relevant to all ports
   public TypeNames getType() {
      TypeNames tyn = EmitterFactory.type(dp.getType());
      return tyn;
   }

   // relevant to event data ports
   public NameEmitter getIndexType() {
      NameEmitter tyn = EmitterFactory.type(indexType);
      return tyn;
   }

   // relevant (kinda) to event ports
   // we should never enqueue more than 32k messages!
   public String getIndexMax() {
      return "32767";
   }

   // relevant to all ports
   public NameEmitter getThreadImplementation() {
      return EmitterFactory.threadImplementation(dp.getOwner());
   }

   // relevant to all ports
   public boolean getHasFanout() {
      return dp.getConnections().size() > 1;
   }

   // relevant to all ports
   public boolean getHasInitializeEntrypoint() {
      boolean result = dp.getInitializeEntrypointSourceText() != null;
      return result;
   }

   // relevant to all ports
   public NameEmitter getInitializeEntrypointOpt() {
      return EmitterFactory.externalHandler(dp.getInitializeEntrypointSourceText());
   }

   //////////////////////////////////////////////////////////
   //
   // 'root' name and elements.
   // 
   //////////////////////////////////////////////////////////

   public String getName() {
      return dp.getName();
   }

   public String getQualifiedName() {
      return dp.getQualifiedName();
   }

   // relevant to only event data ports
   public String getQueueSize() {
      if (!(dp instanceof InputEventPort)) {
         throw new TbException("Error: getQueueSize: port " + dp.getName() + " is not an input event port so has no queue.");
      }
      InputEventPort iep = (InputEventPort)dp; 
      return Integer.toString(iep.getQueueSize());
   }

   //////////////////////////////////////////////////////////
   //
   // functions for creating local declarations with the port name
   // 
   //////////////////////////////////////////////////////////

   // relevant to any port
   public String getVarDecl() {
      return dp.getType().getCType().varString(getName());
   }

   //////////////////////////////////////////////////////////
   //
   // query functions
   // 
   //////////////////////////////////////////////////////////

   // relevant to distinguish event from event-data ports.
   public boolean getHasData() {
      return dp.hasData(); 
   }

   // relevant only for event-data ports
   public boolean getHasDispatcher() {
      if (dp instanceof DispatchableInputPort) {
         return  ((DispatchableInputPort)dp).getExternalHandlerList().size() != 0;
      }
      else return false;
   }


   // type tests - should no longer be necessary at all
   public boolean getIsInputEvent() {
      return (dp instanceof InputEventPort) &&
            (dp.getType() instanceof UnitType); 
   }

   public boolean getIsInputEventData() {
      return (dp instanceof InputEventPort) &&
            !(dp.getType() instanceof UnitType); 
   }

   public boolean getIsInputData() {
      return (dp instanceof InputDataPort) ; 
   }

   public boolean getIsOutputData() {
      return (dp instanceof OutputDataPort);
   }

   public boolean getIsOutputEvent() {
      return (dp instanceof OutputEventPort);
   }

   public boolean getIsPeriodic() {
      return (dp instanceof InputPeriodicPort);
   }

   public boolean getIsIRQ() {
      return (dp instanceof InputIrqPort); 
   }

   public boolean getIsInitializer() {
      return (dp instanceof InitializerPort); 
   }

   //////////////////////////////////////////////////////////
   //
   // Destination function (for input ports)
   // 
   //////////////////////////////////////////////////////////

   // relevant only for event-data ports
   public boolean getHasPassiveConnectionDestination() {
      for (PortConnection i: dp.getConnections()) {
         if (i.getDestPort().getOwner().getIsPassive()) {
            return true;
         }
      }
      return false;
   }

   // for all ports
   public List<NameEmitter> getConnections() {
      List<NameEmitter> connections = new ArrayList<>();
      for (PortConnection i: dp.getConnections()) {
         connections.add(EmitterFactory.portConnection(i));
      }
      return connections;
   }

   // for all ports, kind of.
   public NameEmitter getSingletonConnection() {
      if (dp.getConnections().size() != 1) {
         throw new TbException(
               "PortNames::getSingletonConnection: The port " + dp.getName() + 
               " has multiple connections, violating the invariant.");
      }
      else {
         return EmitterFactory.portConnection(dp.getConnections().get(0));
      }
   }


   //////////////////////////////////////////////////////////////
   //
   // Names for mutex function calls
   // 
   //////////////////////////////////////////////////////////////



   public String getCamkesMutexLockFnCall() {
      return getMutex() + "_lock()";
   }

   public String getCamkesMutexUnlockFnCall() {
      return getMutex() + "_unlock()";
   }

   //////////////////////////////////////////////////////////////
   //
   // Names for passive component global data related to port
   //
   // TODO: these need to change for dataport-based comm scheme.
   // 
   //////////////////////////////////////////////////////////////

   public String getPrefix() {
      return OSModel.getPrefix();
   }

   public String getGlobalMaxDispatchSize() {
      return getPrefix() + "_max_tmp_array_" + getName(); 
   }

   public String getGlobalIndex() {
      return getPrefix() + "_tmp_used_" + getName();
   }

   public String getGlobalData() {
      return getPrefix() + "_tmp_array_" + getName();
   }

   public String getData() {
      return getName() + "_data";
   }

   public String getIndex() {
      return getName() + "_index";
   }

   public String getDataField() {
      return getDispatchStructArgName() + "." + getData();
   }

   public String getIndexField() {
      return getDispatchStructArgName() + "." + getIndex(); 
   }

   public String getDataFieldFromPtr() {
      return getDispatchStructArgName() + "->" + getData();
   }

   public String getIndexFieldFromPtr() {
      return getDispatchStructArgName() + "->" + getIndex(); 
   }

   //////////////////////////////////////////////////////////////
   //
   // Names for port dispatcher contents: these are specific to kinds
   // of ports, so the port type should be checked prior to 
   // calling these functions.
   // 
   //////////////////////////////////////////////////////////////

   public List<NameEmitter> getExternalHandlers() {
      DispatchableInputPort dip = ((DispatchableInputPort) dp);
      List<NameEmitter> ehl = new ArrayList<>(); 
      for (ExternalHandler eh : dip.getExternalHandlerList()) {
         ehl.add(EmitterFactory.externalHandler(eh)); 
      }
      return ehl;
   }

   public List<NameEmitter> getMemoryRegions() {
      InputIrqPort iip = (InputIrqPort)dp; 
      List<NameEmitter> regions = new ArrayList<>();
      for (Map.Entry<String, String> entry : iip.getMemoryRegions().entrySet()) {
         NameEmitter region = EmitterFactory.memoryRegion(entry.getKey(), entry.getValue());
         regions.add(region);
      }
      return regions; 
   }

   public String getSignalNumber() {
      InputIrqPort iip = (InputIrqPort)dp; 
      return Integer.toString(iip.getNumber()); 
   }

   public String getSignalName() {
      InputIrqPort iip = (InputIrqPort)dp; 
      return iip.getSignalName(); 
   }

   public String getIrqObject() {
      return getName() + "_obj";
   }

   public String getIrqComponent() {
      return getName() + "_hw"; 
   }

   public String getFirstLevelInterruptHandler() {
      InputIrqPort iip = (InputIrqPort)dp; 
      return iip.getFirstLevelInterruptHandler(); 
   }

   // for creating dispatcher structures for components
   public String getDispatchStructTypeName() {
      return getPrefix() + "_" + getQualifiedName() + "_struct"; 
   }

   public String getMailboxStructTypeName() {
      return getPrefix() + "_" + getQualifiedName() + "_mbox";
   }

   public String getDispatchStructArgName() {
      return getPrefix() + "_" + getQualifiedName() + "_arg";
   }
   public String getEChronosSignalNumberForDispatcher() {
      throw new TbException("getEChronosSignalNumberForDispatcher currently unimplemented!");
   }

   public String getEChronosIrqFlihName() {
      return getPrefix() + ("_irq_" + getSignalName()).toLowerCase();
   }

   public String getEChronosIrqSignalSetDefine() {
      return ("SIGNAL_SET_" + this.getEChronosIrqFlihName()).toUpperCase();
   }

   public String getEChronosIrqSignalDefine() {
      return ("IRQ_EVENT_ID_" + this.getEChronosIrqFlihName()).toUpperCase();
   }

   public String getFirstLevelInterruptHandlerWrapper() {
      return getPrefix() + "_" + this.getFirstLevelInterruptHandler();
   }

   public String getEChronosIncomingPortWriterName() {
      return getPrefix() + "_" + getQualifiedName() + "_" + getType().getWriterFn();
   }

   public String getVxWorksIncomingPortWriterName() {
      return getEChronosIncomingPortWriterName();
   }

   public NameEmitter getMaxDispatchContracts() {
      DispatchableInputPort dip = (DispatchableInputPort )dp; 
      OutgoingDispatchContract odc = 
            OutgoingDispatchContract.maxDispatcherUse(dip.getDispatchLimits());
      NameEmitter odcNames = 
            EmitterFactory.outgoingDispatchContract(dip, odc); 
      return odcNames;
   }


   public List<NameEmitter> getDispatchableContracts() {
      DispatchableInputPort dip = (DispatchableInputPort )dp; 
      OutgoingDispatchContract odc = 
            OutgoingDispatchContract.maxDispatcherUse(dip.getDispatchLimits());
      List<NameEmitter> pdl = new ArrayList<>(); 
      for (Map.Entry<OutputEventPort, Integer> elem : odc.getPassiveContract().entrySet()) {
         NameEmitter names = 
               EmitterFactory.dispatchContract(dip, elem);
         if (elem.getValue() > 0) {
            pdl.add(new DispatchContractNames(dip, elem));
         }
      }
      return pdl;
   }

   public String getPeriodicDispatcherPeriod() {
      InputPeriodicPort ipp = (InputPeriodicPort)dp;
      return Integer.toString(ipp.getPeriod());
   }

   public String getDispatchOccurredVar() {
      return getPrefix() + "_occurred_" + getName(); 
   }

   public String getPeriodicTimeVar() {
      return getPrefix() + "_time_" + getName(); 
   }

   public String getActiveThreadInternalDispatcherFnName() {
      return getPrefix() + "_" + dp.getOwner().getNormalizedName() + "_" + getName() + "_dispatcher";
   }

   public String getDispatcherCFileDispatcherFnName() {
      return getActiveThreadInternalDispatcherFnName();
   }

   public String getIdlDispatcherName() {
      return "dispatch_" + getName();
   }

   public String getPassiveComponentDispatcherPathName() {
      // TODO: remove this...

      return ((ThreadImplementationNames)this.getThreadImplementation()).getInterfaceInstanceName() + "_" + 
      this.getIdlDispatcherName(); 
   }

   //////////////////////////////////////////////////////////////
   //
   // Names for dispatcher thread global data for send/receive ports
   // 
   //////////////////////////////////////////////////////////////

   public String getQueueFrontName() { return getPrefix() + "_queue_front_" + this.getName(); }
   public String getQueueBackName()  { return getPrefix() + "_queue_back_" + this.getName(); }
   public String getQueueDataName()  { return getPrefix() + "_queue_" + this.getName(); }
   public String getQueueFullName() { return getPrefix() + "_queue_full_" + this.getName(); }
   public String getQueueIsFullName() { return getPrefix() + "_queue_is_full_" + this.getName(); }
   public String getQueueIsEmptyName() { return getPrefix() + "_queue_is_empty_" + this.getName(); }
   public String getQueueReadName() { return getPrefix() + "_queue_read_" + this.getName(); }
   public String getQueueWriteName() { return getPrefix() + "_queue_write_" + this.getName(); }

   public String getThreadImplReaderFnName(String commPrim, String tName, String varName) {
      if (commPrim != null) {
         return commPrim;
      } else {
         return tName + "_read_" + varName;
      }
   }

   public String getThreadImplWriterFnName(String commPrim, String tName, String varName) {
      if (commPrim != null) {
         return commPrim;
      } else {
         return tName + "_write_" + varName;
      }
   }

   public String getAadlReaderFnName() {
      ThreadImplementationNames tin = new ThreadImplementationNames(dp.getOwner());
      return getThreadImplReaderFnName(dp.getCommprimFnNameOpt(), tin.getNormalizedName(), getName());
   }

   public String getAadlWriterFnName() {
      ThreadImplementationNames tin = new ThreadImplementationNames(dp.getOwner());
      return getThreadImplWriterFnName(dp.getCommprimFnNameOpt(), tin.getNormalizedName(), getName());
   }


   //////////////////////////////////////////////////////////////
   //
   // Names for dispatcher thread global data for read/write ports
   // and shared variables. 
   // 
   //////////////////////////////////////////////////////////////

   public String getReaderWriterImplVar() {
      return getPrefix() + "_" + getName() + "_var";
   }

   public String getMutex() {
      return (getPrefix() + "_" + dp.getOwner().getNormalizedName() + "_" + dp.getName() + "_mut").toLowerCase();
   }

   public String getEChronosMutexConst() {
      return (ModelNames.getEChronosPrefix() + "_MUTEX_ID_" + getMutex()).toUpperCase();
   }

   ////////////////////////////////////////////////////////////
   // 
   // Decl functions; these should be eliminated and replaced
   // with a <type> <varName> declaration in stringtemplate.
   //
   ////////////////////////////////////////////////////////////

   public String getQueueDataDecl()  { 
      if (!(dp instanceof InputEventPort)) {
         throw new TbException("Error: getQueueDecl: port " + dp.getName() + " is not an input event port so has no queue.");
      }
      InputEventPort iep = (InputEventPort)dp; 
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



   ////////////////////////////////////////////////////////////
   // 
   // FnCall and Stmt functions; these should be eliminated and replaced
   // with a <varName>(<args>) declaration in stringtemplate.
   //
   ////////////////////////////////////////////////////////////

   public String getIsEmptyFnCall() {
      return getQueueIsEmptyName() + "()"; 
   }

   public String getIsFullFnCall() {
      return getQueueIsFullName() + "()"; 
   }

   public String getGlobalDataSlot() {
      return getGlobalData() + "[*" + getGlobalIndex() + "]";
   }

   public String getVarRef() {
      return this.getType().getName() + this.getName(); 
   }

   public String getReaderWriterImplVarDecl() {
      return dp.getType().getCType().varString(getReaderWriterImplVar());
   }

   /////////////////////////////////////////////////////////////////
   //
   // Mailbox id for the port.
   // 
   // Right now, trusted build iterates through each connection and 
   // builds the necessary shared memory or CAmkES RPC for each.
   // The wrinkle for mailboxes is that we want write access for 
   // some buffers but not others.
   //
   /////////////////////////////////////////////////////////////////

   public int getCalculateWriterBufferSize() {
      return 12 * (dp.getConnections().size() + 2);
   }


   /////////////////////////////////////////////////////////////////////
   //
   // dummy methods that will be removed (as will this class) once 
   // we port over the names into appropriate emitter subclasses
   //
   /////////////////////////////////////////////////////////////////////
   
   @Override
   public void getWritePortCFiles(File directory) {
      // TODO Auto-generated method stub

   }

   @Override
   public void getWritePortHFiles(File directory) {
      // TODO Auto-generated method stub

   }

   @Override
   public String getWritePortHPrototypes() {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   public String getWritePortDeclarations() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public PortFeature getModelElement() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getWritePortPreEntrypoint() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getWritePortPostEntrypoint() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getWritePortEventResponder() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getWritePortThreadInitializer() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getIncomingWriterName() {
      // TODO Auto-generated method stub
      return null;
   }


};
