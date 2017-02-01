/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osate.aadl2.Property;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterListRegistry;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitterCamkesVM;
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
import edu.umn.cs.crisys.tb.util.PropertyUtil;
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

  public boolean getUseVmBuild() {
     return !m.getVirtualMachineList().isEmpty() || 
           m.getExtendedDataMap().containsKey("Camkes_VM_Build");
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
  
  public boolean getIsx86Target() {
     return m.getHWTarget().equalsIgnoreCase("x86");
  }
  
  public boolean getIsTK1Target() {
     return m.getHWTarget().equalsIgnoreCase("TK1");
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
  
  final public static Property TB_SYS_ADD_DUMMY_ARGS_TO_VOID_FNS = Util
        .getPropertyDefinitionInWorkspace("TB_SYS::Add_Dummy_Arg_To_Void_Fns");

  public boolean addDummyArgToVoidFns() {
     return (boolean) PropertyUtils.getBooleanValue(m.getProcessorImpl(), 
           TB_SYS_ADD_DUMMY_ARGS_TO_VOID_FNS);
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
  
  public int getVmConfigNumberMin() {
     return getVmNumber();
  }

  public int getVmConfigNumberMax() {
     return getVmNumber() + 2;
  }
  
  public String getVmInstanceName() {
     return "vm" + getVmNumber(); 
  }
  
  public String getVmComponentName() {
     return "Init" + getVmNumber(); 
  }

  public ST getTemplateST(String stName) {
     STGroupFile template = Util.createTemplate("PortEmitterRPCAllEvent.stg");
     return template.getInstanceOf(stName); 
  }

  public String getVmComponentDefs() {
     String result = "";
     for (PortListEmitterCamkesVM pe: EmitterListRegistry.getVMPortListEmitters()) {
        result += pe.getCamkesAddVMComponentLevelDeclarations(m, m.getVmCrossingPorts());
     }
     return result;
  }
  
  final public static Property VM_CONFIG_FILE = Util
        .getPropertyDefinitionInWorkspace("TB_SYS::VM_Config_File");
  
  
  public String getPerVmConfigDefs() {
     String result = ""; 
     
     String configFileName = 
        Util.getStringValueOpt(m.getProcessorImpl(), VM_CONFIG_FILE);
     
     ST st; 
     if (configFileName != null) {
        File aadlDirectory = Util.getDirectory(m.getProcessorImpl());
        File srcFilePath = new File(aadlDirectory, configFileName);
        
        STGroupFile templates = new STGroupFile(srcFilePath.getAbsolutePath());
        templates.setListener(Util.getListener());
        st = templates.getInstanceOf("VmConfig");
        if (st == null) {
           throw new TbException("Error: Vm configuration file: " + srcFilePath + " not found, or does not contain a template named 'VmConfig'");
        }            
     } else {
        st = Util.createTemplate("CamkesVmConfig.stg").getInstanceOf("VmConfig");
     }
     st.add("model", this);
     result += st.render();
     for (PortListEmitterCamkesVM pe: EmitterListRegistry.getVMPortListEmitters()) {
        result += pe.getCamkesAddAssemblyFileVMConfigDeclarations(m, m.getVmCrossingPorts());
     }
     return result;
  }

  public String getPerVmConnections() {
     String result = ""; 
     for (PortListEmitterCamkesVM pe: EmitterListRegistry.getVMPortListEmitters()) {
        result += pe.getCamkesAddAssemblyFileVMCompositionDeclarations(m, m.getVmCrossingPorts());
     }
     return result;
     
  }

  public ModelNames getParent() {
     if (m.getParent() != null) {
        return EmitterFactory.model((OSModel)m.getParent());
     } 
     else return null;
  }
  public void getAddVMLinuxFiles(File linuxDirectory) {
     for (PortListEmitterCamkesVM pe: EmitterListRegistry.getVMPortListEmitters()) {
        pe.getAddLinuxVMFiles(m, m.getVmCrossingPorts(), linuxDirectory);
     }
  }

  public void getAddVMComponentFiles(File componentDirectory) {
     for (PortListEmitterCamkesVM pe: EmitterListRegistry.getVMPortListEmitters()) {
        pe.getAddVMComponentFiles(m, m.getVmCrossingPorts(), componentDirectory);
     }
  }

  /* How to do the linux side?  Well, depends on who is calling. */
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
  
  public List<PortEmitter> getVmCrossingPorts() {
     List<PortEmitter> elemList = new ArrayList<>(); 
     for (PortFeature pc: m.getVmCrossingPorts()) {
        elemList.add(EmitterFactory.port(pc));
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
