/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.model.connection.PortConnection;

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
  
  /* Note: this function is 'brittle' in the face of fan-out, 
   * which is of course not allowed by seL4 
   * This might break the ground team's use of AADL */
  
  public HashMap<OutputEventPort, Integer> getPassiveContract() {
	  HashMap<OutputEventPort, Integer> passiveContract = new HashMap<>();
	  for (Map.Entry<OutputEventPort, Integer> entry : this.getContract().entrySet()) {
		  for (PortConnection pc: entry.getKey().getConnections()) {
			  if (pc.getDestPort().getOwner().getIsPassive()) {
				  passiveContract.put(entry.getKey(), entry.getValue());
			  }
		  }
	  }
	  return passiveContract;
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
