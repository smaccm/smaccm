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

package edu.umn.cs.crisys.tb.parse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.AbstractFeature;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.Element;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Parameter;
import org.osate.aadl2.PortCategory;
import org.osate.aadl2.Property;
import org.osate.aadl2.SubprogramAccess;
// import org.osate.aadl2.SubprogramGroupAccess;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.impl.BusAccessImpl;
import org.osate.aadl2.impl.DataAccessImpl;
import org.osate.aadl2.impl.DataImplementationImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.DataSubcomponentImpl;
import org.osate.aadl2.impl.DataTypeImpl;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.ProcessImplementationImpl;
import org.osate.aadl2.impl.ProcessorImplementationImpl;
import org.osate.aadl2.impl.SubcomponentImpl;
import org.osate.aadl2.impl.SubprogramGroupAccessImpl;
import org.osate.aadl2.impl.SubprogramGroupTypeImpl;
import org.osate.aadl2.impl.SubprogramTypeImpl;
import org.osate.aadl2.impl.ThreadSubcomponentImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.impl.VirtualProcessorImplementationImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.ConnectionKind;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.impl.ComponentInstanceImpl;
import org.osate.aadl2.instance.impl.FeatureInstanceImpl;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.tb.Logger;
import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.OSModel.OSTarget;
import edu.umn.cs.crisys.tb.model.TopLevelModel;
import edu.umn.cs.crisys.tb.model.connection.EndpointConnection;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.connection.SharedData;
import edu.umn.cs.crisys.tb.model.connection.SharedDataAccessor;
import edu.umn.cs.crisys.tb.model.legacy.*;
import edu.umn.cs.crisys.tb.model.port.*;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.process.ProcessInstance;
import edu.umn.cs.crisys.tb.model.rpc.Direction;
import edu.umn.cs.crisys.tb.model.rpc.ExternalRemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.InternalRemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureParameter;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint.AccessType;
import edu.umn.cs.crisys.tb.model.thread.*;
import edu.umn.cs.crisys.tb.model.type.*;
import edu.umn.cs.crisys.tb.parse.antlr.*;
import edu.umn.cs.crisys.tb.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class AadlModelParser {
   private SystemImplementation systemImplementation;
   private SystemInstance systemInstance;

   // containers for AADL AST objects
   private ArrayList<ThreadTypeImpl> threadTypeImplList = new ArrayList<>();

   // Instance objects
   private ArrayList<ComponentInstance> threadInstanceList = new ArrayList<>();
   private ArrayList<ComponentInstance> processInstanceList = new ArrayList<>();
   private ArrayList<ComponentInstance> processorInstanceList = new ArrayList<>();
   private ArrayList<ComponentInstance> virtualProcessorInstanceList = new ArrayList<>(); 
   private ArrayList<ConnectionInstance> connectionInstances = new ArrayList<>();

   private Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap = new HashMap<>();
   private Map<ProcessImplementationImpl, ProcessImplementation> processImplementationMap = new HashMap<>();

   private Map<DataSubcomponentImpl, SharedData> sharedDataMap = new HashMap<>();	
   private HashMap<PortImpl, PortFeature> portMap = new HashMap<PortImpl, PortFeature>();
   private Map<ComponentInstance, ThreadInstance> threadInstanceMap = new HashMap<>();
   //private Map<ComponentInstance, ProcessInstance> processInstanceMap = new HashMap<>();  
   private Map<ComponentInstance, OSModel> osModelMap = new HashMap<>(); 

   private Set<DataClassifier> dataTypes = new HashSet<DataClassifier>();

   private TopLevelModel tlm;

   private Logger logger;

   // Model constructor
   public AadlModelParser(SystemImplementation sysimpl, SystemInstance si, TopLevelModel model, Logger logger) {
      this.systemImplementation = sysimpl;
      this.systemInstance = si;
      this.logger = logger;
      this.tlm = model;

      // re-init the counters.
      PortConnection.init();
      ThreadInstance.init();

      // setModelProperties(sysimpl, this.model);

      // Parse existing AADL model
      findThreadTypeImpls(systemImplementation);
      findTopLevelComponentInstances(systemInstance);


      // construct the OSModel implementations (using the processor list)
      constructOSModelMap();
      constructVmModelMap();

      // Initialize thread implementations
      constructThreadImplMap();

      // Initialize process implementations
      constructProcessImplMap();

      // Initialize connections
      initializeConnections();

      // Initialize Periodic Dispatcher
      initializeThreadCalendar(); 


      // Harvest model type data
      harvestModelTypeData();

      // initialize the model thread and shared data lists.
//      this.model.threadImplementationList = new ArrayList<ThreadImplementation>(this.threadImplementationMap.values());
      //this.model.threadImplementationList.sort(Comparator.comparing(ThreadImplementation::getNormalizedName));
//      this.model.sharedDataList = new ArrayList<SharedData>(this.sharedDataMap.values());
      this.tlm.getModels().addAll(this.osModelMap.values());
      this.tlm.getModels().sort(Comparator.comparing(OSModel::getPathName));
   }

   final public static Property CAMKES_VM_BUILD = Util.getPropertyDefinitionInWorkspace("TB_SYS::Camkes_VM_Build");

   /***************************************************************************
    * 
    * Functions for building properties related to model 
    * 
    ***************************************************************************/
   void setModelProperties(NamedElement sysimpl, OSModel model) {

      // Construct the properties found directly in the 
      // model
      // find the OS and target hardware
      String OS = PropertyUtil.getOS(sysimpl);
      if ("echronos".equalsIgnoreCase(OS)) {
         model.setOsTarget(OSModel.OSTarget.eChronos);
      } else if ("camkes".equalsIgnoreCase(OS)) {
         model.setOsTarget(OSModel.OSTarget.CAmkES);
      } else if ("vxworks".equalsIgnoreCase(OS)) {
         model.setOsTarget(OSModel.OSTarget.VxWorks);  
      } else if ("linux".equalsIgnoreCase(OS)) {
         model.setOsTarget(OSModel.OSTarget.linux);
      }
      else {
         this.logger.error("[Trusted Build]: OS target: [" + OS + "] not recognized by trusted build");
         throw new TbException("Parse failure on OS target property ");
      }

      String HW = PropertyUtil.getHW(sysimpl); 
      model.setHWTarget(HW);

      // Determine whether to use real-time extensions
      model.setUseOSRealTimeExtensions(PropertyUtil.getUseOsRealTimeExtensions(sysimpl));

      // note: may be null, and that's o.k.
      model.setOutputDirectory(Util.getStringValueOpt(sysimpl, PropertyUtil.TB_SYS_OUTPUT_DIRECTORY));

      // create properties related to timers.
      try {
         model.setGenerateSystickIRQ(PropertyUtils.getBooleanValue(systemImplementation, PropertyUtil.GENERATE_SCHEDULER_SYSTICK_IRQ));
         model.setExternalTimerComponent(PropertyUtils.getBooleanValue(systemImplementation, PropertyUtil.EXTERNAL_TIMER_COMPONENT));
      } catch (Exception e) {
         this.logger.error("Unexpected internal exception: properties [generateSystickIRQ, externalTimerComponent] should have default value in TB_SYS, but were not found.");
         throw new TbException("Parse failure on one of [generateSystickIRQ, externalTimerComponent] target property ");
      }

      try {
         if (model.getOsTarget() == OSTarget.CAmkES) {
            model.setCamkesTimeServerAadlThreadMinIndex((int)PropertyUtils.getIntegerValue(systemImplementation, PropertyUtil.CAMKES_TIME_SERVER_AADL_THREAD_MIN_INDEX));
            model.setCamkesDataportRpcMinIndex((int)PropertyUtils.getIntegerValue(systemImplementation, PropertyUtil.CAMKES_DATAPORT_RPC_MIN_INDEX));
         }
      } catch (Exception e) {
         this.logger.error("Properties camkesTimeServerAadlThreadMinIndex and camkesDataportRpcMinIndex must be assigned for CAmkES target.");
         throw new TbException("Parse failure on one of [camkesTimeServerAadlThreadMinIndex, camkesDataportRpcMinIndex] target property ");
      }

      boolean useVmBuild = PropertyUtils.getBooleanValue(sysimpl, CAMKES_VM_BUILD);
      if (useVmBuild) {
         model.getExtendedDataMap().put("Camkes_VM_Build", new Boolean(useVmBuild));
      }
      
      if (model.isExternalTimerComponent()) {
         try {
            model.setCamkesExternalTimerCompletePath(PropertyUtils.getStringValue(systemImplementation, PropertyUtil.CAMKES_EXTERNAL_TIMER_COMPLETE_PATH));
            model.setCamkesExternalTimerInterfacePath(PropertyUtils.getStringValue(systemImplementation, PropertyUtil.CAMKES_EXTERNAL_TIMER_INTERFACE_PATH));
         } catch (Exception e) {
            this.logger.error("Error in system implementation: if property External_Timer_Component is true, then properties: {TB_SYS::CAmkES_External_Timer_Interface_Path, TB_SYS::CAmkES_External_Timer_Complete_Path} must also be defined.");
            throw new TbException("Parse failure on external timer component properties.");
         }
      } else {
         try {
            model.setCamkesInternalTimerTimersPerClient((int)PropertyUtils.getIntegerValue(systemImplementation, PropertyUtil.CAMKES_INTERNAL_TIMER_TIMERS_PER_CLIENT));
         } catch (Exception e) {
            this.logger.error("Unexpected internal exception: property [camkesInternalTimerTimersPerClient] should have default value but were not found.");
            throw new TbException("Parse failure on [camkesInternalTimerTimersPerClient] target property ");
         }
      }

      try {
         model.eChronosGenerateCModules = PropertyUtils.getBooleanValue(systemImplementation, PropertyUtil.ECHRONOS_GENERATE_C_MODULES);
         if (model.eChronosGenerateCModules) {
            model.eChronosCModulePath = PropertyUtils.getStringValue(systemImplementation, PropertyUtil.ECHRONOS_C_MODULE_PATH);
         }
      } catch (Exception e) {
         this.logger.error("If eChronosGenerateCModules is 'true', then eChronosCModulePath must be defined.");
         throw new TbException("Parse failure on eChronosCModulePath target property ");
      }

      // default value is null.
      model.eChronosFlashLoadAddress = 
            Util.getStringValueOpt(systemImplementation, 
                  PropertyUtil.ECHRONOS_FLASH_LOAD_ADDRESS);

      try {
         model.setCamkesUseMailboxDataports(
               (boolean)PropertyUtils.getBooleanValue(systemImplementation, 
                     PropertyUtil.MAILBOX));
      } catch(Exception e) {}

      // grab system implementation level external mutexes
      List<String> externalMutexList = (ArrayList<String>) PropertyUtil.getExternalMutexList(systemImplementation);
      List<String> externalSemList = (ArrayList<String>) PropertyUtil.getExternalSemaphoreList(systemImplementation);
      model.getLegacyMutexList().addAll(externalMutexList);
      model.getLegacySemaphoreList().addAll(externalSemList);

      // add "extra" files to the model, if necessary.
      
      List<String> topLevelFileNames = 
            Util.getSourceTextListOpt(this.systemImplementation, PropertyUtil.SOURCE_TEXT);

      if (topLevelFileNames != null) {
         for (String s : topLevelFileNames) {
            logger.status("Referenced File: " + s);
            if (s.endsWith(".a")) {
               model.getLibraryFiles().add(s);
            } else {
               model.getSourceFiles().add(s);
            }
         }
      }
      // grab all files referenced in the model.
      initializeLegacyIRQs(sysimpl, model);

   }


   /***************************************************************************
    * 
    * Functions for filtering AADL elements for further processing
    * 
    ***************************************************************************/

   private void findTopLevelComponentInstances(SystemInstance top) {
      connectionInstances.addAll(top.getAllConnectionInstances());

      for (ComponentInstance ci : top.getAllComponentInstances()) {
         System.out.println("name: " + ci.getName());
         System.out.println("full name: " + ci.getFullName());
         System.out.println("name path: " + ci.getComponentInstancePath());
         if (ci.getCategory() == ComponentCategory.THREAD) {
            threadInstanceList.add(ci);
         } else if (ci.getCategory() == ComponentCategory.PROCESS) {
            processInstanceList.add(ci);
         } else if (ci.getCategory() == ComponentCategory.PROCESSOR) {
            this.processorInstanceList.add(ci);
            ci.getChildren();
         } else if (ci.getCategory() == ComponentCategory.VIRTUAL_PROCESSOR) {
            this.virtualProcessorInstanceList.add(ci); 
            ci.getComponentClassifier(); 
         }
      }
   }

   private void findThreadTypeImpls(Element elem) {
      if (elem instanceof SubcomponentImpl) {
         SubcomponentImpl sub = (SubcomponentImpl) elem;
         ComponentImplementation impl = sub.getComponentImplementation();
         Classifier classifier = null;

         if (sub instanceof ThreadSubcomponentImpl) {
            ThreadSubcomponentImpl tsub = (ThreadSubcomponentImpl) sub;
            classifier = tsub.getClassifier();

            if (classifier instanceof ThreadTypeImpl) {
               ThreadTypeImpl tti = (ThreadTypeImpl) classifier;
               threadTypeImplList.add(tti);
               return;
            } else {
               String error = "In findThreads: classifier for ThreadSubcomponentImpl is not a ThreadTypeImpl.n";
               System.out.println(error);
            }
         } else if (impl != null) {
            findThreadTypeImpls(impl);
         }
      }
      for (Element child : elem.getChildren()) {
         findThreadTypeImpls(child);
      }
   }

   /***************************************************************************
    * 
    * Port constructors
    * 
    ***************************************************************************/

   InputIrqPort addIrqHandler(PortImpl port, ThreadImplementation ti) {
      try {
         List<String> entrypoints = PropertyUtil.getComputeEntrypointList(port);
         if (entrypoints == null) { 
            throw new TbException("missing entrypoints");
         }
         List<String> files = Util.getSourceTextListOpt(port,PropertyUtil.SOURCE_TEXT);
         String signal_name = Util.getStringValue(port, PropertyUtil.TB_SYS_SIGNAL_NAME);
         String flih_handler = Util.getStringValue(port, PropertyUtil.ISR_HANDLER);
         int signal_number ;
         try {
            signal_number = (int)PropertyUtils.getIntegerValue(port, PropertyUtil.TB_SYS_SIGNAL_NUMBER);
         } catch (NumberFormatException nfe) {
            logger.error("ISR Port " + port.getName() + ": signal number is not a positive integer. Original error: " + nfe.toString());
            throw new TbException("ISR construction error");
         } catch (PropertyNotPresentException pnpe) {
            signal_number = InputIrqPort.NO_SIGNAL_NUMBER;

            // signal number is necessary for CAmkES, but not eChronos
            
            if (ti.getModel().getOsTarget() == OSModel.OSTarget.CAmkES) {
               throw pnpe;
            }
         }
         Map<String, String> memoryRegions;
         memoryRegions = PropertyUtil.getMemoryRegions(port);
         ArrayList<ExternalHandler> ehl = new ArrayList<ExternalHandler>();
         for (String s: entrypoints) {
            ExternalHandler eh = new ExternalHandler(s);
            ehl.add(eh);
         }
         InputIrqPort ip = new InputIrqPort(port.getName(), ti, 
               signal_name, signal_number, flih_handler, memoryRegions);
         ip.setExternalHandlerList(ehl);
         String sendsEventsTo = Util.getStringValueOpt(port, PropertyUtil.TB_SYS_SENDS_EVENTS_TO);
         ip.setOptSendsEventsToString(sendsEventsTo);
         if (files != null) {
            ip.setImplementationFileList(files);
         }
         return ip;

      } catch (Exception e) {
         logger.error("Exception in construction of ISR port: " + port.getName() + " (likely missing one of {Compute_Entrypoint, TB_SYS::Signal_Name, TB_SYS::First_Level_Interrupt_Handler}).  Error code: " + 
               e.toString());
         throw new TbException("Problem constructing ISR port.");
      }
   }

   void addExtHandlersAndOptSendStrings(DispatchableInputPort dpi, PortImpl port, ThreadImplementation ti) {

      List<String> entrypoints = PropertyUtil.getComputeEntrypointList(port);
      if (entrypoints != null) {
         ArrayList<ExternalHandler> ehl = new ArrayList<ExternalHandler>();
         for (String s: entrypoints) {
            ExternalHandler eh = new ExternalHandler(s);
            ehl.add(eh);
         }
         List<String> files = Util.getSourceTextListOpt(port,PropertyUtil.SOURCE_TEXT);
         String sendsEventsTo = Util.getStringValueOpt(port, PropertyUtil.TB_SYS_SENDS_EVENTS_TO);
         dpi.setExternalHandlerList(ehl);
         dpi.setOptSendsEventsToString(sendsEventsTo);
         if (files != null) {
            dpi.setImplementationFileList(files);
         }
      } else {
         logger.warn("Warning: port: " + port.getName() + " in thread: " + ti.getName() + " does not have a compute entrypoint and will not be dispatched.");
      }
   }

   void addPort(PortImpl port, ThreadImplementation ti) {
      PortFeature dp = null;
      if (port.getDirection() == DirectionType.IN_OUT) {
         this.constructWarning(ti.getName(), "IN_OUT ports", port.getName(), null);
         throw new TbException("Gentlemen do not process IN_OUT ports");
      }
      Type datatype = getDataType(port);
      if (port.getCategory() == PortCategory.DATA) {
         if (port.getDirection() == DirectionType.IN) {
            dp = new InputDataPort(port.getName(), datatype, ti);
         } else {
            dp = new OutputDataPort(port.getName(), datatype, ti);
         }
      } else if (port.getCategory() == PortCategory.EVENT) {
         if (port.getDirection() == DirectionType.IN) {
            // handle IRQs specially
            if (PropertyUtil.getIsIsr(port)) {
               throw new TbException("ISR ports must be event-data ports with type Base_Types::Integer_64.");
            } else {
              int queueSize = PropertyUtil.getQueueSize(port); 
              dp = new InputEventPort(port.getName(), new UnitType(), ti, queueSize);
              addExtHandlersAndOptSendStrings((DispatchableInputPort)dp,port,ti);
            }
         } else {
           int queueSize = PropertyUtil.getQueueSize(port); 
           dp = new OutputEventPort(port.getName(), new UnitType(), ti,queueSize);
         }
      } else if (port.getCategory() == PortCategory.EVENT_DATA) {
         if (port.getDirection() == DirectionType.IN) {
            if (PropertyUtil.getIsIsr(port)) {
               if (datatype.equals(new IntType(64, true))) { 
                  dp = addIrqHandler(port, ti); 
               } else {
                  throw new TbException("ISR ports must be event-data ports with type Base_Types::Integer_64.");
               }
            } else {
              int queueSize = PropertyUtil.getQueueSize(port); 
              dp = new InputEventPort(port.getName(), datatype, ti, queueSize);
              addExtHandlersAndOptSendStrings((DispatchableInputPort)dp,port,ti);
            }
         } else {
           int queueSize = PropertyUtil.getQueueSize(port); 
           dp = new OutputEventPort(port.getName(), datatype, ti, queueSize);
         }
      }
      ti.addPort(dp);

      String initializeEntrypointSourceText = Util.getStringValueOpt(port, PropertyUtil.INITIALIZE_ENTRYPOINT_SOURCE_TEXT);
      String commprimFnNameOpt = Util.getStringValueOpt(port, PropertyUtil.TB_SYS_COMMPRIM_SOURCE_TEXT);
      String commprimHeaderNameOpt = Util.getStringValueOpt(port, PropertyUtil.TB_SYS_COMMPRIM_SOURCE_HEADER);
      dp.setCommprimFnNameOpt(commprimFnNameOpt);
      dp.setCommprimHeaderNameOpt(commprimHeaderNameOpt);
      if (initializeEntrypointSourceText != null) {
         dp.setInitializeEntrypointSourceText(new ExternalHandler(initializeEntrypointSourceText));
      }
      portMap.put(port, dp);
   }

   /***************************************************************************
    * 
    * Thread implementation constructors
    * 
    ***************************************************************************/


   private void setDispatchProtocol(ThreadTypeImpl tti, ThreadImplementation ti, boolean isPassive) {
      EnumerationLiteral dispatchProtocol;  
      try {
         dispatchProtocol = PropertyUtil.getDispatchProtocol(tti);
      } catch (Exception e) {
         throw new TbException(
               "Dispatch protocol not found for thread: " + tti.getFullName());
      }
      String dpName = dispatchProtocol.getName(); 
      if (isPassive) { 
         if (!"Aperiodic".equalsIgnoreCase(dpName)) {
            throw new TbException(
                  "For passive thread " + tti.getFullName() + "'Aperiodic' is the only currently supported dispatch protocol.");
         }
      }
      else {
         if (!("Sporadic".equalsIgnoreCase(dpName) || 
               "Periodic".equalsIgnoreCase(dpName) ||
               "Hybrid".equalsIgnoreCase(dpName))) {
            throw new TbException(
                  "For active thread " + tti.getFullName() + ", dispatch protocol must be one of {Sporadic, Periodic, Hybrid}.");
         } else {
            try {
               double periodInUs = PropertyUtil.getPeriodInMicroseconds(tti);
               ti.setPeriodInMicroseconds((int) periodInUs);
            } catch (Exception e) {
               if ("Periodic".equalsIgnoreCase(dpName)) {
                  throw new TbException(
                        "For thread " + tti.getFullName()
                        + " with dispatch protocol " + dpName
                        + " property 'Period' is required.");
               } else {
                  this.logger.warn("For thread " + tti.getFullName()
                  + " with dispatch protocol " + dpName
                  + " property 'Period' is not specified; this is necessary for schedulability analysis.");
               }
            }
         }
      }
      ti.setDispatchProtocol(dispatchProtocol.getName());
   }

   private void createOptPeriodicHandler(ThreadTypeImpl tti, ThreadImplementation ti) {
      String dpName = ti.getDispatchProtocol();
      if ((dpName.equalsIgnoreCase("Periodic") || 
            dpName.equalsIgnoreCase("Hybrid"))) {
         try {
            double periodInUs = PropertyUtil.getPeriodInMicroseconds(tti);
            int period = (int)(periodInUs / 1000.0);
            List<String> entrypointNameList = PropertyUtil.getComputeEntrypointList(tti); 
            List<ExternalHandler> handlerList = new ArrayList<ExternalHandler>();
            for (String entrypoint : entrypointNameList) {
               handlerList.add(new ExternalHandler(entrypoint)); 
            }
            InputPeriodicPort ipp = new InputPeriodicPort("periodic_dispatcher", ti); 

            String sendsEventsTo = Util.getStringValueOpt(tti, PropertyUtil.TB_SYS_SENDS_EVENTS_TO);
            ipp.setPeriod(period);
            ipp.setOptSendsEventsToString(sendsEventsTo);
            List<String> files = Util.getSourceTextListOpt(tti, PropertyUtil.SOURCE_TEXT);
            if (files != null) {
               ipp.setImplementationFileList(files);
            }
            ipp.setExternalHandlerList(handlerList);
            ti.addPort(ipp);
         } catch (Exception e) {
            throw new TbException(
                  "For thread " + tti.getFullName()
                  + " with dispatch protocol " + dpName
                  + " properties: 'Period', 'Compute_Entrypoint_Source_Text', and 'Source_Text' are required.");
         }
      }
   }

   public void constructWarning(String threadName, String featureType, String featureName, String supportDate) {
      String str = 
            "Thread " + threadName + " contains " + featureType + " " +   
                  featureName + ".  Features of type " + featureType + 
                  " are currently unsupported so declaration will be ignored."; 
      if (supportDate != null) {
         str += "  Support for " + featureType + " is planned for " + supportDate + ".";
      } else {
         str += " No support for " + featureType + " is planned.";
      }
      logger.warn(str);
   }

   public class SendsToWalker extends SendsToBaseListener {
      ArrayList<OutgoingDispatchContract> contracts = new ArrayList<>();
      List<OutputEventPort> ports;

      public SendsToWalker(List<OutputEventPort> ports) {
         this.ports = ports;
      }

      public ArrayList<OutgoingDispatchContract> getContracts() {return contracts;}

      public OutputEventPort findMatchingPort(String id) {
         for (OutputEventPort oep : ports) {
            if (id.equalsIgnoreCase(oep.getName())) {
               return oep;
            }
         }
         throw new TbException("For dispatch contract, unable to match id: " + id + " to an output port.");
      }

      @Override
      public void enterSends_to_tl(SendsToParser.Sends_to_tlContext ctx) {
         OutgoingDispatchContract contract = new OutgoingDispatchContract(this.ports); 
         int entries = ctx.ID().size();
         for (int index = 0; index < entries; index++) {
            String id = ctx.ID(index).getText(); 
            Integer count = Integer.decode(ctx.INT(index).getText());
            OutputEventPort oep = findMatchingPort(id);
            contract.add(oep, count);
         }
         contracts.add(contract);
      }
   }

   private SendsToWalker constructDispatchLimit(String sendsEventsTo, List<OutputEventPort> outputPorts) {
      ANTLRInputStream input = new ANTLRInputStream(sendsEventsTo);
      TokenStream tokens = new CommonTokenStream( new SendsToLexer( input ) );
      SendsToParser parser = new SendsToParser(tokens); 
      ParseTree tree = parser.program();
      ParseTreeWalker walker = new ParseTreeWalker(); 
      SendsToWalker surrogate = new SendsToWalker(outputPorts); 
      walker.walk(surrogate, tree);
      return surrogate;
   }

   private void constructDispatchLimits(ThreadTypeImpl tti, ThreadImplementation ti) {
      try {
         String sendsEventsTo = Util.getStringValueOpt(tti, PropertyUtil.TB_SYS_SENDS_EVENTS_TO);
         List<OutputEventPort> outputs = ti.getAllOutputEventPorts();
         SendsToWalker threadSurrogate = null;

         if (sendsEventsTo != null) {
            threadSurrogate = constructDispatchLimit(sendsEventsTo, outputs); 
         }

         List<DispatchableInputPort> dispList = ti.getDispatcherList();
         for (DispatchableInputPort d : dispList) {
            if (d.getOptSendsEventsToString() != null) {
               String set = d.getOptSendsEventsToString();
               SendsToWalker s = constructDispatchLimit(set, outputs);
               d.setDispatchLimits(s.getContracts());
            } else if (threadSurrogate != null){
               d.setDispatchLimits(threadSurrogate.getContracts());
            } else {
               if (ti.getIsExternal()) {
                  this.logger.warn("Port: " + d.getName() + " of external thread: " + ti.getName() + " does not have dispatch limits");
                  d.setDispatchLimits(new ArrayList<OutgoingDispatchContract>());
               } else 
                  throw new TbException("No dispatch limit (Sends_Events_To) specified for dispatcher " + d.getName());
            }
         }
      }
      catch (Exception ae) {
         TbException excp = new TbException("In thread " + ti.getName() + ": " + ae);
         throw excp;
      }
   }

   private void createOptInitializeEntrypoint(ThreadTypeImpl tti, ThreadImplementation ti) {
      String initializer = Util.getStringValueOpt(tti, PropertyUtil.INITIALIZE_ENTRYPOINT_SOURCE_TEXT);
      if (initializer != null) {
         ExternalHandler handler = new ExternalHandler(initializer);
         ArrayList<ExternalHandler> handlers = new ArrayList<ExternalHandler>();
         handlers.add(handler); 
         InitializerPort id = new InitializerPort(ti.getName() + "_initializer", ti);
         id.setExternalHandlerList(handlers);
         ti.setInitializeEntrypointOpt(id);
         ti.addPort(id);
      }
   }

   private ThreadImplementation constructThreadImplementation(ThreadTypeImpl tti, OSModel model) {
      String name = tti.getName();
      boolean isPassive = PropertyUtil.getThreadType(tti);
      boolean isExternal = PropertyUtil.getIsExternal(tti);
      int priority = -1; 
      int stackSize = 4096;

      if (isPassive) {
         throw new TbException("Trusted build: 'Rename' branch currently does not support passive threads.");
      }
      // Ordering: we have constructed the OSModels specifically to make this 
      // connection easy, but...nothing is simple.  We still need to 
      // map from threads back to processes.
      
      if (!(isPassive || isExternal)) {
         priority = PropertyUtil.getPriority(tti);
         stackSize = PropertyUtil.getStackSizeInBytes(tti);
      } else {
         // TODO: Compute priorities for passive threads.
         priority = 200; 
         // TODO: deprecate use of 'default' stack size
         stackSize = 16384;
         try {
            PropertyUtil.getPriority(tti);
            logger.warn("Warning: priority ignored for passive/external thread: " + name);
         } catch (TbException e) {}
         try {
            stackSize = PropertyUtil.getStackSizeInBytes(tti); 
         } catch (TbException e) {
            if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
               logger.warn("Deprecation warning: default stack size (16384) used for passive thread: " + name);
            }
         }
      }
      String generatedEntrypoint = tti.getFullName();
      ThreadImplementation ti = 
            new ThreadImplementation(model, name, priority, stackSize, generatedEntrypoint, isPassive);

      double minComputeTime = PropertyUtil.getMinComputeExecutionTimeInMicroseconds(tti);
      double maxComputeTime = PropertyUtil.getMaxComputeExecutionTimeInMicroseconds(tti);
      boolean requiresTimeServices = PropertyUtils.getBooleanValue(tti, PropertyUtil.REQUIRES_TIME_SERVICES);
      List<String> externalMutexList = (ArrayList<String>) PropertyUtil.getExternalMutexList(tti);
      List<String> externalSemaphoreList = (ArrayList<String>) PropertyUtil.getExternalSemaphoreList(tti);

      ti.setMinExecutionTimeInMicroseconds((int) minComputeTime);
      ti.setMaxExecutionTimeInMicroseconds((int) maxComputeTime);
      ti.setIsExternal(isExternal);
      ti.setRequiresTimeServices(requiresTimeServices);
      ti.setExternalMutexList(externalMutexList);
      ti.setExternalSemaphoreList(externalSemaphoreList);

      //generatedEntrypoint = Util.getStringValue(tti, ThreadUtil.LEGACY_ENTRYPOINT);

      setDispatchProtocol(tti, ti, isPassive);
      createOptPeriodicHandler(tti, ti); 
      createOptInitializeEntrypoint(tti, ti); 

      List<String> fileNames = Util.getSourceTextListOpt(tti, PropertyUtil.SOURCE_TEXT);
      ti.setSourceFileList(fileNames);

      /* This is WRONG, but it is how I'm going to leave it until 
       * we add better support for instances */
      
      
      for (Feature f: tti.getAllFeatures()) {
         if (f instanceof PortImpl) {
            addPort((PortImpl)f, ti); 
         } 
         else if (f instanceof FeatureGroup) {
            constructWarning(name, "feature group", f.getName(), "11/2014");
         }
         else if (f instanceof SubprogramAccess) {
            constructWarning(name, "subprogram access", f.getName(), "11/2014");
         }
         /* else if (f instanceof SubprogramGroupAccess) {
        constructWarning(name, "subprogram group access", f.getName(), "11/2014");
      } */
         else if (f instanceof BusAccessImpl) {
            constructWarning(name, "bus access", f.getName(), null);
         } else if (f instanceof AbstractFeature) {
            constructWarning(name, "abstract feature", f.getName(), null);
         }
      }

      if (!ti.getAllOutputEventPorts().isEmpty()) {
         constructDispatchLimits(tti, ti); 
      } else {
         ti.setDispatchLimits(new ArrayList<OutgoingDispatchContract>());
      }
      return ti;
   }

   /* Move tests to thread instances:
    *    if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
               logger.warn("Warning: stack size ignored for passive/external thread: " + name);
         }

    */
   
   /***************************************************************************
    * 
    * OSModel constructors - must be called prior to constructing threads or 
    * processes.
    * 
    ***************************************************************************/
   public void constructOSModel(ComponentInstance c) {
      ProcessorImplementationImpl pti = (ProcessorImplementationImpl) c.getComponentClassifier(); 
      OSModel osModel = new OSModel(pti, c);
      osModel.setTopLevelModel(this.tlm);
      this.setModelProperties(pti, osModel);
      this.osModelMap.put(c, osModel);
   }

   public void constructOSModelMap() {
      for (ComponentInstance c: this.processorInstanceList) {
         constructOSModel(c);
      }
   }

   public void constructVmModel(ComponentInstance c) {
      VirtualProcessorImplementationImpl pti = 
            (VirtualProcessorImplementationImpl)c.getComponentClassifier();
      Element parent = c.getOwner();
      if (parent == null || 
         ((ComponentInstance)parent).getCategory() != ComponentCategory.PROCESSOR) {
         this.logger.error("Error: Trusted build virtual processor: " + c.getName() +  " must have a processor parent");
         throw new TbException("Error: Trusted build virtual processor " + c.getName() +  " must have a defined processor parent in AADL"); 
      }
      ComponentInstance parentProc = (ComponentInstance)parent;
      OSModel parentModel = this.osModelMap.get(parentProc);      
      OSModel osModel = new OSModel(pti, c); 
      osModel.setTopLevelModel(this.tlm);
      this.setModelProperties(pti, osModel);
      parentModel.addVirtualMachine(osModel);
      osModel.setParent(parentModel);
      this.osModelMap.put(c, osModel);
   }
   
   public void constructVmModelMap() {
      for (ComponentInstance c: this.virtualProcessorInstanceList) {
         constructVmModel(c);
      }
   }
   /***************************************************************************
    * 
    * Process constructors - must be called after threads are constructed.
    * 
    ***************************************************************************/
   public ThreadTypeImpl optFindThreadImplementation(Element elem) {
      if (elem instanceof ThreadSubcomponentImpl) {
         ThreadSubcomponentImpl tsub = (ThreadSubcomponentImpl) elem;
         Element classifier = tsub.getClassifier();
         if (classifier instanceof ThreadTypeImpl) {
            ThreadTypeImpl tti = (ThreadTypeImpl) classifier;
            return tti;
         } else {
            throw new TbException("optFindThreadImplementation: unexpected behavior.");
         }
      } else if (elem instanceof ThreadTypeImpl) {
         return (ThreadTypeImpl)elem;
      } 
      return null;
   }

   public ProcessImplementation constructProcessImplementation(ProcessImplementationImpl pti) {
      ProcessImplementation procImpl = 
            new ProcessImplementation(pti.getName());
      for (Element elem: pti.getChildren()) {
         ThreadTypeImpl tti = optFindThreadImplementation(elem);
         if (tti != null) {
            if (this.threadImplementationMap.containsKey(tti)) {
               ThreadImplementation ti = this.threadImplementationMap.get(tti);
               procImpl.addThreadImplementation(ti);
               ti.setParentProcess(procImpl);
            } else {
               throw new TbException("Error: unable to find thread implementation " + elem.toString() + 
                     " in the thread implementation map.");
            }
         }
      }
      return procImpl; 
   }

   public ProcessInstance constructProcessInstance(ComponentInstance c, ProcessImplementation impl) {
      ProcessInstance pi = new ProcessInstance(c.getName(), impl);
      for (Element elem: c.getChildren()) {
         if (this.threadInstanceMap.containsKey(elem)) {
            ThreadInstance ti = this.threadInstanceMap.get(elem);
            pi.addThreadInstance(ti);
            ti.setProcessInstance(pi);
         }
      }
      return pi; 
   }


   public OSModel findOsModel(NamedElement ne) {
      try {
         ComponentInstance procBinding = PropertyUtil.getProcessorBinding(ne);
         return osModelMap.get(procBinding);
      } catch (Exception e) {
         throw new TbException("Trusted build: object " + ne.getName() + " is not bound to a processor or virtual processor.");
      }
   }

   public void constructProcessImplMap() {
      Map<ProcessImplementationImpl, ProcessImplementation> processMap = 
            this.processImplementationMap;
      for (ComponentInstance c: this.processInstanceList) {
         ProcessImplementationImpl pti = (ProcessImplementationImpl) c.getComponentClassifier(); 
         ProcessImplementation procImpl; 
         OSModel model = findOsModel(c);

         if (processMap.containsKey(pti)) {
            procImpl = processMap.get(pti); 
         } else {
            procImpl = constructProcessImplementation(pti);
            model.getProcessImplementationList().add(procImpl);
            procImpl.setModel(model);
         }
         ProcessInstance pi = constructProcessInstance(c, procImpl);
         procImpl.addProcessInstance(pi);
         pi.setModel(model);
         model.getProcessInstanceList().add(pi);
      }
   }

   /***************************************************************************
    * 
    * Thread map constructors
    * 
    ***************************************************************************/

   private Map<ThreadTypeImpl, List<ComponentInstance>> 
   constructThreadImplToInstanceMap() {

      // map component instances to implementations.
      HashMap<ThreadTypeImpl, List<ComponentInstance>> 
      threadImplToInstanceMap = new HashMap<ThreadTypeImpl, List<ComponentInstance>>(); 
      for (ComponentInstance co : threadInstanceList) {
         ThreadTypeImpl threadType = (ThreadTypeImpl)co.getComponentClassifier(); 
         if (!threadImplToInstanceMap.containsKey(threadType)) {
            threadImplToInstanceMap.put(threadType, new ArrayList<ComponentInstance>());
         } 
         threadImplToInstanceMap.get(threadType).add(co);
      }

      return threadImplToInstanceMap;
   }

   private void constructThreadImplMap() {
      threadImplementationMap = new HashMap<ThreadTypeImpl, ThreadImplementation>(threadTypeImplList.size());

      // map component instances to implementations.
      Map<ThreadTypeImpl, List<ComponentInstance>> threadImplToInstanceMap = 
            constructThreadImplToInstanceMap();

      OSModel model; 

      
      for (ThreadTypeImpl tti : threadTypeImplList) {
         
         // TODO: in reality, a thread implementation is *not* associated
         // with a given OS model (processor).  We are calling the binding 
         // of the first thread instance the thread implementation binding.
         // This is of course, very sketchy.  Once we support multiple 
         // instances, we will need to fix this, but this will break all
         // kinds of stuff, so I'm holding off until we can make this 
         // larger change.
         
         List<ComponentInstance> instances = threadImplToInstanceMap.get(tti);
         model = findOsModel(instances.get(0));
         assert(model != null); 

         ThreadImplementation threadImplementation = constructThreadImplementation(tti, model); 
         for (ComponentInstance co: instances) {
            ThreadInstance instance = new ThreadInstance(threadImplementation);
            threadImplementation.addThreadInstance(instance);
            // TODO: why do I need the thread instance map?
            this.threadInstanceMap.put(co, instance);
         }
         threadImplementationMap.put(tti, threadImplementation);
         model.getLegacyMutexList().addAll(threadImplementation.getExternalMutexList());
         model.getLegacySemaphoreList().addAll(threadImplementation.getExternalSemaphoreList());
      }
   }

   public Map<ThreadTypeImpl, ThreadImplementation> getThreadImplementationMap() {
      return threadImplementationMap;
   }

   /***************************************************************************
    * 
    * Connection and related function constructors
    * 
    ***************************************************************************/

   private SharedDataAccessor.AccessType getDataAccessRights(DataAccessImpl destAccessImpl) {
      EnumerationLiteral access = null;
      try {
         access = PropertyUtils.getEnumLiteral(destAccessImpl, PropertyUtil.ACCESS_RIGHT);
      } catch (Exception e) {
         throw new TbException("Required property 'Access_Right' not found for data access: " + destAccessImpl.getName());
      }

      SharedDataAccessor.AccessType accessType;
      if (access.getName().equalsIgnoreCase("write_only")) {
         accessType = SharedDataAccessor.AccessType.WRITE;
      } else if (access.getName().equalsIgnoreCase("read_only")) {
         accessType = SharedDataAccessor.AccessType.READ;
      } else if (access.getName().equalsIgnoreCase("read_write")) {
         accessType = SharedDataAccessor.AccessType.READ_WRITE;
      } else {
         throw new TbException("Required property 'Access_Right' has value: " + access.getName() + 
               " which is unsupported.");
      }
      return accessType;
   }

   ThreadInstance getThreadInstance(ComponentInstance inst) {
      ThreadInstance threadInst = this.threadInstanceMap.get(inst); 
      if (threadInst == null) {
         throw new TbException("For connection instance: " + inst.getName() + 
               " one of source/destination thread instances was not defined ('null').");
      }
      return threadInst;
   }

   final public static Property CUSTOM_CAMKES_CONNECTOR = Util.getPropertyDefinitionInWorkspace("TB_SYS::CAmkES_Custom_Connector");

   private PortConnection constructPortConnection(ConnectionInstance ci) {
      PortImpl destPortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getDestination());
      PortImpl sourcePortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getSource());

      if ((destPortImpl == null) || (sourcePortImpl == null)) {
         throw new TbException("For connection instance: " + ci.getName() + 
               " one of source/destination port implementations was not defined ('null').");
      }

      PortFeature sPort = portMap.get(sourcePortImpl);
      PortFeature dPort = portMap.get(destPortImpl);
      if (sPort == null || dPort == null || 
            !(sPort instanceof OutputPort) || 
            !(dPort instanceof InputPort)) {
         throw new TbException("For connection instance: " + ci.getName() + 
               " one of source/destination thread port ASTs was not defined ('null').");
      }
      OutputPort sourcePort = (OutputPort)sPort;
      InputPort destPort = (InputPort)dPort;

      // MWW 4/13/2015: check types, since AADL doesn't!!!
      if (!sourcePort.getType().equals(destPort.getType())) {
         throw new TbException("For connection instance: " + ci.getName() 
         + " source port [" + sourcePort.getName() + "] type [" + sourcePort.getType().toString() + "] " + 
         "does not match destination port [" + destPort.getName() + "] type [" + destPort.getType().toString() + "]");
      }

      // find source and destination thread instances.
      ThreadInstance srcThreadInstance = getThreadInstance(ci.getSource().getComponentInstance());
      ThreadInstance dstThreadInstance = getThreadInstance(ci.getDestination().getComponentInstance()); 


      // create connection object and connect to ports and thread instances.
      PortConnection conn = new PortConnection(Util.normalizeAadlName(ci.getName()), srcThreadInstance, dstThreadInstance, sourcePort, destPort);
      srcThreadInstance.addIsSrcOfConnection(conn);
      dstThreadInstance.addIsDstOfConnection(conn);
      sourcePort.addConnection(conn);
      destPort.addConnection(conn);

      // 7/1/2016 Check for Mailbox property
      boolean useMailbox = false; 
      try {
         useMailbox = PropertyUtils.getBooleanValue(ci, PropertyUtil.MAILBOX);
      } catch(Exception e) {}
      conn.setIsMailbox(useMailbox);

      String camkesCustomConnector = Util.getStringValueOpt(ci, CUSTOM_CAMKES_CONNECTOR);
      if (camkesCustomConnector != null) {
         conn.setExtendedData("CamkesCustomConnector", camkesCustomConnector);
      }

      return conn;
   }

   private ThreadImplementation findThreadImplementationForName(String name) {
      for (Map.Entry<ThreadTypeImpl, ThreadImplementation> entry : 
         this.threadImplementationMap.entrySet()) {
         if (entry.getKey().getName().equals(name)) {
            return entry.getValue(); 
         } else {
            logger.info("Thread name: " + entry.getKey().getName());
         }
      }
      return null;
   }

   private SharedDataAccessor constructAccess(ConnectionInstance ci) {
      ConnectionInstanceEnd source = ci.getSource();
      ConnectionInstanceEnd destination = ci.getDestination();
      DataAccessImpl destAccessImpl = 
            PortUtil.getDataAccessImplFromConnectionInstanceEnd(destination);
      DataSubcomponentImpl srcDataComponent = 
            PortUtil.getDataSubcomponentImplFromConnectionInstanceEnd(source);
      String commprimFnNameOpt = Util.getStringValueOpt(destAccessImpl, PropertyUtil.TB_SYS_COMMPRIM_SOURCE_TEXT);
      String commprimHeaderNameOpt = Util.getStringValueOpt(destAccessImpl, PropertyUtil.TB_SYS_COMMPRIM_SOURCE_HEADER);
      SharedDataAccessor.AccessType accessType = getDataAccessRights(destAccessImpl); 
      SharedData sharedData;
      OSModel destinationOS = findOsModel(destination.getComponentInstance());
      
      if (this.sharedDataMap.containsKey(srcDataComponent)) {
         sharedData = this.sharedDataMap.get(srcDataComponent);
      } else {
         String ttiName = null;
         sharedData = new SharedData(srcDataComponent.getName(), getDataType(srcDataComponent));
         this.sharedDataMap.put(srcDataComponent, sharedData);
         destinationOS.getSharedDataList().add(sharedData);
         
         if (destinationOS.getOsTarget() == OSTarget.CAmkES) {
            try {
               ttiName = Util.getStringValue(srcDataComponent, PropertyUtil.CAMKES_OWNER_THREAD);
            } catch (Exception e) {
               throw new TbException("For shared data instance: " + srcDataComponent.getName() + 
                     ": Generating to CAmkES OS and missing CAmkES_Owner_Thread property.");
            }
            ThreadImplementation ti = findThreadImplementationForName(ttiName);  
            if (ti == null) {
               throw new TbException("For shared data instance: " + srcDataComponent.getName() + 
                     ": Generating to CAmkES OS and thread id: " + ttiName + " does not map to a known thread feature in the process.");
            }
            sharedData.setCamkesOwner(ti);
         }
      }

      // find destination thread instance and implementation.
      ComponentInstance aadlThreadInstance = ci.getDestination().getComponentInstance(); 
      ThreadInstance threadInstance = this.threadInstanceMap.get(aadlThreadInstance); 
      if (threadInstance == null) {
         throw new TbException("For connection instance: " + ci.getName() + 
               "the accessing thread instance was not defined ('null').");
      }
      ThreadImplementation threadImpl = threadInstance.getThreadImplementation();

      SharedDataAccessor sharedDataAccess = 
            new SharedDataAccessor(threadImpl, destAccessImpl.getName(), 
                  sharedData, accessType, 
                  commprimFnNameOpt, commprimHeaderNameOpt);

      threadImpl.addSharedDataAccessor(sharedDataAccess); 

      return sharedDataAccess;
   }

   private RemoteProcedureGroup lookupRemoteProcedureGroup(SubprogramGroupTypeImpl subGroup) {
      RemoteProcedureGroup rpg;
      if (tlm.getRemoteProcedureGroupMap().containsKey(subGroup.getName())) {
         rpg = tlm.getRemoteProcedureGroupMap().get(subGroup.getName());
      } else {
         // construct remote procedure group!
         if (PropertyUtil.getIsExternal(subGroup)) {
            String header = Util.getStringValue(subGroup, PropertyUtil.TB_SYS_COMMPRIM_SOURCE_HEADER);
            rpg = new ExternalRemoteProcedureGroup(subGroup.getName(), header);
         } else {
            ArrayList<RemoteProcedure> remoteProcedures = new ArrayList<>(); 
            for (SubprogramAccess sa: subGroup.getOwnedSubprogramAccesses()) {
               SubprogramTypeImpl sub = (SubprogramTypeImpl)sa.getFeatureClassifier();
               List<RemoteProcedureParameter> args = new ArrayList<>(); 
   
               // if the initial parameter is an out parameter of type 
               // "returns", treat it as the return value of the function. 
               boolean initial = true;
               Type returnType = new UnitType();
               for (Parameter parm : sub.getOwnedParameters()) {
                  Type t = lookupType((DataClassifier)parm.getClassifier());
                  String id = parm.getName();
                  Direction dir = 
                        (parm.getDirection() == DirectionType.IN) ? Direction.IN :
                           (parm.getDirection() == DirectionType.OUT) ? Direction.OUT :
                              Direction.IN_OUT;
                  boolean passByReference = PropertyUtil.getIsPassByReference(parm);
                  RemoteProcedureParameter modelParam = 
                        new RemoteProcedureParameter(t, dir, id, passByReference);
   
                  // kludge for function return values.
                  if (initial && id.equalsIgnoreCase("returns") && 
                        dir == Direction.OUT) {
                     returnType = t;
                  } else {
                     args.add(modelParam);
                  }
                  initial = false;
               }
               remoteProcedures.add(new RemoteProcedure(sa.getName(), args, returnType));
            }
            rpg = new InternalRemoteProcedureGroup(remoteProcedures, subGroup.getName());
         }
         tlm.getRemoteProcedureGroupMap().put(subGroup.getName(), rpg);
      }
      return rpg;
   }


   private EndpointConnection constructSubprogramAccess(ConnectionInstance ci) {
      FeatureInstanceImpl provides = (FeatureInstanceImpl)ci.getDestination(); 
      FeatureInstanceImpl requires = (FeatureInstanceImpl)ci.getSource(); 
      if (provides.getCategory() != FeatureCategory.SUBPROGRAM_GROUP_ACCESS ||
            requires.getCategory() != FeatureCategory.SUBPROGRAM_GROUP_ACCESS) {
         throw new TbException("For subprogram access: " + provides.getCategory() + " is not currently supported by SystemBuild (only subprogram groups are supported).");
      }
      SubprogramGroupAccessImpl providesAccess = (SubprogramGroupAccessImpl)provides.getFeature(); 
      SubprogramGroupTypeImpl subGroup = (SubprogramGroupTypeImpl)providesAccess.getClassifier();

      ThreadInstance requiresInstance = getThreadInstance(requires.getComponentInstance());
      ThreadInstance providesInstance = getThreadInstance(provides.getComponentInstance()); 

      RemoteProcedureGroup rpg = lookupRemoteProcedureGroup(subGroup);
      RemoteProcedureGroupEndpoint requiresEnd = 
            new RemoteProcedureGroupEndpoint(requiresInstance.getThreadImplementation(), rpg, 
                  requires.getName(), AccessType.REQUIRES);

      RemoteProcedureGroupEndpoint providesEnd = 
            new RemoteProcedureGroupEndpoint(providesInstance.getThreadImplementation(), rpg, 
                  provides.getName(), AccessType.PROVIDES);

      EndpointConnection ec = new EndpointConnection(requiresInstance, requiresEnd, 
            providesInstance, providesEnd);

      String camkesCustomConnector = Util.getStringValueOpt(ci, CUSTOM_CAMKES_CONNECTOR);
      if (camkesCustomConnector != null) {
         ec.setExtendedData("CamkesCustomConnector", camkesCustomConnector);
      }


      
      requiresInstance.addIsRequiresOfEndpointConnectionList(ec);
      providesInstance.addIsProvidesOfEndpointConnectionList(ec);

      requiresInstance.getThreadImplementation().addRequiresRPG(requiresEnd);
      providesInstance.getThreadImplementation().addProvidesRPG(providesEnd);

      return ec;
   }

   private void initializeConnections() {
      try {
         for (ConnectionInstance ci : connectionInstances) {
            if (ci.getKind() == ConnectionKind.PORT_CONNECTION) {
               constructPortConnection(ci);
            } else if (ci.getKind() == ConnectionKind.ACCESS_CONNECTION && 
                  ci.getDestination() instanceof FeatureInstanceImpl &&
                  ci.getSource() instanceof FeatureInstanceImpl) {
               constructSubprogramAccess(ci);
            } else if (ci.getKind() == ConnectionKind.ACCESS_CONNECTION) {
               constructAccess(ci); 
            } else {
               throw new TbException("ConnectionKind: " + ci.getKind() + " is not currently supported by SystemBuild.");
            }
         }
      } catch (Exception e) {
         throw new TbException("Unexpected exception: " + e.toString() + " in AadlModelParser::initializeConnections.");
      }
   }


   public Map<DataSubcomponentImpl, SharedData> getDataMap() {
      return this.sharedDataMap;
   }

   /***************************************************************************
    * 
    * AADL model type constructors
    * 
    ***************************************************************************/

   public Map<String, Type> getAstTypes() {
      return this.tlm.getAstTypes();
   }

   private Type getDataType(PortImpl portImpl) {
      Type dataType = null;
      DataClassifier classifier = (DataClassifier) portImpl.getClassifier();
      dataType = lookupType(classifier);

      return dataType;
   }

   private Type getDataType(DataSubcomponentImpl dataImpl) {
      DataClassifier classifier = (DataClassifier) dataImpl.getClassifier();
      return lookupType(classifier);
   }

   public void harvestModelTypeData() {
      // Collect thread data types
      for (ThreadTypeImpl t : this.threadTypeImplList) {
         collectDataTypes(t);
      }

      // CHANGE: MWW: for externally defined types,
      // Do not create the type but record the header
      // for inclusion into the AADL types header.


      // create internal ast types from the AADL types
      for (DataClassifier dc : this.dataTypes) {
         createAstType(dc);
      }
      for (Type t : this.tlm.getAstTypes().values()) {
         t.init(this.tlm.getAstTypes());
      }
   }

   // find the data types associated with some "top level" element.
   public void collectDataTypes(Element elem) {
      if (elem instanceof DataPortImpl) {
         DataPortImpl dpi = (DataPortImpl) elem;
         Classifier dpiClass = dpi.getClassifier();

         if (dpiClass instanceof DataTypeImpl || dpiClass instanceof DataImplementationImpl) {
            this.dataTypes.add((DataClassifier) dpiClass);
         }
      }
      for (Element child : elem.getChildren()) {
         collectDataTypes(child);
      }
   }

   public Type lookupType(DataClassifier dc) {
      if (dc == null) {
         return null;
      }

      String dcName = getDataClassifierName(dc);

      Type ty = createAstType(dc);
      if (this.tlm.getAstTypes().containsKey(dcName)) {
         return new IdType(dcName, this.tlm.getAstTypes().get(dcName));
      } else {
         return ty;
      }
   }

   private String getDataClassifierName(DataClassifier dc) {
      String typeNameOpt = Util.getStringValueOpt(dc, 
            PropertyUtil.C_TYPE_NAME);
      String qualifiedName = dc.getQualifiedName();
      String normalizedName = Util.normalizeAadlName(qualifiedName);
      return (typeNameOpt != null) ? typeNameOpt : normalizedName; 
   }

   public Type createAstType(DataClassifier dc) {
      if (dc == null) {
         return null;
      }

      String qualifiedName = dc.getQualifiedName();
      String normalizedName = getDataClassifierName(dc);

      if (this.tlm.getAstTypes().containsKey(normalizedName)) {
         // return this.model.getAstTypes().get(normalizedName);
         Type t = this.tlm.getAstTypes().get(normalizedName);
         return new IdType(normalizedName, t);
      }

      // base types defined by the data modeling annex
      if ("Base_Types::Boolean".equals(qualifiedName)) {
         return new BoolType();
      } else if ("Base_Types::Integer_8".equals(qualifiedName)) {
         return new IntType(8, true);
      } else if ("Base_Types::Integer_16".equals(qualifiedName)) {
         return new IntType(16, true);
      } else if ("Base_Types::Integer_32".equals(qualifiedName)) {
         return new IntType(32, true);
      } else if ("Base_Types::Integer_64".equals(qualifiedName)) {
         return new IntType(64, true);
      } else if ("Base_Types::Unsigned_8".equals(qualifiedName)) {
         return new IntType(8, false);
      } else if ("Base_Types::Unsigned_16".equals(qualifiedName)) {
         return new IntType(16, false);
      } else if ("Base_Types::Unsigned_32".equals(qualifiedName)) {
         return new IntType(32, false);
      } else if ("Base_Types::Unsigned_64".equals(qualifiedName)) {
         return new IntType(64, false);
      } else if ("Base_Types::Float".equals(qualifiedName)) {
         return new RealType(FloatEnum.SINGLE);			
      } else if ("Base_Types::Float_32".equals(qualifiedName)) {
         return new RealType(FloatEnum.SINGLE);
      } else if ("Base_Types::Float_64".equals(qualifiedName)) {
         return new RealType(FloatEnum.DOUBLE);
      } else if ("Base_Types::Character".equals(qualifiedName)) {
         throw new TbException("Character types are currently unsupported");
      } else if ("Base_Types::String" == qualifiedName) {
         throw new TbException("String types are currently unsupported");
      } else if (PropertyUtil.getIsExternal(dc)) {
         try {
            String name = dc.getName();
            String header = Util.getStringValue(dc, PropertyUtil.TB_SYS_COMMPRIM_SOURCE_HEADER);
            Integer typeSize = Util.getIntegerValueOpt(dc, PropertyUtil.C_TYPE_SIZE);
            Type et;
            if (typeSize != null) {
               et = new ExternalType(name, header, typeSize.intValue());
            } else {
               et = new ExternalType(name, header);
            }
            this.tlm.getAstTypes().put(name, et);
            this.tlm.getExternalTypeHeaders().add(header);
            logger.info("Creating external type: " + name);
            return et;
         } catch (Exception e) {
            //logger.error("Error: Property " + ThreadUtil.TB_SYS_COMMPRIM_SOURCE_HEADER_NAME + " must be provided for external types");
            throw new TbException("Error: Property " + PropertyUtil.TB_SYS_COMMPRIM_SOURCE_HEADER_NAME + " must be provided for external types");
         }
      } else if (dc instanceof DataTypeImpl) {
         DataTypeImpl dti = (DataTypeImpl)dc;
         EnumerationLiteral el = PropertyUtil.getDataRepresentationName(dti);
         DataClassifier childDc = PropertyUtil.getBaseType(dti);
         int size = PropertyUtil.getDimension(dti);
         Type childElem = createAstType(childDc); 
         if ((el.getName()).equalsIgnoreCase("Array")) {
            Type at = new ArrayType(childElem, size);
            this.tlm.getAstTypes().put(normalizedName, at);
            // return new ArrayType(childElem, size);
            return new IdType(normalizedName, at);
         } else {
            throw new TbException("Examining type: " + dc.getFullName() + 
                  " found unexpected representation type: '"+ el.getName() + "'; expecting 'Array'.");
         }
      } else if (dc instanceof DataImplementationImpl) {
         DataImplementationImpl dii = (DataImplementationImpl) dc;
         EList<DataSubcomponent> subcomponents = dii.getOwnedDataSubcomponents();
         if (subcomponents.isEmpty()) {

            // check if array type.
            EnumerationLiteral el = PropertyUtil.getDataRepresentationName(dii);
            DataClassifier childDc = PropertyUtil.getBaseType(dii);
            int size = PropertyUtil.getDimension(dii);
            Type childElem = createAstType(childDc); 
            if ((el.getName()).equalsIgnoreCase("Array")) {
               ArrayType at = new ArrayType(childElem, size); 
               this.tlm.getAstTypes().put(normalizedName, at);
               // return at;
               return new IdType(normalizedName, at);
            } else {
               throw new TbException("Examining type: " + dc.getFullName() + 
                     " found unexpected representation type: '"+ dc.getName() + "'; expecting 'Array'.");
            }
         }
         else {
            RecordType rt = new RecordType();
            for (DataSubcomponent c : subcomponents) {
               Classifier subClass = c.getClassifier();
               if (subClass instanceof DataClassifier) {
                  Type subType = createAstType((DataClassifier) subClass);
                  // this.logger.info("The name of the field is: " + c.getName());
                  rt.addField(c.getName(), subType);
               } else {
                  throw new TbException(
                        "In createAstType: Subcomponent is not a data classifier");
               }
            }
            this.tlm.getAstTypes().put(normalizedName, rt);
            // return rt;
            return new IdType(normalizedName, rt);
         }
      } else {
         throw new TbException(
               "In createAstType: data classifier is not data type or data implementation");
      }
   }

   /***************************************************************************
    * 
    * Misc. model constructors
    * 
    ***************************************************************************/


   private void initializeLegacyIRQs(NamedElement proc, OSModel osModel) {
      List<String> irqStrings = PropertyUtil.getLegacyIRQList(proc);

      if (irqStrings.size() % 2 != 0) {
         throw new TbException("Error: legacy IRQ property should be list of size 2*n, where each element of n is a signal_name, handler_name pair");
      }
      Iterator<String> it1 = irqStrings.iterator();
      while (it1.hasNext()) {
         String name = it1.next();
         String handlerName = it1.next();
         ExternalISR irq = new ExternalISR(name, handlerName);
         osModel.getExternalISRList().add(irq);
      }

      List<String> irqEventStrings = PropertyUtil.getLegacyIRQEventList(proc);
      if (irqEventStrings.size() % 3 != 0) {
         throw new TbException("Error: legacy IRQ Event property should be a list of size 3*n, where each element of n is a IRQ_event_name, task_name, sig_set triple");
      }
      it1 = irqEventStrings.iterator();
      while (it1.hasNext()) {
         String eventName = it1.next();
         String taskName = it1.next();
         String signalSetString = it1.next(); 
         int signal;
         try {
            signal = Integer.parseInt(signalSetString);
         } catch (NumberFormatException e) {
            throw new TbException("Error: legacy IRQ event property: third argument of triple not a number.");
         }
         ExternalIRQEvent evt = new ExternalIRQEvent(eventName, taskName, signal);
         osModel.getExternalIRQEventList().add(evt);
      }

      List<String> externalIrqStrings = PropertyUtil.getExternalIRQList(this.systemImplementation);
      if (externalIrqStrings.size() %2 != 0) {
         throw new TbException("Error: External IRQ property should be a list of size 2*n, where each element of n is a external_irq, irq_number pair");
      }
      it1 = externalIrqStrings.iterator();
      while (it1.hasNext()) {
         String externIrqName = it1.next();
         String externIrqNumberString = it1.next();
         int externIrqNumber;
         try {
            externIrqNumber = Integer.parseInt(externIrqNumberString);
         } catch (NumberFormatException e) {
            throw new TbException("Error: External IRQ property: second argument of pair is not a number");
         }
         ExternalIRQ irq = new ExternalIRQ(externIrqName, externIrqNumber);
         osModel.getExternalIRQList().add(irq);
      }
   }

   private void initializeThreadCalendar() {

      for (ThreadImplementation ti: this.threadImplementationMap.values()) {
         for (PortFeature p: ti.getPortList()) {
            if (p instanceof InputPeriodicPort) {
               InputPeriodicPort pd = (InputPeriodicPort)p;
               ti.getModel().getThreadCalendar().addPeriodicPort(pd);
            }
         }
      }
   }

   public Map<ProcessImplementationImpl, ProcessImplementation> getProcessImplementationMap() {
      return processImplementationMap;
   }

   public void setProcessImplementationMap(Map<ProcessImplementationImpl, ProcessImplementation> processImplementationMap) {
      this.processImplementationMap = processImplementationMap;
   }

}