/**
 * 
 */
package edu.umn.cs.crisys.tb.model.port;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class InitializerPort extends DispatchableInputPort {

   
   public static Type towerInitializerPortType() { return new IntType(64, true);}
   public static Type normalPortType() { return new UnitType(); }
   
   // public static Type initializerPortType() { return new UnitType();}
  /**
   * @param portName
   * @param dataType
   * @param owner
   */
  public InitializerPort(String portName, 
      ThreadImplementation owner) {
    super(portName, EmitterFactory.model(owner.getModel()).addDummyArgToVoidFns()? towerInitializerPortType() : normalPortType(), owner);
  }

}
