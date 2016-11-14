/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.TreeSet;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.linux.LinuxUtil;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.EndpointConnection;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.connection.SharedDataAccessor;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author Whalen
 *
 */
public class ThreadImplementationNames implements NameEmitter {
  ThreadImplementation ti;
  
  public ThreadImplementationNames(ThreadImplementation ti) {
    this.ti = ti;
  }

  //////////////////////////////////////////////////////////
  //
  // Name creation for children.
  // 
  //////////////////////////////////////////////////////////
  
  
  // MWW TODO: temporary; once we create a periodic port, this 
  // should go away.
  
  public List<PortEmitter> getPeriodicDispatchers() {
    ArrayList<PortEmitter> dnList = new ArrayList<>();
    for (PortFeature p : ti.getPortList()) {
      if (p instanceof InputPeriodicPort) {
          dnList.add(EmitterFactory.port(p));
      }
    }
    return dnList;
  }
  
  // includes passive thread dispatchers invoked by this component.
  public List<NameEmitter> getPassiveDispatcherRegion() {
    ArrayList<NameEmitter> dnList = new ArrayList<>();
    for (InputPort d : ti.getPassiveDispatcherRegion()) {
      dnList.add(EmitterFactory.port(d));
    }
    return dnList;
  }
  
  public List<NameEmitter> getPassiveThreadRegion() {
    Set<ThreadImplementation> threads = ti.getPassiveThreadRegion();
    List<NameEmitter> threadNames = new ArrayList<>();
    for (ThreadImplementation ti: threads) {
      threadNames.add(EmitterFactory.threadImplementation(ti));
    }
    return threadNames;
  }
  
  public String getPeriodInMicroseconds() {
	  return Integer.toString(ti.getPeriodInMicroseconds());
  }
  
  public String getMaxExecutionTimeInMicroseconds() {
	  return Integer.toString(ti.getMaxExecutionTimeInMicroseconds());
  }
  
  public List<NameEmitter> getNonlocalActiveThreadConnectionFrontier() {
    ArrayList<NameEmitter> dnList = new ArrayList<>();
    for (PortConnection d : ti.getNonlocalActiveThreadConnectionFrontier()) {
      dnList.add(EmitterFactory.portConnection(d));
    }
    return dnList;
  }

  public List<NameEmitter> getActiveThreadConnectionList() {
	    ArrayList<NameEmitter> dnList = new ArrayList<>();
	    for (PortConnection d : ti.getActiveThreadConnectionList()) {
	      dnList.add(EmitterFactory.portConnection(d));
	    }
	    return dnList;
	  }
  
  public List<NameEmitter> getLocalActiveThreadConnectionFrontier() {
    ArrayList<NameEmitter> dnList = new ArrayList<>();
    for (PortConnection d : ti.getLocalActiveThreadConnectionFrontier()) {
      dnList.add(EmitterFactory.portConnection(d));
    }
    return dnList;
  }

