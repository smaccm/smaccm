/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.ExternalHandler;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InitializerPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputIrqPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutgoingDispatchContract;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.PortConnection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.BoolType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class PortNames {
  DataPort dp; 
  Type indexType = new IntType(32, false); 
  
  public PortNames(DataPort dp) {
    this.dp = dp;
  }
  
  //////////////////////////////////////////////////////////
  //
  // Constructors for type names related to port
  // 
  //////////////////////////////////////////////////////////
  public TypeNames getType() {
    TypeNames tyn = new TypeNames(dp.getType());
    return tyn;
  }
  
  public TypeNames getIndexType() {
    TypeNames tyn = new TypeNames(indexType);
    return tyn;
  }
  
  // we should never enqueue more than 32k messages!
  public String getIndexMax() {
	  return "32767";
  }
  
  public ThreadImplementationNames getThreadImplementation() {
    return new ThreadImplementationNames(dp.getOwner());
  }

  public boolean getHasInitializeEntrypoint() {
    boolean result = dp.getInitializeEntrypointSourceText() != null;
    return result;
  }
  
  public ExternalHandlerNames getInitializeEntrypointOpt() {
    return new ExternalHandlerNames(dp.getInitializeEntrypointSourceText());
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
    return dp.getOwner().getName() + "_" + getName();
  }
  
  public String getQueueSize() {
    if (!(dp instanceof InputEventPort)) {
      throw new Aadl2RtosException("Error: getQueueSize: port " + dp.getName() + " is not an input event port so has no queue.");
    }
    InputEventPort iep = (InputEventPort)dp; 
    return Integer.toString(iep.getQueueSize());
  }

  //////////////////////////////////////////////////////////
  //
  // functions for creating local declarations with the port name
  // 
  //////////////////////////////////////////////////////////
  

  public String getVarDecl() {
    return dp.getType().getCType().varString(getName());
  }
  
  //////////////////////////////////////////////////////////
  //
  // query functions
  // 
  //////////////////////////////////////////////////////////

  public boolean getHasData() {
    return dp.hasData(); 
  }
  
  public boolean getHasDispatcher() {
    if (dp instanceof DispatchableInputPort) {
      return  ((DispatchableInputPort)dp).getExternalHandlerList().size() != 0;
    }
    else return false;
  }
  
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
  public List<PortConnectionNames> getConnections() {
	  
	  List<PortConnectionNames> connections = new ArrayList<>();
	  for (PortConnection i: dp.getConnections()) {
		  connections.add(new PortConnectionNames(i));
	  }
	  return connections;
  }
  
  public PortConnectionNames getSingletonConnection() {
	  if (dp.getConnections().size() != 1) {
		  throw new Aadl2RtosException("Error: getSingletonConnection: cardinality of connection != 1");
	  }
	  else {
		  return new PortConnectionNames(dp.getConnections().get(0));
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
  
  public String getGlobalMaxDispatchSize() {
    return "smaccm_max_tmp_array_" + getName(); 
  }
  
  public String getGlobalIndex() {
    return "smaccm_tmp_used_" + getName();
  }

  public String getGlobalData() {
    return "smaccm_tmp_array_" + getName();
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

  public List<ExternalHandlerNames> getExternalHandlers() {
    DispatchableInputPort dip = ((DispatchableInputPort) dp);
    List<ExternalHandlerNames> ehl = new ArrayList<>(); 
    for (ExternalHandler eh : dip.getExternalHandlerList()) {
      ehl.add(new ExternalHandlerNames(eh)); 
    }
    return ehl;
  }

  public List<MemoryRegionName> getMemoryRegions() {
    InputIrqPort iip = (InputIrqPort)dp; 
    List<MemoryRegionName> regions = new ArrayList<>();
    for (Map.Entry<String, String> entry : iip.getMemoryRegions().entrySet()) {
        MemoryRegionName region = new MemoryRegionName(entry.getKey(), entry.getValue());
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
     return "smaccm_" + getQualifiedName() + "_struct"; 
  }

  public String getDispatchStructArgName() {
    return "smaccm_" + getQualifiedName() + "_arg";
  }
  public String getEChronosSignalNumberForDispatcher() {
    throw new Aadl2RtosException("getEChronosSignalNumberForDispatcher currently unimplemented!");
  }
  
  public OutgoingDispatchContractNames getMaxDispatchContracts() {
    DispatchableInputPort dip = (DispatchableInputPort )dp; 
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(dip.getDispatchLimits());
    OutgoingDispatchContractNames odcNames = new OutgoingDispatchContractNames(dip, odc); 
    return odcNames;
  }
  

  public List<DispatchContractNames> getDispatchableContracts() {
    DispatchableInputPort dip = (DispatchableInputPort )dp; 
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(dip.getDispatchLimits());
    List<DispatchContractNames> pdl = new ArrayList<>(); 
    for (Map.Entry<OutputEventPort, Integer> elem : odc.getContract().entrySet()) {
      DispatchContractNames names = new DispatchContractNames(dip, elem);
      if (names.getCanDispatch()) {
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
    return "smaccm_occurred_" + getName(); 
  }
  
  public String getPeriodicTimeVar() {
    return "smaccm_time_" + getName(); 
  }
  
  public String getActiveThreadInternalDispatcherFnName() {
    return "smaccm_" + dp.getOwner().getNormalizedName() + "_" + getName() + "_dispatcher";
  }
  
  public String getDispatcherCFileDispatcherFnName() {
    return getActiveThreadInternalDispatcherFnName();
  }
    
  public String getIdlDispatcherName() {
    return "dispatch_" + getName();
  }
  
  public String getPassiveComponentDispatcherPathName() {
    return this.getThreadImplementation().getInterfaceInstanceName() + "_" + 
        this.getIdlDispatcherName(); 
  }
  
  //////////////////////////////////////////////////////////////
  //
  // Names for dispatcher thread global data for send/receive ports
  // 
  //////////////////////////////////////////////////////////////
  
  public String getQueueFrontName() { return "smaccm_queue_front_" + this.getName(); }
  public String getQueueBackName()  { return "smaccm_queue_back_" + this.getName(); }
  public String getQueueDataName()  { return "smaccm_queue_" + this.getName(); }
  public String getQueueFullName() { return "smaccm_queue_full_" + this.getName(); }

  public String getQueueIsFullName() { return "smaccm_queue_is_full_" + this.getName(); }
  public String getQueueIsEmptyName() { return "smaccm_queue_is_empty_" + this.getName(); }
  public String getQueueReadName() { return "smaccm_queue_read_" + this.getName(); }
  public String getQueueWriteName() { return "smaccm_queue_write_" + this.getName(); }

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
    return "smaccm_" + getName() + "_var";
  }

  public String getMutex() {
    return ("smaccm_" + dp.getOwner().getNormalizedName() + "_" + dp.getName() + "_mut").toLowerCase();
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
      throw new Aadl2RtosException("Error: getQueueDecl: port " + dp.getName() + " is not an input event port so has no queue.");
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
   
};
