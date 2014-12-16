/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.Connection;

/**
 * @author Whalen
 *
 */
public class ConnectionNames {

  Connection c;
  
  public ConnectionNames(Connection c) {
    this.c = c;
  }
  
  public PortNames getSourcePort() {
    return new PortNames(c.getSourcePort());
  }
  
  public PortNames getDestPort() {
    return new PortNames(c.getDestPort());
  }
  
  public ThreadImplementationNames getDestPortThreadImplementation() {
    return new ThreadImplementationNames(c.getDestThreadInstance().getThreadImplementation());
  }
  
  public ThreadImplementationNames getSourcePortThreadImplementation() {
    return new ThreadImplementationNames(c.getSourceThreadInstance().getThreadImplementation());
  }
}
