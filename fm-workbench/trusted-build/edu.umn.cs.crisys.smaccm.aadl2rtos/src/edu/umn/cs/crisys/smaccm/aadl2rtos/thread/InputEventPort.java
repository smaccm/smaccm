/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

/**
 * @author Whalen
 *
 */
public class InputEventPort extends Port {

  private String entrypointSourceText;
  private int queueSize = 1;
  
  public InputEventPort(org.osate.aadl2.Port port, Type dataType, ThreadImplementation owner) {
    super(port, dataType, owner);
    try {
      this.entrypointSourceText = Util.getStringValue(port,ThreadUtil.COMPUTE_ENTRYPOINT_SOURCE_TEXT);
      this.queueSize = (int) PropertyUtils.getIntegerValue(port, ThreadUtil.QUEUE_SIZE);
    } catch (Exception e) {
      throw new Aadl2RtosException("For input event port: " + this.getName() + 
          "either entrypointSourceText or queueSize property is not defined.  Original error: " + 
          e.toString());
    }
  }
  
  public int getQueueSize() {
    return this.queueSize;
  }
  
  public String getEntrypointSourceText() {
    return this.entrypointSourceText;
  }
  
}
