/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;

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

  public String getReaderFnName(String tName, String varName) {
    if (sda.getCommPrimFnNameOpt() != null) {
      return sda.getCommPrimFnNameOpt();
    } else {
      ThreadImplementationNames tin = new ThreadImplementationNames(sda.getOwner());
      return tin.getNormalizedName() + "_read_" + getName();
    }
  }

  public String getWriterFnName(String tName, String varName) {
    if (sda.getCommPrimFnNameOpt() != null) {
      return sda.getCommPrimFnNameOpt();
    } else {
      ThreadImplementationNames tin = new ThreadImplementationNames(sda.getOwner());
      return tin.getNormalizedName() + "_write_" + getName();
    }
  }

  public boolean getAccessContainsInput() {
    return (sda.getAccessType() == SharedDataAccessor.AccessType.READ ||
            sda.getAccessType() == SharedDataAccessor.AccessType.READ_WRITE);
  }
  
  public boolean getAccessContainsOutput() {
    return (sda.getAccessType() == SharedDataAccessor.AccessType.WRITE ||
        sda.getAccessType() == SharedDataAccessor.AccessType.READ_WRITE);
  }
  
  public TypeNames getType() {
    return new TypeNames(sda.getSharedData().getType());
  }
  
  public SharedDataNames getSharedData() {
    return new SharedDataNames(sda.getSharedData());
  }
  
  public boolean hasCommPrimHeader() {
    return sda.getCommPrimHeaderNameOpt() != null;
  }
  
  public String getCommPrimHeaderName() {
    if (sda.getCommPrimHeaderNameOpt() == null) {
      throw new Aadl2RtosException("No CommPrimHeader to return in getCommPrimHeaderName");
    }
    return sda.getCommPrimHeaderNameOpt();
  }
  
}
