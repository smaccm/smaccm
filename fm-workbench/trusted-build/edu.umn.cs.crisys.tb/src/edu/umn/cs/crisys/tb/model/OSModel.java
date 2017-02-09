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

import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ProcessorImplementation;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;

import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.legacy.ExternalIRQEvent;
import edu.umn.cs.crisys.tb.model.legacy.ExternalISR;
import edu.umn.cs.crisys.tb.model.port.ExternalIRQ;
import edu.umn.cs.crisys.tb.model.port.InputIrqPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.process.ProcessInstance;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.InternalRemoteProcedureGroup;
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
   private String instanceName;

   //private SystemImplementation systemImplementation;
   //private SystemInstance systemInstance;
   private ComponentImplementation processorImpl;
   private ComponentInstance processorInstance;
      

   TopLevelModel tlm = null; 
   public TopLevelModel getTopLevelModel() { return tlm; }
   public void setTopLevelModel(TopLevelModel tlm) { this.tlm = tlm; } 
   
   OSModel parent = null; 
   public ModelElement getParent() { return parent; }
   public void setParent(OSModel parent) { this.parent = parent; }
   

   public enum OSTarget {CAmkES, eChronos, VxWorks, linux}; 
   private OSTarget osTarget = OSTarget.eChronos;

   // Currently supported targets: QEMU, ODROID, PX4, X86
   private String HWTarget ;
   private String outputDirectory;


   // Implementation objects: external.
   private List<ProcessInstance> processInstanceList = new ArrayList<>();
   private List<ProcessImplementation> processImplementationList = new ArrayList<>();
   private List<SharedData> sharedDataList = new ArrayList<>();
   private ThreadCalendar threadCalendar = new ThreadCalendar(this);
   private Set<String> sourceFiles = new HashSet<>();
   private List<String> libraryFiles = new ArrayList<>(); 	

   private List<String> legacyMutexList = new ArrayList<>();
   private List<String> legacySemaphoreList = new ArrayList<>();
   private List<ExternalISR> externalISRList = new ArrayList<>();
   private List<ExternalIRQEvent> externalIRQEventList = new ArrayList<>();
   private List<ExternalIRQ> externalIRQList = new ArrayList<>();



   // properties related to timers and dispatch
   private boolean generateSystickIRQ;
   private boolean externalTimerComponent; 

   // CAMKES specific properties
   private String camkesExternalTimerInterfacePath;
   private String camkesExternalTimerCompletePath;
   private int camkesInternalTimerTimersPerClient; 
   private int camkesTimeServerAadlThreadMinIndex; 
   private int camkesDataportRpcMinIndex;
   private boolean camkesUseMailboxDataports = false;
   private boolean useOSRealTimeExtensions = false; 

   // Properties related to VMs
   public class VMDataport {
      public ThreadInstance owner; 
      public String name;
      public Type type;
      public VMDataport(ThreadInstance o, String n, Type t) { owner = o; name = n; type = t;}
      public boolean equals(Object o) {
         if (o == null) return false;
         if (!(o instanceof VMDataport)) return false;
         VMDataport other = (VMDataport)o;
         return (owner.equals(other.owner) &&
                 name.equals(other.name) &&
                 type.equals(other.type));
      }
   };
   
   public class CrossVMDataportConnection {
      public VMDataport src;
      public List<VMDataport> dests;
      public CrossVMDataportConnection(VMDataport s, List<VMDataport> d) {src = s; dests = d;}
   }
   
   private List<CrossVMDataportConnection> crossVmConnections = new ArrayList<>();
   
   public List<CrossVMDataportConnection> getCrossVmConnections() { return crossVmConnections; }
   
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

   public List<OSModel> virtualMachineList = new ArrayList<>(); 
   
   // Figure this out by examining binding.
   // public ProcessorInstance binding
   

   // how do we want to represent hierarchy?
   public OSModel(ComponentImplementation processorImpl,
         ComponentInstance processorInstance) {
      this.processorImpl = processorImpl;
      this.processorInstance = processorInstance;
      this.instanceName = processorInstance.getName();
   }
//   public SystemImplementation getSystemImplementation() {
//      return this.systemImplementation;
//   }

