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
    return Names.getReaderWriterMutexName(tip.getPort()) + "_lock();";
  }
  
  public String unlockMutex(ThreadInstancePort tip) {
    return Names.getReaderWriterMutexName(tip.getPort()) + "_unlock();";
  }
  
  public String lockMutex(SharedData sd) {
    return "// lock for SharedData objects currently unimplemented for CAmkES; "; 
  }
  public String unlockMutex(SharedData sd) {
    return "// unlock for SharedData objects currently unimplemented for CAmkES; "; 
  }
  public String signalDispatcherProc(ThreadInstancePort tip) {
    return Names.getDispatcherComponentMutexName(tip.getThreadInstance().getThreadImplementation());
  }
  
}
