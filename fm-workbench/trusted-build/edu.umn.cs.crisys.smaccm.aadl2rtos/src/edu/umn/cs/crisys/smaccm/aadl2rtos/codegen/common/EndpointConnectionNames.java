/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.EndpointConnection;

/**
 * @author Whalen
 *
 */
public class EndpointConnectionNames {

  EndpointConnection conn;
  
  public EndpointConnectionNames(EndpointConnection conn) {
    this.conn = conn;
  }
  
  public RemoteProcedureGroupEndpointNames getRequiresEndpoint() {
    return new RemoteProcedureGroupEndpointNames(conn.getRequiresEndpoint());
  }
  
  public RemoteProcedureGroupEndpointNames getProvidesEndpoint() {
    return new RemoteProcedureGroupEndpointNames(conn.getProvidesEndpoint());
  }
  
  public ThreadImplementationNames getRequiresThreadImplementation() {
    return new ThreadImplementationNames(conn.getRequiresThreadInstance().getThreadImplementation());
  }
  
  public ThreadImplementationNames getProvidesThreadImplementation() {
    return new ThreadImplementationNames(conn.getProvidesThreadInstance().getThreadImplementation());
  }
}