  public List<NameEmitter> getEndpoints() {
    ArrayList<NameEmitter> endpoints = new ArrayList<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getRequiresRPGSet()) {
      endpoints.add(EmitterFactory.remoteProcedureGroupEndpoint(rpge));
    }
    for (RemoteProcedureGroupEndpoint rpge : ti.getProvidesRPGSet()) {
      endpoints.add(EmitterFactory.remoteProcedureGroupEndpoint(rpge));
    }
    return endpoints;
  }
  
  public List<NameEmitter> getProvidedEndpoints() {
    ArrayList<NameEmitter> endpoints = new ArrayList<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getProvidesRPGSet()) {
      endpoints.add(EmitterFactory.remoteProcedureGroupEndpoint(rpge));
    }
    return endpoints;
  }

  public List<NameEmitter> getRequiredEndpoints() {
    ArrayList<NameEmitter> endpoints = new ArrayList<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getRequiresRPGSet()) {
      endpoints.add(EmitterFactory.remoteProcedureGroupEndpoint(rpge));
    }
    return endpoints;
  }
  
  public Collection<NameEmitter> getUsedRpgs() {
    Map<String, NameEmitter> rpgMap = new HashMap<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getRequiresRPGSet()) {
      rpgMap.put(rpge.getRemoteProcedureGroup().getId(), 
          EmitterFactory.remoteProcedureGroup(rpge.getRemoteProcedureGroup()));
    }
    for (RemoteProcedureGroupEndpoint rpge : ti.getProvidesRPGSet()) {
      rpgMap.put(rpge.getRemoteProcedureGroup().getId(), 
          EmitterFactory.remoteProcedureGroup(rpge.getRemoteProcedureGroup()));
    }
    return rpgMap.values();
  }

  public <U extends PortFeature> List<PortEmitter> constructPortNames(List<U> ports) {
    ArrayList<PortEmitter> pnList = new ArrayList<>(); 
    for (PortFeature d : ports) {
      pnList.add(EmitterFactory.port(d)); 
    }
    return pnList;
  }
  
  public List<PortEmitter> getDispatchers() {
    return constructPortNames(ti.getDispatcherList());
  }
  
  public List<PortEmitter> getDispatchersWithEntrypoints() {
	  List<DispatchableInputPort> l = ti.getDispatcherList();
	  l = l.stream().
			filter(e -> !e.getExternalHandlerList().isEmpty()).
			collect(Collectors.toList());
	  return constructPortNames(l);
  }
  
  public List<PortEmitter> getAllOutputEventPorts() {
    return constructPortNames(ti.getAllOutputEventPorts());
  }
  
  public List<PortEmitter> getInputDataPortList() {
    return constructPortNames(ti.getInputDataPortList());
  }

  public List<PortEmitter> getInputEventPortList() {
    return constructPortNames(ti.getInputEventPortList());
  }

  public List<PortEmitter> getInputEventDataPortList() {
    return constructPortNames(ti.getInputEventDataPortList());
  }

  
  public List<PortEmitter> getOutputDataPortList() {
    return constructPortNames(ti.getOutputDataPortList());
  }
  
  public List<PortEmitter> getInputPortList() {
    return constructPortNames(ti.getInputPorts());
  }
  
  public List<PortEmitter> getOutputPortList() {
    return constructPortNames(ti.getOutputPorts());
  }
  
  public List<PortEmitter> getPortList() {
    return constructPortNames(ti.getPortList());
  }

  public NameEmitter getModel() {
    return EmitterFactory.model(ti.getModel());
  }
  
  public boolean getContainsDispatchers() {
    
    return !(ti.getDispatcherList().isEmpty());
  }

  
  public List<NameEmitter> getSharedDataAccessorList() {
    List<NameEmitter> sn = new ArrayList<>();
    for (SharedDataAccessor i : ti.getSharedDataAccessors()) {
      sn.add(EmitterFactory.sharedDataAccessor(i));
    }
    return sn;
  }
  
  public List<NameEmitter> getOtherThreadImplementations() {
    List<NameEmitter> others = new ArrayList<>(); 
    for (ThreadImplementation t: ti.getModel().getAllThreadImplementations()) {
      if (t != ti) {
        others.add(EmitterFactory.threadImplementation(t));
      }
    }
    return others;
  }

  public List<NameEmitter> getOtherActiveThreadImplementations() {
    List<NameEmitter> others = new ArrayList<>(); 
    for (ThreadImplementation t: ti.getModel().getActiveThreadImplementations()) {
      if (t != ti) {
        others.add(EmitterFactory.threadImplementation(t));
      }
    }
    return others;
  }

  ThreadInstance getThreadInstance() {
    List<ThreadInstance> tii = ti.getThreadInstanceList();
    if (tii.size() != 1) {
      throw new TbException("Currently multiple thread instances per implementation are not supported.");
    }
    return tii.get(0);
  }
  
  public List<NameEmitter> getIsSrcOfConnectionList() {
    List<NameEmitter> cl = new ArrayList<>(); 
    ThreadInstance tii = getThreadInstance(); 
    for (PortConnection t: tii.getIsSrcOfConnectionList()) {
      cl.add(EmitterFactory.portConnection(t));
    }
    return cl;
  }

  public List<NameEmitter> getIsRequiresOfConnectionList() {
    List<NameEmitter> cl = new ArrayList<>(); 
    ThreadInstance tii = getThreadInstance(); 
    for (EndpointConnection t: tii.getIsRequiresOfEndpointConnectionList()) {
      cl.add(EmitterFactory.endpointConnection(t));
    }
    return cl;
  }
  
  public List<TypeNames> getThreadPortTypes() {
    Set<Type> usedTypes = new HashSet<>(); 
    for (PortFeature p : ti.getPortList()) {
      usedTypes.add(p.getType());
    }
    List<TypeNames> tn = new ArrayList<>(); 
    for (Type t : usedTypes) {
      tn.add(new TypeNames(t));
    }
    tn.sort(Comparator.comparing(TypeNames::getName));
    return tn;
  }
  
  public String getPrefix() {
	  return OSModel.getPrefix(); 
  }
  
  public String getThreadDispatcherMutex() {
	  return getPrefix() + "_" + ti.getNormalizedName() + "_dispatcher_mtx";
  }
  
  public String getEChronosThreadDispatcherMutexConst() {
	  return (ModelNames.getEChronosPrefix() + "_MUTEX_ID_" + this.getThreadDispatcherMutex()).toUpperCase();
  }

  public String getEChronosTaskIdConst() {
	  return (ModelNames.getEChronosPrefix() + "_TASK_ID_" + this.getNormalizedName()).toUpperCase();
  }

  public String getGeneratedEntrypoint() {
	    return getPrefix() + "_" + ti.getGeneratedEntrypoint();
  }

  
  public String getEChronosDispatchSignal() {
    return ModelNames.getEChronosDispatchSignal(); 
  }
  
  public String getEChronosDispatchSignalConst() {
    return (ModelNames.getEChronosPrefix() + "_SIGNAL_ID_" + this.getEChronosDispatchSignal()).toUpperCase();
  }
  public String getEChronosInterruptSignal() {
    return this.getNormalizedName() + "_dispatch_interrupt";
  }
  
  public String getEChronosInterruptSignalConst() {
    return (ModelNames.getEChronosPrefix() + "_INTERRUPT_EVENT_ID_" + this.getEChronosInterruptSignal()).toUpperCase();
  }

  public List<String> getExternalMutexes() {
    return ti.getExternalMutexList();
  }
  
  public List<String> getExternalSemaphores() {
    return ti.getExternalSemaphoreList();
  }
  
  //////////////////////////////////////////////////////////
  //
  // query functions
  // 
  //////////////////////////////////////////////////////////

  public boolean getIsActive() {
    return !(ti.getIsPassive());
  }
  
  public boolean getIsPassive() {
    return ti.getIsPassive();
  }
  
  public boolean getIsExternal() {
    return ti.getIsExternal();
  }
  
  public boolean getRequiresTimeServices() {
    return ti.getRequiresTimeServices();
  }

  //////////////////////////////////////////////////////////
  //
  // 'root' name
  // 
  //////////////////////////////////////////////////////////

  public String getNormalizedName() {
    return ti.getNormalizedName();
  }
  
  public String getOsSpecificStackSize() {
	  if (ti.getModel().getOsTarget() == OSModel.OSTarget.eChronos) {
		  // TODO: MWW temporary (2/17/2016): eChronos apparently measures stack size in 32 bit words. 
		    return Integer.toString(ti.getStackSize() / 4 + ((ti.getStackSize() % 4 == 0) ? 0 : 1)); 
	  }
	  return Integer.toString(ti.getStackSize());
  }
  
  public String getPriority() {
	  return Integer.toString(ti.getPriority());
  }
  
  //////////////////////////////////////////////////////////
  //
  // Lock and unlocks for dispatcher component
  // 
  //////////////////////////////////////////////////////////
  
  public String getCamkesDispatcherLockStmt() {
    return getDispatcherComponentSemaphoreName() + "_wait();\n";
  }
  
  public String getCamkesDispatcherUnlockStmt() {
    return getDispatcherComponentSemaphoreName() + "_post();\n";
  }

  //////////////////////////////////////////////////////////
  //
  // Component / interface names
  // 
  //////////////////////////////////////////////////////////

  public String getIdlName() {
    return getNormalizedName() + "_interface";
  }
  
  public String getIdlFileName() {
    return getIdlName() + ".idl4";
  }    

  public String getIdlDispatchHeaderFileName() {
    return getIdlName() + "_dispatch_types.h";
  }    

  public String getDispatcherComponentName() {
    return "dispatch_" + getNormalizedName();
  }    
  
  public String getDispatcherComponentInstanceName() {
    return getDispatcherComponentName() + "_inst";
  }    

  public boolean getHasInitializeEntrypoint() {
    return ti.getInitializeEntrypointOpt() != null;
  }
  
  public PortEmitter getInitializeEntrypointOpt() {
    return EmitterFactory.port(ti.getInitializeEntrypointOpt()); 
  }
  
  public String getComponentName() {
    return ti.getNormalizedName();
  }    
  
  public String getDispatcherComponentSemaphoreName() {
    return getPrefix() + "_dispatch_sem"; 
  }
  
  public String getInterfaceInstanceName() {
    return ti.getNormalizedName() + "_inst";
  }

  public String getComponentInstanceName() {
    return ti.getNormalizedName() + "_inst";
  }

  public String getComponentDispatcherInterfaceVarIdName() {
    return "dispatch";
  }
  
  public String getTimeServicesConnectionName() {
    return ti.getNormalizedName() + "_time_services"; 
  }
  
  //////////////////////////////////////////////////////////
  //
  // Function names
  // 
  //////////////////////////////////////////////////////////

  public String getThreadImplMainFnName() {
    return getGeneratedEntrypoint();
  }

  
  //////////////////////////////////////////////////////////
  //
  // File names / path names.
  // 
  //////////////////////////////////////////////////////////

  public String getComponentCamkesFileName() {
    return getComponentName() + ".camkes";
  }
  
  public String getRootToCamkesComponentFilePath() {
    return "components/" + getNormalizedName() + "/" + 
        getComponentCamkesFileName();
  }

  public String getRootToCamkesDispatcherComponentFilePath() {
    return "components/" + getDispatcherComponentName() + "/" + 
        getDispatcherComponentCamkesFileName(); 
  }
  
  public String getDispatcherComponentCamkesFileName() {
    return getDispatcherComponentName() + ".camkes";
  }    

  public String getComponentGlueCodeHFileName() {
    return getPrefix() + "_" + getNormalizedName() + ".h";
  }    

  public String getComponentCamkesHFileName() {
    return getNormalizedName() + ".h";
  }    

  public String getDispatcherComponentCamkesHFileName() {
    return getDispatcherComponentName() + ".h";
  }    

  public String getComponentGlueCodeCFileName() {
    return getPrefix() + "_" + getNormalizedName() + ".c";
  }    

  public String getDispatcherComponentGlueCodeCFileName() {
    return getPrefix() + "_dispatch_" + ti.getNormalizedName() + ".c";
  }    

  /*****************************************************
   * 
   * Added to remove an OS-dependency in ports
   * 
   *****************************************************/
  
  public String getDispatcherMainLockReleaseStmt() {
     OSModel model = this.ti.getModel();
     if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
        return this.getCamkesDispatcherUnlockStmt();
     } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
        return "rtos_signal_send(" + 
              this.getEChronosTaskIdConst() + ", " + 
              this.getEChronosDispatchSignalConst() + ");";
     } else if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
        return "semGive(" + 
              this.getThreadDispatcherMutex() + "); ";
     } else if (model.getOsTarget() == OSModel.OSTarget.linux) {
        return LinuxUtil.postSem(this.getThreadDispatcherMutex()); 
     } else {
        throw new TbException(
           "ThreadImplementation::getDispatcherMainLockReleaseStmt: OS [" + 
            model.getOsTarget() + "] not recognized"); 
     }
  }
  
}
