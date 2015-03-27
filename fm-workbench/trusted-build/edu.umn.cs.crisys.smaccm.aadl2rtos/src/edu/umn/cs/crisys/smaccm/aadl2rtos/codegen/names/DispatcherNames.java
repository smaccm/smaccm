/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.ExternalHandler;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.IRQDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.InputEventDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.OutgoingDispatchContract;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.PeriodicDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class DispatcherNames {
  Dispatcher dp; 
  
  DispatcherNames(Dispatcher dp) {
    this.dp = dp;
  }
  
  //////////////////////////////////////////////////////////////////////
  //
  // root port name
  // 
  //////////////////////////////////////////////////////////////////////

  public String getName() {
    return dp.getName();
  }
  
  //////////////////////////////////////////////////////////////////////
  //
  // query functions.
  //
  //////////////////////////////////////////////////////////////////////

  public boolean getHasData() {
    Type ty = dp.getType();
    boolean hasData = !(ty instanceof UnitType);
    return hasData; 
  }
  
  public boolean getIsPeriodic() {
    return (dp instanceof PeriodicDispatcher);
  }
  
  public boolean getIsIRQ() {
    return (dp instanceof IRQDispatcher); 
  }

  public boolean getIsInput() {
    return (dp instanceof InputEventDispatcher); 
  }
  public boolean getIsEvent() {
    return (getIsInput()) &&
         !((InputEventDispatcher)dp).getEventPort().hasData(); 
  }
  
  public boolean getIsEventData() {
    return (getIsInput()) &&
        ((InputEventDispatcher)dp).getEventPort().hasData(); 
  }
  
  //////////////////////////////////////////////////////////
  //
  // Constructors for name classes related to port
  // 
  //////////////////////////////////////////////////////////
  
  public List<ExternalHandlerNames> getExternalHandlers() {
     List<ExternalHandlerNames> ehl = new ArrayList<>(); 
     for (ExternalHandler eh : dp.getExternalHandlerList()) {
       ehl.add(new ExternalHandlerNames(eh)); 
     }
     return ehl;
  }

  ///////////////////////////////////////////////////
  //
  // IRQ Dispatcher things 
  //
  ///////////////////////////////////////////////////
  
  public List<MemoryRegionName> getMemoryRegions() {
    if (dp instanceof IRQDispatcher) {
      IRQDispatcher pd = (IRQDispatcher)dp;
      List<MemoryRegionName> regions = new ArrayList<>();
      for (Map.Entry<String, Long> entry : pd.getMemoryRegions().entrySet()) {
        MemoryRegionName region = new MemoryRegionName(entry.getKey(), entry.getValue());
        regions.add(region);
      }
      return regions; 
    } else {
      throw new Aadl2RtosException("getPeriodicDispatcherPeriod() : dispatcher is not a periodic dispatcher");
    }    
  }
  
  public String getSignalNumber() {
    if (dp instanceof IRQDispatcher) {
      IRQDispatcher pd = (IRQDispatcher)dp;
      return Integer.toString(pd.getNumber()); 
    } else {
      throw new Aadl2RtosException("getPeriodicDispatcherPeriod() : dispatcher is not a periodic dispatcher");
    }    
  }

  public String getIrqObject() {
    return getName() + "_obj";
  }

  public String getIrqComponent() {
    return getName() + "_hw"; 
  }

  public String getFirstLevelInterruptHandler() {
    if (dp instanceof IRQDispatcher) {
      IRQDispatcher pd = (IRQDispatcher)dp;
      return pd.getFirstLevelInterruptHandler(); 
    } else {
      throw new Aadl2RtosException("getPeriodicDispatcherPeriod() : dispatcher is not a periodic dispatcher");
    }
  }
  
  public OutgoingDispatchContractNames getMaxDispatchContracts() {
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(dp.getDispatchLimits());
    OutgoingDispatchContractNames odcNames = new OutgoingDispatchContractNames(odc); 
    return odcNames;
  }
  

  public List<DispatchContractNames> getDispatchableContracts() {
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(dp.getDispatchLimits());
    List<DispatchContractNames> pdl = new ArrayList<>(); 
    for (Map.Entry<OutputEventPort, Integer> elem : odc.getContract().entrySet()) {
      DispatchContractNames names = new DispatchContractNames(elem);
      if (names.getCanDispatch()) {
        pdl.add(new DispatchContractNames(elem));
      }
    }
    return pdl;
  }

  public PortNames getInputEventDispatcherPort() {
    if (dp instanceof InputEventDispatcher) {
      PortNames port = new PortNames(((InputEventDispatcher) dp).getEventPort());
      return port;
    } else {
      throw new Aadl2RtosException("getInputEventDispatcherPort() : dispatcher is not an input event dispatcher");
    }
  }

  public String getPeriodicDispatcherPeriod() {
    if (dp instanceof PeriodicDispatcher) {
      PeriodicDispatcher pd = (PeriodicDispatcher)dp;
      return Integer.toString(pd.getPeriod());
    } else {
      throw new Aadl2RtosException("getPeriodicDispatcherPeriod() : dispatcher is not a periodic dispatcher");
    }
  }
  
  public TypeNames getType() {
    return new TypeNames(dp.getType());
  }

  public ThreadImplementationNames getThreadImplementation() {
    return new ThreadImplementationNames(dp.getOwner());
  }

  //////////////////////////////////////////////////////////
  //
  // Var names for global variables related to dispatcher
  // 
  //////////////////////////////////////////////////////////
  

  public String getDispatchOccurredVar() {
    return "smaccm_occurred_" + getName(); 
  }
  
  public String getPeriodicTimeVar() {
    return "smaccm_time_" + getName(); 
  }
  
  
  public String getIdlDispatcherName() {
    if (getIsInput()) {
      return this.getInputEventDispatcherPort().getIdlDispatcherName();
    }
    return "dispatch_" + getName(); 
  }
  
  //////////////////////////////////////////////////////////
  //
  // Function names related to dispatcher
  // 
  //////////////////////////////////////////////////////////

  public String getDispatcherComponentDispatchName() {
    ThreadImplementationNames tin = new ThreadImplementationNames(dp.getOwner());
    return tin.getNormalizedName() + "_" + dp.getName();
  }
  
  // NOTE: for getDispatcherCFileDispatcherFnName, value returned must 
  // be the same as PortNames::getDispatcherName(); 
  // That is why the custom case with getIsInput().

  public String getDispatcherCFileDispatcherFnName() {
    if (getIsInput()) {
      return this.getInputEventDispatcherPort().getDispatcherCFileDispatcherFnName(); 
    } else {
      return "smaccm_dispatcher_" + getName();
    }
  }

  //////////////////////////////////////////////////////////
  //
  // Param/stmt/FnCall writers related to this port.
  // 
  //////////////////////////////////////////////////////////

  public String getPassiveComponentDispatcherPathName() {
    return this.getThreadImplementation().getInterfaceInstanceName() + "_" + 
        this.getIdlDispatcherName(); 
  }
  
};




