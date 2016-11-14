/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.legacy.ExternalIRQEvent;
import edu.umn.cs.crisys.tb.model.legacy.ExternalISR;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.port.ExternalIRQ;
import edu.umn.cs.crisys.tb.model.port.InputIrqPort;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

/**
 * @author Whalen
 *
 */
public class ModelNames implements NameEmitter {
  OSModel m;
  
  public ModelNames(OSModel m) {
    this.m = m; 
  }
  
  static public String getEChronosPrefix() {
	  return "rtos";
  }
  
  public static String getPrefix() {
	  return OSModel.getPrefix();
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
      for (PortFeature dp : ti.getOutputEventDataPortList()) {
        srTypes.add(dp.getType());
      }
      for (PortFeature dp : ti.getInputEventDataPortList()) {
        srTypes.add(dp.getType());
      }
    }
    return srTypes;
  }
  
  public List<NameEmitter> getActiveThreadSendReceiveTypeList() {
    List<NameEmitter> tl = new ArrayList<NameEmitter>();
    for (Type t: getActiveThreadSRTypes()) {
      tl.add(EmitterFactory.type(t));
    }
    return tl;
  }

  List<NameEmitter> constructThreadImplList(List<ThreadImplementation> threadList) {
    List<NameEmitter> tl = new ArrayList<>();
    for (ThreadImplementation t: threadList) {
      tl.add(EmitterFactory.threadImplementation(t));
    }
    return tl;    
  }
  
  public List<NameEmitter> getActiveThreadImplementations() {
    return constructThreadImplList(m.getActiveThreadImplementations());
  }
  
  public List<NameEmitter> getPassiveThreadImplementations() {
    return constructThreadImplList(m.getPassiveThreadImplementations());
  }

  public List<NameEmitter> getThreadImplementations() {
    return constructThreadImplList(m.getAllThreadImplementations());
  }

  public ThreadCalendarNames getThreadCalendar() {
    return EmitterFactory.threadCalendar(m.getThreadCalendar());
  }

  public List<PortEmitter> getIrqDispatchers() {
    List<PortEmitter> irqs = new ArrayList<>(); 
    for (InputIrqPort disp : m.getIRQDispatcherList()) {
      irqs.add(EmitterFactory.port(disp));
    }
    return irqs;
  }
  
  /* For internal vs. external IRQs (from eChronos' perspective) */
  public List<PortEmitter> getExternalIrqDispatchers() {
    List<PortEmitter> irqs = new ArrayList<>(); 
    for (InputIrqPort disp : m.getIRQDispatcherList()) {
      if (disp.getNumber() != InputIrqPort.NO_SIGNAL_NUMBER) {  
        irqs.add(EmitterFactory.port(disp));
      }
    }
    return irqs;
  }

  /* For internal vs. external IRQs (from eChronos' perspective) */
  public List<PortEmitter> getInternalIrqDispatchers() {
    List<PortEmitter> irqs = new ArrayList<>(); 
    for (InputIrqPort disp : m.getIRQDispatcherList()) {
      if (disp.getNumber() == InputIrqPort.NO_SIGNAL_NUMBER) {  
        irqs.add(EmitterFactory.port(disp));
      }
    }
    return irqs;
  }
  
  public List<NameEmitter> getSharedData() {
    List<NameEmitter> sdal = new ArrayList<>(); 
    for (SharedData sd : m.getSharedDataList()) {
      sdal.add(EmitterFactory.sharedData(sd));
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
    return getPrefix() + "_" + getSystemImplementationName() + "_types.h";
  }

  public String getCamkesSystemAssemblyFileName() {
    return getSystemImplementationName() + "_assembly.camkes";
  }
  
  public String getEChronosPrxFileName() {
    return getSystemImplementationName() + ".prx";
  }
  public boolean getGenerateSystickIrq() {
	  return m.getGenerateSystickIRQ();
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
    return m.getOsTarget().equals(OSModel.OSTarget.CAmkES);
  }
  
  public boolean getIsEChronosTarget() {
    return m.getOsTarget().equals(OSModel.OSTarget.eChronos);
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
    return getPrefix() + "_" + "dispatcher";
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
