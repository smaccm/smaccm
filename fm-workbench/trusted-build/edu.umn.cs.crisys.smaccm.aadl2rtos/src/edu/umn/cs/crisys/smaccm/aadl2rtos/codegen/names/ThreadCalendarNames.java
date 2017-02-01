/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadCalendar;

/**
 * @author Whalen
 *
 */
public class ThreadCalendarNames {

  ThreadCalendar c;
  
  public ThreadCalendarNames(ThreadCalendar c) {
    this.c = c;
  }
  
  ////////////////////////////////////////////////////////////
  //
  // Query Functions
  //
  ////////////////////////////////////////////////////////////
  
  public boolean getHasDispatchers() {
    return c.hasDispatchers();
  }

  ////////////////////////////////////////////////////////////
  //
  // Functions for returning child elements
  //
  ////////////////////////////////////////////////////////////

  public List<PortNames> getPeriodicDispatchers() {
    List<PortNames> dl = new ArrayList<>();
    for (InputPeriodicPort d: c.getPeriodicDispatchers()) {
      dl.add(new PortNames(d));
    }
    dl.sort(Comparator.comparing(PortNames::getQualifiedName));
    return dl;
  }

  ////////////////////////////////////////////////////////////
  //
  // Names for periodic dispatcher-related information
  // Note: probably should go in its own component 
  //  (ThreadCalendarNames.java?)
  //
  ////////////////////////////////////////////////////////////

  public String getCalendarFnName() {
    return "smaccm_thread_calendar";
  }
  
  public String getSystickInitializerFnName() {
    return "smaccm_initialize_px4_systick_interrupt";
  }

  public String getPeriodicDispatcherPathName() {
    return "components/"+ getPeriodicDispatcherComponentName() + "/" + getPeriodicDispatcherCamkesFileName();
  }

  public String getPeriodicDispatcherCamkesFileName() {
    return getPeriodicDispatcherComponentName() + ".camkes";
  }
  
  public String getPeriodicDispatcherCFileName() {
    return "smaccm_" + getPeriodicDispatcherComponentName() + ".c";
  }
  
  public String getPeriodicDispatcherHFileName() {
    return getPeriodicDispatcherComponentName() + ".h";
  }

  public String getPeriodicDispatcherComponentName() {
    return "dispatch_periodic"; 
  }
  
  public String getPeriodicDispatcherInstanceName() {
    return "dispatch_periodic_inst"; 
  }

  public String getHyperperiodSubdivisions() {
    return Integer.toString(c.getHyperperiodSubdivisions());
  }
  
  public String getGreatestCommonDivisorInMilliseconds() {
    return Integer.toString(c.getGreatestCommonDivisorInMilliseconds());
  }
  
  public String getHzTickRate() { 
	  if (c.hasFixedTickRate()) {
	     c.checkFixedTickRateForPeriods();
	     return Integer.toString(c.fixedTickRateInHz()); 
	  }
     // HACK for QEMU (simulator) target for VxWorks
	  else if (c.getModel().getHWTarget().equalsIgnoreCase("QEMU")) {
		  return Integer.toString(100);
	  } else {
		  return Integer.toString(1000);
	  }
  }
  
  public String getMsPerTick() {
     if (c.hasFixedTickRate()) {
        c.checkFixedTickRateForPeriods();
        return Integer.toString(c.getFixedTickRateInMS()); 
     }
	  // HACK for QEMU (simulator) target for VxWorks
	  if (c.getModel().getHWTarget().equalsIgnoreCase("QEMU")) {
		 return Integer.toString(10);  
	  } else {
		  return Integer.toString(1); 
	  }
  }
  
  public String getPriority() {
    return "150";
  }
  
}
