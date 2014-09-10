/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Whalen
 *
 */
public abstract class OutputPort extends DataPort {
 
  public OutputPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
  }  
  
  public abstract boolean isOutputDataPort(); 
  public abstract boolean isOutputEventPort();
}
