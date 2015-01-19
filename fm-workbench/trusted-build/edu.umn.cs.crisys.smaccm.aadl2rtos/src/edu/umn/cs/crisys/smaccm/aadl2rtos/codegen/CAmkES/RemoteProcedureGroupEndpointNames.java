/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroupEndpoint.AccessType;

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
  
  public RemoteProcedureGroupNames getRemoteProcedureGroup() {
    return new RemoteProcedureGroupNames(rpge.getRemoteProcedureGroup());
  }
  
  public String getProvidesOrRequires() {
    return (rpge.getAccessType() == AccessType.PROVIDES) ? "provides" : "requires";
  }
  
}
