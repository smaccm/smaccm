/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

/**
 * @author Whalen
 *
 */
public class DispatchContractNames {
  OutputEventPort oep;
  int size;
  Type indexType = new IntType(32, false); 
  
  public DispatchContractNames(Map.Entry<OutputEventPort, Integer> odc) {
    this.oep = odc.getKey();
    this.size = odc.getValue();
  }
  
  public DispatchContractNames(OutputEventPort oep, int size) {
    this.oep = oep;
    this.size = size;
  }
  
  public PortNames getPort() {
    return new PortNames(oep); 
  }
  
  public String getMaxDispatchSize() {
    return Integer.toString(size); 
  }
  
  public String getDataDecl() {
    PortNames pn = new PortNames(oep); 
    ArrayType aty = new ArrayType(oep.getType(), this.size);
    return aty.getCType().varString(pn.getData()); 
  }
  
  public String getIndexDecl() {
    PortNames pn = new PortNames(oep); 
    return pn.getIndexDecl();
  }
  
  public List<PortNames> getDispatchTargetList() {
    List<PortNames> targets = new ArrayList<>(); 
    for (Connection c: this.oep.getConnections()) {
      targets.add(new PortNames(c.getDestPort()));
    }
    return targets;
  }

  public String getDispatchArrayTypeName() {
    TypeNames tni = new TypeNames(oep.getType());
    return "smaccm_" + Util.normalizeAadlName(tni.getName()) 
        + "_array_" + this.getMaxDispatchSize(); 
  }

}
