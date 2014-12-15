package edu.umn.cs.crisys.smaccm.aadl2camkes.ast;

public class Dataport {

	String id;
	Type type; 
	
	public Dataport(String _portId, Type _portType) {
		id = _portId;
		type = _portType; 
	}
	
	public String getId() { return id; }
	
	public Type getType() { return type; }
	
	@Override
	public String toString() {
		return "Dataport " + id + " : " + (type.toString());
	}

	@Override
	public int hashCode() {
		return id.hashCode() * type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dataport) {
			Dataport dp = (Dataport)obj;
			return id.equals(dp.id) && 
				   type.equals(dp.type);
		}
		else 
			return false;
	}

}
