/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Whalen
 *
 */
public class InputPeriodicPort extends DispatchableInputPort {

  private int period = 0;

  /**
   * @param portName : name of the port
   * @param dataType : type of data sent over the port
   * @param owner
   */
  public InputPeriodicPort(String portName, 
      ThreadImplementation owner) {
    super(portName, InputPeriodicPort.getPortType(), owner);

  }

  static public Type getPortType() { return new IntType(64, false); }
  
  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + period;
    return result;
  }

}
