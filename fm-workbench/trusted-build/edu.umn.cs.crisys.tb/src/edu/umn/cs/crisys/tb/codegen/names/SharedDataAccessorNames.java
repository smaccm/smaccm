/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.names;

import edu.umn.cs.crisys.tb.model.connection.SharedDataAccessor;

/**
 * @author Whalen
 *
 */
public class SharedDataAccessorNames {

  SharedDataAccessor sda;
  
  public SharedDataAccessorNames(SharedDataAccessor sda) {
    this.sda = sda;
  }
  
  public String getName() {
    return sda.getName();
  }

  public TypeNames getType() {
    return new TypeNames(sda.getSharedData().getType());
  }
  
  public SharedDataNames getSharedData() {
    return new SharedDataNames(sda.getSharedData());
  }
  
  public ThreadImplementationNames getOwner() {
    return new ThreadImplementationNames(sda.getOwner());
  }
    
}
