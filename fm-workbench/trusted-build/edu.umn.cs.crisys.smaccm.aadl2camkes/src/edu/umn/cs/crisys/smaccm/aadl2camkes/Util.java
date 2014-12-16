package edu.umn.cs.crisys.smaccm.aadl2camkes;

import java.util.Collection;

import org.osate.aadl2.NamedElement;

public class Util {

	public static String normalizeAadlName(String name) {
		return name.replaceAll("[^A-Za-z0-9]", "_"); 
	}
	
	public static String normalizeAadlName(NamedElement n) {
		return normalizeAadlName(n.getQualifiedName());
	}
	
	public static String toSafeString(Object e) {
		try {
			return e.toString();
		}
		catch (Throwable t) {
			return "Unable to print toString()";
		}
	}
	
	//@SuppressWarnings("rawtypes")
	public static <T> String toSafeStringCollection(Collection<T> c) {
		boolean first = true; 
		StringBuffer sb = new StringBuffer(); 
		sb.append("[");
		for (Object e: c) {
			if (!first) {
				sb.append(", ");
			} else {
				first = false;
			}
			sb.append(toSafeString(e)) ; 
		}
		sb.append("]"); 
		
		return sb.toString();
	}
}
