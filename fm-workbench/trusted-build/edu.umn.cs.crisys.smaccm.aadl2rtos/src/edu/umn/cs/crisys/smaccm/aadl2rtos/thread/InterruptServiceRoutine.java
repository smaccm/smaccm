/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Whalen
 *
 */
public class InterruptServiceRoutine {

  String signalName;
  MyPort outputServiceRoutinePort;
  MyPort destPort;
  List<ThreadInstance> destThreads = new ArrayList<ThreadInstance>();
  
  /*
   * TODO: We need to refactor the port hierarchy so that we can create an 
   * OutputInterruptServiceRoutine port.  It is quite different from other 
   * ports.  However, in the mean time, we will "fake" an output event port with 
   * a null thread owner and a null datatype.  The 
   * 
   */
  
  public InterruptServiceRoutine(String signalName) {
    this.signalName = signalName; 
    this.outputServiceRoutinePort = new MyPort(this); 
  }
  
  public MyPort getOutputPort() {
    return this.outputServiceRoutinePort;
  }
  
  public void setDestinationPort(MyPort destPort) {
    this.destPort = destPort;
  }
  
  public MyPort getDestinationPort() {
    return this.destPort;
  }
  
  public void addThreadInstance(ThreadInstance ti) {
    this.destThreads.add(ti);
  }
  
  public List<ThreadInstance> getThreadInstances() {
    return this.destThreads;
  }
  
  public String getSignalName() {
    return signalName;
  }
  
  public String getHandlerName() {
    return "smaccm_" + getSignalName(); 
  }
  
  public String getIrqSignalName() {
    return ("smaccm_irq_" + getSignalName()).toLowerCase();
  }
  
  public String getIrqSignalSetDefine() {
    return ("SIGNAL_SET_" + this.getIrqSignalName()).toUpperCase(); 
  }
  
  public String getIrqSignalDefine() {
    return ("IRQ_EVENT_ID_" + this.getIrqSignalName()).toUpperCase();
  }
}
