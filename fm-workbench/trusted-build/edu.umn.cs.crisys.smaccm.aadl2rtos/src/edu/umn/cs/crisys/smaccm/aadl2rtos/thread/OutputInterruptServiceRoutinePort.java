/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Type;

/**
 * @author Whalen
 *
 */
public class OutputInterruptServiceRoutinePort extends Port {

  public OutputInterruptServiceRoutinePort(org.osate.aadl2.Port port, Type dataType, ThreadImplementation owner) {
    super(port, dataType, owner);
    
    // TODO: Check that dataType is either NULL or a scalar type.
  }
}
