package edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread;

/**
 * @author Mead, Whalen
 * 
 * 
 * 
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.PortNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class ThreadImplementation {

	private InitializerPort initEntrypointHandler = null;
	private int priority = -1;
  private int stackSize = 0; 
  private double minExecutionTime = -1.0; 
  private double maxExecutionTime = -1.0; 
  
  private String name;
  private String generatedEntrypoint = null;
  private  Model model;
  
	private List<ThreadInstance> threadInstanceList = new ArrayList<ThreadInstance>();
	private String dispatchProtocol; 
	private Boolean isPassive; 
	private Boolean isExternal = false;
	private Boolean requiresTimeServices = false;
	
	// Necessary for eChronos build.  "Location" defines number of thread.
	private int eChronosThreadLocation; 
	
	
  // Data port lists.  This is stupid.  
	private List<DataPort> ports = new ArrayList<DataPort>();
	private ArrayList<SharedDataAccessor> accessorList = new ArrayList<SharedDataAccessor>();
  
  private List<String> externalMutexList = new ArrayList<String>();
  private List<String> externalSemaphoreList = new ArrayList<String>();
  private List<String> externalReferencedFiles = new ArrayList<String>(); 
	private List<String> sourceFileList = new ArrayList<String>(); 
	
	// Outgoing dispatch contract (limits on dispatch)
	private ArrayList<OutgoingDispatchContract> dispatchLimits = new ArrayList<OutgoingDispatchContract>();
		
	// RPCs
	private Set<RemoteProcedureGroupEndpoint> providesRPGSet = new HashSet<>(); 
	private Set<RemoteProcedureGroupEndpoint> requiresRPGSet = new HashSet<>();
	
	// Constructor
	public ThreadImplementation(Model model, String name, int priority, int stackSize, 
	    String generatedEntrypoint, boolean isPassive) {
    this.name = name;
    this.priority = priority;
    this.stackSize = stackSize;
    this.model = model;
	  this.generatedEntrypoint = generatedEntrypoint;
	  this.isPassive = isPassive; 
  }
	
  public Model getModel() {
    return model;
  }
  
  public String getName() {
    return name;
  }

  public String getNormalizedName() {
    return Util.normalizeAadlName(this.getName());  
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public int getStackSize() {
    return this.stackSize;
  }

  public String getGeneratedEntrypoint() {
    return "smaccm_" + generatedEntrypoint;
  }

  
  /**
   * @return the eChronosThreadLocation
   */
  public int geteChronosThreadLocation() {
    return eChronosThreadLocation;
  }

  /**
   * @param eChronosThreadLocation the eChronosThreadLocation to set
   */
  public void seteChronosThreadLocation(int eChronosThreadLocation) {
    this.eChronosThreadLocation = eChronosThreadLocation;
  }

  /**
   * @return the isExternal
   */
  public Boolean getIsExternal() {
    return isExternal;
  }

  /**
   * @param isExternal the isExternal to set
   */
  public void setIsExternal(Boolean isExternal) {
    this.isExternal = isExternal;
  }

  /**
   * @return the minExecutionTime
   */
  public double getMinExecutionTime() {
    return minExecutionTime;
  }

  /**
   * @param minExecutionTime the minExecutionTime to set
   */
  public void setMinExecutionTime(double minExecutionTime) {
    this.minExecutionTime = minExecutionTime;
  }

  /**
   * @return the maxExecutionTime
   */
  public double getMaxExecutionTime() {
    return maxExecutionTime;
  }

  /**
   * @param maxExecutionTime the maxExecutionTime to set
   */
  public void setMaxExecutionTime(double maxExecutionTime) {
    this.maxExecutionTime = maxExecutionTime;
  }

  public List<SharedDataAccessor> getSharedDataAccessors() {
    return this.accessorList;
  }
  
  public void addSharedDataAccessor(SharedDataAccessor sda) {
    this.accessorList.add(sda);
  }
  
	/*
	public void addDispatcher(Dispatcher d) {
	  this.dispatcherList.add(d);
	}
	
	public List<Dispatcher> getDispatcherList() {
	  return this.dispatcherList; 
	}
	*/
  
  
	public InitializerPort getInitializeEntrypointOpt() {
		return this.initEntrypointHandler;
	}

	public void setInitializeEntrypointOpt(InitializerPort handler) {
	  this.initEntrypointHandler = handler;
	}

	public void addThreadInstance(ThreadInstance instance) {
		threadInstanceList.add(instance);
	}

	
 
  /**
   * @return the externalMutexList
   */
  public List<String> getExternalMutexList() {
    return externalMutexList;
  }

  /**
   * @param externalMutexList the externalMutexList to set
   */
  public void setExternalMutexList(List<String> externalMutexList) {
    this.externalMutexList = externalMutexList;
  }

  /**
   * @return the externalSemaphoreList
   */
  public List<String> getExternalSemaphoreList() {
    return externalSemaphoreList;
  }

  /**
   * @param externalSemaphoreList the externalSemaphoreList to set
   */
  public void setExternalSemaphoreList(List<String> externalSemaphoreList) {
    this.externalSemaphoreList = externalSemaphoreList;
  }

  /**
   * @return the externalReferencedFiles
   */
  public List<String> getExternalReferencedFiles() {
    return externalReferencedFiles;
  }

  /**
   * @param externalReferencedFiles the externalReferencedFiles to set
   */
  public void setExternalReferencedFiles(List<String> externalReferencedFiles) {
    this.externalReferencedFiles = externalReferencedFiles;
  }

  /**
   * @return the dispatchLimits
   */
  public ArrayList<OutgoingDispatchContract> getDispatchLimits() {
    return dispatchLimits;
  }

  /**
   * @param dispatchLimits the dispatchLimits to set
   */
  public void setDispatchLimits(ArrayList<OutgoingDispatchContract> dispatchLimits) {
    this.dispatchLimits = dispatchLimits;
  }

  public Set<DispatchableInputPort> getPassiveDispatcherRegion() {
    HashSet<DispatchableInputPort> dispatchers = new HashSet<>();
    for (DispatchableInputPort d : getDispatcherList()) {
      DispatcherTraverser dt = new DispatcherTraverser(); 
      dt.passiveDispatchersFromActiveThread(dispatchers, d);
    }
    return dispatchers;
  }
  
  public Set<PortConnection> getNonlocalActiveThreadConnectionFrontier() {
    Set<PortConnection> frontier = new HashSet<>(); 
    for (DispatchableInputPort d : getDispatcherList()) {
      DispatcherTraverser dt = new DispatcherTraverser();
      dt.dispatcherNonlocalActiveThreadConnectionFrontier(d, frontier);
    }
    
    // MWW: 4/14/2015; external threads are not "well behaved" in terms 
    // of their dispatchers, and should only be connected to other 
    // active threads.
    
    if (this.getIsExternal()) {
      for (DataPort p : this.getOutputPorts()) {
        for (PortConnection pc : p.getConnections()) {
          if (pc.getDestPort().getOwner().getIsPassive()) {
            throw new Aadl2RtosException("Error: Unsupported connection for thread " + this.getName() + ": Output ports of external threads can only be connected to active threads");
          }
          else {
            frontier.add(pc);
          }
        }
      }
    }
    return frontier;
  }

  public Set<PortConnection> getLocalActiveThreadConnectionFrontier() {
    Set<PortConnection> frontier = new HashSet<>(); 
    for (DispatchableInputPort d : getDispatcherList()) {
      DispatcherTraverser dt = new DispatcherTraverser();
      dt.dispatcherLocalActiveThreadConnectionFrontier(d, frontier);
    }
    return frontier;
  }

  public Set<ThreadImplementation> getPassiveThreadRegion() {
    HashSet<ThreadImplementation> threads = new HashSet<>();
    for (DispatchableInputPort d : getPassiveDispatcherRegion()) {
      threads.add(d.getOwner());
    }
    return threads;
  }
  
  /**
   * @return the isPassive
   */
  public Boolean getIsPassive() {
    return isPassive;
  }

  /**
   * @param isPassive the isPassive to set
   */
  public void setIsPassive(Boolean isPassive) {
    this.isPassive = isPassive;
  }


  public List<ThreadInstance> getThreadInstanceList() {
		return threadInstanceList;
	}

	
	public String getDispatchProtocol() {
	  return this.dispatchProtocol;
	}
	
	public void setDispatchProtocol(String dispatchProtocol) {
	  this.dispatchProtocol = dispatchProtocol;
	}

	/*
	public int getSignalNumberForInputEventPort(InputEventPort port) {

		for (int i = 0; i < dispatcherList.size(); i++) {
		  Dispatcher d = dispatcherList.get(i);
      if (d instanceof InputEventDispatcher) {
        InputEventDispatcher id = (InputEventDispatcher)d;
        if (id.getEventPort() == port) {
          return i;
        }
      }
		}
		return -1; // didn't find it.
	}
	
	public int getSignalNumberForDispatcher(Dispatcher dispatcher) {
		for (int i = 0; i < dispatcherList.size(); i++) {
		  if (dispatcher.equals(dispatcherList.get(i))) {
		    return i;
		  }
		}
		throw new Aadl2RtosException("Unable to find expected dispatcher in thread " + this.getName()) ;
	}
	*/
	
	public List<ThreadInstancePort> getThreadInstanceInputPorts() {
	  ArrayList<ThreadInstancePort> tips = new ArrayList<ThreadInstancePort>();
	  
	  for (ThreadInstance ti: this.getThreadInstanceList()) {
	    tips.addAll(ti.getThreadInstanceInputPorts());
	  }
	  return tips;
	}
	
  
  /**
   * @return the providesRPCList
   */
  public Set<RemoteProcedureGroupEndpoint> getProvidesRPGSet() {
    return providesRPGSet;
  }

  public void addProvidesRPG(RemoteProcedureGroupEndpoint rpg) {
    providesRPGSet.add(rpg);
  }
  
  /**
   * @param providesRPCList the providesRPCList to set
   */
  public void setProvidesRPGSet(Set<RemoteProcedureGroupEndpoint> providesRPGList) {
    this.providesRPGSet = providesRPGList;
  }

  /**
   * @return the requiresRPCList
   */
  public Set<RemoteProcedureGroupEndpoint> getRequiresRPGSet() {
    return requiresRPGSet;
  }

  public void addRequiresRPG(RemoteProcedureGroupEndpoint rpg) {
    requiresRPGSet.add(rpg);
  }
  
  /**
   * @param requiresRPCList the requiresRPCList to set
   */
  public void setRequiresRPGSet(Set<RemoteProcedureGroupEndpoint> requiresRPGList) {
    this.requiresRPGSet = requiresRPGList;
  }


  public List<DispatchableInputPort> getDispatcherList() {
    List<DispatchableInputPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof DispatchableInputPort) {
        l.add((DispatchableInputPort)p);
      }
    }
    return l;
  }

  /**
   * @return the inputDataPortList
   */
  public List<InputDataPort> getInputDataPortList() {
    List<InputDataPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof InputDataPort) {
        l.add((InputDataPort)p);
      }
    }
    return l;
  }


  public void addPort(DataPort idp) {
    this.ports.add(idp);
  }

  /**
   * @return the outputDataPortList
   */
  public List<OutputDataPort> getOutputDataPortList() {
    List<OutputDataPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof OutputDataPort) {
        l.add((OutputDataPort)p);
      }
    }
    return l;
  }

  /**
   * @return the inputEventPortList
   */
  public List<InputEventPort> getInputEventPortList() {
    List<InputEventPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof InputEventPort) {
        if (p.getType() instanceof UnitType) {
          l.add((InputEventPort)p);
        }
      }
    }
    return l;
  }

  public List<InputEventPort> getInputEventDataPortList() {
    List<InputEventPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof InputEventPort) {
        if (!(p.getType() instanceof UnitType)) {
          l.add((InputEventPort)p);
        }
      }
    }
    return l;
  }

  /**
   * @return the outputEventPortList
   */
  public List<OutputEventPort> getOutputEventPortList() {
    List<OutputEventPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof OutputEventPort) {
        if (p.getType() instanceof UnitType) {
          l.add((OutputEventPort)p);
        }
      }
    }
    return l;
  }

  public List<OutputEventPort> getOutputEventDataPortList() {
    List<OutputEventPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof OutputEventPort) {
        if (!(p.getType() instanceof UnitType)) {
          l.add((OutputEventPort)p);
        }
      }
    }
    return l;
  }
  
  public List<InputPort> getInputPorts() {
    List<InputPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof InputPort) {
        l.add((InputPort)p);
      }
    }
    return l;
  }
  
  public List<OutputPort> getOutputPorts() {
    List<OutputPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof OutputPort) {
        l.add((OutputPort)p);
      }
    }
    return l;
  }

  
  public List<OutputEventPort> getAllOutputEventPorts() {
    List<OutputEventPort> l = new ArrayList<>(); 
    for (DataPort p: this.ports) {
      if (p instanceof OutputEventPort) {
        l.add((OutputEventPort)p);
      }
    }
    return l;
  }
  
  /**
   * @return
   */
  public List<DataPort> getPortList() {
    return ports;
  }

  /**
   * @return the sourceFileList
   */
  public List<String> getSourceFileList() {
    return sourceFileList;
  }

  /**
   * @param sourceFileList the sourceFileList to set
   */
  public void setSourceFileList(List<String> sourceFileList) {
    if (sourceFileList != null) {
      this.sourceFileList = sourceFileList;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ThreadImplementation other = (ThreadImplementation) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  public Boolean getRequiresTimeServices() {
    return requiresTimeServices;
  }

  public void setRequiresTimeServices(Boolean requiresTimeServices) {
    this.requiresTimeServices = requiresTimeServices;
  }

  
  
}


