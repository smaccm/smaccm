package edu.umn.cs.crisys.tb.model.binding;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.model.process.ProcessInstance;

/*
 * this is going to be our new "model" class.
 */

public class BindingModel {
	
	private List<ProcessInstance> processInstanceList = new ArrayList<ProcessInstance>();
	private BindingModel owner ; 
	private String name;
	
	// For VMs, we will need information on period, budget, priority, etc.
	// Not quite sure how to manage this!
	
	public BindingModel(BindingModel owner, String name) {
		this.owner = owner;
		this.name = name;
	}
	
	public BindingModel getOwner() {
		return this.owner; 
	}

	public String getName() {
		return this.name;
	}
	
	public List<ProcessInstance> getProcessInstanceList() {
		return this.processInstanceList;
	}
}
