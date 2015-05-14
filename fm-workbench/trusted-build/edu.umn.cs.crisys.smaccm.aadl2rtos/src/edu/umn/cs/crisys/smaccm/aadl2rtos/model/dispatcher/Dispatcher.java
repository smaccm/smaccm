package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public abstract class Dispatcher {

	private List<ExternalHandler> externalHandlerList;
	private List<String> implementationFileList;
	private ThreadImplementation owner;
  private String optSendsEventsToString = null;
	
	 // Outgoing dispatch contract (limits on dispatch)
  private ArrayList<OutgoingDispatchContract> dispatchLimits = new ArrayList<OutgoingDispatchContract>();

	
	public Dispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList) {
	  this.owner = owner;
	  this.externalHandlerList = externalHandlerList;
	}
	
  /**
   * @return the dispatchLimits
   */
  public ArrayList<OutgoingDispatchContract> getDispatchLimits() {
    return dispatchLimits;
  }

  
  /**
   * @param dispatchLimits the dispatchLimits to set
   */
  public void setDispatchLimits(ArrayList<OutgoingDispatchContract> dispatchLimits) {
    this.dispatchLimits = dispatchLimits;
  }
	
	/**
   * @return the implementationFileList
   */
  public List<String> getImplementationFileList() {
    return implementationFileList;
  }

  /**
   * @param implementationFileList the implementationFileList to set
   */
  public void setImplementationFileList(List<String> implementationFileList) {
    if (implementationFileList != null) {
      this.implementationFileList = implementationFileList;
    } else {
      this.implementationFileList = new ArrayList<String>(); 
    }
  }

  public abstract String getName(); 
	
	public abstract Type getType(); 
	
	public List<ExternalHandler> getExternalHandlerList() {
	  return this.externalHandlerList;
	}
	
	
	
	/**
   * @return the optSendsEventsToString
   */
  public String getOptSendsEventsToString() {
    return optSendsEventsToString;
  }

  /**
   * @param optSendsEventsToString the optSendsEventsToString to set
   */
  public void setOptSendsEventsToString(String optSendsEventsToString) {
    this.optSendsEventsToString = optSendsEventsToString;
  }

  public ThreadImplementation getOwner() {
	  return this.owner;
	}
  
  public int hashCode() {
    return 0;
  }
}
