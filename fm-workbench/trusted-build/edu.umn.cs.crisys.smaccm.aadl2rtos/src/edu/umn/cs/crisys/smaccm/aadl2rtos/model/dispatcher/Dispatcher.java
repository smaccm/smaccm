package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public abstract class Dispatcher {

	private List<ExternalHandler> externalHandlerList;
//	private int period = 0;
//	private MyPort eventPort = null;
	private ThreadImplementation owner;
	//private DispatcherType dispatcherType;

	public Dispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList) {
	  this.owner = owner;
	  this.externalHandlerList = externalHandlerList;
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
