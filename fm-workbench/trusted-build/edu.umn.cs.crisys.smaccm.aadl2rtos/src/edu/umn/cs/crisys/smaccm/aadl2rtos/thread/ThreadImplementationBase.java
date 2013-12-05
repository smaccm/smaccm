package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

/**
 * @author Mead, Whalen
 * 
 * TODO:    In "ThreadImplementation"
 *    ! FIXED: only one thread instance for thread implementation (should be list)
 *    -> ISR threads are not distinguished from "regular" threads
 * 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.AstHelper;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class ThreadImplementationBase {

	protected int priority = -1;
	protected int stackSize = 0; 
	protected String name;
	protected String generatedEntrypoint = null;
    protected List<String> fileNames = new ArrayList<String>();

  protected int kochabThreadLocation;
  
  public int getKochabThreadLocation() {
    return this.kochabThreadLocation;
  }
  
  public void setKochabThreadLocation(int loc) {
    this.kochabThreadLocation = loc;
  }
    
    
	// Constructor
	public ThreadImplementationBase(ThreadTypeImpl tti, AstHelper astHelper) {
		name = tti.getName().toLowerCase();
		priority = ThreadUtil.getPriority(tti);
		stackSize = ThreadUtil.getStackSizeInBytes(tti);
		fileNames = Util.getSourceTextListOpt(tti, ThreadUtil.SOURCE_TEXT);
	}

  public List<String> getFileNames() {
    return this.fileNames;
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

	
	
}