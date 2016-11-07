/*
Copyright (c) 2011, 2013 Rockwell Collins and the University of Minnesota.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
 */

package edu.umn.cs.crisys.tb.model;

import java.util.ArrayList;
//import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;

import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.legacy.ExternalIRQEvent;
import edu.umn.cs.crisys.tb.model.legacy.ExternalISR;
import edu.umn.cs.crisys.tb.model.port.ExternalIRQ;
import edu.umn.cs.crisys.tb.model.port.InputIrqPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.thread.ThreadCalendar;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstancePort;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.util.Util;

/*****************************************************
 * 
 * @author whalen
 *
 * To do: remove OS-specific aspects (well, actually most of the 
 * data here).
 * 
 */
public class OSModel extends ModelElementBase {
   private String systemImplementationName;
   private String systemInstanceName;

   private SystemImplementation systemImplementation;
   private SystemInstance systemInstance;

   // TODO: once we support multiple model elements, this should
   // return the parent.
   public ModelElement getParent() { return null; }

   public enum OSTarget {CAmkES, eChronos, VxWorks, linux}; 
   private OSTarget osTarget = OSTarget.eChronos;

   // Currently supported targets: QEMU, ODROID, PX4, X86
   public String HWTarget ;
   public String outputDirectory;

   // Connection instances - drives number of semaphores
   // (one function per thread implementation, pass in thread instance id)
   List<PortConnection> connectionInstances = new ArrayList<PortConnection>();
   // private ArrayList<String> semaphoreList = new ArrayList<String>();


   // Implementation objects: external.
   public List<ThreadImplementation> threadImplementationList = new ArrayList<ThreadImplementation>();
   public List<SharedData> sharedDataList = new ArrayList<SharedData>();
   public ThreadCalendar threadCalendar = new ThreadCalendar(this);
   public Set<String> sourceFiles = new HashSet<String>();
   public List<String> libraryFiles = new ArrayList<String>(); 	

   public List<String> legacyMutexList = new ArrayList<String>();
   public List<String> legacySemaphoreList = new ArrayList<String>();
   public List<ExternalISR> externalISRList = new ArrayList<ExternalISR>();
   public List<ExternalIRQEvent> externalIRQEventList = new ArrayList<ExternalIRQEvent>();
   public List<ExternalIRQ> externalIRQList = new ArrayList<ExternalIRQ>();
   public List<PortConnection> connectionList = new ArrayList<PortConnection>(); 

   // type stuff
   public Set<String> externalTypeHeaders = new HashSet<String>(); 

   public Map<String, Type> astTypes = new HashMap<String, Type>();
   public Map<String, RemoteProcedureGroup> remoteProcedureGroupMap = new HashMap<>(); 
   public Map<String, RemoteProcedure> remoteProcedureMap = new HashMap<>();

   // properties related to timers and dispatch
   public boolean generateSystickIRQ;
   public boolean externalTimerComponent; 

   // CAMKES specific properties
   public String camkesExternalTimerInterfacePath;
   public String camkesExternalTimerCompletePath;
   public int camkesInternalTimerTimersPerClient; 
   public int camkesTimeServerAadlThreadMinIndex; 
   public int camkesDataportRpcMinIndex;
   boolean camkesUseMailboxDataports = false;
   boolean useOSRealTimeExtensions; 

   /**
    * @return the useOSRealTimeExtensions
    */
   public boolean isUseOSRealTimeExtensions() {
      return useOSRealTimeExtensions;
   }

   /**
    * @param useOSRealTimeExtensions the useOSRealTimeExtensions to set
    */
   public void setUseOSRealTimeExtensions(boolean useOSRealTimeExtensions) {
      this.useOSRealTimeExtensions = useOSRealTimeExtensions;
   }


   // eChronos-specific properties
   public boolean eChronosGenerateCModules;
   public String eChronosCModulePath;
   public String eChronosFlashLoadAddress;

   public enum CommMutualExclusionPrimitive {MUTEX, SUSPEND_INTERRUPT} ; 
   public CommMutualExclusionPrimitive commMutexPrimitive = CommMutualExclusionPrimitive.MUTEX; 

   public enum ISRType {InThreadContextISR, SignalingISR} ; 
   public ISRType isrType = ISRType.InThreadContextISR; 

   // Model constructor
   public OSModel(SystemImplementation systemImplementation, 
         SystemInstance systemInstance) {
      this.systemImplementation = systemImplementation;
      this.systemInstance = systemInstance; 

      this.systemImplementationName = systemImplementation.getName();
      this.systemInstanceName = systemInstance.getName();
   }

   public SystemImplementation getSystemImplementation() {
      return this.systemImplementation;
   }

   public SystemInstance getSystemInstance() {
      return this.systemInstance;
   }

   /**
    * @return the osTarget
    */
   public OSTarget getOsTarget() {
      return osTarget;
   }

