/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;


/**
 * @author Whalen
 *
 * This class represents a shared data object in AADL.
 */
public class RemoteProcedureGroupEndpoint {

  private ThreadImplementation owner;
  private RemoteProcedureGroup rpg;
  private boolean isExternal; 
  private String accessorName;
  public enum AccessType {PROVIDES, REQUIRES};
  private AccessType accessType;
  
  public RemoteProcedureGroupEndpoint(ThreadImplementation owner, 
                            RemoteProcedureGroup rpg,
                            String endpointName, 
                            AccessType accessType, 
                            boolean isExternal) {
    this.rpg = rpg;
    this.owner = owner;
    this.accessorName = endpointName;
    this.accessType = accessType;
    this.isExternal = isExternal;
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

  /**
   * @return
   */
  public boolean getIsExternal() {
    // TODO Auto-generated method stub
    return isExternal;
  }
}
