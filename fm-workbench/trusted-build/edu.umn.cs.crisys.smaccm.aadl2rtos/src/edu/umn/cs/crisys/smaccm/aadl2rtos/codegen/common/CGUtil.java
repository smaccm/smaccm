/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.OutgoingDispatchContract;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;

/**
 * @author Whalen
 *
 */
public class CGUtil {

  public static OutgoingDispatchContract maxDispatcherUse(List<OutgoingDispatchContract> dl, List<OutputEventPort> oeps) {
    OutgoingDispatchContract max = new OutgoingDispatchContract(oeps);
    for (OutgoingDispatchContract d: dl) {
      max.mergeLargest(d);
    }
    return max;
  }
  
}
