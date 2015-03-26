/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;

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
}
