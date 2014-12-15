package edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy;

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

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementationBase;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;


public class LegacyThreadImplementation extends ThreadImplementationBase {

	// Data port lists
	private List<String> legacyMutexList = new ArrayList<String>();
  private List<String> legacySemaphoreList = new ArrayList<String>();
  private List<String> legacyReferencedFiles = new ArrayList<String>(); 

  public LegacyThreadImplementation(Model model, String name, int priority, int stackSize,  
      List<String> legacyMutexList, List<String> legacySemaphoreList, String generatedEntrypoint) {
    super(model, name, priority, stackSize);
    this.legacyMutexList = legacyMutexList;
    this.legacySemaphoreList = legacySemaphoreList;
    this.generatedEntrypoint = generatedEntrypoint;
  }
  
	public boolean isLegacyThread() {
		return true;
	}

	public List<String> getLegacyMutexes() {
	    return this.legacyMutexList;
	}
	
	public List<String> getLegacySemaphores() {
	  return this.legacySemaphoreList;
	}

  /**
   * @return the legacyReferencedFiles
   */
  public List<String> getLegacyReferencedFiles() {
    return legacyReferencedFiles;
  }

  /**
   * @param legacyReferencedFiles the legacyReferencedFiles to set
   */
  public void setLegacyReferencedFiles(List<String> legacyReferencedFiles) {
    this.legacyReferencedFiles = legacyReferencedFiles;
  }
	  
}

/*
 *    // Determine whether this is a legacy ("blob") thread
  legacyThread = ThreadUtil.getLegacyValue(tti);
  if (legacyThread) {
    legacySemaphoreList = (ArrayList<String>) ThreadUtil.getLegacySemaphoreList(tti);
  }

 */
// Constructor

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

