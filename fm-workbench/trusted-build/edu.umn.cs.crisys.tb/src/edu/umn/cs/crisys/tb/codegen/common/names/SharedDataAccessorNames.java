/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.connection.SharedDataAccessor;

/**
 * @author Whalen
 *
 */
public class SharedDataAccessorNames implements NameEmitter {

  SharedDataAccessor sda;
  
  public SharedDataAccessorNames(SharedDataAccessor sda) {
    this.sda = sda;
  }
  
  public String getName() {
    return sda.getName();
  }

  public TypeNames getType() {
    return EmitterFactory.type(sda.getSharedData().getType());
  }
  
  public NameEmitter getSharedData() {
    return EmitterFactory.sharedData(sda.getSharedData());
  }
  
  public NameEmitter getOwner() {
    return EmitterFactory.threadImplementation(sda.getOwner());
  }
    
}
