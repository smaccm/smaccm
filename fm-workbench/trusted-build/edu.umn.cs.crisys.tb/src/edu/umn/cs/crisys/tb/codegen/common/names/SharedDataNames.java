/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.connection.SharedDataAccessor;

/**
 * @author Whalen
 *
 */
public class SharedDataNames implements NameEmitter {
  
  SharedData sd ; 
  
  public SharedDataNames(SharedData sd) {
    this.sd = sd;
  }
  
  public String getVarName() {
    return sd.getVarName();
  }
  
  public TypeNames getType() {
    return EmitterFactory.type(sd.getType());
  }
  
  public List<NameEmitter> getNonChosenAccessList() {
    List<NameEmitter> sdal = new ArrayList<>(); 
    for (SharedDataAccessor elem : sd.getAccessors()) {
      if (elem.getOwner() != sd.getCamkesOwner()) {
        sdal.add(EmitterFactory.sharedDataAccessor(elem));
      }
    }
    return sdal;
  }
  
  public NameEmitter getChosenAccess() {
    for (SharedDataAccessor sda : sd.getAccessors()) {
      if (sda.getOwner() == sd.getCamkesOwner()) {
        return EmitterFactory.sharedDataAccessor(sda);
      }
    }
    throw new TbException("Shared data element [" + sd.getVarName() + "] is not accessed by CAmkES owner thread [" + sd.getCamkesOwner().getName() + "]"); 
  }
}
