/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public class InputPeriodicPort extends DispatchableInputPort {

  // Note: this is currently specified in millseconds; 
  // It probably should be in microseconds.
	
  private int periodInMilliseconds = 0;

  /**
   * @param portName : name of the port
   * @param dataType : type of data sent over the port
   * @param owner
   */
  public InputPeriodicPort(String portName, 
      ThreadImplementation owner) {
    super(portName, InputPeriodicPort.getPortType(), owner);

  }

  static public Type getPortType() { return new IntType(64, true); }
  
  public int getPeriod() {
    return periodInMilliseconds;
  }

  public void setPeriod(int period) {
    this.periodInMilliseconds = period;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + periodInMilliseconds;
    return result;
  }

}
