/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.connection.EndpointConnection;

/**
 * @author Whalen
 *
 */
public class EndpointConnectionNames implements NameEmitter {

  EndpointConnection conn;
  
  public EndpointConnectionNames(EndpointConnection conn) {
    this.conn = conn;
  }
  
  public NameEmitter getRequiresEndpoint() {
    return EmitterFactory.remoteProcedureGroupEndpoint(conn.getRequiresEndpoint());
  }
  
  public NameEmitter getProvidesEndpoint() {
    return EmitterFactory.remoteProcedureGroupEndpoint(conn.getProvidesEndpoint());
  }
  
  public NameEmitter getRequiresThreadImplementation() {
    return EmitterFactory.threadImplementation(conn.getRequiresThreadInstance().getThreadImplementation()); 
  }
  
  public NameEmitter getProvidesThreadImplementation() {
    return EmitterFactory.threadImplementation(conn.getProvidesThreadInstance().getThreadImplementation());
  }
  
  public String getConnector() {
     if (conn.containsExtendedData("CamkesCustomConnector")) {
        return (String)conn.getExtendedData("CamkesCustomConnector");
     } else {
        return "seL4RPCCall";
     }
  }

}
