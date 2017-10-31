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
public class InternalRemoteProcedureGroup extends RemoteProcedureGroup {
   private List<RemoteProcedure> procedures;
   
   public InternalRemoteProcedureGroup(List<RemoteProcedure> procedures, String id) {
      super(id);
      this.procedures = procedures;
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

@Override
public String getIdl4FileName() {
   // TODO Auto-generated method stub
   return getId() + ".idl4";
}
   
}
