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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.SystemInstance;

import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.Connection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.InterruptServiceRoutine;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.LegacyExternalIRQ;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.LegacyIRQEvent;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.LegacyThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.MyPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadCalendar;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadImplementationBase;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadInstancePort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;

public class Model {
	private SystemImplementation systemImplementation;
	private SystemInstance systemInstance;

	// Connection instances - drives number of semaphores
	// (one function per thread implementation, pass in thread instance id)
	private List<Connection> connectionInstances = new ArrayList<Connection>();
	// private ArrayList<String> semaphoreList = new ArrayList<String>();

	// Implementation objects
	private Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap;
	private List<ThreadImplementation> threadImplementationList;
	private List<InterruptServiceRoutine> isrList;
	private List<SharedData> sharedDataList;
	private ThreadCalendar threadCalendar;
	private Set<String> sourceFiles = new HashSet<String>();
	private List<String> libraryFiles = new ArrayList<String>(); 	

	private List<LegacyThreadImplementation> legacyThreadList;
	private List<String> legacyMutexList;
	private List<String> legacySemaphoreList;
	private List<LegacyExternalIRQ> legacyExternalIRQList;
	private List<LegacyIRQEvent> legacyIRQEventList;
	private boolean generateSystickIRQ; 
	
	// private Map<ThreadImplementation, Set<Pair<MyPort, MyPort>>> threadSourcePorts = new HashMap<ThreadImplementation, Set<Pair<MyPort, MyPort>>>();

	// Model constructor
	public Model(SystemImplementation sysImpl, 
	             SystemInstance sysInst, 
	             Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap, 
               List<Connection> connectionInstances, 
               List<InterruptServiceRoutine> isrList, 
               ThreadCalendar threadCalendar, 
               Set<String> sourceFiles, 
               List<String> libraryFiles, 
               List<SharedData> sharedDataList,
               List<LegacyThreadImplementation> legacyThreadList,
               List<String> legacyMutexList,
               List<String> legacySemaphoreList,
               List<LegacyExternalIRQ> legacyExternalIRQList,
               List<LegacyIRQEvent> legacyIRQEventList, 
               boolean generateSystickIRQ) {
	  this.sharedDataList = sharedDataList; 
	  this.sourceFiles = sourceFiles;
	  this.libraryFiles = libraryFiles;
		this.threadImplementationMap = threadImplementationMap;
		this.threadImplementationList = 
		      new ArrayList<ThreadImplementation>(threadImplementationMap.values());
		this.isrList = isrList;
		this.systemImplementation = sysImpl;
		this.systemInstance = sysInst;
		this.connectionInstances = connectionInstances;
		this.threadCalendar = threadCalendar;
		this.legacyThreadList = legacyThreadList;
		this.legacyMutexList = legacyMutexList;
		this.legacySemaphoreList = legacySemaphoreList;
		this.legacyExternalIRQList = legacyExternalIRQList;
		this.legacyIRQEventList = legacyIRQEventList;
		this.generateSystickIRQ = generateSystickIRQ;
		
		// createSemaphoreList();
	}

	public List<ThreadInstance> getDestinationThreadsForPort(MyPort pi) {
		List<ThreadInstance> destThreads = new ArrayList<ThreadInstance>();

		// find all destinations of the given connection
		for (Connection ci : pi.getConnections()) {
		  destThreads.add(ci.getDestThreadInstance());
		}
		return destThreads;
	}

	public SystemImplementation getSystemImplementation() {
		return systemImplementation;
	}

	public SystemInstance getSystemInstance() {
		return systemInstance;
	}

	public Set<String> getSourceFiles()  {
	  return this.sourceFiles; 
	}
	
	public List<String> getLibraryFiles()  {
		return this.libraryFiles; 
	}
	
	public void addSourceFile(String fileName) {
	  this.sourceFiles.add(fileName);
	}
	
	public void addLibraryFile(String fileName) {
		this.libraryFiles.add(fileName);
	}	
	
	public List<InterruptServiceRoutine> getISRList() {
	  return this.isrList;
	}
	
	public String getSystemInstanceName() {
		return systemInstance.getName();
	}
	
	public ThreadCalendar getThreadCalendar() {
	  return this.threadCalendar;
	}
  
	public List<SharedData> getSharedDataList() {
	  return this.sharedDataList;
	}
/*	public List<ThreadImplementation> getTaskThreads() {
		return ThreadUtil.getTaskThreads(threadImplementationMap.values());
	}

	public List<ThreadImplementation> getISRThreads() {
		return ThreadUtil.getISRThreads(threadImplementationMap.values());
	}
*/
	
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
	
	public List<LegacyExternalIRQ> getLegacyExternalIRQs() {
	  return this.legacyExternalIRQList;
	}
	
	public List<LegacyIRQEvent> getLegacyIRQEvents() {
	  return this.legacyIRQEventList;
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

	public List<ThreadImplementation> getThreads(List<String> threadNameList) {
		List<ThreadImplementation> threadList = new ArrayList<ThreadImplementation>();
		for (String threadName : threadNameList) { 
			threadList.add(threadImplementationMap.get(threadName));
		}
		return threadList;
	}

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
}