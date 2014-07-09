package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

import java.util.List;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public class InputEventDispatcher extends Dispatcher {

	private MyPort eventPort = null;

	
	public InputEventDispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList, 
	    MyPort eventPort) {
	  super(owner, externalHandlerList);
	  this.eventPort = eventPort;
	}
	
	public String getName() {
     return this.getEventPort().getName();
	}
	
	public MyPort getEventPort() { return this.eventPort; }
	
}
