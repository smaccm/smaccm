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
public class InputDataPort extends InputPort {
 
  public InputDataPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
  }    
  
}
