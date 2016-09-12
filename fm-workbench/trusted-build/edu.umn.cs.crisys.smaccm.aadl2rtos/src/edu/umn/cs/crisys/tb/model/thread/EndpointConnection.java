package edu.umn.cs.crisys.tb.model.thread;

import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint;


public class EndpointConnection {
  private ThreadInstance requiresThreadInstance;
	private ThreadInstance providesThreadInstance;
	private RemoteProcedureGroupEndpoint requires; 
	private RemoteProcedureGroupEndpoint provides;
	
	//private String semaphore = null;
	
	// 
	// Track source/deestination thread?  Add ISR connection type -- reader function for data port: just don't grab semaphore
	
	public enum ConnectionType {
	    DATA_CONNECTION, EVENT_CONNECTION
	}
			
	public EndpointConnection(ThreadInstance requiresThreadInstance, 
	                  RemoteProcedureGroupEndpoint requires, 
                    ThreadInstance providesThreadInstance, 
	                  RemoteProcedureGroupEndpoint provides) {	
		this.requiresThreadInstance = requiresThreadInstance;
		this.providesThreadInstance = providesThreadInstance;
		this.requires = requires;
		this.provides = provides;
	}

	public ThreadInstance getRequiresThreadInstance() {
	  return this.requiresThreadInstance;
	}
	
	public ThreadInstance getProvidesThreadInstance() {
	  return this.providesThreadInstance;
	}

	public RemoteProcedureGroupEndpoint getRequiresEndpoint() {
	  return this.requires;
	}
	
	public RemoteProcedureGroupEndpoint getProvidesEndpoint() {
	  return this.provides;
	}
	
}