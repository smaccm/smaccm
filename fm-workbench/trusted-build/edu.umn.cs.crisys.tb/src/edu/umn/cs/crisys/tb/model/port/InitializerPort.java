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
public class InitializerPort extends DispatchableInputPort {

  public static Type initializerPortType() { return new IntType(64, true);}
  /**
   * @param portName
   * @param dataType
   * @param owner
   */
  public InitializerPort(String portName, 
      ThreadImplementation owner) {
    super(portName, initializerPortType(), owner);
    // TODO Auto-generated constructor stub
  }

}
