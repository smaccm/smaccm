/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;

/**
 * @author Whalen
 *
 */
public class OutgoingDispatchContract {
  
  HashMap<OutputEventPort, Integer> contract;

  protected OutgoingDispatchContract() {
    contract = new HashMap<OutputEventPort, Integer>();
  }
  
  public OutgoingDispatchContract(List<OutputEventPort> ports) {
    contract = new HashMap<OutputEventPort, Integer>();
    
    // initializing all output ports to zero.
    for (OutputEventPort p : ports) {
      this.add(p, 0);
    }
  }
  
  /**
   * @return the contract
   */
  public HashMap<OutputEventPort, Integer> getContract() {
    return contract;
  }
  
  public void add(OutputEventPort oep, int value) {
    if (contract.containsKey(oep)) {
      value += contract.get(oep);
    }
    contract.put(oep,  value); 
  }
  
  // Merge this dispatch contract with another, preserving the largest number of 
  // dispatches for each output port.
  public void mergeLargest(OutgoingDispatchContract other) {
    for (Map.Entry<OutputEventPort, Integer> entry : other.contract.entrySet()) {
      if (contract.containsKey(entry.getKey())) {
        if (contract.get(entry.getKey()) < entry.getValue()) {
          contract.put(entry.getKey(), entry.getValue());
        }
      } else {
        contract.put(entry.getKey(), entry.getValue()); 
      }
    }
  }
  
  public static OutgoingDispatchContract maxDispatcherUse(List<OutgoingDispatchContract> dl) {
    OutgoingDispatchContract max = new OutgoingDispatchContract();
    for (OutgoingDispatchContract d: dl) {
      max.mergeLargest(d);
    }
    return max;
  }
  
  public static OutgoingDispatchContract maxUsedDispatchers(List<OutgoingDispatchContract> dl) {
    OutgoingDispatchContract max = maxDispatcherUse(dl); 
    Iterator<Map.Entry<OutputEventPort, Integer>> it = max.getContract().entrySet().iterator(); 
    while (it.hasNext()) {
      Map.Entry<OutputEventPort, Integer> elem = it.next();
      if (elem.getValue() <= 0) {
        it.remove();
      }
    }
    return max;
  }
  public int hashCode() {
    return 0;
  }

}
