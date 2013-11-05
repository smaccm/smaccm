/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Type;

/**
 * @author Whalen
 *
 */
public class InputDataPort extends Port {

  public InputDataPort(org.osate.aadl2.Port port, Type dataType, ThreadImplementation owner) {
    super(port, dataType, owner);
  }
}
