/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.names;

import edu.umn.cs.crisys.tb.model.port.ExternalHandler;

public class ExternalHandlerNames {
  ExternalHandler eh; 
  ExternalHandlerNames(ExternalHandler eh) {
    this.eh = eh;
  }
  
  public String getHandlerName() {
    return eh.getHandlerName(); 
  }
}

