/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.ExternalRemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.InternalRemoteProcedureGroup;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureGroupNames implements NameEmitter {
  RemoteProcedureGroup rpg;
  
  public RemoteProcedureGroupNames(RemoteProcedureGroup rpg) {
    this.rpg = rpg;
  }
  
  public String getName() {
    return rpg.getId(); 
  }
  
  public String getAadlName() {
    return "aadl_" + getName(); 
  }
  
  public String getIdlName() {
    return rpg.getIdl4FileName();
  }
  
  public boolean getIsExternal() {
     return (rpg instanceof ExternalRemoteProcedureGroup);
  }
    
  public List<NameEmitter> getRemoteProcedures() {
     if (rpg instanceof InternalRemoteProcedureGroup) {
        InternalRemoteProcedureGroup irpg = 
              (InternalRemoteProcedureGroup)rpg;
        List<NameEmitter> names = new ArrayList<>(); 
        for (RemoteProcedure rp: irpg.getProcedures()) {
          names.add(EmitterFactory.remoteProcedure(rp));
        }
        return names;
     } else {
        throw new TbException("RemoteProcedureGroupNames::getRemoteProcedures: group is externally defined.");
     }
  }
}
