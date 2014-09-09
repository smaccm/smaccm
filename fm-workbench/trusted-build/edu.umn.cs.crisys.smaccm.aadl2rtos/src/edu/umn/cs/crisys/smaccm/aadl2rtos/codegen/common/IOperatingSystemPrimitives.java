/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstancePort;

/**
 * @author Whalen
 *
 */
public interface IOperatingSystemPrimitives {
  public String lockMutex(ThreadInstancePort tip);
  public String unlockMutex(ThreadInstancePort tip);
  public String lockMutex(SharedData sd); 
  public String unlockMutex(SharedData sd);
  public String signalDispatcherProc(ThreadInstancePort tip);
  
}
