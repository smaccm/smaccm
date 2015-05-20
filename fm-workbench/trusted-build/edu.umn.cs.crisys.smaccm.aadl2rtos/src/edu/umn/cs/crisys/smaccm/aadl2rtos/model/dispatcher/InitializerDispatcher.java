package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Mead, Whalen
 * 
 * TODO: I want separate subclasses for event and periodic dispatchers.
 * 
 */
public class InitializerDispatcher extends Dispatcher {

	
	public InitializerDispatcher(ThreadImplementation owner, List<ExternalHandler> externalHandlerList) {
	  super(owner, externalHandlerList);
	  setImplementationFileList(null); 
	}
	

	public String getName() {
	  return (this.getOwner().getNormalizedName() + "_initializer");
	}
	
	
	public Type getType() {
	  return new UnitType();
	}
	
	public String getFullName() {
     return "initializer_dispatcher";
	}


  @Override
  public int hashCode() {
    int result = 31;
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PeriodicDispatcher other = (PeriodicDispatcher) obj;
    return (this.getOwner() == other.getOwner());
  }
	
	
}
