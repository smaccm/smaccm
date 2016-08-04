/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.PortConnection;

/**
 * @author Whalen
 *
 */
public class PortConnectionNames {

  PortConnection c;
  
  public PortConnectionNames(PortConnection c) {
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
  
  public String getName() {
	  if (getSourcePort().getConnections().size() > 1)
		  return getSourcePort().getQualifiedName() + "_" + getDestPort().getQualifiedName();
	  else
		  return getSourcePort().getQualifiedName();
  }
}
