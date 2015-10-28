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

package edu.umn.cs.crisys.smaccm.aadl2rtos.parse;

import java.util.ArrayList;
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
import org.osate.aadl2.Parameter;
import org.osate.aadl2.PortCategory;
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
import org.osate.aadl2.impl.SubcomponentImpl;
import org.osate.aadl2.impl.SubprogramGroupAccessImpl;
import org.osate.aadl2.impl.SubprogramGroupTypeImpl;
import org.osate.aadl2.impl.SubprogramTypeImpl;
import org.osate.aadl2.impl.ThreadSubcomponentImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.ConnectionKind;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.impl.FeatureInstanceImpl;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.Direction;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroupEndpoint.AccessType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureParameter;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model.OSTarget;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.antlr.*;

public class AadlModelParser {
	private SystemImplementation systemImplementation;
	private SystemInstance systemInstance;

	// containers for AADL AST objects
	private ArrayList<ThreadTypeImpl> threadTypeImplList;
	
	// Instance objects
  private ArrayList<ComponentInstance> threadInstanceList;
  private ArrayList<ConnectionInstance> connectionInstances;

	// TODO: Thread names do not have to be globally unique: 
	private Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap;
	private Map<DataSubcomponentImpl, SharedData> sharedDataMap = 
	    new HashMap<DataSubcomponentImpl, SharedData>();	
	private HashMap<PortImpl, DataPort> portMap = new HashMap<PortImpl, DataPort>();
	private Map<ComponentInstance, ThreadInstance> threadInstanceMap;
	private Set<DataClassifier> dataTypes = new HashSet<DataClassifier>();
	
	private Model model;
	
	private Logger logger;
	
