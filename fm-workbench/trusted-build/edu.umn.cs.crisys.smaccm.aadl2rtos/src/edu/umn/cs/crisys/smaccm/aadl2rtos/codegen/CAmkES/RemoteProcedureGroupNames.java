/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroup;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureGroupNames {
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
  
  public List<RemoteProcedureNames> getRemoteProcedures() {
    List<RemoteProcedureNames> names = new ArrayList<>(); 
    for (RemoteProcedure rp: rpg.getProcedures()) {
      names.add(new RemoteProcedureNames(rp));
    }
    return names;
  }
}
