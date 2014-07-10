package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;

//import org.osate.aadl2.instance.ConnectionInstanceEnd;

//import edu.umn.cs.crisys.smaccm.aadl2rtos.AstHelper;

public class Connection {
	private static int connectionIDCounter = 0;
	private int connectionID = 0;
	//private ConnectionType connectionType;
	

	
	private ThreadInstance sourceThreadInstance;
	private ThreadInstance destThreadInstance;
	private OutputPort sourcePort; 
	private InputPort destPort;
	
	//private String semaphore = null;
	
	// 
	// Track source/deestination thread?  Add ISR connection type -- reader function for data port: just don't grab semaphore
	
	public enum ConnectionType {
	    DATA_CONNECTION, EVENT_CONNECTION
	}
		
	public static void init() {
	  Connection.connectionIDCounter = 0;
	}
	
	public Connection(ThreadInstance sourceThreadInstance, 
	                  ThreadInstance destThreadInstance, 
	                  OutputPort sourcePort, 
	                  InputPort destPort) {	
		this.connectionID = connectionIDCounter;
		this.sourceThreadInstance = sourceThreadInstance;
		this.destThreadInstance = destThreadInstance;
		this.sourcePort = sourcePort;
		this.destPort = destPort;
		
		//this.source = connectionInstance.getSource();
		//this.destination = connectionInstance.getDestination();
		//this.semaphore = "connection_" + sourcePort.getName() + 
		//       "_to_" + destPort.getName() + "_" + (Integer.toString(connectionIDCounter));
		Connection.connectionIDCounter++;
	}

	public ThreadInstance getSourceThreadInstance() {
	  return this.sourceThreadInstance;
	}
	
	public ThreadInstance getDestThreadInstance() {
	  return this.destThreadInstance;
	}

	public OutputPort getSourcePort() {
	  return this.sourcePort;
	}
	
	public InputPort getDestPort() {
	  return this.destPort;
	}
	
	public int getConnectionID() {
		return connectionID;
	}
	
	// public String getSemaphoreName() {
	//	return this.semaphore;
	// }
	
}