package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import java.util.Collection;

public class Component {
	
	private String name;
	
	private String entrypoint; 
	
	private Collection<Dataport> dataports; 
	
	private DispatchMechanism dispatchMechanism;
	
	private Integer stacksize = null; 
	
	
	public Component(String n, 
					String e, 
					Collection<Dataport> data, DispatchMechanism mechanism, 
					Integer SS) {
		name = n;
		entrypoint = e;
		dataports = data;
		dispatchMechanism = mechanism;
		stacksize = SS; 
	}
	
	public String getName() { return name ; }
	public String getEntrypoint() { return entrypoint ; }
	public Collection<Dataport> getDataports() { return dataports; }
	public DispatchMechanism getDispatchMechanism() { return dispatchMechanism; }
	public Integer getStackSize() { return stacksize; }
	
	@Override
	public String toString() {
		return "Component " + name;
	}

	@Override
	public int hashCode() {
		return name.hashCode() + dataports.hashCode() + dispatchMechanism.hashCode(); 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Component) {
			Component dp = (Component)obj;
			return (getName().equals(dp.getName()) && 
				   (getDataports().equals(dp.getDataports())) && 
				   (getDispatchMechanism().equals(dp.getDispatchMechanism())));
		}
		else 
			return false;
	}
	
	
}
