/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import java.util.*;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.thread.*;

/**
 * @author Whalen
 *
 * Need to traverse the thread / interface relationships.
 * What do we need to record? 
 *  - in fact, we care about *dispatchers*.
 *  
 *  In the new schema, we need active thread dispatchers
 *  to all passive targets.
 *  
 *  We want {active, passive} -> active to be a direct connection to
 *  the target.
 *  
 */


public class DispatcherTraverser {

  
  public DispatcherTraverser() {}
  
  private boolean allowRecursion = false;

  private String circularityString(Deque<DispatchableInputPort> path, DispatchableInputPort d) {
     String result = "AADL2RTOS Error: circular references between dispatchers.  Path: [" + System.lineSeparator(); 
     for (DispatchableInputPort dip: path) {
        result += "   " + dip.getName() + " --> " + System.lineSeparator();
     }
     result += "   " + d.getName() + System.lineSeparator();
     result += "]; "; 
     return result;
  }
  /*
   * This function returns the set of passive dispatch input ports traverseable 
   * from an initial input port.
   * 
   */
  private void traversePassiveDispatchersInternal(Set<DispatchableInputPort> visited, Deque<DispatchableInputPort> path, DispatchableInputPort d, String indent) {
    
    // System.out.println(indent + "Visiting dispatcher: " + d.getOwner().getName() + "." + d.getName()); 
    if (path.contains(d) && !allowRecursion) {
      throw new TbException("[Trusted Build]: Error: circular reference between dispatchers");
    } 
    if (!visited.contains(d) && d.getOwner().getIsPassive()) {
      visited.add(d);
      OutgoingDispatchContract used = 
          OutgoingDispatchContract.maxUsedDispatchers(d.getDispatchLimits());
      for (Map.Entry<OutputEventPort, Integer> elem : used.getContract().entrySet()) {
        for (PortConnection pc : elem.getKey().getConnections()) {
          // should be only output event ports, so connection should be to input event ports! 
          InputEventPort iep = (InputEventPort)pc.getDestPort();
          path.push(d);
          traversePassiveDispatchersInternal(visited, path, iep, indent + "  ");
          path.pop();
        }
      }
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////
  //
  // dispatcherActiveThreadConnectionFrontier
  // 
  // This method finds the connections from all passive dispatch paths referenced from  
  // a provided 'initial' dispatcher paramter that lead to active threads.
  //
  ////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * finds the connections from all passive dispatch paths referenced from  
   * the 'initial' dispatcher that lead to active threads.  The 'initial' argument 
   * specifies the dispatcher from which to start the search, and the 'frontier'
   * is the set in which to place the frontier nodes. 
   * 
   * @param  initial  the initial dispatcher for traversal
   * @param  frontier the set containing the result of the traversal.
   */
  private void dispatcherActiveThreadConnectionFrontier(DispatchableInputPort initial, Set<PortConnection> frontier) {
    Set<DispatchableInputPort> visited = new HashSet<DispatchableInputPort>(); 
    visited.add(initial);
    passiveDispatchersFromActiveThread(visited, initial);
    for (DispatchableInputPort d : visited) {
      for (OutgoingDispatchContract limit : d.getDispatchLimits()) {
        for (Map.Entry<OutputEventPort, Integer> elem : limit.getContract().entrySet()) {
          for (PortConnection pc : elem.getKey().getConnections()) {
            InputEventPort iep = (InputEventPort)pc.getDestPort();
            if (!iep.getOwner().getIsPassive()) {
              frontier.add(pc);
            }
          }
        }
      }
    }
  }
  
  /* this method removes 'self dispatches' from the list of dispatchers. */
  
  public void dispatcherNonlocalActiveThreadConnectionFrontier(DispatchableInputPort init, Set<PortConnection> frontier) {
    dispatcherActiveThreadConnectionFrontier(init, frontier); 
    Iterator<PortConnection> setIt = frontier.iterator();
    while (setIt.hasNext()) {
      PortConnection pc = setIt.next();
      if (pc.getDestPort().getOwner() == init.getOwner() ) {
        setIt.remove();
      }
    }  
  }
  
  /* This method contains only 'self dispatches' from the active thread */
  public void dispatcherLocalActiveThreadConnectionFrontier(DispatchableInputPort init, Set<PortConnection> frontier) {
    dispatcherActiveThreadConnectionFrontier(init, frontier); 
    Iterator<PortConnection> setIt = frontier.iterator();
    while (setIt.hasNext()) {
      PortConnection pc = setIt.next();
      if (pc.getDestPort().getOwner() != init.getOwner() ) {
        setIt.remove();
      }
    }  
  }
/*
  public Set<Dispatcher> dispatcherActiveThreadFrontier(Dispatcher init, Set<Dispatcher> frontier) {
    Set<Dispatcher> visited = new HashSet<Dispatcher>(); 
    passiveDispatchersFromActiveThread(visited, init);
    for (Dispatcher d : visited) {
      for (OutgoingDispatchContract limit : d.getDispatchLimits()) {
        for (HashMap.Entry<OutputEventPort, Integer> elem : limit.getContract().entrySet()) {
          for (PortConnection pc : elem.getKey().getConnections()) {
            InputEventPort iep = (InputEventPort)pc.getDestPort();
            InputEventDispatcher ied = iep.getOptDispatcher();
            if (ied != null && 
                !iep.getOwner().getIsPassive() &&
                (init.getOwner() != iep.getOwner()))  
            {
              frontier.add(ied);
            }
          }
        }
      }
    }
    return frontier;
  }
  */
  
  public void passiveDispatchersFromActiveThread(Set<DispatchableInputPort> visited, DispatchableInputPort d) {
    if (d.getOwner().getIsPassive()) {
      visited.add(d);
    }
    
    for (OutgoingDispatchContract limit : d.getDispatchLimits()) {
      for (Map.Entry<OutputEventPort, Integer> elem : limit.getContract().entrySet()) {
        for (PortConnection pc : elem.getKey().getConnections()) {
          InputEventPort iep = (InputEventPort)pc.getDestPort();
          Deque<DispatchableInputPort> deque = new ArrayDeque<>();
          traversePassiveDispatchersInternal(visited, deque, iep, " ");
        }
      }
    }
  }
  
  public void dispatcherActiveThreadConnections(DispatchableInputPort initial, Set<PortConnection> frontier) {
     for (OutgoingDispatchContract limit : initial.getDispatchLimits()) {
        for (Map.Entry<OutputEventPort, Integer> elem : limit.getContract().entrySet()) {
          for (PortConnection pc : elem.getKey().getConnections()) {
            InputEventPort iep = (InputEventPort)pc.getDestPort();
            if (!iep.getOwner().getIsPassive()) {
            	frontier.add(pc);
            }
          }
        }
     }
  }
}
