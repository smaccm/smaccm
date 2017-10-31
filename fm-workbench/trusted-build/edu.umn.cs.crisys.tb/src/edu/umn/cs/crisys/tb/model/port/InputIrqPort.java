/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import java.util.Map;

import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class InputIrqPort extends DispatchableInputPort {

    private int signalNumber;
    private String signalName;
    private String firstLevelInterruptHandler;
    private Map<String, String> memoryRegions;
    private static boolean useTowerProtocol = false;

    public static final int NO_SIGNAL_NUMBER = -1;

    /**
     * @param portName
     * @param dataType
     * @param owner
     */
    public InputIrqPort(String portName, ThreadImplementation owner, String signalName, int signalNumber,
            String firstLevelInterruptHandler, Map<String, String> memoryRegions) {
        // super(portName, new UnitType(), owner);

        super(portName, useTowerProtocol ? new IntType(64, true) : new UnitType(), owner);
        this.signalName = signalName;

        this.signalNumber = signalNumber;
        this.firstLevelInterruptHandler = firstLevelInterruptHandler;
        this.memoryRegions = memoryRegions;
    }

    public int getNumber() {
        return signalNumber;
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

}
