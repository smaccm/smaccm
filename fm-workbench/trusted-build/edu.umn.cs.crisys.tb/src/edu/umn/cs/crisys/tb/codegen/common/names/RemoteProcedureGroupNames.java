/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroup;

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
    return getName() + ".idl4";
  }
  
  public List<NameEmitter> getRemoteProcedures() {
    List<NameEmitter> names = new ArrayList<>(); 
    for (RemoteProcedure rp: rpg.getProcedures()) {
      names.add(EmitterFactory.remoteProcedure(rp));
    }
    return names;
  }
}
