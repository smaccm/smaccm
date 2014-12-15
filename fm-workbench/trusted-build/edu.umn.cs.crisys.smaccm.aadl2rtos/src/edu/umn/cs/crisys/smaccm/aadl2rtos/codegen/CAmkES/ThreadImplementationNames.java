/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.Connection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

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
  
  public List<DispatcherNames> getDispatchers() {
    ArrayList<DispatcherNames> dnList = new ArrayList<>();
    for (Dispatcher d : ti.getDispatcherList()) {
      dnList.add(new DispatcherNames(d));
    }
    return dnList;
  }
  
  public <U extends DataPort> List<PortNames> constructPortNames(List<U> ports) {
    ArrayList<PortNames> pnList = new ArrayList<>(); 
    for (DataPort d : ports) {
      pnList.add(new PortNames(d)); 
    }
    return pnList;
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

  public ModelNames getModel() {
    return new ModelNames(ti.getModel());
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
    for (ThreadImplementation t: ti.getModel().getThreadImplementations()) {
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
  
  public List<ConnectionNames> getIsSrcOfConnectionList() {
    List<ConnectionNames> cl = new ArrayList<>(); 
    ThreadInstance tii = getThreadInstance(); 
    for (Connection t: tii.getIsSrcOfConnectionList()) {
      cl.add(new ConnectionNames(t));
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
  
  //////////////////////////////////////////////////////////
  //
  // 'root' name
  // 
  //////////////////////////////////////////////////////////

  public String getNormalizedName() {
    return ti.getNormalizedName();
  }
  
  //////////////////////////////////////////////////////////
  //
  // Lock and unlocks for dispatcher component
  // 
  //////////////////////////////////////////////////////////
  
  public String getDispatcherLockStmt() {
    return getDispatcherComponentMutexName() + "_lock();\n";
  }
  
  public String getDispatcherUnlockStmt() {
    return getDispatcherComponentMutexName() + "_unlock();\n";
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
  
  public String getComponentName() {
    return ti.getNormalizedName();
  }    
  
  public String getDispatcherComponentMutexName() {
    return "smaccm_dispatch_mutex"; 
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
