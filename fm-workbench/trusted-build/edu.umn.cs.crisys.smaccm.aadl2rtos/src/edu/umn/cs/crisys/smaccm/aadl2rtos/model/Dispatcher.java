package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

import java.util.List;

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

	public enum DispatcherType {
		    INPUT_PORT_DISPATCHER, 
		    PERIODIC_DISPATCHER
	}
	
	public Dispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList) {
	  this.owner = owner;
	  this.externalHandlerList = externalHandlerList;
	}
	
	
	public abstract String getName(); 
	
	public List<ExternalHandler> getExternalHandlerList() {
	  return this.externalHandlerList;
	}
	
	public ThreadImplementation getOwner() {
	  return this.owner;
	}
}
