/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureParameter;

/**
 * @author Whalen
 *
 */
public class RemoteProcedureNames implements NameEmitter {

  RemoteProcedure rp;
  
  public RemoteProcedureNames(RemoteProcedure rp) {
    this.rp = rp;
  }
  
  public String getName() {
    return rp.getId();
  }
  
  public TypeNames getReturnType() {
    return EmitterFactory.type(rp.getReturnType());
  }
  
  public List<NameEmitter> getParameters() {
    ArrayList<NameEmitter> names = new ArrayList<>(); 
    for (RemoteProcedureParameter rpp : rp.getArguments()) {
      names.add(EmitterFactory.remoteProcedureParameter(rpp));
    }
    return names;
  }
}
