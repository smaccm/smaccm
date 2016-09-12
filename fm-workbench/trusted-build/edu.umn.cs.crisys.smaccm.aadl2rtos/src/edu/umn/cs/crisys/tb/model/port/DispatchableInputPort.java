/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public class DispatchableInputPort extends InputPort {

  private List<ExternalHandler> externalHandlerList = new ArrayList<ExternalHandler>();
  private String optSendsEventsToString = null;

  // Outgoing dispatch contract (limits on dispatch)
  private ArrayList<OutgoingDispatchContract> dispatchLimits = new ArrayList<OutgoingDispatchContract>();

  
  /**
   * @param portName
   * @param dataType
   * @param owner
   */
  public DispatchableInputPort(String portName, Type dataType,
      ThreadImplementation owner) {
    super(portName, dataType, owner);
    // TODO Auto-generated constructor stub
  }

  public List<ExternalHandler> getExternalHandlerList() {
    return externalHandlerList;
  }

  public boolean getHasHandler() {
	  return !getExternalHandlerList().isEmpty();
  }
  
  public void setExternalHandlerList(List<ExternalHandler> externalHandlerList) {
    this.externalHandlerList = externalHandlerList;
  }

  public String getOptSendsEventsToString() {
    return optSendsEventsToString;
  }

  public void setOptSendsEventsToString(String optSendsEventsToString) {
    this.optSendsEventsToString = optSendsEventsToString;
  }

  public ArrayList<OutgoingDispatchContract> getDispatchLimits() {
    return dispatchLimits;
  }

  public void setDispatchLimits(ArrayList<OutgoingDispatchContract> dispatchLimits) {
    this.dispatchLimits = dispatchLimits;
  }

  
}
