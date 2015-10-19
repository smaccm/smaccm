package com.rockwellcollins.atc.agree.analysis;

import java.util.List;

import jkind.JKindException;
import jkind.api.results.JKindResult;
import jkind.api.results.PropertyResult;
import jkind.api.results.Renaming;
import jkind.api.results.Status;
import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.UnknownProperty;
import jkind.results.ValidProperty;

public class ConsistencyResult extends JKindResult {

    public ConsistencyResult(String name, List<String> properties, List<Boolean> invertStatus,
            Renaming renaming) {
        super(name, properties, invertStatus, renaming);
    }
}
