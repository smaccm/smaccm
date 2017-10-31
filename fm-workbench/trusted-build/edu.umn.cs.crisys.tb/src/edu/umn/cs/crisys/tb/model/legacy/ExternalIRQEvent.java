/**
 * 
 */
package edu.umn.cs.crisys.tb.model.legacy;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;

/**
 * @author Whalen
 *
 */
public class ExternalIRQEvent extends ModelElementBase {
  String name; 
  String task_name;
  int sig_set; 
  
  public ExternalIRQEvent(String name, String task_name, int sig_set) {
    this.name = name;
    this.task_name = task_name;
    this.sig_set = sig_set;
  }
  
  public String getName() {
    return name;
  }
  
  public String getTaskName() {
    return task_name;
  }
  
  public int getSigSet() {
    return sig_set;
  }

@Override
public ModelElement getParent() {
   return null;
}
  
} ; 
