package com.rockwellcollins.atc.resolute.analysis.values;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class BoolValue extends ResoluteValue {
    final private boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public boolean getBool() {
        return value;
    }

    @Override
    public ResoluteType getType() {
        return BaseType.BOOL;
    }
    
    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public int hashCode() {
        return value ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoolValue) {
            BoolValue bv = (BoolValue) obj;
            return bv.value == value;
        }

        return false;
    }
}
