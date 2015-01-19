/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureParameter;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureNames {

  RemoteProcedure rp;
  
  public RemoteProcedureNames(RemoteProcedure rp) {
    this.rp = rp;
  }
  
  public String getName() {
    return rp.getId();
  }
  
  public TypeNames getReturnType() {
    return new TypeNames(rp.getReturnType());
  }
  
  public List<RemoteProcedureParameterNames> getParameters() {
    ArrayList<RemoteProcedureParameterNames> names = new ArrayList<>(); 
    for (RemoteProcedureParameter rpp : rp.getArguments()) {
      names.add(new RemoteProcedureParameterNames(rpp));
    }
    return names;
  }
}
