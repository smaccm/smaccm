/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.IRQDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

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
  
  public List<ThreadImplementationNames> getPassiveThreadImplementations() {
    return constructThreadImplList(m.getPassiveThreadImplementations());
  }

  public List<ThreadImplementationNames> getThreadImplementations() {
    return constructThreadImplList(m.getAllThreadImplementations());
  }

  public ThreadCalendarNames getThreadCalendar() {
    return new ThreadCalendarNames(m.getThreadCalendar());
  }

  public List<DispatcherNames> getIrqDispatchers() {
    List<DispatcherNames> irqs = new ArrayList<>(); 
    for (IRQDispatcher disp : m.getIRQDispatcherList()) {
      irqs.add(new DispatcherNames(disp));
    }
    return irqs;
  }
  
  ////////////////////////////////////////////////////////////
  //
  // Names for system information
  //
  ////////////////////////////////////////////////////////////

  public String getSystemImplementationName() {
    return Util.normalizeAadlName(m.getSystemImplementationName());
  }
  
  public String getSystemTypeHeaderName() {
    return "smaccm_" + getSystemImplementationName() + "_types.h";
  }

  public String getSystemAssemblyFileName() {
    return getSystemImplementationName() + "_assembly.camkes";
  }
  
  int connNumber = 0; 
  public String getGenerateConnectionName() {
    connNumber++; 
    return "conn" + connNumber;
  }

  public boolean getIsCamkesTarget() {
    return m.getOsTarget().equals(Model.OSTarget.CAmkES);
  }
  
  public boolean getIsEChronosTarget() {
    return m.getOsTarget().equals(Model.OSTarget.eChronos);
  }
  
  public boolean getIsOdroidTarget() {
    return m.getHWTarget().equalsIgnoreCase("odroid");
  }
  
  public boolean getIsQemuTarget() {
    return m.getHWTarget().equalsIgnoreCase("qemu");
  }
    
  public String getOsTarget() {
    return m.getOsTarget().toString();
  }
  
  public String getHwTarget() {
    return m.getHWTarget();
  }
  
}
