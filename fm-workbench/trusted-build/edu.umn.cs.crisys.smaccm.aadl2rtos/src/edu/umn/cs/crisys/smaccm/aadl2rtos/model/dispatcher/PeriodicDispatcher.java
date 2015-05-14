package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

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
	  return "periodic_" + Integer.toString(this.getPeriod()) + "_ms"; 
	}
	
	static public Type getPeriodicDispatcherType() {
    return new IntType(64, false);
	}
	
	public Type getType() {
	  return getPeriodicDispatcherType();
	}
	
	public String getFullName() {
     return "periodic_dispatcher (" + Integer.toString(this.getPeriod()) + " ms)";
	}


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + period;
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PeriodicDispatcher other = (PeriodicDispatcher) obj;
    if (period != other.period)
      return false;
    return true;
  }
	
	
}
