/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc;

import java.util.List;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureGroup {
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
   
}
