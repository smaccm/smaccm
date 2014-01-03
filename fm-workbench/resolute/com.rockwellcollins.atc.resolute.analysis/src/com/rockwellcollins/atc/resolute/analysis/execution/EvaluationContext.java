package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.SystemOperationMode;

public class EvaluationContext {
    private final ComponentInstance thisInst;
    private final Map<String, Set<NamedElement>> sets;

    public EvaluationContext(ComponentInstance thisInst, Map<String, Set<NamedElement>> sets) {
        this.thisInst = thisInst;
        this.sets = sets;
    }
    
    public EvaluationContext filterByMode(SystemOperationMode mode) {
        Map<String, Set<NamedElement>> filteredSet = new HashMap<>();
        for (Entry<String, Set<NamedElement>> entry : sets.entrySet()) {
            String name = entry.getKey();
            Set<NamedElement> set = entry.getValue();
            filteredSet.put(name, filterBySysMode(set, mode));
        }
        return new EvaluationContext(thisInst, filteredSet);
    }

    private Set<NamedElement> filterBySysMode(Set<NamedElement> set, SystemOperationMode mode) {
        Set<NamedElement> filterSet = new HashSet<>();
        
        for (NamedElement ne : set) {
            if (ne instanceof ConnectionInstance) {
                ConnectionInstance ci = (ConnectionInstance) ne;
                List<SystemOperationMode> modeList = ci.getExistsInModes();
                if (modeList == null || modeList.contains(mode)) {
                    filterSet.add(ci);
                }
            } else {
                ComponentInstance compInst = (ComponentInstance) ne;
                List<SystemOperationMode> modeList = compInst.getExistsInModes();
                if (modeList == null || modeList.contains(mode)) {
                    filterSet.add(compInst);
                }
            }
        }

        return filterSet;
    }

    public ComponentInstance getThisInstance() {
        return thisInst;
    }

    public Set<NamedElement> getSet(String name) {
        Set<NamedElement> set = sets.get(name);
        if (set == null) {
            return Collections.emptySet();
        } else {
            return set;
        }
    }
}
