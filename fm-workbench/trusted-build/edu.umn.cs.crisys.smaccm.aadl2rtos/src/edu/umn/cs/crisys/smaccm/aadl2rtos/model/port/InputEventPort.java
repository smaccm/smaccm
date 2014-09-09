/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
//import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class InputEventPort extends InputPort {

  private int queueSize = 0;
  //private List<String> entrypointSourceText = null;
  
  
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

  public boolean isInputDataPort() { return false; }
  
  public boolean isInputEventPort() {
    return this.getType() instanceof UnitType;
  }
  
  public boolean isInputEventDataPort() {
    return !isInputEventPort();
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

  /*
  public int getPortID() {
    if (this.getOwner() != null) {
      return this.getOwner().getSignalNumberForInputEventPort(this);
    } else {
      throw new Aadl2RtosException("Port " + this.getName() + " does not have an owning thread");
    }
  }
    */
  
  
}
