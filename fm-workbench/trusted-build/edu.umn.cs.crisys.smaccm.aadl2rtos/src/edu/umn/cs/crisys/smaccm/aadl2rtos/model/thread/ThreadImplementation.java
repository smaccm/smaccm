package edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread;

/**
 * @author Mead, Whalen
 * 
 * TODO:    In "ThreadImplementation"
 *    ! FIXED: only one thread instance for thread implementation (should be list)
 *    -> ISR threads are not distinguished from "regular" threads
 * 
 * TODO: Why the fuck do we have "legacy semaphores" stored in threads?
 * TODO: Get rid of isrThread, all the other ISR shit.
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

	private ExternalHandler initEntrypointHandler = null;
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
	
	// Necessary for eChronos build.  "Location" defines number of thread.
	private int eChronosThreadLocation; 
	
	// private String smaccmSysSignalOpt = null;
	// private String isrHandlerName = null;
	
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
	private List<RemoteProcedureGroupEndpoint> providesRPGList = new ArrayList<>(); 
	private List<RemoteProcedureGroupEndpoint> requiresRPGList = new ArrayList<>();
	
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
	
	public ExternalHandler getInitializeEntrypointOpt() {
		return this.initEntrypointHandler;
	}

	public void setInitializeEntrypointOpt(ExternalHandler handler) {
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
  public List<RemoteProcedureGroupEndpoint> getProvidesRPGList() {
    return providesRPGList;
  }

  public void addProvidesRPG(RemoteProcedureGroupEndpoint rpg) {
    providesRPGList.add(rpg);
  }
  
  /**
   * @param providesRPCList the providesRPCList to set
   */
  public void setProvidesRPGList(List<RemoteProcedureGroupEndpoint> providesRPGList) {
    this.providesRPGList = providesRPGList;
  }

  /**
   * @return the requiresRPCList
   */
  public List<RemoteProcedureGroupEndpoint> getRequiresRPGList() {
    return requiresRPGList;
  }

  public void addRequiresRPG(RemoteProcedureGroupEndpoint rpg) {
    requiresRPGList.add(rpg);
  }
  
  /**
   * @param requiresRPCList the requiresRPCList to set
   */
  public void setRequiresRPGList(List<RemoteProcedureGroupEndpoint> requiresRPGList) {
    this.requiresRPGList = requiresRPGList;
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
  
  
}


