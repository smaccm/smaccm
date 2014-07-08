package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

/**
 * @author Mead, Whalen
 * 
 * TODO:    In "ThreadImplementation"
 *    ! FIXED: only one thread instance for thread implementation (should be list)
 *    -> ISR threads are not distinguished from "regular" threads
 * 
 */

import java.util.ArrayList;
import java.util.List;


public class LegacyThreadImplementation extends ThreadImplementationBase {

	// Data port lists
	private List<String> legacyMutexList = new ArrayList<String>();
  private List<String> legacySemaphoreList = new ArrayList<String>();
	/*
	 * 		// Determine whether this is a legacy ("blob") thread
		legacyThread = ThreadUtil.getLegacyValue(tti);
		if (legacyThread) {
			legacySemaphoreList = (ArrayList<String>) ThreadUtil.getLegacySemaphoreList(tti);
		}

	 */
	// Constructor
  public LegacyThreadImplementation(String name, int priority, int stackSize, List<String> fileNames, 
      List<String> legacyMutexList, List<String> legacySemaphoreList, String generatedEntrypoint) {
    super(name, priority, stackSize, fileNames);
    this.legacyMutexList = legacyMutexList;
    this.legacySemaphoreList = legacySemaphoreList;
    this.generatedEntrypoint = generatedEntrypoint;
  }
  
  /*
	public LegacyThreadImplementation(ThreadTypeImpl tti)  
	{
		super(tti);
		try { 
			legacyMutexList = (ArrayList<String>) ThreadUtil.getLegacyMutexList(tti);
      legacySemaphoreList = (ArrayList<String>) ThreadUtil.getLegacySemaphoreList(tti);
			generatedEntrypoint = Util.getStringValue(tti, ThreadUtil.LEGACY_ENTRYPOINT);
		} catch (Exception e) {
			throw new Aadl2RtosException("Error: Legacy threads must have a SMACCM_SYS::Legacy_Entrypoint property defined");
		}
	}
	*/
  
	public boolean isLegacyThread() {
		return true;
	}

	public List<String> getLegacyMutexes() {
	    return this.legacyMutexList;
	}
	
	public List<String> getLegacySemaphores() {
	  return this.legacySemaphoreList;
	}
	  
}