/**
 * 
 */
package edu.umn.cs.crisys.tb.model.rpc;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;


/**
 * @author Whalen
 *
 * This class represents a shared data object in AADL.
 */
public class RemoteProcedureGroupEndpoint extends ModelElementBase {

  private ThreadImplementation owner;
  private RemoteProcedureGroup rpg;
  private String accessorName;
  public enum AccessType {PROVIDES, REQUIRES};
  private AccessType accessType;
  
  public RemoteProcedureGroupEndpoint(ThreadImplementation owner, 
                            RemoteProcedureGroup rpg,
                            String endpointName, 
                            AccessType accessType) {
    this.rpg = rpg;
    this.owner = owner;
    this.accessorName = endpointName;
    this.accessType = accessType;
  }
  
  public String getName() {
    return this.accessorName;
  }
  
  public AccessType getAccessType() {
    return this.accessType;
  }
  
  
  public ThreadImplementation getOwner() {
    return this.owner;
  }
  
  public RemoteProcedureGroup getRemoteProcedureGroup() {
    return this.rpg;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((accessType == null) ? 0 : accessType.hashCode());
    result = prime * result
        + ((accessorName == null) ? 0 : accessorName.hashCode());
    result = prime * result + ((rpg == null) ? 0 : rpg.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RemoteProcedureGroupEndpoint other = (RemoteProcedureGroupEndpoint) obj;
    if (accessType != other.accessType)
      return false;
    if (accessorName == null) {
      if (other.accessorName != null)
        return false;
    } else if (!accessorName.equals(other.accessorName))
      return false;
    if (rpg == null) {
      if (other.rpg != null)
        return false;
    } else if (!rpg.equals(other.rpg))
      return false;
    return true;
  }

@Override
public ModelElement getParent() {
   // TODO Auto-generated method stub
   return this.getOwner();
}


  
}
