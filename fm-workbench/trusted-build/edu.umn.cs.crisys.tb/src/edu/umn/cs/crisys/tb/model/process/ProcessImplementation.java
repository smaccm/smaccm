package edu.umn.cs.crisys.tb.model.process;

/**
 * @author Mead, Whalen
 * 
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.connection.SharedDataAccessor;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.port.DispatchableInputPort;
import edu.umn.cs.crisys.tb.model.port.DispatcherTraverser;
import edu.umn.cs.crisys.tb.model.port.InitializerPort;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputEventPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutgoingDispatchContract;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroupEndpoint;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

public class ProcessImplementation extends ModelElementBase {
    private String name;
    private  OSModel model;
  
	private List<ProcessInstance> processInstanceList = new ArrayList<ProcessInstance>();
	private List<ThreadImplementation> threadImplementationList = new ArrayList<ThreadImplementation>();
	private Boolean isExternal = false;
  
	/*
	 * Things to revisit:
	 *   - Where mutexes and semaphores are stored.
	 *   - Where shared memory is stored / managed
	 *   - Where RPC endpoints are stored
	 *   - Whether we want to track ports at the process level, or just determine whether
	 *     a connection crosses a thread boundary.
	 */
		
	// Constructor
	public ProcessImplementation(String name) {
      this.name = name;
    }
	
    public OSModel getModel() {
      return model;
    }
  
    public void setModel(OSModel model) {
       this.model= model ;
    }
    
  public String getName() {
    return name;
  }

  public String getNormalizedName() {
    return Util.normalizeAadlName(this.getName());  
  }
  

  /**
   * @return the isExternal
   */
  public Boolean getIsExternal() {
    return isExternal;
  }

  /**
   * @param isExternal the isExternal to set
   */
  public void setIsExternal(Boolean isExternal) {
    this.isExternal = isExternal;
  }


  public void addProcessInstance(ProcessInstance instance) {
	processInstanceList.add(instance);
  }
  
  public List<ProcessInstance> getProcessInstanceList() {
     return processInstanceList; 
  }

  public List<ThreadImplementation> getThreadImplementationList() {
     return this.threadImplementationList; 
  }
 
  public void addThreadImplementation(ThreadImplementation ti) {
     threadImplementationList.add(ti); 
  }
  
  public List<PortFeature> getPortList() {
     List<PortFeature> features = new ArrayList<>(); 
     for (ThreadImplementation ti: threadImplementationList) {
        features.addAll(ti.getPortList());
     }
     return features;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProcessImplementation other = (ProcessImplementation) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

@Override
public ModelElement getParent() {
   return this.getModel();
}

}


