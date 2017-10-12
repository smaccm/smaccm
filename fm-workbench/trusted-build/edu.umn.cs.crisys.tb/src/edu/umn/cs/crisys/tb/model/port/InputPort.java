/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public abstract class InputPort extends PortFeature {
   
  public InputPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
  }

}
