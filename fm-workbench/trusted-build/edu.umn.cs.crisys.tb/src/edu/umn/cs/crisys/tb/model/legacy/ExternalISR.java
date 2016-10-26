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
public class ExternalISR extends ModelElementBase {
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

@Override
public ModelElement getParent() {
   return null;
}
}
