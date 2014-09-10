/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.OutgoingDispatchContract;

/**
 * @author Whalen
 *
 */
public class CGUtil {

  public static OutgoingDispatchContract maxDispatcherUse(List<OutgoingDispatchContract> dl) {
    OutgoingDispatchContract max = new OutgoingDispatchContract();
    for (OutgoingDispatchContract d: dl) {
      max.mergeLargest(d);
    }
    return max;
  }
  
}
