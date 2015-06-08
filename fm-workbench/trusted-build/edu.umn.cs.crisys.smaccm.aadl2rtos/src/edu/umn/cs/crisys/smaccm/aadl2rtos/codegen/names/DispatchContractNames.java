/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IdType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.*;

/**
 * @author Whalen
 *
 */
public class DispatchContractNames {
  DispatchableInputPort owner;
  Map.Entry<OutputEventPort, Integer> odc;
  OutputEventPort oep;
  int size;
  Type indexType = new IntType(32, false); 
  
  public DispatchContractNames(DispatchableInputPort owner, Map.Entry<OutputEventPort, Integer> odc) {
    this.owner = owner;
    this.oep = odc.getKey();
    this.size = odc.getValue();
  }
  
  public PortNames getPort() {
    return new PortNames(oep); 
  }
  
  public String getMaxDispatchSize() {
    return Integer.toString(size); 
  }
  
  public boolean getCanDispatch() {
    return size > 0;
  }
    
  
  public List<PortNames> getPassiveDispatchTargetList() {
    List<PortNames> targets = new ArrayList<>(); 
    for (PortConnection c: this.oep.getConnections()) {
      if (c.getDestPort().getOwner().getIsPassive()) {
        targets.add(new PortNames(c.getDestPort()));
      }
    }
    return targets;
  }

  public List<PortNames> getActiveDispatchTargetList() {
    List<PortNames> targets = new ArrayList<>(); 
    for (PortConnection c: this.oep.getConnections()) {
      if (!c.getDestPort().getOwner().getIsPassive()) {
        targets.add(new PortNames(c.getDestPort()));
      }
    }
    return targets;
    
  }
  
  public PortNames getOwner() {
    return new PortNames(owner);
  }
  
  public String getDispatchStructTypeName() {
    return getOwner().getDispatchStructTypeName();
  }

}
