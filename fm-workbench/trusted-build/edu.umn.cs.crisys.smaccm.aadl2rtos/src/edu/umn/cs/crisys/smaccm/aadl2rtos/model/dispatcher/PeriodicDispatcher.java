package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public class PeriodicDispatcher extends Dispatcher {

	private int period = 0;
	
	public PeriodicDispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList, int period) {
	  super(owner, externalHandlerList);
		this.period = period;
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
     return "periodic_dispatcher (" + Integer.toString(this.getPeriod()) + " ms)";
	}
}
