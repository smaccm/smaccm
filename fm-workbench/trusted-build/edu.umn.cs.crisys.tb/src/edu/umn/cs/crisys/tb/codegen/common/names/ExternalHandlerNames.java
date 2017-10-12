/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;

public class ExternalHandlerNames implements NameEmitter {
  ExternalHandler eh; 
  
  public ExternalHandlerNames(ExternalHandler eh) {
    this.eh = eh;
  }
  
  public String getHandlerName() {
    return eh.getHandlerName(); 
  }
}

