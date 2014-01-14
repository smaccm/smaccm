package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;

import com.rockwellcollins.atc.resolute.analysis.values.ConnectionValue;

public class FeatureToConnectionsMap {
    private final Map<FeatureInstance, List<ConnectionValue>> map = new HashMap<>();
    
    public FeatureToConnectionsMap(SystemInstance sysInst) {
        for (ConnectionInstance connInst : sysInst.getAllConnectionInstances()) {
            FeatureInstance prevFeat = null;
            for (InstanceObject instObj : connInst.getThroughFeatureInstances()) {
                if (instObj instanceof FeatureInstance) {
                    FeatureInstance featInst = (FeatureInstance) instObj;
                    // strange hack to handle feature groups... sorry...
                    if (featInst.getCategory() == FeatureCategory.FEATURE_GROUP) {
                        if (prevFeat == null
                                || prevFeat.getCategory() == FeatureCategory.FEATURE_GROUP) {
                            // mark all the features of this group as connected
                            addAllSubFeatsToConnMap(featInst, connInst);
                        } else {
                            boolean found = false;
                            for (FeatureInstance subFeat : featInst.getFeatureInstances()) {
                                if (subFeat.getName().equals(prevFeat.getName())) {
                                    add(subFeat, connInst);
                                    found = true;
                                    break;
                                }
                            }
                            assert (found);
                        }
                    } else {
                        add(featInst, connInst);
                    }
                    prevFeat = featInst;
                }
            }
        }
    }

    private void addAllSubFeatsToConnMap(FeatureInstance feat, ConnectionInstance connInst) {
        for (FeatureInstance subFeat : feat.getFeatureInstances()) {
            addAllSubFeatsToConnMap(subFeat, connInst);
        }
        if (feat.getFeatureInstances().isEmpty()) {
            add(feat, connInst);
        }
    }

    private void add(FeatureInstance feat, ConnectionInstance connInst) {
        if (map.containsKey(feat)) {
            map.get(feat).add(new ConnectionValue(connInst, false));
        } else {
            List<ConnectionValue> list = new ArrayList<>();
            list.add(new ConnectionValue(connInst, false));
            map.put(feat, list);
        }
    }

    public List<ConnectionValue> get(FeatureInstance feat) {
        if (map.containsKey(feat)) {
            return map.get(feat);
        } else {
            return Collections.emptyList();
        }
    }
}
