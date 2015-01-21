/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.Direction;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureParameter;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureParameterNames {
  RemoteProcedureParameter rpp; 
  
  public RemoteProcedureParameterNames(RemoteProcedureParameter rpp) {
    this.rpp = rpp;
  }
  
  public String getDirection() {
    Direction d = rpp.getParamDirection();
    String name = 
        (d == Direction.IN) ? "in" : 
          (d == Direction.OUT) ? "out" : "in out";
    return name;
  }
  
  public String getName() {
    return rpp.getId(); 
  }
  
  public TypeNames getType() {
    return new TypeNames(rpp.getParamType());
  }
}
