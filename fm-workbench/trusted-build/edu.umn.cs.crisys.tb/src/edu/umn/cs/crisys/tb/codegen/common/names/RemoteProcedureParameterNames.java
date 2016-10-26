/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.rpc.Direction;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureParameter;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureParameterNames implements NameEmitter {
  RemoteProcedureParameter rpp; 
  
  public RemoteProcedureParameterNames(RemoteProcedureParameter rpp) {
    this.rpp = rpp;
  }
  
  public String getDirection() {
    Direction d = rpp.getParamDirection();
    String name = 
        (d == Direction.IN) ? 
            (rpp.isByReference() ? "refin" : "in") :   
          (d == Direction.OUT) ? "out" : "inout";
    return name;
  }
  
  public String getName() {
    return rpp.getId(); 
  }
  
  public TypeNames getType() {
    return new TypeNames(rpp.getParamType());
  }

  public boolean getIsInput() {
    return (rpp.getParamDirection() == Direction.IN);
  }
}
