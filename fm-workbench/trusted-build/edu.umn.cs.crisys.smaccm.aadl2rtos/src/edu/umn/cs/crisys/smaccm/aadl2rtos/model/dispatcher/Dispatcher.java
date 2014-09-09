package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.OutgoingDispatchContract;
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
	private ThreadImplementation owner;

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
	
	public abstract String getName(); 
	
	public abstract Type getType(); 
	
	public List<ExternalHandler> getExternalHandlerList() {
	  return this.externalHandlerList;
	}
	
	public ThreadImplementation getOwner() {
	  return this.owner;
	}
}
