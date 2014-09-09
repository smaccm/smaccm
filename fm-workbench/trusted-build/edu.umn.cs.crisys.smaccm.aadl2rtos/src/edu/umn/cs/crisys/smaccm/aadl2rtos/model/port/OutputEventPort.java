/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class OutputEventPort extends OutputPort {
 
  public OutputEventPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
  }  
  
  public boolean isOutputDataPort() { return false; } 
  public boolean isOutputEventPort() {return true; }
  public boolean hasData() {
    return !(this.getType() instanceof UnitType);
  }
}
