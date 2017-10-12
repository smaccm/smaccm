/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.thread.ThreadCalendar;

/**
 * @author Whalen
 *
 */
public class ThreadCalendarNames implements NameEmitter {

  ThreadCalendar c;
  
  public ThreadCalendarNames(ThreadCalendar c) {
    this.c = c;
  }
  
  public String getPrefix() {
	  return c.getModel().getPrefix();
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

  public List<PortEmitter> getPeriodicDispatchers() {
    List<PortEmitter> dl = new ArrayList<>();
    for (InputPeriodicPort d: c.getPeriodicDispatchers()) {
      dl.add(EmitterFactory.port(d));
    }
    dl.sort(Comparator.comparing(PortEmitter::getQualifiedName));
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
    return getPrefix() + "_thread_calendar";
  }
  
  public String getSystickInitializerFnName() {
    return getPrefix() + "_initialize_px4_systick_interrupt";
  }

  public String getPeriodicDispatcherPathName() {
    return "components/"+ getPeriodicDispatcherComponentName() + "/" + getPeriodicDispatcherCamkesFileName();
  }

  public String getPeriodicDispatcherCamkesFileName() {
    return getPeriodicDispatcherComponentName() + ".camkes";
  }
  
  public String getPeriodicDispatcherCFileName() {
    return getPrefix() + "_" + getPeriodicDispatcherComponentName() + ".c";
  }
  
  public String getPeriodicDispatcherHFileName() {
    return "camkes.h";
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
