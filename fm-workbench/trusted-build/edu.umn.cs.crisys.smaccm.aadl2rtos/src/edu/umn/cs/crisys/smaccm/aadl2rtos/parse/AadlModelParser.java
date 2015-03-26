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
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.*;
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

	
	//
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
		String OS = ThreadUtil.getOS(sysimpl);
	  if ("echronos".equalsIgnoreCase(OS)) {
	    model.setOsTarget(Model.OSTarget.eChronos);
	  } else if ("camkes".equalsIgnoreCase(OS)) {
	    model.setOsTarget(Model.OSTarget.CAmkES);
	  } else {
	    this.logger.error("OS target: [" + OS + "] not recognized by aadl2rtos");
	    throw new Aadl2RtosException("Parse failure on OS target property ");
	  }

	  String HW = ThreadUtil.getHW(sysimpl); 
	  model.setHWTarget(HW);
	  
	  // note: may be null, and that's o.k.
	  model.setOutputDirectory(Util.getStringValueOpt(sysimpl, ThreadUtil.SMACCM_SYS_OUTPUT_DIRECTORY));
	  
		threadTypeImplList = new ArrayList<ThreadTypeImpl>();
		threadInstanceList = new ArrayList<ComponentInstance>();
		threadInstanceMap = new HashMap<ComponentInstance, ThreadInstance>();
		
		// Connection instances
		connectionInstances = new ArrayList<ConnectionInstance>();

		// Parse existing AADL model
		findThreadTypeImpls(systemImplementation);
		findTopLevelComponentInstances(systemInstance);

		
		// create the SystickIRQ value, if it exists.
		try {
		  this.model.generateSystickIRQ = PropertyUtils.getBooleanValue(systemImplementation, ThreadUtil.GENERATE_SCHEDULER_SYSTICK_IRQ);
		} catch (Exception e) {}
		
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
  
  void addIrqHandler(PortImpl port, ThreadImplementation ti) {
    try {
      List<String> entrypoints = ThreadUtil.getComputeEntrypointList(port);
      if (entrypoints == null) { 
         throw new Aadl2RtosException("missing entrypoints");
      }
      List<String> files = Util.getSourceTextListOpt(port,ThreadUtil.SOURCE_TEXT);
      String signal_name = Util.getStringValue(port, ThreadUtil.SMACCM_SYS_SIGNAL_NAME);
      String flih_handler = Util.getStringValue(port, ThreadUtil.ISR_HANDLER);
      int signal_number ;
      try {
        signal_number = (int)PropertyUtils.getIntegerValue(port, ThreadUtil.SMACCM_SYS_SIGNAL_NUMBER);
      } catch (NumberFormatException nfe) {
        logger.error("ISR Port " + port.getName() + ": signal number is not a positive integer. Original error: " + nfe.toString());
        throw new Aadl2RtosException("ISR construction error");
      }
      Map<String, Long> memoryRegions;
      memoryRegions = ThreadUtil.getMemoryRegions(port);
      ArrayList<ExternalHandler> ehl = new ArrayList<ExternalHandler>();
      for (String s: entrypoints) {
          ExternalHandler eh = new ExternalHandler(s);
          ehl.add(eh);
      }
      IRQDispatcher disp = new IRQDispatcher(ti, ehl, signal_name, signal_number, flih_handler, memoryRegions);
      String sendsEventsTo = Util.getStringValueOpt(port, ThreadUtil.SMACCM_SYS_SENDS_EVENTS_TO);
      disp.setOptSendsEventsToString(sendsEventsTo);
      disp.setImplementationFileList(files);
      ti.addDispatcher(disp);
    } catch (Exception e) {
      logger.error("Exception in construction of ISR port: " + port.getName() + " (likely missing one of {Compute_Entrypoint, SMACCM_SYS::Signal_Name, SMACCM_SYS::First_Level_Interrupt_Handler}).  Error code: " + 
          e.toString());
    }
  }
  
	InputEventPort addInputEventPort(PortImpl port, Type datatype, ThreadImplementation ti) {
    int queueSize = PortUtil.getQueueSize(port); 
    InputEventPort iep = new InputEventPort(port.getName(), datatype, ti, queueSize);
    if (iep.hasData()) {
      ti.addInputEventDataPort(iep);
    } else {
      ti.addInputEventPort(iep);
    }
    
    List<String> entrypoints = ThreadUtil.getComputeEntrypointList(port);
    if (entrypoints != null) {
      ArrayList<ExternalHandler> ehl = new ArrayList<ExternalHandler>();
      for (String s: entrypoints) {
          ExternalHandler eh = new ExternalHandler(s);
          ehl.add(eh);
      }
      InputEventDispatcher disp = new InputEventDispatcher(ti, ehl, iep);
      List<String> files = Util.getSourceTextListOpt(port,ThreadUtil.SOURCE_TEXT);
      String sendsEventsTo = Util.getStringValueOpt(port, ThreadUtil.SMACCM_SYS_SENDS_EVENTS_TO);
      disp.setOptSendsEventsToString(sendsEventsTo);
      disp.setImplementationFileList(files);
      iep.setDispatcher(disp);
      ti.addDispatcher(disp);
    } else {
      logger.warn("Warning: event port: " + port.getName() + " does not have a compute entrypoint and will not be dispatched.");
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
        InputDataPort idp = new InputDataPort(port.getName(), datatype, ti);
        ti.addInputDataPort(idp);
        dp = idp;
      } else {
        OutputDataPort odp = new OutputDataPort(port.getName(), datatype, ti);
        ti.addOutputDataPort(odp);
        dp = odp;
      }
    } else if (port.getCategory() == PortCategory.EVENT) {
      if (port.getDirection() == DirectionType.IN) {
        // handle IRQs specially
        if (ThreadUtil.getIsIsr(port)) {
          dp = null;
          addIrqHandler(port, ti); 
        } else {
          dp = addInputEventPort(port, new UnitType(), ti);
        }
      } else {
        OutputEventPort oep = new OutputEventPort(port.getName(), new UnitType(), ti);
        ti.addOutputEventPort(oep);
        dp = oep;
      }
    } else if (port.getCategory() == PortCategory.EVENT_DATA) {
      if (port.getDirection() == DirectionType.IN) {
        if (ThreadUtil.getIsIsr(port)) {
          dp = null;
          throw new Aadl2RtosException("ISR ports can only be event ports.");
        } else {
          dp = addInputEventPort(port, datatype, ti);
        }
      } else {
        OutputEventPort oep = new OutputEventPort(port.getName(), datatype, ti);
        dp = oep;
        ti.addOutputEventDataPort(oep);
      }
    }

    if (dp != null) {
      String initializeEntrypointSourceText = Util.getStringValueOpt(port, ThreadUtil.INITIALIZE_ENTRYPOINT_SOURCE_TEXT);
      String commprimFnNameOpt = Util.getStringValueOpt(port, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
      String commprimHeaderNameOpt = Util.getStringValueOpt(port, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
      dp.setCommprimFnNameOpt(commprimFnNameOpt);
      dp.setCommprimHeaderNameOpt(commprimHeaderNameOpt);
      if (initializeEntrypointSourceText != null) {
        dp.setInitializeEntrypointSourceText(new ExternalHandler(initializeEntrypointSourceText));
      }
      portMap.put(port, dp);
    }
	}
	
  /***************************************************************************
   * 
   * Thread implementation constructors
   * 
   ***************************************************************************/

  
  private void setDispatchProtocol(ThreadTypeImpl tti, ThreadImplementation ti, boolean isPassive) {
    EnumerationLiteral dispatchProtocol;  
    try {
      dispatchProtocol = ThreadUtil.getDispatchProtocol(tti);
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
        int period = (int) PropertyUtils.getIntegerValue(tti, ThreadUtil.PERIOD);
        List<String> entrypointNameList = ThreadUtil.getComputeEntrypointList(tti); 
        List<ExternalHandler> handlerList = new ArrayList<ExternalHandler>();
        for (String entrypoint : entrypointNameList) {
          handlerList.add(new ExternalHandler(entrypoint)); 
        }
        Dispatcher d = new PeriodicDispatcher(ti, handlerList, period);
        String sendsEventsTo = Util.getStringValueOpt(tti, ThreadUtil.SMACCM_SYS_SENDS_EVENTS_TO);
        d.setOptSendsEventsToString(sendsEventsTo);
        d.setImplementationFileList(Util.getSourceTextListOpt(tti, ThreadUtil.SOURCE_TEXT));
        // TODO: Put the OutgoingDispatchContract here!
        ti.addDispatcher(d);
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
      String sendsEventsTo = Util.getStringValueOpt(tti, ThreadUtil.SMACCM_SYS_SENDS_EVENTS_TO);
      List<OutputEventPort> outputs = ti.getAllOutputEventPorts();
      SendsToWalker threadSurrogate = null;
      
      if (sendsEventsTo != null) {
        threadSurrogate = constructDispatchLimit(sendsEventsTo, outputs); 
      }
      
      // ti.setDispatchLimits(surrogate.getContracts());
      // the dispatch limits really should be defined per dispatcher.  For now,
      // we are setting them per-thread.
      
      List<Dispatcher> dispList = ti.getDispatcherList();
      for (Dispatcher d : dispList) {
        if (d.getOptSendsEventsToString() != null) {
          String set = d.getOptSendsEventsToString();
          SendsToWalker s = constructDispatchLimit(set, outputs);
          d.setDispatchLimits(s.getContracts());
        } else if (threadSurrogate != null){
          d.setDispatchLimits(threadSurrogate.getContracts());
        } else {
          throw new Aadl2RtosException("No dispatch limit (Sends_Outputs_To) specified for dispatcher " + d.getName());
        }
      }
    }
    catch (Exception ae) {
      Aadl2RtosException excp = new Aadl2RtosException("In thread " + ti.getName() + ": " + ae);
      throw excp;
    }
  }
  
  private void createOptInitializeEntrypoint(ThreadTypeImpl tti, ThreadImplementation ti) {
    String initializer = Util.getStringValueOpt(tti, ThreadUtil.INITIALIZE_ENTRYPOINT_SOURCE_TEXT);
    if (initializer != null) {
      ti.setInitializeEntrypointOpt(new ExternalHandler(initializer));
    }
  }
  
  private ThreadImplementation constructThreadImplementation(ThreadTypeImpl tti) {
    String name = tti.getName().toLowerCase();
    boolean isPassive = ThreadUtil.getThreadType(tti);    
    int priority = -1; 
    int stackSize = -1;
    
    if (!isPassive) {
      priority = ThreadUtil.getPriority(tti);
      stackSize = ThreadUtil.getStackSizeInBytes(tti);
    } else {
      try {
        ThreadUtil.getPriority(tti);
        logger.warn("Warning: priority ignored for passive thread: " + name);
      } catch (Aadl2RtosException e) {}
      try {
        ThreadUtil.getStackSizeInBytes(tti); 
        logger.warn("Warning: stack size ignored for passive thread: " + name);
      } catch (Aadl2RtosException e) {}
    }
    
    String generatedEntrypoint = tti.getFullName();
    ThreadImplementation ti = 
        new ThreadImplementation(model, name, priority, stackSize, generatedEntrypoint, isPassive);

    double minComputeTime = ThreadUtil.getMinComputeExecutionTimeInMicroseconds(tti);
    double maxComputeTime = ThreadUtil.getMaxComputeExecutionTimeInMicroseconds(tti);
    boolean isExternal = ThreadUtil.getIsExternal(tti);
    List<String> externalMutexList = (ArrayList<String>) ThreadUtil.getExternalMutexList(tti);
    List<String> externalSemaphoreList = (ArrayList<String>) ThreadUtil.getExternalSemaphoreList(tti);
            
    ti.setMinExecutionTime(minComputeTime);
    ti.setMaxExecutionTime(maxComputeTime);
    ti.setIsExternal(isExternal);
    ti.setExternalMutexList(externalMutexList);
    ti.setExternalSemaphoreList(externalSemaphoreList);
    
    //generatedEntrypoint = Util.getStringValue(tti, ThreadUtil.LEGACY_ENTRYPOINT);
    
    setDispatchProtocol(tti, ti, isPassive);
    createOptPeriodicHandler(tti, ti); 
    createOptInitializeEntrypoint(tti, ti); 

    List<String> fileNames = Util.getSourceTextListOpt(tti, ThreadUtil.SOURCE_TEXT);
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
      access = PropertyUtils.getEnumLiteral(destAccessImpl, ThreadUtil.ACCESS_RIGHT);
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
      throw new Aadl2RtosException("Required property 'Access_Right' has value: " + access.getName() + 
          " which is unsupported: currently only write_only and read_only are supported.");
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
	
	private SharedDataAccessor constructAccess(ConnectionInstance ci) {
    ConnectionInstanceEnd destination = ci.getDestination();
	  DataAccessImpl destAccessImpl = 
        PortUtil.getDataAccessImplFromConnectionInstanceEnd(destination);
    DataSubcomponentImpl srcDataComponent = 
        PortUtil.getDataSubcomponentImplFromConnectionInstanceEnd(ci.getSource());
    String commprimFnNameOpt = Util.getStringValueOpt(destAccessImpl, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
    String commprimHeaderNameOpt = Util.getStringValueOpt(destAccessImpl, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
    SharedDataAccessor.AccessType accessType = getDataAccessRights(destAccessImpl); 

    SharedData sharedData;
    if (this.sharedDataMap.containsKey(srcDataComponent)) {
      sharedData = this.sharedDataMap.get(srcDataComponent);
    } else {
      sharedData = new SharedData(srcDataComponent.getName(), getDataType(srcDataComponent));
      this.sharedDataMap.put(srcDataComponent, sharedData);
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
        logger.info("The subprogram type name is: " + sub.getName());
        logger.info("The subprogram access name is: " + sa.getName());
        for (Parameter parm : sub.getOwnedParameters()) {
          Type t = lookupType((DataClassifier)parm.getClassifier());
          String id = parm.getName();
          Direction dir = 
              (parm.getDirection() == DirectionType.IN) ? Direction.IN :
                (parm.getDirection() == DirectionType.OUT) ? Direction.OUT :
                  Direction.IN_OUT; 
          RemoteProcedureParameter modelParam = new RemoteProcedureParameter(t, dir, id);
          args.add(modelParam);
        }
        Type t = new UnitType();
        remoteProcedures.add(new RemoteProcedure(sa.getName(), args, t));
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
              requires.getName(), AccessType.REQUIRES, ThreadUtil.getIsExternal(requires));
    
    RemoteProcedureGroupEndpoint providesEnd = 
        new RemoteProcedureGroupEndpoint(providesInstance.getThreadImplementation(), rpg, 
            provides.getName(), AccessType.PROVIDES, ThreadUtil.getIsExternal(provides));
    
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
	        // TODO: add additional qualifier to accurately identify kind as Data Access Connection
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
        ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
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
			return this.model.getAstTypes().get(normalizedName);
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
		} else if (dc instanceof DataTypeImpl) {
		  DataTypeImpl dti = (DataTypeImpl)dc;
			EnumerationLiteral el = Util.getDataRepresentationName(dti);
			DataClassifier childDc = Util.getBaseType(dti);
		  int size = Util.getDimension(dti);
			Type childElem = createAstType(childDc); 
			if ((el.getName()).equalsIgnoreCase("Array")) {
			  Type at = new ArrayType(childElem, size);
			  this.model.astTypes.put(normalizedName, at);
			  return new ArrayType(childElem, size);
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
				    return at;
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
				return rt;
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
      for (Dispatcher d: i.getDispatcherList()) {
        if (d.getImplementationFileList() != null) {
          this.model.sourceFiles.addAll(d.getImplementationFileList());
        }
      }
    }
    
    // create initializer handler, if it exists.
    List<String> topLevelFileNames = 
        Util.getSourceTextListOpt(this.systemImplementation, ThreadUtil.SOURCE_TEXT);
 
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
    List<String> irqStrings = ThreadUtil.getLegacyIRQList(this.systemImplementation);
    
    if (irqStrings.size() % 2 != 0) {
      throw new Aadl2RtosException("Error: legacy IRQ property should be list of size 2*n, where each element of n is a signal_name, handler_name pair");
    }
    Iterator<String> it1 = irqStrings.iterator();
    while (it1.hasNext()) {
      String name = it1.next();
      String handlerName = it1.next();
      ExternalISR irq = new ExternalISR(name, handlerName);
      this.model.legacyExternalIRQList.add(irq);
    }
    
    List<String> irqEventStrings = ThreadUtil.getLegacyIRQEventList(this.systemImplementation);
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
      this.model.legacyIRQEventList.add(evt);
    }
    
    List<String> externalIrqStrings = ThreadUtil.getExternalIRQList(this.systemImplementation);
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
      for (Dispatcher d: ti.getDispatcherList()) {
        if (d instanceof PeriodicDispatcher) {
          PeriodicDispatcher pd = (PeriodicDispatcher)d;
          this.model.threadCalendar.addPeriodicDispatcher(pd);
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