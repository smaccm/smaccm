package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;

public class ClaimCallContext {
    final private FunctionDefinition fnDef;
    final private List<ResoluteValue> argVals;

    public ClaimCallContext(FunctionDefinition fnDef, List<ResoluteValue> argVals) {
        assert fnDef != null && argVals != null;
        this.fnDef = fnDef;
        this.argVals = argVals;
    }

    @Override
    public int hashCode() {
        return fnDef.hashCode() + argVals.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClaimCallContext)) {
            return false;
        }

        ClaimCallContext other = (ClaimCallContext) obj;
        return fnDef.equals(other.fnDef) && argVals.equals(other.argVals);
    }
}
