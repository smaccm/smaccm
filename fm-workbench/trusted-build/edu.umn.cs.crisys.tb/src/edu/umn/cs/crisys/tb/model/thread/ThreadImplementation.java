package edu.umn.cs.crisys.tb.model.thread;

/**
 * @author Mead, Whalen
 * 
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterListRegistry;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitterCamkes;
import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.connection.SharedDataAccessor;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.DispatcherTraverser;
import edu.umn.cs.crisys.tb.model.port.InitializerPort;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutgoingDispatchContract;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

public class ThreadImplementation extends ModelElementBase {

   private InitializerPort initEntrypointHandler = null;
   private int priority = -1;
   private int stackSize = 0; 
   private int minExecutionTimeInMicroseconds = -1; 
   private int maxExecutionTimeInMicroseconds = -1; 
   private int periodInMicroseconds = -1; 

   private String name;
   private String generatedEntrypoint = null;
   private  OSModel model;

   private List<ThreadInstance> threadInstanceList = new ArrayList<ThreadInstance>();
   private String dispatchProtocol; 
   private Boolean isPassive; 
   private Boolean isExternal = false;
   private Boolean requiresTimeServices = false;

   // Necessary for eChronos build.  "Location" defines number of thread.
   private int eChronosThreadLocation; 


   private List<PortFeature> ports = new ArrayList<PortFeature>();
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

   public ProcessImplementation parentProcess;
   
   // Constructor
   public ThreadImplementation(OSModel model, String name, int priority, int stackSize, 
         String generatedEntrypoint, boolean isPassive) {
      this.name = name;
      this.priority = priority;
      this.stackSize = stackSize;
      this.model = model;
      this.setGeneratedEntrypoint(generatedEntrypoint);
      this.isPassive = isPassive; 
   }

   public OSModel getModel() {
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
   public int getMinExecutionTimeInMicroseconds() {
      return minExecutionTimeInMicroseconds;
   }

   /**
    * @param minExecutionTime the minExecutionTime to set
    */
   public void setMinExecutionTimeInMicroseconds(int minExecutionTime) {
      this.minExecutionTimeInMicroseconds = minExecutionTime;
   }

   /**
    * @return the maxExecutionTime
    */
   public int getMaxExecutionTimeInMicroseconds() {
      return maxExecutionTimeInMicroseconds;
   }

   /**
    * @param maxExecutionTime the maxExecutionTime to set
    */
   public void setMaxExecutionTimeInMicroseconds(int maxExecutionTime) {
      this.maxExecutionTimeInMicroseconds = maxExecutionTime;
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

   public List<PortConnection> getActiveThreadConnectionList() {
      Set<PortConnection> frontier = new HashSet<>();
      for (DispatchableInputPort d : getDispatcherList()) {
         DispatcherTraverser dt = new DispatcherTraverser();
         dt.dispatcherActiveThreadConnections(d, frontier);
      }

      List<PortConnection> result = new ArrayList<>(frontier);
      result.sort(Comparator.comparing(PortConnection::getConnectionID));
      return result;
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
         for (PortFeature p : this.getOutputPorts()) {
            for (PortConnection pc : p.getConnections()) {
               if (pc.getDestPort().getOwner().getIsPassive()) {
                  throw new TbException("Error: Unsupported connection for thread " + this.getName() + ": Output ports of external threads can only be connected to active threads");
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
      for (PortFeature p: this.ports) {
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
      for (PortFeature p: this.ports) {
         if (p instanceof InputDataPort) {
            l.add((InputDataPort)p);
         }
      }
      return l;
   }


   public void addPort(PortFeature idp) {
      this.ports.add(idp);
   }

   /**
    * @return the outputDataPortList
    */
   public List<OutputDataPort> getOutputDataPortList() {
      List<OutputDataPort> l = new ArrayList<>(); 
      for (PortFeature p: this.ports) {
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
      for (PortFeature p: this.ports) {
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
      for (PortFeature p: this.ports) {
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
      for (PortFeature p: this.ports) {
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
      for (PortFeature p: this.ports) {
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
      for (PortFeature p: this.ports) {
         if (p instanceof InputPort) {
            l.add((InputPort)p);
         }
      }
      return l;
   }

   public List<OutputPort> getOutputPorts() {
      List<OutputPort> l = new ArrayList<>(); 
      for (PortFeature p: this.ports) {
         if (p instanceof OutputPort) {
            l.add((OutputPort)p);
         }
      }
      return l;
   }


   public List<OutputEventPort> getAllOutputEventPorts() {
      List<OutputEventPort> l = new ArrayList<>(); 
      for (PortFeature p: this.ports) {
         if (p instanceof OutputEventPort) {
            l.add((OutputEventPort)p);
         }
      }
      return l;
   }

   /**
    * @return
    */
   public List<PortFeature> getPortList() {
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
      return requiresTimeServices || 
            this.getDispatchProtocol().equalsIgnoreCase("Periodic") || 
            this.getDispatchProtocol().equalsIgnoreCase("Hybrid");
   }

   public void setRequiresTimeServices(Boolean requiresTimeServices) {
      this.requiresTimeServices = requiresTimeServices;
   }


   /**
    * @return the periodInMilliseconds
    */
   public int getPeriodInMicroseconds() {
      return periodInMicroseconds;
   }

   /**
    * @param periodInMilliseconds the periodInMilliseconds to set
    */
   public void setPeriodInMicroseconds(int periodInMicroseconds) {
      this.periodInMicroseconds = periodInMicroseconds;
   }

   public String getGeneratedEntrypoint() {
      return generatedEntrypoint;
   }

   public void setGeneratedEntrypoint(String generatedEntrypoint) {
      this.generatedEntrypoint = generatedEntrypoint;
   }

   @Override
   public ModelElement getParent() {
      // TODO Make parent the process once we get to that.
      return this.getParentProcess();
   }

   public ProcessImplementation getParentProcess() {
      return parentProcess;
   }

   public void setParentProcess(ProcessImplementation parentProcess) {
      this.parentProcess = parentProcess;
   }

     
}


