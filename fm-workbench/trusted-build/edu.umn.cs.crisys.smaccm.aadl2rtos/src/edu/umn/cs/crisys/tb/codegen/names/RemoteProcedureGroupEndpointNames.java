/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.names;

import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint.AccessType;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureGroupEndpointNames {

  RemoteProcedureGroupEndpoint rpge;
  
  public RemoteProcedureGroupEndpointNames(RemoteProcedureGroupEndpoint rpge) {
    this.rpge = rpge;
  }
  
  public String getName() {
    return rpge.getName(); 
  }
    
  public String getAadlName() {
    return "aadl_" + rpge.getName(); 
  }
  
  public RemoteProcedureGroupNames getRemoteProcedureGroup() {
    return new RemoteProcedureGroupNames(rpge.getRemoteProcedureGroup());
  }
  
  public String getProvidesOrRequires() {
    return (rpge.getAccessType() == AccessType.PROVIDES) ? "provides" : "uses";
  }
  
}
