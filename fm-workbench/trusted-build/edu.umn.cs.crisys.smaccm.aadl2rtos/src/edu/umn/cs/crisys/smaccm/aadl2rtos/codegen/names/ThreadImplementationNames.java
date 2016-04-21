/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.EndpointConnection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.PortConnection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;

/**
 * @author Whalen
 *
 */
public class ThreadImplementationNames {
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
  
/*  public List<DispatcherNames> getPeriodicDispatchers() {
    ArrayList<DispatcherNames> dnList = new ArrayList<>();
    for (Dispatcher d : ti.getDispatcherList()) {
      if (d instanceof PeriodicDispatcher) {
          dnList.add(new DispatcherNames(d));
      }
    }
    return dnList;
  }
  */
  
  // includes passive thread dispatchers invoked by this component.
  public List<PortNames> getPassiveDispatcherRegion() {
    ArrayList<PortNames> dnList = new ArrayList<>();
    for (InputPort d : ti.getPassiveDispatcherRegion()) {
      dnList.add(new PortNames(d));
    }
    return dnList;
  }
  
  public List<ThreadImplementationNames> getPassiveThreadRegion() {
    Set<ThreadImplementation> threads = ti.getPassiveThreadRegion();
    List<ThreadImplementationNames> threadNames = new ArrayList<>();
    for (ThreadImplementation ti: threads) {
      threadNames.add(new ThreadImplementationNames(ti));
    }
    return threadNames;
  }
  
  public String getPeriodInMicroseconds() {
	  return Integer.toString(ti.getPeriodInMicroseconds());
  }
  
  public String getMaxExecutionTimeInMicroseconds() {
	  return Integer.toString(ti.getMaxExecutionTimeInMicroseconds());
  }
  
  public List<PortConnectionNames> getNonlocalActiveThreadConnectionFrontier() {
    ArrayList<PortConnectionNames> dnList = new ArrayList<>();
    for (PortConnection d : ti.getNonlocalActiveThreadConnectionFrontier()) {
      dnList.add(new PortConnectionNames(d));
    }
    return dnList;
  }

  public List<PortConnectionNames> getActiveThreadConnectionList() {
	    ArrayList<PortConnectionNames> dnList = new ArrayList<>();
	    for (PortConnection d : ti.getActiveThreadConnectionList()) {
	      dnList.add(new PortConnectionNames(d));
	    }
	    return dnList;
	  }
  
  public List<PortConnectionNames> getLocalActiveThreadConnectionFrontier() {
    ArrayList<PortConnectionNames> dnList = new ArrayList<>();
    for (PortConnection d : ti.getLocalActiveThreadConnectionFrontier()) {
      dnList.add(new PortConnectionNames(d));
    }
    return dnList;
  }

