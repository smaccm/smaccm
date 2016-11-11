package edu.umn.cs.crisys.smaccm.aadl2rtos.util;

import org.osate.aadl2.impl.BusSubcomponentImpl;
import org.osate.aadl2.impl.DataAccessImpl;
import org.osate.aadl2.impl.DataSubcomponentImpl;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
//import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;

public abstract class PortUtil {

	public static ThreadTypeImpl getThreadFromPortImpl(PortImpl pi) {
		return (ThreadTypeImpl) pi.getContainingClassifier();
	}

	public static PortImpl getPortImplFromConnectionInstanceEnd(ConnectionInstanceEnd ci) {
		if (ci.getInstantiatedObjects().size() != 1) {
		  throw new Aadl2RtosException("Internal error: more than one connection instance per port");
		}
	  if (!(ci.getInstantiatedObjects().get(0) instanceof BusSubcomponentImpl)) {
			return (PortImpl) ci.getInstantiatedObjects().get(0);
		} else {
			return null;
		}
	}
	
  public static DataAccessImpl getDataAccessImplFromConnectionInstanceEnd(ConnectionInstanceEnd ci) {
    if (ci.getInstantiatedObjects().size() != 1) {
      throw new Aadl2RtosException("Internal error: more than one connection instance per port");
    }
    if (!(ci.getInstantiatedObjects().get(0) instanceof BusSubcomponentImpl)) {
      return (DataAccessImpl) ci.getInstantiatedObjects().get(0);
    } else {
      return null;
    }
  }
	
  public static DataSubcomponentImpl getDataSubcomponentImplFromConnectionInstanceEnd(ConnectionInstanceEnd ci) {
    if (ci.getInstantiatedObjects().size() != 1) {
      throw new Aadl2RtosException("Internal error: more than one connection instance per port");
    }
    if (!(ci.getInstantiatedObjects().get(0) instanceof BusSubcomponentImpl)) {
      return (DataSubcomponentImpl) ci.getInstantiatedObjects().get(0);
    } else {
      return null;
    }
  }
  
}