/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureParameter {

  private Type paramType;
  private Direction paramDirection;
  private String id; 
  

  public RemoteProcedureParameter(Type paramType, Direction paramDirection, String id) {
    this.paramType = paramType;
    this.paramDirection = paramDirection;
    this.id = id;
  }

  /**
   * @return the paramType
   */
  public Type getParamType() {
    return paramType;
  }

  /**
   * @param paramType the paramType to set
   */
  public void setParamType(Type paramType) {
    this.paramType = paramType;
  }

  /**
   * @return the paramDirection
   */
  public Direction getParamDirection() {
    return paramDirection;
  }

  /**
   * @param paramDirection the paramDirection to set
   */
  public void setParamDirection(Direction paramDirection) {
    this.paramDirection = paramDirection;
  }
  
  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }
}