   /**
    * @param osTarget the osTarget to set
    */
   public void setOsTarget(OSTarget osTarget) {
      this.osTarget = osTarget;
   }



   /**
    * @return the hWTarget
    */
   public String getHWTarget() {
      return HWTarget;
   }

   /**
    * @param hWTarget the hWTarget to set
    */
   public void setHWTarget(String hWTarget) {
      HWTarget = hWTarget;
   }

   /**
    * @return the outputDirectory
    */
   public String getOutputDirectory() {
      return outputDirectory;
   }

   public Set<String> getExternalTypeHeaders() {
      return externalTypeHeaders;
   }

   public void setExternalTypeHeaders(Set<String> externalTypeHeaders) {
      this.externalTypeHeaders = externalTypeHeaders;
   }

   /**
    * @param outputDirectory the outputDirectory to set
    */
   public void setOutputDirectory(String outputDirectory) {
      this.outputDirectory = outputDirectory;
   }

   public Set<String> getSourceFiles()  {
      return this.sourceFiles; 
   }

   public List<String> getLibraryFiles()  {
      return this.libraryFiles; 
   }

   public CommMutualExclusionPrimitive getCommMutexPrimitive() {
      return this.commMutexPrimitive; 
   }

   public ISRType getISRType() {
      return this.isrType; 
   }

   public void addSourceFile(String fileName) {
      this.sourceFiles.add(fileName);
   }

   public void addLibraryFile(String fileName) {
      this.libraryFiles.add(fileName);
   }	

   public List<InputIrqPort> getIRQDispatcherList() {
      List<InputIrqPort> idList = new ArrayList<>(); 
      for (ThreadImplementation ti: this.getAllThreadImplementations()) {
         for (InputPort d: ti.getInputPorts()) {
            if (d instanceof InputIrqPort) {
               idList.add((InputIrqPort)d);
            }
         }
      }
      return idList;
   }

   public String getSystemImplementationName() {
      return systemImplementationName;
   }

   public String getSystemInstanceName() {
      return this.systemInstanceName;
   }

   public ThreadCalendar getThreadCalendar() {
      return this.threadCalendar;
   }

   public List<SharedData> getSharedDataList() {
      return this.sharedDataList;
   }

   /*
	public List<ThreadImplementation> getInternalThreadImplementations() {
	  List<ThreadImplementation> internalThreads = new ArrayList<ThreadImplementation>();
	  for (ThreadImplementation ti: this.threadImplementationList) {
	    if (!ti.getIsExternal()) {
	      internalThreads.add(ti);
	    }
	  }
	  return internalThreads;
	}

	public List<ThreadImplementation> getExternalThreadImplementations() {
    List<ThreadImplementation> externalThreads = new ArrayList<ThreadImplementation>();
    for (ThreadImplementation ti: this.threadImplementationList) {
      if (!ti.getIsExternal()) {
        externalThreads.add(ti);
      }
    }
    return externalThreads;
	}
    */
   public List<ThreadImplementation> getAllThreadImplementations() {
      return threadImplementationList;
   }

   public List<ThreadImplementation> getActiveThreadImplementations() {
      List<ThreadImplementation> activeThreads = new ArrayList<ThreadImplementation>(); 
      for (ThreadImplementation ti: getAllThreadImplementations()) {
         if (!ti.getIsPassive()) {
            activeThreads.add(ti);
         }
      }
      return activeThreads;
   }

   public List<ThreadImplementation> getPassiveThreadImplementations() {
      List<ThreadImplementation> t = new ArrayList<ThreadImplementation>(); 
      for (ThreadImplementation ti: getAllThreadImplementations()) {
         if (ti.getIsPassive()) {
            t.add(ti);
         }
      }
      return t;
   }

   /**
    * @return the rpcInterfaces
    */
   public Map<String, RemoteProcedureGroup> getRemoteProcedureGroupMap() {
      return remoteProcedureGroupMap;
   }

   /**
    * @param rpcInterfaces the rpcInterfaces to set
    */
   public void setRemoteProcedureGroupMap(Map<String, RemoteProcedureGroup> rpcInterfaces) {
      this.remoteProcedureGroupMap = rpcInterfaces;
   }


   /**
    * @return the remoteProcedureMap
    */
   public Map<String, RemoteProcedure> getRemoteProcedureMap() {
      return remoteProcedureMap;
   }

   /**
    * @param remoteProcedureMap the remoteProcedureMap to set
    */
   public void setRemoteProcedureMap(
         Map<String, RemoteProcedure> remoteProcedureMap) {
      this.remoteProcedureMap = remoteProcedureMap;
   }

   public List<ExternalISR> getExternalISRs() {
      return this.externalISRList;
   }

   public List<ExternalIRQEvent> getExternalIRQEvents() {
      return this.externalIRQEventList;
   }

   public List<ExternalIRQ> getExternalIRQs() {
      return this.externalIRQList;
   }

