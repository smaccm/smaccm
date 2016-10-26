/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.OutgoingDispatchContract;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;

/**
 * @author Whalen
 *
 */
public class OutgoingDispatchContractNames implements NameEmitter {
  OutgoingDispatchContract odc; 
  DispatchableInputPort owner;
  
  public OutgoingDispatchContractNames(DispatchableInputPort owner, OutgoingDispatchContract odc) {
    this.owner = owner;
    this.odc = odc;
  }
  
  public List<NameEmitter> getContracts() {
    List<NameEmitter> pdl = new ArrayList<>(); 
    for (Map.Entry<OutputEventPort, Integer> elem : odc.getContract().entrySet()) {
      pdl.add(EmitterFactory.dispatchContract(owner, elem));
    }
    return pdl;
  }

  public List<NameEmitter> getPassiveContracts() {
    List<NameEmitter> pdl = new ArrayList<>(); 
    for (Map.Entry<OutputEventPort, Integer> elem : odc.getPassiveContract().entrySet()) {
      pdl.add(EmitterFactory.dispatchContract(owner, elem));
    }
    return pdl;
  }
  
  public NameEmitter getOwner() {
    return EmitterFactory.port(owner); 
  }
}

