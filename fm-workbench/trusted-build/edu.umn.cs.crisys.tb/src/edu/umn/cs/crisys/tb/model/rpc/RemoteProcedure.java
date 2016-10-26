/**
 * 
 */
package edu.umn.cs.crisys.tb.model.rpc;

import java.util.List;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public class RemoteProcedure extends ModelElementBase {
  private List<RemoteProcedureParameter> arguments;
  private Type returnType;
  private String id; 

  public RemoteProcedure(String id, List<RemoteProcedureParameter> arguments, Type returnType) {
    this.id = id;
    this.arguments = arguments; 
    this.returnType = returnType; 
  }
  
  /**
   * @return the arguments
   */
  public List<RemoteProcedureParameter> getArguments() {
    return arguments;
  }
  /**
   * @param arguments the arguments to set
   */
  public void setArguments(List<RemoteProcedureParameter> arguments) {
    this.arguments = arguments;
  }
  /**
   * @return the paramType
   */
  public Type getReturnType() {
    return returnType;
  }
  /**
   * @param paramType the paramType to set
   */
  public void setReturnType(Type paramType) {
    this.returnType = paramType;
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

@Override
public ModelElement getParent() {
   return null;
}

  
}
