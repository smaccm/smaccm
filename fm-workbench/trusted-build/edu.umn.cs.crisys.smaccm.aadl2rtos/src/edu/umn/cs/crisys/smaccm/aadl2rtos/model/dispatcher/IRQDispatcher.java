package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public class IRQDispatcher extends Dispatcher {

  private int signalNumber;
  private String signalName;
  private String firstLevelInterruptHandler;
  private Map<String, String> memoryRegions; 
	
	public IRQDispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList, 
	    String signalName, int signalNumber, 
	    String firstLevelInterruptHandler, Map<String, String> memoryRegions) {
	  super(owner, externalHandlerList);
	  this.signalName = signalName;
	  this.signalNumber = signalNumber;
	  this.firstLevelInterruptHandler = firstLevelInterruptHandler;
	  this.memoryRegions = memoryRegions;
	}
	
	public String getName() {
	  return signalName;
	}

	public int getNumber() {
	  return signalNumber;
	}
	
	public Type getType() { 
	  return new UnitType(); 
	}
	
  public String getEChronosIrqSmaccmFlihName() {
    return ("smaccm_irq_" + getSignalName()).toLowerCase();
  }
  
  public String getEChronosIrqSignalSetDefine() {
    return ("SIGNAL_SET_" + this.getEChronosIrqSmaccmFlihName()).toUpperCase(); 
  }
  
  public String getEChronosIrqSignalDefine() {
    return ("IRQ_EVENT_ID_" + this.getEChronosIrqSmaccmFlihName()).toUpperCase();
  }
	
  public Map<String, String> getMemoryRegions() {
    return memoryRegions;
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
	
  public int hashCode() {
    return 0;
  }
}