	// Model constructor
	public AadlModelParser(SystemImplementation sysimpl, SystemInstance si, Model model, Logger logger) {
		this.systemImplementation = sysimpl;
		this.systemInstance = si;
		this.logger = logger;
		this.model = model;
		
		// re-init the counters.
		PortConnection.init();
		ThreadInstance.init();
		
		// find the OS and target hardware
		String OS = PropertyUtil.getOS(sysimpl);
	  if ("echronos".equalsIgnoreCase(OS)) {
	    model.setOsTarget(Model.OSTarget.eChronos);
	  } else if ("camkes".equalsIgnoreCase(OS)) {
	    model.setOsTarget(Model.OSTarget.CAmkES);
	  } else {
	    this.logger.error("OS target: [" + OS + "] not recognized by aadl2rtos");
	    throw new Aadl2RtosException("Parse failure on OS target property ");
	  }

	  String HW = PropertyUtil.getHW(sysimpl); 
	  model.setHWTarget(HW);
	  
	  // note: may be null, and that's o.k.
	  model.setOutputDirectory(Util.getStringValueOpt(sysimpl, PropertyUtil.SMACCM_SYS_OUTPUT_DIRECTORY));
	  
		threadTypeImplList = new ArrayList<ThreadTypeImpl>();
		threadInstanceList = new ArrayList<ComponentInstance>();
		threadInstanceMap = new HashMap<ComponentInstance, ThreadInstance>();
		
		// Connection instances
		connectionInstances = new ArrayList<ConnectionInstance>();

		// Parse existing AADL model
		findThreadTypeImpls(systemImplementation);
		findTopLevelComponentInstances(systemInstance);

		
		// create properties related to timers.
		try {
		  this.model.generateSystickIRQ = PropertyUtils.getBooleanValue(systemImplementation, PropertyUtil.GENERATE_SCHEDULER_SYSTICK_IRQ);
		  this.model.externalTimerComponent = PropertyUtils.getBooleanValue(systemImplementation, PropertyUtil.EXTERNAL_TIMER_COMPONENT);
		} catch (Exception e) {
      this.logger.error("Unexpected internal exception: properties [generateSystickIRQ, externalTimerComponent] should have default value in SMACCM_SYS, but were not found.");
      throw new Aadl2RtosException("Parse failure on one of [generateSystickIRQ, externalTimerComponent] target property ");
		}
		
		try {
		  if (this.model.getOsTarget() == OSTarget.CAmkES) {
		    this.model.camkesTimeServerAadlThreadMinIndex = (int)PropertyUtils.getIntegerValue(systemImplementation, PropertyUtil.CAMKES_TIME_SERVER_AADL_THREAD_MIN_INDEX);
		    this.model.camkesDataportRpcMinIndex = (int)PropertyUtils.getIntegerValue(systemImplementation, PropertyUtil.CAMKES_DATAPORT_RPC_MIN_INDEX);
		  }
		} catch (Exception e) {
      this.logger.error("Properties camkesTimeServerAadlThreadMinIndex and camkesDataportRpcMinIndex must be assigned for CAmkES target.");
      throw new Aadl2RtosException("Parse failure on one of [camkesTimeServerAadlThreadMinIndex, camkesDataportRpcMinIndex] target property ");
		}
		
		if (this.model.externalTimerComponent) {
		  try {
		    this.model.camkesExternalTimerCompletePath = PropertyUtils.getStringValue(systemImplementation, PropertyUtil.CAMKES_EXTERNAL_TIMER_COMPLETE_PATH);
		    this.model.camkesExternalTimerInterfacePath = PropertyUtils.getStringValue(systemImplementation, PropertyUtil.CAMKES_EXTERNAL_TIMER_INTERFACE_PATH);
		  } catch (Exception e) {
		    this.logger.error("Error in system implementation: if property External_Timer_Component is true, then properties: {SMACCM_SYS::CAmkES_External_Timer_Interface_Path, SMACCM_SYS::CAmkES_External_Timer_Complete_Path} must also be defined.");
		    throw new Aadl2RtosException("Parse failure on external timer component properties.");
		  }
		} else {
		  try {
		    this.model.camkesInternalTimerTimersPerClient = (int)PropertyUtils.getIntegerValue(systemImplementation, PropertyUtil.CAMKES_INTERNAL_TIMER_TIMERS_PER_CLIENT);
		  } catch (Exception e) {
	      this.logger.error("Unexpected internal exception: property [camkesInternalTimerTimersPerClient] should have default value but were not found.");
	      throw new Aadl2RtosException("Parse failure on [camkesInternalTimerTimersPerClient] target property ");
		  }
		}
		
		try {
		  this.model.eChronosGenerateCModules = PropertyUtils.getBooleanValue(systemImplementation, PropertyUtil.ECHRONOS_GENERATE_C_MODULES);
		  if (this.model.eChronosGenerateCModules) {
		    this.model.eChronosCModulePath = PropertyUtils.getStringValue(systemImplementation, PropertyUtil.ECHRONOS_C_MODULE_PATH);
		  }
		} catch (Exception e) {
      this.logger.error("If eChronosGenerateCModules is 'true', then eChronosCModulePath must be defined.");
      throw new Aadl2RtosException("Parse failure on eChronosCModulePath target property ");
    }
		// Initialize thread implementations
		constructThreadImplMap();

		// Initialize connections
		initializeConnections();
		
		// Initialize Periodic Dispatcher
		initializeThreadCalendar(); 
		
		// grab all files referenced in the model.
		initializeFiles();
		initializeLegacyIRQs();
		
		// Harvest model type data
		harvestModelTypeData();
		
		// initialize the model thread and shared data lists.
		this.model.threadImplementationList = new ArrayList<ThreadImplementation>(this.threadImplementationMap.values());
		this.model.sharedDataList = new ArrayList<SharedData>(this.sharedDataMap.values());
	}

	/***************************************************************************
   * 
   * Functions for filtering AADL elements for further processing
   * 
   ***************************************************************************/

