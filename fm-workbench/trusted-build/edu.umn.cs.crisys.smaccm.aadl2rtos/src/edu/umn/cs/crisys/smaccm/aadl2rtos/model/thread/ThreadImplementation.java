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
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class ThreadImplementation {

	private InitializerDispatcher initEntrypointHandler = null;
	private int priority = -1;
  private int stackSize = 0; 
  private double minExecutionTime = -1.0; 
  private double maxExecutionTime = -1.0; 
  
  private String name;
  private String generatedEntrypoint = null;
  private  Model model;
  
	private List<ThreadInstance> threadInstanceList = new ArrayList<ThreadInstance>();
	private ArrayList<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();
	private String dispatchProtocol; 
	private Boolean isPassive; 
	private Boolean isExternal = false;
	private Boolean requiresTimeServices = false;
	
	// Necessary for eChronos build.  "Location" defines number of thread.
	private int eChronosThreadLocation; 
	
	
  // Data port lists
	private ArrayList<InputDataPort> inputDataPortList = new ArrayList<InputDataPort>();
  private ArrayList<OutputDataPort> outputDataPortList = new ArrayList<OutputDataPort>();
	private ArrayList<InputEventPort> inputEventPortList = new ArrayList<InputEventPort>();
	private ArrayList<OutputEventPort> outputEventPortList = new ArrayList<OutputEventPort>();
	private ArrayList<OutputEventPort> outputEventDataPortList = new ArrayList<OutputEventPort>();
	private ArrayList<InputEventPort> inputEventDataPortList = new ArrayList<InputEventPort>();
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
  
	
	public void addDispatcher(Dispatcher d) {
	  this.dispatcherList.add(d);
	}
	
	public List<Dispatcher> getDispatcherList() {
	  return this.dispatcherList; 
	}
	
	public InitializerDispatcher getInitializeEntrypointOpt() {
		return this.initEntrypointHandler;
	}

	public void setInitializeEntrypointOpt(InitializerDispatcher handler) {
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

  public Set<Dispatcher> getPassiveDispatcherRegion() {
    HashSet<Dispatcher> dispatchers = new HashSet<>();
    for (Dispatcher d : getDispatcherList()) {
      DispatcherTraverser dt = new DispatcherTraverser(); 
      dt.passiveDispatchersFromActiveThread(dispatchers, d);
    }
    return dispatchers;
  }
  
  public Set<PortConnection> getNonlocalActiveThreadConnectionFrontier() {
    Set<PortConnection> frontier = new HashSet<>(); 
    for (Dispatcher d : getDispatcherList()) {
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
    for (Dispatcher d : getDispatcherList()) {
      DispatcherTraverser dt = new DispatcherTraverser();
      dt.dispatcherLocalActiveThreadConnectionFrontier(d, frontier);
    }
    return frontier;
  }

  public Set<ThreadImplementation> getPassiveThreadRegion() {
    HashSet<ThreadImplementation> threads = new HashSet<>();
    for (Dispatcher d : getPassiveDispatcherRegion()) {
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

  /**
   * @return the inputDataPortList
   */
  public ArrayList<InputDataPort> getInputDataPortList() {
    return inputDataPortList;
  }

  /**
   * @param inputDataPortList the inputDataPortList to set
   */
  public void setInputDataPortList(ArrayList<InputDataPort> inputDataPortList) {
    this.inputDataPortList = inputDataPortList;
  }

  public void addInputDataPort(InputDataPort idp) {
    this.inputDataPortList.add(idp);
  }

  /**
   * @return the outputDataPortList
   */
  public ArrayList<OutputDataPort> getOutputDataPortList() {
    return outputDataPortList;
  }

  /**
   * @param outputDataPortList the outputDataPortList to set
   */
  public void setOutputDataPortList(ArrayList<OutputDataPort> outputDataPortList) {
    this.outputDataPortList = outputDataPortList;
  }

  public void addOutputDataPort(OutputDataPort odp) {
    this.outputDataPortList.add(odp);
  }
  /**
   * @return the inputEventPortList
   */
  public ArrayList<InputEventPort> getInputEventPortList() {
    return inputEventPortList;
  }

  /**
   * @param inputEventPortList the inputEventPortList to set
   */
  public void setInputEventPortList(ArrayList<InputEventPort> inputEventPortList) {
    this.inputEventPortList = inputEventPortList;
  }
  
  public void addInputEventPort(InputEventPort inputEventPort) {
    this.inputEventPortList.add(inputEventPort);
  }

  /**
   * @return the outputEventPortList
   */
  public ArrayList<OutputEventPort> getOutputEventPortList() {
    return outputEventPortList;
  }

  /**
   * @param outputEventPortList the outputEventPortList to set
   */
  public void setOutputEventPortList(
      ArrayList<OutputEventPort> outputEventPortList) {
    this.outputEventPortList = outputEventPortList;
  }
  
  public void addOutputEventPort(OutputEventPort oep) {
    this.outputEventPortList.add(oep);
  }

  /**
   * @return the outputEventDataPortList
   */
  public ArrayList<OutputEventPort> getOutputEventDataPortList() {
    return outputEventDataPortList;
  }

  /**
   * @param outputEventDataPortList the outputEventDataPortList to set
   */
  public void setOutputEventDataPortList(
      ArrayList<OutputEventPort> outputEventDataPortList) {
    this.outputEventDataPortList = outputEventDataPortList;
  }

  public void addOutputEventDataPort(OutputEventPort oep) {
    this.outputEventDataPortList.add(oep);
  }
  
  /**
   * @return the inputEventDataPortList
   */
  public ArrayList<InputEventPort> getInputEventDataPortList() {
    return inputEventDataPortList;
  }

  /**
   * @param inputEventDataPortList the inputEventDataPortList to set
   */
  public void setInputEventDataPortList(
      ArrayList<InputEventPort> inputEventDataPortList) {
    this.inputEventDataPortList = inputEventDataPortList;
  }
	
	public void addInputEventDataPort(InputEventPort iep) {
	  this.inputEventDataPortList.add(iep);
	}

  /**
   * @return
   */
  public List<InputPort> getInputPorts() {
    // TODO Auto-generated method stub
    ArrayList<InputPort> inputPorts = new ArrayList<InputPort>();
    inputPorts.addAll(this.inputDataPortList);
    inputPorts.addAll(this.inputEventDataPortList);
    inputPorts.addAll(this.inputEventPortList);
    return inputPorts;
  }
  
  public List<OutputPort> getOutputPorts() {
    ArrayList<OutputPort> outputPorts = new ArrayList<OutputPort>();
    outputPorts.addAll(this.outputDataPortList);
    outputPorts.addAll(this.outputEventDataPortList);
    outputPorts.addAll(this.outputEventPortList);
    return outputPorts;
  }

  public List<OutputEventPort> getAllOutputEventPorts() {
    ArrayList<OutputEventPort> outputPorts = new ArrayList<>();
    outputPorts.addAll(this.outputEventDataPortList);
    outputPorts.addAll(this.outputEventPortList);
    return outputPorts;
  }
  
  /**
   * @return
   */
  public List<DataPort> getPortList() {
    ArrayList<DataPort> ports = new ArrayList<DataPort>();
    ports.addAll(getOutputPorts());
    ports.addAll(getInputPorts()); 
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


