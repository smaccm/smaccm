/**
 * 
 */
package edu.umn.cs.crisys.tb.model.rpc;

import java.util.List;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureGroup extends ModelElementBase {
   private List<RemoteProcedure> procedures;
   private String id;
   
   public RemoteProcedureGroup(List<RemoteProcedure> procedures, String id) {
     this.procedures = procedures;
     this.id = id;
   }

  /**
   * @return the procedures
   */
  public List<RemoteProcedure> getProcedures() {
    return procedures;
  }

  /**
   * @param procedures the procedures to set
   */
  public void setProcedures(List<RemoteProcedure> procedures) {
    this.procedures = procedures;
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
