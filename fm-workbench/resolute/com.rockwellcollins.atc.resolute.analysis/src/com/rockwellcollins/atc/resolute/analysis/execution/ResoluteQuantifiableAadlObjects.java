package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.SystemOperationMode;

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
    static public final Set<NamedElement> virtualProcessorSet = new HashSet<>();
    static public final Set<NamedElement> memorySet = new HashSet<>();
    static public final Set<NamedElement> busSet = new HashSet<>();
    static public final Set<NamedElement> virtualBusSet = new HashSet<>();
    static public final Set<NamedElement> deviceSet = new HashSet<>();
    static public final Set<NamedElement> systemSet = new HashSet<>();
    static public final Set<NamedElement> abstractSet = new HashSet<>();
    public static Set<NamedElement> abstractModalSet = new HashSet<>();
    public static Set<NamedElement> virtualProcessorModalSet;
    public static Set<NamedElement> virtualBusModalSet;
    public static Set<NamedElement> threadModalSet;
    public static Set<NamedElement> threadGroupModalSet;
    public static Set<NamedElement> systemModalSet;
    public static Set<NamedElement> subprogramModalSet;
    public static Set<NamedElement> subprogramGroupModalSet;
    public static Set<NamedElement> propertyModalSet;
    public static Set<NamedElement> processModalSet;
    public static Set<NamedElement> processorModalSet;
    public static Set<NamedElement> memoryModalSet;
    public static Set<NamedElement> deviceModalSet;
    public static Set<NamedElement> connectionModalSet;
    public static Set<NamedElement> dataModalSet;
    public static Set<NamedElement> componentModalSet;
    public static Set<NamedElement> busModalSet;

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
        ResoluteQuantifiableAadlObjects.virtualProcessorSet.clear();
    }

    public static Set<NamedElement> getAllComponentsOfType(String typeStr, boolean filterModal) {
        switch (typeStr) {
        case "connection":
            
            return filterModal ? connectionModalSet : connectionSet;
        case "property":
            return filterModal ? propertyModalSet : propertySet;
        case "component":
            return filterModal ? componentModalSet : componentSet;
        case "abstract":
            return filterModal ? abstractModalSet : abstractSet;
        case "bus":
            return filterModal ? busModalSet : busSet;
        case "data":
            return filterModal ? dataModalSet : dataSet;
        case "device":
            return filterModal ? deviceModalSet : deviceSet;
        case "memory":
            return filterModal ? memoryModalSet : memorySet;
        case "process":
            return filterModal ? processModalSet : processSet;
        case "processor":
            return filterModal ? processorModalSet : processorSet;
        case "subprogram_group":
            return filterModal ? subprogramGroupModalSet : subprogramGroupSet;
        case "subprogram":
            return filterModal ? subprogramModalSet : subprogramSet;
        case "system":
            return filterModal ? systemModalSet : systemSet;
        case "thread_group":
            return filterModal ? threadGroupModalSet : threadGroupSet;
        case "thread":
            return filterModal ? threadModalSet : threadSet;
        case "virtual_bus":
            return filterModal ? virtualBusModalSet : virtualBusSet;
        case "virtual_processor":
            return filterModal ? virtualProcessorModalSet : virtualProcessorSet;
        default:
            throw new IllegalArgumentException("Unknown component type: " + typeStr);
        }
    }
    
    static private Set<NamedElement> filterBySysMode(Set<NamedElement> set, SystemOperationMode mode){
        Set<NamedElement> filterSet = new HashSet<>();
        ComponentInstance compInst;
        for(NamedElement el : set){
            
            if(el instanceof ConnectionInstance){
                ConnectionInstance connInst = (ConnectionInstance)el;
                List<SystemOperationMode> modeList = connInst.getExistsInModes();

                if(modeList == null || modeList.contains(mode))
                    filterSet.add(connInst);

            }else{

                compInst = (ComponentInstance)el;
                List<SystemOperationMode> modeList = compInst.getExistsInModes();

                if(modeList == null || modeList.contains(mode)){
                    filterSet.add(compInst);
                }
            }
        }
        
        return filterSet;
    }
    
    static public void filterBySysMode(SystemOperationMode mode){

        abstractModalSet = filterBySysMode(abstractSet, mode);
        busModalSet = filterBySysMode(busSet, mode);        
        componentModalSet = filterBySysMode(componentSet, mode);       
        dataModalSet = filterBySysMode(dataSet, mode);      
        connectionModalSet = filterBySysMode(connectionSet, mode);        
        deviceModalSet = filterBySysMode(deviceSet, mode);       
        memoryModalSet = filterBySysMode(memorySet, mode);        
        processorModalSet = filterBySysMode(processorSet, mode);        
        processModalSet = filterBySysMode(processSet, mode);        
        propertyModalSet = filterBySysMode(propertySet, mode);        
        subprogramGroupModalSet = filterBySysMode(subprogramGroupSet, mode);        
        subprogramModalSet = filterBySysMode(subprogramSet, mode);        
        systemModalSet = filterBySysMode(systemSet, mode);       
        threadGroupModalSet = filterBySysMode(threadGroupSet, mode);      
        threadModalSet = filterBySysMode(threadSet, mode);   
        virtualBusModalSet = filterBySysMode(virtualBusSet, mode);  
        virtualProcessorModalSet = filterBySysMode(virtualProcessorSet, mode);
                
    }
    
    
    
    
}