  private void findTopLevelComponentInstances(SystemInstance top) {
    connectionInstances.addAll(top.getAllConnectionInstances());

    for (ComponentInstance ci : top.getAllComponentInstances()) {
      if (ci.getCategory() == ComponentCategory.THREAD) {
        threadInstanceList.add(ci);
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
         throw new Aadl2RtosException("missing entrypoints");
      }
      List<String> files = Util.getSourceTextListOpt(port,PropertyUtil.SOURCE_TEXT);
      String signal_name = Util.getStringValue(port, PropertyUtil.SMACCM_SYS_SIGNAL_NAME);
      String flih_handler = Util.getStringValue(port, PropertyUtil.ISR_HANDLER);
      int signal_number ;
      try {
        signal_number = (int)PropertyUtils.getIntegerValue(port, PropertyUtil.SMACCM_SYS_SIGNAL_NUMBER);
      } catch (NumberFormatException nfe) {
        logger.error("ISR Port " + port.getName() + ": signal number is not a positive integer. Original error: " + nfe.toString());
        throw new Aadl2RtosException("ISR construction error");
      } catch (PropertyNotPresentException pnpe) {
        signal_number = InputIrqPort.NO_SIGNAL_NUMBER;
        
        // signal number is necessary for CAmkES, but not eChronos
        if (model.getOsTarget() == Model.OSTarget.CAmkES) {
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
      String sendsEventsTo = Util.getStringValueOpt(port, PropertyUtil.SMACCM_SYS_SENDS_EVENTS_TO);
      ip.setOptSendsEventsToString(sendsEventsTo);
      if (files != null) {
    	  ip.setImplementationFileList(files);
      }
      return ip;
      
    } catch (Exception e) {
      logger.error("Exception in construction of ISR port: " + port.getName() + " (likely missing one of {Compute_Entrypoint, SMACCM_SYS::Signal_Name, SMACCM_SYS::First_Level_Interrupt_Handler}).  Error code: " + 
          e.toString());
      throw new Aadl2RtosException("Problem constructing ISR port.");
    }
  }
  
	InputEventPort addInputEventPort(PortImpl port, Type datatype, ThreadImplementation ti) {
    int queueSize = PropertyUtil.getQueueSize(port); 
    InputEventPort iep = new InputEventPort(port.getName(), datatype, ti, queueSize);
    
    List<String> entrypoints = PropertyUtil.getComputeEntrypointList(port);
    if (entrypoints != null) {
      ArrayList<ExternalHandler> ehl = new ArrayList<ExternalHandler>();
      for (String s: entrypoints) {
          ExternalHandler eh = new ExternalHandler(s);
          ehl.add(eh);
      }
      List<String> files = Util.getSourceTextListOpt(port,PropertyUtil.SOURCE_TEXT);
      String sendsEventsTo = Util.getStringValueOpt(port, PropertyUtil.SMACCM_SYS_SENDS_EVENTS_TO);
      iep.setExternalHandlerList(ehl);
      iep.setOptSendsEventsToString(sendsEventsTo);
      if (files != null) {
    	  iep.setImplementationFileList(files);
      }
    } else {
      logger.warn("Warning: event port: " + port.getName() + " in thread: " + ti.getName() + " does not have a compute entrypoint and will not be dispatched.");
    }
    return iep;
	}
	
	void addPort(PortImpl port, ThreadImplementation ti) {
	  DataPort dp = null;
	  if (port.getDirection() == DirectionType.IN_OUT) {
	    this.constructWarning(ti.getName(), "IN_OUT ports", port.getName(), null);
	    throw new Aadl2RtosException("Gentlemen do not process IN_OUT ports");
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
          dp = addIrqHandler(port, ti); 
        } else {
          dp = addInputEventPort(port, new UnitType(), ti);
        }
      } else {
        dp = new OutputEventPort(port.getName(), new UnitType(), ti);
      }
    } else if (port.getCategory() == PortCategory.EVENT_DATA) {
      if (port.getDirection() == DirectionType.IN) {
        if (PropertyUtil.getIsIsr(port)) {
          throw new Aadl2RtosException("ISR ports can only be event ports.");
        } else {
          dp = addInputEventPort(port, datatype, ti);
        }
      } else {
        dp = new OutputEventPort(port.getName(), datatype, ti);
      }
    }
    ti.addPort(dp);

    String initializeEntrypointSourceText = Util.getStringValueOpt(port, PropertyUtil.INITIALIZE_ENTRYPOINT_SOURCE_TEXT);
    String commprimFnNameOpt = Util.getStringValueOpt(port, PropertyUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
    String commprimHeaderNameOpt = Util.getStringValueOpt(port, PropertyUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
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
      throw new Aadl2RtosException(
          "Dispatch protocol not found for thread: " + tti.getFullName());
    }
    String dpName = dispatchProtocol.getName(); 
    if (isPassive) { 
      if (!"Aperiodic".equalsIgnoreCase(dpName)) {
        throw new Aadl2RtosException(
            "For passive thread " + tti.getFullName() + "'Aperiodic' is the only currently supported dispatch protocol.");
      }
    }
    else {
      if (!("Sporadic".equalsIgnoreCase(dpName) || 
            "Periodic".equalsIgnoreCase(dpName) ||
            "Hybrid".equalsIgnoreCase(dpName))) {
        throw new Aadl2RtosException(
            "For active thread " + tti.getFullName() + ", dispatch protocol must be one of {Sporadic, Periodic, Hybrid}.");
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
        
        String sendsEventsTo = Util.getStringValueOpt(tti, PropertyUtil.SMACCM_SYS_SENDS_EVENTS_TO);
        ipp.setPeriod(period);
        ipp.setOptSendsEventsToString(sendsEventsTo);
        List<String> files = Util.getSourceTextListOpt(tti, PropertyUtil.SOURCE_TEXT);
        if (files != null) {
        	ipp.setImplementationFileList(files);
        }
        ipp.setExternalHandlerList(handlerList);
        ti.addPort(ipp);
      } catch (Exception e) {
        throw new Aadl2RtosException(
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
      throw new Aadl2RtosException("For dispatch contract, unable to match id: " + id + " to an output port.\n");
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
      String sendsEventsTo = Util.getStringValueOpt(tti, PropertyUtil.SMACCM_SYS_SENDS_EVENTS_TO);
      List<OutputEventPort> outputs = ti.getAllOutputEventPorts();
      SendsToWalker threadSurrogate = null;
      
      if (sendsEventsTo != null) {
        threadSurrogate = constructDispatchLimit(sendsEventsTo, outputs); 
      }
      
      // ti.setDispatchLimits(surrogate.getContracts());
      // the dispatch limits really should be defined per dispatcher.  For now,
      // we are setting them per-thread.
      
      List<DispatchableInputPort> dispList = ti.getDispatcherList();
      for (DispatchableInputPort d : dispList) {
        if (d.getOptSendsEventsToString() != null) {
          String set = d.getOptSendsEventsToString();
          SendsToWalker s = constructDispatchLimit(set, outputs);
          d.setDispatchLimits(s.getContracts());
        } else if (threadSurrogate != null){
          d.setDispatchLimits(threadSurrogate.getContracts());
        } else {
          throw new Aadl2RtosException("No dispatch limit (Sends_Events_To) specified for dispatcher " + d.getName());
        }
      }
    }
    catch (Exception ae) {
      Aadl2RtosException excp = new Aadl2RtosException("In thread " + ti.getName() + ": " + ae);
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
  
  private ThreadImplementation constructThreadImplementation(ThreadTypeImpl tti) {
    String name = tti.getName();
    boolean isPassive = PropertyUtil.getThreadType(tti);
    boolean isExternal = PropertyUtil.getIsExternal(tti);
    int priority = -1; 
    int stackSize = 4096;
    
    if (!(isPassive || isExternal)) {
      priority = PropertyUtil.getPriority(tti);
      
      // MWW TODO: temporary until after May 15 code drop
      stackSize = java.lang.Math.max(PropertyUtil.getStackSizeInBytes(tti), 4096);
    } else {
      // TODO: Compute priorities for passive threads.
      priority = 200; 
      try {
        PropertyUtil.getPriority(tti);
        logger.warn("Warning: priority ignored for passive/external thread: " + name);
      } catch (Aadl2RtosException e) {}
      try {
        PropertyUtil.getStackSizeInBytes(tti); 
        logger.warn("Warning: stack size ignored for passive/external thread: " + name);
      } catch (Aadl2RtosException e) {}
    }
    
    String generatedEntrypoint = tti.getFullName();
    ThreadImplementation ti = 
        new ThreadImplementation(model, name, priority, stackSize, generatedEntrypoint, isPassive);

    double minComputeTime = PropertyUtil.getMinComputeExecutionTimeInMicroseconds(tti);
    double maxComputeTime = PropertyUtil.getMaxComputeExecutionTimeInMicroseconds(tti);
    boolean requiresTimeServices = PropertyUtils.getBooleanValue(tti, PropertyUtil.REQUIRES_TIME_SERVICES);
    List<String> externalMutexList = (ArrayList<String>) PropertyUtil.getExternalMutexList(tti);
    List<String> externalSemaphoreList = (ArrayList<String>) PropertyUtil.getExternalSemaphoreList(tti);
            
    ti.setMinExecutionTime(minComputeTime);
    ti.setMaxExecutionTime(maxComputeTime);
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
		
		for (ThreadTypeImpl tti : threadTypeImplList) {
			ThreadImplementation threadImplementation = constructThreadImplementation(tti); 
			if (threadImplToInstanceMap.containsKey(tti )) {
        for (ComponentInstance co: threadImplToInstanceMap.get(tti)) {
          ThreadInstance instance = new ThreadInstance(threadImplementation);
          threadImplementation.addThreadInstance(instance);
          // TODO: why do I need the thread instance map?
          this.threadInstanceMap.put(co, instance);
        }
			}
			else {
        throw new Aadl2RtosException("Unable to find any thread instances for implementation: " + tti.getName()) ; 
			}
			threadImplementationMap.put(tti, threadImplementation);
      this.model.legacyMutexList.addAll(threadImplementation.getExternalMutexList());
      this.model.legacySemaphoreList.addAll(threadImplementation.getExternalSemaphoreList());
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
      throw new Aadl2RtosException("Required property 'Access_Right' not found for data access: " + destAccessImpl.getName());
    }
    
    SharedDataAccessor.AccessType accessType;
    if (access.getName().equalsIgnoreCase("write_only")) {
      accessType = SharedDataAccessor.AccessType.WRITE;
    } else if (access.getName().equalsIgnoreCase("read_only")) {
      accessType = SharedDataAccessor.AccessType.READ;
    } else if (access.getName().equalsIgnoreCase("read_write")) {
      accessType = SharedDataAccessor.AccessType.READ_WRITE;
    } else {
      throw new Aadl2RtosException("Required property 'Access_Right' has value: " + access.getName() + 
          " which is unsupported.");
    }
    return accessType;
	}

	ThreadInstance getThreadInstance(ComponentInstance inst) {
	  ThreadInstance threadInst = this.threadInstanceMap.get(inst); 
	  if (threadInst == null) {
	    throw new Aadl2RtosException("For connection instance: " + inst.getName() + 
	        " one of source/destination thread instances was not defined ('null').");
	  }
	  return threadInst;
	}

	private PortConnection constructPortConnection(ConnectionInstance ci) {
    PortImpl destPortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getDestination());
    PortImpl sourcePortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getSource());

    if ((destPortImpl == null) || (sourcePortImpl == null)) {
      throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
          " one of source/destination port implementations was not defined ('null').");
    }
    
    DataPort sPort = portMap.get(sourcePortImpl);
    DataPort dPort = portMap.get(destPortImpl);
    if (sPort == null || dPort == null || 
        !(sPort instanceof OutputPort) || 
        !(dPort instanceof InputPort)) {
      throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
          " one of source/destination thread port ASTs was not defined ('null').");
    }
    OutputPort sourcePort = (OutputPort)sPort;
    InputPort destPort = (InputPort)dPort;
            
    // MWW 4/13/2015: check types, since AADL doesn't!!!
    if (!sourcePort.getType().equals(destPort.getType())) {
      throw new Aadl2RtosException("For connection instance: " + ci.getName() 
          + " source port [" + sourcePort.getName() + "] type [" + sourcePort.getType().toString() + "] " + 
          "does not match destination port [" + destPort.getName() + "] type [" + destPort.getType().toString() + "]");
    }
    
    // find source and destination thread instances.
    ThreadInstance srcThreadInstance = getThreadInstance(ci.getSource().getComponentInstance());
    ThreadInstance dstThreadInstance = getThreadInstance(ci.getDestination().getComponentInstance()); 
    
    
    // create connection object and connect to ports and thread instances.
    PortConnection conn = new PortConnection(srcThreadInstance, dstThreadInstance, sourcePort, destPort);
    srcThreadInstance.addIsSrcOfConnection(conn);
    dstThreadInstance.addIsDstOfConnection(conn);
    sourcePort.addConnection(conn);
    destPort.addConnection(conn);
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
    ConnectionInstanceEnd destination = ci.getDestination();
	  DataAccessImpl destAccessImpl = 
        PortUtil.getDataAccessImplFromConnectionInstanceEnd(destination);
    DataSubcomponentImpl srcDataComponent = 
        PortUtil.getDataSubcomponentImplFromConnectionInstanceEnd(ci.getSource());
    String commprimFnNameOpt = Util.getStringValueOpt(destAccessImpl, PropertyUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
    String commprimHeaderNameOpt = Util.getStringValueOpt(destAccessImpl, PropertyUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
    SharedDataAccessor.AccessType accessType = getDataAccessRights(destAccessImpl); 

    SharedData sharedData;
    if (this.sharedDataMap.containsKey(srcDataComponent)) {
      sharedData = this.sharedDataMap.get(srcDataComponent);
    } else {
      String ttiName = null;
      sharedData = new SharedData(srcDataComponent.getName(), getDataType(srcDataComponent));
      this.sharedDataMap.put(srcDataComponent, sharedData);
      if (model.getOsTarget() == OSTarget.CAmkES) {
        try {
          ttiName = Util.getStringValue(srcDataComponent, PropertyUtil.CAMKES_OWNER_THREAD);
        } catch (Exception e) {
            throw new Aadl2RtosException("For shared data instance: " + srcDataComponent.getName() + 
                ": Generating to CAmkES OS and missing CAmkES_Owner_Thread property.");
        }
        ThreadImplementation ti = findThreadImplementationForName(ttiName);  
        if (ti == null) {
          throw new Aadl2RtosException("For shared data instance: " + srcDataComponent.getName() + 
                ": Generating to CAmkES OS and thread id: " + ttiName + " does not map to a known thread feature in the process.");
        }
        sharedData.setCamkesOwner(ti);
      }
    }
    
    // find destination thread instance and implementation.
    ComponentInstance aadlThreadInstance = ci.getDestination().getComponentInstance(); 
    ThreadInstance threadInstance = this.threadInstanceMap.get(aadlThreadInstance); 
    if (threadInstance == null) {
      throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
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
	  if (model.getRemoteProcedureGroupMap().containsKey(subGroup.getName())) {
      rpg = model.getRemoteProcedureGroupMap().get(subGroup.getName());
    } else {
      // construct remote procedure group!
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
      rpg = new RemoteProcedureGroup(remoteProcedures, subGroup.getName());
      model.getRemoteProcedureGroupMap().put(subGroup.getName(), rpg);
    }
	  return rpg;
	}
	
	
	private EndpointConnection constructSubprogramAccess(ConnectionInstance ci) {
	  FeatureInstanceImpl provides = (FeatureInstanceImpl)ci.getDestination(); 
	  FeatureInstanceImpl requires = (FeatureInstanceImpl)ci.getSource(); 
    if (provides.getCategory() != FeatureCategory.SUBPROGRAM_GROUP_ACCESS ||
        requires.getCategory() != FeatureCategory.SUBPROGRAM_GROUP_ACCESS) {
      throw new Aadl2RtosException("For subprogram access: " + provides.getCategory() + " is not currently supported by SystemBuild (only subprogram groups are supported).");
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

    requiresInstance.addIsRequiresOfEndpointConnectionList(ec);
    providesInstance.addIsProvidesOfEndpointConnectionList(ec);
    
    requiresInstance.getThreadImplementation().addRequiresRPG(requiresEnd);
    providesInstance.getThreadImplementation().addProvidesRPG(providesEnd);
    
    return ec;
	}
	/*
	 * 
	 *     if (destination instanceof FeatureInstanceImpl) {
      FeatureInstanceImpl impl = (FeatureInstanceImpl)destination;
      if (impl.getCategory() == FeatureCategory.SUBPROGRAM_GROUP_ACCESS) {
        if (ci.getInstantiatedObjects().size() != 1) {
          throw new Aadl2RtosException("Internal error: more than one connection instance per port");
        }
        Object obj = ci.getInstantiatedObjects().get(0);
        
        if (!(ci.getInstantiatedObjects().get(0) instanceof BusSubcomponentImpl)) {
          return null;
        }
        
      }
    }

	 */
	private void initializeConnections() {
		try {
			for (ConnectionInstance ci : connectionInstances) {
			  if (ci.getKind() == ConnectionKind.PORT_CONNECTION) {
			    PortConnection conn = constructPortConnection(ci);
			    this.model.connectionList.add(conn);
	      } else if (ci.getKind() == ConnectionKind.ACCESS_CONNECTION && 
	                 ci.getDestination() instanceof FeatureInstanceImpl &&
	                 ci.getSource() instanceof FeatureInstanceImpl) {
	        constructSubprogramAccess(ci);
	      } else if (ci.getKind() == ConnectionKind.ACCESS_CONNECTION) {
	        constructAccess(ci); 
	      } else {
	        throw new Aadl2RtosException("ConnectionKind: " + ci.getKind() + " is not currently supported by SystemBuild.");
	      }
			}
		} catch (Exception e) {
			 throw new Aadl2RtosException("Unexpected exception: " + e.toString() + " in AadlModelParser::initializeConnections.");
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
		return this.model.astTypes;
	}

  private Type getDataType(PortImpl portImpl) {
    Type dataType = null;
    DataClassifier classifier = (DataClassifier) portImpl.getClassifier();
    // String dcName = Util.normalizeAadlName(classifier);

//    if (!this.model.astTypes.containsKey(dcName)) {
//      System.out.println("Type not found: " + dcName + "\n");
//    } 
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
		for (Type t : this.model.astTypes.values()) {
			t.init(this.model.getAstTypes());
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
		if (this.model.getAstTypes().containsKey(dcName)) {
			return new IdType(dcName, this.model.getAstTypes().get(dcName));
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

		if (this.model.getAstTypes().containsKey(normalizedName)) {
			// return this.model.getAstTypes().get(normalizedName);
		  Type t = this.model.getAstTypes().get(normalizedName);
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
			throw new Aadl2RtosException("Character types are currently unsupported");
		} else if ("Base_Types::String" == qualifiedName) {
			throw new Aadl2RtosException("String types are currently unsupported");
		} else if (PropertyUtil.getIsExternal(dc)) {
		  try {
		    String name = dc.getName();
		    String header = Util.getStringValue(dc, PropertyUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
		    Type et = new ExternalType(name, header);
		    this.model.astTypes.put(name, et);
		    this.model.externalTypeHeaders.add(header);
		    logger.info("Creating external type: " + name);
		    return et;
		  } catch (Exception e) {
		    //logger.error("Error: Property " + ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER_NAME + " must be provided for external types");
		    throw new Aadl2RtosException("Error: Property " + PropertyUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER_NAME + " must be provided for external types");
		  }
		} else if (dc instanceof DataTypeImpl) {
		  DataTypeImpl dti = (DataTypeImpl)dc;
			EnumerationLiteral el = Util.getDataRepresentationName(dti);
			DataClassifier childDc = Util.getBaseType(dti);
		  int size = Util.getDimension(dti);
			Type childElem = createAstType(childDc); 
			if ((el.getName()).equalsIgnoreCase("Array")) {
			  Type at = new ArrayType(childElem, size);
			  this.model.astTypes.put(normalizedName, at);
			  // return new ArrayType(childElem, size);
	      return new IdType(normalizedName, at);
			} else {
			  throw new Aadl2RtosException("Examining type: " + dc.getFullName() + 
			      " found unexpected representation type: '"+ el.getName() + "'; expecting 'Array'.");
			}
		} else if (dc instanceof DataImplementationImpl) {
			DataImplementationImpl dii = (DataImplementationImpl) dc;
			EList<DataSubcomponent> subcomponents = dii.getOwnedDataSubcomponents();
			if (subcomponents.isEmpty()) {
				
				// check if array type.
				EnumerationLiteral el = Util.getDataRepresentationName(dii);
				DataClassifier childDc = Util.getBaseType(dii);
				int size = Util.getDimension(dii);
				Type childElem = createAstType(childDc); 
				if ((el.getName()).equalsIgnoreCase("Array")) {
				    ArrayType at = new ArrayType(childElem, size); 
				    this.model.astTypes.put(normalizedName, at);
				    // return at;
		        return new IdType(normalizedName, at);
				} else {
				   throw new Aadl2RtosException("Examining type: " + dc.getFullName() + 
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
						throw new Aadl2RtosException(
								"In createAstType: Subcomponent is not a data classifier");
					}
				}
				this.model.astTypes.put(normalizedName, rt);
				// return rt;
        return new IdType(normalizedName, rt);
			}
		} else {
			throw new Aadl2RtosException(
					"In createAstType: data classifier is not data type or data implementation");
		}
	}

  /***************************************************************************
   * 
   * Misc. model constructors
   * 
   ***************************************************************************/
  
  private void initializeFiles() {
    
    // Get dispatcher file names.
    for (ThreadImplementation i: this.getThreadImplementationMap().values()) {
      for (DataPort p : i.getPortList()) {
        if (p.getImplementationFileList() != null) {
          this.model.sourceFiles.addAll(p.getImplementationFileList());
        }
      }
    }
    
    // create initializer handler, if it exists.
    List<String> topLevelFileNames = 
        Util.getSourceTextListOpt(this.systemImplementation, PropertyUtil.SOURCE_TEXT);
 
    if (topLevelFileNames != null) {
      for (String s : topLevelFileNames) {
        logger.status("Referenced File: " + s);

        if (s.endsWith(".a")) {
          this.model.libraryFiles.add(s);
        } else {
          this.model.sourceFiles.add(s);
        }
      }
    }
  }
  
  private void initializeLegacyIRQs() {
    List<String> irqStrings = PropertyUtil.getLegacyIRQList(this.systemImplementation);
    
    if (irqStrings.size() % 2 != 0) {
      throw new Aadl2RtosException("Error: legacy IRQ property should be list of size 2*n, where each element of n is a signal_name, handler_name pair");
    }
    Iterator<String> it1 = irqStrings.iterator();
    while (it1.hasNext()) {
      String name = it1.next();
      String handlerName = it1.next();
      ExternalISR irq = new ExternalISR(name, handlerName);
      this.model.externalISRList.add(irq);
    }
    
    List<String> irqEventStrings = PropertyUtil.getLegacyIRQEventList(this.systemImplementation);
    if (irqEventStrings.size() % 3 != 0) {
      throw new Aadl2RtosException("Error: legacy IRQ Event property should be a list of size 3*n, where each element of n is a IRQ_event_name, task_name, sig_set triple");
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
          throw new Aadl2RtosException("Error: legacy IRQ event property: third argument of triple not a number.");
      }
      ExternalIRQEvent evt = new ExternalIRQEvent(eventName, taskName, signal);
      this.model.externalIRQEventList.add(evt);
    }
    
    List<String> externalIrqStrings = PropertyUtil.getExternalIRQList(this.systemImplementation);
    if (externalIrqStrings.size() %2 != 0) {
      throw new Aadl2RtosException("Error: External IRQ property should be a list of size 2*n, where each element of n is a external_irq, irq_number pair");
    }
    it1 = externalIrqStrings.iterator();
    while (it1.hasNext()) {
      String externIrqName = it1.next();
      String externIrqNumberString = it1.next();
      int externIrqNumber;
      try {
        externIrqNumber = Integer.parseInt(externIrqNumberString);
      } catch (NumberFormatException e) {
        throw new Aadl2RtosException("Error: External IRQ property: second argument of pair is not a number");
      }
      ExternalIRQ irq = new ExternalIRQ(externIrqName, externIrqNumber);
      this.model.externalIRQList.add(irq);
    }
  }

  private void initializeThreadCalendar() {
    
    for (ThreadImplementation ti: this.threadImplementationMap.values()) {
      for (DataPort p: ti.getPortList()) {
        if (p instanceof InputPeriodicPort) {
          InputPeriodicPort pd = (InputPeriodicPort)p;
          this.model.threadCalendar.addPeriodicPort(pd);
        }
      }
    }
  }
  
  
  
  
  /*  private void findAllSourceTexts(Element elem) {
    if (elem instanceof SubcomponentImpl) {
      SubcomponentImpl sub = (SubcomponentImpl) elem;
      ComponentImplementation impl = sub.getComponentImplementation();
      Classifier classifier = null; 
      
      if (elem instanceof SystemImpl) {
        SystemImpl simpl = (SystemImpl) sub;
      }
    } 
  }
  */
	
  
  // If ISR thread, create new ISR, port, and connections to receive from ISR.
  /*  
    String signal = threadImplementation.getSmaccmSysSignalOpt();
    String handler = threadImplementation.getISRHandlerName();
    InterruptServiceRoutine isr = 
         new InterruptServiceRoutine(signal, handler);
    this.model.isrList.add(isr);
    List<String> handlerList = new ArrayList<String>();
    handlerList.add(handler);
    MyPort destPort = new MyPort("smaccm_isr_input_port", 
        handlerList, threadImplementation.getFileNames().get(0), null, 
        null, null, threadImplementation, MyPort.PortType.INPUT_EVENT_PORT);
    threadImplementation.addPort(destPort);
    isr.setDestinationPort(destPort);
    for (ThreadInstance ti: threadImplementation.getThreadInstanceList()) {
      Connection c_fake = new Connection(null, ti, isr.getOutputPort(), destPort);
      this.model.connectionList.add(c_fake);
      destPort.addConnection(c_fake);
      isr.addThreadInstance(ti);
    }
  }

  private LegacyThreadImplementation constructLegacyThreadImplementation(ThreadTypeImpl tti) {
    // For thread base, 
    String name = tti.getName().toLowerCase();
    int priority = ThreadUtil.getPriority(tti);
    int stackSize = ThreadUtil.getStackSizeInBytes(tti);
    List<String> fileNames = Util.getSourceTextListOpt(tti, ThreadUtil.SOURCE_TEXT);
    List<String> legacyMutexList;
    List<String> legacySemaphoreList;
    String generatedEntrypoint;
    try { 
      legacyMutexList = (ArrayList<String>) ThreadUtil.getExternalMutexList(tti);
      legacySemaphoreList = (ArrayList<String>) ThreadUtil.getExternalSemaphoreList(tti);
      generatedEntrypoint = Util.getStringValue(tti, ThreadUtil.LEGACY_ENTRYPOINT);
      LegacyThreadImplementation thread = new LegacyThreadImplementation(model, name, priority, stackSize,  
          legacyMutexList, legacySemaphoreList, generatedEntrypoint);
      thread.setExternalReferencedFiles(fileNames);
      return thread;
    } catch (Exception e) {
      throw new Aadl2RtosException("Error: Legacy threads must have a SMACCM_SYS::Legacy_Entrypoint property defined");
    }
  }

  */ 
}