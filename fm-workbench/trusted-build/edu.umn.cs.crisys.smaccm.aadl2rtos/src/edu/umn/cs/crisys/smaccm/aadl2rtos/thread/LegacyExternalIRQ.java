/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

/**
 * @author Whalen
 *
 */
public class LegacyExternalIRQ {
  String name;
  String handler_name;
  
  public LegacyExternalIRQ(String name, String handler_name) {
    this.name = name;
    this.handler_name = handler_name;
  }
  
  public String getName() {
    return name;
  }
  
  public String getHandlerName() {
    return handler_name;
  }
}
