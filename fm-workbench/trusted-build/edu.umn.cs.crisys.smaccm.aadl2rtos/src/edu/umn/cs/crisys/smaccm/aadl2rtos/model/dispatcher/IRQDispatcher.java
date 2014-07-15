package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public class IRQDispatcher extends Dispatcher {

  private String signalName;
  private String firstLevelInterruptHandler;
	
	public IRQDispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList, 
	    String signalName, String firstLevelInterruptHandler) {
	  super(owner, externalHandlerList);
	  this.signalName = signalName;
	  this.firstLevelInterruptHandler = firstLevelInterruptHandler;
	}
	
	public String getName() {
	  return signalName;
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
	
  /**
   * @return the signalName
   */
  public String getSignalName() {
    return signalName;
  }

  /**
   * @param signalName the signalName to set
   */
  public void setSignalName(String signalName) {
    this.signalName = signalName;
  }

  /**
   * @return the firstLevelInterruptHandler
   */
  public String getFirstLevelInterruptHandler() {
    return firstLevelInterruptHandler;
  }

  public String getFirstLevelInterruptHandlerWrapper() {
    return "smaccm_" + this.getFirstLevelInterruptHandler();
  }
  /**
   * @param firstLevelInterruptHandler the firstLevelInterruptHandler to set
   */
  public void setFirstLevelInterruptHandler(String firstLevelInterruptHandler) {
    this.firstLevelInterruptHandler = firstLevelInterruptHandler;
  }
	
}
