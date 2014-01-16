/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;


/**
 * @author Whalen
 *
 * This class represents a shared data object in AADL.
 */
public class SharedDataAccessor {

  private ThreadImplementation owner;
  private String accessorName;
  private String commprimFnNameOpt;
  private String commprimHeaderNameOpt;
  private SharedData sharedData;
  public enum AccessType {READ, WRITE, READ_WRITE};
  private AccessType accessType;
  
  public SharedDataAccessor(ThreadImplementation owner, String accessorName, 
                            SharedData sharedData, AccessType accessType, 
                            String commprimFnNameOpt, 
                            String commprimHeaderNameOpt) {
    this.commprimFnNameOpt = commprimFnNameOpt;
    this.commprimHeaderNameOpt = commprimHeaderNameOpt;
    this.owner = owner;
    this.accessorName = accessorName;
    this.sharedData = sharedData;
    this.accessType = accessType;
  }
  
  public String getName() {
    return this.accessorName;
  }
  
  public AccessType getAccessType() {
    return this.accessType;
  }
  
  public SharedData getSharedData() {
    return this.sharedData;
  }
  
  public ThreadImplementation getOwner() {
    return this.owner;
  }
  
  public String getCommPrimFnNameOpt() {
    return this.commprimFnNameOpt;
  }
  
  public String getCommPrimHeaderNameOpt() {
    return this.commprimHeaderNameOpt;
  }
}
