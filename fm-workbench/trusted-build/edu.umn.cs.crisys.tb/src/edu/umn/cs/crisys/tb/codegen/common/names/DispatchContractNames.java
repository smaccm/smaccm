/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.thread.*;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public class DispatchContractNames implements NameEmitter {
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
  
  public NameEmitter getPort() {
    return EmitterFactory.port(oep); 
  }
  
  public String getMaxDispatchSize() {
    return Integer.toString(size); 
  }
  
  public boolean getCanDispatch() {
    return size > 0;
  }
    
  
  public List<NameEmitter> getPassiveDispatchTargetList() {
    List<NameEmitter> targets = new ArrayList<>(); 
    for (PortConnection c: this.oep.getConnections()) {
      if (c.getDestPort().getOwner().getIsPassive()) {
        targets.add(EmitterFactory.port(c.getDestPort()));
      }
    }
    return targets;
  }

  public List<NameEmitter> getActiveDispatchTargetList() {
    List<NameEmitter> targets = new ArrayList<>(); 
    for (PortConnection c: this.oep.getConnections()) {
      if (!c.getDestPort().getOwner().getIsPassive()) {
        targets.add(EmitterFactory.port(c.getDestPort()));
      }
    }
    return targets;
    
  }
  
  public NameEmitter getOwner() {
    return EmitterFactory.port(owner);
  }
  
}
