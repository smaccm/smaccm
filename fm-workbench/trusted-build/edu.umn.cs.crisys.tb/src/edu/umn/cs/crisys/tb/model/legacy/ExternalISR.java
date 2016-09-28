/**
 * 
 */
package edu.umn.cs.crisys.tb.model.legacy;

/**
 * @author Whalen
 *
 */
public class ExternalISR {
  String name;
  String handler_name;
  
  public ExternalISR(String name, String handler_name) {
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
