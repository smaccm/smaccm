/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.ArrayType;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public class InputEventPort extends DispatchableInputPort {

  private int queueSize = 0;
  
  public InputEventPort(String portName, 
      Type dataType,
      ThreadImplementation owner,
      int queueSize) {
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


  /**
   * @param queueSize the queueSize to set
   */
  public void setQueueSize(int queueSize) {
    this.queueSize = queueSize;
  }
  

  public Type getQueueType() {
    Type ty = this.getType();
    return new ArrayType(ty, this.getQueueSize());
  }
  
  public Type getCircRefType() {
    if (this.getQueueSize() <= 256) {
      return new IntType(8, false);
    } else if (this.getQueueSize() <= 65536) {
      return new IntType(16, false);
    } else {
         return new IntType(32, false);
    }
  }

  
  
}
