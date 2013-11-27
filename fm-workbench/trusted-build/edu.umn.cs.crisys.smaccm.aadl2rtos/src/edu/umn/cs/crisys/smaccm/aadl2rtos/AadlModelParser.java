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

package edu.umn.cs.crisys.smaccm.aadl2rtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.Element;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.Feature;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.impl.BusSubcomponentImpl;
import org.osate.aadl2.impl.DataAccessImpl;
import org.osate.aadl2.impl.DataSubcomponentImpl;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.SubcomponentImpl;
import org.osate.aadl2.impl.SystemImpl;
import org.osate.aadl2.impl.ThreadSubcomponentImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.ConnectionKind;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.IdType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.Connection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.Dispatcher.DispatcherType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ExternalHandler;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.InterruptServiceRoutine;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.MyPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.SharedDataAccessor.AccessType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadCalendar;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Pair;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.PortUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class AadlModelParser {
	private SystemImplementation systemImplementation;
	private SystemInstance systemInstance;
	private AstHelper astHelper;

	// containers for AADL AST objects
	private ArrayList<ThreadTypeImpl> threadTypeImplList;
	private Set<SystemImpl> systemImplList = new HashSet<SystemImpl>();
	
	// Instance objects
  private ArrayList<ComponentInstance> threadInstanceList;
  private ArrayList<ConnectionInstance> connectionInstances;
	private ArrayList<InstanceObject> abstractInstances;
	private ArrayList<InstanceObject> busInstances;
	private ArrayList<InstanceObject> dataInstances;
	private ArrayList<InstanceObject> deviceInstances;
	private ArrayList<InstanceObject> memoryInstances;
	private ArrayList<InstanceObject> processInstances;
	private ArrayList<InstanceObject> processorInstances;
	private ArrayList<InstanceObject> subprogramInstances;
	private ArrayList<InstanceObject> subprogramGroupInstances;
	private ArrayList<InstanceObject> systemInstances;
	private ArrayList<InstanceObject> threadGroupInstances;
	private ArrayList<InstanceObject> virtualBusInstances;
	private ArrayList<InstanceObject> virtualProcessorInstances;

	
	// Our AST containers

	// TODO: Thread names do not have to be globally unique: 
	private Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap;
	private Map<DataSubcomponentImpl, SharedData> sharedDataMap = 
	    new HashMap<DataSubcomponentImpl, SharedData>();
	
	private HashMap<PortImpl, MyPort> portMap = new HashMap<PortImpl, MyPort>();
	private HashSet<String> fileNames =new HashSet<String>();
	private ArrayList<Connection> connectionList;
	private ArrayList<InterruptServiceRoutine> isrList = new ArrayList<InterruptServiceRoutine>();
	private List<String> legacySemaphoreList = new ArrayList<String>();
	private ThreadCalendar calendar = new ThreadCalendar();
	
	// Instance map
	private Map<ComponentInstance, ThreadInstance> threadInstanceMap;

	private Logger logger;
	
	// Model constructor
	public AadlModelParser(SystemImplementation sysimpl, SystemInstance si, Logger logger) {
		this.systemImplementation = sysimpl;
		this.systemInstance = si;
		this.logger = logger;
		
		// re-init the counters.
		Connection.init();
		ThreadInstance.init();
		MyPort.init();
		
		threadTypeImplList = new ArrayList<ThreadTypeImpl>();
		threadInstanceList = new ArrayList<ComponentInstance>();
		connectionList = new ArrayList<Connection>();
		// threadSourcePorts = new HashMap<ThreadImplementation, Set<Pair<MyPort, MyPort>>>();
		threadInstanceMap = new HashMap<ComponentInstance, ThreadInstance>();
		
		// Connection instances
		connectionInstances = new ArrayList<ConnectionInstance>();

		// Instance objects
		abstractInstances = new ArrayList<InstanceObject>();
		busInstances = new ArrayList<InstanceObject>();
		dataInstances = new ArrayList<InstanceObject>();
		deviceInstances = new ArrayList<InstanceObject>();
		memoryInstances = new ArrayList<InstanceObject>();
		processInstances = new ArrayList<InstanceObject>();
		processorInstances = new ArrayList<InstanceObject>();
		subprogramInstances = new ArrayList<InstanceObject>();
		subprogramGroupInstances = new ArrayList<InstanceObject>();
		systemInstances = new ArrayList<InstanceObject>();
		threadGroupInstances = new ArrayList<InstanceObject>();
		virtualBusInstances = new ArrayList<InstanceObject>();
		virtualProcessorInstances = new ArrayList<InstanceObject>();

		// Parse existing AADL model
		findThreadTypeImpls(systemImplementation);
		findTopLevelComponentInstances(systemInstance);
		findAllSourceTexts(systemImplementation); 
		
		// Initialize AST helper
		astHelper = new AstHelper(threadTypeImplList);

		// Initialize thread implementations
		initializeThreadImplMap();

		// Initialize connections
		initializeConnections();

		// initialize shared data
		
		// Initialize Periodic Dispatcher
		initializeThreadCalendar(); 
		//addPeriodicISR(); 
		
		// grab all files referenced in the model.
		initializeFiles();
		
		// Harvest model data
		astHelper.harvestModelData();
	}

	private void initializeThreadImplMap() {
		threadImplementationMap = new HashMap<ThreadTypeImpl, ThreadImplementation>(threadTypeImplList.size());

		for (ThreadTypeImpl tti : threadTypeImplList) {
			String threadImplName = tti.getName();
			ThreadImplementation threadImplementation = new ThreadImplementation(tti, astHelper);

			for (ComponentInstance co : threadInstanceList) {
				String threadType = co.getComponentClassifier().getName().toString();

				// Fixed.
				if (threadType.equalsIgnoreCase(threadImplName)) {
					ThreadInstance instance = new ThreadInstance(co, threadImplementation, astHelper);
					threadImplementation.addThreadInstance(instance);
					this.threadInstanceMap.put(co, instance);
				}
			}
			
			if (threadImplementation.isLegacyThread()) {
				legacySemaphoreList.addAll(threadImplementation.getLegacySemaphores());
			}
			
			// If ISR thread, create new ISR, port, and connections to receive from ISR.
			if (threadImplementation.isISRThread()) {
			  String signal = threadImplementation.getSmaccmSysSignalOpt();
			  InterruptServiceRoutine isr = 
			       new InterruptServiceRoutine(signal);
			  this.isrList.add(isr);
			  List<String> signalList = new ArrayList<String>();
			  signalList.add(signal);
			  MyPort destPort = new MyPort("smaccm_isr_input_port", 
			      signalList, threadImplementation.getFileNames().get(0), null, 
			      null, null, threadImplementation, MyPort.PortType.INPUT_EVENT_PORT);
			  threadImplementation.addPort(destPort);
			  isr.setDestinationPort(destPort);
			  for (ThreadInstance ti: threadImplementation.getThreadInstanceList()) {
			    Connection c_fake = new Connection(null, ti, isr.getOutputPort(), destPort);
			    this.connectionList.add(c_fake);
			    destPort.addConnection(c_fake);
			    isr.addThreadInstance(ti);
			  }
			}
			
			// Find and add thread ports.
			EList<Feature> features = tti.getAllFeatures(); 
			for (Feature f: features) {
			  if (f instanceof PortImpl) {
			    PortImpl portImpl = (PortImpl)f;
			    Type datatype = getDataType(portImpl);
	        MyPort port = new MyPort(portImpl, datatype, threadImplementation);
			    threadImplementation.addPort(port);
			    portMap.put(portImpl, port);
			  } else if (f instanceof DataAccessImpl) {
			    // TODO: Something here.
			  }
			  
			}
			// 
			// Add dispatchers for all input event and input event data ports
			for (MyPort p : threadImplementation.getInputEventPorts()) {
			  List<String> entrypoints = p.getComputeEntrypointOpt();
			  String file = p.getSourceFileOpt();
			  // if (file == null) {
			  //   file = threadImplementation.
			  // }
			  if (entrypoints != null) {
				ArrayList<ExternalHandler> ehl = new ArrayList<ExternalHandler>();
				for (String s: entrypoints) {
			      ExternalHandler eh = new ExternalHandler(s, file);
			      ehl.add(eh);
				}
			    Dispatcher disp = new Dispatcher(threadImplementation, p, ehl);
			    threadImplementation.addDispatcher(disp);
			  } else {
			    logger.warn("Warning: event port: " + p.getName() + " does not have a compute entrypoint and will not be dispatched.");
			  }
			}			
			threadImplementationMap.put(tti, threadImplementation);
		}
	}

	private void initializeFiles() {
	  
	  // Get dispatcher file names.
	  for (ThreadImplementation i: this.getThreadImplementationMap().values()) {
	    for (Dispatcher d: i.getDispatcherList()) {
	      for (ExternalHandler h: d.getExternalHandlerList()) {
	        if (h.getHandlerFileName() != null) {
	          this.fileNames.add(h.getHandlerFileName());
	        }
	      }
	    }
	    this.fileNames.addAll(i.getFileNames());
	  }
	  
    // create initializer handler, if it exists.
    List<String> topLevelFileNames = 
        Util.getSourceTextListOpt(this.systemImplementation, ThreadUtil.SOURCE_TEXT);
    if (topLevelFileNames != null) {
      this.fileNames.addAll(topLevelFileNames);
    }
    
    for (String s: this.fileNames) {
      logger.status("Referenced File: " + s);
    }
	}
	
	private void initializeConnections() {
		try {
			for (ConnectionInstance ci : connectionInstances) {
			  if (ci.getKind() == ConnectionKind.PORT_CONNECTION) {
  				PortImpl destPortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getDestination());
  				PortImpl sourcePortImpl = PortUtil.getPortImplFromConnectionInstanceEnd(ci.getSource());
  
  				if ((destPortImpl == null) || (sourcePortImpl == null)) {
  				  throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
  				      " one of source/destination port implementations was not defined ('null').");
  				}
  				
  				MyPort sourcePort = portMap.get(sourcePortImpl);
          MyPort destPort = portMap.get(destPortImpl);
  				if (sourcePort == null || destPort == null) {
            throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
                " one of source/destination thread port ASTs was not defined ('null').");
  				}
  								
  				// find source and destination thread instances.
  				ComponentInstance aadlSrcThreadInstance = ci.getSource().getComponentInstance(); 
  				ComponentInstance aadlDstThreadInstance = ci.getDestination().getComponentInstance(); 
  				ThreadInstance srcThreadInstance = this.threadInstanceMap.get(aadlSrcThreadInstance); 
  				ThreadInstance dstThreadInstance = this.threadInstanceMap.get(aadlDstThreadInstance);
  				if ((srcThreadInstance == null) || (dstThreadInstance == null)) {
            throw new Aadl2RtosException("For connection instance: " + ci.getName() + 
                " one of source/destination thread instances was not defined ('null').");
          }
  				
  				// create connection object and connect to ports and thread instances.
  				Connection conn = new Connection(srcThreadInstance, dstThreadInstance, sourcePort, destPort);
  				srcThreadInstance.addIsSrcOfConnection(conn);
  				dstThreadInstance.addIsDstOfConnection(conn);
  				sourcePort.addConnection(conn);
  				destPort.addConnection(conn);
  				this.connectionList.add(conn);
	      } else if (ci.getKind() == ConnectionKind.ACCESS_CONNECTION) {
          DataAccessImpl destAccessImpl = 
              PortUtil.getDataAccessImplFromConnectionInstanceEnd(ci.getDestination());
          DataSubcomponentImpl srcDataComponent = 
              PortUtil.getDataSubcomponentImplFromConnectionInstanceEnd(ci.getSource());
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
          String commprimFnNameOpt = Util.getStringValueOpt(destAccessImpl, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
          String commprimHeaderNameOpt = Util.getStringValueOpt(destAccessImpl, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
          EnumerationLiteral access = null;
          try {
            access = PropertyUtils.getEnumLiteral(destAccessImpl, ThreadUtil.ACCESS_RIGHT);
          } catch (Exception e) {
            throw new Aadl2RtosException("Required property 'Access_Right' not found for data access: " + destAccessImpl.getName());
          }
          
          SharedDataAccessor.AccessType accessType;
          if (access.getName().equalsIgnoreCase("write_only")) {
            accessType = AccessType.WRITE;
          } else if (access.getName().equalsIgnoreCase("read_only")) {
            accessType = AccessType.READ;
          } else if (access.getName().equalsIgnoreCase("read_write")) {
            accessType = AccessType.READ_WRITE;
            throw new Aadl2RtosException("Required property 'Access_Right' has value: " + access.getName() + 
                " which is unsupported: currently only write_only and read_only are supported.");
          } else {
            throw new Aadl2RtosException("Required property 'Access_Right' has value: " + access.getName() + 
                " which is unsupported.");
          }
          
          SharedDataAccessor sharedDataAccess = 
              new SharedDataAccessor(threadImpl, destAccessImpl.getName(), 
                  sharedData, accessType, 
                  commprimFnNameOpt, commprimHeaderNameOpt);
          
          threadImpl.addSharedDataAccessor(sharedDataAccess); 
	      } else {
	        throw new Aadl2RtosException("ConnectionKind: " + ci.getKind() + " is not currently supported by SystemBuild.");
	      }
			}
		} catch (Exception e) {
			 throw new Aadl2RtosException("Unexpected exception: " + e.toString() + " in AadlModelParser::initializeConnections.");
		}
	}

	private void initializeThreadCalendar() {
	  
	  for (ThreadImplementation ti: this.threadImplementationMap.values()) {
	    for (Dispatcher d: ti.getDispatcherList()) {
	      if (d.getDispatcherType() == DispatcherType.PERIODIC_DISPATCHER) {
	        calendar.addPeriodicDispatcher(d);
	      }
	    }
	  }
	}
	
	/*
	private void addPeriodicISR() {
	  if (this.calendar.hasDispatchers()) {
	    this.isrList.
	  }
	}
	*/
	
	private Type getDataType(PortImpl portImpl) {
	  Type dataType = null;
    DataClassifier classifier = (DataClassifier) portImpl.getClassifier();
    String dcName = Util.normalizeAadlName(classifier);

    if (!astHelper.astTypes.containsKey(dcName)) {
      System.out.println("Type not found: " + dcName + "\n");
    } 
    dataType = astHelper.lookupType(classifier);

    return dataType;
	}

	private Type getDataType(DataSubcomponentImpl dataImpl) {
	  DataClassifier classifier = (DataClassifier) dataImpl.getClassifier();
	  return astHelper.lookupType(classifier);
	}
	
	/*	
	private Type getDataType(ConnectionInstanceEnd endPoint) {
	  NamedElement no = endPoint.getInstantiatedObjects().get(0);
		if (no instanceof PortImpl) {
		  return getDataType((PortImpl)no);
		}
		else
		   throw new Aadl2RtosException("For code generation, connections must terminate at ports.  Actual type: " + no);
	}
*/
	
	private void findAllSourceTexts(Element elem) {
/*	  if (elem instanceof SubcomponentImpl) {
	    SubcomponentImpl sub = (SubcomponentImpl) elem;
	    ComponentImplementation impl = sub.getComponentImplementation();
	    Classifier classifier = null; 
	    
	    if (elem instanceof SystemImpl) {
	      SystemImpl simpl = (SystemImpl) sub;
	    }
	  } */
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

	private void findTopLevelComponentInstances(SystemInstance top) {
		List<ComponentInstance> components = top.getAllComponentInstances();
		List<ConnectionInstance> connections = top.getAllConnectionInstances();

		for (ConnectionInstance ci : connections) {
			connectionInstances.add(ci);
		}

		for (ComponentInstance ci : components) {
			ComponentCategory category = ci.getCategory();

			if (category == ComponentCategory.ABSTRACT) {
				abstractInstances.add(ci);
			} else if (category == ComponentCategory.BUS) {
				busInstances.add(ci);
			} else if (category == ComponentCategory.DATA) {
				dataInstances.add(ci);
			} else if (category == ComponentCategory.DEVICE) {
				deviceInstances.add(ci);
			} else if (category == ComponentCategory.MEMORY) {
				memoryInstances.add(ci);
			} else if (category == ComponentCategory.PROCESS) {
				processInstances.add(ci);
			} else if (category == ComponentCategory.PROCESSOR) {
				processorInstances.add(ci);
			} else if (category == ComponentCategory.SUBPROGRAM) {
				subprogramInstances.add(ci);
			} else if (category == ComponentCategory.SUBPROGRAM_GROUP) {
				subprogramGroupInstances.add(ci);
			} else if (category == ComponentCategory.SYSTEM) {
				systemInstances.add(ci);
			} else if (category == ComponentCategory.THREAD) {
				threadInstanceList.add(ci);
			} else if (category == ComponentCategory.THREAD_GROUP) {
				threadGroupInstances.add(ci);
			} else if (category == ComponentCategory.VIRTUAL_BUS) {
				virtualBusInstances.add(ci);
			} else if (category == ComponentCategory.VIRTUAL_PROCESSOR) {
				virtualProcessorInstances.add(ci);
			}
		}
	}

	public AstHelper getAstHelper() {
		return astHelper;
	}

  public Map<DataSubcomponentImpl, SharedData> getDataMap() {
    return this.sharedDataMap;
  }

  public Map<ThreadTypeImpl, ThreadImplementation> getThreadImplementationMap() {
		return threadImplementationMap;
	}
	
	public List<InterruptServiceRoutine> getISRList() {
	  return this.isrList;
	}
	
	public List<Connection> getConnectionList() {
	  return this.connectionList;
	}
	
	public ThreadCalendar getThreadCalendar() {
	  return this.calendar;
	}
	
	public Set<String> getFileNames() {
	  return this.fileNames;
	}

	public List<String> getLegacySemaphoreList() {
		return this.legacySemaphoreList;
	}
}