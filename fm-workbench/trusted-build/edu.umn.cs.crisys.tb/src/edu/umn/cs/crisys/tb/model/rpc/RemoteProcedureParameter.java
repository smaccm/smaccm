/**
 * 
 */
package edu.umn.cs.crisys.tb.model.rpc;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureParameter extends ModelElementBase {

  private Type paramType;
  private Direction paramDirection;
  private String id; 
  // This is only relevant for input parameters; all other parameter types 
  // must be passed by reference in C.
  private boolean byReference;

  public RemoteProcedureParameter(Type paramType, Direction paramDirection, String id, 
      boolean byReference) {
    this.paramType = paramType;
    this.paramDirection = paramDirection;
    this.id = id;
    this.byReference = byReference;
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

  public boolean isByReference() {
    return byReference;
  }

  public void setByReference(boolean byReference) {
    this.byReference = byReference;
  }

@Override
public ModelElement getParent() {
   return null;
}
}
