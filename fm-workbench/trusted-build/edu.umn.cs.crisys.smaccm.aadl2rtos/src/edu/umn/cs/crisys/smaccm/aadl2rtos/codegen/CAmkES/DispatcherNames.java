/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.ExternalHandler;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.IRQDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.InputEventDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.PeriodicDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.OutgoingDispatchContract;
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
  
  // NOTE: must be the same as PortNames::getDispatcherName(); 
  // That is why the custom case with instanceof.
  public String getDispatcherName() {
    if (dp instanceof InputEventDispatcher) {
      InputEventDispatcher iep = (InputEventDispatcher)dp;
      return (new PortNames(iep.getEventPort())).getDispatcherName();
    }
    return "smaccm_" + getName() + "_dispatcher";
  }

  public boolean getHasData() {
    return !(dp.getType() instanceof UnitType); 
  }
  
  public String getName() {
    return dp.getName();
  }

  public List<ExternalHandlerNames> getExternalHandlers() {
     List<ExternalHandlerNames> ehl = new ArrayList<>(); 
     for (ExternalHandler eh : dp.getExternalHandlerList()) {
       ehl.add(new ExternalHandlerNames(eh)); 
     }
     return ehl;
  }
                               
  public OutgoingDispatchContractNames getMaxDispatchContracts() {
    OutgoingDispatchContract odc = 
        OutgoingDispatchContract.maxDispatcherUse(dp.getDispatchLimits());
    OutgoingDispatchContractNames odcNames = new OutgoingDispatchContractNames(odc); 
    return odcNames;
  }
  
  public String getAsRefParam() {
    return Names.createRefParameter(dp.getType(), getName());
  }
  
  // dispatchers != ports.  We could introduce a visitor framework
  // for ports and do overriding here, but instead we will just use
  // instanceof.  
  
  public String getIsEmptyFnCall() {
    if (dp instanceof InputEventDispatcher) {
      InputEventDispatcher idp = (InputEventDispatcher)dp;
      PortNames pn = new PortNames(idp.getEventPort());
      return pn.getIsEmptyFnCall(); 
    } else {
      throw new Aadl2RtosException("Error: isEmpty called on non-port dispatcher");
    }
  }
  
  /*
   * This needs to split on the argument type.
   */
  public String getSmaccmDispatchFnStmts() {
    if (dp instanceof InputEventDispatcher) {
      InputEventDispatcher ied = (InputEventDispatcher)dp;
      if (!ied.getEventPort().hasData()) {
        return this.getDispatcherName() + "();\n";
      } else {
        StringBuffer sb = new StringBuffer();
        PortNames pn = new PortNames(ied.getEventPort());
        sb.append(pn.getVarDecl() + ";\n"); 
        sb.append(pn.getQueueReadName() + "(" + pn.getVarRef() + ");\n");
        sb.append(this.getDispatcherName() + "(" + pn.getVarRef() + ");\n" );
        return sb.toString();
      }
    }
    else if (dp instanceof IRQDispatcher) {
      return this.getDispatcherName() + "();\n";
    }
    else if (dp instanceof PeriodicDispatcher) {
      return this.getDispatcherName() + "( &" + this.getPeriodicTimeVar() + ");\n"; 
    }
    else throw new Aadl2RtosException("Error: unexpected dispatch type in getSmaccmDispatchFnStmts");
  }
  
  public String getDispatchOccurredVar() {
    return "smaccm_occurred_" + getName(); 
  }
  
  public String getPeriodicTimeVar() {
    return "smaccm_time_" + getName(); 
  }
  
  public boolean getIsPeriodic() {
    return (dp instanceof PeriodicDispatcher);
  }
  
  public boolean getIsIRQ() {
    return (dp instanceof IRQDispatcher); 
  }
  
  public boolean getIsEvent() {
    return (dp instanceof InputEventDispatcher) &&
         !((InputEventDispatcher)dp).getEventPort().hasData(); 
  }
  
  public boolean getIsEventData() {
    return (dp instanceof InputEventDispatcher) &&
        ((InputEventDispatcher)dp).getEventPort().hasData(); 
  }
  
  public String getPassiveComponentDispatcherName() {
    return Names.getComponentInstanceName(dp.getOwner()) + "_" + 
        this.getDispatcherName(); 
  }
  
};