//   public SystemInstance getSystemInstance() {
//      return this.systemInstance;
//   }

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
      for (ThreadImplementation ti: this.getThreadImplementationList()) {
         for (InputPort d: ti.getInputPorts()) {
            if (d instanceof InputIrqPort) {
               idList.add((InputIrqPort)d);
            }
         }
      }
      return idList;
   }

   public String getInstanceName() {
      return this.instanceName;
   }
   
   public String getPathName() {
      String prefix = ""; 
      if (this.parent != null) {
         prefix = parent.getPathName() + "_"; 
      }
      return prefix + getInstanceName() ; 
   }

   public ThreadCalendar getThreadCalendar() {
      return this.threadCalendar;
   }

   public List<SharedData> getSharedDataList() {
      return this.sharedDataList;
   }

   public List<ThreadImplementation> getThreadImplementationList() {
      List<ThreadImplementation> threadImpls = new ArrayList<>(); 
      for (ProcessImplementation pi: this.getProcessImplementationList()) {
         threadImpls.addAll(pi.getThreadImplementationList());
      }
      return threadImpls;
   }

   public List<ThreadImplementation> getActiveThreadImplementations() {
      List<ThreadImplementation> activeThreads = new ArrayList<ThreadImplementation>(); 
      for (ThreadImplementation ti: getThreadImplementationList()) {
         if (!ti.getIsPassive()) {
            activeThreads.add(ti);
         }
      }
      return activeThreads;
   }

   public List<ThreadImplementation> getPassiveThreadImplementations() {
      List<ThreadImplementation> t = new ArrayList<ThreadImplementation>(); 
      for (ThreadImplementation ti: getThreadImplementationList()) {
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
      return tlm.getRemoteProcedureGroupMap();
   }

   /**
    * @param rpcInterfaces the rpcInterfaces to set
    */
   public void setRemoteProcedureGroupMap(Map<String, RemoteProcedureGroup> rpcInterfaces) {
      tlm.setRemoteProcedureGroupMap(rpcInterfaces);
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
      for (ProcessInstance t: getProcessInstanceList()) {
         list.addAll(t.getThreadInstanceList()); 
      }
      return list;
   }

   public List<PortFeature> getPortFeatureList() {
      List<PortFeature> features = new ArrayList<>(); 
      for (ThreadImplementation ti: this.getThreadImplementationList()) {
         features.addAll(ti.getPortList());
      }
      return features;
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
      return this.tlm.getAstTypes();
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

   /***************************************************************
    * 
    * Camkes-specific functions
    * 
    ***************************************************************/

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

   
   /***************************************************************
    * 
    * EChronos-specific functions
    * 
    ***************************************************************/

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

   int vmDataportNumber = 0; 
   public int getGenerateVmDataportNumber() {
      vmDataportNumber++; 
      return vmDataportNumber;
   }
   
   public static String getPrefix() {
      return Util.getPrefix();
   }

   public List<OSModel> getVirtualMachineList() {
      return virtualMachineList;
   }

   public void setVirtualMachineList(List<OSModel> virtualMachineList) {
      this.virtualMachineList = virtualMachineList;
   }
   
   public void addVirtualMachine(OSModel model) {
      this.virtualMachineList.add(model);
   }
   
   public Set<String> getExternalTypeHeaders() {
      return tlm.getExternalTypeHeaders();
   }

   public ComponentImplementation getProcessorImpl() {
      return processorImpl;
   }

   public void setProcessorImpl(ComponentImplementation processorImpl) {
      this.processorImpl = processorImpl;
   }

   public ComponentInstance getProcessorInstance() {
      return processorInstance;
   }

   public void setProcessorInstance(ComponentInstance processorInstance) {
      this.processorInstance = processorInstance;
   }
   public List<ProcessImplementation> getProcessImplementationList() {
      return processImplementationList;
   }
   
   public List<ProcessInstance> getProcessInstanceList() {
      return processInstanceList;
   }
   
   public void setProcessImplementationList(List<ProcessImplementation> processImplementationList) {
      this.processImplementationList = processImplementationList;
   }
   public List<String> getLegacyMutexList() {
      return legacyMutexList;
   }
   public void setLegacyMutexList(List<String> legacyMutexList) {
      this.legacyMutexList = legacyMutexList;
   }
   public List<String> getLegacySemaphoreList() {
      return legacySemaphoreList;
   }
   public void setLegacySemaphoreList(List<String> legacySemaphoreList) {
      this.legacySemaphoreList = legacySemaphoreList;
   }
   public List<ExternalISR> getExternalISRList() {
      return externalISRList;
   }
   public void setExternalISRList(List<ExternalISR> externalISRList) {
      this.externalISRList = externalISRList;
   }
   public List<ExternalIRQEvent> getExternalIRQEventList() {
      return externalIRQEventList;
   }
   public void setExternalIRQEventList(List<ExternalIRQEvent> externalIRQEventList) {
      this.externalIRQEventList = externalIRQEventList;
   }
   public List<ExternalIRQ> getExternalIRQList() {
      return externalIRQList;
   }
   public void setExternalIRQList(List<ExternalIRQ> externalIRQList) {
      this.externalIRQList = externalIRQList;
   }
   public boolean iseChronosGenerateCModules() {
      return eChronosGenerateCModules;
   }
   public void seteChronosGenerateCModules(boolean eChronosGenerateCModules) {
      this.eChronosGenerateCModules = eChronosGenerateCModules;
   }
   public String geteChronosCModulePath() {
      return eChronosCModulePath;
   }
   public void seteChronosCModulePath(String eChronosCModulePath) {
      this.eChronosCModulePath = eChronosCModulePath;
   }
   public String geteChronosFlashLoadAddress() {
      return eChronosFlashLoadAddress;
   }
   public void seteChronosFlashLoadAddress(String eChronosFlashLoadAddress) {
      this.eChronosFlashLoadAddress = eChronosFlashLoadAddress;
   }
   public ISRType getIsrType() {
      return isrType;
   }
   public void setIsrType(ISRType isrType) {
      this.isrType = isrType;
   }
   public void setSharedDataList(List<SharedData> sharedDataList) {
      this.sharedDataList = sharedDataList;
   }
   public void setThreadCalendar(ThreadCalendar threadCalendar) {
      this.threadCalendar = threadCalendar;
   }
   public void setSourceFiles(Set<String> sourceFiles) {
      this.sourceFiles = sourceFiles;
   }
   public void setLibraryFiles(List<String> libraryFiles) {
      this.libraryFiles = libraryFiles;
   }
   public void setGenerateSystickIRQ(boolean generateSystickIRQ) {
      this.generateSystickIRQ = generateSystickIRQ;
   }
   
   
   // What is the right way to do this?
   // To get all thread instances for the platform, find all thread 
   // port instances, and see which ones connect across platforms.
   // 
   // Note: there aren't thread port instances, only thread connection
   // instances.
   // This is what we really want...we want to know for a group of
   // connections all those that cross boundaries; further, we would
   // like it separated by whether the connection source or destination
   // originates within the VM.
   
   
   public List<PortConnection> getHostSourceToVMDestConnections() {
      List<PortConnection> vmCrossing = new ArrayList<>(); 
      for (ThreadInstance ti: this.getAllThreadInstances()) {
         for (PortConnection pi: ti.getAllConnectionsList()) {
            ThreadInstance src = pi.getSourceThreadInstance(); 
            OSModel srcModel = Util.getElementOSModel(src);
            if (srcModel == this.getParent()) {
               vmCrossing.add(pi);
            }
         }
      }
      return vmCrossing;
   }
   
   public boolean isVM() {
       return (this.getParent() != null); 
   }
   
   public List<PortConnection> getHostDestToVMSourceConnections() {
      List<PortConnection> vmCrossing = new ArrayList<>(); 
      for (ThreadInstance ti: this.getAllThreadInstances()) {
         for (PortConnection pi: ti.getAllConnectionsList()) {
            ThreadInstance dst = pi.getDestThreadInstance();
            OSModel dstModel = Util.getElementOSModel(dst);
            if (dstModel == this.getParent()) {
               vmCrossing.add(pi);
            }
         }
      }
      return vmCrossing;
   }

   public List<PortConnection> getVmCrossingConnections() {
      List<PortConnection> elemList = getHostSourceToVMDestConnections();
      elemList.addAll(getHostDestToVMSourceConnections());  
      return elemList;
   }
   
   public List<PortFeature> getVmCrossingPorts() {
      Set<PortFeature> portSet = new HashSet<>(); 
      for (PortConnection conn: getVmCrossingConnections()) {
         portSet.add(conn.getSourcePort());
         portSet.add(conn.getDestPort());
      }
      return new ArrayList<PortFeature>(portSet);
      
   }
}