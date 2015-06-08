/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class InitializerPort extends DispatchableInputPort {

  /**
   * @param portName
   * @param dataType
   * @param owner
   */
  public InitializerPort(String portName, 
      ThreadImplementation owner) {
    super(portName, new UnitType(), owner);
    // TODO Auto-generated constructor stub
  }

}
