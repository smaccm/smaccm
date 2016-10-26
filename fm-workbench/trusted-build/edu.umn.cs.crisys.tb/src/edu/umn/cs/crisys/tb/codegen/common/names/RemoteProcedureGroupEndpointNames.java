/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint.AccessType;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureGroupEndpointNames implements NameEmitter {

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
  
  public NameEmitter getRemoteProcedureGroup() {
    return EmitterFactory.remoteProcedureGroup(rpge.getRemoteProcedureGroup());
  }
  
  public String getProvidesOrRequires() {
    return (rpge.getAccessType() == AccessType.PROVIDES) ? "provides" : "uses";
  }
  
}
