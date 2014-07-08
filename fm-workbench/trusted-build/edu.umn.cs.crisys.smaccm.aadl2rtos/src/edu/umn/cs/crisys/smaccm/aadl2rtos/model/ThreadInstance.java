package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.instance.ComponentInstance;

public class ThreadInstance {
  private static int threadIdCounter = 0;
  private int threadId = 0;
  
  private ThreadImplementation impl;
	
	private ComponentInstance aadlInstance;
	private ArrayList<Connection> isSrcOfConnectionList = new ArrayList<Connection>();
	private ArrayList<Connection> isDstOfConnectionList = new ArrayList<Connection>(); 
	
	public static void init() {
	  ThreadInstance.threadIdCounter = 0;
	}
	
	//output ISR port/ ISR connection b/w outputisrport and inputeventport
	public ThreadInstance(ThreadImplementation impl) {
	  this.impl = impl;
	  this.threadId = ThreadInstance.threadIdCounter++; 
	}
	
	public String getName() {
	  return (impl.getName() + "_instance_" + getThreadId()).toLowerCase();
	}
	
	public String getGeneratedEntrypoint() {
	  return impl.getGeneratedEntrypoint() + "_Instance_" + getThreadId();
	}
	
	public String getKochabThreadId() {
	  return "TASK_ID_" + (this.impl.getName().toUpperCase());
	}
	
	public List<ThreadInstancePort> getThreadInstanceInputPorts() {
	  ArrayList<ThreadInstancePort> ports = new ArrayList<ThreadInstancePort>();
	  for (MyPort p: this.getThreadImplementation().getInputPorts()) {
	    ThreadInstancePort tip = new ThreadInstancePort(this, p);
	    ports.add(tip);
	  }
	  return ports;
	}
	
	public void addIsSrcOfConnection(Connection c) {
		 this.isSrcOfConnectionList.add(c);
	}

  public void addIsDstOfConnection(Connection c) {
     this.isDstOfConnectionList.add(c);
  }

  public ThreadImplementation getThreadImplementation() {
	  return this.impl;
	}
	
  public int getThreadId() {
    return this.threadId;
  }
	public ComponentInstance getAadlInstance() {
	  return this.aadlInstance;
	}
	
	public ArrayList<Connection> getIsSrcOfConnectionList() {
		return isSrcOfConnectionList;
	}
	
	public ArrayList<Connection> getIsDstOfConnectionList() {
	  return isDstOfConnectionList;
	}
	
	public int getStackSize() {
		return this.impl.getStackSize();
	}
}
