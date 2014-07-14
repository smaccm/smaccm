/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Whalen
 *
 */
public abstract class InputPort extends DataPort {
 
  public InputPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
  }    
  
  public abstract boolean isInputEventPort();  
  public abstract boolean isInputEventDataPort();
  public abstract boolean isInputDataPort(); 
  
}
