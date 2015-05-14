/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.PeriodicDispatcher;
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

  public List<DispatcherNames> getPeriodicDispatchers() {
    List<DispatcherNames> dl = new ArrayList<>();
    for (PeriodicDispatcher d: c.getPeriodicDispatchers()) {
      dl.add(new DispatcherNames(d));
    }
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
  
  public String getPeriodicDispatcherCamkesHFileName() {
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
  
  public String getPriority() {
    return "50";
  }
  
}
