/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutgoingDispatchContract;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;

/**
 * @author Whalen
 *
 */
public class OutgoingDispatchContractNames {
  OutgoingDispatchContract odc; 
  DispatchableInputPort owner;
  
  OutgoingDispatchContractNames(DispatchableInputPort owner, OutgoingDispatchContract odc) {
    this.owner = owner;
    this.odc = odc;
  }
  
  public List<DispatchContractNames> getContracts() {
    List<DispatchContractNames> pdl = new ArrayList<>(); 
    for (Map.Entry<OutputEventPort, Integer> elem : odc.getContract().entrySet()) {
      pdl.add(new DispatchContractNames(owner, elem));
    }
    return pdl;
  }

  public PortNames getOwner() {
    return new PortNames(owner);
  }
}

