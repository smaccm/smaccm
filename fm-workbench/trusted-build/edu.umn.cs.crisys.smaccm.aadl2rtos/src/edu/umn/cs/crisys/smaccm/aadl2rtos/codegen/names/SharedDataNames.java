/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;

/**
 * @author Whalen
 *
 */
public class SharedDataNames {
  
  SharedData sd ; 
  
  public SharedDataNames(SharedData sd) {
    this.sd = sd;
  }
  
  public String getVarName() {
    return sd.getVarName();
  }
  
  public TypeNames getType() {
    return new TypeNames(sd.getType());
  }
  
  public List<SharedDataAccessorNames> getNonChosenAccessList() {
    List<SharedDataAccessorNames> sdal = new ArrayList<>(); 
    if (sd.getAccessors().isEmpty()) {
      throw new Aadl2RtosException("ERROR: Shared Data element: " + sd.getVarName() +" in AADL model has no accessors."); 
    }
    Iterator<SharedDataAccessor> it = sd.getAccessors().iterator();
    // skip the first element.
    it.next();
    while (it.hasNext()) {
      SharedDataAccessor elem = it.next(); 
      sdal.add(new SharedDataAccessorNames(elem));
    }
    return sdal;
  }
  
  public SharedDataAccessorNames getChosenAccess() {
    if (sd.getAccessors().isEmpty()) {
      throw new Aadl2RtosException("ERROR: Shared Data element: " + sd.getVarName() +" in AADL model has no accessors."); 
    }
    Iterator<SharedDataAccessor> it = sd.getAccessors().iterator();
    return new SharedDataAccessorNames(it.next());
  }
}