  public List<RemoteProcedureGroupEndpointNames> getEndpoints() {
    ArrayList<RemoteProcedureGroupEndpointNames> endpoints = new ArrayList<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getRequiresRPGSet()) {
      endpoints.add(new RemoteProcedureGroupEndpointNames(rpge));
    }
    for (RemoteProcedureGroupEndpoint rpge : ti.getProvidesRPGSet()) {
      endpoints.add(new RemoteProcedureGroupEndpointNames(rpge));
    }
    return endpoints;
  }
  
  public List<RemoteProcedureGroupEndpointNames> getProvidedEndpoints() {
    ArrayList<RemoteProcedureGroupEndpointNames> endpoints = new ArrayList<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getProvidesRPGSet()) {
      endpoints.add(new RemoteProcedureGroupEndpointNames(rpge));
    }
    return endpoints;
  }

  public List<RemoteProcedureGroupEndpointNames> getRequiredEndpoints() {
    ArrayList<RemoteProcedureGroupEndpointNames> endpoints = new ArrayList<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getRequiresRPGSet()) {
      endpoints.add(new RemoteProcedureGroupEndpointNames(rpge));
    }
    return endpoints;
  }
  
  public Collection<RemoteProcedureGroupNames> getUsedRpgs() {
    Map<String, RemoteProcedureGroupNames> rpgMap = new HashMap<>();
    for (RemoteProcedureGroupEndpoint rpge : ti.getRequiresRPGSet()) {
      rpgMap.put(rpge.getRemoteProcedureGroup().getId(), 
          new RemoteProcedureGroupNames(rpge.getRemoteProcedureGroup()));
    }
    for (RemoteProcedureGroupEndpoint rpge : ti.getProvidesRPGSet()) {
      rpgMap.put(rpge.getRemoteProcedureGroup().getId(), 
          new RemoteProcedureGroupNames(rpge.getRemoteProcedureGroup()));
    }
    return rpgMap.values();
  }

  public <U extends DataPort> List<PortNames> constructPortNames(List<U> ports) {
    ArrayList<PortNames> pnList = new ArrayList<>(); 
    for (DataPort d : ports) {
      pnList.add(new PortNames(d)); 
    }
    return pnList;
  }
  
  public List<PortNames> getDispatchers() {
    return constructPortNames(ti.getDispatcherList());
  }
  
  public List<PortNames> getAllOutputEventPorts() {
    return constructPortNames(ti.getAllOutputEventPorts());
  }
  
  public List<PortNames> getInputDataPortList() {
    return constructPortNames(ti.getInputDataPortList());
  }

  public List<PortNames> getInputEventPortList() {
    return constructPortNames(ti.getInputEventPortList());
  }

  public List<PortNames> getInputEventDataPortList() {
    return constructPortNames(ti.getInputEventDataPortList());
  }

  
  public List<PortNames> getOutputDataPortList() {
    return constructPortNames(ti.getOutputDataPortList());
  }
  
  public List<PortNames> getInputPortList() {
    return constructPortNames(ti.getInputPorts());
  }
  
  public List<PortNames> getOutputPortList() {
    return constructPortNames(ti.getOutputPorts());
  }
  
  public List<PortNames> getPortList() {
    return constructPortNames(ti.getPortList());
  }

  public ModelNames getModel() {
    return new ModelNames(ti.getModel());
  }
  
  public boolean getContainsDispatchers() {
    
    return !(ti.getDispatcherList().isEmpty());
  }

  
  public List<SharedDataAccessorNames> getSharedDataAccessorList() {
    List<SharedDataAccessorNames> sn = new ArrayList<>();
    for (SharedDataAccessor i : ti.getSharedDataAccessors()) {
      sn.add(new SharedDataAccessorNames(i));
    }
    return sn;
  }
  
  public List<ThreadImplementationNames> getOtherThreadImplementations() {
    List<ThreadImplementationNames> others = new ArrayList<>(); 
    for (ThreadImplementation t: ti.getModel().getAllThreadImplementations()) {
      if (t != ti) {
        others.add(new ThreadImplementationNames(t));
      }
    }
    return others;
  }

  public List<ThreadImplementationNames> getOtherActiveThreadImplementations() {
    List<ThreadImplementationNames> others = new ArrayList<>(); 
    for (ThreadImplementation t: ti.getModel().getActiveThreadImplementations()) {
      if (t != ti) {
        others.add(new ThreadImplementationNames(t));
      }
    }
    return others;
  }

  ThreadInstance getThreadInstance() {
    List<ThreadInstance> tii = ti.getThreadInstanceList();
    if (tii.size() != 1) {
      throw new Aadl2RtosException("Currently multiple thread instances per implementation are not supported.");
    }
    return tii.get(0);
  }
  
  public List<PortConnectionNames> getIsSrcOfConnectionList() {
    List<PortConnectionNames> cl = new ArrayList<>(); 
    ThreadInstance tii = getThreadInstance(); 
    for (PortConnection t: tii.getIsSrcOfConnectionList()) {
      cl.add(new PortConnectionNames(t));
    }
    return cl;
  }

  public List<EndpointConnectionNames> getIsRequiresOfConnectionList() {
    List<EndpointConnectionNames> cl = new ArrayList<>(); 
    ThreadInstance tii = getThreadInstance(); 
    for (EndpointConnection t: tii.getIsRequiresOfEndpointConnectionList()) {
      cl.add(new EndpointConnectionNames(t));
    }
    return cl;
  }
  
  public List<TypeNames> getThreadPortTypes() {
    Set<Type> usedTypes = new HashSet<>(); 
    for (DataPort p : ti.getPortList()) {
      usedTypes.add(p.getType());
    }
    List<TypeNames> tn = new ArrayList<>(); 
    for (Type t : usedTypes) {
      tn.add(new TypeNames(t));
    }
    return tn;
  }
  
  public String getEChronosThreadDispatcherMutex() {
	  return "smaccm_" + ti.getNormalizedName() + "_dispatcher_mtx";
  }
  
  public String getEChronosThreadDispatcherMutexConst() {
	  return (ModelNames.getEChronosPrefix() + "_MUTEX_ID_" + this.getEChronosThreadDispatcherMutex()).toUpperCase();
  }

  public String getEChronosTaskIdConst() {
	  return (ModelNames.getEChronosPrefix() + "_TASK_ID_" + this.getNormalizedName()).toUpperCase();
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
	  if (ti.getModel().getOsTarget() == Model.OSTarget.eChronos) {
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
  
  public PortNames getInitializeEntrypointOpt() {
    return new PortNames(ti.getInitializeEntrypointOpt()); 
  }
  
  public String getComponentName() {
    return ti.getNormalizedName();
  }    
  
  public String getDispatcherComponentSemaphoreName() {
    return "smaccm_dispatch_sem"; 
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
    return ti.getGeneratedEntrypoint();
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
    return "smaccm_" + getNormalizedName() + ".h";
  }    

  public String getComponentCamkesHFileName() {
    return getNormalizedName() + ".h";
  }    

  public String getDispatcherComponentCamkesHFileName() {
    return getDispatcherComponentName() + ".h";
  }    

  public String getComponentGlueCodeCFileName() {
    return "smaccm_" + getNormalizedName() + ".c";
  }    

  public String getDispatcherComponentGlueCodeCFileName() {
    return "smaccm_dispatch_" + ti.getNormalizedName() + ".c";
  }    

}
