/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.ExternalHandler;

public class ExternalHandlerNames {
  ExternalHandler eh; 
  ExternalHandlerNames(ExternalHandler eh) {
    this.eh = eh;
  }
  
  public String getHandlerName() {
    return eh.getHandlerName(); 
  }
}

