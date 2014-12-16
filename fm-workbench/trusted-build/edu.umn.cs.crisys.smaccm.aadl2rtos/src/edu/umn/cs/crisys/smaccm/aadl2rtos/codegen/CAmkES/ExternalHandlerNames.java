/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.ExternalHandler;

public class ExternalHandlerNames {
  ExternalHandler eh; 
  ExternalHandlerNames(ExternalHandler eh) {
    this.eh = eh;
  }
  
  public String getHandlerName() {
    return eh.getHandlerName(); 
  }
}

