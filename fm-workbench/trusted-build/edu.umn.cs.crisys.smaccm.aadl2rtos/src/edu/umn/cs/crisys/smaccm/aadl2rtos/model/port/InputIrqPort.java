/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import java.util.Map;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class InputIrqPort extends DispatchableInputPort {

<<<<<<< HEAD
  private int signalNumber;
  private String signalName;
  private String firstLevelInterruptHandler;
  private Map<String, String> memoryRegions; 

  public static final int NO_SIGNAL_NUMBER = -1;
  /**
   * @param portName
   * @param dataType
   * @param owner
   */
  public InputIrqPort(String portName, ThreadImplementation owner,
      String signalName, int signalNumber, 
      String firstLevelInterruptHandler, Map<String, String> memoryRegions) {
    super(portName, new UnitType(), owner);
    this.signalName = signalName;
    this.signalNumber = signalNumber;
    this.firstLevelInterruptHandler = firstLevelInterruptHandler;
    this.memoryRegions = memoryRegions;
  }

  // TODO: move most of this stuff into the 'name' class for Irq ports.
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
  
=======
    private int signalNumber;
    private String signalName;
    private String firstLevelInterruptHandler;
    private Map<String, String> memoryRegions;

    public static final int NO_SIGNAL_NUMBER = -1;

    /**
     * @param portName
     * @param dataType
     * @param owner
     */
    public InputIrqPort(String portName, ThreadImplementation owner, String signalName, int signalNumber,
            String firstLevelInterruptHandler, Map<String, String> memoryRegions) {
        // super(portName, new UnitType(), owner);

        super(portName, new IntType(64, true), owner);
        this.signalName = signalName;

        this.signalNumber = signalNumber;
        this.firstLevelInterruptHandler = firstLevelInterruptHandler;
        this.memoryRegions = memoryRegions;
    }

    // TODO: move most of this stuff into the 'name' class for Irq ports.
    public int getNumber() {
        return signalNumber;
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
     * @param signalName
     *            the signalName to set
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
     * @param firstLevelInterruptHandler
     *            the firstLevelInterruptHandler to set
     */
    public void setFirstLevelInterruptHandler(String firstLevelInterruptHandler) {
        this.firstLevelInterruptHandler = firstLevelInterruptHandler;
    }

    public int hashCode() {
        return 0;
    }

>>>>>>> upstream/develop
}
