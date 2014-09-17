/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.IOperatingSystemPrimitives;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstancePort;

/**
 * @author Whalen
 *
 */
public class CAmkESSystemPrimitives implements IOperatingSystemPrimitives {
  public String lockMutex(ThreadInstancePort tip) {
    PortNames pn = new PortNames(tip.getPort());
    return pn.getMutex() + "_lock();";
  }
  
  public String unlockMutex(ThreadInstancePort tip) {
    PortNames pn = new PortNames(tip.getPort());
    return pn.getMutex() + "_unlock();";
  }
  
  public String lockMutex(SharedData sd) {
    return "// lock for SharedData objects currently unimplemented for CAmkES; "; 
  }
  public String unlockMutex(SharedData sd) {
    return "// unlock for SharedData objects currently unimplemented for CAmkES; "; 
  }
  public String signalDispatcherProc(ThreadInstancePort tip) {
    ThreadImplementationNames tin = 
        new ThreadImplementationNames(tip.getThreadInstance().getThreadImplementation());
    return tin.getDispatcherComponentMutexName();
  }
  
}
