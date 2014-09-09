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
public class InputDataPort extends InputPort {
 
  public InputDataPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
  }    
  
  public boolean isInputDataPort() { return true; }
  public boolean isInputEventPort() { return false; }
  public boolean isInputEventDataPort() { return false; }
  
}
