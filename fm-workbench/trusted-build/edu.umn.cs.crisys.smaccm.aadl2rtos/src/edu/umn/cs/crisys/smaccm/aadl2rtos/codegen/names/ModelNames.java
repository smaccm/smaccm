/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy.ExternalIRQEvent;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy.ExternalISR;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputIrqPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ExternalIRQ;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
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
  
  static public String getEChronosPrefix() {
	  return "rtos";
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

  public List<PortNames> getIrqDispatchers() {
    List<PortNames> irqs = new ArrayList<>(); 
    for (InputIrqPort disp : m.getIRQDispatcherList()) {
      irqs.add(new PortNames(disp));
    }
    return irqs;
  }
  
  /* For internal vs. external IRQs (from eChronos' perspective) */
  public List<PortNames> getExternalIrqDispatchers() {
    List<PortNames> irqs = new ArrayList<>(); 
    for (InputIrqPort disp : m.getIRQDispatcherList()) {
      if (disp.getNumber() != InputIrqPort.NO_SIGNAL_NUMBER) {  
        irqs.add(new PortNames(disp));
      }
    }
    return irqs;
  }

  /* For internal vs. external IRQs (from eChronos' perspective) */
  public List<PortNames> getInternalIrqDispatchers() {
    List<PortNames> irqs = new ArrayList<>(); 
    for (InputIrqPort disp : m.getIRQDispatcherList()) {
      if (disp.getNumber() == InputIrqPort.NO_SIGNAL_NUMBER) {  
        irqs.add(new PortNames(disp));
      }
    }
    return irqs;
  }
  
  public List<SharedDataNames> getSharedData() {
    List<SharedDataNames> sdal = new ArrayList<>(); 
    for (SharedData sd : m.getSharedDataList()) {
      sdal.add(new SharedDataNames(sd));
    }
    return sdal;
  }
  
  ////////////////////////////////////////////////////////////
  //
  // Names for system information
  //
  ////////////////////////////////////////////////////////////

  public String getSystemImplementationName() {
    return Util.normalizeAadlName(m.getSystemImplementationName());
  }
  
  public List<String> getExternalTypeHeaders() {
    List<String> eths = new ArrayList<String>();
    for (String h: m.getExternalTypeHeaders()) {
      eths.add(h);
    }
    return eths;
  }
  
  public String getSystemTypeHeaderName() {
    return "smaccm_" + getSystemImplementationName() + "_types.h";
  }

  public String getCamkesSystemAssemblyFileName() {
    return getSystemImplementationName() + "_assembly.camkes";
  }
  
  public String getEChronosPrxFileName() {
    return getSystemImplementationName() + ".prx";
  }
  public boolean getGenerateSystickIrq() {
	  return m.getGenerateSystemTick();
  }
  
  public int getTimerRateInMS() {
     return m.getTimerRateInMS();
  }
  
  public List<ExternalIRQ> getExternalIRQs() {
		return m.getExternalIRQs();
  }

  public String getGenerateConnectionName() {
    return "conn" + m.getGenerateConnectionNumber();
  }

  public String getGenerateCamkesTimeServerThreadIndex() {
    return Integer.toString(m.getGenerateCamkesTimeServerThreadIndex());
  }

  public String getGenerateCamkesDataportRpcMinIndex() {
    return Integer.toString(m.getGenerateCamkesDataportRpcMinIndex());
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
  
  public boolean getUseOSRealTimeExtensions() {
	  return m.isUseOSRealTimeExtensions();
  }
  
  
  public List<String> getExternalMutexList() {
	return m.getExternalMutexList();
  }
  
  public List<ExternalISR> getExternalISRs() {
    return m.getExternalISRs();
  }

  public List<String> getExternalSemaphoreList() {
	  return m.getExternalSemaphoreList();
  }
  
  public List<ExternalIRQEvent> getExternalIRQEvents() {
	return m.getExternalIRQEvents();
  }

  public boolean getExternalTimerComponent() {
    return m.isExternalTimerComponent();
  }
  
  public String getCamkesExternalTimerInterfacePath() {
    return m.getCamkesExternalTimerInterfacePath();
  }
  
  public String getCamkesExternalTimerCompletePath() {
    return m.getCamkesExternalTimerCompletePath();
  }

  public boolean getCamkesUseMailboxDataports() {
	return m.getCamkesUseMailboxDataports();
  }
  
  public String getCamkesInternalTimerTimersPerClient() {
    return Integer.toString(m.getCamkesInternalTimerTimersPerClient());
  }
  
  public String getCamkesTimeServerAadlThreadMinIndex() {
    return Integer.toString(m.getCamkesTimeServerAadlThreadMinIndex());
  }
  
  public static String getEChronosDispatchSignal() {
    return "smaccm_dispatcher";
  }
  
  public boolean getEChronosGenerateCModules() {
    return m.isEChronosGenerateCModules();
  }

  public String getEChronosCModulePath() {
    return m.getEChronosCModulePath();
  }
  
  public String getEChronosFlashLoadAddress() {
	  return m.getEChronosFlashLoadAddress();
  }

  public List<String> getCFileModules() {
    List<String> toReturn = new ArrayList<>(); 
    for (String s: m.getSourceFiles()) {
      toReturn.add(Util.pathRemoveExtension(Util.fileNameFromPath(s)));
    }
    return toReturn;
  }

}
