package edu.umn.cs.crisys.smaccm.aadl2camkes.ast;

import java.util.Collection;

public class Component {
	
	private String name;
	
	private Collection<Dataport> dataports; 
	
	private DispatchMechanism dispatchMechanism;
	
	public Component(String n, Collection<Dataport> data, DispatchMechanism mechanism) {
		name = n;
		dataports = data;
		dispatchMechanism = mechanism;
	}
	
	public String getName() { return name ; }
	public Collection<Dataport> getDataports() { return dataports; }
	public DispatchMechanism getDispatchMechanism() { return dispatchMechanism; }
	
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
