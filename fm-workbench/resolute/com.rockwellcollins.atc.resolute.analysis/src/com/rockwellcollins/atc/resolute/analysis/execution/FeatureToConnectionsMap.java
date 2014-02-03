package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionReference;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.SystemInstance;

import com.rockwellcollins.atc.resolute.analysis.values.ConnectionValue;

public class FeatureToConnectionsMap {
    private final Map<FeatureInstance, List<ConnectionValue>> map = new HashMap<>();

    public FeatureToConnectionsMap(SystemInstance sysInst) {
        for (ConnectionInstance connInst : sysInst.getAllConnectionInstances()) {
            for (ConnectionReference conRef : connInst.getConnectionReferences()) {
                if (conRef.getSource() instanceof FeatureInstance) {
                    FeatureInstance fi = (FeatureInstance) conRef.getSource();
                    add(fi, connInst);
                }
                if (conRef.getDestination() instanceof FeatureInstance) {
                    FeatureInstance fi = (FeatureInstance) conRef.getDestination();
                    add(fi, connInst);
                }
            }
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
