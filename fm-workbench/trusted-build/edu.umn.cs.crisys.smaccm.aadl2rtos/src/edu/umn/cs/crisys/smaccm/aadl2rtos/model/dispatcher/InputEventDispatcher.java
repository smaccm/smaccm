package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public class InputEventDispatcher extends Dispatcher {

	private InputEventPort eventPort = null;

	
	public InputEventDispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList, 
	    InputEventPort eventPort) {
	  super(owner, externalHandlerList);
	  this.eventPort = eventPort;
	}
	
	public String getName() {
     return this.getEventPort().getName();
	}
	
	// we want to return null for unit types to assist with template generation.
	public Type getType() {
	  Type dt = this.getEventPort().getDataType();
	  if (dt == null || this.getEventPort().getDataType() instanceof UnitType) {
	    return null;
	  } else {
	    return dt;
	  }
	}
	  
	public InputEventPort getEventPort() { return this.eventPort; }
	
}
