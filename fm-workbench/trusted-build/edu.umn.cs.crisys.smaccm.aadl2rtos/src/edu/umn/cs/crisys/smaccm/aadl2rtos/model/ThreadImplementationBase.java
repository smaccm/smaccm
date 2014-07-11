package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

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

	public int getPriority() {
	  return this.priority;
	}
	
	public int getStackSize() {
	  return this.stackSize;
	}

	public String getGeneratedEntrypoint() {
		return generatedEntrypoint;
	}

	public boolean isISRThread() {
		// TODO Auto-generated method stub
		return false;
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
