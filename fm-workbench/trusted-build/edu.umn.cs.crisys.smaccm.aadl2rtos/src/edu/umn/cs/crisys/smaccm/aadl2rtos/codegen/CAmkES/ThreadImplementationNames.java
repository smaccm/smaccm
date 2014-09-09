/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;

/**
 * @author Whalen
 *
 */
public class ThreadImplementationNames {
  ThreadImplementation ti;
  
  public ThreadImplementationNames(ThreadImplementation ti) {
    this.ti = ti;
  }
  
  public String getDispatcherLockStmt() {
    return Names.getDispatcherComponentMutexName(ti) + "_lock();\n";
  }
  
  public String getDispatcherUnlockStmt() {
    return Names.getDispatcherComponentMutexName(ti) + "_unlock();\n";
  }
  
  public List<DispatcherNames> getDispatchers() {
    ArrayList<DispatcherNames> dnList = new ArrayList<>();
    for (Dispatcher d : ti.getDispatcherList()) {
      dnList.add(new DispatcherNames(d));
    }
    return dnList;
  }
}
