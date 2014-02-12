package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import org.osate.aadl2.instance.ConnectionInstance;
//import org.osate.aadl2.instance.ConnectionInstanceEnd;

//import edu.umn.cs.crisys.smaccm.aadl2rtos.AstHelper;

public class Connection {
	private static int connectionIDCounter = 0;
	private int connectionID = 0;
	//private ConnectionType connectionType;
	

	
	private ThreadInstance sourceThreadInstance;
	private ThreadInstance destThreadInstance;
	private MyPort sourcePort; 
	private MyPort destPort;
	
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
	                  MyPort sourcePort, 
	                  MyPort destPort) {	
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

	public MyPort getSourcePort() {
	  return this.sourcePort;
	}
	
	public MyPort getDestPort() {
	  return this.destPort;
	}
	
	public int getConnectionID() {
		return connectionID;
	}
	
	// public String getSemaphoreName() {
	//	return this.semaphore;
	// }
	
}