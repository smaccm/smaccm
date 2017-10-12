/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

/**
 * @author Whalen
 *
 */
public class ExternalIRQ {
  String name; 
  int irq_id; 
  
  public ExternalIRQ(String name, int irq_id) {
    this.name = name;
    this.irq_id = irq_id;
  }
  
  public String getName() {
    return name;
  }
  
  public int getIrqId() {
    return irq_id;
  }
    
} ; 
