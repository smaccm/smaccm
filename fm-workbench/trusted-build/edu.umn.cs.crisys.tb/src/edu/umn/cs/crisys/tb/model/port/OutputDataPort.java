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
public class OutputDataPort extends OutputPort {
 
  public OutputDataPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
  }  
  
  public boolean isOutputDataPort() { return true; } 
  public boolean isOutputEventPort() {return false; }
}