   public List<ThreadInstance> getAllThreadInstances() {
      List<ThreadInstance> list = new ArrayList<ThreadInstance>(); 
      for (ThreadImplementation t: getAllThreadImplementations()) {
         list.addAll(t.getThreadInstanceList()); 
      }
      return list;
   }

   public List<ThreadInstancePort> getAllThreadInstanceInputPorts() {
      ArrayList<ThreadInstancePort> instances = 
            new ArrayList<ThreadInstancePort>();
      for (ThreadInstance ti: getAllThreadInstances()) {
         instances.addAll(ti.getThreadInstanceInputPorts());
      }
      return instances;
   }


   /*
	public Map<ThreadImplementation, Set<Pair<MyPort, MyPort>>> getThreadSourcePorts() {
		return threadSourcePorts;
	}
    */	

   //	public List<ThreadImplementation> getThreads(List<String> threadNameList) {
   //		List<ThreadImplementation> threadList = new ArrayList<ThreadImplementation>();
   //		for (String threadName : threadNameList) { 
   //			threadList.add(threadImplementationMap.get(threadName));
   //		}
   //		return threadList;
   //	}


   public List<PortConnection> getConnections() {
      return this.connectionInstances;
   }

   public List<String> getExternalMutexList() {
      return this.legacyMutexList;
   }

   public List<String> getExternalSemaphoreList() {
      return this.legacySemaphoreList;
   }

   public boolean getGenerateSystickIRQ() {
      return this.generateSystickIRQ;
   }

   public Map<String, Type> getAstTypes() {
      return this.astTypes;
   }

   @Override
   public int hashCode() {
      return 0;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      else 
         return false;
   }

   public boolean isExternalTimerComponent() {
      return externalTimerComponent;
   }

   public void setExternalTimerComponent(boolean externalTimerComponent) {
      this.externalTimerComponent = externalTimerComponent;
   }

   public String getCamkesExternalTimerInterfacePath() {
      return camkesExternalTimerInterfacePath;
   }

   public void setCamkesExternalTimerInterfacePath(
         String camkesExternalTimerInterfacePath) {
      this.camkesExternalTimerInterfacePath = camkesExternalTimerInterfacePath;
   }

   public String getCamkesExternalTimerCompletePath() {
      return camkesExternalTimerCompletePath;
   }

   public void setCamkesExternalTimerCompletePath(
         String camkesExternalTimerCompletePath) {
      this.camkesExternalTimerCompletePath = camkesExternalTimerCompletePath;
   }

   public int getCamkesInternalTimerTimersPerClient() {
      return camkesInternalTimerTimersPerClient;
   }

   public void setCamkesInternalTimerTimersPerClient(
         int camkesInternalTimerTimersPerClient) {
      this.camkesInternalTimerTimersPerClient = camkesInternalTimerTimersPerClient;
   }

   public int getCamkesTimeServerAadlThreadMinIndex() {
      return camkesTimeServerAadlThreadMinIndex;
   }

   public void setCamkesTimeServerAadlThreadMinIndex(
         int camkesTimeServerAadlThreadMinIndex) {
      this.camkesTimeServerAadlThreadMinIndex = camkesTimeServerAadlThreadMinIndex;
   }

   public int getGenerateCamkesTimeServerThreadIndex() {
      return camkesTimeServerAadlThreadMinIndex++; 
   }


   public int getCamkesDataportRpcMinIndex() {
      return camkesDataportRpcMinIndex;
   }

   public void setCamkesDataportRpcMinIndex(int camkesDataportRpcMinIndex) {
      this.camkesDataportRpcMinIndex = camkesDataportRpcMinIndex;
   }

   public int getGenerateCamkesDataportRpcMinIndex() {
      return camkesTimeServerAadlThreadMinIndex++; 
   }

   public boolean getCamkesUseMailboxDataports() {
      return this.camkesUseMailboxDataports;
   }

   public void setCamkesUseMailboxDataports(boolean camkesUseMailboxDataports) {
      this.camkesUseMailboxDataports = camkesUseMailboxDataports;
   }

   public boolean isEChronosGenerateCModules() {
      return eChronosGenerateCModules;
   }

   public void setEChronosGenerateCModules(boolean eChronosGenerateCModules) {
      this.eChronosGenerateCModules = eChronosGenerateCModules;
   }

   public String getEChronosCModulePath() {
      return eChronosCModulePath;
   }

   public void setEChronosCModulePath(String eChronosCModulePath) {
      this.eChronosCModulePath = eChronosCModulePath;
   }

   public String getEChronosFlashLoadAddress() {
      return this.eChronosFlashLoadAddress;
   }

   public void setEChronosFlashLoadAddress(String eChronosFlashLoadAddress) {
      this.eChronosFlashLoadAddress = eChronosFlashLoadAddress;
   }

   int connNumber = 0; 
   public int getGenerateConnectionNumber() {
      connNumber++; 
      return connNumber;
   }

   public static String getPrefix() {
      return Util.getPrefix();
   }

}