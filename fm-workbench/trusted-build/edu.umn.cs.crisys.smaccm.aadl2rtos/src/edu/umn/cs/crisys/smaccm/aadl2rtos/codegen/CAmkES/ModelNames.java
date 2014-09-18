/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;

/**
 * @author Whalen
 *
 */
public class ModelNames {
  Model m;
  
  public ModelNames(Model m) {
    this.m = m; 
  }
  
  ////////////////////////////////////////////////////////////
  //
  // Constructors for child information
  //
  ////////////////////////////////////////////////////////////

  Set<Type> getActiveThreadSRTypes() {
    Set<Type> srTypes = new HashSet<Type>(); 
    srTypes.add(new UnitType());
    for (ThreadImplementation ti: m.getActiveThreadImplementations()) {
      for (DataPort dp : ti.getOutputEventDataPortList()) {
        srTypes.add(dp.getType());
      }
      for (DataPort dp : ti.getInputEventDataPortList()) {
        srTypes.add(dp.getType());
      }
    }
    return srTypes;
  }
  
  public List<TypeNames> getActiveThreadSendReceiveTypeList() {
    List<TypeNames> tl = new ArrayList<TypeNames>();
    for (Type t: getActiveThreadSRTypes()) {
      tl.add(new TypeNames(t));
    }
    return tl;
  }

  List<ThreadImplementationNames> constructThreadImplList(List<ThreadImplementation> threadList) {
    List<ThreadImplementationNames> tl = new ArrayList<>();
    for (ThreadImplementation t: threadList) {
      tl.add(new ThreadImplementationNames(t));
    }
    return tl;    
  }
  
  public List<ThreadImplementationNames> getActiveThreadImplementations() {
    return constructThreadImplList(m.getActiveThreadImplementations());
  }
  
  public List<ThreadImplementationNames> getThreadImplementations() {
    return constructThreadImplList(m.getThreadImplementations());
  }

  public ThreadCalendarNames getThreadCalendar() {
    return new ThreadCalendarNames(m.getThreadCalendar());
  }

  ////////////////////////////////////////////////////////////
  //
  // Names for system information
  //
  ////////////////////////////////////////////////////////////

  public String getSystemTypeHeaderName() {
    return "smaccm_" + m.getSystemInstanceName() + "_types.h";
  }

  public String getSystemAssemblyFileName() {
    return "smaccm_" + m.getSystemInstanceName() + "_assembly.camkes";
  }
  
  int connNumber = 0; 
  public String getGenerateConnectionName() {
    connNumber++; 
    return "conn" + connNumber;
  }
}
