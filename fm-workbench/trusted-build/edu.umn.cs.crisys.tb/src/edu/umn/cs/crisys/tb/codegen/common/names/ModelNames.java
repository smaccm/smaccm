/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterListRegistry;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitterCamkes;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.legacy.ExternalIRQEvent;
import edu.umn.cs.crisys.tb.model.legacy.ExternalISR;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
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
    return constructThreadImplList(m.getThreadImplementationList());
  }
  
  public List<ProcessImplementationNames> getProcessImplementations() {
     List<ProcessImplementationNames> names = new ArrayList<>(); 
     for (ProcessImplementation pi: m.getProcessImplementationList()) {
        names.add(EmitterFactory.processImplementation(pi));
     }
     return names;
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

  public String getPathName() {
    return Util.normalizeAadlName(m.getPathName());
  }
  
  public List<String> getExternalTypeHeaders() {
    List<String> eths = new ArrayList<String>();
    for (String h: m.getExternalTypeHeaders()) {
      eths.add(h);
    }
    return eths;
  }
  
  public String getSystemTypeHeaderName() {
    return getPrefix() + "_" + getPathName() + "_types.h";
  }

  public String getCamkesSystemAssemblyFileName() {
    return getPathName() + "_assembly.camkes";
  }
  
  public String getEChronosPrxFileName() {
    return getPathName() + ".prx";
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
  
  public List<ModelNames> getVirtualMachineList() {
     List<ModelNames> childList = new ArrayList<>(); 
     for (OSModel vm: m.getVirtualMachineList()) {
        childList.add(new ModelNames(vm));
     }
     return childList;
  }
  
  public boolean getContainsVM() {
     return !m.getVirtualMachineList().isEmpty();
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
  
  /***************************************************************
   * 
   * Camkes-specific functions
   * 
   ***************************************************************/
  
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
  
  public String getPortListEmitterAssemblyIdlImports() {
     String s = ""; 
     List<PortFeature> ports = this.m.getPortFeatureList(); 
     List<PortListEmitter> emitters = EmitterListRegistry.getPortListEmitters(); 
     for (PortListEmitter e: emitters) {
        if (e instanceof PortListEmitterCamkes) {
           PortListEmitterCamkes camkesEmitter = (PortListEmitterCamkes)e;
           s += camkesEmitter.camkesAddAssemblyIdlImports(m, ports);
        }
     }
     return s;
  }
  
  public int getVmNumber() {
     return ((OSModel)this.m.getParent()).virtualMachineList.indexOf(m);
  }
  
  public String getVmInstanceName() {
     return "vm" + getVmNumber(); 
  }
  
  public ST getTemplateST(String stName) {
     STGroupFile template = Util.createTemplate("PortEmitterRPCAllEvent.stg");
     return template.getInstanceOf(stName); 
  }

  public String getPerVmConfigDefs() {
     ST st = Util.createTemplate("CamkesVmConfig.stg").getInstanceOf("VmConfig");
     st.add("model", this);
     return st.render();
  }

  /*
  public List<PortConnectionEmitter> getHostSourceToVMDestConnections() {
     List<PortConnectionEmitter> elemList = new ArrayList<>(); 
     for (PortConnection pc: m.getHostSourceToVMDestConnections()) {
        elemList.add(EmitterFactory.portConnection(pc));
     }
     return elemList;
  }
  
  public List<PortConnectionEmitter> getHostDestToVMSourceConnections() {
     List<PortConnectionEmitter> elemList = new ArrayList<>(); 
     for (PortConnection pc: m.getHostDestToVMSourceConnections()) {
        elemList.add(EmitterFactory.portConnection(pc));
     }
     return elemList;
  }
*/
  
  public List<PortConnectionEmitter> getVmCrossingConnections() {
     List<PortConnectionEmitter> elemList = new ArrayList<>(); 
     for (PortConnection pc: m.getVmCrossingConnections()) {
        elemList.add(EmitterFactory.portConnection(pc));
     }
     return elemList;
  }
  
  /***************************************************************
   * 
   * EChronos-specific functions
   * 
   ***************************************************************/
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
