/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import java.util.List;



import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;
//import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Whalen
 *
 */
public class InputEventPort extends DataPort {

  private int queueSize = 0;
  private List<String> entrypointSourceText = null;
  
  
  public InputEventPort(String portName, 
      Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
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


  /**
   * @return the entrypointSourceText
   */
  public List<String> getEntrypointSourceText() {
    return entrypointSourceText;
  }


  /**
   * @param entrypointSourceText the entrypointSourceText to set
   */
  public void setEntrypointSourceText(List<String> entrypointSourceText) {
    this.entrypointSourceText = entrypointSourceText;
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
