package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public class Dispatcher {

	private List<ExternalHandler> externalHandlerList;
	private int period = 0;
	private MyPort eventPort = null;
	private ThreadImplementation owner;
	private DispatcherType dispatcherType;

	public enum DispatcherType {
		    INPUT_PORT_DISPATCHER, 
		    PERIODIC_DISPATCHER
	}
	
	public Dispatcher(ThreadImplementation owner, int period, List<ExternalHandler> externalHandlerList) {
		dispatcherType = DispatcherType.PERIODIC_DISPATCHER;
		this.owner = owner;
		this.period = period;
		this.externalHandlerList = externalHandlerList;
	}
	
	public Dispatcher(ThreadImplementation owner, MyPort eventPort, ArrayList<ExternalHandler> externalHandlerList) {
	  dispatcherType = DispatcherType.INPUT_PORT_DISPATCHER;
	  this.owner = owner;
	  this.eventPort = eventPort;
	  this.externalHandlerList = externalHandlerList;
	}

	public String getPeriodicIrqSignalName() {
      String irqSignalName = ("smaccm_periodic_dispatch_" + this.getOwner().getName()).toLowerCase();
      return irqSignalName;
	}

	public String getPeriodicIrqSignalDefine() {
	  return ("IRQ_EVENT_ID_" + this.getPeriodicIrqSignalName()).toUpperCase(); 
	}

	public int getPeriod() { return this.period; }

	public String getName() {
     return (this.getDispatcherType() == DispatcherType.INPUT_PORT_DISPATCHER) ? 
            this.getEventPort().getName() : 
            "periodic_dispatcher (" + Integer.toString(this.getPeriod()) + " ms)";
	}
	
	public MyPort getEventPort() { return this.eventPort; }
	
	public DispatcherType getDispatcherType() {
	  return this.dispatcherType;
	}
	
	public List<ExternalHandler> getExternalHandlerList() {
	  return this.externalHandlerList;
	}
	
	public ThreadImplementation getOwner() {
	  return this.owner;
	}
}
