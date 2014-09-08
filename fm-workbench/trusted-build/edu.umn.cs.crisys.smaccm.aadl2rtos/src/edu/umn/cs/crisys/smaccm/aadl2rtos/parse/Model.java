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
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.IRQDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy.LegacyExternalISR;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy.LegacyIRQEvent;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.legacy.LegacyThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.Connection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ExternalIRQ;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadCalendar;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementationBase;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstancePort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

public class Model {
	private String systemImplementationName;
	private String systemInstanceName;
	
	public enum OSTarget {CAmkES, eChronos}; 
	private OSTarget osTarget = OSTarget.eChronos;
	
	// Connection instances - drives number of semaphores
	// (one function per thread implementation, pass in thread instance id)
	List<Connection> connectionInstances = new ArrayList<Connection>();
	// private ArrayList<String> semaphoreList = new ArrayList<String>();


	// Implementation objects: external.
	List<ThreadImplementation> threadImplementationList = new ArrayList<ThreadImplementation>();
	List<SharedData> sharedDataList = new ArrayList<SharedData>();
	ThreadCalendar threadCalendar = new ThreadCalendar();
	Set<String> sourceFiles = new HashSet<String>();
	List<String> libraryFiles = new ArrayList<String>(); 	

	List<LegacyThreadImplementation> legacyThreadList = new ArrayList<LegacyThreadImplementation>();
	List<String> legacyMutexList = new ArrayList<String>();
	List<String> legacySemaphoreList = new ArrayList<String>();
	List<LegacyExternalISR> legacyExternalIRQList = new ArrayList<LegacyExternalISR>();
	List<LegacyIRQEvent> legacyIRQEventList = new ArrayList<LegacyIRQEvent>();
	List<ExternalIRQ> externalIRQList = new ArrayList<ExternalIRQ>();
	List<Connection> connectionList = new ArrayList<Connection>(); 
	
	// type stuff
	Map<String, Type> astTypes = new HashMap<String, Type>();
	Map<String, RemoteProcedureGroup> rpcInterfaces = new HashMap<String, RemoteProcedureGroup>(); 
	
	boolean generateSystickIRQ;
	
	public enum CommMutualExclusionPrimitive {Semaphore, SuspendInterrupt} ; 
	CommMutualExclusionPrimitive commMutexPrimitive = CommMutualExclusionPrimitive.SuspendInterrupt; 
	
	public enum ISRType {InThreadContextISR, SignalingISR} ; 
	ISRType isrType = ISRType.InThreadContextISR; 
	
	// Model constructor
	public Model(String systemImplementationName, 
	             String systemInstanceName) {
		this.systemImplementationName = systemImplementationName;
		this.systemInstanceName = systemInstanceName;
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
	
  public List<IRQDispatcher> getIRQDispatcherList() {
    List<IRQDispatcher> idList = new ArrayList<IRQDispatcher>(); 
    for (ThreadImplementation ti: this.getThreadImplementations()) {
      for (Dispatcher d: ti.getDispatcherList()) {
        if (d instanceof IRQDispatcher) {
          idList.add((IRQDispatcher)d);
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
	
	public List<ThreadImplementation> getThreadImplementations() {
		return this.threadImplementationList;
	}
	
	public List<LegacyThreadImplementation> getLegacyThreadImplementations() {
		return this.legacyThreadList;
	}
	
	public List<ThreadImplementationBase> getAllThreadImplementations() {
    List<ThreadImplementationBase> allTasks = new ArrayList<ThreadImplementationBase>();
    for (ThreadImplementation ti: getThreadImplementations()) {
      allTasks.add(ti);
    }
    for (LegacyThreadImplementation lti: getLegacyThreadImplementations()) {
      allTasks.add(lti);
    }
    return allTasks;
	}
	
	public List<ThreadImplementation> getActiveThreadImplementations() {
	  List<ThreadImplementation> activeThreads = new ArrayList<ThreadImplementation>(); 
	  for (ThreadImplementation ti: getThreadImplementations()) {
	    if (!ti.getIsPassive()) {
	      activeThreads.add(ti);
	    }
	  }
	  return activeThreads;
	}
	
  public List<ThreadImplementation> getPassiveThreadImplementations() {
    List<ThreadImplementation> t = new ArrayList<ThreadImplementation>(); 
    for (ThreadImplementation ti: getThreadImplementations()) {
      if (ti.getIsPassive()) {
        t.add(ti);
      }
    }
    return t;
  }

  /**
   * @return the rpcInterfaces
   */
  public Map<String, RemoteProcedureGroup> getRpcInterfaces() {
    return rpcInterfaces;
  }

  /**
   * @param rpcInterfaces the rpcInterfaces to set
   */
  public void setRpcInterfaces(Map<String, RemoteProcedureGroup> rpcInterfaces) {
    this.rpcInterfaces = rpcInterfaces;
  }

  public List<LegacyExternalISR> getLegacyExternalIRQs() {
	  return this.legacyExternalIRQList;
	}
	
	public List<LegacyIRQEvent> getLegacyIRQEvents() {
	  return this.legacyIRQEventList;
	}
	
	public List<ExternalIRQ> getExternalIRQs() {
		return this.externalIRQList;
	}
	
	public List<ThreadInstance> getAllThreadInstances() {
	  List<ThreadInstance> list = new ArrayList<ThreadInstance>(); 
	  for (ThreadImplementation t: getThreadImplementations()) {
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

	
	public List<Connection> getConnections() {
	  return this.connectionInstances;
	}

	public List<String> getLegacyMutexList() {
		return this.legacyMutexList;
	}
	
  public List<String> getLegacySemaphoreList() {
    return this.legacySemaphoreList;
  }

  public boolean getGenerateSystickIRQ() {
	  return this.generateSystickIRQ;
	}

  public Map<String, Type> getAstTypes() {
		return this.astTypes;
}


}