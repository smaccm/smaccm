package com.rockwellcollins.atc.resolute.analysis;

import java.util.HashSet;
import java.util.Set;

import org.osate.aadl2.NamedElement;

public class ResoluteQuantifiableAadlObjects {
	static public final Set<NamedElement> connectionSet = new HashSet<>();
	static public final Set<NamedElement> componentSet = new HashSet<>();
	static public final Set<NamedElement> propertySet = new HashSet<>();
	static public final Set<NamedElement> dataSet = new HashSet<>();
	static public final Set<NamedElement> threadSet = new HashSet<>();
	static public final Set<NamedElement> threadGroupSet = new HashSet<>();
	static public final Set<NamedElement> processSet = new HashSet<>();
	static public final Set<NamedElement> subprogramSet = new HashSet<>();
	static public final Set<NamedElement> subprogramGroupSet = new HashSet<>();
	static public final Set<NamedElement> processorSet = new HashSet<>();
	static public final Set<NamedElement> virtualProcessorType = new HashSet<>();
	static public final Set<NamedElement> memorySet = new HashSet<>();
	static public final Set<NamedElement> busSet = new HashSet<>();
	static public final Set<NamedElement> virtualBusSet = new HashSet<>();
	static public final Set<NamedElement> deviceSet = new HashSet<>();
	static public final Set<NamedElement> systemSet = new HashSet<>();
	static public final Set<NamedElement> abstractSet = new HashSet<>();

	static public void clearAllSets() {
		ResoluteQuantifiableAadlObjects.abstractSet.clear();
		ResoluteQuantifiableAadlObjects.busSet.clear();
		ResoluteQuantifiableAadlObjects.componentSet.clear();
		ResoluteQuantifiableAadlObjects.connectionSet.clear();
		ResoluteQuantifiableAadlObjects.dataSet.clear();
		ResoluteQuantifiableAadlObjects.deviceSet.clear();
		ResoluteQuantifiableAadlObjects.memorySet.clear();
		ResoluteQuantifiableAadlObjects.processorSet.clear();
		ResoluteQuantifiableAadlObjects.processSet.clear();
		ResoluteQuantifiableAadlObjects.propertySet.clear();
		ResoluteQuantifiableAadlObjects.subprogramGroupSet.clear();
		ResoluteQuantifiableAadlObjects.subprogramSet.clear();
		ResoluteQuantifiableAadlObjects.systemSet.clear();
		ResoluteQuantifiableAadlObjects.threadGroupSet.clear();
		ResoluteQuantifiableAadlObjects.threadSet.clear();
		ResoluteQuantifiableAadlObjects.virtualBusSet.clear();
		ResoluteQuantifiableAadlObjects.virtualProcessorType.clear();
	}

	public static Set<NamedElement> getAllComponentsOfType(String typeStr) {
		switch (typeStr) {
		case "connection":
			return ResoluteQuantifiableAadlObjects.connectionSet;
		case "property":
			return ResoluteQuantifiableAadlObjects.propertySet;
		case "component":
			return ResoluteQuantifiableAadlObjects.componentSet;
		case "abstract":
			return ResoluteQuantifiableAadlObjects.abstractSet;
		case "bus":
			return ResoluteQuantifiableAadlObjects.busSet;
		case "data":
			return ResoluteQuantifiableAadlObjects.dataSet;
		case "device":
			return ResoluteQuantifiableAadlObjects.deviceSet;
		case "memory":
			return ResoluteQuantifiableAadlObjects.memorySet;
		case "process":
			return ResoluteQuantifiableAadlObjects.processSet;
		case "processor":
			return ResoluteQuantifiableAadlObjects.processorSet;
		case "subprogram_group":
			return ResoluteQuantifiableAadlObjects.subprogramGroupSet;
		case "subprogram":
			return ResoluteQuantifiableAadlObjects.subprogramSet;
		case "system":
			return ResoluteQuantifiableAadlObjects.systemSet;
		case "thread_group":
			return ResoluteQuantifiableAadlObjects.threadGroupSet;
		case "thread":
			return ResoluteQuantifiableAadlObjects.threadSet;
		case "virtual_bus":
			return ResoluteQuantifiableAadlObjects.virtualBusSet;
		case "virtual_processor":
			return ResoluteQuantifiableAadlObjects.virtualProcessorType;
		default:
			throw new IllegalArgumentException("Unknown component type: " + typeStr);
		}
	}
}
