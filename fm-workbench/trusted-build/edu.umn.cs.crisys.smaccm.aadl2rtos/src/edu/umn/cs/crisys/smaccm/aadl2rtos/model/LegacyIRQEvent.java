/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

/**
 * @author Whalen
 *
 */
public class LegacyIRQEvent {
  String name; 
  String task_name;
  int sig_set; 
  
  public LegacyIRQEvent(String name, String task_name, int sig_set) {
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
  
} ; 
