/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

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
    return "dispatch_periodic.camkes";
  }
  
  public String getPeriodicDispatcherComponentName() {
    return "dispatch_periodic"; 
  }
  
  public String getPeriodicDispatcherInstanceName() {
    return "dispatch_periodic_inst"; 
  }
  
}
