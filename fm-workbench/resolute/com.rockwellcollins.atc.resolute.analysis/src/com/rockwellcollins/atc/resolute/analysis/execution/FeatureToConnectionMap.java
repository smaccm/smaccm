package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.HashMap;
import java.util.Map;

import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;

public class FeatureToConnectionMap {
    private final Map<FeatureInstance, ConnectionInstance> map = new HashMap<>();
    
    public FeatureToConnectionMap(SystemInstance sysInst) {
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
                                    map.put(subFeat, connInst);
                                    found = true;
                                    break;
                                }
                            }
                            assert (found);
                        }
                    } else {
                        map.put(featInst, connInst);
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
            map.put(feat, connInst);
        }
    }

    public ConnectionInstance get(FeatureInstance feat) {
        return map.get(feat);
    }
}
