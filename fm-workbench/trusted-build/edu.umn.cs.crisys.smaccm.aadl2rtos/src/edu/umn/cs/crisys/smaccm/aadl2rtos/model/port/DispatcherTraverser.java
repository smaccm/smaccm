/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import java.util.*;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.*;

/**
 * @author Whalen
 *
 * Need to traverse the thread / interface relationships.
 * What do we need to record? 
 *  - really, we *do not care* about destinations; we care about *sources*
 *  - in fact, we only care about *dispatchers*.
 */


public class DispatcherTraverser {

  /* 
  public class VisitedDispatcher {
    public ;
    public InputEventPort targetPort;
    
    public VisitedElement(ThreadImplementation source, 
                          ThreadImplementation target,
                          InputEventPort targetPort) {
      this.source = source;
      this.target = target;
      this.targetPort = targetPort;
    }
    
    public boolean equals(Object other) {
      if (other instanceof VisitedElement) {
        VisitedElement vo = (VisitedElement)other;
        return (target == vo.target && targetPort == vo.targetPort);
      }
      else 
        return false;
    }
  }
  */
  
  public DispatcherTraverser() {}
  
  private boolean allowRecursion = false;
  
  private void traversePassiveDispatchersInternal(Set<DispatchableInputPort> visited, Deque<DispatchableInputPort> path, DispatchableInputPort d, String indent) {
    
    // System.out.println(indent + "Visiting dispatcher: " + d.getOwner().getName() + "." + d.getName()); 
    if (path.contains(d) && !allowRecursion) {
      throw new Aadl2RtosException("AADL2RTOS: Error: circular reference between dispatchers");
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
  // the 'init' dispatcher that lead to active threads.
  //
  ////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * finds the connections from all passive dispatch paths referenced from  
   * the 'init' dispatcher that lead to active threads.  The init argument 
   * specifies the dispatcher from which to start the search, and the 'frontier'
   * is the set in which to place the frontier nodes. 
   * 
   * @param  init  the initial dispatcher for traversal
   * @param  frontier the set containing the result of the traversal.
   */
  private void dispatcherActiveThreadConnectionFrontier(DispatchableInputPort init, Set<PortConnection> frontier) {
    Set<DispatchableInputPort> visited = new HashSet<DispatchableInputPort>(); 
    visited.add(init);
    passiveDispatchersFromActiveThread(visited, init);
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
}
