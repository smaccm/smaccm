package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

/**
 * @author Mead, Whalen
 * 
 * TODO:    In "ThreadImplementation"
 *    ! FIXED: only one thread instance for thread implementation (should be list)
 *    -> ISR threads are not distinguished from "regular" threads
 * 
 */

public class ThreadImplementationBase {

	protected int priority = -1;
	protected int stackSize = 0; 
	protected double minExecutionTime = -1.0; 
	protected double maxExecutionTime = -1.0; 
	
	protected String name;
	protected String generatedEntrypoint = null;
  
  protected int kochabThreadLocation;
  
  public int getKochabThreadLocation() {
    return this.kochabThreadLocation;
  }
  
  public void setKochabThreadLocation(int loc) {
    this.kochabThreadLocation = loc;
  }
        
	public ThreadImplementationBase(String name, int priority, int stackSize) {
	  this.name = name;
	  this.priority = priority;
	  this.stackSize = stackSize;
	}
	
	public String getName() {
		return name;
	}

	public String getNormalizedName() {
	  return Util.normalizeAadlName(this.getName());  
	}
	
	public int getPriority() {
	  return this.priority;
	}
	
	public int getStackSize() {
	  return this.stackSize;
	}

	public String getGeneratedEntrypoint() {
		return generatedEntrypoint;
	}

  /**
   * @return the minExecutionTime
   */
  public double getMinExecutionTime() {
    return minExecutionTime;
  }

  /**
   * @param minExecutionTime the minExecutionTime to set
   */
  public void setMinExecutionTime(double minExecutionTime) {
    this.minExecutionTime = minExecutionTime;
  }

  /**
   * @return the maxExecutionTime
   */
  public double getMaxExecutionTime() {
    return maxExecutionTime;
  }

  /**
   * @param maxExecutionTime the maxExecutionTime to set
   */
  public void setMaxExecutionTime(double maxExecutionTime) {
    this.maxExecutionTime = maxExecutionTime;
  }

}

// Constructor
/* 
public ThreadImplementationBase(ThreadTypeImpl tti) {
  name = tti.getName().toLowerCase();
  priority = ThreadUtil.getPriority(tti);
  stackSize = ThreadUtil.getStackSizeInBytes(tti);
  fileNames = Util.getSourceTextListOpt(tti, ThreadUtil.SOURCE_TEXT);
}
*/
