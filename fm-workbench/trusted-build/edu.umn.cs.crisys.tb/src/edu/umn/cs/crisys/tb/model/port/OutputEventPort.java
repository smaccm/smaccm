/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class OutputEventPort extends OutputPort {
 
  private int queueSize;
  
  public OutputEventPort(String portName, 
      Type dataType,
      ThreadImplementation owner,int queueSize) {
    super(portName, dataType, owner);
    this.queueSize = queueSize;
  // this.entrypointSourceText = entrypointSourceText; 
}

/**
 * @return the queueSize
 */
public int getQueueSize() {
  return queueSize;
}


  
  public boolean isOutputDataPort() { return false; } 
  public boolean isOutputEventPort() {return true; }
  public boolean hasData() {
    return !(this.getType() instanceof UnitType);
  }
}
