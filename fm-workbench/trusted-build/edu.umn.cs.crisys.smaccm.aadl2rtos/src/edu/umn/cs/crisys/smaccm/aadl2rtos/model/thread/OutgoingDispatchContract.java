/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;

/**
 * @author Whalen
 *
 */
public class OutgoingDispatchContract {
  
  HashMap<OutputEventPort, Integer> contract;

  public OutgoingDispatchContract() {
    contract = new HashMap<OutputEventPort, Integer>();
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
}
