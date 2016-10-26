package edu.umn.cs.crisys.tb.model.process;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.instance.ComponentInstance;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.connection.EndpointConnection;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;

public class ProcessInstance extends ModelElementBase {
  private static int processIdCounter = 0;
  private int processId = 0;
  
  private ProcessImplementation impl;
	
	private ArrayList<ThreadInstance> threadInstanceList = new ArrayList<ThreadInstance>();
  
	public static void init() {
	  ProcessInstance.processIdCounter = 0;
	}
	
	//output ISR port/ ISR connection b/w outputisrport and inputeventport
	public ProcessInstance(ProcessImplementation impl) {
	  this.impl = impl;
	  this.processId = ProcessInstance.processIdCounter++; 
	}
	
	public int getProcessId() {
		return processId;
	}
	
	public String getName() {
	  return (impl.getName() + "_instance_" + getProcessId()).toLowerCase();
	}
	
    public List<ThreadInstance> getThreadInstanceList() {
    	return threadInstanceList;
    }

   @Override
   public ModelElement getParent() {
      // map over to implementation models.
      // Do we want to have model instances and implementations?
      // I don't think so.
      return this.impl.getParent();
   }
}