package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionReference;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.SystemInstance;


public class FeatureToConnectionsMap {
    private final Map<FeatureInstance, List<ConnectionInstance>> map = new HashMap<>();

    public FeatureToConnectionsMap(SystemInstance sysInst) {
        for (ConnectionInstance connInst : sysInst.getAllConnectionInstances()) {
            for (ConnectionReference conRef : connInst.getConnectionReferences()) {
                if (conRef.getSource() instanceof FeatureInstance) {
                    FeatureInstance fi = (FeatureInstance) conRef.getSource();
                    add(fi, connInst);
                    addForFeatureGroups(fi, connInst);
                }
                if (conRef.getDestination() instanceof FeatureInstance) {
                    FeatureInstance fi = (FeatureInstance) conRef.getDestination();
                    add(fi, connInst);
                    addForFeatureGroups(fi, connInst);
                }
            }
        }
    }

    private void addForFeatureGroups(FeatureInstance fi, ConnectionInstance connInst) {
        EObject parent = fi.eContainer();
        while (parent instanceof FeatureInstance) {
            add((FeatureInstance) parent, connInst);
            parent = parent.eContainer();
        }
    }

    private void add(FeatureInstance feat, ConnectionInstance conn) {
        if (map.containsKey(feat)) {
            map.get(feat).add(conn);
        } else {
            List<ConnectionInstance> list = new ArrayList<>();
            list.add(conn);
            map.put(feat, list);
        }
    }

    public List<ConnectionInstance> get(FeatureInstance feat) {
        if (map.containsKey(feat)) {
            return map.get(feat);
        } else {
            return Collections.emptyList();
        }
    }
}
